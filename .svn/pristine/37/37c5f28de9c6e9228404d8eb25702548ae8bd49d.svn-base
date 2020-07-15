package cn.com.eju.deal.Report.controller;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.service.MembershipService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.WebUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "membership")
public class MembershipController extends BaseReportController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "membershipService")
    private MembershipService membershipService;


    /**
     * 初始化
     *
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap mop) {
        return "Report/membershipDetail";
    }

    /**
     * 查询--list
     *
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "query", method = RequestMethod.GET)
    public String queryList(HttpServletRequest request, ModelMap mop) {
        // 获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        // 分页信息
        PageInfo pageInfo = getPageInfo(request);

        //获取页面显示数据
        List<?> reportlist = new ArrayList<>();

        changeParam(reqMap);
        reqMap.put("userId", UserInfoHolder.getUserId());
        try {

            ResultData<?> reback = membershipService.queryList(reqMap, pageInfo);
            //页面数据
            reportlist = (List<?>) reback.getReturnData();

        } catch (Exception e) {
            logger.error("report", "MembershipController", "queryList", "", UserInfoHolder.getUserId(), "", "公盘会员明细查询失败", e);
        }

        //存放到mop中
        mop.addAttribute("reportlist", reportlist);

        return "Report/membershipDetailReportListCtx";
    }

    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void exportList(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());

        changeParam(reqMap);

        try {
            ResultData<?> reback = membershipService.exportList(reqMap);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            downLoadExcel(request, response, contentlist, reqMap, ReportConstant.MEMBERSHIP_CODE, ReportConstant.MEMBERSHIP_NAME);

        } catch (Exception e) {
            logger.error("report", "MembershipController", "exportList", "input param: reqMap=" + reqMap.toString(), -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }
    }


}
