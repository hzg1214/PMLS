package cn.com.eju.deal.houseLinkage.report.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.service.CommonService;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.contract.ContractInfoDto;
import cn.com.eju.deal.dto.houseLinkage.report.ReportDto;
import cn.com.eju.deal.dto.houseLinkage.report.ReportInfoDto;
import cn.com.eju.deal.houseLinkage.estate.service.EstateService;
import cn.com.eju.deal.houseLinkage.linkAchieveChange.service.AchieveChangeLogService;
import cn.com.eju.deal.houseLinkage.linkAchieveChange.service.LinkAchieveChangeService;
import cn.com.eju.deal.houseLinkage.report.service.ReportService;
import cn.com.eju.deal.user.service.UserService;

/**   
* Controller层
* @author qianwei
* @date 2016年4月29日 下午9:25:30
*/
@Controller
@RequestMapping(value = "report")
public class ReportController extends BaseController
{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "reportService")
    private ReportService reportService;

    @Resource(name = "achieveChangeLogService")
    private AchieveChangeLogService achieveChangeLogService;
    
    @Resource(name = "commonService")
    private CommonService commonService;
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "linkAchieveChangeService")
    private LinkAchieveChangeService linkAchieveChangeService;
    
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
        // 进度列表
        ResultData<?> resultProcessList = new ResultData<>();
        try
        {
            resultProcessList = reportService.getProcessList();
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.report", "ReportController", "list", "", null, "", "进度列表初始化！", e);
        }
        mop.put("processList", resultProcessList.getReturnData());
      //得到归属项目部 add by zhenggang.Huang 2019/07/12 begin
        List<?> rebacklist = null;
        try{
        	Map<String,Object> reqMap = new HashMap<>();
        	String cityNo = UserInfoHolder.get().getCityNo();
        	reqMap.put("cityNo", cityNo);
        	ResultData<?> resultData = estateService.getProjectDepartment(reqMap);
        	rebacklist = (List<?>) resultData.getReturnData();
        }catch(Exception e)
        {
        	 logger.error("houseLinkage.report", "ReportController", "list", "", null, "", "创建--初始化-归属项目部", e);
        }

        mop.put("rebacklist", rebacklist);
        //end
        //读取搜索条件 add by wangkanlin 2017/08/23
        Map<String, Object> map = bindParamToMap(request);
        if(map.containsKey("searchParam") && "1".equals(map.get("searchParam"))){
            Map<String,Object> searchParamMap = getRememberSearch(request, ComConstants.REPORT_LIST_SEARCH);
            mop.put("searchParamMap", JSON.toJSON(searchParamMap));
        }else{
            clearSearch(request, response, ComConstants.REPORT_LIST_SEARCH);
        }
        mop.put("list_search_page", ComConstants.REPORT_LIST_SEARCH);
        
        return "houseLinkage/report/reportList";
    }
    
    /** 
    * 查询--list
    * @param request
    * @param mop
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "q", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap mop, HttpServletResponse response)
    {
        
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        
        if(reqMap.containsKey("estateNm") && null!=reqMap.get("estateNm")){
        	String estateNm = reqMap.get("estateNm").toString();
        	estateNm = estateNm.trim();
        	reqMap.put("estateNm", estateNm);
        }
        
        //页面数据
        List<?> contentlist = null;
        try
        {
        	//保存搜索数据 add by wangkanlin 2017-08-23
            if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.REPORT_LIST_SEARCH, reqMap);
            }
            PageInfo pageInfo = getPageInfo(request);
            
            //获取页面显示数据
            ResultData<?> reback = reportService.index(reqMap, pageInfo);
            
            contentlist = (List<?>)reback.getReturnData();
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.report", "ReportController", "index", "", null, "", "查询--list", e);
        }
        
        //存放到mop中
        mop.addAttribute("userAcCityNo", UserInfoHolder.get().getCityNo());
        mop.addAttribute("contentlist", contentlist);

        return "houseLinkage/report/reportListCtx";
    }
    
    
    /**
     *  退筹，退订，退房 
     *  @param request
     * @throws Exception 
     */
    @RequestMapping(value = "/reback", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnView<?, ?> updateReback(HttpServletRequest request)
    {
        
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.remove("_method");
        ReportDto reportDto = new ReportDto();
        //修改主表
        ResultData<?> resultData = new ResultData<>();
        try
        {
        	setReportDto(reqMap, reportDto);
        	reportDto.setOperUserCode(UserInfoHolder.get().getUserCode());
        	reportDto.setOperUserName(UserInfoHolder.get().getUserName());
            //更新
        	resultData = reportService.updateReback(reportDto);
        }
        catch (Exception e)
        {
        	rspMap.put(Constant.RETURN_DATA_KEY, "500");
            logger.error("report", "ReportController", "updateReback", "", null, "", "修改 ", e);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        if (null != resultData.getReturnData())
        {
            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        }
        return getMapView(rspMap);
    }
    
    
    /** 
     * 设置DTO
     * @param reqMap
     * @param ReportDto
     */
    public static void setReportDto(Map<String, Object> reqMap, ReportDto reportDto) throws Exception
    {
       String estateId = (String)reqMap.get("estateId");
       if(StringUtil.isNotEmpty(estateId)){
    	   reportDto.setEstateId(estateId);
       }
       
       String reportId = (String)reqMap.get("reportId");
       if(StringUtil.isNotEmpty(reportId)){
    	   reportDto.setReportId(reportId);
       }
       
       String status = (String)reqMap.get("status");
       String operateDate = (String) reqMap.get("operateDate");
       Date date = getFormatStringDate(operateDate+" 00:00:00","yyyy-MM-dd HH:mm:ss");
       if("13503".equals(status)){//退筹
    	   reportDto.setPledgedBackDate(date);
       }else if("13504".equals(status)){//退定
    	   reportDto.setRoughBackDate(date);
       }else if("13505".equals(status)){//退房
    	   reportDto.setDealBackDate(date);
       }
    }
    
    
    
    
    
    
    
    
    
    
    /** 
    * 创建--初始化
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "c", method = RequestMethod.GET)
    public String toAdd(ModelMap mop)
    {
        //返回map
        ResultData<?> resultData = new ResultData<ContractDto>();
        try
        {
            UserInfo userInfo = UserInfoHolder.get();
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.report", "ReportController", "toAdd", "", null, "", "创建--初始化", e);
        }
        return "houseLinkage/report/reportAdd";
    }
    
    /**
     *  创建
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
        
        ContractInfoDto contractInfoDto = new ContractInfoDto();
        try
        {
            //获取页面字段值，转为DTO
            setDto(reqMap, contractInfoDto, "create", "");
            
//            ResultData<?> resultData = reportService.create(contractInfoDto);
//            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
//            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.report", "ReportController", "index", "", null, "", "创建", e);
        }
        
        return getOperateJSONView(rspMap);
    }
    
    /**
     *  修改 
     *  @param request
     * @throws Exception 
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnView<?, ?> update(HttpServletRequest request, @RequestParam(value = "isform", required = false) String isform, @PathVariable("id") String id)
    {
        
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.remove("_method");
        ContractInfoDto contractInfoDto = new ContractInfoDto();
        try
        {
            setDto(reqMap, contractInfoDto, "update", id);
            //更新
//            reportService.update(contractInfoDto);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.report", "ReportController", "update", "", null, "", "修改 ", e);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        
        return getOperateJSONView(rspMap);
    }

    /**
     * 查看
     * @param estateId
     * @param companyId
     * @param customerId
     * @param relateId
     * @param mop
     * @return
     */
    @RequestMapping(value = "/{estateId}/{companyId}/{customerId}/{relateId}/{fromType}", method = RequestMethod.GET)
    public String show(@PathVariable("estateId") String estateId, @PathVariable("companyId") String companyId, @PathVariable("customerId") String customerId, @PathVariable("relateId") Integer relateId, @PathVariable("fromType") Integer fromType, ModelMap mop)
    {
        //返回map
        ResultData<?> resultData = new ResultData<ReportInfoDto>();
        ResultData<?> resultData1 = new ResultData<>();
        String dataSwitch = "0";
        try
        {
            resultData = reportService.getReport(estateId, companyId, customerId);
            
            ResultData<?> resultDataSwitch 	= null;
            if(resultData != null){
            	Map<String, Object> reportSearchResultDto = (Map<String, Object>) resultData.getReturnData();
            	if(reportSearchResultDto!=null && reportSearchResultDto.get("report")!=null){
            		Map<String, Object> reportMap = (Map<String, Object>) reportSearchResultDto.get("report");
            		if(reportMap!=null && reportMap.get("cityNo")!=null && reportMap.get("dealDate")!=null){
            			String cityNo = reportMap.get("cityNo").toString();
            			String dealDate = reportMap.get("dealDate").toString();
            			resultDataSwitch = commonService.getSwitchInfo(cityNo);
                        Map<String, Object> map = (Map<String, Object>) resultDataSwitch.getReturnData();
                        if(null != map.get("yearMonth") && StringUtil.isNotEmpty(dealDate)){
                       	 	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                       	 	Date yearMonthDate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("yearMonth").toString()); 
                       	 	Date dealDateDate = new SimpleDateFormat("yyyy-MM-dd").parse(dealDate); 
                       	 	if(dealDateDate.before(yearMonthDate)){
                       	 		dataSwitch = "1";
                       	 	}
//                       	 	String dateString = formatter.format(yearMonthDate);  
//                       	 	mop.put("yearMonth", yearMonthDate);
                        }
            		}
            	}
            }
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.report", "ReportController", "show", "", null, "", "查看 ", e);
        }

        try {
            resultData1 = achieveChangeLogService.getLogList(relateId);
            if(resultData1 != null){
                mop.addAttribute("LogInfo", resultData1.getReturnData());
            }
        } catch (Exception e) {
            logger.error("PMLS", "ReportController", "show", "", null, "", "查询业绩调整日志异常", e);
        }

        //存放到mop中
        mop.addAttribute("userAcCityNo", UserInfoHolder.get().getCityNo());
        mop.addAttribute("dataSwitch", dataSwitch);
        mop.addAttribute("reportInfo", resultData.getReturnData());
        mop.addAttribute("fromType", fromType);
        //弹窗用
        mop.addAttribute("estateId", estateId);
        mop.addAttribute("companyId", companyId);
        mop.addAttribute("customerId", customerId);
        mop.addAttribute("relateId", relateId);
        return "houseLinkage/report/reportDetail";
    }
    
    /** 
    * 删除
    * @param id
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnView<?, ?> destroy(@PathVariable int id, HttpServletResponse response)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        //获取当前操作人usreId
        int updateId = 0;
        
        try
        {
            updateId = UserInfoHolder.getUserId();
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.report", "ReportController", "destroy", "", null, "", "删除 ", e);
        }
        
        try
        {
            //删除
            reportService.delete(id, updateId);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.report", "ReportController", "destroy", "", null, "", "删除 ", e);
        }
        
        //响应码
        int status = response.getStatus();
        
        rspMap.put(Constant.RETURN_CODE_KEY, status);
        
        return getOperateJSONView(rspMap);
        
    }
    
    /** 
     * 获取页面字段值，转为DTO
     * @param reqMap
     * @param contractInfoDto
     */
    public static void setDto(Map<String, Object> reqMap, ContractInfoDto contractInfoDto, String type, String id)
        throws Exception
    {
        UserInfo userInfo = UserInfoHolder.get();
        
    }
    
    /**
    * 根据日期字符串返回日期对象
    * @param sDat 日期字符串 
    * @param strFormat 格式
    * @return 日期对象
     * @throws ParseException 
    */
    private static Date getFormatStringDate(String sDat, String strFormat) throws ParseException{
            if (StringUtil.isNotEmpty(sDat)){
                // 解析日期 
                SimpleDateFormat myFmt = new SimpleDateFormat(strFormat);
                return myFmt.parse(sDat);
            }
            return null;
    }
    
    @RequestMapping("toOperDetail")
    public String toOperDetail(HttpServletRequest request,ModelMap mop) {
        Map<String, Object> reqMap = bindParamToMap(request);
        
        try {
            ResultData<?> resultData = reportService.getOperDetail(reqMap);
            
            mop.put("info", resultData.getReturnData());
            mop.put("reqMap", reqMap);
        }catch (Exception e) {
            logger.error("houseLinkage.report", "ReportController", "toOperDetail", "", null, "", "业务节点详情查询 ", e);
        }
        
        return "houseLinkage/report/reportOperDtl";
    }
    
    @RequestMapping("toOperDetailUpdate")
    public String toOperDetailUpdate(HttpServletRequest request,ModelMap mop) {
        Map<String, Object> reqMap = bindParamToMap(request);
        
        try {
            ResultData<?> resultData = reportService.getOperDetail(reqMap);
            ResultData<?> resultDataSwitch 	= null;
            if(resultData != null){
            	mop.put("info", resultData.getReturnData());
            	Map<String, Object> reportSearchResultDto = (Map<String, Object>) resultData.getReturnData();
            	if(reportSearchResultDto!=null && reportSearchResultDto.get("estateCityNo")!=null){
            		String cityNo = reportSearchResultDto.get("estateCityNo").toString();
            		 resultDataSwitch = commonService.getSwitchInfo(cityNo);
                     Map<String, Object> map = (Map<String, Object>) resultDataSwitch.getReturnData();
                     if(null != map.get("yearMonth")){
                    	 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                    	 Date date = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("yearMonth").toString()); 
                    	 String dateString = formatter.format(date);  
                    	 mop.put("yearMonth", dateString);
                     }
                    List<Map<String,Object>> accountProjectList =  (List<Map<String,Object>>)reportService.getAccountProject(UserInfoHolder.get().getCityNo()).getReturnData();
                    mop.put("accountProjectList", accountProjectList);
            	}
            }
        }catch (Exception e) {
            logger.error("houseLinkage.report", "ReportController", "toOperDetailUpdate", "", null, "", "跳转业务节点详情修改 ", e);
        }
        
        return "houseLinkage/report/reportOperDtlUpdate";
    }
    
    @RequestMapping("operDetailUpdate/{reportId}/{reportDetailId}")
    @ResponseBody
    public String operDetailUpdate(HttpServletRequest request,ModelMap mop,@PathVariable Integer reportId,@PathVariable Integer reportDetailId) {
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("reportId", reportId);
        reqMap.put("reportDetailId", reportDetailId);
        
        reqMap.put("userCode", UserInfoHolder.get().getUserCode());
        reqMap.put("userName", UserInfoHolder.get().getUserName());
        reqMap.put("userId", UserInfoHolder.getUserId());
        
        ResultData<?> resultData = null;
        try {
            resultData = reportService.operDetailUpdate(reqMap);
        }catch (Exception e) {
            resultData = new ResultData<>();
            resultData.setFail("系统未知异常");
            logger.error("houseLinkage.report", "ReportController", "operDetailUpdate", "", null, "", "业务节点详情修改 ", e);
        }
        
        return resultData.toString();
    }
    
    @RequestMapping(value = "selectBuildingNoRepeatCount", method = RequestMethod.POST)
    @ResponseBody
    public String selectBuildingNoRepeatCount(HttpServletRequest request) {
        ResultData<?> resultData = new ResultData<>();
        Map<String, Object> map = bindParamToMap(request);
        try {
            resultData = reportService.selectBuildingNoRepeatCount(map.get("buildingNo").toString(), map.get("reportId").toString());
     
        } catch (Exception e) {
            resultData.setFail("楼室号判重查询异常");
            logger.error("houseLinkage.report", "ReportController", "selectBuildingNoRepeatCount"
           		 , JsonUtil.parseToJson(map), null, "", "楼室号判重查询异常", e);
        }
        return resultData.toString();
    }
    /** 
     * @Title: editFyAcountMode 
     * @Description: 业绩调整弹窗
     */
    @RequestMapping(value = "/editAchievementMode", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editAchievementMode(HttpServletRequest request,ModelMap mop){
   	 //请求map
   	 Map<String, Object> reqMap = bindParamToMap(request);
   	 //报备编号
   	 mop.put("reportId", reqMap.get("reportId").toString());
   	 //客户
   	 mop.put("customerNm", reqMap.get("customerNm").toString());
   	 mop.put("customerTel", reqMap.get("customerTel").toString());
   	 //联系人
   	 mop.put("oldContactId", reqMap.get("contactId").toString());
   	 mop.put("oldContactNm", reqMap.get("contactNm").toString());
   	 //原来所属中心
   	 mop.put("oldCenterGroupId", reqMap.get("centerGroupId").toString());
   	 mop.put("oldCenterGroupName", reqMap.get("centerGroupName").toString());
   	 //操作人
   	 mop.put("userCode", UserInfoHolder.get().getUserCode());
   	 mop.put("userName", UserInfoHolder.get().getUserName());
   	 mop.put("userIdCreate", UserInfoHolder.get().getUserId());
   	 //返回页面参数
     mop.put("estateId", reqMap.get("estateId").toString());
     mop.put("companyId", reqMap.get("companyId").toString());
     mop.put("customerId", reqMap.get("customerId").toString());
     mop.put("relateId", reqMap.get("relateId").toString());
     mop.put("fromType", reqMap.get("fromType").toString());
     
     /*Map<String, Object> reqMap2 = new HashMap<String, Object>();
     ResultData<?> resultData = new ResultData<>();
     try {
    	 reqMap2.put("cityNo", UserInfoHolder.get().getCityNo());
    	 reqMap2.put("adjutFlag", 1);
         resultData = this.userService.getLinkUser(reqMap2,null);
     }catch (Exception e){
     	logger.error("houseLinkage.report",
                 "ReportController",
                 "getLinkUser",
                 "",
                 UserInfoHolder.getUserId(),
                 "",
                 "获取业绩人列表",
                 e);
     }
     if(null != resultData.getReturnData()) {
    	 mop.put("linkUserList", resultData.getReturnData());
     }*/
   	 ModelAndView mv = new ModelAndView( "houseLinkage/report/achievementAdjustModePopup");
   	 return mv;
    } 
    
    /**
     * 获取业绩人列表
     * @param mop
     * @return
     */
    @RequestMapping(value = "getLinkUser", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getLinkUser(ModelMap mop){
        ResultData<?> resultData = new ResultData<>();
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        Map<String, Object> reqMap = new HashMap<String, Object>();
        try {
        	reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
        	reqMap.put("adjutFlag", 1);
            resultData = this.userService.getLinkUser(reqMap,null);
        }catch (Exception e){
        	logger.error("houseLinkage.report",
                    "ReportController",
                    "getLinkUser",
                    "",
                    UserInfoHolder.getUserId(),
                    "",
                    "获取业绩人列表",
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
     * 获取业绩人中心列表
     * @param mop
     * @return
     */
    @RequestMapping(value = "getLinkUserCenter", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getLinkUserCenter(HttpServletRequest request,ModelMap mop){
    	ResultData<?> resultData = new ResultData<>();
    	Map<String, Object> reqMap = bindParamToMap(request);
    	//返回map
    	Map<String, Object> rspMap = new HashMap<String, Object>();
    	try {
    		if(reqMap.containsKey("linkUserCode")) {
    			String linkUserCode = reqMap.get("linkUserCode").toString();
    			resultData = this.reportService.getLinkUserCenter(linkUserCode);
    		}
    	}catch (Exception e){
    		logger.error("houseLinkage.report",
    				"ReportController",
    				"getLinkUser",
    				"",
    				UserInfoHolder.getUserId(),
    				"",
    				"获取业绩人归属中心列表",
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
     * @Title: saveAchievementAdjut 
     * @Description: 保存调整后的业绩
     */
    @RequestMapping(value = "/saveAchievementAdjut", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> saveAchievementAdjut(HttpServletRequest request, ModelMap modelMap){
    	ReturnView returnView = new ReturnView();
        ResultData resultData = new ResultData();
        resultData.setFail();
   	 	Map<String, Object> rspMap = new HashMap<String, Object>();
	   	 //请求map
	   	Map<String, Object> reqMap = bindParamToMap(request);
	   	try{
	   		resultData = linkAchieveChangeService.saveAchievementAdjut(reqMap);
	   	} catch (Exception e){
	   		resultData.setFail("保存联动业绩调整信息异常!");
            logger.error("PMLS", "ReportController", "saveAchievementAdjut", reqMap.toString(), null, "", "保存联动业绩调整信息异常", e);
        }
	   	returnView.setOperateReturnType();
        returnView.setReturnCode(resultData.getReturnCode());
        returnView.setReturnMsg(resultData.getReturnMsg());
        returnView.setReturnValue(resultData.getReturnData());

        return returnView;
    } 
    /**
     * 模糊查询业绩人
     * @param mop
     * @return
     */
    @RequestMapping(value = "getLinkUserByCondition", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getLinkUserByCondition(HttpServletRequest request,ModelMap mop){
    	ResultData<?> resultData = new ResultData<>();
    	Map<String, Object> reqMap = bindParamToMap(request);
    	//返回map
    	Map<String, Object> rspMap = new HashMap<String, Object>();
    	try {
    		PageInfo pageInfo = getPageInfo(request);
    		resultData = this.userService.getLinkUserByCondition(reqMap,null);
    	}catch (Exception e){
    		logger.error("houseLinkage.report",
    				"ReportController",
    				"getLinkUserByCondition",
    				"",
    				UserInfoHolder.getUserId(),
    				"",
    				"模糊查询业绩人",
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
     * @Title: editCustomerInfoMode 
     * @Description: 客户信息调整弹窗
     */
    @RequestMapping(value = "/editCustomerInfoMode", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editCustomerInfoMode(HttpServletRequest request,ModelMap mop){
   	 //请求map
   	 Map<String, Object> reqMap = bindParamToMap(request);
   	 //报备编号
   	 mop.put("reportId", reqMap.get("reportId").toString());
   	 //客户
   	 mop.put("customerNm", reqMap.get("customerNm").toString());
   	 mop.put("customerTel", reqMap.get("customerTel").toString());
   	 //第二组客户
   	 mop.put("customerNmTwo", reqMap.get("customerNmTwo").toString());
   	 mop.put("customerTelTwo", reqMap.get("customerTelTwo").toString());
   	 //操作人
   	 mop.put("userCode", UserInfoHolder.get().getUserCode());
   	 mop.put("userName", UserInfoHolder.get().getUserName());
   	 mop.put("userIdCreate", UserInfoHolder.get().getUserId());
   	 //返回页面参数
     mop.put("estateId", reqMap.get("estateId").toString());
     mop.put("companyId", reqMap.get("companyId").toString());
     mop.put("customerId", reqMap.get("customerId").toString());
     mop.put("relateId", reqMap.get("relateId").toString());
     mop.put("fromType", reqMap.get("fromType").toString());
   	 ModelAndView mv = new ModelAndView( "houseLinkage/report/editCustomerInfoModePopup");
   	 return mv;
    } 
    /** 
     * @Title: saveCustomerInfoAdjut 
     * @Description: 保存修改后的客户信息
     */
    @RequestMapping(value = "/saveCustomerInfoAdjut", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> saveCustomerInfoAdjut(HttpServletRequest request, ModelMap modelMap){
    	ReturnView returnView = new ReturnView();
        ResultData resultData = new ResultData();
        resultData.setFail();
   	 	Map<String, Object> rspMap = new HashMap<String, Object>();
	   	 //请求map
	   	Map<String, Object> reqMap = bindParamToMap(request);
	   	try{
	   		resultData = linkAchieveChangeService.saveCustomerInfoAdjut(reqMap);
	   	} catch (Exception e){
	   		resultData.setFail("保存客户修改信息异常!");
            logger.error("PMLS", "ReportController", "saveCustomerInfoAdjut", reqMap.toString(), null, "", "保存客户修改信息异常", e);
        }
	   	returnView.setOperateReturnType();
        returnView.setReturnCode(resultData.getReturnCode());
        returnView.setReturnMsg(resultData.getReturnMsg());
        returnView.setReturnValue(resultData.getReturnData());

        return returnView;
    } 
}
