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

public class ExcelForPerformanceSum extends ExcelUseXmlAbstract {

    private short bodyStyle;
    private short intStyle;
    private short moneyStyle;
    private int length;

    public void downloadSheet(LinkedHashMap<String, Object> map, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("业绩汇总");

        Map<String, XSSFCellStyle> style = super.createStyles(wb);
        super.headerStyle = style.get(ExcelStyleEnum.HEADER.getValue()).getIndex();
        this.bodyStyle = style.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();
        XSSFCellStyle intCellStyle = style.get(ExcelStyleEnum.INT_BLACK.getValue());
        intCellStyle.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
        this.intStyle = intCellStyle.getIndex();
        XSSFCellStyle moneyCellStyle = style.get(ExcelStyleEnum.MONEY_UNLOCKED_BLACK.getValue());
        moneyCellStyle.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
        this.moneyStyle = moneyCellStyle.getIndex();

        File tempExcel = File.createTempFile("tempExcel", ".xlsx");
        File temp = File.createTempFile("sheet", ".xml");
        try {
            FileOutputStream fos = new FileOutputStream(tempExcel);
            wb.write(fos);
            fos.close();
            String sheetRef = sheet.getPackagePart().getPartName().getName();
            Writer fw = new PrintWriter(temp, "UTF-8");
            this.createSheet(fw, map);
            fw.close();
            super.substitute(tempExcel, temp, sheetRef.substring(1), os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            os.close();
        }
    }

    private void createSheet(Writer out, LinkedHashMap<String, Object> map) throws Exception {
        List<LinkedHashMap<String, Object>> columnDtos = (List<LinkedHashMap<String, Object>>) map.get("columnDtos");
        length = columnDtos.size() - 4;

        SpeedSheetWriter sw = new SpeedSheetWriter(out);
        sw.beginSheet(setColumnWidth(columnDtos), 0, 1, "A2");
        int index = 0;
        List<String> listMerge = new ArrayList<>();
        index = this.createSheetHead(sw, index, listMerge, columnDtos);

        List<LinkedHashMap<String, Object>> results = (List<LinkedHashMap<String, Object>>) map.get("contentDtos");
        if (results != null && results.size() > 0) {
            for (LinkedHashMap<String, Object> contentMap : results) {
                Integer groupSize = (Integer) contentMap.get("groupSize");
                //每一个交易中心 groupSize行数据
                if ((index - 1) % groupSize == 0) {
                    listMerge.add("A" + (index + 1) + ":A" + (index + 39));

                    listMerge.add("B" + (index + 1) + ":B" + (index + 18));
                    listMerge.add("B" + (index + 19) + ":B" + (index + 21));
                    listMerge.add("B" + (index + 22) + ":B" + (index + 29));
                    listMerge.add("B" + (index + 30) + ":B" + (index + 39));

                    listMerge.add("C" + (index + 2) + ":C" + (index + 4));
                    listMerge.add("C" + (index + 5) + ":C" + (index + 8));
                    listMerge.add("C" + (index + 9) + ":C" + (index + 10));
                    listMerge.add("C" + (index + 11) + ":C" + (index + 14));
                    listMerge.add("C" + (index + 15) + ":C" + (index + 16));
                    listMerge.add("C" + (index + 17) + ":C" + (index + 18));

                    listMerge.add("C" + (index + 1) + ":D" + (index + 1));
                    for (int i = 19; i < 32; i++) {
                        listMerge.add("C" + (index + i) + ":D" + (index + i));
                    }

                    listMerge.add("C" + (index + 32) + ":C" + (index + 33));
                    listMerge.add("C" + (index + 34) + ":C" + (index + 35));
                    listMerge.add("C" + (index + 36) + ":C" + (index + 37));
                    listMerge.add("C" + (index + 38) + ":C" + (index + 39));
                }

                index = this.createSheetBody(sw, index, contentMap);
            }
        }

        sw.endSheetWithMerge(listMerge);
    }

    /**
     * 创建sheet头部内容
     */
    private int createSheetHead(SpeedSheetWriter sw, int index, List<String> listMerge, List<LinkedHashMap<String, Object>> columnDtos) throws IOException {
        nowRowIndex = index;
        nowColIndex = 0;
        sw.insertRow(nowRowIndex);
        //第一行
        for (LinkedHashMap map : columnDtos) {
            if (map.get("colTitle") != null) {
                nowColIndex = createBlock(sw, map.get("colTitle").toString(), Integer.parseInt(map.get("mergeColNum").toString()), 1, listMerge);
            }
        }

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
        sw.createCell(i++, map.get("centerName").toString(), bodyStyle);
        sw.createCell(i++, map.get("bizType").toString(), bodyStyle);
        sw.createCell(i++, map.get("bizStage").toString(), bodyStyle);
        sw.createCell(i++, map.get("subjName").toString(), bodyStyle);

        String rowType = map.get("rowType").toString();
        short bodyStyle = rowType.equals("money") ? moneyStyle : intStyle;

        if (this.length >= 1) {
            sw.createCell(i++, Double.parseDouble(map.get("col1").toString()), bodyStyle);
        }
        if (this.length >= 2) {
            sw.createCell(i++, Double.parseDouble(map.get("col2").toString()), bodyStyle);
        }
        if (this.length >= 3) {
            sw.createCell(i++, Double.parseDouble(map.get("col3").toString()), bodyStyle);
        }
        if (this.length >= 4) {
            sw.createCell(i++, Double.parseDouble(map.get("col4").toString()), bodyStyle);
        }
        if (this.length >= 5) {
            sw.createCell(i++, Double.parseDouble(map.get("col5").toString()), bodyStyle);
        }
        if (this.length >= 6) {
            sw.createCell(i++, Double.parseDouble(map.get("col6").toString()), bodyStyle);
        }
        if (this.length >= 7) {
            sw.createCell(i++, Double.parseDouble(map.get("col7").toString()), bodyStyle);
        }
        if (this.length >= 8) {
            sw.createCell(i++, Double.parseDouble(map.get("col8").toString()), bodyStyle);
        }
        if (this.length >= 9) {
            sw.createCell(i++, Double.parseDouble(map.get("col9").toString()), bodyStyle);
        }
        if (this.length >= 10) {
            sw.createCell(i++, Double.parseDouble(map.get("col10").toString()), bodyStyle);
        }
        if (this.length >= 11) {
            sw.createCell(i++, Double.parseDouble(map.get("col11").toString()), bodyStyle);
        }
        if (this.length >= 12) {
            sw.createCell(i++, Double.parseDouble(map.get("col12").toString()), bodyStyle);
        }
        if (this.length >= 13) {
            sw.createCell(i++, Double.parseDouble(map.get("col13").toString()), bodyStyle);
        }
        if (this.length >= 14) {
            sw.createCell(i++, Double.parseDouble(map.get("col14").toString()), bodyStyle);
        }
        if (this.length >= 15) {
            sw.createCell(i++, Double.parseDouble(map.get("col15").toString()), bodyStyle);
        }
        if (this.length >= 16) {
            sw.createCell(i++, Double.parseDouble(map.get("col16").toString()), bodyStyle);
        }
        if (this.length >= 17) {
            sw.createCell(i++, Double.parseDouble(map.get("col17").toString()), bodyStyle);
        }
        if (this.length >= 18) {
            sw.createCell(i++, Double.parseDouble(map.get("col18").toString()), bodyStyle);
        }

        sw.endRow();
        index++;

        return index;
    }

    /**
     * 设置列宽
     */
    public List<ExcelColumnSet> setColumnWidth(List<LinkedHashMap<String, Object>> list) {
        List<ExcelColumnSet> columnSet = new ArrayList<>();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            LinkedHashMap<String, Object> map = list.get(i);
            ExcelColumnSet c = new ExcelColumnSet(String.valueOf(i + 1), String.valueOf(i + 1));
            c.setWidth(Integer.parseInt(map.get("colWidth").toString()));
            columnSet.add(c);
        }

        return columnSet;
    }

}
