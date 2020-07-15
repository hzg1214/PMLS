package cn.com.eju.deal.store.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.Map;

/** 
*  保证金收款service
*/
@Service("storeReceiveService")
public class StoreReceiveService extends BaseService{
    /**
     * 查询维护人列表
     */
    public ResultData getStoreReceiveList(Map<String, Object> reqMap, PageInfo pageInfo)throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "storeReceive" + "/getList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;

    }
    /**
     * 获取保证金收款基本信息
     * @param id
     * @return
     * @throws Exception
     */
    public ResultData<?> getbriefById(int id) throws Exception
    {
        //调用 接口/{userId}
        String url = SystemParam.getWebConfigValue("RestServer") + "storeReceive" + "/brief/{id}";
        ResultData<?> backResult = get(url, id);
        return backResult;
    }
    
    /** 
     * @Title: update 
     * @Description: 更新保证金信息
     * @throws Exception     
     */
     public void update(Map<String, Object> reqMap) throws Exception{
         String url = SystemParam.getWebConfigValue("RestServer") + "storeReceive" + "";
         
         reqMap.put("userIdUpt",UserInfoHolder.getUserId());
         String submitOaStatus = reqMap.get("submitOaStatus").toString();
         if("".equals(submitOaStatus) || submitOaStatus== null){
        	 reqMap.put("delFlag","1");
         }else{
        	 reqMap.put("submitOaStatus",21201);
         }
         put(url, reqMap);
     }

    public ResultData<?> reverse(Map<String, Object> map) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "storeReceive" + "/reverse/{param}";
        ResultData<?> backResult = get(url, map);
        return backResult;
    }
}
