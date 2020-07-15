package cn.com.eju.pmls.poi.common;

import cn.com.eju.deal.common.util.ExcelUtil;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 13-12-3
 * Time: 上午10:18
 * To change this template use File | Settings | File Templates.
 */
public class ExcelUseXmlAbstract {

    protected int nowRowIndex = 0;
    protected int nowColIndex = 0;
    protected short headerStyle = 0;

    protected final void substitute(File zipfile, File tmpfile, String entry,
                                    OutputStream out) throws IOException {
        ZipFile zip = new ZipFile(zipfile);
        ZipOutputStream zos = new ZipOutputStream(out);
        try {
            @SuppressWarnings("unchecked")
            Enumeration<ZipEntry> en = (Enumeration<ZipEntry>) zip.entries();
            while (en.hasMoreElements()) {
                ZipEntry ze = en.nextElement();
                if (!ze.getName().equals(entry)) {
                    zos.putNextEntry(new ZipEntry(ze.getName()));
                    InputStream is = zip.getInputStream(ze);
                    copyStream(is, zos);
                    is.close();
                }
            }
            zos.putNextEntry(new ZipEntry(entry));
            InputStream is = new FileInputStream(tmpfile);
            try {
                copyStream(is, zos);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zip.close();
            zos.close();

        }
    }

    protected final void copyStream(InputStream in, OutputStream out)
            throws IOException {
        byte[] chunk = new byte[1024];
        int count;
        while ((count = in.read(chunk)) >= 0) {
            out.write(chunk, 0, count);
        }
    }

    /**
     * 创建一个标题单元格，并且进行合并
     *
     * @param name       标题显示字，如果没有或者为空字符串，也不会添加
     * @param spanColumn 合并列的数量，如果小于等于零就不合并
     * @param spanRow    合并行的数量，如果小于等于零就不合并
     * @throws IOException
     */
    protected int createBlock(SpeedSheetWriter sw, String name, int spanColumn, int spanRow, List<String> listMerge) throws IOException {
        String beginColumnChar = ExcelUtil.getColumnChar(nowColIndex + 1);
        int endColIndex = nowColIndex + spanColumn;
        if (name != null) {
            while (nowColIndex < endColIndex) {
                sw.createCell(nowColIndex++, name, headerStyle);
            }
        }
        String endColumnChar = ExcelUtil.getColumnChar(endColIndex);
        if (spanRow > 0 && spanColumn > 0 && listMerge != null) {
            listMerge.add(beginColumnChar + (nowRowIndex + 1) + ":" + endColumnChar + (nowRowIndex + spanRow));
        }
        //nowColIndex = endColIndex;
        return endColIndex;
    }

    protected int createBlock(SpeedSheetWriter sw, String name, int spanColumn, int spanRow, short style, List<String> listMerge) throws IOException {
        String beginColumnChar = ExcelUtil.getColumnChar(nowColIndex + 1);
        int endColIndex = nowColIndex + spanColumn;
        if (name != null) {
            while (nowColIndex < endColIndex) {
                sw.createCell(nowColIndex++, name, style);
            }
        }
        String endColumnChar = ExcelUtil.getColumnChar(endColIndex);
        if (spanRow > 0 && spanColumn > 0 && listMerge != null) {
            listMerge.add(beginColumnChar + (nowRowIndex + 1) + ":" + endColumnChar + (nowRowIndex + spanRow));
        }
        //nowColIndex = endColIndex;
        return endColIndex;
    }

    protected Map<String, XSSFCellStyle> createStyles(XSSFWorkbook wb) {

        Map<String, XSSFCellStyle> styles = new HashMap<String, XSSFCellStyle>();

        XSSFCellStyle headerStyle = wb.createCellStyle();
        XSSFFont headerFont = wb.createFont();
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
        headerStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        headerStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        headerStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        headerStyle.setFont(headerFont);
        styles.put(ExcelStyleEnum.HEADER.getValue(), headerStyle);

        //灰色
        XSSFCellStyle lockedStyle = wb.createCellStyle();
        XSSFFont lockedFont = wb.createFont();
        lockedFont.setFontName("微软雅黑");
        lockedFont.setFontHeightInPoints((short) 9);
        lockedStyle.setFillForegroundColor(new XSSFColor(new byte[]{(byte) 0xf0, (byte) 0xf0, (byte) 0xf0}));
        lockedStyle.setBorderBottom(CellStyle.BORDER_THIN);
        lockedStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        lockedStyle.setBorderTop(CellStyle.BORDER_THIN);
        lockedStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        lockedStyle.setBorderLeft(CellStyle.BORDER_THIN);
        lockedStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        lockedStyle.setBorderRight(CellStyle.BORDER_THIN);
        lockedStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        lockedStyle.setWrapText(true);
        lockedStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        lockedStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
//        lockedStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        lockedStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT);
        lockedStyle.setFont(lockedFont);
        styles.put(ExcelStyleEnum.LOCKED.getValue(), lockedStyle);

        XSSFDataFormat fmt = wb.createDataFormat();
        short percentFormat = fmt.getFormat("0.00%");
        short intFormat = fmt.getFormat("#,##0;-#,##0;-");
        short moneyFormat = fmt.getFormat("#,##0.00;-#,##0.00;-");
        short doubleFormat = fmt.getFormat("#0.00;-#0.00;0.00");
        short dateFormat = fmt.getFormat("yyyy-MM-dd");
        short dateMinuteFormat = fmt.getFormat("yyyy-MM-dd HH:mm:ss");

        XSSFCellStyle lockedNumStyle = wb.createCellStyle();
        XSSFFont lockedNumFont = wb.createFont();
        lockedNumFont.setFontName("微软雅黑");
        lockedNumFont.setFontHeightInPoints((short) 9);
        lockedNumStyle.setFillForegroundColor(new XSSFColor(new byte[]{(byte) 0xf0, (byte) 0xf0, (byte) 0xf0}));
        lockedNumStyle.setBorderBottom(CellStyle.BORDER_THIN);
        lockedNumStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        lockedNumStyle.setBorderTop(CellStyle.BORDER_THIN);
        lockedNumStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        lockedNumStyle.setBorderLeft(CellStyle.BORDER_THIN);
        lockedNumStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        lockedNumStyle.setBorderRight(CellStyle.BORDER_THIN);
        lockedNumStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        lockedNumStyle.setWrapText(true);
        lockedNumStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        lockedNumStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        lockedNumStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        lockedNumStyle.setFont(lockedNumFont);
        lockedNumStyle.setDataFormat(doubleFormat);
        styles.put(ExcelStyleEnum.LOCKED_NUM.getValue(), lockedNumStyle);

        XSSFCellStyle lockedDateStyle = wb.createCellStyle();
        XSSFFont lockedDateFont = wb.createFont();
        lockedDateFont.setFontName("微软雅黑");
        lockedDateFont.setFontHeightInPoints((short) 9);
        lockedDateStyle.setFillForegroundColor(new XSSFColor(new byte[]{(byte) 0xf0, (byte) 0xf0, (byte) 0xf0}));
        lockedDateStyle.setBorderBottom(CellStyle.BORDER_THIN);
        lockedDateStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        lockedDateStyle.setBorderTop(CellStyle.BORDER_THIN);
        lockedDateStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        lockedDateStyle.setBorderLeft(CellStyle.BORDER_THIN);
        lockedDateStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        lockedDateStyle.setBorderRight(CellStyle.BORDER_THIN);
        lockedDateStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        lockedDateStyle.setWrapText(true);
        lockedDateStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        lockedDateStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        lockedDateStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        lockedDateStyle.setFont(lockedNumFont);
        lockedDateStyle.setDataFormat(dateFormat);
        styles.put(ExcelStyleEnum.DATE_LOCK_BLACK.getValue(), lockedDateStyle);

        //黑色
        XSSFFont blackFont = wb.createFont();

        putStyle(wb, styles, null, blackFont, false, false, XSSFCellStyle.ALIGN_LEFT, ExcelStyleEnum.BODY_BLACK_Left.getValue());
        putStyle(wb, styles, null, blackFont, false, false, XSSFCellStyle.ALIGN_CENTER, ExcelStyleEnum.BODY_BLACK.getValue());
        putStyle(wb, styles, intFormat, blackFont, false, false, XSSFCellStyle.ALIGN_CENTER, ExcelStyleEnum.INT_BLACK.getValue());
        putStyle(wb, styles, percentFormat, blackFont, false, false, XSSFCellStyle.ALIGN_CENTER, ExcelStyleEnum.PERCENT_BLACK.getValue());
        putStyle(wb, styles, moneyFormat, blackFont, false, true, XSSFCellStyle.ALIGN_CENTER, ExcelStyleEnum.MONEY_LOCKED_BLACK.getValue());
        putStyle(wb, styles, moneyFormat, blackFont, false, true, XSSFCellStyle.ALIGN_RIGHT, ExcelStyleEnum.MONEY_LOCKED_BLACK_RIGHT.getValue());
        putStyle(wb, styles, moneyFormat, blackFont, false, false, XSSFCellStyle.ALIGN_CENTER, ExcelStyleEnum.MONEY_UNLOCKED_BLACK.getValue());
        putStyle(wb, styles, dateFormat, blackFont, false, false, XSSFCellStyle.ALIGN_CENTER, ExcelStyleEnum.DATE_BLACK.getValue());
//        putStyle(wb, styles, dateFormat, blackFont, false, true, XSSFCellStyle.ALIGN_CENTER, ExcelStyleEnum.DATE_LOCK_BLACK.getValue());
        putStyle(wb, styles, dateMinuteFormat, blackFont, false, false, XSSFCellStyle.ALIGN_CENTER, ExcelStyleEnum.DATE_MINUTE_BLACK.getValue());

        XSSFFont blackFontBold = wb.createFont();
        putStyle(wb, styles, null, blackFontBold, true, false, XSSFCellStyle.ALIGN_CENTER, ExcelStyleEnum.BODY_BLACK_BOLD.getValue());
        putStyle(wb, styles, null, blackFontBold, true, false, XSSFCellStyle.ALIGN_LEFT, ExcelStyleEnum.BODY_BLACK_BOLD_LEFT.getValue());
        putStyle(wb, styles, intFormat, blackFontBold, true, false, XSSFCellStyle.ALIGN_CENTER, ExcelStyleEnum.INT_BLACK_BOLD.getValue());
        putStyle(wb, styles, moneyFormat, blackFontBold, true, false, XSSFCellStyle.ALIGN_CENTER, ExcelStyleEnum.MONEY_UNLOCKED_BLACK_BOLD.getValue());
        putStyle(wb, styles, dateFormat, blackFontBold, true, false, XSSFCellStyle.ALIGN_CENTER, ExcelStyleEnum.DATE_BLACK_BOLD.getValue());

        putStyle(wb, styles, doubleFormat, blackFont, false, false, XSSFCellStyle.ALIGN_CENTER, ExcelStyleEnum.DOUBLE_BLACK.getValue());

        //红色
        XSSFFont redFont = wb.createFont();
        redFont.setColor(XSSFFont.COLOR_RED);

        putStyle(wb, styles, null, redFont, false, false, XSSFCellStyle.ALIGN_CENTER, ExcelStyleEnum.BODY_RED.getValue());
        putStyle(wb, styles, intFormat, redFont, false, false, XSSFCellStyle.ALIGN_CENTER, ExcelStyleEnum.INT_RED.getValue());
        putStyle(wb, styles, percentFormat, redFont, false, false, XSSFCellStyle.ALIGN_CENTER, ExcelStyleEnum.PERCENT_RED.getValue());
        putStyle(wb, styles, moneyFormat, redFont, false, true, XSSFCellStyle.ALIGN_CENTER, ExcelStyleEnum.MONEY_LOCKED_RED.getValue());
        putStyle(wb, styles, moneyFormat, redFont, false, false, XSSFCellStyle.ALIGN_CENTER, ExcelStyleEnum.MONEY_UNLOCKED_RED.getValue());
        putStyle(wb, styles, dateFormat, redFont, false, false, XSSFCellStyle.ALIGN_CENTER, ExcelStyleEnum.DATE_RED.getValue());
        putStyle(wb, styles, dateMinuteFormat, redFont, false, false, XSSFCellStyle.ALIGN_CENTER, ExcelStyleEnum.DATE_MINUTE_RED.getValue());
        putStyle(wb, styles, doubleFormat, redFont, false, false, XSSFCellStyle.ALIGN_CENTER, ExcelStyleEnum.DOUBLE_RED.getValue());

        //LOCKED_RED
        XSSFCellStyle lockedRedStyle = wb.createCellStyle();
        redFont.setFontName("微软雅黑");
        redFont.setFontHeightInPoints((short) 9);
        lockedRedStyle.setFillForegroundColor(new XSSFColor(new byte[]{(byte) 0xf0, (byte) 0xf0, (byte) 0xf0}));
        lockedRedStyle.setBorderBottom(CellStyle.BORDER_THIN);
        lockedRedStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        lockedRedStyle.setBorderTop(CellStyle.BORDER_THIN);
        lockedRedStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        lockedRedStyle.setBorderLeft(CellStyle.BORDER_THIN);
        lockedRedStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        lockedRedStyle.setBorderRight(CellStyle.BORDER_THIN);
        lockedRedStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        lockedRedStyle.setWrapText(true);
        lockedRedStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        lockedRedStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        lockedRedStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT);
        lockedRedStyle.setFont(redFont);
        styles.put(ExcelStyleEnum.LOCKED_RED.getValue(), lockedRedStyle);

        //LOCKED_NUM_RED
        XSSFCellStyle lockedNumRedStyle = wb.createCellStyle();
        lockedNumRedStyle.setFillForegroundColor(new XSSFColor(new byte[]{(byte) 0xf0, (byte) 0xf0, (byte) 0xf0}));
        lockedNumRedStyle.setBorderBottom(CellStyle.BORDER_THIN);
        lockedNumRedStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        lockedNumRedStyle.setBorderTop(CellStyle.BORDER_THIN);
        lockedNumRedStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        lockedNumRedStyle.setBorderLeft(CellStyle.BORDER_THIN);
        lockedNumRedStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        lockedNumRedStyle.setBorderRight(CellStyle.BORDER_THIN);
        lockedNumRedStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        lockedNumRedStyle.setWrapText(true);
        lockedNumRedStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        lockedNumRedStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        lockedNumRedStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        lockedNumRedStyle.setFont(redFont);
        lockedNumRedStyle.setDataFormat(doubleFormat);
        styles.put(ExcelStyleEnum.LOCKED_NUM_RED.getValue(), lockedNumRedStyle);

        //DATE_LOCK_RED
        XSSFCellStyle lockedDateRedStyle = wb.createCellStyle();
        lockedDateRedStyle.setFillForegroundColor(new XSSFColor(new byte[]{(byte) 0xf0, (byte) 0xf0, (byte) 0xf0}));
        lockedDateRedStyle.setBorderBottom(CellStyle.BORDER_THIN);
        lockedDateRedStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        lockedDateRedStyle.setBorderTop(CellStyle.BORDER_THIN);
        lockedDateRedStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        lockedDateRedStyle.setBorderLeft(CellStyle.BORDER_THIN);
        lockedDateRedStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        lockedDateRedStyle.setBorderRight(CellStyle.BORDER_THIN);
        lockedDateRedStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        lockedDateRedStyle.setWrapText(true);
        lockedDateRedStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        lockedDateRedStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        lockedDateRedStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        lockedDateRedStyle.setFont(redFont);
        lockedDateRedStyle.setDataFormat(dateFormat);
        styles.put(ExcelStyleEnum.DATE_LOCK_RED.getValue(), lockedDateRedStyle);

        //绿色
        XSSFFont greenFont = wb.createFont();
        byte[] b = new byte[3];
        b[0] = (byte) 0x0;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x0;
        greenFont.setColor(new XSSFColor(b));

        putStyle(wb, styles, null, greenFont, false, false, XSSFCellStyle.ALIGN_CENTER, ExcelStyleEnum.BODY_GREEN.getValue());
        putStyle(wb, styles, intFormat, greenFont, false, false, XSSFCellStyle.ALIGN_CENTER, ExcelStyleEnum.INT_GREEN.getValue());
        putStyle(wb, styles, percentFormat, greenFont, false, false, XSSFCellStyle.ALIGN_CENTER, ExcelStyleEnum.PERCENT_GREEN.getValue());
        putStyle(wb, styles, moneyFormat, greenFont, false, true, XSSFCellStyle.ALIGN_CENTER, ExcelStyleEnum.MONEY_LOCKED_GREEN.getValue());
        putStyle(wb, styles, moneyFormat, greenFont, false, false, XSSFCellStyle.ALIGN_CENTER, ExcelStyleEnum.MONEY_UNLOCKED_GREEN.getValue());
        putStyle(wb, styles, dateFormat, greenFont, false, false, XSSFCellStyle.ALIGN_CENTER, ExcelStyleEnum.DATE_GREEN.getValue());
        putStyle(wb, styles, dateMinuteFormat, greenFont, false, false, XSSFCellStyle.ALIGN_CENTER, ExcelStyleEnum.DATE_MINUTE_GREEN.getValue());

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
    private void putStyle(XSSFWorkbook wb, Map<String, XSSFCellStyle> styles, Short fmt, XSSFFont font, boolean isBold, boolean locked, short align, String key) {

        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 9);
        font.setBold(isBold);

        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(align);
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
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

    public void downloadSheet(List<LinkedHashMap<String, Object>> list, File file)throws IOException{

    }


    public void downloadSheet(LinkedHashMap<String, Object> map, File file) throws IOException {

    }

}
