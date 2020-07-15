package cn.com.eju.pmls.commission.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.base.support.UserInfoHolder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("pmlsCashBillService")
public class PmlsCashBillService extends BaseService {

    /**
     * 查询列表
     */
    public ResultData getCashBillList(Map<String, Object> reqMap, PageInfo pageInfo)throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/getCashBillList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }

    /**
     * 获取请款单详情信息
     * @return
     * @throws Exception
     */
    public ResultData getCashBillDeatilById(Map<String,Object> map) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/getCashBillDeatilById/{param}";
        ResultData<?> backResult = get(url, map);
        return backResult;
    }

    /**
     * 提交OA
     * @param reqMap
     * @throws Exception
     */
    public ResultData listToSubmitOa(Map<String, Object> reqMap) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/listToSubmitOa";
        reqMap.put("userCode", UserInfoHolder.get().getUserCode());
        ResultData resultData = post(url, reqMap);
        return resultData;
    }

    public void remove(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/remove";
        put(url, reqMap);
    }
}
