package cn.com.eju.pmls.poi;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.common.util.DateUtils;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.poi.common.ExcelColumnSet;
import cn.com.eju.pmls.poi.common.ExcelStyleEnum;
import cn.com.eju.pmls.poi.common.ExcelUseXmlAbstract;
import cn.com.eju.pmls.poi.common.SpeedSheetWriter;
import cn.com.eju.pmls.statisticsReport.linkDetail.service.PmlsLinkProjectTraceService;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelForLinkProjectTraceBigData extends ExcelUseXmlAbstract {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    private short lockBlackStyle;
    private short bodyBlackStyle;
    private short bodyBlackLiftStyle;
    private short moneyBlackStyle;
    private short intBlackStyle;
    private short dateBlackStyle;
    private short dateLockBlackStyle;
    private String yearMonth;
    private String endDateStr;

    private PmlsLinkProjectTraceService pmlsLinkProjectTraceService=new PmlsLinkProjectTraceService();

    public void downloadSheet(Map<String, Object> reqMap, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("联动项目跟踪");

        Map<String, XSSFCellStyle> style = super.createStyles(wb);
        super.headerStyle = style.get(ExcelStyleEnum.HEADER.getValue()).getIndex();
        this.lockBlackStyle = style.get(ExcelStyleEnum.LOCKED.getValue()).getIndex();
        this.bodyBlackStyle = style.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();
        this.bodyBlackLiftStyle = style.get(ExcelStyleEnum.BODY_BLACK_Left.getValue()).getIndex();
        this.moneyBlackStyle = style.get(ExcelStyleEnum.MONEY_LOCKED_BLACK_RIGHT.getValue()).getIndex();
        this.intBlackStyle = style.get(ExcelStyleEnum.INT_BLACK.getValue()).getIndex();
        this.dateLockBlackStyle = style.get(ExcelStyleEnum.DATE_LOCK_BLACK.getValue()).getIndex();
        this.dateBlackStyle = style.get(ExcelStyleEnum.DATE_BLACK.getValue()).getIndex();
        this.yearMonth = DateUtils.formatDate(new Date(),"yyyy-MM");
        this.endDateStr = (String) reqMap.get("endDate");

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
        SpeedSheetWriter sw = new SpeedSheetWriter(out);
        //sw.beginSheet(setColumnWidth(), 0, 3, "A4");
        sw.beginSheet(setColumnWidth(), 4, 4, "E5");
        int index = 0;
        List<String> listMerge = new ArrayList<>();
        index = this.createSheetHead(sw, index, listMerge);

        PageInfo pageInfo = new PageInfo();
        pageInfo.setCurPage(1);
        pageInfo.setPageLimit(5000);
        ResultData<?> reback = pmlsLinkProjectTraceService.queryLinkProjectTraceList(reqMap, pageInfo);
        List<LinkedHashMap<String, Object>> contentList = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

        Integer totalCount = Integer.valueOf(reback.getTotalCount());
        logger.info("第一次查询，totalCount:" + totalCount + ",时间：" + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
        if (contentList != null && contentList.size() > 0) {
            for (LinkedHashMap<String, Object> map : contentList) {
                index = this.createSheetBody(sw, index, map);
            }
        }
        if (totalCount > contentList.size()) {
            Integer pageCount = (int) Math.ceil(Double.valueOf(totalCount) / (double) contentList.size());
            for (int i = 2; i <= pageCount; i++) {
                pageInfo.setCurPage(i);
                reback = pmlsLinkProjectTraceService.queryLinkProjectTraceList(reqMap, pageInfo);
                logger.info("第" + i + "次查询,共" + pageCount + "次。时间：" + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
                contentList = (List<LinkedHashMap<String, Object>>) reback.getReturnData();
                if (contentList != null && contentList.size() > 0) {
                    for (LinkedHashMap<String, Object> map : contentList) {
                        index = this.createSheetBody(sw, index, map);
                    }
                }
            }
        }
        logger.info("查询完成，时间：" + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));

//        sw.endSheetWithPasswordMerge2(listMerge, true, index);
//        sw.endWorkSheet();
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
        nowColIndex = createBlock(sw, "序号", 1, 3,headerStyle, listMerge);
        nowColIndex = createBlock(sw, "城市", 1, 3,headerStyle, listMerge);
        nowColIndex = createBlock(sw, "核算主体编码", 1, 3,headerStyle, listMerge);
        nowColIndex = createBlock(sw, "核算主体", 1, 3,headerStyle, listMerge);
        nowColIndex = createBlock(sw, "区域", 1, 3,headerStyle, listMerge);
        nowColIndex = createBlock(sw, "项目编号", 1, 3,headerStyle, listMerge);
        nowColIndex = createBlock(sw, "项目名称", 1, 3,headerStyle, listMerge);
        nowColIndex = createBlock(sw, "项目归属核算", 1, 3,headerStyle, listMerge);
        nowColIndex = createBlock(sw, "甲方名称", 1, 3,headerStyle, listMerge);
        nowColIndex = createBlock(sw, "开发商名称", 1, 3,headerStyle, listMerge);
        nowColIndex = createBlock(sw, "是否大客户", 1, 3,headerStyle, listMerge);
        nowColIndex = createBlock(sw, "大客户简称", 1, 3,headerStyle, listMerge);
        nowColIndex = createBlock(sw, "概况", 7, 1, listMerge);
        /*nowColIndex = createBlock(sw, "最新预计（实&预）", 10, 1, listMerge);*/
        nowColIndex = createBlock(sw, "当年实际", 10, 1, listMerge);
        /*nowColIndex = createBlock(sw, "累计实际", 16, 1, listMerge);*/
        nowColIndex = createBlock(sw, "往年应计", 5, 1, listMerge);
        nowColIndex = createBlock(sw, "总累计", 12, 1, listMerge);
        /*nowColIndex = createBlock(sw, "垫佣跟踪", 3, 1, listMerge);*/
        nowColIndex = createBlock(sw, "回款账龄分析("+endDateStr+")", 18, 1, listMerge);
        nowColIndex = createBlock(sw, "开票账龄("+endDateStr+")", 7, 2, listMerge);

        nowColIndex = createBlock(sw, "有垫佣的应计未回账龄("+endDateStr+")", 6, 2, listMerge);
        nowColIndex = createBlock(sw, "应计未转应收账龄("+endDateStr+")", 7, 2, listMerge);

        sw.endRow();
        //第二行
        sw.insertRow(++nowRowIndex);
        nowColIndex = 0;
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "CRM建项日期", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "销售状态", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "项目状态", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "OA立项完成日期", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "甲方结算节点", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "合同周期", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "是否垫佣", 1, 2, listMerge);

        /*nowColIndex = createBlock(sw, "大定金额", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "成销金额", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "税后收入", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "税后返佣", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "税前净收", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "税后净收", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "营业费用", 3, 1, listMerge);
        nowColIndex = createBlock(sw, "预计利润", 1, 2, listMerge);*/
        // 当年实际
        nowColIndex = createBlock(sw, "大定金额", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "成销金额", 1, 2, listMerge);
        /*nowColIndex = createBlock(sw, "税后收入", 1, 2, listMerge);*/
        nowColIndex = createBlock(sw, "税前收入", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "应收收入", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "应计实收", 1, 2, listMerge);
        /*nowColIndex = createBlock(sw, "财务实收", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "税后返佣", 1, 2, listMerge);*/
        nowColIndex = createBlock(sw, "税前返佣", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "实际返佣", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "税前净收", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "税前垫佣", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "实际垫佣", 1, 2, listMerge);
        /*nowColIndex = createBlock(sw, "税后净收", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "营业费用", 3, 1, listMerge);
        nowColIndex = createBlock(sw, "实际利润", 1, 2, listMerge);*/
        //往年应计
        nowColIndex = createBlock(sw, "未回余额", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "当期回款", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "当期调整应计", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "当期调整坏账", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "当期未回余额", 1, 2, listMerge);
        // 总累计
        nowColIndex = createBlock(sw, "大定套数", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "大定金额", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "成销套数", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "成销金额", 1, 2, listMerge);
        /*nowColIndex = createBlock(sw, "应计收入税前", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "应计收入税后", 1, 2, listMerge);*/
        nowColIndex = createBlock(sw, "税前收入", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "应收收入", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "应计实收", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "应计返佣", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "实际返佣", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "税前净收", 1, 2, listMerge);
        /*nowColIndex = createBlock(sw, "税后净收", 1, 2, listMerge);*/
        nowColIndex = createBlock(sw, "税前垫佣", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "实际垫佣", 1, 2, listMerge);
        /*nowColIndex = createBlock(sw, "财务实收", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "应计内佣", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "实际营业费用", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "实际项目利润", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "实际项目利润率", 1, 2, listMerge);*/


        /*nowColIndex = createBlock(sw, "前三个月回款金额", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "垫佣方案", 1, 2, listMerge);*/
        /*nowColIndex = createBlock(sw, "应计垫佣", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "实际垫佣", 1, 2, listMerge);*/
        /*nowColIndex = createBlock(sw, "实际垫佣已回款", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "实际垫佣未回款", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "应收垫佣", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "应收垫佣已回款", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "应收垫佣未回款", 1, 2, listMerge);*/

        nowColIndex = createBlock(sw, "应计未回款", 6, 1, listMerge);
        nowColIndex = createBlock(sw, "垫佣未回款", 6, 1, listMerge);
        nowColIndex = createBlock(sw, "应收未回款（含已开票）", 6, 1, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        sw.endRow();
        //第三行
        sw.insertRow(++nowRowIndex);
        nowColIndex = 0;
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);

        /*nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "内佣", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "销售成本", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "工资+奖金", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);*/

        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        /*nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "内佣", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "销售成本", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "工资+奖金", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);*/

        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        /*nowColIndex = createBlock(sw, "", 1, 0, listMerge);*/
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        /*nowColIndex = createBlock(sw, "", 1, 0, listMerge);*/
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        /*nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);*/

        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);

        /*nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);*/
        /*nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);*/
        /*nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);*/

        nowColIndex = createBlock(sw, "账龄合计", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "1-3个月", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "4-6个月", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "7-9个月", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "10-12个月", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "一年以上", 1, 0, listMerge);

        nowColIndex = createBlock(sw, "账龄合计", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "1-3个月", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "4-6个月", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "7-9个月", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "10-12个月", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "一年以上", 1, 0, listMerge);

        nowColIndex = createBlock(sw, "账龄合计", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "1-3个月", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "4-6个月", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "7-9个月", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "10-12个月", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "一年以上", 1, 0, listMerge);


        nowColIndex = createBlock(sw, "账龄合计", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "1-3个月", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "4-6个月", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "7-9个月", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "10-12个月", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "13-24个月", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "24个月以上", 1, 0, listMerge);

        nowColIndex = createBlock(sw, "账龄合计", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "1-3个月", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "4-6个月", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "7-9个月", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "10-12个月", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "一年以上", 1, 0, listMerge);

        nowColIndex = createBlock(sw, "账龄合计", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "1-3个月", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "4-6个月", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "7-9个月", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "10-12个月", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "13-24个月", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "24个月以上", 1, 0, listMerge);


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

        short lockStyle = lockBlackStyle;
        short bodyStyle = bodyBlackStyle;
        short bodyLeftStyle = bodyBlackLiftStyle;
        short moneyStyle = moneyBlackStyle;
        short intStyle = intBlackStyle;
        short dateLockStyle = dateLockBlackStyle;
        short dateStyle = dateBlackStyle;

        sw.createCellStr(i++, index - 2, bodyLeftStyle);
        sw.createCell(i++, map.get("cityName") == null ? "" : map.get("cityName").toString(), bodyLeftStyle);
        sw.createCell(i++, map.get("accountProjectNo") == null ? "" : map.get("accountProjectNo").toString(), bodyLeftStyle);
        sw.createCell(i++, map.get("accountProject") == null ? "" : map.get("accountProject").toString(), bodyLeftStyle);
        sw.createCell(i++, map.get("qyName") == null ? "" : map.get("qyName").toString(), bodyLeftStyle);
        sw.createCell(i++, map.get("projectNo") == null ? "" : map.get("projectNo").toString(), bodyLeftStyle);
        sw.createCell(i++, map.get("projectName") == null ? "" : map.get("projectName").toString(), bodyLeftStyle);
        sw.createCell(i++, map.get("accountProjectStr") == null ? "" : map.get("accountProjectStr").toString(), bodyLeftStyle);
        sw.createCell(i++, map.get("partnerNm") == null ? "" : map.get("partnerNm").toString(), bodyLeftStyle);
        sw.createCell(i++, map.get("devCompany") == null ? "" : map.get("devCompany").toString(), bodyLeftStyle);
        sw.createCell(i++, map.get("isBigCustomer") == null ? "" : map.get("isBigCustomer").toString(), bodyLeftStyle);
        sw.createCell(i++, map.get("bigCustomer") == null ? "" : map.get("bigCustomer").toString(), bodyLeftStyle);
        if(map.get("projectNo").equals("-")){
            sw.createCell(i++, "-", dateStyle);
        }else{
            sw.createCell(i++, map.get("projectCrtDt") == null ? "" : map.get("projectCrtDt").toString(), dateStyle);
        }
        sw.createCell(i++, map.get("saleStatusNm") == null ? "" : map.get("saleStatusNm").toString(), bodyStyle);
        sw.createCell(i++, map.get("signStatusNm") == null ? "" : map.get("signStatusNm").toString(), bodyStyle);
        if(map.get("projectNo").equals("-")){
            sw.createCell(i++, "-", dateStyle);
        }else{
            sw.createCell(i++, map.get("budgetDate") == null ? "" : map.get("budgetDate").toString(), dateStyle);
        }
        sw.createCell(i++, map.get("commissionConditionNm") == null ? "" : map.get("commissionConditionNm").toString(), bodyStyle);
        // 合同周期
        sw.createCell(i++, map.get("htDate") == null ? "" : map.get("htDate").toString(), bodyLeftStyle);
        sw.createCell(i++, map.get("isPadCom") == null ? "" : map.get("isPadCom").toString(), bodyStyle);
        /*sw.createCell(i++, clearZero(map.get("abaRoughAmt")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("abaDealAmt")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("abaAtIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("abaAtRebate")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("abaPtNetIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("abaAtNetIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("abaBusInnerCom")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("abaBusSaleCost")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("abaBusWage")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("abaProfit")), moneyStyle);*/

        sw.createCell(i++, clearZero(map.get("actRoughAmt")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("actDealAmt")), moneyStyle);
        /*sw.createCell(i++, clearZero(map.get("actAtIncome")), moneyStyle);*/
        //税前收入
        sw.createCell(i++, clearZero(map.get("actAtIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("actRecieveIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("actRealIncome")), moneyStyle);
        /*sw.createCell(i++, clearZero(map.get("actFinanceIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("actAtRebate")), moneyStyle);*/
        //税前返佣
        sw.createCell(i++, clearZero(map.get("actAtRebate")), moneyStyle);
        //实际返佣
        sw.createCell(i++, clearZero(map.get("actFinanceIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("actPtNetIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("actAtDyIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("actAtSjDyIncome")), moneyStyle);
        /*sw.createCell(i++, clearZero(map.get("actAtNetIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("actBusInnerCom")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("actBusSaleCost")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("actBusWage")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("actRealProfit")), moneyStyle);*/

        sw.createCell(i++, clearZero(map.get("wnwhAmonut")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dqhkAmonut")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dqyjAmonut")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dqhzAmonut")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dqwhAmount")), moneyStyle);

        sw.createCell(i++, clearIntZero(map.get("totalRoughNum")), intStyle);
        sw.createCell(i++, clearZero(map.get("totalRoughAmt")), moneyStyle);//大定金额
        sw.createCell(i++, clearIntZero(map.get("totalDealNum")), intStyle);
        sw.createCell(i++, clearZero(map.get("totalDealAmt")), moneyStyle);//成销金额
        sw.createCell(i++, clearZero(map.get("totalIncomesq")), moneyStyle);//税前收入
        sw.createCell(i++, clearZero(map.get("totalRecieveIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalRecieveRealIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalRecieveRebate")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalRealRecieveRebate")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalPtNetIncome")), moneyStyle);
        /*sw.createCell(i++, clearZero(map.get("totalAtNetIncome")), moneyStyle);*/
        sw.createCell(i++, clearZero(map.get("totalAtDyIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalAtSjDyIncome")), moneyStyle);
        /*sw.createCell(i++, clearZero(map.get("totalFinanceIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalRecieveInnerCom")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalRealBusFee")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalRealProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalRealProfitRate")), moneyStyle);*/


        /*sw.createCell(i++, clearZero(map.get("pre3Amt")), moneyStyle);
        sw.createCell(i++, map.get("padComRule") == null ? "" : map.get("padComRule").toString(), bodyStyle);*/
        /*sw.createCell(i++, clearZero(map.get("accruedPadComAmt")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("realPadComAmt")), moneyStyle);*/
        /*sw.createCell(i++, clearZero(map.get("padComBack")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("padComUnBack")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("receivePadComAmt")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("receivePadComBack")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("receivePadComUnBack")), moneyStyle);*/

        sw.createCell(i++, clearZero(map.get("yjAmountAll")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("yjAmount13")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("yjAmount46")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("yjAmount79")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("yjAmount1012")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("yjAmount1399")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("dyAmountAll")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dyAmount13")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dyAmount46")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dyAmount79")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dyAmount1012")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dyAmount1399")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("ysAmountAll")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("ysAmount13")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("ysAmount46")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("ysAmount79")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("ysAmount1012")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("ysAmount1399")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("kpAmountAll")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("kpAmount13")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("kpAmount46")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("kpAmount79")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("kpAmount1012")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("kpAmount1324")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("kpAmount2499")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("dyyjwhAll")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dyyjwh13")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dyyjwh46")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dyyjwh79")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dyyjwh1012")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dyyjwh1399")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("yjysAll")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("yjys13")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("yjys46")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("yjys79")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("yjys1012")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("yjys1324")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("yjys2599")), moneyStyle);


        sw.endRow();
        index++;

        return index;
    }


    /**
     * 设置列宽
     */
    public List<ExcelColumnSet> setColumnWidth() {
        List<ExcelColumnSet> columnSet = new ArrayList<>();

        ExcelColumnSet c;

        int nColIndex = 1;

        //序号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(5);
        columnSet.add(c);
        nColIndex++;

        //城市
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex ++;

        //项目编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex ++;

        //核算主体code
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex ++;

        //核算主体
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex ++;

        //区域
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex ++;

        //项目名称
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
        columnSet.add(c);
        nColIndex++;

        //项目归属核算
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(25);
        columnSet.add(c);
        nColIndex++;

        //甲方名称
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(25);
        columnSet.add(c);
        nColIndex++;

        //开发商名称
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(25);
        columnSet.add(c);
        nColIndex++;

        //是否大客户
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //大客户简称
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex++;

        //CRM建项日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(15);
        columnSet.add(c);
        nColIndex++;

        //销售状态
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;

        //签约状态
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;

        //OA立项完成日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(15);
        columnSet.add(c);
        nColIndex++;

        //甲方结算节点
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(15);
        columnSet.add(c);
        nColIndex++;

        //合同周期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
        columnSet.add(c);
        nColIndex++;

        //是否垫佣
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;

        //大定金额->实际项目利润率
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex+26));
        c.setWidth(18);
        columnSet.add(c);
        nColIndex+=26;

        //账龄合计->一年以上
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex+17));
        c.setWidth(18);
        columnSet.add(c);
        nColIndex+=17;

        //开票账龄
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex+8));
        c.setWidth(18);
        columnSet.add(c);
        nColIndex+=8;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex+13));
        c.setWidth(18);
        columnSet.add(c);
        nColIndex+=13;

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
}
