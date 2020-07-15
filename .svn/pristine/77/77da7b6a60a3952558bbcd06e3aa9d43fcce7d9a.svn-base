package cn.com.eju.pmls.statisticsReport.projectDetail.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by haidan on 2020/6/16.
 */
@Service("pmlsProjectDetailService")
public class PmlsProjectDetailService extends BaseService {
    public ResultData<?> queryList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception{
//调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "pmlsProjectDetail" + "/queryList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;
    }
}
