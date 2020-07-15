package cn.com.eju.deal.scene.commission.controller;

import java.util.HashMap;
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

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.linkage.service.LinkageCityService;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.houseLinkage.estate.service.EstateService;
import cn.com.eju.deal.scene.commission.service.LnkYjReportService;
import cn.com.eju.deal.scene.commission.service.SceneCommissionService;

/**
 * desc:返佣对象维护
 * @author :zhenggang.Huang
 * @date   :2019年5月5日
 */
@Controller
@RequestMapping(value = "lnkYjReport")
public class LnkYjReportController extends BaseController
{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "sceneCommissionService")
    private SceneCommissionService sceneCommissionService;
    
    @Resource(name = "lnkYjReportService")
    private LnkYjReportService lnkYjReportService;
    
    @Resource(name = "linkageCityService")
    private LinkageCityService linkageCityService;
    
    @Resource(name = "estateService")
    private EstateService estateService;
    
    /** 
    * 初始化
    * @param request
    * @param model
    * @return
    */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap mop, HttpServletResponse response)
    {
        String cityNo = UserInfoHolder.get().getCityNo();
        //区域列表
        ResultData<?> resultDistrictList = new ResultData<>();
        try
        {
            resultDistrictList = this.linkageCityService.getDistrictList(cityNo);
        }
        catch (Exception e)
        {
            logger.error("sceneCommission", "LnkYjReportController", "", "", null, "", "", e);
        }
      //归属项目部列表
        try{
        	Map<String,Object> reqMap = new HashMap<>();
        	reqMap.put("cityNo", cityNo);
        	ResultData<?> resultData = estateService.getProjectDepartment(reqMap);
        	List<?> rebacklist = (List<?>) resultData.getReturnData();
        	mop.put("rebacklist", rebacklist);
        }catch(Exception e)
        {
        	 logger.error("sceneInCommission", "LnkYjReportController", "", "", null, "", "创建--初始化-归属项目部", e);
        }
        
        mop.put("districtList", resultDistrictList.getReturnData());

        try{
            Map<String,Object> reqMap = new HashMap<>();
            reqMap.put("cityNo", cityNo);
            ResultData<?> resultData = estateService.getEstateNmList(reqMap);
            List<?> estateList = (List<?>) resultData.getReturnData();
            mop.put("estateList", estateList);
            mop.addAttribute("estateTypeList", SystemParam.getCodeListByKey("245"));
        } catch (Exception e) {
            logger.error("sceneInCommission", "LnkYjReportController", "", "", null, "", "创建--初始化-楼盘名称", e);
        }
        
        Map<String, Object> map = bindParamToMap(request);
        if (map.containsKey("searchParam") && "1".equals(map.get("searchParam"))) {
            Map<String, Object> searchParamMap = getRememberSearch(request, ComConstants.LNKYJREPORT_LIST_SEARCH);
            if(searchParamMap.containsKey("estateNmKey")) {//不记住楼盘
            	searchParamMap.remove("estateNmKey");
            }
            mop.put("searchParamMap", JSON.toJSON(searchParamMap));
        } else {
            clearSearch(request, response, ComConstants.LNKYJREPORT_LIST_SEARCH);
        }
        mop.put("list_search_page", ComConstants.LNKYJREPORT_LIST_SEARCH);
        return "scene/commission/lnkYjReportList";
    }
    
    /**
     * desc:列表
     * 2019年4月29日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "getLnkYjReprotList", method = RequestMethod.GET)
    public String getLnkYjReprotList(HttpServletRequest request, ModelMap mop, HttpServletResponse response) {
        String cityNo = UserInfoHolder.get().getCityNo();
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        bindParamToAttrbute(request);
        PageInfo pageInfo = getPageInfo(request);
        //获取页面显示数据
        ResultData<?> reback = new ResultData<>();
        try {
        	if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.LNKYJREPORT_LIST_SEARCH, reqMap);
            }
            reqMap.put("cityNo", cityNo);
            reback = lnkYjReportService.getLnkYjReportList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("lnkYjReport", "lnkYjReportController", "getLnkYjReportList", "", null, "", "", e);
            reback.setFail();
        }

        //页面数据
        List<?> contentlist = (List<?>) reback.getReturnData();
        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        
        return "scene/commission/lnkYjReportListCtx";

    }
    
    /**
     * 
     * desc:查看
     * 2019年4月29日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "getYjReportDetaileById/{ReportId}", method = RequestMethod.GET)
    public ModelAndView getYjReportDeatilById(
    		@PathVariable("ReportId") String ReportId, ModelMap mop){
    	ResultData<?> resultData = new ResultData<>();
       try{
       	 Map<String, Object> map = new HashMap<>();
       	 map.put("reportId", ReportId);
            resultData = lnkYjReportService.getYjReportDeatilById(map);
        }catch (Exception e){
            logger.error("lnkYjReport", "lnkYjReportController", "getYjReportDeatilById", "", UserInfoHolder.getUserId(), "", "查看返佣维护对象详情失败", e);
        }
       	mop.addAttribute("yjReportInfo", resultData.getReturnData());
        //返回视图
        ModelAndView mv = new ModelAndView("scene/commission/lnkYjReportDetail");
        return mv;
    }
    
    /**
     * desc:返佣维护初始化
     * 2019年4月30日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "maintenance", method = RequestMethod.GET)
    public ModelAndView toMaintenanceList(HttpServletRequest request, ModelMap mop)
    {
//        bindParamToAttrbute(request);
    	Map<String, Object> reqMap = bindParamToMap(request);
    	String reportinfoStr = (String) reqMap.get("reprotinfoStr");
    	String projectNo = (String) reqMap.get("projectNo");
    	if(!StringUtil.isEmpty(reportinfoStr)) {
    		String[] reportInfo = reportinfoStr.split(",");//2246|BB2016120900004
    		String ids = "";
    		String reportIds = "";
    		for(String report : reportInfo) {
    			ids += report.split("#")[0]+",";
    			reportIds += report.split("#")[1]+",";
    		}
    		mop.addAttribute("ids", ids.substring(0,ids.length()-1));
    		mop.addAttribute("reportIds", reportIds.substring(0,reportIds.length()-1));
    		if(reportInfo.length>0) {
    			mop.addAttribute("size", reportInfo.length);
    		}
    	}
    	mop.addAttribute("projectNo", projectNo);
//    	mop.addAttribute("projectNo", "FYSH0491");
        ModelAndView mv = new ModelAndView("scene/commission/lnkYjReportListPopup");
        return mv;
    }
    
    
    /**
     * desc:保存返佣对象
     * 2019年4月30日
     * author:zhenggang.Huang
     */
    @RequestMapping(value="saveMaintenance", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView saveMaintenance (HttpServletRequest request){
        ReturnView returnView = new ReturnView();
        ResultData resultData = new ResultData();
        resultData.setFail();
        //请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("crtEmpId",UserInfoHolder.get().getUserId());
        try {
            resultData = lnkYjReportService.saveMaintenance(reqMap);
        } catch (Exception e) {
            resultData.setFail("保存维护人异常!");
            logger.error("PMLS", "LnkYjReportController", "saveMaintenance", reqMap.toString(), null, "", "佣金维护变更异常", e);
        }
        //returnView.setOperateReturnType();
        returnView.setReturnCode(resultData.getReturnCode());
        returnView.setReturnMsg(resultData.getReturnMsg());
        //returnView.setReturnValue(resultData.getReturnData());
        return returnView;
    }
    
    /**
     * desc:模糊查询公司
     * 2019年5月5日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "getCompanyByCondition", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getCompanyByCondition(HttpServletRequest request,ModelMap mop){
    	ResultData<?> resultData = new ResultData<>();
    	Map<String, Object> reqMap = bindParamToMap(request);
    	//返回map
    	Map<String, Object> rspMap = new HashMap<String, Object>();
    	String estateId = (String) reqMap.get("estateId");
    	try {
    		PageInfo pageInfo = getPageInfo(request);
    		resultData = lnkYjReportService.getCompanyByCondition(reqMap,null);
    	}catch (Exception e){
    		logger.error("PMLS",
    				"LnkYjReportController",
    				"getCompanyByCondition",
    				"",
    				UserInfoHolder.getUserId(),
    				"",
    				"模糊查询公司",
    				e);
    	}
    	rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
    	rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
    	if (null != resultData.getReturnData()) {
    		rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
    	}
    	return getMapView(rspMap);
    }
    
    /**
     * desc:编辑初始化
     * 2019年5月10日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/u/{ReportId}", method = RequestMethod.GET)
    public String toEdit(@PathVariable("ReportId") String ReportId,HttpServletRequest request, ModelMap mop)
    {
        Map<String, Object> map = new HashMap<>();
        //返回map
        ResultData<?> resultData = new ResultData<>();
        Map<String, Object> reqMap = bindParamToMap(request);
        try
        {
          	map.put("reportId", ReportId);
            resultData = lnkYjReportService.getYjReportDeatilById(map);
        }
        catch (Exception e)
        {
            logger.error("lnkYjReport", "lnkYjReportController", "toEdit", "", UserInfoHolder.getUserId(), "", "编辑返佣对象失败", e);
        }
        mop.addAttribute("yjReportInfo", resultData.getReturnData());
//        mop.addAttribute("projectNo", "FYSH0491");
        return "scene/commission/lnkYjReportEdit";
    }
    
    /**
     * desc:保存编辑
     * 2019年5月10日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/saveEdit/{ReportId}", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnView saveEdit(@PathVariable("ReportId") String ReportId,HttpServletRequest request, ModelMap mop)
    {
    	ReturnView returnView = new ReturnView();
    	Map<String, Object> map = new HashMap<>();
    	//返回map
    	ResultData<?> resultData = new ResultData<>();
    	Map<String, Object> reqMap = bindParamToMap(request);
    	reqMap.put("crtEmpId",UserInfoHolder.get().getUserId());
    	try
    	{
    		resultData = lnkYjReportService.saveEditMaintenance(reqMap);
    	}
    	catch (Exception e)
    	{
    		logger.error("lnkYjReport", "lnkYjReportController", "saveEdit", "", UserInfoHolder.getUserId(), "", "编辑返佣对象失败", e);
    	}
    	returnView.setOperateReturnType();
        returnView.setReturnCode(resultData.getReturnCode());
        returnView.setReturnMsg(resultData.getReturnMsg());
        returnView.setReturnValue(resultData.getReturnData());
        return returnView;
    }
}
