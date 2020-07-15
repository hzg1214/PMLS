package cn.com.eju.deal.houseLinkage.lnkAchievementSum.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Service层
 * 联动业绩汇总
 */
@Service("lnkAchievementSumService")
public class LnkAchievementSumService extends BaseService {

    /**
     * 查询-list
     * @param reqMap
     * @throws Exception
     */
    public ResultData<?> queryLnkAchievementSumList(Map<String, Object> reqMap, PageInfo pageInfo)
            throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "lnkAchievementSum" + "/queryLnkAchievementSumList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;

    }
}
