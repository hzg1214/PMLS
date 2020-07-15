package cn.com.eju.pmls.statisticsReport.common.controller;

import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.statisticsReport.common.service.PmlsCommonReportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "pmlsCommonReport")
public class PmlsCommonReportController extends BaseReportController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "pmlsCommonReportService")
    private PmlsCommonReportService pmlsCommonReportService;

    /*
     * 获取HBL归属区域
     */
    @RequestMapping(value = "queryHblRegionList", method = RequestMethod.GET)
    public ResultData<?> queryHblRegionList(HttpServletRequest request) {
        ResultData<?> resultData = null;
        try {
            Map<String, Object> reqMap = bindParamToMap(request);
            changeParamToList(reqMap, ",");
            reqMap.put("userCode", UserInfoHolder.get().getUserCode());
            resultData = pmlsCommonReportService.queryHblRegionList(reqMap);
        } catch (Exception ex) {
            logger.error("获取HBL归属区域异常", ex);
        }
        return resultData;
    }

    /*
     * 获取HBL归属板块
     */
    @RequestMapping(value = "queryHblAreaCityList", method = RequestMethod.GET)
    public ResultData<?> queryHblAreaCityList(HttpServletRequest request) {
        ResultData<?> resultData = null;
        try {
            Map<String, Object> reqMap = bindParamToMap(request);
            changeParamToList(reqMap, ",");
            reqMap.put("userCode", UserInfoHolder.get().getUserCode());
            resultData = pmlsCommonReportService.queryHblAreaCityList(reqMap);
        } catch (Exception ex) {
            logger.error("获取HBL归属板块异常", ex);
        }
        return resultData;
    }


    /*
     * 获取HBL归属城市
     */
    @RequestMapping(value = "queryHblCityList", method = RequestMethod.GET)
    public ResultData<?> queryHblCityList(HttpServletRequest request) {
        ResultData<?> resultData = null;
        try {
            Map<String, Object> reqMap = bindParamToMap(request);
            changeParamToList(reqMap, ",");
            reqMap.put("userCode", UserInfoHolder.get().getUserCode());
            resultData = pmlsCommonReportService.queryHblCityList(reqMap);
        } catch (Exception ex) {
            logger.error("获取HBL归属城市异常", ex);
        }
        return resultData;
    }


    /*
     * 获取HBL归属中心
     */
    @RequestMapping(value = "queryHblCenterList", method = RequestMethod.GET)
    public ResultData<?> queryHblCenterList(HttpServletRequest request) {
        ResultData<?> resultData = null;
        try {
            Map<String, Object> reqMap = bindParamToMap(request);
            changeParamToList(reqMap, ",");
            reqMap.put("userCode", UserInfoHolder.get().getUserCode());
            resultData = pmlsCommonReportService.queryHblCenterList(reqMap);
        } catch (Exception ex) {
            logger.error("获取HBL归属中心异常", ex);
        }
        return resultData;
    }
}
