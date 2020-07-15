package cn.com.eju.pmls.poi.common;


import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.common.util.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import java.util.*;

public class ExcelSXSSFCommon {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    protected void createCellDate(SXSSFWorkbook workBook, Row row
            ,int columnIndex,Object o,CellStyle cellStyle) throws Exception {
        try {
            if (o == null || "".equals(o.toString()) || "-".equals(o.toString())) {
                createCellStr(workBook, row,columnIndex,"-",cellStyle);
            } else {
                Calendar cal = Calendar.getInstance();
                cal.setTime(DateUtils.parseDate(String.valueOf(o)));

                Cell cell = row.createCell(columnIndex);
                cell.setCellValue(cal);

                //setCellStyle(cell, workBook);
                cell.setCellStyle(cellStyle);


            }
        } catch (Exception e) {
            logger.error("报表##导出##createCellDate##日期格式处理异常columnIndex="+columnIndex,e);
            e.printStackTrace();
        }
    }

    protected void createCellDateSpace(SXSSFWorkbook workBook, Row row
            ,int columnIndex,Object o,CellStyle cellStyle) throws Exception {
        try {
            if (o == null || "".equals(o.toString()) || "-".equals(o.toString())) {
                createCellStr(workBook, row,columnIndex,"",cellStyle);
            } else {
                Calendar cal = Calendar.getInstance();
                cal.setTime(DateUtils.parseDate(String.valueOf(o)));

                Cell cell = row.createCell(columnIndex);
                cell.setCellValue(cal);

                //setCellStyle(cell, workBook);
                cell.setCellStyle(cellStyle);


            }
        } catch (Exception e) {
            logger.error("报表##导出##createCellDate##日期格式处理异常columnIndex="+columnIndex,e);
            e.printStackTrace();
        }
    }

    //日期格式数据库已处理  当做字符串处理
    /*protected void createCellDate(SXSSFWorkbook workBook, Row row
            ,int columnIndex,Object o,CellStyle cellStyle) throws Exception {
        try {
            String str = "";
            if (o == null || "".equals(o.toString()) || "-".equals(o.toString())) {
                str = "-";
            }else{
                str = o.toString();
            }
            createCellStr(workBook, row,columnIndex,str,cellStyle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


    protected void createCellMoney (SXSSFWorkbook workBook, Row row
            ,int columnIndex,Double cellValue,CellStyle cellStyle){
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue(cellValue);
        //setCellStyle(cell, workBook);
        cell.setCellStyle(cellStyle);
    }


    protected void createCellMoneySpace (SXSSFWorkbook workBook, Row row
            ,int columnIndex,Object o,CellStyle cellStyle){
        try {
            if (o == null || "".equals(o.toString()) || "-".equals(o.toString())) {
                createCellStr(workBook, row,columnIndex,"",cellStyle);
            } else {
                Double cellValue = Double.parseDouble(o.toString());
                createCellMoney(workBook,row,columnIndex,cellValue,cellStyle);
            }
        } catch (Exception e) {
            logger.error("报表##导出##createCellMoneySpace##金额格式处理异常columnIndex="+columnIndex,e);
            e.printStackTrace();
        }
    }


    protected void createCellStr (SXSSFWorkbook workBook, Row row
            ,int columnIndex,String cellValue,CellStyle cellStyle){
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue(cellValue);
        //setCellStyle(cell, workBook);
        cell.setCellStyle(cellStyle);
    }

    protected void createCellInt (SXSSFWorkbook workBook, Row row
            ,int columnIndex,Integer cellValue,CellStyle cellStyle){
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue(cellValue);
        //setCellStyle(cell, workBook);
        cell.setCellStyle(cellStyle);
    }

    /**
     * 设置单元格样式
     * @param cell
     *            单元格
     */
    public static void setCellStyle(Cell cell, SXSSFWorkbook workBook) {
        // 设置样式
        CellStyle cellStyle = workBook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 设置字体居中
        // 设置字体
        Font font = workBook.createFont();
        font.setFontName("微软雅黑");
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short) 9);
        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);
    }


    public String clearNull(Object value) {
        return value == null || value.equals("") ? "-" : value.toString();
    }

    public String clearNull(Object value,Boolean showFlag) {
        if(!showFlag) return"-";

        return value == null || value.equals("") ? "-" : value.toString();
    }

    public Double clearZero(Object value) {
        if (value == null || value.equals("-"))
            return Double.parseDouble("0.00");

        Double data = Double.parseDouble(value.toString());
        return data;
    }


    public Double clearZero(Object value,Boolean showFlag) {
        if(!showFlag)  return Double.parseDouble("0.00");
        if (value == null || value.equals("-"))
            return Double.parseDouble("0.00");

        Double data = Double.parseDouble(value.toString());
        return data;
    }

    public Integer clearIntZero(Object value) {
        if (value == null || value.equals("-"))
            return Integer.parseInt("0");

        int data = Integer.parseInt(value.toString());
        return data;
    }

    public int clearIntZero(Object value,Boolean showFlag) {
        if(!showFlag)  return Integer.parseInt("0");
        if (value == null || value.equals("-"))
            return Integer.parseInt("0");

        int data = Integer.parseInt(value.toString());
        return data;
    }


    public String replaceChars(String str, String mask) {
        if (str == null)
            return "";

        if (str.length() <= 6 || mask.length() <= 0 || str.length() <= mask.length())
            return str;

        int startPos = 0;
        String result = str;

        try {

            startPos = (str.length() - mask.length()) / 2 + 1;
            result = str.substring(0, startPos - 1) + mask;
            result = result + str.substring(result.length(), str.length());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public String replaceChars(String str, String mask,Boolean ShowFlag) {
        if(!ShowFlag) return "";
        if (str == null)
            return "";

        if (str.length() <= 6 || mask.length() <= 0 || str.length() <= mask.length())
            return str;

        int startPos = 0;
        String result = str;

        try {

            startPos = (str.length() - mask.length()) / 2 + 1;
            result = str.substring(0, startPos - 1) + mask;
            result = result + str.substring(result.length(), str.length());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    protected Map<String, CellStyle> createStyles(SXSSFWorkbook wb) {

        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();

        CellStyle headerStyle = wb.createCellStyle();
        Font headerFont = wb.createFont();
        headerFont.setBold(true);
        headerFont.setFontName("微软雅黑");
        headerFont.setFontHeightInPoints((short) 9);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setBorderBottom(CellStyle.BORDER_THIN);
        headerStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderTop(CellStyle.BORDER_THIN);
        headerStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderLeft(CellStyle.BORDER_THIN);
        headerStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderRight(CellStyle.BORDER_THIN);
        headerStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setWrapText(true);
        headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
        headerStyle.setFont(headerFont);
        styles.put(ExcelStyleEnumSxssf.HEADER.getValue(), headerStyle);

        //灰色
        CellStyle lockedStyle = wb.createCellStyle();
        Font lockedFont = wb.createFont();
        lockedFont.setFontName("微软雅黑");
        lockedFont.setFontHeightInPoints((short) 9);
        lockedStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        lockedStyle.setBorderBottom(CellStyle.BORDER_THIN);
        lockedStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        lockedStyle.setBorderTop(CellStyle.BORDER_THIN);
        lockedStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        lockedStyle.setBorderLeft(CellStyle.BORDER_THIN);
        lockedStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        lockedStyle.setBorderRight(CellStyle.BORDER_THIN);
        lockedStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        lockedStyle.setWrapText(true);
        lockedStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        lockedStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
//        lockedStyle.setAlignment(CellStyle.ALIGN_CENTER);
        lockedStyle.setAlignment(CellStyle.ALIGN_LEFT);
        lockedStyle.setFont(lockedFont);
        styles.put(ExcelStyleEnumSxssf.LOCKED.getValue(), lockedStyle);

        DataFormat fmt = wb.createDataFormat();
        short percentFormat = fmt.getFormat("0.00%");
        short intFormat = fmt.getFormat("#,##0;-#,##0;-");
        short moneyFormat = fmt.getFormat("#,##0.00;-#,##0.00;-");
        short doubleFormat = fmt.getFormat("#0.00;-#0.00;0.00");
        short dateFormat = fmt.getFormat("yyyy-MM-dd");
        short dateMinuteFormat = fmt.getFormat("yyyy-MM-dd HH:mm:ss");

        CellStyle lockedNumStyle = wb.createCellStyle();
        Font lockedNumFont = wb.createFont();
        lockedNumFont.setFontName("微软雅黑");
        lockedNumFont.setFontHeightInPoints((short) 9);
        lockedNumStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
        lockedNumStyle.setBorderBottom(CellStyle.BORDER_THIN);
        lockedNumStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        lockedNumStyle.setBorderTop(CellStyle.BORDER_THIN);
        lockedNumStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        lockedNumStyle.setBorderLeft(CellStyle.BORDER_THIN);
        lockedNumStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        lockedNumStyle.setBorderRight(CellStyle.BORDER_THIN);
        lockedNumStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        lockedNumStyle.setWrapText(true);
        lockedNumStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        lockedNumStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        lockedNumStyle.setAlignment(CellStyle.ALIGN_CENTER);
        lockedNumStyle.setFont(lockedNumFont);
        lockedNumStyle.setDataFormat(doubleFormat);
        styles.put(ExcelStyleEnumSxssf.LOCKED_NUM.getValue(), lockedNumStyle);

        CellStyle lockedDateStyle = wb.createCellStyle();
        Font lockedDateFont = wb.createFont();
        lockedDateFont.setFontName("微软雅黑");
        lockedDateFont.setFontHeightInPoints((short) 9);
        lockedDateStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
        lockedDateStyle.setBorderBottom(CellStyle.BORDER_THIN);
        lockedDateStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        lockedDateStyle.setBorderTop(CellStyle.BORDER_THIN);
        lockedDateStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        lockedDateStyle.setBorderLeft(CellStyle.BORDER_THIN);
        lockedDateStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        lockedDateStyle.setBorderRight(CellStyle.BORDER_THIN);
        lockedDateStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        lockedDateStyle.setWrapText(true);
        lockedDateStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        lockedDateStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        lockedDateStyle.setAlignment(CellStyle.ALIGN_CENTER);
        lockedDateStyle.setFont(lockedNumFont);
        lockedDateStyle.setDataFormat(dateFormat);
        styles.put(ExcelStyleEnumSxssf.DATE_LOCK_BLACK.getValue(), lockedDateStyle);

        //黑色
        Font blackFont = wb.createFont();

        putStyle(wb, styles, null, blackFont, false, false, CellStyle.ALIGN_LEFT, ExcelStyleEnumSxssf.BODY_BLACK_Left.getValue());
        putStyle(wb, styles, null, blackFont, false, false, CellStyle.ALIGN_CENTER, ExcelStyleEnumSxssf.BODY_BLACK.getValue());
        putStyle(wb, styles, intFormat, blackFont, false, false, CellStyle.ALIGN_CENTER, ExcelStyleEnumSxssf.INT_BLACK.getValue());
        putStyle(wb, styles, percentFormat, blackFont, false, false, CellStyle.ALIGN_CENTER, ExcelStyleEnumSxssf.PERCENT_BLACK.getValue());
        putStyle(wb, styles, moneyFormat, blackFont, false, true, CellStyle.ALIGN_CENTER, ExcelStyleEnumSxssf.MONEY_LOCKED_BLACK.getValue());
        putStyle(wb, styles, moneyFormat, blackFont, false, true, CellStyle.ALIGN_RIGHT, ExcelStyleEnumSxssf.MONEY_LOCKED_BLACK_RIGHT.getValue());
        putStyle(wb, styles, moneyFormat, blackFont, false, false, CellStyle.ALIGN_CENTER, ExcelStyleEnumSxssf.MONEY_UNLOCKED_BLACK.getValue());
        putStyle(wb, styles, dateFormat, blackFont, false, false, CellStyle.ALIGN_CENTER, ExcelStyleEnumSxssf.DATE_BLACK.getValue());
//        putStyle(wb, styles, dateFormat, blackFont, false, true, CellStyle.ALIGN_CENTER, ExcelStyleEnumSxssf.DATE_LOCK_BLACK.getValue());
        putStyle(wb, styles, dateMinuteFormat, blackFont, false, false, CellStyle.ALIGN_CENTER, ExcelStyleEnumSxssf.DATE_MINUTE_BLACK.getValue());

        Font blackFontBold = wb.createFont();
        putStyle(wb, styles, null, blackFontBold, true, false, CellStyle.ALIGN_CENTER, ExcelStyleEnumSxssf.BODY_BLACK_BOLD.getValue());
        putStyle(wb, styles, null, blackFontBold, true, false, CellStyle.ALIGN_LEFT, ExcelStyleEnumSxssf.BODY_BLACK_BOLD_LEFT.getValue());
        putStyle(wb, styles, intFormat, blackFontBold, true, false, CellStyle.ALIGN_CENTER, ExcelStyleEnumSxssf.INT_BLACK_BOLD.getValue());
        putStyle(wb, styles, moneyFormat, blackFontBold, true, false, CellStyle.ALIGN_CENTER, ExcelStyleEnumSxssf.MONEY_UNLOCKED_BLACK_BOLD.getValue());
        putStyle(wb, styles, dateFormat, blackFontBold, true, false, CellStyle.ALIGN_CENTER, ExcelStyleEnumSxssf.DATE_BLACK_BOLD.getValue());

        putStyle(wb, styles, doubleFormat, blackFont, false, false, CellStyle.ALIGN_CENTER, ExcelStyleEnumSxssf.DOUBLE_BLACK.getValue());

        //红色
        Font redFont = wb.createFont();
        redFont.setColor(Font.COLOR_RED);

        putStyle(wb, styles, null, redFont, false, false, CellStyle.ALIGN_CENTER, ExcelStyleEnumSxssf.BODY_RED.getValue());
        putStyle(wb, styles, intFormat, redFont, false, false, CellStyle.ALIGN_CENTER, ExcelStyleEnumSxssf.INT_RED.getValue());
        putStyle(wb, styles, percentFormat, redFont, false, false, CellStyle.ALIGN_CENTER, ExcelStyleEnumSxssf.PERCENT_RED.getValue());
        putStyle(wb, styles, moneyFormat, redFont, false, true, CellStyle.ALIGN_CENTER, ExcelStyleEnumSxssf.MONEY_LOCKED_RED.getValue());
        putStyle(wb, styles, moneyFormat, redFont, false, false, CellStyle.ALIGN_CENTER, ExcelStyleEnumSxssf.MONEY_UNLOCKED_RED.getValue());
        putStyle(wb, styles, dateFormat, redFont, false, false, CellStyle.ALIGN_CENTER, ExcelStyleEnumSxssf.DATE_RED.getValue());
        putStyle(wb, styles, dateMinuteFormat, redFont, false, false, CellStyle.ALIGN_CENTER, ExcelStyleEnumSxssf.DATE_MINUTE_RED.getValue());
        putStyle(wb, styles, doubleFormat, redFont, false, false, CellStyle.ALIGN_CENTER, ExcelStyleEnumSxssf.DOUBLE_RED.getValue());

        //LOCKED_RED
        CellStyle lockedRedStyle = wb.createCellStyle();
        redFont.setFontName("微软雅黑");
        redFont.setFontHeightInPoints((short) 9);
        lockedRedStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        lockedRedStyle.setBorderBottom(CellStyle.BORDER_THIN);
        lockedRedStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        lockedRedStyle.setBorderTop(CellStyle.BORDER_THIN);
        lockedRedStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        lockedRedStyle.setBorderLeft(CellStyle.BORDER_THIN);
        lockedRedStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        lockedRedStyle.setBorderRight(CellStyle.BORDER_THIN);
        lockedRedStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        lockedRedStyle.setWrapText(true);
        lockedRedStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        lockedRedStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        lockedRedStyle.setAlignment(CellStyle.ALIGN_LEFT);
        lockedRedStyle.setFont(redFont);
        styles.put(ExcelStyleEnumSxssf.LOCKED_RED.getValue(), lockedRedStyle);

        //LOCKED_NUM_RED
        CellStyle lockedNumRedStyle = wb.createCellStyle();
        lockedNumRedStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        lockedNumRedStyle.setBorderBottom(CellStyle.BORDER_THIN);
        lockedNumRedStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        lockedNumRedStyle.setBorderTop(CellStyle.BORDER_THIN);
        lockedNumRedStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        lockedNumRedStyle.setBorderLeft(CellStyle.BORDER_THIN);
        lockedNumRedStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        lockedNumRedStyle.setBorderRight(CellStyle.BORDER_THIN);
        lockedNumRedStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        lockedNumRedStyle.setWrapText(true);
        lockedNumRedStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        lockedNumRedStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        lockedNumRedStyle.setAlignment(CellStyle.ALIGN_CENTER);
        lockedNumRedStyle.setFont(redFont);
        lockedNumRedStyle.setDataFormat(doubleFormat);
        styles.put(ExcelStyleEnumSxssf.LOCKED_NUM_RED.getValue(), lockedNumRedStyle);

        //DATE_LOCK_RED
        CellStyle lockedDateRedStyle = wb.createCellStyle();
        lockedDateRedStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        lockedDateRedStyle.setBorderBottom(CellStyle.BORDER_THIN);
        lockedDateRedStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        lockedDateRedStyle.setBorderTop(CellStyle.BORDER_THIN);
        lockedDateRedStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        lockedDateRedStyle.setBorderLeft(CellStyle.BORDER_THIN);
        lockedDateRedStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        lockedDateRedStyle.setBorderRight(CellStyle.BORDER_THIN);
        lockedDateRedStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        lockedDateRedStyle.setWrapText(true);
        lockedDateRedStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        lockedDateRedStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        lockedDateRedStyle.setAlignment(CellStyle.ALIGN_CENTER);
        lockedDateRedStyle.setFont(redFont);
        lockedDateRedStyle.setDataFormat(dateFormat);
        styles.put(ExcelStyleEnumSxssf.DATE_LOCK_RED.getValue(), lockedDateRedStyle);


        Font normalFont = wb.createFont();
        normalFont.setColor(XSSFFont.COLOR_RED);

        putStyle(wb, styles, null, normalFont, false, false, CellStyle.ALIGN_CENTER, ExcelStyleEnumSxssf.BODY_GREEN.getValue());
        putStyle(wb, styles, intFormat, normalFont, false, false, CellStyle.ALIGN_CENTER, ExcelStyleEnumSxssf.INT_GREEN.getValue());
        putStyle(wb, styles, percentFormat, normalFont, false, false, CellStyle.ALIGN_CENTER, ExcelStyleEnumSxssf.PERCENT_GREEN.getValue());
        putStyle(wb, styles, moneyFormat, normalFont, false, true, CellStyle.ALIGN_CENTER, ExcelStyleEnumSxssf.MONEY_LOCKED_GREEN.getValue());
        putStyle(wb, styles, moneyFormat, normalFont, false, false, CellStyle.ALIGN_CENTER, ExcelStyleEnumSxssf.MONEY_UNLOCKED_GREEN.getValue());
        putStyle(wb, styles, dateFormat, normalFont, false, false, CellStyle.ALIGN_CENTER, ExcelStyleEnumSxssf.DATE_GREEN.getValue());
        putStyle(wb, styles, dateMinuteFormat, normalFont, false, false, CellStyle.ALIGN_CENTER, ExcelStyleEnumSxssf.DATE_MINUTE_GREEN.getValue());

        return styles;
    }

    /**
     * 存储单元格样式
     *
     * @param wb
     * @param styles
     * @param fmt    单元格格式
     * @param font   单元格字体
     * @param locked 单元格是否锁定，锁定就不能编辑
     * @param key
     */
    private void putStyle(SXSSFWorkbook wb, Map<String, CellStyle> styles, Short fmt, Font font, boolean isBold, boolean locked, short align, String key) {

        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 9);
        font.setBold(isBold);

        CellStyle style = wb.createCellStyle();
        style.setAlignment(align);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        if (fmt != null) {
            style.setDataFormat(fmt.shortValue());
        }
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setFont(font);
        style.setLocked(locked);
        styles.put(key, style);

    }

    public void downloadSheet(List<LinkedHashMap<String, Object>> list, File file, HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public void downloadSheet(Map<String, Object> reqMap, File outPutFile, String yearly) throws IOException {

    }

    public void downloadSheet(Map<String, Object> reqMap, File outPutFile) throws IOException {

    }

    public void downloadSheet(Map<String, String> head, List<LinkedHashMap<String, Object>> list, File file) throws IOException {

    }
}
