package cn.com.eju.deal.houseLinkage.statistic.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.contract.ContractInfoDto;

/**   
* Service层
* @author (qianwei)
* @date 2016年4月29日 下午9:30:27
*/
@Service("statisticService")
public class StatisticService extends BaseService<ContractInfoDto>
{
    
    //private final static String REST_STATISTIC = SystemCfg.getString("statisticRestServer");
    
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
        String url = SystemParam.getWebConfigValue("RestServer") + "statistic" + "/{id}";
        
        ResultData<?> backResult = get(url, id);
        
        return backResult;
    }
    
    /** 
     * 获取日期类型列表
     * @param id
     * @return
     * @throws Exception
     */
     public ResultData<?> getDateKbnList(String dateTypeKbn)
         throws Exception
     {
         
         //调用 接口
         String url = SystemParam.getWebConfigValue("RestServer") + "statistic" + "/dateTypeKbn/{dateTypeKbn}";
         
         ResultData<?> backResult = get(url, dateTypeKbn);
         
         return backResult;
     }
     
    public ResultData<?> getstatistic(String estateId, String companyId, String customerId)
            throws Exception
    {
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "statistic" + "/{estateId}/{companyId}/{customerId}";
        
        ResultData<?> backResult = get(url, estateId, companyId, customerId);
        
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
        String url = SystemParam.getWebConfigValue("RestServer") + "statistic" + "/q/{param}";
        
        ResultData<?> reback = get(url, reqMap, pageInfo);
        
        return reback;
        
    }
    
    /** 
     * 查询-公司统计list
     * @param reqMap
     * @return
     * @throws Exception
     */
     public ResultData<?> companyList(Map<String, Object> reqMap, PageInfo pageInfo)
         throws Exception
     {
         
         //调用 接口
         String url = SystemParam.getWebConfigValue("RestServer") + "statistic" + "/qCompany/{param}";
         
         ResultData<?> reback = get(url, reqMap, pageInfo);
         
         return reback;
         
     }
     
     /** 
      * 查询-统计明细list
      * @param reqMap
      * @return
      * @throws Exception
      */
      public ResultData<?> statisticDetailList(Map<String, Object> reqMap, PageInfo pageInfo)
          throws Exception
      {
          
          //调用 接口
          String url = SystemParam.getWebConfigValue("RestServer") + "statistic" + "/qStatisticDetail/{param}";
          
          ResultData<?> reback = get(url, reqMap, pageInfo);
          
          return reback;
          
      }
    
    /** 
    * 创建
    * @param contractInfoDto
    * @return
    * @throws Exception
    */
    public ResultData<?> create(ContractInfoDto contractInfoDto)
        throws Exception
    {
        
        String url = SystemParam.getWebConfigValue("RestServer") + "statistic" + "";
        
        ResultData<?> backResult = post(url, contractInfoDto);
        
        return backResult;
    }
    
    /** 
    * 更新
    * @param contractInfoDto
    * @return
    * @throws Exception
    */
    public void update(ContractInfoDto contractInfoDto)
        throws Exception
    {
        
        String url = SystemParam.getWebConfigValue("RestServer") + "statistic" + "";
        
        put(url, contractInfoDto);
        
    }
    
    /** 
    * 删除
    * @param id
    * @param updateId
    * @return
    * @throws Exception
    */
    public void delete(int id, int updateId)
        throws Exception
    {
        
        String url = SystemParam.getWebConfigValue("RestServer") + "statistic" + "/{id}/{updateId}";
        
        delete(url, id, updateId);
        
    }
    
    /** 
     * 审核
     * @param contractInfoDto
     * @return
     * @throws Exception
     */
     public void audit(ContractInfoDto contractInfoDto)
         throws Exception
     {
         
         String url = SystemParam.getWebConfigValue("RestServer") + "statistic" + "/audit";
         
         put(url, contractInfoDto);
         
     }
     /** 
      * 验证处理
      * @param reqMap
      * @return
      * @throws Exception
      */
      public ResultData<?> checkStoreLock(Map<String, Object> reqMap)
          throws Exception
      {
          String url = SystemParam.getWebConfigValue("RestServer") + "statistic" + "/check/{param}";
          ResultData<?> backResult =get(url, reqMap);
          return backResult;
      }

}
