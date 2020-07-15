package cn.com.eju.pmls.poi;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.houseLinkage.linkDetail.service.LinkDetailService;
import cn.com.eju.deal.poi.common.ExcelStyleEnum;
import cn.com.eju.pmls.poi.common.ExcelSXSSFCommon;
import cn.com.eju.pmls.poi.common.ExcelStyleEnumSxssf;
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成报表文件
 * */
public class ExcelForLinkDetailBigDataSxCsv extends ExcelSXSSFCommon {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    private LinkDetailService linkDetailService = new LinkDetailService();

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



    public void downloadSheet(Map<String, Object> reqMap, File outPutFile, String yearly) {
        logger.info("联动明细报表##导出##downloadSheet start ##userId="+reqMap.get("userId"));
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try{
            String filename = "linkDetail_"+yearly+".xlsx";
            String templatePath = (String) reqMap.get("templatePath");
            String srcPath = templatePath + "template" + File.separator + "linkDetail" + File.separator + filename;
            reqMap.put("templatePath", null);
            File srcPathFile = new File(srcPath);
            logger.info("联动明细报表##导出##downloadSheet 模板路径##"+srcPath +"##userId="+reqMap.get("userId"));
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


            bodyRedStyle = styleMap.get(ExcelStyleEnumSxssf.BODY_RED.getValue());
            dateRedStyle = styleMap.get(ExcelStyleEnumSxssf.DATE_RED.getValue());
            moneyRedStyle = styleMap.get(ExcelStyleEnumSxssf.MONEY_UNLOCKED_RED.getValue());
            intRedStyle = styleMap.get(ExcelStyleEnumSxssf.INT_RED.getValue());


            Integer userId = 0;
            if(reqMap.get("userId")!=null){
                userId = Integer.parseInt(reqMap.get("userId").toString());
            }
            String pathNameCsv = (String)reqMap.get("pathNameCsv");
            logger.info("联动明细报表##导出##生成数据 readCSV start userid="+userId);
            int rowIndex = readCSV(pathNameCsv,userId);
            logger.info("联动明细报表##导出##生成数据 readCSV end userid="+userId+"##rowIndex="+rowIndex);

            outputStream = new FileOutputStream(outPutFile);
            workBook.write(outputStream);
        }catch (Throwable e){
            logger.error("联动明细报表##导出##downloadSheet 异常"+"##userId="+reqMap.get("userId"),e);
            //记入--错误日志表
            logger.error("excelForLinkDetailBigDataSxssf",
                    "ExcelForLinkDetailBigDataSxssf",
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
        logger.info("联动明细报表##导出##downloadSheet end"+"##userId="+reqMap.get("userId"));
    }

    /**
     * 创建sheet具体内容
     */
    private int createSheetBody(LinkedHashMap<String, Object> map, int rowIndex,Integer userId) throws Exception {
        //logger.info("联动明细报表##导出##生成数据 createSheetBody start userid="+userId+"##rowIndex="+rowIndex);
        if (map != null && map.get("suitNum") != null) {
                int columnIndex = 0;
                Row row = sheet.createRow(rowIndex);
                bodyStyle = "1".equals(map.get("suitNum").toString()) ? bodyBlackStyle : bodyRedStyle;
                dateStyle = "1".equals(map.get("suitNum").toString()) ? dateBlackStyle : dateRedStyle;
                moneyStyle = "1".equals(map.get("suitNum").toString()) ? moneyBlackStyle : moneyRedStyle;
                intStyle = "1".equals(map.get("suitNum").toString()) ? intBlackStyle : intRedStyle;

                super.createCellStr(workBook,row,columnIndex++, map.get("rowNum") == null ? "" : map.get("rowNum").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("accountProjectNo") == null ? "" : map.get("accountProjectNo").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("accountProject") == null ? "" : map.get("accountProject").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("regionName") == null ? "" : map.get("regionName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("areaCityName") == null ? "" : map.get("areaCityName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("cityGroupName") == null ? "" : map.get("cityGroupName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("areaGroupName") == null ? "" : map.get("areaGroupName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("centerGroupName") == null ? "" : map.get("centerGroupName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("groupName") == null ? "" : map.get("groupName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("expenderName") == null ? "" : map.get("expenderName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("expenderCode") == null ? "" : map.get("expenderCode").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("projectCityName") == null ? "" : map.get("projectCityName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("projectDepartmentName") == null ? "" : map.get("projectDepartmentName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("srCityName") == null ? "" : map.get("srCityName").toString(), bodyStyle);

                super.createCellStr(workBook,row,columnIndex++, map.get("customerFromName") == null ? "" : map.get("customerFromName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("storeNo") == null ? "" : map.get("storeNo").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("storeName") == null ? "" : map.get("storeName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("companyName") == null ? "" : map.get("companyName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("addressDetail") == null ? "" : map.get("addressDetail").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("storeSize") == null ? "" : map.get("storeSize").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("contractTypeName") == null ? "" : map.get("contractTypeName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("saleName") == null ? "" : map.get("saleName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("salePhone") == null ? "" : map.get("salePhone").toString(), bodyStyle);

                super.createCellStr(workBook,row,columnIndex++, map.get("customerNm") == null ? "" : map.get("customerNm").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("customerTel") == null ? "" : map.get("customerTel").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("customerNum") == null ? "" : map.get("customerNum").toString(), bodyStyle);

                super.createCellStr(workBook,row,columnIndex++, map.get("estateId") == null ? "" : map.get("estateId").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("projectNo") == null ? "" : map.get("projectNo").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("estateNm") == null ? "" : map.get("estateNm").toString(), bodyStyle);

                super.createCellStr(workBook,row,columnIndex++, map.get("partnerNm") == null ? "" : map.get("partnerNm").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("bigCustomerFlagStr") == null ? "" : map.get("bigCustomerFlagStr").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("bigCustomerName") == null ? "" : map.get("bigCustomerName").toString(), bodyStyle);

                super.createCellStr(workBook,row,columnIndex++, map.get("prepaidName") == null ? "" : map.get("prepaidName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("reportId") == null ? "" : map.get("reportId").toString(), bodyStyle);
                super.createCellDate(workBook,row,columnIndex++, map.get("reportDate"), dateStyle);
                super.createCellDate(workBook,row,columnIndex++, map.get("seeDate"),dateStyle);
                super.createCellDate(workBook,row,columnIndex++, map.get("pledgedDate"),dateStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("buildingNo") == null ? "" : map.get("buildingNo").toString(), bodyStyle);
                super.createCellInt(workBook,row,columnIndex++, clearIntZero(map.get("suitNum")), intStyle);

                String preBack = (String)map.get("preBack");
                String preBackStr = "";
                if("1".equals(preBack)){
                    preBackStr = "预退定";
                }else if("2".equals(preBack)){
                    preBackStr = "预退房";
                }
                super.createCellStr(workBook,row,columnIndex++, preBackStr, bodyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("roughArea")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("roughPrice")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("roughAmount")), moneyStyle);
                super.createCellDate(workBook,row,columnIndex++, map.get("roughDate"),dateStyle);
                super.createCellDate(workBook,row,columnIndex++, map.get("roughAuditTime"),dateStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("dealArea")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("dealPrice")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("dealAmount")), moneyStyle);
                super.createCellDate(workBook,row,columnIndex++, map.get("dealDate"),dateStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("incomeStatusStr") == null ? "" : map.get("incomeStatusStr").toString(), bodyStyle);

                /********* 应计 begin **********/

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("totalEPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("totalEATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("totalEPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("totalEATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("totalEPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("totalEATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("totalEPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("totalEATPrepaid")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("beforeEPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("beforeEATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("beforeEPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("beforeEATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("beforeEPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("beforeEATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("beforeEPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("beforeEATPrepaid")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("thisEPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("thisEATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("thisEPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("thisEATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("thisEPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("thisEATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("thisEPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("thisEATPrepaid")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("janEPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("janEATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("janEPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("janEATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("janEPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("janEATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("janEPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("janEATPrepaid")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("febEPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("febEATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("febEPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("febEATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("febEPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("febEATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("febEPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("febEATPrepaid")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("marEPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("marEATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("marEPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("marEATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("marEPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("marEATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("marEPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("marEATPrepaid")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("aprEPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("aprEATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("aprEPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("aprEATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("aprEPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("aprEATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("aprEPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("aprEATPrepaid")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("mayEPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("mayEATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("mayEPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("mayEATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("mayEPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("mayEATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("mayEPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("mayEATPrepaid")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("junEPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("junEATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("junEPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("junEATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("junEPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("junEATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("junEPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("junEATPrepaid")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("julEPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("julEATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("julEPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("julEATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("julEPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("julEATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("julEPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("julEATPrepaid")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("augEPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("augEATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("augEPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("augEATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("augEPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("augEATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("augEPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("augEATPrepaid")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("sepEPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("sepEATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("sepEPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("sepEATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("sepEPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("sepEATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("sepEPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("sepEATPrepaid")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("octEPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("octEATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("octEPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("octEATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("octEPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("octEATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("octEPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("octEATPrepaid")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("novEPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("novEATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("novEPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("novEATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("novEPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("novEATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("novEPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("novEATPrepaid")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("decEPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("decEATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("decEPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("decEATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("decEPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("decEATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("decEPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("decEATPrepaid")), moneyStyle);

                /********* 应计 end **********/

                /********* 应收 begin **********/
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("totalRBPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("totalRBATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("beforeRBPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("beforeRBATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("thisRBPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("thisRBATIncome")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("janRBPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("janRBATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("febRBPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("febRBBATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("marRBPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("marRBATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("aprRBPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("aprRBATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("mayRBPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("mayRBATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("junRBPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("junRBATIncome")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("julRBPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("julRBATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("augRBPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("augRBATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("sepRBPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("sepRBATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("octRBPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("octRBATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("novRBPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("novRBATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("decRBPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("decRBATIncome")), moneyStyle);
                /********* 应收 end **********/

                /********* 实际 begin **********/

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("totalAPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("totalAATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("totalAPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("totalAATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("totalAPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("totalAATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("totalAPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("totalAATPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("totalAPTReceive")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("totalAPTBalance")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("beforeAPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("beforeAATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("beforeAPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("beforeAATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("beforeAPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("beforeAATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("beforeAPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("beforeAATPrepaid")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("thisAPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("thisAATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("thisAPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("thisAATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("thisAPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("thisAATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("thisAPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("thisAATPrepaid")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("janAPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("janAATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("janAPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("janAATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("janAPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("janAATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("janAPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("janAATPrepaid")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("febAPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("febAATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("febAPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("febAATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("febAPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("febAATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("febAPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("febAATPrepaid")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("marAPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("marAATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("marAPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("marAATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("marAPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("marAATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("marAPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("marAATPrepaid")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("aprAPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("aprAATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("aprAPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("aprAATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("aprAPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("aprAATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("aprAPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("aprAATPrepaid")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("mayAPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("mayAATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("mayAPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("mayAATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("mayAPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("mayAATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("mayAPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("mayAATPrepaid")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("junAPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("junAATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("junAPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("junAATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("junAPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("junAATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("junAPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("junAATPrepaid")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("julAPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("julAATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("julAPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("julAATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("julAPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("julAATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("julAPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("julAATPrepaid")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("augAPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("augAATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("augAPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("augAATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("augAPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("augAATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("augAPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("augAATPrepaid")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("sepAPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("sepAATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("sepAPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("sepAATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("sepAPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("sepAATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("sepAPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("sepAATPrepaid")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("octAPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("octAATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("octAPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("octAATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("octAPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("octAATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("octAPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("octAATPrepaid")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("novAPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("novAATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("novAPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("novAATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("novAPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("novAATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("novAPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("novAATPrepaid")), moneyStyle);

                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("decAPTIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("decAATIncome")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("decAPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("decAATCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("decAPTProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("decAATProfit")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("decAPTPrepaid")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("decAATPrepaid")), moneyStyle);

                /********* 实际 end **********/

                /********* 内佣 begin **********/
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("totalNPTCommission")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("totalNATCommission")), moneyStyle);
                /********* 内佣 end **********/

                super.createCellStr(workBook,row,columnIndex++, map.get("remark") == null ? "" : map.get("remark").toString(), bodyStyle);

                super.createCellStr(workBook,row,columnIndex++, map.get("expenderName") == null ? "" : map.get("expenderName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("expenderCode") == null ? "" : map.get("expenderCode").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("groupLeaderName") == null ? "" : map.get("groupLeaderName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("groupLeaderCode") == null ? "" : map.get("groupLeaderCode").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("areaLeaderName") == null ? "" : map.get("areaLeaderName").toString(), bodyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("areaLeaderCode") == null ? "" : map.get("areaLeaderCode").toString(), bodyStyle);

                super.createCellStr(workBook,row,columnIndex++, map.get("fyObject1") == null ? "" : map.get("fyObject1").toString(), bodyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("befYJFY1")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("aftYJFY1")), moneyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("fyObject2") == null ? "" : map.get("fyObject2").toString(), bodyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("befYJFY2")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("aftYJFY2")), moneyStyle);
                super.createCellStr(workBook,row,columnIndex++, map.get("fyObject3") == null ? "" : map.get("fyObject3").toString(), bodyStyle);
                super.createCellMoney(workBook,row,columnIndex++, clearZero(map.get("befYJFY3")), moneyStyle);
                super.createCellMoney(workBook,row,columnIndex, clearZero(map.get("aftYJFY3")), moneyStyle);
        }
        //logger.info("联动明细报表##导出##生成数据 createSheetBody end userid="+userId+"##rowIndex="+rowIndex);
        return rowIndex;
    }




    /**
     * 读取CSV文件
     * @param filePath:全路径名
     */
    public int readCSV(String filePath,Integer userId) throws Exception {
        int rowIndex = 3;
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
                    map.put("regionName", reader.get(3));
                    map.put("areaCityName", reader.get(4));
                    map.put("cityGroupName", reader.get(5));
                    map.put("areaGroupName", reader.get(6));
                    map.put("centerGroupName", reader.get(7));
                    map.put("groupName", reader.get(8));
                    map.put("expenderName", reader.get(9));
                    map.put("expenderCode", reader.get(10));
                    map.put("projectCityName", reader.get(11));
                    map.put("projectDepartmentName", reader.get(12));
                    map.put("srCityName", reader.get(13));
                    map.put("customerFromName", reader.get(14));
                    map.put("storeNo", reader.get(15));
                    map.put("storeName", reader.get(16));
                    map.put("companyName", reader.get(17));
                    map.put("addressDetail", reader.get(18));
                    map.put("storeSize", reader.get(19));
                    map.put("contractTypeName", reader.get(20));
                    map.put("saleName", reader.get(21));
                    map.put("salePhone", reader.get(22));
                    map.put("customerNm", reader.get(23));
                    map.put("customerTel", reader.get(24));
                    map.put("customerNum", reader.get(25));
                    map.put("estateId", reader.get(26));
                    map.put("projectNo", reader.get(27));
                    map.put("estateNm", reader.get(28));
                    map.put("partnerNm", reader.get(29));
                    map.put("bigCustomerFlagStr", reader.get(30));
                    map.put("bigCustomerName", reader.get(31));
                    map.put("prepaidName", reader.get(32));
                    map.put("reportId", reader.get(33));
                    map.put("reportDate", reader.get(34));
                    map.put("seeDate", reader.get(35));
                    map.put("pledgedDate", reader.get(36));
                    map.put("buildingNo", reader.get(37));
                    map.put("suitNum", reader.get(38));
                    map.put("preBack", reader.get(39));
                    map.put("roughArea", reader.get(40));
                    map.put("roughPrice", reader.get(41));
                    map.put("roughAmount", reader.get(42));
                    map.put("roughDate", reader.get(43));
                    map.put("roughAuditTime", reader.get(44));
                    map.put("dealArea", reader.get(45));
                    map.put("dealPrice", reader.get(46));
                    map.put("dealAmount", reader.get(47));
                    map.put("dealDate", reader.get(48));
                    map.put("incomeStatusStr", reader.get(49));
                    map.put("totalEPTIncome", reader.get(50));
                    map.put("totalEATIncome", reader.get(51));
                    map.put("totalEPTCommission", reader.get(52));
                    map.put("totalEATCommission", reader.get(53));
                    map.put("totalEPTProfit", reader.get(54));
                    map.put("totalEATProfit", reader.get(55));
                    map.put("totalEPTPrepaid", reader.get(56));
                    map.put("totalEATPrepaid", reader.get(57));
                    map.put("beforeEPTIncome", reader.get(58));
                    map.put("beforeEATIncome", reader.get(59));
                    map.put("beforeEPTCommission", reader.get(60));
                    map.put("beforeEATCommission", reader.get(61));
                    map.put("beforeEPTProfit", reader.get(62));
                    map.put("beforeEATProfit", reader.get(63));
                    map.put("beforeEPTPrepaid", reader.get(64));
                    map.put("beforeEATPrepaid", reader.get(65));
                    map.put("thisEPTIncome", reader.get(66));
                    map.put("thisEATIncome", reader.get(67));
                    map.put("thisEPTCommission", reader.get(68));
                    map.put("thisEATCommission", reader.get(69));
                    map.put("thisEPTProfit", reader.get(70));
                    map.put("thisEATProfit", reader.get(71));
                    map.put("thisEPTPrepaid", reader.get(72));
                    map.put("thisEATPrepaid", reader.get(73));
                    map.put("janEPTIncome", reader.get(74));
                    map.put("janEATIncome", reader.get(75));
                    map.put("janEPTCommission", reader.get(76));
                    map.put("janEATCommission", reader.get(77));
                    map.put("janEPTProfit", reader.get(78));
                    map.put("janEATProfit", reader.get(79));
                    map.put("janEPTPrepaid", reader.get(80));
                    map.put("janEATPrepaid", reader.get(81));
                    map.put("febEPTIncome", reader.get(82));
                    map.put("febEATIncome", reader.get(83));
                    map.put("febEPTCommission", reader.get(84));
                    map.put("febEATCommission", reader.get(85));
                    map.put("febEPTProfit", reader.get(86));
                    map.put("febEATProfit", reader.get(87));
                    map.put("febEPTPrepaid", reader.get(88));
                    map.put("febEATPrepaid", reader.get(89));
                    map.put("marEPTIncome", reader.get(90));
                    map.put("marEATIncome", reader.get(91));
                    map.put("marEPTCommission", reader.get(92));
                    map.put("marEATCommission", reader.get(93));
                    map.put("marEPTProfit", reader.get(94));
                    map.put("marEATProfit", reader.get(95));
                    map.put("marEPTPrepaid", reader.get(96));
                    map.put("marEATPrepaid", reader.get(97));
                    map.put("aprEPTIncome", reader.get(98));
                    map.put("aprEATIncome", reader.get(99));
                    map.put("aprEPTCommission", reader.get(100));
                    map.put("aprEATCommission", reader.get(101));
                    map.put("aprEPTProfit", reader.get(102));
                    map.put("aprEATProfit", reader.get(103));
                    map.put("aprEPTPrepaid", reader.get(104));
                    map.put("aprEATPrepaid", reader.get(105));
                    map.put("mayEPTIncome", reader.get(106));
                    map.put("mayEATIncome", reader.get(107));
                    map.put("mayEPTCommission", reader.get(108));
                    map.put("mayEATCommission", reader.get(109));
                    map.put("mayEPTProfit", reader.get(110));
                    map.put("mayEATProfit", reader.get(111));
                    map.put("mayEPTPrepaid", reader.get(112));
                    map.put("mayEATPrepaid", reader.get(113));
                    map.put("junEPTIncome", reader.get(114));
                    map.put("junEATIncome", reader.get(115));
                    map.put("junEPTCommission", reader.get(116));
                    map.put("junEATCommission", reader.get(117));
                    map.put("junEPTProfit", reader.get(118));
                    map.put("junEATProfit", reader.get(119));
                    map.put("junEPTPrepaid", reader.get(120));
                    map.put("junEATPrepaid", reader.get(121));
                    map.put("julEPTIncome", reader.get(122));
                    map.put("julEATIncome", reader.get(123));
                    map.put("julEPTCommission", reader.get(124));
                    map.put("julEATCommission", reader.get(125));
                    map.put("julEPTProfit", reader.get(126));
                    map.put("julEATProfit", reader.get(127));
                    map.put("julEPTPrepaid", reader.get(128));
                    map.put("julEATPrepaid", reader.get(129));
                    map.put("augEPTIncome", reader.get(130));
                    map.put("augEATIncome", reader.get(131));
                    map.put("augEPTCommission", reader.get(132));
                    map.put("augEATCommission", reader.get(133));
                    map.put("augEPTProfit", reader.get(134));
                    map.put("augEATProfit", reader.get(135));
                    map.put("augEPTPrepaid", reader.get(136));
                    map.put("augEATPrepaid", reader.get(137));
                    map.put("sepEPTIncome", reader.get(138));
                    map.put("sepEATIncome", reader.get(139));
                    map.put("sepEPTCommission", reader.get(140));
                    map.put("sepEATCommission", reader.get(141));
                    map.put("sepEPTProfit", reader.get(142));
                    map.put("sepEATProfit", reader.get(143));
                    map.put("sepEPTPrepaid", reader.get(144));
                    map.put("sepEATPrepaid", reader.get(145));
                    map.put("octEPTIncome", reader.get(146));
                    map.put("octEATIncome", reader.get(147));
                    map.put("octEPTCommission", reader.get(148));
                    map.put("octEATCommission", reader.get(149));
                    map.put("octEPTProfit", reader.get(150));
                    map.put("octEATProfit", reader.get(151));
                    map.put("octEPTPrepaid", reader.get(152));
                    map.put("octEATPrepaid", reader.get(153));
                    map.put("novEPTIncome", reader.get(154));
                    map.put("novEATIncome", reader.get(155));
                    map.put("novEPTCommission", reader.get(156));
                    map.put("novEATCommission", reader.get(157));
                    map.put("novEPTProfit", reader.get(158));
                    map.put("novEATProfit", reader.get(159));
                    map.put("novEPTPrepaid", reader.get(160));
                    map.put("novEATPrepaid", reader.get(161));
                    map.put("decEPTIncome", reader.get(162));
                    map.put("decEATIncome", reader.get(163));
                    map.put("decEPTCommission", reader.get(164));
                    map.put("decEATCommission", reader.get(165));
                    map.put("decEPTProfit", reader.get(166));
                    map.put("decEATProfit", reader.get(167));
                    map.put("decEPTPrepaid", reader.get(168));
                    map.put("decEATPrepaid", reader.get(169));
                    map.put("totalRBPTIncome", reader.get(170));
                    map.put("totalRBATIncome", reader.get(171));
                    map.put("beforeRBPTIncome", reader.get(172));
                    map.put("beforeRBATIncome", reader.get(173));
                    map.put("thisRBPTIncome", reader.get(174));
                    map.put("thisRBATIncome", reader.get(175));
                    map.put("janRBPTIncome", reader.get(176));
                    map.put("janRBATIncome", reader.get(177));
                    map.put("febRBPTIncome", reader.get(178));
                    map.put("febRBBATIncome", reader.get(179));
                    map.put("marRBPTIncome", reader.get(180));
                    map.put("marRBATIncome", reader.get(181));
                    map.put("aprRBPTIncome", reader.get(182));
                    map.put("aprRBATIncome", reader.get(183));
                    map.put("mayRBPTIncome", reader.get(184));
                    map.put("mayRBATIncome", reader.get(185));
                    map.put("junRBPTIncome", reader.get(186));
                    map.put("junRBATIncome", reader.get(187));
                    map.put("julRBPTIncome", reader.get(188));
                    map.put("julRBATIncome", reader.get(189));
                    map.put("augRBPTIncome", reader.get(190));
                    map.put("augRBATIncome", reader.get(191));
                    map.put("sepRBPTIncome", reader.get(192));
                    map.put("sepRBATIncome", reader.get(193));
                    map.put("octRBPTIncome", reader.get(194));
                    map.put("octRBATIncome", reader.get(195));
                    map.put("novRBPTIncome", reader.get(196));
                    map.put("novRBATIncome", reader.get(197));
                    map.put("decRBPTIncome", reader.get(198));
                    map.put("decRBATIncome", reader.get(199));
                    map.put("totalAPTIncome", reader.get(200));
                    map.put("totalAATIncome", reader.get(201));
                    map.put("totalAPTCommission", reader.get(202));
                    map.put("totalAATCommission", reader.get(203));
                    map.put("totalAPTProfit", reader.get(204));
                    map.put("totalAATProfit", reader.get(205));
                    map.put("totalAPTPrepaid", reader.get(206));
                    map.put("totalAATPrepaid", reader.get(207));
                    map.put("totalAPTReceive", reader.get(208));
                    map.put("totalAPTBalance", reader.get(209));
                    map.put("beforeAPTIncome", reader.get(210));
                    map.put("beforeAATIncome", reader.get(211));
                    map.put("beforeAPTCommission", reader.get(212));
                    map.put("beforeAATCommission", reader.get(213));
                    map.put("beforeAPTProfit", reader.get(214));
                    map.put("beforeAATProfit", reader.get(215));
                    map.put("beforeAPTPrepaid", reader.get(216));
                    map.put("beforeAATPrepaid", reader.get(217));
                    map.put("thisAPTIncome", reader.get(218));
                    map.put("thisAATIncome", reader.get(219));
                    map.put("thisAPTCommission", reader.get(220));
                    map.put("thisAATCommission", reader.get(221));
                    map.put("thisAPTProfit", reader.get(222));
                    map.put("thisAATProfit", reader.get(223));
                    map.put("thisAPTPrepaid", reader.get(224));
                    map.put("thisAATPrepaid", reader.get(225));
                    map.put("janAPTIncome", reader.get(226));
                    map.put("janAATIncome", reader.get(227));
                    map.put("janAPTCommission", reader.get(228));
                    map.put("janAATCommission", reader.get(229));
                    map.put("janAPTProfit", reader.get(230));
                    map.put("janAATProfit", reader.get(231));
                    map.put("janAPTPrepaid", reader.get(232));
                    map.put("janAATPrepaid", reader.get(233));
                    map.put("febAPTIncome", reader.get(234));
                    map.put("febAATIncome", reader.get(235));
                    map.put("febAPTCommission", reader.get(236));
                    map.put("febAATCommission", reader.get(237));
                    map.put("febAPTProfit", reader.get(238));
                    map.put("febAATProfit", reader.get(239));
                    map.put("febAPTPrepaid", reader.get(240));
                    map.put("febAATPrepaid", reader.get(241));
                    map.put("marAPTIncome", reader.get(242));
                    map.put("marAATIncome", reader.get(243));
                    map.put("marAPTCommission", reader.get(244));
                    map.put("marAATCommission", reader.get(245));
                    map.put("marAPTProfit", reader.get(246));
                    map.put("marAATProfit", reader.get(247));
                    map.put("marAPTPrepaid", reader.get(248));
                    map.put("marAATPrepaid", reader.get(249));
                    map.put("aprAPTIncome", reader.get(250));
                    map.put("aprAATIncome", reader.get(251));
                    map.put("aprAPTCommission", reader.get(252));
                    map.put("aprAATCommission", reader.get(253));
                    map.put("aprAPTProfit", reader.get(254));
                    map.put("aprAATProfit", reader.get(255));
                    map.put("aprAPTPrepaid", reader.get(256));
                    map.put("aprAATPrepaid", reader.get(257));
                    map.put("mayAPTIncome", reader.get(258));
                    map.put("mayAATIncome", reader.get(259));
                    map.put("mayAPTCommission", reader.get(260));
                    map.put("mayAATCommission", reader.get(261));
                    map.put("mayAPTProfit", reader.get(262));
                    map.put("mayAATProfit", reader.get(263));
                    map.put("mayAPTPrepaid", reader.get(264));
                    map.put("mayAATPrepaid", reader.get(265));
                    map.put("junAPTIncome", reader.get(266));
                    map.put("junAATIncome", reader.get(267));
                    map.put("junAPTCommission", reader.get(268));
                    map.put("junAATCommission", reader.get(269));
                    map.put("junAPTProfit", reader.get(270));
                    map.put("junAATProfit", reader.get(271));
                    map.put("junAPTPrepaid", reader.get(272));
                    map.put("junAATPrepaid", reader.get(273));
                    map.put("julAPTIncome", reader.get(274));
                    map.put("julAATIncome", reader.get(275));
                    map.put("julAPTCommission", reader.get(276));
                    map.put("julAATCommission", reader.get(277));
                    map.put("julAPTProfit", reader.get(278));
                    map.put("julAATProfit", reader.get(279));
                    map.put("julAPTPrepaid", reader.get(280));
                    map.put("julAATPrepaid", reader.get(281));
                    map.put("augAPTIncome", reader.get(282));
                    map.put("augAATIncome", reader.get(283));
                    map.put("augAPTCommission", reader.get(284));
                    map.put("augAATCommission", reader.get(285));
                    map.put("augAPTProfit", reader.get(286));
                    map.put("augAATProfit", reader.get(287));
                    map.put("augAPTPrepaid", reader.get(288));
                    map.put("augAATPrepaid", reader.get(289));
                    map.put("sepAPTIncome", reader.get(290));
                    map.put("sepAATIncome", reader.get(291));
                    map.put("sepAPTCommission", reader.get(292));
                    map.put("sepAATCommission", reader.get(293));
                    map.put("sepAPTProfit", reader.get(294));
                    map.put("sepAATProfit", reader.get(295));
                    map.put("sepAPTPrepaid", reader.get(296));
                    map.put("sepAATPrepaid", reader.get(297));
                    map.put("octAPTIncome", reader.get(298));
                    map.put("octAATIncome", reader.get(299));
                    map.put("octAPTCommission", reader.get(300));
                    map.put("octAATCommission", reader.get(301));
                    map.put("octAPTProfit", reader.get(302));
                    map.put("octAATProfit", reader.get(303));
                    map.put("octAPTPrepaid", reader.get(304));
                    map.put("octAATPrepaid", reader.get(305));
                    map.put("novAPTIncome", reader.get(306));
                    map.put("novAATIncome", reader.get(307));
                    map.put("novAPTCommission", reader.get(308));
                    map.put("novAATCommission", reader.get(309));
                    map.put("novAPTProfit", reader.get(310));
                    map.put("novAATProfit", reader.get(311));
                    map.put("novAPTPrepaid", reader.get(312));
                    map.put("novAATPrepaid", reader.get(313));
                    map.put("decAPTIncome", reader.get(314));
                    map.put("decAATIncome", reader.get(315));
                    map.put("decAPTCommission", reader.get(316));
                    map.put("decAATCommission", reader.get(317));
                    map.put("decAPTProfit", reader.get(318));
                    map.put("decAATProfit", reader.get(319));
                    map.put("decAPTPrepaid", reader.get(320));
                    map.put("decAATPrepaid", reader.get(321));
                    map.put("totalNPTCommission", reader.get(322));
                    map.put("totalNATCommission", reader.get(323));
                    map.put("remark", reader.get(324));
                    map.put("expenderName", reader.get(325));
                    map.put("expenderCode", reader.get(326));
                    map.put("groupLeaderName", reader.get(327));
                    map.put("groupLeaderCode", reader.get(328));
                    map.put("areaLeaderName", reader.get(329));
                    map.put("areaLeaderCode", reader.get(330));
                    map.put("fyObject1", reader.get(331));
                    map.put("befYJFY1", reader.get(332));
                    map.put("aftYJFY1", reader.get(333));
                    map.put("fyObject2", reader.get(334));
                    map.put("befYJFY2", reader.get(335));
                    map.put("aftYJFY2", reader.get(336));
                    map.put("fyObject3", reader.get(337));
                    map.put("befYJFY3", reader.get(338));
                    map.put("aftYJFY3", reader.get(339));
                    if (map.get("rowNum") != null) {
                        createSheetBody(map, rowIndex, userId);
                        rowIndex++;
                    }
                //}
            }
        } catch (Exception e) {
                logger.error("联动明细报表##导出##readCSV 异常"+"##userId="+userId,e);
                //记入--错误日志表
                logger.error("excelForLinkDetailBigDataSxssf",
                        "ExcelForLinkDetailBigDataSxssf",
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
