package cn.com.eju.deal.keFuWj.controller;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.service.CityService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.keFuWj.service.KeFuWjEvaluationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static cn.com.eju.deal.common.util.BaseUtils.getYearList;

/**
 * Created by haidan on 2019/9/24.
 */
@Controller
@RequestMapping(value = "keFuWjEvaluation")
public class KeFuWjEvaluationController extends BaseReportController {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "cityService")
    CityService cityService;

    @Resource(name = "keFuWjEvaluationService")
    KeFuWjEvaluationService keFuWjEvaluationService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, ModelMap mop) throws Exception {
        //构建ModelAndView实例，并设置跳转地址
        ModelAndView mv = new ModelAndView("keFuWj/keFuWjEvaluation");
        UserInfo userInfo = UserInfoHolder.get();
        List<String> yearList = getYearList(2019);
        mop.put("yearList", yearList);
        try {
            //查询isService=1的城市（所在城市）
            ResultData<?> reback = cityService.queryCityListByPlace();
            List<?> cityList = (List<?>) reback.getReturnData();
            mv.addObject("cityList", cityList);
            //查询归属城市
            ResultData<?> resultData = cityService.queryCityListByAffiliation(userInfo.getUserId());
            List<?> acCitylist = (List<?>) resultData.getReturnData();
            mv.addObject("acCitylist", acCitylist);
        } catch (Exception e) {
            logger.error("list", "KeFuWjEvaluationController", "list", "", null, "", "查询城市", e);
        }
        return mv;
    }

    /**
     * 导出EXCEL
     */
    @RequestMapping(value = "exportWjEvaluation", method = RequestMethod.GET)
    public void exportWjEvaluation(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            ResultData<?> reback = keFuWjEvaluationService.getWjEvaluationList(reqMap);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();
            downLoadExcel(request, response, contentlist, reqMap, ReportConstant.KEFUWJEVALUATION_CODE, ReportConstant.KEFUWJEVALUATION_NAME);
        } catch (Exception e) {
            logger.error("KeFuWjAnalyse", "KeFuWjAnalyseController", "exportWjByCode", "input param: reqMap=" + reqMap.toString(), -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }
    }

}
