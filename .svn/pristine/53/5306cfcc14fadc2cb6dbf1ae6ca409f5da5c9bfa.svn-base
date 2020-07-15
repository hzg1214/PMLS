package cn.com.eju.deal.dataAuthority.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.dataAuthority.DataAuthorityDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("dataAuthorityService")
public class DataAuthorityService extends BaseService{
    public ResultData<?> getDataAuthorityList(Map<String, Object> reqMap, PageInfo pageInfo)
            throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "dataAuthority" + "/dataAuthorityList/{param}/";
        ResultData<?> reback = get(url, reqMap,pageInfo);
        return reback;
    }
    public ResultData<?> getQyInfo(Map<String, Object> reqMap)
            throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "dataAuthority" + "/getQyInfo/{param}/";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }

    public ResultData saveData(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "dataAuthority" + "/saveData";

        ResultData<?> reback = post(url,reqMap);
        return reback;
    }

    public ResultData deleteData(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "dataAuthority" + "/deleteData";

        ResultData<?> reback = post(url,reqMap);
        return reback;
    }

}