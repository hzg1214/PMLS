package cn.com.eju.deal.contract.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.contract.ContractChangeDto;
import org.springframework.stereotype.Service;


@Service
public class ContractChangeNewService extends BaseService<ContractChangeDto> {

    public ResultData<?> queryContractChangeNewList(Integer contractId) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "contractChangeNew" + "/queryContractChangeNewList/{contractId}";
        ResultData<?> reback = get(url,contractId);
        return reback;
    }

    public ResultData<?> getContractAndStoreInfo(Integer storeId, Integer contractId) throws Exception{

        String url = SystemParam.getWebConfigValue("RestServer") + "contractChangeNew" + "/getContractAndStoreInfo/{storeId}/{contractId}";
        ResultData<?> reback = get(url,storeId,contractId);
        return reback;
    }

    public ResultData<?> saveContractChange(ContractChangeDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "contractChangeNew" + "/saveContractChange";
        ResultData<?> reback = post(url,dto);
        return reback;
    }

    public ResultData<?> findContractChangeNewById(Integer id) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "contractChangeNew" + "/findContractChangeNewById/{id}";
        ResultData<?> reback = get(url,id);
        return reback;
    }
}
