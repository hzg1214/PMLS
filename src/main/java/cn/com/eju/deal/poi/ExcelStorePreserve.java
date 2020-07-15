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

/**
 * @Auther: xuliangliang
 * @Date: 2018\10\24 0024 11:07
 * @Description:
 */
public class ExcelStorePreserve extends ExcelUseXmlAbstract {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    private short bodyStyleBlack;
    private short bodyStyleRed;
    private short dateStyleBlack;
    private short dateStyleRed;
    private short moneyStyleBlack;
    private short moneyStyleRed;

    private XSSFSheet sheet;

    public void downloadSheet(List<LinkedHashMap<String, Object>> list, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook();
        sheet = wb.createSheet("维护门店明细");

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
        nowColIndex = createBlock(sw, "核算主体", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "维护信息", 7, 1, listMerge);
        nowColIndex = createBlock(sw, "公司信息", 7, 1, listMerge);
        nowColIndex = createBlock(sw, "门店信息", 13, 1, listMerge);
        nowColIndex = createBlock(sw, "合同信息", 14, 1, listMerge);
        nowColIndex = createBlock(sw, "翻牌业绩信息", 2, 1, listMerge);
        nowColIndex = createBlock(sw, "乙转甲", 8, 1, listMerge);
        nowColIndex = createBlock(sw, "续签", 6, 1, listMerge);
        nowColIndex = createBlock(sw, "保证金信息", 6, 1, listMerge);
        nowColIndex = createBlock(sw, "备注", 1, 2, listMerge);
        sw.endRow();
        nowRowIndex = nowRowIndex + 1;

        nowColIndex = 0;


        //第二行
        //业绩信息
        sw.insertRow(nowRowIndex);
        nowColIndex = createBlock(sw, "序号", 1, 1, null);
        nowColIndex = createBlock(sw, "核算主体", 1, 1, null);
        nowColIndex = createBlock(sw, "维护区域", 1, 1, null);
        nowColIndex = createBlock(sw, "维护城市", 1, 1, null);
        nowColIndex = createBlock(sw, "维护事业部", 1, 1, null);
        nowColIndex = createBlock(sw, "维护中心", 1, 1, null);
        nowColIndex = createBlock(sw, "维护人姓名", 1, 1, null);
        nowColIndex = createBlock(sw, "维护人工号", 1, 1, null);
        nowColIndex = createBlock(sw, "维护开始日期", 1, 1, null);

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
        nowColIndex = createBlock(sw, "门店地址", 1, 1, null);
        nowColIndex = createBlock(sw, "门店规模", 1, 1, null);
        nowColIndex = createBlock(sw, "门店资质等级", 1, 1, null);
        nowColIndex = createBlock(sw, "经营场所类型", 1, 1, null);
        nowColIndex = createBlock(sw, "门店负责人", 1, 1, null);
        nowColIndex = createBlock(sw, "联系电话", 1, 1, null);
        nowColIndex = createBlock(sw, "门店签约状况", 1, 1, null);
        nowColIndex = createBlock(sw, "营业状态", 1, 1, null);

        //合同信息
        nowColIndex = createBlock(sw, "合同编号", 1, 1, null);
        nowColIndex = createBlock(sw, "合作模式", 1, 1, null);
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
        //-- 维护信息 --
        sw.createCell(i++, clearNull(map.get("checkBody")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("area")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("city")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("sybName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("groupName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("maintainerName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("maintainer")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("dateMtcStart")), bodyStyle);

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
        sw.createCell(i++, clearNull(map.get("addressDetail")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("storeSizeScaleName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("abtypeStore")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("businessPlaceTypeName")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("contacts")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("mobilePhone")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("storeSignStatus")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("businessStatus")), bodyStyle);
        //-- 合同信息 --
        sw.createCell(i++, clearNull(map.get("contractNo")), bodyStyle);
        sw.createCell(i++, clearNull(map.get("cooperateMode")), bodyStyle);
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
        //序号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex++;

        //维护信息
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


        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(28);
        columnSet.add(c);
        nColIndex = nColIndex + 1;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 7));
        c.setWidth(13);
        columnSet.add(c);
        nColIndex = nColIndex +7;



        //合同信息
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 13));
        c.setWidth(14);
        columnSet.add(c);
        nColIndex = nColIndex + 14;



        //翻牌业绩信息
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 1));
        c.setWidth(13);
        columnSet.add(c);
        nColIndex = nColIndex + 2;

        //乙转甲
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 2));
        c.setWidth(14);
        columnSet.add(c);
        nColIndex = nColIndex + 3;

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
