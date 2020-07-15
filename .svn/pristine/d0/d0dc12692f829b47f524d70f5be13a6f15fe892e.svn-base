package cn.com.eju.deal.store.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.store.StoreMaintainerDto;

/** 
* @ClassName: StoreMaintainerService 
* @Description: 门店维护人关系service
* @author 张文辉
* @date 2016年6月7日 下午6:15:39
*/
@Service("storeMaintainerService")
public class StoreMaintainerService extends BaseService<StoreMaintainerDto>
{
    //private final static String REST_SERVICE = SystemCfg.getString("storeMaintainerRestServer");
    /** 
    * @Title: create 
    * @Description: 门店维护人关系
    * @param storeMaintainer
    * @return
    * @throws Exception     
    */
    public ResultData<?> create(StoreMaintainerDto storeMaintainer) throws Exception
    {
        ResultData<?> backResult =new ResultData<>();
        String url = SystemParam.getWebConfigValue("RestServer") + "storeMaintainer" + "";
        backResult = post(url, storeMaintainer);
        return backResult;
    }
    
    /**
     * 查询门店维护人历史
     */
     public ResultData<?> getStoreMaintainerHisList (Map<String, Object> reqMap, PageInfo pageInfo) 
         throws Exception
     {
         //调用 接口
         String url = SystemParam.getWebConfigValue("RestServer") + "storeMaintainer" + "/qMaintainerHis/{param}";
         ResultData<?> reback = get(url, reqMap, pageInfo);
         return reback;
     }

     //Add 2017/04/10 cning 门店维护人查询--->
     /** 
      * 查询
      * @param storeId
      * @return
      * @throws Exception
      */
     public ResultData<?> getByStoreId(int storeId) throws Exception
     {
         
         //调用 接口
         String url = SystemParam.getWebConfigValue("RestServer") + "storeMaintainer" + "/g/{storeId}";
         
         ResultData<?> backResult = get(url, storeId);
         
         return backResult;
     }
    //Add 2017/04/10 cning 门店维护人查询<---
     
	/**
	 * 门店是否有维护人
	 * 
	 * @param storeId
	 * @return
	 * @throws Exception
	 */
	public Boolean hasMtner(Integer storeId) throws Exception {
		// 调用 接口
		String url = SystemParam.getWebConfigValue("RestServer") + "storeMaintainer" + "/hasmtn/{storeId}";
		ResultData<?> backResult = get(url, storeId);
		Boolean hasFlag = (Boolean) backResult.getReturnData();
		return hasFlag;
	}

    /**
     * 门店维护人的check
     */
    public ResultData<?> chkMaintainer (Map<String, Object> reqMap)throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "storeMaintainer" + "/chkMaintainer/{param}";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }
}
