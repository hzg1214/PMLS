package cn.com.eju.deal.store.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.store.StoreDecorationDto;

/**
 * 门店装修Service
 * @author  wushuang
 * @date 2016年8月16日 下午6:16:08
 */
@Service("storeDecorationService")
public class StoreDecorationService extends BaseService<StoreDecorationDto>
{
    /**
     * 
        * @Title: getStoreDecoration
        * @Description: 查询门店装修状态
        * @return
        * @throws Exception
     */
    public ResultData<?> getStoreDecoration(Map<String, Object> reqMap)
        throws Exception
    {
         
        String url = SystemParam.getWebConfigValue("RestServer") + "storeDecoration" + "/qDecoration/{param}";
        ResultData<?> backResult = get(url, reqMap);
        return backResult;
        
    }
    
    /**
     * 
        * @Title: addSecondRecord
        * @Description: 新增第二次装修记录
        * @return
        * @throws Exception
     */
    
    public ResultData<?> addSecondRecord(StoreDecorationDto storeDecorationDto)
        throws Exception
    {
        ResultData<?> backResult = new ResultData<>();
        String url = SystemParam.getWebConfigValue("RestServer") + "storeDecoration" + "/cDecoration";

        backResult = post(url, storeDecorationDto);
        
        return backResult;
    }
    
}
