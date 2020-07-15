package cn.com.eju.pmls.basicInformation.personnelPermissions.Controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.pmls.basicInformation.personnelPermissions.dto.UserCenterCityDto;
import cn.com.eju.pmls.basicInformation.personnelPermissions.service.PersonnelPermissionsService;

/**
 * desc:人员权限管理
 * date:2019-11-01
 */
@Controller
@RequestMapping(value = "personnelPermissions")
public class PersonnelPermissionsController extends BaseController {
	
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "personnelPermissionsService")
    private PersonnelPermissionsService personnelPermissionsService;

    /**
     * desc:查询-初始化
     * 2019年11月1日
     */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView personnelPermissionsList(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("basicInformation/personnelPermissions/personnelPermissionsList");
	}

    /**
     * desc:查询列表
     * 2019年11月1日
     */
    @RequestMapping(value = "/queryPersonnelPermissionsList", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> queryPersonnelPermissionsList(HttpServletRequest request) {

    	Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            //获取页面显示数据
            resultData = personnelPermissionsService.queryPersonnelPermissionsList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("personnelPermissions",
                    "PersonnelPermissionsController",
                    "queryPersonnelPermissionsList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询人员权限列表异常",
                    e);
        }
        return resultData;
    }
    
    @RequestMapping(value = "/getCenterListByCityNo", method = RequestMethod.GET)
    @ResponseBody
    public ResultData<?> getCenterListByCityNo(HttpServletRequest request) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        Map<String, Object> queryParam = bindParamToMap(request);
        //返回map
        ResultData resultData = new ResultData<>();

        // 城市初始默认值
        UserInfo userInfo = UserInfoHolder.get();
        if (userInfo != null) {
            queryParam.put("userId", userInfo.getUserId());
        }

        try {
            resultData = personnelPermissionsService.getCenterGroup(queryParam);
        } catch (Exception e) {
            logger.error("personnelPermissions", "PersonnelPermissionsController", "getCenterListByCityNo", "", null, "", "根据行政区DistrictNo获取其板块List", e);
        }

        return resultData;
    }
    
    /**
     * desc:获取城市列表
     * 2019年11月5日
     */
    @RequestMapping(value = "/getCityList", method = RequestMethod.GET)
    @ResponseBody
    public ResultData<?> getCityList(HttpServletRequest request) {
    	//返回map
    	Map<String, Object> queryParam = bindParamToMap(request);
    	//返回map
    	ResultData resultData = new ResultData<>();
    	
    	try {
    		resultData = personnelPermissionsService.getCityList(queryParam);
    	} catch (Exception e) {
    		logger.error("personnelPermissions", "PersonnelPermissionsController", "getCityList", "", null, "", "获取城市列表失败", e);
    	}
    	
    	return resultData;
    }
    
  //新增用户页面
    @RequestMapping(value = "addUserPage", method = RequestMethod.GET)
    public String addUserPage(Model model, HttpServletRequest request) {
    	String id=request.getParameter("id");
    	ResultData<UserCenterCityDto> resultData = new ResultData<>();
    	if(id!=null && !"".equals(id)){//查询品牌信息，进入修改页面
            Map<String, Object> reqMap = bindParamToMap(request);
            try {
                resultData = personnelPermissionsService.getUserInfo(reqMap, null);
                model.addAttribute("id",id);
                model.addAttribute("userDto", JSONObject.toJSON(resultData.getReturnData()));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return "basicInformation/personnelPermissions/addUserPage";
    }

  //新增人员
    @ResponseBody
    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public ResultData<?> addUser(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;


        try {
            	if(reqMap.containsKey("centerIds") && StringUtil.isNotEmpty((String)reqMap.get("centerIds"))) {
                	String centerIds = (String)reqMap.get("centerIds");
                	String[] centerIdArr = centerIds.split(",");
                	reqMap.put("centerIds", Arrays.asList(centerIdArr));
                }
            //获取页面显示数据
        	UserCenterCityDto dto = (UserCenterCityDto) MapToEntityConvertUtil.convert(reqMap,UserCenterCityDto.class,"");
            dto.setUserIdCreate(UserInfoHolder.getUserId());
            resultData = personnelPermissionsService.addUser(dto);
        } catch (Exception e) {
        	logger.error("personnelPermissions",
                    "PersonnelPermissionsController",
                    "addUser",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "新增人员信息异常",
                    e);
        }
        return resultData;
    }
    
    //跟新人员信息
    @ResponseBody
    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
    public ResultData<?> updateUser(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
        	UserCenterCityDto dto = (UserCenterCityDto) MapToEntityConvertUtil.convert(reqMap,UserCenterCityDto.class,"");
            resultData = personnelPermissionsService.updateUser(dto);
        } catch (Exception e) {
            logger.error("personnelPermissions",
                    "PersonnelPermissionsController",
                    "updateUser",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "修改人员信息异常",
                    e);
        }
        return resultData;
    }
    
    //删除人员
    @ResponseBody
    @RequestMapping(value = "deleteUser", method = RequestMethod.POST)
    public ResultData<?> deleteUser(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
        	UserCenterCityDto dto = (UserCenterCityDto) MapToEntityConvertUtil.convert(reqMap,UserCenterCityDto.class,"");
            resultData = personnelPermissionsService.deleteUser(dto);
        } catch (Exception e) {
            logger.error("personnelPermissions",
                    "PersonnelPermissionsController",
                    "deleteUser",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "删除人员信息异常",
                    e);
        }
        return resultData;
    }
}
