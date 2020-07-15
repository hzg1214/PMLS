package cn.com.eju.deal.houseLinkage.linkProjectTrace.controller;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.Report.model.ReportQueueAjax;
import cn.com.eju.deal.Report.service.ReportQueueService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.houseLinkage.linkProjectTrace.service.LinkProjectTraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Controller层
 * 联动项目跟踪
 *
 * @author yinkun
 */
@Controller
@RequestMapping(value = "linkProjectTrace")
public class LinkProjectTraceController extends BaseReportController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private LinkProjectTraceService linkProjectTraceService;

    @Resource
    private ReportQueueService reportQueueService;

    @Autowired
    @Qualifier("threadPoolTaskExecutorLinkDetail")
    private ThreadPoolTaskExecutor threadPoolTaskExecutorLinkDetail;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String linkProjectTraceList(ModelMap mop) {

        try {
            Map<String,Object> map = new HashMap<>();
            map.put("userCode",UserInfoHolder.get().getUserCode());
            ResultData<?> reback = linkProjectTraceService.queryCityList(map);
            List<?> citylist = (List<?>) reback.getReturnData();
            mop.addAttribute("citylist", citylist);
        } catch (Exception e) {
            logger.error("linkProjectTrace", "LinkProjectTraceController", "list", "", null, "", "查询城市", e);
        }

        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("reportKey", "linkDetail_filepath");
        reqMap.put("userId", UserInfoHolder.get().getUserId());
        ResultData<?> reback = new ResultData<>();
        try {
            reback = reportQueueService.selectReportListByUser(reqMap);
        } catch (Exception e) {
            logger.error("linkDetail", "LinkDetailController", "list", "", null, "", "", e);
            reback.setFail();
        }
        List<?> contentlist = (List<?>) reback.getReturnData();
        int userReportSize = 0;
        if (!CollectionUtils.isEmpty(contentlist)) {
            userReportSize = contentlist.size();
        }
        mop.put("userReportSize", userReportSize);

        return "houseLinkage/linkProjectTrace/linkProjectTraceList";
    }

    /**
     * 查询--list
     */
    @RequestMapping(value = "querylinkProjectTraceList", method = RequestMethod.GET)
    public String querylinkProjectTraceList(HttpServletRequest request, ModelMap mop) {

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        PageInfo pageInfo = getPageInfo(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        reqMap.put("userCode",UserInfoHolder.get().getUserCode());
        //点击查询
        reqMap.put("clickType",1);
        //处理参数
        dealRequestParam(reqMap);

        //页面数据
        List<?> contentlist = null;
        try {
            //获取页面显示数据
            ResultData<?> reback = linkProjectTraceService.queryLinkProjectTraceList(reqMap, pageInfo);
            //
            contentlist = (List<?>) reback.getReturnData();

        } catch (Exception e) {
            logger.error("linkProjectTrace", "LinkProjectTraceController", "queryLinkProjectTraceList", "", null, "", "查询--list", e);
        }

        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        mop.addAttribute("endDate", reqMap.get("endDate"));
        return "houseLinkage/linkProjectTrace/linkProjectTraceListCtx";
    }

    private void dealRequestParam(Map<String, Object> reqMap) {
        //当前年份
        Calendar date = Calendar.getInstance();
        reqMap.put("currentYear",date.get(Calendar.YEAR));

        //账龄年月
        /*if(reqMap.containsKey("accountAgeYear") && reqMap.containsKey("accountAgeMonth")){
            reqMap.put("accountAgeYearMonth",reqMap.get("accountAgeYear").toString()+"-"+reqMap.get("accountAgeMonth").toString());
        }*/

        //开始结束年月
        boolean initDateFlag = true;
        if(StringUtil.isEmpty(reqMap.get("startDate").toString())){
            initDateFlag = false;
            reqMap.put("startDate", "1879-01");
            reqMap.put("endDate", DateUtil.fmtDate(new Date(),"yyyy-MM"));
        }

        //前3个月为选择的开始年月的前3个月
        if(initDateFlag){
            date.setTime(DateUtil.getDate(reqMap.get("startDate").toString()+"-01","yyyy-MM-dd"));
        }

        String pre3Month = "";
        for (int i = 0;i<3;i++){
            date.add(Calendar.MONTH,-1);
            String suffix = "-";
            if(date.get(Calendar.MONTH)+1 < 10){
                suffix = "-0";
            }
            pre3Month += String.valueOf(date.get(Calendar.YEAR))+suffix+String.valueOf(date.get(Calendar.MONTH)+1)+"-01,";
        }
        pre3Month = pre3Month.substring(0,pre3Month.length()-1);
        reqMap.put("pre3Month",pre3Month);
    }

    /**
     * 导出EXCEL
     */
    @RequestMapping(value = "exportLinkProjectTrace", method = RequestMethod.GET)
    public void exportLinkProjectDetal(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        reqMap.put("optFlag", "export");
        reqMap.put("userCode",UserInfoHolder.get().getUserCode());
        //处理参数
        dealRequestParam(reqMap);

        try {
            ResultData<?> reback = linkProjectTraceService.queryLinkProjectTraceList(reqMap, null);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            downLoadExcel(request, response, contentlist, reqMap, ReportConstant.LINKPROJECTTRACE_CODE, ReportConstant.LINKPROJECTTRACE_NAME);

        } catch (Exception e) {
            logger.error("linkProjectTrace", "LinkProjectTraceController", "export", "input param: reqMap=" + reqMap.toString(), -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }

    }

    @RequestMapping(value = "exportLinkProjectTraceAjax", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView exportAjax(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        UserInfo userInfo = UserInfoHolder.get();
        if (userInfo == null) {
            map.put("message", "获取用户失败");
            return getOperateJSONView(map);
        }

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        reqMap.put("optFlag", "export");
        reqMap.put("userCode",UserInfoHolder.get().getUserCode());
        //点击导出
        reqMap.put("clickType",2);
        //处理参数
        dealRequestParam(reqMap);
        int queueSize = 0;
        try {
            //加
            reportQueueService.updateReportQueueTotal("linkDetail_filepath", 1);
            ReportQueueAjax reportDb = new ReportQueueAjax();
            reportDb.setDateCreate(new Date());
            reportDb.setDelFlag("N");
            reportDb.setReportKey("linkDetail_filepath");
            reportDb.setUserId(UserInfoHolder.getUserId());
            reportDb.setUserIdCreate(UserInfoHolder.getUserId());
            int id = reportQueueService.addReportQueueAjax(reportDb);

            String ctxPath = request.getSession().getServletContext().getRealPath("/");
            reqMap.put("ctxPath", ctxPath);
            linkProjectTraceService.downLoadExcelAjax(reqMap, ReportConstant.LINKPROJECTTRACE_CODE, ReportConstant.LINKPROJECTTRACE_NAME, id);

            ResultData resultData = reportQueueService.selectReportQueueTotalTopOne("linkDetail_filepath");
            LinkedHashMap<String, Object> linkedHashMap = (LinkedHashMap<String, Object>) resultData.getReturnData();
            if (resultData.getReturnData() != null) {
                queueSize = Integer.valueOf(linkedHashMap.get("positiveSize") + "") - Integer.valueOf(linkedHashMap.get("minusSize") + "") - threadPoolTaskExecutorLinkDetail.getCorePoolSize();
            }

            if (queueSize <= 0) {
                queueSize = 0;
            }
        } catch (Exception e) {
            logger.error("linkProjectTrace", "LinkProjectTraceController", "export", "input param: reqMap=" + reqMap.toString(), -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }
        map.put("message", "您前面还有" + queueSize + "人排队处理；正在排队处理请稍后(排队过程中可离开当前页面;下次进入联动跟踪分析表页面会自动下载)");
        return getOperateJSONView(map);
    }

}
