package cn.com.eju.pmls.statisticsReport.rptQTLinkDetail.controller;


import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.pmls.statisticsReport.rptQTLinkDetail.service.RptQTLinkDetailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value = "rptQTLinkDetail")
public class RptQTLinkDetailController extends BaseReportController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private RptQTLinkDetailService rptQTLinkDetailService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView linkConversionRateList(ModelMap mop) {
        ModelAndView mv = new ModelAndView("statisticsReport/rptQTLinkDetail/rptQTLinkDetail");
        String cityNo = UserInfoHolder.get().getCityNo();
        mop.addAttribute("cityNo", cityNo);
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
            changeParamToList(reqMap, ",");
            changeParam(reqMap);

            resultData = rptQTLinkDetailService.queryList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("statisticsReport",
                    "RptQTLinkDetailController",
                    "queryList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询其他收入明细异常",
                    e);
        }
        return resultData;
    }

    @RequestMapping(value = "export", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> export(HttpServletRequest request){
        Map<String, Object> map = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            map.put("userId", UserInfoHolder.getUserId());
            changeParamToList(map, ",");
            changeParam(map);

            resultData = rptQTLinkDetailService.export(map);
        } catch (Exception e) {
            resultData.setFail("导出其他收入明细异常!");
            logger.error("statisticsReport",
                    "RptQTLinkDetailController",
                    "queryList",
                    "input param: " + map.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "导出其他收入明细异常!",
                    e);
        }
        return resultData;
    }

}
