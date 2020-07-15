package cn.com.eju.pmls.statisticsReport.linkDetail.controller;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.Report.model.ReportQueueAjax;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.pmls.report.service.PmlsReportQueueService;
import cn.com.eju.pmls.statisticsReport.linkDetail.service.PmlsLinkProjectPartATraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping(value = "pmlsLinkProjectPartATrace")
public class PmlsLinkProjectPartATraceController extends BaseReportController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    PmlsLinkProjectPartATraceService pmlsLinkProjectPartATraceService;

    @Resource
    private PmlsReportQueueService pmlsReportQueueService;

    @Autowired
    @Qualifier("threadPoolTaskExecutorLinkProjectPartATrace")
    private ThreadPoolTaskExecutor threadPoolTaskExecutorLinkProjectPartATrace;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView projectPartATraceList(ModelMap mop) {
        ModelAndView mv = new ModelAndView("statisticsReport/linkProjectTrace/linkProjectPartATraceList");


        try {
            Map<String, Object> map = new HashMap<>();
            map.put("userCode", UserInfoHolder.get().getUserCode());
            ResultData<?> reback = pmlsLinkProjectPartATraceService.queryCityList(map);
            List<?> citylist = (List<?>) reback.getReturnData();
            mop.addAttribute("citylist", citylist);
        } catch (Exception e) {
            logger.error("projectPartATraceList 取得城市发生错误!", e);
            logger.error("linkDetail", "PmlsLinkProjectPartATraceController", "projectPartATraceList", "", null, "", "查询城市", e);
        }

        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("reportKey", "linkProjectPartATrace_filepath");
        reqMap.put("userId", UserInfoHolder.get().getUserId());
        ResultData<?> reback = new ResultData<>();
        try {
            reback = pmlsReportQueueService.selectReportListByUser(reqMap);
        } catch (Exception e) {
            logger.error("linkDetail", "PmlsLinkProjectPartATraceController", "projectPartATraceList", "", null, "", "linkProjectPartATrace_filepath", e);
            reback.setFail();
        }
        List<?> contentlist = (List<?>) reback.getReturnData();
        int userReportSize = 0;
        if (!CollectionUtils.isEmpty(contentlist)) {
            userReportSize = contentlist.size();
        }
        mop.put("userReportSize", userReportSize);
        String countDateEndStr = DateUtil.fmtDate(new Date(),"yyyy-MM");
        mop.put("countDateEndStr",countDateEndStr);

        return mv;
    }

    @RequestMapping(value = "queryLinkProjectPartATraceList", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> queryLinkProjectPartATraceList(HttpServletRequest request, ModelMap mop) {

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        PageInfo pageInfo = getPageInfo(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        reqMap.put("userCode", UserInfoHolder.get().getUserCode());
        //点击查询
        reqMap.put("clickType",1);

        ResultData<?> reback = null;
        try {
            //获取页面显示数据
            reback = pmlsLinkProjectPartATraceService.queryLinkProjectPartATraceList(reqMap, pageInfo);

        } catch (Exception e) {
            logger.error("linkDetail", "PmlsLinkProjectPartATraceController", "queryLinkProjectPartATraceList", "", null, "", "查询--list", e);
        }

        return reback;
    }


    @RequestMapping(value = "exportLinkProjectPartATraceAjax", method = RequestMethod.GET)
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
        reqMap.put("optFlag", "export");
        reqMap.put("userCode", UserInfoHolder.get().getUserCode());
        //点击导出
        reqMap.put("clickType",2);

        int queueSize = 0;
        try {
            //加
            pmlsReportQueueService.updateReportQueueTotal("linkProjectPartATrace_filepath", 1);
            ReportQueueAjax reportDb = new ReportQueueAjax();
            reportDb.setDateCreate(new Date());
            reportDb.setDelFlag("N");
            reportDb.setReportKey("linkProjectPartATrace_filepath");
            reportDb.setUserId(UserInfoHolder.getUserId());
            reportDb.setUserIdCreate(UserInfoHolder.getUserId());
            int id = pmlsReportQueueService.addReportQueueAjax(reportDb);

            String ctxPath = request.getSession().getServletContext().getRealPath("/");
            reqMap.put("ctxPath", ctxPath);
            pmlsLinkProjectPartATraceService.downLoadExcelAjax(reqMap, ReportConstant.LINKPROJECTPARTATRACE_CODE, ReportConstant.LINKPROJECTPARTATRACE_NAME, id);

            ResultData resultData = pmlsReportQueueService.selectReportQueueTotalTopOne("linkProjectPartATrace_filepath");
            LinkedHashMap<String, Object> linkedHashMap = (LinkedHashMap<String, Object>) resultData.getReturnData();
            if (resultData.getReturnData() != null) {
                queueSize = Integer.valueOf(linkedHashMap.get("positiveSize") + "") - Integer.valueOf(linkedHashMap.get("minusSize") + "") - threadPoolTaskExecutorLinkProjectPartATrace.getCorePoolSize();
            }

            if (queueSize <= 0) {
                queueSize = 0;
            }
        } catch (Exception e) {
            logger.error("linkDetail", "PmlsLinkProjectPartATraceController", "export", "input param: reqMap=" + reqMap.toString(), -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }
        map.put("message", "您前面还有" + queueSize + "人排队处理；正在排队处理请稍后(排队过程中可离开当前页面;下次进入联动甲方跟踪分析表页面会自动下载)");
        return getOperateJSONView(map);
    }

}
