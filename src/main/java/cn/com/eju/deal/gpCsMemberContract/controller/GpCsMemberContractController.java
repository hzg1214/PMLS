package cn.com.eju.deal.gpCsMemberContract.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.helper.SeqNoHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.service.CityService;
import cn.com.eju.deal.common.service.CommonService;
import cn.com.eju.deal.common.service.ProvinceService;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.gpCsMemberContract.service.GpCsMemberContractService;
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
 * 公盘初始会员合同
 */
@Controller
@RequestMapping(value = "gpCsMemberContract")
public class GpCsMemberContractController extends BaseController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private GpCsMemberContractService gpCsMemberContractService;
    @Resource(name = "oaOperatorService")
    private OaOperatorService oaOperatorService;
    @Resource(name = "commonService")
    private CommonService commonService;
    @Resource(name="provinceService")
    private ProvinceService provinceService;
    @Resource(name="cityService")
    private CityService cityService;
    /** 
     * @Description: 公盘初始会员合同初始化
     */
 	@RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, ModelMap mop,HttpServletResponse response){
         //构建ModelAndView实例，并设置跳转地址
         ModelAndView mv = new ModelAndView("gpCsMemberContract/gpCsMemberContractList");
         UserInfo userInfo = UserInfoHolder.get();
         String cityNo = userInfo.getCityNo();
         if (StringUtil.isNotEmpty(cityNo)){
 	        Map<String, Object> map = bindParamToMap(request);
 	        if (map.containsKey("searchParam") && "1".equals(map.get("searchParam"))) {
 	            Map<String, Object> searchParamMap = getRememberSearch(request, ComConstants.GPCSMEMBER_LIST_SEARCH);
 	            mv.addObject("searchParamMap", JSON.toJSON(searchParamMap));
 	        } else {
 	            clearSearch(request, response, ComConstants.GPCSMEMBER_LIST_SEARCH);
 	        }
 	        mop.put("list_search_page", ComConstants.GPCSMEMBER_LIST_SEARCH);
         }
         return mv;
         
     }
 	/**
     * 获取公盘初始会员合同列表页
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "getGpCsMemberContractList", method = RequestMethod.GET)
    public ModelAndView getGpCsMemberContractList(HttpServletRequest request, ModelMap mop,HttpServletResponse response){
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        //返回list
        ResultData<?> resultData = null;
        List<?> contentlist = new ArrayList<>();
        try {
        	if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.GPCSMEMBER_LIST_SEARCH, reqMap);
            }
        	reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
            resultData = gpCsMemberContractService.getGpCsMemberContractList(reqMap,pageInfo);
            if(resultData != null){
                contentlist = (List<?>) resultData.getReturnData();
            }
        }catch (Exception e){
            logger.error("gpCsMemberContract", "GpCsMemberContractController", "getGpContractStopList", reqMap.toString(), null, "", "获取联动框架合同列表页失败", e);
        }
        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        
        //查询是否是经办人
        ResultData<?> backResult = new ResultData<>();
        try {
            backResult = oaOperatorService.getByUserCode(UserInfoHolder.get().getUserCode());
        }
        catch (Exception e){
            logger.error("gpCsMemberContract","GpCsMemberContractController","getGpContractStopList","",UserInfoHolder.getUserId(),
                    "","合同查询, 查询经办人失败", e);
        }
        //存放到mop中
        mop.addAttribute("oaOperator", backResult.getReturnData());
        
        ModelAndView mv = new ModelAndView("gpCsMemberContract/gpCsMemberContractListCtx");
        return mv;
    }
    /**
     * 根据id查看公盘初始会员合同详情
     * @param id
     * @param mop
     * @return
     */
    @RequestMapping(value = "getGpCsMemberContractById/{id}", method = RequestMethod.GET)
    public ModelAndView getGpCsMemberContractById(@PathVariable("id") Integer id, ModelMap mop){
    	ResultData<?> resultData = null;
        try{
            resultData = gpCsMemberContractService.getGpCsMemberContractById(id);
            if(resultData != null){
            	mop.addAttribute("csMember", resultData.getReturnData());
            }
        }catch (Exception e){
            logger.error("gpCsMemberContract", "GpCsMemberContractController", "getGpCsMemberContractById", "", UserInfoHolder.getUserId(), "", "查看公盘初始会员合同失败", e);
        }
        //查询是否是经办人
        ResultData<?> backResult = new ResultData<>();
        try {
            backResult = oaOperatorService.getByUserCode(UserInfoHolder.get().getUserCode());
        }
        catch (Exception e){
            logger.error("gpCsMemberContract","GpCsMemberContractController","getGpCsMemberContractById","",UserInfoHolder.getUserId(),
                    "","合同查询, 查询经办人失败", e);
        }
        //存放到mop中
        mop.addAttribute("oaOperator", backResult.getReturnData());
        //返回视图
        ModelAndView mv = new ModelAndView("gpCsMemberContract/gpCsMemberContractDetail");
        return mv;
    }
    /** 
     * @Description: 公盘初始会员合同新增页面
     */
    @RequestMapping(value = "/toAddGpCsMemberContract/{centerId}/{centerName}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView toAddGpContractChange(@PathVariable("centerId") int centerId, @PathVariable("centerName") String centerName,ModelMap mop){
		 //操作人
    	 UserInfo userInfo = UserInfoHolder.get();
		 mop.put("userCode", userInfo.getUserCode());
		 mop.put("userName", userInfo.getUserName());
		 mop.put("userIdCreate", userInfo.getUserId());
		 mop.put("centerId", centerId);
		 mop.put("centerName", centerName);
		 try {
            ResultData<?> reback = this.provinceService.queryProvinceList();
            //页面数据
            List<?> provinceList = (List<?>) reback.getReturnData();
            mop.put("provinceList",provinceList);
	     } catch (Exception e1) {
	            logger.warn("获取省份列表失败");
	     }
		 //类型
		 try{
			 //我方全称
	        Map<String, Object> queryParam = new HashMap<>();
	        queryParam.put("cityNo",userInfo.getCityNo());
	        ResultData<?> fullNameList = this.commonService.queryFullNameList(queryParam);
	        mop.put("fullNameList", fullNameList.getReturnData());
         }catch (Exception e){
            logger.warn("获取我方全称列表失败");
         }
		 ModelAndView mv = new ModelAndView( "gpCsMemberContract/gpCsMemberContractAdd");
		 return mv;
    } 
    /** 
     * @Description: 公盘初始会员合同编辑页面
     */
    @RequestMapping(value = "/toEidtGpCsMemberContract/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView toEidtGpContractChange(@PathVariable("id") Integer id,ModelMap mop){
    	//操作人
    	mop.put("userCode", UserInfoHolder.get().getUserCode());
    	mop.put("userName", UserInfoHolder.get().getUserName());
    	mop.put("userIdCreate", UserInfoHolder.get().getUserId());
    	mop.put("cityNo", UserInfoHolder.get().getCityNo());
    	//返回页面参数
    	ResultData<?> resultData = null;
        try{
            resultData = gpCsMemberContractService.getGpCsMemberContractById(id);
            if(resultData != null){
            	mop.addAttribute("csMember", resultData.getReturnData());
            }
        }catch (Exception e){
            logger.error("gpCsMemberContract", "GpCsMemberContractController", "toEidtGpCsMemberContract", "", UserInfoHolder.getUserId(), "", "查看公盘合同终止失败", e);
        }
        try {
            ResultData<?> reback = this.provinceService.queryProvinceList();
            //页面数据
            List<?> provinceList = (List<?>) reback.getReturnData();
            mop.put("provinceList",provinceList);
	     } catch (Exception e1) {
	            logger.warn("获取省份列表失败");
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
            logger.error("gpCsMemberContract", "GpCsMemberContractController", "toEidtGpCsMemberContract", id+"", null, "", "查询城市失败", e);
        }
    	ModelAndView mv = new ModelAndView( "gpCsMemberContract/gpCsMemberContractEdit");
    	return mv;
    } 
    /**
     * 保存公盘初始会员协议
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveGpCsMemberContract",method = RequestMethod.POST)
    public ResultData<?> saveGpCsMemberContract(HttpServletRequest request){
        ResultData<?> resultData = new ResultData<>();
        // 获取map
     	Map<String, Object> reqMap = bindParamToMap(request);
     	reqMap.put("userIdCreate", UserInfoHolder.get().getUserId());
     	reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
     	//判断更新或保存
        if(!reqMap.containsKey("id")){
            String gpCsMemberContractNo = SeqNoHelper.getInstance().getSeqNoByTypeCode("TYPE_GPCSMEMBER");
            reqMap.put("gpCsMemberContractNo", gpCsMemberContractNo);
            reqMap.put("type", "create");
        }else {
        	reqMap.put("type", "update");
        }
        try{
           resultData = gpCsMemberContractService.saveGpCsMemberContract(reqMap);
        }catch (Exception e){
            resultData.setFail("保存公盘初始会员协议失败");
            logger.error("gpCsMemberContract", "GpCsMemberContractController", "saveGpCsMemberContract", "",
                    UserInfoHolder.getUserId(), "", "保存公盘初始会员协议失败！", e);
        }

        return resultData;
    }
    
    /** 
     * @Description: 提交Oa
     */
     @RequestMapping(value = "/submitGpCsMemberContractOa", method = RequestMethod.GET)
     @ResponseBody
     public String submitGpCsMemberContractOa(HttpServletRequest request) {
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
         ResultData gpCsMemberContractOa = null;
         try {
        	 gpCsMemberContractOa = gpCsMemberContractService.submitGpCsMemberContractOa(reqMap);
	       	  if(ReturnCode.FAILURE.equals(gpCsMemberContractOa.getReturnCode())) {
	       		  rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
	       		  rspMap.put(Constant.RETURN_MSG_KEY, gpCsMemberContractOa.getReturnMsg());
	       	  }
         } catch (Exception e) {
             logger.error("gpCsMemberContract", "GpCsMemberContractController", "toOaGPStopSubmit", "", UserInfoHolder.getUserId(), "", "提交OA失败", e);
         }
         return gpCsMemberContractOa.toString();
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
         reqMap.put("approveState", GPCONTRACT_STATUS_CANCEL);
         try{
        	 gpCsMemberContractService.updateStatus(reqMap);
         }
         catch (Exception e) {
             rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
             rspMap.put(Constant.RETURN_MSG_KEY, "更新公盘初始会员合同终止状态失败");
             logger.error("gpCsMemberContract", "GpCsMemberContractController", "updateStatus", "", UserInfoHolder.getUserId(), "", "", e);
             return getOperateJSONView(rspMap);
         }
         return getOperateJSONView(rspMap);
     }
     /**
      * 关联公盘数据
      * @param request
      * @param mop
      * @return
      */
     @ResponseBody
     @RequestMapping(value = "getGpInfoByCompanyId", method = RequestMethod.GET)
     public ReturnView<?, ?> getGpInfoByCompanyId(HttpServletRequest request, ModelMap mop) {
         Map<String, Object> reqMap = bindParamToMap(request);
         Map<String, Object> rspMap = new HashMap<String, Object>();
         try{
             ResultData<?> resultData = gpCsMemberContractService.getGpInfoByCompanyId(reqMap);
             rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
             rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
         }catch (Exception e){
             logger.error("gpCsMemberContract", "GpCsMemberContractController", "getGpInfoByCompanyId", "", UserInfoHolder.getUserId(), "", "查询关联公盘列表失败", e);
             rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
             rspMap.put(Constant.RETURN_MSG_KEY, "查询关联公盘列表失败");
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
            gpCsMemberContractService.operateChangeCt(id);
        } catch (Exception e) {
            logger.error("gpCsMemberContract", "GpCsMemberContractController", "operateChangeCt", "", UserInfoHolder.getUserId(), "", "状态变更失败", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "状态变更失败");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }
}
