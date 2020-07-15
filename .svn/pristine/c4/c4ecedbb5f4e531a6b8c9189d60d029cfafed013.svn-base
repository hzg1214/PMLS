package cn.com.eju.deal.houseLinkage.linkAchieveChange.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 联动业绩调整
 * Created by hzy on 2017/10/23.
 */
@Service("achieveChangeLogService")
public class AchieveChangeLogService extends BaseService {

    /**
     * 查询联动业绩调整日志列表
     * @param relateId
     * @return
     * @throws Exception
     */
    public ResultData getLogList(Integer relateId)throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "achieveChangeLog" + "/getList/{relateId}";

        ResultData<?> reback = get(url, relateId);

        return reback;

    }

}
