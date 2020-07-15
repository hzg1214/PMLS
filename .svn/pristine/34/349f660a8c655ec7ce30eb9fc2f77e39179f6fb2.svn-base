package cn.com.eju.deal.scene.commission.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.scene.CommissionResultDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author kunyin
 * @date 2019/04/09
 */
@Service("lnkYjNyService")
public class LnkYjNyService extends BaseService {


    public ResultData<?> getLnkYjNyList(Map<String,Object> reqMap,PageInfo pageInfo) throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "lnkYjNy" + "/getLnkYjNyList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;
    }

    public ResultData nyImport(List<Map<String,Object>> nyList) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "lnkYjNy" + "/nyImport";
        ResultData reback = post(url, nyList);
        return  reback;
    }
}
