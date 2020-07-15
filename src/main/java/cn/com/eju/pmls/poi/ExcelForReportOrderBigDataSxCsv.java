package cn.com.eju.pmls.poi;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.pmls.poi.common.ExcelSXSSFCommon;
import cn.com.eju.pmls.poi.common.ExcelStyleEnumSxssf;
import com.alibaba.fastjson.JSON;
import com.csvreader.CsvReader;
import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 生成报表文件
 * */
public class ExcelForReportOrderBigDataSxCsv extends ExcelSXSSFCommon {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    private SXSSFWorkbook workBook;
    private Sheet sheet;
    public static char separator = ',';

    private CellStyle bodyStyle;
    private CellStyle dateStyle;
    private CellStyle moneyStyle;
    private CellStyle intStyle;

    private CellStyle bodyBlackStyle;
    private CellStyle dateBlackStyle;
    private CellStyle moneyBlackStyle;
    private CellStyle intBlackStyle;

    private CellStyle bodyRedStyle;
    private CellStyle dateRedStyle;
    private CellStyle moneyRedStyle;
    private CellStyle intRedStyle;

    private CellStyle dateMinuteBlackStyle;

    public void downloadSheet(Map<String, Object> reqMap, File outPutFile) {
        logger.info("订单明细报表##导出##downloadSheet start##userId="+reqMap.get("userId")+"##reqMap=" + JSON.toJSONString(reqMap));
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try{
            String filename = "reportOrderDetail.xlsx";
            String templatePath = (String) reqMap.get("templatePath");
            String srcPath = templatePath + "template" + File.separator + ReportConstant.REPORT_ORDER_DETAIL_CODE + File.separator + filename;
            reqMap.put("templatePath", null);
            File srcPathFile = new File(srcPath);
            logger.info("订单明细报表##导出##downloadSheet 模板路径##"+srcPath +"##userId="+reqMap.get("userId"));
            inputStream = new FileInputStream(srcPathFile.getAbsolutePath());
            OPCPackage pkg = OPCPackage.open(inputStream);
            XSSFWorkbook xssfwb = new XSSFWorkbook(pkg);

            workBook = new SXSSFWorkbook(xssfwb,50);
            sheet = workBook.getSheetAt(0);


            Map<String, CellStyle> styleMap = createStyles(workBook);
            bodyBlackStyle = styleMap.get(ExcelStyleEnumSxssf.BODY_BLACK.getValue());
            dateBlackStyle = styleMap.get(ExcelStyleEnumSxssf.DATE_BLACK.getValue());
            moneyBlackStyle = styleMap.get(ExcelStyleEnumSxssf.MONEY_UNLOCKED_BLACK.getValue());
            intBlackStyle = styleMap.get(ExcelStyleEnumSxssf.INT_BLACK.getValue());
            dateMinuteBlackStyle = styleMap.get(ExcelStyleEnumSxssf.DATE_MINUTE_BLACK.getValue());

            bodyRedStyle = styleMap.get(ExcelStyleEnumSxssf.BODY_RED.getValue());
            dateRedStyle = styleMap.get(ExcelStyleEnumSxssf.DATE_RED.getValue());
            moneyRedStyle = styleMap.get(ExcelStyleEnumSxssf.MONEY_UNLOCKED_RED.getValue());
            intRedStyle = styleMap.get(ExcelStyleEnumSxssf.INT_RED.getValue());


            Integer userId = 0;
            if(reqMap.get("userId")!=null){
                userId = Integer.parseInt(reqMap.get("userId").toString());
            }
            String pathNameCsv = (String)reqMap.get("pathNameCsv");
            logger.info("订单明细报表##导出##生成数据 readCSV start userid="+userId);
            int rowIndex = readCSV(pathNameCsv,userId);
            logger.info("订单明细报表##导出##生成数据 readCSV end ##userId="+ userId+"##rowIndex="+rowIndex+"##reqMap=" + JSON.toJSONString(reqMap));

            outputStream = new FileOutputStream(outPutFile);
            workBook.write(outputStream);
        }catch (Throwable e){
            logger.error("订单明细报表##导出##downloadSheet 异常##userId="+reqMap.get("userId")+"##reqMap=" + JSON.toJSONString(reqMap),e);
            //记入--错误日志表
            logger.error("excelForReportOrderBigDataSxCsv",
                    "ExcelForReportOrderBigDataSxCsv",
                    "downloadSheet()",
                    null,
                    UserInfoHolder.getUserId(),
                    null,
                    null,
                    e);
        }finally {
            IOUtils.closeQuietly(outputStream);
            IOUtils.closeQuietly(inputStream);
            workBook.dispose();
        }
        logger.info("订单明细报表##导出##downloadSheet end##userId="+reqMap.get("userId")+"##reqMap=" + JSON.toJSONString(reqMap));
    }

    /**
     * 创建sheet具体内容
     */
    private int createSheetBody(LinkedHashMap<String, Object> map, int rowIndex,Integer userId) throws Exception {
        //logger.info("订单明细报表##导出##生成数据 createSheetBody start userid="+userId+"##rowIndex="+rowIndex);
        if (map != null && map.get("rowNum") != null) {
                int columnIndex = 0;
                Row row = sheet.createRow(rowIndex);
                bodyStyle       =       bodyBlackStyle;
                dateStyle       =       dateBlackStyle;
                moneyStyle      =       moneyBlackStyle;
                intStyle        =       intBlackStyle;

                super.createCellStr(workBook,row,columnIndex++, map.get("rowNum") == null ? "" : map.get("rowNum").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("accountProjectNo") == null ? "" : map.get("accountProjectNo").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("accountProject") == null ? "" : map.get("accountProject").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("org_regionName") == null ? "" : map.get("org_regionName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("org_areaCityName") == null ? "" : map.get("org_areaCityName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("cityGroupName") == null ? "" : map.get("cityGroupName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("areaGroupName") == null ? "" : map.get("areaGroupName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("centerGroupName") == null ? "" : map.get("centerGroupName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("groupName") == null ? "" : map.get("groupName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("expenderName") == null ? "" : map.get("expenderName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("expenderCode") == null ? "" : map.get("expenderCode").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("projectCityName") == null ? "" : map.get("projectCityName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("projectDepartmentName") == null ? "" : map.get("projectDepartmentName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("projectKfName") == null ? "" : map.get("projectKfName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("projectKfCode") == null ? "" : map.get("projectKfCode").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("projectFzName") == null ? "" : map.get("projectFzName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("projectFzCode") == null ? "" : map.get("projectFzCode").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("reportNo") == null ? "" : map.get("reportNo").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("reportStatus") == null ? "" : map.get("reportStatus").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("confirmStatus") == null ? "" : map.get("confirmStatus").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("customerNm") == null ? "" : map.get("customerNm").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("customerTel") == null ? "" : map.get("customerTel").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("buildingNo") == null ? "" : map.get("buildingNo").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("projectNo") == null ? "" : map.get("projectNo").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("estateNm") == null ? "" : map.get("estateNm").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("companyNo") == null ? "" : map.get("companyNo").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("companyNm") == null ? "" : map.get("companyNm").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("topbrandName") == null ? "" : map.get("topbrandName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("storeNo") == null ? "" : map.get("storeNo").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("storeName") == null ? "" : map.get("storeName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("reportAgent") == null ? "" : map.get("reportAgent").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("reportAgentTel") == null ? "" : map.get("reportAgentTel").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("reportType") == null ? "" : map.get("reportType").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("customerFromName") == null ? "" : map.get("customerFromName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("reportDtlStatus") == null ? "" : map.get("reportDtlStatus").toString(), bodyStyle);
                super.createCellDateSpace(workBook,row,columnIndex++, map.get("reportDate") == null ? "" : map.get("reportDate").toString(), dateMinuteBlackStyle);
                super.createCellDateSpace(workBook,row,columnIndex++, map.get("reportAuditDate") == null ? "" : map.get("reportAuditDate").toString(), dateMinuteBlackStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("seeDtlStatus") == null ? "" : map.get("seeDtlStatus").toString(), bodyStyle);
                super.createCellDateSpace(workBook,row,columnIndex++, map.get("seeDate") == null ? "" : map.get("seeDate").toString(), dateStyle);
                super.createCellDateSpace(workBook,row,columnIndex++, map.get("seeCrtDate") == null ? "" : map.get("seeCrtDate").toString(), dateStyle);
                super.createCellDateSpace(workBook,row,columnIndex++, map.get("roughInputDate") == null ? "" : map.get("roughInputDate").toString(), dateStyle);
                super.createCellMoneySpace(workBook,row,columnIndex++, map.get("roughAmount") == null ? "" : map.get("roughAmount").toString(), moneyBlackStyle);
                super.createCellDateSpace(workBook,row,columnIndex++, map.get("roughAuditTime") == null ? "" : map.get("roughAuditTime").toString(), dateStyle);
                super.createCellDateSpace(workBook,row,columnIndex++, map.get("dealDate") == null ? "" : map.get("dealDate").toString(), dateStyle);
                super.createCellMoneySpace(workBook,row,columnIndex++, map.get("dealAmount") == null ? "" : map.get("dealAmount").toString(), moneyBlackStyle);
                super.createCellMoneySpace(workBook,row,columnIndex++, map.get("area") == null ? "" : map.get("area").toString(), moneyBlackStyle);
                super.createCellMoneySpace(workBook,row,columnIndex++, map.get("yjsr_BefTaxAmount") == null ? "" : map.get("yjsr_BefTaxAmount").toString(), moneyBlackStyle);
                super.createCellMoneySpace(workBook,row,columnIndex++, map.get("yjsr_AftTaxAmount") == null ? "" : map.get("yjsr_AftTaxAmount").toString(), moneyBlackStyle);
                super.createCellMoneySpace(workBook,row,columnIndex++, map.get("yssr_BefTaxAmount") == null ? "" : map.get("yssr_BefTaxAmount").toString(), moneyBlackStyle);
                super.createCellMoneySpace(workBook,row,columnIndex++, map.get("yssr_AftTaxAmount") == null ? "" : map.get("yssr_AftTaxAmount").toString(), moneyBlackStyle);
                super.createCellMoneySpace(workBook,row,columnIndex++, map.get("yiHuiKuanAmount") == null ? "" : map.get("yiHuiKuanAmount").toString(), moneyBlackStyle);
                super.createCellMoneySpace(workBook,row,columnIndex++, map.get("yjJiWeiHuiKuanAt") == null ? "" : map.get("yjJiWeiHuiKuanAt").toString(), moneyBlackStyle);
                super.createCellMoneySpace(workBook,row,columnIndex++, map.get("yiShouWeiHuiKuanAt") == null ? "" : map.get("yiShouWeiHuiKuanAt").toString(), moneyBlackStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("kpzt") == null ? "" : map.get("kpzt").toString(), bodyStyle);
                super.createCellMoneySpace(workBook,row,columnIndex++, map.get("kpAmount") == null ? "" : map.get("kpAmount").toString(), moneyBlackStyle);
                super.createCellDateSpace(workBook,row,columnIndex++, map.get("lastKpDate") == null ? "" : map.get("lastKpDate").toString(), dateStyle);
                super.createCellDateSpace(workBook,row,columnIndex++, map.get("lastHkDate") == null ? "" : map.get("lastHkDate").toString(), dateStyle);
                super.createCellMoneySpace(workBook,row,columnIndex++, map.get("yjdy_BefTaxAmount") == null ? "" : map.get("yjdy_BefTaxAmount").toString(), moneyBlackStyle);
                super.createCellMoneySpace(workBook,row,columnIndex++, map.get("sjdy_BefTaxAmount") == null ? "" : map.get("sjdy_BefTaxAmount").toString(), moneyBlackStyle);
                super.createCellMoneySpace(workBook,row,columnIndex++, map.get("shengYuDyAt") == null ? "" : map.get("shengYuDyAt").toString(), moneyBlackStyle);
                super.createCellMoneySpace(workBook,row,columnIndex++, map.get("yjfy_BefTaxAmount") == null ? "" : map.get("yjfy_BefTaxAmount").toString(), moneyBlackStyle);
                super.createCellMoneySpace(workBook,row,columnIndex++, map.get("sjfy_BefTaxAmount") == null ? "" : map.get("sjfy_BefTaxAmount").toString(), moneyBlackStyle);
                super.createCellMoneySpace(workBook,row,columnIndex++, map.get("shengYuFyAt") == null ? "" : map.get("shengYuFyAt").toString(), moneyBlackStyle);
                super.createCellMoneySpace(workBook,row,columnIndex++, map.get("requestAmount") == null ? "" : map.get("requestAmount").toString(), moneyBlackStyle);
                super.createCellDateSpace(workBook,row,columnIndex++, map.get("lastQkApproveTime") == null ? "" : map.get("lastQkApproveTime").toString(), dateStyle);
                super.createCellMoneySpace(workBook,row,columnIndex++, map.get("yiZhiFuAt") == null ? "" : map.get("yiZhiFuAt").toString(), moneyBlackStyle);
                super.createCellDateSpace(workBook,row,columnIndex++, map.get("recordDate") == null ? "" : map.get("recordDate").toString(), dateStyle);
                super.createCellMoneySpace(workBook,row,columnIndex++, map.get("shengYuWeiShouPiaoAt") == null ? "" : map.get("shengYuWeiShouPiaoAt").toString(), moneyBlackStyle);
                super.createCellMoneySpace(workBook,row,columnIndex++, map.get("yjny_postAmount") == null ? "" : map.get("yjny_postAmount").toString(), moneyBlackStyle);
                super.createCellMoneySpace(workBook,row,columnIndex++, map.get("yjny_totalAmount") == null ? "" : map.get("yjny_totalAmount").toString(), moneyBlackStyle);
                super.createCellMoneySpace(workBook,row,columnIndex++, map.get("pledgedBackAt") == null ? "" : map.get("pledgedBackAt").toString(), moneyBlackStyle);
                super.createCellDateSpace(workBook,row,columnIndex++, map.get("roughBackDate") == null ? "" : map.get("roughBackDate").toString(), dateStyle);
                super.createCellDateSpace(workBook,row,columnIndex++, map.get("dealBackDate") == null ? "" : map.get("dealBackDate").toString(), dateStyle);
        }
        //logger.info("订单明细报表##导出##生成数据 createSheetBody end userid="+userId+"##rowIndex="+rowIndex);
        return rowIndex;
    }




    /**
     * 读取CSV文件
     * @param filePath:全路径名
     */
    public int readCSV(String filePath,Integer userId) throws Exception {
        int rowIndex = 2;
        CsvReader reader = null;
/*        List<LinkedHashMap<String, Object>> lilst = new ArrayList<>();*/
        try {
            //如果生产文件乱码，windows下用gbk，linux用UTF-8
            String csvReadCharset = SystemParam.getWebConfigValue("csvReadCharset");
            reader = new CsvReader(filePath, separator, Charset.forName(csvReadCharset));

            // 读取表头
//            reader.readHeaders();
//            String[] headArray = reader.getHeaders();//获取标题
//            System.out.println(headArray[0] + headArray[1] + headArray[2]);

            // 逐条读取记录，直至读完
            //int i = 0;

            while (reader.readRecord()) {
                //i++;
                //if(i>3) {
                    LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
                    map.put("rowNum", reader.get(0));
                    map.put("accountProjectNo", reader.get(1));
                    map.put("accountProject", reader.get(2));
                    map.put("org_regionName", reader.get(3));
                    map.put("org_areaCityName", reader.get(4));
                    map.put("cityGroupName", reader.get(5));
                    map.put("areaGroupName", reader.get(6));
                    map.put("centerGroupName", reader.get(7));
                    map.put("groupName", reader.get(8));
                    map.put("expenderName", reader.get(9));
                    map.put("expenderCode", reader.get(10));
                    map.put("projectCityName", reader.get(11));
                    map.put("projectDepartmentName", reader.get(12));
                    map.put("projectKfName", reader.get(13));
                    map.put("projectKfCode", reader.get(14));
                    map.put("projectFzName", reader.get(15));
                    map.put("projectFzCode", reader.get(16));
                    map.put("reportNo", reader.get(17));
                    map.put("reportStatus", reader.get(18));
                    map.put("confirmStatus", reader.get(19));
                    map.put("customerNm", reader.get(20));
                    map.put("customerTel", reader.get(21));
                    map.put("buildingNo", reader.get(22));
                    map.put("projectNo", reader.get(23));
                    map.put("estateNm", reader.get(24));
                    map.put("companyNo", reader.get(25));
                    map.put("companyNm", reader.get(26));
                    map.put("topbrandName", reader.get(27));
                    map.put("storeNo", reader.get(28));
                    map.put("storeName", reader.get(29));
                    map.put("reportAgent", reader.get(30));
                    map.put("reportAgentTel", reader.get(31));
                    map.put("reportType", reader.get(32));
                    map.put("customerFromName", reader.get(33));
                    map.put("reportDtlStatus", reader.get(34));
                    map.put("reportDate", reader.get(35));
                    map.put("reportAuditDate", reader.get(36));
                    map.put("seeDtlStatus", reader.get(37));
                    map.put("seeDate", reader.get(38));
                    map.put("seeCrtDate", reader.get(39));
                    map.put("roughInputDate", reader.get(40));
                    map.put("roughAmount", reader.get(41));
                    map.put("roughAuditTime", reader.get(42));
                    map.put("dealDate", reader.get(43));
                    map.put("dealAmount", reader.get(44));
                    map.put("area", reader.get(45));
                    map.put("yjsr_BefTaxAmount", reader.get(46));
                    map.put("yjsr_AftTaxAmount", reader.get(47));
                    map.put("yssr_BefTaxAmount", reader.get(48));
                    map.put("yssr_AftTaxAmount", reader.get(49));
                    map.put("yiHuiKuanAmount", reader.get(50));
                    map.put("yjJiWeiHuiKuanAt", reader.get(51));
                    map.put("yiShouWeiHuiKuanAt", reader.get(52));
                    map.put("kpzt", reader.get(53));
                    map.put("kpAmount", reader.get(54));
                    map.put("lastKpDate", reader.get(55));
                    map.put("lastHkDate", reader.get(56));
                    map.put("yjdy_BefTaxAmount", reader.get(57));
                    map.put("sjdy_BefTaxAmount", reader.get(58));
                    map.put("shengYuDyAt", reader.get(59));
                    map.put("yjfy_BefTaxAmount", reader.get(60));
                    map.put("sjfy_BefTaxAmount", reader.get(61));
                    map.put("shengYuFyAt", reader.get(62));
                    map.put("requestAmount", reader.get(63));
                    map.put("lastQkApproveTime", reader.get(64));
                    map.put("yiZhiFuAt", reader.get(65));
                    map.put("recordDate", reader.get(66));
                    map.put("shengYuWeiShouPiaoAt", reader.get(67));
                    map.put("yjny_postAmount", reader.get(68));
                    map.put("yjny_totalAmount", reader.get(69));
                    map.put("pledgedBackAt", reader.get(70));
                    map.put("roughBackDate", reader.get(71));
                    map.put("dealBackDate", reader.get(72));
                    if (map.get("rowNum") != null) {
                        createSheetBody(map, rowIndex, userId);
                        rowIndex++;
                    }
                //}
            }
        } catch (Exception e) {
                logger.error("订单明细报表##导出##readCSV 异常"+"##userId="+userId,e);
                //记入--错误日志表
                logger.error("excelForReportOrderBigDataSxCsv",
                        "ExcelForReportOrderBigDataSxCsv",
                        "readCSV()",
                        null,
                        UserInfoHolder.getUserId(),
                        null,
                        null,
                        e);
        } finally {
            if (null != reader) {
                reader.close();
            }
        }
        return rowIndex;
    }
}
