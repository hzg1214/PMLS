package cn.com.eju.pmls.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.pmls.poi.common.ExcelColumnSet;
import cn.com.eju.pmls.poi.common.ExcelStyleEnum;
import cn.com.eju.pmls.poi.common.ExcelUseXmlAbstract;
import cn.com.eju.pmls.poi.common.SpeedSheetWriter;

public class ExcelForRemittanceTrack extends ExcelUseXmlAbstract {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());


    XSSFWorkbook wb;
    private XSSFSheet sheet;

    private Map<String, XSSFCellStyle> styles;
    private String trackType;
    
    //第1周合计
    private static String SUM_WEEK1 = "SUM(H_,I_)";
    //第2周合计
    private static String SUM_WEEK2 = "SUM(K_,L_)";
    //第3周合计
    private static String SUM_WEEK3 = "SUM(N_,O_)";
    //第4周合计
    private static String SUM_WEEK4 = "SUM(Q_,R_)";
    //第5周合计
    private static String SUM_WEEK5 = "SUM(T_,U_)";
    //第6周合计
    private static String SUM_WEEK6 = "SUM(W_,X_)";
    //月份合计
    private static String SUM_MONTH = "SUM(H_,I_,K_,L_,N_,O_,Q_,R_,T_,U_,W_,X_)";

    public ExcelForRemittanceTrack(String trackType){
    	this.trackType = trackType;
    }

    public void downloadSheet(Map<String, Object> head, List<LinkedHashMap<String, Object>> list, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);
        wb = new XSSFWorkbook();
        String sheetName = head.get("templateType").toString();
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

    private void createSheet(Writer out, Map<String, Object> head, List<LinkedHashMap<String, Object>> results) throws Exception {
        SpeedSheetWriter sw = new SpeedSheetWriter(out);
        sw.beginSheet(setColumnWidth(), 4, 3, "E4");

        List<String> listMerge = new ArrayList<String>();
        int index = 0;
        index = this.createSheetHead(sw, index, head, listMerge);
        if (results != null && results.size() > 0) {
            for (LinkedHashMap<String, Object> map : results){
                index = this.createSheetBody(head,sw, index, map);
            }
        }

        sw.endSheetWithPasswordMerge3(listMerge, true, "");
        sw.endWorkSheet();
    }

    /**
     * 创建sheet头部内容
     */
    private int createSheetHead(SpeedSheetWriter sw, int index, Map<String, Object> head, List<String> listMerge) throws IOException {
        nowRowIndex = index;
        nowColIndex = 0;

        short mainStyle = headerStyle;
        //首行隐藏字段
        sw.insertHiddenRow(nowRowIndex);
        nowColIndex = createBlock(sw, "EEB8", 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, trackType, 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, head.get("yearMonthVersion").toString(), 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, head.get("weekVersion").toString(), 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, head.get("sortNo").toString(), 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, head.get("year").toString(), 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, head.get("month").toString(), 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, head.get("weekNo").toString(), 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, head.get("cityNo").toString(), 1, 1, mainStyle, listMerge);
        sw.endRow();
//        System.out.println("yearMonthVersion"+   head.get("yearMonthVersion").toString());
//        System.out.println("weekVersion"+   head.get("weekVersion").toString());
//        System.out.println("sortNo"+   head.get("sortNo").toString());
//        System.out.println("trackType"+   head.get("trackType").toString());
        nowRowIndex++;

        nowColIndex = 0;
        //第一行
        sw.insertRow(nowRowIndex);
        nowColIndex = createBlock(sw, "序号", 1, 2, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "项目编号", 1, 2, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "项目名称", 1, 2, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "合作方", 1, 2, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "项目状态", 1, 2, mainStyle, listMerge);
        Integer month = Integer.parseInt(head.get("month").toString());
        if("0".equals(trackType)) {
        	nowColIndex = createBlock(sw, month+"月预计回款合计", 1, 2, mainStyle, listMerge);
        }else {
        	nowColIndex = createBlock(sw, month+"月实际回款合计", 1, 2, mainStyle, listMerge);
        }
        
        String weekDate1=(String) head.get("weekDate1");
        if(weekDate1!=null) {
        	nowColIndex = createBlock(sw, weekDate1, 3, 1, mainStyle, listMerge);
        }
        
        String weekDate2=(String) head.get("weekDate2");
        if(weekDate2!=null) {
        	nowColIndex = createBlock(sw, weekDate2, 3, 1, mainStyle, listMerge);
        }
        
        String weekDate3=(String) head.get("weekDate3");
        if(weekDate3!=null) {
        	nowColIndex = createBlock(sw, weekDate3, 3, 1, mainStyle, listMerge);
        }
        
        String weekDate4=(String) head.get("weekDate4");
        if(weekDate4!=null) {
        	nowColIndex = createBlock(sw, weekDate4, 3, 1, mainStyle, listMerge);
        }
        
        String weekDate5=(String) head.get("weekDate5");
        if(weekDate5!=null) {
        	nowColIndex = createBlock(sw, weekDate5, 3, 1, mainStyle, listMerge);
        }
        
        String weekDate6=(String) head.get("weekDate6");
        if(weekDate6!=null) {
        	nowColIndex = createBlock(sw, weekDate6, 3, 1, mainStyle, listMerge);
        }
        
        sw.endRow();

        nowRowIndex++;
        nowColIndex = 0;
        //第二行
        //
        sw.insertRow(nowRowIndex);

        nowColIndex = createBlock(sw, "序号", 1, 0, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "项目编号", 1, 0, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "项目名称", 1, 0, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "合作方", 1, 0, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "项目状态", 1, 0, mainStyle, listMerge);
        
        if("0".equals(trackType)) {
        	nowColIndex = createBlock(sw, month+"月预计回款合计", 1, 0, mainStyle, listMerge);
        }else {
        	nowColIndex = createBlock(sw, month+"月实际回款合计", 1, 0, mainStyle, listMerge);
        }
        
        if(weekDate1!=null) {
        	nowColIndex = createBlock(sw, "合计", 1, 1, mainStyle, listMerge);
        	nowColIndex = createBlock(sw, "现金", 1, 1, mainStyle, listMerge);
        	nowColIndex = createBlock(sw, "抵房", 1, 1, mainStyle, listMerge);
        }
        
        if(weekDate2!=null) {
        	nowColIndex = createBlock(sw, "合计", 1, 1, mainStyle, listMerge);
        	nowColIndex = createBlock(sw, "现金", 1, 1, mainStyle, listMerge);
        	nowColIndex = createBlock(sw, "抵房", 1, 1, mainStyle, listMerge);
        }
        
        if(weekDate3!=null) {
        	nowColIndex = createBlock(sw, "合计", 1, 1, mainStyle, listMerge);
        	nowColIndex = createBlock(sw, "现金", 1, 1, mainStyle, listMerge);
        	nowColIndex = createBlock(sw, "抵房", 1, 1, mainStyle, listMerge);
        }
        
        if(weekDate4!=null) {
        	nowColIndex = createBlock(sw, "合计", 1, 1, mainStyle, listMerge);
        	nowColIndex = createBlock(sw, "现金", 1, 1, mainStyle, listMerge);
        	nowColIndex = createBlock(sw, "抵房", 1, 1, mainStyle, listMerge);
        }
        
        if(weekDate5!=null) {
        	nowColIndex = createBlock(sw, "合计", 1, 1, mainStyle, listMerge);
        	nowColIndex = createBlock(sw, "现金", 1, 1, mainStyle, listMerge);
        	nowColIndex = createBlock(sw, "抵房", 1, 1, mainStyle, listMerge);
        }
        
        if(weekDate6!=null) {
        	nowColIndex = createBlock(sw, "合计", 1, 1, mainStyle, listMerge);
        	nowColIndex = createBlock(sw, "现金", 1, 1, mainStyle, listMerge);
        	nowColIndex = createBlock(sw, "抵房", 1, 1, mainStyle, listMerge);
        }

        sw.endRow();
        nowRowIndex = nowRowIndex + 1;

        return nowRowIndex;
    }

    /**
     * 创建sheet具体内容
     */
    private int createSheetBody(Map<String, Object> head,SpeedSheetWriter sw, int index,LinkedHashMap<String, Object> map) throws Exception {
        sw.insertRow(index);
        int i = 0;

        short cnStyle = styles.get(ExcelStyleEnum.LOCKED.getValue()).getIndex();
        short numStyle = styles.get(ExcelStyleEnum.DOUBLE_BLACK.getValue()).getIndex();
        short numLockStyle = styles.get(ExcelStyleEnum.LOCKED_NUM.getValue()).getIndex();
        short bodyBlackStyle = styles.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();
        short dateBlockStyle=styles.get(ExcelStyleEnum.DATE_BLACK.getValue()).getIndex();
        short dateLockStyle = styles.get(ExcelStyleEnum.DATE_LOCK_BLACK.getValue()).getIndex();

        //序号
        sw.createCell(i++, map.get("rowNo").toString(), cnStyle);
        //项目编号
        sw.createCell(i++, map.get("projectNo")==null?"":map.get("projectNo").toString(), cnStyle);
        //项目名称
        sw.createCell(i++, map.get("projectName")==null?"":map.get("projectName").toString(), cnStyle);
        //合作方
        sw.createCell(i++, map.get("partnerName")==null?"":map.get("partnerName").toString(), cnStyle);
        //项目状态
        sw.createCell(i++, map.get("projectStatusStr").toString(), cnStyle);
        
//        setCreateDoubleCell("hkTotal", i++, numLockStyle, false, map, sw);
        String monthFormula = SUM_MONTH.replace("_", String.valueOf(index+1));
        sw.createCellWithFormula(i++, monthFormula, numLockStyle);
        
        String weekDate1=(String) head.get("weekDate1");
        if(weekDate1!=null) {
        	//公式
            String formula = SUM_WEEK1.replace("_", String.valueOf(index+1));
            sw.createCellWithFormula(i++, formula, numLockStyle);
        	setCreateDoubleCell("xjAccount1", i++, numStyle, false, map, sw);
        	setCreateDoubleCell("dfAccount1", i++, numStyle, false, map, sw);
        }
        
        String weekDate2=(String) head.get("weekDate2");
        if(weekDate2!=null) {
        	//公式
            String formula = SUM_WEEK2.replace("_", String.valueOf(index+1));
            sw.createCellWithFormula(i++, formula, numLockStyle);
        	setCreateDoubleCell("xjAccount2", i++, numStyle, false, map, sw);
        	setCreateDoubleCell("dfAccount2", i++, numStyle, false, map, sw);
        }
        
        String weekDate3=(String) head.get("weekDate3");
        if(weekDate3!=null) {
//        	setCreateDoubleCell("weekTotal3", i++, numLockStyle, false, map, sw);
        	//公式
            String formula = SUM_WEEK3.replace("_", String.valueOf(index+1));
            sw.createCellWithFormula(i++, formula, numLockStyle);
        	setCreateDoubleCell("xjAccount3", i++, numStyle, false, map, sw);
        	setCreateDoubleCell("dfAccount3", i++, numStyle, false, map, sw);
        }
        
        String weekDate4=(String) head.get("weekDate4");
        if(weekDate4!=null) {
//        	setCreateDoubleCell("weekTotal4", i++, numLockStyle, false, map, sw);
        	//公式
            String formula = SUM_WEEK4.replace("_", String.valueOf(index+1));
            sw.createCellWithFormula(i++, formula, numLockStyle);
        	setCreateDoubleCell("xjAccount4", i++, numStyle, false, map, sw);
        	setCreateDoubleCell("dfAccount4", i++, numStyle, false, map, sw);
        }
        
        String weekDate5=(String) head.get("weekDate5");
        if(weekDate5!=null) {
//        	setCreateDoubleCell("weekTotal5", i++, numLockStyle, false, map, sw);
        	//公式
            String formula = SUM_WEEK5.replace("_", String.valueOf(index+1));
            sw.createCellWithFormula(i++, formula, numLockStyle);
        	setCreateDoubleCell("xjAccount5", i++, numStyle, false, map, sw);
        	setCreateDoubleCell("dfAccount5", i++, numStyle, false, map, sw);
        }
        
        String weekDate6=(String) head.get("weekDate6");
        if(weekDate6!=null) {
//        	setCreateDoubleCell("weekTotal6", i++, numLockStyle, false, map, sw);
        	//公式
            String formula = SUM_WEEK6.replace("_", String.valueOf(index+1));
            sw.createCellWithFormula(i++, formula, numLockStyle);
        	setCreateDoubleCell("xjAccount6", i++, numStyle, false, map, sw);
        	setCreateDoubleCell("dfAccount6", i++, numStyle, false, map, sw);
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
        ExcelColumnSet c;

        int nColIndex = 1;

        //序号-回款合计
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(6);
        columnSet.add(c);
        nColIndex = nColIndex + 1;
        
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(16);
        columnSet.add(c);
        nColIndex = nColIndex + 1;
        
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex+1));
        c.setWidth(25);
        columnSet.add(c);
        nColIndex = nColIndex + 2;
        
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex+2));
        c.setWidth(16);
        columnSet.add(c);
        nColIndex = nColIndex + 2;

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
