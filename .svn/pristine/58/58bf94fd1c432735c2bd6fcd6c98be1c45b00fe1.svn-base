package cn.com.eju.deal.scene.padCommission.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.linkage.service.LinkageCityService;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.FileUtils;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateInfoDto;
import cn.com.eju.deal.dto.scene.PadCommissionResultDto;
import cn.com.eju.deal.houseLinkage.estate.service.EstateService;
import cn.com.eju.deal.poi.ExcelForPadCommision;
import cn.com.eju.deal.scene.padCommission.service.ScenePadCommissionService;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Controller层
 *
 * @author xuliang
 * @date 2018年1月31日20:12:51
 */
@Controller
@RequestMapping(value = "scenePadCommission")
public class ScenePadCommissionController extends BaseController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "scenePadCommissionService")
    private ScenePadCommissionService scenePadCommissionService;

    @Resource(name = "linkageCityService")
    private LinkageCityService linkageCityService;

    @Resource(name = "estateService")
    private EstateService estateService;

    //模板类型
    private String templateType = "";
    //业绩城市
    private String cityNo = "";

    /**
     * 初始化
     *
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap mop) {
        String cityNo = UserInfoHolder.get().getCityNo();

        //归属项目部列表
        try {
            Map<String, Object> reqMap = new HashMap<>();
            reqMap.put("cityNo", cityNo);
            ResultData<?> resultData = estateService.getProjectDepartment(reqMap);
            List<?> rebacklist = (List<?>) resultData.getReturnData();
            mop.put("rebacklist", rebacklist);
        } catch (Exception e) {
            logger.error("scenePadCommission", "ScenePadCommissionController", "", "", null, "", "创建--初始化-归属项目部", e);
        }
        try{
            Map<String,Object> reqMap = new HashMap<>();
            reqMap.put("cityNo", cityNo);
            ResultData<?> resultData = estateService.getEstateNmList(reqMap);
            List<?> estateList = (List<?>) resultData.getReturnData();
            mop.put("estateList", estateList);
        } catch (Exception e) {
            logger.error("sceneInCommission", "SceneInCommissionController", "", "", null, "", "创建--初始化-楼盘名称", e);
        }

        //mop.put("districtList", resultDistrictList.getReturnData());
        return "scene/padCommission/scenePadCommissionList";
    }
    /*
     */

    /**
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "qScenePadCommission", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap mop) {
        String cityNo = UserInfoHolder.get().getCityNo();
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        String estateTypes = (String) reqMap.get("estateType");
        Integer estateType = Integer.valueOf(estateTypes);

        PageInfo pageInfo = getPageInfo(request);
        //获取页面显示数据
        ResultData<?> reback = new ResultData<>();
        try {
            reqMap.put("cityNo", cityNo);
            reback = scenePadCommissionService.getPadCommissionList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("scenePadCommission", "ScenePadCommissionController", "qScenePadCommission", "", null, "", "", e);
            reback.setFail();
        }

        //页面数据
        List<?> contentlist = (List<?>) reback.getReturnData();

        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        String returnAddress = "";
        returnAddress = "scene/padCommission/scenePadCommissionListCtx";

        return returnAddress;

    }

    @RequestMapping(value = "del", method = RequestMethod.POST)
    @ResponseBody
    public ResultData del(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        //获取页面显示数据
        ResultData<?> reback = new ResultData<>();
        try {
            reback = scenePadCommissionService.del(reqMap);
        } catch (Exception e) {
            logger.error("scenePadCommission", "ScenePadCommissionController", "del", "", null, "", "", e);
            reback.setFail("删除异常！");
        }

        return reback;

    }

    /*
     * 导出check
     * created by xuliang 2018-2-1 16:12:03
     */
    @RequestMapping(value = "exportCheck", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> exportCheck(HttpServletRequest request, HttpServletResponse response) {
        String cityNo = UserInfoHolder.get().getCityNo();
        Map<String, Object> rspMap = new HashMap<String, Object>();
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> reback;
        try {
            reqMap.put("cityNo", cityNo);
            reback = scenePadCommissionService.getPadCommissionList(reqMap, null);
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

    /*
     * 导出Excel
     * created by xuliang 2018-2-1 16:12:03
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response) {
        String cityNo = UserInfoHolder.get().getCityNo();
        Map<String, Object> rspMap = new HashMap<String, Object>();
        Map<String, Object> reqMap = bindParamToMap(request);

        try {
            reqMap.put("cityNo", cityNo);
            ResultData<?> reback = scenePadCommissionService.getPadCommissionList(reqMap, null);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            String ctxPath = request.getSession().getServletContext().getRealPath("/");
            String url = ctxPath + "data" + File.separator + "scenePadCommission";

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
                String templateTypeStr = SystemParam.getDicValueByDicCode(templateType);
                String pathName = url + File.separator + time + File.separator + templateTypeStr + ".xlsx";
                ExcelForPadCommision instance = new ExcelForPadCommision(Integer.valueOf(templateType));
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
            logger.error("scenePadCommission",
                    "ScenePadCommissionController",
                    "export",
                    "input param: reqMap=" + reqMap.toString(), -
                            UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "导出异常",
                    e);
        }
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

        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        MultipartFile multFile = multiRequest.getFile("historyDataFile");
        String fileName = multFile.getOriginalFilename();
        String estateType = reqMap.get("estateTypeImput").toString();   //模板類型

        Workbook wb;
        try {
            wb = new XSSFWorkbook(multFile.getInputStream());
            int  number= wb.getNumberOfSheets();
            if (number>1){
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
                Boolean reault = getSheetCellValue(wb, r, mapList);
                if (reault == false) {
                    continue;
                }
            }
            //数据导入
            ResultData insertResult = insertLinkImport(mapList);
//			Boolean insertResult = false;
            if (!"200".equals(insertResult.getReturnCode())) {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "导入数据失败！");
                return getSearchJSONView(rspMap);
            }

            wb.close();

            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
            rspMap.put(Constant.RETURN_MSG_KEY, insertResult.getReturnMsg());
        } catch (IOException e1) {
            logger.error("sceneInCommission", "SceneInCommissionController", "imput", "", null, "", "", e1);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导入数据失败！");
        }

        return getSearchJSONView(rspMap);
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
     *
     * @param r excel行
     * @return
     */
    private Boolean getSheetCellValue(Workbook wb, Row r, List<Map<String, Object>> mapList) {

        //跳过列头
        if (r.getRowNum() < 3) {
            return false;
        }
        String reportId = r.getCell(2).getStringCellValue();    //报备ID

        Map<String, Object> map = new HashMap<>();
        //应垫佣金税前
        String befTaxYjAmountTotal = getCellValue(wb, r.getCell(9));
        //应垫佣金税后
        String aftTaxYjAmountTotal = getCellValue(wb, r.getCell(10));
        //应垫佣金税前
        String befTaxYjAmount = getCellValue(wb, r.getCell(11));
        //应垫佣金税后
        String aftTaxYjAmount = getCellValue(wb, r.getCell(12));
        //应垫佣金日期
        String yjRecordDate = getCellValue(wb, r.getCell(13));
        //应垫佣金税前
        String befTaxYjAmount1 = getCellValue(wb, r.getCell(14));
        //应垫佣金税后
        String aftTaxYjAmount1 = getCellValue(wb, r.getCell(15));
        //应垫佣金日期
        String yjRecordDate1 = getCellValue(wb, r.getCell(16));
        //应垫佣金税前
        String befTaxYjAmount2 = getCellValue(wb, r.getCell(17));
        //应垫佣金税后
        String aftTaxYjAmount2 = getCellValue(wb, r.getCell(18));
        //应垫佣金日期
        String yjRecordDate2 = getCellValue(wb, r.getCell(19));
        //应垫佣金税前
        String befTaxYjAmount3 = getCellValue(wb, r.getCell(20));
        //应垫佣金税后
        String aftTaxYjAmount3 = getCellValue(wb, r.getCell(21));
        //应垫佣金日期
        String yjRecordDate3 = getCellValue(wb, r.getCell(22));

        //实垫佣金小计税前
        String befTaxSjAmountTotal = getCellValue(wb, r.getCell(23));
        //实垫佣金小计税后
        String aftTaxSjAmountTotal = getCellValue(wb, r.getCell(24));
        //实垫佣金税前
        String befTaxSjAmount = getCellValue(wb, r.getCell(25));
        //实垫佣金税后
        String aftTaxSjAmount = getCellValue(wb, r.getCell(26));
        //实垫佣金日期
        String sjRecordDate = getCellValue(wb, r.getCell(27));
        //实垫佣金税前
        String befTaxSjAmount1 = getCellValue(wb, r.getCell(28));
        //实垫佣金税后
        String aftTaxSjAmount1 = getCellValue(wb, r.getCell(29));
        //实垫佣金日期
        String sjRecordDate1 = getCellValue(wb, r.getCell(30));
        //实垫佣金税前
        String befTaxSjAmount2 = getCellValue(wb, r.getCell(31));
        //实垫佣金税后
        String aftTaxSjAmount2 = getCellValue(wb, r.getCell(32));
        //实垫佣金日期
        String sjRecordDate2 = getCellValue(wb, r.getCell(33));

        //只要填了值就添加数据库
        if (StringUtil.isNotEmpty(befTaxYjAmount) || StringUtil.isNotEmpty(aftTaxYjAmount) || StringUtil.isNotEmpty(yjRecordDate)
                || StringUtil.isNotEmpty(befTaxSjAmount) || StringUtil.isNotEmpty(aftTaxSjAmount) || StringUtil.isNotEmpty(sjRecordDate)
                ||StringUtil.isNotEmpty(befTaxYjAmountTotal)||StringUtil.isNotEmpty(aftTaxYjAmountTotal)||StringUtil.isNotEmpty(befTaxYjAmount1)||StringUtil.isNotEmpty(aftTaxYjAmount1)||StringUtil.isNotEmpty(yjRecordDate1)
                ||StringUtil.isNotEmpty(befTaxYjAmount2)||StringUtil.isNotEmpty(aftTaxYjAmount2)||StringUtil.isNotEmpty(yjRecordDate2)||StringUtil.isNotEmpty(befTaxYjAmount3)||StringUtil.isNotEmpty(aftTaxYjAmount3)
                ||StringUtil.isNotEmpty(yjRecordDate3)||StringUtil.isNotEmpty(befTaxSjAmountTotal)||StringUtil.isNotEmpty(aftTaxSjAmountTotal)||StringUtil.isNotEmpty(befTaxSjAmount1)||StringUtil.isNotEmpty(aftTaxSjAmount1)
                ||StringUtil.isNotEmpty(sjRecordDate1)||StringUtil.isNotEmpty(befTaxSjAmount2)||StringUtil.isNotEmpty(aftTaxSjAmount2)||StringUtil.isNotEmpty(sjRecordDate2)) {
            //数据添加
            map.put("cityNo", cityNo);
            map.put("templateType", templateType);
            map.put("reportId", reportId);

            map.put("befTaxYjAmountTotal", befTaxYjAmountTotal);
            map.put("aftTaxYjAmountTotal", aftTaxYjAmountTotal);
            map.put("befTaxYjAmount", befTaxYjAmount);
            map.put("aftTaxYjAmount", aftTaxYjAmount);
            map.put("yjRecordDate", yjRecordDate);
            map.put("befTaxYjAmount1", befTaxYjAmount1);
            map.put("aftTaxYjAmount1", aftTaxYjAmount1);
            map.put("yjRecordDate1", yjRecordDate1);
            map.put("befTaxYjAmount2", befTaxYjAmount2);
            map.put("aftTaxYjAmount2", aftTaxYjAmount2);
            map.put("yjRecordDate2", yjRecordDate2);
            map.put("befTaxYjAmount3", befTaxYjAmount3);
            map.put("aftTaxYjAmount3", aftTaxYjAmount3);
            map.put("yjRecordDate3", yjRecordDate3);

            map.put("befTaxSjAmountTotal", befTaxSjAmountTotal);
            map.put("aftTaxSjAmountTotal", aftTaxSjAmountTotal);
            map.put("befTaxSjAmount", befTaxSjAmount);
            map.put("aftTaxSjAmount", aftTaxSjAmount);
            map.put("sjRecordDate", sjRecordDate);
            map.put("befTaxSjAmount1", befTaxSjAmount1);
            map.put("aftTaxSjAmount1", aftTaxSjAmount1);
            map.put("sjRecordDate1", sjRecordDate1);
            map.put("befTaxSjAmount2", befTaxSjAmount2);
            map.put("aftTaxSjAmount2", aftTaxSjAmount2);
            map.put("sjRecordDate2", sjRecordDate2);

            map.put("CrtEmpId", UserInfoHolder.getUserId());
            map.put("UptEmpId", UserInfoHolder.getUserId());
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
            EstateInfoDto estateInfoDto = new EstateInfoDto();
            List<PadCommissionResultDto> padCommissionResultList = new ArrayList<PadCommissionResultDto>();

            //批量处理数据
            for (Map<String, Object> map : mapList) {
                String cityNo = map.get("cityNo").toString();
                String reportId = map.get("reportId").toString();
                String CrtEmpId = map.get("CrtEmpId").toString();
                String UptEmpId = map.get("UptEmpId").toString();
                String delFlg = map.get("delFlg").toString();

                String befTaxYjAmountTotal = map.get("befTaxYjAmountTotal").toString();
                String aftTaxYjAmountTotal = map.get("aftTaxYjAmountTotal").toString();
                String befTaxYjAmount = map.get("befTaxYjAmount").toString();
                String aftTaxYjAmount = map.get("aftTaxYjAmount").toString();
                String yjRecordDate = map.get("yjRecordDate").toString();
                String befTaxYjAmount1 = map.get("befTaxYjAmount1").toString();
                String aftTaxYjAmount1 = map.get("aftTaxYjAmount1").toString();
                String yjRecordDate1 = map.get("yjRecordDate1").toString();
                String befTaxYjAmount2 = map.get("befTaxYjAmount2").toString();
                String aftTaxYjAmount2 = map.get("aftTaxYjAmount2").toString();
                String yjRecordDate2 = map.get("yjRecordDate2").toString();
                String befTaxYjAmount3 = map.get("befTaxYjAmount3").toString();
                String aftTaxYjAmount3 = map.get("aftTaxYjAmount3").toString();
                String yjRecordDate3 = map.get("yjRecordDate3").toString();

                String befTaxSjAmountTotal = map.get("befTaxSjAmountTotal").toString();
                String aftTaxSjAmountTotal = map.get("aftTaxSjAmountTotal").toString();
                String befTaxSjAmount = map.get("befTaxSjAmount").toString();
                String aftTaxSjAmount = map.get("aftTaxSjAmount").toString();
                String sjRecordDate = map.get("sjRecordDate").toString();
                String befTaxSjAmount1 = map.get("befTaxSjAmount1").toString();
                String aftTaxSjAmount1 = map.get("aftTaxSjAmount1").toString();
                String sjRecordDate1 = map.get("sjRecordDate1").toString();
                String befTaxSjAmount2 = map.get("befTaxSjAmount2").toString();
                String aftTaxSjAmount2 = map.get("aftTaxSjAmount2").toString();
                String sjRecordDate2 = map.get("sjRecordDate2").toString();


                PadCommissionResultDto padCommissionDto = new PadCommissionResultDto();
                padCommissionDto.setCityNo(cityNo);
                padCommissionDto.setReportId(reportId);
                padCommissionDto.setCrtEmpId(CrtEmpId);
                padCommissionDto.setUptEmpId(UptEmpId);
                padCommissionDto.setDelFlag(delFlg);

                padCommissionDto.setBefTaxYjAmountTotal(befTaxYjAmountTotal);
                padCommissionDto.setAftTaxYjAmountTotal(aftTaxYjAmountTotal);
                padCommissionDto.setBefTaxYjAmount(befTaxYjAmount);
                padCommissionDto.setAftTaxYjAmount(aftTaxYjAmount);
                padCommissionDto.setYjRecordDate(yjRecordDate);
                padCommissionDto.setBefTaxYjAmount1(befTaxYjAmount1);
                padCommissionDto.setAftTaxYjAmount1(aftTaxYjAmount1);
                padCommissionDto.setYjRecordDate1(yjRecordDate1);
                padCommissionDto.setBefTaxYjAmount2(befTaxYjAmount2);
                padCommissionDto.setAftTaxYjAmount2(aftTaxYjAmount2);
                padCommissionDto.setYjRecordDate2(yjRecordDate2);
                padCommissionDto.setBefTaxYjAmount3(befTaxYjAmount3);
                padCommissionDto.setAftTaxYjAmount3(aftTaxYjAmount3);
                padCommissionDto.setYjRecordDate3(yjRecordDate3);

                padCommissionDto.setBefTaxSjAmountTotal(befTaxSjAmountTotal);
                padCommissionDto.setAftTaxSjAmountTotal(aftTaxSjAmountTotal);
                padCommissionDto.setBefTaxSjAmount(befTaxSjAmount);
                padCommissionDto.setAftTaxSjAmount(aftTaxSjAmount);
                padCommissionDto.setSjRecordDate(sjRecordDate);
                padCommissionDto.setBefTaxSjAmount1(befTaxSjAmount1);
                padCommissionDto.setAftTaxSjAmount1(aftTaxSjAmount1);
                padCommissionDto.setSjRecordDate1(sjRecordDate1);
                padCommissionDto.setBefTaxSjAmount2(befTaxSjAmount2);
                padCommissionDto.setAftTaxSjAmount2(aftTaxSjAmount2);
                padCommissionDto.setSjRecordDate2(sjRecordDate2);

                padCommissionResultList.add(padCommissionDto);

            }
            estateInfoDto.setPadCommissionResultList(padCommissionResultList);

            resultData = scenePadCommissionService.insertLnkImport(estateInfoDto);
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
