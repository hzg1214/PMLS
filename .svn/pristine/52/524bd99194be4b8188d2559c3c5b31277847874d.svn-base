package cn.com.eju.pmls.statisticsReport.linkZjcbDetail.controller;

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
import cn.com.eju.deal.base.dto.code.CommonCodeDto;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.houseLinkage.estate.service.EstateService;
import cn.com.eju.pmls.report.service.PmlsReportQueueService;
import cn.com.eju.pmls.statisticsReport.linkZjcbDetail.service.PmlsLinkMarginDetailService;

/**
 * desc:联动资金成本(诚意金、保证金资金)
 * @author :zhenggang.Huang
 * @date   :2019年9月12日
 */
@Controller
@RequestMapping(value = "pmlsLinkMarginDetail")
public class PmlsLinkMarginDetailController extends BaseReportController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private PmlsLinkMarginDetailService linkMarginDetailService;

    @Resource
    private PmlsReportQueueService pmlsReportQueueService;

    @Autowired
    @Qualifier("threadPoolTaskExecutorPmlsLinkMarginDetail")
    private ThreadPoolTaskExecutor threadPoolTaskExecutorPmlsLinkMarginDetail;
    
    @Resource(name = "estateService")
    private EstateService estateService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String linkMarginDetailList(HttpServletRequest request, ModelMap mop) {

    	List<CommonCodeDto> marginTypeCodeList = SystemParam.getCodeListByKey("267");//类型
        mop.addAttribute("marginTypeCodeList", marginTypeCodeList);
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("reportKey", "linkMarginDetail_filepath");
        reqMap.put("userId", UserInfoHolder.getUserId());
        ResultData<?> reback = new ResultData<>();
        
        try {
            reback = pmlsReportQueueService.selectReportListByUser(reqMap);
        } catch (Exception e) {
            logger.error("linkMarginDetail", "LinkMarginDetailController", "list", "", null, "", "", e);
            reback.setFail();
        }
        List<?> contentlist = (List<?>) reback.getReturnData();
        int userReportSize = 0;
        if (!CollectionUtils.isEmpty(contentlist)) {
            userReportSize = contentlist.size();
        }
        mop.put("userReportSize", userReportSize);

        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        String organization = (String) reqMap.get("organization");
        if (StringUtil.isNotEmpty(organization)) {
            year = organization;
        }
        mop.put("year", year);
        mop.put("marginTypeList", SystemParam.getCodeListByKey(DictionaryConstants.LINK_MARGIN_TYPE));
        return "statisticsReport/linkZjcbDetail/linkMarginDetailList";
    }

    /**
     * desc:获取成本中心列表
     * 2019年9月12日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "getCostList", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getCostList(HttpServletRequest request) {

        //返回map
        ResultData resultData = new ResultData<>();

        Map<String, Object> rspMap = bindParamToMap(request);
        String organization = rspMap.get("organization").toString();

        try {
            resultData =  linkMarginDetailService.queryCostCenterList(organization);
        } catch (Exception e) {
            logger.error("pmlsLinkMarginDetail", "PmlsLinkMarginDetailController", "getCostList", "", null, "", "获取成本中心列表失败", e);
        }

        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());

        return getSearchJSONView(rspMap);
    }
    
    /**
     * desc:查询列表
     * 2019年9月12日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "queryLinkMarginDetailList", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> queryLinkMarginDetailList(HttpServletRequest request, ModelMap mop) {

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        PageInfo pageInfo = getPageInfo(request);
        //页面数据
        List<?> contentlist = null;
        ResultData<?> reback= null;
        changeParamToList(reqMap, ",");
        try {
            //获取页面显示数据
            reback = linkMarginDetailService.queryLinkMarginDetailList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("pmlsLinkMarginDetail", "PmlsLinkMarginDetailController", "queryLinkMarginDetailList", "", null, "", "查询--list", e);
        }

        return reback;
    }

    /**
     * desc:导出excel
     * 2019年9月12日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "exportLinkMarginDetailAjax", method = RequestMethod.GET)
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

        changeParamToList(reqMap, ",");
        int queueSize = 0;
        try {
            //加
        	pmlsReportQueueService.updateReportQueueTotal("linkMarginDetail_filepath", 1);
            ReportQueueAjax reportDb = new ReportQueueAjax();
            reportDb.setDateCreate(new Date());
            reportDb.setDelFlag("N");
            reportDb.setReportKey("linkMarginDetail_filepath");
            reportDb.setUserId(UserInfoHolder.getUserId());
            reportDb.setUserIdCreate(UserInfoHolder.getUserId());
            int id = pmlsReportQueueService.addReportQueueAjax(reportDb);

            String ctxPath = request.getSession().getServletContext().getRealPath("/");
            reqMap.put("ctxPath", ctxPath);
            linkMarginDetailService.downLoadExcelAjax(reqMap, ReportConstant.LINKMARGINDETAIL_CODE, ReportConstant.LINKMARGINDETAIL_NAME, id);

            ResultData resultData = pmlsReportQueueService.selectReportQueueTotalTopOne("linkMarginDetail_filepath");
            LinkedHashMap<String, Object> linkedHashMap = (LinkedHashMap<String, Object>) resultData.getReturnData();
            if (resultData.getReturnData() != null) {
                queueSize = Integer.valueOf(linkedHashMap.get("positiveSize") + "") - Integer.valueOf(linkedHashMap.get("minusSize") + "") - threadPoolTaskExecutorPmlsLinkMarginDetail.getCorePoolSize();
            }
            if (queueSize <= 0) {
                queueSize = 0;
            }
        } catch (Exception e) {
            logger.error("pmlsLinkMarginDetail", "PmlsLinkMarginDetailController", "export", "input param: reqMap=" + reqMap.toString(), -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }
        map.put("message", "您前面还有" + queueSize + "人排队处理；正在排队处理请稍后(排队过程中可离开当前页面;下次进入联动资金成本(诚意金&保证金)页面会自动下载)");
        return getOperateJSONView(map);
    }

}
