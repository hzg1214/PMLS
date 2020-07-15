package cn.com.eju.deal.houseLinkage.linkZjcbDetail;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.Report.model.ReportQueueAjax;
import cn.com.eju.deal.Report.service.ReportQueueService;
import cn.com.eju.deal.base.dto.code.CommonCodeDto;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.houseLinkage.estate.service.EstateService;
import cn.com.eju.deal.houseLinkage.linkZjcbDetail.service.LinkZjcbDetailService;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * desc:联动资金成本(垫佣资金)
 *
 * @author :zhenggang.Huang
 * @date :2019年7月18日
 */
@Controller
@RequestMapping(value = "linkZjcbDetail")
public class LinkZjcbDetailController extends BaseReportController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private LinkZjcbDetailService linkZjcbDetailService;

    @Resource
    private ReportQueueService reportQueueService;

    @Autowired
    @Qualifier("threadPoolTaskExecutorLinkZjcbDetail")
    private ThreadPoolTaskExecutor threadPoolTaskExecutorLinkZjcbDetail;

    @Resource(name = "estateService")
    private EstateService estateService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String linkZjcbDetailList(HttpServletRequest request, ModelMap mop) {
        List<CommonCodeDto> customerFromList = SystemParam.getCodeListByKey("174");//报备来源
        mop.addAttribute("customerFromList", customerFromList);

        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("reportKey", "linkZjcbDetail_filepath");
        reqMap.put("userId", UserInfoHolder.get().getUserId());
        ResultData<?> reback = new ResultData<>();
        try {
            reback = reportQueueService.selectReportListByUser(reqMap);
        } catch (Exception e) {
            logger.error("linkZjcbDetail", "LinkZjcbDetailController", "list", "", null, "", "", e);
            reback.setFail();
        }
        List<?> contentlist = (List<?>) reback.getReturnData();
        int userReportSize = 0;
        if (!CollectionUtils.isEmpty(contentlist)) {
            userReportSize = contentlist.size();
        }
        mop.put("userReportSize", userReportSize);

        String cityNo = UserInfoHolder.get().getCityNo();
        try {
//            Map<String,Object> estateMap = new HashMap<>();
            reqMap.put("cityNo", cityNo);
            ResultData<?> resultData = estateService.getEstateNmList(reqMap);
            List<?> estateList = (List<?>) resultData.getReturnData();
            mop.put("estateList", estateList);
            mop.addAttribute("estateTypeList", SystemParam.getCodeListByKey("245"));
        } catch (Exception e) {
            logger.error("sceneInCommission", "LnkYjReportController", "", "", null, "", "创建--初始化-楼盘名称", e);
        }
        return "houseLinkage/linkZjcbDetail/linkZjcbDetailList";
    }

    /**
     * desc:查询列表
     * 2019年7月18日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "queryLinkZjcbDetailList", method = RequestMethod.GET)
    public String queryLinkZjcbDetailList(HttpServletRequest request, ModelMap mop) {

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        PageInfo pageInfo = getPageInfo(request);
        reqMap.put("userId", UserInfoHolder.getUserId());

//        changeParamToList(reqMap);
        changeParam(reqMap);
        //页面数据
        List<?> contentlist = null;

        try {
            //获取页面显示数据
            ResultData<?> reback = linkZjcbDetailService.queryLinkZjcbDetailList(reqMap, pageInfo);

            contentlist = (List<?>) reback.getReturnData();
        } catch (Exception e) {
            logger.error("linkZjcbDetail", "LinkZjcbDetailController", "queryLinkZjcbDetailList", "", null, "", "查询--list", e);
        }

        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        String organization = (String) reqMap.get("organization");
        if (StringUtil.isNotEmpty(organization)) {
            year = organization;
        }
        // 起初时间
        String qcTimeStage = "";
        String zljTimeStage = "";
        String dnyqTimeStage = "";
        String dnxzTimeStage = "";

        String dnTimeStr = year + "-01-01";
        LocalDate dnld = LocalDate.parse(dnTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dnyqld = dnld.plusDays(-1);
        // 起初没有选择的场合
        if (!reqMap.containsKey("dateStage") || "".equals(reqMap.get("dateStage").toString())) {

            dnyqTimeStage = dnyqld.toString() + "及以前";
            dnxzTimeStage = dnTimeStr + "及以后";
        }
        // 选择起初的场合
        else {
            String qcDateStr = reqMap.get("dateStage").toString() + "-01";
            LocalDate qcld = LocalDate.parse(qcDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate qcyqld = qcld.plusDays(-1);
            qcTimeStage = qcyqld.toString() + "及以前";
            zljTimeStage = qcDateStr + "及以后";

            // 起初<年度
            if (qcld.isBefore(dnld)) {
                dnyqTimeStage = qcDateStr + "至" + dnyqld.toString();
                dnxzTimeStage = dnTimeStr + "及以后";
            }
            // 起初>年度
            else {
                dnyqTimeStage = "";
                dnxzTimeStage = qcDateStr + "及以后";
            }
        }

        mop.addAttribute("qcTimeStage", qcTimeStage);
        mop.addAttribute("zljTimeStage", zljTimeStage);
        mop.addAttribute("dnyqTimeStage", dnyqTimeStage);
        mop.addAttribute("dnxzTimeStage", dnxzTimeStage);

        mop.addAttribute("year", year);

        //存放到mop中
        mop.addAttribute("contentlist", contentlist);

//        return "houseLinkage/linkZjcbDetail/linkZjcbDetailListCtx";
        return "houseLinkage/linkZjcbDetail/linkZjcbDetailListCtxNew";
    }

    /**
     * desc:导出excel
     * 2019年7月19日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "exportLinkZjcbDetailAjax", method = RequestMethod.GET)
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

        //changeParamToList(reqMap);
        reqMap = changeParam(reqMap);

        int queueSize = 0;
        try {
            //加
            reportQueueService.updateReportQueueTotal("linkZjcbDetail_filepath", 1);
            ReportQueueAjax reportDb = new ReportQueueAjax();
            reportDb.setDateCreate(new Date());
            reportDb.setDelFlag("N");
            reportDb.setReportKey("linkZjcbDetail_filepath");
            reportDb.setUserId(UserInfoHolder.getUserId());
            reportDb.setUserIdCreate(UserInfoHolder.getUserId());
            int id = reportQueueService.addReportQueueAjax(reportDb);

            String ctxPath = request.getSession().getServletContext().getRealPath("/");
            reqMap.put("ctxPath", ctxPath);
            linkZjcbDetailService.downLoadExcelAjax(reqMap, ReportConstant.LINKZJCBDETAIL_CODE, ReportConstant.LINKZJCBDETAIL_NAME, id);

            ResultData resultData = reportQueueService.selectReportQueueTotalTopOne("linkZjcbDetail_filepath");
            LinkedHashMap<String, Object> linkedHashMap = (LinkedHashMap<String, Object>) resultData.getReturnData();
            if (resultData.getReturnData() != null) {
                queueSize = Integer.valueOf(linkedHashMap.get("positiveSize") + "") - Integer.valueOf(linkedHashMap.get("minusSize") + "") - threadPoolTaskExecutorLinkZjcbDetail.getCorePoolSize();
            }
            if (queueSize <= 0) {
                queueSize = 0;
            }
        } catch (Exception e) {
            logger.error("linkZjcbDetail", "LinkZjcbDetailController", "export", "input param: reqMap=" + reqMap.toString(), -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }
        map.put("message", "您前面还有" + queueSize + "人排队处理；正在排队处理请稍后(排队过程中可离开当前页面;下次进入联动资金成本(垫佣)页面会自动下载)");
        return getOperateJSONView(map);
    }

}
