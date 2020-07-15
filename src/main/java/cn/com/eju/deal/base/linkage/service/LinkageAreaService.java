package cn.com.eju.deal.base.linkage.service;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.dto.linkage.AreaDto;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;

/**   
* Service层
* @author (li_xiaodong)
* @date 2016年2月2日 下午9:30:27
*/
@Service("linkageAreaService")
public class LinkageAreaService extends BaseService<AreaDto>
{
    
    //private final static String SystemParam.getWebConfigValue("RestServer") + "linkages" = SystemCfg.getString("linkagesRestServer");
    
    /** 
    * 查询
    * @param id
    * @return
    * @throws Exception
    */
    public ResultData<?> getAreaListByDistrictNo(String districtNo)
        throws Exception
    {
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "linkages" + "/area/{districtNo}";
        
        ResultData<?> backResult = get(url, districtNo);
        
        return backResult;
    }
    
   
    
}
