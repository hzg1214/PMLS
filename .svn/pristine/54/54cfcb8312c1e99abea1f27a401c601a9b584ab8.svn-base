package cn.com.eju.pmls.poi.common;


import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.CellReference;

import java.io.*;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class SpeedSheetWriter {

    private final Writer _out;
    private int _rownum;

    public SpeedSheetWriter(Writer out) {
        _out = out;
    }

    public void beginSheet() throws IOException {
        _out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<worksheet xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\">");
        _out.write("<sheetData>\n");
    }

    /**
     * 设置列宽、固定行、列 PS:topLeftCell="A1"
     * @param columnSet  列宽集合
     * @param xSplit  列号ABC...
     * @param ySplit 行号123...
     * @param topLeftCell 行列右下角的单元格:列号+1的字母和行号+1的组合
     */
    public void beginSheet(List<ExcelColumnSet> columnSet, int xSplit, int ySplit, String topLeftCell) throws IOException {
        _out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<worksheet xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\">");
        if (xSplit > 0 || ySplit > 0) {
            _out.write("<sheetViews><sheetView tabSelected=\"1\" topLeftCell=\"A1\" workbookViewId=\"0\">");
            _out.write("<pane xSplit=\"" + xSplit + "\" ySplit=\"" + ySplit + "\" topLeftCell=\"" + topLeftCell + "\" activePane=\"bottomRight\" state=\"frozen\"/>");
            _out.write("</sheetView></sheetViews>");
        }
        if (columnSet != null && columnSet.size() > 0) {
            _out.write("<cols>\n");
            for (ExcelColumnSet set : columnSet) {
                _out.write(set.getColString() + "\n");
            }
            _out.write("</cols>\n");
        }
        _out.write("<sheetData>\n");
    }

    public void beginSheetWithHidden(String[] hiddenColumnList) throws IOException {
        _out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<worksheet xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\">");
        if (hiddenColumnList != null && hiddenColumnList.length > 0) {
            _out.write("<cols>");
            for (String str : hiddenColumnList) {
                _out.write("<col min=\"" + str + "\" max=\"" + str + "\" hidden=\"1\"/>");
            }
            _out.write("</cols>");
        }
        _out.write("<sheetData>\n");
    }

    public void beginSheetWithHidden(String[] hiddenColumnList, List<ExcelColumnSet> columnSet, int xSplit, int ySplit, String topLeftCell) throws IOException {
        _out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<worksheet xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\">");
        if (xSplit > 0 || ySplit > 0) {
            _out.write("<sheetViews><sheetView tabSelected=\"1\" topLeftCell=\"A1\" workbookViewId=\"0\">");
            _out.write("<pane xSplit=\"" + xSplit + "\" ySplit=\"" + ySplit + "\" topLeftCell=\"" + topLeftCell + "\" activePane=\"bottomRight\" state=\"frozen\"/>");
            _out.write("</sheetView></sheetViews>");
        }
        if (hiddenColumnList != null && hiddenColumnList.length > 0) {
            _out.write("<cols>");
            for (String str : hiddenColumnList) {
                _out.write("<col min=\"" + str + "\" max=\"" + str + "\" hidden=\"1\"/>");
            }
            _out.write("</cols>");
        }
        if (columnSet != null && columnSet.size() > 0) {
            _out.write("<cols>\n");
            for (ExcelColumnSet set : columnSet) {
                _out.write(set.getColString() + "\n");
            }
            _out.write("</cols>\n");
        }
        _out.write("<sheetData>\n");
    }

    public void endSheet() throws IOException {
        _out.write("</sheetData>");
        _out.write("</worksheet>");
    }

    public void endSheetWithMerge(List<String> mergeList) throws IOException {
        _out.write("</sheetData>");
        if (mergeList != null && mergeList.size() > 0) {
            _out.write("<mergeCells count=\"" + mergeList.size() + "\">");
            for (String str : mergeList) {
                _out.write("<mergeCell ref=\"" + str + "\"/>");
            }
            _out.write("</mergeCells>");
        }
        _out.write("</worksheet>");
    }

    public void endSheetWithPasswordMerge(List<String> mergeList, boolean withPassword) throws IOException {
        _out.write("</sheetData>");
        //解锁密码: yxysdbb
        if (withPassword) _out.write("<sheetProtection password=\"EEA6\" sheet=\"1\" objects=\"1\" scenarios=\"1\"/>");
        if (mergeList != null && mergeList.size() > 0) {
            _out.write("<mergeCells count=\"" + mergeList.size() + "\">");
            for (String str : mergeList) {
                _out.write("<mergeCell ref=\"" + str + "\"/>");
            }
            _out.write("</mergeCells>");
        }
        _out.write("</worksheet>");
    }

    public void endSheetWithPasswordMergeForCW(List<String> mergeList, boolean withPassword, int index) throws IOException {
        //<autoFilter ref="A2:CP1797"/>
        _out.write("</sheetData>");
        //解锁密码: xuliang
        if (withPassword)
            _out.write("<sheetProtection password=\"EEB8\" sheet=\"1\" objects=\"1\" scenarios=\"1\" autoFilter=\"0\"/><autoFilter ref=\"A2:CP" + index + "\"/>");
        if (mergeList != null && mergeList.size() > 0) {
            _out.write("<mergeCells count=\"" + mergeList.size() + "\">");
            for (String str : mergeList) {
                _out.write("<mergeCell ref=\"" + str + "\"/>");
            }
            _out.write("</mergeCells>");
        }
        _out.write("</worksheet>");
    }
    //可表头筛选（autoFilter）
    public void endSheetWithPasswordMergeYJ(List<String> mergeList, boolean withPassword, String autoRef) throws IOException {
        _out.write("</sheetData>");
        //解锁密码: xuliang
        if (withPassword)
            _out.write("<sheetProtection password=\"EEB8\" sheet=\"1\" objects=\"1\" scenarios=\"1\" autoFilter=\"0\"/><autoFilter ref=\"" + autoRef + "\"/>");
        if (mergeList != null && mergeList.size() > 0) {
            _out.write("<mergeCells count=\"" + mergeList.size() + "\">");
            for (String str : mergeList) {
                _out.write("<mergeCell ref=\"" + str + "\"/>");
            }
            _out.write("</mergeCells>");
        }
    }

    public void endSheetWithPasswordMerge2(List<String> mergeList, boolean withPassword, int index) throws IOException {
        _out.write("</sheetData>");
        //解锁密码: xuliang
        if (withPassword)
            _out.write("<sheetProtection password=\"EEB8\" sheet=\"1\" objects=\"1\" scenarios=\"1\" autoFilter=\"0\"/>");
        if (mergeList != null && mergeList.size() > 0) {
            _out.write("<mergeCells count=\"" + mergeList.size() + "\">");
            for (String str : mergeList) {
                _out.write("<mergeCell ref=\"" + str + "\"/>");
            }
            _out.write("</mergeCells>");
        }
    }
    //可插入行，删除行（insertRows,deleteRows），表头筛选（autoFilter）
    public void endSheetWithPasswordMerge3(List<String> mergeList, boolean withPassword, String autoRef) throws IOException {
        _out.write("</sheetData>");
        //解锁密码: xuliang
        if (withPassword)
            _out.write("<sheetProtection password=\"EEB8\" sheet=\"1\" objects=\"1\" scenarios=\"1\" insertRows=\"0\" deleteRows=\"0\"  autoFilter=\"0\"/><autoFilter ref=\"" + autoRef + "\"/>");
        if (mergeList != null && mergeList.size() > 0) {
            _out.write("<mergeCells count=\"" + mergeList.size() + "\">");
            for (String str : mergeList) {
                _out.write("<mergeCell ref=\"" + str + "\"/>");
            }
            _out.write("</mergeCells>");
        }
    }
    
    public void startDataValidations(int count) throws IOException{
    	_out.write("<dataValidations count=\"" + count + "\">");
    }
    
    public void addDataValidation(String dataType, String validationType, String sqref, String... values) throws IOException {
    	_out.write("<dataValidation type=\"" + dataType + "\" operator=\"" + validationType + "\" allowBlank=\"1\" showInputMessage=\"1\" showErrorMessage=\"1\" sqref=\"" + sqref + "\">");
    	_out.write("<formula1>" + values[0] + "</formula1>");
    	_out.write("</dataValidation>");
    }
    
    public void endDataValidations() throws IOException{
    	_out.write("</dataValidations>");
    }
    
    public void endWorkSheet() throws IOException{
        _out.write("</worksheet>");
    }

    /**
     * Insert a new row
     *
     * @param rownum 0-based row number
     */
    public void insertRow(int rownum) throws IOException {
        _out.write("<row r=\"" + (rownum + 1) + "\">\n");
        this._rownum = rownum;
    }

    public void insertRow(int rownum, int height) throws IOException {
        _out.write("<row r=\"" + (rownum + 1) + "\" ht=\"" + height + "\"  customHeight=\"1\">\n");
        this._rownum = rownum;
    }

    public void insertHiddenRow(int rownum) throws IOException {
        _out.write("<row r=\"" + (rownum + 1) + "\" hidden=\"1\">\n");
        this._rownum = rownum;
    }

    /**
     * Insert row end marker
     */
    public void endRow() throws IOException {
        _out.write("</row>\n");
    }

    public void createCell(int columnIndex, String value)
            throws IOException {
        createCell(columnIndex, value, -1);
    }


    public void createCell(int columnIndex, String value, int styleIndex)
            throws IOException {

        String ref = new CellReference(_rownum, columnIndex)
                .formatAsString();
        _out.write("<c r=\"" + ref + "\" t=\"inlineStr\"");
        if (styleIndex != -1)
            _out.write(" s=\"" + styleIndex + "\"");
        _out.write(">");
//        _out.write("<is><t>" + value + "</t></is>");
        if (value != null)
            //_out.write("<is><t>" + value.replace("@@", "\n").replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;") + "</t></is>");
            _out.write("<is><t>" + value.replaceAll("\b","\\\\b").replace("@@", "\n").replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;") + "</t></is>");
        else
            _out.write("<is><t>" + value + "</t></is>");

        _out.write("</c>");
    }

    public void createCell(int columnIndex, double value, int styleIndex)
            throws IOException {
        String ref = new CellReference(_rownum, columnIndex)
                .formatAsString();
        _out.write("<c r=\"" + ref + "\" t=\"n\"");
        if (styleIndex != -1)
            _out.write(" s=\"" + styleIndex + "\"");
        _out.write(">");
        _out.write("<v>" + value + "</v>");
        _out.write("</c>");
    }

    public void createEmptyCell(int columnIndex, int styleIndex)
            throws IOException {
        String ref = new CellReference(_rownum, columnIndex)
                .formatAsString();
        _out.write("<c r=\"" + ref + "\" t=\"n\"");
        if (styleIndex != -1)
            _out.write(" s=\"" + styleIndex + "\"");
        _out.write("/>");
    }

    public void createCellStr(int columnIndex, int value, int styleIndex)
            throws IOException {
        String ref = new CellReference(_rownum, columnIndex)
                .formatAsString();
        _out.write("<c r=\"" + ref + "\" t=\"inlineStr\"");
        if (styleIndex != -1)
            _out.write(" s=\"" + styleIndex + "\"");
        _out.write(">");
        _out.write("<is><t>" + value + "</t></is>");
        _out.write("</c>");
    }

    public void createCellStr(int columnIndex, double value, int styleIndex)
            throws IOException {
        String ref = new CellReference(_rownum, columnIndex)
                .formatAsString();
        _out.write("<c r=\"" + ref + "\" t=\"inlineStr\"");
        if (styleIndex != -1)
            _out.write(" s=\"" + styleIndex + "\"");
        _out.write(">");
        _out.write("<is><t>" + value + "</t></is>");
        _out.write("</c>");
    }

    public void createCellWithFormula(int columnIndex, String formula, int styleIndex) throws IOException {
        String ref = new CellReference(_rownum, columnIndex)
                .formatAsString();
        _out.write("<c r=\"" + ref + "\" t=\"n\"");
        if (styleIndex != -1)
            _out.write(" s=\"" + styleIndex + "\"");
        _out.write(">");
        _out.write("<f>");
        _out.write(formula);
        _out.write("</f>");
        _out.write("</c>");
    }

    /**
     * 特殊的公式转换便于生成复杂的公式，特殊字符替换原则：
     * 1. $currentRowIndex$ 替换成当前行号
     *
     * @param columnIndex
     * @param formula
     * @param styleIndex
     * @throws IOException
     */
    public void createCellWithSpFormula(int columnIndex, String formula, int styleIndex) throws IOException {
        String ref = new CellReference(_rownum, columnIndex)
                .formatAsString();
        _out.write("<c r=\"" + ref + "\" t=\"n\"");
        if (styleIndex != -1)
            _out.write(" s=\"" + styleIndex + "\"");
        _out.write(">");
        _out.write("<f>");
        formula = formula.replaceAll("\\$currentRowIndex\\$", "" + (_rownum + 1));
//        System.out.println(formula);
        //System.out.println("formula ===== " + formula);
        _out.write(formula);
        _out.write("</f>");
        _out.write("</c>");
    }

    public void createCell(int columnIndex, double value)
            throws IOException {
        createCell(columnIndex, value, -1);
    }

    public void createCell(int columnIndex, Calendar value, int styleIndex)
            throws IOException {
        createCell(columnIndex, DateUtil.getExcelDate(value, false),
                styleIndex);
    }

    public static void createMuchSheet(File zipfile, Map<String, File> sheetData, OutputStream out) throws ZipException, IOException {
        ZipFile zip = new ZipFile(zipfile);
        ZipOutputStream zos = new ZipOutputStream(out);
        @SuppressWarnings("unchecked")
        Enumeration<ZipEntry> en = (Enumeration<ZipEntry>) zip.entries();
        while (en.hasMoreElements()) {
            ZipEntry ze = en.nextElement();
            if (!ze.getName().startsWith("xl/worksheets/")) {
//				System.out.println(ze.getName()+"###");
                zos.putNextEntry(new ZipEntry(ze.getName()));
                InputStream is = zip.getInputStream(ze);
                copyStream(is, zos);
                is.close();
            }
        }
        for (String str : sheetData.keySet()) {
//			System.out.println("@@@ sheet == "+str);
            zos.putNextEntry(new ZipEntry(str));
            InputStream is = new FileInputStream(sheetData.get(str));
            copyStream(is, zos);
            is.close();
        }
        zos.close();
    }

    private static void copyStream(InputStream in, OutputStream out)
            throws IOException {
        byte[] chunk = new byte[1024];
        int count;
        while ((count = in.read(chunk)) >= 0) {
            out.write(chunk, 0, count);
        }
    }

}
