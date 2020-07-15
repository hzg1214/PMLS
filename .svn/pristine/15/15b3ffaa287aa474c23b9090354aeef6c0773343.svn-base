package cn.com.eju.deal.staffMaintain.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.common.CenterDto;
import cn.com.eju.deal.houseLinkage.estate.dto.City;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("yfCenterUserService")
public class YFCenterUserService extends BaseService {

    /*
        查询queryList
     */
    public ResultData<?> queryList(Map<String, Object> reqMap, PageInfo pageInfo)
            throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "yfCenterUser" + "/q/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;

    }

    /*
        新增保存人员维护信息
     */
    public ResultData<?> save(Map<String, Object> map) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "yfCenterUser" + "/save";

        ResultData<?> backResult = post(url, map);

        return backResult;
    }

    /**
     * 查询getById
     */
    public ResultData<?> getById(int id)
            throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "yfCenterUser" + "/{id}";

        ResultData<?> backResult = get(url, id);

        return backResult;
    }

    /*
        更新提交人员维护信息
     */
    public ResultData<?> update(Map<String, Object> map) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "yfCenterUser" + "/update";

        ResultData<?> backResult = post(url, map);
        return backResult;

    }

    /*
        删除提交人员维护信息
    */
    public ResultData<?> delete(Map<String, Object> map) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "yfCenterUser" + "/delete";

        ResultData<?> backResult = post(url, map);


        return backResult;
    }

    public ResultData<List<City>> getAreaCity(Map<String, Object> map) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "yfCenterUser" + "/getAreaCity/{param}";

        ResultData<List<City>> backResult = get(url, map);
        return backResult;
    }



    /**
     * 获取归属中心
     *
     * @param map
     * @return
     * @throws Exception
     */
    public ResultData<List<CenterDto>> getCenterGroup(Map<String, Object> map) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "yfCenterUser" + "/getCenterGroup/{param}";

        ResultData<List<CenterDto>> backResult = get(url, map);

        return backResult;
    }
    /**
     * 获取归属中心
     *
     * @param map
     * @return
     * @throws Exception
     */
    public ResultData<List<Map<?, ?> >> getCenterAuth(Map<String, Object> map) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "yfCenterUser" + "/getCenterAuth/{param}";

        ResultData<List<Map<?, ?> >> backResult = get(url, map);

        return backResult;
    }

    public ResultData<?> queryYFTBind(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "youFangTongBind" + "/queryYFTBind";

        ResultData<?> backResult = post(url, reqMap);


        return backResult;
    }
}
