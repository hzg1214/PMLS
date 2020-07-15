package cn.com.eju.deal.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.common.util.DateUtils;
import cn.com.eju.deal.poi.common.ExcelColumnSet;
import cn.com.eju.deal.poi.common.ExcelStyleEnum;
import cn.com.eju.deal.poi.common.ExcelUseXmlAbstract;
import cn.com.eju.deal.poi.common.SpeedSheetWriter;

public class ExcelForPerformanceQuery extends ExcelUseXmlAbstract {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    private short bodyBlackStyle;
    private short bodyBlackStyle_bold;
    private short moneyBlackStyle;
    private short moneyBlackStyle_bold;
    private short intBlackStyle;
    private short intBlackStyle_bold;
    private short dateBlackStyle;
    private short dateBlackStyle_bold;
    private String dimension;


    public void downloadSheet(List<LinkedHashMap<String, Object>> list, File file, String dimension) throws IOException {
        OutputStream os = new FileOutputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("业绩查询");

        Map<String, XSSFCellStyle> style = super.createStyles(wb);
        super.headerStyle = style.get(ExcelStyleEnum.HEADER.getValue()).getIndex();
        this.bodyBlackStyle = style.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();
        this.bodyBlackStyle_bold = style.get(ExcelStyleEnum.BODY_BLACK_BOLD.getValue()).getIndex();

        this.moneyBlackStyle = style.get(ExcelStyleEnum.MONEY_UNLOCKED_BLACK.getValue()).getIndex();
        this.moneyBlackStyle_bold = style.get(ExcelStyleEnum.MONEY_UNLOCKED_BLACK_BOLD.getValue()).getIndex();

        this.intBlackStyle = style.get(ExcelStyleEnum.INT_BLACK.getValue()).getIndex();
        this.intBlackStyle_bold = style.get(ExcelStyleEnum.INT_BLACK_BOLD.getValue()).getIndex();

        this.dateBlackStyle = style.get(ExcelStyleEnum.DATE_BLACK.getValue()).getIndex();
        this.dateBlackStyle_bold = style.get(ExcelStyleEnum.DATE_BLACK_BOLD.getValue()).getIndex();

        this.dimension = dimension;

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
        nowColIndex = createBlock(sw, "归属区域", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "归属城市", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "所在城市", 1, 2, listMerge);
        if (!dimension.equals("city")) {
            nowColIndex = createBlock(sw, "中心", 1, 2, listMerge);
        }
        if (!dimension.equals("store")) {
            if (dimension.equals("person")) {
                nowColIndex = createBlock(sw, "姓名", 1, 2, listMerge);
                nowColIndex = createBlock(sw, "工号", 1, 2, listMerge);
            }
            nowColIndex = createBlock(sw, "维护门店", 3, 1, listMerge);
            nowColIndex = createBlock(sw, "拓展", 6, 1, listMerge);
        } else {
            nowColIndex = createBlock(sw, "门店编号", 1, 2, listMerge);
            nowColIndex = createBlock(sw, "门店地址", 1, 2, listMerge);
            nowColIndex = createBlock(sw, "合同到期日期", 1, 2, listMerge);
        }

        nowColIndex = createBlock(sw, "交易", 4, 1, listMerge);
        nowColIndex = createBlock(sw, "联动", 6, 1, listMerge);
        sw.endRow();

        //第二行
        sw.insertRow(++nowRowIndex);
        nowColIndex = 0;
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        if (!dimension.equals("city")) {
            nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        }
        if (!dimension.equals("store")) {
            if (dimension.equals("person")) {
                nowColIndex = createBlock(sw, "", 1, 0, listMerge);
                nowColIndex = createBlock(sw, "", 1, 0, listMerge);
            }
            nowColIndex = createBlock(sw, "门店数", 1, 1, listMerge);
            nowColIndex = createBlock(sw, "签约B", 1, 1, listMerge);
            nowColIndex = createBlock(sw, "翻牌验收", 1, 1, listMerge);

            nowColIndex = createBlock(sw, "草签B", 1, 1, listMerge);
            nowColIndex = createBlock(sw, "签约B", 1, 1, listMerge);
            nowColIndex = createBlock(sw, "甲类", 1, 1, listMerge);
            nowColIndex = createBlock(sw, "装修完成", 1, 1, listMerge);
            nowColIndex = createBlock(sw, "翻牌验收", 1, 1, listMerge);
            nowColIndex = createBlock(sw, "续签", 1, 1, listMerge);
        } else {
            nowColIndex = createBlock(sw, "", 1, 0, listMerge);
            nowColIndex = createBlock(sw, "", 1, 0, listMerge);
            nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        }

        nowColIndex = createBlock(sw, "报单", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "网签审核", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "不动产受理", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "办结", 1, 1, listMerge);

        nowColIndex = createBlock(sw, "报备", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "带看", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "大定套数", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "大定金额", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "成销套数", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "成销金额", 1, 1, listMerge);
        sw.endRow();

        nowRowIndex = nowRowIndex + 1;
        return nowRowIndex;
    }

    /**
     * 创建sheet具体内容
     */
    private int createSheetBody(SpeedSheetWriter sw, int index, LinkedHashMap<String, Object> map) throws Exception {
        short bodyBlackStyle;
        short moneyBlackStyle;
        short intBlackStyle;
        short dateBlackStyle;
        if (index == 2) {
            bodyBlackStyle = this.bodyBlackStyle_bold;
            moneyBlackStyle = this.moneyBlackStyle_bold;
            intBlackStyle = this.intBlackStyle_bold;
            dateBlackStyle = this.dateBlackStyle_bold;
        } else {
            bodyBlackStyle = this.bodyBlackStyle;
            moneyBlackStyle = this.moneyBlackStyle;
            intBlackStyle = this.intBlackStyle;
            dateBlackStyle = this.dateBlackStyle;
        }

        sw.insertRow(index);
        int i = 0;

        sw.createCell(i++, map.get("regionName") == null ? "" : map.get("regionName").toString(), bodyBlackStyle);
        sw.createCell(i++, map.get("areaCityName") == null ? "" : map.get("areaCityName").toString(), bodyBlackStyle);
        sw.createCell(i++, map.get("cityName") == null ? "" : map.get("cityName").toString(), bodyBlackStyle);
        if (!dimension.equals("city")) {
            sw.createCell(i++, map.get("centerName") == null ? "" : map.get("centerName").toString(), bodyBlackStyle);
        }

        if (!dimension.equals("store")) {
            if (dimension.equals("person")) {
                sw.createCell(i++, map.get("maintainerName") == null ? "" : map.get("maintainerName").toString(), bodyBlackStyle);
                sw.createCell(i++, map.get("maintainerCode") == null ? "" : map.get("maintainerCode").toString(), bodyBlackStyle);
            }

            sw.createCell(i++, clearIntZero(map.get("mtcStoreNum")), intBlackStyle);
            sw.createCell(i++, clearIntZero(map.get("mtcSignBNum")), intBlackStyle);
            sw.createCell(i++, clearIntZero(map.get("mtcFlopPassNum")), intBlackStyle);

            sw.createCell(i++, clearIntZero(map.get("expInitBNum")), intBlackStyle);
            sw.createCell(i++, clearIntZero(map.get("expSignBNum")), intBlackStyle);
            sw.createCell(i++, clearIntZero(map.get("expClassANum")), intBlackStyle);
            sw.createCell(i++, clearIntZero(map.get("expFlopCompleteNum")), intBlackStyle);
            sw.createCell(i++, clearIntZero(map.get("expFlopPassNum")), intBlackStyle);
            sw.createCell(i++, clearIntZero(map.get("expRenewNum")), intBlackStyle);
        } else {
            sw.createCell(i++, map.get("storeNo") == null ? "" : map.get("storeNo").toString(), bodyBlackStyle);
            sw.createCell(i++, map.get("storeAddress") == null ? "" : map.get("storeAddress").toString(), bodyBlackStyle);
            setCreateDateCell("dateLifeEnd", i++, dateBlackStyle, map, sw);
        }

        sw.createCell(i++, clearIntZero(map.get("excReportNum")), intBlackStyle);
        sw.createCell(i++, clearIntZero(map.get("excNetSignNum")), intBlackStyle);
        sw.createCell(i++, clearIntZero(map.get("excEstateAcptNum")), intBlackStyle);
        sw.createCell(i++, clearIntZero(map.get("excHandleEndNum")), intBlackStyle);

        sw.createCell(i++, clearIntZero(map.get("lnkReportNum")), intBlackStyle);
        sw.createCell(i++, clearIntZero(map.get("lnkSeeNum")), intBlackStyle);
        sw.createCell(i++, clearIntZero(map.get("lnkRoughNum")), intBlackStyle);
        sw.createCell(i++, clearZero(map.get("lnkRoughAmount")), moneyBlackStyle);
        sw.createCell(i++, clearIntZero(map.get("lnkDealNum")), intBlackStyle);
        sw.createCell(i++, clearZero(map.get("lnkDealAmount")), moneyBlackStyle);

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

        //城市
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 2));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex = nColIndex + 3;

        if (!dimension.equals("city")) {
            c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
            c.setWidth(12);
            columnSet.add(c);
            nColIndex++;
        }

        if (dimension.equals("store")) {
            c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
            c.setWidth(9);
            columnSet.add(c);
            nColIndex++;

            c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
            c.setWidth(35);
            columnSet.add(c);
            nColIndex++;

            c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
            c.setWidth(10);
            columnSet.add(c);
            nColIndex++;
        }
        else if (dimension.equals("person")) {
            c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 1));
            c.setWidth(8);
            columnSet.add(c);
            nColIndex = nColIndex + 2;
        }

        //非门店
        if (!dimension.equals("store")) {
            //维护
            c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 2));
            c.setWidth(9);
            columnSet.add(c);
            nColIndex = nColIndex + 3;

            //拓展
            c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 5));
            c.setWidth(9);
            columnSet.add(c);
            nColIndex = nColIndex + 6;

        }

        //交易
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 3));
        c.setWidth(9);
        columnSet.add(c);
        nColIndex = nColIndex + 4;

        //联动
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 2));
        c.setWidth(9);
        columnSet.add(c);
        nColIndex = nColIndex + 3;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex++;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(9);
        columnSet.add(c);
        nColIndex++;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex++;

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

    /**
     * 创建日期型sheet
     */
    private void setCreateDateCell(String dateName ,int i,short dateStyle, LinkedHashMap<String, Object> map,SpeedSheetWriter sw) throws Exception {

        Calendar cal = Calendar.getInstance();

        try {
            if (map.get(dateName) == null || "".equals(map.get(dateName).toString()) || "-".equals(map.get(dateName).toString())) {
                sw.createCell(i, "-", dateStyle);
            } else {
                cal.setTime(DateUtils.parseDate(String.valueOf(map.get(dateName))));
                sw.createCell(i, cal, dateStyle);
            }
        } catch (Exception e) {
            logger.error(String.valueOf(map.get(dateName)));
        }
    }
}
