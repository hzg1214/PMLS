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
import cn.com.eju.deal.common.util.DateUtils;
import cn.com.eju.deal.poi.common.ExcelColumnSet;
import cn.com.eju.deal.poi.common.ExcelStyleEnum;
import cn.com.eju.deal.poi.common.ExcelUseXmlAbstract;
import cn.com.eju.deal.poi.common.SpeedSheetWriter;

public class ExcelForSign extends ExcelUseXmlAbstract {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    private short bodyStyleBlack;
    private short dateStyleBlack;

    private XSSFSheet sheet;

    public void downloadSheet(List<LinkedHashMap<String, Object>> list, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook();
        sheet = wb.createSheet("已签门店明细");

        Map<String, XSSFCellStyle> style = super.createStyles(wb);
        super.headerStyle = style.get(ExcelStyleEnum.HEADER.getValue()).getIndex();
        this.bodyStyleBlack = style.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();
        this.dateStyleBlack = style.get(ExcelStyleEnum.DATE_BLACK.getValue()).getIndex();

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
        sw.beginSheet(setColumnWidth(), 0, 3, "A4");
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
//            for (int i = 47; i < nowColIndex - 1; i++) {
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
        //List<String> listMerge=new  ArrayList<String>();
        //第一行
        sw.insertRow(nowRowIndex);
        nowColIndex = createBlock(sw, "序号", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "归属区域", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "归属城市", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "所在城市", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "归属中心", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "门店编号", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "门店名称", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "区域", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "详细地址", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "联系人", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "联系方式", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "经纪人数", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "门店状态", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "公司名称", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "公司注册地址", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "合同编号", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "合作模式", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "草签日期", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "OA审核通过日期", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "保证金", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "翻牌日期", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "营业状态", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "平台服务费", 4, 1, listMerge);
        nowColIndex = createBlock(sw, "交易", 4, 1, listMerge);
        nowColIndex = createBlock(sw, "联动", 6, 1, listMerge);
        //        nowColIndex = createBlock(sw, "联动收佣返佣", 5, 1, listMerge);
        nowColIndex = createBlock(sw, "系统使用", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "房友安装机器数", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "房友活跃度", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "IP地址", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "创建人员", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "创建人员工号", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "签约人员", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "签约人员工号", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "维护人员", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "维护人员工号", 1, 3, listMerge);
        nowColIndex = createBlock(sw, "最后跟进日期", 1, 3, listMerge);
        sw.endRow();

        nowRowIndex = nowRowIndex + 1;

        nowColIndex = 0;


        //第二行
        //平台服务费
        sw.insertRow(nowRowIndex);
        nowColIndex = createBlock(sw, "序号", 1, 1, null);
        nowColIndex = createBlock(sw, "", 21, 1, null);
        nowColIndex = createBlock(sw, "月缴费标准", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "已缴金额", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "到期日期", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "状态", 1, 2, listMerge);

        //交易
        nowColIndex = createBlock(sw, "报单", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "网签", 1, 2, listMerge);
        //        nowColIndex = createBlock(sw, "贷款", 1, 2, listMerge);
        //        nowColIndex = createBlock(sw, "评估", 1, 2, listMerge);
        //        nowColIndex = createBlock(sw, "办证", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "不动产受理", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "办结", 1, 2, listMerge);
        //        nowColIndex = createBlock(sw, "净收入", 1, 2, listMerge);

        //联动
        nowColIndex = createBlock(sw, "报备", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "带看", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "大定", 2, 1, listMerge);
        nowColIndex = createBlock(sw, "成销", 2, 1, listMerge);

        //联动收佣返佣
        //        nowColIndex = createBlock(sw, "开发商-收佣", 3, 1, listMerge);
        //        nowColIndex = createBlock(sw, "门店-返佣", 2, 1, listMerge);
        nowColIndex = createBlock(sw, "", 11, 1, null);
        sw.endRow();

        nowRowIndex = nowRowIndex + 1;

        nowColIndex = 0;

        //第三行
        //大定
        sw.insertRow(nowRowIndex);
        nowColIndex = createBlock(sw, "序号", 1, 1, null);
        nowColIndex = createBlock(sw, "", 31, 1, null);
        nowColIndex = createBlock(sw, "套数", 1, 1, null);
        nowColIndex = createBlock(sw, "金额", 1, 1, null);
        //成销
        nowColIndex = createBlock(sw, "套数", 1, 1, null);
        nowColIndex = createBlock(sw, "金额", 1, 1, null);
        //开发商-收佣
        //        nowColIndex = createBlock(sw, "应计", 1, 1, null);
        //        nowColIndex = createBlock(sw, "应收", 1, 1, null);
        //        nowColIndex = createBlock(sw, "实收", 1, 1, null);
        //门店-返佣
        //        nowColIndex = createBlock(sw, "应计", 1, 1, null);
        //        nowColIndex = createBlock(sw, "实付", 1, 1, null);
        nowColIndex = createBlock(sw, "", 11, 1, null);

        sw.endRow();

        nowRowIndex = nowRowIndex + 1;

        return nowRowIndex;
    }


    /**
     * 创建sheet具体内容
     */
    private int createSheetBody(SpeedSheetWriter sw, int index, LinkedHashMap<String, java.lang.Object> map) throws Exception {
        sw.insertRow(index);
        int i = 0;

        short bodyStyle;
        short dateStyle;

        bodyStyle = bodyStyleBlack;
        dateStyle = dateStyleBlack;

        //        int storenum= Integer.parseInt(map.get("storeCnt").toString());
        //        if (storenum == -1){
        //            bodyStyle = bodyStyleRed;
        //            dateStyle = dateStyleRed;
        //        }

        //序号
        sw.createCell(i++, index - 2, bodyStyle);
        //-- 门店信息 --
        sw.createCell(i++, (String) map.get("regionName"), bodyStyle);
        sw.createCell(i++, (String) map.get("areaCityName"), bodyStyle);
        sw.createCell(i++, (String) map.get("cityGroupName"), bodyStyle);
        sw.createCell(i++, (String) map.get("centerGroupName"), bodyStyle);
        sw.createCell(i++, (String) map.get("storeNo"), bodyStyle);
        sw.createCell(i++, (String) map.get("storeName"), bodyStyle);
        sw.createCell(i++, (String) map.get("districtName"), bodyStyle);
        sw.createCell(i++, (String) map.get("addressDetail"), bodyStyle);
        //-- 门店所属中心--
        sw.createCell(i++, (String) map.get("contacts"), bodyStyle);
        sw.createCell(i++, (String) map.get("mobilePhone"), bodyStyle);
        sw.createCell(i++, (String) map.get("storeScale"), bodyStyle);
        sw.createCell(i++, (String) map.get("storeStatus"), bodyStyle);
        sw.createCell(i++, (String) map.get("companyName"), bodyStyle);
        sw.createCell(i++, (String) map.get("businessCompanyAddress"), bodyStyle);
        sw.createCell(i++, (String) map.get("contractNo"), bodyStyle);
        sw.createCell(i++, (String) map.get("cooperateMode"), bodyStyle);
        setCreateDateCell("contractDateCreate", i++, dateStyle, map, sw);
        setCreateDateCell("performDate", i++, dateStyle, map, sw);
        sw.createCell(i++, (String) map.get("depositFee"), bodyStyle);
        setCreateDateCell("dateFlopCkAccept", i++, dateStyle, map, sw);
        sw.createCell(i++, (String) map.get("businessStatus"), bodyStyle);
        //-- 平台服务费 --
        sw.createCell(i++, (String) map.get("receivableMoney"), bodyStyle);
        sw.createCell(i++, (String) map.get("accountMoney"), bodyStyle);
        setCreateDateCell("overDate", i++, dateStyle, map, sw);
        sw.createCell(i++, (String) map.get("platformStatus"), bodyStyle);
        //-- 交易 --
        sw.createCell(i++, (String) map.get("exchangeReportNum"), bodyStyle);
        sw.createCell(i++, (String) map.get("exchangeSignedNum"), bodyStyle);
        //        sw.createCell(i++, map.get("exchangeLoanedNum"), bodyStyle);
        //        sw.createCell(i++, map.get("exchangeEvaluateNum"), bodyStyle);
        //        sw.createCell(i++, map.get("exchangePermitNum"), bodyStyle);
        sw.createCell(i++, (String) map.get("exchangeEstateNum"), bodyStyle);
        sw.createCell(i++, (String) map.get("exchangeHandleEndNum"), bodyStyle);
        //        sw.createCell(i++, map.get("exchangeProfit"), bodyStyle);
        //-- 联动 --
        sw.createCell(i++, (String) map.get("linkReportNum"), bodyStyle);
        sw.createCell(i++, (String) map.get("linkSeeNum"), bodyStyle);
        sw.createCell(i++, (String) map.get("linkRoughNum"), bodyStyle);
        sw.createCell(i++, (String) map.get("linkRoughAmount"), bodyStyle);
        sw.createCell(i++, (String) map.get("linkDealNum"), bodyStyle);
        sw.createCell(i++, (String) map.get("linkDealAmount"), bodyStyle);
        //-- 联动收佣返佣 --
        //        sw.createCell(i++, map.get("linkDeveloperCommissionAccrued"), bodyStyle);
        //        sw.createCell(i++, map.get("linkDeveloperCommissionReceivable"), bodyStyle);
        //        sw.createCell(i++, map.get("linkDeveloperCommissionReal"), bodyStyle);
        //        sw.createCell(i++, map.get("linkStoreCommissionAccrued"), bodyStyle);
        //        sw.createCell(i++, map.get("linkStoreCommissionReal"), bodyStyle);
        //-- 房友 --
        sw.createCell(i++, (String) map.get("fangyouSystem"), bodyStyle);
        sw.createCell(i++, (String) map.get("fangyouMachineNum"), bodyStyle);
        sw.createCell(i++, (String) map.get("fangyouLive"), bodyStyle);
        sw.createCell(i++, (String) map.get("fangyouIPAddress"), bodyStyle);
        //-- 创建人员--
        sw.createCell(i++, (String) map.get("createName"), bodyStyle);
        sw.createCell(i++, (String) map.get("createNameNo"), bodyStyle);
        //-- 签约人员--
        sw.createCell(i++, (String) map.get("signedName"), bodyStyle);
        sw.createCell(i++, (String) map.get("signedNameNo"), bodyStyle);
        //-- 维护人员--
        sw.createCell(i++, (String) map.get("maintainerName"), bodyStyle);
        sw.createCell(i++, (String) map.get("maintainerCode"), bodyStyle);
        //-- 最后跟进日期--
        setCreateDateCell("dateFollowUpNew", i++, dateStyle, map, sw);


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

        //归属区域
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //归属城市
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //所在城市
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //归属中心
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex++;

        //名称
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(26);
        columnSet.add(c);
        nColIndex++;

        //门店归属区域
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //地址
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(34);
        columnSet.add(c);
        nColIndex++;

        //联系人
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //联系方式
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex++;

        //经济人数
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //门店状态
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //公司名称
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(30);
        columnSet.add(c);
        nColIndex++;

        //公司注册地址
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(45);
        columnSet.add(c);
        nColIndex++;

        //合同编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(16);
        columnSet.add(c);
        nColIndex++;

        //合作模式
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(14);
        columnSet.add(c);
        nColIndex++;

        //日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(14);
        columnSet.add(c);
        nColIndex++;

        //保证金
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;

        //日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(14);
        columnSet.add(c);
        nColIndex++;

        //状态
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;

        //平台服务费
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 2));
        c.setWidth(16);
        columnSet.add(c);
        nColIndex = nColIndex + 4;

        //交易
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 2));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex = nColIndex + 4;
        //联动
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 2));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex = nColIndex + 2;

        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 2));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex = nColIndex + 4;

        //系统使用
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;
        //安装几台数
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;
        //活跃度
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(8);
        columnSet.add(c);
        nColIndex++;
        //ip地址
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex++;
        //创建人
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;
        //创建工号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;
        //签约人员
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;
        //签约人员工号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;
        //维护人员
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;
        //维护人员工号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;
        //最后跟进日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(16);
        columnSet.add(c);
        nColIndex++;
        return columnSet;
    }

    /**
     * 创建日期型sheet
     */
    private void setCreateDateCell(String dateName, int i, short dateStyle, LinkedHashMap<String, Object> map, SpeedSheetWriter sw) throws Exception {

        Calendar cal = Calendar.getInstance();
        try {
            if (map.get(dateName) == null || "".equals(map.get(dateName).toString()) || "-".equals(map.get(dateName).toString())) {
                sw.createCell(i, "", dateStyle);
            } else {
                cal.setTime(DateUtils.parseDate((String) map.get(dateName)));
                sw.createCell(i, cal, dateStyle);
            }
        } catch (Exception e) {
            logger.error((String) map.get(dateName));
        }

    }
}

