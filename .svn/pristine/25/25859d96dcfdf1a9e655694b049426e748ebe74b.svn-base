package cn.com.eju.pmls.otherReport.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("pmlsQtSuccessSaleService")
public class PmlsQtSuccessSaleService extends BaseService{
    /**
     * 获取成销记录加载至退成销页面
     * @param id
     * @return
     */
    public ResultData<?> getDealRecord(Integer id) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "qtSuccessSale" + "/getDealRecord/{id}";
        ResultData<?> reback = get(url, id);
        return reback;
    }

    public ResultData<?> dealBack(Map<String, Object> reqMap) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "qtSuccessSale" + "/dealBack";
        ResultData<?> backResult = post(url,reqMap);
        return backResult;
    }

    public ResultData<?> successSale(Map<String, Object> reqMap) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "qtSuccessSale" + "/successSale";
        ResultData<?> backResult = post(url,reqMap);
        return backResult;
    }
}
