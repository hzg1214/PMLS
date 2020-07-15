package cn.com.eju.deal.keFuOrder.controller;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.helper.SeqNoHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.service.CityService;
import cn.com.eju.deal.common.service.CommonService;
import cn.com.eju.deal.common.service.ProvinceService;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.dto.kefuOrder.KeFuOrderDto;
import cn.com.eju.deal.houseLinkage.estate.dto.City;
import cn.com.eju.deal.keFuOrder.service.KeFuOrderContractService;
import cn.com.eju.deal.oa.service.OaOperatorService;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 客服反馈工单
 */
@Controller
@RequestMapping(value = "keFuOrder")
public class KeFuOrderContractController extends BaseReportController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private KeFuOrderContractService keFuOrderContractService;
    @Resource(name = "oaOperatorService")
    private OaOperatorService oaOperatorService;
    @Resource(name = "commonService")
    private CommonService commonService;
    @Resource(name="provinceService")
    private ProvinceService provinceService;
    @Resource(name="cityService")
    private CityService cityService;
    /** 
     * @Description: 客服反馈工单初始化
     */
 	@RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, ModelMap mop,HttpServletResponse response) throws Exception{
         //构建ModelAndView实例，并设置跳转地址
         ModelAndView mv = new ModelAndView("keFuOrder/keFuOrderList");//gpCsMemberContractList
         UserInfo userInfo = UserInfoHolder.get();
         String cityNo = userInfo.getCityNo();
         if (StringUtil.isNotEmpty(cityNo)){
 	        Map<String, Object> map = bindParamToMap(request);
 	        if (map.containsKey("searchParam") && "1".equals(map.get("searchParam"))) {
 	            Map<String, Object> searchParamMap = getRememberSearch(request, ComConstants.KEFUORDER_LIST_SEARCH);
 	            mv.addObject("searchParamMap", JSON.toJSON(searchParamMap));
 	        } else {
 	            clearSearch(request, response, ComConstants.KEFUORDER_LIST_SEARCH);
 	        }
 	        mop.put("list_search_page", ComConstants.KEFUORDER_LIST_SEARCH);
         }

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.get().getUserId());
        ResultData<?> resultData = commonService.queryCityList(reqMap);

        //List<City> cityList = JSONObject.parseArray(JsonUtil.parseToJson(resultData.getReturnData()),List<>.class);

        String cityIdListStr ="" ;
        JSONArray jsonArray = JSONObject.parseArray(JsonUtil.parseToJson(resultData.getReturnData()));
        if(!jsonArray.isEmpty()) {
            for(int i = 0,l = jsonArray.size();i<l;i++) {
                Map<String,Object> map = JsonUtil.parseToObject(jsonArray.get(i).toString(), Map.class);
                cityIdListStr +=map.get("cityId").toString()+",";
            }
        }

        cityIdListStr+="0";
         mop.put("authCityIds",cityIdListStr);
         mop.put("categoryOneList", SystemParam.getCodeListByKey(DictionaryConstants.KEFU_CATEGORYONE_TYPE));
         return mv;
         
     }
 	/**
     * 获取客服反馈工单列表页
     */
    @RequestMapping(value = "getKeFuOrderList", method = RequestMethod.GET)
    public ModelAndView getKeFuOrderList(HttpServletRequest request, ModelMap mop,HttpServletResponse response){
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        //返回list
        ResultData<?> resultData = null;
        List<?> contentlist = new ArrayList<>();
        try {
        	if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.KEFUORDER_LIST_SEARCH, reqMap);
            }
        	reqMap.put("userCityNo", UserInfoHolder.get().getCityNo());
        	reqMap.put("userId", UserInfoHolder.get().getUserId());
            resultData = keFuOrderContractService.getKeFuOrderList(reqMap,pageInfo);
            if(resultData != null){
                contentlist = (List<?>) resultData.getReturnData();
            }
        }catch (Exception e){
            logger.error("keFuOrder", "KeFuOrderContractController", "getKeFuOrderList", reqMap.toString(), null, "", "获取客服反馈工单列表失败", e);
        }
        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        ModelAndView mv = new ModelAndView("keFuOrder/keFuOrderListCtx");
        return mv;
    }
    
    /** 
     * @Description: 客服反馈工单新增页面
     */
    @RequestMapping(value = "/toAddKeFuOrder", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView toAddKeFuOrder(ModelMap mop){
		 //操作人
    	 UserInfo userInfo = UserInfoHolder.get();
    	 mop.put("userCode",userInfo.getUserCode());
    	 mop.put("userName",userInfo.getUserName());
		 mop.put("userIdCreate", userInfo.getUserId());
         //一级分类
         mop.put("categoryOneList", SystemParam.getCodeListByKey(DictionaryConstants.KEFU_CATEGORYONE_TYPE));
         mop.put("orderDescList", SystemParam.getCodeListByKey(DictionaryConstants.KEFU_ORDERDESC_TYPE));
         mop.put("orderProductList", SystemParam.getCodeListByKey(DictionaryConstants.KEFU_ORDERPRODUCT_TYPE));
		 ModelAndView mv = new ModelAndView( "keFuOrder/keFuOrderAdd");
		 return mv;
    } 
    /** 
     * @Description: 客服反馈工单编辑页面
     */
    @RequestMapping(value = "/toEidtKeFuOrder/{id}/{operatorFlag}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView toEidtKeFuOrder(@PathVariable("id") Integer id,@PathVariable("operatorFlag") Integer operatorFlag,ModelMap mop){
    	//operatorFlag为0，除归属城市、经纪公司、门店，其他可修改。operatorFlag为1，除了经办人项其他不可修改
    	mop.put("operatorFlag", operatorFlag);
    	//操作人
    	mop.put("userIdCreate", UserInfoHolder.get().getUserId());
    	//返回页面参数
    	ResultData<?> resultData = null;
        try{
            resultData = keFuOrderContractService.getKeFuOrderById(id);
            if(resultData != null){
            	mop.addAttribute("keFuOrder", resultData.getReturnData());
            }
        }catch (Exception e){
            logger.error("keFuOrder", "KeFuOrderContractController", "toEidtGpCsMemberContract", "", UserInfoHolder.getUserId(), "", "查看客服反馈工单失败", e);
        }
        //一级分类
        mop.put("categoryOneList", SystemParam.getCodeListByKey(DictionaryConstants.KEFU_CATEGORYONE_TYPE));
        mop.put("orderDescList", SystemParam.getCodeListByKey(DictionaryConstants.KEFU_ORDERDESC_TYPE));
        mop.put("orderProductList", SystemParam.getCodeListByKey(DictionaryConstants.KEFU_ORDERPRODUCT_TYPE));
    	ModelAndView mv = new ModelAndView( "keFuOrder/keFuOrderEdit");
    	return mv;
    } 
    /**
     * 保存客服反馈工单
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveKeFuOrder",method = RequestMethod.POST)
    public ResultData<?> saveKeFuOrder(HttpServletRequest request){
        ResultData<?> resultData = new ResultData<>();
        // 获取map
     	Map<String, Object> reqMap = bindParamToMap(request);
     	reqMap.put("userIdCreate", UserInfoHolder.get().getUserId());
     	//判断更新或保存
        if(!reqMap.containsKey("id")){
            String keFuOrderNo = SeqNoHelper.getInstance().getSeqNoByTypeCode("TYPE_KEFU_ORDER");
            reqMap.put("keFuOrderNo", keFuOrderNo);
            reqMap.put("type", "create");
        }else {
        	reqMap.put("type", "update");
        }
        if(reqMap.containsKey("selectConsultProduct")){
        	//咨询产品名称
        	String selectConsultProduct = (String)reqMap.get("selectConsultProduct");
        	String selectConsultProductNm = (String)reqMap.get("selectConsultProductNm");
        	if(selectConsultProduct!=null && !"".equals(selectConsultProduct)){
        		selectConsultProduct=selectConsultProduct.replaceAll(";",",");
        		selectConsultProductNm=selectConsultProductNm.replaceAll(";",",");
        		reqMap.put("selectConsultProduct", selectConsultProduct);
        		reqMap.put("selectConsultProductNm", selectConsultProductNm);
        	}
        }
        
        try{
           resultData = keFuOrderContractService.saveKeFuOrder(reqMap);
        }catch (Exception e){
            resultData.setFail("保存客服反馈工单失败");
            logger.error("keFuOrder", "KeFuOrderContractController", "saveKeFuOrder", "",UserInfoHolder.getUserId(), "", "保存客服反馈工单失败！", e);
        }
        return resultData;
    }
    /**
     * 根据id查看客服反馈工单详情
     * @param id
     * @param mop
     * @return
     */
    @RequestMapping(value = "getKeFuOrderById/{id}", method = RequestMethod.GET)
    public ModelAndView getKeFuOrderById(@PathVariable("id") Integer id, ModelMap mop){
    	ResultData<?> resultData = null;
        try{
            resultData = keFuOrderContractService.getKeFuOrderById(id);
            if(resultData != null){
            	mop.addAttribute("keFuOrder", resultData.getReturnData());
            }
        }catch (Exception e){
            logger.error("keFuOrder", "KeFuOrderContractController", "getKeFuOrderById", "", UserInfoHolder.getUserId(), "", "查看客服反馈工单详情失败", e);
        }

        //返回视图
        ModelAndView mv = new ModelAndView("keFuOrder/keFuOrderDetail");
        return mv;
    }
    /**
     * 选择经办人
     */
    @RequestMapping(value = "toChooseOperator", method = RequestMethod.GET)
    public String toChooseOperator(HttpServletRequest request, ModelMap mop){
        bindParamToAttrbute(request);
        return "keFuOrder/operatorListPopup";
    }
    /**
     * 获取经办人列表
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "/getOperatorList", method = RequestMethod.GET)
    public String getOperatorList(HttpServletRequest request, ModelMap mop){
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        //返回list
        ResultData<?> resultData = null;
        List<?> linkUserList = new ArrayList<>();
        try{
            resultData = keFuOrderContractService.getOperatorList(reqMap,pageInfo);
            if(resultData != null){
                linkUserList = (List<?>) resultData.getReturnData();
            }
        }catch (Exception e){
            logger.error("CRM", "KeFuOrderContractController", "getOperatorList", reqMap.toString(), null, "", "获取经办人列表", e);
        }
        //存放到mop中
        mop.addAttribute("linkUserList", linkUserList);
        return "houseLinkage/linkAchieveChange/achieveMentUserListCtxPopup2";
    }
    /**
     * 根据一级分类类别查询二级分类
     * @param request
     * @param mop
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getCategoryTwo", method = RequestMethod.GET)
    public ReturnView<?, ?> getCategoryTwo(HttpServletRequest request, ModelMap mop) {
        Map<String, Object> reqMap = bindParamToMap(request);
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try{
            ResultData<?> resultData = keFuOrderContractService.getCategoryTwo(reqMap);
            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        }catch (Exception e){
            logger.error("CRM", "KeFuOrderContractController", "getCategoryTwo", "", UserInfoHolder.getUserId(), "", "查询二级分类列表失败", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "查询二级分类列表失败");
        }
        return getMapView(rspMap);
    }
    /**
     * 导出EXCEL
     */
    @RequestMapping(value = "exportKeFuOrderList", method = RequestMethod.GET)
    public void exportKeFuOrderList(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        reqMap.put("optFlag", "export");
        try {
            ResultData<?> reback = keFuOrderContractService.getKeFuOrderList(reqMap, null);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();
            downLoadExcel(request, response, contentlist, reqMap, ReportConstant.KEFUORDER_CODE, ReportConstant.KEFUORDER_NAME);
        } catch (Exception e) {
            logger.error("CRM", "KeFuOrderContractController", "export", "input param: reqMap=" + reqMap.toString(), -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }

    }
    
    /**
     * desc:工单回复
     * 2019年3月19日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/reply", method = RequestMethod.GET)
    public ModelAndView reply( ModelMap mop,HttpServletRequest request) {
    	Map<String, Object> reqMap = bindParamToMap(request);
    	mop.addAttribute("reqMap", reqMap);
        ModelAndView mv = new ModelAndView("keFuOrder/keFuOrderReplyPop");
        return mv;
    }

    /**
     * desc:工单核验
     */
    @RequestMapping(value = "/verify",method = RequestMethod.GET)
    public ModelAndView verify(ModelMap mop,HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.addAttribute("reqMap", reqMap);
        ModelAndView mv = new ModelAndView("keFuOrder/keFuOrderVerifyPop");
        return mv;
    }

    /**
     * desc:更新回复
     * 2019年3月19日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/saveKeFuReply", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> saveKeFuReply(HttpServletRequest request) {
    	Map<String, Object> rspMap = new HashMap<String, Object>();
    	Map<String, Object> reqMap = bindParamToMap(request);
    	reqMap.put("userIdCreate", UserInfoHolder.get().getUserId());
        try{
            ResultData<?> resultData = keFuOrderContractService.saveKeFuReply(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        }catch (Exception e){
            logger.error("keFuOrder", "KeFuOrderContractController", "saveKeFulReply", "", UserInfoHolder.getUserId(), "", "客服回复失败", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "回复失败！");
        }
        return getMapView(rspMap);
    }

    /*
     工单核验保存
     */
    @RequestMapping(value = "/saveKeFuVerify", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?,?> saveKeFuVerify(HttpServletRequest request) {
        Map<String, Object> rspMap = new HashMap<String, Object>();
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userIdCreate", UserInfoHolder.get().getUserId());
        reqMap.put("opUserCode", UserInfoHolder.get().getUserCode());
        try {
            ResultData<?> resultData = keFuOrderContractService.saveKeFuVerify(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        } catch (Exception e) {
            logger.error("keFuOrder", "KeFuOrderContractController", "saveKeFuVerify", "", UserInfoHolder.getUserId(), "", "客服回复失败", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "核验失败！");
        }
        return getMapView(rspMap);
    }
    /**
     * 根据storeId查看客服反馈工单列表
     * @return
     */
    @RequestMapping(value = "getKeFuOrderListByStoreId/{storeId}", method = RequestMethod.GET)
    public ModelAndView getKeFuOrderListByStoreId(@PathVariable("storeId") Integer storeId, ModelMap mop){
    	ResultData<?> resultData = null;
        try{
            resultData = keFuOrderContractService.getKeFuOrderListByStoreId(storeId);
            if(resultData != null){
            	mop.addAttribute("content", resultData.getReturnData());
            }
        }catch (Exception e){
            logger.error("keFuOrder", "KeFuOrderContractController", "getKeFuOrderListByStoreId", "", UserInfoHolder.getUserId(), "", "根据门店Id查看客服反馈工单列表失败", e);
        }
        //返回视图
        ModelAndView mv = new ModelAndView("keFuOrder/storeKeFuOrder");
        return mv;
    }

    /**
     * 再次提醒经办人
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/reAlert",method = RequestMethod.POST)
    public ResultData<?> reAlert(HttpServletRequest request){
        ResultData<?> resultData = new ResultData<>();
        // 获取map
        Map<String, Object> reqMap = bindParamToMap(request);

        try{
            resultData = keFuOrderContractService.reAlert(reqMap);
        }catch (Exception e){
            resultData.setFail("提醒经办人失败");
            logger.error("keFuOrder", "KeFuOrderContractController", "reAlert", "",UserInfoHolder.getUserId(), "", "提醒经办人失败！", e);
        }
        return resultData;
    }
}
