package cn.com.eju.deal.dto.houseLinkage.statistic;

import java.io.Serializable;
import java.util.Date;

import cn.com.eju.deal.base.support.SystemParam;

public class StatisticCompanySearchResultDto implements Serializable {
	
	private static final long serialVersionUID = 268231807297165902L;
	
	private Integer id;
	// 楼盘ID
	private String estateId;
	// 公司编码
	private String companyId;
	// 公司名
	private String companyNm;
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
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyNm() {
		return companyNm;
	}
	public void setCompanyNm(String companyNm) {
		this.companyNm = companyNm;
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