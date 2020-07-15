package cn.com.eju.deal.common.service;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.common.SHBigDistrictDto;


/**   
* 上海事业部区域信息Service
* @author 张文辉
* @date 2016年7月11日 下午2:36:31
*/
@Service("sHBigDistrictService")
public class SHBigDistrictService extends BaseService<SHBigDistrictDto>
{
    //private final static String REST_SERVICE = SystemCfg.getString("sHBigDistrictRestServer");
    
    /** 
    * @Title: getBigDistrictByGroupId 
    * @Description: 根据groupId查询上海事业部区域信息
    * @param groupId
    * @return
    * @throws Exception     
    */
    public ResultData<?> getBigDistrictBySelectedPostId(Integer selectedPostId)
        throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "sHBigDistrict" + "/selectedPostId/{selectedPostId}";
        ResultData<?> reback = get(url, selectedPostId);
        return reback;
    }
}
