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

public class ExcelForInCommision extends ExcelUseXmlAbstract{
	private final LogHelper logger = LogHelper.getLogger(this.getClass()); 
	
//    private short bodyStyleBlack;
//    private short bodyStyleRed;
//    private short dateStyleBlack;
//    private short dateStyleRed;
    
    private String endYear;
    private String endMonth;
    private String yearNowStr;
	
    XSSFWorkbook wb;
	private XSSFSheet sheet;
	
	private Map<String, XSSFCellStyle> styles;
	private int commisionType; // 20601，20602，20603

	private static String SUB_FORMULAR_01 = "SUM(J_,L_,N_,P_,R_,T_,V_,X_,Z_,AB_,AD_,AF_,AH_)";
	private static String SUB_FORMULAR_02 = "SUM(K_,M_,O_,Q_,S_,U_,W_,Y_,AA_,AC_,AE_,AG_,AI_)";
	private static String SUB_FORMULAR_03 = "SUM(AL_,AN_,AP_,AR_,AT_,AV_,AX_,AZ_,BB_,BD_,BF_,BH_,BJ_)";
	private static String SUB_FORMULAR_04 = "SUM(AM_,AO_,AQ_,AS_,AU_,AW_,AY_,BA_,BC_,BE_,BG_,BI_,BK_)";
	
	public ExcelForInCommision(int type){
		commisionType = type;
	}

    public void downloadSheet(Map<String, String> head,List<LinkedHashMap<String, Object>> list, File file) throws IOException{
    	OutputStream os = new FileOutputStream(file);
        wb = new XSSFWorkbook();
        String sheetName = head.get("templateType");
        sheet = wb.createSheet(sheetName);

        styles = super.createStyles(wb);
        super.headerStyle = styles.get(ExcelStyleEnum.HEADER.getValue()).getIndex();
//        this.bodyStyleBlack = style.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();      
//        this.bodyStyleRed = style.get(ExcelStyleEnum.BODY_RED.getValue()).getIndex();      
//        this.dateStyleBlack = style.get(ExcelStyleEnum.DATE_BLACK.getValue()).getIndex();
//        this.dateStyleRed = style.get(ExcelStyleEnum.DATE_RED.getValue()).getIndex();

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
        sw.beginSheet(setColumnWidth(), 7, 4, "H5");
        
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
        String startValid = "G";
        String endValid = "AI";
        if (commisionType == 20601){
        	endValid = "BK";
        }else if (commisionType == 20602 || commisionType == 20606){
        }else if (commisionType == 20603){
        	endValid = "BK";
        }
		//增加筛选
        sw.endSheetWithPasswordMergeYJ(listMerge, true, "A4:" + endValid + index);
        if (results != null && results.size() > 0) {
        	sw.startDataValidations(1);
            sw.addDataValidation("decimal", "greaterThanOrEqual", startValid + "6:" + endValid + index , "-999999999");
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

//        short mainStyle = bodyStyleRed;
//        short commisionStyle = bodyStyleBlack;
        //List<String> listMerge=new  ArrayList<String>();
    	// UserId, Password, Year, MonthXSSFCellStyle style6 = wb.createCellStyle();
        short mainStyle = headerStyle;
        sw.insertHiddenRow(nowRowIndex);
        nowColIndex = createBlock(sw, head.get("UserId"), 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "EEB8", 1, 1, mainStyle, listMerge);
        endYear = head.get("EndYear");
        nowColIndex = createBlock(sw, endYear, 1, 1, mainStyle, listMerge);
        endMonth = head.get("EndMonth");
        nowColIndex = createBlock(sw, endMonth, 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, String.valueOf(commisionType), 1, 1, mainStyle, listMerge);
        String dateStart = head.get("CountDateStart");
        if (dateStart == null) dateStart = "";
        nowColIndex = createBlock(sw, dateStart, 1, 1, mainStyle, listMerge);
        String dateEnd = head.get("CountDateEnd");
        if (dateEnd == null) dateEnd = "";
        nowColIndex = createBlock(sw, dateEnd, 1, 1, mainStyle, listMerge);
//        Calendar calendar = Calendar.getInstance();
        yearNowStr = head.get("YearSale");
        if (yearNowStr == null) yearNowStr = "";
//        int monthNow = calendar.get(Calendar.MONTH + 1);
//        nowColIndex = createBlock(sw, yearNowStr, 1, 1, mainStyle, listMerge);
//        nowColIndex = createBlock(sw, String.valueOf(monthNow), 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, head.get("UserName"), 1, 1, mainStyle, listMerge);
        nowColIndex = createBlock(sw, head.get("YearSale"), 1, 1, mainStyle, listMerge);
    	
	    sw.endRow();
	
	    nowRowIndex++;
	
	    nowColIndex = 0;
        //第一行
        sw.insertRow(nowRowIndex);
        nowColIndex = createBlock(sw, "编号", 1, 3, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "业绩信息", 6, 1, mainStyle, listMerge);
        int blocks = 1;
        if (commisionType == 20601){
            nowColIndex = createBlock(sw, "应计收入", 28, 1, mainStyle, listMerge);
            nowColIndex = createBlock(sw, "应计返佣", 28, 1, mainStyle, listMerge);
            blocks = 2;
        }else if (commisionType == 20602){
            nowColIndex = createBlock(sw, "应计内佣", 28, 1, mainStyle, listMerge);
        }else if (commisionType == 20603){
            nowColIndex = createBlock(sw, "应计实收", 28, 1, mainStyle, listMerge);
            nowColIndex = createBlock(sw, "实际返佣", 28, 1, mainStyle, listMerge);
            blocks = 2;
        }else if(commisionType == 20606){
			nowColIndex = createBlock(sw, "应收收入", 28, 1, mainStyle, listMerge);
		}
        	
        sw.endRow();

        nowRowIndex++;

        nowColIndex = 0;
        
        
        //第二行
        //业绩信息
        sw.insertRow(nowRowIndex);
        nowColIndex = createBlock(sw, "编号", 1, 0, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "楼盘名", 1, 2, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "报备编号", 1, 2, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "楼室号", 1, 2, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "套数", 1, 2, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "成销总价", 1, 2, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "成销日期", 1, 2, mainStyle, listMerge);
        
        //收入返佣信息
        for (int i=0;i<blocks;i++){
	        nowColIndex = createBlock(sw, "小计", 2, 1, mainStyle, listMerge);
	        nowColIndex = createBlock(sw, "当年以前", 2, 1, mainStyle, listMerge);
	        nowColIndex = createBlock(sw, yearNowStr + "年1月", 2, 1, mainStyle, listMerge);
	        nowColIndex = createBlock(sw, yearNowStr + "年2月", 2, 1, mainStyle, listMerge);
	        nowColIndex = createBlock(sw, yearNowStr + "年3月", 2, 1, mainStyle, listMerge);
	        nowColIndex = createBlock(sw, yearNowStr + "年4月", 2, 1, mainStyle, listMerge);
	        nowColIndex = createBlock(sw, yearNowStr + "年5月", 2, 1, mainStyle, listMerge);
	        nowColIndex = createBlock(sw, yearNowStr + "年6月", 2, 1, mainStyle, listMerge);
	        nowColIndex = createBlock(sw, yearNowStr + "年7月", 2, 1, mainStyle, listMerge);
	        nowColIndex = createBlock(sw, yearNowStr + "年8月", 2, 1, mainStyle, listMerge);
	        nowColIndex = createBlock(sw, yearNowStr + "年9月", 2, 1, mainStyle, listMerge);
	        nowColIndex = createBlock(sw, yearNowStr + "年10月", 2, 1, mainStyle, listMerge);
	        nowColIndex = createBlock(sw, yearNowStr + "年11月", 2, 1, mainStyle, listMerge);
	        nowColIndex = createBlock(sw, yearNowStr + "年12月", 2, 1, mainStyle, listMerge);
        }
    	
	    sw.endRow();
	
	    nowRowIndex++;
	
	    nowColIndex = 0;
	    
	    
	    //第三行
	    //业绩信息
	    sw.insertRow(nowRowIndex);
        nowColIndex = createBlock(sw, "编号", 1, 0, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "楼盘名", 1, 0, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "报备编号", 1, 0, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "楼室号", 1, 0, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "套数", 1, 0, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "成销总价", 1, 0, mainStyle, listMerge);
        nowColIndex = createBlock(sw, "成销日期", 1, 0, mainStyle, listMerge);
	    
	    //收入返佣信息
        if (commisionType == 20601){
	        for (int i=0;i<blocks*14;i++){
		        nowColIndex = createBlock(sw, "税前", 1, 1, mainStyle, listMerge);
		        nowColIndex = createBlock(sw, "税后", 1, 1, mainStyle, listMerge);
	        }
        }else if (commisionType == 20602){
            for (int i=0;i<blocks*14;i++){
    	        nowColIndex = createBlock(sw, "岗位佣金", 1, 1, mainStyle, listMerge);
    	        nowColIndex = createBlock(sw, "团佣", 1, 1, mainStyle, listMerge);
            }
        }else if (commisionType == 20603){
            for (int i=0;i<blocks*14;i++){
    	        nowColIndex = createBlock(sw, "税前", 1, 1, mainStyle, listMerge);
    	        nowColIndex = createBlock(sw, "税后", 1, 1, mainStyle, listMerge);
            }
        }else if (commisionType == 20606){
			for (int i=0;i<blocks*14;i++){
				nowColIndex = createBlock(sw, "税前", 1, 1, mainStyle, listMerge);
				nowColIndex = createBlock(sw, "税后", 1, 1, mainStyle, listMerge);
			}
		}

        sw.endRow();

        nowRowIndex = nowRowIndex + 1;

        return nowRowIndex;
    }
    /*
    private List<LinkedHashMap<String, String>> concatMap(List<LinkedHashMap<String, String>> sourceList){
    	List<LinkedHashMap<String, String>> concatList = new ArrayList<>();
    	for (LinkedHashMap<String, String> sourceMap : sourceList){
    		String reportId = sourceMap.get("reportId");
			boolean existed = false;
			LinkedHashMap<String, String> targetMap = null;
    		for (LinkedHashMap<String, String> map1 : concatList){
    			if (map1.get("reportId").equals(reportId)){
    				existed = true;
    				targetMap = map1;
    			}
    		}
			if (!existed){
				targetMap = new LinkedHashMap<>();
			}
			if (targetMap != null) for (String key : sourceMap.keySet()){
				targetMap.put(key + sourceMap.get("amountType"), sourceMap.get(key));
			}
			concatList.add(targetMap);
    	}
    	return concatList;
    }
*/
    /**
     * 创建sheet具体内容
     */
    private int createSheetBody(SpeedSheetWriter sw, int index,LinkedHashMap<String, Object> map) throws Exception {
        sw.insertRow(index);
        int i = 0;
        
        short cnStyle = styles.get(ExcelStyleEnum.LOCKED.getValue()).getIndex();   
        short numStyle = styles.get(ExcelStyleEnum.DOUBLE_BLACK.getValue()).getIndex();    
        short numLockStyle = styles.get(ExcelStyleEnum.LOCKED_NUM.getValue()).getIndex();    
//        short dateStyle = styles.get(ExcelStyleEnum.DATE_LOCK_BLACK.getValue()).getIndex();
//        short numLockStyle = styles.get("no_protect_body_lock").getIndex();//金额格式黑色锁定
//		short numStyle = styles.get("no_protect_body").getIndex();//金额格式黑色
//		short cnStyle =styles.get("center").getIndex();//中文格式黑色
//		short dateStyle = styles.get("date").getIndex();//日期格式黑色

        //序号
        sw.createCellStr(i++, index-3, cnStyle);
        //-- 业绩信息 --
        //楼盘名
        sw.createCell(i++, map.get("estateNm").toString(), cnStyle);
        //报备编号
        sw.createCell(i++, map.get("reportId").toString(), cnStyle);
        //楼室号
        sw.createCell(i++, map.get("buildingNo").toString(), cnStyle);
        //套数
        sw.createCell(i++, map.get("num").toString(), cnStyle);
        //成销总价
        setCreateDoubleCell("dealAmount", i++, numLockStyle, true, map, sw);
        //成销日期
        Object dealDate = map.get("dealDate");
        String dealDateStr = "";
        if (dealDate != null && dealDate instanceof String){
        	if (dealDate.toString().length()>=10) dealDateStr = dealDate.toString().substring(0, 10);
        }
        sw.createCell(i++, dealDateStr, cnStyle);
        
        //收入返佣信息
        int year = 0;
        int month = 0;
        try {
        	year = Integer.valueOf(endYear);
	        month = Integer.valueOf(endMonth);
        }catch (Exception e) {}
        int yearNow = Integer.valueOf(yearNowStr);
        if (commisionType == 20601){
//        	setCreateDoubleCell("totalPreTax01", i++, numLockStyle, map, sw);
//        	setCreateDoubleCell("totalAfterTax01", i++, numLockStyle, map, sw);
        	String formula = SUB_FORMULAR_01.replace("_", String.valueOf(index+1));
        	sw.createCellWithFormula(i++, formula, numLockStyle);
        	formula = SUB_FORMULAR_02.replace("_", String.valueOf(index+1));
        	sw.createCellWithFormula(i++, formula, numLockStyle);
        	setCreateDoubleCell("beforeAmountPreTax01", i++, numLockStyle, true, map, sw);
        	setCreateDoubleCell("beforeAmountAfterTax01", i++, numLockStyle, true, map, sw);
        	setCreateDoubleCell("janPreTax01", i++, (year>yearNow || (year==yearNow && month>0))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("janAfterTax01", i++, (year>yearNow || (year==yearNow && month>0))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("febPreTax01", i++, (year>yearNow || (year==yearNow && month>1))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("febAfterTax01", i++, (year>yearNow || (year==yearNow && month>1))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("marPreTax01", i++, (year>yearNow || (year==yearNow && month>2))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("marAfterTax01", i++, (year>yearNow || (year==yearNow && month>2))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("aprPreTax01", i++, (year>yearNow || (year==yearNow && month>3))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("aprAfterTax01", i++, (year>yearNow || (year==yearNow && month>3))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("mayPreTax01", i++, (year>yearNow || (year==yearNow && month>4))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("mayAfterTax01", i++, (year>yearNow || (year==yearNow && month>4))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("junPreTax01", i++, (year>yearNow || (year==yearNow && month>5))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("junAfterTax01", i++, (year>yearNow || (year==yearNow && month>5))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("julPreTax01", i++, (year>yearNow || (year==yearNow && month>6))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("julAfterTax01", i++, (year>yearNow || (year==yearNow && month>6))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("augPreTax01", i++, (year>yearNow || (year==yearNow && month>7))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("augAfterTax01", i++, (year>yearNow || (year==yearNow && month>7))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("sepPreTax01", i++, (year>yearNow || (year==yearNow && month>8))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("sepAfterTax01", i++, (year>yearNow || (year==yearNow && month>8))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("octPreTax01", i++, (year>yearNow || (year==yearNow && month>9))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("octAfterTax01", i++, (year>yearNow || (year==yearNow && month>9))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("novPreTax01", i++, (year>yearNow || (year==yearNow && month>10))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("novAfterTax01", i++, (year>yearNow || (year==yearNow && month>10))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("decPreTax01", i++, (year>yearNow || (year==yearNow && month>11))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("decAfterTax01", i++, (year>yearNow || (year==yearNow && month>11))? numLockStyle:numStyle, false, map, sw);
//        	setCreateDoubleCell("totalPreTax02", i++, numLockStyle, map, sw);
//        	setCreateDoubleCell("totalAfterTax02", i++, numLockStyle, map, sw);
        	formula = SUB_FORMULAR_03.replace("_", String.valueOf(index+1));
        	sw.createCellWithFormula(i++, formula, numLockStyle);
        	formula = SUB_FORMULAR_04.replace("_", String.valueOf(index+1));
        	sw.createCellWithFormula(i++, formula, numLockStyle);
        	setCreateDoubleCell("beforeAmountPreTax02", i++, numLockStyle, true, map, sw);
        	setCreateDoubleCell("beforeAmountAfterTax02", i++, numLockStyle, true, map, sw);
        	setCreateDoubleCell("janPreTax02", i++, (year>yearNow || (year==yearNow && month>0))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("janAfterTax02", i++, (year>yearNow || (year==yearNow && month>0))? numLockStyle:numStyle,false, map, sw);
        	setCreateDoubleCell("febPreTax02", i++, (year>yearNow || (year==yearNow && month>1))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("febAfterTax02", i++, (year>yearNow || (year==yearNow && month>1))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("marPreTax02", i++, (year>yearNow || (year==yearNow && month>2))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("marAfterTax02", i++, (year>yearNow || (year==yearNow && month>2))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("aprPreTax02", i++, (year>yearNow || (year==yearNow && month>3))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("aprAfterTax02", i++, (year>yearNow || (year==yearNow && month>3))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("mayPreTax02", i++, (year>yearNow || (year==yearNow && month>4))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("mayAfterTax02", i++, (year>yearNow || (year==yearNow && month>4))? numLockStyle:numStyle,false, map, sw);
        	setCreateDoubleCell("junPreTax02", i++, (year>yearNow || (year==yearNow && month>5))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("junAfterTax02", i++, (year>yearNow || (year==yearNow && month>5))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("julPreTax02", i++, (year>yearNow || (year==yearNow && month>6))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("julAfterTax02", i++, (year>yearNow || (year==yearNow && month>6))? numLockStyle:numStyle,false, map, sw);
        	setCreateDoubleCell("augPreTax02", i++, (year>yearNow || (year==yearNow && month>7))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("augAfterTax02", i++, (year>yearNow || (year==yearNow && month>7))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("sepPreTax02", i++, (year>yearNow || (year==yearNow && month>8))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("sepAfterTax02", i++, (year>yearNow || (year==yearNow && month>8))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("octPreTax02", i++, (year>yearNow || (year==yearNow && month>9))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("octAfterTax02", i++, (year>yearNow || (year==yearNow && month>9))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("novPreTax02", i++, (year>yearNow || (year==yearNow && month>10))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("novAfterTax02", i++, (year>yearNow || (year==yearNow && month>10))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("decPreTax02", i++, (year>yearNow || (year==yearNow && month>11))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("decAfterTax02", i++, (year>yearNow || (year==yearNow && month>11))? numLockStyle:numStyle,false, map, sw);
        }else if (commisionType == 20602){
//        	setCreateDoubleCell("totalPreTax01", i++, numLockStyle, map, sw);
//        	setCreateDoubleCell("totalAfterTax01", i++, numLockStyle, map, sw);
        	String formula = SUB_FORMULAR_01.replace("_", String.valueOf(index+1));
        	sw.createCellWithFormula(i++, formula, numLockStyle);
        	formula = SUB_FORMULAR_02.replace("_", String.valueOf(index+1));
        	sw.createCellWithFormula(i++, formula, numLockStyle);
        	setCreateDoubleCell("beforeAmountPreTax01", i++, numLockStyle, true, map, sw);
        	setCreateDoubleCell("beforeAmountAfterTax01", i++, numLockStyle, true, map, sw);
        	setCreateDoubleCell("janPreTax01", i++, (year>yearNow || (year==yearNow && month>0))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("janAfterTax01", i++, (year>yearNow || (year==yearNow && month>0))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("febPreTax01", i++, (year>yearNow || (year==yearNow && month>1))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("febAfterTax01", i++, (year>yearNow || (year==yearNow && month>1))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("marPreTax01", i++, (year>yearNow || (year==yearNow && month>2))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("marAfterTax01", i++, (year>yearNow || (year==yearNow && month>2))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("aprPreTax01", i++, (year>yearNow || (year==yearNow && month>3))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("aprAfterTax01", i++, (year>yearNow || (year==yearNow && month>3))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("mayPreTax01", i++, (year>yearNow || (year==yearNow && month>4))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("mayAfterTax01", i++, (year>yearNow || (year==yearNow && month>4))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("junPreTax01", i++, (year>yearNow || (year==yearNow && month>5))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("junAfterTax01", i++, (year>yearNow || (year==yearNow && month>5))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("julPreTax01", i++, (year>yearNow || (year==yearNow && month>6))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("julAfterTax01", i++, (year>yearNow || (year==yearNow && month>6))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("augPreTax01", i++, (year>yearNow || (year==yearNow && month>7))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("augAfterTax01", i++, (year>yearNow || (year==yearNow && month>7))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("sepPreTax01", i++, (year>yearNow || (year==yearNow && month>8))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("sepAfterTax01", i++, (year>yearNow || (year==yearNow && month>8))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("octPreTax01", i++, (year>yearNow || (year==yearNow && month>9))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("octAfterTax01", i++, (year>yearNow || (year==yearNow && month>9))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("novPreTax01", i++, (year>yearNow || (year==yearNow && month>10))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("novAfterTax01", i++, (year>yearNow || (year==yearNow && month>10))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("decPreTax01", i++, (year>yearNow || (year==yearNow && month>11))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("decAfterTax01", i++, (year>yearNow || (year==yearNow && month>11))? numLockStyle:numStyle, false, map, sw);
        }else if (commisionType == 20603){
//        	setCreateDoubleCell("totalPreTax01", i++, numLockStyle, map, sw);
//        	setCreateDoubleCell("totalAfterTax01", i++, numLockStyle, map, sw);
        	String formula = SUB_FORMULAR_01.replace("_", String.valueOf(index+1));
        	sw.createCellWithFormula(i++, formula, numLockStyle);
        	formula = SUB_FORMULAR_02.replace("_", String.valueOf(index+1));
        	sw.createCellWithFormula(i++, formula, numLockStyle);
        	setCreateDoubleCell("beforeAmountPreTax01", i++, numLockStyle, true, map, sw);
        	setCreateDoubleCell("beforeAmountAfterTax01", i++, numLockStyle, true, map, sw);
        	setCreateDoubleCell("janPreTax01", i++, (year>yearNow || (year==yearNow && month>0))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("janAfterTax01", i++, (year>yearNow || (year==yearNow && month>0))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("febPreTax01", i++, (year>yearNow || (year==yearNow && month>1))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("febAfterTax01", i++, (year>yearNow || (year==yearNow && month>1))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("marPreTax01", i++, (year>yearNow || (year==yearNow && month>2))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("marAfterTax01", i++, (year>yearNow || (year==yearNow && month>2))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("aprPreTax01", i++, (year>yearNow || (year==yearNow && month>3))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("aprAfterTax01", i++, (year>yearNow || (year==yearNow && month>3))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("mayPreTax01", i++, (year>yearNow || (year==yearNow && month>4))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("mayAfterTax01", i++, (year>yearNow || (year==yearNow && month>4))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("junPreTax01", i++, (year>yearNow || (year==yearNow && month>5))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("junAfterTax01", i++, (year>yearNow || (year==yearNow && month>5))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("julPreTax01", i++, (year>yearNow || (year==yearNow && month>6))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("julAfterTax01", i++, (year>yearNow || (year==yearNow && month>6))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("augPreTax01", i++, (year>yearNow || (year==yearNow && month>7))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("augAfterTax01", i++, (year>yearNow || (year==yearNow && month>7))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("sepPreTax01", i++, (year>yearNow || (year==yearNow && month>8))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("sepAfterTax01", i++, (year>yearNow || (year==yearNow && month>8))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("octPreTax01", i++, (year>yearNow || (year==yearNow && month>9))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("octAfterTax01", i++, (year>yearNow || (year==yearNow && month>9))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("novPreTax01", i++, (year>yearNow || (year==yearNow && month>10))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("novAfterTax01", i++, (year>yearNow || (year==yearNow && month>10))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("decPreTax01", i++, (year>yearNow || (year==yearNow && month>11))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("decAfterTax01", i++, (year>yearNow || (year==yearNow && month>11))? numLockStyle:numStyle, false, map, sw);
//        	setCreateDoubleCell("totalPreTax02", i++, numLockStyle, map, sw);
//        	setCreateDoubleCell("totalAfterTax02", i++, numLockStyle, map, sw);
        	formula = SUB_FORMULAR_03.replace("_", String.valueOf(index+1));
        	sw.createCellWithFormula(i++, formula, numLockStyle);
        	formula = SUB_FORMULAR_04.replace("_", String.valueOf(index+1));
        	sw.createCellWithFormula(i++, formula, numLockStyle);
        	setCreateDoubleCell("beforeAmountPreTax02", i++, numLockStyle, true, map, sw);
        	setCreateDoubleCell("beforeAmountAfterTax02", i++, numLockStyle, true, map, sw);
        	setCreateDoubleCell("janPreTax02", i++, (year>yearNow || (year==yearNow && month>0))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("janAfterTax02", i++, (year>yearNow || (year==yearNow && month>0))? numLockStyle:numStyle,false, map, sw);
        	setCreateDoubleCell("febPreTax02", i++, (year>yearNow || (year==yearNow && month>1))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("febAfterTax02", i++, (year>yearNow || (year==yearNow && month>1))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("marPreTax02", i++, (year>yearNow || (year==yearNow && month>2))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("marAfterTax02", i++, (year>yearNow || (year==yearNow && month>2))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("aprPreTax02", i++, (year>yearNow || (year==yearNow && month>3))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("aprAfterTax02", i++, (year>yearNow || (year==yearNow && month>3))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("mayPreTax02", i++, (year>yearNow || (year==yearNow && month>4))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("mayAfterTax02", i++, (year>yearNow || (year==yearNow && month>4))? numLockStyle:numStyle,false, map, sw);
        	setCreateDoubleCell("junPreTax02", i++, (year>yearNow || (year==yearNow && month>5))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("junAfterTax02", i++, (year>yearNow || (year==yearNow && month>5))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("julPreTax02", i++, (year>yearNow || (year==yearNow && month>6))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("julAfterTax02", i++, (year>yearNow || (year==yearNow && month>6))? numLockStyle:numStyle,false, map, sw);
        	setCreateDoubleCell("augPreTax02", i++, (year>yearNow || (year==yearNow && month>7))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("augAfterTax02", i++, (year>yearNow || (year==yearNow && month>7))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("sepPreTax02", i++, (year>yearNow || (year==yearNow && month>8))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("sepAfterTax02", i++, (year>yearNow || (year==yearNow && month>8))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("octPreTax02", i++, (year>yearNow || (year==yearNow && month>9))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("octAfterTax02", i++, (year>yearNow || (year==yearNow && month>9))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("novPreTax02", i++, (year>yearNow || (year==yearNow && month>10))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("novAfterTax02", i++, (year>yearNow || (year==yearNow && month>10))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("decPreTax02", i++, (year>yearNow || (year==yearNow && month>11))? numLockStyle:numStyle, false, map, sw);
        	setCreateDoubleCell("decAfterTax02", i++, (year>yearNow || (year==yearNow && month>11))? numLockStyle:numStyle,false, map, sw);
        }else if (commisionType == 20606){
//        	setCreateDoubleCell("totalPreTax01", i++, numLockStyle, map, sw);
//        	setCreateDoubleCell("totalAfterTax01", i++, numLockStyle, map, sw);
			String formula = SUB_FORMULAR_01.replace("_", String.valueOf(index+1));
			sw.createCellWithFormula(i++, formula, numLockStyle);
			formula = SUB_FORMULAR_02.replace("_", String.valueOf(index+1));
			sw.createCellWithFormula(i++, formula, numLockStyle);
			setCreateDoubleCell("beforeAmountPreTax01", i++, numLockStyle, true, map, sw);
			setCreateDoubleCell("beforeAmountAfterTax01", i++, numLockStyle, true, map, sw);
			setCreateDoubleCell("janPreTax01", i++, (year>yearNow || (year==yearNow && month>0))? numLockStyle:numStyle, false, map, sw);
			setCreateDoubleCell("janAfterTax01", i++, (year>yearNow || (year==yearNow && month>0))? numLockStyle:numStyle, false, map, sw);
			setCreateDoubleCell("febPreTax01", i++, (year>yearNow || (year==yearNow && month>1))? numLockStyle:numStyle, false, map, sw);
			setCreateDoubleCell("febAfterTax01", i++, (year>yearNow || (year==yearNow && month>1))? numLockStyle:numStyle, false, map, sw);
			setCreateDoubleCell("marPreTax01", i++, (year>yearNow || (year==yearNow && month>2))? numLockStyle:numStyle, false, map, sw);
			setCreateDoubleCell("marAfterTax01", i++, (year>yearNow || (year==yearNow && month>2))? numLockStyle:numStyle, false, map, sw);
			setCreateDoubleCell("aprPreTax01", i++, (year>yearNow || (year==yearNow && month>3))? numLockStyle:numStyle, false, map, sw);
			setCreateDoubleCell("aprAfterTax01", i++, (year>yearNow || (year==yearNow && month>3))? numLockStyle:numStyle, false, map, sw);
			setCreateDoubleCell("mayPreTax01", i++, (year>yearNow || (year==yearNow && month>4))? numLockStyle:numStyle, false, map, sw);
			setCreateDoubleCell("mayAfterTax01", i++, (year>yearNow || (year==yearNow && month>4))? numLockStyle:numStyle, false, map, sw);
			setCreateDoubleCell("junPreTax01", i++, (year>yearNow || (year==yearNow && month>5))? numLockStyle:numStyle, false, map, sw);
			setCreateDoubleCell("junAfterTax01", i++, (year>yearNow || (year==yearNow && month>5))? numLockStyle:numStyle, false, map, sw);
			setCreateDoubleCell("julPreTax01", i++, (year>yearNow || (year==yearNow && month>6))? numLockStyle:numStyle, false, map, sw);
			setCreateDoubleCell("julAfterTax01", i++, (year>yearNow || (year==yearNow && month>6))? numLockStyle:numStyle, false, map, sw);
			setCreateDoubleCell("augPreTax01", i++, (year>yearNow || (year==yearNow && month>7))? numLockStyle:numStyle, false, map, sw);
			setCreateDoubleCell("augAfterTax01", i++, (year>yearNow || (year==yearNow && month>7))? numLockStyle:numStyle, false, map, sw);
			setCreateDoubleCell("sepPreTax01", i++, (year>yearNow || (year==yearNow && month>8))? numLockStyle:numStyle, false, map, sw);
			setCreateDoubleCell("sepAfterTax01", i++, (year>yearNow || (year==yearNow && month>8))? numLockStyle:numStyle, false, map, sw);
			setCreateDoubleCell("octPreTax01", i++, (year>yearNow || (year==yearNow && month>9))? numLockStyle:numStyle, false, map, sw);
			setCreateDoubleCell("octAfterTax01", i++, (year>yearNow || (year==yearNow && month>9))? numLockStyle:numStyle, false, map, sw);
			setCreateDoubleCell("novPreTax01", i++, (year>yearNow || (year==yearNow && month>10))? numLockStyle:numStyle, false, map, sw);
			setCreateDoubleCell("novAfterTax01", i++, (year>yearNow || (year==yearNow && month>10))? numLockStyle:numStyle, false, map, sw);
			setCreateDoubleCell("decPreTax01", i++, (year>yearNow || (year==yearNow && month>11))? numLockStyle:numStyle, false, map, sw);
			setCreateDoubleCell("decAfterTax01", i++, (year>yearNow || (year==yearNow && month>11))? numLockStyle:numStyle, false, map, sw);
		}

        sw.createCell(i++, map.get("estateId").toString(), cnStyle);
        //报备详细id
        sw.createCell(i++, map.get("detailId").toString(), cnStyle);
        
        sw.endRow();
        index++;

        return index;
    }

	/**
     * 创建sheet隐藏内容
	 * @throws Exception 
    private int createSheetFoot(SpeedSheetWriter sw, int index, LinkedHashMap<String, String> data) throws Exception {

//    	List<String> hiddenString = new LinkedList<String>();
//		hiddenString.add("importSettlement");
//		hiddenString.add(data.get("CrtEmpId"));
//		hiddenString.add(data.get("UptEmpId"));
//		hiddenString.add(data.get("DelFlg"));
//		hiddenString.add(data.get("CrtDt"));
//		hiddenString.add(data.get("UptDt"));
//		sheet.setHiddenString(hiddenString);
//		sheet.setColumnHidden(index, true);
		sw.insertRow(index);
		int i=0;//列
        sw.createCell(i++, data.get("CrtEmpId"), bodyStyleBlack);
        sw.createCell(i++, data.get("UptEmpId"), bodyStyleBlack);
        sw.createCell(i++, data.get("DelFlg"), bodyStyleBlack);
        setCreateDateCell("CrtDt", i++, bodyStyleBlack, data, sw);
        setCreateDateCell("UptDt", i++, bodyStyleBlack, data, sw);
		sw.endRow();
		index++;
        return index;
	}
     */

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
              
        //业绩信息
        //楼盘名
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(25);
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
        c.setWidth(10);
        columnSet.add(c);
        nColIndex = nColIndex + 1;
        
        //楼室号，成销总价，成销日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 1));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 2;
        
        //收入返佣信息
        int cells = 28;
        if (commisionType == 20601){
        	cells *= 2;
        }else if (commisionType == 20602 || commisionType == 20606){
        	cells *= 1;
        }else if (commisionType == 20603){
        	cells *= 2;
        }
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + cells - 1));
        c.setWidth(14);
        columnSet.add(c);
        nColIndex = nColIndex + cells;
        
        //楼盘编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
        c.setHidden(true);
        columnSet.add(c);
        nColIndex = nColIndex + 1;
        
        //报备详细id
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
        c.setHidden(true);
        columnSet.add(c);
        nColIndex = nColIndex + 1;
        
        return columnSet;
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