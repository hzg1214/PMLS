package cn.com.eju.deal.companyInfoDetail.controller;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.companyInfoDetail.service.CompanyInfoDetailService;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.WebUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller层
 * 公司信息明细表
 */
@Controller
@RequestMapping(value = "companyInfoDetail")
public class CompanyInfoDetailController extends BaseReportController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private CompanyInfoDetailService companyInfoDetailService;
    /**
     * 初始化
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String companyInfoDeatilList(ModelMap mop) {
        return "companyInfoDetail/companyInfoDetailList";
    }
    /**
     * 查询--list
     */
    @RequestMapping(value = "queryCompanyInfoDetailList", method = RequestMethod.GET)
    public String queryCompanyInfoDetailList(HttpServletRequest request, ModelMap mop) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        PageInfo pageInfo = getPageInfo(request);
        UserInfo userInfo = UserInfoHolder.get();
        reqMap.put("userId", userInfo.getUserId());
        reqMap.put("userCityNo", userInfo.getCityNo());
        reqMap.put("userCode", userInfo.getUserCode());
        //页面数据
        List<?> contentlist = null;
        try {
            //获取页面显示数据
            ResultData<?> reback = companyInfoDetailService.queryCompanyInfoDetailList(reqMap, pageInfo);
            contentlist = (List<?>) reback.getReturnData();
        } catch (Exception e) {
            logger.error("companyInfoDetail", "CompanyInfoDetailController", "queryCompanyInfoDetailList", "", null, "", "查询--list", e);
        }
        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        return "companyInfoDetail/companyInfoDetailListCtx";
    }

    /**
     * 导出EXCEL
     */
    @RequestMapping(value = "exportCompanyInfoDetailList", method = RequestMethod.GET)
    public void exportStoreDepositDeatil(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        reqMap.put("optFlag", "export");
        try {
            ResultData<?> reback = companyInfoDetailService.queryCompanyInfoDetailList(reqMap, null);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();
            downLoadExcel(request, response, contentlist, reqMap, ReportConstant.COMPANYINFODETAIL_CODE, ReportConstant.COMPANYINFODETAIL_NAME);

        } catch (Exception e) {
            logger.error("companyInfoDetail", "CompanyInfoDetailController", "export", "input param: reqMap=" + reqMap.toString(), -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }

    }
    
}
