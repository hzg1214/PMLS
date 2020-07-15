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

public class ExcelForLinkProjectDetail extends ExcelUseXmlAbstract {

    private short bodyBlackStyle;
    private short moneyBlackStyle;
    private short intBlackStyle;

    public void downloadSheet(List<LinkedHashMap<String, Object>> list, File file,String searchType) throws IOException {
        OutputStream os = new FileOutputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("联动项目明细");

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
            this.createSheet(fw, list, searchType);
            fw.close();
            super.substitute(tempExcel, temp, sheetRef.substring(1), os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            os.close();
        }
    }

    private void createSheet(Writer out, List<LinkedHashMap<String, Object>> results,String searchType) throws Exception {
        SpeedSheetWriter sw = new SpeedSheetWriter(out);
        sw.beginSheet(setColumnWidth(searchType), 0, 3, "A4");
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
        nowColIndex = createBlock(sw, "序号", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "项目归属区域", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "项目归属城市", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "项目所在城市", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "项目归属部门", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "报备归属城市", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "报备归属中心", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "楼盘名", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "城市", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "区域", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "地址", 1, 3, listMerge);
//        nowColIndex = createBlock(sw, "系统编号", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "项目编号", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "合作类型", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "合作方名称", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "是否大客户", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "大客户简称", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "是否独家", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "是否直签", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "垫佣金额", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "合作周期", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "项目状态", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "物业类型", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "合作模式", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "当期实际(不含结转)", 12, 1, listMerge);
        nowColIndex = createBlock(sw, "总累计", 12, 1, listMerge);

        sw.endRow();
        //第二行
        sw.insertRow(++nowRowIndex);
        nowColIndex = 0;
        nowColIndex = createBlock(sw, "序号", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "项目归属区域", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "项目归属城市", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "项目所在城市", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "项目归属部门", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "报备归属城市", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "报备归属中心", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "楼盘名", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "城市", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "区域", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "地址", 1, 0, listMerge);
        //nowColIndex = createBlock(sw, "系统编号", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "项目编号", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "合作类型", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "合作方名称", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "是否大客户", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "大客户简称", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "是否独家", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "是否直签", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "垫佣金额", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "合作周期", 1,0, listMerge);
        nowColIndex = createBlock(sw, "项目状态", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "物业类型", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "合作模式", 1, 0, listMerge);

        for(int i=0;i<2;i++) {
            nowColIndex = createBlock(sw, "大定", 3, 1, listMerge);
            nowColIndex = createBlock(sw, "成销", 3, 1, listMerge);
            nowColIndex = createBlock(sw, "应计收入（税前）", 3, 1, listMerge);
            nowColIndex = createBlock(sw, "实际回款", 3, 1, listMerge);
        }

        sw.endRow();
        //第三行
        sw.insertRow(++nowRowIndex);
        nowColIndex = 0;
        nowColIndex = createBlock(sw, "序号", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "项目归属区域", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "项目归属城市", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "项目所在城市", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "项目归属部门", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "报备归属城市", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "报备归属中心", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "楼盘名", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "城市", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "区域", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "地址", 1, 0, listMerge);
        //nowColIndex = createBlock(sw, "系统编号", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "项目编号", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "合作类型", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "合作方名称", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "是否大客户", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "大客户简称", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "是否独家", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "是否直签", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "垫佣金额", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "合作周期", 1,0, listMerge);
        nowColIndex = createBlock(sw, "项目状态", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "物业类型", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "合作模式", 1, 0, listMerge);

        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                nowColIndex = createBlock(sw, "套数", 1, 1, listMerge);
                nowColIndex = createBlock(sw, "面积", 1, 1, listMerge);
                nowColIndex = createBlock(sw, "金额", 1, 1, listMerge);
            }
            for(int k=0;k<2;k++){
                nowColIndex = createBlock(sw, "收入", 1, 1, listMerge);
                nowColIndex = createBlock(sw, "返佣", 1, 1, listMerge);
                nowColIndex = createBlock(sw, "净收", 1, 1, listMerge);
            }
        }
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

        sw.createCell(i++, map.get("rowNum") == null ? "" : map.get("rowNum").toString(), bodyStyle);
        sw.createCell(i++, map.get("regionName") == null ? "" : map.get("regionName").toString(), bodyStyle);
        sw.createCell(i++, map.get("areaCityName") == null ? "" : map.get("areaCityName").toString(), bodyStyle);
        sw.createCell(i++, map.get("cityGroupName") == null ? "" : map.get("cityGroupName").toString(), bodyStyle);
        sw.createCell(i++, map.get("centerGroupName") == null ? "" : map.get("centerGroupName").toString(), bodyStyle);
        sw.createCell(i++, map.get("performCityName") == null ? "" : map.get("performCityName").toString(), bodyStyle);
        sw.createCell(i++, map.get("performCenterName") == null ? "" : map.get("performCenterName").toString(), bodyStyle);
        sw.createCell(i++, map.get("estateNm") == null ? "" : map.get("estateNm").toString(), bodyStyle);
        sw.createCell(i++, map.get("cityName") == null ? "" : map.get("cityName").toString(), bodyStyle);
        sw.createCell(i++, map.get("districtName") == null ? "" : map.get("districtName").toString(), bodyStyle);
        sw.createCell(i++, map.get("address") == null ? "" : map.get("address").toString(), bodyStyle);
       // sw.createCell(i++, map.get("estateId") == null ? "" : map.get("estateId").toString(), bodyStyle);
        sw.createCell(i++, map.get("projectNo") == null ? "" : map.get("projectNo").toString(), bodyStyle);
        sw.createCell(i++, map.get("partnerName") == null ? "" : map.get("partnerName").toString(), bodyStyle);
        sw.createCell(i++, map.get("partnerNm") == null ? "" : map.get("partnerNm").toString(), bodyStyle);
        sw.createCell(i++, map.get("bigCustomerFlagVakue") == null ? "" : map.get("bigCustomerFlagVakue").toString(), bodyStyle);
        sw.createCell(i++, map.get("bigCustomerName") == null ? "" : map.get("bigCustomerName").toString(), bodyStyle);
        sw.createCell(i++, map.get("particularFlagVakue") == null ? "" : map.get("particularFlagVakue").toString(), bodyStyle);
        sw.createCell(i++, map.get("directSignFlagVakue") == null ? "" : map.get("directSignFlagVakue").toString(), bodyStyle);
        sw.createCell(i++, clearZero(map.get("yjdyAmount")), moneyStyle);
        sw.createCell(i++, map.get("estateCycle") == null ? "" : map.get("estateCycle").toString(), bodyStyle);
        
        sw.createCell(i++, map.get("projectStatusName") == null ? "" : map.get("projectStatusName").toString(), bodyStyle);
        sw.createCell(i++, map.get("mgtKbnName") == null ? "" : map.get("mgtKbnName").toString(), bodyStyle);
        sw.createCell(i++, map.get("cooperationModeName") == null ? "" : map.get("cooperationModeName").toString(), bodyStyle);
        sw.createCell(i++, clearIntZero(map.get("curRoughNum")), intStyle);
        sw.createCell(i++, clearZero(map.get("curRoughArea")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("curRoughAmount")), moneyStyle);
        sw.createCell(i++, clearIntZero(map.get("curDealNum")), intStyle);
        sw.createCell(i++, clearZero(map.get("curDealArea")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("curDealAmount")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("curEPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("curEPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("curEPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("curAPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("curAPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("curAPTProfit")), moneyStyle);

        sw.createCell(i++, clearIntZero(map.get("accRoughNum")), intStyle);
        sw.createCell(i++, clearZero(map.get("accRoughArea")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("accRoughAmount")), moneyStyle);
        sw.createCell(i++, clearIntZero(map.get("accDealNum")), intStyle);
        sw.createCell(i++, clearZero(map.get("accDealArea")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("accDealAmount")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("accEPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("accEPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("accEPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("accAPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("accAPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("accAPTProfit")), moneyStyle);

        sw.endRow();
        index++;

        return index;
    }


    /**
     * 设置列宽
     */
    public List<ExcelColumnSet> setColumnWidth(String searchType) {
        List<ExcelColumnSet> columnSet = new ArrayList<>();

        ExcelColumnSet c;

        int nColIndex = 1;

        //序号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(5);
        columnSet.add(c);
        nColIndex++;

        //归属城市
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 4));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex += 5;

        //中心
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        if("1".equals(searchType)){
            c.setHidden(true);
        }
        columnSet.add(c);
        nColIndex++;

        //楼盘名
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
        columnSet.add(c);
        nColIndex++;

        //城市
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;

        //区域
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //地址
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(25);
        columnSet.add(c);
        nColIndex++;

//        //系统编号
//        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
//        c.setWidth(16);
//        columnSet.add(c);
//        nColIndex++;

        //项目编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(16);
        columnSet.add(c);
        nColIndex++;
        
        //合作方
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(15);
        columnSet.add(c);
        nColIndex++;

        //合作方名称
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(9);
        columnSet.add(c);
        nColIndex++;
            
        //是否大客户
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(9);
        columnSet.add(c);
        nColIndex++;
        //大客户简称
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(9);
        columnSet.add(c);
        nColIndex++;
        //是否独家
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(9);
        columnSet.add(c);
        nColIndex++;
        //是否直签
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(9);
        columnSet.add(c);
        nColIndex++;
        //垫佣金额
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(16);
        columnSet.add(c);
        nColIndex++;
        //合作周期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
        columnSet.add(c);
        nColIndex++;

        //项目状态
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(9);
        columnSet.add(c);
        nColIndex++;

        //物业类型
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex++;

        //合作模式
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(9);
        columnSet.add(c);
        nColIndex++;

      

        //大定
        //套数
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;

        //面积
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //金额
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex++;

        //成销
        //套数
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;

        //面积
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //金额
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex++;

        //应计收入
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 2));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 3;

        //实际回款
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 2));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 3;

        //大定
        //套数
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;

        //面积
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //金额
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex++;

        //成销
        //套数
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;

        //面积
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //金额
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex++;

        //应计收入
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 2));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 3;

        //实际回款
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 2));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 3;

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
