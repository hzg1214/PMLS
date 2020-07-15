package cn.com.eju.pmls.skStatement.service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("skStatementService")
public class SkStatementService extends BaseService {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    // 查询
    public ResultData<?> queryList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "skStatementController" + "/queryList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }

    // 取得收入基本信息
    public ResultData<?> getSkStatementById(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "skStatementController" + "/getSkStatementById/{param}";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }
    public ResultData<?> allocateCheck(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "skStatementController" + "/allocateCheck/{param}";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }


}
