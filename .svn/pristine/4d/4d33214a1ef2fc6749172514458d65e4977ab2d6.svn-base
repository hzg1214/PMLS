package cn.com.eju.deal.store.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.store.StoreStopCancel;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("storeStopCancelService")
public class StoreStopCancelService extends BaseService<StoreStopCancel> {

    public ResultData<?> storeStopCancelAdd(StoreStopCancel storeStopCancel) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "storeStopCancel/storeStopCancelAdd";
        ResultData<?> backResult = post(url, storeStopCancel);
        return backResult;
    }

    public ResultData<?> queryList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception{

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "storeStopCancel/list/{param}";

        return get(url, reqMap, pageInfo);

    }

    public ResultData<?> getStopCancelById(Integer stopId) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "storeStopCancel/{id}";

        return get(url,stopId);

    }

    public ResultData<?> rejectStopCancel(StoreStopCancel storeStopCancel) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "storeStopCancel/reject";
        return post(url,storeStopCancel);
    }

    public ResultData<?> auditPass(StoreStopCancel storeStopCancel) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "storeStopCancel/pass";
        return post(url,storeStopCancel);
    }
}
