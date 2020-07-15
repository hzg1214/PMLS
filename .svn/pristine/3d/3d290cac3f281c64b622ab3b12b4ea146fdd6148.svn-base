package cn.com.eju.deal.scene.estate.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.service.CommonService;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.houseLinkage.report.ReportDto;
import cn.com.eju.deal.houseLinkage.estate.service.EstateService;
import cn.com.eju.deal.houseLinkage.report.service.ReportService;

import com.alibaba.fastjson.JSON;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yinkun on 2018/5/21.
 */
@Controller
@RequestMapping("reportToValid")
public class ReportToValidController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "reportService")
    private ReportService reportService;
    @Resource(name = "commonService")
    private CommonService commonService;
    
    @Resource(name = "estateService")
    private EstateService estateService;

    /**
     * 初始化
     * @param request
     * @param mop
     * @param response
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap mop, HttpServletResponse response)
    {
        Map<String, Object> map = bindParamToMap(request);
        if(map.containsKey("searchParam") && "1".equals(map.get("searchParam"))){
            Map<String,Object> searchParamMap = getRememberSearch(request, ComConstants.REPORTTOVALID_LIST);
            searchParamMap.remove("roughAuditStatus");
            mop.put("searchParamMap", JSON.toJSON(searchParamMap));
        }else{
            clearSearch(request, response, ComConstants.REPORTTOVALID_LIST);
        }
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
        mop.put("list_search_page", ComConstants.REPORTTOVALID_LIST);

        return "houseLinkage/report/reportValidList";
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
            if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.REPORTTOVALID_LIST, reqMap);
            }
            PageInfo pageInfo = getPageInfo(request);

            //获取页面显示数据
            ResultData<?> reback = reportService.index(reqMap, pageInfo);

            contentlist = (List<?>)reback.getReturnData();
        }
        catch (Exception e)
        {
            logger.error("report.tovalid", "ReportToValidController", "index", "", null, "", "查询大定待审核列表失败", e);
        }

        //存放到mop中
        mop.addAttribute("contentlist", contentlist);

        return "houseLinkage/report/reportValidListCtx";
    }

    /**
     * 初始化
     * @param request
     * @param mop
     * @param response
     * @return
     */
    @RequestMapping(value = "valided", method = RequestMethod.GET)
    public String listValided(HttpServletRequest request, ModelMap mop, HttpServletResponse response)
    {
        Map<String, Object> map = bindParamToMap(request);
        if(map.containsKey("searchParam") && "1".equals(map.get("searchParam"))){
            Map<String,Object> searchParamMap = getRememberSearch(request, ComConstants.REPORTTOVALIDED_LIST);
            searchParamMap.remove("roughAuditStatus");
            mop.put("searchParamMap", JSON.toJSON(searchParamMap));
        }else{
            clearSearch(request, response, ComConstants.REPORTTOVALIDED_LIST);
        }
        mop.put("list_search_page", ComConstants.REPORTTOVALIDED_LIST);
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
        return "houseLinkage/report/reportValidedList";
    }

    /**
     * 查询--list
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "valided/q", method = RequestMethod.GET)
    public String indexValided(HttpServletRequest request, ModelMap mop, HttpServletResponse response)
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
            if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.REPORTTOVALIDED_LIST, reqMap);
            }
            PageInfo pageInfo = getPageInfo(request);

            //获取页面显示数据
            ResultData<?> reback = reportService.index(reqMap, pageInfo);

            contentlist = (List<?>)reback.getReturnData();
        }
        catch (Exception e)
        {
            logger.error("report.tovalid", "ReportToValidController", "index", "", null, "", "查询大定已审核列表失败", e);
        }

        //存放到mop中
        mop.addAttribute("contentlist", contentlist);

        return "houseLinkage/report/reportValidedListCtx";
    }

    @RequestMapping("/toView/{id}")
    public String toView(ModelMap mop,@PathVariable int id,HttpServletRequest request){

        ResultData<?> result = new ResultData<>();
        try {
            result = reportService.getById(id);
        } catch (Exception e) {
            logger.error("report.toValidView", "ReportToValidController", "toView", "id="+id, null, "", "查询大定已审核详情", e);
        }
        mop.addAttribute("reportInfo",result.getReturnData());
        mop.addAttribute("fromType",request.getParameter("fromType"));
        
        ResultData<?> dataSwitch = null;
        if(result != null){
        	Map<String, Object> reportDto = (Map<String, Object>) result.getReturnData();
        	if(reportDto!=null && reportDto.get("estateCityNo")!=null){
        		String estateCityNo = reportDto.get("estateCityNo").toString();
        		String roughDate = reportDto.get("roughInputDate").toString();
        		try {
					dataSwitch = commonService.getSwitchInfo(estateCityNo);
					if(null != dataSwitch){
						Map<String, Object> map = (Map<String, Object>) dataSwitch.getReturnData();
						if(null != map.get("yearMonth")){
							mop.put("yearMonth", map.get("yearMonth").toString());
							Date date = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("yearMonth").toString()); 
	                    	Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(roughDate); 
	                    	if(date2.before(date)){
	                    		//不能审核通过。弹提示框
	                    		mop.put("passFlag", "1");
	                    	}else {
	                    		mop.put("passFlag", "0");
	                    	}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
                 
        	}
        }
        return "houseLinkage/report/reportValidView";
    }

    @RequestMapping(value = "roughAudit", method = RequestMethod.GET)
    @ResponseBody
    public String roughAudit(HttpServletRequest request, ModelMap mop, HttpServletResponse response) {

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        ReportDto reportDto = new ReportDto();
        reportDto.setId(Integer.valueOf(reqMap.get("id").toString()));
        reportDto.setRoughAuditStatus(reqMap.get("roughAuditStatus").toString());
        reportDto.setRoughAuditId(Long.valueOf(UserInfoHolder.getUserId()));
        reportDto.setRoughAuditTime(new Date());
        if("1".equals(reqMap.get("roughAuditStatus").toString())){
            reportDto.setRoughDate(DateUtil.getDate(reqMap.get("roughInputDate").toString()));
            reportDto.setInComeStatus(Integer.valueOf(reqMap.get("inComeStatus").toString()));
            reportDto.setDetailId(Integer.valueOf(reqMap.get("detailId").toString()));
        }
        if("2".equals(reqMap.get("roughAuditStatus").toString())){
            reportDto.setRoughAuditDesc(reqMap.get("roughAuditDesc").toString());
        }

        ResultData<?> resultData = new ResultData<>();
        try {
            resultData = reportService.update(reportDto);
            if("-1".equals(resultData.getReturnCode())){
                return resultData.toString();
            }

            if("1".equals(reqMap.get("roughAuditStatus").toString())) {
                reportService.updateDetailRoughDate(reportDto);

                //添加返佣对象表记录
                reportDto.setReportId(reqMap.get("reportId").toString());
                reportDto.setCompanyId(reqMap.get("companyNo").toString());
                reportService.insertYjReport(reportDto);
            }
        } catch (Exception e) {
            resultData.setFail("大定审核失败");
            logger.error("report.roughAudit", "ReportToValidController", "roughAudit", JsonUtil.parseToJson(reportDto), null, "", "大定审核失败", e);
        }

        return resultData.toString();
    }

    @RequestMapping("/toReject/{id}")
    public String toReject(ModelMap mop,@PathVariable int id){
        return "houseLinkage/report/reportValidRejectPop";
    }

    @RequestMapping("/toInComeStatus/{detailId}")
    public String toInComeStatus(ModelMap mop,@PathVariable int detailId){
        return "houseLinkage/report/reportInComePop";
    }
    
    
     
     @RequestMapping(value = "getYHApproveCheck/{id}", method = RequestMethod.GET)
     @ResponseBody
     public String getYHApproveCheck(HttpServletRequest request, @PathVariable Integer id) {
         ResultData<?> resultData = new ResultData<>();
         try {
             resultData = reportService.getYHApproveCheck(id);
      
         } catch (Exception e) {
             resultData.setFail("大定审核失败");
             logger.error("report.roughAudit", "ReportToValidController", "getYHApproveCheck"
            		 , id.toString(), null, "", "大定审核失败", e);
         }
         return resultData.toString();
     }
}
