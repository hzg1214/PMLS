package cn.com.eju.deal.company.service;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.company.CompanyDto;

/**   
* Service层
* @author (li_xiaodong)
* @date 2016年2月2日 下午9:30:27
*/
@Service("companyService")
public class CompanyService extends BaseService<CompanyDto>
{
    
    //private final static String SystemParam.getWebConfigValue("RestServer") + "companys" = SystemCfg.getString("companysRestServer");
    //    private String SystemParam.getWebConfigValue("RestServer") + "companys" = SystemParam.getWebConfigValue("RestServer") + "companys";
    
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
        String url = SystemParam.getWebConfigValue("RestServer") + "companys" + "/{id}";
        
        ResultData<?> backResult = get(url, id);
        
        return backResult;
    }

    /**
     * 查询
     * @param id
     * @return
     * @throws Exception
     */
    public ResultData<?> getBriefById(int id)
            throws Exception
    {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "companys" + "/brief/{id}";

        ResultData<?> backResult = get(url, id);

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
        String url = SystemParam.getWebConfigValue("RestServer") + "companys" + "/q/{param}";
        
        ResultData<?> reback = get(url, reqMap, pageInfo);
        
        return reback;
        
    }
    
    /** 
    * 查询-list--供合同选取公司
    * @param reqMap
    * @return
    * @throws Exception
    */
    public ResultData<?> indexOwn(Map<String, Object> reqMap, PageInfo pageInfo)
        throws Exception
    {
        
        //调用 接口
        String url =SystemParam.getWebConfigValue("RestServer")+ "companys" + "/q/own/{param}";
        
        ResultData<?> reback = get(url, reqMap, pageInfo);
        
        return reback;
        
    }
    
    /** 
    * 创建
    * @param studentDto
    * @return
    * @throws Exception
    */
    public ResultData<?> create(CompanyDto companyDto)
        throws Exception
    {
        
        String url = SystemParam.getWebConfigValue("RestServer") + "companys" + "";
        
        ResultData<?> backResult = post(url, companyDto);
        
        return backResult;
    }
    
    /** 
    * 更新
    * @param studentDto
    * @return
    * @throws Exception
    */
    public void update(CompanyDto companyDto)
        throws Exception
    {
        
        String url = SystemParam.getWebConfigValue("RestServer") + "companys" + "";
        
        put(url, companyDto);
        
    }
    
    /** 
    * 更新
    * @param studentDto
    * @return
    * @throws Exception
    */
    public void updateCompany(CompanyDto companyDto)
        throws Exception
    {
        
        String url = SystemParam.getWebConfigValue("RestServer") + "companys" + "/update";
        
        ResultData<?> backResult = post(url, companyDto);
        
        //return backResult;

        
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
        
        String url = SystemParam.getWebConfigValue("RestServer") + "companys" + "/{id}";
        
        delete(url, id);
        
    }
    
    /**
     * 根据选择岗查询组织架构orgId
    * @param selectPostId
    * @return
    * @throws Exception
     */
    public ResultData<?> queryOrgIdByPostId(Integer selectPostId)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "companys" + "/postId/{selectPostId}";
        ResultData<?> backResult = get(url, selectPostId);
        return backResult;
    }
    
    /** 
     * @Title: queryLoglistByCompanyId 
     * @Description: 通过公司ID查询修改日志
     * @param reqMap
     * @param pageInfo
     * @return
     * @throws Exception     
     */
    public ResultData<?>  queryLoglistByCompanyId(Map<String, Object> reqMap, PageInfo pageInfo)
        throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "companys" + "/log/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;
    }
    
    /*
     * @Title: checkBusinessLicenseNo 
     * @Description: 公司营业执照重复验证
     * @param reqMap
     * @param pageInfo
     * @return
     * @throws Exception     
     */
    public ResultData<?>  checkBusinessLicenseNo(String businessLicenseNo)
        throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "companys" + "/check/{businessLicenseNo}";
        ResultData<?> reback = get(url, businessLicenseNo);

        return reback;
    }
    
    /** 
    * 查询
    * @param id
    * @return
    * @throws Exception
    */
    public ResultData<?> getByNo(String companyNo)
        throws Exception
    {
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "companys" + "/byNo/{companyNo}";
        
        ResultData<?> backResult = get(url, companyNo);
        
        return backResult;
    }
    /** 
     * 查询
     * @param companyName
     * @return
     * @throws Exception
     */
     public ResultData<?> queryCompanyName(String companyName)
         throws Exception
     {
         
         //调用 接口
         String url = SystemParam.getWebConfigValue("RestServer") + "companys" + "/queryCompanyName/{companyName}";
         
         ResultData<?> backResult = get(url, companyName);
         
         return backResult;
     }
}
