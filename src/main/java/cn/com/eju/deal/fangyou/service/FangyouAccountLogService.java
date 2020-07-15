package cn.com.eju.deal.fangyou.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;


@Service("fangyouAccountLogService")
public class FangyouAccountLogService extends BaseService{
	/**
     * 根据门店id查询其房友账号变更日志
     * @return
     * @throws Exception
     */
    public ResultData getFangyouAccountList(Integer storeId)throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "fangyouAccount" + "/getFangyouAccountList/{storeId}";
        ResultData<?> reback = get(url, storeId);
        return reback;
    }
    /**
     * 编辑房友账号
     */
    public void changeFyAcount(Map<String, Object> reqMap)
    		throws Exception{ 
    	String url = SystemParam.getWebConfigValue("RestServer") + "fangyouAccount/changeFyAcount";
    	post(url, reqMap);
    }  
	
}
