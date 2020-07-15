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

public class ExcelForNoSign extends ExcelUseXmlAbstract {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    private short bodyStyleBlack;
    private short dateStyleBlack;

    private XSSFSheet sheet;

    public void downloadSheet(List<LinkedHashMap<String, Object>> list, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook();
        sheet = wb.createSheet("未签门店明细");

        Map<String, XSSFCellStyle> style = super.createStyles(wb);
        super.headerStyle = style.get(ExcelStyleEnum.HEADER.getValue()).getIndex();
        this.bodyStyleBlack = style.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();
        this.dateStyleBlack = style.get(ExcelStyleEnum.DATE_BLACK.getValue()).getIndex();

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
        List<String> listMerge = new ArrayList<String>();
        int index = 0;
        index = this.createSheetHead(sw, index, listMerge);
        if (results != null && results.size() > 0) {
            for (LinkedHashMap<String, Object> map : results) {
                index = this.createSheetBody(sw, index, map);
            }
        }
        sw.endSheetWithMerge(listMerge);

//        // 自动调整列宽
//        if (nowColIndex > 0) {
//            for (int i = 47; i < nowColIndex - 1; i++) {
//                sheet.autoSizeColumn(i);
//            }
//        }
    }

    /**
     * 创建sheet头部内容
     */
    private int createSheetHead(SpeedSheetWriter sw, int index, List<String> listMerge) throws IOException {
        nowRowIndex = index;

        nowColIndex = 0;
        //List<String> listMerge=new  ArrayList<String>();
        //第一行
        sw.insertRow(nowRowIndex);
        nowColIndex = createBlock(sw, "序号", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "归属区域", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "归属城市", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "所在城市", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "归属中心", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "门店编号", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "门店名称", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "区域", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "详细地址", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "联系人", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "联系方式", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "经济人数", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "门店状态", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "连锁品牌", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "当前使用系统", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "当前店招品牌到期时间", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "主营商圈/板块", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "历史合同到期日", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "合同结束原因", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "创建人员", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "创建人员工号", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "维护人员", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "维护人员工号", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "门店所属中心", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "最后跟进日期", 1, 1, listMerge);
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

        short bodyStyle;
        short dateStyle;

        bodyStyle = bodyStyleBlack;
        dateStyle = dateStyleBlack;

        //序号
        sw.createCell(i++, index, bodyStyle);
        sw.createCell(i++, (String) map.get("regionName"), bodyStyle);
        sw.createCell(i++, (String) map.get("areaCityName"), bodyStyle);
        sw.createCell(i++, (String) map.get("cityGroupName"), bodyStyle);
        sw.createCell(i++, (String) map.get("centerGroupName"), bodyStyle);
        sw.createCell(i++, (String) map.get("storeNo"), bodyStyle);
        sw.createCell(i++, (String) map.get("storeName"), bodyStyle);
        sw.createCell(i++, (String) map.get("districtName"), bodyStyle);
        sw.createCell(i++, (String) map.get("addressDetail"), bodyStyle);
        sw.createCell(i++, (String) map.get("contacts"), bodyStyle);
        sw.createCell(i++, (String) map.get("mobilePhone"), bodyStyle);
        sw.createCell(i++, (String) map.get("storeScale"), bodyStyle);
        sw.createCell(i++, (String) map.get("storeStatus"), bodyStyle);
        sw.createCell(i++, (String) map.get("chainBrand"), bodyStyle);
        sw.createCell(i++, (String) map.get("nowUserSystem"), bodyStyle);
        setCreateDateCell("storeLeaseDueTime", i++, dateStyle, map, sw);
        sw.createCell(i++, (String) map.get("mainBusiness"), bodyStyle);
        setCreateDateCell("hisDueDate", i++, dateStyle, map, sw);
        sw.createCell(i++, (String) map.get("dueCause"), bodyStyle);
        sw.createCell(i++, (String) map.get("createName"), bodyStyle);
        sw.createCell(i++, (String) map.get("createNameNo"), bodyStyle);
        sw.createCell(i++, (String) map.get("maintainerName"), bodyStyle);
        sw.createCell(i++, (String) map.get("maintainerCode"), bodyStyle);
        sw.createCell(i++, (String) map.get("centerName"), bodyStyle);
        setCreateDateCell("dateFollowUpNew", i++, dateStyle, map, sw);
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
        c.setWidth(5);
        columnSet.add(c);
        nColIndex++;

        //归属区域
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //归属城市
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //所在城市
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //归属中心
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex++;

        //名称
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(28);
        columnSet.add(c);
        nColIndex++;

        //门店归属区域
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //地址
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(35);
        columnSet.add(c);
        nColIndex++;


        //联系人
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //联系方式
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex++;

        //经济人数
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;

        //门店状态
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;

        //连锁品牌
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;
        //使用系统
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;
        //门店到期时间
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(13);
        columnSet.add(c);
        nColIndex++;
        //板块
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(16);
        columnSet.add(c);
        nColIndex++;
        //合同到期时间
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(13);
        columnSet.add(c);
        nColIndex++;
        //合同结束原因
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;
        //合同结束原因
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //创建人
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;
        //创建工号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;
        //维护人员
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;
        //维护人员工号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;
        //门店所属中心
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //跟进日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(16);
        columnSet.add(c);
        nColIndex++;


        return columnSet;
    }

    /**
     * 创建日期型sheet
     */
    private void setCreateDateCell(String dateName, int i, short dateStyle, LinkedHashMap<String, Object> map, SpeedSheetWriter sw) throws Exception {

        Calendar cal = Calendar.getInstance();
        try {
            if (map.get(dateName) == null || "".equals(map.get(dateName).toString()) || "-".equals(map.get(dateName).toString())) {
                sw.createCell(i, "", dateStyle);
            } else {
                cal.setTime(DateUtils.parseDate((String) map.get(dateName)));
                sw.createCell(i, cal, dateStyle);
            }
        } catch (Exception e) {
            logger.error((String) map.get(dateName));
        }

    }
}

