package cn.com.eju.deal.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.common.WXPushInfoDto;
import cn.com.eju.deal.dto.storeAudit.StoreNewDto;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by xu on 2017/4/19.
 */
@Service("storeAuditService")
public class StoreAuditService extends BaseService {
    public ResultData<?> getStoreList (Map<String, Object> reqMap, PageInfo pageInfo)
            throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "storeAuditController" + "/getStoreList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }
    public ResultData getStoreById(StoreNewDto dto) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "storeAuditController" + "/getStoreById";
        ResultData reback = post(url, dto);
        return reback;
    }
    public ResultData updateStoreAuditStatus(StoreNewDto dto) throws Exception {
        String url2 = SystemParam.getWebConfigValue("RestServer") + "sweepStreetsController" + "/updateStoreAuditStatus";
        ResultData reback = post(url2, dto);
        return reback;
    }
    public ResultData addPushInfo(WXPushInfoDto dto) throws Exception {
        String url2 = SystemParam.getWebConfigValue("RestServer") + "maintainStoreController" + "/addPushInfo";
        ResultData reback = post(url2, dto);
        return reback;
    }
}
