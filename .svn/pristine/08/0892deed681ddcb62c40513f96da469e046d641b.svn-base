package cn.com.eju.deal.poi;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.common.util.DateUtils;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.houseLinkage.linkZjcbDetail.service.LinkZjcbDetailService;
import cn.com.eju.deal.poi.common.ExcelColumnSet;
import cn.com.eju.deal.poi.common.ExcelStyleEnum;
import cn.com.eju.deal.poi.common.ExcelUseXmlAbstract;
import cn.com.eju.deal.poi.common.SpeedSheetWriter;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelForLinkZjcbDetailBigDataNew extends ExcelUseXmlAbstract {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    private LinkZjcbDetailService linkZjcbDetailService = new LinkZjcbDetailService();

    private short bodyBlackStyle;
    private short doubleBlackStyle;
    private String yearly;
    private String qcTimeStage;
    private String zljTimeStage;
    private String dnyqTimeStage;
    private String dnxzTimeStage;

    public void downloadSheet(Map<String, Object> reqMap, File file, String yearly,
                              String qcTimeStage, String zljTimeStage, String dnyqTimeStage, String dnxzTimeStage)
            throws IOException {
        OutputStream os = new FileOutputStream(file);
        this.yearly = yearly;
        this.qcTimeStage = qcTimeStage;
        this.zljTimeStage = zljTimeStage;
        this.dnyqTimeStage = dnyqTimeStage;
        this.dnxzTimeStage = dnxzTimeStage;

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("联动资金成本(垫佣资金)");

        Map<String, XSSFCellStyle> style = super.createStyles(wb);
        super.headerStyle = style.get(ExcelStyleEnum.HEADER.getValue()).getIndex();
        this.bodyBlackStyle = style.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();
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
        if (contentList != null && totalCount > contentList.size()) {
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
        nowColIndex = createBlock(sw, "序号", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "业绩归属城市", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "业绩所在城市", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "业绩归属中心", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "项目归属城市", 1, 2, listMerge);

        nowColIndex = createBlock(sw, "项目编号", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "项目名称", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "报备编号", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "楼室号", 1, 2, listMerge);

//        nowColIndex = createBlock(sw, "总累计", 1, 2, listMerge);
//        nowColIndex = createBlock(sw, yearly+"以前", 1, 2, listMerge);
//        nowColIndex = createBlock(sw, yearly + "新增", 1, 2, listMerge);

        nowColIndex = createBlock(sw, "期初" + qcTimeStage, 3, 1, listMerge);
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
        nowColIndex = createBlock(sw, "业绩归属城市", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "业绩所在城市", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "业绩归属中心", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "项目归属城市", 1, 0, listMerge);

        nowColIndex = createBlock(sw, "项目编号", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "项目名称", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "报备编号", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "楼室号", 1, 0, listMerge);

        nowColIndex = createBlock(sw, "返佣", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "垫佣", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "回款", 1, 1, listMerge);

        nowColIndex = createBlock(sw, "总累计" + zljTimeStage , 1, 1, listMerge);
        nowColIndex = createBlock(sw, "当年以前" + dnyqTimeStage, 1, 1, listMerge);
        nowColIndex = createBlock(sw, "当年新增" + dnxzTimeStage, 1, 1, listMerge);
        for (int i = 0; i < 12; i++) {
            nowColIndex = createBlock(sw, "超3个月垫佣金额", 1, 1, listMerge);
            nowColIndex = createBlock(sw, "超3个月垫佣回款", 1, 1, listMerge);
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
        short doubleBlack = doubleBlackStyle;
        sw.createCell(i++, map.get("rowNum") == null ? "" : map.get("rowNum").toString(), bodyStyle);

        sw.createCell(i++, map.get("areaCityName") == null ? "" : map.get("areaCityName").toString(), bodyStyle);

        sw.createCell(i++, map.get("cityGroupName") == null ? "" : map.get("cityGroupName").toString(), bodyStyle);

        sw.createCell(i++, map.get("centerGroupName") == null ? "" : map.get("centerGroupName").toString(), bodyStyle);

        sw.createCell(i++, map.get("projectCityName") == null ? "" : map.get("projectCityName").toString(), bodyStyle);

        sw.createCell(i++, map.get("projectNo") == null ? "" : map.get("projectNo").toString(), bodyStyle);

        sw.createCell(i++, map.get("estateNm") == null ? "" : map.get("estateNm").toString(), bodyStyle);

        sw.createCell(i++, map.get("reportId") == null ? "" : map.get("reportId").toString(), bodyStyle);

        sw.createCell(i++, map.get("buildingNo") == null ? "" : map.get("buildingNo").toString(), bodyStyle);

        sw.createCell(i++, clearZero(map.get("qcfy")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("qcdy")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("qchk")), doubleBlack);

        sw.createCell(i++, clearZero(map.get("totalAmount")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("preTotal")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("thisTotal")), doubleBlack);

        sw.createCell(i++, clearZero(map.get("dyjan")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("dyhkjan")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("zjcbjan")), doubleBlack);

        sw.createCell(i++, clearZero(map.get("dyfeb")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("dyhkfeb")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("zjcbfeb")), doubleBlack);

        sw.createCell(i++, clearZero(map.get("dymar")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("dyhkmar")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("zjcbmar")), doubleBlack);

        sw.createCell(i++, clearZero(map.get("dyapr")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("dyhkapr")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("zjcbapr")), doubleBlack);

        sw.createCell(i++, clearZero(map.get("dymay")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("dyhkmay")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("zjcbmay")), doubleBlack);

        sw.createCell(i++, clearZero(map.get("dyjun")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("dyhkjun")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("zjcbjun")), doubleBlack);

        sw.createCell(i++, clearZero(map.get("dyjul")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("dyhkjul")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("zjcbjul")), doubleBlack);

        sw.createCell(i++, clearZero(map.get("dyaug")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("dyhkaug")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("zjcbaug")), doubleBlack);

        sw.createCell(i++, clearZero(map.get("dysep")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("dyhksep")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("zjcbsep")), doubleBlack);

        sw.createCell(i++, clearZero(map.get("dyoct")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("dyhkoct")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("zjcboct")), doubleBlack);

        sw.createCell(i++, clearZero(map.get("dynov")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("dyhknov")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("zjcbnov")), doubleBlack);

        sw.createCell(i++, clearZero(map.get("dydec")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("dyhkdec")), doubleBlack);
        sw.createCell(i++, clearZero(map.get("zjcbdec")), doubleBlack);

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
        nColIndex = nColIndex + 5;

        //项目名称
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
        columnSet.add(c);
        nColIndex++;

        //报备编号及以后
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 4));
        c.setWidth(15);
        columnSet.add(c);
        nColIndex = nColIndex + 5;

        //资金成本列
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 2));
        c.setWidth(28);
        columnSet.add(c);
        nColIndex = nColIndex + 3;

        //资金成本列
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 35));
        c.setWidth(15);
        columnSet.add(c);
        nColIndex = nColIndex + 36;


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
    private void setCreateDoubleCell(String key, int i, short style, boolean fillWithZero, LinkedHashMap<String, Object> map, SpeedSheetWriter sw) throws Exception {
        double value = 0;
        Object o = map.get(key);
//        DecimalFormat formatter = new DecimalFormat("0.00");
        try {
            if (map.get(key) == null) {
                if (!fillWithZero) {
                    sw.createEmptyCell(i, style);
                    return;
                }
            } else {
                if (o instanceof String) {
                    String s = o.toString();
                    value = Double.valueOf(s);
                } else if (o instanceof Double) {
                    value = (Double) o;
                }
            }
        } catch (Exception e) {
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
