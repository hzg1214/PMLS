package cn.com.eju.deal.storeRelocation.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;

import java.util.Map;

import org.springframework.stereotype.Service;


@Service
public class StoreRelocationService extends BaseService {

    public ResultData<?> getContractAndStoreInfo(Integer storeId, Integer contractId) throws Exception{

        String url = SystemParam.getWebConfigValue("RestServer") + "storeRelocation" + "/getContractAndStoreInfo/{storeId}/{contractId}";
        ResultData<?> reback = get(url,storeId,contractId);
        return reback;
    }

    public ResultData<?> getStoreRelocationById(Integer id) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "storeRelocation" + "/getStoreRelocationById/{id}";
        ResultData<?> reback = get(url,id);
        return reback;
    }
    /**
     * 门店迁址提交OA审核
     * @param reqMap
     */
    public ResultData storeRelocationSubmitOa(Map<String, Object> reqMap) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "storeRelocation" + "/storeRelocationSubmitOa";
        ResultData resultData = post(url, reqMap);
        return resultData;
    }
    
    /**
     * check该门店是否可以迁址
     * @param id
     * @return
     * @throws Exception
     */
    public ResultData<?> checkDecorationStatus(Integer id) throws Exception{
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "storeRelocation" + "/checkDecorationStatus/{id}";
        ResultData<?> backResult = get(url, id);
        return backResult;
    }
    /**
     * check该门店是否可以迁址
     * @param id
     * @return
     * @throws Exception
     */
    public ResultData<?> checkStoreAddress(Map<String, Object> reqMap) throws Exception{
    	//调用 接口
    	String url = SystemParam.getWebConfigValue("RestServer") + "storeRelocation" + "/checkStoreAddress/{param}";
    	ResultData<?> backResult = get(url, reqMap);
    	return backResult;
    }
    /**
     * 保存门店迁址
     * @throws Exception
     */
    public ResultData<?> saveStoreRelocation(Map<String, Object> reqMap) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "storeRelocation" + "/saveStoreRelocation";
        ResultData<?> reback = post(url,reqMap);
        return reback;
    }
}
