package cn.com.eju.deal.common.service;


import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("commonService")
public class CommonService extends BaseService {
    /**
     * TODO (获取关账信息)
     *
     * @return
     */
    public ResultData<?> getSwitchInfo(String cityNo) throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "commons" + "/getSwitchInfo/" + cityNo;

        ResultData<?> backResult = get(url);

        return backResult;
    }

    public ResultData<?> queryOrgList(Map<String, Object> reqMap) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "commons" + "/queryOrgList/{param}";

        ResultData<?> backResult = get(url, reqMap);

        return backResult;
    }

    public ResultData<?> queryCityList(Map<String, Object> reqMap) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "commons" + "/queryCityList/{param}";

        ResultData<?> backResult = get(url, reqMap);

        return backResult;
    }

    public ResultData<?> queryCenterList(Map<String, Object> reqMap) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "commons" + "/queryCenterList/{param}";

        ResultData<?> backResult = get(url, reqMap);

        return backResult;
    }

    //根据cityNo查询模板编号
    public ResultData<?> getCitySettingByCityNo(String cityNo) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "commons" + "/qtemplatecode/" + cityNo;

        ResultData<?> backResult = get(url);

        return backResult;
    }

    /**
     * 得到门店所属城市
     *
     * @return
     * @throws Exception
     */
    public ResultData<?> queryCityListByIsService() throws Exception {

                String url = SystemParam.getWebConfigValue("RestServer") + "citycascade" + "/queryCityListByIsService";

//        String url = "http://localhost:8080/CRMService/" + "citycascade" + "/queryCityListByIsService";

        ResultData<?> reback = get(url);

        return reback;
    }

    public ResultData<?> queryFullNameList(Map<String, Object> reqMap) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "commons" + "/queryFullNameList/{param}";

        ResultData<?> backResult = get(url, reqMap);

        return backResult;
    }
}
