package cn.com.eju.pmls.poi;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.DateUtils;
import cn.com.eju.pmls.poi.common.ExcelColumnSet;
import cn.com.eju.pmls.poi.common.ExcelStyleEnum;
import cn.com.eju.pmls.poi.common.ExcelUseXmlAbstract;
import cn.com.eju.pmls.poi.common.SpeedSheetWriter;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

/**
 * Created by eju on 2019/5/27.
 */
public class ExcelForBatchSale extends ExcelUseXmlAbstract {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    private short lockBlackStyle;
    private short bodyBlackStyle;
    private short dateBlackStyle;
    private short numStyle;
    public void downloadSheet(List<LinkedHashMap<String, Object>> list, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("批量成销模板");

        Map<String, XSSFCellStyle> style = super.createStyles(wb);
        super.headerStyle = style.get(ExcelStyleEnum.HEADER.getValue()).getIndex();
        this.lockBlackStyle = style.get(ExcelStyleEnum.LOCKED.getValue()).getIndex();
        this.bodyBlackStyle = style.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();
        this.dateBlackStyle = style.get(ExcelStyleEnum.DATE_BLACK.getValue()).getIndex();
        this.numStyle = style.get(ExcelStyleEnum.DOUBLE_BLACK.getValue()).getIndex();
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
        sw.beginSheet(setColumnWidth(), 0, 0, "A3");
        int index = 0;
        List<String> listMerge = new ArrayList<>();
        index = this.createSheetHead(sw, index, listMerge);
        if (results != null && results.size() > 0) {
            for (LinkedHashMap<String, Object> map : results) {
                index = this.createSheetBody(sw, index, map);
            }
        }
        sw.endSheetWithPasswordMerge3(listMerge, true, "A2:F2");
        sw.endWorkSheet();
    }

    /**
     * 创建sheet头部内容
     */
    private int createSheetHead(SpeedSheetWriter sw, int index, List<String> listMerge) throws IOException {
        nowRowIndex = index;
        nowColIndex = 0;

        short mainStyle = headerStyle;
        //首行隐藏字段
        sw.insertHiddenRow(nowRowIndex);
        nowColIndex = createBlock(sw, String.valueOf(UserInfoHolder.get().getUserId()), 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "EEB8", 1, 1, mainStyle, listMerge);
        sw.endRow();

        nowRowIndex++;

        nowColIndex = 0;
        sw.insertRow(nowRowIndex);
        //第一行
        nowColIndex = createBlock(sw, "报备编号", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "楼室号", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "成销面积(㎡)*", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "成销金额（元）*", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "核算主体*", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "应计收入税前(元)*", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "应计收入税后(元)*", 1, 1, headerStyle, listMerge);

        nowColIndex = createBlock(sw, "应计返佣税前(元)*", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "应计返佣税后(元)*", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "应计垫佣税前(元)", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "应计垫佣税后(元)", 1, 1, headerStyle, listMerge);

//        nowColIndex = createBlock(sw, "房友返佣税前(元)*", 1, 1, headerStyle, listMerge);
//        nowColIndex = createBlock(sw, "房友返佣税后(元)*", 1, 1, headerStyle, listMerge);
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
        short amountStyle = numStyle;
        short bodyStyle = bodyBlackStyle;
        sw.createCell(i++, map.get("reportId") == null ? "" : map.get("reportId").toString(), lockStyle);
        sw.createCell(i++, map.get("floor") == null ? "" : map.get("floor").toString(), lockStyle);
        setCreateDoubleCell("saleAcreage", i++, amountStyle, false, map, sw);
        setCreateDoubleCell("saleAmount", i++, amountStyle, false, map, sw);
        sw.createCell(i++, map.get("accountProject") == null ? "" : map.get("accountProject").toString(), bodyStyle);
        setCreateDoubleCell("befYjsrTaxAmount", i++, amountStyle, false, map, sw);
        setCreateDoubleCell("aftYjsrTaxAmount", i++, amountStyle, false, map, sw);

        setCreateDoubleCell("befYjfyTaxAmount", i++, amountStyle, false, map, sw);
        setCreateDoubleCell("aftYjfyTaxAmount", i++, amountStyle, false, map, sw);
        setCreateDoubleCell("befYjdyTaxAmount", i++, amountStyle, false, map, sw);
        setCreateDoubleCell("aftYjdyTaxAmount", i++, amountStyle, false, map, sw);

//        setCreateDoubleCell("befFangyouYJFYAmount", i++, amountStyle, false, map, sw);
//        setCreateDoubleCell("aftFangyouYJFYAmount", i++, amountStyle, false, map, sw);
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
        //报备编号
        c = new ExcelColumnSet("1", "1");
        c.setWidth(18);
        columnSet.add(c);

        //楼市号
        c = new ExcelColumnSet("2","2");
        c.setWidth(15);
        columnSet.add(c);

        c = new ExcelColumnSet("3", "4");
        c.setWidth(15);
        columnSet.add(c);

        c = new ExcelColumnSet("5", "5");
        c.setWidth(18);
        columnSet.add(c);

//        c = new ExcelColumnSet("6", "13");
        c = new ExcelColumnSet("6", "11");
        c.setWidth(15);
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

    /**
     * 创建浮点型cell
     */
    private void setCreateDoubleCell(String key ,int i,short style, boolean fillWithZero, LinkedHashMap<String, Object> map,SpeedSheetWriter sw) throws Exception {
        double value = 0;
        Object o = map.get(key);
        //        DecimalFormat formatter = new DecimalFormat("0.00");
        try{
            if (map.get(key) == null) {
                if (!fillWithZero) {
                    sw.createEmptyCell(i, style);
                    return;
                }
            } else {
                if (o instanceof String) {
                    String s = o.toString();
                    value = Double.valueOf(s);
                }else if (o instanceof Double){
                    value = (Double) o;
                }
            }
        }catch(Exception e){
            logger.error(map.get(key).toString());
        }
        sw.createCell(i, value, style);
    }

}
