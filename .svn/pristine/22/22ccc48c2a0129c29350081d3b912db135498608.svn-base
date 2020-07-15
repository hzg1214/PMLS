package cn.com.eju.deal.houseLinkage.storeDeposit.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Service层
 */
@Service("storeDepositDeatilService")
public class StoreDepositDeatilService extends BaseService {

    /**
     * 查询-list
     * @param reqMap
     * @throws Exception
     */
    public ResultData<?> queryStoreDepositDeatilList(Map<String, Object> reqMap, PageInfo pageInfo)
            throws Exception {
        //调用 接口queryStoreDepositDeatilList 
        String url = SystemParam.getWebConfigValue("RestServer") + "storeDepositDeatil" + "/queryStoreDepositDeatilList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;

    }
    /**
     * 查询所有的业绩城市及其核算主体
     * @return
     * @throws Exception
     */
    public ResultData<?> queryCityList() throws Exception{
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer")  + "storeDepositDeatil" + "/queryCityList";

        ResultData<?> reback = get(url);

        return reback;
    }
    /**
     * 根据城市编号查询其核算主体
     * @param cityNo
     * @return
     * @throws Exception
     */
    public ResultData queryAccountProject(String cityNo) throws Exception{
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "storeDepositDeatil" + "/queryAccountProject/{cityNo}";
        
        ResultData backResult = get(url, cityNo);
        
        return backResult;
    }
}
