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

public class ExcelForKeFuOrder extends ExcelUseXmlAbstract {

    private short lockBlackStyle;
    private short bodyBlackStyle;
    private short dateBlackStyle;
    public void downloadSheet(List<LinkedHashMap<String, Object>> list, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("客服工单");

        Map<String, XSSFCellStyle> style = super.createStyles(wb);
        super.headerStyle = style.get(ExcelStyleEnum.HEADER.getValue()).getIndex();
        this.lockBlackStyle = style.get(ExcelStyleEnum.LOCKED.getValue()).getIndex();
        this.bodyBlackStyle = style.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();
        this.dateBlackStyle = style.get(ExcelStyleEnum.DATE_BLACK.getValue()).getIndex();
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
        nowColIndex = createBlock(sw, "序号", 1, 1, headerStyle, listMerge);
       
        nowColIndex = createBlock(sw, "工单编号", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "归属城市", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "门店城市", 1, 1, headerStyle, listMerge);

        nowColIndex = createBlock(sw, "公司编号", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "公司名称", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "门店编号", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "门店名称", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "合作模式", 1, 1, headerStyle, listMerge);
        
        nowColIndex = createBlock(sw, "一级分类", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "二级分类", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "问题概要", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "经办人", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "创建人", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "创建时间", 1, 1, headerStyle, listMerge);
        
        nowColIndex = createBlock(sw, "问题状态", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "核验状态", 1, 1, headerStyle, listMerge);
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
        short dateStyle = dateBlackStyle;
        
        sw.createCell(i++, index , lockStyle);
        
        sw.createCell(i++, map.get("orderNo") == null ? "" : map.get("orderNo").toString(), bodyStyle);
        sw.createCell(i++, map.get("cityName") == null ? "" : map.get("cityName").toString(), bodyStyle);
        sw.createCell(i++, map.get("storeCityName") == null ? "" : map.get("storeCityName").toString(), bodyStyle);
        sw.createCell(i++, map.get("companyNo") == null ? "" : map.get("companyNo").toString(), bodyStyle);
        sw.createCell(i++, map.get("companyName") == null ? "" : map.get("companyName").toString(), bodyStyle);
        sw.createCell(i++, map.get("storeNo") == null ? "" : map.get("storeNo").toString(), bodyStyle);
        sw.createCell(i++, map.get("storeName") == null ? "" : map.get("storeName").toString(), bodyStyle);
        sw.createCell(i++, map.get("contractTypeName") == null ? "" : map.get("contractTypeName").toString(), bodyStyle);
        sw.createCell(i++, map.get("categoryOneNm") == null ? "" : map.get("categoryOneNm").toString(), bodyStyle);
        sw.createCell(i++, map.get("categoryTwoNm") == null ? "" : map.get("categoryTwoNm").toString(), bodyStyle);
        sw.createCell(i++, map.get("questionTopic") == null ? "" : map.get("questionTopic").toString(), bodyStyle);
        sw.createCell(i++, map.get("userName") == null ? "" : map.get("userName").toString(), bodyStyle);
        sw.createCell(i++, map.get("createName") == null ? "" : map.get("createName").toString(), bodyStyle);
        setCreateDateCell("dateCreate", i++, dateStyle, map, sw);
        
        sw.createCell(i++, map.get("dealStatusNm") == null ? "" : map.get("dealStatusNm").toString(), bodyStyle);
        sw.createCell(i++, map.get("checkStatusNm") == null ? "" : map.get("checkStatusNm").toString(), bodyStyle);
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
        c = new ExcelColumnSet("1", "1");
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;

        c = new ExcelColumnSet("2","2");
        c.setWidth(18);
        columnSet.add(c);
        nColIndex ++;

        c = new ExcelColumnSet("3", "4");
        c.setWidth(10);
        columnSet.add(c);

        c = new ExcelColumnSet("5", "5");
        c.setWidth(16);
        columnSet.add(c);

        c = new ExcelColumnSet("6", "6");
        c.setWidth(30);
        columnSet.add(c);

        c = new ExcelColumnSet("7", "8");
        c.setWidth(16);
        columnSet.add(c);

        c = new ExcelColumnSet("12", "12");
        c.setWidth(30);
        columnSet.add(c);

        c = new ExcelColumnSet("9", "11");
        c.setWidth(12);
        columnSet.add(c);

        c = new ExcelColumnSet("13", "13");
        c.setWidth(12);
        columnSet.add(c);
        c = new ExcelColumnSet("14", "15");
        c.setWidth(10);
        columnSet.add(c);
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
     * 创建日期型sheet
     */
    private void setCreateDateCell(String dateName ,int i,short dateStyle, LinkedHashMap<String, Object> map,SpeedSheetWriter sw) throws Exception {

        Calendar cal = Calendar.getInstance();

        try{
            if (map.get(dateName) == null || "".equals(map.get(dateName).toString()) || "-".equals(map.get(dateName).toString())) {
                sw.createCell(i, "-", dateStyle);
            } else {
                cal.setTime(DateUtils.parseDate(String.valueOf(map.get(dateName))));
                sw.createCell(i, cal, dateStyle);
            }
        }catch(Exception e){
        }

    }
}
