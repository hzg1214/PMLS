package cn.com.eju.deal.houseLinkage.lnkAchievementSum.controller;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.houseLinkage.lnkAchievementSum.service.LnkAchievementSumService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller层
 * 联动业绩汇总
 */
@Controller
@RequestMapping(value = "lnkAchievementSum")
public class LnkAchievementSumController extends BaseReportController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private LnkAchievementSumService lnkAchievementSumService;
    /**
     * 初始化
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String lnkAchievementSumList(ModelMap mop) {
    	
        return "houseLinkage/lnkAchievementSum/lnkAchievementSumList";
    }
    /**
     * 查询--list
     */
    @RequestMapping(value = "queryLnkAchievementSumList", method = RequestMethod.GET)
    public String queryLnkAchievementSumList(HttpServletRequest request, ModelMap mop) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        PageInfo pageInfo = getPageInfo(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        changeParam(reqMap);
        //页面数据
        List<?> contentlist = null;
        try {
            //获取页面显示数据
        	String yearStr = reqMap.get("endDate").toString().substring(0,4);
        	reqMap.put("yearStr", yearStr);
            ResultData<?> reback = lnkAchievementSumService.queryLnkAchievementSumList(reqMap, pageInfo);
            contentlist = (List<?>) reback.getReturnData();
        } catch (Exception e) {
            logger.error("lnkAchievementSum", "LnkAchievementSumController", "queryLnkAchievementSumList", "", null, "", "查询--list", e);
        }
        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        String startDate = reqMap.get("startDate").toString();
        String endDate = reqMap.get("endDate").toString();
        mop.addAttribute("startDate", startDate);
        mop.addAttribute("endDate", endDate);
        return "houseLinkage/lnkAchievementSum/lnkAchievementSumListCtx";
    }

    /**
     * 导出EXCEL
     */
    @RequestMapping(value = "exportLnkAchievementSum", method = RequestMethod.GET)
    public void exportLnkAchievementSum(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        reqMap.put("optFlag", "export");
        changeParam(reqMap);
        try {
        	String yearStr = reqMap.get("endDate").toString().substring(0,4);
        	reqMap.put("yearStr", yearStr);
            ResultData<?> reback = lnkAchievementSumService.queryLnkAchievementSumList(reqMap, null);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            downLoadExcel(request, response, contentlist, reqMap, ReportConstant.LNKACHIEVEMENTSUM_CODE, ReportConstant.LNKACHIEVEMENTSUM_NAME);

        } catch (Exception e) {
            logger.error("lnkAchievementSum", "LnkAchievementSumController", "exportLnkAchievementSum", "input param: reqMap=" + reqMap.toString(), -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }

    }
    
}
