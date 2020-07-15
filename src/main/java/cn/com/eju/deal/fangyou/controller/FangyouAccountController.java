package cn.com.eju.deal.fangyou.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.company.service.CompanyService;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.fangyou.service.FangyouAccountLogService;
import cn.com.eju.deal.fangyou.service.FangyouAccountService;
import cn.com.eju.deal.store.service.StoreService;

/** 
* @ClassName: FangYouAccountController 
* @Description: 房友账号绑定Controller
* @author cning
* @date 2017年07月05日  
*/
@Controller
@RequestMapping(value = "fangyouAccount")
public class FangyouAccountController extends BaseController{
	private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "fangyouAccountService")
    private FangyouAccountService fangyouAccountService;
    @Resource
    private FangyouAccountLogService fangyouAccountLogService;

    @Resource(name = "storeService")
    private StoreService storeService;
    
    @Resource(name = "companyService")
    private CompanyService companyService;

    /** 
     * 房友账号绑定页面
     * @return
     */
    @RequestMapping(value = "/account/{storeId}", method = RequestMethod.GET)
    public ModelAndView toStoreTrailList(@PathVariable("storeId") Integer storeId, ModelMap mop)
    {
    	ResultData<?> resultData;
		try {
			resultData = storeService.getById(storeId);
			 //存放到mop中
	        mop.addAttribute("store", resultData.getReturnData());
	        
	        //门店房友关联表
	        resultData = fangyouAccountService.getByStoreId(storeId);
        	mop.addAttribute("content", resultData.getReturnData());
		} 
	    catch (Exception e)
	    {
	        logger.error("Store", "FangyouAccountController", "toStoreTrailList", "", UserInfoHolder.getUserId(), "", "房友账号绑定失败", e);  
	    }

        //返回视图
        ModelAndView mv = new ModelAndView("fangyou/fangYouAccountList");
        
        return mv;
    }
    
    /** 
    * @Title: querylist 
    * @Description: 房友账号绑定列表
    * @param request
    * @param mop
    * @return
    * @throws Exception     
    */
    @RequestMapping(value = "q/{storeId}", method = RequestMethod.GET)
    public String querylist(HttpServletRequest request, ModelMap mop)
    {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        
       //需要数据权限
        reqMap.put(Constant.DATA_AUTH_KEY, true);
        
        PageInfo pageInfo = getPageInfo(request);
        
        try
        {
        	//获取页面显示数据
        	ResultData<?> reback = fangyouAccountService.queryList(reqMap, pageInfo);
        	List<?> contentlist = (List<?>)reback.getReturnData();
        	mop.addAttribute("contentlist", contentlist);
        	
        }
        catch (Exception e)
        {
            logger.error("follow", "FollowControler", "querylist", "", null, "", " 房友绑定列表失败！", e);
        }
        
        return "fangyou/fangYouAccountListCtx";
    }
    
    
    /** 
    * @Title: toBundling 
    * @Description: 绑定房友账号页面
    * @return
    * @throws Exception     
    */
    @RequestMapping(value = "bundling/{storeId}/{storeNo}/{name}/{operType}", method = RequestMethod.GET)
    public String toBundling(@PathVariable("storeId") int  storeId, 
    		@PathVariable("storeNo") String storeNo,
    		@PathVariable("name") String name,
    		@PathVariable("operType") int operType, ModelMap mop)
    {
        mop.addAttribute("storeId", storeId);
        mop.addAttribute("storeNo", storeNo);
        mop.addAttribute("name", name);
        mop.addAttribute("operType", operType);
        return "fangyou/fangYouBundling";
    }
    
    /** 
     * @Title: 房友账号绑定
     * @Description: 
     * @return
     * @throws Exception     
     */
     @RequestMapping(value = "bundling", method = RequestMethod.POST)
     @ResponseBody
     public ReturnView<?, ?>  addBundling(HttpServletRequest request,  ModelMap mop)
     {
    	//获取请求参数
         Map<String, Object> reqMap = bindParamToMap(request);
         
        //返回map
         Map<String, Object> rspMap = new HashMap<String, Object>();

         try
         {
        	 String fangyouNo = reqMap.get("fangyouNo").toString().trim();
        	 
        	 //判断房友账号(公司NO)是否存在
        	 ResultData<?> resultCompanyData = companyService.getByNo(fangyouNo);
        	 Map<String, Object> company = (Map<String, Object>)resultCompanyData.getReturnData();
        	 if(null == company)
        	 {
        		 rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);            
	             rspMap.put(Constant.RETURN_MSG_KEY, "不是有效的房友账号编码，请重新输入！");
	             return getSearchJSONView(rspMap);
        	 }
        	 
        	 if(!"1".equals(company.get("fangyouOpenStatus")))
        	 {
        		 rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);            
	             rspMap.put(Constant.RETURN_MSG_KEY, "该公司并未开通房友账号，不能进行门店绑定！");
	             return getSearchJSONView(rspMap);
        	 }
        	 
             ResultData<?> resultData = fangyouAccountService.addBundling(reqMap);    
             if(!"200".equals(resultData.getReturnCode())){
	        	 rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
	             rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
	             return getSearchJSONView(rspMap);
             }
         }
         catch (Exception e)
         {
             logger.error("follow", "FollowControler", "create", "", null, "", " 房友账号绑定失败！", e);
         }

         return getOperateJSONView(rspMap);
     }
     /** 
      * 根据门店id查询其房友账号变更日志
      * @return
      */
     @RequestMapping(value = "/toFangyouAccountView/{storeId}", method = RequestMethod.GET)
     public ModelAndView toFangyouAccountView(@PathVariable("storeId") Integer storeId, ModelMap mop){
     	ResultData<?> resultData;
 		try {
 			resultData = storeService.getbriefById(storeId);
 			 //存放到mop中
 	        mop.addAttribute("store", resultData.getReturnData());
 	        mop.addAttribute("storeId", storeId);
 	        //根据门店id查询其房友账号变更日志
 	        resultData = fangyouAccountLogService.getFangyouAccountList(storeId);
         	mop.addAttribute("Logcontent", resultData.getReturnData());
 		} catch (Exception e){
 	        logger.error("Store", "FangyouAccountController", "toStoreTrailList", "", UserInfoHolder.getUserId(), "", "房友账号绑定失败", e);  
 	    }
         //返回视图
         ModelAndView mv = new ModelAndView("fangyou/fangYouAccountView");
         
         return mv;
     }
     /** 
      * @Title: editFyAcountMode 
      * @Description: 编辑房友账号弹窗
      */
     @RequestMapping(value = "/editFyAcountMode", method = RequestMethod.GET)
     @ResponseBody
     public ModelAndView editFyAcountMode(HttpServletRequest request,ModelMap mop){
    	 //请求map
    	 Map<String, Object> reqMap = bindParamToMap(request);
    	 //返回视图
    	 mop.put("storeId", reqMap.get("storeId").toString());
    	 mop.put("oldFyAcount", reqMap.get("fyAcount").toString());
    	 mop.put("storeNo", reqMap.get("storeNo").toString());
    	 mop.put("userCode", UserInfoHolder.get().getUserCode());
    	 mop.put("userName", UserInfoHolder.get().getUserName());
    	 mop.put("userIdCreate", UserInfoHolder.get().getUserId());
    	 ModelAndView mv = new ModelAndView( "fangyou/editFyAcountModePopup");
    	 return mv;
     } 
     /** 
      * @Title: changeFyAcount 
      * @Description: 修改房友账号
      */
     @RequestMapping(value = "/changeFyAcount", method = RequestMethod.POST)
     @ResponseBody
     public ReturnView<?, ?> changeFyAcount(HttpServletRequest request, ModelMap modelMap){
    	 //返回map
    	 Map<String, Object> rspMap = new HashMap<String, Object>();
    	 //请求map
    	 Map<String, Object> reqMap = bindParamToMap(request);
    	 try{
    		 fangyouAccountLogService.changeFyAcount(reqMap);
    	 } catch (Exception e){
    		 logger.error("Store", "FangyouAccountController", "changeFyAcount", "", null, "", "编辑房友账号失败！", e);
    		 rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
    		 rspMap.put(Constant.RETURN_MSG_KEY, "编辑房友账号失败！");
    		 return getOperateJSONView(rspMap);
    	 }
    	 rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
    	 return getOperateJSONView(rspMap);
     } 
    
}
