package cn.com.eju.deal.houseLinkage.linkConversionRate.controller;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.houseLinkage.estate.service.EstateService;
import cn.com.eju.deal.houseLinkage.linkConversionRate.service.LinkConversionRateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by haidan on 2019/8/28.
 */
@Controller
@RequestMapping(value = "linkConversionRate")
public class LinkConversionRateController extends BaseReportController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private LinkConversionRateService linkConversionRateService;

    @Resource(name = "estateService")
    private EstateService estateService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String linkConversionRateList(ModelMap mop) {
        String cityNo = UserInfoHolder.get().getCityNo();
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("cityNo", cityNo);
        try {
            ResultData<?> resultData = estateService.getEstateNmList(reqMap);
            List<?> estateList = (List<?>) resultData.getReturnData();
            mop.put("estateList", estateList);
        } catch (Exception e) {
            logger.error("linkConversionRate", "LinkConversionRateController", "linkConversionRateList", cityNo, UserInfoHolder.get().getUserId(), "", "初始化项目名称", e);
        }

        return "houseLinkage/linkConversionRate/linkConversionRateList";
    }

    @RequestMapping(value = "queryList", method = RequestMethod.GET)
    public String queryList(HttpServletRequest request, ModelMap mop) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        PageInfo pageInfo = getPageInfo(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        changeParamToList(reqMap);
        changeParam(reqMap);

        //页面数据
        List<?> contentlist = null;
        try {
            //处理日期
            reqMap = this.handleDate(reqMap);
            //获取页面显示数据
            ResultData<?> reback = linkConversionRateService.queryList(reqMap, pageInfo);

            contentlist = (List<?>) reback.getReturnData();
        } catch (Exception e) {
            logger.error("linkConversionRate", "LinkConversionRateController", "queryList", reqMap.toString(), null, "", "查询--list", e);
        }
        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        return "houseLinkage/linkConversionRate/linkConversionRateListCtx";
    }

    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        changeParamToList(reqMap);
        changeParam(reqMap);

        try {
            //处理日期
            reqMap = this.handleDate(reqMap);
            reqMap.put("optFlag", 1);
            //获取页面显示数据
            ResultData<?> reback = linkConversionRateService.queryList(reqMap, null);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            downLoadExcel(request, response, contentlist, reqMap, ReportConstant.LINKCONVERSIONRATE_CODE, ReportConstant.LINKCONVERSIONRATE_NAME);

        } catch (Exception e) {
            logger.error("linkConversionRate", "LinkConversionRateController", "export", reqMap.toString(), null, "", "导出Excel失败", e);
        }
    }

    private Map<String, Object> handleDate(Map<String, Object> param) {
        Map<String, Object> reqMap = param;
        String dateStart = "";
        String dateEnd = "";
        String serachKey = (String) reqMap.get("serachKey");
        String yearly = (String) reqMap.get("yearly");
        if ("y".equals(serachKey)) {
            dateStart = yearly + "-01-01";
            dateEnd = yearly + "-12-31";
        } else if ("q".equals(serachKey)) {
            String quarter = (String) reqMap.get("quarter");
            String[] qArr = quarter.split(";");
            Arrays.sort(qArr);
            String qNewStr = "";
            for (int i = 0; i < qArr.length; i++) {
                qNewStr = qNewStr + qArr[i] + ",";
            }
            if (StringUtil.isNotEmpty(qNewStr)) {
                qNewStr = qNewStr.substring(0, qNewStr.length() - 1);
                quarter = qNewStr;
            }
            switch (quarter) {
                case "1":
                    dateStart = yearly + "-01-01";
                    dateEnd = yearly + "-03-31";
                    break;
                case "2":
                    dateStart = yearly + "-04-01";
                    dateEnd = yearly + "-06-30";
                    break;
                case "3":
                    dateStart = yearly + "-07-01";
                    dateEnd = yearly + "-09-30";
                    break;
                case "4":
                    dateStart = yearly + "-10-01";
                    dateEnd = yearly + "-12-31";
                    break;
                case "1,2":
                    dateStart = yearly + "-01-01";
                    dateEnd = yearly + "-06-30";
                    break;
                case "1,2,3":
                    dateStart = yearly + "-01-01";
                    dateEnd = yearly + "-09-30";
                    break;
                case "1,2,3,4":
                    dateStart = yearly + "-01-01";
                    dateEnd = yearly + "-12-31";
                    break;
                case "2,3":
                    dateStart = yearly + "-04-01";
                    dateEnd = yearly + "-09-30";
                    break;
                case "2,3,4":
                    dateStart = yearly + "-04-01";
                    dateEnd = yearly + "-12-31";
                    break;
                case "3,4":
                    dateStart = yearly + "-07-01";
                    dateEnd = yearly + "-12-31";
                    break;
            }
        }
        reqMap.remove("quarter");
        reqMap.put("dateStart", dateStart);
        reqMap.put("dateEnd", dateEnd);
        return reqMap;
    }
}
