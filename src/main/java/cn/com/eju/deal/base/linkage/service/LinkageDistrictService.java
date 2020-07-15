package cn.com.eju.deal.base.linkage.service;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.dto.linkage.DistrictDto;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;

/**   
* Service层
* @author (li_xiaodong)
* @date 2016年2月2日 下午9:30:27
*/
@Service("linkageDistrictService")
public class LinkageDistrictService extends BaseService<DistrictDto>
{
    
    //private final static String REST_SERVICE = SystemCfg.getString("linkagesRestServer");
    
    /** 
    * 查询
    * @param id
    * @return
    * @throws Exception
    */
    public ResultData<?> getDistrictListByCityNo(String cityNo)
        throws Exception
    {
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "linkages" + "/district/{cityNo}";
        
        ResultData<?> backResult = get(url, cityNo);
        
        return backResult;
    }
    
   
    
}
