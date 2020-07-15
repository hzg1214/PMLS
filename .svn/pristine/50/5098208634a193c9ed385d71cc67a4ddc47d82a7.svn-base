package cn.com.eju.deal.houseLinkage.linkDetail.controller;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.Report.model.ReportQueueAjax;
import cn.com.eju.deal.Report.service.ReportQueueService;
import cn.com.eju.deal.base.dto.code.CommonCodeDto;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.houseLinkage.linkDetail.service.LinkDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Controller层
 * 联动明细
 *
 * @author tanlang
 */
@Controller
@RequestMapping(value = "linkDetail")
public class LinkDetailController extends BaseReportController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private LinkDetailService linkDetailService;

    @Resource
    private ReportQueueService reportQueueService;

    @Autowired
    @Qualifier("threadPoolTaskExecutorLinkDetail")
    private ThreadPoolTaskExecutor threadPoolTaskExecutorLinkDetail;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String linkDetailList(HttpServletRequest request, ModelMap mop) {
        List<CommonCodeDto> customerFromList = SystemParam.getCodeListByKey("174");//报备来源
        mop.addAttribute("customerFromList", customerFromList);

        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("reportKey", "linkDetail_filepath");
        reqMap.put("userId", UserInfoHolder.get().getUserId());
        ResultData<?> reback = new ResultData<>();
        try {
            reback = reportQueueService.selectReportListByUser(reqMap);
        } catch (Exception e) {
            logger.error("linkDetail", "LinkDetailController", "list", "", null, "", "", e);
            reback.setFail();
        }
        List<?> contentlist = (List<?>) reback.getReturnData();
        int userReportSize = 0;
        if (!CollectionUtils.isEmpty(contentlist)) {
            userReportSize = contentlist.size();
        }
        mop.put("userReportSize", userReportSize);

        return "houseLinkage/linkDetail/linkDetailList";
    }

    /**
     * 核算主体
     * @param request
     * @return
     */
    @RequestMapping(value = "/getAccountProject", method = RequestMethod.GET)
    public  ResultData<?> getAccountProject(HttpServletRequest request) {

        ResultData<?> resultData = new ResultData<>();
        try {
            resultData = linkDetailService.getAccountProject();
        } catch (Exception e) {
            logger.error("linkDetail", "LinkDetailController", "getAccountProject", "", null, "", "", e);
            resultData.setFail();
        }
        return resultData;
    }


    /**
     * 查询--list
     */
    @RequestMapping(value = "queryLinkDetailList", method = RequestMethod.GET)
    public String queryLinkDetailList(HttpServletRequest request, ModelMap mop) {

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        PageInfo pageInfo = getPageInfo(request);
        reqMap.put("userId", UserInfoHolder.getUserId());

        changeParamToList(reqMap);
        changeParam(reqMap);
        //页面数据
        List<?> contentlist = null;

        try {
            //获取页面显示数据
            ResultData<?> reback = linkDetailService.queryLinkDetailList(reqMap, pageInfo);

            contentlist = (List<?>) reback.getReturnData();
        } catch (Exception e) {
            logger.error("linkDetail", "LinkDetailController", "queryLinkDetailList", "", null, "", "查询--list", e);
        }

        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        String organization = (String) reqMap.get("organization");
        if (StringUtil.isNotEmpty(organization)) {
            year = organization;
        }
        mop.addAttribute("year", year);

        //存放到mop中
        mop.addAttribute("contentlist", contentlist);

        return "houseLinkage/linkDetail/linkDetailListCtx";
    }

    /**
     * 导出EXCEL
     */
    @RequestMapping(value = "exportLinkDetailList", method = RequestMethod.GET)
    public void exportLinkDetailList(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());

        changeParamToList(reqMap);
        reqMap = changeParam(reqMap);

        try {

            ResultData<?> reback = linkDetailService.queryLinkDetailList(reqMap, null);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            downLoadExcel(request, response, contentlist, reqMap, ReportConstant.LINKDETAIL_CODE, ReportConstant.LINKDETAIL_NAME);

        } catch (Exception e) {
            logger.error("linkDetail", "LinkDetailController", "exportLinkDetailList", "input param: reqMap=" + reqMap.toString(), -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }

    }

    @RequestMapping(value = "exportLinkDetailAjax", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView exportAjax(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        UserInfo userInfo = UserInfoHolder.get();
        if (userInfo == null) {
            map.put("message", "获取用户失败");
            return getOperateJSONView(map);
        }

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());

        changeParamToList(reqMap);
        reqMap = changeParam(reqMap);

        int queueSize = 0;
        try {
            //加
            reportQueueService.updateReportQueueTotal("linkDetail_filepath", 1);
            ReportQueueAjax reportDb = new ReportQueueAjax();
            reportDb.setDateCreate(new Date());
            reportDb.setDelFlag("N");
            reportDb.setReportKey("linkDetail_filepath");
            reportDb.setUserId(UserInfoHolder.getUserId());
            reportDb.setUserIdCreate(UserInfoHolder.getUserId());
            int id = reportQueueService.addReportQueueAjax(reportDb);

            String ctxPath = request.getSession().getServletContext().getRealPath("/");
            reqMap.put("ctxPath", ctxPath);
            linkDetailService.downLoadExcelAjax(reqMap, ReportConstant.LINKDETAIL_CODE, ReportConstant.LINKDETAIL_NAME, id);

            ResultData resultData = reportQueueService.selectReportQueueTotalTopOne("linkDetail_filepath");
            LinkedHashMap<String, Object> linkedHashMap = (LinkedHashMap<String, Object>) resultData.getReturnData();
            if (resultData.getReturnData() != null) {
                queueSize = Integer.valueOf(linkedHashMap.get("positiveSize") + "") - Integer.valueOf(linkedHashMap.get("minusSize") + "") - threadPoolTaskExecutorLinkDetail.getCorePoolSize();
            }

            if (queueSize <= 0) {
                queueSize = 0;
            }
        } catch (Exception e) {
            logger.error("linkDetail", "LinkDetailController", "export", "input param: reqMap=" + reqMap.toString(), -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }
        map.put("message", "您前面还有" + queueSize + "人排队处理；正在排队处理请稍后(排队过程中可离开当前页面;下次进入联动明细表页面会自动下载)");
        return getOperateJSONView(map);
    }


    @RequestMapping(value = "linkProjectDetail", method = RequestMethod.GET)
    public String linkProjectDetailList() {
        return "houseLinkage/linkProjectDetail/linkProjectDetailList";
    }

    /**
     * 查询--list
     */
    @RequestMapping(value = "querylinkProjectDetailList", method = RequestMethod.GET)
    public String querylinkProjectDetailList(HttpServletRequest request, ModelMap mop) {

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        PageInfo pageInfo = getPageInfo(request);
        //TODO
        reqMap.put("userId", UserInfoHolder.getUserId());
//        reqMap.put("userId", 156);

        changeParamToList(reqMap,",");
        changeParam(reqMap,";",",");

        //页面数据
        List<?> contentlist = null;
        try {
            //获取页面显示数据
            //点击查询
            reqMap.put("clickType",1);
            ResultData<?> reback = linkDetailService.queryLinkProjectDetailList(reqMap, pageInfo);
            //
            contentlist = (List<?>) reback.getReturnData();

        } catch (Exception e) {
            logger.error("linkProjectDetail", "LinkDetailController", "queryLinkProjectDetailList", "", null, "", "查询--list", e);
        }

        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        String organization = (String) reqMap.get("organization");
        if (StringUtil.isNotEmpty(organization)) {
            year = organization;
        }
        mop.addAttribute("year", year);
        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        mop.addAttribute("searchType", reqMap.get("searchType"));

        return "houseLinkage/linkProjectDetail/linkProjectDetailListCtx";
    }

    /**
     * 导出EXCEL
     */
    @RequestMapping(value = "exportLinkProjectDetal", method = RequestMethod.GET)
    public void exportLinkProjectDetal(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        reqMap.put("optFlag", "export");

        //changeParamToList(reqMap);
        changeParam(reqMap);

        try {
            //点击导出
            reqMap.put("clickType",2);
            ResultData<?> reback = linkDetailService.queryLinkProjectDetailList(reqMap, null);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            downLoadExcel(request, response, contentlist, reqMap, ReportConstant.LINKPROJECTDETAIL_CODE, ReportConstant.LINKPROJECTDETAIL_NAME);

        } catch (Exception e) {
            logger.error("linkProjectDetail", "LinkDetailController", "export", "input param: reqMap=" + reqMap.toString(), -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }

    }


}
