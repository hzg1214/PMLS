package cn.com.eju.deal.keFuWj.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.keFuWj.dto.WjCity;
import cn.com.eju.deal.keFuWj.service.KeFuWjService;

/**
 * 
 * desc:客服问卷调查
 * @author :zhenggang.Huang
 * @date   :2019年6月17日
 */
@Controller
@RequestMapping(value = "keFuWj")
public class KeFuWjController extends BaseReportController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "keFuWjService")
    private KeFuWjService keFuWjService;
    
    /**
     * desc:初始化
     * 2019年6月17日
     * author:zhenggang.Huang
     */
 	@RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, ModelMap mop,HttpServletResponse response) throws Exception{
         //构建ModelAndView实例，并设置跳转地址
         ModelAndView mv = new ModelAndView("keFuWj/keFuWjList");
         UserInfo userInfo = UserInfoHolder.get();
         String cityNo = userInfo.getCityNo();
         if (StringUtil.isNotEmpty(cityNo)){
 	        Map<String, Object> map = bindParamToMap(request);
 	        if (map.containsKey("searchParam") && "1".equals(map.get("searchParam"))) {
 	            Map<String, Object> searchParamMap = getRememberSearch(request, ComConstants.KEFUWJ_LIST_SEARCH);
 	            mv.addObject("searchParamMap", JSON.toJSON(searchParamMap));
 	        } else {
 	            clearSearch(request, response, ComConstants.KEFUWJ_LIST_SEARCH);
 	        }
 	        mop.put("list_search_page", ComConstants.KEFUWJ_LIST_SEARCH);
         }

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.get().getUserId());
        return mv;
         
     }

	/**
	 * desc:列表
	 * 2019年6月17日
	 * author:zhenggang.Huang
	 */
    @RequestMapping(value = "getKeFuWjList", method = RequestMethod.GET)
    public ModelAndView getkeFuWjList(HttpServletRequest request, ModelMap mop,HttpServletResponse response){
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        //返回list
        ResultData<?> resultData = new ResultData<>();
        List<?> contentlist = new ArrayList<>();
        try {
        	if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.KEFUWJ_LIST_SEARCH, reqMap);
            }
            resultData = keFuWjService.getKeFuWjList(reqMap,pageInfo);
            contentlist = (List<?>) resultData.getReturnData();
        }catch (Exception e){
            logger.error("keFuWj", "keFuWjContractController", "getkeFuWjList", reqMap.toString(), null, "", "获取客服反馈工单列表失败", e);
        }
        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        ModelAndView mv = new ModelAndView("keFuWj/keFuWjListCtx");
        return mv;
    }

    /**
     * 问卷预览弹窗
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "tokeFuWjView", method = RequestMethod.GET)
    public ModelAndView tokeFuWjView(HttpServletRequest request, ModelMap mop){
        bindParamToAttrbute(request);
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            ResultData<?> resultData = keFuWjService.queryKeFuWjList(Integer.valueOf(reqMap.get("id").toString()));
            mop.put("KeFuWj",resultData.getReturnData());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mv = new ModelAndView("keFuWj/keFuWjViewPopup");
        return mv;
    }

    /**
     * 问卷绑定城市弹窗
     * @param id
     * @param mop
     * @return
     */
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public String modify(@PathVariable("id") int id, ModelMap mop) {
        try {
            ResultData<?> resultData = keFuWjService.queryKeFuWjHById(id);
            mop.put("keFuWjH",resultData.getReturnData());
            ResultData<?> cityData = (ResultData<List<WjCity>>) keFuWjService.getWjCityList();
            mop.put("cityList",cityData.getReturnData());
        } catch (Exception e) {
            logger.error("modify", "KeFuWjController", "modify", "",
                    UserInfoHolder.getUserId(), "", "修改Popup画面初始化-问卷模板维护信息失败！", e);

        }
        return "keFuWj/keFuWjModifyPopup";
    }
    /**
     * 
     * desc:获取适用城市列表
     * 2019年6月17日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "getWjCityList", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getWjCityList(HttpServletRequest request) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        //返回map
        ResultData<List<WjCity>> resultData = new ResultData<>();

        // 城市初始默认值
        Map<String, Object> queryParam = new HashMap<>();
        UserInfo userInfo = UserInfoHolder.get();
        if (userInfo != null) {
            queryParam.put("userId", userInfo.getUserId());
        }

        try {
            resultData = (ResultData<List<WjCity>>) keFuWjService.getWjCityList();
        } catch (Exception e) {
            logger.error("kefuwj", "KeFuWjController", "getWjCityList", "", null, "", "获取适用城市列表失败", e);
        }

        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());

        return getSearchJSONView(rspMap);
    }

    /**
     * @Title: queryCityAvailable
     * @Description: 查询城市是否绑定问卷
     */
    @ResponseBody
    @RequestMapping(value = "/getWjCheckCityList", method = RequestMethod.POST)
    public ReturnView<?, ?> getWjCheckCityList(HttpServletRequest request) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        try {
            //更新
            resultData = keFuWjService.getWjCheckCityList(reqMap);
        } catch (Exception e) {
            logger.error("keFuWj", "KeFuWjController", "finalize", "", UserInfoHolder.getUserId(), "", "状态变更失败", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "状态变更失败");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        rspMap.put(Constant.RETURN_DATA_KEY,resultData.getReturnData());
        return getOperateJSONView(rspMap);
    }

    /**
     * @Title: finalize
     * @Description: 问卷状态变更为未启用
     */
    @ResponseBody
    @RequestMapping(value = "/finalize", method = RequestMethod.POST)
    public ReturnView<?, ?> finalize(HttpServletRequest request) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.remove("_method");
        try {
            //更新
            keFuWjService.finalize(reqMap);
        } catch (Exception e) {
            logger.error("keFuWj", "KeFuWjController", "finalize", "", UserInfoHolder.getUserId(), "", "状态变更失败", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "状态变更失败");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }

    /**
     * @Title: queryCityAvailable
     * @Description: 查询城市是否绑定问卷
     */
    @ResponseBody
    @RequestMapping(value = "/queryCityIsAvailable", method = RequestMethod.POST)
    public ReturnView<?, ?> queryCityIsAvailable(HttpServletRequest request) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        String msg = null;
        try {
            //更新
            msg = keFuWjService.queryCityIsAvailable(reqMap);
        } catch (Exception e) {
            logger.error("keFuWj", "KeFuWjController", "finalize", "", UserInfoHolder.getUserId(), "", "状态变更失败", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "状态变更失败");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        rspMap.put(Constant.RETURN_MSG_KEY,msg);
        return getOperateJSONView(rspMap);
    }

    /**
     * 问卷绑定城市
     * @param request
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> update(HttpServletRequest request) {

        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        //构建返回
        ResultData<?> resultData = new ResultData<>();

        try {
            resultData = keFuWjService.update(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        } catch (Exception e) {
            logger.error("更新异常", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "更新失败！");
        }
        return getMapView(rspMap);
    }

    /**
     * desc:导入
     * 2019年6月19日
     * author:zhenggang.Huang
     */
     @RequestMapping(value = "imput", method = RequestMethod.POST)
     @ResponseBody
     public ReturnView<?, ?> imput(HttpServletRequest request, HttpServletResponse response)
     {
    	 // 返回map
         Map<String, Object> rspMap = new HashMap<String, Object>();
         
         //获取请求参数
         Map<String, Object> reqMap = bindParamToMap(request);
         
         try {
			reqMap = keFuWjService.wjImport(reqMap,rspMap,request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("keFuWj", "KeFuWjController", "imput", "", UserInfoHolder.getUserId(), "", "问卷导入失败", e);
		}
		
		return getSearchJSONView(rspMap);      
     }

    /**
     * 问卷删除
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> remove(HttpServletRequest request, ModelMap mop) {

        Map<String, Object> rspMap = new HashMap<String, Object>();

        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);

        try {
            keFuWjService.remove(reqMap);
        } catch (Exception e) {
            logger.error("KeFuWjH", "KeFuWjController", "remove", "",
                    UserInfoHolder.getUserId(), "", "删除问卷处理失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "删除失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }
    
    /**
     * desc:下载模板
     * 2019年6月21日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "exportWjMbById", method = RequestMethod.GET)
    public void exportWjMbById(HttpServletRequest request,HttpServletResponse response) {

        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            ResultData<?> reback = keFuWjService.queryKeFuWjList(Integer.valueOf(reqMap.get("id").toString()));
        	LinkedHashMap<String, Object> lh= (LinkedHashMap<String, Object>) reback.getReturnData();
        	List<LinkedHashMap<String, Object>> contentlist = new ArrayList<>();
        	contentlist.add(lh);
        	downLoadExcel(request, response, contentlist, reqMap, ReportConstant.KEFUWJ_CODE, ReportConstant.KEFUWJ_NAME);

        } catch (Exception e) {
        	e.printStackTrace();
            logger.error("kefuwj", "KefuWjController", "exportWjMbById", null, -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }
    }
    
    /**
     * 
     * desc:已调查列表
     * 2019年7月29日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "getInvestedList/{storeId}", method = RequestMethod.GET)
    public ModelAndView getInvestedList(@PathVariable("storeId") Integer storeId, ModelMap mop){
    	ResultData<?> resultData = null;
        try{
            resultData = keFuWjService.getInvestedList(storeId);
            if(resultData != null){
            	mop.addAttribute("contentlist", resultData.getReturnData());
            }
        }catch (Exception e){
            logger.error("keFuWj", "KefuWjController", "getKeFuOrderListByStoreId", "", UserInfoHolder.getUserId(), "", "根据门店Id查看问卷已调查列表失败", e);
        }
      //存放到mop中
        mop.addAttribute("storeId", storeId);
        ModelAndView mv = new ModelAndView("keFuWj/storeKeFuInvestedList");
        return mv;
    }

    /**
     * 已测评列表
     * @param storeId
     * @param mop
     * @return
     */
    @RequestMapping(value = "getEvaluationList/{storeId}", method = RequestMethod.GET)
    public ModelAndView getEvaluationList(@PathVariable("storeId") Integer storeId, ModelMap mop){
    	ResultData<?> resultData = null;
        try{
            resultData = keFuWjService.getEvaluationList(storeId);
            if(resultData != null){
            	mop.addAttribute("contentlist", resultData.getReturnData());
            }
        }catch (Exception e){
            logger.error("keFuWj", "KefuWjController", "getKeFuOrderListByStoreId", "", UserInfoHolder.getUserId(), "", "根据门店Id查看测评已测评列表失败", e);
        }
      //存放到mop中
        mop.addAttribute("storeId", storeId);
        ModelAndView mv = new ModelAndView("keFuWj/storeKeFuEvaluationList");
        return mv;
    }

    /**
     * desc:已调查列表查看
     * 2019年7月29日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "toInvestedView", method = RequestMethod.GET)
    public ModelAndView kefuInvestedView(HttpServletRequest request, ModelMap mop){
        bindParamToAttrbute(request);
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        Integer storeId = Integer.parseInt((String) reqMap.get("storeId"));
        Integer id = Integer.parseInt((String) reqMap.get("id"));
        String storeNo = (String) reqMap.get("storeNo");
        mop.addAttribute("storeNo", storeNo);
        //获取门店信息
        try {
        	ResultData<?> resultData = keFuWjService.getStoreData(storeId);
            if ("200".equals(resultData.getReturnCode())) {
            	mop.addAttribute("storeData", resultData.getReturnData());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("keFuWj", "KefuWjController", "toInvestedView", "", UserInfoHolder.getUserId(), "", "查看问卷调查信息失败", e);
        }
        //获取问卷调查信息
        try {
        	if (id > 0) {
        		ResultData<?> relDetail = keFuWjService.getSurveyData(id);
        		if ("200".equals(relDetail.getReturnCode())) {
        			mop.addAttribute("surveyData", relDetail.getReturnData());
        		}
        	} else {
        		mop.addAttribute("surveyData", JsonUtil.parseToJson(""));
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        	logger.error("keFuWj", "KefuWjController", "toInvestedView", "", UserInfoHolder.getUserId(), "", "查看问卷调查信息失败", e);
        }
        ModelAndView mv = new ModelAndView("keFuWj/kefuInvestedViewPopup");
        return mv;
    }

    @RequestMapping(value = "toEvaluationView", method = RequestMethod.GET)
    public ModelAndView kefuEvaluationView(HttpServletRequest request, ModelMap mop){
        bindParamToAttrbute(request);
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        Integer storeId = Integer.parseInt((String) reqMap.get("storeId"));
        Integer id = Integer.parseInt((String) reqMap.get("id"));
        String storeNo = (String) reqMap.get("storeNo");
        mop.addAttribute("storeNo", storeNo);
        //获取门店信息
        try {
            ResultData<?> resultData = keFuWjService.getStoreData(storeId);
            if ("200".equals(resultData.getReturnCode())) {
                mop.addAttribute("storeData", resultData.getReturnData());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("keFuWj", "KefuWjController", "toEvaluationView", "", UserInfoHolder.getUserId(), "", "查看测评信息失败", e);
        }
        //获取问卷调查信息
        try {
            if (id > 0) {
                ResultData<?> relDetail = keFuWjService.getEvaluationData(id);
                if ("200".equals(relDetail.getReturnCode())) {
                    mop.addAttribute("evaluationData", relDetail.getReturnData());
                }
            } else {
                mop.addAttribute("evaluationData", JsonUtil.parseToJson(""));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("keFuWj", "KefuWjController", "toEvaluationView", "", UserInfoHolder.getUserId(), "", "查看测评信息失败", e);
        }
        ModelAndView mv = new ModelAndView("keFuWj/kefuEvaluationViewPopup");
        return mv;
    }
}
