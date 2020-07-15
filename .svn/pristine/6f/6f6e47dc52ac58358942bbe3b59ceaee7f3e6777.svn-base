package cn.com.eju.pmls.commission.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ny
 */
@Service("pmlsLnkYjNyService")
public class PmlsLnkYjNyService extends BaseService {

    /**
     * 原方法：LnkYjNyService同名方法
     * */
    public ResultData<?> getLnkYjNyList(Map<String,Object> reqMap,PageInfo pageInfo) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "lnkYjNy" + "/getLnkYjNyList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;
    }

    /**
     * 原方法：LnkYjNyService同名方法
     * */
    public ResultData nyImport(List<Map<String,Object>> nyList) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "lnkYjNy" + "/nyImport";
        ResultData reback = post(url, nyList);
        return  reback;
    }
}
