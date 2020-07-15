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
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.common.util.DateUtils;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.houseLinkage.linkZjcbDetail.service.LinkZjcbDetailService;
import cn.com.eju.deal.poi.common.ExcelColumnSet;
import cn.com.eju.deal.poi.common.ExcelStyleEnum;
import cn.com.eju.deal.poi.common.ExcelUseXmlAbstract;
import cn.com.eju.deal.poi.common.SpeedSheetWriter;

public class ExcelForLinkZjcbDetailBigData extends ExcelUseXmlAbstract {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    private LinkZjcbDetailService linkZjcbDetailService = new LinkZjcbDetailService();

    private short bodyBlackStyle;
    private short bodyRedStyle;
    private short dateBlackStyle;
    private short dateRedStyle;
    private short moneyBlackStyle;
    private short moneyRedStyle;
    private short intBlackStyle;
    private short intRedStyle;
    private String yearly;

    public void downloadSheet(Map<String, Object> reqMap, File file, String yearly) throws IOException {
        OutputStream os = new FileOutputStream(file);
        this.yearly = yearly;

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("联动明细(资金成本)");

        Map<String, XSSFCellStyle> style = super.createStyles(wb);
        super.headerStyle = style.get(ExcelStyleEnum.HEADER.getValue()).getIndex();
        this.bodyBlackStyle = style.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();
        this.bodyRedStyle = style.get(ExcelStyleEnum.BODY_RED.getValue()).getIndex();
        this.dateBlackStyle = style.get(ExcelStyleEnum.DATE_BLACK.getValue()).getIndex();
        this.dateRedStyle = style.get(ExcelStyleEnum.DATE_RED.getValue()).getIndex();
        this.moneyBlackStyle = style.get(ExcelStyleEnum.MONEY_UNLOCKED_BLACK.getValue()).getIndex();
        this.moneyRedStyle = style.get(ExcelStyleEnum.MONEY_UNLOCKED_RED.getValue()).getIndex();
        this.intBlackStyle = style.get(ExcelStyleEnum.INT_BLACK.getValue()).getIndex();
        this.intRedStyle = style.get(ExcelStyleEnum.INT_RED.getValue()).getIndex();

        File tempExcel = File.createTempFile("tempExcel", ".xlsx");
        File temp = File.createTempFile("sheet", ".xml");
        try {
            FileOutputStream fos = new FileOutputStream(tempExcel);
            wb.write(fos);
            fos.close();
            String sheetRef = sheet.getPackagePart().getPartName().getName();
            Writer fw = new PrintWriter(temp, "UTF-8");
            this.createSheet(fw, reqMap);
            fw.close();
            super.substitute(tempExcel, temp, sheetRef.substring(1), os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            os.close();
        }
    }

    private void createSheet(Writer out, Map<String, Object> reqMap) throws Exception {
        SpeedSheetWriter sw = new SpeedSheetWriter(out);
        sw.beginSheet(setColumnWidth(), 0, 1, "A2");//0:y轴 1：X轴  A2数据开始位置  A2得位置要根据x,y取值来改变   比如1，1，B2
        int index = 0;
        List<String> listMerge = new ArrayList<>();
        index = this.createSheetHead(sw, index, listMerge);

        PageInfo pageInfo = new PageInfo();
        pageInfo.setCurPage(1);
        pageInfo.setPageLimit(5000);
        logger.info("第1次查询开始。。。");
        ResultData<?> reback = linkZjcbDetailService.queryLinkZjcbDetailList(reqMap, pageInfo);
        List<LinkedHashMap<String, Object>> contentList = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

        Integer totalCount = Integer.valueOf(reback.getTotalCount());
        logger.info("第1次查询结束，totalCount:" + totalCount);
        if (contentList != null && contentList.size() > 0) {
            for (LinkedHashMap<String, Object> map : contentList) {
                index = this.createSheetBody(sw, index, map);
            }
        }
        logger.info("第1次写文件开始。。。");
        if (totalCount > contentList.size()) {
            Integer pageCount = (int) Math.ceil(Double.valueOf(totalCount) / Double.valueOf(contentList.size()));
            for (int i = 2; i <= pageCount; i++) {
                pageInfo.setCurPage(i);
                logger.info("第" + i + "次查询开始,共" + pageCount + "次。");
                reback = linkZjcbDetailService.queryLinkZjcbDetailList(reqMap, pageInfo);
                logger.info("第" + i + "次查询结束,共" + pageCount + "次。");
                contentList = (List<LinkedHashMap<String, Object>>) reback.getReturnData();
                if (contentList != null && contentList.size() > 0) {
                    for (LinkedHashMap<String, Object> map : contentList) {
                        index = this.createSheetBody(sw, index, map);
                    }
                }
                logger.info("第" + i + "次写文件结束。");
            }
        }
        logger.info("查询结束！");
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
        nowColIndex = createBlock(sw, "业绩归属区域", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "业绩归属城市", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "业绩所在城市", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "项目归属城市", 1, 1, listMerge);
        
        nowColIndex = createBlock(sw, "收入归属城市", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "项目编号", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "楼盘名", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "报备编号", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "楼室号", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "总累计资金成本", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "当年以前资金成本", 1, 1, listMerge);
        nowColIndex = createBlock(sw, yearly + "资金成本", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "1月资金成本", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "2月资金成本", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "3月资金成本", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "4月资金成本", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "5月资金成本", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "6月资金成本", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "7月资金成本", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "8月资金成本", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "9月资金成本", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "10月资金成本", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "11月资金成本", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "12月资金成本", 1, 1, listMerge);

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
//        short dateStyle = dateRedStyle;
        short moneyStyle = moneyBlackStyle;
//        short intStyle = intRedStyle;
        sw.createCell(i++, map.get("rowNum") == null ? "" : map.get("rowNum").toString(), bodyStyle);
        
        sw.createCell(i++, map.get("regionName") == null ? "" : map.get("regionName").toString(), bodyStyle);
        
        sw.createCell(i++, map.get("areaCityName") == null ? "" : map.get("areaCityName").toString(), bodyStyle);
        
        sw.createCell(i++, map.get("cityGroupName") == null ? "" : map.get("cityGroupName").toString(), bodyStyle);
        
//        sw.createCell(i++, map.get("areaGroupName") == null ? "" : map.get("areaGroupName").toString(), bodyStyle);
        
        sw.createCell(i++, map.get("projectCityName") == null ? "" : map.get("projectCityName").toString(), bodyStyle);
        
        sw.createCell(i++, map.get("srCityName") == null ? "" : map.get("srCityName").toString(), bodyStyle);
        
        sw.createCell(i++, map.get("projectNo") == null ? "" : map.get("projectNo").toString(), bodyStyle);
        
        sw.createCell(i++, map.get("estateNm") == null ? "" : map.get("estateNm").toString(), bodyStyle);
        
        sw.createCell(i++, map.get("reportId") == null ? "" : map.get("reportId").toString(), bodyStyle);
        
        sw.createCell(i++, map.get("buildingNo") == null ? "" : map.get("buildingNo").toString(), bodyStyle);
        
        sw.createCell(i++, clearZero(map.get("totalAmount")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("preTotal")), moneyStyle);
        
        sw.createCell(i++, clearZero(map.get("thisTotal")), moneyStyle);
        
        sw.createCell(i++, clearZero(map.get("jan")), moneyStyle);
        
        sw.createCell(i++, clearZero(map.get("feb")), moneyStyle);
        
        sw.createCell(i++, clearZero(map.get("apr")), moneyStyle);
        
        sw.createCell(i++, clearZero(map.get("mar")), moneyStyle);
        
        sw.createCell(i++, clearZero(map.get("may")), moneyStyle);
        
        sw.createCell(i++, clearZero(map.get("jun")), moneyStyle);
        
        sw.createCell(i++, clearZero(map.get("jul")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("aug")), moneyStyle);
        
        sw.createCell(i++, clearZero(map.get("sep")), moneyStyle);
        
        sw.createCell(i++, clearZero(map.get("oct")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("nov")), moneyStyle);
        
        sw.createCell(i++, clearZero(map.get("dec")), moneyStyle);

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

        //城市
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 10));
        c.setWidth(15);
        columnSet.add(c);
        nColIndex = nColIndex+ 11;

        //资金成本列
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex+13));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex+ 14;

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
    private void setCreateDateCell(String dateName, int i, short dateStyle, LinkedHashMap<String, Object> map, SpeedSheetWriter sw) throws Exception {

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
