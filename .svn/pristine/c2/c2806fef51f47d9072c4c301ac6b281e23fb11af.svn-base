package cn.com.eju.deal.store.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;

import java.util.Map;

import org.springframework.stereotype.Service;


@Service("storeAuthCheckService")
public class StoreAuthCheckService extends BaseService {
	
	/**
     * 查询列表
     */
    public ResultData getStoreAuthCheckList(Map<String, Object> reqMap, PageInfo pageInfo)throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "AuthCheck" + "/getStoreAuthCheckList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;

    }
    /**
     * 根据id授牌验收详情
     * @throws Exception
     */
    public ResultData<?> getStoreAuthCheckInfoById(Integer id) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "AuthCheck" + "/getStoreAuthCheckInfoById/{id}";
        ResultData<?> backResult = get(url, id);
        return backResult;
    }
    
    /**
     * 授牌验收申请审核通过
     * @param reqMap
     * @throws Exception
     */
    public ResultData<?> auditPass(Map<String, Object> reqMap) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "AuthCheck" + "/auditPass";
        ResultData<?> reback = post(url,reqMap);
        return reback;
    }
    
}
