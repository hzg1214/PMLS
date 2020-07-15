package cn.com.eju.pmls.statisticsReport.refund.controller;

import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.Report.model.ReportQueueAjax;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.pmls.remittanceTrack.service.RemittanceTrackService;
import cn.com.eju.pmls.report.service.PmlsReportQueueService;
import cn.com.eju.pmls.statisticsReport.refund.service.PmlsRefundTraceService;
import com.alibaba.fastjson.JSON;
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
import java.util.*;

@Controller
@RequestMapping(value = "pmlsRefundTraceController")
public class PmlsRefundTraceController extends BaseReportController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    PmlsRefundTraceService pmlsRefundTraceService;


    @Resource
    private PmlsReportQueueService pmlsReportQueueService;

    @Autowired
    @Qualifier("threadPoolTaskExecutorForPartWeek")
    private ThreadPoolTaskExecutor threadPoolTaskExecutorForPartWeek;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView refundTraceList(ModelMap mop) {
        ModelAndView mv = new ModelAndView("statisticsReport/refund/refundTraceList");

        try {
            Map<String, Object> map = new HashMap<>();
            map.put("userCode", UserInfoHolder.get().getUserCode());
            ResultData<?> reback = pmlsRefundTraceService.queryCityList(map);
            List<?> citylist = (List<?>) reback.getReturnData();
            mop.addAttribute("citylist", citylist);
            String year= DateUtil.fmtDate(new Date(),"yyyy");
            String month=DateUtil.fmtDate(new Date(),"MM");
            mv.addObject("year",year);
            mv.addObject("month",month);



            Map<String, Object> reqMap = new HashMap<>();
            reqMap.put("reportKey", "linkProjectPartAWeek_filepath");
            reqMap.put("userId", UserInfoHolder.getUserId());
            ResultData<?> rebackSize = new ResultData<>();
            rebackSize = pmlsReportQueueService.selectReportListByUser(reqMap);

            List<?> contentlist = (List<?>) rebackSize.getReturnData();
            int userReportSize = 0;
            if (!CollectionUtils.isEmpty(contentlist)) {
                userReportSize = contentlist.size();
            }
            mop.put("userReportSize", userReportSize);

        } catch (Exception e) {
            logger.error("refundTraceList 初始化页面发生错误!", e);
            logger.error("refund", "pmlsRefundTraceController", "refundTraceList", "", null, "", "初始化页面", e);
        }
        return mv;
    }

    @RequestMapping(value = "queryList", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> queryList(HttpServletRequest request) {
        logger.info("联动项目周回款跟踪：查询bengin");
        Map<String, Object> reqMap = bindParamToMap(request);
        logger.info("联动项目周回款跟踪：查询bengin##reqMap="+JSON.toJSONString(reqMap));
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            reqMap.put("userId", UserInfoHolder.getUserId());
            reqMap.put("userCode", UserInfoHolder.get().getUserCode());
            reqMap.put("clickType",1);
            resultData = pmlsRefundTraceService.queryList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("联动项目周回款跟踪：查询error##reqMap="+JSON.toJSONString(reqMap));
            logger.error("pmlsRefundTraceController",
                    "PmlsRefundTraceController",
                    "queryList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询联动项目周回款跟踪异常",
                    e);
        }
        logger.info("联动项目周回款跟踪：查询end##reqMap="+JSON.toJSONString(reqMap));
        return resultData;
    }


    @RequestMapping(value = "export", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> export(HttpServletRequest request) {
        logger.info("联动项目周回款跟踪：导出bengin");
        Map<String, Object> map = bindParamToMap(request);
        logger.info("联动项目周回款跟踪：导出bengin##map="+JSON.toJSONString(map));
        ResultData<?> resultData =  new ResultData<>();
        int queueSize = 0;
        try {
            map.put("userId", UserInfoHolder.getUserId());
            map.put("userCode", UserInfoHolder.get().getUserCode());
            map.put("clickType",2);



            //加
            pmlsReportQueueService.updateReportQueueTotal("linkProjectPartAWeek_filepath", 1);
            ReportQueueAjax reportDb = new ReportQueueAjax();
            reportDb.setDateCreate(new Date());
            reportDb.setDelFlag("N");
            reportDb.setReportKey("linkProjectPartAWeek_filepath");
            reportDb.setUserId(UserInfoHolder.getUserId());
            reportDb.setUserIdCreate(UserInfoHolder.getUserId());
            int id = pmlsReportQueueService.addReportQueueAjax(reportDb);

            pmlsRefundTraceService.export(map,id);


            ResultData resultDataQueue = pmlsReportQueueService.selectReportQueueTotalTopOne("linkProjectPartAWeek_filepath");
            LinkedHashMap<String, Object> linkedHashMap = (LinkedHashMap<String, Object>) resultDataQueue.getReturnData();
            if (resultDataQueue.getReturnData() != null) {
                queueSize = Integer.valueOf(linkedHashMap.get("positiveSize") + "") - Integer.valueOf(linkedHashMap.get("minusSize") + "") - threadPoolTaskExecutorForPartWeek.getCorePoolSize();
            }
            if (queueSize <= 0) {
                queueSize = 0;
            }
        } catch (Exception e) {
            logger.error("联动项目周回款跟踪：导出error##map="+JSON.toJSONString(map));
            resultData.setFail("导出联动项目周回款跟踪失败!");
            logger.error("pmlsRefundTraceController",
                    "PmlsRefundTraceController",
                    "export",
                    "input param: " + map.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "导出联动项目周回款跟踪异常!",
                    e);
            return resultData;
        }
        logger.info("联动项目周回款跟踪：导出end##map="+JSON.toJSONString(map));

        String messages = "您前面还有" + queueSize + "人排队处理；正在排队处理请稍后(排队过程中可离开当前页面;下次进入联动项目周回款跟踪页面会自动下载)";
        resultData.setSuccess(messages);
        return resultData;
    }
}
