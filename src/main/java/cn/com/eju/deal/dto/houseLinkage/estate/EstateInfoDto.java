package cn.com.eju.deal.dto.houseLinkage.estate;

import cn.com.eju.deal.dto.scene.PadCommissionResultDto;

import java.io.Serializable;
import java.util.List;

/**   
* EstateInfoDto  Dto
* @author (qianwei)
* @date 2016年3月22日 下午4:42:54
*/
public class EstateInfoDto implements Serializable
{

    /**
    * TODO(用一句话描述这个变量表示什么)
    */ 
    private static final long serialVersionUID = 8797366850847783778L;
    // 楼盘
    private EstateDto estate;
    // 楼盘规则列表
    private List<EstateRuleDto> estateRuleDetails;
    // 楼盘户型列表
	private List<EstateTypeDto> estateTypeDetails;
	// 周边配套列表
	private List<EstateSupportDto> estateSupportDetails;
	// 图片文件列表
	private List<PhotoDto> filePhoto;
	// 变更历史
	private List<EstateChangeDto> estateChangeDetails;
	//项目城市变更
	private List<EstateReleaseCityDto> estateReleaseCityDto;
	//垫佣列表
	private List<PadCommissionResultDto> padCommissionResultList;

	private String operationType;//操作

	public List<PadCommissionResultDto> getPadCommissionResultList() {
		return padCommissionResultList;
	}

	public void setPadCommissionResultList(List<PadCommissionResultDto> padCommissionResultList) {
		this.padCommissionResultList = padCommissionResultList;
	}

	public EstateDto getEstate() {
		return estate;
	}
	public void setEstate(EstateDto estate) {
		this.estate = estate;
	}
	public List<EstateRuleDto> getEstateRuleDetails() {
		return estateRuleDetails;
	}
	public void setEstateRuleDetails(List<EstateRuleDto> estateRuleDetails) {
		this.estateRuleDetails = estateRuleDetails;
	}
	public List<EstateTypeDto> getEstateTypeDetails() {
		return estateTypeDetails;
	}
	public void setEstateTypeDetails(List<EstateTypeDto> estateTypeDetails) {
		this.estateTypeDetails = estateTypeDetails;
	}
	public List<EstateSupportDto> getEstateSupportDetails() {
		return estateSupportDetails;
	}
	public void setEstateSupportDetails(List<EstateSupportDto> estateSupportDetails) {
		this.estateSupportDetails = estateSupportDetails;
	}
	public List<PhotoDto> getFilePhoto() {
		return filePhoto;
	}
	public void setFilePhoto(List<PhotoDto> filePhoto) {
		this.filePhoto = filePhoto;
	}
	public List<EstateChangeDto> getEstateChangeDetails() {
		return estateChangeDetails;
	}
	public void setEstateChangeDetails(List<EstateChangeDto> estateChangeDetails) {
		this.estateChangeDetails = estateChangeDetails;
	}
	public List<EstateReleaseCityDto> getEstateReleaseCityDto() {
		return estateReleaseCityDto;
	}
	public void setEstateReleaseCityDto(List<EstateReleaseCityDto> estateReleaseCityDto) {
		this.estateReleaseCityDto = estateReleaseCityDto;
	}
	// 楼盘规则会签改动记录
    private List<EstateRuleHisDto> esateNowAndHistory;

	public List<EstateRuleHisDto> getEsateNowAndHistory() {
		return esateNowAndHistory;
	}

	public void setEsateNowAndHistory(List<EstateRuleHisDto> esateNowAndHistory) {
		this.esateNowAndHistory = esateNowAndHistory;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
}
