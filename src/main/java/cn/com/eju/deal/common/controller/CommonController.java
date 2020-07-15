package cn.com.eju.deal.common.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.service.CommonService;
import cn.com.eju.deal.common.util.WXSendMsgUtil;
import cn.com.eju.deal.core.support.*;
import cn.com.eju.deal.core.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 公用controller
 *
 * @author (li_xiaodong)
 * @date 2015年11月29日 下午5:15:09
 */
@Controller
@RequestMapping("/commons")
public class CommonController extends BaseController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private CommonService commonService;

    /**
     * 刷新缓存
     *
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> refresh(HttpServletRequest request, ModelMap mop) {
        //响应应Map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        logger.info("外部接口调入,刷新配置");

        SystemParam.refreshCodeMap();

        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);

        return getOperateJSONView(rspMap);

    }

    /**
     * 查询架构年份
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryOrgList", method = RequestMethod.GET)
    public ReturnView<?, ?> queryOrgList(HttpServletRequest request) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        //返回map
        Map<String, Object> rspMap = new HashMap<>();
        ResultData<?> resultData = new ResultData<>();
        try {
            //获取页面显示数据
            resultData = commonService.queryOrgList(reqMap);
        } catch (Exception e) {
            logger.error("commons", "CommonController", "queryOrgList", "", null, "", "获取组织架构失败！", e);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        if (null != resultData.getReturnData()) {
            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        }
        return getMapView(rspMap);
    }

    /**
     * 查询城市
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryCityList", method = RequestMethod.GET)
    public ReturnView<?, ?> queryCityList(HttpServletRequest request) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.get().getUserId());
        //返回map
        Map<String, Object> rspMap = new HashMap<>();
        ResultData<?> resultData = new ResultData<>();
        try {
            //获取页面显示数据
            resultData = commonService.queryCityList(reqMap);
        } catch (Exception e) {
            logger.error("commons", "CommonController", "queryCityList", "", null, "", "获取城市失败！", e);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        if (null != resultData.getReturnData()) {
            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        }
        return getMapView(rspMap);
    }

    /**
     * 得到门店所属城市
     *
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "getStoreCity", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getStoreCity(HttpServletRequest request, ModelMap mop) {

        Map<String, Object> rspMap = new HashMap<String, Object>();

        //查询isService=1的城市
        ResultData<?> reback = new ResultData<>();

        try {

            reback = commonService.queryCityListByIsService();

        } catch (Exception e) {

            e.printStackTrace();
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "门店所属城市初始化失败");
            return getOperateJSONView(rspMap);

        }

        rspMap.put(Constant.RETURN_CODE_KEY, reback.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, reback.getReturnMsg());
        rspMap.put(Constant.RETURN_DATA_KEY, reback.getReturnData());
        return getOperateJSONView(rspMap);
    }

    /**
     * 查询中心
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryCenterList", method = RequestMethod.GET)
    public ReturnView<?, ?> queryCenterList(HttpServletRequest request) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.get().getUserId());
        //返回map
        Map<String, Object> rspMap = new HashMap<>();
        ResultData<?> resultData = new ResultData<>();
        try {
            //获取页面显示数据
            resultData = commonService.queryCenterList(reqMap);
        } catch (Exception e) {
            logger.error("commons", "CommonController", "queryCenterList", "", null, "", "获取中心失败！", e);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        if (null != resultData.getReturnData()) {
            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        }
        return getMapView(rspMap);
    }

    /**
     * 清空搜索条件 cookie
     *
     * @param pageType
     * @return
     */
    @RequestMapping(value = "/clearSearchParam", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> clearSearchParam(HttpServletRequest request, HttpServletResponse response, String pageType) {
        ReturnView<?, ?> jsonView = new ReturnView<String, Object>();
        jsonView.setFail();
        try {
            clearSearch(request, response, pageType);
        } catch (Exception e) {
            logger.error("base", "BaseController", "clearSearchParam", null, null, null, AppMsg.getString("清空cookie中的搜索条件失败"), e);
        }
        return jsonView;
    }

    @ResponseBody
    @RequestMapping(value = "queryFullNameList", method = RequestMethod.GET)
    public ReturnView<?, ?> queryFullNameList(HttpServletRequest request) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
        //返回map
        Map<String, Object> rspMap = new HashMap<>();
        ResultData<?> resultData = new ResultData<>();
        try {
            //获取页面显示数据
            resultData = commonService.queryFullNameList(reqMap);
        } catch (Exception e) {
            logger.error("commons", "CommonController", "queryFullNameList", "", null, "", "获取我方全称列表失败！", e);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        if (null != resultData.getReturnData()) {
            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        }
        return getMapView(rspMap);
    }

    @ResponseBody
    @RequestMapping(value = "pushInfoToWX", method = RequestMethod.GET)
    public ReturnView<?, ?> pushInfoToWX(HttpServletRequest request) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        //返回map
        Map<String, Object> rspMap = new HashMap<>();

        if(!reqMap.containsKey("userCode") || !reqMap.containsKey("pushMsg")){
            rspMap.put(Constant.RETURN_CODE_KEY, -1);
            rspMap.put(Constant.RETURN_MSG_KEY, "无推送用户名或消息");
        }

        try {
            Integer agentId=Integer.parseInt(SystemParam.getWebConfigValue("msgAgentId"));
            WXSendMsgUtil.Send_msg(reqMap.get("userCode").toString(),"","","text",agentId,reqMap.get("pushMsg").toString());
            rspMap.put(Constant.RETURN_CODE_KEY, 200);
            rspMap.put(Constant.RETURN_MSG_KEY, "推送成功");
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, -1);
            rspMap.put(Constant.RETURN_MSG_KEY, "推送失败");
            logger.error("commons", "CommonController", "pushInfoToWX", JsonUtil.parseToJson(reqMap), null, "", "推送微信消息失败！", e);
        }

        return getMapView(rspMap);
    }
}
