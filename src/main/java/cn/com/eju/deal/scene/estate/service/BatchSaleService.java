package cn.com.eju.deal.scene.estate.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("batchSaleService")
public class BatchSaleService extends BaseService {

    /**
     * 批量成销列表
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData<?> getBatchSaleList(Map<String,Object> reqMap) throws Exception{
        String url=SystemParam.getWebConfigValue("RestServer")+"batchSaleController"+"/getBatchSaleDetailList";
        ResultData<?> resultData=post(url,reqMap);
        return resultData;
    }

    /**
     * 批量成销总套数
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData<?> countBatchSaleDetailAll(Map<String,Object> reqMap) throws Exception{
        String url=SystemParam.getWebConfigValue("RestServer")+"batchSaleController"+"/countBatchSaleDetail";
        ResultData<?> resultData=post(url,reqMap);
        return resultData;
    }

    /**
     * 添加批量成销
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData<?> addBatchSaleDetail(Map<String,Object> reqMap) throws Exception{
        String url=SystemParam.getWebConfigValue("RestServer")+"batchSaleController"+"/addBatchSaleDetail";
        ResultData<?> resultData=post(url,reqMap);
        return resultData;
    }

    /**
     * 删除批量成销子表信息
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData<?> deleteBatchSaleDetailById(Map<String,Object> reqMap) throws  Exception{
        String url=SystemParam.getWebConfigValue("RestServer")+"batchSaleController"+"/deleteBatchSaleDetailById";
        ResultData<?> resultData=post(url,reqMap);
        return resultData;
    }


    public ResultData<?> submitBatchSaleAll(Map<String,Object> repMap) throws Exception{
        String url=SystemParam.getWebConfigValue("RestServer")+"batchSaleController"+"/submitBatchSaleAll";
        ResultData<?> resultData=post(url,repMap);
        return resultData;
    }

    public ResultData<?> updateBatchSaleDetailAll(Map<String,Object> repMap) throws Exception{
        String url =SystemParam.getWebConfigValue("RestServer")+"batchSaleController"+"/updateBatchSaleDetailAll";
        ResultData<?> resultData=post(url,repMap);
        return resultData;
    }

    public ResultData<?> checkAccountProject(Map<String, Object> reqMap) throws Exception {
        String url =SystemParam.getWebConfigValue("RestServer")+"batchSaleController"+"/checkAccountProject";
        ResultData<?> resultData=post(url,reqMap);
        return resultData;
    }
}
