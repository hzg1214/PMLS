package cn.com.eju.deal.store.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.store.service.StoreMapService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by tanlang on 2017-09-05.
 */
@Controller
@RequestMapping(value = "storeMap")
public class StoreMapController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private StoreMapService storeMapService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        UserInfo userInfo = UserInfoHolder.get();
        model.addAttribute("cityNo", userInfo.getCityNo());
        model.addAttribute("cityName", userInfo.getCityName());
        model.addAttribute("shoupaiTypeList", JSONArray.parseArray(JSON.toJSONString(SystemParam.getCodeListByKey(DictionaryConstants.SHOUPAI_TYPE + ""))));
        return "store/storeMap";
    }

    @ResponseBody
    @RequestMapping(value = "getMapInfo", method = RequestMethod.POST)
    public ResultData<?> getMapInfo(HttpServletRequest request) {
        UserInfo userInfo = UserInfoHolder.get();
        Map<String, Object> reqMap = bindParamToMap(request);
        if (reqMap.get("cityNo") == null || "".equals(reqMap.get("cityNo"))) {
            reqMap.put("cityNo", userInfo.getCityNo());
        }
        ResultData<?> mapInfo = null;
        try {
            mapInfo = storeMapService.getMapInfo(reqMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapInfo;
    }

    @ResponseBody
    @RequestMapping(value = "getStoreCount", method = RequestMethod.POST)
    public Object getStoreCount(HttpServletRequest request) {
        UserInfo userInfo = UserInfoHolder.get();
        Map<String, Object> reqMap = bindParamToMap(request);
        //reqMap.put("cityNo", userInfo.getCityNo());
        reqMap.put("userId", userInfo.getUserId());
        ResultData<?> resultData = null;
        try {
            resultData = storeMapService.getStoreCount(reqMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultData.getReturnData();
    }
    @ResponseBody
    @RequestMapping(value = "getCenterPosition", method = RequestMethod.POST)
    public Object getCenterPosition(HttpServletRequest request) {
        UserInfo userInfo = UserInfoHolder.get();
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", userInfo.getUserId());
        ResultData<?> resultData = null;
        try {
            resultData = storeMapService.getCenterPosition(reqMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultData.getReturnData();
    }
}
