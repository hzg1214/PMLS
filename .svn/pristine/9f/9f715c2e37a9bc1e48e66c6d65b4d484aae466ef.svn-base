package cn.com.eju.deal.poi;

import cn.com.eju.deal.common.util.DateUtils;
import cn.com.eju.deal.poi.common.ExcelColumnSet;
import cn.com.eju.deal.poi.common.ExcelStyleEnum;
import cn.com.eju.deal.poi.common.ExcelUseXmlAbstract;
import cn.com.eju.deal.poi.common.SpeedSheetWriter;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelForStoreDepositDeatil extends ExcelUseXmlAbstract {

    private short lockBlackStyle;
    private short bodyBlackStyle;
    private short moneyBlackStyle;
    public void downloadSheet(List<LinkedHashMap<String, Object>> list, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("保证金明细");

        Map<String, XSSFCellStyle> style = super.createStyles(wb);
        super.headerStyle = style.get(ExcelStyleEnum.HEADER.getValue()).getIndex();
        this.lockBlackStyle = style.get(ExcelStyleEnum.LOCKED.getValue()).getIndex();
        this.bodyBlackStyle = style.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();
        this.moneyBlackStyle = style.get(ExcelStyleEnum.MONEY_LOCKED_BLACK_RIGHT.getValue()).getIndex();
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
        nowColIndex = createBlock(sw, "城市", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "核算主体", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "门店编号", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "门店名称", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "门店所属中心", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "经纪公司编号", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "经纪公司", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "供应商编号", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "供应商名称", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "期初金额", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "本期新增", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "本期退款", 1, 1, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "期末余额", 1, 1, headerStyle, listMerge);
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

        sw.createCell(i++, index , lockStyle);
        sw.createCell(i++, map.get("cityName") == null ? "" : map.get("cityName").toString(), bodyStyle);
        sw.createCell(i++, map.get("accountProject") == null ? "" : map.get("accountProject").toString(), bodyStyle);
        sw.createCell(i++, map.get("storeNo") == null ? "" : map.get("storeNo").toString(), bodyStyle);
        sw.createCell(i++, map.get("storeName") == null ? "" : map.get("storeName").toString(), bodyStyle);
        sw.createCell(i++, map.get("groupName") == null ? "" : map.get("groupName").toString(), bodyStyle);
        sw.createCell(i++, map.get("companyNo") == null ? "" : map.get("companyNo").toString(), bodyStyle);
        sw.createCell(i++, map.get("companyName") == null ? "" : map.get("companyName").toString(), bodyStyle);
        sw.createCell(i++, map.get("providerCode") == null ? "" : map.get("providerCode").toString(), bodyStyle);
        sw.createCell(i++, map.get("providerName") == null ? "" : map.get("providerName").toString(), bodyStyle);

        sw.createCell(i++, clearZero(map.get("firstAmount")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("thisPeriodReceiveAmount")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("thisPeriodBackAmount")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("endAmount")), moneyStyle);
        
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

        //核算主体
        c = new ExcelColumnSet("3", "3");
        c.setWidth(18);
        columnSet.add(c);
        //门店、公司
        c = new ExcelColumnSet("4", "8");
        c.setWidth(16);
        columnSet.add(c);

        //供应商编号和名称
        c = new ExcelColumnSet("9", "9");
        c.setWidth(18);
        columnSet.add(c);
        c = new ExcelColumnSet("10", "10");
        c.setWidth(22);
        columnSet.add(c);

        //四个金额
        c = new ExcelColumnSet("11", "14");
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
