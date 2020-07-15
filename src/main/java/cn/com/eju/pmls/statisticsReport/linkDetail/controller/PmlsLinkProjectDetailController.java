package cn.com.eju.pmls.statisticsReport.linkDetail.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.Report.model.ReportQueueAjax;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.pmls.report.service.PmlsReportQueueService;
import cn.com.eju.pmls.statisticsReport.linkDetail.service.PmlsLinkProjectDetailService;

/**
 * Controller层
 * 联动项目明细
 *
 */
@Controller
@RequestMapping(value = "pmlsLinkProjectDetail")
public class PmlsLinkProjectDetailController extends BaseReportController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private PmlsLinkProjectDetailService linkProjectDetailService;

    @Resource
    private PmlsReportQueueService pmlsReportQueueService;
    
    @Autowired
    @Qualifier("threadPoolTaskExecutorPmlsLinkProjectDetail")
    private ThreadPoolTaskExecutor threadPoolTaskExecutorPmlsLinkProjectDetail;


    /**
     * desc:初始化
     * 2019年10月31日
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String linkProjectDetailList(ModelMap mop) {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("reportKey", "linkProjectDetail_filepath");
        reqMap.put("userId", UserInfoHolder.getUserId());
        ResultData<?> reback = new ResultData<>();
        
        try {
            reback = pmlsReportQueueService.selectReportListByUser(reqMap);
        } catch (Exception e) {
            logger.error("linkMarginDetail", "LinkMarginDetailController", "list", "", null, "", "", e);
            reback.setFail();
        }
        List<?> contentlist = (List<?>) reback.getReturnData();
        int userReportSize = 0;
        if (!CollectionUtils.isEmpty(contentlist)) {
            userReportSize = contentlist.size();
        }
        mop.put("userReportSize", userReportSize);
        return "statisticsReport/linkDetail/linkProjectDetailList";
    }

    /**
     * 查询--list
     */
    @RequestMapping(value = "querylinkProjectDetailList", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> querylinkProjectDetailList(HttpServletRequest request, ModelMap mop) {

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        PageInfo pageInfo = getPageInfo(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        changeParam(reqMap);

        //页面数据
        ResultData<?> reback = null;
        try {
            //获取页面显示数据
            //点击查询
            reqMap.put("clickType",1);
            reback = linkProjectDetailService.queryLinkProjectDetailList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("pmlsLinkProjectDetail", "PmlsLinkDetailController", "queryLinkProjectDetailList", "", null, "", "查询--list", e);
        }
        return reback;
    }

    /**
     * 导出EXCEL
     */
    @RequestMapping(value = "exportLinkProjectDetal", method = RequestMethod.GET)
    public void exportLinkProjectDetal(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        reqMap.put("optFlag", "export");

        changeParam(reqMap);

        try {
            //点击导出
            reqMap.put("clickType",2);
            ResultData<?> reback = linkProjectDetailService.queryLinkProjectDetailList(reqMap, null);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            downLoadExcel(request, response, contentlist, reqMap, ReportConstant.LINKPROJECTDETAIL_CODE, ReportConstant.LINKPROJECTDETAIL_NAME);

        } catch (Exception e) {
            logger.error("pmlsLinkProjectDetail", "PmlsLinkDetailController", "export", "input param: reqMap=" + reqMap.toString(), -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }

    }

    /**
     * desc:导出excel-ajax
     * 2019年9月12日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "exportLinkProjectDetailAjax", method = RequestMethod.GET)
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
        reqMap.put("clickType",2);
        
        changeParam(reqMap);
        int queueSize = 0;
        try {
            //加
        	pmlsReportQueueService.updateReportQueueTotal("linkProjectDetail_filepath", 1);
            ReportQueueAjax reportDb = new ReportQueueAjax();
            reportDb.setDateCreate(new Date());
            reportDb.setDelFlag("N");
            reportDb.setReportKey("linkProjectDetail_filepath");
            reportDb.setUserId(UserInfoHolder.getUserId());
            reportDb.setUserIdCreate(UserInfoHolder.getUserId());
            int id = pmlsReportQueueService.addReportQueueAjax(reportDb);

            String ctxPath = request.getSession().getServletContext().getRealPath("/");
            reqMap.put("ctxPath", ctxPath);
            linkProjectDetailService.downLoadExcelAjax(reqMap, ReportConstant.LINKPROJECTDETAIL_CODE, ReportConstant.LINKPROJECTDETAIL_NAME, id);

            ResultData resultData = pmlsReportQueueService.selectReportQueueTotalTopOne("companyDetail_filepath");
            LinkedHashMap<String, Object> linkedHashMap = (LinkedHashMap<String, Object>) resultData.getReturnData();
            if (resultData.getReturnData() != null) {
                queueSize = Integer.valueOf(linkedHashMap.get("positiveSize") + "") - Integer.valueOf(linkedHashMap.get("minusSize") + "") - threadPoolTaskExecutorPmlsLinkProjectDetail.getCorePoolSize();
            }
            if (queueSize <= 0) {
                queueSize = 0;
            }
        } catch (Exception e) {
            logger.error("pmlsLinkProjectDetail", "PmlsLinkProjectDetailController", "export", "input param: reqMap=" + reqMap.toString(), -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }
        map.put("message", "您前面还有" + queueSize + "人排队处理；正在排队处理请稍后(排队过程中可离开当前页面;下次进入联动项目明细页面会自动下载)");
        return getOperateJSONView(map);
    }

}
