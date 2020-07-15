package cn.com.eju.pmls.statisticsReport.rptQTLinkDetail.service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping(value = "rptQTLinkDetailService")
public class RptQTLinkDetailService extends BaseService
{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());


    public ResultData<?> queryList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "rptQTLinkDetail" + "/queryList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;
    }

    public ResultData<?> export(Map<String, Object> reqMap) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "rptQTLinkDetail" + "/export";

        ResultData<?> reback = post(url, reqMap);

        return reback;
    }


}
