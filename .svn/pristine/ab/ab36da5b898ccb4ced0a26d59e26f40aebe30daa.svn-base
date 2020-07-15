package cn.com.eju.deal.gpContract.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.helper.SeqNoHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.gpContract.GpContractChangeDto;
import cn.com.eju.deal.dto.gpContract.GpContractDto;
import cn.com.eju.deal.gpContract.service.GpContractChangeService;
import cn.com.eju.deal.oa.service.OaOperatorService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公盘合同终止
 */
@Controller
@RequestMapping(value = "gpContractChange")
public class GpContractChangeController extends BaseController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private GpContractChangeService gpContractChangeService;
    @Resource(name = "oaOperatorService")
    private OaOperatorService oaOperatorService;
    
    /** 
     * @Title: toAddGpContractChange 
     * @Description: 调整到公盘合同终止新增页面
     */
    @RequestMapping(value = "/toAddGpContractChange/{gpContractId}/{companyName}/{gpContractNo}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView toAddGpContractChange(@PathVariable("gpContractId") Integer gpContractId,
    		@PathVariable("companyName") String companyName,
    		@PathVariable("gpContractNo") String gpContractNo,
    		ModelMap mop){
   	 	//请求map
		 //Map<String, Object> reqMap = bindParamToMap(request);
		 //操作人
		 mop.put("userCode", UserInfoHolder.get().getUserCode());
		 mop.put("userName", UserInfoHolder.get().getUserName());
		 mop.put("userIdCreate", UserInfoHolder.get().getUserId());
		 //返回页面参数
		 mop.put("gpContractId", gpContractId);
		 mop.put("companyName", companyName);
		 mop.put("gpContractNo", gpContractNo);
		 /*
		 mop.put("companyId", companyId);
		 mop.put("gpAgreementNo", gpAgreementNo);*/
		 
		 // 获取页面上门店列表的数据
		 List<?> storeList = new ArrayList<>();
		 try {
			// 根据合同ID查询门店信息
			Integer id = 1;
			ResultData<?> reback = gpContractChangeService.queryStoreInfoOfGpContract(id,gpContractId,"create");
			// 页面数据
			storeList = (List<?>) reback.getReturnData();
		 } catch (Exception e) {
			logger.error("contract", "ContractChangeController", "toAdd", "",
					UserInfoHolder.getUserId(), "", "门店信息查询失败！", e);
		 }
		 // 存放到mop中
		 mop.addAttribute("storeList", storeList);
		 mop.put("stopTypeList", SystemParam.getCodeListByKey(DictionaryConstants.GPCONTRACTSTOP_TYPE));
		 ModelAndView mv = new ModelAndView( "gpContract/gpContractChangeAdd");
		 return mv;
    } 
    /** 
     * @Title: toAddGpContractChange 
     * @Description: 调整到公盘合同终止编辑页面
     */
    @RequestMapping(value = "/toEidtGpContractChange/{id}/{gpContractId}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView toEidtGpContractChange(@PathVariable("id") Integer id,
    		@PathVariable("gpContractId") Integer gpContractId,ModelMap mop){
    	//操作人
    	mop.put("userCode", UserInfoHolder.get().getUserCode());
    	mop.put("userName", UserInfoHolder.get().getUserName());
    	mop.put("userIdCreate", UserInfoHolder.get().getUserId());
    	//返回页面参数
    	mop.put("gpContractId", gpContractId);
    	
    	 // 获取页面上门店列表的数据
		List<?> storeList = new ArrayList<>();
		try {
			// 根据合同ID查询门店信息
			ResultData<?> reback = gpContractChangeService.queryStoreInfoOfGpContract(id,gpContractId,"update");
			// 页面数据
			storeList = (List<?>) reback.getReturnData();
		} catch (Exception e) {
			logger.error("contract", "ContractChangeController", "toAdd", "",
					UserInfoHolder.getUserId(), "", "门店信息查询失败！", e);
		}
    	ResultData<?> resultData = null;
        try{
            resultData = gpContractChangeService.getGpContractStopInfoById(id);
            if(resultData != null){
            	mop.addAttribute("gpContractChangeInfo", resultData.getReturnData());
            }
        }catch (Exception e){
            logger.error("gpContractChange", "GpContractChangeController", "toEidtGpContractChange", "", UserInfoHolder.getUserId(), "", "查看公盘合同终止失败", e);
        }
        mop.addAttribute("storeList", storeList);
    	mop.put("stopTypeList", SystemParam.getCodeListByKey(DictionaryConstants.GPCONTRACTSTOP_TYPE));
    	ModelAndView mv = new ModelAndView( "gpContract/gpContractChangeEdit");
    	return mv;
    } 
    /**
     * 保存公盘合同终止
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveGpContractChange",method = RequestMethod.POST)
    public ResultData<?> saveContractChange(HttpServletRequest request){
        ResultData<?> resultData = new ResultData<>();
        // 获取map
     	Map<String, Object> reqMap = bindParamToMap(request);
     	reqMap.put("userIdCreate", UserInfoHolder.get().getUserId());
     	//判断更新或保存
        if(!reqMap.containsKey("id")){
            String gpContractStopNo = SeqNoHelper.getInstance().getSeqNoByTypeCode("TYPE_GPCONTRACTSTOP");
            reqMap.put("gpContractStopNo", gpContractStopNo);
            reqMap.put("type", "create");
        }else {
        	reqMap.put("type", "update");
        }
        try{
           resultData = gpContractChangeService.saveGpContractChange(reqMap);
        }catch (Exception e){
            resultData.setFail("保存合同终止申请失败");
            logger.error("gpContractChange", "GpContractChangeController", "saveGpContractChange", "",
                    UserInfoHolder.getUserId(), "", "保存合同终止申请失败！", e);
        }

        return resultData;
    }
    /** 
     * @Title: list 
     * @Description: 公盘合同终止初始化
     */
 	@RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, ModelMap mop,HttpServletResponse response){
         //构建ModelAndView实例，并设置跳转地址
         ModelAndView mv = new ModelAndView("gpContract/gpContractStopList");
         UserInfo userInfo = UserInfoHolder.get();
         String cityNo = userInfo.getCityNo();
         if (StringUtil.isNotEmpty(cityNo)){
 	        Map<String, Object> map = bindParamToMap(request);
 	        if (map.containsKey("searchParam") && "1".equals(map.get("searchParam"))) {
 	            Map<String, Object> searchParamMap = getRememberSearch(request, ComConstants.GPCONTRACTSTOP_LIST_SEARCH);
 	            mv.addObject("searchParamMap", JSON.toJSON(searchParamMap));
 	        } else {
 	            clearSearch(request, response, ComConstants.GPCONTRACTSTOP_LIST_SEARCH);
 	        }
 	        mop.put("list_search_page", ComConstants.GPCONTRACTSTOP_LIST_SEARCH);
         }
         return mv;
         
     }
 	/**
     * 获取联动框架合同列表页
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "getGpContractStopList", method = RequestMethod.GET)
    public ModelAndView getGpContractStopList(HttpServletRequest request, ModelMap mop,HttpServletResponse response){
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        //返回list
        ResultData<?> resultData = null;
        List<?> contentlist = new ArrayList<>();
        try {
        	if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.GPCONTRACTSTOP_LIST_SEARCH, reqMap);
            }
        	reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
            resultData = gpContractChangeService.getGpContractStopList(reqMap,pageInfo);
            if(resultData != null){
                contentlist = (List<?>) resultData.getReturnData();
            }
        }catch (Exception e){
            logger.error("gpContractChange", "GpContractChangeController", "getGpContractStopList", reqMap.toString(), null, "", "获取联动框架合同列表页失败", e);
        }
        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        
        //查询是否是经办人
        ResultData<?> backResult = new ResultData<>();
        try {
            backResult = oaOperatorService.getByUserCode(UserInfoHolder.get().getUserCode());
        }
        catch (Exception e){
            logger.error("gpContractChange","GpContractChangeController","getGpContractStopList","",UserInfoHolder.getUserId(),
                    "","合同查询, 查询经办人失败", e);
        }
        //存放到mop中
        mop.addAttribute("oaOperator", backResult.getReturnData());
        
        ModelAndView mv = new ModelAndView("gpContract/gpContractStopListCtx");
        return mv;
    }
    /**
     * 根据id查询公盘合同终止详情
     * @param id
     * @param mop
     * @return
     */
    @RequestMapping(value = "toView/{id}", method = RequestMethod.GET)
    public ModelAndView toView(@PathVariable("id") Integer id, ModelMap mop){
    	ResultData<?> resultData = null;
        try{
            resultData = gpContractChangeService.getGpContractStopInfoById(id);
            if(resultData != null){
            	mop.addAttribute("gpContractChangeInfo", resultData.getReturnData());
            }
        }catch (Exception e){
            logger.error("gpContractChange", "GpContractChangeController", "toView", "", UserInfoHolder.getUserId(), "", "查看公盘合同终止失败", e);
        }
        //返回视图
        ModelAndView mv = new ModelAndView("gpContract/gpContractChangeDetail");
        return mv;
    }
    /** 
     * @Title: listToSubmitOa
     * @Description: 提交Oa
     * @return
     * @     
     */
     @RequestMapping(value = "/toOaGPStopSubmit", method = RequestMethod.GET)
     @ResponseBody
     public String toOaGPStopSubmit(HttpServletRequest request) {
         //返回map
         Map<String, Object> rspMap = new HashMap<String, Object>();
         //获取map
         Map<String, Object> reqMap = bindParamToMap(request);
         reqMap.remove("_method");
         reqMap.put("userIdCreate", UserInfoHolder.getUserId());
	     reqMap.put("userCode", UserInfoHolder.get().getUserCode());
	     reqMap.put("userName", UserInfoHolder.get().getUserName());
	     reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
	     reqMap.put("cityName", UserInfoHolder.get().getCityName());
         ResultData toOaGPStopSubmit = null;
         try {
        	  toOaGPStopSubmit = gpContractChangeService.gpContractStopSubmitOa(reqMap);
	       	  if(ReturnCode.FAILURE.equals(toOaGPStopSubmit.getReturnCode())) {
	       		  rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
	       		  rspMap.put(Constant.RETURN_MSG_KEY, toOaGPStopSubmit.getReturnMsg());
	       	  }
         } catch (Exception e) {
             logger.error("gpContractChange", "GpContractChangeController", "toOaGPStopSubmit", "", UserInfoHolder.getUserId(), "", "提交OA失败", e);
         }
         return toOaGPStopSubmit.toString();
     }
     /**
      * 公盘合同作废
      * @param id
      * @return
      */
     @RequestMapping(value = "/cancel", method = RequestMethod.GET)
     @ResponseBody
     public ReturnView<?, ?> cancel(HttpServletRequest request){
         //返回map
         Map<String, Object> rspMap = new HashMap<String, Object>();
         //获取map
         Map<String, Object> reqMap = bindParamToMap(request);
         reqMap.put("userIdUpt", UserInfoHolder.getUserId());
         reqMap.put("dateUpt", new Date());
         //reqMap.put("delFlag", 1);
         //作废状态
         int GPCONTRACT_STATUS_CANCEL = 4;
         int GPCONTRACTStORE_STATUS_CANCEL = 1;
         reqMap.put("approveState", GPCONTRACT_STATUS_CANCEL);
         reqMap.put("cancelStatus", GPCONTRACTStORE_STATUS_CANCEL);
         try{
        	 gpContractChangeService.updateStatus(reqMap);
         }
         catch (Exception e) {
             rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
             rspMap.put(Constant.RETURN_MSG_KEY, "更新公盘合同终止状态失败");
             logger.error("gpContractChange", "GpContractChangeController", "updateStatus", "", UserInfoHolder.getUserId(), "", "", e);
             return getOperateJSONView(rspMap);
         }
         return getOperateJSONView(rspMap);
     }

    /**
     * @Title: operateChangeCt
     * @Description: 运营变更合同状态并补记录
     */
    @ResponseBody
    @RequestMapping(value = "/operateChangeCt", method = RequestMethod.POST)
    public ReturnView<?, ?> operateChangeCt(HttpServletRequest request) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        Integer id = Integer.valueOf(reqMap.get("id").toString());
        try {
            //更新
            gpContractChangeService.operateChangeCt(id);
        } catch (Exception e) {
            logger.error("gpContractChange", "GpContractChangeController", "operateChangeCt", "", UserInfoHolder.getUserId(), "", "状态变更失败", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "状态变更失败");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }
}
