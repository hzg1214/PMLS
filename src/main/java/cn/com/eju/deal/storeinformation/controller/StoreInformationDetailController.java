package cn.com.eju.deal.storeinformation.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.Report.service.CommonReportService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.storeinformation.service.StoreInformationDetailService;

/**
 * desc: 门店信息明细
 * @author :zhenggang.Huang
 * @date   :2018年12月28日
 */
@Controller
@RequestMapping(value = "storeInformationDetail")
public class StoreInformationDetailController extends BaseReportController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private StoreInformationDetailService storeInformationDetailService;
    
    @Resource(name = "commonReportService")
    private CommonReportService commonReportService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String storeInformationDetailList(HttpServletRequest request, ModelMap mop) {
        return "storeinformation/storeInformationDetailList";
    }

    /**
     * 查询--list
     */
    @RequestMapping(value = "queryInformationDetailList", method = RequestMethod.GET)
    public String queryInformationDetailList(HttpServletRequest request, ModelMap mop) {

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        PageInfo pageInfo = getPageInfo(request);
        reqMap.put("userId", UserInfoHolder.getUserId());

        changeParamToList(reqMap);
        changeParam(reqMap);
        //页面数据
        List<?> contentlist = null;

        try {
            //获取页面显示数据
            ResultData<?> reback = storeInformationDetailService.queryInformationDetailList(reqMap, pageInfo);

            contentlist = (List<?>) reback.getReturnData();
        } catch (Exception e) {
            logger.error("storeInformationDetail", "StoreInformationDetailController", "queryStoreDetailList", "", null, "", "查询--list", e);
        }

        //存放到mop中
        mop.addAttribute("contentlist", contentlist);

        return "storeinformation/storeInformationDetailListCtx";
    }

    /**
     * 导出EXCEL
     */
    @RequestMapping(value = "exportStoreInformationDetailList", method = RequestMethod.GET)
    public void exportStoreInformationDetailList(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());

        changeParamToList(reqMap);
        reqMap = changeParam(reqMap);
        try {

            ResultData<?> reback = storeInformationDetailService.queryInformationDetailList(reqMap, null);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            downLoadExcel(request, response, contentlist, reqMap, ReportConstant.STOREINFORMATIONDETAIL_CODE, ReportConstant.STOREINFORMATIONDETAIL_NAME);

        } catch (Exception e) {
            logger.error("storeInformation", "StoreInformationDetailController", "exportStoreInformationDetailList", "input param: reqMap=" + reqMap.toString(), -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }

    }
}
