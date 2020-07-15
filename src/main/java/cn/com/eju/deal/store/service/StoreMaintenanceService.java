package cn.com.eju.deal.store.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.store.StoreMaintenanceDto;

/** 
*  维护人菜单service
*/
@Service("storeMaintenanceService")
public class StoreMaintenanceService extends BaseService{
    /**
     * 查询维护人列表
     */
    public ResultData getStoreMaintenanceList(Map<String, Object> reqMap, PageInfo pageInfo)throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "storeMaintenance" + "/getList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;

    }

	public ResultData saveMaintenance(Map<String, Object> reqMap) throws Exception {
		String url = SystemParam.getWebConfigValue("RestServer") + "storeMaintenance" + "/saveMaintenance";

        ResultData<?> reback = post(url,reqMap);
		return reback;
	}
}
