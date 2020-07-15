package cn.com.eju.deal.company.service;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.company.CompanyStoreDto;
import cn.com.eju.deal.dto.company.GpCompanyStoreDto;

/**   
* 公司门店关系 Service层
* @author 张文辉
* @date 2016年7月4日 下午5:47:17
*/
@Service("gpcompanyStoreService")
public class GpCompanyStoreService extends BaseService<GpCompanyStoreDto>
{
    
   
   
     
     /** 
      * 创建GpcomanyStore关系
      * @param comanyStore
      * @return
      * @throws Exception
      */
      public ResultData<?> createGpCompanyStore(GpCompanyStoreDto companyStoreDto)
          throws Exception
      {
          
          String url = SystemParam.getWebConfigValue("RestServer") + "companyStore" + "/gpCsAdd";
          
          ResultData<?> backResult = post(url, companyStoreDto);
          
          return backResult;
      }
    
     
     
     /**
      * 删除comanyStore关系
      *
      */
     public void deletegpRelate(GpCompanyStoreDto companyStoreDto)
         throws Exception
     {
         String url = SystemParam.getWebConfigValue("RestServer") + "companyStore" + "/deletegpRelate";
         put(url, companyStoreDto);
     }
     
}
