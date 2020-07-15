package cn.com.eju.deal.performanceSum.controller;


import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.dto.performanceSum.PerformanceSumColumnDto;
import cn.com.eju.deal.performanceSum.service.PerformanceSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Controller层
 * Created by tanlang on 2017-05-23.
 */
@Controller
@RequestMapping(value = "performanceSum")
public class PerformanceSumController extends BaseReportController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private PerformanceSumService performanceSumService;

    @RequestMapping(value = "weekSum", method = RequestMethod.GET)
    public String weekSum() {
        return "performanceSum/performanceSumWeek";
    }

    @RequestMapping(value = "monthSum", method = RequestMethod.GET)
    public String monthSum() {
        return "performanceSum/performanceSumMonth";
    }

    @RequestMapping(value = "yearSum", method = RequestMethod.GET)
    public String yearSum() {
        return "performanceSum/performanceSumYear";
    }

    @RequestMapping(value = "currentSum", method = RequestMethod.GET)
    public String currentSum() {
        return "performanceSum/performanceSumCurrent";
    }

    @RequestMapping(value = "queryWeek", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView queryWeek(HttpServletRequest request) {

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        LinkedHashMap contentlist = null;

        changeParam(reqMap);

        try {
            ResultData<?> reback = performanceSumService.queryList(reqMap, null);
            //页面数据
            contentlist = (LinkedHashMap) reback.getReturnData();
        } catch (Exception e) {

            logger.error("performanceSum", "PerformanceSumController", "queryWeek", "", null, "", "", e);
        }

        ModelAndView mv = new ModelAndView("performanceSum/performanceSumCtx");

        List<PerformanceSumColumnDto> columnDtos = (List<PerformanceSumColumnDto>) contentlist.get("columnDtos");

        mv.addObject("columnlist", columnDtos);
        mv.addObject("length", columnDtos.size() - 4);
        mv.addObject("contentlist", contentlist.get("contentDtos"));

        return mv;
    }

    @RequestMapping(value = "exportWeek", method = RequestMethod.GET)
    public void exportWeek(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        reqMap = changeParam(reqMap);
        try {
            ResultData<?> reback = performanceSumService.queryList(reqMap, null);
            LinkedHashMap<String, Object> contentlist = (LinkedHashMap<String, Object>) reback.getReturnData();

            List<LinkedHashMap<String, Object>> list = new ArrayList<>();
            list.add(contentlist);
            downLoadExcel(request, response, list, reqMap, ReportConstant.PERFORMANCESUM_CODE, ReportConstant.PERFORMANCESUM_WEEK_NAME);

        } catch (Exception e) {
            logger.error("导出异常", e);
        }

    }

    @RequestMapping(value = "queryMonth", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView queryMonth(HttpServletRequest request) {

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        reqMap.put("dateEnd", reqMap.get("dateEnd") + "-01");
        LinkedHashMap contentlist = null;

        changeParam(reqMap);

        try {
            ResultData<?> reback = performanceSumService.queryList(reqMap, null);
            //页面数据
            contentlist = (LinkedHashMap) reback.getReturnData();

        } catch (Exception e) {
            logger.error("performanceSum", "PerformanceSumController", "queryMonth", "", null, "", "", e);
        }

        ModelAndView mv = new ModelAndView("performanceSum/performanceSumCtx");

        List<PerformanceSumColumnDto> columnDtos = (List<PerformanceSumColumnDto>) contentlist.get("columnDtos");

        mv.addObject("columnlist", columnDtos);
        mv.addObject("length", columnDtos.size() - 4);
        mv.addObject("contentlist", contentlist.get("contentDtos"));

        return mv;
    }

    @RequestMapping(value = "exportMonth", method = RequestMethod.GET)
    public void exportMonth(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        reqMap.put("dateEnd", reqMap.get("dateEnd") + "-01");
        reqMap = changeParam(reqMap);
        try {

            ResultData<?> reback = performanceSumService.queryList(reqMap, null);
            LinkedHashMap<String, Object> contentlist = (LinkedHashMap<String, Object>) reback.getReturnData();

            List<LinkedHashMap<String, Object>> list = new ArrayList<>();
            list.add(contentlist);
            downLoadExcel(request, response, list, reqMap, ReportConstant.PERFORMANCESUM_CODE, ReportConstant.PERFORMANCESUM_MONTH_NAME);

        } catch (Exception e) {
            logger.error("导出异常", e);
        }

    }

    @RequestMapping(value = "queryYear", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView queryYear(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        reqMap.put("dateEnd", reqMap.get("dateEnd") + "-01");

        changeParam(reqMap);

        LinkedHashMap contentlist = null;

        try {
            ResultData<?> reback = performanceSumService.queryList(reqMap, null);
            //页面数据
            contentlist = (LinkedHashMap) reback.getReturnData();
        } catch (Exception e) {
            logger.error("performanceSum", "PerformanceSumController", "queryYear", "", null, "", "", e);
        }

        ModelAndView mv = new ModelAndView("performanceSum/performanceSumCtx");

        List<PerformanceSumColumnDto> columnDtos = (List<PerformanceSumColumnDto>) contentlist.get("columnDtos");

        mv.addObject("columnlist", columnDtos);
        mv.addObject("length", columnDtos.size() - 4);
        mv.addObject("contentlist", contentlist.get("contentDtos"));

        return mv;
    }

    @RequestMapping(value = "exportYear", method = RequestMethod.GET)
    public void exportYear(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        reqMap.put("dateEnd", reqMap.get("dateEnd") + "-01");

        changeParam(reqMap);

        try {

            ResultData<?> reback = performanceSumService.queryList(reqMap, null);
            LinkedHashMap<String, Object> contentlist = (LinkedHashMap<String, Object>) reback.getReturnData();

            List<LinkedHashMap<String, Object>> list = new ArrayList<>();
            list.add(contentlist);
            downLoadExcel(request, response, list, reqMap, ReportConstant.PERFORMANCESUM_CODE, ReportConstant.PERFORMANCESUM_YEAR_NAME);

        } catch (Exception e) {
            logger.error("导出异常", e);
        }

    }

    @RequestMapping(value = "queryCurrent", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView queryCurrent(HttpServletRequest request) {

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());

        changeParam(reqMap);

        LinkedHashMap contentlist = null;
        try {
            ResultData<?> reback = performanceSumService.queryList(reqMap, null);
            //页面数据
            contentlist = (LinkedHashMap) reback.getReturnData();

        } catch (Exception e) {
            logger.error("queryCurrent", e);
        }

        ModelAndView mv = new ModelAndView("performanceSum/performanceSumCtx");

        List<PerformanceSumColumnDto> columnDtos = (List<PerformanceSumColumnDto>) contentlist.get("columnDtos");

        mv.addObject("columnlist", columnDtos);
        mv.addObject("length", columnDtos.size() - 4);
        mv.addObject("contentlist", contentlist.get("contentDtos"));

        return mv;
    }

    @RequestMapping(value = "exportCurrent", method = RequestMethod.GET)
    public void exportCurrent(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());

        changeParam(reqMap);

        try {
            ResultData<?> reback = performanceSumService.queryList(reqMap, null);
            LinkedHashMap<String, Object> contentlist = (LinkedHashMap<String, Object>) reback.getReturnData();

            List<LinkedHashMap<String, Object>> list = new ArrayList<>();
            list.add(contentlist);
            downLoadExcel(request, response, list, reqMap, ReportConstant.PERFORMANCESUM_CODE, ReportConstant.PERFORMANCESUM_CURRENT_NAME);

        } catch (Exception e) {
            logger.error("导出异常", e);
        }

    }


    @RequestMapping(value = "queryCityList", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> queryCityList(HttpServletRequest request) {

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        ResultData<?> resultData = new ResultData<>();
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try {
            resultData = performanceSumService.queryCityList(reqMap);
        } catch (Exception e) {
            logger.error("", "PerformanceSumController", "queryCityList",
                    "", UserInfoHolder.getUserId(), "", "获取城市失败", e);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        if (null != resultData.getReturnData()) {
            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        }

        return getMapView(rspMap);
    }

    @RequestMapping(value = "queryCenterList", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> queryCenterList(HttpServletRequest request) {

        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = new ResultData<>();
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try {
            resultData = performanceSumService.queryCenterList(reqMap);
        } catch (Exception e) {
            logger.error("", "PerformanceSumController", "queryCityList",
                    "", UserInfoHolder.getUserId(), "", "获取中心失败", e);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        if (null != resultData.getReturnData()) {
            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        }

        return getMapView(rspMap);
    }
}
