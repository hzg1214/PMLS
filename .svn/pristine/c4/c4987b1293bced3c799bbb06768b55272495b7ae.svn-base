package cn.com.eju.deal.scene.commission.service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.scene.CommissionResultDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by eju on 2019/4/3.
 */
@Service("sceneLinkCommissionService")
public class SceneLinkCommissionService extends BaseService {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());


    public ResultData<?> getLinkCommissionList(Map<String,Object> reqMap,PageInfo pageInfo) throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "sceneLinkCommission" + "/getLinkCommissionList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;
    }

    public ResultData insertLnkImport(List<CommissionResultDto> commissionResultList) throws Exception {
        CommissionResultDto dto = new CommissionResultDto();
        dto.setCommissionResultDtoList(commissionResultList);
        String url = SystemParam.getWebConfigValue("RestServer") + "sceneLinkCommission" + "/imputAdd";
        ResultData reback = post(url, dto);
        return  reback;
    }
}
