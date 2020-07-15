package cn.com.eju.pmls.frontPanel.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;

@Service("frontPanelService")
public class FrontPanelService extends BaseService {

    /**
     * 获取 合作确认函列表
     * @param reqMap
     * @param pageInfo
     * @return
     * @throws Exception
     */
    public ResultData<?> getFrontPanelData(Map<String,Object> reqMap) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "frontPanelController" + "/query/{param}";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }

}
