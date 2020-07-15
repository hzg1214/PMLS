package cn.com.eju.deal.otherReport.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.dto.houseLinkage.report.ReportInfoDto;
import cn.com.eju.deal.dto.student.StudentDto;
import cn.com.eju.deal.houseLinkage.estate.service.EstateService;
import cn.com.eju.deal.otherReport.model.LnkQtReport;
import cn.com.eju.deal.otherReport.service.QtReportService;

/**
 * @author :zhenggang.Huang
 * @date   :2019年10月11日
 */
@Controller
@RequestMapping(value = "qtReport")
public class QtReportController extends BaseController
{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "qtReportService")
    private QtReportService qtReportService;

    @Resource(name = "estateService")
    private EstateService estateService;

    /**
     * desc:初始化
     * 2019年10月12日
     * author:zhenggang.Huang
     */
     @RequestMapping(value = "", method = RequestMethod.GET)
     public String list(HttpServletRequest request, ModelMap mop, HttpServletResponse response)
     {
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
         	 logger.error("qtReport", "QtReportController", "list", "", null, "", "创建--初始化-归属项目部", e);
         }

         mop.put("rebacklist", rebacklist);
         //end
         //读取搜索条件 add by wangkanlin 2017/08/23
         Map<String, Object> map = bindParamToMap(request);
         if(map.containsKey("searchParam") && "1".equals(map.get("searchParam"))){
             Map<String,Object> searchParamMap = getRememberSearch(request, ComConstants.QTREPORT_LIST_SEARCH);
             mop.put("searchParamMap", JSON.toJSON(searchParamMap));
         }else{
             clearSearch(request, response, ComConstants.QTREPORT_LIST_SEARCH);
         }
         mop.put("list_search_page", ComConstants.QTREPORT_LIST_SEARCH);
         
         return "otherReport/qtReportList";
     }
     
     /**
      * desc:列表
      * 2019年10月12日
      * author:zhenggang.Huang
      */
      @RequestMapping(value = "q", method = RequestMethod.GET)
      public String queryList(HttpServletRequest request, ModelMap mop, HttpServletResponse response)
      {
          
          //获取请求参数
          Map<String, Object> reqMap = bindParamToMap(request);
          
          //页面数据
          List<?> contentlist = null;
          try
          {
              if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                  rememberSearch(request, response, ComConstants.QTREPORT_LIST_SEARCH, reqMap);
              }
              PageInfo pageInfo = getPageInfo(request);
              //获取页面显示数据
              ResultData<?> reback = qtReportService.queryList(reqMap, pageInfo);
              
              contentlist = (List<?>)reback.getReturnData();
          }
          catch (Exception e)
          {
              logger.error("qtReport", "QtReportController", "queryList", "", null, "", "查询--list", e);
          }
          //存放到mop中
          mop.addAttribute("userAcCityNo", UserInfoHolder.get().getCityNo());
          mop.addAttribute("contentlist", contentlist);
          String qtType = (String) reqMap.get("qtType") == null?"qtReport":(String) reqMap.get("qtType");
          mop.addAttribute("qtType", qtType);

          return "otherReport/qtReportListCtx";
      }





    /**
     * @Description: 其他收入新增报备
     */
    @RequestMapping(value = "toQtReport/{id}", method = RequestMethod.GET)
    public String toReport1(@PathVariable("id") Integer id, HttpServletRequest request, ModelMap mop){
        ResultData<?> resultData = null;
        ResultData<?> cityList = null;
        try
        {
            resultData = estateService.getById(id);

            cityList = qtReportService.getBasCitySettingList();

        }
        catch (Exception e)
        {
            logger.error("qtReport", "QtReportController", "toQtReport", "", null, "", "跳转其他收入报备页面", e);
        }
        mop.addAttribute("estateInfo", resultData.getReturnData());
        mop.addAttribute("cityList", cityList.getReturnData());
        return "otherReport/qtReportAdd";
    }


    /**
     * 查询归属城市下的归属中心
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getBasCitySettingList", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getBasCitySettingList(ModelMap mop)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        //返回map
        ResultData<?> resultData = new ResultData<StudentDto>();

        try{
            resultData = qtReportService.getBasCitySettingList();
        }
        catch (Exception e){
            logger.error("qtReport", "QtReportController", "getBasCitySettingList", "", null, "", "查询归属城市下的归属城市失败！", e);
        }

        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        return getSearchJSONView(rspMap);
    }


    /**
     * 查询归属城市下的归属中心
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getAchivAchievementLevelSettingList/{cityNo}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getAchivAchievementLevelSettingList(ModelMap mop,@PathVariable("cityNo") String cityNo)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        //返回map
        ResultData<?> resultData = new ResultData<StudentDto>();

        try{
            resultData = qtReportService.getAchivAchievementLevelSettingList(cityNo);
        }
        catch (Exception e){
            logger.error("qtReport", "QtReportController", "getAchivAchievementLevelSettingList", "", null, "", "查询归属城市下的归属中心失败！", e);
        }

        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        return getSearchJSONView(rspMap);
    }


    /**
     * @Title: createReport
     * @Description: 新增报备
     */
    @RequestMapping(value = "createReport", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> createReport(HttpServletRequest request, ModelMap modelMap,@RequestBody LnkQtReport lnkQtReport){
        Map<String, Object> reqMap = bindParamToMap(request);
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try{
            UserInfo userInfo = (UserInfo) WebUtil.getValueFromSession(request, Constant.SESSION_KEY_USERINFO);
            // 新增报备处理
            lnkQtReport.setCrtUserId(userInfo.getUserId());
            ResultData<?> resultData = this.qtReportService.createReport(lnkQtReport);

            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        }
        catch (Exception e)
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            logger.error("qtReport", "QtReportController", "createReport", "", null, "", "创建报备失败！", e);
        }
        return getOperateJSONView(rspMap);
    }
    
    /**
     * 查看
     * @param estateId
     * @param id
     * @param mop
     * @return
     */
	@RequestMapping(value = "/{estateId}/{id}/{qtType}", method = RequestMethod.GET)
	public String getQtReportById(@PathVariable("estateId") String estateId, @PathVariable("id") Integer id, @PathVariable("qtType") String qtType,ModelMap mop) {
		// 返回map
		ResultData<?> resultData = null;
		Map<String, Object> reqMap = new HashMap<>();
		reqMap.put("id", id);
		try {
			resultData = qtReportService.getQtReportById(reqMap);
		} catch (Exception e) {
			logger.error("qtReport", "QtReportController", "getQtReportById", "", null, "", "查看 ", e);
		}

		mop.addAttribute("qtReportInfo", resultData.getReturnData());
		mop.addAttribute("estateId", estateId);
		mop.addAttribute("qtType", qtType);
		return "otherReport/qtReportDetail";
	}



    /**
     * @Title: validQtReport
     * @Description:
     */
    @RequestMapping(value = "validQtReport/{reportId}", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> validQtReport(HttpServletRequest request, ModelMap modelMap
            ,@PathVariable("reportId") Integer reportId){
        Map<String, Object> reqMap = bindParamToMap(request);
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try{
            UserInfo userInfo = (UserInfo) WebUtil.getValueFromSession(request, Constant.SESSION_KEY_USERINFO);

            ResultData<?> resultData = this.qtReportService.validQtReport(reportId,userInfo.getUserId());

            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        }
        catch (Exception e)
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            logger.error("qtReport", "QtReportController", "validQtReport", "", null, "", "报备无效操作失败！", e);
        }
        return getOperateJSONView(rspMap);
    }
    
    /**
     * desc:业务节点-查看
     * 2019年10月17日
     * author:zhenggang.Huang
     */
    @RequestMapping("toOperDetail")
    public String toOperDetail(HttpServletRequest request,ModelMap mop) {
        Map<String, Object> reqMap = bindParamToMap(request);
        
        try {
            ResultData<?> resultData = qtReportService.getQtReportById(reqMap);
            ResultData<?> resultData1 = qtReportService.getOperDetail(reqMap);
            mop.put("info", resultData.getReturnData());
            mop.put("yjReport", resultData1.getReturnData());
            mop.put("reqMap", reqMap);
        }catch (Exception e) {
            logger.error("qtReport", "QrReportController", "toOperDetail", "", null, "", "业务节点详情查询 ", e);
        }
        
        return "otherReport/qtReportOperDetail";
    }
}
