package cn.com.eju.deal.contract.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.contract.AchievementCancelDto;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 
* 门店业绩撤销Service
* @author wushuang
* @date 2016年10月12日 上午10:57:23
 */
@Service("achievementCancelService")
public class AchievementCancelService extends BaseService<AchievementCancelDto>
{
    /**
     * 
    * 根据合同Id 查询合同下的门店
    * @param contractId
    * @return
     * @throws Exception 
     */
    public ResultData<?> getAchievementCancelInfo(Integer contractId) throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "achievement" + "/cancel/storelist/{contractId}";
        ResultData<?> result = get(url, contractId);
        return result;
    }
    
    /**
     * 
    * 获取门店业绩撤销记录
    * @param contractId
    * @return
     * @throws Exception 
     */
    public ResultData<?> getAchievementCancelRecord(Integer contractId) throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "achievement" + "/cancel/cancellist/{contractId}";
        ResultData<?> result = get(url, contractId);
        return result;
    }
    
    /**
     * 
    * 根据合同ID和门店撤销编号获取撤销详细信息
    * @param reqMap
    * @return
     * @throws Exception 
     */
    public ResultData<?> getInfoToView(Map<String, Object> reqMap) throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "achievement" + "/toView/cancelinfo/{param}";
        ResultData<?> result = get(url, reqMap);
        return result;
    }
    
    /**
     * 
    * 获取门店撤销申请撤销所需的信息【OA专用】
    * @param reqMap
    * @return
     * @throws Exception 
     */
    public ResultData<?> getOAAuditInfo(Map<String, Object> reqMap) throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "achievement" + "/oa/oaauditnfo/{param}";
        ResultData<?> result = get(url, reqMap);
        return result;
    }

    /**
     * 
    * 插入一条撤销记录 更新门店撤消状态
    * @param reqMap
    * @return
     * @throws Exception 
     */
    public ResultData<?> createNewRecord(Map<String, Object> reqMap) throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "achievement" + "/create/{param}";
        ResultData<?> result = get(url, reqMap);
        return result;
    }

    /**
     * 
    * 门店业绩撤销变更更新数据
    * @param reqMap
    * @return
     * @throws Exception 
     */
    public ResultData<?> updateCancelRecord(Map<String, Object> reqMap) throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "achievement" + "/update/{param}";
        ResultData<?> result = get(url, reqMap);
        return result;
    }

//    /**
//     * 
//    * 根据合同Id查询最新合同
//    * @param contractId
//    * @return
//     * @throws Exception 
//     */
//    public ResultData<?> getNewestContract(Integer contractId) throws Exception
//    {
//        String url = SystemParam.getWebConfigValue("RestServer") + "achievement" + "/getnewest/{contractId}";
//        ResultData<?> result = get(url, contractId);
//        return result;
//    }

    /** 
     * 根据flowID查询合同变更中的门店
     * @param flowId 流程ID
     * @param auditRst 返回结果
     * @param userId 操作人ID
     * @return 合同门店关系列表
     * @throws Exception
     */
     public ResultData<?> updateCtrctRelateInfo(String achievementCancelNo,String flowId,Integer auditRst, Integer userId) throws Exception {
         // 调用 接口
         String url = SystemParam.getWebConfigValue("RestServer") + "achievement" + "/flowId/auditRst/{achievementCancelNo}/{flowId}/{auditRst}/{userId}";
         ResultData<?> reback = get(url, achievementCancelNo,flowId, auditRst, userId);
         return reback;
     }

     /**
      * 
     * 业绩撤销,检查门店签约新合同情况
     * @param reqMap
     * @return
     * @throws Exception 
      */
    public ResultData<?> checkStoreSignContract(Map<String, Object> reqMap) throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "achievement" + "/check/sign/{param}";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }
    
    /**
     * 查询门店装修记录
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData<?> getStoreDecorationList(Map<String, Object> reqMap) throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "achievement" + "/getStoreDecorationList/{param}";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }


    public ResultData<?> operateAuditCt(String achievementCancelNo)  throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "achievement" + "/operateAuditCt/{achievementCancelNo}";
        ResultData<?> result = get(url, achievementCancelNo);
        return result;
    }
}
