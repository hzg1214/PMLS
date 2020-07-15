package cn.com.eju.deal.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/23.
 */
@Service("achieveChangeService")
public class AchieveChangeService extends BaseService {

    public ResultData<?> queryList (Map<String, Object> reqMap, PageInfo pageInfo)
            throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "achieveChange" + "/queryList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }
}
