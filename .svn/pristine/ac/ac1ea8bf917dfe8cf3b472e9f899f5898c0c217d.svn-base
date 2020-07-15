package cn.com.eju.deal.performanceQuery.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.performanceQuery.PerformanceQueryDto;

@Service("performanceQueryService")
public class PerformanceQueryService extends BaseService<PerformanceQueryDto> {


    public ResultData<?> queryPerformanceList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "performanceQuery" + "/queryPerformanceList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;
    }

}
