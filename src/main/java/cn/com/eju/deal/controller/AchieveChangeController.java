package cn.com.eju.deal.controller;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.FileUtils;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.poi.ExcelForAchieveChange;
import cn.com.eju.deal.service.AchieveChangeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/23.
 */
@Controller
@RequestMapping(value = "achieveChange")
public class AchieveChangeController extends BaseReportController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());


    @Resource(name = "achieveChangeService")
    private AchieveChangeService achieveChangeService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String list(HttpServletRequest request) {
        return "achieveChange/index";
    }

    /**
     * 查询--list
     */
    @RequestMapping(value = "queryList", method = RequestMethod.GET)
    public String queryList(HttpServletRequest request, ModelMap mop) {

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        PageInfo pageInfo = getPageInfo(request);

        changeParamToList(reqMap);
        changeParam(reqMap);

        //页面数据
        List<?> contentlist = null;
        try {
            //获取页面显示数据
            ResultData<?> reback = achieveChangeService.queryList(reqMap, pageInfo);

            contentlist = (List<?>) reback.getReturnData();

        } catch (Exception e) {
            logger.error("achieveChange", "AchievementChangeController", "queryList", "", null, "", "查询--list", e);
        }

        //存放到mop中
        mop.addAttribute("contentlist", contentlist);

        return "achieveChange/listPage";
    }

    /**
     * 导出EXCEL
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> reqMap = bindParamToMap(request);

        changeParamToList(reqMap);
        changeParam(reqMap);

        try {
            ResultData<?> reback = achieveChangeService.queryList(reqMap, null);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            downLoadExcel(request, response, contentlist, reqMap, ReportConstant.ACHIEVECHANGE_CODE, ReportConstant.ACHIEVECHANGE_NAME);

        } catch (Exception e) {
            logger.error("AchieveChange", "AchieveChange", "export", "input param: reqMap=" + reqMap.toString(), -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }

    }
}
