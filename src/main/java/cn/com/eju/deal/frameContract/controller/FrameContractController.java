package cn.com.eju.deal.frameContract.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.eju.deal.common.service.CityService;
import cn.com.eju.deal.common.service.ProvinceService;
import cn.com.eju.deal.core.support.*;
import cn.com.eju.deal.oa.service.OaOperatorService;

import org.springframework.ui.Model;
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
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.frameContract.service.FrameContractService;

/** 
* @Description: 联动框架合同controller
*/
@RestController
@RequestMapping(value = "frameContract")
public class FrameContractController extends BaseController
{
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    private FrameContractService frameContractService;

    @Resource(name = "oaOperatorService")
    private OaOperatorService oaOperatorService;

    @Resource(name="provinceService")
    private ProvinceService provinceService;

    @Resource(name="cityService")
    private CityService cityService;
    
    /** 
    * @Title: list 
    * @Description: 联动框架合同列表页面
    */
	@RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, ModelMap mop,HttpServletResponse response)
    {
        //构建ModelAndView实例，并设置跳转地址
        ModelAndView mv = new ModelAndView("frameContract/frameContractList");
        UserInfo userInfo = UserInfoHolder.get();
        String cityNo = userInfo.getCityNo();
        if (StringUtil.isNotEmpty(cityNo)){
	        Map<String, Object> map = bindParamToMap(request);
	        if (map.containsKey("searchParam") && "1".equals(map.get("searchParam"))) {
	            Map<String, Object> searchParamMap = getRememberSearch(request, ComConstants.FRAMECONTRACT_LIST_SEARCH);
	            mv.addObject("searchParamMap", JSON.toJSON(searchParamMap));
	        } else {
	            clearSearch(request, response, ComConstants.FRAMECONTRACT_LIST_SEARCH);
	        }
	        mop.put("list_search_page", ComConstants.FRAMECONTRACT_LIST_SEARCH);
        }
        return mv;
        
    }
    
	/**
     * 获取联动框架合同列表页
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public ModelAndView getFrameContractList(HttpServletRequest request, ModelMap mop,HttpServletResponse response)
    {
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        //返回list
        ResultData<?> resultData = null;
        List<?> contentlist = new ArrayList<>();
        try {
        	if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.FRAMECONTRACT_LIST_SEARCH, reqMap);
            }
        	reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
            resultData = frameContractService.getFrameContractList(reqMap,pageInfo);
            if(resultData != null){
                contentlist = (List<?>) resultData.getReturnData();
            }
        }catch (Exception e){
            logger.error("CRM", "FrameContractController", "getFrameContractList", reqMap.toString(), null, "", "获取联动框架合同列表页失败", e);
        }
        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        ModelAndView mv = new ModelAndView("frameContract/frameContractListCtx");
        return mv;
    }
    
    /**
     * 根据id查询框架合同详情
     * @param id
     * @param mop
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView showdetail(@PathVariable("id") Integer id, ModelMap mop){
    	ResultData<?> resultData = null;
        try{
            resultData = frameContractService.getbriefById(id);
            if(resultData != null){
            	mop.addAttribute("frameContractInfo", resultData.getReturnData());
            }
        }catch (Exception e){
            logger.error("Store", "FrameContractController", "showdetail", "", UserInfoHolder.getUserId(), "", "查看框架合同详情失败", e);
        }
        String oaOperator = "1";
        //查询登陆人的核算主体数量
        ResultData usercodeResult = null;
        try {
        	usercodeResult = frameContractService.getUserMappingAccountProject(UserInfoHolder.get().getUserCode());

            if(usercodeResult.getReturnData() == null){
                oaOperator = "0";
            }

            if(usercodeResult.getReturnCode().equals("200")){
                List<?> usercodeResultList = (List<?>) usercodeResult.getReturnData();
                mop.addAttribute("accountProjectLenth", usercodeResultList.size());

                if(usercodeResultList.size() == 1) {
                    Map<?, ?> mapUserTemp = (Map<?, ?>) usercodeResultList.get(0);
                    String accountProject = mapUserTemp.get("accountProject").toString();
                    String accountProjectCode = mapUserTemp.get("accountProjectCode").toString();
                    mop.addAttribute("accountProject", accountProject);
                    mop.addAttribute("accountProjectCode", accountProjectCode);
                }
            }
		} catch (Exception e) {
			logger.error("Store", "FrameContractController", "showdetail", "", UserInfoHolder.getUserId(), "", "查看登陆人的核算主体失败", e);
		}
        //存放到mop中
        mop.addAttribute("oaOperator", oaOperator);
        mop.addAttribute("loginUserCode", UserInfoHolder.get().getUserCode());
        mop.put("agreementTypeList", SystemParam.getCodeListByKey(DictionaryConstants.FRAMECONTRACT_TYPE));
        //返回视图
        ModelAndView mv = new ModelAndView("frameContract/frameContractDetail");
        return mv;
    }
    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ReturnView<?, ?> update(HttpServletRequest request) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.remove("_method");
        try{
        	reqMap.put("userIdUpt",UserInfoHolder.getUserId());
            //更新
        	frameContractService.update(reqMap);
        }
        catch (Exception e){
            logger.error("Store", "FrameContractController", "update", "", UserInfoHolder.getUserId(), "", "更新框架合同失败", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "更新框架协议失败");
        }
        return getOperateJSONView(rspMap);
    }
    /**
     * --初始化
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "toChooseFrameContractCompanyList", method = RequestMethod.GET)
    public ModelAndView toChooseFrameContractCompanyList(HttpServletRequest request, ModelMap mop){
        bindParamToAttrbute(request);
        ModelAndView mv = new ModelAndView("frameContract/companyListPopup");
        return mv;
    }
    /**
     * 获取公司列表
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "getFrameContractCompanyList", method = RequestMethod.GET)
    public ModelAndView getFrameContractCompanyList(HttpServletRequest request, ModelMap mop)
    {
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        //返回list
        ResultData<?> resultData = null;
        List<?> contentlist = new ArrayList<>();
        try
        {
            reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
            resultData = frameContractService.getFrameContractCompanyList(reqMap,pageInfo);
            if(resultData != null){
            	contentlist = (List<?>) resultData.getReturnData();
            }
        }
        catch (Exception e)
        {
            logger.error("CRM", "FrameContractController", "getFrameContractCompanyList", reqMap.toString(), null, "", "新增框架合同获取公司列表失败", e);
        }

        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        ModelAndView mv = new ModelAndView("frameContract/companyListCtxPopup");
        return mv;
    }
    /**
     * 新增框架合同页面
     * @param id
     * @param mop
     * @return
     */
    @RequestMapping(value = "/toInsertFrameContract", method = RequestMethod.GET)
    public ModelAndView toInsertFrameContract(HttpServletRequest request,ModelMap mop){
    	
	     Map<String, Object> reqMap = bindParamToMap(request);
	     if(reqMap.isEmpty() || !reqMap.containsKey("changeFrameType")){
	    	 mop.put("changeFrameType", 22101);
	     }else{
	    	 mop.put("changeFrameType", reqMap.get("changeFrameType").toString());
	     }
	   	 //生成编号
	   	 String frameContractNo = SeqNoHelper.getInstance().getSeqNoByTypeCode("TYPE_HTLD");
	   	 mop.put("frameContractNo", frameContractNo);
	   	 //申请人
	   	 mop.put("userIdCreate", UserInfoHolder.get().getUserId());
	   	 mop.put("userCityNo", UserInfoHolder.get().getCityNo());
	   	 mop.put("agreementTypeList", SystemParam.getCodeListByKey(DictionaryConstants.FRAMECONTRACT_TYPE));
	   	 mop.put("reAgreeFlagList", SystemParam.getCodeListByKey(DictionaryConstants.FRAMECONTRACT_REAGREEFLAG_TYPE));

	   	 try {
             ResultData<?> reback = this.provinceService.queryProvinceList();
             //页面数据
             List<?> provinceList = (List<?>) reback.getReturnData();
             mop.put("provinceList",provinceList);
         }catch (Exception e){
             logger.error("frameContract", "FrameContractController", "toInsertFrameContract", reqMap.toString(), null, "", "查询省份失败", e);
         }

	     ModelAndView mv = new ModelAndView("frameContract/frameContractAdd");
	     return mv;
    }
    /**
     *  新增框架合同
     *  @param request
     * @throws Exception 
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> create(HttpServletRequest request, ModelMap modelMap)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        Integer userIdCreate = UserInfoHolder.getUserId();
        reqMap.put("userIdCreate", userIdCreate);
        try
        {
            ResultData<?> resultData = frameContractService.create(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        }
        catch (Exception e)
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, " 新增框架协议失败！");
            
            logger.error("Store", "FrameContractController", "create", "", null, "", "新增框架合同失败！", e);
            
        }
        
        return getOperateJSONView(rspMap);
    }
    /** 
     * 修改--初始化
     * @param id
     * @throws Exception
     */
     @RequestMapping(value = "/u/{id}", method = RequestMethod.GET)
     public ModelAndView toEdit(@PathVariable("id") int id, ModelMap mop) {
         Map<String, Object> map = new HashMap<>();
         //返回map
         ResultData<?> resultData = new ResultData<>();
         try {
             resultData = frameContractService.getbriefById(id);
         }
         catch (Exception e){
             logger.error("store", "FrameContractController", "toEdit", "", UserInfoHolder.getUserId(), "", "修改页面初始化查找框架合同信息失败", e);
         }
         mop.put("agreementTypeList", SystemParam.getCodeListByKey(DictionaryConstants.FRAMECONTRACT_TYPE));
         mop.put("reAgreeFlagList", SystemParam.getCodeListByKey(DictionaryConstants.FRAMECONTRACT_REAGREEFLAG_TYPE));

         mop.put("contractInfo", resultData.getReturnData());
         map = (Map<String, Object>)resultData.getReturnData();
         String companyNo=  map.get("companyNo").toString();
         ResultData<?> resultData2 = new ResultData<>();
         try {
             resultData2 = frameContractService.getCompanyInfoByCompanyNo(companyNo);
         }
         catch (Exception e){
             logger.error("store", "FrameContractController", "toEdit", "", UserInfoHolder.getUserId(), "", "修改页面初始化查找框架合同关联的公司信息失败", e);
         }
         mop.put("companyInfo", resultData2.getReturnData());
         mop.put("userIdCreate", UserInfoHolder.get().getUserId());
       	 mop.put("userCityNo", UserInfoHolder.get().getCityNo());

         try {
             ResultData<?> reback = this.provinceService.queryProvinceList();
             //页面数据
             List<?> provinceList = (List<?>) reback.getReturnData();
             mop.put("provinceList",provinceList);
         }catch (Exception e){
             logger.error("frameContract", "FrameContractController", "toEdit", id+"", null, "", "查询省份失败", e);
         }

         try {
             if(resultData!= null&&resultData.getReturnData()!= null && ((Map<String, Object>) resultData.getReturnData()).get("accountProvinceNo") != null){
                 Map<String,Object> reqMap = new HashMap<>();
                 reqMap.put("provinceNo",((Map<String, Object>) resultData.getReturnData()).get("accountProvinceNo"));
                 ResultData<?> reback = this.cityService.queryCitylist(reqMap);
                 //页面数据
                 List<?> cityList = (List<?>) reback.getReturnData();
                 mop.put("cityList",cityList);
             }
         }catch (Exception e){
             logger.error("frameContract", "FrameContractController", "toEdit", id+"", null, "", "查询城市失败", e);
         }

       	 ModelAndView mv = new ModelAndView("frameContract/frameContractEdit");
         return mv;
     }

 /**
      * 作废合同
      */
     @RequestMapping(value = "/cancel", method = RequestMethod.POST)
     public ReturnView<?, ?> cancel(HttpServletRequest request) {
         //返回map
         Map<String, Object> rspMap = new HashMap<String, Object>();
         //获取map
         Map<String, Object> reqMap = bindParamToMap(request);
         reqMap.remove("_method");
         reqMap.put("userIdUpt",UserInfoHolder.getUserId());
         reqMap.put("approveState",10405);
         try
         {
             //更新
         	frameContractService.update(reqMap);
         }
         catch (Exception e)
         {
             logger.error("Store", "FrameContractController", "cancel", "", UserInfoHolder.getUserId(), "", "框架合同作废失败", e);
             
             rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
             
             rspMap.put(Constant.RETURN_MSG_KEY, "框架协议作废失败");
         }
         return getOperateJSONView(rspMap);
     }

     @RequestMapping(value = "/submitToOA/{contractId}")
     @ResponseBody
     public ResultData<String> submitToOA(@PathVariable Integer contractId){
         ResultData<String> resultData = new ResultData<>();
         Map<String,String> param = new HashMap<>();
         param.put("contractId",contractId+"");
         param.put("userId",UserInfoHolder.getUserId()+"");
         param.put("userCode",UserInfoHolder.get().getUserCode());
         param.put("cityNo",UserInfoHolder.get().getCityNo());
         param.put("userName",UserInfoHolder.get().getUserName());
         try{
             resultData = frameContractService.submitToOA(param);
         }catch (Exception e){
             resultData.setFail("系统异常，请联系管理员");
             logger.error("frameContract", "FrameContractController", "submitToOA", param.toString(), UserInfoHolder.getUserId(), "", "框架合同提交OA失败", e);
         }
         resultData.setReturnData(contractId+"");

         return resultData;
     }
     /** 
      * @Title: toChooseAccountProject 
      * @Description:选择考核主体页面
      * @return    
      */
     @RequestMapping(value = "/toChooseAccountProject", method = RequestMethod.GET)
     @ResponseBody
     public ModelAndView toChooseAccountProject(HttpServletRequest request, ModelMap mop){	
     	ResultData<?> resultData = null;
     	bindParamToAttrbute(request);
     	try {
     		resultData = frameContractService.getUserMappingAccountProject(UserInfoHolder.get().getUserCode());
     		if(resultData != null) {
     			mop.put("userMappingAccountProject", resultData.getReturnData());
     		}
 		} catch (Exception e) {
 			logger.error("Store", "FrameContractController", "showdetail", "", UserInfoHolder.getUserId(), "", "查看登陆人的核算主体失败", e);
 		}
     	//根据业绩城市查询得到核算主体
     	ResultData<?> resultData2 = null;
     	try {
     		resultData2 = frameContractService.getLoginCityAccountProject(UserInfoHolder.get().getCityNo());
     		if(resultData2 != null) {
     			Map<?, ?> mapCityTemp = (Map<?, ?>) resultData2.getReturnData();
     			String accountProject = mapCityTemp.get("accountProject").toString();
     			String accountProjectCode = mapCityTemp.get("accountProjectCode").toString();
     			mop.addAttribute("cityAccountProject", accountProject);
     			mop.addAttribute("cityAccountProjectCode", accountProjectCode);
     		}
     	} catch (Exception e) {
     		logger.error("Store", "FrameContractController", "showdetail", "", UserInfoHolder.getUserId(), "", "查看登陆人的核算主体失败", e);
     	}
     	mop.put("loginUserCode", UserInfoHolder.get().getUserCode());
     	ModelAndView mv = new ModelAndView( "frameContract/frameAccountProjectMapping");
     	return mv;
     } 
     /** 
      * @Title: chooseAccountProject 
      * @Description: 选择一个核算主体
      */
     @RequestMapping(value = "/chooseAccountProject", method = RequestMethod.POST)
     @ResponseBody
     public ReturnView<?, ?> chooseAccountProject(HttpServletRequest request, ModelMap modelMap)
     {
     	//返回map
     	Map<String, Object> rspMap = new HashMap<String, Object>();
     	//请求map
     	Map<String, Object> reqMap = bindParamToMap(request);
     	try{
     		frameContractService.update(reqMap);
     	} catch (Exception e){
     		logger.error("houseLinkage.estate", "EstateController", "changeStatusMode", "", null, "", "保存核算主体失败！", e);
     		rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
     		rspMap.put(Constant.RETURN_MSG_KEY, "保存核算主体！");
     		return getOperateJSONView(rspMap);
     	}
     	rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
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
          reqMap.remove("_method");
          try {
              //更新
        	  frameContractService.operateChangeCt(reqMap);
          } catch (Exception e) {
              logger.error("Store", "FrameContractController", "operateChangeCt", "", UserInfoHolder.getUserId(), "", "状态变更失败", e);
              rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
              rspMap.put(Constant.RETURN_MSG_KEY, "状态变更失败");
              return getOperateJSONView(rspMap);
          }
          rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
          return getOperateJSONView(rspMap);
      }
      /**
       * 根据公司编号获取最新审核通过的框架合同的银行相关信息
       */
      @RequestMapping(value = "/getOldFtBankInfo", method = RequestMethod.GET)
      @ResponseBody
      public Object getOldFtBankInfo(Model model,HttpServletRequest request) {
      	ResultData<?> resultData = new ResultData<>();
          Map<String, Object> reqMap = bindParamToMap(request);
          Object result = null;
          try {
          	resultData = frameContractService.getOldFtBankInfo(reqMap);
  			if(resultData != null &&  resultData.getReturnCode().equals(ReturnCode.SUCCESS)){
  				result = resultData.getReturnData();
  			}
  		} catch (Exception e) {
  			if(result == null){
      			resultData.setFail();
      		}
  			logger.error("Store", "FrameContractController", "getOldFtBankInfo", "", UserInfoHolder.getUserId(), "", "", e);
  		}
          return result;
      }
}
