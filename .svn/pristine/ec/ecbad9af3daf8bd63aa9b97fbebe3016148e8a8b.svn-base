package cn.com.eju.deal.poi;

import cn.com.eju.deal.poi.common.ExcelColumnSet;
import cn.com.eju.deal.poi.common.ExcelStyleEnum;
import cn.com.eju.deal.poi.common.ExcelUseXmlAbstract;
import cn.com.eju.deal.poi.common.SpeedSheetWriter;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelForLnkAchievementSum extends ExcelUseXmlAbstract {
	//构造方法传参
	private String startDate;
	private String endDate;
	public ExcelForLnkAchievementSum(String startDate,String endDate){
		this.startDate = startDate;
		this.endDate = endDate;
	}
    private short lockBlackStyle;
    private short bodyBlackStyle;
    private short moneyBlackStyle;
    private short intBlackStyle;
    public void downloadSheet(List<LinkedHashMap<String, Object>> list, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("联动汇总表");

        Map<String, XSSFCellStyle> style = super.createStyles(wb);
        super.headerStyle = style.get(ExcelStyleEnum.HEADER.getValue()).getIndex();
        this.lockBlackStyle = style.get(ExcelStyleEnum.LOCKED.getValue()).getIndex();
        this.bodyBlackStyle = style.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();
        this.moneyBlackStyle = style.get(ExcelStyleEnum.MONEY_LOCKED_BLACK_RIGHT.getValue()).getIndex();
        this.intBlackStyle = style.get(ExcelStyleEnum.INT_BLACK.getValue()).getIndex();
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
        sw.beginSheet(setColumnWidth(), 1, 3, "A4");
        int index = 0;
        List<String> listMerge = new ArrayList<>();
        index = this.createSheetHead(sw, index, listMerge);
        if (results != null && results.size() > 0) {
            for (LinkedHashMap<String, Object> map : results) {
                index = this.createSheetBody(sw, index, map);
            }
        }
        sw.endSheetWithMerge(listMerge);
    }

    /**
     * 创建sheet头部内容
     */
    private int createSheetHead(SpeedSheetWriter sw, int index, List<String> listMerge) throws IOException {
    	 nowRowIndex = index;
         nowColIndex = 0;
         sw.insertRow(nowRowIndex);
         //第一行
         nowColIndex = createBlock(sw, "序号", 1, 3, listMerge);
         nowColIndex = createBlock(sw, "项目归属区域", 1, 3, listMerge);
         nowColIndex = createBlock(sw, "项目归属城市", 1, 3, listMerge);
         nowColIndex = createBlock(sw, "项目所在城市", 1, 3, listMerge);
         nowColIndex = createBlock(sw, "项目归属部门", 1, 3, listMerge);
         nowColIndex = createBlock(sw, "报备归属城市", 1, 3, listMerge);
         nowColIndex = createBlock(sw, "报备归属中心", 1, 3, listMerge);
         nowColIndex = createBlock(sw, "项目编号", 1, 3, listMerge);
         nowColIndex = createBlock(sw, "项目名称", 1, 3, listMerge);
         nowColIndex = createBlock(sw, "公司编号", 1, 3, listMerge);
         nowColIndex = createBlock(sw, "公司名称", 1, 3, listMerge);
         nowColIndex = createBlock(sw, "门店编号", 1, 3, listMerge);
         nowColIndex = createBlock(sw, "门店名称", 1, 3, listMerge);
         nowColIndex = createBlock(sw, "经服/渠道", 1, 3, listMerge);
         nowColIndex = createBlock(sw, "门店类别", 1, 3, listMerge);
         nowColIndex = createBlock(sw, "门店区域", 1, 3, listMerge);
         if("".equals(startDate)){
         	nowColIndex = createBlock(sw, "当期累计", 8, 1, listMerge);
         }else{
         	nowColIndex = createBlock(sw, "当期累计("+startDate+"至"+endDate+")", 8, 1, listMerge);
         }
         nowColIndex = createBlock(sw, "年累计", 8, 1, listMerge);
         nowColIndex = createBlock(sw, "总累计", 8, 1, listMerge);
         sw.endRow();
         //第二行
         sw.insertRow(++nowRowIndex);
         nowColIndex = 0;
         nowColIndex = createBlock(sw, "序号", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "项目归属区域", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "项目归属城市", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "项目所在城市", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "项目归属部门", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "报备归属城市", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "报备归属中心", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "项目编号", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "项目名称", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "公司编号", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "公司名称", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "门店编号", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "门店名称", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "经服/渠道", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "门店类别", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "门店区域", 1, 0, listMerge);

         for(int i=0;i<3;i++) {
             nowColIndex = createBlock(sw, "报备", 1, 2, listMerge);
             nowColIndex = createBlock(sw, "带看", 1, 2, listMerge);
             nowColIndex = createBlock(sw, "大定", 3, 1, listMerge);
             nowColIndex = createBlock(sw, "成销", 3, 1, listMerge);
         }

         sw.endRow();
         //第三行
         sw.insertRow(++nowRowIndex);
         nowColIndex = 0;
         nowColIndex = createBlock(sw, "序号", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "项目归属区域", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "项目归属城市", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "项目所在城市", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "项目归属部门", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "报备归属城市", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "报备归属中心", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "项目编号", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "项目名称", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "公司编号", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "公司名称", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "门店编号", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "门店名称", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "经服/渠道", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "门店类别", 1, 0, listMerge);
         nowColIndex = createBlock(sw, "门店区域", 1, 0, listMerge);

         for(int i=0;i<3;i++){
            	 nowColIndex = createBlock(sw, "报备", 1, 0, listMerge);
                 nowColIndex = createBlock(sw, "带看", 1, 0, listMerge);
                 
                 nowColIndex = createBlock(sw, "套数", 1, 1, listMerge);
                 nowColIndex = createBlock(sw, "面积", 1, 1, listMerge);
                 nowColIndex = createBlock(sw, "金额", 1, 1, listMerge);
                 nowColIndex = createBlock(sw, "套数", 1, 1, listMerge);
                 nowColIndex = createBlock(sw, "面积", 1, 1, listMerge);
                 nowColIndex = createBlock(sw, "金额", 1, 1, listMerge);
         }
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
        short bodyStyle = bodyBlackStyle;
        short moneyStyle = moneyBlackStyle;
        short intStyle = intBlackStyle;

        sw.createCell(i++, index-2 , lockStyle);
        sw.createCell(i++, map.get("regionName") == null ? "" : map.get("regionName").toString(), bodyStyle);
        sw.createCell(i++, map.get("areaCityName") == null ? "" : map.get("areaCityName").toString(), bodyStyle);
        sw.createCell(i++, map.get("cityName") == null ? "" : map.get("cityName").toString(), bodyStyle);
        sw.createCell(i++, map.get("centerGroupName") == null ? "" : map.get("centerGroupName").toString(), bodyStyle);
        sw.createCell(i++, map.get("cityGroupName") == null ? "" : map.get("cityGroupName").toString(), bodyStyle);
        sw.createCell(i++, map.get("performCenterName") == null ? "" : map.get("performCenterName").toString(), bodyStyle);
        sw.createCell(i++, map.get("projectNo") == null ? "" : map.get("projectNo").toString(), bodyStyle);
        sw.createCell(i++, map.get("projectName") == null ? "" : map.get("projectName").toString(), bodyStyle);
        sw.createCell(i++, map.get("companyId") == null ? "" : map.get("companyId").toString(), bodyStyle);
        sw.createCell(i++, map.get("companyNm") == null ? "" : map.get("companyNm").toString(), bodyStyle);
        sw.createCell(i++, map.get("storeNo") == null ? "" : map.get("storeNo").toString(), bodyStyle);
        sw.createCell(i++, map.get("storeName") == null ? "" : map.get("storeName").toString(), bodyStyle);
        sw.createCell(i++, map.get("expenderName") == null ? "" : map.get("expenderName").toString(), bodyStyle);
        sw.createCell(i++, map.get("storeType") == null ? "" : map.get("storeType").toString(), bodyStyle);
        sw.createCell(i++, map.get("districtName") == null ? "" : map.get("districtName").toString(), bodyStyle);
        //当期
        sw.createCell(i++, clearZero(map.get("dq_bbNum")), intStyle);
        sw.createCell(i++, clearZero(map.get("dq_dkNum")), intStyle);
        sw.createCell(i++, clearZero(map.get("dq_ddNum")), intStyle);
        sw.createCell(i++, clearZero(map.get("dq_ddArea")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dq_ddAmount")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dq_cxNum")), intStyle);
        sw.createCell(i++, clearZero(map.get("dq_cxArea")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dq_cxAmount")), moneyStyle);
        //当年
        sw.createCell(i++, clearZero(map.get("dn_bbNum")), intStyle);
        sw.createCell(i++, clearZero(map.get("dn_dkNum")), intStyle);
        sw.createCell(i++, clearZero(map.get("dn_ddNum")), intStyle);
        sw.createCell(i++, clearZero(map.get("dn_ddArea")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dn_ddAmount")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dn_cxNum")), intStyle);
        sw.createCell(i++, clearZero(map.get("dn_cxArea")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dn_cxAmount")), moneyStyle);
        //总累计
        sw.createCell(i++, clearZero(map.get("lj_bbNum")), intStyle);
        sw.createCell(i++, clearZero(map.get("lj_dkNum")), intStyle);
        sw.createCell(i++, clearZero(map.get("lj_ddNum")), intStyle);
        sw.createCell(i++, clearZero(map.get("lj_ddArea")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("lj_ddAmount")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("lj_cxNum")), intStyle);
        sw.createCell(i++, clearZero(map.get("lj_cxArea")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("lj_cxAmount")), moneyStyle);
        
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
        c = new ExcelColumnSet("1", "1");
        c.setWidth(9);
        columnSet.add(c);
        nColIndex++;

        c = new ExcelColumnSet("2","7");
        c.setWidth(12);
        columnSet.add(c);
        nColIndex ++;

        //项目编号
        c = new ExcelColumnSet("8", "8");
        c.setWidth(10);
        columnSet.add(c);
        //项目名称
        c = new ExcelColumnSet("9", "9");
        c.setWidth(20);
        columnSet.add(c);
        //公司编号
        c = new ExcelColumnSet("10", "10");
        c.setWidth(10);
        columnSet.add(c);
        //公司名称
        c = new ExcelColumnSet("11", "11");
        c.setWidth(20);
        columnSet.add(c);
        //门店
        c = new ExcelColumnSet("12", "12");
        c.setWidth(10);
        columnSet.add(c);
        c = new ExcelColumnSet("13", "13");
        c.setWidth(20);
        columnSet.add(c);
        c = new ExcelColumnSet("14", "16");
        c.setWidth(12);
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
}
