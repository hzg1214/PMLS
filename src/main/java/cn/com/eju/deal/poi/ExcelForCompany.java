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

public class ExcelForCompany extends ExcelUseXmlAbstract {

    private short bodyStyle;

    public void downloadSheet(List<LinkedHashMap<String, Object>> list, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("公司列表");

        Map<String, XSSFCellStyle> style = super.createStyles(wb);
        super.headerStyle = style.get(ExcelStyleEnum.HEADER.getValue()).getIndex();
        this.bodyStyle = style.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();

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
        index = this.createSheetHead(sw, index);
        if (results != null && results.size() > 0) {
            for (LinkedHashMap<String, Object> map : results) {
                index = this.createSheetBody(sw, index, map);
            }
        }
        sw.endSheet();
    }

    /**
     * 创建sheet头部内容
     */
    private int createSheetHead(SpeedSheetWriter sw, int index) throws IOException {
        nowRowIndex = index;
        nowColIndex = 0;
        sw.insertRow(nowRowIndex);
        //第一行
        nowColIndex = createBlock(sw, "公司编号", 1, 1, null);
        nowColIndex = createBlock(sw, "公司名称", 1, 1, null);
        nowColIndex = createBlock(sw, "门店数", 1, 1, null);
        nowColIndex = createBlock(sw, "联系地址", 1, 1, null);
        nowColIndex = createBlock(sw, "创建人", 1, 1, null);
        nowColIndex = createBlock(sw, "创建时间", 1, 1, null);
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
        sw.createCell(i++, (String)map.get("companyNo"), bodyStyle);
        sw.createCell(i++, (String)map.get("companyName"), bodyStyle);
        sw.createCell(i++, (String)map.get("storeCount"), bodyStyle);
        sw.createCell(i++, (String)map.get("cityName") + (String)map.get("districtName") + (String)map.get("address"), bodyStyle);
        sw.createCell(i++, (String)map.get("userCreateName"), bodyStyle);
        sw.createCell(i, DateUtils.formatDate(DateUtils.parseDate((String)map.get("dateCreate"))), bodyStyle);

        sw.endRow();
        index++;

        return index;
    }


    /**
     * 设置列宽
     */
    public List<ExcelColumnSet> setColumnWidth() {
        List<ExcelColumnSet> columnSet = new ArrayList<>();
        ExcelColumnSet c = new ExcelColumnSet("1", "6");
        c.setWidth(18);
        columnSet.add(c);

        return columnSet;
    }
}
