package cn.com.eju.deal.companyInfoDetail.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Service层
 */
@Service("companyInfoDetailService")
public class CompanyInfoDetailService extends BaseService {

    /**
     * 查询-list
     * @param reqMap
     * @throws Exception
     */
    public ResultData<?> queryCompanyInfoDetailList(Map<String, Object> reqMap, PageInfo pageInfo)
            throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "companyInfoDetail" + "/queryCompanyInfoDetailList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;

    }
}
