package cn.com.eju.pmls.basicInformation.personnelPermissions.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.basicInformation.personnelPermissions.dto.UserCenterCityDto;

@Service("personnelPermissionsService")
public class PersonnelPermissionsService extends BaseService {

	/**
	 * desc:查询列表 2019年11月1日
	 */
	public ResultData<?> queryPersonnelPermissionsList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
		// 调用 接口
		String url = SystemParam.getWebConfigValue("RestServer") + "personnelPermissions" + "/queryPersonnelPermissionsList/{param}";
		ResultData<?> reback = get(url, reqMap, pageInfo);
		return reback;
	}

	// 新增人员
	public ResultData<?> addUser(UserCenterCityDto dto) throws Exception {
		String url = SystemParam.getWebConfigValue("RestServer") + "personnelPermissions" + "/addUser";
		ResultData<?> reback = post(url, dto);
		return reback;
	}
	
	//根据城市获取中心
	public ResultData<?> getCenterGroup(Map<String, Object> map) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "personnelPermissions" + "/getCenterGroup/{param}";

        ResultData<?> backResult = get(url, map);

        return backResult;
    }
	
	//获取城市列表
	public ResultData<?> getCityList(Map<String, Object> map) throws Exception {
		
		String url = SystemParam.getWebConfigValue("RestServer") + "personnelPermissions" + "/getCityList/{param}";
		
		ResultData<?> backResult = get(url, map);
		
		return backResult;
	}
	
	//获取人员信息
    public ResultData<UserCenterCityDto> getUserInfo(Map<String,Object> reqMap, PageInfo pageInfo) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "personnelPermissions" + "/getUserInfo/{param}";
        ResultData<UserCenterCityDto> reback = get(url, reqMap, pageInfo);
        return reback;
    }
	
	//跟新人员信息
	public ResultData<?> updateUser(UserCenterCityDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "personnelPermissions" + "/updateUser";
        ResultData<?> reback = post(url, dto);
        return reback;
    }
	
	//删除人员
    public ResultData<?> deleteUser(UserCenterCityDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "personnelPermissions" + "/deleteUser";
        ResultData<?> reback = post(url, dto);
        return reback;
    }
}
