package cn.com.eju.deal.store.controller;

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
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.store.service.StoreReceiveService;
import com.alibaba.fastjson.JSON;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
* @Description: 保证金收款controller
*/
@RestController
@RequestMapping(value = "storeReceive")
public class StoreReceiveController extends BaseController
{
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    private StoreReceiveService storeReceiveService;
    
    /** 
    * @Title: list 
    * @Description: 保证金收款列表页面
    */
	@RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, ModelMap mop,HttpServletResponse response){
        //构建ModelAndView实例，并设置跳转地址
		ModelAndView mv = null ;
		Map<String, Object> map = bindParamToMap(request);
		if(map.containsKey("orderType")){
			Integer orderType = Integer.valueOf(map.get("orderType").toString());
			mop.put("orderTypeFlag", orderType);
			if(orderType == 21401 ){
				mv = new ModelAndView("store/storeReceiveList");
			}
			if(orderType == 21402) {
				mv = new ModelAndView("store/storeAdjustList");
			}
		}else {
			mop.put("orderTypeFlag", 21401);
			mv = new ModelAndView("store/storeReceiveList");
		}
        UserInfo userInfo = UserInfoHolder.get();
        String cityNo = userInfo.getCityNo();
        if (StringUtil.isNotEmpty(cityNo)){
            //区域列表
        	if(map.containsKey("orderType")){
        		Integer orderType = Integer.valueOf(map.get("orderType").toString());
        		//收款
        		if(orderType == 21401 ){
        			cache(request, mop, response, mv, map,ComConstants.STORERECEIVE_LIST_SEARCH);
        		}
        		//调整单
        		if(orderType == 21402 ){
        			cache(request, mop, response, mv, map,ComConstants.STOREADJUST_LIST_SEARCH);
        		}
        	}else{
        		cache(request, mop, response, mv, map,ComConstants.STORERECEIVE_LIST_SEARCH);
        	}
        }
        return mv;
        
    }
	//缓存
	private void cache(HttpServletRequest request, ModelMap mop, HttpServletResponse response, ModelAndView mv,
			Map<String, Object> map,String constants) {
		if (map.containsKey("searchParam") && "1".equals(map.get("searchParam"))) {
			Map<String, Object> searchParamMap = getRememberSearch(request, constants);
			mv.addObject("searchParamMap", JSON.toJSON(searchParamMap));
		} else {
			clearSearch(request, response, constants);
		}
		mop.put("list_search_page",constants);
	}
    
	/**
     * 获取保证金收款列表页
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public ModelAndView getStoreReceiveList(HttpServletRequest request, ModelMap mop,HttpServletResponse response)
    {
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        //返回list
        ResultData<?> resultData = null;
        List<?> contentlist = new ArrayList<>();
        Integer orderType = Integer.valueOf(reqMap.get("orderTypeFlag").toString());
        try {
        	if(orderType == 21401){
        		if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
        			rememberSearch(request, response, ComConstants.STORERECEIVE_LIST_SEARCH, reqMap);
        		}
        	}
        	if(orderType == 21402) {
        		if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
        			rememberSearch(request, response, ComConstants.STOREADJUST_LIST_SEARCH, reqMap);
        		}
        	}
        	reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
            resultData = storeReceiveService.getStoreReceiveList(reqMap,pageInfo);
            if(resultData != null){
                contentlist = (List<?>) resultData.getReturnData();
            }
        }catch (Exception e){
            logger.error("PMLS", "StoreReceiveController", "getStoreReceiveList", reqMap.toString(), null, "", "获取保证金列表页", e);
        }
        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        ModelAndView mv = null;
        if(orderType == 21401){
        	mv = new ModelAndView("store/storeReceiveListCtx");
        }
        if(orderType == 21402){
        	mv = new ModelAndView("store/storeAdjustListCtx");
        }
        mop.addAttribute("userId",UserInfoHolder.getUserId());
        return mv;
    }
    /**
     * 根据id查询保证金详情
     * @param id
     * @param mop
     * @return
     */
    @RequestMapping(value = "/{id}/{companyName}", method = RequestMethod.GET)
    public ModelAndView showdetail(@PathVariable("id") Integer id, @PathVariable("companyName") String companyName,ModelMap mop){
    	ResultData<?> resultData = null;
    	Integer orderType = 0;
        try{
            resultData = storeReceiveService.getbriefById(id);
            if(resultData != null){
            	mop.addAttribute("storeReceiveinfo", resultData.getReturnData());
            }
            Map<String, Object> storeReceiveInfoDto = (Map<String, Object>) resultData.getReturnData();
            if(storeReceiveInfoDto!=null && storeReceiveInfoDto.get("storeReceive")!=null){
            	Map<String, Object> storeReceiveDto = (Map<String, Object>) storeReceiveInfoDto.get("storeReceive");
            	if(storeReceiveDto.containsKey("orderType")){
            		orderType = Integer.valueOf(storeReceiveDto.get("orderType").toString());
            		mop.addAttribute("orderTypeFlag", orderType);
            	}
            }
        }catch (Exception e){
            logger.error("Store", "StoreReceiveController", "showdetail", "", UserInfoHolder.getUserId(), "", "查看保证金收款详情失败", e);
        }
        mop.addAttribute("receiveId", id);
        //返回视图
        ModelAndView mv = null;
        if(orderType == 21402){
        	mop.addAttribute("companyName", companyName);
        	mv = new ModelAndView("store/storeAdjustDetail");
        }else {
        	mv = new ModelAndView("store/storeReceiveDetail");
        }
        return mv;
    }
    /** 
     * @Title: update 
     * @Description: 保证金信息更新
     * @return
     * @     
     */
     @RequestMapping(value = "/update", method = RequestMethod.POST)
     public ReturnView<?, ?> update(HttpServletRequest request) {
         //返回map
         Map<String, Object> rspMap = new HashMap<String, Object>();
         //获取map
         Map<String, Object> reqMap = bindParamToMap(request);
         reqMap.remove("_method");
         try
         {
             //更新
        	 storeReceiveService.update(reqMap);
         }
         catch (Exception e)
         {
             logger.error("Store", "StoreReceiveController", "update", "", UserInfoHolder.getUserId(), "", "更新保证金失败", e);
             
             rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
             
             rspMap.put(Constant.RETURN_MSG_KEY, "更新保证金失败");
         }
         return getOperateJSONView(rspMap);
     }

    @RequestMapping(value = "/reverse/{receiveId}", method = RequestMethod.GET)
    public ResultData<?> reverse(@PathVariable("receiveId") Integer receiveId,ModelMap mop){

         ResultData<?> resultData = new ResultData<>();

         Map<String,Object> map = new HashMap<>();
         map.put("receiveId",receiveId);
         map.put("userId",UserInfoHolder.getUserId());

         try {
             resultData = storeReceiveService.reverse(map);
         }catch (Exception e){
             resultData.setFail();
             logger.error("storeReceive", "StoreReceiveController", "reverse", ""+receiveId, UserInfoHolder.getUserId(), "", "保证金红冲失败", e);
         }

         return resultData;
    }
}
