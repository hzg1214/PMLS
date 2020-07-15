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

public class ExcelForPadCommision extends ExcelUseXmlAbstract{
	private final LogHelper logger = LogHelper.getLogger(this.getClass());


    XSSFWorkbook wb;
	private XSSFSheet sheet;

	private Map<String, XSSFCellStyle> styles;
	private int commisionType; // 20601，20602，20603，20604

    private static String SUB_FORMULAR_01 = "SUM(L_,O_,R_,U_)";
    private static String SUB_FORMULAR_02 = "SUM(M_,P_,S_,V_)";
    private static String SUB_FORMULAR_03 = "SUM(Z_,AC_,AF_)";
    private static String SUB_FORMULAR_04 = "SUM(AA_,AD_,AG_)";

	public ExcelForPadCommision(int type){
		commisionType = type;
	}

    public void downloadSheet(Map<String, String> head,List<LinkedHashMap<String, Object>> list, File file) throws IOException{
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
        //sw.beginSheetWithHidden(new String[]{"CrtEmpId", "UptEmpId", "DelFlg", "CrtDt", "UptDt"}, setColumnWidth(), 6, 3, "L4");
        sw.beginSheet(setColumnWidth(), 4, 3, "E4");
        
        List<String> listMerge = new ArrayList<String>();
        int index = 0;
//        if (!results.isEmpty()) index = createSheetFoot(sw, index, results.get(0));
        index = this.createSheetHead(sw, index, head, listMerge);
        if (results != null && results.size() > 0) {
            for (LinkedHashMap<String, Object> map : results){//concatMap(results)) {
                index = this.createSheetBody(sw, index, map);
            }
        }

//      sw.endSheet();

        sw.endSheetWithPasswordMerge3(listMerge, true, "A3:O3");
        if (results != null && results.size() > 0) {
        	sw.startDataValidations(1);
            sw.addDataValidation("decimal", "greaterThanOrEqual", "J4:" + "J" + index , "-999999999");
			sw.addDataValidation("decimal", "greaterThanOrEqual", "K4:" + "K" + index , "-999999999");
			sw.addDataValidation("decimal", "greaterThanOrEqual", "M4:" + "M" + index , "-999999999");
			sw.addDataValidation("decimal", "greaterThanOrEqual", "N4:" + "N" + index , "-999999999");

            sw.endDataValidations();
        }
        sw.endWorkSheet();

        //自动调整列宽
//        if (nowColIndex > 0) {
//            for(int i = 51; i < nowColIndex -1; i++) {
//                sheet.autoSizeColumn(i);
//            }
//        }
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
        nowColIndex = createBlock(sw, String.valueOf(commisionType), 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, head.get("UserName"), 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, head.get("cityNo"), 1, 1, mainStyle, listMerge);
	    sw.endRow();
	
	    nowRowIndex++;
	
	    nowColIndex = 0;
        //第一行
        sw.insertRow(nowRowIndex);
        nowColIndex = createBlock(sw, "序号", 1, 2, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "楼盘名", 1, 2, mainStyle, listMerge);
		nowColIndex = createBlock(sw, "报备编号", 1, 2, mainStyle, listMerge);
		nowColIndex = createBlock(sw, "楼室号", 1, 2, mainStyle, listMerge);
		nowColIndex = createBlock(sw, "套数", 1, 2, mainStyle, listMerge);
		nowColIndex = createBlock(sw, "大定总价", 1, 2, mainStyle, listMerge);
		nowColIndex = createBlock(sw, "大定日期", 1, 2, mainStyle, listMerge);
		nowColIndex = createBlock(sw, "成销总价", 1, 2, mainStyle, listMerge);
		nowColIndex = createBlock(sw, "成销日期", 1, 2, mainStyle, listMerge);

        nowColIndex = createBlock(sw, "应垫佣金小计", 2, 1, mainStyle, listMerge);
		nowColIndex = createBlock(sw, "应垫佣金", 3, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "应垫佣金调整1", 3, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "应垫佣金调整2", 3, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "应垫佣金调整3", 3, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "实垫佣金小计", 2, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "实垫佣金", 3, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "实垫佣金调整1", 3, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "实垫佣金调整2", 3, 1, mainStyle, listMerge);
        sw.endRow();

        nowRowIndex++;
        nowColIndex = 0;
        //第二行
        //业绩信息
        sw.insertRow(nowRowIndex);

		nowColIndex = createBlock(sw, "序号", 1, 0, mainStyle, listMerge);
		nowColIndex = createBlock(sw, "楼盘名", 1, 0, mainStyle, listMerge);
		nowColIndex = createBlock(sw, "报备编号", 1, 0, mainStyle, listMerge);
		nowColIndex = createBlock(sw, "楼室号", 1, 0, mainStyle, listMerge);
		nowColIndex = createBlock(sw, "套数", 1, 0, mainStyle, listMerge);
		nowColIndex = createBlock(sw, "大定总价", 1, 0, mainStyle, listMerge);
		nowColIndex = createBlock(sw, "大定日期", 1, 0, mainStyle, listMerge);
		nowColIndex = createBlock(sw, "成销总价", 1, 0, mainStyle, listMerge);
		nowColIndex = createBlock(sw, "成销日期", 1, 0, mainStyle, listMerge);

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

        //序号
        sw.createCell(i++, map.get("rowNo").toString(), bodyBlackStyle);
        //楼盘名
        sw.createCell(i++, map.get("estateNm").toString(), bodyBlackStyle);
        //报备编号
        sw.createCell(i++, map.get("reportId").toString(), bodyBlackStyle);
        //楼室号
        sw.createCell(i++, map.get("buildingNo").toString(), bodyBlackStyle);
        //套数
        sw.createCell(i++, map.get("num").toString(), bodyBlackStyle);
		//大定总价
		setCreateDoubleCell("roughAmount", i++, numStyle, false, map, sw);
		//大定日期
		setCreateDateCell("roughDate", i++, dateBlockStyle, map, sw);
        //成销总价
        setCreateDoubleCell("dealAmount", i++, numStyle, false, map, sw);
        //成销日期
		setCreateDateCell("dealDate", i++, dateBlockStyle, map, sw);

		//公式
        String formula = SUB_FORMULAR_01.replace("_", String.valueOf(index+1));
        sw.createCellWithFormula(i++, formula, numLockStyle);
        String formula2 = SUB_FORMULAR_02.replace("_", String.valueOf(index+1));
        sw.createCellWithFormula(i++, formula2, numLockStyle);

        /*//应垫佣金小计税前
        setCreateDoubleCell("befTaxYjAmountTotal", i++, numLockStyle, false, map, sw);
        //应垫佣金小计税后
        setCreateDoubleCell("aftTaxYjAmountTotal", i++, numLockStyle, false, map, sw);*/
		//应垫佣金税前
		setCreateDoubleCell("befTaxYjAmount", i++, numStyle, false, map, sw);
		//应垫佣金税后
		setCreateDoubleCell("aftTaxYjAmount", i++, numStyle, false, map, sw);
		//应垫佣金日期
		setCreateDateCell("yjRecordDate", i++, dateBlockStyle, map, sw);
        //应垫佣金税前
        setCreateDoubleCell("befTaxYjAmount1", i++, numStyle, false, map, sw);
        //应垫佣金税后
        setCreateDoubleCell("aftTaxYjAmount1", i++, numStyle, false, map, sw);
        //应垫佣金日期
        setCreateDateCell("yjRecordDate1", i++, dateBlockStyle, map, sw);
        //应垫佣金税前
        setCreateDoubleCell("befTaxYjAmount2", i++, numStyle, false, map, sw);
        //应垫佣金税后
        setCreateDoubleCell("aftTaxYjAmount2", i++, numStyle, false, map, sw);
        //应垫佣金日期
        setCreateDateCell("yjRecordDate2", i++, dateBlockStyle, map, sw);
        //应垫佣金税前
        setCreateDoubleCell("befTaxYjAmount3", i++, numStyle, false, map, sw);
        //应垫佣金税后
        setCreateDoubleCell("aftTaxYjAmount3", i++, numStyle, false, map, sw);
        //应垫佣金日期
        setCreateDateCell("yjRecordDate3", i++, dateBlockStyle, map, sw);

        //公式
        String formula3 = SUB_FORMULAR_03.replace("_", String.valueOf(index+1));
        sw.createCellWithFormula(i++, formula3, numLockStyle);
        String formula4 = SUB_FORMULAR_04.replace("_", String.valueOf(index+1));
        sw.createCellWithFormula(i++, formula4, numLockStyle);

        /*//实垫佣金小计税前
        setCreateDoubleCell("befTaxSjAmountTotal", i++, numLockStyle, false, map, sw);
        //实垫佣金小计税后
        setCreateDoubleCell("aftTaxSjAmountTotal", i++, numLockStyle, false, map, sw);*/
		//实垫佣金税前
		setCreateDoubleCell("befTaxSjAmount", i++, numStyle, false, map, sw);
		//实垫佣金税后
		setCreateDoubleCell("aftTaxSjAmount", i++, numStyle, false, map, sw);
		//实垫佣金日期
		setCreateDateCell("sjRecordDate", i++, dateBlockStyle, map, sw);
        //实垫佣金税前
        setCreateDoubleCell("befTaxSjAmount1", i++, numStyle, false, map, sw);
        //实垫佣金税后
        setCreateDoubleCell("aftTaxSjAmount1", i++, numStyle, false, map, sw);
        //实垫佣金日期
        setCreateDateCell("sjRecordDate1", i++, dateBlockStyle, map, sw);
        //实垫佣金税前
        setCreateDoubleCell("befTaxSjAmount2", i++, numStyle, false, map, sw);
        //实垫佣金税后
        setCreateDoubleCell("aftTaxSjAmount2", i++, numStyle, false, map, sw);
        //实垫佣金日期
        setCreateDateCell("sjRecordDate2", i++, dateBlockStyle, map, sw);

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
        c.setWidth(10);
        columnSet.add(c);
        nColIndex = nColIndex + 1;
        
        //套数
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex = nColIndex + 1;
        
        //大定总价，大定日期，成销总价，成销日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 3));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex = nColIndex + 4;

		//税前、税后、日期
		c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 5));
		c.setWidth(14);
		columnSet.add(c);
		nColIndex = nColIndex + 25;

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