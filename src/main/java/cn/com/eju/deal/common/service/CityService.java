package cn.com.eju.deal.common.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.common.CityDto;

/** 
* @ClassName: CityService 
* @Description: 城市Service
* @author 陆海丹 
* @date 2016年3月28日 下午2:31:13 
*/
@Service("cityService")
public class CityService extends BaseService<CityDto>
{
    //private final static String REST_SERVICE = SystemCfg.getString("cityCascadeRestServer");
    
    /** 
    * @Title: queryCitylist 
    * @Description: 根据省份编号获取城市列表
    * @param reqMap--provinceNo
    * @return
    * @throws Exception     
    */
    public ResultData<?> queryCitylist(Map<String, Object> reqMap)
        throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "citycascade" + "/city/{param}";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }
    
    /**
    * @Title: queryCityListByIsService
    * @Description: 获取已开通城市
    * ResultData<List<CityDto>>
    */
    public ResultData<?> queryCityListByIsService()
        throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "citycascade"+"/queryCityListByIsService";
        ResultData<?> reback = get(url);
        return reback;
    }


    /**
    * @Title: queryCityListByIsLnkService 
    * @Description: 获取联动城市
    * add by wang kanlin 2017/09/20
    * ResultData<List<CityDto>>  
    */
    public ResultData<?> queryCityListByIsLnkService()
        throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "citycascade"+"/queryCityListByIsLnkService";
        ResultData<?> reback = get(url);
        return reback;
    }
    /**
     * @Description:获取项目发布城市变更列表
     */
    public ResultData<?> queryCitySettingsList()
    		throws Exception{
    	//调用 接口
    	String url = SystemParam.getWebConfigValue("RestServer") + "citycascade"+"/queryCitySettingsList";
    	ResultData<?> reback = get(url);
    	return reback;
    }
    
    /**
    * @Title: queryCityListByIsService
    * @Description: 跟进用户id获取城市列表
    * ResultData<List<CityDto>>
    */
    public ResultData<?> queryCityListByUserId(Map<String, Object> reqMap)
        throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "citycascade"+"/queryCityListByUserId/{param}";
        ResultData<?> reback = get(url,reqMap);
        return reback;
    }
    /**
     * @Title: queryCityListByIsService
     * @Description: 跟进用户id获取城市列表
     */
    public ResultData<?> queryCityNameByCityNo(Map<String, Object> reqMap)
    		throws Exception
    {
    	//调用 接口
    	String url = SystemParam.getWebConfigValue("RestServer") + "citycascade"+"/queryCityNameByCityNo/{param}";
    	ResultData<?> reback = get(url,reqMap);
    	return reback;
    }

    public ResultData<?> queryCityListByPlace()
            throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "citycascade"+"/queryCityListByPlace";
        ResultData<?> reback = get(url);
        return reback;
    }

    public ResultData<?> queryCityListByAffiliation(Integer userId) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "citycascade"+"/queryCityListByAffiliation/{userId}";
        ResultData<?> reback = get(url,userId);
        return reback;
    }
}
