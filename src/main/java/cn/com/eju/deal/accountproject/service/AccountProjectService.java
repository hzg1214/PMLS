package cn.com.eju.deal.accountproject.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.accountproject.AccountProjectList;

@Service("accountProjectService")
public class AccountProjectService extends BaseService {

    /**
     * 
     * desc:查询列表
     * 2019年8月1日
     * author:zhenggang.Huang
     */
    public ResultData<?> queryList(Map<String, Object> reqMap, PageInfo pageInfo)
            throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "accountProject" + "/q/{param}";
//        putCenterIds(reqMap);
        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;

    }

    /**
     * desc:重新组装json
     * 2019年7月28日
     * author:zhenggang.Huang
     */
    private void putAccountProjectNos(Map<String, Object> reqMap) {
    	if(reqMap.containsKey("accountProjectNos") && StringUtil.isNotEmpty((String)reqMap.get("accountProjectNos"))) {
        	String accountProjectNos = (String)reqMap.get("accountProjectNos");
//        	String[] accountProjectNoArr = accountProjectNos.split(";");
        	String[] accountProjectNoArr = accountProjectNos.split(",");
        	reqMap.put("accountProjectNos", Arrays.asList(accountProjectNoArr));
        }
	}

	/**
	 * 
	 * desc:新增保存
	 * 2019年8月1日
	 * author:zhenggang.Huang
	 */
    public ResultData<?> save(Map<String, Object> map) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "accountProject" + "/save";

        putAccountProjectNos(map);
        ResultData<?> backResult = post(url, map);

        return backResult;
    }

    /**
     * 
     * desc:根据id查询
     * 2019年8月1日
     * author:zhenggang.Huang
     */
    public ResultData<?> getById(int id)
            throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "accountProject" + "/{id}";

        ResultData<?> backResult = get(url, id);

        return backResult;
    }

    /**
     * 
     * desc:更新
     * 2019年8月1日
     * author:zhenggang.Huang
     */
    public ResultData<?> update(Map<String, Object> map) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "accountProject" + "/update";
        putAccountProjectNos(map);
        ResultData<?> backResult = post(url, map);
        return backResult;

    }

    /**
     * 
     * desc:删除
     * 2019年8月1日
     * author:zhenggang.Huang
     */
    public ResultData<?> delete(Map<String, Object> map) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "accountProject" + "/delete";

        ResultData<?> backResult = post(url, map);


        return backResult;
    }



    /**
     * 
     * desc:核算主体列表
     * 2019年8月1日
     * author:zhenggang.Huang
     */
    public ResultData<List<AccountProjectList>> getAccountProjectList(Map<String, Object> map) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "accountProject" + "/getAccountProjectList/{param}";

        ResultData<List<AccountProjectList>> backResult = get(url, map);

        return backResult;
    }

}
