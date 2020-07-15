package cn.com.eju.deal.storeContribution.controller;


import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.storeContribution.service.StoreContributionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/storeContribution", method = RequestMethod.GET)
public class StoreContributionController extends BaseReportController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "storeContributionService")
    private StoreContributionService storeContributionService;

    /**
     * 查询初始画面
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap mop) {

        try {
            //获取请求参数
            Map<String, Object> map = bindParamToMap(request);

        } catch (Exception e) {
            logger.error("storeContribution", "StoreContributionController", "index", "",
                    UserInfoHolder.getUserId(), "", "查询画面初始化-门店贡献分析表失败！", e);
        }

        return "storeContribution/storeContribution";
    }

    /**
     * 查询列表数据
     */
    @RequestMapping(value = "/q", method = RequestMethod.GET)
    public String query(HttpServletRequest request, ModelMap mop, HttpServletResponse response) {

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        UserInfo userInfo = UserInfoHolder.get();
        if (userInfo != null) {
            reqMap.put("userId", userInfo.getUserId());
        }
        ResultData<?> reback = new ResultData<>();
        int ldFlag = 0, gpFlag = 0, yjFlag = 0;
        try {
            if (reqMap.containsKey("stages")) {
                String stages = reqMap.get("stages").toString();
                if (stages.contains("ldItem")) ldFlag = 1;
                if (stages.contains("gpItem")) gpFlag = 1;
                if (stages.contains("jyItem")) yjFlag = 1;
            }

            reback = storeContributionService.query(reqMap);
        } catch (Exception e) {
            logger.error("storeContribution", "StoreContributionController", "query", "",
                    UserInfoHolder.getUserId(), "", "查询列表数据-门店贡献分析表失败", e);
        }
        //存放到mop中
        mop.addAttribute("content", reback.getReturnData());
        mop.addAttribute("ldFlag", ldFlag);
        mop.addAttribute("gpFlag", gpFlag);
        mop.addAttribute("yjFlag", yjFlag);


        return "storeContribution/storeContributionCtx";
    }


    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void exportList(HttpServletRequest request, HttpServletResponse response) {

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        UserInfo userInfo = UserInfoHolder.get();
        if (userInfo != null) {
            reqMap.put("userId", userInfo.getUserId());
        }
        ResultData<?> reback = new ResultData<>();

        try {
            reback = storeContributionService.query(reqMap);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();


            downLoadExcel(request, response, contentlist, reqMap, ReportConstant.STORECONTRIBUTION_CODE, ReportConstant.STORECONTRIBUTION_NAME);

        } catch (Exception e) {
            logger.error("storeContribution", "StoreContributionController", "index", "",
                    UserInfoHolder.getUserId(), "", "导出excel-门店贡献分析表失败！", e);
        }


    }

}
