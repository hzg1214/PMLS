package cn.com.eju.deal.poi;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.common.util.DateUtils;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.houseLinkage.linkDetail.service.LinkDetailService;
import cn.com.eju.deal.poi.common.ExcelColumnSet;
import cn.com.eju.deal.poi.common.ExcelStyleEnum;
import cn.com.eju.deal.poi.common.ExcelUseXmlAbstract;
import cn.com.eju.deal.poi.common.SpeedSheetWriter;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelForLinkDetailBigData extends ExcelUseXmlAbstract {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    private LinkDetailService linkDetailService = new LinkDetailService();

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
        XSSFSheet sheet = wb.createSheet("联动明细");

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
            logger.info("导出联动明细报表-downLoadExcelAjax-downloadSheet-createSheet-start！");
            this.createSheet(fw, reqMap);
            logger.info("导出联动明细报表-downLoadExcelAjax-downloadSheet-createSheet-end！");
            fw.close();
            super.substitute(tempExcel, temp, sheetRef.substring(1), os);
        } catch (Exception e) {
            logger.info("导出联动明细报表失败",e);
            e.printStackTrace();
        } finally {
            os.close();
        }
    }

    private void createSheet(Writer out, Map<String, Object> reqMap) throws Exception {
        SpeedSheetWriter sw = new SpeedSheetWriter(out);
        sw.beginSheet(setColumnWidth(), 0, 3, "A4");
        int index = 0;
        List<String> listMerge = new ArrayList<>();
        index = this.createSheetHead(sw, index, listMerge);
        logger.info("导出联动明细报表-queryLinkDetailList-start！");
        PageInfo pageInfo = new PageInfo();
        pageInfo.setCurPage(1);
        pageInfo.setPageLimit(3000);
        logger.info("导出联动明细报表-第1次查询开始。。。");
        ResultData<?> reback = linkDetailService.queryLinkDetailList(reqMap, pageInfo);
        List<LinkedHashMap<String, Object>> contentList = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

        Integer totalCount = Integer.valueOf(reback.getTotalCount());
        logger.info("导出联动明细报表-第1次查询结束，totalCount:" + totalCount);
        if (contentList != null && contentList.size() > 0) {
            for (LinkedHashMap<String, Object> map : contentList) {
                index = this.createSheetBody(sw, index, map);
            }
        }
        logger.info("导出联动明细报表-第1次写文件开始。。。");
        if (totalCount > contentList.size()) {
            Integer pageCount = (int) Math.ceil(Double.valueOf(totalCount) / Double.valueOf(contentList.size()));
            for (int i = 2; i <= pageCount; i++) {
                pageInfo.setCurPage(i);
                logger.info("导出联动明细报表-第" + i + "次查询开始,共" + pageCount + "次。");
                reback = linkDetailService.queryLinkDetailList(reqMap, pageInfo);
                logger.info("导出联动明细报表-第" + i + "次查询结束,共" + pageCount + "次。");
                contentList = (List<LinkedHashMap<String, Object>>) reback.getReturnData();
                if (contentList != null && contentList.size() > 0) {
                    for (LinkedHashMap<String, Object> map : contentList) {
                        index = this.createSheetBody(sw, index, map);
                    }
                }
                logger.info("导出联动明细报表-第" + i + "次写文件结束。");
            }
        }
        logger.info("导出联动明细报表-查询结束！");
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
        nowColIndex = createBlock(sw, "业绩信息", 13, 1, listMerge);
        nowColIndex = createBlock(sw, "案件信息", 36, 1, listMerge);

        nowColIndex = createBlock(sw, "总累计应计", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "当年以前应计", 8, 1, listMerge);
        nowColIndex = createBlock(sw, yearly + "应计", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "1月应计", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "2月应计", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "3月应计", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "4月应计", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "5月应计", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "6月应计", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "7月应计", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "8月应计", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "9月应计", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "10月应计", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "11月应计", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "12月应计", 8, 1, listMerge);

        //应收收入
        nowColIndex = createBlock(sw, "总累计应收收入", 2, 2, listMerge);
        nowColIndex = createBlock(sw, "当年以前应收收入", 2, 2, listMerge);
        nowColIndex = createBlock(sw, yearly + "年应收收入合计", 2, 2, listMerge);
        nowColIndex = createBlock(sw, yearly + "年应收收入", 24, 1, listMerge);

        nowColIndex = createBlock(sw, "总累计实际", 10, 1, listMerge);
        nowColIndex = createBlock(sw, "当年以前实际", 8, 1, listMerge);
        nowColIndex = createBlock(sw, yearly + "实际", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "1月实际", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "2月实际", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "3月实际", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "4月实际", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "5月实际", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "6月实际", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "7月实际", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "8月实际", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "9月实际", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "10月实际", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "11月实际", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "12月实际", 8, 1, listMerge);

        nowColIndex = createBlock(sw, "岗位佣金小计", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "团佣小计", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "备注", 1, 3, listMerge);

        nowColIndex = createBlock(sw, "人员信息", 6, 1, listMerge);

        nowColIndex = createBlock(sw, "返佣对象一", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "应计返佣税前", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "应计返佣税后", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "返佣对象二", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "应计返佣税前", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "应计返佣税后", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "返佣对象三", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "应计返佣税前", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "应计返佣税后", 1, 3, listMerge);

        sw.endRow();
        //第二行
        sw.insertRow(++nowRowIndex);
        nowColIndex = 0;
        nowColIndex = createBlock(sw, "序号", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "核算主体编号", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "核算主体", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "业绩归属区域", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "业绩归属城市", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "业绩所在城市", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "业绩归属事业部", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "业绩归属中心", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "业绩归属组", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "业绩归属人", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "业绩归属人工号", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "项目归属城市", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "项目归属部门", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "收入归属城市", 1, 2, listMerge);

        nowColIndex = createBlock(sw, "来源", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "经纪公司", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "客户", 3, 1, listMerge);
        nowColIndex = createBlock(sw, "系统编号", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "项目编号", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "楼盘名", 1, 2, listMerge);

        nowColIndex = createBlock(sw, "合作方名称", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "是否大客户", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "大客户名称", 1, 2, listMerge);

        nowColIndex = createBlock(sw, "是否垫佣", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "报备编号", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "报备时间", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "带看时间", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "认筹时间", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "楼室号", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "套数", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "预退", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "大定", 5, 1, listMerge);
        nowColIndex = createBlock(sw, "成销", 5, 1, listMerge);

        for (int i = 1; i <= 15; i++) {
            nowColIndex = createBlock(sw, "成销收入", 2, 1, listMerge);
            nowColIndex = createBlock(sw, "返佣金额", 2, 1, listMerge);
            nowColIndex = createBlock(sw, "成销净收", 2, 1, listMerge);
            nowColIndex = createBlock(sw, "达垫佣节点的垫佣", 2, 1, listMerge);
        }
        
        /* for (int i=1;i<=15;i++){
        nowColIndex = createBlock(sw, "成销收入", 2, 1, listMerge);
        nowColIndex = createBlock(sw, "返佣金额", 2, 1, listMerge);
        nowColIndex = createBlock(sw, "成销净收", 2, 1, listMerge);
        nowColIndex = createBlock(sw, "达垫佣节点的垫佣", 2, 1, listMerge);
    	}*/
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);

        //应收收入
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        for (int i = 1; i <= 12; i++) {
            nowColIndex = createBlock(sw, i + "月应收", 2, 1, listMerge);
        }
        nowColIndex = createBlock(sw, "实际收入", 2, 1, listMerge);
        nowColIndex = createBlock(sw, "实际返佣", 2, 1, listMerge);
        nowColIndex = createBlock(sw, "实际净收", 2, 1, listMerge);
        nowColIndex = createBlock(sw, "达垫佣节点的实付垫佣", 2, 1, listMerge);
        nowColIndex = createBlock(sw, "垫佣回款", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "当前仍处于垫付状态的垫佣", 1, 1, listMerge);

        for (int i = 1; i <= 14; i++) {
            nowColIndex = createBlock(sw, "实际收入", 2, 1, listMerge);
            nowColIndex = createBlock(sw, "实际返佣", 2, 1, listMerge);
            nowColIndex = createBlock(sw, "实际净收", 2, 1, listMerge);
            nowColIndex = createBlock(sw, "达垫佣节点的实付垫佣", 2, 1, listMerge);
        }

        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);

        nowColIndex = createBlock(sw, "拓展维护人员", 2, 1, listMerge);
        nowColIndex = createBlock(sw, "拓展维护经理", 2, 1, listMerge);
        nowColIndex = createBlock(sw, "拓展维护总监", 2, 1, listMerge);

        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);

        sw.endRow();
        //第三行
        sw.insertRow(++nowRowIndex);
        nowColIndex = 0;
        nowColIndex = createBlock(sw, "序号", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);

        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "门店编号", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "门店名称", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "公司名称", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "门店地址", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "门店规模", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "合作模式", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "业务员姓名", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "业务员电话", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "客户姓名", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "联系电话", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "人数", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "大定面积", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "大定单价", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "大定总价", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "大定日期", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "大定审核通过日期", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "成销面积", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "成销单价", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "成销总价", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "成销日期", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "收入类型", 1, 1, listMerge);

        for (int i = 1; i <= 60; i++) {
            nowColIndex = createBlock(sw, "税前", 1, 1, listMerge);
            nowColIndex = createBlock(sw, "税后", 1, 1, listMerge);
        }
        //应收收入
        for (int i = 1; i <= 15; i++) {
            nowColIndex = createBlock(sw, "税前", 1, 1, listMerge);
            nowColIndex = createBlock(sw, "税后", 1, 1, listMerge);
        }

        nowColIndex = createBlock(sw, "税前", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "税后", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "税前", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "税后", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "税前", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "税后", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "税前", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "税后", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "税前", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "税前", 1, 1, listMerge);

        for (int i = 1; i <= 56; i++) {
            nowColIndex = createBlock(sw, "税前", 1, 1, listMerge);
            nowColIndex = createBlock(sw, "税后", 1, 1, listMerge);
        }

        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);

        nowColIndex = createBlock(sw, "姓名", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "工号", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "姓名", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "工号", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "姓名", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "工号", 1, 1, listMerge);

        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);
        nowColIndex = createBlock(sw, "", 1, 0, listMerge);

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

        short bodyStyle = "1".equals(map.get("suitNum").toString()) ? bodyBlackStyle : bodyRedStyle;
        short dateStyle = "1".equals(map.get("suitNum").toString()) ? dateBlackStyle : dateRedStyle;
        short moneyStyle = "1".equals(map.get("suitNum").toString()) ? moneyBlackStyle : moneyRedStyle;
        short intStyle = "1".equals(map.get("suitNum").toString()) ? intBlackStyle : intRedStyle;

        sw.createCell(i++, map.get("rowNum") == null ? "" : map.get("rowNum").toString(), bodyStyle);
        sw.createCell(i++, map.get("accountProjectNo") == null ? "" : map.get("accountProjectNo").toString(), bodyStyle);
        sw.createCell(i++, map.get("accountProject") == null ? "" : map.get("accountProject").toString(), bodyStyle);
        sw.createCell(i++, map.get("regionName") == null ? "" : map.get("regionName").toString(), bodyStyle);
        sw.createCell(i++, map.get("areaCityName") == null ? "" : map.get("areaCityName").toString(), bodyStyle);
        sw.createCell(i++, map.get("cityGroupName") == null ? "" : map.get("cityGroupName").toString(), bodyStyle);
        sw.createCell(i++, map.get("areaGroupName") == null ? "" : map.get("areaGroupName").toString(), bodyStyle);
        sw.createCell(i++, map.get("centerGroupName") == null ? "" : map.get("centerGroupName").toString(), bodyStyle);
        sw.createCell(i++, map.get("groupName") == null ? "" : map.get("groupName").toString(), bodyStyle);
        sw.createCell(i++, map.get("expenderName") == null ? "" : map.get("expenderName").toString(), bodyStyle);
        sw.createCell(i++, map.get("expenderCode") == null ? "" : map.get("expenderCode").toString(), bodyStyle);
        sw.createCell(i++, map.get("projectCityName") == null ? "" : map.get("projectCityName").toString(), bodyStyle);
        sw.createCell(i++, map.get("projectDepartmentName") == null ? "" : map.get("projectDepartmentName").toString(), bodyStyle);
        sw.createCell(i++, map.get("srCityName") == null ? "" : map.get("srCityName").toString(), bodyStyle);

        sw.createCell(i++, map.get("customerFromName") == null ? "" : map.get("customerFromName").toString(), bodyStyle);
        sw.createCell(i++, map.get("storeNo") == null ? "" : map.get("storeNo").toString(), bodyStyle);
        sw.createCell(i++, map.get("storeName") == null ? "" : map.get("storeName").toString(), bodyStyle);
        sw.createCell(i++, map.get("companyName") == null ? "" : map.get("companyName").toString(), bodyStyle);
        sw.createCell(i++, map.get("addressDetail") == null ? "" : map.get("addressDetail").toString(), bodyStyle);
        sw.createCell(i++, map.get("storeSize") == null ? "" : map.get("storeSize").toString(), bodyStyle);
        sw.createCell(i++, map.get("contractTypeName") == null ? "" : map.get("contractTypeName").toString(), bodyStyle);
        sw.createCell(i++, map.get("saleName") == null ? "" : map.get("saleName").toString(), bodyStyle);
        sw.createCell(i++, map.get("salePhone") == null ? "" : map.get("salePhone").toString(), bodyStyle);

        sw.createCell(i++, map.get("customerNm") == null ? "" : map.get("customerNm").toString(), bodyStyle);
        sw.createCell(i++, map.get("customerTel") == null ? "" : map.get("customerTel").toString(), bodyStyle);
        sw.createCell(i++, map.get("customerNum") == null ? "" : map.get("customerNum").toString(), bodyStyle);

        sw.createCell(i++, map.get("estateId") == null ? "" : map.get("estateId").toString(), bodyStyle);
        sw.createCell(i++, map.get("projectNo") == null ? "" : map.get("projectNo").toString(), bodyStyle);
        sw.createCell(i++, map.get("estateNm") == null ? "" : map.get("estateNm").toString(), bodyStyle);

        sw.createCell(i++, map.get("partnerNm") == null ? "" : map.get("partnerNm").toString(), bodyStyle);
        sw.createCell(i++, map.get("bigCustomerFlagStr") == null ? "" : map.get("bigCustomerFlagStr").toString(), bodyStyle);
        sw.createCell(i++, map.get("bigCustomerName") == null ? "" : map.get("bigCustomerName").toString(), bodyStyle);

        sw.createCell(i++, map.get("prepaidName") == null ? "" : map.get("prepaidName").toString(), bodyStyle);
        sw.createCell(i++, map.get("reportId") == null ? "" : map.get("reportId").toString(), bodyStyle);
        setCreateDateCell("reportDate", i++, dateStyle, map, sw);
        setCreateDateCell("seeDate", i++, dateStyle, map, sw);
        setCreateDateCell("pledgedDate", i++, dateStyle, map, sw);
        sw.createCell(i++, map.get("buildingNo") == null ? "" : map.get("buildingNo").toString(), bodyStyle);
        sw.createCell(i++, clearIntZero(map.get("suitNum")), bodyStyle);

        String preBack = (String)map.get("preBack");
        String preBackStr = "";
        if("1".equals(preBack)){
            preBackStr = "预退定";
        }else if("2".equals(preBack)){
            preBackStr = "预退房";
        }
        sw.createCell(i++, preBackStr, bodyStyle);

        sw.createCell(i++, clearZero(map.get("roughArea")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("roughPrice")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("roughAmount")), moneyStyle);
        setCreateDateCell("roughDate", i++, dateStyle, map, sw);
        setCreateDateCell("roughAuditTime", i++, dateStyle, map, sw);

        sw.createCell(i++, clearZero(map.get("dealArea")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dealPrice")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("dealAmount")), moneyStyle);
        setCreateDateCell("dealDate", i++, dateStyle, map, sw);
        sw.createCell(i++, map.get("incomeStatusStr") == null ? "" : map.get("incomeStatusStr").toString(), bodyStyle);

        /********* 应计 begin **********/

        sw.createCell(i++, clearZero(map.get("totalEPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalEATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalEPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalEATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalEPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalEATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalEPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalEATPrepaid")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("beforeEPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("beforeEATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("beforeEPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("beforeEATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("beforeEPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("beforeEATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("beforeEPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("beforeEATPrepaid")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("thisEPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("thisEATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("thisEPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("thisEATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("thisEPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("thisEATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("thisEPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("thisEATPrepaid")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("janEPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("janEATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("janEPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("janEATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("janEPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("janEATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("janEPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("janEATPrepaid")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("febEPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("febEATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("febEPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("febEATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("febEPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("febEATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("febEPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("febEATPrepaid")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("marEPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("marEATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("marEPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("marEATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("marEPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("marEATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("marEPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("marEATPrepaid")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("aprEPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("aprEATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("aprEPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("aprEATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("aprEPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("aprEATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("aprEPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("aprEATPrepaid")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("mayEPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("mayEATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("mayEPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("mayEATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("mayEPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("mayEATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("mayEPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("mayEATPrepaid")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("junEPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("junEATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("junEPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("junEATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("junEPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("junEATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("junEPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("junEATPrepaid")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("julEPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("julEATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("julEPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("julEATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("julEPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("julEATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("julEPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("julEATPrepaid")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("augEPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("augEATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("augEPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("augEATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("augEPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("augEATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("augEPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("augEATPrepaid")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("sepEPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("sepEATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("sepEPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("sepEATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("sepEPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("sepEATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("sepEPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("sepEATPrepaid")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("octEPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("octEATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("octEPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("octEATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("octEPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("octEATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("octEPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("octEATPrepaid")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("novEPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("novEATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("novEPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("novEATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("novEPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("novEATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("novEPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("novEATPrepaid")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("decEPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("decEATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("decEPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("decEATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("decEPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("decEATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("decEPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("decEATPrepaid")), moneyStyle);

        /********* 应计 end **********/

        /********* 应收 begin **********/
        sw.createCell(i++, clearZero(map.get("totalRBPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalRBATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("beforeRBPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("beforeRBATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("thisRBPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("thisRBATIncome")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("janRBPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("janRBATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("febRBPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("febRBBATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("marRBPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("marRBATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("aprRBPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("aprRBATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("mayRBPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("mayRBATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("junRBPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("junRBATIncome")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("julRBPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("julRBATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("augRBPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("augRBATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("sepRBPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("sepRBATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("octRBPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("octRBATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("novRBPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("novRBATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("decRBPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("decRBATIncome")), moneyStyle);
        /********* 应收 end **********/

        /********* 实际 begin **********/

        sw.createCell(i++, clearZero(map.get("totalAPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalAATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalAPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalAATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalAPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalAATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalAPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalAATPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalAPTReceive")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalAPTBalance")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("beforeAPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("beforeAATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("beforeAPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("beforeAATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("beforeAPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("beforeAATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("beforeAPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("beforeAATPrepaid")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("thisAPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("thisAATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("thisAPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("thisAATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("thisAPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("thisAATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("thisAPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("thisAATPrepaid")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("janAPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("janAATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("janAPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("janAATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("janAPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("janAATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("janAPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("janAATPrepaid")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("febAPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("febAATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("febAPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("febAATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("febAPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("febAATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("febAPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("febAATPrepaid")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("marAPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("marAATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("marAPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("marAATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("marAPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("marAATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("marAPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("marAATPrepaid")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("aprAPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("aprAATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("aprAPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("aprAATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("aprAPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("aprAATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("aprAPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("aprAATPrepaid")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("mayAPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("mayAATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("mayAPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("mayAATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("mayAPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("mayAATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("mayAPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("mayAATPrepaid")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("junAPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("junAATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("junAPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("junAATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("junAPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("junAATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("junAPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("junAATPrepaid")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("julAPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("julAATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("julAPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("julAATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("julAPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("julAATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("julAPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("julAATPrepaid")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("augAPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("augAATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("augAPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("augAATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("augAPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("augAATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("augAPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("augAATPrepaid")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("sepAPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("sepAATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("sepAPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("sepAATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("sepAPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("sepAATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("sepAPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("sepAATPrepaid")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("octAPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("octAATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("octAPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("octAATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("octAPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("octAATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("octAPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("octAATPrepaid")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("novAPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("novAATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("novAPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("novAATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("novAPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("novAATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("novAPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("novAATPrepaid")), moneyStyle);

        sw.createCell(i++, clearZero(map.get("decAPTIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("decAATIncome")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("decAPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("decAATCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("decAPTProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("decAATProfit")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("decAPTPrepaid")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("decAATPrepaid")), moneyStyle);

        /********* 实际 end **********/

        /********* 内佣 begin **********/
        sw.createCell(i++, clearZero(map.get("totalNPTCommission")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("totalNATCommission")), moneyStyle);
        /********* 内佣 end **********/

        sw.createCell(i++, map.get("remark") == null ? "" : map.get("remark").toString(), moneyStyle);

        sw.createCell(i++, map.get("expenderName") == null ? "" : map.get("expenderName").toString(), bodyStyle);
        sw.createCell(i++, map.get("expenderCode") == null ? "" : map.get("expenderCode").toString(), bodyStyle);
        sw.createCell(i++, map.get("groupLeaderName") == null ? "" : map.get("groupLeaderName").toString(), bodyStyle);
        sw.createCell(i++, map.get("groupLeaderCode") == null ? "" : map.get("groupLeaderCode").toString(), bodyStyle);
        sw.createCell(i++, map.get("areaLeaderName") == null ? "" : map.get("areaLeaderName").toString(), bodyStyle);
        sw.createCell(i++, map.get("areaLeaderCode") == null ? "" : map.get("areaLeaderCode").toString(), bodyStyle);

        sw.createCell(i++, map.get("fyObject1") == null ? "" : map.get("fyObject1").toString(), bodyStyle);
        sw.createCell(i++, clearZero(map.get("befYJFY1")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("aftYJFY1")), moneyStyle);
        sw.createCell(i++, map.get("fyObject2") == null ? "" : map.get("fyObject2").toString(), bodyStyle);
        sw.createCell(i++, clearZero(map.get("befYJFY2")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("aftYJFY2")), moneyStyle);
        sw.createCell(i++, map.get("fyObject3") == null ? "" : map.get("fyObject3").toString(), bodyStyle);
        sw.createCell(i++, clearZero(map.get("befYJFY3")), moneyStyle);
        sw.createCell(i, clearZero(map.get("aftYJFY3")), moneyStyle);

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
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 12));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex += 13;

        //区域
//        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
//        c.setWidth(12);
//        columnSet.add(c);
//        nColIndex++;
//
//        //中心
//        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
//        c.setWidth(13);
//        columnSet.add(c);
//        nColIndex++;
//
//        //小组
//        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
//        c.setWidth(13);
//        columnSet.add(c);
//        nColIndex++;
//
//        //归属人
//        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
//        c.setWidth(10);
//        columnSet.add(c);
//        nColIndex++;

        //来源
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;

        //门店编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //门店名称、门店地址
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 2));
        c.setWidth(25);
        columnSet.add(c);
        nColIndex = nColIndex + 3;

        //门店规模、合作模式
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex+1));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex = nColIndex + 2;

        //业务员姓名、业务员电话、客户姓名、客户电话
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 3));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex = nColIndex + 4;

        //人数
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(5);
        columnSet.add(c);
        nColIndex++;

        //系统编号、项目编号、楼盘名、报备编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 7));
        c.setWidth(16);
        columnSet.add(c);
        nColIndex = nColIndex + 8;

        //报备时间、带看时间、认筹时间
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 2));
        c.setWidth(11);
        columnSet.add(c);
        nColIndex = nColIndex + 3;

        //楼室号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //套数
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(6);
        columnSet.add(c);
        nColIndex++;

        //大定
        //面积
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;

        //单价
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //金额
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex++;

        //日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(13);
        columnSet.add(c);
        nColIndex++;

        //成销
        //面积
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;

        //单价
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //金额
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex++;

        //日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(11);
        columnSet.add(c);
        nColIndex++;

        //收入类型
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex++;

        //应计
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 23));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 24;

        //应计月份
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 95));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex = nColIndex + 96;

        //应收
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 5));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 6;

        //应收月份
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 23));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex = nColIndex + 24;

        //实际
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 25));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 26;

        //实际月份
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 95));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex = nColIndex + 96;

        //内佣
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 1));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 2;

        //备注
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //人员信息
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 5));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex = nColIndex + 6;

        //返佣对象
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(25);
        columnSet.add(c);
        nColIndex++;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 1));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 2;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(25);
        columnSet.add(c);
        nColIndex++;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 1));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 2;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(25);
        columnSet.add(c);
        nColIndex++;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 1));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 2;


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
