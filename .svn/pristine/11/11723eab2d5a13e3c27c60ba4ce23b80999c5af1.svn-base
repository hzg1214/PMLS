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
@Service("pmlsEstatePlanService")
public class PmlsEstatePlanService extends BaseService {

	 /**
     * desc:佣金方案维护-经纪公司列表
     * 2020年2月27日
     */
    public ResultData<?> getCompanyList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
     	//调用 接口
     	String url = SystemParam.getWebConfigValue("RestServer") + "lnkEstateIncomeplan" + "/getCompanyList/{param}";
     	ResultData<?> reback = get(url, reqMap, pageInfo);
     	return reback;
     }

    /**
     * desc:禁用佣金方案
     * 2020年3月2日
     */
    public void toIsEnable(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "lnkEstateIncomeplan" + "/toIsEnable";
        post(url, reqMap);
    }

    /**
     * desc:保存/编辑
     * 2020年3月2日
     */
    public ResultData<?> saveYjPlanInfo(LnkYjPlanDto lnkYjPlanDto) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/saveYjPlanInfo";
        ResultData<?> reback = post(url, lnkYjPlanDto);
        return reback;
    }

    /**
     * desc:佣金方案维护-详细Info
     * 2020年3月3日
     */
    public ResultData<?> getLnkYjPlanDto(Integer planId) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/getLnkYjPlanDto/{planId}";
        ResultData<?> reback = get(url, planId);
        return reback;
    }
    
    
    /**
     * desc:项目-佣金方案列表
     * 2020年2月28日
     */
    public ResultData<?> getYjPlanByProjectNo(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/getYjPlanByProjectNo/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }
    
    /**
     * desc:项目-佣金方案日志列表
     * 2020年2月28日
     */
    public ResultData<?> queryChangeLogList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
    	String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/queryChangeLogList/{param}";
    	ResultData<?> reback = get(url, reqMap, pageInfo);
    	return reback;
    }
    
    /**
     * desc:项目-佣金方案垫佣列表
     * 2020年2月28日
     */
    public ResultData<?> queryMattressControlRuleList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
    	String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/queryMattressControlRuleList/{param}";
    	ResultData<?> reback = get(url, reqMap, pageInfo);
    	return reback;
    }
}
