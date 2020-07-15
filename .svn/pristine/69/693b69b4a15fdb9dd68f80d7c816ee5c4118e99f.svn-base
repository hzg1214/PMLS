package cn.com.eju.deal.poi;

import cn.com.eju.deal.poi.common.ExcelColumnSet;
import cn.com.eju.deal.poi.common.ExcelStyleEnum;
import cn.com.eju.deal.poi.common.ExcelUseXmlAbstract;
import cn.com.eju.deal.poi.common.SpeedSheetWriter;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelForLinkConversionRate extends ExcelUseXmlAbstract {

    private short bodyBlackStyle;
    private short bodyBlackBoldStyle;
    private short money_unlocked_black;
    private short money_unlocked_black_bold;
    private short int_black;
    private short int_black_bold;
    private String year;

    public ExcelForLinkConversionRate(String year) {
        this.year = year;
    }

    public void downloadSheet(List<LinkedHashMap<String, Object>> list, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("联动转化率分析");

        Map<String, XSSFCellStyle> style = super.createStyles(wb);
        super.headerStyle = style.get(ExcelStyleEnum.HEADER.getValue()).getIndex();
        this.bodyBlackStyle = style.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();
        this.bodyBlackBoldStyle = style.get(ExcelStyleEnum.BODY_BLACK_BOLD.getValue()).getIndex();
        this.money_unlocked_black = style.get(ExcelStyleEnum.MONEY_UNLOCKED_BLACK.getValue()).getIndex();
        this.money_unlocked_black_bold = style.get(ExcelStyleEnum.MONEY_UNLOCKED_BLACK_BOLD.getValue()).getIndex();
        this.int_black = style.get(ExcelStyleEnum.INT_BLACK.getValue()).getIndex();
        this.int_black_bold = style.get(ExcelStyleEnum.INT_BLACK_BOLD.getValue()).getIndex();

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
        sw.beginSheet(setColumnWidth(), 0, 2, "A3");
        int index = 0;
        List<String> listMerge = new ArrayList<>();
        index = this.createSheetHead(sw, index, listMerge);
        if (results != null && results.size() > 0) {
            for (LinkedHashMap<String, Object> map : results) {
                index = this.createSheetBody(sw, index, map);
            }
        }
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
        nowColIndex = createBlock(sw, "序号", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "归属区域", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "归属城市", 1, 2, listMerge);
//        nowColIndex = createBlock(sw, "归属中心", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "项目编号", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "项目名称", 1, 2, listMerge);

        nowColIndex = createBlock(sw, year + "年当年", 8, 1, listMerge);
        nowColIndex = createBlock(sw, year + "年第一季度", 8, 1, listMerge);
        nowColIndex = createBlock(sw, year + "年第二季度", 8, 1, listMerge);
        nowColIndex = createBlock(sw, year + "年第三季度", 8, 1, listMerge);
        nowColIndex = createBlock(sw, year + "年第四季度", 8, 1, listMerge);

        sw.endRow();
        //第二行
        sw.insertRow(++nowRowIndex);
        nowColIndex = 0;
        nowColIndex = createBlock(sw, "序号", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "归属区域", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "归属城市", 1, 0, listMerge);
//        nowColIndex = createBlock(sw, "归属中心", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "项目编号", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "项目名称", 1, 0, listMerge);

        nowColIndex = createBlock(sw, "结转大定", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "新增大定", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "累计大定", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "新增成销", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "结转成销转化率", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "新增成销转化率", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "整体成销转化率", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "大定转成销周期", 1, 1, listMerge);

        nowColIndex = createBlock(sw, "结转大定", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "新增大定", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "累计大定", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "新增成销", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "结转成销转化率", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "新增成销转化率", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "整体成销转化率", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "大定转成销周期", 1, 1, listMerge);

        nowColIndex = createBlock(sw, "结转大定", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "新增大定", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "累计大定", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "新增成销", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "结转成销转化率", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "新增成销转化率", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "整体成销转化率", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "大定转成销周期", 1, 1, listMerge);

        nowColIndex = createBlock(sw, "结转大定", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "新增大定", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "累计大定", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "新增成销", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "结转成销转化率", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "新增成销转化率", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "整体成销转化率", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "大定转成销周期", 1, 1, listMerge);

        nowColIndex = createBlock(sw, "结转大定", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "新增大定", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "累计大定", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "新增成销", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "结转成销转化率", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "新增成销转化率", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "整体成销转化率", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "大定转成销周期", 1, 1, listMerge);

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
        String heJi = map.get("estateName") == null ? "" : map.get("estateName").toString();
        short bodyStyle = bodyBlackStyle;
        short moneyStyle = money_unlocked_black;
        short intStyle = int_black;
        if ("合计".equals(heJi)) {
            bodyStyle = bodyBlackBoldStyle;
            moneyStyle = money_unlocked_black_bold;
            intStyle = int_black_bold;
        }

        sw.createCell(i++, map.get("rowNum") == null ? "" : map.get("rowNum").toString(), bodyStyle);
        sw.createCell(i++, map.get("gsRegion") == null ? "" : map.get("gsRegion").toString(), bodyStyle);
        sw.createCell(i++, map.get("gsCity") == null ? "" : map.get("gsCity").toString(), bodyStyle);
//        sw.createCell(i++, map.get("gsCenter") == null ? "" : map.get("gsCenter").toString(), bodyStyle);
        sw.createCell(i++, map.get("projectNo") == null ? "" : map.get("projectNo").toString(), bodyStyle);
        sw.createCell(i++, map.get("estateName") == null ? "" : map.get("estateName").toString(), bodyStyle);

        sw.createCell(i++, clearZero(map.get("dqJzdd")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dqXzdd")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dqLjdd")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dqXzcx")), moneyStyle);
        sw.createCell(i++, map.get("dqJzcxRate") == null ? "" : map.get("dqJzcxRate").toString(), bodyStyle);
        sw.createCell(i++, map.get("dqXzcxRate") == null ? "" : map.get("dqXzcxRate").toString(), bodyStyle);
        sw.createCell(i++, map.get("dqZtcxRate") == null ? "" : map.get("dqZtcxRate").toString(), bodyStyle);
        sw.createCell(i++, clearZero(map.get("dqDdzcxzq")), intStyle);

        sw.createCell(i++, clearZero(map.get("dqJzddQ1")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dqXzddQ1")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dqLjddQ1")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dqXzcxQ1")), moneyStyle);
        sw.createCell(i++, map.get("dqJzcxRateQ1") == null ? "" : map.get("dqJzcxRateQ1").toString(), bodyStyle);
        sw.createCell(i++, map.get("dqXzcxRateQ1") == null ? "" : map.get("dqXzcxRateQ1").toString(), bodyStyle);
        sw.createCell(i++, map.get("dqZtcxRateQ1") == null ? "" : map.get("dqZtcxRateQ1").toString(), bodyStyle);
        sw.createCell(i++, clearZero(map.get("dqDdzcxzqQ1")), intStyle);

        sw.createCell(i++, clearZero(map.get("dqJzddQ2")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dqXzddQ2")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dqLjddQ2")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dqXzcxQ2")), moneyStyle);
        sw.createCell(i++, map.get("dqJzcxRateQ2") == null ? "" : map.get("dqJzcxRateQ2").toString(), bodyStyle);
        sw.createCell(i++, map.get("dqXzcxRateQ2") == null ? "" : map.get("dqXzcxRateQ2").toString(), bodyStyle);
        sw.createCell(i++, map.get("dqZtcxRateQ2") == null ? "" : map.get("dqZtcxRateQ2").toString(), bodyStyle);
        sw.createCell(i++, clearZero(map.get("dqDdzcxzqQ2")), intStyle);

        sw.createCell(i++, clearZero(map.get("dqJzddQ3")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dqXzddQ3")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dqLjddQ3")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dqXzcxQ3")), moneyStyle);
        sw.createCell(i++, map.get("dqJzcxRateQ3") == null ? "" : map.get("dqJzcxRateQ3").toString(), bodyStyle);
        sw.createCell(i++, map.get("dqXzcxRateQ3") == null ? "" : map.get("dqXzcxRateQ3").toString(), bodyStyle);
        sw.createCell(i++, map.get("dqZtcxRateQ3") == null ? "" : map.get("dqZtcxRateQ3").toString(), bodyStyle);
        sw.createCell(i++, clearZero(map.get("dqDdzcxzqQ3")), intStyle);

        sw.createCell(i++, clearZero(map.get("dqJzddQ4")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dqXzddQ4")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dqLjddQ4")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dqXzcxQ4")), moneyStyle);
        sw.createCell(i++, map.get("dqJzcxRateQ4") == null ? "" : map.get("dqJzcxRateQ4").toString(), bodyStyle);
        sw.createCell(i++, map.get("dqXzcxRateQ4") == null ? "" : map.get("dqXzcxRateQ4").toString(), bodyStyle);
        sw.createCell(i++, map.get("dqZtcxRateQ4") == null ? "" : map.get("dqZtcxRateQ4").toString(), bodyStyle);
        sw.createCell(i++, clearZero(map.get("dqDdzcxzqQ4")), intStyle);
        sw.endRow();
        index++;

        return index;
    }

    private Double clearZero(Object value) {
        if (value == null || value.equals("-"))
            return Double.parseDouble("0.00");

        Double data = Double.parseDouble(value.toString());
        return data;
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

        //归属城市
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 1));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex += 2;

        //项目编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(16);
        columnSet.add(c);
        nColIndex++;

        //项目名称
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
        columnSet.add(c);
        nColIndex++;

        //当期数据
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 39));
        c.setWidth(15);
        columnSet.add(c);
        nColIndex += 40;

        return columnSet;
    }
}
