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

public class ExcelForStoreStructure extends ExcelUseXmlAbstract {

    private short bodyStyle;
    private short intStyle;
    private short moneyStyle;
    private short percentStyle;
    private short bodyBoldLeftStyle;

    public void downloadSheet(List<LinkedHashMap<String, Object>> list, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("门店结构");

        Map<String, XSSFCellStyle> style = super.createStyles(wb);
        super.headerStyle = style.get(ExcelStyleEnum.HEADER.getValue()).getIndex();
        this.bodyStyle = style.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();
        this.intStyle = style.get(ExcelStyleEnum.INT_BLACK.getValue()).getIndex();
        this.moneyStyle = style.get(ExcelStyleEnum.MONEY_UNLOCKED_BLACK.getValue()).getIndex();
        this.percentStyle = style.get(ExcelStyleEnum.PERCENT_BLACK.getValue()).getIndex();
        this.bodyBoldLeftStyle = style.get(ExcelStyleEnum.BODY_BLACK_BOLD_LEFT.getValue()).getIndex();

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
        sw.beginSheet(setColumnWidth(), 0, 1, "A2");
        int index = 0;
        List<String> listMerge = new ArrayList<>();

        index = this.createSheetHead(sw, index, listMerge);
        if (results != null && results.size() > 0) {
            for (LinkedHashMap<String, Object> map : results) {
                Integer groupSize = (Integer) map.get("groupSize");
                //groupSize行数据
                if ((index - 1) % groupSize == 0) {
                    listMerge.add("A" + (index + 1) + ":A" + (index + 5));
                    listMerge.add("A" + (index + 6) + ":A" + (index + 9));
                    listMerge.add("A" + (index + 10) + ":A" + (index + 23));
                    listMerge.add("A" + (index + 24) + ":A" + (index + 27));
                    listMerge.add("A" + (index + 28) + ":A" + (index + 29));

                    listMerge.add("B" + (index + 1) + ":B" + (index + 3));
                    listMerge.add("B" + (index + 4) + ":B" + (index + 5));
                    listMerge.add("B" + (index + 6) + ":B" + (index + 9));
                    listMerge.add("B" + (index + 11) + ":B" + (index + 23));
                    listMerge.add("B" + (index + 24) + ":B" + (index + 25));
                    listMerge.add("B" + (index + 26) + ":B" + (index + 27));
                    for (int i = 28; i <= 29; i++) {
                        listMerge.add("B" + (index + i) + ":C" + (index + i));
                    }
                }

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
        nowColIndex = createBlock(sw, "科目", 3, 1, listMerge);
        nowColIndex = createBlock(sw, "当期", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "总累计", 1, 1, listMerge);
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
        sw.createCell(i++, map.get("bizType").toString(), this.bodyStyle);
        sw.createCell(i++, map.get("bizStage").toString(), this.bodyStyle);
        short bodyStyle = map.get("align").toString().equals("left") ? bodyBoldLeftStyle : this.bodyStyle;
        sw.createCell(i++, map.get("subjName").toString(), bodyStyle);

        String rowType = map.get("rowType").toString();
        bodyStyle = rowType.equals("money") ? this.moneyStyle : this.intStyle;
        if (rowType.equals("percent")) {
            sw.createCell(i++, Double.parseDouble(map.get("col1").toString()) / 100, this.percentStyle);
            sw.createCell(i++, Double.parseDouble(map.get("col2").toString()) / 100, this.percentStyle);
        } else {
            sw.createCell(i++, Double.parseDouble(map.get("col1").toString()), bodyStyle);
            sw.createCell(i++, Double.parseDouble(map.get("col2").toString()), bodyStyle);
        }

        sw.endRow();
        index++;

        return index;
    }

    /**
     * 设置列宽
     */
    public List<ExcelColumnSet> setColumnWidth() {
        List<ExcelColumnSet> columnSet = new ArrayList<>();
        ExcelColumnSet c = new ExcelColumnSet("1", "5");
        c.setWidth(15);
        columnSet.add(c);

        return columnSet;
    }

}
