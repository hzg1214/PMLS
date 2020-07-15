package cn.com.eju.deal.oa.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.oa.OaOperatorDto;

/**   
* Service层
* @author (li_xiaodong)
* @date 2016年2月2日 下午9:30:27
*/
@Service("oaOperatorService")
public class OaOperatorService extends BaseService<OaOperatorDto>
{
    
//    private final static String SystemParam.getWebConfigValue("RestServer") + "oa" = SystemCfg.getString("oaRestServer");
    
    /** 
    * 查询
    * @param id
    * @return
    * @throws Exception
    */
    public ResultData<?> getById(int id)
        throws Exception
    {
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "oa" + "/{id}";
        
        ResultData<?> backResult = get(url, id);
        
        return backResult;
    }
    
    /** 
     * 查询
     * @param id
     * @return
     * @throws Exception
     */
    public ResultData<?> getByUserCode(String userCode)
        throws Exception
        {
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "oa" + "/usercode/{userCode}";
        
        ResultData<?> backResult = get(url, userCode);
        
        return backResult;
        }
    
    /** 
    * 查询-list
    * @param reqMap
    * @return
    * @throws Exception
    */
    public ResultData<?> index(Map<String, Object> reqMap, PageInfo pageInfo)
        throws Exception
    {
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "oa" + "/q/{param}";
        
        ResultData<?> reback = get(url, reqMap, pageInfo);
        
        return reback;
        
    }
    
    /** 
    * 创建
    * @param studentDto
    * @return
    * @throws Exception
    */
    public ResultData<?> create(OaOperatorDto oaOperatorDto)
        throws Exception
    {
        
        String url = SystemParam.getWebConfigValue("RestServer") + "oa" + "";
        
        ResultData<?> backResult = post(url, oaOperatorDto);
        
        return backResult;
    }
    
    /** 
    * 更新
    * @param studentDto
    * @return
    * @throws Exception
    */
    public void update(OaOperatorDto oaOperatorDto)
        throws Exception
    {
        
        String url = SystemParam.getWebConfigValue("RestServer") + "oa" + "";
        
        put(url, oaOperatorDto);
        
    }
    
    /** 
    * 删除
    * @param id
    * @param updateId
    * @return
    * @throws Exception
    */
    public void delete(int id)
        throws Exception
    {
        
        String url = SystemParam.getWebConfigValue("RestServer") + "oa" + "/{id}";
        
        delete(url, id);
        
    }
    
    /** 
     * 查询
     * @param id
     * @return
     * @throws Exception
     */
    public ResultData<?> getByDicCode(String dicCode)
        throws Exception
    {
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "commoncodes" + "/code/{dicCode}";
        
        ResultData<?> backResult = get(url, dicCode);
        
        return backResult;
    }
}
