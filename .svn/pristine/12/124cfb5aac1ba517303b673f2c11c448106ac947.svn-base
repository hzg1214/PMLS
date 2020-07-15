package cn.com.eju.deal.store.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;

import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * Created by haidan on 2018/3/23.
 */
@Service("storeDepositService")
public class StoreDepositService extends BaseService{

    /**
     *校验门店是否有审核中的保证金收款或退款记录
     * @param storeNo
     * @return
     * @throws Exception
     */
    public ResultData<Integer> checkStoreDepositLock(String storeNo) throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "storeDeposit" + "/checkStoreDepositLock/{storeNo}";
        ResultData<Integer> backResult = get(url, storeNo);
        return backResult;
    }

    /**
     *校验门店是否有未退款，在途保证金
     * @param storeNo
     * @return
     * @throws Exception
     */
    public ResultData<Map<String,Object>> checkStoreDeposit(Map<String,Object> param) throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "storeDeposit" + "/checkStoreDeposit/{param}";
        ResultData<Map<String,Object>> backResult = get(url, param);
        return backResult;
    }
    
    /**
     * 新门店保证金开关
     * @param cityNo
     * @return
     * @throws Exception
     */
    public ResultData<String> openFlag(String cityNo) throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "storeDeposit" + "/openFlag/{cityNo}";
        ResultData<String> backResult = get(url, cityNo);
        return backResult;
    }
}
