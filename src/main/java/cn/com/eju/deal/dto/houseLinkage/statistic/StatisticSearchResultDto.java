package cn.com.eju.deal.dto.houseLinkage.statistic;

import java.io.Serializable;
import java.util.Date;

import cn.com.eju.deal.base.support.SystemParam;

public class StatisticSearchResultDto implements Serializable {
	
	private static final long serialVersionUID = 268231807297165902L;
	
	private Integer id;
	// 楼盘ID
	private String estateId;
	// 项目编码
	private String projectId;
	// 城市
	private String cityNo;
	private String cityNoNm;
	// 区域
	private String district;
	private String districtNm;
	// 楼盘类型
	private Integer estateType;
	private String estateTypeNm;
	// 楼盘名
	private String estateNm;
	// 合作起始日
	private String cooperationDtStart;
	// 合作截止日
	private String cooperationDtEnd;
	// 总业绩
	private String totalPerformance;
	// 带看奖励
	private String relationReward;
	// 认筹奖励
	private String pledgedReward;
	// 认购奖励
	private String subscribedReward;
	// 成交奖励
	private String bargainReward;
	// 佣金
	private String commission;
	// 报备人数
	private String reportPeopleNum;
	// 带看人数
	private String relationPeopleNum;
	// 认筹人数
	private String pledgedPeopleNum;
	// 认购人数
	private String subscribedPeopleNum;
	// 成交人数
	private String signPeopleNum;
	// 退筹人数
	private String getBackPeopleNum;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEstateId() {
		return estateId;
	}
	public void setEstateId(String estateId) {
		this.estateId = estateId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	public String getCityNoNm() {
		return cityNoNm;
	}
	public void setCityNoNm(String cityNoNm) {
		this.cityNoNm = cityNoNm;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getDistrictNm() {
		return districtNm;
	}
	public void setDistrictNm(String districtNm) {
		this.districtNm = districtNm;
	}
	public Integer getEstateType() {
		return estateType;
	}
	public void setEstateType(Integer estateType) {
		this.estateType = estateType;
	}
	public String getEstateTypeNm() {
		return estateTypeNm;
	}
	public void setEstateTypeNm(String estateTypeNm) {
		this.estateTypeNm = estateTypeNm;
	}
	public String getEstateNm() {
		return estateNm;
	}
	public void setEstateNm(String estateNm) {
		this.estateNm = estateNm;
	}
	public String getCooperationDtStart() {
		return cooperationDtStart;
	}
	public void setCooperationDtStart(String cooperationDtStart) {
		this.cooperationDtStart = cooperationDtStart;
	}
	public String getCooperationDtEnd() {
		return cooperationDtEnd;
	}
	public void setCooperationDtEnd(String cooperationDtEnd) {
		this.cooperationDtEnd = cooperationDtEnd;
	}
	public String getTotalPerformance() {
		return totalPerformance;
	}
	public void setTotalPerformance(String totalPerformance) {
		this.totalPerformance = totalPerformance;
	}
	public String getRelationReward() {
		return relationReward;
	}
	public void setRelationReward(String relationReward) {
		this.relationReward = relationReward;
	}
	public String getPledgedReward() {
		return pledgedReward;
	}
	public void setPledgedReward(String pledgedReward) {
		this.pledgedReward = pledgedReward;
	}
	public String getSubscribedReward() {
		return subscribedReward;
	}
	public void setSubscribedReward(String subscribedReward) {
		this.subscribedReward = subscribedReward;
	}
	public String getBargainReward() {
		return bargainReward;
	}
	public void setBargainReward(String bargainReward) {
		this.bargainReward = bargainReward;
	}
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	public String getReportPeopleNum() {
		return reportPeopleNum;
	}
	public void setReportPeopleNum(String reportPeopleNum) {
		this.reportPeopleNum = reportPeopleNum;
	}
	public String getRelationPeopleNum() {
		return relationPeopleNum;
	}
	public void setRelationPeopleNum(String relationPeopleNum) {
		this.relationPeopleNum = relationPeopleNum;
	}
	public String getPledgedPeopleNum() {
		return pledgedPeopleNum;
	}
	public void setPledgedPeopleNum(String pledgedPeopleNum) {
		this.pledgedPeopleNum = pledgedPeopleNum;
	}
	public String getSubscribedPeopleNum() {
		return subscribedPeopleNum;
	}
	public void setSubscribedPeopleNum(String subscribedPeopleNum) {
		this.subscribedPeopleNum = subscribedPeopleNum;
	}
	public String getSignPeopleNum() {
		return signPeopleNum;
	}
	public void setSignPeopleNum(String signPeopleNum) {
		this.signPeopleNum = signPeopleNum;
	}
	public String getGetBackPeopleNum() {
		return getBackPeopleNum;
	}
	public void setGetBackPeopleNum(String getBackPeopleNum) {
		this.getBackPeopleNum = getBackPeopleNum;
	}
    
    
	
    
	
    
}