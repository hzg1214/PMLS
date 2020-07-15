package cn.com.eju.deal.accountproject.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.eju.deal.accountproject.service.AccountProjectService;
import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.dto.accountproject.AccountProjectList;
import cn.com.eju.deal.houseLinkage.estate.dto.City;
import cn.com.eju.deal.staffMaintain.service.YFCenterUserService;

/**
 * desc:管理人员维护
 * @author :zhenggang.Huang
 * @date   :2019年7月26日
 */
@Controller
@RequestMapping(value = "/accountProject", method = RequestMethod.GET)
public class AccountProjectController extends BaseController {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "yfCenterUserService")
    private YFCenterUserService yfCenterUserService;
    
    @Resource(name = "accountProjectService")
    private AccountProjectService accountProjectService;

    /**
     * desc:查询表头
     * 2019年7月26日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String accountProjectList(HttpServletRequest request, HttpServletResponse response, ModelMap mop) {

        try {
            //获取请求参数
            Map<String, Object> map = bindParamToMap(request);
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

        } catch (Exception e) {
            logger.error("accountProject", "AccountProjectController", "sel", "",
                    UserInfoHolder.getUserId(), "", "查询画面初始化-核算主体维护信息失败！", e);
        }

        return "accountproject/accountProjectList";

    }

    /**
     * desc:查询列表
     * 2019年7月26日
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
        	// 归属城市列表
            ResultData<?> resultCity = yfCenterUserService.getAreaCity(reqMap);
            List<Map<String, Object>> listmap = (List<Map<String, Object>>) resultCity.getReturnData();
            List<String> cityNoList = new ArrayList<>();
            for (int i = 0; i < listmap.size(); i++) {
                Map tempMop = (Map) listmap.get(i);
                String cityNo = (String) tempMop.get("cityNo");
                cityNoList.add(cityNo);
            }
            reqMap.put("cityNoList", cityNoList);
            //获取页面显示数据
            reback = accountProjectService.queryList(reqMap, pageInfo);

            //页面数据
            contentlist = (List<?>) reback.getReturnData();

        } catch (Exception e) {
        	e.printStackTrace();
            logger.error("accountProject", "accountProjectController", "queryList", "",
                    UserInfoHolder.getUserId(), "", "查询列表数据-取得核算主体列表失败", e);
        }

        //存放到mop中
        mop.addAttribute("contentlist", contentlist);

        return "accountproject/accountProjectListCtx";
    }

    /**
     * desc:人员维护弹框初始化
     * 2019年7月26日
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
            
         // 核算主体列表
            ResultData<List<AccountProjectList>> resultCenter = accountProjectService.getAccountProjectList(queryParam);
            mop.put("accountProjectList", resultCenter.getReturnData());

        } catch (Exception e) {
            logger.error("accountProject", "AccountProjectController", "create", "",
                    UserInfoHolder.getUserId(), "", "核算主体Popup画面初始化-核算主体信息失败！", e);
        }
        return "accountproject/accountProjectCreatePopup";
    }

    /**
     * desc:保存创建核算主体
     * 2019年7月26日
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
            ResultData<?> resultData = accountProjectService.save(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());

        } catch (Exception e) {
            logger.error("accountProject", "AccountProjectController", "save", "",
                    UserInfoHolder.getUserId(), "", "保存核算主体信息处理失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "保存失败！");
        }

        return getMapView(rspMap);
    }

    /**
     * desc:编辑
     * 2019年7月26日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public String modify(@PathVariable("id") int id, ModelMap mop) {
        try {

            // 人员维护详细信息（归属城市、归属中心、员工工号、员工姓名）
            ResultData<?> resultData = accountProjectService.getById(id);
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
            
            // 核算主体列表
            ResultData<List<AccountProjectList>> resultCenter = accountProjectService.getAccountProjectList(queryParam);
            mop.put("accountProjectList", resultCenter.getReturnData());


        } catch (Exception e) {
            logger.error("AccountProject", "AccountProjectController", "modify", "",
                    UserInfoHolder.getUserId(), "", "修改Popup画面初始化-核算主体信息失败！", e);
        }
        return "accountproject/accountProjectModifyPopup";
    }

    /**
     * desc:编辑保存
     * 2019年7月26日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> update(HttpServletRequest request, ModelMap mop) {

        Map<String, Object> rspMap = new HashMap<String, Object>();

        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);

        //核算主体编号
        String accountProjectNos = (String)reqMap.get("accountProjectNos");
        if(StringUtils.isEmpty(accountProjectNos)) {
        	rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "请选择核算主体！");
            return getMapView(rspMap);
        }
        try {
            UserInfo userInfo = UserInfoHolder.get();
            if (userInfo != null) {
                reqMap.put("userIdCreate", userInfo.getUserId());
            }

            // 提交更新人员维护信息
            ResultData<?> resultData = accountProjectService.update(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());

        } catch (Exception e) {
            logger.error("accountProject", "AccountProjectController", "update", "",
                    UserInfoHolder.getUserId(), "", "更新核算主体信息处理失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "更新失败！");
        }

        return getMapView(rspMap);
    }

    /**
     * desc:删除
     * 2019年7月26日
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
			ResultData<?> resultData = accountProjectService.delete(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());

        } catch (Exception e) {
            logger.error("accountProject", "AccountProjectController", "delete", "",
                    UserInfoHolder.getUserId(), "", "删除核算主体信息处理失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "删除失败！");
        }

        return getOperateJSONView(rspMap);
    }
}
