package cn.com.eju.pmls.store.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.store.StoreMaintainerDto;

@Service("pmlsStoreMaintainerService")
public class PmlsStoreMaintainerService extends BaseService {

	/**
	 * 查询门店维护人历史
	 */
	public ResultData<?> getStoreMaintainerHisList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
		// 调用 接口
		String url = SystemParam.getWebConfigValue("RestServer") + "storeMaintainer" + "/qMaintainerHis/{param}";
		ResultData<?> reback = get(url, reqMap, pageInfo);
		return reback;
	}

	public ResultData<?> create(StoreMaintainerDto storeMaintainer) throws Exception
    {
        ResultData<?> backResult =new ResultData<>();
        String url = SystemParam.getWebConfigValue("RestServer") + "storeMaintainer" + "";
        backResult = post(url, storeMaintainer);
        return backResult;
    }
}
