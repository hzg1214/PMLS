package cn.com.eju.pmls.statisticsReport.store.controller;

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
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
import cn.com.eju.pmls.statisticsReport.store.service.RptStoreDetailService;

/**
 * Controller层
 * 门店
 */
@RestController
@RequestMapping(value = "rptStoreDetail")
public class RptStoreDetailController extends BaseReportController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private RptStoreDetailService rptStoreDetailService;

    @Resource
    private PmlsReportQueueService pmlsReportQueueService;

    @Autowired
    @Qualifier("threadPoolTaskExecutorRptStoreDetail")
    private ThreadPoolTaskExecutor threadPoolTaskExecutorRptStoreDetail;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, ModelMap mop) {

    	ModelAndView mv = new ModelAndView("statisticsReport/rptStore/rptStoreDetailList");
    	List<CommonCodeDto> brandTypeList = SystemParam.getCodeListByKey("294");//业务类型
        mop.addAttribute("brandTypeList", brandTypeList);
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("reportKey", "storeDetail_filepath");
        reqMap.put("userId", UserInfoHolder.getUserId());
        ResultData<?> reback = new ResultData<>();
        try {
            reback = pmlsReportQueueService.selectReportListByUser(reqMap);
        } catch (Exception e) {
            logger.error("store", "RptStoreDetailController", "list", "", null, "", "", e);
            reback.setFail();
        }
        List<?> contentlist = (List<?>) reback.getReturnData();
        int userReportSize = 0;
        if (!CollectionUtils.isEmpty(contentlist)) {
            userReportSize = contentlist.size();
        }
        mop.put("userReportSize", userReportSize);

        return mv;
    }

    /**
     * 查询--list
     */
    @RequestMapping(value = "queryStoreDetailList", method = RequestMethod.POST)
    public ResultData<?> queryStoreDetailList(HttpServletRequest request) {

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        PageInfo pageInfo = getPageInfo(request);
        reqMap.put("userId", UserInfoHolder.getUserId());

//        changeParamToList(reqMap, ",");
        //页面数据
        ResultData<?> reback = null;

        try {
            //获取页面显示数据
            reback = rptStoreDetailService.queryStoreDetailList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("store", "RptStoreDetailController", "queryStoreDetailList", "", null, "", "查询--list", e);
        }
        return reback;
    }

    /**
     * desc:门店明细导出
     * 2020年6月23日
     */
    @RequestMapping(value = "exportStoreDetailAjax", method = RequestMethod.GET)
    public ReturnView exportStoreDetailAjaxs(HttpServletRequest request, HttpServletResponse response) {

        logger.info("导出门店明细报表开始！");

        Map<String, Object> map = new HashMap<String, Object>();
        UserInfo userInfo = UserInfoHolder.get();
        if (userInfo == null) {
            map.put("message", "获取用户失败");
            return getOperateJSONView(map);
        }

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        reqMap.put("optFlag", "export");
//        changeParamToList(reqMap, ",");

        int queueSize = 0;
        try {
            //加
            pmlsReportQueueService.updateReportQueueTotal("storeDetail_filepath", 1);
            ReportQueueAjax reportDb = new ReportQueueAjax();
            reportDb.setDateCreate(new Date());
            reportDb.setDelFlag("N");
            reportDb.setReportKey("storeDetail_filepath");
            reportDb.setUserId(UserInfoHolder.getUserId());
            reportDb.setUserIdCreate(UserInfoHolder.getUserId());
            int id = pmlsReportQueueService.addReportQueueAjax(reportDb);

            String ctxPath = request.getSession().getServletContext().getRealPath("/");
            reqMap.put("ctxPath", ctxPath);
            logger.info("导出门店明细报表-downLoadExcelAjax-start！");
            rptStoreDetailService.downLoadExcelAjax(reqMap, ReportConstant.STOREDETAIL_CODE, ReportConstant.STOREDETAIL_NAME, id);
            logger.info("导出门店明细报表-downLoadExcelAjax-End！");
            ResultData resultData = pmlsReportQueueService.selectReportQueueTotalTopOne("storeDetail_filepath");
            LinkedHashMap<String, Object> linkedHashMap = (LinkedHashMap<String, Object>) resultData.getReturnData();
            if (resultData.getReturnData() != null) {
                queueSize = Integer.valueOf(linkedHashMap.get("positiveSize") + "") - Integer.valueOf(linkedHashMap.get("minusSize") + "") - threadPoolTaskExecutorRptStoreDetail.getCorePoolSize();
            }

            if (queueSize <= 0) {
                queueSize = 0;
            }
        } catch (Exception e) {
            logger.error("rptStore", "rptStoreDetailController", "export", "input param: reqMap=" + reqMap.toString(), -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }
        logger.info("导出门店明细报表结束！");
        map.put("message", "您前面还有" + queueSize + "人排队处理；正在排队处理请稍后(排队过程中可离开当前页面;下次进入门店明细表页面会自动下载)");
        return getOperateJSONView(map);
    }

}
