package cn.com.eju.deal.keFuWj.controller;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.service.CityService;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.keFuWj.service.KeFuWjAnalyseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by eju on 2019/7/11.
 */
@Controller
@RequestMapping(value = "keFuWjAnalyse")
public class KeFuWjAnalyseController extends BaseReportController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "cityService")
    CityService cityService;

    @Resource(name = "keFuWjAnalyseService")
    KeFuWjAnalyseService keFuWjAnalyseService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, ModelMap mop, HttpServletResponse response) throws Exception{
        //构建ModelAndView实例，并设置跳转地址
        ModelAndView mv = new ModelAndView("keFuWj/keFuWjAnalyse");
        UserInfo userInfo = UserInfoHolder.get();
        String cityNo = userInfo.getCityNo();
        if (StringUtil.isNotEmpty(cityNo)){
            Map<String, Object> map = bindParamToMap(request);
        }
        List<String> yearList = getYearList(2019);
        mop.put("yearList",yearList);
        try {
            //查询isService=1的城市（所在城市）
            ResultData<?> reback = cityService.queryCityListByPlace();
            List<?> placeCitylist = (List<?>) reback.getReturnData();
            mv.addObject("placeCitylist", placeCitylist);
            //查询所属城市
            ResultData<?> resultData = cityService.queryCityListByAffiliation(userInfo.getUserId());
            List<?> affiliationCitylist = (List<?>) resultData.getReturnData();
            mv.addObject("affiliationCitylist", affiliationCitylist);
        } catch (Exception e) {
            logger.error("list", "KeFuWjAnalyseController", "list", "", null, "", "查询城市", e);
        }

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.get().getUserId());
        return mv;

    }

    private List<String> getYearList(Integer startYear) {
        Calendar date = Calendar.getInstance();
        Integer endYear = Integer.valueOf(date.get(Calendar.YEAR));
        List<String> yearList = new ArrayList<>();
        if(endYear-startYear==0){
            yearList.add(String.valueOf(startYear));
        }else{
            for (int i = 0;i<endYear-startYear;i++){
                yearList.add(String.valueOf(startYear+i));
            }
        }
        return yearList;
    }

    @ResponseBody
    @RequestMapping(value = "/queryWjNumber", method = RequestMethod.POST)
    public ReturnView<?, ?> queryWjNumber(HttpServletRequest request) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        //返回map
        ResultData resultData = new ResultData<>();
        reqMap.remove("_method");
        try {
            //查询问卷
            resultData = keFuWjAnalyseService.queryWjNumber(reqMap);
        } catch (Exception e) {
            logger.error("KeFuWjAnaly", "KeFuWjAnalyseController", "queryWjNumber", "", UserInfoHolder.getUserId(), "", "状态变更失败", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "查询失败");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        return getOperateJSONView(rspMap);
    }

    /**
     * @Title: editFyAcountMode
     * @Description: 编辑房友账号弹窗
     */
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView modify(HttpServletRequest request,ModelMap mop){
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData resultData = new ResultData<>();
        reqMap.remove("_method");
        try {
            //查询问卷
            resultData = keFuWjAnalyseService.queryWjNumber(reqMap);
        } catch (Exception e) {
            logger.error("KeFuWjAnaly", "KeFuWjAnalyseController", "queryWjNumber", "", UserInfoHolder.getUserId(), "", "状态变更失败", e);
        }
        mop.put("wjDetail",resultData.getReturnData());
        ModelAndView mv = new ModelAndView( "keFuWj/keFuWjAnalysePopup");
        return mv;
    }

    /*
     * 导出check
     */
    @RequestMapping(value = "exportCheck", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> exportCheck(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> rspMap = new HashMap<String, Object>();
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> reback;
        try {
            reqMap.put("userId", UserInfoHolder.getUserId());
            reback = keFuWjAnalyseService.getWjAnalyseList(reqMap);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();
            if (contentlist == null || contentlist.isEmpty()) {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "没有数据，不能导出Excel！");
                return getOperateJSONView(rspMap);
            }
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导出失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        rspMap.put(Constant.RETURN_MSG_KEY, reback.getReturnMsg());
        return getOperateJSONView(rspMap);
    }

    /**
     * 导出EXCEL
     */
    @RequestMapping(value = "exportWjByCode", method = RequestMethod.GET)
    public void exportWjByCode(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        reqMap.put("optFlag", "export");
        try {
            ResultData<?> reback = keFuWjAnalyseService.getWjAnalyseList(reqMap);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();
            //查询问卷所包含的分类及分类下的题目总和
            ResultData<?> result = keFuWjAnalyseService.queryWjFlListByCode(reqMap.get("wjCode").toString());
            List<LinkedHashMap<String, Object>> list = (List<LinkedHashMap<String, Object>>)result.getReturnData();
            contentlist.get(0).put("wjtmflList",list);
            //根据wjCode查询题目
            ResultData<?> result2 = keFuWjAnalyseService.queryWjTMListByCode(reqMap.get("wjCode").toString());
            List<LinkedHashMap<String, Object>> list2 = (List<LinkedHashMap<String, Object>>)result2.getReturnData();
            contentlist.get(0).put("wjtmList",list2);
            downLoadExcel(request, response, contentlist, reqMap, ReportConstant.KEFUWJANALYSE_CODE, ReportConstant.KEFUWJANALYSE_NAME);
        } catch (Exception e) {
            logger.error("KeFuWjAnalyse", "KeFuWjAnalyseController", "exportWjByCode", "input param: reqMap=" + reqMap.toString(), -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }

    }

}
