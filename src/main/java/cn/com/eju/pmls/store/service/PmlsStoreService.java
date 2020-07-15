package cn.com.eju.pmls.store.service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.store.StoreDto;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("pmlsStoreService")
public class PmlsStoreService extends BaseService {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    /**
     * @param reqMap
     * @param pageInfo
     * @return
     * @throws Exception
     * @Title: queryList
     * @Description: 门店列表
     */
    public ResultData<?> queryList(Map<String, Object> reqMap, PageInfo pageInfo)
            throws Exception {
        List<?> contentlist = new ArrayList<>();
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "store" + "/q/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }

    /**
     * 获取门店基本信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ResultData<?> getbriefById(int id) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "store" + "/brief/{id}/{userId}";
        ResultData<?> backResult = get(url, id, UserInfoHolder.getUserId());
        return backResult;
    }

    public ResultData<?> getStoreLogList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "store/findStoreLogList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }

    public ResultData<?> getStoreLog(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "store/findStoreLogById/{param}";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }
    
    
    /**
     * 查询拓展人员列表[门店维护人]
     */
    public ResultData<?> queryStoreUser(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        //调用 接口
    	String url = SystemParam.getWebConfigValue("RestServer") + "businessManagerController" + "/getMaintainerList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }
    
    /**
     * desc:门店修改
     * 2020年3月31日
     */
    public ResultData<?> getById(int id)throws Exception{
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "store" + "/{id}/{userId}";
        ResultData<?> backResult = get(url, id, UserInfoHolder.getUserId());
        return backResult;
    }
    
    
	/**
	 * 分配维护人时 check选择的维护人是否是当前维护人
	 * 
	 * @param storeId,maitainer
	 * @return
	 * @throws Exception
	 */
	public boolean checkMtner(Map<String, Object> reqMap) throws Exception {
		String url = SystemParam.getWebConfigValue("RestServer") + "store" + "/checkmtner/{param}";
		
		ResultData<?> reback = get(url,reqMap);
		Boolean banFlag = (Boolean) reback.getReturnData();
		return banFlag;
	}
	
	
    /**
     * 更新维护人到门店表
     * @param reqMap
     * @return
     * @throws Exception
     */
    public void updateMtcToStore(StoreDto storeDto) throws Exception{
    	//调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "store" + "/uMtcToStore";
    	put(url, storeDto);
    }
    
    
}
