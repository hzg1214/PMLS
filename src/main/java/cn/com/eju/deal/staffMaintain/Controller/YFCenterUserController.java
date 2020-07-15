package cn.com.eju.deal.staffMaintain.Controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.base.util.HttpClientUtil;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.common.CenterDto;
import cn.com.eju.deal.houseLinkage.estate.dto.City;
import cn.com.eju.deal.staffMaintain.service.YFCenterUserService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/yfCenterUser", method = RequestMethod.GET)
public class YFCenterUserController extends BaseController {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "yfCenterUserService")
    private YFCenterUserService yfCenterUserService;

    /**
     * 查询初始画面
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String yfCenterUserList(HttpServletRequest request, HttpServletResponse response, ModelMap mop) {

        try {
            //获取请求参数
            Map<String, Object> map = bindParamToMap(request);
            if (map.containsKey("searchParam") && "1".equals(map.get("searchParam"))) {
                Map<String, Object> searchParamMap = getRememberSearch(request, ComConstants.STAFF_MAINTAIN_YFCENTERUSER_LIST_SEARCH);
                mop.put("searchParamMap", JSON.toJSON(searchParamMap));
            } else {
                clearSearch(request, response, ComConstants.STAFF_MAINTAIN_YFCENTERUSER_LIST_SEARCH);
            }

            // 城市初始默认值
            Map<String, Object> queryParam = new HashMap<>();
            UserInfo userInfo = UserInfoHolder.get();
            if (userInfo != null) {
                queryParam.put("userId", userInfo.getUserId());
                queryParam.put("cityNo", userInfo.getCityNo());
            }

            // 归属城市列表
            ResultData<List<City>> resultCity = yfCenterUserService.getAreaCity(queryParam);
            mop.put("citylist", resultCity.getReturnData());

            // 归属中心列表
//            ResultData<List<CenterDto>> resultCenter = yfCenterUserService.getCenterGroup(queryParam);
//            mop.put("centerList", resultCenter.getReturnData());

            // 中心权限
            ResultData<List<Map<?, ?>>> resultAuthCenterIds = yfCenterUserService.getCenterAuth(queryParam);
            List<Map<?, ?>> authCenterIds = resultAuthCenterIds.getReturnData();

            String strAuthCenterIds ="";
            if (authCenterIds != null){
                for (Map<?, ?> item : authCenterIds) {
                    strAuthCenterIds += item.get("centerId").toString() + ",";
                }
            }
            strAuthCenterIds+="0";
            mop.put("authCenterIds",strAuthCenterIds);

        } catch (Exception e) {
            logger.error("staffMaintain", "YFCenterUserController", "update", "",
                    UserInfoHolder.getUserId(), "", "查询画面初始化-人员维护信息失败！", e);
        }

        return "staffMaintain/yfCenterUserList";

    }

    /**
     * 查询列表数据
     */
    @RequestMapping(value = "/q", method = RequestMethod.GET)
    public String queryList(HttpServletRequest request, ModelMap mop, HttpServletResponse response) {

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        // 分页信息
        PageInfo pageInfo = getPageInfo(request);

        UserInfo userInfo = UserInfoHolder.get();
        if (userInfo != null) {
            reqMap.put("userId", userInfo.getUserId());
        }

        ResultData<?> reback = new ResultData<>();
        List<?> contentlist = new ArrayList<>();
        try {
            //保存搜索数据
            if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.SCENE_ESTATE_LIST_SEARCH, reqMap);
            }
            //获取页面显示数据
            reback = yfCenterUserService.queryList(reqMap, pageInfo);

            //页面数据
            contentlist = (List<?>) reback.getReturnData();

        } catch (Exception e) {
            logger.error("staffMaintain", "YFCenterUserController", "queryList", "",
                    UserInfoHolder.getUserId(), "", "查询列表数据-取得人员维护失败", e);
        }

        //存放到mop中
        mop.addAttribute("contentlist", contentlist);

        return "staffMaintain/yfCenterUserListCtx";
    }

    /**
     * 新增-Popup画面
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(HttpServletRequest request, ModelMap mop, HttpServletResponse response) {
        try {

            // 城市初始默认值
            Map<String, Object> queryParam = new HashMap<>();
            UserInfo userInfo = UserInfoHolder.get();
            if (userInfo != null) {
                queryParam.put("userId", userInfo.getUserId());
                queryParam.put("cityNo", userInfo.getCityNo());
            }

            // 归属城市列表
            ResultData<List<City>> resultCity = yfCenterUserService.getAreaCity(queryParam);
            mop.put("citylist", resultCity.getReturnData());

            // 归属中心列表
            ResultData<List<CenterDto>> resultCenter = yfCenterUserService.getCenterGroup(queryParam);
            mop.put("centerList", resultCenter.getReturnData());

        } catch (Exception e) {
            logger.error("staffMaintain", "YFCenterUserController", "create", "",
                    UserInfoHolder.getUserId(), "", "新增Popup画面初始化-人员维护信息失败！", e);
        }
        return "staffMaintain/yfCenterUserCreatePopup";
    }

    /**
     * 新增处理
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> save(HttpServletRequest request, ModelMap mop) {

        Map<String, Object> rspMap = new HashMap<String, Object>();

        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);

        try {
            UserInfo userInfo = UserInfoHolder.get();
            if (userInfo != null) {
                reqMap.put("userIdCreate", userInfo.getUserId());
            }
            // 新增保存人员维护信息
            ResultData<?> resultData = yfCenterUserService.save(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());

        } catch (Exception e) {
            logger.error("staffMaintain", "YFCenterUserController", "save", "",
                    UserInfoHolder.getUserId(), "", "保存人员维护信息处理失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "保存失败！");
        }

        return getMapView(rspMap);
    }

    /**
     * 修改-Popup
     */
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public String modify(@PathVariable("id") int id, ModelMap mop) {
        try {

            // 人员维护详细信息（归属城市、归属中心、员工工号、员工姓名）
            ResultData<?> resultData = yfCenterUserService.getById(id);
            resultData.getReturnData();
            mop.put("info", resultData.getReturnData());
            Map<String, Object> info = (Map<String, Object>) resultData.getReturnData();

            // 城市初始默认值
            Map<String, Object> queryParam = new HashMap<>();
            UserInfo userInfo = UserInfoHolder.get();
            if (userInfo != null) {
                queryParam.put("userId", userInfo.getUserId());
                queryParam.put("cityNo", userInfo.getCityNo());
            }
            if (info.containsKey("cityNo")) {
                queryParam.put("cityNo", info.get("cityNo"));
            }

            // 归属城市列表
            ResultData<List<City>> resultCity = yfCenterUserService.getAreaCity(queryParam);
            mop.put("citylist", resultCity.getReturnData());

            // 归属中心列表
            ResultData<List<CenterDto>> resultCenter = yfCenterUserService.getCenterGroup(queryParam);
            mop.put("centerList", resultCenter.getReturnData());


        } catch (Exception e) {
            logger.error("staffMaintain", "YFCenterUserController", "modify", "",
                    UserInfoHolder.getUserId(), "", "修改Popup画面初始化-人员维护信息失败！", e);

        }
        return "staffMaintain/yfCenterUserModifyPopup";
    }

    /**
     * 更新人员维护信息处理
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> update(HttpServletRequest request, ModelMap mop) {

        Map<String, Object> rspMap = new HashMap<String, Object>();

        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);

        try {
            UserInfo userInfo = UserInfoHolder.get();
            if (userInfo != null) {
                reqMap.put("userIdCreate", userInfo.getUserId());
            }

            // 提交更新人员维护信息
            ResultData<?> resultData = yfCenterUserService.update(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());

        } catch (Exception e) {
            logger.error("staffMaintain", "YFCenterUserController", "update", "",
                    UserInfoHolder.getUserId(), "", "更新人员维护信息处理失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "更新失败！");
        }

        return getMapView(rspMap);
    }

    /**
     * 删除人员维护信息处理
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> delete(HttpServletRequest request, ModelMap mop) {

        Map<String, Object> rspMap = new HashMap<String, Object>();

        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);

        try {
            UserInfo userInfo = UserInfoHolder.get();
            if (userInfo != null) {
                reqMap.put("userIdCreate", userInfo.getUserId());
            }

            // 提交删除人员维护信息
            ResultData<?> resultData = yfCenterUserService.delete(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());

        } catch (Exception e) {
            logger.error("staffMaintain", "YFCenterUserController", "update", "",
                    UserInfoHolder.getUserId(), "", "更新人员维护信息处理失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "删除失败！");
        }

        return getOperateJSONView(rspMap);
    }
    @RequestMapping(value = "/queryYFTBind", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> queryYFTBind(HttpServletRequest request, ModelMap mop) {

        Map<String, Object> rspMap = new HashMap<String, Object>();

        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);

        try {

            ResultData<?> resultData = yfCenterUserService.queryYFTBind(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
            rspMap.put(Constant.RETURN_DATA_KEY,resultData.getReturnData());

        } catch (Exception e) {
            logger.error("staffMaintain", "YFCenterUserController", "queryYFTBind", "",
                    UserInfoHolder.getUserId(), "", "查询友房通绑定信息失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "查询失败！");
        }

        return getOperateJSONView(rspMap);
    }

    @RequestMapping(value = "/checkWHRIsUsed", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> checkWHRIsUsed(HttpServletRequest request, ModelMap mop) {

        Map<String, Object> rspMap = new HashMap<String, Object>();

        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        try {

            ResultData<String> resultData = new ResultData<String>();
            resultData = getyFInterfaceInfo("/CheckWHRIsUsed", JSON.toJSONString(reqMap), null, UserInfoHolder.getUserId());
            rspMap.put(Constant.RETURN_DATA_KEY,resultData.getReturnData());
            rspMap.put(Constant.RETURN_CODE_KEY,resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY,resultData.getReturnMsg());
        } catch (Exception e) {
            logger.error("staffMaintain", "YFCenterUserController", "checkWHRIsUsed", "",
                    UserInfoHolder.getUserId(), "", "查询友房通信息失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "查询失败！");
        }

        return getOperateJSONView(rspMap);
    }

    private ResultData getyFInterfaceInfo(String func, String paramMap, String typeId, Integer userId) {
        ResultData<String> resultData = new ResultData<String>();
        Map<String, Object> returnMap = new HashMap<String, Object>();
        resultData.setFail("调用有房接口失败");
        String returnDataStr = null;
        String url = SystemParam.getWebConfigValue("youfangReportUrl") +func;
        logger.info("调用有房接口start#####请求#url="+url);
        try {
            returnDataStr = HttpClientUtil.httpPostYF(url, paramMap);
            if(StringUtil.isNotEmpty(returnDataStr)){
                returnMap = JSON.parseObject(returnDataStr, Map.class);
                if(returnMap.containsKey("BFlag") && returnMap.containsKey("TData")){
                    Integer bFlag = (Integer) returnMap.get("BFlag");
                    Object tData = returnMap.get("TData");
                    if(10==bFlag){
                        resultData.setSuccess();
                        resultData.setReturnData(JSON.toJSONString(tData));
                    }else{
                        resultData.setFail("调用接口失败");
                    }
                }
            }
        } catch (Exception e) {
            try {
                logger.error("YFCenterUserController","YFCenterUserController","getyFInterfaceInfo","userId="+ userId, userId,null,
                        "调用有房接口:#####请求参数#url="+url+"#####返回信息"+returnDataStr,e);
            } catch (Exception e2) {

            }

            resultData.setFail("调用有房接口异常");
        }
        return resultData;
    }

    /**
     * 根据城市CityNo获取其行政区List
     */
    @RequestMapping(value = "/city/{cityNo}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getCenterListByCityNo(@PathVariable("cityNo") String cityNo) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        //返回map
        ResultData<List<CenterDto>> resultData = new ResultData<List<CenterDto>>();

        // 城市初始默认值
        Map<String, Object> queryParam = new HashMap<>();
        UserInfo userInfo = UserInfoHolder.get();
        if (userInfo != null) {
            queryParam.put("userId", userInfo.getUserId());
        }
        queryParam.put("cityNo", cityNo);

        try {
            resultData = yfCenterUserService.getCenterGroup(queryParam);
        } catch (Exception e) {
            logger.error("base.linkage", "LinkageController", "getDistrictListByCityNo", "", null, "", "根据行政区DistrictNo获取其板块List", e);
        }

        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());

        return getSearchJSONView(rspMap);
    }


}
