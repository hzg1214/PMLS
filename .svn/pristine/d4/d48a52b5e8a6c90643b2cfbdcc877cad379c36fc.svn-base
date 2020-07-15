package cn.com.eju.deal.contract.service;

import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.dto.contract.ContractDto;

/**   
* 合同基本信息服务类
* @author (li_xiaodong)
* @date 2016年6月12日 上午9:59:27
*/
@Service("cntrctService")
public class CntrctService extends BaseService<ContractDto>
{
    
    /** 
    * 更新
    * @param contractInfoDto
    * @return
    * @throws Exception
    */
    public void update(ContractDto contractDto)
        throws Exception
    {
        
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/status";
        
        put(url, contractDto);
        
    }

    /**
     * 更新门店续签状态
     * @param contractId
     * @return
     */
    public void updateStoreReNewFlag(Integer contractId)
            throws Exception
    {

        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/updateStoreReNewFlag/{contractId}";

        ResultData<?> backResult = get(url,contractId);

    }


    
   
    
}
