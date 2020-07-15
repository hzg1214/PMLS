package cn.com.eju.deal.Report.controller;

import cn.com.eju.deal.Report.dto.UsrOrgHisDto;
import cn.com.eju.deal.Report.service.CommonReportService;
import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.BaseUtils;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by eju on 2018/1/18.
 */
@Controller
@RequestMapping(value = "commonReport")
public class CommonReportController extends BaseController {

    @Resource(name = "commonReportService")
    private CommonReportService commonReportService;


    /**
     * 获取归属区域
     * @param request
     * @return
     */
    @RequestMapping(value = "/getRegion", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView getRegion(HttpServletRequest request) {

        ReturnView returnView = new ReturnView();
        returnView.setFail();

        // 获取请求参数
        Map<String, Object> map = bindParamToMap(request);
        map.put("sqlUnControl", false);

        UserInfo userInfo = UserInfoHolder.get();
        if (userInfo != null) {
            map.put("userId", userInfo.getUserId());
        }
        else {
            return returnView;
        }

        List<UsrOrgHisDto> list = null;
        ResultData<List<UsrOrgHisDto>> resultData = null;
        try {

            resultData = commonReportService.getRegion(map);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(resultData != null){
            list = resultData.getReturnData();
        }

        returnView.setSuccess();
        returnView.put("list", list);

        return returnView;
    }

    /**
     * 获取归属城市
     * @param request
     * @return
     */
    @RequestMapping(value = "/getAreaCity", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView getAreaCity(HttpServletRequest request) {

        ReturnView returnView = new ReturnView();
        returnView.setFail();

        // 获取请求参数
        Map<String, Object> map = bindParamToMap(request);
        map.put("sqlUnControl", false);

        String regionCodes = (String)map.get("regionCodes");
        if(StringUtil.isEmpty(regionCodes)){
            returnView.setFail();
            return returnView;
        }
        List<String> regionCodeList = BaseUtils.changeArrayToList(regionCodes,";");
        map.put("regionCodeList",regionCodeList);
        map.remove("regionCodes");

        UserInfo userInfo = UserInfoHolder.get();
        if (userInfo != null) {
            map.put("userId", userInfo.getUserId());
        }
        else {
            return returnView;
        }

        List<UsrOrgHisDto> list = null;
        ResultData<List<UsrOrgHisDto>> resultData = null;
        try {

            resultData = commonReportService.getAreaCity(map);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(resultData != null){
            list = resultData.getReturnData();
        }

        returnView.setSuccess();
        returnView.put("list", list);

        return returnView;
    }
    
    /**
     * 	获取归属城市(不传区域)
     * @param request
     * @return
     */
    @RequestMapping(value = "/getAllAreaCity", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView getAllAreaCity(HttpServletRequest request) {
    	
    	ReturnView returnView = new ReturnView();
    	returnView.setFail();
    	
    	// 获取请求参数
    	Map<String, Object> map = bindParamToMap(request);
    	map.put("sqlUnControl", false);
    	
    	String regionCodes = (String)map.get("regionCodes");
    	List<String> regionCodeList  = null;
    	if(!StringUtil.isEmpty(regionCodes)){
    		regionCodeList = BaseUtils.changeArrayToList(regionCodes,";");
    	}
    	map.put("regionCodeList",regionCodeList);
    	map.remove("regionCodes");
    	
    	UserInfo userInfo = UserInfoHolder.get();
    	if (userInfo != null) {
    		map.put("userId", userInfo.getUserId());
    	}
    	else {
    		return returnView;
    	}
    	
    	List<UsrOrgHisDto> list = null;
    	ResultData<List<UsrOrgHisDto>> resultData = null;
    	try {
    		
    		resultData = commonReportService.getAreaCity(map);
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	if(resultData != null){
    		list = resultData.getReturnData();
    	}
    	
    	returnView.setSuccess();
    	returnView.put("list", list);
    	
    	return returnView;
    }

    /**
     * 获取所在城市
     * @param request
     * @return
     */
    @RequestMapping(value = "/getCityGroup", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView getCityGroup(HttpServletRequest request) {

        ReturnView returnView = new ReturnView();
        returnView.setFail();

        // 获取请求参数
        Map<String, Object> map = bindParamToMap(request);
        map.put("sqlUnControl", false);

        String regionCodes = (String)map.get("regionCodes");
        String areaCityCodes = (String)map.get("areaCityCodes");

        String yearly = (String)map.get("yearly");
        if("2017".equals(yearly)){
            if(StringUtil.isNotEmpty(regionCodes) || StringUtil.isNotEmpty(areaCityCodes)){
                returnView.setFail();
                return returnView;
            }
        }else if("2018".equals(yearly)){
            if(StringUtil.isEmpty(regionCodes) && StringUtil.isEmpty(areaCityCodes)){
                returnView.setFail();
                return returnView;
            }
        }

        List<String> regionCodeList = BaseUtils.changeArrayToList(regionCodes,";");
        List<String> areaCityCodeList = BaseUtils.changeArrayToList(areaCityCodes,";");
        map.put("regionCodeList",regionCodeList);
        map.put("areaCityCodeList",areaCityCodeList);
        map.remove("regionCodes");
        map.remove("areaCityCodes");

        UserInfo userInfo = UserInfoHolder.get();
        if (userInfo != null) {
            map.put("userId", userInfo.getUserId());
        }
        else {
            return returnView;
        }

        List<UsrOrgHisDto> list = null;
        ResultData<List<UsrOrgHisDto>> resultData = null;
        try {

            resultData = commonReportService.getCityGroup(map);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(resultData != null){
            list = resultData.getReturnData();
        }

        returnView.setSuccess();
        returnView.put("list", list);

        return returnView;
    }

    /**
     * 获取归属中心
     * @param request
     * @return
     */
    @RequestMapping(value = "/getCenterGroup", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView getCenterGroup(HttpServletRequest request) {

        ReturnView returnView = new ReturnView();
        returnView.setFail();

        // 获取请求参数
        Map<String, Object> map = bindParamToMap(request);
        map.put("sqlUnControl", false);

        String regionCodes = (String)map.get("regionCodes");
        String areaCityCodes = (String)map.get("areaCityCodes");
        String cityIds = (String)map.get("cityIds");
        if(StringUtil.isEmpty(cityIds)){
            return returnView;
        }

        List<String> regionCodeList = BaseUtils.changeArrayToList(regionCodes,";");
        List<String> areaCityCodeList = BaseUtils.changeArrayToList(areaCityCodes,";");
        List<String> cityIdList = BaseUtils.changeArrayToList(cityIds,";");
        map.put("regionCodeList",regionCodeList);
        map.put("areaCityCodeList",areaCityCodeList);
        map.put("cityIdList",cityIdList);
        map.remove("regionCodes");
        map.remove("areaCityCodes");
        map.remove("cityIds");

        UserInfo userInfo = UserInfoHolder.get();
        if (userInfo != null) {
            map.put("userId", userInfo.getUserId());
        }
        else {
            return returnView;
        }

        List<UsrOrgHisDto> list = null;
        ResultData<List<UsrOrgHisDto>> resultData = null;
        try {
            resultData = commonReportService.getCenterGroup(map);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(resultData != null){
            list = resultData.getReturnData();
        }

        returnView.setSuccess();
        returnView.put("list", list);

        return returnView;
    }

    /**
     * 获取项目归属部门
     * @param request
     * @return
     */
    @RequestMapping(value = "/getDeptGroup", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView getDeptGroup(HttpServletRequest request) {

        ReturnView returnView = new ReturnView();
        returnView.setFail();

        // 获取请求参数
        Map<String, Object> map = bindParamToMap(request);
        map.put("sqlUnControl", false);

        String cityIds = (String)map.get("cityIds");
        if(StringUtil.isEmpty(cityIds)){
            return returnView;
        }

        List<String> cityIdList = BaseUtils.changeArrayToList(cityIds,";");

        map.put("cityIdList",cityIdList);
        map.remove("cityIds");

        UserInfo userInfo = UserInfoHolder.get();
        if (userInfo != null) {
            map.put("userId", userInfo.getUserId());
        }
        else {
            return returnView;
        }

        List<UsrOrgHisDto> list = null;
        ResultData<List<UsrOrgHisDto>> resultData = null;
        try {
            resultData = commonReportService.getDeptGroup(map);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(resultData != null){
            list = resultData.getReturnData();
        }

        returnView.setSuccess();
        returnView.put("list", list);

        return returnView;
    }

}
