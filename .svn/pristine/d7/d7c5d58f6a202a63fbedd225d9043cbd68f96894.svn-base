package cn.com.eju.deal.gpContract.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;

import java.util.Map;

import cn.com.eju.deal.dto.gpContract.GpContractChangeDto;
import org.springframework.stereotype.Service;


@Service("gpContractChangeService")
public class GpContractChangeService extends BaseService {
	
	 /**
     * 根据公盘合同ID查询门店信息 
     * @throws Exception
     */
    public ResultData<?> queryStoreInfoOfGpContract(Integer id,Integer gpContractId,String type) throws Exception {
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "gpContractChange" + "/queryStoreInfoOfGpContract/{id}/{gpContractId}/{type}";
        ResultData<?> reback = get(url, id,gpContractId,type);
        return reback;
    }
    /**
     * 保存或跟新公盘合同终止申请
     * @param reqMap
     * @throws Exception
     */
    public ResultData<?> saveGpContractChange(Map<String, Object> reqMap) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "gpContractChange" + "/saveGpContractChange";
        ResultData<?> reback = post(url,reqMap);
        return reback;
    }
    /**
     * 查询列表
     */
    public ResultData getGpContractStopList(Map<String, Object> reqMap, PageInfo pageInfo)throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "gpContractChange" + "/getGpContractStopList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;

    }
	/**
     * 根据id查询公盘合同终止详情
     * @throws Exception
     */
    public ResultData<?> getGpContractStopInfoById(Integer id) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "gpContractChange" + "/getGpContractStopInfoById/{id}";
        ResultData<?> backResult = get(url, id);
        return backResult;
    }
    /**
     * 公盘提交OA审核
     * @param reqMap
     */
    public ResultData gpContractStopSubmitOa(Map<String, Object> reqMap) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "gpContractChange" + "/gpContractStopSubmitOa";
        ResultData resultData = post(url, reqMap);
        return resultData;
    }
    /**
     * 作废
     * @param reqMap
     * @throws Exception
     */
	public void updateStatus(Map<String, Object> reqMap) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "gpContractChange" + "/updateStatus";
        put(url, reqMap);
    }

    public void operateChangeCt(Integer id) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "gpContractChange" + "/operateChangeCt/{id}";
        get(url, id);
    }
}
