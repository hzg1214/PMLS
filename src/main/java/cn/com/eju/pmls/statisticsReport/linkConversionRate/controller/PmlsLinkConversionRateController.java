package cn.com.eju.pmls.statisticsReport.linkConversionRate.controller;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.pmls.statisticsReport.linkConversionRate.service.PmlsLinkConversionRateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "pmlsLinkConversionRate")
public class PmlsLinkConversionRateController extends BaseReportController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private PmlsLinkConversionRateService pmlsLinkConversionRateService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView linkConversionRateList(ModelMap mop) {
        ModelAndView mv = new ModelAndView("statisticsReport/linkConversionRate/linkConversionRate");
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
//            changeParamToList(reqMap,",");
            changeParam(reqMap);
//            reqMap = this.handleDate(reqMap);

            resultData = pmlsLinkConversionRateService.queryList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("statisticsReport",
                    "PmlsLinkConversionRateController",
                    "queryList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询联动转化率列表数据内容异常",
                    e);
        }
        return resultData;
    }

    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
//        changeParamToList(reqMap,",");
        changeParam(reqMap);

        try {
            //处理日期
//            reqMap = this.handleDate(reqMap);
            reqMap.put("optFlag", 1);
            //获取页面显示数据
            ResultData<?> reback = pmlsLinkConversionRateService.queryList(reqMap, null);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            downLoadExcel(request, response, contentlist, reqMap, ReportConstant.LINKCONVERSIONRATE_CODE, ReportConstant.LINKCONVERSIONRATE_NAME);

        } catch (Exception e) {
            logger.error("linkConversionRate", "LinkConversionRateController", "export", reqMap.toString(), null, "", "导出Excel失败", e);
        }
    }

}
