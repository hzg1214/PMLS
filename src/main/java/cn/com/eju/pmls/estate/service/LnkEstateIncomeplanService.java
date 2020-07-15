package cn.com.eju.pmls.estate.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.estate.dto.LnkYjPlanDto;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by eju on 2019/12/6.
 */
@Service("lnkEstateIncomeplanService")
public class LnkEstateIncomeplanService extends BaseService {

    public void addIncomePlan(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "lnkEstateIncomeplan" + "/addIncomePlan";
        post(url, reqMap);
    }

    public void toProhibit(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "lnkEstateIncomeplan" + "/toProhibit";
        post(url, reqMap);
    }

    public ResultData<?> queryPlanById(Integer id) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "lnkEstateIncomeplan" + "/queryPlanById/" + id;
        ResultData<?> backResult = get(url);
        return backResult;
    }

    public void editIncomePlan(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "lnkEstateIncomeplan" + "/editIncomePlan";
        post(url, reqMap);
    }

    public ResultData<?> queryCXSQAmount(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "lnkEstateIncomeplan" + "/queryCXSQAmount/{param}";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }

    public ResultData<?> queryIncomeplanList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "lnkEstateIncomeplan" + "/queryIncomeplanList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }

}
