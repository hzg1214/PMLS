package cn.com.eju.pmls.skStatement.service;


import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("skAllocateService")
public class SkAllocateService extends BaseService {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    public ResultData<?> getAllocateListForDeal(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "skAllocate" + "/getAllocateListForDeal/{param}";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }

    public ResultData<?> getAllocateListForBuilding(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "skAllocate" + "/getAllocateListForBuilding/{param}";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }

    public ResultData<?> submitAllocate(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "skAllocate/submitAllocate";
        ResultData<?> reback = post(url, reqMap);
        return reback;
    }

    public ResultData<?> getSkAllocateDtlListById(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "skAllocate" + "/getSkAllocateDtlListById/{param}";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }

    public ResultData<?> cancelAll(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "skAllocate/cancelAll";
        ResultData<?> reback = post(url, reqMap);
        return reback;
    }

    public ResultData<?> cancelDtl(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "skAllocate/cancelDtl";
        ResultData<?> reback = post(url, reqMap);
        return reback;
    }


    public ResultData<?> recordList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "skAllocate" + "/recordList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }

    public ResultData<?> logList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "skAllocate" + "/logList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }

    public ResultData<?> querySkAllocateDtlList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "skAllocate" + "/querySkAllocateDtlList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }

}

