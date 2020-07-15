package cn.com.eju.deal.Report.service;

import java.util.Map;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.Report.dto.BasScheduleDto;

@Service("expendReportService")
public class ExpendReportService extends BaseService<BasScheduleDto> {

	/**
	 * 查询-list
	 * 选择城市查询事业部
	 *
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	public Map<?, ?> getAllGroupByTypeIdAndCityId(Map<String, Object> reqMap) throws Exception {
		//调用 接口
		String url = SystemParam.getWebConfigValue("RestServer") + "expendReport" + "/getAllGroupByTypeIdAndCityId/{param}";

		Map<?, ?> reback = index(url, reqMap);

		return reback;
	}

	//选择事业部查询部门
	public Map<?, ?> getAllGroupByOrgIdAndTypeId(Map<String, Object> reqMap) throws Exception {
		//调用 接口
		String url = SystemParam.getWebConfigValue("RestServer") + "expendReport" + "/getAllGroupByOrgIdAndTypeId/{param}";

		Map<?, ?> reback = index(url, reqMap);

		return reback;
	}

	public Map<?, ?> selectGroupByOrgId(Map<String, Object> reqMap) throws Exception {
		//调用 接口
		String url = SystemParam.getWebConfigValue("RestServer") + "expendReport" + "/selectGroupByOrgId/{param}";


		Map<?, ?> reback = index(url, reqMap);

		return reback;
	}


	/**
	 * 查询-list
	 * *******加载组******************
	 *
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	public Map<?, ?> getAllGroupByCityId(Map<String, Object> reqMap) throws Exception {

		//调用 接口
		String url = SystemParam.getWebConfigValue("RestServer") + "expendReport" + "/getAllGroupByCityId/{param}";

		Map<?, ?> reback = index(url, reqMap);

		return reback;
	}

	//**********************加载城市************************************
	public Map<?, ?> getCityByIsService(Map<String, Object> reqMap) throws Exception {
		//调用 接口
		String url = SystemParam.getWebConfigValue("RestServer") + "expendReport" + "/getCityByIsService/{param}";


		Map<?, ?> reback = index(url, reqMap);

		return reback;
	}


	public ResultData<?> queryExpandDetailList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
		//调用 接口
		String url = SystemParam.getWebConfigValue("RestServer") + "expendReport" + "/queryExpandDetailList/{param}";

		ResultData<?> reback = get(url, reqMap, pageInfo);

		return reback;
	}

	public ResultData<?> exportExpandDetailList(Map<String, Object> reqMap) throws Exception {
		//调用 接口
		String url = SystemParam.getWebConfigValue("RestServer") + "expendReport" + "/exportExpandDetailList/{param}";

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

}
