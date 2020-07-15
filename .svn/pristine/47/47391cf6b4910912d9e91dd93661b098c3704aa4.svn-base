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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelForMembershipDetail extends ExcelUseXmlAbstract

{
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    private short bodyStyleBlack;
    private short bodyStyleRed;
    private short dateStyleBlack;
    private short dateStyleRed;
    private short moneyStyleBlack;
    private short moneyStyleRed;
    private short intBlack;
    private short intRed;

    private XSSFSheet sheet;

    public void downloadSheet(List<LinkedHashMap<String, Object>> list, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook();
        sheet = wb.createSheet("公盘会员明细");

        Map<String, XSSFCellStyle> style = super.createStyles(wb);
        super.headerStyle = style.get(ExcelStyleEnum.HEADER.getValue()).getIndex();
        this.bodyStyleBlack = style.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();
        this.bodyStyleRed = style.get(ExcelStyleEnum.BODY_RED.getValue()).getIndex();
        this.dateStyleBlack = style.get(ExcelStyleEnum.DATE_BLACK.getValue()).getIndex();
        this.dateStyleRed = style.get(ExcelStyleEnum.DATE_RED.getValue()).getIndex();
        this.moneyStyleBlack = style.get(ExcelStyleEnum.MONEY_LOCKED_BLACK.getValue()).getIndex();
        this.moneyStyleRed = style.get(ExcelStyleEnum.MONEY_LOCKED_RED.getValue()).getIndex();
        this.intBlack = style.get(ExcelStyleEnum.INT_BLACK.getValue()).getIndex();
        this.intRed = style.get(ExcelStyleEnum.INT_RED.getValue()).getIndex();

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
        sw.beginSheet(setColumnWidth(), 0, 2, "A3");
        List<String> listMerge = new ArrayList<String>();
        int index = 0;
        index = this.createSheetHead(sw, index, listMerge);
        if (results != null && results.size() > 0) {
            for (LinkedHashMap<String, Object> map : results) {
                index = this.createSheetBody(sw, index, map);
            }
        }

        sw.endSheetWithPasswordMerge3(listMerge, false, "A2:BL2");

//        //自动调整列宽
//        if (nowColIndex > 0) {
//            for (int i = 46; i < nowColIndex - 1; i++) {
//                sheet.autoSizeColumn(i);
//            }
//        }
        sw.endWorkSheet();
    }

    /**
     * 创建sheet头部内容
     */
    private int createSheetHead(SpeedSheetWriter sw, int index, List<String> listMerge) throws IOException {
        nowRowIndex = index;
        nowColIndex = 0;

        //首行隐藏字段
        sw.insertRow(nowRowIndex);

        nowColIndex = createBlock(sw, "序号", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "业绩信息", 5, 1, listMerge);
        nowColIndex = createBlock(sw, "公司信息", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "门店信息", 13, 1, listMerge);
        nowColIndex = createBlock(sw, "公盘合作协议信息", 18, 1, listMerge);
        nowColIndex = createBlock(sw, "保证金信息", 2, 1, listMerge);
        nowColIndex = createBlock(sw, "会员信息", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "门店录入信息", 3, 1, listMerge);
        nowColIndex = createBlock(sw, "业绩归属人员信息", 6, 1, listMerge);
        nowColIndex = createBlock(sw, "门店维护人员信息", 6, 1, listMerge);
        nowColIndex = createBlock(sw, "备注", 1, 2, listMerge);

        sw.endRow();

        nowRowIndex++;

        nowColIndex = 0;
        //第一行
        sw.insertRow(nowRowIndex);
        nowColIndex = createBlock(sw, "序号", 1, 2, null);
        nowColIndex = createBlock(sw, "业绩归属城市", 1, 1, null);
        nowColIndex = createBlock(sw, "业绩所在城市", 1, 1, null);
        nowColIndex = createBlock(sw, "业绩归属事业部", 1, 1, null);
        nowColIndex = createBlock(sw, "业绩归属中心", 1, 1, null);
        nowColIndex = createBlock(sw, "业绩归属人", 1, 1, null);
        nowColIndex = createBlock(sw, "公司编号", 1, 1, null);
        nowColIndex = createBlock(sw, "公司名", 1, 1, null);
        nowColIndex = createBlock(sw, "公司经营地址", 1, 1, null);
        nowColIndex = createBlock(sw, "法人", 1, 1, null);
        nowColIndex = createBlock(sw, "联系电话", 1, 1, null);
        nowColIndex = createBlock(sw, "公司注册地址", 1, 1, null);
        nowColIndex = createBlock(sw, "统一社会信用代码", 1, 1, null);
        nowColIndex = createBlock(sw, "营业执照性质", 1, 1, null);
        nowColIndex = createBlock(sw, "门店编号", 1, 1, null);
        nowColIndex = createBlock(sw, "挂牌名称", 1, 1, null);
        nowColIndex = createBlock(sw, "门店城市", 1, 1, null);
        nowColIndex = createBlock(sw, "所属行政区", 1, 1, null);
        nowColIndex = createBlock(sw, "门店所属中心", 1, 1, null);
        nowColIndex = createBlock(sw, "经营场所类型", 1, 1, null);
        nowColIndex = createBlock(sw, "门店地址", 1, 1, null);
        nowColIndex = createBlock(sw, "门店规模", 1, 1, null);
        nowColIndex = createBlock(sw, "门店关联1.0房友账号", 1, 1, null);
        nowColIndex = createBlock(sw, "连锁情况", 1, 1, null);
        nowColIndex = createBlock(sw, "连锁品牌", 1, 1, null);
        nowColIndex = createBlock(sw, "经纪人数", 1, 1, null);
        nowColIndex = createBlock(sw, "营业状态", 1, 1, null);
        nowColIndex = createBlock(sw, "合同编号", 1, 1, null);
        nowColIndex = createBlock(sw, "合同类别", 1, 1, null);
        nowColIndex = createBlock(sw, "甲方授权代表", 1, 1, null);
        nowColIndex = createBlock(sw, "甲方联系方式", 1, 1, null);
        nowColIndex = createBlock(sw, "开户名", 1, 1, null);
        nowColIndex = createBlock(sw, "开户行", 1, 1, null);
        nowColIndex = createBlock(sw, "开户省市", 1, 1, null);
        nowColIndex = createBlock(sw, "银行账号", 1, 1, null);
        nowColIndex = createBlock(sw, "协议书编号", 1, 1, null);
        nowColIndex = createBlock(sw, "合作开始日期", 1, 1, null);
        nowColIndex = createBlock(sw, "合作结束日期", 1, 1, null);
        nowColIndex = createBlock(sw, "门店数", 1, 1, null);
        nowColIndex = createBlock(sw, "终止类型", 1, 1, null);
        nowColIndex = createBlock(sw, "终止日期", 1, 1, null);
        nowColIndex = createBlock(sw, "草签日期", 1, 1, null);
        nowColIndex = createBlock(sw, "审批通过日期", 1, 1, null);
        nowColIndex = createBlock(sw, "合同审批状态", 1, 1, null);
        nowColIndex = createBlock(sw, "是否首签", 1, 1, null);
        /*nowColIndex = createBlock(sw, "公盘合同终止编号", 1, 1, null);
        nowColIndex = createBlock(sw, "终止类型", 1, 1, null);
        nowColIndex = createBlock(sw, "终止日期", 1, 1, null);
        nowColIndex = createBlock(sw, "OA审核通过日期", 1, 1, null);
        nowColIndex = createBlock(sw, "合同审核状态", 1, 1, null);*/
//        nowColIndex = createBlock(sw, "公盘保证金", 1, 1, null);
        nowColIndex = createBlock(sw, "是否到账", 1, 1, null);
        nowColIndex = createBlock(sw, "到账日期", 1, 1, null);
        nowColIndex = createBlock(sw, "会员生效/失效日期", 1, 1, null);
        nowColIndex = createBlock(sw, "姓名", 1, 1, null);
        nowColIndex = createBlock(sw, "工号", 1, 1, null);
        nowColIndex = createBlock(sw, "录入日期", 1, 1, null);
        nowColIndex = createBlock(sw, "拓展专员姓名", 1, 1, null);
        nowColIndex = createBlock(sw, "拓展专员工号", 1, 1, null);
        nowColIndex = createBlock(sw, "拓展经理工号", 1, 1, null);
        nowColIndex = createBlock(sw, "拓展经理姓名", 1, 1, null);
        nowColIndex = createBlock(sw, "中心负责人名", 1, 1, null);
        nowColIndex = createBlock(sw, "中心负责人工号", 1, 1, null);
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
        short intStyle;


        bodyStyle = bodyStyleBlack;
        dateStyle = dateStyleBlack;
        intStyle = intBlack;

        Integer num = Integer.valueOf(map.get("storeCnt").toString());
        if (num < 0) {
            bodyStyle = bodyStyleRed;
            dateStyle = dateStyleRed;
            intStyle = intRed;
        }


        //序号
        sw.createCell(i++, clearNull(map.get("rowNum")), bodyStyle);
//        sw.createCell(i++, clearNull(map.get("regionName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("areaCityName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("performanceCity")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("performanceDepartment")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("performanceCenter")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("performancePeople")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("companyNo")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("companyName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("address")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("legalPerson")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("contactNumber")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("businessLicenseCompanyAddress")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("businessLicenseNo")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("businessLicenseType")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("storeNo")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("storeName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("storeCityName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("districtName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("storeGroupName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("businessPlaceTypeName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("addressDetail")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("storeSizeScaleName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("fyAccount")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("LinkageSituation")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("brandName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("agentNum")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("businessStatus")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("gpContractNo")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("contractType")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("partyBNm")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("partyBTel")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("accountNm")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("bankAccountNm")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("accountProvinceCityNm")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("bankAccount")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("gpAgreementNo")), bodyStyle);
        setCreateDateCell("dateLifeStart", i++, dateStyle, map, sw);
        setCreateDateCell("dateLifeEnd", i++, dateStyle, map, sw);
        sw.createCell(i++, clearIntNull(map.get("storeCnt")), intStyle);
        sw.createCell(i++, clearNull(map.get("stopType")), bodyStyle);
        setCreateDateCell("stopDate", i++, dateStyle, map, sw);
        setCreateDateCell("dateCreate", i++, dateStyle, map, sw);
        setCreateDateCell("performDate", i++, dateStyle, map, sw);
        sw.createCell(i++, clearNull(map.get("contractCheckStatusName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("isFirst")), bodyStyle);
        /*sw.createCell(i++, clearNull(map.get("gpContractStopNo")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("stopType")), bodyStyle);
        setCreateDateCell("stopDate", i++, dateStyle, map, sw);
        setCreateDateCell("approvePassDate", i++, dateStyle, map, sw);
        sw.createCell(i++, clearNull(map.get("stopApproveStatus")), bodyStyle);*/
//        sw.createCell(i++, clearNull(map.get("depositFee")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("isArrival")), bodyStyle);
        setCreateDateCell("pastTrialDate", i++, dateStyle, map, sw);
        setCreateDateCell("approvePassDate", i++, dateStyle, map, sw);
        sw.createCell(i++, clearNull(map.get("userName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("userCode")), bodyStyle);
        setCreateDateCell("storeDateCreate", i++, dateStyle, map, sw);
        sw.createCell(i++, clearNull(map.get("expandCommissioner")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("expandCommissionerNum")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("expandManager")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("expandManagerNum")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("centerLeaderName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("centerLeaderCode")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("areaGroupName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("centerGroupName")), bodyStyle);
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

//        //业绩归属区域
//        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
//        c.setWidth(10);
//        columnSet.add(c);
//        nColIndex++;

        //业绩归属城市
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //业绩所在城市
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //业绩归属事业部
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(15);
        columnSet.add(c);
        nColIndex++;

        //业绩归属中心
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //业绩归属人
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //公司编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //公司名
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(24);
        columnSet.add(c);
        nColIndex++;

        //公司经营地址
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(50);
        columnSet.add(c);
        nColIndex++;

        //法人
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //联系电话
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //公司注册地址
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(50);
        columnSet.add(c);
        nColIndex++;

        //统一社会信用代码
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(24);
        columnSet.add(c);
        nColIndex++;

        //营业执照性质
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //门店编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //挂牌名称
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(24);
        columnSet.add(c);
        nColIndex++;

        //门店城市
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //所属行政区
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //门店所属中心
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //经营场所类型
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //门店地址
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(50);
        columnSet.add(c);
        nColIndex++;

        //门店规模
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //门店关联1.0房友账号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //连锁情况
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //连锁品牌
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //经纪人数
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //营业状态
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //合同编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
        columnSet.add(c);
        nColIndex++;

        //合同类别
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //甲方授权代表
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //甲方联系方式
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
        columnSet.add(c);
        nColIndex++;

        //开户名
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(18);
        columnSet.add(c);
        nColIndex++;

        //开户行
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(18);
        columnSet.add(c);
        nColIndex++;

        //开户省市
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(18);
        columnSet.add(c);
        nColIndex++;

        //银行账号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(24);
        columnSet.add(c);
        nColIndex++;

        //协议书编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(24);
        columnSet.add(c);
        nColIndex++;

        //合作开始日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //合作结束日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //门店数
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //终止类型
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //终止日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //草签日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //审批通过日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //合同审批状态
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //是否首签
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        /*//公盘合同终止编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //终止类型
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //终止日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //OA审核通过日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //合同审核状态
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;*/

//        //公盘保证金
//        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
//        c.setWidth(10);
//        columnSet.add(c);
//        nColIndex++;

        //是否到账
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //到账日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //会员生效/失效日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(25);
        columnSet.add(c);
        nColIndex++;

        //姓名
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //工号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //录入日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //拓展专员姓名
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //拓展专员工号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //拓展经理工号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //拓展经理姓名
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //中心负责人名
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //中心负责人工号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //维护事业部
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //维护中心
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //维护组
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //维护人姓名
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //维护人工号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //维护开始日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //备注
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(40);
        columnSet.add(c);
        nColIndex++;

        return columnSet;
    }

    public String clearNull(Object value) {

        return value == null ? "-" : value.toString();
    }

    public Integer clearIntNull(Object value) {
        return value == null ? 0 : Integer.valueOf(value.toString());
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

