package cn.com.eju.pmls.commission.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.FileUtils;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.pmls.poi.ExcelForLnkYjNy;
import cn.com.eju.pmls.commission.service.CrmOriginalService;
import cn.com.eju.pmls.commission.service.PmlsLnkYjNyService;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STUnsignedShortHex;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ny
 */
@Controller
@RequestMapping(value = "pmlsLnkYjNy")
public class PmlsLnkYjNyController extends BaseController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "pmlsLnkYjNyService")
    private PmlsLnkYjNyService pmlsLnkYjNyService;

    @Resource(name = "crmOriginalService")
    private CrmOriginalService crmOriginalService;

    //模板类型
    private String templateType = "";
    //业绩城市
    private String cityNo = "";

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap mop) {
        String cityNo = UserInfoHolder.get().getCityNo();

        //归属项目部列表
        try {
            Map<String, Object> reqMap = new HashMap<>();
            reqMap.put("cityNo", cityNo);
            ResultData<?> resultData = crmOriginalService.getProjectDepartment(reqMap);
            List<?> rebacklist = (List<?>) resultData.getReturnData();
            mop.put("rebacklist", rebacklist);
        } catch (Exception e) {
            logger.error("pmlsLnkYjNy", "PmlsLnkYjNyController", "getProjectDepartment", cityNo, null, "", "创建--初始化-归属项目部", e);
        }
        try {
            Map<String, Object> reqMap = new HashMap<>();
            reqMap.put("cityNo", cityNo);
            ResultData<?> resultData = crmOriginalService.getEstateNmList(reqMap);
            List<?> estateList = (List<?>) resultData.getReturnData();
            mop.put("estateList", estateList);
        } catch (Exception e) {
            logger.error("pmlsLnkYjNy", "PmlsLnkYjNyController", "getEstateNmList", cityNo, null, "", "创建--初始化-楼盘名称", e);
        }
        mop.put("cityNo", cityNo);
        String countDateEndStr = DateUtil.fmtDate(new Date(),"yyyy-MM-dd");
        mop.put("countDateEndStr",countDateEndStr);
//        return "commission/lnkYjNyList";
        return "commission/commissionListNy";
    }

    /**
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "queryList", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> queryList(HttpServletRequest request, ModelMap mop) {
        String cityNo = UserInfoHolder.get().getCityNo();
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        PageInfo pageInfo = getPageInfo(request);
        //获取页面显示数据
        ResultData<?> reback = new ResultData<>();
        try {
            reqMap.put("cityNo", cityNo);
            reback = pmlsLnkYjNyService.getLnkYjNyList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("pmlsLnkYjNy", "PmlsLnkYjNyController", "queryList", JsonUtil.parseToJson(reqMap), null, "", "", e);
            reback.setFail();
        }
        return reback;

    }

    /*
     * 导出Excel
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response) {
        String cityNo = UserInfoHolder.get().getCityNo();
        Map<String, Object> rspMap = new HashMap<String, Object>();
        Map<String, Object> reqMap = bindParamToMap(request);

        try {
            reqMap.put("cityNo", cityNo);
            reqMap.put("optFlag",1);
            ResultData<?> reback = pmlsLnkYjNyService.getLnkYjNyList(reqMap, null);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            String ctxPath = request.getSession().getServletContext().getRealPath("/");
            String url = ctxPath + "data" + File.separator + "sceneYSSRCommission";

            File dataFile = new File(ctxPath + "data");
            if (!dataFile.isDirectory()) {
                dataFile.mkdir();
            }

            File direct = new File(url);
            if (!direct.isDirectory()) {
                direct.mkdir();
            }

            long time = System.currentTimeMillis();
            //删掉历史文件
            FileUtils.deleteFile(direct, time);

            File directory = new File(url + File.separator + time);
            if (!directory.isDirectory()) {
                directory.mkdir();
            }

            try {
                String templateType = reqMap.get("estateType").toString();
                String templateTypeStr = "内佣模板";
                String pathName = url + File.separator + time + File.separator + templateTypeStr + ".xlsx";
                ExcelForLnkYjNy instance = new ExcelForLnkYjNy(Integer.valueOf(templateType));
                Map<String, String> map = new HashMap<>();
                UserInfo userInfo = UserInfoHolder.get();
                map.put("UserId", String.valueOf(userInfo.getUserId()));
                map.put("UserName", userInfo.getUserName() + "(" + userInfo.getUserCode() + ")");
                map.put("templateType", templateTypeStr);
                map.put("cityNo", cityNo);

                instance.downloadSheet(map, contentlist, new File(pathName));

                String fileName = templateTypeStr + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx";
                super.download(request, response, pathName, fileName);
            } catch (Exception e) {
                response.setCharacterEncoding("GBK");
                response.getWriter().write("下载Excel失败" + e.getMessage());
                response.getWriter().close();
                logger.error("下载Excel失败", e);
                e.printStackTrace();
            }

        } catch (Exception e) {
            logger.error("pmlsLnkYjNy",
                    "PmlsLnkYjNyController",
                    "export",
                    "input param: reqMap=" + reqMap.toString(), -
                            UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "导出异常",
                    e);
        }
    }

    /*
     * 导出check
     */
    @RequestMapping(value = "exportCheck", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> exportCheck(HttpServletRequest request, HttpServletResponse response) {
        String cityNo = UserInfoHolder.get().getCityNo();
        Map<String, Object> rspMap = new HashMap<String, Object>();
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> reback;
        try {
            reqMap.put("cityNo", cityNo);
            reback = pmlsLnkYjNyService.getLnkYjNyList(reqMap, null);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();
            if (contentlist == null || contentlist.isEmpty()) {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "没有数据，不能导出Excel！");
                return getOperateJSONView(rspMap);
            }
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导出失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        rspMap.put(Constant.RETURN_MSG_KEY, reback.getReturnMsg());
        return getOperateJSONView(rspMap);
    }

    @RequestMapping(value = "toView", method = RequestMethod.GET)
    public ModelAndView toView() {
        ModelAndView mv = new ModelAndView("commission/commissionImportNy");
        return mv;
    }

    /**
     * 导入
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "imput", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> imput(HttpServletRequest request, HttpServletResponse response) {
        // 返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

      /*  MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        MultipartFile multFile = multiRequest.getFile("historyDataFile");
        String fileName = multFile.getOriginalFilename();*/


        String fileName = "";
        String estateType = reqMap.get("estateTypeImput").toString();   //模板類型
        CommonsMultipartFile multFile = null;



        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                MultipartFile importFile = multiRequest.getFile(iter.next());
                //文件名
                fileName = importFile.getOriginalFilename();
                //MultipartFile转File
                multFile = (CommonsMultipartFile) importFile;
            }
        }


        Workbook wb;
        try {
            wb = new XSSFWorkbook(multFile.getInputStream());
            int number = wb.getNumberOfSheets();
            if (number > 1) {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "Excel文档格式错误,不允许多个sheet！");
                return getSearchJSONView(rspMap);
            }
            Sheet sheet1 = wb.getSheetAt(0);

            //导入check
            Map<String, Object> rtnMap = checkImport(sheet1, estateType);
            if (null != rtnMap) {
                return getSearchJSONView(rtnMap);
            }

            List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
            for (Row r : sheet1) {
                //校验日期不能大于当前
                Map<String, Object> dateMap = checkDate(wb, r);
                if (null != dateMap) {
                    return getSearchJSONView(dateMap);
                }
                Boolean reault = getSheetCellValue(wb, r, mapList);
                if (!reault) {
                    continue;
                }
            }
            //数据导入
            ResultData insertResult = insertLinkImport(mapList);
            //			Boolean insertResult = false;
            if (!"200".equals(insertResult.getReturnCode())) {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, insertResult.getReturnMsg());
                return getSearchJSONView(rspMap);
            }

            wb.close();

            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
            rspMap.put(Constant.RETURN_MSG_KEY, insertResult.getReturnMsg());
        } catch (IOException e1) {
            logger.error("pmlsLnkYjNy", "PmlsLnkYjNyController", "imput", "", null, "", "", e1);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导入数据失败！");
        }

        return getSearchJSONView(rspMap);
    }

    private Map<String, Object> checkDate(Workbook wb, Row r) {
        //跳过列头
        if (r.getRowNum() < 3) {
            return null;
        }
        Map<String, Object> rspMap = new HashMap<>();
        Date nowDate = new Date();
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        //日期
        String recordDate = getCellValue(wb, r.getCell(15));
        String recordDate1 = getCellValue(wb, r.getCell(18));
        String recordDate2 = getCellValue(wb, r.getCell(21));
        String recordDate3 = getCellValue(wb, r.getCell(24));
        String recordDate4 = getCellValue(wb, r.getCell(27));
        String recordDate5 = getCellValue(wb, r.getCell(30));
        String recordDate6 = getCellValue(wb, r.getCell(33));

        try {
            Date tmpDate = StringUtil.isEmpty(recordDate) ? nowDate : format1.parse(recordDate);
            Date tmpDate1 = StringUtil.isEmpty(recordDate1) ? nowDate : format1.parse(recordDate1);
            Date tmpDate2 = StringUtil.isEmpty(recordDate2) ? nowDate : format1.parse(recordDate2);
            Date tmpDate3 = StringUtil.isEmpty(recordDate3) ? nowDate : format1.parse(recordDate3);
            Date tmpDate4 = StringUtil.isEmpty(recordDate4) ? nowDate : format1.parse(recordDate4);
            Date tmpDate5 = StringUtil.isEmpty(recordDate5) ? nowDate : format1.parse(recordDate5);
            Date tmpDate6 = StringUtil.isEmpty(recordDate6) ? nowDate : format1.parse(recordDate6);

            if (tmpDate.getTime() > nowDate.getTime() || tmpDate1.getTime() > nowDate.getTime()
                    || tmpDate2.getTime() > nowDate.getTime() || tmpDate3.getTime() > nowDate.getTime()
                    || tmpDate4.getTime() > nowDate.getTime() || tmpDate5.getTime() > nowDate.getTime()
                    || tmpDate6.getTime() > nowDate.getTime()) {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "应计内佣日期不能大于当前日期");
                return rspMap;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 导入Check
     *
     * @return
     */
    private Map<String, Object> checkImport(Sheet sheet1, String strTemplateType) {
        Map<String, Object> rspMap = new HashMap<String, Object>();
        if (sheet1.getLastRowNum() == 0) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导入文件数据格式有误！");
            return rspMap;
        }

        //密码验证
        if (isSheetModify((XSSFSheet) sheet1)) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导入数据模板不对，请重新下载模板数据！");
            return rspMap;
        }

        templateType = sheet1.getRow(0).getCell(2).getStringCellValue();//模板类型
        String userId = sheet1.getRow(0).getCell(0).getStringCellValue();
        cityNo = sheet1.getRow(0).getCell(4).getStringCellValue();//业绩城市

        //验证模板类型
        if (!strTemplateType.equals(templateType)) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "选择的模板类型与导入文件的模板类型不匹配！");
            return rspMap;
        }

        //判断操作人的工号是否和模板的用户工号一致
        if (!userId.equals(UserInfoHolder.getUserId().toString())) {
            String userName = sheet1.getRow(0).getCell(3).getStringCellValue();
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导入模板为" + userName + "下载的，请使用自己下载的模板进行导入！");
            return rspMap;
        }
        //判断操作人的业绩城市是否和模板的业绩城市一致
        if (!cityNo.equals(UserInfoHolder.get().getCityNo())) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导入模板对应的业绩城市和您的业绩城市不一致，请下载最新的模板进行导入！");
            return rspMap;
        }
        return null;
    }

    //验证excel密码
    private boolean isSheetModify(XSSFSheet sheet) {
        boolean haveModify = true;
        try {
            String pwd = sheet.getRow(0).getCell(1).getStringCellValue();
            CTSheetProtection csheet = sheet.getCTWorksheet().getSheetProtection();
            if (csheet != null) {
                STUnsignedShortHex passwordST = csheet.xgetPassword();
                if (passwordST != null || !passwordST.getStringValue().equals(pwd)) {
                    haveModify = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return haveModify;
    }

    /**
     * 取得excel值
     * 1,跳过列头2,调整列数据填写错误，请调整导表后重新导入
     *
     * @param r excel行
     * @return
     */
    private Boolean getSheetCellValue(Workbook wb, Row r, List<Map<String, Object>> mapList) {

        //跳过列头
        if (r.getRowNum() < 3) {
            return false;
        }
        String reportId = r.getCell(3).getStringCellValue();    //报备ID

        Map<String, Object> map = new HashMap<>();
        //套数
        String num = getCellValue(wb, r.getCell(7));
        //岗位佣金
        String postAmountTotal = getCellValue(wb, r.getCell(11));
        //团佣
        String totalAmountTotal = getCellValue(wb, r.getCell(12));
        //岗位佣金
        String postAmount = getCellValue(wb, r.getCell(13));
//        //应计无数据，忽略调整数据
//        if (StringUtil.isEmpty(postAmount)) {
//            return false;
//        }
        //团佣
        String totalAmount = getCellValue(wb, r.getCell(14));
        //日期
        String recordDate = getCellValue(wb, r.getCell(15));
        //岗位佣金
        String postAmount1 = getCellValue(wb, r.getCell(16));
        //团佣
        String totalAmount1 = getCellValue(wb, r.getCell(17));
        //日期
        String recordDate1 = getCellValue(wb, r.getCell(18));
        //岗位佣金
        String postAmount2 = getCellValue(wb, r.getCell(19));
        //团佣
        String totalAmount2 = getCellValue(wb, r.getCell(20));
        //日期
        String recordDate2 = getCellValue(wb, r.getCell(21));
        //岗位佣金
        String postAmount3 = getCellValue(wb, r.getCell(22));
        //团佣
        String totalAmount3 = getCellValue(wb, r.getCell(23));
        //日期
        String recordDate3 = getCellValue(wb, r.getCell(24));
        //岗位佣金
        String postAmount4 = getCellValue(wb, r.getCell(25));
        //团佣
        String totalAmount4 = getCellValue(wb, r.getCell(26));
        //日期
        String recordDate4 = getCellValue(wb, r.getCell(27));
        //岗位佣金
        String postAmount5 = getCellValue(wb, r.getCell(28));
        //团佣
        String totalAmount5 = getCellValue(wb, r.getCell(29));
        //日期
        String recordDate5 = getCellValue(wb, r.getCell(30));
        //岗位佣金
        String postAmount6 = getCellValue(wb, r.getCell(31));
        //团佣
        String totalAmount6 = getCellValue(wb, r.getCell(32));
        //日期
        String recordDate6 = getCellValue(wb, r.getCell(33));
        //公司编号
        String companyNo = getCellValue(wb, r.getCell(34));
        //报备明细id
        String detailId = getCellValue(wb, r.getCell(35));
        String switchFlag = getCellValue(wb, r.getCell(36));
        String switchFlag1 = getCellValue(wb, r.getCell(37));
        String switchFlag2 = getCellValue(wb, r.getCell(38));
        String switchFlag3 = getCellValue(wb, r.getCell(39));
        String switchFlag4 = getCellValue(wb, r.getCell(40));
        String switchFlag5 = getCellValue(wb, r.getCell(41));
        String switchFlag6 = getCellValue(wb, r.getCell(42));

        //只要填了值就添加数据库
        if (StringUtil.isNotEmpty(postAmount) || StringUtil.isNotEmpty(totalAmount) || StringUtil.isNotEmpty(recordDate)
                || StringUtil.isNotEmpty(postAmountTotal) || StringUtil.isNotEmpty(totalAmountTotal) || StringUtil.isNotEmpty(postAmount1) || StringUtil.isNotEmpty(totalAmount1) || StringUtil.isNotEmpty(recordDate1)
                || StringUtil.isNotEmpty(postAmount2) || StringUtil.isNotEmpty(totalAmount2) || StringUtil.isNotEmpty(recordDate2) || StringUtil.isNotEmpty(postAmount3) || StringUtil.isNotEmpty(totalAmount3)
                || StringUtil.isNotEmpty(recordDate3) || StringUtil.isNotEmpty(recordDate4) || StringUtil.isNotEmpty(recordDate5) || StringUtil.isNotEmpty(recordDate6)
                || StringUtil.isNotEmpty(totalAmount4) || StringUtil.isNotEmpty(totalAmount5) || StringUtil.isNotEmpty(totalAmount6)
                || StringUtil.isNotEmpty(postAmount4) || StringUtil.isNotEmpty(postAmount5) || StringUtil.isNotEmpty(postAmount6)) {
            //数据添加
            map.put("cityNo", cityNo);
            map.put("templateType", templateType);
            map.put("reportId", reportId);

            map.put("postAmountTotal", postAmountTotal);
            map.put("totalAmountTotal", totalAmountTotal);
            map.put("postAmount", postAmount);
            map.put("totalAmount", totalAmount);
            map.put("recordDate", recordDate);
            map.put("postAmount1", postAmount1);
            map.put("totalAmount1", totalAmount1);
            map.put("recordDate1", recordDate1);
            map.put("postAmount2", postAmount2);
            map.put("totalAmount2", totalAmount2);
            map.put("recordDate2", recordDate2);
            map.put("postAmount3", postAmount3);
            map.put("totalAmount3", totalAmount3);
            map.put("recordDate3", recordDate3);
            map.put("postAmount4", postAmount4);
            map.put("totalAmount4", totalAmount4);
            map.put("recordDate4", recordDate4);
            map.put("postAmount5", postAmount5);
            map.put("totalAmount5", totalAmount5);
            map.put("recordDate5", recordDate5);
            map.put("postAmount6", postAmount6);
            map.put("totalAmount6", totalAmount6);
            map.put("recordDate6", recordDate6);
            map.put("num", num);
            map.put("companyNo", companyNo);
            map.put("detailId", detailId);
            map.put("switchFlag", switchFlag);
            map.put("switchFlag1", switchFlag1);
            map.put("switchFlag2", switchFlag2);
            map.put("switchFlag3", switchFlag3);
            map.put("switchFlag4", switchFlag4);
            map.put("switchFlag5", switchFlag5);
            map.put("switchFlag6", switchFlag6);

            map.put("crtEmpId", UserInfoHolder.getUserId());
            map.put("uptEmpId", UserInfoHolder.getUserId());
            map.put("delFlg", 0);
            mapList.add(map);
        }
        return true;
    }

    /**
     * 导入操作
     *
     * @param mapList
     * @return
     */
    private ResultData insertLinkImport(List<Map<String, Object>> mapList) {
        ResultData resultData = new ResultData();
        try {
            resultData = pmlsLnkYjNyService.nyImport(mapList);
            return resultData;
        } catch (Exception e) {
            resultData.setReturnCode("-1");
            return resultData;
        }
    }


    //excel类型判断
    private String getCellValue(Workbook wb, Cell cell) {
        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
        String rtnVal = "";

        DecimalFormat df = new DecimalFormat("0.00");
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            //判断是否为日期类型
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                //用于转化为日期格式
                Date d = cell.getDateCellValue();
                DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                rtnVal = formater.format(d);
            } else {
                // 用于格式化数字，只保留数字的整数部分
                rtnVal = df.format(cell.getNumericCellValue());
            }
        }
        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            rtnVal = cell.getStringCellValue();
        }
        if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            evaluator.evaluateFormulaCell(cell);
            rtnVal = df.format(cell.getNumericCellValue());
        }
        return rtnVal;
    }
}
