package cn.com.eju.deal.storeinformation.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.houseLinkage.LinkDetail.LinkDetailDto;

/**
 * desc:  门店信息明细  
 * @author :zhenggang.Huang
 * @date   :2019年1月2日
 */
@Service("storeInformationDetailService")
public class StoreInformationDetailService extends BaseService<LinkDetailDto> {

    /**
     * 查询-list
     *
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData<?> queryInformationDetailList(Map<String, Object> reqMap, PageInfo pageInfo)
            throws Exception {

        reqMap.put("optFlag", "search");

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "storeInformationDetail" + "/queryInformationDetailList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;

    }

}
