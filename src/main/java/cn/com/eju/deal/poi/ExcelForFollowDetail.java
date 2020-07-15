package cn.com.eju.deal.poi;

import cn.com.eju.deal.common.util.DateUtils;
import cn.com.eju.deal.poi.common.ExcelColumnSet;
import cn.com.eju.deal.poi.common.ExcelStyleEnum;
import cn.com.eju.deal.poi.common.ExcelUseXmlAbstract;
import cn.com.eju.deal.poi.common.SpeedSheetWriter;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelForFollowDetail extends ExcelUseXmlAbstract {

    private short bodyStyle;
    private short dateMinuteStyle;

    public void downloadSheet(List<LinkedHashMap<String, Object>> list, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("跟进明细");

        Map<String, XSSFCellStyle> style = super.createStyles(wb);
        super.headerStyle = style.get(ExcelStyleEnum.HEADER.getValue()).getIndex();
        this.bodyStyle = style.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();
        this.dateMinuteStyle = style.get(ExcelStyleEnum.DATE_MINUTE_BLACK.getValue()).getIndex();

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
        sw.beginSheet(setColumnWidth(), 7, 2, "H3");
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
        nowColIndex = createBlock(sw, "业绩归属区域", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "业绩归属城市", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "业绩所在城市", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "业绩归属中心", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "门店编号", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "门店名称", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "店招编号", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "城市", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "区域", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "地址", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "签到位置", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "中心", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "门店创建人", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "签约类型", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "合同状态", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "翻牌进度", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "营业状态", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "维护人员", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "跟进方式", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "跟进目的", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "跟进描述", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "跟进人", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "跟进日期", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "签到距门店距离", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "签到时间", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "签退时间", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "签到时长", 1, 2, listMerge);
        nowColIndex = createBlock(sw, "服务项目", 5, 1, listMerge);
        nowColIndex = createBlock(sw, "调查项目", 6, 1, listMerge);
        nowColIndex = createBlock(sw, "检查项目", 4, 1, listMerge);
        sw.endRow();
        //第二行
        sw.insertRow(++nowRowIndex);
        nowColIndex = 0;
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

        nowColIndex = createBlock(sw, "联合晨会", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "夕会", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "门店PK", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "门店培训", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "区域活动", 1, 1, listMerge);

        nowColIndex = createBlock(sw, "招聘培训", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "公盘", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "系统", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "交易", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "联动", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "财务返款", 1, 1, listMerge);

        nowColIndex = createBlock(sw, "VI", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "工服", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "房源", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "合同", 1, 1, listMerge);

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

        sw.createCell(i++, map.get("regionName") == null ? "" : map.get("regionName").toString(), bodyStyle);
        sw.createCell(i++, map.get("areaCityName") == null ? "" : map.get("areaCityName").toString(), bodyStyle);
        sw.createCell(i++, map.get("cityGroupName") == null ? "" : map.get("cityGroupName").toString(), bodyStyle);
        sw.createCell(i++, map.get("centerGroupName") == null ? "" : map.get("centerGroupName").toString(), bodyStyle);
        sw.createCell(i++, map.get("storeNo") == null ? "" : map.get("storeNo").toString(), bodyStyle);
        sw.createCell(i++, map.get("storeName") == null ? "" : map.get("storeName").toString(), bodyStyle);
        sw.createCell(i++, map.get("signageNo") == null ? "" : map.get("signageNo").toString(), bodyStyle);
        sw.createCell(i++, map.get("cityName") == null ? "" : map.get("cityName").toString(), bodyStyle);
        sw.createCell(i++, map.get("areaName") == null ? "" : map.get("areaName").toString(), bodyStyle);
        sw.createCell(i++, map.get("address") == null ? "" : map.get("address").toString(), bodyStyle);
        sw.createCell(i++, map.get("signLocation") == null ? "" : map.get("signLocation").toString(), bodyStyle);
        sw.createCell(i++, map.get("tradeCenter") == null ? "" : map.get("tradeCenter").toString(), bodyStyle);
        sw.createCell(i++, map.get("developer") == null ? "" : map.get("developer").toString(), bodyStyle);
        sw.createCell(i++, map.get("contractTypeName") == null ? "" : map.get("contractTypeName").toString(), bodyStyle);
        sw.createCell(i++, map.get("contractStatus") == null ? "" : map.get("contractStatus").toString(), bodyStyle);
        sw.createCell(i++, map.get("decorateStatusName") == null ? "" : map.get("decorateStatusName").toString(), bodyStyle);
        sw.createCell(i++, map.get("businessStatusName") == null ? "" : map.get("businessStatusName").toString(), bodyStyle);
        sw.createCell(i++, map.get("maintainerName") == null ? "" : map.get("maintainerName").toString(), bodyStyle);
        sw.createCell(i++, map.get("followTypeName") == null ? "" : map.get("followTypeName").toString(), bodyStyle);
        sw.createCell(i++, map.get("followAim") == null ? "" : map.get("followAim").toString(), bodyStyle);
        sw.createCell(i++, map.get("followDesc") == null ? "" : map.get("followDesc").toString(), bodyStyle);
        sw.createCell(i++, map.get("follower") == null ? "" : map.get("follower").toString(), bodyStyle);
        if (map.get("followDate") == null) {
            sw.createCell(i++, "", dateMinuteStyle);
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(DateUtils.parseDate(map.get("followDate").toString(), "yyyy-MM-dd HH:mm:ss"));
            sw.createCell(i++, cal, dateMinuteStyle);
        }

        sw.createCell(i++, map.get("distance") == null ? "" : map.get("distance").toString(), bodyStyle);
        if (map.get("signDate") == null) {
            sw.createCell(i++, "", dateMinuteStyle);
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(DateUtils.parseDate(map.get("signDate").toString(), "yyyy-MM-dd HH:mm:ss"));
            sw.createCell(i++, cal, dateMinuteStyle);
        }
        if (map.get("signOutDate") == null) {
            sw.createCell(i++, "", dateMinuteStyle);
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(DateUtils.parseDate(map.get("signOutDate").toString(), "yyyy-MM-dd HH:mm:ss"));
            sw.createCell(i++, cal, dateMinuteStyle);
        }
        sw.createCell(i++, map.get("signDuration") == null ? "" : map.get("signDuration").toString(), bodyStyle);

        sw.createCell(i++, map.get("morningMeeting") == null ? "" : map.get("morningMeeting").toString(), bodyStyle);
        sw.createCell(i++, map.get("duskMeeting") == null ? "" : map.get("duskMeeting").toString(), bodyStyle);
        sw.createCell(i++, map.get("storePK") == null ? "" : map.get("storePK").toString(), bodyStyle);
        sw.createCell(i++, map.get("storeTrain") == null ? "" : map.get("storeTrain").toString(), bodyStyle);
        sw.createCell(i++, map.get("areaActivity") == null ? "" : map.get("areaActivity").toString(), bodyStyle);

        sw.createCell(i++, map.get("hireTrain") == null ? "" : map.get("hireTrain").toString(), bodyStyle);
        sw.createCell(i++, map.get("publicPlate") == null ? "" : map.get("publicPlate").toString(), bodyStyle);
        sw.createCell(i++, map.get("system") == null ? "" : map.get("system").toString(), bodyStyle);
        sw.createCell(i++, map.get("trade") == null ? "" : map.get("trade").toString(), bodyStyle);
        sw.createCell(i++, map.get("link") == null ? "" : map.get("link").toString(), bodyStyle);
        sw.createCell(i++, map.get("refund") == null ? "" : map.get("refund").toString(), bodyStyle);

        sw.createCell(i++, map.get("vi") == null ? "" : map.get("vi").toString(), bodyStyle);
        sw.createCell(i++, map.get("uniform") == null ? "" : map.get("uniform").toString(), bodyStyle);
        sw.createCell(i++, map.get("houseResource") == null ? "" : map.get("houseResource").toString(), bodyStyle);
        sw.createCell(i, map.get("contractName") == null ? "" : map.get("contractName").toString(), bodyStyle);

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

        //城市
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 3));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex += 4;

        //门店编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex++;

        //门店名称
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(21);
        columnSet.add(c);
        nColIndex++;

        //店招编号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex++;

        //城市
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 1));
        c.setWidth(7);
        columnSet.add(c);
        nColIndex += 2;

        //地址
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(33);
        columnSet.add(c);
        nColIndex++;

        //签到位置
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(32);
        columnSet.add(c);
        nColIndex++;

        //中心
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(11);
        columnSet.add(c);
        nColIndex++;

        //创建人
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(11);
        columnSet.add(c);
        nColIndex++;

        //签约类型
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 1));
        c.setWidth(11);
        columnSet.add(c);
        nColIndex++;

        //合同状态
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 1));
        c.setWidth(11);
        columnSet.add(c);
        nColIndex++;

        //翻牌进度
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 1));
        c.setWidth(13);
        columnSet.add(c);
        nColIndex++;

        //营业状态
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 1));
        c.setWidth(12);
        columnSet.add(c);
        nColIndex++;

        //维护人
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(11);
        columnSet.add(c);
        nColIndex++;

        //跟进方式
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 3));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //跟进目的
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(20);
        columnSet.add(c);
        nColIndex++;

        //跟进描述
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 2));
        c.setWidth(40);
        columnSet.add(c);
        nColIndex++;

        //跟进人
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex + 2));
        c.setWidth(11);
        columnSet.add(c);
        nColIndex++;

        //跟进日期
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(16);
        columnSet.add(c);
        nColIndex++;

        //签到距离
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;


        //签到时间
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(16);
        columnSet.add(c);
        nColIndex++;

        //签退时间
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(16);
        columnSet.add(c);
        nColIndex++;

        //签到时长
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex++;

        //服务项目
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex = nColIndex + 5;

        //调查项目
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);
        nColIndex = nColIndex + 6;

        //检查项目
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(10);
        columnSet.add(c);

        return columnSet;
    }

}
