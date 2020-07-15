package cn.com.eju.pmls.statisticsReport.badDebts.controller;

import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.pmls.statisticsReport.badDebts.service.BadDebtsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value = "badDebts")
public class BadDebtsController extends BaseReportController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private BadDebtsService badDebtsService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView linkConversionRateList(ModelMap mop) {
        ModelAndView mv = new ModelAndView("statisticsReport/badDebts/badDebtsDetailList");
        return mv;
    }

    @RequestMapping(value = "queryList", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> queryList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            reqMap.put("userId", UserInfoHolder.getUserId());
            reqMap.put("clickType",1);
            resultData = badDebtsService.queryList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("statisticsReport",
                    "BadDebtsController",
                    "queryList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询坏账明细异常",
                    e);
        }
        return resultData;
    }


    @RequestMapping(value = "export", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> export(HttpServletRequest request) {
        Map<String, Object> map = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            map.put("userId", UserInfoHolder.getUserId());
            map.put("clickType",2);
            resultData = badDebtsService.export(map);
        } catch (Exception e) {
            resultData.setFail("导出坏账明细异常!");
            logger.error("statisticsReport",
                    "BadDebtsController",
                    "queryList",
                    "input param: " + map.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "导出坏账明细异常!",
                    e);
        }
        return resultData;
    }

}
