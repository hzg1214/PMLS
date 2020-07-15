package cn.com.eju.pmls.poi;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.houseLinkage.linkDetail.service.LinkDetailService;
import cn.com.eju.pmls.poi.common.ExcelSXSSFCommon;
import cn.com.eju.pmls.poi.common.ExcelStyleEnumSxssf;
import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成报表文件
 * */
public class ExcelForLinkDetailBigDataSx extends ExcelSXSSFCommon {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    private LinkDetailService linkDetailService = new LinkDetailService();

    private SXSSFWorkbook workBook;
    private Sheet sheet;


    private CellStyle bodyStyle;
    private CellStyle dateStyle;
    private CellStyle moneyStyle;

    private CellStyle bodyBlackStyle;
    private CellStyle dateBlackStyle;
    private CellStyle moneyBlackStyle;

    private CellStyle bodyRedStyle;
    private CellStyle dateRedStyle;
    private CellStyle moneyRedStyle;



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

            workBook = new SXSSFWorkbook(xssfwb,100);
            sheet = workBook.getSheetAt(0);
            int rowIndex = 3;


            Map<String, CellStyle> styleMap = createStyles(workBook);
            bodyBlackStyle = styleMap.get(ExcelStyleEnumSxssf.BODY_BLACK.getValue());
            dateBlackStyle = styleMap.get(ExcelStyleEnumSxssf.DATE_BLACK.getValue());
            moneyBlackStyle = styleMap.get(ExcelStyleEnumSxssf.MONEY_UNLOCKED_BLACK.getValue());

            bodyRedStyle = styleMap.get(ExcelStyleEnumSxssf.BODY_RED.getValue());
            dateRedStyle = styleMap.get(ExcelStyleEnumSxssf.DATE_RED.getValue());
            moneyRedStyle = styleMap.get(ExcelStyleEnumSxssf.MONEY_UNLOCKED_RED.getValue());


            logger.info("联动明细报表##导出##queryLinkDetailList start##userId="+reqMap.get("userId"));
            PageInfo pageInfo = new PageInfo();
            pageInfo.setCurPage(1);
            pageInfo.setPageLimit(3000);
            logger.info("联动明细报表##导出##queryLinkDetailList第1次查询开始##userId="+reqMap.get("userId"));
            ResultData<?> reback = linkDetailService.queryLinkDetailList(reqMap, pageInfo);
            List<LinkedHashMap<String, Object>> contentList = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            Integer totalCount = Integer.valueOf(reback.getTotalCount());
            logger.info("联动明细报表##导出##queryLinkDetailList第1次查询结束，totalCount:" + totalCount+"##userId="+reqMap.get("userId"));
            Integer userId = 0;
            if(reqMap.get("userId")!=null){
                userId = Integer.parseInt(reqMap.get("userId").toString());
            }

            if (contentList != null && contentList.size() > 0) {
                logger.info("联动明细报表##导出##createSheetBody start##userId="+reqMap.get("userId"));
                rowIndex = createSheetBody(contentList,rowIndex,userId);
            }

            if (totalCount > contentList.size()) {
                Integer pageCount = (int) Math.ceil(Double.valueOf(totalCount) / Double.valueOf(contentList.size()));
                for (int i = 2; i <= pageCount; i++) {
                    pageInfo.setCurPage(i);
                    logger.info("联动明细报表##导出##queryLinkDetailList第"+i+"次查询开始,共" + pageCount + "次##userId="+reqMap.get("userId"));
                    reback = linkDetailService.queryLinkDetailList(reqMap, pageInfo);
                    logger.info("联动明细报表##导出##queryLinkDetailList第"+i+"次查询结束,共" + pageCount + "次##userId="+reqMap.get("userId"));
                    contentList = (List<LinkedHashMap<String, Object>>) reback.getReturnData();
                    if (contentList != null && contentList.size() > 0) {
                        rowIndex = createSheetBody(contentList,rowIndex,userId);
                    }

                }
            }
            logger.info("联动明细报表##导出##downloadSheet createSheetBody end"+"##userId="+reqMap.get("userId"));
            outputStream = new FileOutputStream(outPutFile);
            workBook.write(outputStream);
        }catch (Throwable e){
            logger.error("联动明细报表##导出##downloadSheet 异常"+"##userId="+reqMap.get("userId"));
            e.printStackTrace();
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
        }
        logger.info("联动明细报表##导出##downloadSheet end"+"##userId="+reqMap.get("userId"));
    }

    /**
     * 创建sheet具体内容
     */
    private int createSheetBody(List<LinkedHashMap<String, Object>> lilst, int index,Integer userId) throws Exception {
        logger.info("联动明细报表##导出##生成数据 createSheetBody start userid="+userId+"##index="+index);
        int rowIndex = index;
        if (lilst != null && lilst.size() > 0) {
            for (LinkedHashMap<String, Object> map : lilst) {
                int columnIndex = 0;
                Row row = sheet.createRow(rowIndex);
                bodyStyle = "1".equals(map.get("suitNum").toString()) ? bodyBlackStyle : bodyRedStyle;
                dateStyle = "1".equals(map.get("suitNum").toString()) ? dateBlackStyle : dateRedStyle;
                moneyStyle = "1".equals(map.get("suitNum").toString()) ? moneyBlackStyle : moneyRedStyle;


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
                super.createCellStr(workBook,row,columnIndex++, clearIntZero(map.get("suitNum")).toString(), bodyStyle);

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


                rowIndex++;
            }
        }
        logger.info("联动明细报表##导出##生成数据 createSheetBody end userid="+userId+"##rowIndex="+rowIndex);
        return rowIndex;
    }
}
