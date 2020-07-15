package cn.com.eju.pmls.commission.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.commission.dto.PmlsPerformSwitchWeekDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * switch
 */
@Service("pmlsPerformSwitchWeekService")
public class PmlsPerformSwitchWeekService extends BaseService {

	public ResultData<?> getList(Map<String, Object> reqMap)
			throws Exception {
		// 调用OMS接口
		String url = SystemParam.getWebConfigValue("RestServer") + "pmlsPerformSwitchWeek" + "/getList/{param}";
		ResultData<?> reback = get(url, reqMap);
		return reback;
	}

	public ResultData<?> create(List<PmlsPerformSwitchWeekDto> switchDtos)throws Exception{
		String url = SystemParam.getWebConfigValue("RestServer") + "pmlsPerformSwitchWeek/create";
		ResultData<?> backResult = post(url, switchDtos);
		return backResult;
	}

}
