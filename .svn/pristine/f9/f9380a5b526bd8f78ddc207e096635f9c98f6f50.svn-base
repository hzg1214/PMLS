package cn.com.eju.deal.Report.service;

import cn.com.eju.deal.Report.dto.BasScheduleDto;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("storeStopReportService")
public class StoreStopReportService extends BaseService<BasScheduleDto> {

	public ResultData<?> queryStoreStopDetailList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
		//调用 接口
		String url = SystemParam.getWebConfigValue("RestServer") + "expendReport" + "/queryExpandDetailList/{param}";
//		String url = "http://127.0.0.1:8080/PMLSService/" + "storeStopReport" + "/queryStoreStopDetailList/{param}";
		ResultData<?> reback = get(url, reqMap, pageInfo);

		return reback;
	}

	public ResultData<?> exportStoreStopDetailList(Map<String, Object> reqMap) throws Exception {
		//调用 接口
		String url = SystemParam.getWebConfigValue("RestServer") + "storeStopReport" + "/exportStoreStopDetailList/{param}";
//		String url = "http://127.0.0.1:8080/CRMService/" + "storeStopReport" + "/exportStoreStopDetailList/{param}";
		ResultData<?> reback = get(url, reqMap);

		return reback;
	}

}
