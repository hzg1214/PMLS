package cn.com.eju.pmls.statisticsReport.common.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("pmlsCommonReportService")
public class PmlsCommonReportService extends BaseService {

    public ResultData<?> queryHblRegionList(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "pmlsUserCenterCitySetting" + "/queryHblRegionList";
        ResultData<?> resultData = post(url, reqMap);
        return resultData;
    }

    public ResultData<?> queryHblAreaCityList(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "pmlsUserCenterCitySetting" + "/queryHblAreaCityList";
        ResultData<?> resultData = post(url, reqMap);
        return resultData;
    }

    public ResultData<?> queryHblCityList(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "pmlsUserCenterCitySetting" + "/queryHblCityList";
        ResultData<?> resultData = post(url, reqMap);
        return resultData;
    }

    public ResultData<?> queryHblCenterList(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "pmlsUserCenterCitySetting" + "/queryHblCenterList";
        ResultData<?> resultData = post(url, reqMap);
        return resultData;
    }
}
