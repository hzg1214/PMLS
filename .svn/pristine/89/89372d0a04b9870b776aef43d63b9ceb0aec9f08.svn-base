package cn.com.eju.deal.performanceSum.service;


import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.performanceSum.PerformanceSumDto;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by tanlang on 2017-05-23.
 */
@Service("performanceSumService")
public class PerformanceSumService extends BaseService<PerformanceSumDto> {

    public ResultData<?> queryList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") +"performanceSum" + "/queryList/{param}";

        ResultData<?> resultData = get(url, reqMap, null);

        return resultData;
    }

    public ResultData<?> queryCityList(Map<String, Object> reqMap) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") +"performanceSum" + "/queryCityList/{param}";

        ResultData<?> resultData = get(url, reqMap);

        return resultData;
    }

    public ResultData<?> queryCenterList(Map<String, Object> reqMap) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") +"performanceSum" + "/queryCenterList/{param}";

        ResultData<?> resultData = get(url, reqMap);

        return resultData;
    }
}
