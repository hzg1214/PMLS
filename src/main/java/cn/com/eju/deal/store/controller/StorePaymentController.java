package cn.com.eju.deal.store.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.helper.SeqNoHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.service.CommonService;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.contract.service.ContractService;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.PinyinUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.houseLinkage.estate.EstatePhotosDto;
import cn.com.eju.deal.store.service.StorePaymentService;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/** 
* @Description: 保证金退款controller
*/
@RestController
@RequestMapping(value = "storePayment")
public class StorePaymentController extends BaseController
{
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    private StorePaymentService storePaymentService;
    
    @Resource(name = "contractService")
    private ContractService contractService;
    
    @Resource(name = "commonService")
    private CommonService commonService;
    
    /** 
    * @Title: list 
    * @Description: 保证金退款列表页面
    */
	@RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, ModelMap mop,HttpServletResponse response)
    {
        //构建ModelAndView实例，并设置跳转地址
        ModelAndView mv = new ModelAndView("store/storePaymentList");
        UserInfo userInfo = UserInfoHolder.get();
        String cityNo = userInfo.getCityNo();
        if (StringUtil.isNotEmpty(cityNo)){
	        Map<String, Object> map = bindParamToMap(request);
	        if (map.containsKey("searchParam") && "1".equals(map.get("searchParam"))) {
	            Map<String, Object> searchParamMap = getRememberSearch(request, ComConstants.STOREPAYMENT_LIST_SEARCH);
	            mv.addObject("searchParamMap", JSON.toJSON(searchParamMap));
	        } else {
	            clearSearch(request, response, ComConstants.STOREPAYMENT_LIST_SEARCH);
	        }
	        mop.put("list_search_page", ComConstants.STOREPAYMENT_LIST_SEARCH);
        }
        return mv;
        
    }
    
	/**
     * 获取保证金退款列表页
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public ModelAndView getStorePaymentList(HttpServletRequest request, ModelMap mop,HttpServletResponse response)
    {
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        //返回list
        ResultData<?> resultData = null;
        List<?> contentlist = new ArrayList<>();
        try {
        	if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.STOREPAYMENT_LIST_SEARCH, reqMap);
            }
        	reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
            resultData = storePaymentService.getStorePaymentList(reqMap,pageInfo);
            if(resultData != null){
                contentlist = (List<?>) resultData.getReturnData();
            }
        }catch (Exception e){
            logger.error("PMLS", "StorePaymentController", "getStoreReceiveList", reqMap.toString(), null, "", "获取保证金列表页", e);
        }
        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        ModelAndView mv = new ModelAndView("store/storePaymentListCtx");
        return mv;
    }
    /**
     * 根据id查询保证金详情
     * @param id
     * @param mop
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView showdetail(@PathVariable("id") Integer id, ModelMap mop){
    	ResultData<?> resultData = null;
        try{
            resultData = storePaymentService.getbriefById(id);
            if(resultData != null){
            	mop.addAttribute("storeBackinfo", resultData.getReturnData());
            }
        }catch (Exception e){
            logger.error("Store", "StoreReceiveController", "showdetail", "", UserInfoHolder.getUserId(), "", "查看保证金收款详情失败", e);
        }
        mop.addAttribute("receiveId", id);
        //返回视图
        ModelAndView mv = new ModelAndView("store/storePaymentDetail");
        return mv;
    }
    /** 
     * @Title: update 
     * @Description: 保证金退款信息更新
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
        	 storePaymentService.update(reqMap);
         }
         catch (Exception e)
         {
             logger.error("Store", "StorePaymentController", "update", "", UserInfoHolder.getUserId(), "", "更新保证金失败", e);
             
             rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
             
             rspMap.put(Constant.RETURN_MSG_KEY, "更新保证金失败");
         }
         return getOperateJSONView(rspMap);
     }
     /**
      * --初始化
      * @param request
      * @param mop
      * @return
      */
     @RequestMapping(value = "toPaymentContractList", method = RequestMethod.GET)
     public ModelAndView toPaymentContractList(HttpServletRequest request, ModelMap mop){
         bindParamToAttrbute(request);
         ModelAndView mv = new ModelAndView("store/storePaymentContractListPopup");
         return mv;
     }
     /**
      * 获取合同列表
      * @param request
      * @param mop
      * @return
      */
     @RequestMapping(value = "getPaymentContractList", method = RequestMethod.GET)
     public ModelAndView getPaymentContractList(HttpServletRequest request, ModelMap mop)
     {
         Map<String, Object> reqMap = bindParamToMap(request);
         PageInfo pageInfo = getPageInfo(request);
         //返回list
         ResultData<?> resultData = null;
         List<?> contractInfo = new ArrayList<>();
         try
         {
             reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
             resultData = storePaymentService.getPaymentContractList(reqMap,pageInfo);
             if(resultData != null){
            	 contractInfo = (List<?>) resultData.getReturnData();
             }
         }
         catch (Exception e)
         {
             logger.error("PMLS", "StorePaymentController", "getPaymentContractList", reqMap.toString(), null, "", "新增保证金退款获取合同列表", e);
         }

         //存放到mop中
         mop.addAttribute("contractInfo", contractInfo);
         ModelAndView mv = new ModelAndView("store/storePaymentContractListCtxPopup");
         return mv;
     }
     
     /**
      * 新增保证金退款页面
      * @param id
      * @param mop
      * @return
      */
     @RequestMapping(value = "/toInsertPayment/{id}", method = RequestMethod.GET)
     public ModelAndView insertPaymen(@PathVariable("id") Integer id, ModelMap mop){
    	 //合同信息
    	 ResultData<?> resultData = null;
    	 ResultData<?> resultDataSwitch 	= null;
    	 ResultData<?> basCitySettingByCityNo = null;
         try{
             resultData = storePaymentService.getContractInfoById(id);
             if(resultData != null){
            	 mop.addAttribute("contractInfo", resultData.getReturnData());
            	 Map<String, Object> contractInfo = (Map<String, Object>) resultData.getReturnData();
            	 if(contractInfo!=null && contractInfo.get("contract")!=null){
                	 Map<String, Object> contractDto = (Map<String, Object>) contractInfo.get("contract");
                     String cityNo = (String) contractDto.get("acCityNo");
                     String contractCityName = (String) contractDto.get("contractCityName");
                     //根据城市编号获取相应的退款生成编号
                     basCitySettingByCityNo = storePaymentService.getBasCitySettingByCityNo(cityNo);
                     String tkTypeCode = basCitySettingByCityNo.getReturnData().toString();
                     String paymentNo = SeqNoHelper.getInstance().getSeqNoByTypeCode(tkTypeCode);
                     mop.put("paymentNo", paymentNo);
                     //获取关账信息
                     resultDataSwitch = commonService.getSwitchInfo(cityNo);
                     Map<String, Object> map = (Map<String, Object>) resultDataSwitch.getReturnData();
                     mop.addAttribute("yearMonth", map.get("yearMonth"));
            	 }
             }else{
            	 mop.addAttribute("yearMonth", "1970-01-01");
             }
         }catch (Exception e){
             logger.error("Store", "StorePaymentController", "insertPaymen", "", UserInfoHolder.getUserId(), "", "根据合同查询合同信息失败", e);
         }
    	 //申请人
    	 mop.put("userName", UserInfoHolder.get().getUserName());
         ModelAndView mv = new ModelAndView("store/storePaymentAdd");
         return mv;
     }
     /**
      *  新增保证金退款
      *  @param request
      * @throws Exception 
      */
     @RequestMapping(value = "", method = RequestMethod.POST)
     @ResponseBody
     public ReturnView<?, ?> create(HttpServletRequest request, ModelMap modelMap, EstatePhotosDto estatePhotosDto)
     {
         //返回map
         Map<String, Object> rspMap = new HashMap<String, Object>();
         //请求map
         Map<String, Object> reqMap = bindParamToMap(request);
         Integer userIdCreate = UserInfoHolder.getUserId();
         reqMap.put("userIdCreate", userIdCreate);
         try
         {
             ResultData<?> resultData = storePaymentService.create(reqMap,estatePhotosDto);
             rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
             rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
         }
         catch (Exception e)
         {
             rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
             rspMap.put(Constant.RETURN_MSG_KEY, "保证金退款新增失败！");
             
             logger.error("Store", "StorePaymentController", "create", "", null, "", "保证金退款新增失败！", e);
             
         }
         
         return getOperateJSONView(rspMap);
     }
}
