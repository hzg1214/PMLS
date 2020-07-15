package cn.com.eju.pmls.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.common.util.DateUtils;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.poi.common.ExcelColumnSet;
import cn.com.eju.deal.poi.common.ExcelStyleEnum;
import cn.com.eju.deal.poi.common.ExcelUseXmlAbstract;
import cn.com.eju.deal.poi.common.SpeedSheetWriter;
import cn.com.eju.pmls.statisticsReport.linkDetail.service.PmlsLinkProjectDetailService;
/**
 * 联动项目明细
 * 
 */
public class ExcelForLinkProjectDetailBigData extends ExcelUseXmlAbstract {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    private PmlsLinkProjectDetailService pmlsLinkProjectDetailService = new PmlsLinkProjectDetailService();

    private short bodyBlackStyle;
    private short bodyRedStyle;
    private short dateBlackStyle;
    private short dateRedStyle;
    private short moneyBlackStyle;
    private short moneyRedStyle;
    private short intBlackStyle;
    private short intRedStyle;
    private short doubleBlackStyle;
    private String yearly;

    public void downloadSheet(Map<String, Object> reqMap, File file, String yearly) throws IOException {
        OutputStream os = new FileOutputStream(file);
        this.yearly = yearly;

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("联动项目明细");
        Map<String, XSSFCellStyle> style = super.createStyles(wb);
        super.headerStyle = style.get(ExcelStyleEnum.HEADER.getValue()).getIndex();
        this.bodyBlackStyle = style.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();
        this.bodyRedStyle = style.get(ExcelStyleEnum.BODY_RED.getValue()).getIndex();
        this.dateBlackStyle = style.get(ExcelStyleEnum.DATE_BLACK.getValue()).getIndex();
        this.dateRedStyle = style.get(ExcelStyleEnum.DATE_RED.getValue()).getIndex();
        this.moneyBlackStyle = style.get(ExcelStyleEnum.MONEY_UNLOCKED_BLACK.getValue()).getIndex();
        this.moneyRedStyle = style.get(ExcelStyleEnum.MONEY_UNLOCKED_RED.getValue()).getIndex();
        this.intBlackStyle = style.get(ExcelStyleEnum.INT_BLACK.getValue()).getIndex();
        this.intRedStyle = style.get(ExcelStyleEnum.INT_RED.getValue()).getIndex();
        this.doubleBlackStyle = style.get(ExcelStyleEnum.DOUBLE_BLACK.getValue()).getIndex();
        File tempExcel = File.createTempFile("tempExcel", ".xlsx");
        File temp = File.createTempFile("sheet", ".xml");
        try {
            FileOutputStream fos = new FileOutputStream(tempExcel);
            wb.write(fos);
            fos.close();
            String sheetRef = sheet.getPackagePart().getPartName().getName();
            Writer fw = new PrintWriter(temp, "UTF-8");
            this.createSheet(fw, reqMap);
            fw.close();
            super.substitute(tempExcel, temp, sheetRef.substring(1), os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            os.close();
        }
    }

    private void createSheet(Writer out, Map<String, Object> reqMap) throws Exception {
    	String searchType = (String) reqMap.get("searchType");
        SpeedSheetWriter sw = new SpeedSheetWriter(out);
        sw.beginSheet(setColumnWidth(searchType), 0, 3, "A4");//0:y轴 1：X轴  A2数据开始位置  A2得位置要根据x,y取值来改变   比如1，1，B2
        int index = 0;
        List<String> listMerge = new ArrayList<>();
        index = this.createSheetHead(sw, index, listMerge);

        PageInfo pageInfo = new PageInfo();
        pageInfo.setCurPage(1);
        pageInfo.setPageLimit(5000);
        logger.info("联动项目明细 第1次查询开始。。。");
        ResultData<?> reback = pmlsLinkProjectDetailService.queryLinkProjectDetailList(reqMap, pageInfo);
        List<LinkedHashMap<String, Object>> contentList = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

        Integer totalCount = Integer.valueOf(reback.getTotalCount());
        logger.info("联动项目明细 第1次查询结束，totalCount:" + totalCount);
        if (contentList != null && contentList.size() > 0) {
            for (LinkedHashMap<String, Object> map : contentList) {
                index = this.createSheetBody(sw, index, map);
            }
        }
        logger.info("联动项目明细 第1次写文件开始。。。");
        if (contentList != null && totalCount > contentList.size()) {
            Integer pageCount = (int) Math.ceil(Double.valueOf(totalCount) / Double.valueOf(contentList.size()));
            for (int i = 2; i <= pageCount; i++) {
                pageInfo.setCurPage(i);
                logger.info("联动项目明细 第" + i + "次查询开始,共" + pageCount + "次。");
                reback = pmlsLinkProjectDetailService.queryLinkProjectDetailList(reqMap, pageInfo);
                logger.info("联动项目明细 第" + i + "次查询结束,共" + pageCount + "次。");
                contentList = (List<LinkedHashMap<String, Object>>) reback.getReturnData();
                if (contentList != null && contentList.size() > 0) {
                    for (LinkedHashMap<String, Object> map : contentList) {
                        index = this.createSheetBody(sw, index, map);
                    }
                }
                logger.info("联动项目明细 第" + i + "次写文件结束。");
            }
        }
        logger.info("查询结束！");
        sw.endSheetWithMerge(listMerge);
    }

    /**
     * 创建sheet头部内容
     */
    private int createSheetHead(SpeedSheetWriter sw, int index, List<String> listMerge) throws IOException {
        nowRowIndex = index;
        nowColIndex = 0;
        sw.insertRow(nowRowIndex);
        //第一行
        nowColIndex = createBlock(sw, "序号", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "项目归属区域", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "项目归属城市", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "项目所在城市", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "项目归属部门", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "报备归属城市", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "考核主体", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "楼盘名", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "城市", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "区域", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "地址", 1, 3, listMerge);
//        nowColIndex = createBlock(sw, "系统编号", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "项目编号", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "合作类型", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "合作方名称", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "是否大客户", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "大客户简称", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "是否独家", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "是否直签", 1, 3, listMerge);
        //nowColIndex = createBlock(sw, "垫佣金额", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "合作周期", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "项目状态", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "物业类型", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "合作模式", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "当期实际(不含结转)", 27, 1, listMerge);
        nowColIndex = createBlock(sw, "总累计", 26, 1, listMerge);

        sw.endRow();
        //第二行
        sw.insertRow(++nowRowIndex);
        nowColIndex = 0;
        nowColIndex = createBlock(sw, "序号", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "项目归属区域", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "项目归属城市", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "项目所在城市", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "项目归属部门", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "报备归属城市", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "报备归属中心", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "楼盘名", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "城市", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "区域", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "地址", 1, 0, listMerge);
        //nowColIndex = createBlock(sw, "系统编号", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "项目编号", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "合作类型", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "合作方名称", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "是否大客户", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "大客户简称", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "是否独家", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "是否直签", 1, 0, listMerge);
        //nowColIndex = createBlock(sw, "垫佣金额", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "合作周期", 1,0, listMerge);
        nowColIndex = createBlock(sw, "项目状态", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "物业类型", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "合作模式", 1, 0, listMerge);

        nowColIndex = createBlock(sw, "大定", 3, 1, listMerge);
        nowColIndex = createBlock(sw, "成销", 3, 1, listMerge);
        nowColIndex = createBlock(sw, "应计收入（税前）", 3, 1, listMerge);

        nowColIndex = createBlock(sw, "税后净收", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "财务费用", 5, 1, listMerge);
        nowColIndex = createBlock(sw, "利润", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "实际回款", 3, 1, listMerge);
        nowColIndex = createBlock(sw, "比例", 8, 1, listMerge);

        nowColIndex = createBlock(sw, "大定", 3, 1, listMerge);
        nowColIndex = createBlock(sw, "成销", 3, 1, listMerge);
        nowColIndex = createBlock(sw, "应计收入（税前）", 3, 1, listMerge);
        nowColIndex = createBlock(sw, "税后净收", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "财务费用", 5, 1, listMerge);
        nowColIndex = createBlock(sw, "利润", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "实际回款", 3, 1, listMerge);
        nowColIndex = createBlock(sw, "比例", 7, 1, listMerge);

        sw.endRow();
        //第三行
        sw.insertRow(++nowRowIndex);
        nowColIndex = 0;
        nowColIndex = createBlock(sw, "序号", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "项目归属区域", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "项目归属城市", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "项目所在城市", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "项目归属部门", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "报备归属城市", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "报备归属中心", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "楼盘名", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "城市", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "区域", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "地址", 1, 0, listMerge);
        //nowColIndex = createBlock(sw, "系统编号", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "项目编号", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "合作类型", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "合作方名称", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "是否大客户", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "大客户简称", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "是否独家", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "是否直签", 1, 0, listMerge);
        //nowColIndex = createBlock(sw, "垫佣金额", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "合作周期", 1,0, listMerge);
        nowColIndex = createBlock(sw, "项目状态", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "物业类型", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "合作模式", 1, 0, listMerge);

        nowColIndex = createBlock(sw, "套数", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "面积", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "金额", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "套数", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "面积", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "金额", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "收入", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "返佣", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "净收", 1, 1, listMerge);

        nowColIndex = createBlock(sw, "税后净收", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "附加税", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "内佣", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "运维", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "线下成本", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "资金成本", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "利润", 1, 0, listMerge);

        nowColIndex = createBlock(sw, "收入", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "返佣", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "净收", 1, 1, listMerge);

        nowColIndex = createBlock(sw, "期初净收比例", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "毛收费率", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "净收比例", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "内佣占比", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "运维占比", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "线下成本占比", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "资金成本占比", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "毛利率", 1, 1, listMerge);

        nowColIndex = createBlock(sw, "套数", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "面积", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "金额", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "套数", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "面积", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "金额", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "收入", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "返佣", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "净收", 1, 1, listMerge);

        nowColIndex = createBlock(sw, "税后净收", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "附加税", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "内佣", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "运维", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "线下成本", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "资金成本", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "利润", 1, 0, listMerge);

        nowColIndex = createBlock(sw, "收入", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "返佣", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "净收", 1, 1, listMerge);

        nowColIndex = createBlock(sw, "毛收费率", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "净收比例", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "内佣占比", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "运维占比", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "线下成本占比", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "资金成本占比", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "毛利率", 1, 1, listMerge);


        sw.endRow();
        nowRowIndex = nowRowIndex + 1;

        return nowRowIndex;
    }

    /**
     * 创建sheet具体内容
     */
    private int createSheetBody(SpeedSheetWriter sw, int index, LinkedHashMap<String, Object> map) throws Exception {
    	sw.insertRow(index);
        int i = 0;

        short bodyStyle = bodyBlackStyle;
        short moneyStyle = moneyBlackStyle;
        short intStyle = intBlackStyle;

        sw.createCell(i++, map.get("rowNum") == null ? "" : map.get("rowNum").toString(), bodyStyle);
        sw.createCell(i++, map.get("regionName") == null ? "" : map.get("regionName").toString(), bodyStyle);
        sw.createCell(i++, map.get("areaCityName") == null ? "" : map.get("areaCityName").toString(), bodyStyle);
        sw.createCell(i++, map.get("cityGroupName") == null ? "" : map.get("cityGroupName").toString(), bodyStyle);
        sw.createCell(i++, map.get("centerGroupName") == null ? "" : map.get("centerGroupName").toString(), bodyStyle);
        sw.createCell(i++, map.get("performCityName") == null ? "" : map.get("performCityName").toString(), bodyStyle);
        //sw.createCell(i++, map.get("performCenterName") == null ? "" : map.get("performCenterName").toString(), bodyStyle);
        sw.createCell(i++, map.get("hblName") == null ? "" : map.get("hblName").toString(), bodyStyle);
        sw.createCell(i++, map.get("estateNm") == null ? "" : map.get("estateNm").toString(), bodyStyle);
        sw.createCell(i++, map.get("cityName") == null ? "" : map.get("cityName").toString(), bodyStyle);
        sw.createCell(i++, map.get("districtName") == null ? "" : map.get("districtName").toString(), bodyStyle);
        sw.createCell(i++, map.get("address") == null ? "" : map.get("address").toString(), bodyStyle);
       // sw.createCell(i++, map.get("estateId") == null ? "" : map.get("estateId").toString(), bodyStyle);
        sw.createCell(i++, map.get("projectNo") == null ? "" : map.get("projectNo").toString(), bodyStyle);
        sw.createCell(i++, map.get("partnerName") == null ? "" : map.get("partnerName").toString(), bodyStyle);
        sw.createCell(i++, map.get("partnerNm") == null ? "" : map.get("partnerNm").toString(), bodyStyle);
        sw.createCell(i++, map.get("bigCustomerFlagVakue") == null ? "" : map.get("bigCustomerFlagVakue").toString(), bodyStyle);
        sw.createCell(i++, map.get("bigCustomerName") == null ? "" : map.get("bigCustomerName").toString(), bodyStyle);
        sw.createCell(i++, map.get("particularFlagVakue") == null ? "" : map.get("particularFlagVakue").toString(), bodyStyle);
        sw.createCell(i++, map.get("directSignFlagVakue") == null ? "" : map.get("directSignFlagVakue").toString(), bodyStyle);
        //sw.createCell(i++, clearZero(map.get("yjdyAmount")), moneyStyle);
        sw.createCell(i++, map.get("estateCycle") == null ? "" : map.get("estateCycle").toString(), bodyStyle);
        
        sw.createCell(i++, map.get("projectStatusName") == null ? "" : map.get("projectStatusName").toString(), bodyStyle);
        sw.createCell(i++, map.get("mgtKbnName") == null ? "" : map.get("mgtKbnName").toString(), bodyStyle);
        sw.createCell(i++, map.get("cooperationModeName") == null ? "" : map.get("cooperationModeName").toString(), bodyStyle);
        sw.createCell(i++, clearIntZero(map.get("curRoughNum")), intStyle);
        sw.createCell(i++, clearZero(map.get("curRoughArea")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("curRoughAmount")), moneyStyle);
        sw.createCell(i++, clearIntZero(map.get("curDealNum")), intStyle);
        sw.createCell(i++, clearZero(map.get("curDealArea")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("curDealAmount")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("curEPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("curEPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("curEPTProfit")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("curESHProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("curFCAC01012")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("curNYAmount")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("curFCAC01028")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("curCost")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("curFCAC01127")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("curLRProfit")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("curAPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("curAPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("curAPTProfit")), moneyStyle);

        sw.createCell(i++, map.get("qcjsRate") == null ? "" : map.get("qcjsRate").toString(), bodyStyle);

        sw.createCell(i++, map.get("curMSFRate") == null ? "" : map.get("curMSFRate").toString(), bodyStyle);
        sw.createCell(i++, map.get("curJSBRate") == null ? "" : map.get("curJSBRate").toString(), bodyStyle);
        sw.createCell(i++, map.get("curNYZRate") == null ? "" : map.get("curNYZRate").toString(), bodyStyle);
        sw.createCell(i++, map.get("curYWZRate") == null ? "" : map.get("curYWZRate").toString(), bodyStyle);
        sw.createCell(i++, map.get("curXXCBZRate") == null ? "" : map.get("curXXCBZRate").toString(), bodyStyle);
        sw.createCell(i++, map.get("curZJCBZRate") == null ? "" : map.get("curZJCBZRate").toString(), bodyStyle);
        sw.createCell(i++, map.get("curMLRate") == null ? "" : map.get("curMLRate").toString(), bodyStyle);

        sw.createCell(i++, clearIntZero(map.get("accRoughNum")), intStyle);
        sw.createCell(i++, clearZero(map.get("accRoughArea")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("accRoughAmount")), moneyStyle);
        sw.createCell(i++, clearIntZero(map.get("accDealNum")), intStyle);
        sw.createCell(i++, clearZero(map.get("accDealArea")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("accDealAmount")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("accEPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("accEPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("accEPTProfit")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("accESHProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("accFCAC01012")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("accNYAmount")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("accFCAC01028")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("accCost")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("accFCAC01127")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("accLRProfit")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("accAPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("accAPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("accAPTProfit")), moneyStyle);


        sw.createCell(i++, map.get("accMSFRate") == null ? "" : map.get("accMSFRate").toString(), bodyStyle);
        sw.createCell(i++, map.get("accJSBRate") == null ? "" : map.get("accJSBRate").toString(), bodyStyle);
        sw.createCell(i++, map.get("accNYZRate") == null ? "" : map.get("accNYZRate").toString(), bodyStyle);
        sw.createCell(i++, map.get("accYWZRate") == null ? "" : map.get("accYWZRate").toString(), bodyStyle);
        sw.createCell(i++, map.get("accXXCBZRate") == null ? "" : map.get("accXXCBZRate").toString(), bodyStyle);
        sw.createCell(i++, map.get("accZJCBZRate") == null ? "" : map.get("accZJCBZRate").toString(), bodyStyle);
        sw.createCell(i++, map.get("accMLRate") == null ? "" : map.get("accMLRate").toString(), bodyStyle);

        sw.endRow();
        index++;

        return index;
    }


    /**
     * 设置列宽
     */
    public List<ExcelColumnSet> setColumnWidth(String searchType) {
        List<ExcelColumnSet> columnSet = new ArrayList<>();

        ExcelColumnSet c;

        int nColIndex = 1;

        //序号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(5);
        columnSet.add(c);
        nColIndex++;

        //归属城市
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 4));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex += 5;

        //中心
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        if(!"2".equals(searchType)){
            c.setHidden(true);
        }
        columnSet.add(c);
        nColIndex++;

        //楼盘名
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
        columnSet.add(c);
        nColIndex++;

        //城市
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;

        //区域
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //地址
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(40);
        columnSet.add(c);
        nColIndex++;

//        //系统编号
//        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
//        c.setWidth(16);
//        columnSet.add(c);
//        nColIndex++;

        //项目编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(16);
        columnSet.add(c);
        nColIndex++;
        
        //合作方
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(15);
        columnSet.add(c);
        nColIndex++;

        //合作方名称
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(30);
        columnSet.add(c);
        nColIndex++;
            
        //是否大客户
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(9);
        columnSet.add(c);
        nColIndex++;
        //大客户简称
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(9);
        columnSet.add(c);
        nColIndex++;
        //是否独家
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(9);
        columnSet.add(c);
        nColIndex++;
        //是否直签
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(9);
        columnSet.add(c);
        nColIndex++;
//        //垫佣金额
//        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
//        c.setWidth(16);
//        columnSet.add(c);
//        nColIndex++;
        //合作周期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(25);
        columnSet.add(c);
        nColIndex++;

        //项目状态
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(9);
        columnSet.add(c);
        nColIndex++;

        //物业类型
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex++;

        //合作模式
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(9);
        columnSet.add(c);
        nColIndex++;

        //大定
        //套数
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;

        //面积
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //金额
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(18);
        columnSet.add(c);
        nColIndex++;

        //成销
        //套数
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;

        //面积
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //金额
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(18);
        columnSet.add(c);
        nColIndex++;

        //应计收入
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 2));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 3;

        //当期：税后净收 + 务费用（附加税，内佣， 运维，线下成本，    资金成本 + 利润
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 6));
        c.setWidth(15);
        if("1".equals(searchType)){
            c.setHidden(true);
        }
        columnSet.add(c);
        nColIndex = nColIndex + 7;


        //实际回款
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 2));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 3;

        //当期比例:期初净收比例,毛收费率,净收比例,内佣占比,运维占比,线下成本占比,资金成本占比,毛利率
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 7));
        c.setWidth(15);
        if("1".equals(searchType)){
            c.setHidden(true);
        }
        columnSet.add(c);
        nColIndex = nColIndex + 8;

        //大定
        //套数
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;

        //面积
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //金额
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(18);
        columnSet.add(c);
        nColIndex++;

        //成销
        //套数
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;

        //面积
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //金额
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(18);
        columnSet.add(c);
        nColIndex++;

        //应计收入
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 2));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 3;

        //累计：税后净收 + 务费用（附加税，内佣， 运维，线下成本，    资金成本 + 利润
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 6));
        c.setWidth(15);
        if("1".equals(searchType)){
            c.setHidden(true);
        }
        columnSet.add(c);
        nColIndex = nColIndex + 7;

        //实际回款
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 2));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 3;

        //累计比例:期初净收比例,毛收费率,净收比例,内佣占比,运维占比,线下成本占比,资金成本占比,毛利率
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 6));
        c.setWidth(15);
        if("1".equals(searchType)){
            c.setHidden(true);
        }
        columnSet.add(c);
        nColIndex = nColIndex + 7;

        return columnSet;
    }

    public Double clearZero(Object value) {
        if (value == null || value.equals("-"))
            return Double.parseDouble("0.00");

        Double data = Double.parseDouble(value.toString());
        return data;
    }

    public int clearIntZero(Object value) {
        if (value == null || value.equals("-"))
            return Integer.parseInt("0");

        int data = Integer.parseInt(value.toString());
        return data;
    }

    /**
     * 创建浮点型cell
     */
    private void setCreateDoubleCell(String key ,int i,short style, boolean fillWithZero, LinkedHashMap<String, Object> map,SpeedSheetWriter sw) throws Exception {
    	double value = 0;
    	Object o = map.get(key);
        try{
        	if (map.get(key) == null) {
        		if (!fillWithZero) {
        			sw.createEmptyCell(i, style);
        			return;
        		}
            } else {
            	if (o instanceof String) {
	            	String s = o.toString();
	            	value = Double.valueOf(s);
            	}else if (o instanceof Double){
	                value = (Double) o;
            	}
            }
        }catch(Exception e){
        	logger.error(map.get(key).toString());
        }
        sw.createCell(i, value, style);
    }

    /**
     * 创建日期型sheet
     */
    private void setCreateDateCell(String dateName, int i, short dateStyle, LinkedHashMap<String, Object> map, SpeedSheetWriter sw) throws Exception {

        Calendar cal = Calendar.getInstance();

        try {
            if (map.get(dateName) == null || "".equals(map.get(dateName).toString()) || "-".equals(map.get(dateName).toString())) {
                sw.createCell(i, "-", dateStyle);
            } else {
                cal.setTime(DateUtils.parseDate(String.valueOf(map.get(dateName))));
                sw.createCell(i, cal, dateStyle);
            }
        } catch (Exception e) {
            logger.error(String.valueOf(map.get(dateName)));
        }

    }
}
