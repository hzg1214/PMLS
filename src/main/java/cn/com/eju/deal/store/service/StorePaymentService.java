package cn.com.eju.deal.store.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.houseLinkage.estate.EstatePhotosDto;

/** 
*  保证金退款service
*/
@Service("storePaymentService")
public class StorePaymentService extends BaseService{
    /**
     * 查询维护人列表
     */
    public ResultData getStorePaymentList(Map<String, Object> reqMap, PageInfo pageInfo)throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "storePayment" + "/getList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;

    }
    /**
     * 获取保证金退款基本信息
     * @param id
     * @return
     * @throws Exception
     */
    public ResultData<?> getbriefById(int id) throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "storePayment" + "/brief/{id}";
        ResultData<?> backResult = get(url, id);
        return backResult;
    }
    /** 
     * @Title: update 
     * @Description: 删除保证金退款
     * @throws Exception     
     */
     public void update(Map<String, Object> reqMap) throws Exception{
         String url = SystemParam.getWebConfigValue("RestServer") + "storePayment" + "";
         
         reqMap.put("userIdUpt",UserInfoHolder.getUserId());
         String submitOaStatus = reqMap.get("submitOaStatus").toString();
         if("".equals(submitOaStatus) || submitOaStatus== null){
        	 reqMap.put("delFlag","1");
         }else{
        	 reqMap.put("submitOaStatus",21203);
         }
         put(url, reqMap);
     }
     
     /**
      * 查询要新增的保证金合同列表
      */
     public ResultData<?> getPaymentContractList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
     	//调用 接口
     	String url = SystemParam.getWebConfigValue("RestServer") + "storePayment" + "/getPaymentContractList/{param}";
     	ResultData<?> reback = get(url, reqMap, pageInfo);
     	return reback;
     }
     /**
      * 根据合同id获取信息
      * @param id
      * @return
      * @throws Exception
      */
     public ResultData<?> getContractInfoById(int id) throws Exception{
     	String url = SystemParam.getWebConfigValue("RestServer") + "storePayment" + "/getContractInfoById/{id}";
     	ResultData<?> backResult = get(url, id);
     	return backResult;
     }
     /**
      * 根据合同城市编码获取生成退款编号
      * @param cityNo
      * @return
      * @throws Exception
      */
     public ResultData<?> getBasCitySettingByCityNo(String cityNo) throws Exception {
    	 String url = SystemParam.getWebConfigValue("RestServer") + "storePayment" + "/getBasCitySettingByCityNo/{cityNo}";
    	 ResultData<?> backResult = get(url, cityNo);
    	 return backResult;
     }
     
     /**
      * 创建
      * @param reqMap
      * @return
      * @throws Exception
      */
     public ResultData<?> create(Map<String, Object> reqMap,EstatePhotosDto estatePhotosDto)
    	        throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "storePayment/create";
        ResultData<?> backResult = post(url, reqMap);
        return backResult;
    }

}
