package cn.com.eju.pmls.poi;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.pmls.commission.enums.EstateTypeEnum;
import cn.com.eju.pmls.poi.common.ExcelColumnSet;
import cn.com.eju.pmls.poi.common.ExcelStyleEnum;
import cn.com.eju.pmls.poi.common.ExcelUseXmlAbstract;
import cn.com.eju.pmls.poi.common.SpeedSheetWriter;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by eju on 2019/12/18.
 */
public class ExcelForLnkFyFy extends ExcelUseXmlAbstract {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());


    XSSFWorkbook wb;
    private XSSFSheet sheet;

    private Map<String, XSSFCellStyle> styles;
    private String commisionType;

    private static String SUB_FORMULAR_01 = "SUM(N_,Q_,T_,W_,Z_,AC_,AF_)";
    private static String SUB_FORMULAR_02 = "SUM(O_,R_,U_,X_,AA_,AD_,AG_)";

    public ExcelForLnkFyFy(String type){
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
        sw.beginSheet(setColumnWidth(), 4, 3, "E4");

        List<String> listMerge = new ArrayList<String>();
        int index = 0;
        index = this.createSheetHead(sw, index, head, listMerge);
        if (results != null && results.size() > 0) {
            for (LinkedHashMap<String, Object> map : results){
                index = this.createSheetBody(sw, index, map);
            }
        }

        sw.endSheetWithPasswordMerge3(listMerge, true, "A3:AQ3");
        if (results != null && results.size() > 0) {
            sw.startDataValidations(1);
            sw.addDataValidation("decimal", "greaterThanOrEqual", "L4:" + "L" + index , "-999999999");
            sw.addDataValidation("decimal", "greaterThanOrEqual", "M4:" + "M" + index , "-999999999");

            sw.endDataValidations();
        }
        sw.endWorkSheet();

    }

    /**
     * 创建sheet头部内容
     */
    private int createSheetHead(SpeedSheetWriter sw, int index, Map<String, String> head, List<String> listMerge) throws IOException {
        nowRowIndex = index;
        nowColIndex = 0;

        short mainStyle = headerStyle;
        //首行隐藏字段
        sw.insertHiddenRow(nowRowIndex);
        nowColIndex = createBlock(sw, head.get("UserId"), 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "EEB8", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, commisionType, 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, head.get("UserName"), 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, head.get("cityNo"), 1, 1, mainStyle, listMerge);
        sw.endRow();

        nowRowIndex++;

        nowColIndex = 0;
        //第一行
        sw.insertRow(nowRowIndex);
        nowColIndex = createBlock(sw, "序号", 1, 2, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "项目编号", 1, 2, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "项目名称", 1, 2, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "订单编号", 1, 2, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "渠道公司编号", 1, 2, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "渠道公司", 1, 2, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "楼室号", 1, 2, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "套数", 1, 2, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "成销总价", 1, 2, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "成销日期", 1, 2, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "收入类型", 1, 2, mainStyle, listMerge);

        nowColIndex = createBlock(sw, "房友返佣小计", 2, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "房友返佣", 3, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "房友返佣调整1", 3, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "房友返佣调整2", 3, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "房友返佣调整3", 3, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "房友返佣调整4", 3, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "房友返佣调整5", 3, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "房友返佣调整6", 3, 1, mainStyle, listMerge);


        sw.endRow();

        nowRowIndex++;
        nowColIndex = 0;
        //第二行
        //业绩信息
        sw.insertRow(nowRowIndex);

        nowColIndex = createBlock(sw, "序号", 1, 0, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "项目编号", 1, 0, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "项目名称", 1, 0, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "订单编号", 1, 0, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "渠道公司编号", 1, 0, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "渠道公司", 1, 0, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "楼室号", 1, 0, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "套数", 1, 0, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "成销总价", 1, 0, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "成销日期", 1, 0, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "收入类型", 1, 0, mainStyle, listMerge);

        nowColIndex = createBlock(sw, "税前", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "税后", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "税前", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "税后", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "日期", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "税前", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "税后", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "日期", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "税前", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "税后", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "日期", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "税前", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "税后", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "日期", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "税前", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "税后", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "日期", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "税前", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "税后", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "日期", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "税前", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "税后", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "日期", 1, 1, mainStyle, listMerge);

        sw.endRow();
        nowRowIndex = nowRowIndex + 1;

        return nowRowIndex;
    }

    /**
     * 创建sheet具体内容
     */
    private int createSheetBody(SpeedSheetWriter sw, int index,LinkedHashMap<String, Object> map) throws Exception {
        sw.insertRow(index);
        int i = 0;

        short cnStyle = styles.get(ExcelStyleEnum.LOCKED.getValue()).getIndex();
        short numStyle = styles.get(ExcelStyleEnum.DOUBLE_BLACK.getValue()).getIndex();
        short numLockStyle = styles.get(ExcelStyleEnum.LOCKED_NUM.getValue()).getIndex();
        short bodyBlackStyle = styles.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();
        short dateBlockStyle=styles.get(ExcelStyleEnum.DATE_BLACK.getValue()).getIndex();
        short dateLockStyle = styles.get(ExcelStyleEnum.DATE_LOCK_BLACK.getValue()).getIndex();

        Integer num = Integer.valueOf(map.get("num").toString());
        if (num < 0) {
            cnStyle = styles.get(ExcelStyleEnum.LOCKED_RED.getValue()).getIndex();
            numStyle = styles.get(ExcelStyleEnum.DOUBLE_RED.getValue()).getIndex();
            numLockStyle = styles.get(ExcelStyleEnum.LOCKED_NUM_RED.getValue()).getIndex();
            bodyBlackStyle = styles.get(ExcelStyleEnum.BODY_RED.getValue()).getIndex();
            dateBlockStyle = styles.get(ExcelStyleEnum.DATE_RED.getValue()).getIndex();
            dateLockStyle = styles.get(ExcelStyleEnum.DATE_LOCK_RED.getValue()).getIndex();
        }

        //序号
        sw.createCell(i++, map.get("rowNo").toString(), cnStyle);
        //项目编号
        sw.createCell(i++, map.get("projectNo")==null?"":map.get("projectNo").toString(), cnStyle);
        //楼盘名
        sw.createCell(i++, map.get("estateNm").toString(), cnStyle);
        //订单编号
        sw.createCell(i++, map.get("reportId").toString(), cnStyle);
        //渠道公司编号
        sw.createCell(i++, map.get("companyNo") == null ? "" : (String) map.get("companyNo"), cnStyle);
        //渠道公司
        sw.createCell(i++, map.get("companyName") == null ? "" : (String) map.get("companyName"), cnStyle);
        //楼室号
        sw.createCell(i++, map.get("buildingNo").toString(), cnStyle);
        //套数
        //返佣
        Integer defaultFlag = Integer.valueOf(map.get("defaultFlag").toString());
        if(defaultFlag == 0){
            sw.createCell(i++, map.get("num").toString(), cnStyle);
            //成销总价
            setCreateDoubleCell("dealAmount", i++, numLockStyle, false, map, sw);
        }else if(defaultFlag == 1){
            sw.createCell(i++, "", cnStyle);
            sw.createCell(i++, "", cnStyle);
        }
        //成销日期
        setCreateDateCell("dealDate", i++, dateLockStyle, map, sw);
        //收入类型
        sw.createCell(i++, map.get("inComeName")==null?"":map.get("inComeName").toString(), cnStyle);

        //公式
        String formula = SUB_FORMULAR_01.replace("_", String.valueOf(index+1));
        sw.createCellWithFormula(i++, formula, numLockStyle);
        String formula2 = SUB_FORMULAR_02.replace("_", String.valueOf(index+1));
        sw.createCellWithFormula(i++, formula2, numLockStyle);

        //开关账标记
        Integer switchFlag = (Integer) map.get("switchFlag");
        if (switchFlag != null && switchFlag > 0) {
            //税前
            setCreateDoubleCell("befTaxAmount", i++, numLockStyle, false, map, sw);
            //税后
            setCreateDoubleCell("aftTaxAmount", i++, numLockStyle, false, map, sw);
            //日期
            setCreateDateCell("recordDate", i++, dateLockStyle, map, sw);
        } else {
            //税前
            setCreateDoubleCell("befTaxAmount", i++, numStyle, false, map, sw);
            //税后
            setCreateDoubleCell("aftTaxAmount", i++, numStyle, false, map, sw);
            //日期
            setCreateDateCell("recordDate", i++, dateBlockStyle, map, sw);
        }

        Integer switchFlag1 = (Integer) map.get("switchFlag1");
        if ((switchFlag1 != null && switchFlag1 > 0) || num <0) {
            //税前
            setCreateDoubleCell("befTaxAmount1", i++, numLockStyle, false, map, sw);
            //税后
            setCreateDoubleCell("aftTaxAmount1", i++, numLockStyle, false, map, sw);
            //日期
            setCreateDateCell("recordDate1", i++, dateLockStyle, map, sw);
        } else {
            //税前
            setCreateDoubleCell("befTaxAmount1", i++, numStyle, false, map, sw);
            //税后
            setCreateDoubleCell("aftTaxAmount1", i++, numStyle, false, map, sw);
            //日期
            setCreateDateCell("recordDate1", i++, dateBlockStyle, map, sw);
        }

        Integer switchFlag2 = (Integer) map.get("switchFlag2");
        if ((switchFlag2 != null && switchFlag2 > 0) || num <0) {
            //税前
            setCreateDoubleCell("befTaxAmount2", i++, numLockStyle, false, map, sw);
            //税后
            setCreateDoubleCell("aftTaxAmount2", i++, numLockStyle, false, map, sw);
            //日期
            setCreateDateCell("recordDate2", i++, dateLockStyle, map, sw);
        } else {
            //税前
            setCreateDoubleCell("befTaxAmount2", i++, numStyle, false, map, sw);
            //税后
            setCreateDoubleCell("aftTaxAmount2", i++, numStyle, false, map, sw);
            //日期
            setCreateDateCell("recordDate2", i++, dateBlockStyle, map, sw);
        }

        Integer switchFlag3 = (Integer) map.get("switchFlag3");
        if ((switchFlag3 != null && switchFlag3 > 0) || num <0) {
            //税前
            setCreateDoubleCell("befTaxAmount3", i++, numLockStyle, false, map, sw);
            //税后
            setCreateDoubleCell("aftTaxAmount3", i++, numLockStyle, false, map, sw);
            //日期
            setCreateDateCell("recordDate3", i++, dateLockStyle, map, sw);
        } else {
            //税前
            setCreateDoubleCell("befTaxAmount3", i++, numStyle, false, map, sw);
            //税后
            setCreateDoubleCell("aftTaxAmount3", i++, numStyle, false, map, sw);
            //日期
            setCreateDateCell("recordDate3", i++, dateBlockStyle, map, sw);
        }

        Integer switchFlag4 = (Integer) map.get("switchFlag4");
        if ((switchFlag4 != null && switchFlag4 > 0) || num <0) {
            //税前
            setCreateDoubleCell("befTaxAmount4", i++, numLockStyle, false, map, sw);
            //税后
            setCreateDoubleCell("aftTaxAmount4", i++, numLockStyle, false, map, sw);
            //日期
            setCreateDateCell("recordDate4", i++, dateLockStyle, map, sw);
        } else {
            //税前
            setCreateDoubleCell("befTaxAmount4", i++, numStyle, false, map, sw);
            //税后
            setCreateDoubleCell("aftTaxAmount4", i++, numStyle, false, map, sw);
            //日期
            setCreateDateCell("recordDate4", i++, dateBlockStyle, map, sw);
        }

        Integer switchFlag5 = (Integer) map.get("switchFlag5");
        if ((switchFlag5 != null && switchFlag5 > 0) || num <0) {
            //税前
            setCreateDoubleCell("befTaxAmount5", i++, numLockStyle, false, map, sw);
            //税后
            setCreateDoubleCell("aftTaxAmount5", i++, numLockStyle, false, map, sw);
            //日期
            setCreateDateCell("recordDate5", i++, dateLockStyle, map, sw);
        } else {
            //税前
            setCreateDoubleCell("befTaxAmount5", i++, numStyle, false, map, sw);
            //税后
            setCreateDoubleCell("aftTaxAmount5", i++, numStyle, false, map, sw);
            //日期
            setCreateDateCell("recordDate5", i++, dateBlockStyle, map, sw);
        }

        Integer switchFlag6 = (Integer) map.get("switchFlag6");
        if ((switchFlag6 != null && switchFlag6 > 0) || num <0) {
            //税前
            setCreateDoubleCell("befTaxAmount6", i++, numLockStyle, false, map, sw);
            //税后
            setCreateDoubleCell("aftTaxAmount6", i++, numLockStyle, false, map, sw);
            //日期
            setCreateDateCell("recordDate6", i++, dateLockStyle, map, sw);
        } else {
            //税前
            setCreateDoubleCell("befTaxAmount6", i++, numStyle, false, map, sw);
            //税后
            setCreateDoubleCell("aftTaxAmount6", i++, numStyle, false, map, sw);
            //日期
            setCreateDateCell("recordDate6", i++, dateBlockStyle, map, sw);
        }
        //订单明细id
        sw.createCell(i++, map.get("detailId").toString(), bodyBlackStyle);
        sw.createCell(i++, map.get("switchFlag") == null ? "0" : map.get("switchFlag").toString(), bodyBlackStyle);
        sw.createCell(i++, map.get("switchFlag1") == null ? "0" : map.get("switchFlag1").toString(), bodyBlackStyle);
        sw.createCell(i++, map.get("switchFlag2") == null ? "0" : map.get("switchFlag2").toString(), bodyBlackStyle);
        sw.createCell(i++, map.get("switchFlag3") == null ? "0" : map.get("switchFlag3").toString(), bodyBlackStyle);
        sw.createCell(i++, map.get("switchFlag4") == null ? "0" : map.get("switchFlag4").toString(), bodyBlackStyle);
        sw.createCell(i++, map.get("switchFlag5") == null ? "0" : map.get("switchFlag5").toString(), bodyBlackStyle);
        sw.createCell(i++, map.get("switchFlag6") == null ? "0" : map.get("switchFlag6").toString(), bodyBlackStyle);
        sw.createCell(i++, map.get("num").toString(), bodyBlackStyle);
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
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(6);
        columnSet.add(c);
        nColIndex++;

        //项目编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(9);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //项目名称
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(19);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //订单编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(16);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //渠道公司编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //渠道公司
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(31);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //楼室号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //套数
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(6);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //成销总价，成销日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 1));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex = nColIndex + 2;

        //收入类型
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //税前、税后、日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 23));
        c.setWidth(14);
        columnSet.add(c);
        nColIndex = nColIndex + 23;

        c =  new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 8));
        c.setHidden(true);
        columnSet.add(c);

        return columnSet;
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
