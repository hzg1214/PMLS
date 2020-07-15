package cn.com.eju.pmls.poi;

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
import cn.com.eju.deal.houseLinkage.linkMarginDetail.service.LinkMarginDetailService;
import cn.com.eju.deal.poi.common.ExcelColumnSet;
import cn.com.eju.deal.poi.common.ExcelStyleEnum;
import cn.com.eju.deal.poi.common.ExcelUseXmlAbstract;
import cn.com.eju.deal.poi.common.SpeedSheetWriter;
/**
 * 联动资金成本(保证金、诚意金)
 * zhenggang.Huang
 */
public class ExcelForLinkMarginDetailBigData extends ExcelUseXmlAbstract {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    private LinkMarginDetailService linkMarginDetailService = new LinkMarginDetailService();

    private short bodyBlackStyle;
    private short bodyRedStyle;
    private short dateBlackStyle;
    private short dateRedStyle;
    private short moneyBlackStyle;
    private short moneyRedStyle;
    private short intBlackStyle;
    private short intRedStyle;
    private short doubleBlackStyle;
    private String yearly;

    public void downloadSheet(Map<String, Object> reqMap, File file, String yearly) throws IOException {
        OutputStream os = new FileOutputStream(file);
        this.yearly = yearly;

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("联动资金成本(保证金&诚意金)");
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
        this.doubleBlackStyle = style.get(ExcelStyleEnum.DOUBLE_BLACK.getValue()).getIndex();
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
        sw.beginSheet(setColumnWidth(), 4, 2, "E3");//0:y轴 1：X轴  A2数据开始位置  A2得位置要根据x,y取值来改变   比如1，1，B2
        int index = 0;
        List<String> listMerge = new ArrayList<>();
        index = this.createSheetHead(sw, index, listMerge);

        PageInfo pageInfo = new PageInfo();
        pageInfo.setCurPage(1);
        pageInfo.setPageLimit(5000);
        logger.info("第1次查询开始。。。");
        ResultData<?> reback = linkMarginDetailService.queryLinkMarginDetailList(reqMap, pageInfo);
        List<LinkedHashMap<String, Object>> contentList = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

        Integer totalCount = Integer.valueOf(reback.getTotalCount());
        logger.info("第1次查询结束，totalCount:" + totalCount);
        if (contentList != null && contentList.size() > 0) {
            for (LinkedHashMap<String, Object> map : contentList) {
                index = this.createSheetBody(sw, index, map);
            }
        }
        logger.info("第1次写文件开始。。。");
        if (contentList != null && totalCount > contentList.size()) {
            Integer pageCount = (int) Math.ceil(Double.valueOf(totalCount) / Double.valueOf(contentList.size()));
            for (int i = 2; i <= pageCount; i++) {
                pageInfo.setCurPage(i);
                logger.info("第" + i + "次查询开始,共" + pageCount + "次。");
                reback = linkMarginDetailService.queryLinkMarginDetailList(reqMap, pageInfo);
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
        nowColIndex = createBlock(sw, "序号", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "考核主体", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "核算主体", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "成本中心", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "项目归属城市", 1, 2, listMerge);
        
        nowColIndex = createBlock(sw, "项目编号", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "项目名称", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "预付款单号", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "客商/供应商", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "类型", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "预计归还日期", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "预付款金额", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "实际付款金额", 1, 2, listMerge);
        
        nowColIndex = createBlock(sw, "还款金额", 2, 1, listMerge);
        
        nowColIndex = createBlock(sw, "未收回金额", 1, 2, listMerge);
        
        nowColIndex = createBlock(sw, "资金成本", 3, 1, listMerge);
        
        nowColIndex = createBlock(sw, "1月", 3, 1, listMerge);
        nowColIndex = createBlock(sw, "2月", 3, 1, listMerge);
        nowColIndex = createBlock(sw, "3月", 3, 1, listMerge);
        nowColIndex = createBlock(sw, "4月", 3, 1, listMerge);
        nowColIndex = createBlock(sw, "5月", 3, 1, listMerge);
        nowColIndex = createBlock(sw, "6月", 3, 1, listMerge);
        nowColIndex = createBlock(sw, "7月", 3, 1, listMerge);
        nowColIndex = createBlock(sw, "8月", 3, 1, listMerge);
        nowColIndex = createBlock(sw, "9月", 3, 1, listMerge);
        nowColIndex = createBlock(sw, "10月", 3, 1, listMerge);
        nowColIndex = createBlock(sw, "11月", 3, 1, listMerge);
        nowColIndex = createBlock(sw, "12月", 3, 1, listMerge);

        sw.endRow();
        //第二行
        sw.insertRow(++nowRowIndex);
        nowColIndex = 0;
        nowColIndex = createBlock(sw, "序号", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "考核主体", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "核算主体", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "成本中心", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "项目归属城市", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "项目编号", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "项目名称", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "预付款单号", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "客商/供应商", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "类型", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "预计归还日期", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "预付款金额", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "实际付款金额", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "正常回款", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "核销", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "未收回金额", 1, 0, listMerge);
        
        nowColIndex = createBlock(sw, "总累计", 1, 1, listMerge);
        nowColIndex = createBlock(sw, yearly+"以前", 1, 1, listMerge);
        nowColIndex = createBlock(sw, yearly + "新增", 1, 1, listMerge);
        for(int i=0;i<12;i++) {
        	nowColIndex = createBlock(sw, "超3个月保证金金额", 1, 1, listMerge);
        	nowColIndex = createBlock(sw, "超3个月保证金回款", 1, 1, listMerge);
        	nowColIndex = createBlock(sw, "资金成本", 1, 1, listMerge);
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
        short dateStyle = dateBlackStyle;
        short doubleBlack = doubleBlackStyle;
        sw.createCell(i++, map.get("rowNum") == null ? "" : map.get("rowNum").toString(), bodyStyle);

        sw.createCell(i++, map.get("kh_name") == null ? "" : map.get("kh_name").toString(), bodyStyle);

        sw.createCell(i++, map.get("hs_name") == null ? "" : map.get("hs_name").toString(), bodyStyle);

        sw.createCell(i++, map.get("costName") == null ? "" : map.get("costName").toString(), bodyStyle);
        
        sw.createCell(i++, map.get("gsCityName") == null ? "" : map.get("gsCityName").toString(), bodyStyle);
        
        sw.createCell(i++, map.get("projectNo") == null ? "" : map.get("projectNo").toString(), bodyStyle);
        
        sw.createCell(i++, map.get("projectName") == null ? "" : map.get("projectName").toString(), bodyStyle);
        
        sw.createCell(i++, map.get("oaNo") == null ? "" : map.get("oaNo").toString(), bodyStyle);
        
        sw.createCell(i++, map.get("ksName") == null ? "" : map.get("ksName").toString(), bodyStyle);
        
        sw.createCell(i++, map.get("marginTypeName") == null ? "" : map.get("marginTypeName").toString(), bodyStyle);
        
        setCreateDateCell("yfhkDate", i++, dateStyle, map, sw);
        
        sw.createCell(i++, clearZero(map.get("oaAmount")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("sjfkAmountTotal")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("hkAmountTotal")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("hxAmount")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("cyAmountTotal")), doubleBlack);
        
        sw.createCell(i++, clearZero(map.get("totalAmount")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("preTotal")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("thisTotal")), doubleBlack);
        
        sw.createCell(i++, clearZero(map.get("marginjan")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("marginhkjan")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("marginCbjan")), doubleBlack);
                                                                      
        sw.createCell(i++, clearZero(map.get("marginfeb")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("marginhkfeb")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("marginCbfeb")), doubleBlack);
        
        sw.createCell(i++, clearZero(map.get("marginmar")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("marginhkmar")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("marginCbmar")), doubleBlack);
                                                                      
        sw.createCell(i++, clearZero(map.get("marginapr")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("marginhkapr")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("marginCbapr")), doubleBlack);
                                                                      
        sw.createCell(i++, clearZero(map.get("marginmay")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("marginhkmay")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("marginCbmay")), doubleBlack);
                                                                      
        sw.createCell(i++, clearZero(map.get("marginjun")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("marginhkjun")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("marginCbjun")), doubleBlack);
                                                                      
        sw.createCell(i++, clearZero(map.get("marginjul")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("marginhkjul")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("marginCbjul")), doubleBlack);
                                                                      
        sw.createCell(i++, clearZero(map.get("marginaug")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("marginhkaug")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("marginCbaug")), doubleBlack);
                                                                      
        sw.createCell(i++, clearZero(map.get("marginsep")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("marginhksep")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("marginCbsep")), doubleBlack);
                                              
        sw.createCell(i++, clearZero(map.get("marginoct")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("marginhkoct")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("marginCboct")), doubleBlack);
        
        sw.createCell(i++, clearZero(map.get("marginnov")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("marginhknov")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("marginCbnov")), doubleBlack);
        
        sw.createCell(i++, clearZero(map.get("margindec")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("marginhkdec")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("marginCbdec")), doubleBlack);

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
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 5));
        c.setWidth(15);
        columnSet.add(c);
        nColIndex = nColIndex+ 5;
        
        //项目名称
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex+2));
        c.setWidth(20);
        columnSet.add(c);
        nColIndex = nColIndex+ 3;
        
        //客商/供应商及以后
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 46));
        c.setWidth(15);
        columnSet.add(c);
        nColIndex = nColIndex+ 47;

        return columnSet;
    }

    public Double clearZero(Object value) {
        if (value == null || value.equals("-") || "0.00".equals(value.toString())) 
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
     * 创建浮点型cell
     */
    private void setCreateDoubleCell(String key ,int i,short style, boolean fillWithZero, LinkedHashMap<String, Object> map,SpeedSheetWriter sw) throws Exception {
    	double value = 0;
    	Object o = map.get(key);
        try{
        	if (map.get(key) == null) {
        		if (!fillWithZero) {
        			sw.createEmptyCell(i, style);
        			return;
        		}
            } else {
            	if (o instanceof String) {
	            	String s = o.toString();
	            	value = Double.valueOf(s);
            	}else if (o instanceof Double){
	                value = (Double) o;
            	}
            }
        }catch(Exception e){
        	logger.error(map.get(key).toString());
        }
        sw.createCell(i, value, style);
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
