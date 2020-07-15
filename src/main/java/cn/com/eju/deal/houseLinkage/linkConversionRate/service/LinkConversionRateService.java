package cn.com.eju.deal.houseLinkage.linkConversionRate.service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by haidan on 2019/8/28.
 */
@Service("linkConversionRateService")
public class LinkConversionRateService extends BaseService{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    public ResultData<?> queryList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception{
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "linkConversionRate" + "/queryList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;
    }
}
