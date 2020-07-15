package cn.com.eju.pmls.frameContract.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.helper.SeqNoHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.service.CityService;
import cn.com.eju.deal.common.service.ProvinceService;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.oa.service.OaOperatorService;
import cn.com.eju.pmls.channelBusiness.dto.BusinessManagerDto;
import cn.com.eju.pmls.channelBusiness.service.BusinessManagerService;
import cn.com.eju.pmls.frameContract.service.PmlsFrameContractService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Description: 联动框架合同controller
*/
@Controller
@RequestMapping(value = "pmlsFrameContract")
public class PmlsFrameContractController extends BaseController
{
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    private PmlsFrameContractService pmlsFrameContractService;

    @Resource(name = "oaOperatorService")
    private OaOperatorService oaOperatorService;

    @Resource(name="provinceService")
    private ProvinceService provinceService;

    @Resource(name="cityService")
    private CityService cityService;
    @Resource(name = "businessManagerService")
    private BusinessManagerService businessManagerService;

    /**
    * @Title: list
    * @Description: 联动框架合同列表页面
    */
	@RequestMapping(value = "frameContractList", method = RequestMethod.GET)
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
    //获取省份列表数据
    @ResponseBody
    @RequestMapping(value = "province", method = RequestMethod.GET)
    public ResultData provincelist(HttpServletRequest request, ModelMap mop)
    {
        ResultData<?> resultData = new ResultData<>();
        try {
            resultData = this.provinceService.queryProvinceList();
        }catch (Exception e){
            logger.error("CRM", "FrameContractController", "province", null, null, "", "获取省份列表失败", e);
        }
        return resultData;
    }
    @RequestMapping(value = "/selectBusinessPage", method = RequestMethod.GET)
    public String selectBusinessPage(HttpServletRequest request, ModelMap mop){
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.addAllAttributes(reqMap);
        return "frameContract/selectBusinessPage";
    }

    @RequestMapping(value = "/selectBusinessPage/{estateId}", method = RequestMethod.GET)
    public String selectBusinessPageEstateId(@PathVariable("estateId") String estateId, HttpServletRequest request, ModelMap mop) {
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.put("estateId", estateId);
        mop.addAllAttributes(reqMap);
        return "frameContract/selectBusinessPage";
    }


    @RequestMapping(value = "/selectBankInfoPage", method = RequestMethod.GET)
    public String selectBankInfoPage(HttpServletRequest request, ModelMap mop){
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.addAllAttributes(reqMap);
        return "frameContract/selectBankInfoPage";
    }

	/**
     * 获取联动框架合同列表页
     * @param request
     * @param mop
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getFrameContractList", method = RequestMethod.POST)
    public ResultData getFrameContractList(HttpServletRequest request, ModelMap mop,HttpServletResponse response)
    {
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        //返回list
        ResultData<?> resultData = new ResultData<>();
        try {
        	if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.FRAMECONTRACT_LIST_SEARCH, reqMap);
            }
        	reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
            resultData = pmlsFrameContractService.getFrameContractList(reqMap,pageInfo);

        }catch (Exception e){
            logger.error("CRM", "FrameContractController", "getFrameContractList", reqMap.toString(), null, "", "获取联动框架合同列表页失败", e);
        }
        //存放到mop中
        return resultData;
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
            resultData = pmlsFrameContractService.getbriefById(id);
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
        	usercodeResult = pmlsFrameContractService.getUserMappingAccountProject(UserInfoHolder.get().getUserCode());

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
    @ResponseBody
    @RequestMapping(value = "/updateFrameContract", method = RequestMethod.POST)
    public ReturnView<?, ?> updateFrameContract(HttpServletRequest request) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.remove("_method");
        try{
        	reqMap.put("userIdUpt",UserInfoHolder.getUserId());
            //更新
            pmlsFrameContractService.update(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
            rspMap.put(Constant.RETURN_MSG_KEY, "修改成功！");
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
    @ResponseBody
    @RequestMapping(value = "getFrameContractCompanyList", method = RequestMethod.POST)
    public ResultData getFrameContractCompanyList(HttpServletRequest request, ModelMap mop)
    {
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        //返回list
        ResultData<?> resultData = null;
        List<?> contentlist = new ArrayList<>();
        try
        {
            reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
            resultData = pmlsFrameContractService.getFrameContractCompanyList(reqMap,pageInfo);

        }
        catch (Exception e)
        {
            logger.error("CRM", "FrameContractController", "getFrameContractCompanyList", reqMap.toString(), null, "", "新增框架合同获取公司列表失败", e);
        }
        return resultData;
    }
    /**
     * 框架合同详情页面
     * @return
     */
    @RequestMapping(value = "frameContractDetailPage", method = RequestMethod.GET)
    public ModelAndView frameContractDetailPage(Model model, HttpServletRequest request) {
        String id=request.getParameter("id");
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = new ResultData<>();
        String oaOperator = "1";
        //查询登陆人的核算主体数量
        ResultData usercodeResult = null;
        try {
            usercodeResult = pmlsFrameContractService.getUserMappingAccountProject(UserInfoHolder.get().getUserCode());
            if(usercodeResult.getReturnData() == null){
                oaOperator = "0";
            }
            model.addAttribute("oaOperator",oaOperator);
            if(usercodeResult.getReturnCode().equals("200")){
                List<?> usercodeResultList = (List<?>) usercodeResult.getReturnData();
                if(usercodeResultList!=null) {
                	model.addAttribute("accountProjectLenth", usercodeResultList.size());
                	
                	if(usercodeResultList.size() == 1) {
                		Map<?, ?> mapUserTemp = (Map<?, ?>) usercodeResultList.get(0);
                		String accountProject = mapUserTemp.get("accountProject").toString();
                		String accountProjectCode = mapUserTemp.get("accountProjectCode").toString();
                		model.addAttribute("accountProject", accountProject);
                		model.addAttribute("accountProjectCode", accountProjectCode);
                	}
                }
            }
            if(id!=null && !"".equals(id)){//查询商户信息，进入修改页面
                resultData = pmlsFrameContractService.getbriefById(Integer.parseInt(id));
                model.addAttribute("id",id);
                model.addAttribute("frameContractDto", JSONObject.toJSON(resultData.getReturnData()));
            }
        }catch (Exception e){
            logger.error("frameContract", "FrameContractController", "frameContractDetailPage", reqMap.toString(), null, "", "查询框架协议信息失败", e);
        }
        ModelAndView mv = new ModelAndView("frameContract/frameContractDetailPage");
        return mv;
    }
    /**
     * 新增框架合同页面
     * @return
     */
    @RequestMapping(value = "addFrameContractPage", method = RequestMethod.GET)
    public ModelAndView addFrameContractPage(Model model, HttpServletRequest request) {
        String id=request.getParameter("id");
        String companyId=request.getParameter("companyId");
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = new ResultData<>();
        try {
            if(id!=null && !"".equals(id)){//查询商户信息，进入修改页面
                resultData = pmlsFrameContractService.getbriefById(Integer.parseInt(id));
                model.addAttribute("id",id);
                model.addAttribute("frameContractDto", JSONObject.toJSON(resultData.getReturnData()));
            }
            if(companyId!=null && !"".equals(companyId)){
                BusinessManagerDto dto = new BusinessManagerDto();
                dto.setId(Integer.parseInt(companyId));
                resultData = businessManagerService.getBusinessInfo(dto);
                model.addAttribute("businessDto", JSONObject.toJSON(resultData.getReturnData()));
            }
            model.addAttribute("companyId",reqMap.get("companyId"));
        }catch (Exception e){
            logger.error("frameContract", "FrameContractController", "addFrameContractPage", reqMap.toString(), null, "", "查询框架协议信息失败", e);
        }
        ModelAndView mv = new ModelAndView("frameContract/addFrameContractPage");
        return mv;
    }
    /**
     *  新增框架合同
     *  @param request
     * @throws Exception
     */
    @RequestMapping(value = "addFrameContract", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> addFrameContract(HttpServletRequest request, ModelMap modelMap)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        Integer userIdCreate = UserInfoHolder.getUserId();
        String cityNo = UserInfoHolder.get().getCityNo();
        reqMap.put("userIdCreate", userIdCreate);
        reqMap.put("userCityNo", cityNo);
        reqMap.put("contractNo",  SeqNoHelper.getInstance().getSeqNoByTypeCode("TYPE_HTLD"));
        try
        {
            ResultData<?> resultData = pmlsFrameContractService.create(reqMap);
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
             resultData = pmlsFrameContractService.getbriefById(id);
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
             resultData2 = pmlsFrameContractService.getCompanyInfoByCompanyNo(companyNo);
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
     @ResponseBody
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
             pmlsFrameContractService.update(reqMap);
             rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
             rspMap.put(Constant.RETURN_MSG_KEY, "作废成功！");
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
             resultData = pmlsFrameContractService.submitToOA(param);
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
     		resultData = pmlsFrameContractService.getUserMappingAccountProject(UserInfoHolder.get().getUserCode());
     		if(resultData != null) {
     			mop.put("userMappingAccountProject", resultData.getReturnData());
     		}
 		} catch (Exception e) {
 			logger.error("Store", "FrameContractController", "showdetail", "", UserInfoHolder.getUserId(), "", "查看登陆人的核算主体失败", e);
 		}
     	//根据业绩城市查询得到核算主体
     	ResultData<?> resultData2 = null;
     	try {
     		resultData2 = pmlsFrameContractService.getLoginCityAccountProject(UserInfoHolder.get().getCityNo());
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
            pmlsFrameContractService.update(reqMap);
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
              pmlsFrameContractService.operateChangeCt(reqMap);
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
      @RequestMapping(value = "getOldFtBankInfo", method = RequestMethod.GET)
      @ResponseBody
      public Object getOldFtBankInfo(Model model,HttpServletRequest request) {
      	ResultData<?> resultData = new ResultData<>();
          Map<String, Object> reqMap = bindParamToMap(request);
          Object result = null;
          try {
          	resultData = pmlsFrameContractService.getOldFtBankInfo(reqMap);
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

    /**
     * 查询渠道开户行信息列表
     * @param request
     * @param mop
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getBankInfoList", method = RequestMethod.POST)
    public ResultData getBankInfoList(HttpServletRequest request, ModelMap mop,HttpServletResponse response)
    {
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        //返回list
        ResultData<?> resultData = new ResultData<>();
        try {
            resultData = pmlsFrameContractService.getBankInfoList(reqMap,pageInfo);
        }catch (Exception e){
            logger.error("CRM", "FrameContractController", "getBankInfoList", reqMap.toString(), null, "", "查询渠道开户行信息列表失败", e);
        }
        //存放到mop中
        return resultData;
    }
    /**
     * 删除对公账户
     * @param request
     * @param mop
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteBankInfo", method = RequestMethod.POST)
    public ResultData deleteBankInfo(HttpServletRequest request, ModelMap mop,HttpServletResponse response)
    {
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        //返回list
        ResultData<?> resultData = new ResultData<>();
        try {
            resultData = pmlsFrameContractService.deleteBankInfo(reqMap,pageInfo);
        }catch (Exception e){
            logger.error("CRM", "FrameContractController", "deleteBankInfo", reqMap.toString(), null, "", "删除对公账户", e);
        }
        //存放到mop中
        return resultData;
    }
    
    /**
     * desc:选择核算主体
     * 2020年4月10日
     */
    @RequestMapping(value = "toSelAccountProject", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView relateStoreUser(HttpServletRequest request, ModelMap mop) {
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.put("reqMap", reqMap);
        ResultData<?> resultData = null;
     	try {
     		resultData = pmlsFrameContractService.getUserMappingAccountProject(UserInfoHolder.get().getUserCode());
     		if(resultData != null) {
     			mop.put("userMappingAccountProject", resultData.getReturnData());
     		}
 		} catch (Exception e) {
 			logger.error("CRM", "FrameContractController", "toSelAccountProject", "", UserInfoHolder.getUserId(), "", "查看登陆人的核算主体失败", e);
 		}
     	//根据业绩城市查询得到核算主体
     	ResultData<?> resultData2 = null;
     	try {
     		resultData2 = pmlsFrameContractService.getLoginCityAccountProject(UserInfoHolder.get().getCityNo());
     		if(resultData2 != null) {
     			Map<?, ?> mapCityTemp = (Map<?, ?>) resultData2.getReturnData();
     			String accountProject = mapCityTemp.get("accountProject").toString();
     			String accountProjectCode = mapCityTemp.get("accountProjectCode").toString();
     			mop.addAttribute("cityAccountProject", accountProject);
     			mop.addAttribute("cityAccountProjectCode", accountProjectCode);
     		}
     	} catch (Exception e) {
     		logger.error("CRM", "FrameContractController", "toSelAccountProject", "", UserInfoHolder.getUserId(), "", "查看登陆人的核算主体失败", e);
     	}
        ModelAndView mv = new ModelAndView("frameContract/frameSelAccountProject");
        return mv;
    }

}
