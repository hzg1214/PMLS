package cn.com.eju.pmls.borrowMoney.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.borrowMoney.dto.BorrowMoneyDto;
import cn.com.eju.pmls.borrowMoney.dto.HkPlanDto;
import cn.com.eju.pmls.channelBusiness.dto.BusinessManagerDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("borrowMoneyService")
public class BorrowMoneyService extends BaseService {
    //获取借佣列表
    public ResultData<?> getBorrowMoneyList(Map<String,Object> reqMap, PageInfo pageInfo) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "borrowMoneyController" + "/getBorrowMoneyList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }
    //获取借佣进度列表
    public ResultData<?> getBorrowMoneyProgressList(Map<String,Object> reqMap, PageInfo pageInfo) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "borrowMoneyController" + "/getBorrowMoneyProgressList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }
    //获取借佣明细列表
    public ResultData<?> getBorrowMoneyDetailList(Map<String,Object> reqMap, PageInfo pageInfo) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "borrowMoneyController" + "/getBorrowMoneyDetailList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }
    //获取借佣详情信息
    public ResultData<BusinessManagerDto> getBorrowMoneyInfo(BorrowMoneyDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "borrowMoneyController" + "/getBorrowMoneyInfo";
        ResultData<BusinessManagerDto> reback = post(url, dto);
        return reback;
    }

    //获取还款计划列表
    public ResultData<?> getHkPlanList(Map<String,Object> reqMap, PageInfo pageInfo) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "borrowMoneyController" + "/getHkPlanList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }
    //获取还款利息列表
    public ResultData<?> getHkPlanInterestList(Map<String,Object> reqMap, PageInfo pageInfo) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "borrowMoneyController" + "/getHkPlanInterestList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }
    //获取还款计划详情
    public ResultData<HkPlanDto> getHkPlanInfo(HkPlanDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "borrowMoneyController" + "/getHkPlanInfo";
        ResultData<HkPlanDto> reback = post(url, dto);
        return reback;
    }

    //还款操作
    public ResultData updateHkPlan(HkPlanDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "borrowMoneyController" + "/updateHkPlan";
        ResultData reback = post(url, dto);
        return reback;
    }

}
