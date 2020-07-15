package cn.com.eju.deal.common.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.common.DistrictDto;

/** 
* @ClassName: DistrictService 
* @Description: 区域Service
* @author 陆海丹 
* @date 2016年3月28日 下午2:35:00 
*/
@Service("districtService")
public class DistrictService extends BaseService<DistrictDto>
{
    //private final static String REST_SERVICE = SystemCfg.getString("cityCascadeRestServer");
    
    /** 
    * @Title: queryDistrictlist 
    * @Description: 根据城市编号获取区域列表
    * @param reqMap --cityNo
    * @return
    * @throws Exception     
    */
    public ResultData<?> queryDistrictlist(Map<String, Object> reqMap)
        throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "citycascade" + "/district/{param}";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }
}
