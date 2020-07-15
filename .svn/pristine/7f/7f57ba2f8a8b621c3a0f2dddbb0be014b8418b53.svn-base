package cn.com.eju.deal.storeContribution.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("storeContributionService")
public class StoreContributionService extends BaseService {

    /*
        查询query
    */
    public ResultData<?> query(Map<String, Object> reqMap)
            throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "" + "/q/{param}";

        ResultData<?> reback = get(url, reqMap);

        return reback;

    }


}
