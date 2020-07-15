package cn.com.eju.deal.company.service;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.company.CompanyStoreDto;

/**   
* 公司门店关系 Service层
* @author 张文辉
* @date 2016年7月4日 下午5:47:17
*/
@Service("companyStoreService")
public class CompanyStoreService extends BaseService<CompanyStoreDto>
{
    
    //private final static String REST_SERVICE = SystemCfg.getString("companyStoreRestServer");
//    private final String REST_SERVICE = SystemParam.getWebConfigValue("RestServer") + "companyStore";
    
    /** 
     * 创建comanyStore关系
     * @param comanyStore
     * @return
     * @throws Exception
     */
     public ResultData<?> createCompanyStore(CompanyStoreDto companyStoreDto)
         throws Exception
     {
         
         String url = SystemParam.getWebConfigValue("RestServer") + "companyStore" + "";
         
         ResultData<?> backResult = post(url, companyStoreDto);
         
         return backResult;
     }
    
     /**
      * 删除comanyStore关系
      *
      */
     public void deleteRelate(CompanyStoreDto companyStoreDto)
         throws Exception
     {
         String url = SystemParam.getWebConfigValue("RestServer") + "companyStore" + "/deleteRelate";
         put(url, companyStoreDto);
     }
     
}
