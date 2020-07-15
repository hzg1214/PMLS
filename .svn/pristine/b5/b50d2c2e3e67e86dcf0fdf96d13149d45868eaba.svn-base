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

/**
 * Created by haidan on 2019/9/25.
 */
public class ExcelForKefuWjEvaluation extends ExcelUseXmlAbstract {

    private short bodyBlackStyle;
    private short intBlackStyle;
    public ExcelForKefuWjEvaluation(){

    }
    public void downloadSheet(List<LinkedHashMap<String, Object>> list, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("门店测评数据统计表");

        Map<String, XSSFCellStyle> style = super.createStyles(wb);
        super.headerStyle = style.get(ExcelStyleEnum.HEADER.getValue()).getIndex();
        this.bodyBlackStyle = style.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();
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
        sw.beginSheet(setColumnWidth(), 0, 2, "A3");
        int index = 0;
        List<String> listMerge = new ArrayList<>();
        index = this.createSheetHead(sw, index, listMerge,results);
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
    private int createSheetHead(SpeedSheetWriter sw, int index, List<String> listMerge,List<LinkedHashMap<String, Object>> results) throws Exception {
        nowRowIndex = index;
        nowColIndex = 0;
        sw.insertRow(nowRowIndex);
        //第一行
        nowColIndex = createBlock(sw, "序号", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "归属城市", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "门店所在城市", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "所属中心", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "公司编号", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "公司名称", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "门店编号", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "门店名称", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "门店地址", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "联系人", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "电话", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "区域", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "区域分数", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "门店规模", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "门店规模分数", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "凝聚力", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "凝聚力分数", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "服务需求", 9, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "服务潜力", 9, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "品牌服务", 5, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "服务", 6, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "测评总分", 1, 2, headerStyle, listMerge);

        sw.endRow();
        //第二行
        sw.insertRow(++nowRowIndex);
        nowColIndex = 0;
        nowColIndex = createBlock(sw, "", 1, 0, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, headerStyle, listMerge);

        nowColIndex = createBlock(sw, "品牌", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "招聘", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "培训", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "服务", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "系统", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "交易", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "公盘", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "联动", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "合计", 1, 1, listMerge);

        nowColIndex = createBlock(sw, "品牌", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "招聘", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "培训", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "服务", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "系统", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "交易", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "公盘", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "联动", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "合计", 1, 1, listMerge);

        nowColIndex = createBlock(sw, "店招合规", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "统一着装", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "场所规范", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "无负面报道", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "合计", 1, 1, listMerge);

        nowColIndex = createBlock(sw, "系统", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "培训", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "招聘", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "交易", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "联动", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "公盘", 1, 1, listMerge);

        nowColIndex = createBlock(sw, "", 1, 0, headerStyle, listMerge);

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
        short intStyle = intBlackStyle;
        sw.createCell(i++, clearIntZero(map.get("rowNo")), intStyle);
        sw.createCell(i++, map.get("acCityName") == null ? "" : map.get("acCityName").toString(), bodyStyle);
        sw.createCell(i++, map.get("cityName") == null ? "" : map.get("cityName").toString(), bodyStyle);
        sw.createCell(i++, map.get("centerName") == null ? "" : map.get("centerName").toString(), bodyStyle);
        sw.createCell(i++, map.get("companyNo") == null ? "" : map.get("companyNo").toString(), bodyStyle);
        sw.createCell(i++, map.get("companyName") == null ? "" : map.get("companyName").toString(), bodyStyle);
        sw.createCell(i++, map.get("storeNo") == null ? "" : map.get("storeNo").toString(), bodyStyle);
        sw.createCell(i++, map.get("storeName") == null ? "" : map.get("storeName").toString(), bodyStyle);
        sw.createCell(i++, map.get("storeAddress") == null ? "" : map.get("storeAddress").toString(), bodyStyle);
        sw.createCell(i++, map.get("storeManager") == null ? "" : map.get("storeManager").toString(), bodyStyle);
        sw.createCell(i++, map.get("storeManagerPhone") == null ? "" : map.get("storeManagerPhone").toString(), bodyStyle);

        sw.createCell(i++, map.get("qy") == null ? "" : map.get("qy").toString(), bodyStyle);
        sw.createCell(i++, clearIntZero(map.get("qyfs")), intStyle);
        sw.createCell(i++, map.get("mdgm") == null ? "" : map.get("mdgm").toString(), bodyStyle);
        sw.createCell(i++, clearIntZero(map.get("mdgmfs")), intStyle);
        sw.createCell(i++, map.get("njl") == null ? "" : map.get("njl").toString(), bodyStyle);
        sw.createCell(i++, clearIntZero(map.get("njlfs")), intStyle);

        sw.createCell(i++, clearIntZero(map.get("fwxq_pp")), intStyle);
        sw.createCell(i++, clearIntZero(map.get("fwxq_zp")), intStyle);
        sw.createCell(i++, clearIntZero(map.get("fwxq_px")), intStyle);
        sw.createCell(i++, clearIntZero(map.get("fwxq_fw")), intStyle);
        sw.createCell(i++, clearIntZero(map.get("fwxq_xt")), intStyle);
        sw.createCell(i++, clearIntZero(map.get("fwxq_jy")), intStyle);
        sw.createCell(i++, clearIntZero(map.get("fwxq_gp")), intStyle);
        sw.createCell(i++, clearIntZero(map.get("fwxq_ld")), intStyle);
        sw.createCell(i++, clearIntZero(map.get("fwxq_hj")), intStyle);

        sw.createCell(i++, clearIntZero(map.get("fwql_pp")), intStyle);
        sw.createCell(i++, clearIntZero(map.get("fwql_zp")), intStyle);
        sw.createCell(i++, clearIntZero(map.get("fwql_px")), intStyle);
        sw.createCell(i++, clearIntZero(map.get("fwql_fw")), intStyle);
        sw.createCell(i++, clearIntZero(map.get("fwql_xt")), intStyle);
        sw.createCell(i++, clearIntZero(map.get("fwql_jy")), intStyle);
        sw.createCell(i++, clearIntZero(map.get("fwql_gp")), intStyle);
        sw.createCell(i++, clearIntZero(map.get("fwql_ld")), intStyle);
        sw.createCell(i++, clearIntZero(map.get("fwql_hj")), intStyle);

        sw.createCell(i++, clearIntZero(map.get("ppfw_dzhg")), intStyle);
        sw.createCell(i++, clearIntZero(map.get("ppfw_tyzz")), intStyle);
        sw.createCell(i++, clearIntZero(map.get("ppfw_csgf")), intStyle);
        sw.createCell(i++, clearIntZero(map.get("ppfw_wfmbd")), intStyle);
        sw.createCell(i++, clearIntZero(map.get("ppfw_hj")), intStyle);

        sw.createCell(i++, clearIntZero(map.get("xtfw")), intStyle);
        sw.createCell(i++, clearIntZero(map.get("pxfw")), intStyle);
        sw.createCell(i++, clearIntZero(map.get("zpfw")), intStyle);
        sw.createCell(i++, clearIntZero(map.get("jyfw")), intStyle);
        sw.createCell(i++, clearIntZero(map.get("ldyw")), intStyle);
        sw.createCell(i++, clearIntZero(map.get("gpyw")), intStyle);

        sw.createCell(i++, map.get("cpTotalScore") == null ? "" : map.get("cpTotalScore").toString(), bodyStyle);

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

        //归属城市
        c = new ExcelColumnSet("2", "2");
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;

        c = new ExcelColumnSet("3","3");
        c.setWidth(10);
        columnSet.add(c);
        nColIndex ++;

        c = new ExcelColumnSet("4", "5");
        c.setWidth(10);
        columnSet.add(c);

        c = new ExcelColumnSet("6","6");
        c.setWidth(16);
        columnSet.add(c);
        nColIndex ++;

        c = new ExcelColumnSet("7", "7");
        c.setWidth(10);
        columnSet.add(c);

        c = new ExcelColumnSet("8", "8");
        c.setWidth(12);
        columnSet.add(c);

        c = new ExcelColumnSet("9", "9");
        c.setWidth(20);
        columnSet.add(c);

        c = new ExcelColumnSet("10", "10");
        c.setWidth(10);
        columnSet.add(c);

        c = new ExcelColumnSet("11", "11");
        c.setWidth(12);
        columnSet.add(c);

        c = new ExcelColumnSet("12", "12");
        c.setWidth(14);
        columnSet.add(c);

        c = new ExcelColumnSet("13", "13");
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
