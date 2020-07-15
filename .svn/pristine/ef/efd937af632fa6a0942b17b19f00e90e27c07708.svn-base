package cn.com.eju.pmls.poi;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.pmls.poi.common.ExcelColumnSet;
import cn.com.eju.pmls.poi.common.ExcelStyleEnum;
import cn.com.eju.pmls.poi.common.ExcelUseXmlAbstract;
import cn.com.eju.pmls.poi.common.SpeedSheetWriter;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelForAllocate extends ExcelUseXmlAbstract {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());


    XSSFWorkbook wb;
    private XSSFSheet sheet;

    private Map<String, XSSFCellStyle> styles;

    private static String SUB_FORMULAR = "M_-N_";
    private static String SUB_FORMULAR01 = "K_-L_";
//    private static String SUB_FORMULAR01 = "N_/(1+P_/100)";


    public void downloadSheet(Map<String, String> head, List<LinkedHashMap<String, Object>> list, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);
        wb = new XSSFWorkbook();
        sheet = wb.createSheet();
        styles = super.createStyles(wb);
        super.headerStyle = styles.get(ExcelStyleEnum.HEADER.getValue()).getIndex();

        File tempExcel = File.createTempFile("tempExcel", ".xlsx");
        File temp = File.createTempFile("sheet", ".xml");
        try {
            FileOutputStream fos = new FileOutputStream(tempExcel);
            wb.write(fos);
            fos.close();
            String sheetRef = sheet.getPackagePart().getPartName().getName();
            Writer fw = new PrintWriter(temp, "UTF-8");
            this.createSheet(fw, head, list);
            fw.close();
            super.substitute(tempExcel, temp, sheetRef.substring(1), os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            os.close();
        }
    }

    private void createSheet(Writer out, Map<String, String> head, List<LinkedHashMap<String, Object>> results) throws Exception {
        SpeedSheetWriter sw = new SpeedSheetWriter(out);
        sw.beginSheet(setColumnWidth(), 0, 2, "A3");

        List<String> listMerge = new ArrayList<String>();
        int index = 0;
        index = this.createSheetHead(sw, index, head, listMerge);
        if (results != null && results.size() > 0) {
            index = this.createSheetHJLine(sw, index, results);
            for (LinkedHashMap<String, Object> map : results) {
                index = this.createSheetBody(sw, index, map);
            }
        }

        sw.endSheetWithPasswordMerge3(listMerge, true, "A2:C2");
        sw.endWorkSheet();

    }

    /**
     * 创建sheet头部内容
     */
    private int createSheetHead(SpeedSheetWriter sw, int index, Map<String, String> head, List<String> listMerge) throws IOException {
        nowRowIndex = index;
        nowColIndex = 0;

        short mainStyle = headerStyle;
        //首行隐藏字段
        sw.insertHiddenRow(nowRowIndex);
        nowColIndex = createBlock(sw, head.get("UserId"), 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "EEB8", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, head.get("UserName"), 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, head.get("cityNo"), 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, head.get("reportNos"), 1, 1, mainStyle, listMerge);
        sw.endRow();

        nowRowIndex++;

        nowColIndex = 0;
        //第一行
        sw.insertRow(nowRowIndex);
        nowColIndex = createBlock(sw, "订单编号", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "客户姓名", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "楼室号", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "大定面积", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "大定总价", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "大定日期", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "大定过审日期", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "成销面积", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "成销总价", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "成销日期", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "成销收入(税前)", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "成销实收金额(税前)", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "未回款金额(税前)", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "本次拆分金额(元)", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "剩余拆分金额(元)", 1, 1, mainStyle, listMerge);

        sw.endRow();

        nowRowIndex++;
        return nowRowIndex;
    }

    /**
     * 创建合计行（第一行）
     *
     * @param sw
     * @param index
     * @param results
     * @return
     */
    private int createSheetHJLine(SpeedSheetWriter sw, int index, List<LinkedHashMap<String, Object>> results) throws IOException {
        sw.insertRow(index);
        int i = 0;
        short cnStyle = styles.get(ExcelStyleEnum.LOCKED.getValue()).getIndex();
        short numLockStyle = styles.get(ExcelStyleEnum.LOCKED_NUM.getValue()).getIndex();
        Integer rowsNum = results.size();
        rowsNum = rowsNum + 3;
        //订单编号 A
        sw.createCell(i++, "合计", cnStyle);
        //客户姓名 B
        sw.createCell(i++, "", cnStyle);
        //楼室号 C
        sw.createCell(i++, "", cnStyle);
        //大定面积 D
        sw.createCell(i++, "", cnStyle);
        //大定总价 E
        sw.createCell(i++, "", cnStyle);
        //大定日期 F
        sw.createCell(i++, "", cnStyle);
        //大定过审日期 G
        sw.createCell(i++, "", cnStyle);
        //成销面积 H
        sw.createCell(i++, "", cnStyle);
        //成销总价 I
        sw.createCell(i++, "", cnStyle);
        //成销日期 J
        sw.createCell(i++, "", cnStyle);
        //成销收入(税前) K
        String formulaK = "SUM(K4:K" + rowsNum + ")";
        sw.createCellWithFormula(i++, formulaK, numLockStyle);
        //成销实收金额(税前) L
        String formulaL = "SUM(L4:L" + rowsNum + ")";
        sw.createCellWithFormula(i++, formulaL, numLockStyle);
        //未回款金额(税前) M = K -L
        String formulaM = "SUM(M4:M" + rowsNum + ")";
        sw.createCellWithFormula(i++, formulaM, numLockStyle);
        //本次拆分金额(税前) N
        String formulaN = "SUM(N4:N" + rowsNum + ")";
        sw.createCellWithFormula(i++, formulaN, numLockStyle);
        //公式--剩余拆分金额(税前) O
        String formulaO = "SUM(O4:O" + rowsNum + ")";
        sw.createCellWithFormula(i++, formulaO, numLockStyle);

        sw.endRow();
        index++;

        return index;
    }

    /**
     * 创建sheet具体内容
     */
    private int createSheetBody(SpeedSheetWriter sw, int index, LinkedHashMap<String, Object> map) throws Exception {
        sw.insertRow(index);
        int i = 0;

        short cnStyle = styles.get(ExcelStyleEnum.LOCKED.getValue()).getIndex();
//        short numStyle = styles.get(ExcelStyleEnum.DOUBLE_BLACK.getValue()).getIndex();
        short numLockStyle = styles.get(ExcelStyleEnum.LOCKED_NUM.getValue()).getIndex();
        short bodyBlackStyle = styles.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();
//        short dateBlockStyle = styles.get(ExcelStyleEnum.DATE_BLACK.getValue()).getIndex();
        short dateLockStyle = styles.get(ExcelStyleEnum.DATE_LOCK_BLACK.getValue()).getIndex();

  /*      Integer num = Integer.valueOf(map.get("num").toString());
        if (num < 0) {
            cnStyle = styles.get(ExcelStyleEnum.LOCKED_RED.getValue()).getIndex();
            numStyle = styles.get(ExcelStyleEnum.DOUBLE_RED.getValue()).getIndex();
            numLockStyle = styles.get(ExcelStyleEnum.LOCKED_NUM_RED.getValue()).getIndex();
            bodyBlackStyle = styles.get(ExcelStyleEnum.BODY_RED.getValue()).getIndex();
            dateBlockStyle = styles.get(ExcelStyleEnum.DATE_RED.getValue()).getIndex();
            dateLockStyle = styles.get(ExcelStyleEnum.DATE_LOCK_RED.getValue()).getIndex();
        }*/

        //订单编号 A
        sw.createCell(i++, map.get("reportId").toString(), cnStyle);
        //客户姓名 B
        sw.createCell(i++, map.get("customerName") == null ? "" : map.get("customerName").toString(), cnStyle);
        //楼室号 C
        sw.createCell(i++, map.get("buildingNo") == null ? "" : map.get("buildingNo").toString(), cnStyle);
        //大定面积 D
        sw.createCell(i++, clearZero(map.get("roughtArea")), cnStyle);
        //大定总价 E
        sw.createCell(i++, clearZero(map.get("roughtAmount")), cnStyle);
        //大定日期 F
        setCreateDateCell("roughtDate", i++, dateLockStyle, map, sw);
        //大定过审日期 G
        setCreateDateCell("roughAuditTime", i++, dateLockStyle, map, sw);
        //成销面积 H
        sw.createCell(i++, clearZero(map.get("cxArea")), cnStyle);
        //成销总价 I
        sw.createCell(i++, clearZero(map.get("cxAmount")), cnStyle);
        //成销日期 J
        setCreateDateCell("dealDate", i++, dateLockStyle, map, sw);
        //成销收入(税前) K
        sw.createCell(i++, clearZero(map.get("yjAmount_bef")), cnStyle);
        //成销实收金额(税前) L
        sw.createCell(i++, clearZero(map.get("sjAmount_bef")), cnStyle);
        //未回款金额(税前) M = K -L
        String formula01 = SUB_FORMULAR01.replace("_", String.valueOf(index + 1));
        sw.createCellWithFormula(i++, formula01, numLockStyle);
        //本次拆分金额(税前) N
        sw.createCell(i++, "", bodyBlackStyle);
        //公式--剩余拆分金额(税前) O
        String formula = SUB_FORMULAR.replace("_", String.valueOf(index + 1));
        sw.createCellWithFormula(i++, formula, numLockStyle);
        //正记录的本次拆分最大金额 P 隐藏列
        sw.createCell(i++, clearZero(map.get("allocatAmount_bef")), cnStyle);
        //负记录的本次拆分最大金额 Q 隐藏列
        sw.createCell(i++, clearZero(map.get("neAllocatAmount_bef")), cnStyle);

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

        //订单编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(15);
        columnSet.add(c);
        nColIndex++;

        //客户姓名
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //楼室号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //大定面积
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(18);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //大定总价
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //大定日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //大定过审日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //成销面积
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(18);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //成销总价
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //成销日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //成销收入（税前）
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //成销实收金额（税前）
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //未回款金额（税前）
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //本次拆分金额（税前）
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //剩余拆分金额（税前）
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 1));
        c.setHidden(true);
        columnSet.add(c);

        return columnSet;
    }

    public Double clearZero(Object value) {
        if (value == null || value.equals("-"))
            return Double.parseDouble("0.00");

        Double data = Double.parseDouble(value.toString());
        return data;
    }

    private void setCreateDateCell(String key, int i, short style, LinkedHashMap<String, Object> map, SpeedSheetWriter sw) throws Exception {
        String value = "";
        Object obj = map.get(key);
        if (obj != null && obj instanceof String) {
            if (obj.toString().length() >= 10) value = obj.toString().substring(0, 10);
        } else {
            sw.createEmptyCell(i, style);
            return;
        }
        sw.createCell(i++, value, style);
    }
}