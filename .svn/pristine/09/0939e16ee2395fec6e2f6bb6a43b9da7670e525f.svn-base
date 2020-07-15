package cn.com.eju.deal.common.service;


import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.common.ProvinceDto;

/** 
* @ClassName: ProvinceService 
* @Description: 省份Service
* @author 陆海丹 
* @date 2016年3月28日 下午2:22:19 
*/
@Service("provinceService")
public class ProvinceService extends BaseService<ProvinceDto>
{
    //private final static String REST_SERVICE = SystemCfg.getString("cityCascadeRestServer");
    
    /** 
    * @Title: queryProvinceList 
    * @Description: 获取省份列表
    * @return
    * @throws Exception     
    */
    public ResultData<?> queryProvinceList()
        throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "citycascade" + "/province";
        ResultData<?> reback = get(url);
        return reback;
    }
}
