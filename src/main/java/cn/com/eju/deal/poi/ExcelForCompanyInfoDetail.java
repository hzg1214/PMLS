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

public class ExcelForCompanyInfoDetail extends ExcelUseXmlAbstract {

    private short lockBlackStyle;
    private short bodyBlackStyle;
    private short moneyBlackStyle;
    private short intBlackStyle;
    public void downloadSheet(List<LinkedHashMap<String, Object>> list, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("公司信息明细");

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
        sw.beginSheet(setColumnWidth(), 1, 1, "A1");
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
        nowColIndex = createBlock(sw, "序号", 1, 1, headerStyle, listMerge);
       
        nowColIndex = createBlock(sw, "归属城市", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "公司名称", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "公司注册地址", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "公司经营地址", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "法人", 1, 1, headerStyle, listMerge);
        
        nowColIndex = createBlock(sw, "成立年限", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "统一社会信用代码", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "对接人", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "对接人电话", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "门店数量", 1, 1, headerStyle, listMerge);
        
        nowColIndex = createBlock(sw, "员工数量", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "渠道分类", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "可承接项目类型", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "资源覆盖范围", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "特有资源", 1, 1, headerStyle, listMerge);
        
        nowColIndex = createBlock(sw, "一二手联动规模", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "合作密切开发商", 1, 1, headerStyle, listMerge);
        
        nowColIndex = createBlock(sw, "与我司合作项目数量", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "与我司合作大定业绩（万元）", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "与我司合作成销业绩（万元）", 1, 1, headerStyle, listMerge);
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
        
        sw.createCell(i++, index , lockStyle);
        
        sw.createCell(i++, map.get("cityName") == null ? "" : map.get("cityName").toString(), bodyStyle);
        sw.createCell(i++, map.get("companyName") == null ? "" : map.get("companyName").toString(), bodyStyle);
        sw.createCell(i++, map.get("addressDetail") == null ? "" : map.get("addressDetail").toString(), bodyStyle);
        sw.createCell(i++, map.get("companyAddress") == null ? "" : map.get("companyAddress").toString(), bodyStyle);
        sw.createCell(i++, map.get("legalPerson") == null ? "" : map.get("legalPerson").toString(), bodyStyle);
        
        sw.createCell(i++, clearIntZero(map.get("establishYear")), intStyle);
        sw.createCell(i++, map.get("businessLicenseNo") == null ? "" : map.get("businessLicenseNo").toString(), bodyStyle);
        sw.createCell(i++, map.get("dockingPeo") == null ? "" : map.get("dockingPeo").toString(), bodyStyle);
        sw.createCell(i++, map.get("dockingPeoTel") == null ? "" : map.get("dockingPeoTel").toString(), bodyStyle);
        sw.createCell(i++, clearIntZero(map.get("storeNumber")), intStyle);
        
        sw.createCell(i++, clearIntZero(map.get("comStaffNum")), intStyle);
        sw.createCell(i++, map.get("channelTypeName") == null ? "" : map.get("channelTypeName").toString(), bodyStyle);
        sw.createCell(i++, map.get("undertakeTypeName") == null ? "" : map.get("undertakeTypeName").toString(), bodyStyle);
        sw.createCell(i++, map.get("resourcesRange") == null ? "" : map.get("resourcesRange").toString(), bodyStyle);
        sw.createCell(i++, map.get("specificResources") == null ? "" : map.get("specificResources").toString(), bodyStyle);
        sw.createCell(i++, map.get("lnkScaleName") == null ? "" : map.get("lnkScaleName").toString(), bodyStyle);
        sw.createCell(i++, map.get("closeDeveloper") == null ? "" : map.get("closeDeveloper").toString(), bodyStyle);
        
        sw.createCell(i++, clearIntZero(map.get("cooperationNum")), intStyle);
        sw.createCell(i++, clearZero(map.get("roughAmount")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dealAmount")), moneyStyle);
        
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
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;

        //城市
        c = new ExcelColumnSet("2","2");
        c.setWidth(12);
        columnSet.add(c);
        nColIndex ++;

        //公司名称
        c = new ExcelColumnSet("3", "3");
        c.setWidth(18);
        columnSet.add(c);
        //注册地址
        c = new ExcelColumnSet("4", "4");
        c.setWidth(20);
        columnSet.add(c);

        //经营地址
        c = new ExcelColumnSet("5", "5");
        c.setWidth(16);
        columnSet.add(c);
        //法人、成立年限
        c = new ExcelColumnSet("6", "7");
        c.setWidth(12);
        columnSet.add(c);
        c = new ExcelColumnSet("8", "8");
        c.setWidth(20);
        columnSet.add(c);
        //对接人
        c = new ExcelColumnSet("9", "9");
        c.setWidth(12);
        columnSet.add(c);
        c = new ExcelColumnSet("10", "14");
        c.setWidth(15);
        columnSet.add(c);
        //可承接、范围、特有资源、
        c = new ExcelColumnSet("15", "18");
        c.setWidth(18);
        columnSet.add(c);
        //合作关系密切
        c = new ExcelColumnSet("19", "19");
        c.setWidth(20);
        columnSet.add(c);
        c = new ExcelColumnSet("20", "20");
        c.setWidth(13);
        columnSet.add(c);
        //两个金额
        c = new ExcelColumnSet("21", "22");
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
}
