package cn.com.eju.pmls.commission.service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
* Service层
*/
@Service("crmOriginalService")
public class CrmOriginalService extends BaseService{

    private final LogHelper logger = LogHelper.getLogger(this.getClass());


    /**
     * 查询归属项目部
     * @param reqMap
     * @return
     * @throws Exception
     * 原EstateService同名方法
     */
    public ResultData<?> getProjectDepartment(Map<String,Object> reqMap)throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/getProjectDepartment/{param}";

        ResultData<?> backResult = get(url, reqMap);

        return backResult;
    }

    /**
     * 原EstateService同名方法
     * */
    public ResultData<?> getEstateNmList(Map<String,Object> reqMap) throws Exception{
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/getEstateNmList/{param}";

        ResultData<?> backResult = get(url,reqMap);

        return backResult;
    }
}
