package cn.com.eju.deal.common.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.common.AreaDto;

/** 
* @ClassName: AreaService 
* @Description: 板块Service
* @author 陆海丹 
* @date 2016年3月28日 下午2:37:18 
*/
@Service("areaService")
public class AreaService extends BaseService<AreaDto>
{
    //private final static String REST_SERVICE = SystemCfg.getString("cityCascadeRestServer");
    
    /** 
    * @Title: queryArealist 
    * @Description: 根据区域编号获取板块列表
    * @param reqMap--districtNo
    * @return
    * @throws Exception     
    */
    public ResultData<?> queryArealist(Map<String, Object> reqMap)
        throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "citycascade" + "/area/{param}";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }
}
