package cn.com.eju.pmls.statisticsReport.linkDetail.controller;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.Report.model.ReportQueueAjax;
import cn.com.eju.deal.base.dto.code.CommonCodeDto;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.pmls.report.service.PmlsReportQueueService;
import cn.com.eju.pmls.statisticsReport.linkDetail.service.PmlsReportOrderDetailService;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Controller层
 * 订单明细
 */
@Controller
@RequestMapping(value = "pmlsReportOrderDetail")
public class PmlsReportOrderDetailController extends BaseReportController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private PmlsReportOrderDetailService pmlsReportOrderDetailService;

    @Resource
    private PmlsReportQueueService pmlsReportQueueService;

    @Autowired
    @Qualifier("threadPoolTaskExecutorPmlsOrderDetail")
    private ThreadPoolTaskExecutor threadPoolTaskExecutorPmlsOrderDetail;

    @RequestMapping(value = "initList", method = RequestMethod.GET)
    public String initList(HttpServletRequest request, ModelMap mop) {
        List<CommonCodeDto> customerFromList = SystemParam.getCodeListByKey("174");//报备来源
        mop.addAttribute("customerFromList", customerFromList);

        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("reportKey", "reportOrderDetail_filepath");
        reqMap.put("userId", UserInfoHolder.getUserId());
        ResultData<?> reback = new ResultData<>();
        try {
            reback = pmlsReportQueueService.selectReportListByUser(reqMap);
        } catch (Exception e) {
            logger.error("订单明细##ctr initList 异常！",e);
            logger.error("pmlsReportOrderDetail", "PmlsReportOrderDetailController", "initList", "", null, "", "", e);
            reback.setFail();
        }
        List<?> contentlist = (List<?>) reback.getReturnData();
        int userReportSize = 0;
        if (!CollectionUtils.isEmpty(contentlist)) {
            userReportSize = contentlist.size();
        }
        mop.put("userReportSize", userReportSize);

        return "statisticsReport/reportOrderDetail/reportOrderDetailList";
    }


    /**
     * 查询--list
     */
    @RequestMapping(value = "queryList", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> queryList(HttpServletRequest request) {

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        PageInfo pageInfo = getPageInfo(request);
        reqMap.put("userId", UserInfoHolder.getUserId());

        changeParamToList(reqMap, ",");
        changeParam(reqMap, ";", ",");
        //页面数据
        ResultData<?> reback = null;

        try {
            //获取页面显示数据
            reback = pmlsReportOrderDetailService.queryList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("订单明细查询##ctr queryList 异常！",e);
            logger.error("pmlsReportOrderDetail", "PmlsReportOrderDetailController", "queryList", "", null, "", "查询--list", e);
        }
        return reback;
    }


    @RequestMapping(value = "exportAjax", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView exportAjax(HttpServletRequest request, HttpServletResponse response) {

        logger.info("订单明细导出##ctr exportAjax 开始！");

        Map<String, Object> map = new HashMap<String, Object>();
        UserInfo userInfo = UserInfoHolder.get();
        if (userInfo == null) {
            map.put("message", "获取用户失败");
            return getOperateJSONView(map);
        }

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());

        changeParamToList(reqMap, ",");
        changeParam(reqMap, ";", ",");

        int queueSize = 0;
        try {
            //加
            pmlsReportQueueService.updateReportQueueTotal("reportOrderDetail_filepath", 1);
            ReportQueueAjax reportDb = new ReportQueueAjax();
            reportDb.setDateCreate(new Date());
            reportDb.setDelFlag("N");
            reportDb.setReportKey("reportOrderDetail_filepath");
            reportDb.setUserId(UserInfoHolder.getUserId());
            reportDb.setUserIdCreate(UserInfoHolder.getUserId());
            int id = pmlsReportQueueService.addReportQueueAjax(reportDb);

            String ctxPath = request.getSession().getServletContext().getRealPath("/");
            reqMap.put("ctxPath", ctxPath);
            logger.info("订单明细导出##ctr downLoadExcelAjax 开始！");
            pmlsReportOrderDetailService.downLoadExcelAjax(reqMap, ReportConstant.REPORT_ORDER_DETAIL_CODE, ReportConstant.REPORT_ORDER_DETAIL_NAME, id);
            logger.info("订单明细导出##ctr downLoadExcelAjax 结束！");
            ResultData resultData = pmlsReportQueueService.selectReportQueueTotalTopOne("reportOrderDetail_filepath");
            LinkedHashMap<String, Object> linkedHashMap = (LinkedHashMap<String, Object>) resultData.getReturnData();
            if (resultData.getReturnData() != null) {
                queueSize = Integer.valueOf(linkedHashMap.get("positiveSize") + "") - Integer.valueOf(linkedHashMap.get("minusSize") + "") - threadPoolTaskExecutorPmlsOrderDetail.getCorePoolSize();
            }

            if (queueSize <= 0) {
                queueSize = 0;
            }
        } catch (Exception e) {
            logger.error("订单明细导出##ctr exportAjax 异常！",e);
            logger.error("pmlsReportOrderDetail", "PmlsReportOrderDetailController", "exportAjax", "input param: reqMap=" + reqMap.toString(), UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }
        logger.info("订单明细导出##ctr exportAjax 结束!");
        map.put("message", "您前面还有" + queueSize + "人排队处理；正在排队处理请稍后(排队过程中可离开当前页面;下次进入订单明细表页面会自动下载)");
        return getOperateJSONView(map);
    }

    //已改
    @RequestMapping(value = "/checkExport", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> checkExport(HttpServletRequest request) {
        ReturnView returnView = new ReturnView();
        ResultData resultData = new ResultData();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        int userReportSize = 0;
        try {
            resultData = pmlsReportQueueService.selectReportListByUser(reqMap);

            List<?> contentlist = (List<?>) resultData.getReturnData();
            if (!CollectionUtils.isEmpty(contentlist)) {
                userReportSize = contentlist.size();
            }

        } catch (Exception e) {
            logger.error("订单明细##查询用户导出记录##异常"+ JSON.toJSONString(reqMap));
            resultData.setFail("操作失败!");
            logger.error("pmlsReportOrderDetail", "PmlsReportOrderDetailController", "checkExport", reqMap.toString(), null, "", "查询用户是否重复导同一张报表", e);
        }
        returnView.setOperateReturnType();
        returnView.setReturnCode(resultData.getReturnCode());
        returnView.setReturnMsg(resultData.getReturnMsg());
        returnView.setReturnValue(userReportSize);

        return returnView;
    }

}
