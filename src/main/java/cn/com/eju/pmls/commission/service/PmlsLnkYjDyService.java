package cn.com.eju.pmls.commission.service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**   
* Service层
*/
@Service("pmlsLnkYjDyService")
public class PmlsLnkYjDyService extends BaseService
{

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    /**
     * 原方法：LnkYjDyService同名方法
     * */
    public ResultData<?> getLnkYjDyList(Map<String,Object> reqMap,PageInfo pageInfo) throws Exception
    {
    	 String url = SystemParam.getWebConfigValue("RestServer") + "lnkYjDy" + "/getLnkYjDyList/{param}";
    	 
    	  ResultData<?> reback = get(url, reqMap, pageInfo);
          
          return reback;
    }

    /**
     * 原方法：LnkYjDyService同名方法
     * */
    public ResultData dyImport(List<Map<String, Object>> mapList) throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "lnkYjDy" + "/dyImport";
        ResultData reback = post(url, mapList);
        return  reback;
    }

}
