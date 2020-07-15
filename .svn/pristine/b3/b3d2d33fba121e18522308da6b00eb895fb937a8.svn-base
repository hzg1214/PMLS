package cn.com.eju.deal.store.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.store.service.StoreAuthCheckService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 授牌验收
 */
@Controller
@RequestMapping(value = "storeAuthCheck")
public class StoreAuthCheckController extends BaseController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private StoreAuthCheckService storeAuthCheckService;
    
    /** 
     * @Title: list 
     * @Description: 授牌验收初始化
     */
 	@RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, ModelMap mop,HttpServletResponse response){
         //构建ModelAndView实例，并设置跳转地址
         ModelAndView mv = new ModelAndView("store/storeAuthCheckList");
         UserInfo userInfo = UserInfoHolder.get();
         String cityNo = userInfo.getCityNo();
         if (StringUtil.isNotEmpty(cityNo)){
 	        Map<String, Object> map = bindParamToMap(request);
 	        if (map.containsKey("searchParam") && "1".equals(map.get("searchParam"))) {
 	            Map<String, Object> searchParamMap = getRememberSearch(request, ComConstants.STORE_AUTH_CHECK_LIST_SEARCH);
 	            mv.addObject("searchParamMap", JSON.toJSON(searchParamMap));
 	        } else {
 	            clearSearch(request, response, ComConstants.STORE_AUTH_CHECK_LIST_SEARCH);
 	        }
 	        mop.put("list_search_page", ComConstants.STORE_AUTH_CHECK_LIST_SEARCH);
         }
         return mv;
         
     }
    /**
     * 获取授牌验收申请列表页
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "getStoreAuthCheckList", method = RequestMethod.GET)
    public ModelAndView getStoreAuthCheckList(HttpServletRequest request, ModelMap mop,HttpServletResponse response){
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        //返回list
        ResultData<?> resultData = null;
        List<?> contentlist = new ArrayList<>();
        try {
        	UserInfo userInfo = UserInfoHolder.get();
        	reqMap.put("userId",userInfo.getUserId());
        	//reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
        	if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.STORE_AUTH_CHECK_LIST_SEARCH, reqMap);
            }
            resultData = storeAuthCheckService.getStoreAuthCheckList(reqMap,pageInfo);
            if(resultData != null){
                contentlist = (List<?>) resultData.getReturnData();
            }
        }catch (Exception e){
            logger.error("storeAuthCheck", "StoreAuthCheckController", "getStoreAuthCheckList", reqMap.toString(), null, "", "获取授牌验收申请列表页", e);
        }
        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        
        ModelAndView mv = new ModelAndView("store/storeAuthCheckListCtx");
        return mv;
    }
    
    /**
     * 根据id授牌验收详情
     * @param id
     * @param mop
     * @return
     */
    @RequestMapping(value = "getStoreAuthCheckInfoById/{id}", method = RequestMethod.GET)
    public ModelAndView getStoreAuthCheckInfoById(@PathVariable("id") Integer id, ModelMap mop){
    	ResultData<?> resultData = null;
        try{
            resultData = storeAuthCheckService.getStoreAuthCheckInfoById(id);
            if(resultData != null){
            	mop.addAttribute("storeAuthCheckInfo", resultData.getReturnData());
            }
        }catch (Exception e){
            logger.error("storeAuthCheck", "StoreAuthCheckController", "getStoreAuthCheckInfoById", "", UserInfoHolder.getUserId(), "", "查看授牌验收详情失败", e);
        }
        //返回视图
        ModelAndView mv = new ModelAndView("store/storeAuthCheckDetail");
        return mv;
    }
    /**
     * 授牌验收申请审核通过
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/auditPass",method = RequestMethod.POST)
    public ResultData<?> auditPass(HttpServletRequest request){
        ResultData<?> resultData = new ResultData<>();
        // 获取map
     	Map<String, Object> reqMap = bindParamToMap(request);
     	reqMap.put("userIdCreate", UserInfoHolder.get().getUserId());
     	reqMap.put("passFlag", "pass");
        try{
           resultData = storeAuthCheckService.auditPass(reqMap);
        }catch (Exception e){
            resultData.setFail("审核授牌验收申请失败");
            logger.error("storeAuthCheck", "StoreAuthCheckController", "auditPass", "",
                    UserInfoHolder.getUserId(), "", "审核授牌验收申请失败！", e);
        }
        return resultData;
    }
    //审核不通过页面
    @RequestMapping(value = "toAuditReturn/{id}/{storeId}", method = RequestMethod.GET)
    public String toDeposit(@PathVariable("id") Integer id,
    		@PathVariable("storeId") Integer storeId, ModelMap mop) {
        mop.put("id",id);
        mop.put("storeId",storeId);
        return "store/storeAuthCheckAuditReturn";
    }
    /**
     * 授牌验收申请审核不通过
     */
    @ResponseBody
    @RequestMapping(value = "/auditReturn",method = RequestMethod.POST)
    public ResultData<?> auditReturn(HttpServletRequest request){
    	ResultData<?> resultData = new ResultData<>();
    	// 获取map
    	Map<String, Object> reqMap = bindParamToMap(request);
    	reqMap.put("userIdCreate", UserInfoHolder.get().getUserId());
    	reqMap.put("passFlag", "noPass");
    	try{
    		resultData = storeAuthCheckService.auditPass(reqMap);
    	}catch (Exception e){
    		resultData.setFail("审核授牌验收申请不通过失败");
    		logger.error("storeAuthCheck", "StoreAuthCheckController", "auditPass", "",
    				UserInfoHolder.getUserId(), "", "审核授牌验收申请不通过失败！", e);
    	}
    	return resultData;
    }
  
}
