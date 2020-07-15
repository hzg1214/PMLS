package cn.com.eju.deal.user.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.user.UserDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service层
 *
 * @author (li_xiaodong)
 * @date 2016年2月2日 下午9:30:27
 */
@Service("userService")
public class UserService extends BaseService<UserDto> {

    //private final static String REST_SERVICE = SystemParam.getWebConfigValue("RestServer") + "users";
    //private final static String REST_SERVICE = SystemCfg.getString("userRestServer");

    /**
     * 查询-list
     *
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData<?> getAllUser(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "users" + "/all/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;

    }

    /**
     * auth权限
     *
     * @return
     * @throws Exception
     */
    public ResultData<?> getAuth(String systemName) throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "users" + "/auth/{name}";

        ResultData<?> backResult = get(url, systemName);

        return backResult;
    }

    /**
     * Shiro权限
     *
     * @return
     * @throws Exception
     */
    public ResultData<?> queryShiro(String userCode) throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "users" + "/shiro/{name}";

        ResultData<?> backResult = get(url, userCode);

        return backResult;
    }

    /**
     * auth权限
     *
     * @return
     * @throws Exception
     */
    public ResultData<?> getListByPostId(Integer userId, Integer selectpostId) throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "users" + "/auth/{userId}/{selectpostId}";

        ResultData<?> backResult = get(url, userId, selectpostId);

        return backResult;
    }

    /**
     * 切换岗位 获取新的userInfo
     *
     * @return
     * @throws Exception
     */
    public ResultData<?> getNewUserInfo(Integer userId, Integer selectedPostId) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "users" + "/userInfo/{userId}/{selectedPostId}";
        ResultData<?> backResult = get(url, userId, selectedPostId);
        return backResult;
    }

    /**
     * 切换城市 获取新的userInfo
     *
     * @return
     * @throws Exception
     */
    public ResultData<?> getNewPmlsUserInfo(Integer userId, String cityNo) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "users" + "/pmlsuserInfo/{userId}/{selectedcityNo}";
        ResultData<?> backResult = get(url, userId, cityNo);
        return backResult;
    }

    /**
     * 获取所属事业区
     *
     * @param selectOrgId
     * @return
     * @throws Exception
     * @Title: queryGNBySelectOrgId
     */
    public ResultData<?> queryGNBySelectOrgId(String selectOrgId) throws Exception {
        //替换/为Z
        String selectOrgIdStr = selectOrgId.replaceAll("/", "Z");
        String url = SystemParam.getWebConfigValue("RestServer") + "users" + "/userInfo/orgId/{selectOrgIdStr}";
        ResultData<?> backResult = get(url, selectOrgIdStr);
        return backResult;
    }

    /**
     * 根据userCode获取用户
     *
     * @return
     * @throws Exception
     */
    public ResultData<?> getUserByCode(String userCode) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "users" + "/userCode/{userCode}";

        ResultData<?> backResult = get(url, userCode);

        return backResult;
    }

    /**
     * 查询拓展人员列表
     */
    public ResultData<?> getExpanderUser(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "users" + "/expander/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }

    /**
     * 查询联动业绩人员
     */
    public ResultData<?> getLinkUser(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "users" + "/link/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }
    /**
     * 查询维护人列表
     */
    public ResultData<?> getMaintenanceUser(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
    	//调用 接口
    	String url = SystemParam.getWebConfigValue("RestServer") + "users" + "/getMaintenanceUser/{param}";
    	ResultData<?> reback = get(url, reqMap, pageInfo);
    	return reback;
    }

    /**
     * 查询拓展人员列表[门店维护人]
     */
    public ResultData<?> getStoreExpanderUser(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "users" + "/storeexpander/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }

    //临时代码 待改
    public ResultData<?> getSwitchLnk(Map<String, Object> reqMap) throws Exception {
        //           //调用 本地接口
        //    	   ResultData<?> reback  =  get("http://172.28.28.70:8080/OMSService/oms/performswitch/queryswitch",reqMap);
        //           return reback;

        //模拟数据
        ResultData<Map<String, Object>> reback = new ResultData<>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("AAAD4421-21B1-46FD-9DE4-D5A3183CE260", "2016-09-01");
        reback.setReturnData(map);
        reback.setSuccess();
        return reback;
    }

    /**
     * 【描述】: 通过id查询用户信息
     *
     * @return
     * @throws Exception
     * @author yinkun
     * @date: 2017年10月12日 上午9:09:09
     */
    public ResultData<?> getUserInfoById(Integer id) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "users" + "/{id}";

        ResultData<?> backResult = get(url, id);

        return backResult;
    }
    /**
     * 模糊查询联动业绩人员
     */
    public ResultData<?> getLinkUserByCondition(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "users" + "/getLinkUserByCondition/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }
}
