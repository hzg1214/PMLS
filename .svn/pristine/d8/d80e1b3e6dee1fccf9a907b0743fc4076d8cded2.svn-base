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
import org.springframework.util.StringUtils;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.common.util.DateUtils;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.poi.common.ExcelColumnSet;
import cn.com.eju.deal.poi.common.ExcelStyleEnum;
import cn.com.eju.deal.poi.common.ExcelUseXmlAbstract;
import cn.com.eju.deal.poi.common.SpeedSheetWriter;
import cn.com.eju.pmls.statisticsReport.store.service.RptStoreDetailService;
/**
 * 门店明细
 * 
 */
public class ExcelForStoreDetailBigData extends ExcelUseXmlAbstract {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    private RptStoreDetailService rptStoreDetailService = new RptStoreDetailService();

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

    public void downloadSheet(Map<String, Object> reqMap, File file, Map<String, Object> titleMap) throws IOException {
        OutputStream os = new FileOutputStream(file);
        this.yearly = yearly;

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("门店明细");
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
            this.createSheet(fw, reqMap,titleMap);
            fw.close();
            super.substitute(tempExcel, temp, sheetRef.substring(1), os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            os.close();
        }
    }

    private void createSheet(Writer out, Map<String, Object> reqMap,Map<String, Object> titleMap) throws Exception {
    	String searchType = (String) reqMap.get("searchType");
        SpeedSheetWriter sw = new SpeedSheetWriter(out);
        sw.beginSheet(setColumnWidth(searchType,reqMap,titleMap), 6, 2, "G3");//0:y轴 1：X轴  A2数据开始位置  A2得位置要根据x,y取值来改变   比如1，1，B2
        int index = 0;
        List<String> listMerge = new ArrayList<>();
        index = this.createSheetHead(sw, index, listMerge,reqMap,titleMap);

        PageInfo pageInfo = new PageInfo();
        pageInfo.setCurPage(1);
        pageInfo.setPageLimit(5000);
        logger.info("门店明细 第1次查询开始。。。");
        ResultData<?> reback = rptStoreDetailService.queryStoreDetailList(reqMap, pageInfo);
        List<LinkedHashMap<String, Object>> contentList = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

        Integer totalCount = Integer.valueOf(reback.getTotalCount());
        logger.info("门店明细 第1次查询结束，totalCount:" + totalCount);
        if (contentList != null && contentList.size() > 0) {
            for (LinkedHashMap<String, Object> map : contentList) {
                index = this.createSheetBody(sw, index, map,reqMap,titleMap);
            }
        }
        logger.info("门店明细 第1次写文件开始。。。");
        if (contentList != null && totalCount > contentList.size()) {
            Integer pageCount = (int) Math.ceil(Double.valueOf(totalCount) / Double.valueOf(contentList.size()));
            for (int i = 2; i <= pageCount; i++) {
                pageInfo.setCurPage(i);
                logger.info("门店明细 第" + i + "次查询开始,共" + pageCount + "次。");
                reback = rptStoreDetailService.queryStoreDetailList(reqMap, pageInfo);
                logger.info("门店明细 第" + i + "次查询结束,共" + pageCount + "次。");
                contentList = (List<LinkedHashMap<String, Object>>) reback.getReturnData();
                if (contentList != null && contentList.size() > 0) {
                    for (LinkedHashMap<String, Object> map : contentList) {
                        index = this.createSheetBody(sw, index, map,reqMap,titleMap);
                    }
                }
                logger.info("门店明细 第" + i + "次写文件结束。");
            }
        }
        logger.info("查询结束！");
        sw.endSheetWithMerge(listMerge);
    }

    /**
     * 创建sheet头部内容
     */
    private int createSheetHead(SpeedSheetWriter sw, int index, List<String> listMerge,Map<String, Object> reqMap,Map<String, Object> titleMap) throws IOException {
    	Integer roughYear = Integer.valueOf((String)reqMap.get("roughYear")) ;
    	
        nowRowIndex = index;
        nowColIndex = 0;
        sw.insertRow(nowRowIndex);
        //第一行
        nowColIndex = createBlock(sw, "序号", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "归属区域", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "归属城市", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "所在城市", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "门店编号", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "门店名称", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "业务类型", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "门店地址", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "是否房友门店", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "所属公司编号", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "所属公司名称", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "联动维护中心", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "联动维护人", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "维护人工号", 1, 2, listMerge);

        //年维度
        if("yearDimension".equals(reqMap.get("roughDimension").toString())) {
        	nowColIndex = createBlock(sw, "大定金额", 3, 1, listMerge);
        //月维度
        }else if("monthDimension".equals(reqMap.get("roughDimension").toString())) {
        	nowColIndex = createBlock(sw, "大定金额", 15, 1, listMerge);
        //周维度
        }else if("weekDimension".equals(reqMap.get("roughDimension").toString())) {
        	if(!StringUtils.isEmpty(titleMap.get("weekDate54"))) {
        		nowColIndex = createBlock(sw, "大定金额", 57, 1, listMerge);
        	}else if(!StringUtils.isEmpty(titleMap.get("weekDate53"))) {
        		nowColIndex = createBlock(sw, "大定金额", 56, 1, listMerge);
        	}else if(!StringUtils.isEmpty(titleMap.get("weekDate52"))) {
        		nowColIndex = createBlock(sw, "大定金额", 55, 1, listMerge);
        	}
        }

        sw.endRow();
        //第二行
        sw.insertRow(++nowRowIndex);
        nowColIndex = 0;
        nowColIndex = createBlock(sw, "序号", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "归属区域", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "归属城市", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "所在城市", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "门店编号", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "门店名称", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "业务类型", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "门店地址", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "是否房友门店", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "所属公司编号", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "所属公司名称", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "联动维护中心", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "联动维护人", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "维护人工号", 1, 0, listMerge);
        
        //年维度
        nowColIndex = createBlock(sw, "总累计", 1, 1, listMerge);
        nowColIndex = createBlock(sw, roughYear+"年以前", 1, 1, listMerge);
        nowColIndex = createBlock(sw, roughYear+"年新增", 1, 1, listMerge);
        if("monthDimension".equals(reqMap.get("roughDimension").toString())) {
        	nowColIndex = createBlock(sw, roughYear+"年1月", 1, 1, listMerge);
        	nowColIndex = createBlock(sw, roughYear+"年2月", 1, 1, listMerge);
        	nowColIndex = createBlock(sw, roughYear+"年3月", 1, 1, listMerge);
        	
        	nowColIndex = createBlock(sw, roughYear+"年4月", 1, 1, listMerge);
        	nowColIndex = createBlock(sw, roughYear+"年5月", 1, 1, listMerge);
        	nowColIndex = createBlock(sw, roughYear+"年6月", 1, 1, listMerge);
        	
        	nowColIndex = createBlock(sw, roughYear+"年7月", 1, 1, listMerge);
        	nowColIndex = createBlock(sw, roughYear+"年8月", 1, 1, listMerge);
        	nowColIndex = createBlock(sw, roughYear+"年9月", 1, 1, listMerge);
        	
        	nowColIndex = createBlock(sw, roughYear+"年10月", 1, 1, listMerge);
        	nowColIndex = createBlock(sw, roughYear+"年11月", 1, 1, listMerge);
        	nowColIndex = createBlock(sw, roughYear+"年12月", 1, 1, listMerge);
        //周维度
        }else if("weekDimension".equals(reqMap.get("roughDimension").toString())) {
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate1"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate2"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate3"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate4"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate5"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate6"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate7"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate8"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate9"), 1, 1, listMerge);

        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate10"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate11"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate12"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate13"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate14"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate15"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate16"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate17"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate18"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate19"), 1, 1, listMerge);

        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate20"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate21"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate22"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate23"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate24"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate25"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate26"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate27"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate28"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate29"), 1, 1, listMerge);

        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate30"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate31"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate32"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate33"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate34"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate35"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate36"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate37"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate38"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate39"), 1, 1, listMerge);

        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate40"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate41"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate42"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate43"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate44"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate45"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate46"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate47"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate48"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate49"), 1, 1, listMerge);

        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate50"), 1, 1, listMerge);
        	nowColIndex = createBlock(sw, (String)titleMap.get("weekDate51"), 1, 1, listMerge);
        	if(!StringUtils.isEmpty(titleMap.get("weekDate52"))) {
        		nowColIndex = createBlock(sw, (String)titleMap.get("weekDate52"), 1, 1, listMerge);
        	}
        	if(!StringUtils.isEmpty(titleMap.get("weekDate53"))) {
        		nowColIndex = createBlock(sw, (String)titleMap.get("weekDate53"), 1, 1, listMerge);
        	}
        	if(!StringUtils.isEmpty(titleMap.get("weekDate54"))) {
        		nowColIndex = createBlock(sw, (String)titleMap.get("weekDate54"), 1, 1, listMerge);
        	}
        }
        
        sw.endRow();
        nowRowIndex = nowRowIndex + 1;

        return nowRowIndex;
    }

    /**
     * 创建sheet具体内容
     */
    private int createSheetBody(SpeedSheetWriter sw, int index, LinkedHashMap<String, Object> map
    		,Map<String, Object> reqMap,Map<String, Object> titleMap) throws Exception {
    	sw.insertRow(index);
        int i = 0;

        short bodyStyle = bodyBlackStyle;
        short moneyStyle = moneyBlackStyle;
        short intStyle = intBlackStyle;

        sw.createCell(i++, map.get("rowNum") == null ? "" : map.get("rowNum").toString(), bodyStyle);
        sw.createCell(i++, map.get("regionName") == null ? "" : map.get("regionName").toString(), bodyStyle);
        sw.createCell(i++, map.get("areaCityName") == null ? "" : map.get("areaCityName").toString(), bodyStyle);
        sw.createCell(i++, map.get("acCityName") == null ? "" : map.get("acCityName").toString(), bodyStyle);
        sw.createCell(i++, map.get("storeNo") == null ? "" : map.get("storeNo").toString(), bodyStyle);
        sw.createCell(i++, map.get("storeName") == null ? "" : map.get("storeName").toString(), bodyStyle);
        sw.createCell(i++, map.get("brandTypeStr") == null ? "" : map.get("brandTypeStr").toString(), bodyStyle);
        sw.createCell(i++, map.get("storeAddress") == null ? "" : map.get("storeAddress").toString(), bodyStyle);
        sw.createCell(i++, map.get("isFyStoreStr") == null ? "" : map.get("isFyStoreStr").toString(), bodyStyle);
        sw.createCell(i++, map.get("comCompanyNo") == null ? "" : map.get("comCompanyNo").toString(), bodyStyle);
        sw.createCell(i++, map.get("companyName") == null ? "" : map.get("companyName").toString(), bodyStyle);
        sw.createCell(i++, map.get("pmlsGroupName") == null ? "" : map.get("pmlsGroupName").toString(), bodyStyle);
        sw.createCell(i++, map.get("pmlsMaintainName") == null ? "" : map.get("pmlsMaintainName").toString(), bodyStyle);
        sw.createCell(i++, map.get("pmlsMaintainCode") == null ? "" : map.get("pmlsMaintainCode").toString(), bodyStyle);
        
        sw.createCell(i++, formatMoney(map.get("totalAmount")), moneyStyle);
        sw.createCell(i++, formatMoney(map.get("preTotal")), moneyStyle);
        sw.createCell(i++, formatMoney(map.get("thisTotal")), moneyStyle);
        
        //月维度
        if("monthDimension".equals(reqMap.get("roughDimension").toString())) {
        	sw.createCell(i++, formatMoney1(map.get("monthAmount1")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("monthAmount2")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("monthAmount3")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("monthAmount4")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("monthAmount5")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("monthAmount6")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("monthAmount7")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("monthAmount8")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("monthAmount9")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("monthAmount10")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("monthAmount11")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("monthAmount12")), moneyStyle);
        //周维度
        }else if("weekDimension".equals(reqMap.get("roughDimension").toString())) {
        	sw.createCell(i++, formatMoney1(map.get("weekAmount1")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount2")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount3")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount4")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount5")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount6")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount7")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount8")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount9")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount0")), moneyStyle);

        	sw.createCell(i++, formatMoney1(map.get("weekAmount11")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount12")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount13")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount14")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount15")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount16")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount17")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount18")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount19")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount20")), moneyStyle);

        	sw.createCell(i++, formatMoney1(map.get("weekAmount21")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount22")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount23")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount24")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount25")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount26")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount27")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount28")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount29")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount30")), moneyStyle);

        	sw.createCell(i++, formatMoney1(map.get("weekAmount31")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount32")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount33")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount34")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount35")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount36")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount37")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount38")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount39")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount40")), moneyStyle);

        	sw.createCell(i++, formatMoney1(map.get("weekAmount41")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount42")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount43")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount44")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount45")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount46")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount47")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount48")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount49")), moneyStyle);
        	sw.createCell(i++, formatMoney1(map.get("weekAmount50")), moneyStyle);

        	sw.createCell(i++, formatMoney1(map.get("weekAmount51")), moneyStyle);
        	if(!StringUtils.isEmpty(titleMap.get("weekDate52"))) {
        		sw.createCell(i++, formatMoney1(map.get("weekAmount52")), moneyStyle);
        	}
        	if(!StringUtils.isEmpty(titleMap.get("weekDate53"))) {
        		sw.createCell(i++, formatMoney1(map.get("weekAmount53")), moneyStyle);
        	}
        	if(!StringUtils.isEmpty(titleMap.get("weekDate54"))) {
        		sw.createCell(i++, formatMoney1(map.get("weekAmount54")), moneyStyle);
        	}


        }
        
        sw.endRow();
        index++;

        return index;
    }


    /**
     * 设置列宽
     */
    public List<ExcelColumnSet> setColumnWidth(String searchType, Map<String, Object> reqMap,Map<String, Object> titleMap) {
        List<ExcelColumnSet> columnSet = new ArrayList<>();

        ExcelColumnSet c;

        int nColIndex = 1;

        //序号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(5);
        columnSet.add(c);
        nColIndex++;
        
        //归属区域、归属城市、所在城市、门店编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 4));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex += 4;
        
        //门店名称
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(30);
        columnSet.add(c);
        nColIndex++;
        
        //业务类型
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex ++;
        
        //门店地址
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(35);
        columnSet.add(c);
        nColIndex++;
        
        //是否房友、公司编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex+2));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex +=2;
        
        //公司名称
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(35);
        columnSet.add(c);
        nColIndex++;
        
        //联动维护中心、维护人工号、联动维护人
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex+3));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex +=3;
        
        if("yearDimension".equals(reqMap.get("roughDimension").toString())) {
            c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 3));
            c.setWidth(20);
            columnSet.add(c);
            nColIndex += 3;
        }else if("monthDimension".equals(reqMap.get("roughDimension").toString())) {
        	c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 15));
        	c.setWidth(20);
        	columnSet.add(c);
        	nColIndex += 15;
        	
        }else if("weekDimension".equals(reqMap.get("roughDimension").toString())) {
        	c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 3));
            c.setWidth(20);
            columnSet.add(c);
            nColIndex += 3;
        	if(!StringUtils.isEmpty(titleMap.get("weekDate52"))) {
        		c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 52));
            	c.setWidth(30);
            	columnSet.add(c);
            	nColIndex += 52;
        	}else if(!StringUtils.isEmpty(titleMap.get("weekDate53"))) {
        		c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 53));
            	c.setWidth(30);
            	columnSet.add(c);
            	nColIndex += 53;
        	}else if(!StringUtils.isEmpty(titleMap.get("weekDate54"))) {
        		c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 54));
            	c.setWidth(30);
            	columnSet.add(c);
            	nColIndex += 54;
        	}
        }

        return columnSet;
    }
    
    private String formatMoney (Object o){
		if (o == null || "".equals(o.toString()) || "-".equals(o.toString()) || "0.00".equals(o.toString())) {
			return "";
		} else {
			return o.toString();
		}
    }
    
    private String formatMoney1 (Object o){
    	if (o == null || "".equals(o.toString()) || "-".equals(o.toString())) {
    		return "";
    	} else {
    		return o.toString();
    	}
    }
    
    private void createCellMoney (SpeedSheetWriter sw, int i,LinkedHashMap<String, Object> map,Object o
    		,short bodyStyle,short moneyStyle){
        try {
            if (o == null || "".equals(o.toString()) || "-".equals(o.toString()) || "0.00".equals(o.toString())) {
            	sw.createCell(i++,"", bodyStyle);
            } else {
                Double cellValue = Double.parseDouble(o.toString());
                sw.createCell(i++, cellValue, moneyStyle);
            }
        } catch (Exception e) {
            logger.error("报表##导出##createCellMoney##金额格式处理异常columnIndex="+i,e);
            e.printStackTrace();
        }
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
