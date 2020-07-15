package cn.com.eju.pmls.developer.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.helper.SeqNoHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.dto.common.CityDto;
import cn.com.eju.pmls.developer.dto.DeveloperDto;
import cn.com.eju.pmls.developer.service.DeveloperService;

@Controller
@RequestMapping(value = "developer")
public class DeveloperController extends BaseController {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource(name = "developerService")
    private DeveloperService developerService;

    //开发商管理页面
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView developerList() {
        ModelAndView mv = new ModelAndView("developer/developerList");
        List<CityDto> cities = UserInfoHolder.get().getCities();
        mv.addObject("citylist", JSONObject.toJSON(cities));
        //合作类型
        mv.addObject("partnerList", SystemParam.getCodeListByKey(DictionaryConstants.PARTNER));
        return mv;
    }

    //新增开发商页面
    @RequestMapping(value = "addDeveloperPage", method = RequestMethod.GET)
    public String addDeveloperPage(Model model, HttpServletRequest request) {
        String id=request.getParameter("id");
        ResultData<DeveloperDto> resultData = new ResultData<>();
        //合作类型
        model.addAttribute("partnerList", JSONObject.toJSON(SystemParam.getCodeListByKey(DictionaryConstants.PARTNER)));
        try {
            if(id!=null && !"".equals(id)){//查询开发商信息，进入修改页面
                Map<String, Object> reqMap = bindParamToMap(request);
                DeveloperDto dto = (DeveloperDto) MapToEntityConvertUtil.convert(reqMap,DeveloperDto.class,"");
                resultData = developerService.getDeveloperInfo(dto);
                model.addAttribute("id",id);
                model.addAttribute("developerDto", JSONObject.toJSON(resultData.getReturnData()));

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "developer/addDeveloperPage";
    }
    
    //开发商详情页面
    @RequestMapping(value = "developerDetailPage", method = RequestMethod.GET)
    public String developerDetailPage(Model model, HttpServletRequest request) {
        String id=request.getParameter("id");
        ResultData<DeveloperDto> resultData = new ResultData<>();
        try {
            if(id!=null && !"".equals(id)){//查询开发商信息，进入修改页面
                Map<String, Object> reqMap = bindParamToMap(request);
                DeveloperDto dto = (DeveloperDto) MapToEntityConvertUtil.convert(reqMap,DeveloperDto.class,"");
                resultData = developerService.getDeveloperInfo(dto);
                model.addAttribute("id",id);
                model.addAttribute("developerDto", JSONObject.toJSON(resultData.getReturnData()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "developer/developerDetailPage";
    }

    /**
     * 查询开发商管理列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getDeveloperList", method = RequestMethod.POST)
    public ResultData<?> getDeveloperList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        reqMap.put("acCityNo", UserInfoHolder.get().getCityNo());
        try {
            //获取页面显示数据
            resultData = developerService.getDeveloperList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("Developer",
                    "DeveloperController",
                    "getDeveloperList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询开发商管理列表异常",
                    e);
        }

        return resultData;
    }

    //获取开发商信息
    @ResponseBody
    @RequestMapping(value = "getDeveloperInfo", method = RequestMethod.POST)
    public ResultData<?> getDeveloperInfo(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            //获取页面显示数据
            DeveloperDto dto = (DeveloperDto) MapToEntityConvertUtil.convert(reqMap,DeveloperDto.class,"");
            resultData = developerService.getDeveloperInfo(dto);
        } catch (Exception e) {
            logger.error("DeveloperDto",
                    "DeveloperController",
                    "getDeveloperInfo",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询开发商信息异常",
                    e);
        }
        return resultData;
    }
    
    //根据公司名和统一社会信用代码获取开发商信息
    @ResponseBody
    @RequestMapping(value = "getDeveloperInfo2", method = RequestMethod.POST)
    public ResultData<?> getDeveloperInfo2(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            //获取页面显示数据
        	DeveloperDto dto = (DeveloperDto) MapToEntityConvertUtil.convert(reqMap,DeveloperDto.class,"");
            resultData = developerService.getDeveloperInfo2(dto);
        } catch (Exception e) {
        	logger.error("DeveloperDto",
                    "DeveloperController",
                    "getDeveloperInfo",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询开发商信息异常",
                    e);
        }
        return resultData;
    }
    
    //判断开发商是否已存在
    @ResponseBody
    @RequestMapping(value = "getDeveloperCountByName", method = RequestMethod.POST)
    public ResultData<?> getDeveloperCountByName(HttpServletRequest request) {
    	Map<String, Object> reqMap = bindParamToMap(request);
    	ResultData<?> resultData = null;
    	PageInfo pageInfo = getPageInfo(request);
    	try {
    		//获取页面显示数据
    		DeveloperDto dto = (DeveloperDto) MapToEntityConvertUtil.convert(reqMap,DeveloperDto.class,"");
    		resultData = developerService.getDeveloperCountByName(dto);
    	} catch (Exception e) {
    		logger.error("DeveloperDto",
    				"DeveloperController",
    				"getDeveloperCountByName",
    				"input param: reqMap=" + reqMap.toString(),
    				UserInfoHolder.getUserId(),
    				WebUtil.getIPAddress(request),
    				"查询开发商信息异常",
    				e);
    	}
    	return resultData;
    }
    
    //新增开发商
    @ResponseBody
    @RequestMapping(value = "addDeveloper", method = RequestMethod.POST)
    public ResultData<?> addDeveloper(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            DeveloperDto dto = (DeveloperDto) MapToEntityConvertUtil.convert(reqMap,DeveloperDto.class,"");
            String developerCode = SeqNoHelper.getInstance().getSeqNoByTypeCode("TYPE_KF");//开发商编号
            dto.setDeveloperCode(developerCode);
            UserInfo userInfo = UserInfoHolder.get();
            String userId = "";
            if (userInfo != null) {
                userId = userInfo.getUserId()+"";
            }
            dto.setUserIdCreate(userId);
            dto.setAcCityNo(UserInfoHolder.get().getCityNo());
            resultData = developerService.addDeveloper(dto);
        } catch (Exception e) {
            logger.error("DeveloperDto",
                    "DeveloperController",
                    "addDeveloper",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "新增开发商信息异常",
                    e);
        }
        return resultData;
    }
    
    //修改开发商
    @ResponseBody
    @RequestMapping(value = "updateDeveloper", method = RequestMethod.POST)
    public ResultData<?> updateDeveloper(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            DeveloperDto dto = (DeveloperDto) MapToEntityConvertUtil.convert(reqMap,DeveloperDto.class,"");
            UserInfo userInfo = UserInfoHolder.get();
            String userId = "";
            if (userInfo != null) {
                userId = userInfo.getUserId()+"";
            }
            dto.setUserIdUpdate(userId);
            resultData = developerService.updateDeveloper(dto);
        } catch (Exception e) {
            logger.error("DeveloperDto",
                    "DeveloperController",
                    "updateDeveloper",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "修改开发商信息异常",
                    e);
        }
        return resultData;
    }
    
    //删除开发商
    @ResponseBody
    @RequestMapping(value = "deleteDeveloper", method = RequestMethod.POST)
    public ResultData<?> deleteDeveloper(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            DeveloperDto dto = (DeveloperDto) MapToEntityConvertUtil.convert(reqMap,DeveloperDto.class,"");
            UserInfo userInfo = UserInfoHolder.get();
            String userId = "";
            if (userInfo != null) {
                userId = userInfo.getUserId()+"";
            }
            dto.setUserIdUpdate(userId);
            resultData = developerService.deleteDeveloper(dto);
        } catch (Exception e) {
            logger.error("DeveloperDto",
                    "DeveloperController",
                    "deleteDeveloper",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "删除开发商信息异常",
                    e);
        }
        return resultData;
    }
    
    /**
     * desc:新增开发商和城市关系
     * 2019年11月8日
     */
    @ResponseBody
    @RequestMapping(value = "addDeveloperReleaseCity", method = RequestMethod.POST)
    public ResultData<?> addDeveloperReleaseCity(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
        	DeveloperDto dto = (DeveloperDto) MapToEntityConvertUtil.convert(reqMap,DeveloperDto.class,"");
        	UserInfo userInfo = UserInfoHolder.get();
            String userId = "";
            if (userInfo != null) {
                userId = userInfo.getUserId()+"";
            }
            dto.setUserIdCreate(userId);
            dto.setAcCityNo(UserInfoHolder.get().getCityNo());
            resultData = developerService.addDeveloperReleaseCity(dto);
        } catch (Exception e) {
            logger.error("DeveloperDto",
                    "DeveloperController",
                    "addDeveloperReleaseCity",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "新增开发商城市关系表异常",
                    e);
        }
        return resultData;
    }
    
  //根据开发商品牌获取垫佣和大客户
    @ResponseBody
    @RequestMapping(value = "getCustomerAndYjDy", method = RequestMethod.POST)
    public ResultData<?> getCustomerAndYjDy(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
//        	DeveloperDto dto = (DeveloperDto) MapToEntityConvertUtil.convert(reqMap,DeveloperDto.class,"");
            resultData = developerService.getCustomerAndYjDy(reqMap);
        } catch (Exception e) {
        	logger.error("DeveloperDto",
                    "DeveloperController",
                    "getCustomerAndYjDy",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "根据开发商品牌获取垫佣和大客户",
                    e);
        }
        return resultData;
    }
}
