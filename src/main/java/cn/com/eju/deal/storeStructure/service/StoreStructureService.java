package cn.com.eju.deal.storeStructure.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.storeStructure.StoreStructureDto;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by xu on 2017/4/19.
 */
@Service("storeStructureService")
public class StoreStructureService extends BaseService {

    public ResultData<StoreStructureDto> queryStoreStructure(Map<?, ?> reqMap) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "storeStructureController" + "/queryStoreStructure/{param}";
        //
        ResultData<StoreStructureDto> reback = get(url, reqMap);
        return reback;
    }

}
