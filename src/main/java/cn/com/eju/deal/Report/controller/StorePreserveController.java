package cn.com.eju.deal.Report.controller;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.service.ExpendReportService;
import cn.com.eju.deal.Report.service.StorePreserveService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.WebUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.Map.Entry;

/**
 * Controller层
 *
 * @author xuliangliang
 * @date 2018年10月23日
 */
@Controller
@RequestMapping(value = "storePreserve")
public class StorePreserveController extends BaseReportController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private StorePreserveService storePreserveService;


    /**
     * 初始化
     *
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap mop) {
        return "preserve/storePreserve";
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
    public String index(HttpServletRequest request, ModelMap mop) {
        // 获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        String startDate=reqMap.get("preserveTime").toString();
        reqMap.put("preserveTime",startDate+"-01");
        //获取页面显示数据
        List<?> reportlist = new ArrayList<>();

        changeParam(reqMap);

        try {

            ResultData<?> reback = storePreserveService.queryStorePreserveList(reqMap, pageInfo);
            //页面数据
            reportlist = (List<?>) reback.getReturnData();

        } catch (Exception e) {
            logger.error("index", "StorePreserveController", "index", "", UserInfoHolder.getUserId(), "", "门店拓展明细查询失败", e);
        }

        //存放到mop中
        mop.addAttribute("reportlist", reportlist);

        return "preserve/storePreserveListCtx";
    }


    //Add By QJP 2017/06/16 导出维护门店明细报表 start
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        String startDate=reqMap.get("preserveTime").toString();
        reqMap.put("preserveTime",startDate+"-01");
        changeParam(reqMap);

        try {
            ResultData<?> reback = storePreserveService.exportStorePreserveList(reqMap);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            downLoadExcel(request, response, contentlist, reqMap, ReportConstant.STOREPRESERVE_CODE, ReportConstant.STOREPRESERVE_NAME);

        } catch (Exception e) {
            logger.error("expandDetail", "StorePreserveController", "export", "input param: reqMap=" + reqMap.toString(), -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }
    }

    //**********************加载城市************************************
    @RequestMapping(value = "getCityByIsService", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView getCityByIsService(HttpServletRequest request, ModelMap mop) {
        // 获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        Map<?, ?> reback = new HashMap<String, Object>();

        //设置用户id
        reqMap.put("userId", UserInfoHolder.getUserId());
        // 获取页面显示数据
        try {
            reback = storePreserveService.getUserCenterAuthCityName(reqMap);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        for (Map.Entry<?, ?> m : reback.entrySet()) {
            map.put((String) m.getKey(), m.getValue());
        }
        map.put("oldLevelTypeNames", StringUtils.join(GroupConfig.getOldLevelTypeNames(), ","));
        map.put("levelTypeNames", StringUtils.join(GroupConfig.getLevelTypeNames(), ","));
        return getOperateJSONView(map);
    }

    //选择城市查询事业部
    @RequestMapping(value = "getAllGroupByTypeIdAndCityId", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView getAllGroupByTypeIdAndCityId(HttpServletRequest request, ModelMap mop) {

        // 获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        //设置用户id
        reqMap.put("userId", UserInfoHolder.getUserId());
        Map<?, ?> reback = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();

        // 获取页面显示数据
        try {
            reback = storePreserveService.getAreaGroupName(reqMap);

            if (null != reback.get("list")) {
                List<LinkedHashMap<String, Object>> list = (List<LinkedHashMap<String, Object>>) reback.get("list");

                for (Map.Entry<?, ?> m : reback.entrySet()) {
                    map.put((String) m.getKey(), m.getValue());
                }

                map.put("list", list);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return getOperateJSONView(map);
    }


    //选择事业部查询部门
    @RequestMapping(value = "getAllGroupByOrgIdAndTypeId", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView getAllGroupByOrgIdAndTypeId(HttpServletRequest request, ModelMap mop) {
        Map<?, ?> reback = new HashMap<String, Object>();

        // 获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        Map<String, Object> map = new HashMap<String, Object>();

        // 获取页面显示数据
        try {
            reback = storePreserveService.getCenterGroupName(reqMap);

            if (null != reback.get("list")) {
                List<LinkedHashMap<String, Object>> list = (List<LinkedHashMap<String, Object>>) reback.get("list");

                for (Map.Entry<?, ?> m : reback.entrySet()) {
                    map.put((String) m.getKey(), m.getValue());
                }

                map.put("list", list);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return getOperateJSONView(reback);

    }

    @RequestMapping(value = "storePreserveSummary", method = RequestMethod.GET)
    public String storePreserveSummary(HttpServletRequest request, ModelMap mop) {
        return "preserve/storePreserveSummary";
    }

    @RequestMapping(value = "queryStorePreserveSummary", method = RequestMethod.GET)
    public String queryStorePreserveSummary(HttpServletRequest request, ModelMap mop) {
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        String quarter=reqMap.get("quarterIds").toString();
        //获取页面显示数据
        List<?> reportlist = new ArrayList<>();
        changeParam(reqMap);
        reqMap.put("regionCodeStr",reqMap.get("regionCodeStr").toString().replaceAll(",","','"));
        reqMap.put("areaCityCodeStr",reqMap.get("areaCityCodeStr").toString().replaceAll(",","','"));
        reqMap.put("cityIdStr",reqMap.get("cityIdStr").toString().replaceAll(",","','"));
        reqMap.put("centerIdStr",reqMap.get("centerIdStr").toString().replaceAll(",","','"));
        reqMap.put("quarterIds",reqMap.get("quarterIds").toString().replaceAll(";","','"));
        reqMap.put("shopState",reqMap.get("shopState").toString().replaceAll(";","','"));
        reqMap.put("personal",reqMap.get("personal").toString().replaceAll(";","','"));
        try{
            ResultData<?> reback = storePreserveService.queryStorePreserveSummaryList(reqMap, null);
            reportlist = (List<?>) reback.getReturnData();
        }catch (Exception e){

        }
        mop.addAttribute("reportList", reportlist);
        if (quarter.indexOf("Q1") != -1){
            mop.addAttribute("Q1", "Q1");
        }else{
            mop.addAttribute("Q1", null);
        }
        if (quarter.indexOf("Q2") != -1){
            mop.addAttribute("Q2", "Q2");
        }else{
            mop.addAttribute("Q2", null);
        }
        if (quarter.indexOf("Q3") != -1){
            mop.addAttribute("Q3", "Q3");
        }else{
            mop.addAttribute("Q3", null);
        }
        if (quarter.indexOf("Q4") != -1){
            mop.addAttribute("Q4", "Q4");
        }else{
            mop.addAttribute("Q4", null);
        }
        System.out.println("reporlist:" + reportlist);
        return "preserve/storePreserveSummaryListCtx";
    }

    @RequestMapping(value = "exportSummary", method = RequestMethod.GET)
    public void exportSummary(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        reqMap.put("regionCodes",reqMap.get("regionCodes").toString().replaceAll(";","','"));
        reqMap.put("areaCityCodes",reqMap.get("areaCityCodes").toString().replaceAll(";","','"));
        reqMap.put("cityIds",reqMap.get("cityIds").toString().replaceAll(";","','"));
        reqMap.put("centerIds",reqMap.get("centerIds").toString().replaceAll(";","','"));
        reqMap.put("shopState",reqMap.get("shopState").toString().replaceAll(";","','"));
        reqMap.put("personal",reqMap.get("personal").toString().replaceAll(";","','"));
        reqMap.put("quarterIds",reqMap.get("quarterIds").toString().replaceAll(";","','"));
        try {
            ResultData<?> reback = storePreserveService.exportStorePreserveSummaryList(reqMap);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            downLoadExcel(request, response, contentlist, reqMap, ReportConstant.STOREPRESERVESUMMARY_CODE, ReportConstant.STOREPRESERVESUMMARY_NAME);

        } catch (Exception e) {
            logger.error("exportSummary", "StorePreserveController", "exportSummary", "input param: reqMap=" + reqMap.toString(), -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }
    }
}
