package cn.com.eju.deal.Report.service;

import cn.com.eju.deal.Report.dto.BasScheduleDto;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Auther: xuliangliang
 * @Date: 2018\10\23 0023 17:21
 * @Description:
 */
@Service("storePreserveService")
public class StorePreserveService extends BaseService<BasScheduleDto> {
    public ResultData<?> queryStorePreserveList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "storePreserve" + "/queryStorePreserveList/{param}";
//		String url = "http://127.0.0.1:8080/PMLSService/" + "storeStopReport" + "/queryStoreStopDetailList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;
    }

    public ResultData<?> exportStorePreserveList(Map<String, Object> reqMap) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "storePreserve" + "/exportStorePreserveList/{param}";
//		String url = "http://127.0.0.1:8080/PMLSService/" + "storeStopReport" + "/exportStoreStopDetailList/{param}";
        ResultData<?> reback = get(url, reqMap);

        return reback;
    }

    /**
     * 根据userId查询城市
     *
     * @param reqMap
     * @return
     * @throws Exception
     */
    public Map<?, ?> getUserCenterAuthCityName(Map<String, Object> reqMap)
            throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "expendReport" + "/getUserCenterAuthCityName/{param}";

        Map<?, ?> reback = index(url, reqMap);

        return reback;
    }

    /**
     * 根据userId,cityId,组织架构查询事业部/区
     *
     * @param reqMap
     * @return
     * @throws Exception
     */
    public Map<?, ?> getAreaGroupName(Map<String, Object> reqMap)
            throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "expendReport" + "/getAreaGroupName/{param}";

        Map<?, ?> reback = index(url, reqMap);

        return reback;
    }


    /**
     * 根据登录人ID,城市ID，对应区/事业部ID 拿到对应部门/中心
     *
     * @param reqMap
     * @return
     * @throws Exception
     */
    public Map<?, ?> getCenterGroupName(Map<String, Object> reqMap)
            throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "expendReport" + "/getCenterGroupName/{param}";

        Map<?, ?> reback = index(url, reqMap);

        return reback;
    }

    public ResultData<?> queryStorePreserveSummaryList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "storePreserve" + "/queryStorePreserveSummaryList/{param}";
//		String url = "http://127.0.0.1:8080/PMLSService/" + "storeStopReport" + "/queryStoreStopDetailList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;
    }

    public ResultData<?> exportStorePreserveSummaryList(Map<String, Object> reqMap) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "storePreserve" + "/exportStorePreserveSummaryList/{param}";
//		String url = "http://127.0.0.1:8080/PMLSService/" + "storeStopReport" + "/exportStoreStopDetailList/{param}";
        ResultData<?> reback = get(url, reqMap);

        return reback;
    }
}
