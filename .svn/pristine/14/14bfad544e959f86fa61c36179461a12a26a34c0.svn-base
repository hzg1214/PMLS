package cn.com.eju.deal.keFuWj.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by haidan on 2019/9/24.
 */
@Service("keFuWjEvaluationService")
public class KeFuWjEvaluationService extends BaseService {
    public ResultData<?> getWjEvaluationList(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "keFuWjEvaluation" + "/getWjEvaluationList/{param}";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }
}
