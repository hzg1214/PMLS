package cn.com.eju.pmls.commission.service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by eju on 2019/12/18.
 */
@Service("pmlsLnkYjFyFyService")
public class PmlsLnkYjFyFyService extends BaseService {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    public ResultData<?> queryList(Map<String,Object> reqMap, PageInfo pageInfo) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "pmlsLnkYjFyFy" + "/queryList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;
    }

    public ResultData insertLinkFyFy(List<Map<String, Object>> mapList) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "pmlsLnkYjFyFy" + "/insertLinkFyFy";
        ResultData reback = post(url, mapList);
        return  reback;
    }
}
