package cn.com.eju.deal.poi;

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

import cn.com.eju.deal.poi.common.ExcelColumnSet;
import cn.com.eju.deal.poi.common.ExcelStyleEnum;
import cn.com.eju.deal.poi.common.ExcelUseXmlAbstract;
import cn.com.eju.deal.poi.common.SpeedSheetWriter;

/**
 * 
 * desc: 门店信息明细表 excel
 * @author :zhenggang.Huang
 * @date   :2019年1月4日
 */
public class ExcelForStoreInformationDetail extends ExcelUseXmlAbstract {

    private short bodyBlackStyle;
    private short moneyBlackStyle;
    private short intBlackStyle;

    public void downloadSheet(List<LinkedHashMap<String, Object>> list, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("门店信息明细表");

        Map<String, XSSFCellStyle> style = super.createStyles(wb);
        super.headerStyle = style.get(ExcelStyleEnum.HEADER.getValue()).getIndex();
        this.bodyBlackStyle = style.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();
        this.moneyBlackStyle = style.get(ExcelStyleEnum.MONEY_LOCKED_BLACK.getValue()).getIndex();
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
        sw.beginSheet(setColumnWidth(), 0, 1, "A4");
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
        nowColIndex = createBlock(sw, "归属城市", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "所属中心", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "门店城市", 1, 1, listMerge);
        nowColIndex = createBlock(sw,"门店编号",1,1,listMerge);
        nowColIndex = createBlock(sw,"门店名称",1,1,listMerge);
        nowColIndex = createBlock(sw,"门店地址",1,1,listMerge);
        nowColIndex = createBlock(sw,"经营场所",1,1,listMerge);
        nowColIndex = createBlock(sw,"门店规模",1,1,listMerge);
        nowColIndex = createBlock(sw,"维护人员",1,1,listMerge);
        nowColIndex = createBlock(sw,"维护人员工号",1,1,listMerge);
        nowColIndex = createBlock(sw,"合作模式",1,1,listMerge);
        nowColIndex = createBlock(sw,"公司编号",1,1,listMerge);
        nowColIndex = createBlock(sw,"所属公司",1,1,listMerge);
        nowColIndex = createBlock(sw,"创建人",1,1,listMerge);
        nowColIndex = createBlock(sw,"创建时间",1,1,listMerge);

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
        short moneyStyle = moneyBlackStyle;
        short intStyle = intBlackStyle;
        sw.createCell(i++, map.get("acCityName") == null ? "" : map.get("acCityName").toString(), bodyStyle);
        sw.createCell(i++, map.get("centerName") == null ? "" : map.get("centerName").toString(), bodyStyle);
        sw.createCell(i++, map.get("cityName") == null ? "" : map.get("cityName").toString(), bodyStyle);
        sw.createCell(i++, map.get("storeNo") == null ? "" : map.get("storeNo").toString(), bodyStyle);
        sw.createCell(i++, map.get("storeName") == null ? "" : map.get("storeName").toString(), bodyStyle);
        sw.createCell(i++, map.get("addressDetail") == null ? "" : map.get("addressDetail").toString(), bodyStyle);
        sw.createCell(i++, map.get("bussinessPlace") == null ? "" : map.get("bussinessPlace").toString(), bodyStyle);
        sw.createCell(i++, map.get("storeSizeScale") == null ? "" : map.get("storeSizeScale").toString(), bodyStyle);
        sw.createCell(i++, map.get("maintainerName") == null ? "" : map.get("maintainerName").toString(), bodyStyle);
        sw.createCell(i++, map.get("maintainer") == null ? "" : map.get("maintainer").toString(), bodyStyle);
        sw.createCell(i++, map.get("contractTypeName") == null ? "" : map.get("contractTypeName").toString(), bodyStyle);
        sw.createCell(i++, map.get("companyNo") == null ? "" : map.get("companyNo").toString(), bodyStyle);
        sw.createCell(i++, map.get("companyName") == null ? "" : map.get("companyName").toString(), bodyStyle);
        sw.createCell(i++, map.get("userName") == null ? "" : map.get("userName").toString(), bodyStyle);
        sw.createCell(i++, map.get("dateCreate") == null ? "" : map.get("dateCreate").toString(), bodyStyle);

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

        //归属城市
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex ++;
        //所属中心
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex ++;
        //门店城市
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex ++;
        //门店编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex ++;
        //门店名称
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(25);
        columnSet.add(c);
        nColIndex ++;
        //门店地址
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(30);
        columnSet.add(c);
        nColIndex ++;
        //经营场所
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex ++;
        //门店规模
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex ++;
        //维护人员
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex ++;
        //维护人员工号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex ++;
        //合作模式
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex ++;
        //公司编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex ++;
        //所属公司
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(25);
        columnSet.add(c);
        nColIndex ++;
        //创建人
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex ++;
        //创建时间
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(15);
        columnSet.add(c);
        nColIndex ++;

        return columnSet;
    }

}
