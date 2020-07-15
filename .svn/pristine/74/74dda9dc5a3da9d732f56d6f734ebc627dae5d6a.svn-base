package cn.com.eju.deal.poi;

import cn.com.eju.deal.Report.service.ExpendReportService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.common.util.DateUtils;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.poi.common.ExcelColumnSet;
import cn.com.eju.deal.poi.common.ExcelStyleEnum;
import cn.com.eju.deal.poi.common.ExcelUseXmlAbstract;
import cn.com.eju.deal.poi.common.SpeedSheetWriter;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelForExpendBigData extends ExcelUseXmlAbstract {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    private ExpendReportService expendReportService=new ExpendReportService();

    private short bodyStyleBlack;
    private short bodyStyleRed;
    private short dateStyleBlack;
    private short dateStyleRed;
    private short moneyStyleBlack;
    private short moneyStyleRed;

    private XSSFSheet sheet;

    public void downloadSheet(Map<String, Object> reqMap, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook();
        sheet = wb.createSheet("拓展明细");

        Map<String, XSSFCellStyle> style = super.createStyles(wb);
        super.headerStyle = style.get(ExcelStyleEnum.HEADER.getValue()).getIndex();
        this.bodyStyleBlack = style.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();
        this.bodyStyleRed = style.get(ExcelStyleEnum.BODY_RED.getValue()).getIndex();
        this.dateStyleBlack = style.get(ExcelStyleEnum.DATE_BLACK.getValue()).getIndex();
        this.dateStyleRed = style.get(ExcelStyleEnum.DATE_RED.getValue()).getIndex();
        this.moneyStyleBlack = style.get(ExcelStyleEnum.MONEY_LOCKED_BLACK.getValue()).getIndex();
        this.moneyStyleRed = style.get(ExcelStyleEnum.MONEY_LOCKED_RED.getValue()).getIndex();

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
        sw.beginSheet(setColumnWidth(), 0, 2, "A3");
        List<String> listMerge = new ArrayList<String>();
        int index = 0;
        index = this.createSheetHead(sw, index, listMerge);

        PageInfo pageInfo = new PageInfo();
        pageInfo.setCurPage(1);
        pageInfo.setPageLimit(5000);
        ResultData<?> reback = expendReportService.queryExpandDetailList(reqMap,pageInfo);
        List<LinkedHashMap<String, Object>> contentList = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

        Integer totalCount = Integer.valueOf(reback.getTotalCount());
        logger.info("第一次查询，totalCount:" + totalCount + ",时间：" + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
        if (contentList != null && contentList.size() > 0) {
            for (LinkedHashMap<String, Object> map : contentList) {
                index = this.createSheetBody(sw, index, map);
            }
        }
        if (totalCount > contentList.size()) {
            Integer pageCount = (int) Math.ceil(Double.valueOf(totalCount) / (double) contentList.size());
            for (int i = 2; i <= pageCount; i++) {
                pageInfo.setCurPage(i);
                reback = expendReportService.queryExpandDetailList(reqMap,pageInfo);
                logger.info("第" + i + "次查询,共" + pageCount + "次。时间：" + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
                contentList = (List<LinkedHashMap<String, Object>>) reback.getReturnData();
                if (contentList != null && contentList.size() > 0) {
                    for (LinkedHashMap<String, Object> map : contentList) {
                        index = this.createSheetBody(sw, index, map);
                    }
                }
            }
        }
        logger.info("查询完成，时间：" + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
        sw.endSheetWithMerge(listMerge);

//        //自动调整列宽
//        if (nowColIndex > 0) {
//            for (int i = 46; i < nowColIndex - 1; i++) {
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
        //第一行
        sw.insertRow(nowRowIndex);
        nowColIndex = createBlock(sw, "序号", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "业绩信息", 7, 1, listMerge);
        nowColIndex = createBlock(sw, "公司信息", 7, 1, listMerge);
        nowColIndex = createBlock(sw, "门店信息", 25, 1, listMerge);
        nowColIndex = createBlock(sw, "合同信息", 19, 1, listMerge);
        nowColIndex = createBlock(sw, "翻牌业绩信息", 3, 1, listMerge);
        nowColIndex = createBlock(sw, "乙转甲", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "三方变更", 7, 1, listMerge);
        nowColIndex = createBlock(sw, "续签", 6, 1, listMerge);
        nowColIndex = createBlock(sw, "保证金信息", 6, 1, listMerge);
        nowColIndex = createBlock(sw, "平台服务", 5, 1, listMerge);
        nowColIndex = createBlock(sw, "翻牌1.0情况", 3, 1, listMerge);
        nowColIndex = createBlock(sw, "翻牌1.0请款情况", 7, 1, listMerge);
        nowColIndex = createBlock(sw, "翻牌2.0情况", 14, 1, listMerge);
        nowColIndex = createBlock(sw, "翻牌2.0请款情况", 7, 1, listMerge);
        nowColIndex = createBlock(sw, "翻牌3.0情况", 14, 1, listMerge);
        nowColIndex = createBlock(sw, "翻牌3.0请款情况", 7, 1, listMerge);
        nowColIndex = createBlock(sw, "累计装修", 2, 1, listMerge);
        nowColIndex = createBlock(sw, "门店录入信息", 3, 1, listMerge);
        nowColIndex = createBlock(sw, "业绩归属人员信息", 6, 1, listMerge);
        nowColIndex = createBlock(sw, "门店维护人员信息", 6, 1, listMerge);
        nowColIndex = createBlock(sw, "备注", 1, 2, listMerge);
        sw.endRow();

        nowRowIndex = nowRowIndex + 1;

        nowColIndex = 0;


        //第二行
        //业绩信息
        sw.insertRow(nowRowIndex);
        nowColIndex = createBlock(sw, "序号", 1, 1, null);
        nowColIndex = createBlock(sw, "业绩归属区域", 1, 1, null);
        nowColIndex = createBlock(sw, "业绩归属城市", 1, 1, null);
        nowColIndex = createBlock(sw, "业绩所在城市", 1, 1, null);
        nowColIndex = createBlock(sw, "业绩归属事业部", 1, 1, null);
        nowColIndex = createBlock(sw, "业绩归属中心", 1, 1, null);
        nowColIndex = createBlock(sw, "业绩归属组", 1, 1, null);
        nowColIndex = createBlock(sw, "业绩归属人", 1, 1, null);

        //公司信息
        nowColIndex = createBlock(sw, "公司编号", 1, 1, null);
        nowColIndex = createBlock(sw, "公司名称", 1, 1, null);
        nowColIndex = createBlock(sw, "公司经营地址", 1, 1, null);
        nowColIndex = createBlock(sw, "法人", 1, 1, null);
        nowColIndex = createBlock(sw, "公司注册地址", 1, 1, null);
        nowColIndex = createBlock(sw, "营业执照编码", 1, 1, null);
        nowColIndex = createBlock(sw, "营业执照性质", 1, 1, null);

        //门店信息
        nowColIndex = createBlock(sw, "门店编号", 1, 1, null);
        nowColIndex = createBlock(sw, "挂牌名称", 1, 1, null);
        nowColIndex = createBlock(sw, "门店城市", 1, 1, null);
        nowColIndex = createBlock(sw, "所属行政区", 1, 1, null);
        nowColIndex = createBlock(sw, "门店所属中心", 1, 1, null);
        nowColIndex = createBlock(sw, "门店店招尺寸(m*m)", 1, 1, null);
        nowColIndex = createBlock(sw, "门店地址", 1, 1, null);
        nowColIndex = createBlock(sw, "门店规模", 1, 1, null);
        nowColIndex = createBlock(sw, "门店类型", 1, 1, null);
        nowColIndex = createBlock(sw, "经营场所类型", 1, 1, null);
        nowColIndex = createBlock(sw, "门店资质等级", 1, 1, null);
        nowColIndex = createBlock(sw, "门店关联1.0房友账号", 1, 1, null);
        nowColIndex = createBlock(sw, "门店负责人", 1, 1, null);
        nowColIndex = createBlock(sw, "联系电话", 1, 1, null);
        nowColIndex = createBlock(sw, "当前使用系统", 1, 1, null);
        nowColIndex = createBlock(sw, "加盟到期时间", 1, 1, null);
        nowColIndex = createBlock(sw, "门店租赁到期时间", 1, 1, null);
        nowColIndex = createBlock(sw, "连锁情况", 1, 1, null);
        nowColIndex = createBlock(sw, "连锁品牌", 1, 1, null);
        nowColIndex = createBlock(sw, "经纪人数", 1, 1, null);
        nowColIndex = createBlock(sw, "主营业务", 1, 1, null);
        nowColIndex = createBlock(sw, "交易方式", 1, 1, null);
        nowColIndex = createBlock(sw, "门店信息完整度", 1, 1, null);
        nowColIndex = createBlock(sw, "门店签约状况", 1, 1, null);
        nowColIndex = createBlock(sw, "营业状态", 1, 1, null);

        //合同信息
        nowColIndex = createBlock(sw, "合同编号", 1, 1, null);
        nowColIndex = createBlock(sw, "合作模式", 1, 1, null);
        nowColIndex = createBlock(sw, "客户公司帐号", 1, 1, null);
        nowColIndex = createBlock(sw, "开户名", 1, 1, null);
        nowColIndex = createBlock(sw, "开户银行", 1, 1, null);
        nowColIndex = createBlock(sw, "甲方收件人", 1, 1, null);
        nowColIndex = createBlock(sw, "甲方收件地址", 1, 1, null);
        nowColIndex = createBlock(sw, "协议书编号", 1, 1, null);
        nowColIndex = createBlock(sw, "合作开始日期", 1, 1, null);
        nowColIndex = createBlock(sw, "合作到期日期", 1, 1, null);
        nowColIndex = createBlock(sw, "门店数(扣除)", 1, 1, null);
        nowColIndex = createBlock(sw, "门店数(不扣除)", 1, 1, null);
        nowColIndex = createBlock(sw, "违约金金额", 1, 1, null);
        nowColIndex = createBlock(sw, "草签日期", 1, 1, null);
        nowColIndex = createBlock(sw, "签约类型", 1, 1, null);
        nowColIndex = createBlock(sw, "OA审核通过日期", 1, 1, null);
        nowColIndex = createBlock(sw, "OA审核撤销日期", 1, 1, null);
        nowColIndex = createBlock(sw, "OA审核终止日期", 1, 1, null);
        nowColIndex = createBlock(sw, "合同审核状态", 1, 1, null);

        //翻牌业绩信息
        nowColIndex = createBlock(sw, "翻牌模式", 1, 1, null);
        nowColIndex = createBlock(sw, "装修类型", 1, 1, null);
        nowColIndex = createBlock(sw, "翻牌日期", 1, 1, null);

        //乙转甲
        nowColIndex = createBlock(sw, "合同编号", 1, 1, null);
        nowColIndex = createBlock(sw, "协议书编号", 1, 1, null);
        nowColIndex = createBlock(sw, "公司编号", 1, 1, null);
        nowColIndex = createBlock(sw, "公司名称", 1, 1, null);
        nowColIndex = createBlock(sw, "乙转甲起始日期", 1, 1, null);
        nowColIndex = createBlock(sw, "合作结束日期", 1, 1, null);
        nowColIndex = createBlock(sw, "OA审核通过日期", 1, 1, null);
        nowColIndex = createBlock(sw, "业绩归属人", 1, 1, null);
        
        //三方变更
        nowColIndex = createBlock(sw, "合同编号", 1, 1, null);
        nowColIndex = createBlock(sw, "公司编号", 1, 1, null);
        nowColIndex = createBlock(sw, "公司名称", 1, 1, null);
        nowColIndex = createBlock(sw, "变更起始日期", 1, 1, null);
        nowColIndex = createBlock(sw, "变更结束日期", 1, 1, null);
        nowColIndex = createBlock(sw, "OA审核通过日期", 1, 1, null);
        nowColIndex = createBlock(sw, "业绩归属人", 1, 1, null);

        //续签
        nowColIndex = createBlock(sw, "合同编号", 1, 1, null);
        nowColIndex = createBlock(sw, "协议书编号", 1, 1, null);
        nowColIndex = createBlock(sw, "续签起始日期", 1, 1, null);
        nowColIndex = createBlock(sw, "合作结束日期", 1, 1, null);
        nowColIndex = createBlock(sw, "OA审核通过日期", 1, 1, null);
        nowColIndex = createBlock(sw, "业绩归属人", 1, 1, null);

        //保证金信息
        nowColIndex = createBlock(sw, "应收金额", 1, 1, null);
        nowColIndex = createBlock(sw, "到账金额", 1, 1, null);
        nowColIndex = createBlock(sw, "未收金额", 1, 1, null);
        nowColIndex = createBlock(sw, "到账日期", 1, 1, null);
        nowColIndex = createBlock(sw, "退款金额", 1, 1, null);
        nowColIndex = createBlock(sw, "退款日期", 1, 1, null);

        //平台服务费
        nowColIndex = createBlock(sw, "应收金额", 1, 1, null);
        nowColIndex = createBlock(sw, "到账金额", 1, 1, null);
        nowColIndex = createBlock(sw, "到账日期", 1, 1, null);
        nowColIndex = createBlock(sw, "到期日期", 1, 1, null);
        nowColIndex = createBlock(sw, "状态", 1, 1, null);

        //翻牌1.0情况
        nowColIndex = createBlock(sw, "装修公司", 1, 1, null);
        nowColIndex = createBlock(sw, "结算价格合计", 1, 1, null);
        nowColIndex = createBlock(sw, "翻牌完成时间", 1, 1, null);

        //翻牌请款情况（1.0）
        nowColIndex = createBlock(sw, "已付金额", 1, 1, null);
        nowColIndex = createBlock(sw, "第一次请款", 1, 1, null);
        nowColIndex = createBlock(sw, "第一次请款日期", 1, 1, null);
        nowColIndex = createBlock(sw, "第二次请款", 1, 1, null);
        nowColIndex = createBlock(sw, "第二次请款日期", 1, 1, null);
        nowColIndex = createBlock(sw, "第三次请款", 1, 1, null);
        nowColIndex = createBlock(sw, "第三次请款日期", 1, 1, null);

        //翻牌2.0情况
        nowColIndex = createBlock(sw, "装修公司", 1, 1, null);
        nowColIndex = createBlock(sw, "翻牌装修申请日期", 1, 1, null);
        nowColIndex = createBlock(sw, "翻牌申请通过时间", 1, 1, null);
        nowColIndex = createBlock(sw, "OA翻牌申请单号", 1, 1, null);
        nowColIndex = createBlock(sw, "报价小计", 1, 1, null);
        nowColIndex = createBlock(sw, "标注项目报价", 1, 1, null);
        nowColIndex = createBlock(sw, "增加项目报价", 1, 1, null);
        nowColIndex = createBlock(sw, "翻牌完成时间", 1, 1, null);
        nowColIndex = createBlock(sw, "翻牌验收通过时间", 1, 1, null);
        nowColIndex = createBlock(sw, "OA翻牌验收单号", 1, 1, null);
        nowColIndex = createBlock(sw, "装修进度", 1, 1, null);
        nowColIndex = createBlock(sw, "结算价格合计", 1, 1, null);
        nowColIndex = createBlock(sw, "标准项结算价", 1, 1, null);
        nowColIndex = createBlock(sw, "新增项结算价", 1, 1, null);

        //翻牌请款情况（2.0）
        nowColIndex = createBlock(sw, "已付金额", 1, 1, null);
        nowColIndex = createBlock(sw, "第一次请款", 1, 1, null);
        nowColIndex = createBlock(sw, "第一次请款日期", 1, 1, null);
        nowColIndex = createBlock(sw, "第二次请款", 1, 1, null);
        nowColIndex = createBlock(sw, "第二次请款日期", 1, 1, null);
        nowColIndex = createBlock(sw, "第三次请款", 1, 1, null);
        nowColIndex = createBlock(sw, "第三次请款日期", 1, 1, null);

        //翻牌3.0情况
        nowColIndex = createBlock(sw, "装修公司", 1, 1, null);
        nowColIndex = createBlock(sw, "翻牌装修申请日期", 1, 1, null);
        nowColIndex = createBlock(sw, "翻牌申请通过时间", 1, 1, null);
        nowColIndex = createBlock(sw, "OA翻牌申请单号", 1, 1, null);
        nowColIndex = createBlock(sw, "报价小计", 1, 1, null);
        nowColIndex = createBlock(sw, "标注项目报价", 1, 1, null);
        nowColIndex = createBlock(sw, "增加项目报价", 1, 1, null);
        nowColIndex = createBlock(sw, "翻牌完成时间", 1, 1, null);
        nowColIndex = createBlock(sw, "翻牌验收通过时间", 1, 1, null);
        nowColIndex = createBlock(sw, "OA翻牌验收单号", 1, 1, null);
        nowColIndex = createBlock(sw, "装修进度", 1, 1, null);
        nowColIndex = createBlock(sw, "结算价格合计", 1, 1, null);
        nowColIndex = createBlock(sw, "标准项结算价", 1, 1, null);
        nowColIndex = createBlock(sw, "新增项结算价", 1, 1, null);

        //翻牌请款情况（3.0）
        nowColIndex = createBlock(sw, "已付金额", 1, 1, null);
        nowColIndex = createBlock(sw, "第一次请款", 1, 1, null);
        nowColIndex = createBlock(sw, "第一次请款日期", 1, 1, null);
        nowColIndex = createBlock(sw, "第二次请款", 1, 1, null);
        nowColIndex = createBlock(sw, "第二次请款日期", 1, 1, null);
        nowColIndex = createBlock(sw, "第三次请款", 1, 1, null);
        nowColIndex = createBlock(sw, "第三次请款日期", 1, 1, null);

        //累计装修
        nowColIndex = createBlock(sw, "装修数次", 1, 1, null);
        nowColIndex = createBlock(sw, "装修费用", 1, 1, null);

        //门店录入信息
        nowColIndex = createBlock(sw, "姓名", 1, 1, null);
        nowColIndex = createBlock(sw, "工号", 1, 1, null);
        nowColIndex = createBlock(sw, "录入日期", 1, 1, null);

        //业绩归属人员信息
        nowColIndex = createBlock(sw, "拓展专员姓名", 1, 1, null);
        nowColIndex = createBlock(sw, "拓展专员工号", 1, 1, null);
        nowColIndex = createBlock(sw, "拓展经理姓名", 1, 1, null);
        nowColIndex = createBlock(sw, "拓展经理工号", 1, 1, null);
        nowColIndex = createBlock(sw, "中心负责人姓名", 1, 1, null);
        nowColIndex = createBlock(sw, "中心负责人工号", 1, 1, null);

        //门店维护人员信息
        //当前维护人员信息
        nowColIndex = createBlock(sw, "维护事业部", 1, 1, null);
        nowColIndex = createBlock(sw, "维护中心", 1, 1, null);
        nowColIndex = createBlock(sw, "维护组", 1, 1, null);
        nowColIndex = createBlock(sw, "维护人姓名", 1, 1, null);
        nowColIndex = createBlock(sw, "维护人工号", 1, 1, null);
        nowColIndex = createBlock(sw, "维护开始日期", 1, 1, null);
        nowColIndex = createBlock(sw, "备注", 1, 1, null);

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
        short moneyStyle;

        bodyStyle = bodyStyleBlack;
        dateStyle = dateStyleBlack;
        moneyStyle = moneyStyleBlack;

        int storenum = Integer.parseInt(map.get("storeCnt").toString());
        if (storenum == -1) {
            bodyStyle = bodyStyleRed;
            dateStyle = dateStyleRed;
            moneyStyle = moneyStyleRed;
        }

        //序号
        sw.createCell(i++, clearNull(map.get("rowNum")), bodyStyle);
        //-- 业绩信息 --
        sw.createCell(i++, clearNull(map.get("regionName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("areaCityName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("performanceCity")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("performanceDepartment")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("performanceCenter")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("performanceGroup")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("performancePeople")), bodyStyle);
        //-- 公司信息 --
        sw.createCell(i++, clearNull(map.get("companyNo")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("companyName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("address")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("legalPerson")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("businessLicenseCompanyAddress")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("businessLicenseNo")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("businessLicenseType")), bodyStyle);
        //-- 门店信息 --
        sw.createCell(i++, clearNull(map.get("storeNo")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("storeName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("storeCityName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("districtName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("storeGroupName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("storeSignSize")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("addressDetail")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("storeSizeScaleName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("storeTypeName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("businessPlaceTypeName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("abtypeStore")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("fyAccount")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("contacts")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("mobilePhone")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("nowUserSystem")), bodyStyle);
        setCreateDateCell("storeDueTime", i++, dateStyle, map, sw);
        setCreateDateCell("storeLeaseDueTime", i++, dateStyle, map, sw);
        sw.createCell(i++, clearNull(map.get("chainSituation")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("chainBrand")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("agentNum")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("mainBusiness")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("exchangeMethod")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("storeInfoIntegrity")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("storeSignStatus")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("businessStatus")), bodyStyle);
        //-- 合同信息 --
        sw.createCell(i++, clearNull(map.get("contractNo")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("cooperateMode")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("companyBankNo")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("accountName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("bankAccount")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("partyB")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("partyBAddress")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("agreementNo")), bodyStyle);
        setCreateDateCell("dateLifeStart", i++, dateStyle, map, sw);
        setCreateDateCell("dateLifeEnd", i++, dateStyle, map, sw);

        sw.createCell(i++, clearZero(map.get("storeCnt")), bodyStyle);
        sw.createCell(i++, clearZero(map.get("newStoreCnt")), bodyStyle);
        sw.createCell(i++, clearZero(map.get("penaltyFee")), moneyStyle);
        setCreateDateCell("dateCreate", i++, dateStyle, map, sw);
        sw.createCell(i++, clearNull(map.get("contractdistinctionName")), bodyStyle);
        setCreateDateCell("performDate", i++, dateStyle, map, sw);
        setCreateDateCell("cancelDate", i++, dateStyle, map, sw);
        setCreateDateCell("dateUpdate", i++, dateStyle, map, sw);
        sw.createCell(i++, clearNull(map.get("contractCheckStatusName")), bodyStyle);
        //翻牌业绩信息
        sw.createCell(i++, clearNull(map.get("flipModeName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("decorationTypeName")), bodyStyle);
        setCreateDateCell("flipPassDate", i++, dateStyle, map, sw);
        //-- 乙转甲 --
        sw.createCell(i++, clearNull(map.get("b2AContractNo")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("b2AAgreementNo")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("b2ACompanyNo")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("b2ACompanyName")), bodyStyle);
        setCreateDateCell("b2ADateLifeStart", i++, dateStyle, map, sw);
        setCreateDateCell("b2ADateLifeEnd", i++, dateStyle, map, sw);
        setCreateDateCell("b2APerformDate", i++, dateStyle, map, sw);
        sw.createCell(i++, clearNull(map.get("b2APerformancePeople")), bodyStyle);
        //-- 三方变更 --
        sw.createCell(i++, clearNull(map.get("threeChangeContractNo")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("threeChangeCompanyNo")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("threeChangeCompanyName")), bodyStyle);
        setCreateDateCell("threeChangeDateLifeStart", i++, dateStyle, map, sw);
        setCreateDateCell("threeChangeDateLifeEnd", i++, dateStyle, map, sw);
        setCreateDateCell("threeChangePerformDate", i++, dateStyle, map, sw);
        sw.createCell(i++, clearNull(map.get("threeChangePerformancePeople")), bodyStyle);
        //-- 续签 --
        sw.createCell(i++, clearNull(map.get("reNewContractNo")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("reNewAgreementNo")), bodyStyle);
        setCreateDateCell("reNewDateLifeStart", i++, dateStyle, map, sw);
        setCreateDateCell("reNewDateLifeEnd", i++, dateStyle, map, sw);
        setCreateDateCell("reNewPerformDate", i++, dateStyle, map, sw);
        sw.createCell(i++, clearNull(map.get("reNewPerformancePeople")), bodyStyle);
        //-- 保证金信息 --
        sw.createCell(i++, clearZero(map.get("depositFee")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("arrivalDepositFee")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("uncollectedAccount")), moneyStyle);
        setCreateDateCell("dateArrivalAct", i++, dateStyle, map, sw);
        sw.createCell(i++, clearZero(map.get("refundAmount")), moneyStyle);
        setCreateDateCell("refundDate", i++, dateStyle, map, sw);
        //-- 平台服务费 --
        sw.createCell(i++, clearZero(map.get("receivableMoney")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("accountMoney")), moneyStyle);
        setCreateDateCell("accountDate", i++, dateStyle, map, sw);
        setCreateDateCell("overDate", i++, dateStyle, map, sw);
        sw.createCell(i++, clearNull(map.get("platformStatus")), bodyStyle);
        //-- 翻牌1.0情 --
        sw.createCell(i++, clearNull(map.get("decorateCompany10")), bodyStyle);
        sw.createCell(i++, clearZero(map.get("oaMdysSumJsj10")), moneyStyle);
        setCreateDateCell("flipCompleDate10", i++, dateStyle, map, sw);
        //-- 翻牌请款情况（1.0） --
        sw.createCell(i++, clearZero(map.get("payedMoney10")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("firstMoney10")), moneyStyle);
        setCreateDateCell("firstTime10", i++, dateStyle, map, sw);
        sw.createCell(i++, clearZero(map.get("secondMoney10")), moneyStyle);
        setCreateDateCell("secondTime10", i++, dateStyle, map, sw);
        sw.createCell(i++, clearZero(map.get("thirdMoney10")), moneyStyle);
        setCreateDateCell("thirdTime10", i++, dateStyle, map, sw);
        //-- 翻牌2.0情况 --
        sw.createCell(i++, clearNull(map.get("decorateCompany20")), bodyStyle);
        setCreateDateCell("mdfpApplicaDate20", i++, dateStyle, map, sw);
        setCreateDateCell("oaMdfpPassDate20", i++, dateStyle, map, sw);
        sw.createCell(i++, clearNull(map.get("flipApplicaCode20")), bodyStyle);
        sw.createCell(i++, clearZero(map.get("quotatnsubtotal20")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("standardQuotatn20")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("addQuotation20")), moneyStyle);
        setCreateDateCell("flipCompleDate20", i++, dateStyle, map, sw);
        setCreateDateCell("flipPassDate20", i++, dateStyle, map, sw);
        sw.createCell(i++, clearNull(map.get("flipAcceptaceNo20")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("decorateStatusName20")), bodyStyle);
        sw.createCell(i++, clearZero(map.get("oaMdysSumJsj20")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("oaMdysStandardJsj20")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("oaMdysAddJsj20")), moneyStyle);
        //-- 翻牌请款情况（2.0） --
        sw.createCell(i++, clearZero(map.get("payedMoney20")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("firstMoney20")), moneyStyle);
        setCreateDateCell("firstTime20", i++, dateStyle, map, sw);
        sw.createCell(i++, clearZero(map.get("secondMoney20")), moneyStyle);
        setCreateDateCell("secondTime20", i++, dateStyle, map, sw);
        sw.createCell(i++, clearZero(map.get("thirdMoney20")), moneyStyle);
        setCreateDateCell("thirdTime20", i++, dateStyle, map, sw);
        //-- 翻牌3.0情况 --
        sw.createCell(i++, clearNull(map.get("decorateCompany30")), bodyStyle);
        setCreateDateCell("mdfpApplicaDate30", i++, dateStyle, map, sw);
        setCreateDateCell("oaMdfpPassDate30", i++, dateStyle, map, sw);
        sw.createCell(i++, clearNull(map.get("flipApplicaCode30")), bodyStyle);
        sw.createCell(i++, clearZero(map.get("quotatnsubtotal30")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("standardQuotatn30")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("addQuotation30")), moneyStyle);
        setCreateDateCell("flipCompleDate30", i++, dateStyle, map, sw);
        setCreateDateCell("flipPassDate30", i++, dateStyle, map, sw);
        sw.createCell(i++, clearNull(map.get("flipAcceptaceNo30")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("decorateStatusName30")), bodyStyle);
        sw.createCell(i++, clearZero(map.get("oaMdysSumJsj30")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("oaMdysStandardJsj30")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("oaMdysAddJsj30")), moneyStyle);
        //-- 翻牌请款情况（3.0） --
        sw.createCell(i++, clearZero(map.get("payedMoney30")), moneyStyle);
        sw.createCell(i++, clearZero(map.get("firstMoney30")), moneyStyle);
        setCreateDateCell("firstTime30", i++, dateStyle, map, sw);
        sw.createCell(i++, clearZero(map.get("secondMoney30")), moneyStyle);
        setCreateDateCell("secondTime30", i++, dateStyle, map, sw);
        sw.createCell(i++, clearZero(map.get("thirdMoney30")), moneyStyle);
        setCreateDateCell("thirdTime30", i++, dateStyle, map, sw);
        //-- 累计装修 --
        sw.createCell(i++, clearZero(map.get("decorateCount")), bodyStyle);
        sw.createCell(i++, clearZero(map.get("decorateSum")), moneyStyle);
        //-- 门店录入信息 --
        sw.createCell(i++, clearNull(map.get("userName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("userCode")), bodyStyle);
        setCreateDateCell("storeDateCreate", i++, dateStyle, map, sw);
        //-- 业绩归属人员信息 --
        sw.createCell(i++, clearNull(map.get("expandCommissioner")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("expandCommissionerNum")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("expandManager")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("expandManagerNum")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("headCenter")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("headCenterNum")), bodyStyle);
        //-- 门店维护人员信息 --
        //-- 当前维护人员信息 --
        sw.createCell(i++, clearNull(map.get("performanceDepartment")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("center")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("groupName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("maintainerName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("maintainer")), bodyStyle);
        setCreateDateCell("dateMtcStart", i++, dateStyle, map, sw);
        sw.createCell(i++, clearNull(map.get("remark")), bodyStyle);

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

        //业绩信息
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 6));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 7;

        //公司信息
        //公司编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex++;
        //公司名称、公司营业地址
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 1));
        c.setWidth(30);
        columnSet.add(c);
        nColIndex = nColIndex + 2;

        //法人
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //公司注册地址
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(30);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //营业执照编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(18);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //营业执照性质
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //门店信息
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 4));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 5;
        //门店店招尺寸(m*m)
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(16);
        columnSet.add(c);
        nColIndex = nColIndex + 1;
        //门店地址
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(28);
        columnSet.add(c);
        nColIndex = nColIndex + 1;
        //....门店资质等级
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 3));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 4;
        //门店关联1.0房友账号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(15);
        columnSet.add(c);
        nColIndex = nColIndex + 1;
        //
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 7));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 8;

        //主营业务
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(30);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //交易方式
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //门店信息完整度
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(14);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //门店签约状况
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //营业状态
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //合同信息
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 5));
        c.setWidth(14);
        columnSet.add(c);
        nColIndex = nColIndex + 6;

        //甲方收件地址
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(28);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //协议书编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(15);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //签约类型
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 6));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 7;

        //合同审核状态
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 3));
        c.setWidth(13);
        columnSet.add(c);
        nColIndex = nColIndex + 4;

        //翻牌业绩信息
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 2));
        c.setWidth(13);
        columnSet.add(c);
        nColIndex = nColIndex + 3;

        //乙转甲
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 7));
        c.setWidth(14);
        columnSet.add(c);
        nColIndex = nColIndex + 8;
        
        //三方变更
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 1));
        c.setWidth(14);
        columnSet.add(c);
        nColIndex = nColIndex + 2;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(28);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 2));
        c.setWidth(13);
        columnSet.add(c);
        nColIndex = nColIndex + 3;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //续签
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 1));
        c.setWidth(14);
        columnSet.add(c);
        nColIndex = nColIndex + 2;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 2));
        c.setWidth(13);
        columnSet.add(c);
        nColIndex = nColIndex + 3;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        //保证金信息
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 3));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 6;

        //平台服务
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 4));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 5;

        //翻牌1.0情况
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(28);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 1));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 2;

        //翻牌1.0请款情况
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 6));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 7;

        //翻牌2.0情况
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(28);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 2));
        c.setWidth(15);
        columnSet.add(c);
        nColIndex = nColIndex + 3;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 4));
        c.setWidth(13);
        columnSet.add(c);
        nColIndex = nColIndex + 5;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(15);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 3));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 4;

        //翻牌2.0请款情况
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 6));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 7;

        //翻牌3.0情况
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(28);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 2));
        c.setWidth(15);
        columnSet.add(c);
        nColIndex = nColIndex + 3;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 4));
        c.setWidth(13);
        columnSet.add(c);
        nColIndex = nColIndex + 5;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(15);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 3));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 4;

        //翻牌3.0请款情况
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 6));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 7;

        //累计装修
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 1));
        c.setWidth(9);
        columnSet.add(c);
        nColIndex = nColIndex + 2;

        //门店信息录入
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 2));
        c.setWidth(11);
        columnSet.add(c);
        nColIndex = nColIndex + 3;

        //业绩归属人员信息
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 5));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex = nColIndex + 6;

        //维护人员信息
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 5));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex = nColIndex + 6;

        //备注
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(40);
        columnSet.add(c);
        nColIndex = nColIndex++;

        return columnSet;
    }

    public String clearNull(Object value) {

        return value == null ? "-" : value.toString();
    }

    public String replaceChars(String str, String mask) {
        if (str == null)
            return "";

        if (str.length() <= 6 || mask.length() <= 0 || str.length() <= mask.length())
            return str;

        int startPos = 0;
        String result = str;

        try {

            startPos = (str.length() - mask.length()) / 2 + 1;
            result = str.substring(0, startPos - 1) + mask;
            result = result + str.substring(result.length(), str.length());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public Double clearZero(Object value) {
        if (value == null || value.equals("-"))
            return Double.parseDouble("0.00");

        DecimalFormat df = new DecimalFormat("###0.00");
        Double data = Double.parseDouble(value.toString());
        return data;
    }

    public Calendar parseCalendar(String value) {
        Calendar calendar = Calendar.getInstance();
        if (value == null || value.equals("-"))
            return calendar;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = sdf.parse(value);

            calendar.setTime(date);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return calendar;
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
                cal.setTime(DateUtils.parseDate((String) map.get(dateName)));
                sw.createCell(i, cal, dateStyle);
            }
        } catch (Exception e) {
            logger.error((String) map.get(dateName));
        }

    }
}

