package cn.com.eju.deal.keFuWj.service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by eju on 2019/7/12.
 */

@Service("keFuWjAnalyseService")
public class KeFuWjAnalyseService extends BaseService {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    public ResultData queryWjNumber(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "keFuWjAnalyse" + "/queryWjNumber/{param}";
        ResultData<?> backResult = get(url, reqMap);
        return backResult;
    }

    public ResultData<?> getWjAnalyseList(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "keFuWjAnalyse" + "/getWjAnalyseList/{param}";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }

    public ResultData<?> queryWjFlListByCode(String wjCode) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "keFuWjAnalyse" + "/queryWjFlListByCode/{wjCode}";
        ResultData<?> backResult = get(url, wjCode);
        return backResult;
    }

    public ResultData<?> queryWjTMListByCode(String wjCode) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "keFuWjAnalyse" + "/queryWjTMListByCode/{wjCode}";
        ResultData<?> backResult = get(url, wjCode);
        return backResult;
    }
}
