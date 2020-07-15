package cn.com.eju.pmls.store.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;

@Service("pmlsFollowService")
public class PmlsFollowService extends BaseService {

	/**
	 * 
	 * desc:跟进列表
	 * 2020年2月26日
	 */
	public ResultData<?> queryList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
		// 调用 接口
		String url = SystemParam.getWebConfigValue("RestServer") + "follow" + "/q/{param}";
		ResultData<?> reback = get(url, reqMap, pageInfo);
		return reback;
	}

	/**
	 * 
	 * desc:跟进编号查询跟进详情
	 * 2020年2月27日
	 */
	public ResultData<?> getById(int id) throws Exception {
		// 调用 接口
		String url = SystemParam.getWebConfigValue("RestServer") + "follow" + "/{id}";
		ResultData<?> backResult = get(url, id);
		return backResult;
	}
	
	
	/**
	 * 
	 * desc:跟进编号查询跟进详情
	 * 2020年2月27日
	 */
//	public ResultData<?> getFollowViewById(Map<String, Object> reqMap) throws Exception {
//        String url = SystemParam.getWebConfigValue("RestServer") + "follow/getFollowViewById/{param}";
//        ResultData<?> reback = get(url, reqMap);
//        return reback;
//        
//    }
	/**
	 * 
	 * desc:跟进编号查询跟进详情
	 * 2020年2月27日
	 */
	public ResultData<?> getFollowViewById(Integer id) throws Exception {
		//调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "follow" + "/{id}";
        ResultData<?> backResult = get(url, id);
        return backResult;
	}

}
