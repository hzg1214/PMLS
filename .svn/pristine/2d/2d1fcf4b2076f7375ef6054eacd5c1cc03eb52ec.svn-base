package cn.com.eju.pmls.commission.service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;

import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;


import cn.com.eju.pmls.commission.dto.CommissionResultDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
* Service层
*/
@Service("pmlsSceneCommissionService")
public class PmlsSceneCommissionService extends BaseService{

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    /**
     * 原方法：SceneLinkCommissionService.getLinkCommissionList
     * */
    public ResultData<?> getLinkCommissionList(Map<String,Object> reqMap,PageInfo pageInfo) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "sceneLinkCommission" + "/getLinkCommissionList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;
    }
    /**
     * 原方法：SceneLinkCommissionService.insertLnkImport
     * */
    public ResultData insertLnkImport(List<CommissionResultDto> commissionResultList) throws Exception {
        CommissionResultDto dto = new CommissionResultDto();
        dto.setCommissionResultDtoList(commissionResultList);
        String url = SystemParam.getWebConfigValue("RestServer") + "sceneLinkCommission" + "/imputAdd";
        ResultData reback = post(url, dto);
        return  reback;
    }
}
