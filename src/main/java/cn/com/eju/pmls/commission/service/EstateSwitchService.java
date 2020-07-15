package cn.com.eju.pmls.commission.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.commission.dto.PerformSwitchDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * switch
 */
@Service("estateSwitchService")
public class EstateSwitchService extends BaseService {

    public ResultData<?> listCtx(Map<String, Object> reqMap)
            throws Exception {
        // 调用OMS接口
        String url = SystemParam.getWebConfigValue("RestServer") + "performswitch" + "/qMap/{param}";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }

    public ResultData<?> create(List<PerformSwitchDto> switchDtos) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "performswitch";
        ResultData<?> backResult = post(url, switchDtos);
        return backResult;
    }

    /**
     * 查看该月份下城市是否尚有大定待审核的记录
     *
     * @throws Exception
     */
    public ResultData<?> checkRoughToValid(Map<String, Object> reqMap) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "/performswitch" + "/checkRoughToValid/{param}";
        ResultData<?> backResult = get(url, reqMap);
        return backResult;
    }

    /**
     * 检验收款单的入账日期处于关账月份且未拆分完毕，不允许关账！
     */
    public ResultData<?> checkSkAllocate(Map<String, Object> reqMap) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "/performswitch" + "/checkSkAllocate/{param}";
        ResultData<?> backResult = get(url, reqMap);
        return backResult;
    }

}
