package cn.com.eju.deal.staffMaintain.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.common.CenterDto;

@Service("mCenterUserService")
public class MCenterUserService extends BaseService {

    /*
        查询queryList
     */
    public ResultData<?> queryList(Map<String, Object> reqMap, PageInfo pageInfo)
            throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "mCenterUser" + "/q/{param}";
        putCenterIds(reqMap);
        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;

    }

    private void putCenterIds(Map<String, Object> reqMap) {
    	if(reqMap.containsKey("centerIds") && StringUtil.isNotEmpty((String)reqMap.get("centerIds"))) {
        	String centerIds = (String)reqMap.get("centerIds");
        	String[] centerIdArr = centerIds.split(";");
        	reqMap.put("centerIds", Arrays.asList(centerIdArr));
        }
	}

	/*
        新增保存人员维护信息
     */
    public ResultData<?> save(Map<String, Object> map) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "mCenterUser" + "/save";

        putCenterIds(map);
        ResultData<?> backResult = post(url, map);

        return backResult;
    }

    /**
     * 查询getById
     */
    public ResultData<?> getById(int id)
            throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "mCenterUser" + "/{id}";

        ResultData<?> backResult = get(url, id);

        return backResult;
    }

    /*
        更新提交人员维护信息
     */
    public ResultData<?> update(Map<String, Object> map) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "mCenterUser" + "/update";
        putCenterIds(map);
        ResultData<?> backResult = post(url, map);
        return backResult;

    }

    /*
        删除提交人员维护信息
    */
    public ResultData<?> delete(Map<String, Object> map) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "mCenterUser" + "/delete";

        ResultData<?> backResult = post(url, map);


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

        String url = SystemParam.getWebConfigValue("RestServer") + "mCenterUser" + "/getCenterGroup/{param}";

        ResultData<List<CenterDto>> backResult = get(url, map);

        return backResult;
    }

}
