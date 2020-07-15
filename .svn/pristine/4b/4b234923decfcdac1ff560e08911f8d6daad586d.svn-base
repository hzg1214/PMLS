package cn.com.eju.deal.poi;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.common.util.DateUtils;
import cn.com.eju.deal.poi.common.ExcelColumnSet;
import cn.com.eju.deal.poi.common.ExcelStyleEnum;
import cn.com.eju.deal.poi.common.ExcelUseXmlAbstract;
import cn.com.eju.deal.poi.common.SpeedSheetWriter;
import jdk.nashorn.internal.ir.ObjectNode;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Auther: xuliangliang
 * @Date: 2018\10\24 0024 11:07
 * @Description:
 */
public class ExcelStorePreserveSummary extends ExcelUseXmlAbstract {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    private String quarterId;
    private String[] qIdStr;
    public ExcelStorePreserveSummary(String quarterId){
        this.quarterId = quarterId;
        qIdStr = quarterId.split("','");
    }

    private short bodyStyleBlack;
    private short bodyStyleRed;
    private short dateStyleBlack;
    private short dateStyleRed;
    private short moneyStyleBlack;
    private short moneyStyleRed;
    private short bodyStyleBlackLeft;

    private XSSFSheet sheet;

    public void downloadSheet(List<LinkedHashMap<String, Object>> list, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook();
        sheet = wb.createSheet("维护门店汇总");

        Map<String, XSSFCellStyle> style = super.createStyles(wb);
        super.headerStyle = style.get(ExcelStyleEnum.HEADER.getValue()).getIndex();
        this.bodyStyleBlack = style.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();
        this.bodyStyleRed = style.get(ExcelStyleEnum.BODY_RED.getValue()).getIndex();
        this.dateStyleBlack = style.get(ExcelStyleEnum.DATE_BLACK.getValue()).getIndex();
        this.dateStyleRed = style.get(ExcelStyleEnum.DATE_RED.getValue()).getIndex();
        this.moneyStyleBlack = style.get(ExcelStyleEnum.MONEY_LOCKED_BLACK.getValue()).getIndex();
        this.moneyStyleRed = style.get(ExcelStyleEnum.MONEY_LOCKED_RED.getValue()).getIndex();
        this.bodyStyleBlackLeft = style.get(ExcelStyleEnum.BODY_BLACK_Left.getValue()).getIndex();

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
            int rowNum = 0;
            for (LinkedHashMap<String, Object> map : results) {
                rowNum = rowNum + 1;
                List<Map<String,String>> mapList = (List<Map<String,String>>)map.get("infoList");
                int mapListLength = mapList.size();
                listMerge.add("A" + (index + 1)  + ":A" + (index + mapListLength));
                if (qIdStr.length == 4){
                    listMerge.add("G" + (index + 1) + ":G" + (index + mapListLength));
                }
                if (qIdStr.length == 3){
                    listMerge.add("F" + (index + 1) + ":F" + (index + mapListLength));
                }
                if (qIdStr.length == 2){
                    listMerge.add("E" + (index + 1) + ":E" + (index + mapListLength));
                }
                if (qIdStr.length == 1){
                    listMerge.add("D" + (index + 1) + ":D" + (index + mapListLength));
                }

                index = this.createSheetBody(sw, index, map,rowNum);
            }
        }

        sw.endSheetWithMerge(listMerge);

        //自动调整列宽

//        for (int i = 0; i < 8; i++) {
//            sheet.autoSizeColumn(i);
//        }

    }

    /**
     * 创建sheet头部内容
     */
    private int createSheetHead(SpeedSheetWriter sw, int index, List<String> listMerge) throws IOException {
        nowRowIndex = index;

        nowColIndex = 0;
        //第一行
        sw.insertRow(nowRowIndex);
//        nowColIndex = createBlock(sw, "序号", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "科目", 2, 1, listMerge);
        if (quarterId.indexOf("Q1") != -1){
            nowColIndex = createBlock(sw, "Q1", 1, 1, listMerge);
        }
        if (quarterId.indexOf("Q2") != -1){
            nowColIndex = createBlock(sw, "Q2", 1, 1, listMerge);
        }
        if (quarterId.indexOf("Q3") != -1){
            nowColIndex = createBlock(sw, "Q3", 1, 1, listMerge);
        }
        if (quarterId.indexOf("Q4") != -1){
            nowColIndex = createBlock(sw, "Q4", 1, 1, listMerge);
        }
        nowColIndex = createBlock(sw, "类别", 1, 1, listMerge);
        sw.endRow();
        nowRowIndex = nowRowIndex + 1;

        return nowRowIndex;
    }


    /**
     * 创建sheet具体内容
     */
    private int createSheetBody(SpeedSheetWriter sw, int index, LinkedHashMap<String, Object> map,Integer rowNum) throws Exception {



        short bodyStyle;
        short dateStyle;
        short moneyStyle;
        short bodyLeftStyle;
        List<Map<String,String>> list = (List<Map<String,String>>)map.get("infoList");
        bodyStyle = bodyStyleBlack;
        dateStyle = dateStyleBlack;
        moneyStyle = moneyStyleBlack;
        bodyLeftStyle = bodyStyleBlackLeft;
//        }
        int j = 0;
        for (Map<String,String> mapInfo:list) {

            int i = 0;
            sw.insertRow(index);
            //序号
//            sw.createCell(i++, clearNull(rowNum), bodyStyle);
            //-- 维护信息 --
            sw.createCell(i++, clearNull(map.get("name")),bodyStyle);
            sw.createCell(i++, clearNull(mapInfo.get("accountName").replaceAll(" ","-")),bodyLeftStyle);
            if (j == 0){
                if (quarterId.indexOf("Q1") != -1){
                    sw.createCell(i++,numDouble(list.get(11).get("q1"),list.get(1).get("q1")) ,bodyStyle);
                }
                if (quarterId.indexOf("Q2") != -1){
                    sw.createCell(i++,numDouble(list.get(11).get("q2"),list.get(1).get("q2")) ,bodyStyle);
                }
                if (quarterId.indexOf("Q3") != -1){
                    sw.createCell(i++,numDouble(list.get(11).get("q3"),list.get(1).get("q3")) ,bodyStyle);
                }
                if (quarterId.indexOf("Q4") != -1){
                    sw.createCell(i++,numDouble(list.get(11).get("q4"),list.get(1).get("q4")) ,bodyStyle);
                }

            }else{
                if (quarterId.indexOf("Q1") != -1){
                    sw.createCell(i++, numInteger(mapInfo.get("q1")),bodyStyle);
                }
                if (quarterId.indexOf("Q2") != -1){
                    sw.createCell(i++, numInteger(mapInfo.get("q2")),bodyStyle);
                }
                if (quarterId.indexOf("Q3") != -1){
                    sw.createCell(i++, numInteger(mapInfo.get("q3")),bodyStyle);
                }
                if (quarterId.indexOf("Q4") != -1){
                    sw.createCell(i++, numInteger(mapInfo.get("q4")),bodyStyle);
                }
            }
            sw.createCell(i++, clearNull(map.get("orgType")),bodyStyle);
            sw.endRow();
            index++;
            j++;
        }
        return index;
    }


    /**
     * 设置列宽
     */
    public List<ExcelColumnSet> setColumnWidth() {
        List<ExcelColumnSet> columnSet = new ArrayList<>();
        ExcelColumnSet c;

        int nColIndex = 1;


        //区域
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
        columnSet.add(c);
        nColIndex++;

        //科目
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 6));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex++;;

        //Q1
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex++;
        //q2
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 1));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //q3
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //q4
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        return columnSet;
    }

    public String clearNull(Object value) {

        return value == null ? "-" : value.toString();
    }



    public static String numInteger(String value){

        if (value == null || "0.000000".equals(value)){
            return "-";
        }
        DecimalFormat df=new DecimalFormat(",###,##0");
        return df.format(Math.round(Double.valueOf(value)));
    }

    public static String numDouble(String value1,String value2){
        if (value1 == null || value2 == null || "0.000000".equals(value2)){
            return "-";
        }
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(Double.valueOf(value1)/Double.valueOf(value2) * 100)+"%";

    }

}

