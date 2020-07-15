package cn.com.eju.deal.poi;

import cn.com.eju.deal.common.util.DateUtils;
import cn.com.eju.deal.poi.common.ExcelColumnSet;
import cn.com.eju.deal.poi.common.ExcelStyleEnum;
import cn.com.eju.deal.poi.common.ExcelUseXmlAbstract;
import cn.com.eju.deal.poi.common.SpeedSheetWriter;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelForLinkProjectTrace extends ExcelUseXmlAbstract {

    private short lockBlackStyle;
    private short bodyBlackStyle;
    private short moneyBlackStyle;
    private short intBlackStyle;
    private short dateBlackStyle;
    private String yearMonth;

    public void downloadSheet(List<LinkedHashMap<String, Object>> list, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("联动项目跟踪");

        Map<String, XSSFCellStyle> style = super.createStyles(wb);
        super.headerStyle = style.get(ExcelStyleEnum.HEADER.getValue()).getIndex();
        this.lockBlackStyle = style.get(ExcelStyleEnum.LOCKED.getValue()).getIndex();
        this.bodyBlackStyle = style.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();
        this.moneyBlackStyle = style.get(ExcelStyleEnum.MONEY_LOCKED_BLACK_RIGHT.getValue()).getIndex();
        this.intBlackStyle = style.get(ExcelStyleEnum.INT_BLACK.getValue()).getIndex();
        this.dateBlackStyle = style.get(ExcelStyleEnum.DATE_LOCK_BLACK.getValue()).getIndex();
        this.yearMonth = DateUtils.formatDate(new Date(),"yyyy-MM");

        File tempExcel = File.createTempFile("tempExcel", ".xlsx");
        File temp = File.createTempFile("sheet", ".xml");
        try {
            FileOutputStream fos = new FileOutputStream(tempExcel);
            wb.write(fos);
            fos.close();
            String sheetRef = sheet.getPackagePart().getPartName().getName();
            Writer fw = new PrintWriter(temp, "UTF-8");
            this.createSheet(fw, list);
            fw.close();
            super.substitute(tempExcel, temp, sheetRef.substring(1), os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            os.close();
        }
    }

    private void createSheet(Writer out, List<LinkedHashMap<String, Object>> results) throws Exception {
        SpeedSheetWriter sw = new SpeedSheetWriter(out);
        //sw.beginSheet(setColumnWidth(), 0, 3, "A4");
        sw.beginSheet(setColumnWidth(), 4, 4, "E5");
        int index = 0;
        List<String> listMerge = new ArrayList<>();
        index = this.createSheetHead(sw, index, listMerge);
        if (results != null && results.size() > 0) {
            for (LinkedHashMap<String, Object> map : results) {
                index = this.createSheetBody(sw, index, map);
            }
        }

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
        nowColIndex = createBlock(sw, "项目编号", 1, 3,headerStyle, listMerge);
        nowColIndex = createBlock(sw, "项目名称", 1, 3,headerStyle, listMerge);
        nowColIndex = createBlock(sw, "甲方名称", 1, 3,headerStyle, listMerge);
        nowColIndex = createBlock(sw, "开发商名称", 1, 3,headerStyle, listMerge);
        nowColIndex = createBlock(sw, "概况", 5, 1, listMerge);
        nowColIndex = createBlock(sw, "最新预计（实&预）", 10, 1, listMerge);
        nowColIndex = createBlock(sw, "当年实际", 12, 1, listMerge);
        nowColIndex = createBlock(sw, "累计实际", 16, 1, listMerge);
        nowColIndex = createBlock(sw, "垫佣跟踪", 10, 1, listMerge);
        nowColIndex = createBlock(sw, "账龄跟踪("+yearMonth+")", 12, 1, listMerge);

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
        nowColIndex = createBlock(sw, "CRM建项日期", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "销售状态", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "项目状态", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "OA立项完成日期", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "甲方结算节点", 1, 2, listMerge);

        nowColIndex = createBlock(sw, "大定金额", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "成销金额", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "税后收入", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "税后返佣", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "税前净收", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "税后净收", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "营业费用", 3, 1, listMerge);
        nowColIndex = createBlock(sw, "预计利润", 1, 2, listMerge);

        nowColIndex = createBlock(sw, "大定金额", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "成销金额", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "税后收入", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "实收", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "财务实收", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "税后返佣", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "税前净收", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "税后净收", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "营业费用", 3, 1, listMerge);
        nowColIndex = createBlock(sw, "实际利润", 1, 2, listMerge);

        nowColIndex = createBlock(sw, "实际大定套数", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "实际大定金额", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "实际成销套数", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "实际成销金额", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "应计收入", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "税前净收", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "税后净收", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "应收收入", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "应计实收", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "财务实收", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "应计返佣", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "实际返佣", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "应计内佣", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "实际营业费用", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "实际项目利润", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "实际项目利润率", 1, 2, listMerge);

        nowColIndex = createBlock(sw, "是否垫佣", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "前三个月回款金额", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "垫佣方案", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "实际垫佣", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "应计垫佣", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "应计垫佣已回款", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "应计垫佣未回款", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "应收垫佣", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "应收垫佣已回款", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "应收垫佣未回款", 1, 2, listMerge);

        nowColIndex = createBlock(sw, "应计未回款", 6, 1, listMerge);
        nowColIndex = createBlock(sw, "应收未回款（含已开票）", 6, 1, listMerge);

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
        nowColIndex = createBlock(sw, "内佣", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "销售成本", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "工资+奖金", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);

        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "内佣", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "销售成本", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "工资+奖金", 1, 0, listMerge);
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
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);

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
        short moneyStyle = moneyBlackStyle;
        short intStyle = intBlackStyle;
        short dateStyle = dateBlackStyle;

        sw.createCellStr(i++, index - 2, lockStyle);
        sw.createCell(i++, map.get("cityName") == null ? "" : map.get("cityName").toString(), lockStyle);
        sw.createCell(i++, map.get("projectNo") == null ? "" : map.get("projectNo").toString(), lockStyle);
        sw.createCell(i++, map.get("projectName") == null ? "" : map.get("projectName").toString(), lockStyle);
        sw.createCell(i++, map.get("partnerNm") == null ? "" : map.get("partnerNm").toString(), lockStyle);
        sw.createCell(i++, map.get("devCompany") == null ? "" : map.get("devCompany").toString(), lockStyle);
        sw.createCell(i++, map.get("projectCrtDt") == null ? "" : map.get("projectCrtDt").toString(), dateStyle);
        sw.createCell(i++, map.get("saleStatusNm") == null ? "" : map.get("saleStatusNm").toString(), bodyStyle);
        sw.createCell(i++, map.get("signStatusNm") == null ? "" : map.get("signStatusNm").toString(), bodyStyle);
        sw.createCell(i++, map.get("budgetDate") == null ? "" : map.get("budgetDate").toString(), dateStyle);
        sw.createCell(i++, map.get("commissionConditionNm") == null ? "" : map.get("commissionConditionNm").toString(), bodyStyle);

        sw.createCell(i++, clearZero(map.get("abaRoughAmt")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("abaDealAmt")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("abaAtIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("abaAtRebate")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("abaPtNetIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("abaAtNetIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("abaBusInnerCom")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("abaBusSaleCost")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("abaBusWage")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("abaProfit")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("actRoughAmt")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("actDealAmt")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("actAtIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("actRealIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("actFinanceIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("actAtRebate")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("actPtNetIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("actAtNetIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("actBusInnerCom")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("actBusSaleCost")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("actBusWage")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("actRealProfit")), moneyStyle);

        sw.createCell(i++, clearIntZero(map.get("totalRoughNum")), intStyle);
        sw.createCell(i++, clearZero(map.get("totalRoughAmt")), moneyStyle);
        sw.createCell(i++, clearIntZero(map.get("totalDealNum")), intStyle);
        sw.createCell(i++, clearZero(map.get("totalDealAmt")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalPtNetIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalAtNetIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalRecieveIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalRecieveRealIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalFinanceIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalRecieveRebate")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalRealRecieveRebate")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalRecieveInnerCom")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalRealBusFee")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalRealProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalRealProfitRate")), moneyStyle);

        sw.createCell(i++, map.get("isPadCom") == null ? "" : map.get("isPadCom").toString(), bodyStyle);
        sw.createCell(i++, clearZero(map.get("pre3Amt")), moneyStyle);
        sw.createCell(i++, map.get("padComRule") == null ? "" : map.get("padComRule").toString(), bodyStyle);
        sw.createCell(i++, clearZero(map.get("realPadComAmt")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("accruedPadComAmt")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("padComBack")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("padComUnBack")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("receivePadComAmt")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("receivePadComBack")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("receivePadComUnBack")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("yjAmountAll")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("yjAmount13")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("yjAmount46")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("yjAmount79")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("yjAmount1012")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("yjAmount1399")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("ysAmountAll")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("ysAmount13")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("ysAmount46")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("ysAmount79")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("ysAmount1012")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("ysAmount1399")), moneyStyle);

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

        //项目名称
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
        columnSet.add(c);
        nColIndex++;

        //甲方名称
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
        columnSet.add(c);
        nColIndex++;

        //开发商名称
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
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

        //大定金额->实际项目利润率
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex+38));
        c.setWidth(18);
        columnSet.add(c);
        nColIndex+=38;

        //是否垫佣
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;

        //前三个月回款金额
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(18);
        columnSet.add(c);
        nColIndex++;

        //垫佣方案
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(50);
        columnSet.add(c);
        nColIndex++;

        //实际垫佣->应收垫佣未回款
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex+6));
        c.setWidth(18);
        columnSet.add(c);
        nColIndex+=3;

        //账龄合计->一年以上
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex+11));
        c.setWidth(18);
        columnSet.add(c);
        nColIndex+=11;

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
