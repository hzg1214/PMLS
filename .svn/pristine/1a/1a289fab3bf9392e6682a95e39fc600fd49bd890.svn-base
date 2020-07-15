package cn.com.eju.pmls.storeMaintenance.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;

/**
 * desc:门店维护-service
 * @author :zhenggang.Huang
 * @date   :2020年7月10日
 */
@Service("pmlsStoreMaintenanceService")
public class PmlsStoreMaintenanceService extends BaseService {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    /**
     * desc:列表
     * 2020年7月10日
     */
    public ResultData<?> queryList(Map<String, Object> reqMap, PageInfo pageInfo)
            throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "pmlsStoreMaintenance" + "/queryList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }
    
    /**
     * desc:批量变更维护人
     * 2020年7月8日
     */
    public ResultData<?> batchChangeMaintenance(Map<String, Object> reqMap) throws Exception{
        ResultData<?> backResult =new ResultData<>();
        String url = SystemParam.getWebConfigValue("RestServer") + "pmlsStoreMaintenance" + "/batchChangeMaintenance";
        backResult = post(url, reqMap);
        return backResult;
    }
}
