package cn.com.eju.deal.company.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.company.CompanyLogDto;

/**   
* Service层
* @author (li_xiaodong)
* @date 2016年2月2日 下午9:30:27
*/
@Service("companyLogService")
public class CompanyLogService extends BaseService<CompanyLogDto>
{
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
    
    /** 
    * 添加修改日志
    * @param CreateCompanyLog
    * @return
    * @throws Exception
    */
    public void createCompanyLog(CompanyLogDto companyLogDto)
        throws Exception
    {
        
        String url = SystemParam.getWebConfigValue("RestServer") + "companys" + "/addLog";
        
        put(url, companyLogDto);

    }
    
    /** 
     * @Title: getById 
     * @Description: 查询修改日志详细
     * @param reqMap
     * @param pageInfo
     * @return
     * @throws Exception     
     */
    public ResultData<?>  getById(Integer logId)
        throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "companys" + "/logDetail/{logId}";
        ResultData<?> reback = get(url, logId);

        return reback;
    }
}
