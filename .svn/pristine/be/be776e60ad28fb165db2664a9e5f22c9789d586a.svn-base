package cn.com.eju.deal.poi;

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

public class ExcelForProjectDetail extends ExcelUseXmlAbstract {

    private short bodyBlackStyle;
    private short bodyBlackBoldStyle;
    private short money_unlocked_black;
    private short money_unlocked_black_bold;
    private short int_black;
    private short int_black_bold;

    public void downloadSheet(List<LinkedHashMap<String, Object>> list, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("开发项目明细");

        Map<String, XSSFCellStyle> style = super.createStyles(wb);
        super.headerStyle = style.get(ExcelStyleEnum.HEADER.getValue()).getIndex();
        this.bodyBlackStyle = style.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();
        this.bodyBlackBoldStyle = style.get(ExcelStyleEnum.BODY_BLACK_BOLD.getValue()).getIndex();
        this.money_unlocked_black = style.get(ExcelStyleEnum.MONEY_UNLOCKED_BLACK.getValue()).getIndex();
        this.money_unlocked_black_bold = style.get(ExcelStyleEnum.MONEY_UNLOCKED_BLACK_BOLD.getValue()).getIndex();
        this.int_black = style.get(ExcelStyleEnum.INT_BLACK.getValue()).getIndex();
        this.int_black_bold = style.get(ExcelStyleEnum.INT_BLACK_BOLD.getValue()).getIndex();

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
        sw.beginSheet(setColumnWidth(), 0, 1, "A2");
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
        nowColIndex = createBlock(sw, "序号", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "项目归属区域", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "项目归属城市", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "项目所在城市", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "项目归属项目部", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "项目编号", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "项目名称", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "项目状态", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "业务模式", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "开发商品牌", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "开发商名称", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "是否大客户", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "合作方类型", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "合作方品牌", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "合作方名称", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "共场", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "是否直签", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "合作开始时间", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "合作结束时间", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "合同编号", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "物业类型", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "楼盘地理城市", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "楼盘区域", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "开发负责人", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "开发负责人工号", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "项目负责人", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "项目负责人工号", 1, 1, listMerge);

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
        short bodyStyle = bodyBlackStyle;

        sw.createCell(i++, map.get("rowNum") == null ? "" : map.get("rowNum").toString(), bodyStyle);
        sw.createCell(i++, map.get("gsRegion") == null ? "" : map.get("gsRegion").toString(), bodyStyle);
        sw.createCell(i++, map.get("gsCity") == null ? "" : map.get("gsCity").toString(), bodyStyle);
        sw.createCell(i++, map.get("szCity") == null ? "" : map.get("szCity").toString(), bodyStyle);
        sw.createCell(i++, map.get("gsDepartment") == null ? "" : map.get("gsDepartment").toString(), bodyStyle);
        sw.createCell(i++, map.get("projectNo") == null ? "" : map.get("projectNo").toString(), bodyStyle);
        sw.createCell(i++, map.get("projectName") == null ? "" : map.get("projectName").toString(), bodyStyle);
        sw.createCell(i++, map.get("projectStatus") == null ? "" : map.get("projectStatus").toString(), bodyStyle);
        sw.createCell(i++, map.get("businessModel") == null ? "" : map.get("businessModel").toString(), bodyStyle);
        sw.createCell(i++, map.get("developerBrandName") == null ? "" : map.get("developerBrandName").toString(), bodyStyle);
        sw.createCell(i++, map.get("developerName") == null ? "" : map.get("developerName").toString(), bodyStyle);
        sw.createCell(i++, map.get("isBigCustomer") == null ? "" : map.get("isBigCustomer").toString(), bodyStyle);
        sw.createCell(i++, map.get("partnerType") == null ? "" : map.get("partnerType").toString(), bodyStyle);
        sw.createCell(i++, map.get("partnerBrandName") == null ? "" : map.get("partnerBrandName").toString(), bodyStyle);
        sw.createCell(i++, map.get("partnerName") == null ? "" : map.get("partnerName").toString(), bodyStyle);
        sw.createCell(i++, map.get("totalFieldFlag") == null ? "" : map.get("totalFieldFlag").toString(), bodyStyle);
        sw.createCell(i++, map.get("directSignFlag") == null ? "" : map.get("directSignFlag").toString(), bodyStyle);
        sw.createCell(i++, map.get("cooperationDtStart") == null ? "" : map.get("cooperationDtStart").toString(), bodyStyle);
        sw.createCell(i++, map.get("cooperationDtEnd") == null ? "" : map.get("cooperationDtEnd").toString(), bodyStyle);
        sw.createCell(i++, map.get("srlHtNo") == null ? "" : map.get("srlHtNo").toString(), bodyStyle);
        sw.createCell(i++, map.get("managementType") == null ? "" : map.get("managementType").toString(), bodyStyle);
        sw.createCell(i++, map.get("realCity") == null ? "" : map.get("realCity").toString(), bodyStyle);
        sw.createCell(i++, map.get("realDistrict") == null ? "" : map.get("realDistrict").toString(), bodyStyle);
        sw.createCell(i++, map.get("sceneEmpName") == null ? "" : map.get("sceneEmpName").toString(), bodyStyle);
        sw.createCell(i++, map.get("sceneEmpCode") == null ? "" : map.get("sceneEmpCode").toString(), bodyStyle);
        sw.createCell(i++, map.get("projectLeaderEmpName") == null ? "" : map.get("projectLeaderEmpName").toString(), bodyStyle);
        sw.createCell(i++, map.get("projectLeaderEmpCode") == null ? "" : map.get("projectLeaderEmpCode").toString(), bodyStyle);

        sw.endRow();
        index++;

        return index;
    }

    private Double clearZero(Object value) {
        if (value == null || value.equals("-"))
            return Double.parseDouble("0.00");

        Double data = Double.parseDouble(value.toString());
        return data;
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
        c.setWidth(5);
        columnSet.add(c);
        nColIndex++;

        //归属城市
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 2));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex += 3;

        //项目归属项目部
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(14);
        columnSet.add(c);
        nColIndex++;

        //项目编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex++;

        //项目名称
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(18);
        columnSet.add(c);
        nColIndex++;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 15));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex += 16;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(14);
        columnSet.add(c);
        nColIndex++;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex++;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(14);
        columnSet.add(c);
        nColIndex++;

        return columnSet;
    }
}
