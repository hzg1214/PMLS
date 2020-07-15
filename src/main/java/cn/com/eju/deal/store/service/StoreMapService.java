package cn.com.eju.deal.store.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by tanlang on 2017-09-05.
 */
@Service("storeMapService")
public class StoreMapService extends BaseService {

    public ResultData<?> getMapInfo(Map<String, Object> reqMap) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "storeMap" + "/getMapInfo/{param}";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }

    public ResultData<?> getStoreCount(Map<String, Object> reqMap) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "storeMap" + "/getStoreCount/{param}";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }
    public ResultData<?> getCenterPosition(Map<String, Object> reqMap) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "storeMap" + "/getCenterPosition/{param}";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }
}
