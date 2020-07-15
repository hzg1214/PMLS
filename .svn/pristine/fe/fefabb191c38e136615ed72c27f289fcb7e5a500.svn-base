package cn.com.eju.deal.Report.service;

import cn.com.eju.deal.Report.dto.UsrOrgHisDto;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by eju on 2018/1/18.
 */
@Service("commonReportService")
public class CommonReportService extends BaseService {

    /**
     * 获取归属区域
     *
     * @param map
     * @return
     * @throws Exception
     */
    public ResultData<List<UsrOrgHisDto>> getRegion(Map<String, Object> map) throws Exception {

                String url = SystemParam.getWebConfigValue("RestServer") + "commonReport" + "/getRegion/{param}";

//        String url = "http://localhost:8080/CRMService/" + "commonReport" + "/getRegion/{param}";

        ResultData<List<UsrOrgHisDto>> reback = get(url, map);

        return reback;
    }

    /**
     * 获取归属城市
     *
     * @param map
     * @return
     * @throws Exception
     */
    public ResultData<List<UsrOrgHisDto>> getAreaCity(Map<String, Object> map) throws Exception {

                String url = SystemParam.getWebConfigValue("RestServer") + "commonReport" + "/getAreaCity/{param}";

//        String url = "http://localhost:8080/CRMService/" + "commonReport" + "/getAreaCity/{param}";

        ResultData<List<UsrOrgHisDto>> reback = get(url, map);

        return reback;
    }

    /**
     * 获取所在城市
     *
     * @param map
     * @return
     * @throws Exception
     */
    public ResultData<List<UsrOrgHisDto>> getCityGroup(Map<String, Object> map) throws Exception {

                String url = SystemParam.getWebConfigValue("RestServer") + "commonReport" + "/getCityGroup/{param}";

//        String url = "http://localhost:8080/CRMService/" + "commonReport" + "/getCityGroup/{param}";

        ResultData<List<UsrOrgHisDto>> reback = get(url, map);

        return reback;
    }

    /**
     * 获取归属中心
     *
     * @param map
     * @return
     * @throws Exception
     */
    public ResultData<List<UsrOrgHisDto>> getCenterGroup(Map<String, Object> map) throws Exception {

                String url = SystemParam.getWebConfigValue("RestServer") + "commonReport" + "/getCenterGroup/{param}";

//        String url = "http://localhost:8080/CRMService/" + "commonReport" + "/getCenterGroup/{param}";

        ResultData<List<UsrOrgHisDto>> reback = get(url, map);

        return reback;
    }

    /**
     * 获取项目归属部门
     *
     * @param map
     * @return
     * @throws Exception
     */
    public ResultData<List<UsrOrgHisDto>> getDeptGroup(Map<String, Object> map) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "commonReport" + "/getDeptGroup/{param}";

        ResultData<List<UsrOrgHisDto>> reback = get(url, map);

        return reback;
    }

}
