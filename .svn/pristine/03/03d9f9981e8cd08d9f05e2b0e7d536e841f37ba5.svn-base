package cn.com.eju.deal.base.linkage.service;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.dto.linkage.CityDto;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;

/**   
* Service层
* @author (li_xiaodong)
* @date 2016年2月2日 下午9:30:27
*/
@Service("linkageCityService")
public class LinkageCityService extends BaseService<CityDto>
{
    
    //private final static String REST_SERVICE = SystemCfg.getString("linkagesRestServer");
    
    /** 
    * 获取城市list
    * @param id
    * @return
    * @throws Exception
    */
    public ResultData<?> getCityList()
        throws Exception
    {
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "linkages" + "/city";
        
        ResultData<?> backResult = get(url);
        
        return backResult;
    }
    
    /** 
    * @Title: getDistrictList 
    * @Description: 根据城市编号获取区域列表
    * @param cityNo 城市编号
    * @return
    * @throws Exception     
    */
    public ResultData<?> getDistrictList(String cityNo)
        throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "linkages" + "/district/" + cityNo;
        ResultData<?> backResult = get(url);
        return backResult;
    }
    
    
    /** 
    * @Title: getAreaList 
    * @Description: 根据区域编号获取板块列表
    * @param districtNo 区域编号
    * @return
    * @throws Exception     
    */
    public ResultData<?> getAreaList(String districtNo)
        throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "linkages" + "/area/" + districtNo;
        ResultData<?> backResult = get(url);
        return backResult;
    }


    /**
     * @Title: getDistrictList
     * @Description: 根据城市编号获取品牌列表
     * @param cityNo 城市编号
     * @return
     * @throws Exception
     */
    public ResultData<?> getBrandList(String cityNo)
            throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "linkages" + "/getBrandList/" + cityNo;
        ResultData<?> backResult = get(url);
        return backResult;
    }
    
    //2017/07/03 Add cning Start
    /** 
     * @Title: getGroupList 
     * @Description: 根据城市编号获取所属中心
     * @param cityNo 城市编号
     * @param typeId 查询组
     * @param typeId2 查询中心
     * @return
     * @throws Exception     
     */
    public ResultData<?> getGroupList(String cityNo, Integer typeId, Integer typeId2)
    		 throws Exception
    {
    	 //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "linkages" + "/group/" + cityNo + "/" + typeId + "/" + typeId2;
        ResultData<?> backResult = get(url, cityNo, typeId, typeId2);
        return backResult;
    }
    //2017/07/03 Add cning End
}
