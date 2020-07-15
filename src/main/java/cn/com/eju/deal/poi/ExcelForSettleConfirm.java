package cn.com.eju.deal.poi;

import cn.com.eju.deal.base.helper.LogHelper;
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

/**
 * Created by yinkun on 2018/5/4.
 */
public class ExcelForSettleConfirm extends ExcelUseXmlAbstract {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());


    XSSFWorkbook wb;
    private XSSFSheet sheet;

    private Map<String, XSSFCellStyle> styles;
    private int commisionType; // 20601，20602，20603，20604, 20605

    public ExcelForSettleConfirm(int type){
        commisionType = type;
    }

    public void downloadSheet(Map<String, String> head, List<LinkedHashMap<String, Object>> list, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);
        wb = new XSSFWorkbook();
        String sheetName = head.get("templateType");
        sheet = wb.createSheet(sheetName);

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
        sw.beginSheet(setColumnWidth(), 8, 2, "I3");

        List<String> listMerge = new ArrayList<>();
        int index = 0;
        index = this.createSheetHead(sw, index, head, listMerge);
        if (results != null && results.size() > 0) {
            for (LinkedHashMap<String, Object> map : results){
                index = this.createSheetBody(sw, index, map);
            }
        }

        sw.endSheetWithPasswordMergeYJ(listMerge, true, "A2:I2");
        /*if (results != null && results.size() > 0) {
            sw.startDataValidations(1);
            sw.addDataValidation("decimal", "greaterThanOrEqual", "J4:" + "J" + index , "-999999999");
            sw.addDataValidation("decimal", "greaterThanOrEqual", "K4:" + "K" + index , "-999999999");
            sw.addDataValidation("decimal", "greaterThanOrEqual", "M4:" + "M" + index , "-999999999");
            sw.addDataValidation("decimal", "greaterThanOrEqual", "N4:" + "N" + index , "-999999999");
            sw.endDataValidations();
        }*/
        sw.endWorkSheet();
    }

    public List<ExcelColumnSet> setColumnWidth() {
        List<ExcelColumnSet> columnSet = new ArrayList<>();
        ExcelColumnSet c;

        int nColIndex = 1;

        //序号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(6);
        columnSet.add(c);
        nColIndex++;

        //楼盘名
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //楼盘编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(16);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //楼室号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(16);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //套数
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //成销面积(m2)，成销总价(元)，成销日期，结算确认日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 3));
        c.setWidth(14);
        columnSet.add(c);
        nColIndex = nColIndex + 4;

        return columnSet;
    }

    private int createSheetHead(SpeedSheetWriter sw, int index, Map<String, String> head, List<String> listMerge) throws IOException {
        nowRowIndex = index;
        nowColIndex = 0;

        short mainStyle = headerStyle;
        //首行隐藏字段
        sw.insertHiddenRow(nowRowIndex);
        nowColIndex = createBlock(sw, head.get("UserId"), 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "EEB8", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, String.valueOf(commisionType), 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, head.get("UserName"), 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, head.get("cityNo"), 1, 1, mainStyle, listMerge);
        sw.endRow();

        nowRowIndex++;

        nowColIndex = 0;
        //第一行
        sw.insertRow(nowRowIndex);
        nowColIndex = createBlock(sw, "序号", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "楼盘名", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "报备编号", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "楼室号", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "套数", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "成销面积(m2)", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "成销总价(元)", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "成销日期", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "结算确认日期", 1, 1, mainStyle, listMerge);
        sw.endRow();

        nowRowIndex++;
        nowColIndex = 0;

        return nowRowIndex;
    }

    private int createSheetBody(SpeedSheetWriter sw, int index,LinkedHashMap<String, Object> map) throws Exception {
        sw.insertRow(index);
        int i = 0;
        short dateBlockStyle=styles.get(ExcelStyleEnum.DATE_BLACK.getValue()).getIndex();

        short cnStyle = styles.get(ExcelStyleEnum.LOCKED.getValue()).getIndex();
        short numLockStyle = styles.get(ExcelStyleEnum.LOCKED_NUM.getValue()).getIndex();

        //序号
        sw.createCell(i++, map.get("rowNo").toString(), cnStyle);
        //楼盘名
        sw.createCell(i++, map.get("estateNm").toString(), cnStyle);
        //报备编号
        sw.createCell(i++, map.get("reportId").toString(), cnStyle);
        //楼室号
        sw.createCell(i++, map.get("buildingNo").toString(), cnStyle);
        //套数
        sw.createCell(i++, map.get("num").toString(), cnStyle);
        //成销面积(m2)
        setCreateDoubleCell("area", i++, numLockStyle, false, map, sw);
        //成销总价(元)
        setCreateDoubleCell("dealAmount", i++, numLockStyle, false, map, sw);
        //成销日期
        Object dealDate = map.get("dealDate");
        String dealDateStr = "";
        if (dealDate != null && dealDate instanceof String){
            if (dealDate.toString().length()>=10) dealDateStr = dealDate.toString().substring(0, 10);
        }
        sw.createCell(i++, dealDateStr, cnStyle);
        //结算确认日期
        setCreateDateCell("settleConfirmDate", i++, dateBlockStyle, map, sw);

        sw.endRow();
        index++;

        return index;
    }

    private void setCreateDateCell(String key ,int i,short style, LinkedHashMap<String, Object> map,SpeedSheetWriter sw) throws Exception {
        String value="";
        Object obj = map.get(key);
        if (obj != null && obj instanceof String){
            if (obj.toString().length()>=10) value = obj.toString().substring(0, 10);
        }else{
            sw.createEmptyCell(i, style);
            return;
        }
        sw.createCell(i++, value, style);
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
