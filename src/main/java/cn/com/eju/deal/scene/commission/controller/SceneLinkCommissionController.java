package cn.com.eju.deal.scene.commission.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
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
import cn.com.eju.deal.dto.scene.CommissionResultDto;
import cn.com.eju.deal.houseLinkage.estate.service.EstateService;
import cn.com.eju.deal.poi.ExcelForCommision;
import cn.com.eju.deal.scene.commission.service.SceneLinkCommissionService;
import cn.com.eju.deal.scene.enums.EstateTypeEnum;
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
 * Created by eju on 2019/4/3.
 */
@Controller
@RequestMapping(value = "sceneLinkCommission")
public class SceneLinkCommissionController extends BaseController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "estateService")
    private EstateService estateService;

    @Resource(name = "sceneLinkCommissionService")
    private SceneLinkCommissionService sceneLinkCommissionService;

    //模板类型
    private String templateType= "";
    //业绩城市
    private String cityNo = "";

    @RequestMapping(value = "", method = RequestMethod.GET)
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
            logger.error("sceneLinkCommission", "SceneLinkCommissionController", "", "", null, "", "创建--初始化-归属项目部", e);
        }
        try{
            Map<String,Object> reqMap = new HashMap<>();
            reqMap.put("cityNo", cityNo);
            ResultData<?> resultData = estateService.getEstateNmList(reqMap);
            List<?> estateList = (List<?>) resultData.getReturnData();
            mop.put("estateList", estateList);
        } catch (Exception e) {
            logger.error("sceneLinkCommission", "SceneLinkCommissionController", "", "", null, "", "创建--初始化-楼盘名称", e);
        }

        return "scene/commission/sceneLinkCommissionList";
    }

    /**
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "qSceneLinkCommission", method = RequestMethod.GET)
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
            reback = sceneLinkCommissionService.getLinkCommissionList(reqMap, pageInfo);

        } catch (Exception e) {
            logger.error("sceneLinkCommission", "SceneLinkCommissionController", "qSceneLinkCommission", "", null, "", "", e);
            reback.setFail();
        }

        //页面数据
        List<?> contentlist = (List<?>) reback.getReturnData();

        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        mop.addAttribute("estateType",estateType);
        String returnAddress = "";
        returnAddress = "scene/commission/sceneLinkCommissionListCtx";


        return returnAddress;

    }

    /*
     * 导出Excel
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response) {
        String cityNo = UserInfoHolder.get().getCityNo();
        Map<String, Object> rspMap = new HashMap<String, Object>();
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> reback = new ResultData<>();
        try {
            reqMap.put("cityNo", cityNo);
            reqMap.put("optFlag",1);
            reback = sceneLinkCommissionService.getLinkCommissionList(reqMap, null);

            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            String ctxPath = request.getSession().getServletContext().getRealPath("/");
            String url = ctxPath + "data" + File.separator + "sceneLinkCommission";

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
                ExcelForCommision instance = new ExcelForCommision(Integer.valueOf(templateType));
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
            logger.error("sceneLinkCommission",
                    "SceneLinkCommissionController",
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
    @RequestMapping(value = "exportCheck", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> exportCheck(HttpServletRequest request, HttpServletResponse response) {
        String cityNo = UserInfoHolder.get().getCityNo();
        Map<String, Object> rspMap = new HashMap<String, Object>();
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> reback;
        try {
            reqMap.put("cityNo", cityNo);
            reback = sceneLinkCommissionService.getLinkCommissionList(reqMap, null);
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
                Integer reault = getSheetCellValue(wb, r, mapList,estateType);
                if (reault == 1) {
                    continue;
                }else if(reault == 2){
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    rspMap.put(Constant.RETURN_MSG_KEY, "调整列数据填写错误，请调整导表后重新导入！");
                    return getSearchJSONView(rspMap);
                }else if(reault==3){
                    String type = "";
                    if(estateType.equals(EstateTypeEnum.YSSR.getCode().toString())){
                        type = EstateTypeEnum.YSSR.getName();
                    }else if(estateType.equals(EstateTypeEnum.YJSR.getCode().toString())){
                        type = EstateTypeEnum.YJSR.getName();
                    }else if(estateType.equals(EstateTypeEnum.YJFY.getCode().toString())){
                        type = EstateTypeEnum.YJFY.getName();
                    }else if(estateType.equals(EstateTypeEnum.YJSS.getCode().toString())){
                        type = EstateTypeEnum.YJSS.getName();
                    }else if(estateType.equals(EstateTypeEnum.SJFY.getCode().toString())){
                        type = EstateTypeEnum.SJFY.getName();
                    }
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    rspMap.put(Constant.RETURN_MSG_KEY, type+"日期不能大于当前日期！");
                    return getSearchJSONView(rspMap);
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
     *1,跳过列头2,调整列数据填写错误，请调整导表后重新导入
     * @param r excel行
     * @param estateType
     * @return
     */
    private Integer getSheetCellValue(Workbook wb, Row r, List<Map<String, Object>> mapList, String estateType) {

        //跳过列头
        if (r.getRowNum() < 3) {
            return 1;
        }
        String reportId = r.getCell(3).getStringCellValue();    //报备ID

        Map<String, Object> map = new HashMap<>();
        //套数
        String num = getCellValue(wb, r.getCell(42));
        //税前
        String befTaxAmountTotal = getCellValue(wb, r.getCell(11));
        //税后
        String aftTaxAmountTotal = getCellValue(wb, r.getCell(12));
        //税前
        String befTaxAmount = getCellValue(wb, r.getCell(13));
        //税后
        String aftTaxAmount = getCellValue(wb, r.getCell(14));
        //日期
        String recordDate = getCellValue(wb, r.getCell(15));
        //税前
        String befTaxAmount1 = getCellValue(wb, r.getCell(16));
        //税后
        String aftTaxAmount1 = getCellValue(wb, r.getCell(17));
        //日期
        String recordDate1 = getCellValue(wb, r.getCell(18));
        //税前
        String befTaxAmount2 = getCellValue(wb, r.getCell(19));
        //税后
        String aftTaxAmount2 = getCellValue(wb, r.getCell(20));
        //日期
        String recordDate2 = getCellValue(wb, r.getCell(21));
        //税前
        String befTaxAmount3 = getCellValue(wb, r.getCell(22));
        //税后
        String aftTaxAmount3 = getCellValue(wb, r.getCell(23));
        //日期
        String recordDate3 = getCellValue(wb, r.getCell(24));
        //税前
        String befTaxAmount4 = getCellValue(wb, r.getCell(25));
        //税后
        String aftTaxAmount4 = getCellValue(wb, r.getCell(26));
        //日期
        String recordDate4 = getCellValue(wb, r.getCell(27));
        //税前
        String befTaxAmount5 = getCellValue(wb, r.getCell(28));
        //税后
        String aftTaxAmount5 = getCellValue(wb, r.getCell(29));
        //日期
        String recordDate5 = getCellValue(wb, r.getCell(30));
        //税前
        String befTaxAmount6 = getCellValue(wb, r.getCell(31));
        //税后
        String aftTaxAmount6 = getCellValue(wb, r.getCell(32));
        //日期
        String recordDate6 = getCellValue(wb, r.getCell(33));
        //公司编号
        String companyNo = getCellValue(wb, r.getCell(4));
        //报备明细id
        String detailId = getCellValue(wb, r.getCell(34));
        String switchFlag = getCellValue(wb, r.getCell(35));
        String switchFlag1 = getCellValue(wb, r.getCell(36));
        String switchFlag2 = getCellValue(wb, r.getCell(37));
        String switchFlag3 = getCellValue(wb, r.getCell(38));
        String switchFlag4 = getCellValue(wb, r.getCell(39));
        String switchFlag5 = getCellValue(wb, r.getCell(40));
        String switchFlag6 = getCellValue(wb, r.getCell(41));
        //检查数据 应收收入等列有数据，调整列才允许填写数据
        if((StringUtil.isNotEmpty(befTaxAmount1)||StringUtil.isNotEmpty(aftTaxAmount1)||StringUtil.isNotEmpty(befTaxAmount2)
                ||StringUtil.isNotEmpty(aftTaxAmount2)||StringUtil.isNotEmpty(befTaxAmount3)
                ||StringUtil.isNotEmpty(aftTaxAmount3)||StringUtil.isNotEmpty(recordDate1)
                ||StringUtil.isNotEmpty(recordDate2)||StringUtil.isNotEmpty(recordDate3)
                ||StringUtil.isNotEmpty(recordDate4)||StringUtil.isNotEmpty(recordDate5)||StringUtil.isNotEmpty(recordDate6)
                ||StringUtil.isNotEmpty(befTaxAmount4)||StringUtil.isNotEmpty(befTaxAmount5)||StringUtil.isNotEmpty(befTaxAmount6)
                ||StringUtil.isNotEmpty(aftTaxAmount4)||StringUtil.isNotEmpty(aftTaxAmount5)||StringUtil.isNotEmpty(aftTaxAmount6)
            )&&(StringUtil.isEmpty(befTaxAmount)||StringUtil.isEmpty(aftTaxAmount)||StringUtil.isEmpty(recordDate))){
            return 2;
        }
        /*//num为负不允许调整列有值
        if((StringUtil.isNotEmpty(befTaxAmount1)||StringUtil.isNotEmpty(aftTaxAmount1)||StringUtil.isNotEmpty(befTaxAmount2)
                ||StringUtil.isNotEmpty(aftTaxAmount2)||StringUtil.isNotEmpty(befTaxAmount3)
                ||StringUtil.isNotEmpty(aftTaxAmount3)||StringUtil.isNotEmpty(recordDate1)
                ||StringUtil.isNotEmpty(recordDate2)||StringUtil.isNotEmpty(recordDate3)
                ||StringUtil.isNotEmpty(recordDate4)||StringUtil.isNotEmpty(recordDate5)||StringUtil.isNotEmpty(recordDate6)
                ||StringUtil.isNotEmpty(befTaxAmount4)||StringUtil.isNotEmpty(befTaxAmount5)||StringUtil.isNotEmpty(befTaxAmount6)
                ||StringUtil.isNotEmpty(aftTaxAmount4)||StringUtil.isNotEmpty(aftTaxAmount5)||StringUtil.isNotEmpty(aftTaxAmount6)
            )&&("-1").equals(num)){
            return 2;
        }*/
        //日期必须小于当前日期
        try{
            if((StringUtil.isNotEmpty(recordDate) && new SimpleDateFormat("yyyy-MM-dd").parse(recordDate).after(new Date()))
                    ||(StringUtil.isNotEmpty(recordDate1) && new SimpleDateFormat("yyyy-MM-dd").parse(recordDate1).after(new Date()))
                    ||(StringUtil.isNotEmpty(recordDate2) && new SimpleDateFormat("yyyy-MM-dd").parse(recordDate2).after(new Date()))
                    ||(StringUtil.isNotEmpty(recordDate3) && new SimpleDateFormat("yyyy-MM-dd").parse(recordDate3).after(new Date()))
                    ||(StringUtil.isNotEmpty(recordDate4) && new SimpleDateFormat("yyyy-MM-dd").parse(recordDate4).after(new Date()))
                    ||(StringUtil.isNotEmpty(recordDate5) && new SimpleDateFormat("yyyy-MM-dd").parse(recordDate5).after(new Date()))
                    ||(StringUtil.isNotEmpty(recordDate6) && new SimpleDateFormat("yyyy-MM-dd").parse(recordDate6).after(new Date()))){
                return 3;
            }
        }catch (Exception e){
            logger.error("getSheetCellValue", "SceneInCommissionController", "imput", "", null, "", "日期转换失败", e);
        }
        //只要填了值就添加数据库
        if (StringUtil.isNotEmpty(befTaxAmountTotal) || StringUtil.isNotEmpty(aftTaxAmountTotal)
                || StringUtil.isNotEmpty(befTaxAmount) || StringUtil.isNotEmpty(aftTaxAmount) || StringUtil.isNotEmpty(recordDate)) {
            //数据添加
            map.put("cityNo", cityNo);
            map.put("templateType", templateType);
            map.put("reportId", reportId);

            map.put("befTaxAmountTotal", befTaxAmountTotal);
            map.put("aftTaxAmountTotal", aftTaxAmountTotal);
            map.put("befTaxAmount", befTaxAmount);
            map.put("aftTaxAmount", aftTaxAmount);
            map.put("recordDate", recordDate);
            map.put("befTaxAmount1", befTaxAmount1);
            map.put("aftTaxAmount1", aftTaxAmount1);
            map.put("recordDate1", recordDate1);
            map.put("befTaxAmount2", befTaxAmount2);
            map.put("aftTaxAmount2", aftTaxAmount2);
            map.put("recordDate2", recordDate2);
            map.put("befTaxAmount3", befTaxAmount3);
            map.put("aftTaxAmount3", aftTaxAmount3);
            map.put("recordDate3", recordDate3);
            map.put("befTaxAmount4", befTaxAmount4);
            map.put("aftTaxAmount4", aftTaxAmount4);
            map.put("recordDate4", recordDate4);
            map.put("befTaxAmount5", befTaxAmount5);
            map.put("aftTaxAmount5", aftTaxAmount5);
            map.put("recordDate5", recordDate5);
            map.put("befTaxAmount6", befTaxAmount6);
            map.put("aftTaxAmount6", aftTaxAmount6);
            map.put("recordDate6", recordDate6);
            map.put("num",num);
            map.put("companyNo",companyNo);
            map.put("detailId",detailId);
            map.put("switchFlag",switchFlag);
            map.put("switchFlag1",switchFlag1);
            map.put("switchFlag2",switchFlag2);
            map.put("switchFlag3",switchFlag3);
            map.put("switchFlag4",switchFlag4);
            map.put("switchFlag5",switchFlag5);
            map.put("switchFlag6",switchFlag6);
            map.put("estateType",estateType);

            map.put("CrtEmpId", UserInfoHolder.getUserId());
            map.put("UptEmpId", UserInfoHolder.getUserId());
            map.put("delFlg", 0);
            mapList.add(map);
        }
        return 0;
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
            List<CommissionResultDto> commissionResultList = new ArrayList<CommissionResultDto>();

            //批量处理数据
            for (Map<String, Object> map : mapList) {
                String cityNo = map.get("cityNo").toString();
                String reportId = map.get("reportId").toString();
                String CrtEmpId = map.get("CrtEmpId").toString();
                String UptEmpId = map.get("UptEmpId").toString();
                String delFlg = map.get("delFlg").toString();

                String befTaxAmountTotal = map.get("befTaxAmountTotal").toString();
                String aftTaxAmountTotal = map.get("aftTaxAmountTotal").toString();
                String befTaxAmount = map.get("befTaxAmount").toString();
                String aftTaxAmount = map.get("aftTaxAmount").toString();
                String recordDate = map.get("recordDate").toString();
                String befTaxAmount1 = map.get("befTaxAmount1").toString();
                String aftTaxAmount1 = map.get("aftTaxAmount1").toString();
                String recordDate1 = map.get("recordDate1").toString();
                String befTaxAmount2 = map.get("befTaxAmount2").toString();
                String aftTaxAmount2 = map.get("aftTaxAmount2").toString();
                String recordDate2 = map.get("recordDate2").toString();
                String befTaxAmount3 = map.get("befTaxAmount3").toString();
                String aftTaxAmount3 = map.get("aftTaxAmount3").toString();
                String recordDate3 = map.get("recordDate3").toString();
                String befTaxAmount4 = map.get("befTaxAmount4").toString();
                String aftTaxAmount4 = map.get("aftTaxAmount4").toString();
                String recordDate4 = map.get("recordDate4").toString();
                String befTaxAmount5 = map.get("befTaxAmount5").toString();
                String aftTaxAmount5 = map.get("aftTaxAmount5").toString();
                String recordDate5 = map.get("recordDate5").toString();
                String befTaxAmount6 = map.get("befTaxAmount6").toString();
                String aftTaxAmount6 = map.get("aftTaxAmount6").toString();
                String recordDate6 = map.get("recordDate6").toString();
                String num = map.get("num").toString();
                String companyNo = map.get("companyNo").toString();
                String detailId = map.get("detailId").toString();
                String switchFlag = map.get("switchFlag").toString();
                String switchFlag1 = map.get("switchFlag1").toString();
                String switchFlag2 = map.get("switchFlag2").toString();
                String switchFlag3 = map.get("switchFlag3").toString();
                String switchFlag4 = map.get("switchFlag4").toString();
                String switchFlag5 = map.get("switchFlag5").toString();
                String switchFlag6 = map.get("switchFlag6").toString();
                Integer estateType = Integer.valueOf(map.get("estateType").toString());

                CommissionResultDto commissionDto = new CommissionResultDto();
                commissionDto.setCityNo(cityNo);
                commissionDto.setReportId(reportId);
                commissionDto.setCrtEmpId(CrtEmpId);
                commissionDto.setUptEmpId(UptEmpId);
                commissionDto.setDelFlag(delFlg);

                commissionDto.setBefTaxAmountTotal(befTaxAmountTotal);
                commissionDto.setAftTaxAmountTotal(aftTaxAmountTotal);
                commissionDto.setBefTaxAmount(befTaxAmount);
                commissionDto.setAftTaxAmount(aftTaxAmount);
                commissionDto.setRecordDate(recordDate);
                commissionDto.setBefTaxAmount1(befTaxAmount1);
                commissionDto.setAftTaxAmount1(aftTaxAmount1);
                commissionDto.setRecordDate1(recordDate1);
                commissionDto.setBefTaxAmount2(befTaxAmount2);
                commissionDto.setAftTaxAmount2(aftTaxAmount2);
                commissionDto.setRecordDate2(recordDate2);
                commissionDto.setBefTaxAmount3(befTaxAmount3);
                commissionDto.setAftTaxAmount3(aftTaxAmount3);
                commissionDto.setRecordDate3(recordDate3);
                commissionDto.setBefTaxAmount4(befTaxAmount4);
                commissionDto.setAftTaxAmount4(aftTaxAmount4);
                commissionDto.setRecordDate4(recordDate4);
                commissionDto.setBefTaxAmount5(befTaxAmount5);
                commissionDto.setAftTaxAmount5(aftTaxAmount5);
                commissionDto.setRecordDate5(recordDate5);
                commissionDto.setBefTaxAmount6(befTaxAmount6);
                commissionDto.setAftTaxAmount6(aftTaxAmount6);
                commissionDto.setRecordDate6(recordDate6);
                commissionDto.setNum(Integer.valueOf(num));
                commissionDto.setCompanyNo(companyNo);
                commissionDto.setDetailId(detailId);
                commissionDto.setSwitchFlag(Integer.valueOf(switchFlag));
                commissionDto.setSwitchFlag1(Integer.valueOf(switchFlag1));
                commissionDto.setSwitchFlag2(Integer.valueOf(switchFlag2));
                commissionDto.setSwitchFlag3(Integer.valueOf(switchFlag3));
                commissionDto.setSwitchFlag4(Integer.valueOf(switchFlag4));
                commissionDto.setSwitchFlag5(Integer.valueOf(switchFlag5));
                commissionDto.setSwitchFlag6(Integer.valueOf(switchFlag6));
                commissionDto.setEstateType(estateType);

                commissionResultList.add(commissionDto);

            }

            resultData = sceneLinkCommissionService.insertLnkImport(commissionResultList);
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
