package cn.com.eju.pmls.estate.service;

import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateChangeDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateReleaseCityDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateReleaseDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/** 
*  发布城市变更service
*/
@Service("pmlsEstateReleaseCityService")
public class PmlsEstateReleaseCityService extends BaseService{

	/** 
     * @Title: changeStatusMode
     * @Description: 发布城市变更
     */
    public void releaseCity(Map<String, Object> reqMap)
    		throws Exception
    {
    	EstateReleaseDto estateReleaseDto = new EstateReleaseDto();
    	String url = SystemParam.getWebConfigValue("RestServer") + "estateRelease/releaseCity";
    	List<EstateReleaseCityDto> releaseCityDtoList = new ArrayList<EstateReleaseCityDto>();
    	Integer id = Integer.valueOf(reqMap.get("id").toString());
    	String estateId = reqMap.get("estateId").toString();
    	String flag = reqMap.get("flag").toString();
    	String cityNm = reqMap.get("cityNm").toString();
    	String cityNo = reqMap.get("cityNo").toString();
    	String newReleaseCity = reqMap.get("newReleaseCity").toString();
    	String oldReleaseCity = reqMap.get("oldReleaseCity").toString();
    	//reqMap.get("select_newRelseaseCityNo").toString();
    	//变更后的发布城市编号
    	String[] arrays = reqMap.get("select_newRelseaseCityNo").toString().split(",");
    	for (int i = 0; i < arrays.length; i++){
    		if(!cityNo.equals(arrays[i])){
    			EstateReleaseCityDto releaseCity = new EstateReleaseCityDto();
    			releaseCity.setEstateId(estateId);
    			releaseCity.setCityNo(arrays[i]);
    			releaseCity.setDateCreate(new Date());
    			releaseCity.setUserCreate(UserInfoHolder.get().getUserId());
    			//releaseCity.setDelflag("0");
    			releaseCityDtoList.add(releaseCity);
    		}
        }
    	EstateReleaseCityDto releaseCity = new EstateReleaseCityDto();
		releaseCity.setEstateId(estateId);
		releaseCity.setCityNo(cityNo);
		releaseCity.setDateCreate(new Date());
		releaseCity.setUserCreate(UserInfoHolder.get().getUserId());
		releaseCityDtoList.add(releaseCity);
    	/**
    	 * 日志记录城市发布状态变更
    	 */
    	EstateChangeDto estateChangeDto = new EstateChangeDto();
    	estateChangeDto.setChangeType(String.valueOf(DictionaryConstants.Project_Change_EstateReleaseCity));
    	estateChangeDto.setChangeName("项目发布城市变更");
    	//业绩归属城市,进行创建
    	if("0".equals(flag)){
    		estateChangeDto.setChangeDetail("项目发布城市由&quot;"+ cityNm +"&quot;变更为&quot;"+ newReleaseCity +"&quot;");
    	}
    	//进行更新并创建
    	if("1".equals(flag)){
    		estateChangeDto.setChangeDetail("项目发布城市由&quot;"+ oldReleaseCity +"&quot;变更为&quot;"+ newReleaseCity +"&quot;");
    	}
    	estateChangeDto.setChangeDate(new Date());
    	estateChangeDto.setEstateId(id);
    	UserInfo userInfo = UserInfoHolder.get();
    	estateChangeDto.setOperator(userInfo.getUserId());
    	estateChangeDto.setOperatorCode(userInfo.getUserCode());
    	estateChangeDto.setOperatorName(userInfo.getUserName());
    	ArrayList<EstateChangeDto> ecList = new ArrayList<>();
    	ecList.add(estateChangeDto);
    	/**
    	 * 封装pojo
    	 */
    	estateReleaseDto.setEstateChangeDetails(ecList);
    	estateReleaseDto.setEstateReleaseDtoList(releaseCityDtoList);
    	estateReleaseDto.setFlag(flag);
    	estateReleaseDto.setEstateId(estateId);
    	post(url, estateReleaseDto);
    }
    /**
     * 根据estateId查询关联的原发布城市列表
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData<?> getByEstateId(Map<String, Object> reqMap)
            throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "estateRelease"+"/queryCityListByEstateId/{param}";
        ResultData<?> reback = get(url,reqMap);
        return reback;
    }

}
