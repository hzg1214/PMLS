package cn.com.eju.deal.staffMaintain.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.dto.common.CenterDto;
import cn.com.eju.deal.houseLinkage.estate.dto.City;
import cn.com.eju.deal.staffMaintain.service.MCenterUserService;
import cn.com.eju.deal.staffMaintain.service.YFCenterUserService;

/**
 * desc:管理人员维护
 * @author :zhenggang.Huang
 * @date   :2019年5月14日
 */
@Controller
@RequestMapping(value = "/mCenterUser", method = RequestMethod.GET)
public class MCenterUserController extends BaseController {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "yfCenterUserService")
    private YFCenterUserService yfCenterUserService;
    
    @Resource(name = "mCenterUserService")
    private MCenterUserService mCenterUserService;

    /**
     * desc:查询表头
     * 2019年5月14日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String mCenterUserList(HttpServletRequest request, HttpServletResponse response, ModelMap mop) {

        try {
            //获取请求参数
            Map<String, Object> map = bindParamToMap(request);
            if (map.containsKey("searchParam") && "1".equals(map.get("searchParam"))) {
                Map<String, Object> searchParamMap = getRememberSearch(request, ComConstants.STAFF_MAINTAIN_MCENTERUSER_LIST_SEARCH);
                mop.put("searchParamMap", JSON.toJSON(searchParamMap));
            } else {
                clearSearch(request, response, ComConstants.STAFF_MAINTAIN_MCENTERUSER_LIST_SEARCH);
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
            logger.error("staffMaintain", "MCenterUserController", "update", "",
                    UserInfoHolder.getUserId(), "", "查询画面初始化-管理人员维护信息失败！", e);
        }

        return "staffMaintain/mCenterUserList";

    }

    /**
     * desc:查询列表
     * 2019年5月14日
     * author:zhenggang.Huang
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
            reback = mCenterUserService.queryList(reqMap, pageInfo);

            //页面数据
            contentlist = (List<?>) reback.getReturnData();

        } catch (Exception e) {
            logger.error("staffMaintain", "MCenterUserController", "queryList", "",
                    UserInfoHolder.getUserId(), "", "查询列表数据-取得管理人员维护失败", e);
        }

        //存放到mop中
        mop.addAttribute("contentlist", contentlist);

        return "staffMaintain/mCenterUserListCtx";
    }

    /**
     * desc:人员维护弹框初始化
     * 2019年5月15日
     * author:zhenggang.Huang
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
            logger.error("staffMaintain", "MCenterUserController", "create", "",
                    UserInfoHolder.getUserId(), "", "人员维护Popup画面初始化-人员维护信息失败！", e);
        }
        return "staffMaintain/mCenterUserCreatePopup";
    }

    /**
     * desc:保存人员维护
     * 2019年5月15日
     * author:zhenggang.Huang
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
            ResultData<?> resultData = mCenterUserService.save(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());

        } catch (Exception e) {
            logger.error("staffMaintain", "MCenterUserController", "save", "",
                    UserInfoHolder.getUserId(), "", "保存人员维护信息处理失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "保存失败！");
        }

        return getMapView(rspMap);
    }

    /**
     * desc:编辑
     * 2019年5月15日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public String modify(@PathVariable("id") int id, ModelMap mop) {
        try {

            // 人员维护详细信息（归属城市、归属中心、员工工号、员工姓名）
            ResultData<?> resultData = mCenterUserService.getById(id);
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
            logger.error("staffMaintain", "MCenterUserController", "modify", "",
                    UserInfoHolder.getUserId(), "", "修改Popup画面初始化-人员维护信息失败！", e);
        }
        return "staffMaintain/mCenterUserModifyPopup";
    }

    /**
     * desc:编辑保存
     * 2019年5月15日
     * author:zhenggang.Huang
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
            ResultData<?> resultData = mCenterUserService.update(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());

        } catch (Exception e) {
            logger.error("staffMaintain", "MCenterUserController", "update", "",
                    UserInfoHolder.getUserId(), "", "更新人员维护信息处理失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "更新失败！");
        }

        return getMapView(rspMap);
    }

    /**
     * desc:删除
     * 2019年5月15日
     * author:zhenggang.Huang
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
			ResultData<?> resultData = mCenterUserService.delete(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());

        } catch (Exception e) {
            logger.error("staffMaintain", "MCenterUserController", "update", "",
                    UserInfoHolder.getUserId(), "", "更新人员维护信息处理失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "删除失败！");
        }

        return getOperateJSONView(rspMap);
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
