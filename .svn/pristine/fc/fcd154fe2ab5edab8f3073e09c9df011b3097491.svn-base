package cn.com.eju.pmls.estate.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.dto.linkage.CityDto;
import cn.com.eju.deal.base.file.service.FilesService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.linkage.service.LinkageCityService;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.base.util.HttpClientUtil;
import cn.com.eju.deal.cashbill.service.CashBillService;
import cn.com.eju.deal.common.service.CityService;
import cn.com.eju.deal.common.service.CommonService;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.dto.contract.ContractInfoDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstatePhotosDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateReleaseCityDto;
import cn.com.eju.deal.houseLinkage.report.service.ReportInsertService;
import cn.com.eju.pmls.estate.dto.Data;
import cn.com.eju.pmls.estate.dto.YFEstateData;
import cn.com.eju.pmls.estate.service.LnkEstateIncomeplanService;
import cn.com.eju.pmls.estate.service.PmlsEstateReleaseCityService;
import cn.com.eju.pmls.estate.service.PmlsEstateService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller层
 */
@Controller
@RequestMapping(value = "pmlsEstate")
public class PmlsEstateController extends BaseController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "pmlsEstateService")
    private PmlsEstateService pmlsEstateService;

    @Resource(name = "linkageCityService")
    private LinkageCityService linkageCityService;

    @Resource(name = "filesService")
    private FilesService filesService;

    @Resource(name = "reportInsertService")
    private ReportInsertService reportInsertService;

    @Resource(name = "commonService")
    private CommonService commonService;

    @Resource(name = "cityService")
    private CityService cityService;

    @Resource(name = "pmlsEstateReleaseCityService")
    private PmlsEstateReleaseCityService pmlsEstateReleaseCityService;

    @Resource(name = "lnkEstateIncomeplanService")
    private LnkEstateIncomeplanService lnkEstateIncomeplanService;

    @Resource
    private CashBillService cashBillService;

    /**
     * 项目index页面
     *
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap mop, HttpServletResponse response) {
        List<?> rebacklist = null;
        try {
            UserInfo userInfo = UserInfoHolder.get();
            Map<String, Object> reqMap = new HashMap<>();
            String cityNo = UserInfoHolder.get().getCityNo();
            reqMap.put("cityNo", cityNo);
            ResultData<?> resultData = pmlsEstateService.getProjectDepartment(reqMap);
            rebacklist = (List<?>) resultData.getReturnData();
            mop.addAttribute("userIdList", pmlsEstateService.getUserIdList());
            mop.addAttribute("userInfo", userInfo);
        } catch (Exception e) {
            logger.error("pmls.estate", "PmlsEstateController", "index", "", null, "", "初始化-归属项目部", e);
        }
        mop.put("rebacklist", rebacklist);
        return "estate/estateList";
    }

    /**
     * 列表分页
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public ResultData list(HttpServletRequest request) {
        ResultData resultData = new ResultData();
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        if (reqMap.containsKey("estateNm") && null != reqMap.get("estateNm")) {
            String estateNm = reqMap.get("estateNm").toString();
            estateNm = estateNm.trim();
            reqMap.put("estateNm", estateNm);
        }
        PageInfo pageInfo = getPageInfo(request);
        try {
            String cityNo = UserInfoHolder.get().getCityNo();
            reqMap.put("cityNo", cityNo);
            resultData = pmlsEstateService.index(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("pmls.estate", "PmlsEstateController", "list", "", null, "", "项目列表", e);
        }
        return resultData;
    }

    /**
     * 新增项目页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "addPmlsEstateView", method = RequestMethod.GET)
    public String addPmlsEstateView(HttpServletRequest request, ModelMap mop) {
        Map<String, Object> reqParam = bindParamToMap(request);
        mop.addAllAttributes(reqParam);

        UserInfo userInfo = UserInfoHolder.get();
        mop.addAttribute("userInfo", userInfo);
        //区域 districtList
        String cityNo = userInfo.getCityNo();
        if (StringUtil.isNotEmpty(cityNo)) {
            //区域列表
            ResultData<?> resultDistrictList = new ResultData<>();
            try {
                resultDistrictList = this.linkageCityService.getDistrictList(cityNo);
            } catch (Exception e) {
                logger.error("houseLinkage.estate", "EstateController", "toAdd", "", null, "", "创建--初始化", e);
            }
            mop.put("districtList", resultDistrictList.getReturnData());
        }

        //销售状态 salesStatusList
        mop.put("salesStatusList", SystemParam.getCodeListByKey(DictionaryConstants.SALES_STATUS));
        //合作模式cooperationTypeList
        mop.put("cooperationTypeList", SystemParam.getCodeListByKey(DictionaryConstants.COOPERATION_TYPE));
        // 业务模式
        mop.put("businessModelTypeList", SystemParam.getCodeListByKey(DictionaryConstants.BUSINESS_MODEL_TYPE));

        //合作方 partnerList
        mop.put("partnerList", SystemParam.getCodeListByKey(DictionaryConstants.PARTNER));
        //合作方 partnerList
        // 部门列表
        ResultData<?> resultUserList = new ResultData<>();
        try {
            resultUserList = pmlsEstateService.getSceneUserList(cityNo);
        } catch (Exception e) {
            logger.error("houseLinkage.estate", "EstateController", "toAdd", "", null, "", "创建--初始化-部门列表", e);
        }

        //得到归属项目部
        try {
            Map<String, Object> reqMap = new HashMap<>();
            reqMap.put("cityNo", cityNo);
            ResultData<?> resultData = pmlsEstateService.getProjectDepartment(reqMap);
            List<?> rebacklist = (List<?>) resultData.getReturnData();
            mop.put("rebacklist", rebacklist);
        } catch (Exception e) {
            logger.error("houseLinkage.estate", "EstateController", "toAdd", "", null, "", "创建--初始化-归属项目部", e);
        }

        mop.put("sceneUserList", resultUserList.getReturnData());
        //认证类型 authenticationKbnList
        mop.put("authenticationKbnList", SystemParam.getCodeListByKey(DictionaryConstants.AUTHENTICATION_KBN));
        //佣金方式 commissionKbnList
        mop.put("commissionKbnList", SystemParam.getCodeListByKey(DictionaryConstants.COMMISSION_KBN));
        //结佣方式 payKbnList
        mop.put("payKbnList", SystemParam.getCodeListByKey(DictionaryConstants.PAY_KBN));
        //销售方式 saleKbnList
        mop.put("saleKbnList", SystemParam.getCodeListByKey(DictionaryConstants.SALE_KBN));
        //报备方式 reportKbnList
        mop.put("reportKbnList", SystemParam.getCodeListByKey(DictionaryConstants.REPORT_KBN));
        //朝向 directionKbnList
        mop.put("directionKbnList", SystemParam.getCodeListByKey(DictionaryConstants.DIRECTION_KBN));
        //物业类型 mgtKbnList
        mop.put("mgtKbnList", SystemParam.getCodeListByKey(DictionaryConstants.MGT_KBN));
        //产权年限 ownYearKbnList
        mop.put("ownYearKbnList", SystemParam.getCodeListByKey(DictionaryConstants.OWNYEAR_KBN));
        //装修情况 decorationKbnList
        mop.put("decorationKbnList", SystemParam.getCodeListByKey(DictionaryConstants.DECORATION_KBN));
        //建筑类型 typeKbnList
        mop.put("typeKbnList", SystemParam.getCodeListByKey(DictionaryConstants.TYPE_KBN));
        //供暖方式 heatKbnList
        mop.put("heatKbnList", SystemParam.getCodeListByKey(DictionaryConstants.HEAT_KBN));
        //水电燃气 hydropowerGasKbnList
        mop.put("hydropowerGasKbnList", SystemParam.getCodeListByKey(DictionaryConstants.HYDROPOWERGAS_KBN));
        //提前报备期 时
        mop.put("advanceReportHHList", getHours());
        //提前报备期 分
        mop.put("advanceReportMMList", getMinutes());

        try {
            ResultData<?> resultData = pmlsEstateService.queryCountryList();
            List<?> countryList = (List<?>) resultData.getReturnData();
            mop.put("countryList", countryList);
        } catch (Exception e) {
            logger.error("houseLinkage.estate", "EstateController", "toAdd", "", null, "", "创建--初始化-查询国家列表错误", e);
        }
        //是否大客户 typeId 226
        mop.put("bigCustomerStatus", SystemParam.getCodeListByKey(DictionaryConstants.ESTATE_BIGCUSTOMER_TYPE));
        //是否独家 typeId 227
        mop.put("particularList", SystemParam.getCodeListByKey(DictionaryConstants.ESTATE_PARTICULAR_TYPE));
        //是否直签 typeId 228
        mop.put("directSignList", SystemParam.getCodeListByKey(DictionaryConstants.ESTATE_DIRECTSIGN_TYPE));
        //刷筹 typeId 277
        mop.put("brushRaiseList", SystemParam.getCodeListByKey(DictionaryConstants.ESTATE_BRUSHRAISE_TYPE));
        //共场 typeId 278
        mop.put("totalFieldList", SystemParam.getCodeListByKey(DictionaryConstants.ESTATE_TOTALFIELD_TYPE));
        //是否借佣 typeId 279
        mop.put("excuseCommisionList", SystemParam.getCodeListByKey(DictionaryConstants.ESTATE_EXCUSECOMMISION_TYPE));

        //是否手动填写项目名称
        Boolean addEstateManual = null;
        ResultData<?> citySetting = null;
        try {
            citySetting = commonService.getCitySettingByCityNo(cityNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (citySetting != null && citySetting.getReturnData() != null) {
            Map<?, ?> csMap = (Map<?, ?>) citySetting.getReturnData();
            addEstateManual = Boolean.valueOf(csMap.get("addEstateManual").toString());
        }
        mop.put("addEstateManual", addEstateManual);

        return "estate/estateAddInfo";
    }

    /**********************************private function*******************************/
    /**
     * @return
     * @Title: getHours
     * @Description: 小时数
     */
    private List<String> getHours() {
        List<String> hours = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            if (String.valueOf(i).length() < 2) {
                hours.add("0" + String.valueOf(i));
            } else {
                hours.add(String.valueOf(i));
            }
        }
        return hours;
    }

    /**
     * @return
     * @Title: getMinutes
     * @Description: 分钟数
     */
    private List<String> getMinutes() {
        List<String> minutes = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            if (String.valueOf(i).length() < 2) {
                minutes.add("0" + String.valueOf(i));
            } else {
                minutes.add(String.valueOf(i));
            }
        }
        return minutes;
    }

    /**
     * 【描述】: 选择op项目弹出层
     *
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "/selectFromOp", method = RequestMethod.GET)
    public String selectFromOp(HttpServletRequest request, ModelMap mop) {
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.addAllAttributes(reqMap);
        return "estate/selectFromOpPop";
    }

    @RequestMapping(value = "/selectFromOp/q", method = RequestMethod.POST)
    @ResponseBody
    public ResultData selectFromOpList(HttpServletRequest request, ModelMap mop) {
        ResultData resultDataQ = new ResultData();
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        List<Map<String, Object>> estateList = new ArrayList<>();
        try {
            Map<String, String> params = new HashMap<>();
            PageInfo pageInfo = getPageInfo(request);

            params.put("currentPage", pageInfo.getNextPage() + "");
            params.put("pageSize", pageInfo.getPageLimit() + "");
            params.put("keyWord", reqMap.get("estateName").toString());
            params.put("address", reqMap.get("addr").toString());
            params.put("international", reqMap.get("estatePosition").toString());

            String result = getyFInterfaceInfo("/GetYouFangProject", JSON.toJSONString(params), null, UserInfoHolder.getUserId());

            if (StringUtil.isNotEmpty(result)) {
                YFEstateData yFEstateData = JSON.parseObject(result, YFEstateData.class);
                for (Data data : yFEstateData.getData()) {
                    Map<String, Object> cityMap = new HashMap<>();
                    String cityNo = data.getCityNo() == null ? "-1" : data.getCityNo();
                    ResultData<?> resultData = this.pmlsEstateService.getCityByGovCityCode(cityNo);
                    if ("200".equals(resultData.getReturnCode()) && resultData.getReturnData() != null) {
                        cityMap = (Map<String, Object>) resultData.getReturnData();
                        if (cityMap == null) {
                            cityMap = new HashMap<>();
                        }
                    }
                    Map<String, Object> yfMap = new HashMap<>();
                    yfMap.put("estateId", data.getEstateId());
                    yfMap.put("estateNm", data.getEstateNm());
                    yfMap.put("cityNo", cityMap.get("CityNo"));
                    yfMap.put("cityNm", cityMap.get("CityName"));
                    yfMap.put("cityRmsId", cityMap.get("GovCityCode"));
                    yfMap.put("address", data.getAddress());
                    yfMap.put("openTime", data.getOpenTime());
                    yfMap.put("houseCnt", data.getHouseCnt());
                    yfMap.put("parkCnt", data.getParkCnt());
                    yfMap.put("mgtCompany", data.getMgtCompany());
                    yfMap.put("rateFAR", data.getRateFAR());
                    yfMap.put("rateGreen", data.getRateGreen());
                    yfMap.put("mgtPrice", data.getMgtPrice());
                    yfMap.put("houseTransferTime", data.getHouseTransferTime());

                    estateList.add(yfMap);
                }
                resultDataQ.setTotalCount(yFEstateData.getDataCount());
                resultDataQ.setReturnData(estateList);
            }
        } catch (Exception e) {
            logger.error("estate", "PmlsEstateController", "selectFromOpList", "", 0, "", "", e);
        }
        return resultDataQ;
    }

    /**
     * 调用有房接口 获取楼盘分页列表
     *
     * @param func
     * @param paramMap
     * @param typeId
     * @param userId
     * @return
     */
    private String getyFInterfaceInfo(String func, String paramMap, String typeId, Integer userId) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        String result = null;

        String returnDataStr = null;
        String url = SystemParam.getWebConfigValue("youfangReportUrl") + func;

        logger.info("调用有房接口start#####请求#url=" + url);
        try {
            returnDataStr = HttpClientUtil.httpPostYF(url, paramMap);
            if (StringUtil.isNotEmpty(returnDataStr)) {
                returnMap = JSON.parseObject(returnDataStr, Map.class);
                if (returnMap.containsKey("BFlag") && returnMap.containsKey("TData")) {
                    Integer bFlag = (Integer) returnMap.get("BFlag");
                    Object tData = returnMap.get("TData");
                    if (10 == bFlag) {
                        result = JSON.toJSONString(tData);
                    } else {

                    }
                }
            }
        } catch (Exception e) {
            logger.error("houseLinkage.estate", "EstateController", "getyFInterfaceInfo", "userId=" + userId, userId, null,
                    "调用有房接口:#####请求参数#url=" + url + "#####返回信息" + returnDataStr, e);
        }
        return result;
    }


    @RequestMapping(value = "selectFromOpCheck", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> selectFromOpCheck(HttpServletRequest request, ModelMap mop) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        if (reqMap.get("estId") == null) {
            reqMap.put("estId", 9999999);
        }

        ResultData<?> resultData;
        try {
            resultData = pmlsEstateService.selectFromOpCheck(reqMap);
        } catch (Exception e) {
            logger.error("estate", "EstateController", "selectFromOpCheck", "", UserInfoHolder.getUserId(), "", "", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "验证op项目是否已关联异常");

            return getOperateJSONView(rspMap);
        }

        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());

        rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());

        return getOperateJSONView(rspMap);
    }

    /**
     * 获取垫佣甲方列表
     *
     * @param mop
     * @return
     */
    @RequestMapping(value = "getMattressNail", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getMattressNail(ModelMap mop) {
        ResultData<?> resultData = new ResultData<>();
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try {
            resultData = this.pmlsEstateService.getMattressNail();
        } catch (Exception e) {
            logger.error("pmlsEstate",
                    "PmlsEstateController",
                    "pmlsEstateService.getMattressNail",
                    "",
                    UserInfoHolder.getUserId(),
                    "",
                    "获取垫佣甲方失败",
                    e);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        if (null != resultData.getReturnData()) {
            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        }
        return getMapView(rspMap);
    }

    /**
     * 获取大客户列表
     *
     * @param mop
     * @return
     */
    @RequestMapping(value = "getBigCustomerList", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getBigCustomerList(ModelMap mop) {
        ResultData<?> resultData = new ResultData<>();
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try {
            resultData = this.pmlsEstateService.getBigCustomerList();
        } catch (Exception e) {
            logger.error("pmlsEstate",
                    "PmlsEstateController",
                    "pmlsEstateService.getBigCustomerList",
                    "",
                    UserInfoHolder.getUserId(),
                    "",
                    "获取大客户失败",
                    e);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        if (null != resultData.getReturnData()) {
            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        }
        return getMapView(rspMap);
    }

    /**
     * 选择开发负责人页面
     *
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "projectLeaderChoose", method = RequestMethod.GET)
    public String projectLeaderChoose(HttpServletRequest request, ModelMap mop) {
        bindParamToAttrbute(request);
        return "estate/projectLeaderPopup";
    }

    /**
     * 获取开发负责人列表
     *
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "projectLeaderChoose/getList", method = RequestMethod.POST)
    @ResponseBody
    public ResultData getProjectLeaderList(HttpServletRequest request, ModelMap mop) {
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        //返回list
        ResultData<?> resultData = new ResultData();
        try {
            reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
            resultData = pmlsEstateService.getSceneUserList2(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("pmlsEstate", "PmlsEstateController", "getProjectLeaderList", "", null, "", "获取负责人列表失败", e);
        }
        return resultData;
    }

    /**
     * 创建项目
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> create(HttpServletRequest request, ModelMap modelMap, EstatePhotosDto estatePhotosDto) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            ResultData<?> resultData = pmlsEstateService.create(reqMap, estatePhotosDto);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "楼盘新增失败！");
            logger.error("estate", "PmlsEstateController", "create", "", null, "", "楼盘新增失败！", e);
        }
        return getOperateJSONView(rspMap);
    }


    /**
     * 查看项目详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(HttpServletRequest request, @PathVariable("id") Integer id, ModelMap mop) {
        Map<String, Object> reqMap = bindParamToMap(request);
        //返回map
        ResultData<?> resultData = new ResultData<ContractInfoDto>();
        try {
            resultData = pmlsEstateService.getById(id);
            Map<String, Object> estateInfoDto = (Map<String, Object>) resultData.getReturnData();
            if (estateInfoDto != null && estateInfoDto.get("estate") != null) {
                Map<String, Object> estateDto = (Map<String, Object>) estateInfoDto.get("estate");
                String estateId = (String) estateDto.get("estateId");
                String oldCityNo = (String) estateDto.get("cityNm");
                String releaseCityStr = oldCityNo + "、";
                Map<String, Object> rspMap = new HashMap<String, Object>();
                rspMap.put("estateId", estateId);
                ResultData<?> reback1 = pmlsEstateReleaseCityService.getByEstateId(rspMap);
                List<EstateReleaseCityDto> releaseCity = (List<EstateReleaseCityDto>) reback1.getReturnData();
                Map<String, Object> map = new HashMap<String, Object>();
                // 非空判断
                String releaseCityflag = "0";
                if (null != releaseCity && !releaseCity.isEmpty()) {
                    releaseCityflag = "1";
                    for (int i = 0; i < releaseCity.size(); i++) {
                        map = (Map) releaseCity.get(i);
                        if (!oldCityNo.equals(map.get("cityName").toString())) {
                            releaseCityStr += map.get("cityName").toString() + "、";
                        }
                    }
                    releaseCityStr = releaseCityStr.substring(0, releaseCityStr.length() - 1);
                    mop.put("releaseCityStr", releaseCityStr);
                }
                mop.put("releaseCityflag", releaseCityflag);
            }
        } catch (Exception e) {
            logger.error("estate", "PmlsEstateController", "show", "", null, "", "查看", e);
        }

        //存放到mop中
        mop.addAttribute("estateInfo", resultData.getReturnData());
        mop.addAllAttributes(reqMap);
        return "estate/estateDetail";
    }


    /**
     * 修改页面
     *
     * @param id
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{u}/{id}/{opType}", method = RequestMethod.GET)
    public String toEdit(@PathVariable("u") String u, @PathVariable("id") int id,@PathVariable("opType") String opType, ModelMap mop) {
        //楼盘自增编号
        mop.addAttribute("id", id);
        mop.addAttribute("opType", opType);
        UserInfo userInfo = UserInfoHolder.get();
        mop.addAttribute("userInfo", userInfo);
        ResultData<?> resultData = new ResultData<>();
        try {
            resultData = pmlsEstateService.getEstateDetailById(id);
            if (resultData != null) {
                Map<String, Object> map = (Map<String, Object>) resultData.getReturnData();
                String mgtKbnList = (String) map.get("mgtKbn");
                if (mgtKbnList != null && mgtKbnList != "") {
                    String[] mgtKbn = mgtKbnList.split(",");
                    for (String str : mgtKbn) {
                        if (str.contains("13405")) {
                            mop.addAttribute("houseTypeFlag", "0");
                        } else {
                            mop.addAttribute("houseTypeFlag", "1");
                        }
                    }
                }
            }
            //区域 districtList
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) resultData.getReturnData();
            String cityNo = (String) map.get("cityNo");
            if (StringUtil.isNotEmpty(cityNo)) {
                //区域列表
                ResultData<?> resultDistrictList = new ResultData<>();
                try {
                    resultDistrictList = this.linkageCityService.getDistrictList(cityNo);
                } catch (Exception e) {
                    logger.error("houseLinkage.estate", "EstateController", "toEditDetail", "", null, "", "初始化楼盘基本信息和详情", e);
                }
                mop.put("districtList", resultDistrictList.getReturnData());
            }


            //得到归属项目部
            try {
                Map<String, Object> reqMap = new HashMap<>();
                reqMap.put("cityNo", cityNo);
                ResultData<?> resultDatas = pmlsEstateService.getProjectDepartment(reqMap);
                List<?> rebacklist = (List<?>) resultDatas.getReturnData();
                mop.put("rebacklist", rebacklist);
            } catch (Exception e) {
                logger.error("houseLinkage.estate", "EstateController", "toAdd", "", null, "", "创建--初始化-归属项目部", e);
            }

            //销售状态 salesStatusList
            mop.put("salesStatusList", SystemParam.getCodeListByKey(DictionaryConstants.SALES_STATUS));
            //合作方 partnerList
            mop.put("partnerList", SystemParam.getCodeListByKey(DictionaryConstants.PARTNER));
            //案场负责人 sceneDeptIdList 联动
            // 部门列表
            ResultData<?> resultUserList = new ResultData<>();
            try {
                resultUserList = pmlsEstateService.getSceneUserList(cityNo);
            } catch (Exception e) {
                logger.error("houseLinkage.estate", "EstateController", "toEditDetail", "", null, "", "初始化楼盘基本信息和详情-部门列表", e);
            }
            mop.put("sceneUserList", resultUserList.getReturnData());
            //物业类型 mgtKbnList
            mop.put("mgtKbnList", SystemParam.getCodeListByKey(DictionaryConstants.MGT_KBN));
            //产权年限 ownYearKbnList
            mop.put("ownYearKbnList", SystemParam.getCodeListByKey(DictionaryConstants.OWNYEAR_KBN));
            //装修情况 decorationKbnList
            mop.put("decorationKbnList", SystemParam.getCodeListByKey(DictionaryConstants.DECORATION_KBN));
            //建筑类型 typeKbnList
            mop.put("typeKbnList", SystemParam.getCodeListByKey(DictionaryConstants.TYPE_KBN));
            //供暖方式 heatKbnList
            mop.put("heatKbnList", SystemParam.getCodeListByKey(DictionaryConstants.HEAT_KBN));
            //水电燃气 hydropowerGasKbnList
            mop.put("hydropowerGasKbnList", SystemParam.getCodeListByKey(DictionaryConstants.HYDROPOWERGAS_KBN));
            //合作模式cooperationTypeList
            mop.put("cooperationTypeList", SystemParam.getCodeListByKey(DictionaryConstants.COOPERATION_TYPE));

            // 业务模式
            mop.put("businessModelTypeList", SystemParam.getCodeListByKey(DictionaryConstants.BUSINESS_MODEL_TYPE));

            //存放到mop中
            mop.addAttribute("estate", resultData.getReturnData());

            try {
                ResultData<?> resultData1 = pmlsEstateService.queryCountryList();
                List<?> countryList = (List<?>) resultData1.getReturnData();
                mop.put("countryList", countryList);
            } catch (Exception e) {
                logger.error("houseLinkage.estate", "EstateController", "toAdd", "", null, "", "创建--初始化-查询国家列表错误", e);
            }
            //是否大客户 typeId 226
            mop.put("bigCustomerStatus", SystemParam.getCodeListByKey(DictionaryConstants.ESTATE_BIGCUSTOMER_TYPE));
            //是否独家 typeId 227
            mop.put("particularList", SystemParam.getCodeListByKey(DictionaryConstants.ESTATE_PARTICULAR_TYPE));
            //是否直签 typeId 228
            mop.put("directSignList", SystemParam.getCodeListByKey(DictionaryConstants.ESTATE_DIRECTSIGN_TYPE));
            //刷筹 typeId 277
            mop.put("brushRaiseList", SystemParam.getCodeListByKey(DictionaryConstants.ESTATE_BRUSHRAISE_TYPE));
            //共场 typeId 278
            mop.put("totalFieldList", SystemParam.getCodeListByKey(DictionaryConstants.ESTATE_TOTALFIELD_TYPE));
            //是否借佣 typeId 279
            mop.put("excuseCommisionList", SystemParam.getCodeListByKey(DictionaryConstants.ESTATE_EXCUSECOMMISION_TYPE));

            //是否手动填写项目名称
            Boolean addEstateManual = null;
            ResultData<?> citySetting = null;
            try {
                citySetting = commonService.getCitySettingByCityNo(cityNo);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (citySetting != null && citySetting.getReturnData() != null) {
                Map<?, ?> csMap = (Map<?, ?>) citySetting.getReturnData();
                addEstateManual = Boolean.valueOf(csMap.get("addEstateManual").toString());
            }
            mop.put("addEstateManual", addEstateManual);
        } catch (Exception e) {
            logger.error("estate", "PmlsEstateController", "toEdit", "", null, "", "修改--初始化", e);
        }
        //存放到mop中
        mop.addAttribute("estate", JSON.parseObject(JSON.toJSONString(resultData.getReturnData())));
        return "estate/estateEdit";
    }

    /**
     * 修改楼盘信息
     * @param id
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/estateEditInfo/{id}", method = RequestMethod.GET)
    public String estateEditInfo(@PathVariable("id") int id, ModelMap mop) {
        //楼盘自增编号
        mop.addAttribute("id", id);
        UserInfo userInfo = UserInfoHolder.get();
        mop.addAttribute("userInfo", userInfo);
        ResultData<?> resultData = new ResultData<>();
        try {
            resultData = pmlsEstateService.getEstateDetailById(id);
            if (resultData != null) {
                Map<String, Object> map = (Map<String, Object>) resultData.getReturnData();
                String mgtKbnList = (String) map.get("mgtKbn");
                if (mgtKbnList != null && mgtKbnList != "") {
                    String[] mgtKbn = mgtKbnList.split(",");
                    for (String str : mgtKbn) {
                        if (str.contains("13405")) {
                            mop.addAttribute("houseTypeFlag", "0");
                        } else {
                            mop.addAttribute("houseTypeFlag", "1");
                        }
                    }
                }
            }
            //区域 districtList
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) resultData.getReturnData();
            String cityNo = (String) map.get("cityNo");
            if (StringUtil.isNotEmpty(cityNo)) {
                //区域列表
                ResultData<?> resultDistrictList = new ResultData<>();
                try {
                    resultDistrictList = this.linkageCityService.getDistrictList(cityNo);
                } catch (Exception e) {
                    logger.error("楼盘信息修改##查询楼盘信息resultDistrictList失败",e);
                    logger.error("houseLinkage.estate", "EstateController", "estateEditInfo", "", null, "", "初始化楼盘基本信息和详情", e);
                }
                mop.put("districtList", resultDistrictList.getReturnData());
            }


            //得到归属项目部
            try {
                Map<String, Object> reqMap = new HashMap<>();
                reqMap.put("cityNo", cityNo);
                ResultData<?> resultDatas = pmlsEstateService.getProjectDepartment(reqMap);
                List<?> rebacklist = (List<?>) resultDatas.getReturnData();
                mop.put("rebacklist", rebacklist);
            } catch (Exception e) {
                logger.error("楼盘信息修改##查询楼盘信息resultDatas失败",e);
                logger.error("houseLinkage.estate", "EstateController", "estateEditInfo", "", null, "", "创建--初始化-归属项目部", e);
            }

            //销售状态 salesStatusList
            mop.put("salesStatusList", SystemParam.getCodeListByKey(DictionaryConstants.SALES_STATUS));
            //合作方 partnerList
            mop.put("partnerList", SystemParam.getCodeListByKey(DictionaryConstants.PARTNER));
            //案场负责人 sceneDeptIdList 联动
            // 部门列表
            ResultData<?> resultUserList = new ResultData<>();
            try {
                resultUserList = pmlsEstateService.getSceneUserList(cityNo);
            } catch (Exception e) {
                logger.error("楼盘信息修改##查询楼盘信息resultUserList失败",e);
                logger.error("houseLinkage.estate", "EstateController", "estateEditInfo", "", null, "", "初始化楼盘基本信息和详情-部门列表", e);
            }
            mop.put("sceneUserList", resultUserList.getReturnData());
            //物业类型 mgtKbnList
            mop.put("mgtKbnList", SystemParam.getCodeListByKey(DictionaryConstants.MGT_KBN));
            //产权年限 ownYearKbnList
            mop.put("ownYearKbnList", SystemParam.getCodeListByKey(DictionaryConstants.OWNYEAR_KBN));
            //装修情况 decorationKbnList
            mop.put("decorationKbnList", SystemParam.getCodeListByKey(DictionaryConstants.DECORATION_KBN));
            //建筑类型 typeKbnList
            mop.put("typeKbnList", SystemParam.getCodeListByKey(DictionaryConstants.TYPE_KBN));
            //供暖方式 heatKbnList
            mop.put("heatKbnList", SystemParam.getCodeListByKey(DictionaryConstants.HEAT_KBN));
            //水电燃气 hydropowerGasKbnList
            mop.put("hydropowerGasKbnList", SystemParam.getCodeListByKey(DictionaryConstants.HYDROPOWERGAS_KBN));
            //合作模式cooperationTypeList
            mop.put("cooperationTypeList", SystemParam.getCodeListByKey(DictionaryConstants.COOPERATION_TYPE));

            // 业务模式
            mop.put("businessModelTypeList", SystemParam.getCodeListByKey(DictionaryConstants.BUSINESS_MODEL_TYPE));

            //存放到mop中
            mop.addAttribute("estate", resultData.getReturnData());

            try {
                ResultData<?> resultData1 = pmlsEstateService.queryCountryList();
                List<?> countryList = (List<?>) resultData1.getReturnData();
                mop.put("countryList", countryList);
            } catch (Exception e) {
                logger.error("楼盘信息修改##查询楼盘信息countryList失败",e);
                logger.error("houseLinkage.estate", "EstateController", "estateEditInfo", "", null, "", "创建--初始化-查询国家列表错误", e);
            }
            //是否大客户 typeId 226
            mop.put("bigCustomerStatus", SystemParam.getCodeListByKey(DictionaryConstants.ESTATE_BIGCUSTOMER_TYPE));
            //是否独家 typeId 227
            mop.put("particularList", SystemParam.getCodeListByKey(DictionaryConstants.ESTATE_PARTICULAR_TYPE));
            //是否直签 typeId 228
            mop.put("directSignList", SystemParam.getCodeListByKey(DictionaryConstants.ESTATE_DIRECTSIGN_TYPE));
            //刷筹 typeId 277
            mop.put("brushRaiseList", SystemParam.getCodeListByKey(DictionaryConstants.ESTATE_BRUSHRAISE_TYPE));
            //共场 typeId 278
            mop.put("totalFieldList", SystemParam.getCodeListByKey(DictionaryConstants.ESTATE_TOTALFIELD_TYPE));
            //是否借佣 typeId 279
            mop.put("excuseCommisionList", SystemParam.getCodeListByKey(DictionaryConstants.ESTATE_EXCUSECOMMISION_TYPE));

            //是否手动填写项目名称
            Boolean addEstateManual = null;
            ResultData<?> citySetting = null;
            try {
                citySetting = commonService.getCitySettingByCityNo(cityNo);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (citySetting != null && citySetting.getReturnData() != null) {
                Map<?, ?> csMap = (Map<?, ?>) citySetting.getReturnData();
                addEstateManual = Boolean.valueOf(csMap.get("addEstateManual").toString());
            }
            mop.put("addEstateManual", addEstateManual);
        } catch (Exception e) {
            logger.error("楼盘信息修改##查询楼盘信息失败",e);
            logger.error("estate", "PmlsEstateController", "estateEditInfo", "", null, "", "楼盘修改--初始化", e);
        }
        //存放到mop中
        mop.addAttribute("estate", JSON.parseObject(JSON.toJSONString(resultData.getReturnData())));
        return "estate/estateEditInfo";
    }

    /**
     * 修改项目
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> update(HttpServletRequest request, @PathVariable("id") String id, EstatePhotosDto estatePhotosDto) {
        ResultData<?> result = new ResultData<>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.remove("_method");
        try {
            //更新
            result = pmlsEstateService.update(reqMap, estatePhotosDto);
        } catch (Exception e) {
            logger.error("estate", "PmlsEstateController", "update", "", null, "", " 修改 ", e);
            result.setFail("楼盘信息更新失败！");
        }
        return result;
    }

    /**
     * 选择考核主体页面
     *
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "/toChooseAccountProject", method = RequestMethod.GET)
    public String toChooseAccountProject(HttpServletRequest request, ModelMap mop) {
        ResultData<?> resultData = null;
        Map<String, Object> reqMap = bindParamToMap(request);
        String cityNo = "";
        String id = "";
        if (reqMap.containsKey("estateCityNo")) {
            cityNo = reqMap.get("estateCityNo").toString();
            mop.put("estateCityNo", cityNo);
        }
        return "estate/estateAccountProjectMapping";
    }

    /**
     * 审核
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/audit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> audit(HttpServletRequest request, @PathVariable("id") String id) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.remove("_method");
        try {
            //更新
            pmlsEstateService.audit(reqMap);

        } catch (Exception e) {
            logger.error("estate", "PmlsEstateController", "audit", "", null, "", "审核", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "审核失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        try {
            String cityNo = UserInfoHolder.get().getCityNo();
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("cityNo",cityNo);

            ResultData<?> resultData = pmlsEstateService.getLastProjectByCity(param);
            if (resultData != null && resultData.getReturnData() != null) {
                Map<String, Object> resultMap = (Map<String, Object>) resultData.getReturnData();

                if ( resultMap!= null && resultMap.get("id") != null) {
                    rspMap.put(Constant.RETURN_DATA_KEY,  resultMap.get("id").toString());
                }
            }
        } catch (Exception e) {
            logger.error("查询-城市的下一个需要审批项目异常",e);
            logger.error("estate", "PmlsEstateController", "audit", "",
                    null, "", "查询-城市的下一个需要审批项目异常", e);

        }

        return getOperateJSONView(rspMap);
    }

    /**
     * 发布弹出层页面
     *
     * @param id
     * @param mop
     * @return
     */
    @RequestMapping(value = "torelease", method = RequestMethod.GET)
    public String toRelease(int id, ModelMap mop) {
        mop.put("id", id);
        return "estate/estateRelease";
    }

    /**
     * 发布
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/release/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> release(HttpServletRequest request, @PathVariable("id") String id) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.remove("_method");
        try {
            //更新
            pmlsEstateService.release(reqMap);
        } catch (Exception e) {
            logger.error("estate", "PmlsEstateController", "release", "", null, "", "发布失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "发布失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }

    /**
     * 下架
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/down/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> down(HttpServletRequest request, @PathVariable("id") String id) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.remove("_method");
        try {
            //更新
            pmlsEstateService.down(reqMap);
        } catch (Exception e) {
            logger.error("estate", "PmlsEstateController", "down", "", null, "", "下架失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "下架失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }

    /**
     * 撤回
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/backoff/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> backoff(HttpServletRequest request, @PathVariable("id") int id) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        //        Map<String, Object> reqMap = bindParamToMap(request);
        //        reqMap.remove("_method");
        try {
            //更新
            pmlsEstateService.backoff(id);
        } catch (Exception e) {
            logger.error("estate", "PmlsEstateController", "backoff", "", null, "", "撤回失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "撤回失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }

    /**
     * 跟单
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/startProject/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> startProject(HttpServletRequest request, @PathVariable("id") int id) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try {
            //更新
            pmlsEstateService.startProject(id);
        } catch (Exception e) {
            logger.error("estate", "PmlsEstateController", "start", "", null, "", "跟单失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "跟单失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }

    /**
     * 取消跟单
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/startCancel/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> startCancel(HttpServletRequest request, @PathVariable("id") int id) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try {
            //更新
            pmlsEstateService.startCancel(id);
        } catch (Exception e) {
            logger.error("estate", "PmlsEstateController", "start", "", null, "", "取消跟单失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "取消跟单失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }

    /**
     * 结案
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/endProject/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> endProject(HttpServletRequest request, @PathVariable("id") int id) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try {
            //更新
            pmlsEstateService.endProject(id);
        } catch (Exception e) {
            logger.error("estate", "PmlsEstateController", "end", "", null, "", "结案失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "结案失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }

    /**
     * 取消结案
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/endCancel/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> endCancel(HttpServletRequest request, @PathVariable("id") int id) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try {
            //更新
            pmlsEstateService.endCancel(id);
        } catch (Exception e) {
            logger.error("estate", "PmlsEstateController", "endCancel", "", null, "", "结案失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "取消结案失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }


    /**
     * 变更发布城市页面
     *
     * @param id
     * @param estateId
     * @param cityNo
     * @param cityNm
     * @param mop
     * @return
     */
    @RequestMapping(value = "/popupReleaseCity/{id}/{estateId}/{cityNo}/{cityNm}", method = RequestMethod.GET)
    public String popupReleaseCity(@PathVariable("id") String id, @PathVariable("estateId") String estateId,
                                   @PathVariable("cityNo") String cityNo, @PathVariable("cityNm") String cityNm, ModelMap mop) {
        String flag = "0";
        try {
            Map<String, Object> rspMap = new HashMap<String, Object>();
            rspMap.put("estateId", estateId);
            ResultData<?> reback1 = pmlsEstateReleaseCityService.getByEstateId(rspMap);
            List<EstateReleaseCityDto> releaseCity = (List<EstateReleaseCityDto>) reback1.getReturnData();
            String oldReleaseCity = cityNm + ",";
            Map<String, Object> map = new HashMap<String, Object>();
            // 非空判断
            List<String> oldReleaseCityNo = new ArrayList<>();
            if (null != releaseCity && !releaseCity.isEmpty()) {
                flag = "1";
                for (int i = 0; i < releaseCity.size(); i++) {
                    map = (Map) releaseCity.get(i);
                    if (!cityNo.equals(map.get("cityNo").toString())) {
                        oldReleaseCity += map.get("cityName").toString() + ",";
                        oldReleaseCityNo.add(map.get("cityNo").toString());
                    }
                }
                oldReleaseCity = oldReleaseCity.substring(0, oldReleaseCity.length() - 1);
                mop.put("oldReleaseCity", oldReleaseCity);
            }
            oldReleaseCityNo.add(cityNo);
            mop.put("oldReleaseCityNo", oldReleaseCityNo);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        try {
            ResultData<?> reback = cityService.queryCitySettingsList();
            List<?> citylist = (List<?>) reback.getReturnData();
            mop.addAttribute("citylist", citylist);
            mop.addAttribute("citylistLenth", citylist.size());
        } catch (Exception e) {
            logger.error("estate", "PmlsEstateController", "popupReleaseCity", "", null, "", "查询项目发布城市列表失败！", e);
            e.printStackTrace();
        }
        //返回视图 reqMap.put("userCreate", UserInfoHolder.get().getUserId());
        mop.put("id", id);
        mop.put("estateId", estateId);
        mop.put("cityNo", cityNo);
        mop.put("cityNm", cityNm);
        mop.put("flag", flag);
        return "estate/estateReleaseChangeCity";
    }

    /**
     * 修改发布城市变更
     *
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/releaseCity", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> releaseCity(HttpServletRequest request, ModelMap modelMap) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        String oldCityNm = reqMap.get("cityNm").toString();
        String newReleaseCity = oldCityNm + ",";
        String[] cityNoArray = reqMap.get("select_newRelseaseCityNo").toString().split(",");
        for (int i = 0; i < cityNoArray.length; i++) {
            String newCityNo = cityNoArray[i];
            try {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("cityNo", newCityNo);
                ResultData<?> reback = cityService.queryCityNameByCityNo(map);
                List<CityDto> list = (List<CityDto>) reback.getReturnData();
                Map<String, Object> map2 = new HashMap<String, Object>();
                map2 = (Map) list.get(0);
                if (!oldCityNm.equals(map2.get("cityName").toString())) {
                    newReleaseCity += map2.get("cityName").toString() + ",";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        newReleaseCity = newReleaseCity.substring(0, newReleaseCity.length() - 1);
        reqMap.put("newReleaseCity", newReleaseCity);
        try {
            pmlsEstateReleaseCityService.releaseCity(reqMap);
        } catch (Exception e) {
            logger.error("estate", "PmlsEstateController", "releaseCity", "", null, "", "修改变更发布城市失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "修改变更发布城市失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }


    /**
     * 项目信息变更页面
     *
     * @param id
     * @param mop
     * @param request
     * @return
     */
    @RequestMapping(value = "popupEdit", method = RequestMethod.GET)
    public String toUpdate(Integer id, ModelMap mop, HttpServletRequest request) {
        mop.addAllAttributes(bindParamToMap(request));
        ResultData<?> resultData = new ResultData<>();
        try {
            resultData = pmlsEstateService.getEstateDetailById(id);
        } catch (Exception e) {
            logger.error("estate", "PmlsEstateController", "toUpdate", "", null, "", "项目信息变更--初始化", e);
        }
        try {
            Map<String, Object> map = (Map<String, Object>) resultData.getReturnData();
            String cityNo = (String) map.get("realityCityNo");
            //查询isService=1的城市
            ResultData<?> reback = cityService.queryCityListByIsService();
            List<?> citylist = (List<?>) reback.getReturnData();
            mop.addAttribute("citylist", citylist);

            //区域列表
            ResultData<?> resultDistrictList = new ResultData<>();
            resultDistrictList = linkageCityService.getDistrictList(cityNo);
            mop.addAttribute("districtList", resultDistrictList.getReturnData());
            //国家
            ResultData<?> countryResult = pmlsEstateService.queryCountryList();
            List<?> countryList = (List<?>) countryResult.getReturnData();
            mop.put("countryList", countryList);
        } catch (Exception e) {
            logger.error("estate", "PmlsEstateController", "toUpdate", "", null, "", "查询地址信息", e);
        }
        //存放到mop中
        mop.addAttribute("estate", resultData.getReturnData());
        return "estate/estateEditPopup";
    }

    /**
     * 项目信息变更
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/updatePopupDetail", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> updatePopupDetail(HttpServletRequest request) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            pmlsEstateService.updatePopupDetail(reqMap);
        } catch (Exception e) {
            logger.error("estate", "PmlsEstateController", "updatePopupDetail", "", null, "", "修改项目详情失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "修改项目详情失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }

    /**
     * 选择开发商页面
     *
     * @return
     */
    @RequestMapping(value = "selectDeveloper", method = RequestMethod.GET)
    public ModelAndView selectDeveloper(ModelMap mop, HttpServletRequest request) {
        mop.addAllAttributes(bindParamToMap(request));
        ModelAndView mv = new ModelAndView("estate/selectDeveloper");
        return mv;
    }

    /**
     * 审核项目页面
     *
     * @return
     */
    @RequestMapping(value = "estateAuditPopup", method = RequestMethod.GET)
    public ModelAndView estateAuditPopup(ModelMap mop, HttpServletRequest request) {
        mop.addAllAttributes(bindParamToMap(request));
        ModelAndView mv = new ModelAndView("estate/estateAuditPopup");
        return mv;
    }

    @RequestMapping(value = "/popupIncomePlan/{projectNo}", method = RequestMethod.GET)
    public String popupReleaseCity(@PathVariable("projectNo") String projectNo, ModelMap mop) {
        //返回视图
        mop.put("projectNo", projectNo);
        return "estate/estateIncomePlan";
    }

    @RequestMapping(value = "/popupEditPlan/{id}", method = RequestMethod.GET)
    public String popupReleaseCity(@PathVariable("id") Integer id, ModelMap mop) {
        //返回视图
        ResultData<?> resultData = new ResultData<>();
        mop.put("id", id);
        try {
            resultData = lnkEstateIncomeplanService.queryPlanById(id);
        } catch (Exception e) {
            logger.error("estate", "PmlsEstateController", "queryPlanById", "", null, "", "查询收入方案", e);
        }
        mop.addAttribute("planDto", resultData.getReturnData());
        return "estate/editEstateIncomePlan";
    }

    @RequestMapping(value = "/addIncomePlan", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> addIncomePlan(HttpServletRequest request, ModelMap modelMap) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        UserInfo userInfo = UserInfoHolder.get();
        reqMap.put("userId", userInfo.getUserId());
        try {
            lnkEstateIncomeplanService.addIncomePlan(reqMap);
        } catch (Exception e) {
            logger.error("estate", "PmlsEstateController", "addIncomePlan", "", null, "", "新增收款方案失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "新增收款方案失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        rspMap.put(Constant.RETURN_MSG_KEY, "新增收款方案成功！");
        return getOperateJSONView(rspMap);
    }

    @RequestMapping(value = "/editIncomePlan", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> editIncomePlan(HttpServletRequest request, ModelMap modelMap) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        UserInfo userInfo = UserInfoHolder.get();
        reqMap.put("userId", userInfo.getUserId());
        reqMap.put("userCode", userInfo.getUserCode());
        reqMap.put("userName", userInfo.getUserName());
        try {
            lnkEstateIncomeplanService.editIncomePlan(reqMap);
        } catch (Exception e) {
            logger.error("estate", "PmlsEstateController", "editIncomePlan", "", null, "", "新增收款方案失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "修改收款方案失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        rspMap.put(Constant.RETURN_MSG_KEY, "修改收款方案成功！");
        return getOperateJSONView(rspMap);
    }

    @RequestMapping(value = "/toProhibit/{id}/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> toProhibit(HttpServletRequest request, @PathVariable("id") int id, @PathVariable("type") int type) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //请求map
        Map<String, Object> reqMap = new HashMap<>();
        UserInfo userInfo = UserInfoHolder.get();
        reqMap.put("userId", userInfo.getUserId());
        reqMap.put("userCode", userInfo.getUserCode());
        reqMap.put("userName", userInfo.getUserName());
        reqMap.put("id", id);
        reqMap.put("type", type);
        try {
            lnkEstateIncomeplanService.toProhibit(reqMap);
        } catch (Exception e) {
            logger.error("estate", "PmlsEstateController", "toProhibit", "", null, "", "禁用收款方案失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "禁用收款方案失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        if (type == 1) {
            rspMap.put(Constant.RETURN_MSG_KEY, "禁用收款方案成功！");
        } else {
            rspMap.put(Constant.RETURN_MSG_KEY, "启用收款方案成功！");
        }
        return getOperateJSONView(rspMap);
    }

    @RequestMapping(value = "/selectIncomeplanListPopup/{projectNo}/{reportId}/{dealDate}", method = RequestMethod.GET)
    public ModelAndView selectIncomeplanListPopup(@PathVariable("projectNo") String projectNo,
                                                  @PathVariable("reportId") String reportId,
                                                  @PathVariable("dealDate") String dealDate,
                                                  ModelMap mop) {
        mop.put("projectNo", projectNo);
        mop.put("reportId", reportId);
        mop.put("dealDate", dealDate);
        ModelAndView mv = new ModelAndView("estate/selectIncomeplanListPopup");
        return mv;
    }

    @RequestMapping(value = "/selectFyplanListPopup/{projectNo}/{reportId}/{companyNo}/{dealDate}", method = RequestMethod.GET)
    public ModelAndView selectFyplanListPopup(@PathVariable("projectNo") String projectNo,
                                              @PathVariable("reportId") String reportId,
                                              @PathVariable("companyNo") String companyNo,
                                              @PathVariable("dealDate") String dealDate,
                                              ModelMap mop) {
        mop.put("projectNo", projectNo);
        mop.put("reportId", reportId);
        mop.put("companyNo", companyNo);
        mop.put("dealDate", dealDate);

        ModelAndView mv = new ModelAndView("estate/selectFyplanListPopup");
        return mv;
    }


    @RequestMapping(value = "/queryIncomeplanList", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> queryIncomeplanList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            reqMap.put("planType", "28701");
            //获取页面显示数据
            resultData = pmlsEstateService.getProgrammeList(reqMap, pageInfo);

        } catch (Exception e) {
            logger.error("查询收入方案异常", e);

            logger.error("pmlsEstate",
                    "PmlsEstateController",
                    "queryIncomeplanList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询收入方案",
                    e);
        }

        return resultData;
    }


    @RequestMapping(value = "/queryFyplanList", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> queryFyplanList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            reqMap.put("planType", "28702");
            //获取页面显示数据
            resultData = pmlsEstateService.getProgrammeList(reqMap, pageInfo);

        } catch (Exception e) {
            logger.error("查询返佣方案异常", e);

            logger.error("pmlsEstate",
                    "PmlsEstateController",
                    "queryFyplanList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询返佣方案",
                    e);
        }

        return resultData;
    }


    @RequestMapping(value = "/queryCXSQAmount", method = RequestMethod.GET)
    @ResponseBody
    public ResultData<?> queryCXSQAmount(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            resultData = lnkEstateIncomeplanService.queryCXSQAmount(reqMap);
        } catch (Exception e) {

            logger.error("pmlsEstate",
                    "PmlsEstateController",
                    "queryCXSQAmount",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询收入方案",
                    e);
        }

        return resultData;
    }

    /**
     * 取得佣金方案的税前金额
     */
    @RequestMapping(value = "/queryQueryYJAmount", method = RequestMethod.GET)
    @ResponseBody
    public ResultData<?> queryQueryYJAmount(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            resultData = pmlsEstateService.queryQueryYJAmount(reqMap);

        } catch (Exception e) {

            logger.error("pmlsEstate",
                    "PmlsEstateController",
                    "queryQueryYJAmount",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "佣金方案取得税前金额",
                    e);
        }

        return resultData;
    }

    /**
     * 选择项目负责人页面
     *
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "projectLeaderSel", method = RequestMethod.GET)
    public String projectLeaderSel(HttpServletRequest request, ModelMap mop) {
        bindParamToAttrbute(request);
        return "estate/projectLeaderSel";
    }

    /**
     * 获取项目负责人列表
     *
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "projectLeaderSel/getList", method = RequestMethod.POST)
    @ResponseBody
    public ResultData getProjectLeaderSelList(HttpServletRequest request, ModelMap mop) {
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        //返回list
        ResultData<?> resultData = new ResultData();
        try {
            reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
            resultData = pmlsEstateService.getProjectLeaderSelList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("pmlsEstate", "PmlsEstateController", "getProjectLeaderSelList", "", null, "", "获取项目负责人列表失败", e);
        }
        return resultData;
    }

    /**
     * 获取项目信息
     * @param id
     */
    @RequestMapping(value = "/getEstateById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResultData<?> getEstateById(HttpServletRequest request, @PathVariable("id") Integer id) {
        ResultData<?> result = new ResultData<>();
        try {
            result = pmlsEstateService.getEstateById(id);
        } catch (Exception e) {
            logger.error("根据id查询项目基本信息失败",e);
            logger.error("estate", "PmlsEstateController", "getEstateById", "", null, "", " 根据id查询项目基本信息失败 ", e);
            result.setFail("查询项目基本信息失败！");
        }
        return result;
    }

    /**
     * 项目合作意向状态变更
     * @param id
     */
    @RequestMapping(value = "/uptCooperationStatus/{id}/{cooperationStatus}", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> uptCooperationStatus(HttpServletRequest request
            , @PathVariable("id") Integer id, @PathVariable("cooperationStatus") String cooperationStatus) {
        ResultData<?> result = new ResultData<>();
        try {
            result = pmlsEstateService.uptCooperationStatus(id,cooperationStatus);
        } catch (Exception e) {
            logger.error("项目合作意向状态变更异常",e);
            logger.error("estate", "PmlsEstateController", "uptCooperationStatus", id.toString(), null, "", " 项目合作意向状态变更异常 ", e);
            result.setFail("项目合作意向状态变更失败！");
        }
        return result;
    }
}
