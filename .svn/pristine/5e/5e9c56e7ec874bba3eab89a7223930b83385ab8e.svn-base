package cn.com.eju.pmls.statisticsReport.linkZjcbDetail.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.Report.model.ReportQueueAjax;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.houseLinkage.estate.service.EstateService;
import cn.com.eju.pmls.report.service.PmlsReportQueueService;
import cn.com.eju.pmls.statisticsReport.linkZjcbDetail.service.PmlsLinkDyDetailService;

/**
 * desc:联动资金成本(垫佣资金)
 *
 * @date :2019年7月18日
 */
@Controller
@RequestMapping(value = "pmlsLinkDyDetail")
public class PmlsLinkDyDetailController extends BaseReportController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private PmlsLinkDyDetailService pmlsLinkDyDetailService;

    @Resource
    private PmlsReportQueueService pmlsReportQueueService;

    @Autowired
    @Qualifier("threadPoolTaskExecutorPmlsLinkDyDetail")
    private ThreadPoolTaskExecutor threadPoolTaskExecutorPmlsLinkZjcbDetail;

    @Resource(name = "estateService")
    private EstateService estateService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String linkZjcbDetailList(HttpServletRequest request, ModelMap mop) {

        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("reportKey", "linkZjcbDetail_filepath");
        reqMap.put("userId", UserInfoHolder.getUserId());
        ResultData<?> reback = new ResultData<>();
        try {
            reback = pmlsReportQueueService.selectReportListByUser(reqMap);
        } catch (Exception e) {
            logger.error("pmlsLinkDyDetail", "PmlsLinkZjcbDetailController", "list", "", null, "", "", e);
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
            reqMap.put("cityNo", cityNo);
            ResultData<?> resultData = estateService.getEstateNmList(reqMap);
            List<?> estateList = (List<?>) resultData.getReturnData();
            mop.put("estateList", estateList);
            mop.addAttribute("estateTypeList", SystemParam.getCodeListByKey("245"));
        } catch (Exception e) {
            logger.error("sceneInCommission", "LnkYjReportController", "", "", null, "", "创建--初始化-楼盘名称", e);
        }
        return "statisticsReport/linkZjcbDetail/linkDyDetailList";
    }

    @ResponseBody
    @RequestMapping(value = "getDateStage", method = RequestMethod.POST)
	public ResultData<?> getDateStage(HttpServletRequest request) {
		Map<String, Object> reqMap = bindParamToMap(request);
		ResultData<Map<String, Object>> dateStage = new ResultData<>();
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

		Map<String, Object> map = new HashMap<>();
		map.put("qcTimeStage", qcTimeStage);
		map.put("zljTimeStage", zljTimeStage);
		map.put("dnyqTimeStage", dnyqTimeStage);
		map.put("dnxzTimeStage", dnxzTimeStage);
		map.put("year", year);
		dateStage.setReturnCode("200");
		dateStage.setReturnData(map);
		dateStage.setReturnMsg("成功");
		return dateStage;
	}
    
    /**
     * desc:查询列表
     * 2019年7月18日
     * author:zhenggang.Huang
     */
    @ResponseBody
    @RequestMapping(value = "queryLinkZjcbDetailList", method = RequestMethod.POST)
    public ResultData<?> queryLinkZjcbDetailList(HttpServletRequest request) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        PageInfo pageInfo = getPageInfo(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        
//        String dateStage = (String) reqMap.get("dateStage");
//        reqMap.put("dateStage", dateStage);
        changeParamToList(reqMap, ",");
        //changeParam(reqMap);

        //页面数据
        ResultData<?> reback =null;
        
        try {
            //获取页面显示数据
            reback = pmlsLinkDyDetailService.queryLinkZjcbDetailList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("pmlsLinkDyDetail", "PmlsLinkZjcbDetailController", "queryLinkZjcbDetailList", "", null, "", "查询--list", e);
        }
        return reback;
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
//        UserInfo userInfo = UserInfoHolder.get();
//        if (userInfo == null) {
//            map.put("message", "获取用户失败");
//            return getOperateJSONView(map);
//        }

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());

        changeParamToList(reqMap, ",");
        //reqMap = changeParam(reqMap);

        int queueSize = 0;
        try {
            //加
        	pmlsReportQueueService.updateReportQueueTotal("linkZjcbDetail_filepath", 1);
            ReportQueueAjax reportDb = new ReportQueueAjax();
            reportDb.setDateCreate(new Date());
            reportDb.setDelFlag("N");
            reportDb.setReportKey("linkZjcbDetail_filepath");
            reportDb.setUserId(UserInfoHolder.getUserId());
            reportDb.setUserIdCreate(UserInfoHolder.getUserId());
            int id = pmlsReportQueueService.addReportQueueAjax(reportDb);

            String ctxPath = request.getSession().getServletContext().getRealPath("/");
            reqMap.put("ctxPath", ctxPath);
            pmlsLinkDyDetailService.downLoadExcelAjax(reqMap, ReportConstant.LINKZJCBDETAIL_CODE, ReportConstant.LINKZJCBDETAIL_NAME, id);

            ResultData resultData = pmlsReportQueueService.selectReportQueueTotalTopOne("linkZjcbDetail_filepath");
            LinkedHashMap<String, Object> linkedHashMap = (LinkedHashMap<String, Object>) resultData.getReturnData();
            if (resultData.getReturnData() != null) {
                queueSize = Integer.valueOf(linkedHashMap.get("positiveSize") + "") - Integer.valueOf(linkedHashMap.get("minusSize") + "") - threadPoolTaskExecutorPmlsLinkZjcbDetail.getCorePoolSize();
            }
            if (queueSize <= 0) {
                queueSize = 0;
            }
        } catch (Exception e) {
            logger.error("pmlsLinkZjcbDetail", "PmlsLinkZjcbDetailController", "export", "input param: reqMap=" + reqMap.toString(), -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }
        map.put("message", "您前面还有" + queueSize + "人排队处理；正在排队处理请稍后(排队过程中可离开当前页面;下次进入联动资金成本(垫佣)页面会自动下载)");
        return getOperateJSONView(map);
    }

}
