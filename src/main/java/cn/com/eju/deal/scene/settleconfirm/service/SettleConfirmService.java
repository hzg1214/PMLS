package cn.com.eju.deal.scene.settleconfirm.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.houseLinkage.report.ReportInfoDto;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by yinkun on 2018/5/3.
 */
@Service("settleConfirmService")
public class SettleConfirmService extends BaseService<ReportInfoDto>{
    public ResultData<?> getSettleConfirmList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "settleConfirm" + "/getSettleConfirmList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;
    }

    public ResultData batchUpdateSettleConfirmDate(ReportInfoDto reportInfoDto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "settleConfirm" + "/batchUpdateSettleConfirmDate";

        ResultData<?> reback = post(url,reportInfoDto);

        return reback;
    }
}
