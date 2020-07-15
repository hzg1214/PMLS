package cn.com.eju.pmls.estate.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.pmls.estate.dto.LnkYjPlanDto;
import cn.com.eju.pmls.estate.service.PmlsEstatePlanService;
import cn.com.eju.pmls.estate.service.PmlsEstateService;
import cn.com.eju.pmls.scene.service.SceneTradeService;


/**
 * Controller层
 */
@Controller
@RequestMapping(value = "pmlsEstatePlan")
public class PmlsEstatePlanController extends BaseController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "pmlsEstateService")
    private PmlsEstateService pmlsEstateService;

    @Resource(name = "pmlsEstatePlanService")
    private PmlsEstatePlanService pmlsEstatePlanService;


    @Autowired
    SceneTradeService sceneTradeService;
    

    @RequestMapping(value = "/yjPlanAdd/{projectNo}", method = RequestMethod.GET)
    public String yjPlanAdd(@PathVariable("projectNo") String projectNo, ModelMap mop) {
        //返回视图
        mop.put("projectNo", projectNo);
        return "yjPlan/yjPlanAdd";
    }


    /**
     * 查询
     */
    @RequestMapping(value = "querylnkYjWyInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> querylnkYjWyInfo(HttpServletRequest request, ModelMap mop) {
        //获取页面显示数据
        ResultData<?> reback = null;
        try {
            reback = sceneTradeService.querylnkYjWyInfo();
        } catch (Exception e) {
            logger.error("pmlsEstatePlan", "PmlsEstatePlanController", "querylnkYjWyInfo", null, 0, "", "", e);
        }
        return reback;
    }

    @RequestMapping(value = "/yjPlanEdit/{planId}", method = RequestMethod.GET)
    public String yjPlanEdit(@PathVariable("planId") Integer planId, ModelMap mop) {
        //返回视图
        mop.put("planId", planId);
        //获取页面显示数据
        ResultData<?> reback = null;
        try {
            reback = pmlsEstatePlanService.getLnkYjPlanDto(planId);
            if(reback != null){
                mop.addAttribute("lnkYjPlanDto", reback.getReturnData());
                mop.addAttribute("lnkYjPlanDtoJson", JSONObject.toJSON(reback.getReturnData()));
            }

        } catch (Exception e) {
            logger.error("pmlsEstatePlan", "PmlsEstatePlanController", "yjPlanEdit", null, 0, "", "", e);
        }
        return "yjPlan/yjPlanEdit";
    }


    /**
     * desc:保存/编辑
     * 2020年3月2日
     */
    @RequestMapping(value = "saveYjPlanInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> saveYjPlanInfo(HttpServletRequest request, @RequestBody  LnkYjPlanDto lnkYjPlanDto) {
    	//获取页面显示数据
    	ResultData<?> reback = null;
    	UserInfo userInfo = UserInfoHolder.get();
    	try {
    		lnkYjPlanDto.setUserIdCreate(userInfo.getUserId());
    		lnkYjPlanDto.setUserIdUpdate(userInfo.getUserId());
    		reback = pmlsEstatePlanService.saveYjPlanInfo(lnkYjPlanDto);
    	} catch (Exception e) {
    		logger.error("pmlsEstatePlan", "PmlsEstatePlanController", "saveYjPlanInfo", null, 0, "", "", e);
    	}
    	return reback;
    }
    
    /**
     * desc:佣金方案维护-选择经纪公司
     * 2020年2月27日
     */
    @RequestMapping(value = "/selCompany/{projectNo}", method = RequestMethod.GET)
    public String selCompany(@PathVariable("projectNo") String projectNo, HttpServletRequest request, ModelMap mop) {
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("projectNo", projectNo);
        mop.addAllAttributes(reqMap);
        return "yjPlan/selCompany";
    }

    /**
     * desc:佣金方案维护-经纪公司列表
     * 2020年2月27日
     */
    @ResponseBody
    @RequestMapping(value = "getCompanyList", method = RequestMethod.POST)
    public ResultData getCompanyList(HttpServletRequest request, ModelMap mop) {
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        //返回list
        ResultData<?> resultData = null;
        List<?> contentlist = new ArrayList<>();
        try {
            reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
            resultData = pmlsEstatePlanService.getCompanyList(reqMap, pageInfo);

        } catch (Exception e) {
            logger.error("pmlsEstatePlan", "PmlsEstatePlanController", "getCompanyList", reqMap.toString(), null, "", "佣金方案-经纪公司列表失败", e);
        }
        return resultData;
    }
    
    /**
     * desc:启用/禁用佣金方案
     * 2020年3月2日
     */
    @RequestMapping(value = "/toIsEnable/{id}/{projectNo}/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> toIsEnable(HttpServletRequest request, @PathVariable("id") int id, @PathVariable("projectNo") String projectNo, @PathVariable("type") int type) {
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
        reqMap.put("projectNo", projectNo);
        try {
        	pmlsEstatePlanService.toIsEnable(reqMap);
        } catch (Exception e) {
            logger.error("pmlsEstatePlan", "PmlsEstatePlanController", "toIsEnable", "", null, "", "禁用佣金方案失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "禁用佣金方案失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        if (type == 0) {
            rspMap.put(Constant.RETURN_MSG_KEY, "禁用佣金方案成功！");
        } else {
            rspMap.put(Constant.RETURN_MSG_KEY, "启用佣金方案成功！");
        }
        return getOperateJSONView(rspMap);
    }
    
    /**
     * desc:佣金收入列表
     * 2020年3月4日
     */
	@RequestMapping(value = "queryYjsrList/{projectNo}", method = RequestMethod.POST)
	@ResponseBody
	public ResultData<?> queryYjsrList(@PathVariable("projectNo") String projectNo,HttpServletRequest request, ModelMap mop) {
		//获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = new ResultData<>();
        PageInfo pageInfo = getPageInfo(request);
        try
        {
        	reqMap.put("projectNo", projectNo);
        	reqMap.put("planType", "28701");
            resultData = pmlsEstatePlanService.getYjPlanByProjectNo(reqMap, pageInfo);
        }
        
        catch (Exception e)
        {
            logger.error("pmlsEstatePlan", "PmlsEstatePlanController", "queryYjsrList", "", null, "", "", e);
        }
		return resultData;
	}
	
	/**
	 * desc:佣金返佣列表
	 * 2020年3月4日
	 */
	@RequestMapping(value = "queryYjfyList/{projectNo}", method = RequestMethod.POST)
	@ResponseBody
	public ResultData<?> queryYjfyList(@PathVariable("projectNo") String projectNo,HttpServletRequest request, ModelMap mop) {
		//获取请求参数
		Map<String, Object> reqMap = bindParamToMap(request);
		ResultData<?> resultData = new ResultData<>();
		PageInfo pageInfo = getPageInfo(request);
		try
		{
			reqMap.put("projectNo", projectNo);
			reqMap.put("planType", "28702");
			resultData = pmlsEstatePlanService.getYjPlanByProjectNo(reqMap, pageInfo);
		}
		
		catch (Exception e)
		{
			logger.error("pmlsEstatePlan", "PmlsEstatePlanController", "queryYjfyList", "", null, "", "", e);
		}
		return resultData;
	}
	
	/**
	 * desc:佣金返佣日志列表
	 * 2020年3月6日
	 */
	@RequestMapping(value = "queryChangeLogList/{estateId}", method = RequestMethod.POST)
	@ResponseBody
	public ResultData<?> queryChangeLogList(@PathVariable("estateId") Integer estateId,HttpServletRequest request, ModelMap mop) {
		//获取请求参数
		Map<String, Object> reqMap = bindParamToMap(request);
		ResultData<?> resultData = new ResultData<>();
		PageInfo pageInfo = getPageInfo(request);
		try
		{
			reqMap.put("estateId2", estateId);
			resultData = pmlsEstatePlanService.queryChangeLogList(reqMap, pageInfo);
		}
		
		catch (Exception e)
		{
			logger.error("pmlsEstatePlan", "PmlsEstatePlanController", "queryChangeLogList", "", null, "", "", e);
		}
		return resultData;
	}
	
	/**
	 * desc:佣金返佣垫佣列表
	 * 2020年5月19日
	 */
	@RequestMapping(value = "queryMattressControlRuleList/{projectNo}", method = RequestMethod.POST)
	@ResponseBody
	public ResultData<?> queryMattressControlRuleList(@PathVariable("projectNo") String projectNo,HttpServletRequest request, ModelMap mop) {
		//获取请求参数
		Map<String, Object> reqMap = bindParamToMap(request);
		logger.info("pmlsEstatePlan,PmlsEstatePlanController,queryMattressControlRuleList,项目-项目详情-佣金返佣垫佣列表，入参:"+reqMap);
		ResultData<?> resultData = new ResultData<>();
		PageInfo pageInfo = getPageInfo(request);
		try
		{
			reqMap.put("projectNo", projectNo);
			resultData = pmlsEstatePlanService.queryMattressControlRuleList(reqMap, pageInfo);
			logger.info("pmlsEstatePlan,PmlsEstatePlanController,queryMattressControlRuleList,项目-项目详情-佣金返佣垫佣列表，返回:"+resultData);
		}
		
		catch (Exception e)
		{
			logger.error("pmlsEstatePlan", "PmlsEstatePlanController", "queryMattressControlRuleList", "", null, "", "", e);
		}
		return resultData;
	}
	
}
