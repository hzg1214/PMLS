package cn.com.eju.deal.gpCsMemberContract.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;

import java.util.Map;

import org.springframework.stereotype.Service;


@Service("gpCsMemberContractService")
public class GpCsMemberContractService extends BaseService {
	
	/**
     * 查询列表
     */
    public ResultData getGpCsMemberContractList(Map<String, Object> reqMap, PageInfo pageInfo)throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "gpCsMemberContract" + "/getGpCsMemberContractList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    } 
    /**
     * 根据id查询公盘初始会员合同详情
     * @throws Exception
     */
    public ResultData<?> getGpCsMemberContractById(Integer id) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "gpCsMemberContract" + "/getGpCsMemberContractById/{id}";
        ResultData<?> backResult = get(url, id);
        return backResult;
    }
    /**
     * 保存或跟新公盘初始会员合同
     * @param reqMap
     * @throws Exception
     */
    public ResultData<?> saveGpCsMemberContract(Map<String, Object> reqMap) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "gpCsMemberContract" + "/saveGpCsMemberContract";
        ResultData<?> reback = post(url,reqMap);
        return reback;
    }
    
    /**
     * 提交OA审核
     * @param reqMap
     */
    public ResultData submitGpCsMemberContractOa(Map<String, Object> reqMap) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "gpCsMemberContract" + "/submitGpCsMemberContractOa";
        ResultData resultData = post(url, reqMap);
        return resultData;
    }
    /**
     * 作废
     * @param reqMap
     * @throws Exception
     */
	public void updateStatus(Map<String, Object> reqMap) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "gpCsMemberContract" + "/updateStatus";
        put(url, reqMap);
    }
	public ResultData<?> getGpInfoByCompanyId(Map<String, Object> reqMap)throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "gpCsMemberContract" + "/getGpInfoByCompanyId/{param}";
        ResultData<?> backResult = get(url, reqMap);
        return backResult;
    }

    public void operateChangeCt(Integer id) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "gpCsMemberContract" + "/operateChangeCt/{id}";
        get(url, id);
    }
}
