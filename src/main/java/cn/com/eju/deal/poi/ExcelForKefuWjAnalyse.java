package cn.com.eju.deal.poi;

import cn.com.eju.deal.common.util.DateUtils;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.kefuWj.KeFuWjAnalyseDto;
import cn.com.eju.deal.dto.kefuWj.WjAnalyseFlScoreDto;
import cn.com.eju.deal.dto.kefuWj.WjAnalyseTmScoreDto;
import cn.com.eju.deal.poi.common.ExcelColumnSet;
import cn.com.eju.deal.poi.common.ExcelStyleEnum;
import cn.com.eju.deal.poi.common.ExcelUseXmlAbstract;
import cn.com.eju.deal.poi.common.SpeedSheetWriter;
import com.alibaba.fastjson.JSON;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by eju on 2019/7/15.
 */
public class ExcelForKefuWjAnalyse extends ExcelUseXmlAbstract {

    private short lockBlackStyle;
    private short bodyBlackStyle;
    private short dateBlackStyle;
    private short moneyBlackStyle;
    private short doubleBlackStyle;
    private String wjCode;
    public ExcelForKefuWjAnalyse(String wjCode){
        this.wjCode = wjCode;
    }
    public void downloadSheet(List<LinkedHashMap<String, Object>> list, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("满意度问卷统计表");

        Map<String, XSSFCellStyle> style = super.createStyles(wb);
        super.headerStyle = style.get(ExcelStyleEnum.HEADER.getValue()).getIndex();
        this.lockBlackStyle = style.get(ExcelStyleEnum.LOCKED.getValue()).getIndex();
        this.bodyBlackStyle = style.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();
        this.dateBlackStyle = style.get(ExcelStyleEnum.DATE_BLACK.getValue()).getIndex();
        this.moneyBlackStyle = style.get(ExcelStyleEnum.MONEY_LOCKED_BLACK_RIGHT.getValue()).getIndex();
        this.doubleBlackStyle = style.get(ExcelStyleEnum.DOUBLE_BLACK.getValue()).getIndex();
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
        sw.beginSheet(setColumnWidth(), 7, 2, "H3");
        int index = 0;
        List<String> listMerge = new ArrayList<>();
        index = this.createSheetHead(sw, index, listMerge,results);
        if (results != null && results.size() > 0) {
            for (LinkedHashMap<String, Object> map : results) {
                index = this.createSheetBody(sw, index, map,(List<LinkedHashMap<String, Object>>)results.get(0).get("wjtmList"));
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
        nowColIndex = createBlock(sw, "归属城市", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "门店所在城市", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "所属中心", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "公司编号", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "公司名称", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "门店编号", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "门店名称", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "门店地址", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "门店负责人", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "联系人电话", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "验证码接收手机号码", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "创建时间", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "门店现状", 1, 2, headerStyle, listMerge);
        nowColIndex = createBlock(sw, "问卷状态", 1, 2, headerStyle, listMerge);
        //获取问卷所包含的分类及分类下的题目总和（+1栏合计）
        List<LinkedHashMap<String, Object>> list = (List<LinkedHashMap<String, Object>>)results.get(0).get("wjtmflList");
        if (list != null && list.size() > 0) {
            for (LinkedHashMap<String, Object> map : list) {
                nowColIndex = createBlock(sw, map.get("dicValue").toString(), Integer.valueOf(map.get("flCount").toString())+1, 1, headerStyle, listMerge);
            }
        }
        nowColIndex = createBlock(sw, "合计", 1, 2, headerStyle, listMerge);
        sw.endRow();
        //第二行
        sw.insertRow(++nowRowIndex);
        nowColIndex = 0;
        nowColIndex = createBlock(sw, "归属城市", 1, 1, headerStyle, null);
        nowColIndex = createBlock(sw, "门店所在城市", 1, 1, headerStyle, null);
        nowColIndex = createBlock(sw, "所属中心", 1, 1, headerStyle, null);
        nowColIndex = createBlock(sw, "公司编号", 1, 1, headerStyle, null);
        nowColIndex = createBlock(sw, "公司名称", 1, 1, headerStyle, null);
        nowColIndex = createBlock(sw, "门店编号", 1, 1, headerStyle, null);
        nowColIndex = createBlock(sw, "门店名称", 1, 1, headerStyle, null);
        nowColIndex = createBlock(sw, "门店地址", 1, 1, headerStyle, null);
        nowColIndex = createBlock(sw, "门店负责人", 1, 1, headerStyle, null);
        nowColIndex = createBlock(sw, "联系人电话", 1, 1, headerStyle, null);
        nowColIndex = createBlock(sw, "验证码接收手机号码", 1, 1, headerStyle, null);
        nowColIndex = createBlock(sw, "创建时间", 1, 1, headerStyle, null);
        nowColIndex = createBlock(sw, "门店现状", 1, 1, headerStyle, null);
        nowColIndex = createBlock(sw, "问卷状态", 1, 1, headerStyle, null);
        //获取题目
        List<LinkedHashMap<String, Object>> list2 = (List<LinkedHashMap<String, Object>>)results.get(0).get("wjtmList");
        if (list2 != null && list2.size() > 0) {
            String wjFlType = null;
            for (LinkedHashMap<String, Object> map : list2) {
                //如果为null表示第一次进来，不比较分类type
                if(wjFlType == null){
                    nowColIndex = createBlock(sw, map.get("wjtmValue").toString(), 1, 1, headerStyle, listMerge);
                    wjFlType = map.get("wjtmflType").toString();
                }else{
                    //比较分类type是否与之前一致，一致则不给wjFlType赋值，不一致则增加小计一栏
                    if(wjFlType.equals(map.get("wjtmflType").toString())){
                        nowColIndex = createBlock(sw, map.get("wjtmValue").toString(), 1, 1, headerStyle, listMerge);
                    }else{
                        nowColIndex = createBlock(sw, "小计", 1, 1, null);
                        nowColIndex = createBlock(sw, map.get("wjtmValue").toString(), 1, 1, headerStyle, listMerge);
                        wjFlType = map.get("wjtmflType").toString();
                    }
                }
            }
            //为最后一个分类增加小计
            nowColIndex = createBlock(sw, "小计", 1, 1, null);
        }
        nowColIndex = createBlock(sw, "合计", 1, 1, null);

        sw.endRow();

        nowRowIndex = nowRowIndex + 1;

        return nowRowIndex;
    }


    /**
     * 创建sheet具体内容
     */
    private int createSheetBody(SpeedSheetWriter sw, int index, LinkedHashMap<String, Object> map, List<LinkedHashMap<String, Object>> wjtmList) throws Exception {
        sw.insertRow(index);
        int i = 0;

        short lockStyle = lockBlackStyle;
        short bodyStyle = bodyBlackStyle;
        short dateStyle = dateBlackStyle;
        short moneyStyle = moneyBlackStyle;
        short doubleBlack = doubleBlackStyle;

        KeFuWjAnalyseDto keFuWjAnalyseDto = JSON.parseObject(JSON.toJSONString(map), KeFuWjAnalyseDto.class);

        sw.createCell(i++, keFuWjAnalyseDto.getAcCityName() == null ? "" : keFuWjAnalyseDto.getAcCityName(), bodyStyle);
        sw.createCell(i++, keFuWjAnalyseDto.getCityName() == null ? "" : keFuWjAnalyseDto.getCityName(), bodyStyle);
        sw.createCell(i++, keFuWjAnalyseDto.getCenterName() == null ? "" : keFuWjAnalyseDto.getCenterName(), bodyStyle);
        sw.createCell(i++, keFuWjAnalyseDto.getCompanyNo() == null ? "" : keFuWjAnalyseDto.getCompanyNo(), bodyStyle);
        sw.createCell(i++, keFuWjAnalyseDto.getCompanyName() == null ? "" : keFuWjAnalyseDto.getCompanyName(), bodyStyle);
        sw.createCell(i++, keFuWjAnalyseDto.getStoreNo() == null ? "" : keFuWjAnalyseDto.getStoreNo(), bodyStyle);
        sw.createCell(i++, keFuWjAnalyseDto.getStoreName() == null ? "" : keFuWjAnalyseDto.getStoreName(), bodyStyle);
        sw.createCell(i++, keFuWjAnalyseDto.getStoreAddress() == null ? "" : keFuWjAnalyseDto.getStoreAddress(), bodyStyle);
        sw.createCell(i++, keFuWjAnalyseDto.getStoreManager() == null ? "" : keFuWjAnalyseDto.getStoreManager(), bodyStyle);
        sw.createCell(i++, keFuWjAnalyseDto.getStoreManagerPhone() == null ? "-" : keFuWjAnalyseDto.getStoreManagerPhone(), bodyStyle);
        sw.createCell(i++, keFuWjAnalyseDto.getWjfkTel() == null ? "-" : keFuWjAnalyseDto.getWjfkTel(), bodyStyle);
        setCreateDateCell(keFuWjAnalyseDto.getDateCreate(), i++, dateStyle, sw);
        sw.createCell(i++, keFuWjAnalyseDto.getStoreStatusName() == null ? "" : keFuWjAnalyseDto.getStoreStatusName(), bodyStyle);
        if(keFuWjAnalyseDto.getWjType().equals("0")){
            sw.createCell(i++, "未完成", bodyStyle);
        }else if(keFuWjAnalyseDto.getWjType().equals("1")){
            sw.createCell(i++, "部分完成", bodyStyle);
        }if(keFuWjAnalyseDto.getWjType().equals("2")){
            sw.createCell(i++, "已完成", bodyStyle);
        }
        //获取问卷题目
        if(keFuWjAnalyseDto.getWjAnalyseTmScores()!=null&&keFuWjAnalyseDto.getWjAnalyseTmScores().size()>0){
            //定义一个变量，用来记录分类应取第几个
            int j = 0;
            //定义分类类型
            String wjflType = null;
            for(WjAnalyseTmScoreDto wjAnalyseTmScoreDto : keFuWjAnalyseDto.getWjAnalyseTmScores()){
                //如果分类等于null，表示第一次进，直接获取题目，并把分类置为此题分类
                if(wjflType == null){
                    sw.createCell(i++, clearZeroTwo(wjAnalyseTmScoreDto.getWjtmScore().toString()), doubleBlack);
                    wjflType = wjAnalyseTmScoreDto.getWjtmflType();
                }else{
                    //如果分类等于此题分类，则直接获取题目
                    if(wjflType.equals(wjAnalyseTmScoreDto.getWjtmflType())){
                        sw.createCell(i++, clearZeroTwo(wjAnalyseTmScoreDto.getWjtmScore().toString()), doubleBlack);
                    }else{
                        //不等于则增加小计，然后增加题目
                        WjAnalyseFlScoreDto wjAnalyseFlScoreDto = keFuWjAnalyseDto.getWjAnalyseFlScores().get(j);
                        sw.createCell(i++, clearZeroTwo(wjAnalyseFlScoreDto.getWjflScore().toString()), doubleBlack);
                        j++;
                        wjflType = wjAnalyseTmScoreDto.getWjtmflType();
                        sw.createCell(i++, clearZeroTwo(wjAnalyseTmScoreDto.getWjtmScore().toString()), doubleBlack);
                    }
                }
            }
            //增加最后一个分类的小计
            WjAnalyseFlScoreDto wjAnalyseFlScoreDto = keFuWjAnalyseDto.getWjAnalyseFlScores().get(j);
            sw.createCell(i++, clearZeroTwo(wjAnalyseFlScoreDto.getWjflScore().toString()), doubleBlack);
        }else{
            //为未完成的门店
            if (wjtmList != null && wjtmList.size() > 0) {
                String wjFlType = null;
                for (LinkedHashMap<String, Object> tmMap : wjtmList) {
                    //如果为null表示第一次进来，不比较分类type
                    if(wjFlType == null){
                        sw.createCell(i++, clearZero(null), moneyStyle);
                        wjFlType = tmMap.get("wjtmflType").toString();
                    }else{
                        //比较分类type是否与之前一致，一致则不给wjFlType赋值，不一致则增加小计一栏
                        if(wjFlType.equals(tmMap.get("wjtmflType").toString())){
                            sw.createCell(i++, clearZero(null), moneyStyle);
                        }else{
                            sw.createCell(i++, clearZero(null), moneyStyle);
                            sw.createCell(i++, clearZero(null), moneyStyle);
                            wjFlType = tmMap.get("wjtmflType").toString();
                        }
                    }
                }
                //为最后一个分类增加小计
                sw.createCell(i++, clearZero(null), moneyStyle);
            }
        }
        sw.createCell(i++, clearZero(keFuWjAnalyseDto.getWjdcTotalscore().toString()), moneyStyle);
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
        c = new ExcelColumnSet("1", "1");
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;

        c = new ExcelColumnSet("2","2");
        c.setWidth(10);
        columnSet.add(c);
        nColIndex ++;

        c = new ExcelColumnSet("3", "4");
        c.setWidth(10);
        columnSet.add(c);

        c = new ExcelColumnSet("5","5");
        c.setWidth(16);
        columnSet.add(c);
        nColIndex ++;

        c = new ExcelColumnSet("6", "6");
        c.setWidth(10);
        columnSet.add(c);

        c = new ExcelColumnSet("7", "7");
        c.setWidth(12);
        columnSet.add(c);

        c = new ExcelColumnSet("8", "8");
        c.setWidth(20);
        columnSet.add(c);

        c = new ExcelColumnSet("9", "9");
        c.setWidth(10);
        columnSet.add(c);

        c = new ExcelColumnSet("10", "10");
        c.setWidth(12);
        columnSet.add(c);

        c = new ExcelColumnSet("11", "11");
        c.setWidth(14);
        columnSet.add(c);

        c = new ExcelColumnSet("12", "12");
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

    public Double clearZeroTwo(Object value) {
        if (value == null || value.equals("-") || "0".equals(value.toString()))
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
    private void setCreateDateCell(Date date , int i, short dateStyle, SpeedSheetWriter sw) throws Exception {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (date == null || "".equals(date) || "-".equals(date)) {
            sw.createCell(i, "-", dateStyle);
            return;
        }
        String dateString = formatter.format(date);

        try{
            if (dateString == null || "".equals(dateString) || "-".equals(dateString)) {
                sw.createCell(i, "-", dateStyle);
            } else {
                cal.setTime(DateUtils.parseDate(dateString));
                sw.createCell(i, cal, dateStyle);
            }
        }catch(Exception e){
        }

    }

}
