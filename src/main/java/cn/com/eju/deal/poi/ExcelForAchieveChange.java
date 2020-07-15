package cn.com.eju.deal.poi;

import cn.com.eju.deal.base.helper.LogHelper;
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

public class ExcelForAchieveChange extends ExcelUseXmlAbstract {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

	private short bodyBlackStyle;
    private short bodyRedStyle;
    private short dateBlackStyle;
    private short dateRedStyle;

    public void downloadSheet(List<LinkedHashMap<String, Object>> list, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("业绩变更");
        /*sheet.autoSizeColumn(1,true);*/
        Map<String, XSSFCellStyle> style = super.createStyles(wb);
        super.headerStyle = style.get(ExcelStyleEnum.HEADER.getValue()).getIndex();
        
        this.bodyBlackStyle = style.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();
        this.bodyRedStyle = style.get(ExcelStyleEnum.BODY_RED.getValue()).getIndex();
        this.dateBlackStyle = style.get(ExcelStyleEnum.DATE_BLACK.getValue()).getIndex();
        this.dateRedStyle = style.get(ExcelStyleEnum.DATE_RED.getValue()).getIndex();
        
        
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
        sw.beginSheet(setColumnWidth(), 0, 1, "A3");
        //sw.createCell(columnIndex, value);
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

        nowColIndex = createBlock(sw, "变更类型", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "门店编号", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "门店名称", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "变更前", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "变更后", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "变更时间", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "操作人", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "变更原因", 1, 1, listMerge);
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
        short bodyStyle = "维护人".equals(map.get("type").toString()) ? bodyRedStyle : bodyBlackStyle;
        short dateStyle = "维护人".equals(map.get("type").toString()) ? dateRedStyle : dateBlackStyle ;
        //short bodyStyle = bodyBlackStyle;
        //short dateStyle = dateBlackStyle;
        sw.createCell(i++, map.get("type") == "M" ? "交易中心" : "维护人", bodyStyle);
        sw.createCell(i++, map.get("storeNo") == null ? "" : map.get("storeNo").toString(), bodyStyle);
        sw.createCell(i++, map.get("name") == null ? "" : map.get("name").toString(), bodyStyle);
        sw.createCell(i++, map.get("changebefore") == null ? "" : map.get("changebefore").toString(), bodyStyle);
        sw.createCell(i++, map.get("changeafter") == null ? "" : map.get("changeafter").toString(), bodyStyle);
        setCreateDateCell("dateCreate", i++, dateStyle, map, sw);
        sw.createCell(i++, map.get("usercreate") == null ? "" : map.get("usercreate").toString(), bodyStyle);
        sw.createCell(i++, map.get("reason") == null ? "" : map.get("reason").toString(), bodyStyle);
        
        
       /* String type ;
        type = map.get("type").equals("M")?"维护人":"交易中心";
        sw.createCell(i++, type, bodyStyle);
        sw.createCell(i++, this.isEmpy(map.get("storeNo")), bodyStyle);
        sw.createCell(i++, this.isEmpy(map.get("name")), bodyStyle);
        sw.createCell(i++, this.isEmpy(map.get("changebefore")), bodyStyle);
        sw.createCell(i++, this.isEmpy(map.get("changeafter")), bodyStyle);
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtils.parseDate(map.get("dateCreate").toString()));
        sw.createCell(i++, cal, dateStyle);

        sw.createCell(i++, this.isEmpy(map.get("usercreate")), bodyStyle);
        sw.createCell(i++, this.isEmpy(map.get("reason")), bodyStyle);*/
        sw.endRow();
        index++;

        return index;
    }


    /**
     * 设置列宽
     */
    public List<ExcelColumnSet> setColumnWidth() {
        List<ExcelColumnSet> columnSet = new ArrayList<>();
        /*ExcelColumnSet c = new ExcelColumnSet("1", "34");
        c.setWidth(18);
        
        columnSet.add(c);*/
        
        ExcelColumnSet c;
        int nColIndex = 1;
        //变更类型
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;
        
        //门店编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(13);
        columnSet.add(c);
        nColIndex++;
        
        //门店名称
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(25);
        columnSet.add(c);
        nColIndex++;
        
        //变更前
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(13);
        columnSet.add(c);
        nColIndex++;
        
       //变更后
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(13);
        columnSet.add(c);
        nColIndex++;
        
        //变更时间
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(13);
        columnSet.add(c);
        nColIndex++;
        
        //操作人
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 1));
        c.setWidth(13);
        columnSet.add(c);
        nColIndex++;
        
        //操作人
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 1));
        c.setWidth(35);
        columnSet.add(c);
        nColIndex++;
        
        return columnSet;
    }
    private String isEmpy(Object obj){
        return obj == null?"":obj.toString();
    }
    /**
     * 创建日期型sheet
     */
    private void setCreateDateCell(String dateName ,int i,short dateStyle, LinkedHashMap<String, Object> map,SpeedSheetWriter sw) throws Exception {

        Calendar cal = Calendar.getInstance();

        try{
            if (map.get(dateName) == null || "".equals(map.get(dateName).toString()) || "-".equals(map.get(dateName).toString())) {
                sw.createCell(i, "-", dateStyle);
            } else {
                cal.setTime(DateUtils.parseDate(String.valueOf(map.get(dateName))));
                sw.createCell(i, cal, dateStyle);
            }
        }catch(Exception e){
            logger.error(String.valueOf(map.get(dateName)));
        }

    }
}
