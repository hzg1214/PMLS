package cn.com.eju.deal.performanceQuery.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.performanceQuery.service.PerformanceQueryService;

/**
 * 业绩查询
 */
@Controller
@RequestMapping(value = "performanceQuery")
public class PerformanceQueryController extends BaseReportController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "performanceQueryService")
    private PerformanceQueryService performanceQueryService;

    /**
     * 业绩查询视图
     *
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView performanceQuery() {
        ModelAndView mav = new ModelAndView("performanceQuery/performanceQueryList");
        return mav;
    }

    /**
     * 查询业绩
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "query", method = RequestMethod.GET)
    public String query(HttpServletRequest request, ModelMap mop) {

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        PageInfo pageInfo = getPageInfo(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        reqMap.put("optFlag", "search");
        String startDate = (String) reqMap.get("startDate");
        String endDate = (String) reqMap.get("endDate");

        if (StringUtil.isEmpty(startDate)) {
            reqMap.put("dateStart", "2015-01-01");
        } else {
            reqMap.put("dateStart", startDate);
        }

        if (StringUtil.isEmpty(endDate)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            reqMap.put("dateEnd", sdf.format(new Date()));
        } else {
            reqMap.put("dateEnd", endDate);
        }

        changeParam(reqMap);

        //页面数据
        List<?> contentlist = new ArrayList<>();
        try {

            //获取页面显示数据
            ResultData<?> reback = performanceQueryService.queryPerformanceList(reqMap, pageInfo);

            //页面数据
            contentlist = (List<?>) reback.getReturnData();

        } catch (Exception e) {
            logger.error("performanceQuery", "PerformanceQueryController", "query", "", null, "", "业绩查询查询失败！", e);
        }

        //存放到mop中
        mop.addAttribute("contentList", contentlist);

        String sumBy = reqMap.get("sumBy").toString();

        if (sumBy.equals("center")) {
            return "performanceQuery/centerListCtx";
        } else if (sumBy.equals("city")) {
            return "performanceQuery/cityListCtx";
        } else if (sumBy.equals("store")) {
            return "performanceQuery/storeListCtx";
        } else {
            return "performanceQuery/personListCtx";
        }
    }


    /**
     * 导出
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        reqMap.put("optFlag", "export");

        String startDate = (String) reqMap.get("startDate");
        String endDate = (String) reqMap.get("endDate");

        if (StringUtil.isEmpty(startDate)) {
            reqMap.put("dateStart", "2015-01-01");
        } else {
            reqMap.put("dateStart", startDate);
        }

        if (StringUtil.isEmpty(endDate)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            reqMap.put("dateEnd", sdf.format(new Date()));
        } else {
            reqMap.put("dateEnd", endDate);
        }

        changeParam(reqMap);

        try {
            ResultData<?> reback = performanceQueryService.queryPerformanceList(reqMap, null);
            List<LinkedHashMap<String, Object>> contentList = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            downLoadExcel(request, response, contentList, reqMap, ReportConstant.EXCELFORPERFORMANCEQUERY_CODE, ReportConstant.EXCELFORPERFORMANCEQUERY_NAME);

        } catch (Exception e) {
            logger.error("performanceQuery", "PerformanceQueryController", "export", "", null, "", " 导出业绩查询失败！", e);
        }

    }
}
