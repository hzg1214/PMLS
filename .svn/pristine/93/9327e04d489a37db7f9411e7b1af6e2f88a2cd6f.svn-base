package cn.com.eju.pmls.statisticsReport.projectDetail.controller;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.pmls.commission.service.CrmOriginalService;
import cn.com.eju.pmls.statisticsReport.projectDetail.service.PmlsProjectDetailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by haidan on 2020/6/16.
 */
@Controller
@RequestMapping(value = "pmlsProjectDetail")
public class PmlsProjectDetailController extends BaseReportController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    private PmlsProjectDetailService pmlsProjectDetailService;
    @Resource(name = "crmOriginalService")
    private CrmOriginalService crmOriginalService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView projectDetailList(ModelMap mop) {
        ModelAndView mv = new ModelAndView("statisticsReport/projectDetail/projectDetailList");
        String cityNo = UserInfoHolder.get().getCityNo();
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("cityNo", cityNo);
        //归属项目部列表
        try {
            ResultData<?> resultData = crmOriginalService.getProjectDepartment(reqMap);
            List<?> rebacklist = (List<?>) resultData.getReturnData();
            mop.put("rebacklist", rebacklist);
        } catch (Exception e) {
            logger.error("pmlsProjectDetail", "PmlsProjectDetailController", "", "", null, "", "初始化-归属项目部", e);
        }
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
            changeParamToList(reqMap,",");
            changeParam(reqMap);

            resultData = pmlsProjectDetailService.queryList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("statisticsReport","PmlsProjectDetailController","queryList","input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),WebUtil.getIPAddress(request),"查询开发项目明细列表数据内容异常",e);
        }
        return resultData;
    }

    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        changeParamToList(reqMap,",");
        changeParam(reqMap);

        try {
            reqMap.put("optFlag", 1);
            //获取页面显示数据
            ResultData<?> reback = pmlsProjectDetailService.queryList(reqMap, null);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            downLoadExcel(request, response, contentlist, reqMap, ReportConstant.PROJECTDETAIL_CODE, ReportConstant.PROJECTDETAIL_NAME);

        } catch (Exception e) {
            logger.error("statisticsReport", "PmlsProjectDetailController", "export", reqMap.toString(), null, "", "导出开发项目明细Excel失败", e);
        }
    }
}
