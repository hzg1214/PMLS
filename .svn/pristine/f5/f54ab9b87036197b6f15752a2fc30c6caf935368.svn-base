package cn.com.eju.deal.contract.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.contract.ContractInfoDto;

@Service("extOmsService")
public class ExtOmsService extends BaseService<ContractInfoDto>{
	/**
	 * 新增合同流水
	 * @param flowId
	 * @return
	 * @throws Exception
	 */
	public ResultData<?> createPerformNodeRecordByFlowId(String flowId) throws Exception {
		// 调用 接口
		String url = SystemParam.getWebConfigValue("RestServer") + "extOms" + "/{flowId}";
		ResultData<?> backResult = get(url, flowId);
		return backResult;
	}

	/**
	 * 
	    * @Title: updateChgStatusToOmsSplit
	    * @Description: 变更终止通过-更新OMS保证金合同状态
	    * @return
	    * @throws Exception
	 */
    public void updateChgStatusToOmsSplit(Map<String, Object> reqMap) throws Exception
    {
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "extOms" + "/updateChgState/{param}";
        ResultData<?> backResult = get(url, reqMap);
    }
}
