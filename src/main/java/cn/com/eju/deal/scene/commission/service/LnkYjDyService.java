package cn.com.eju.deal.scene.commission.service;

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
* @author (luhaidan)
* @date 2019/04/03
*/
@Service("lnkYjDyService")
public class LnkYjDyService extends BaseService
{

    private final LogHelper logger = LogHelper.getLogger(this.getClass());


    public ResultData<?> getLnkYjDyList(Map<String,Object> reqMap,PageInfo pageInfo) throws Exception
    {
    	 String url = SystemParam.getWebConfigValue("RestServer") + "lnkYjDy" + "/getLnkYjDyList/{param}";
    	 
    	  ResultData<?> reback = get(url, reqMap, pageInfo);
          
          return reback;
    }

    /**
    * 导入新增
    * @return
    * @throws Exception
    */
    public ResultData dyImport(List<Map<String, Object>> mapList) throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "lnkYjDy" + "/dyImport";
        ResultData reback = post(url, mapList);
        return  reback;
    }

}
