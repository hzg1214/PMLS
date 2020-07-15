package cn.com.eju.deal.scene.inCommission.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateInfoDto;

/**
 * Service层
 *
 * @author (sucen)
 * @date 2017年8月7日 下午9:25:30
 */
@Service("sceneInCommissionService")
public class SceneInCommissionService extends BaseService {

    //private final static String REST_SCENE_COMMISION = SystemCfg.getString("sceneCommissionRestServer");

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    public ResultData<?> getInCommissionList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "sceneInCommission" + "/getInCommissionList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;
    }

    /**
     * 导入新增
     *
     * @param id
     * @param updateId
     * @return
     * @throws Exception
     */
    public void insertLnkImport(Map<String, Object> map)
            throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "sceneInCommission" + "/imputAdd";//"/imputAdd/{param}";
        post(url, map);
        //get(url,map);

    }

    /**
     * 导入更新
     *
     * @param id
     * @param updateId
     * @return
     * @throws Exception
     */
    public void updateLnkImport(Map<String, Object> map)
            throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "sceneInCommission" + "/imputUp";///{param}";
        post(url, map);
        //get(url,map);

    }

    /**
     * 根据报备ID查询数据
     *
     * @param reportId
     * @return
     */
    public ResultData<?> getLnkImportByReportId(Map<String, Object> map) {
        String url = SystemParam.getWebConfigValue("RestServer") + "sceneInCommission" + "/byReportId/{param}";

        ResultData<?> resultData = null;
        try {
            resultData = get(url, map);
        } catch (Exception e) {
            logger.warn("数据导入失败！");
        }
        return resultData;
    }

    /**
     * 导入时写入log日志
     *
     * @param map
     * @return
     */
    public ResultData<?> createLogLnkImport(Map<String, Object> map) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "sceneInCommission" + "/imputLog/{param}";
        return get(url, map);
    }

    /*
     * 获得对应城市的关账年月
     * created by wang kanlin 2017/8/15
     */
    public ResultData<?> getInCommissionSwitchMonth(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "sceneInCommission" + "/queryCommisionSwitchMonth/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;
    }

    /*
     * 获得佣金详情列表
     * created by wang kanlin 2017/8/15
     */
    public ResultData<?> queryInCommissionList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "sceneInCommission" + "/queryCommisionList/{param}";


        UserInfo userInfo = UserInfoHolder.get();
        reqMap.put("operUserId", userInfo.getUserId());
        reqMap.put("operUserName", userInfo.getUserName());
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        reqMap.put("operDt", date);
        reqMap.put("fileName", format.format(date) + "_" + userInfo.getUserName());
        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;

    }
}
