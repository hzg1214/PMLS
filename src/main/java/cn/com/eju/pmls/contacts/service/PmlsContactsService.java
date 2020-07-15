package cn.com.eju.pmls.contacts.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.contacts.ContactsDto;

/**   
* Service层
*/
@Service("pmlsContactsService")
public class PmlsContactsService extends BaseService<ContactsDto>
{
    
    //private final static String REST_SERVICE = SystemCfg.getString("contactsRestServer");
    
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
        String url = SystemParam.getWebConfigValue("RestServer") + "contacts" + "/{id}";
        
        ResultData<?> backResult = get(url, id);
        
        return backResult;
    }
    
    //Add 2017/04/10 cning --->
    /** 
     * 查询
     * @param companyId
     * @return
     * @throws Exception
     */
     public ResultData<?> getByCompanyId(int companyId)
         throws Exception
     {
         
         //调用 接口
         String url = SystemParam.getWebConfigValue("RestServer") + "contacts" + "g/{id}";
         
         ResultData<?> backResult = get(url, companyId);
         
         return backResult;
     }
     //Add 2017/04/10 cning <---
     
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
        String url = SystemParam.getWebConfigValue("RestServer") + "contacts" + "/q/{param}";
        
        ResultData<?> reback = get(url, reqMap, pageInfo);
        
        return reback;
        
    }
    
    /** 
    * 创建
    * @param studentDto
    * @return
    * @throws Exception
    */
    public ResultData<?> create(ContactsDto contactsDto)
        throws Exception
    {
        
        String url = SystemParam.getWebConfigValue("RestServer") + "contacts" + "";
        
        ResultData<?> backResult = post(url, contactsDto);
        
        return backResult;
    }
    
    /** 
    * 更新
    * @param contactsDto
    * @return
    * @throws Exception
    */
    public void update(ContactsDto contactsDto)
        throws Exception
    {
        
        String url = SystemParam.getWebConfigValue("RestServer") + "contacts" + "";
        
        put(url, contactsDto);
        
    }
    
    /** 
    * 删除
    * @param id
    * @return
    * @throws Exception
    */
    public void delete(int id)
        throws Exception
    {
        
        String url = SystemParam.getWebConfigValue("RestServer") + "contacts" + "/{id}";
        
        delete(url, id);
        
    }
    
}
