package cn.com.eju.deal.NoSigned.controller;

import cn.com.eju.deal.NoSigned.service.NoSignReportService;
import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.Report.controller.GroupConfig;
import cn.com.eju.deal.Report.service.ExpendReportService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.service.CityService;
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
 * @author QJP
 * @date 2017年6月22日
 */
@Controller
@RequestMapping(value = "noSignedStore")
public class NoSignReportController extends BaseReportController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private NoSignReportService noSignReportService;

    @Resource
    private ExpendReportService expendReportService;

    @Resource
    private CityService cityService;

    /**
     * 初始化
     *
     * @param request
     * @param mop
     * @return NoSignReportController
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap mop) {
        return "NoSigned/noSignDetailList";
    }


    /**
     * 查询未签门店明细--list
     *
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "q", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap mop) {
        // 获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("createUserId", UserInfoHolder.getUserId());
        PageInfo pageInfo = getPageInfo(request);

        changeParamToList(reqMap);
        changeParam(reqMap);

        //获得当前登陆人所有的城市
        try {
            reqMap.put("userId", UserInfoHolder.getUserId());
            //getAllCity(reqMap);
            changeParamToList(reqMap);

        } catch (Exception e) {

            logger.error("NoSignReport", "NoSignReportController", "index", "", UserInfoHolder.getUserId(), "", "所有城市查询失败", e);
        }

        //获取页面显示数据
        List<?> reportlist = new ArrayList<>();
        try {
            ResultData<?> reback = noSignReportService.queryNoSignDetailList(reqMap, pageInfo);

            //页面数据
            reportlist = (List<?>) reback.getReturnData();

        } catch (Exception e) {
            logger.error("NoSignReport", "NoSignReportController", "index", "", UserInfoHolder.getUserId(), "", "已签门店明细查询失败", e);
        }

        //存放到mop中
        mop.addAttribute("reportlist", reportlist);


        return "NoSigned/noSignDetailListCxt";
    }

    /**
     * 导出
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("createUserId", UserInfoHolder.getUserId());

        changeParamToList(reqMap);
        changeParam(reqMap);
        //获得当前登陆人所有的城市
        try {
            reqMap.put("userId", UserInfoHolder.getUserId());
            //getAllCity(reqMap);
            changeParamToList(reqMap);

        } catch (Exception e) {

            logger.error("NoSignReport", "NoSignReportController", "export", "", UserInfoHolder.getUserId(), "", "所有城市查询失败", e);
        }

        try {
            ResultData<?> reback = noSignReportService.queryNoSignDetailList(reqMap, null);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            downLoadExcel(request, response, contentlist, reqMap, ReportConstant.NOSIGNDETAIL_CODE, ReportConstant.NOSIGNDETAIL_NAME);

        } catch (Exception e) {
            logger.error("noSignDetail", "NoSignReportController", "export", "input param: reqMap=" + reqMap.toString(), -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }
    }


    //**********************加载城市************************************
    @RequestMapping(value = "getCityByIsService", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView getCityByIsService(HttpServletRequest request, ModelMap mop) {
        // 获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        Map<?, ?> reback = new HashMap<String, Object>();
        UserInfo userInfo = UserInfoHolder.get();
        reqMap.put("cityNo", userInfo.getCityNo());
        //设置用户id
        reqMap.put("userId", UserInfoHolder.getUserId());
        // 获取页面显示数据
        try {
            reback = expendReportService.getCityByIsService(reqMap);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        for (Entry<?, ?> m : reback.entrySet()) {
            map.put((String) m.getKey(), m.getValue());
        }
        map.put("oldLevelTypeNames", StringUtils.join(GroupConfig.getOldLevelTypeNames(), ","));
        map.put("levelTypeNames", StringUtils.join(GroupConfig.getLevelTypeNames(), ","));
        return getOperateJSONView(map);
    }

}
