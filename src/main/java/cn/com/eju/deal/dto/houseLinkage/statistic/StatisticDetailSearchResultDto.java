package cn.com.eju.deal.dto.houseLinkage.statistic;

import java.io.Serializable;
import java.util.Date;

import cn.com.eju.deal.base.support.SystemParam;

public class StatisticDetailSearchResultDto implements Serializable {
	
	private static final long serialVersionUID = 268231807297165902L;
	
	// 楼盘ID
	private String estateId;
	// 项目统计编号
	private String countId;
	// 城市
	private String cityNo;
	private String cityNoNm;
	// 区域
	private String districtId;
	private String districtNm;
	// 楼盘类型
	private Integer estateType;
	private String estateTypeNm;
	// 楼盘名
	private String estateNm;
	// 客户名
	private String customerNm;
	// 手机
	private String customerTel;
	// 公司名
	private String companyNm;
	// 经纪人部门
	private String deptNm;
	// 经纪人
	private String empNm;
	// 奖励类型
	private Integer rewardType;
	private String rewardTypeNm;
	// 奖励金额
	private String rewardMoney;
	// 结算金额
	private String accountMoney;
	// 结算
	private Integer accountType;
	private String accountTypeNm;
	// 结算状态
	private Integer accountStatus;
	private String accountStatusNm;
	// 认定日
	private String recognitionDay;
	// 带看奖励
	private String relationReward;
	private String accountRelationReward;
	// 认筹奖励
	private String pledgedReward;
	private String accountPledgedReward;
	// 认购奖励
	private String subscribedReward;
	private String accountSubscribedReward;
	// 成交奖励
	private String bargainReward;
	private String accountBargainReward;
	// 佣金
	private String commission;
	private String accountCommission;
	private Integer commissionAccountStatus;
	
	public String getEstateId() {
		return estateId;
	}
	public void setEstateId(String estateId) {
		this.estateId = estateId;
	}
	public String getCountId() {
		return countId;
	}
	public void setCountId(String countId) {
		this.countId = countId;
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
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
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
	public String getCustomerNm() {
		return customerNm;
	}
	public void setCustomerNm(String customerNm) {
		this.customerNm = customerNm;
	}
	public String getCustomerTel() {
		return customerTel;
	}
	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}
	public String getCompanyNm() {
		return companyNm;
	}
	public void setCompanyNm(String companyNm) {
		this.companyNm = companyNm;
	}
	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
	public String getEmpNm() {
		return empNm;
	}
	public void setEmpNm(String empNm) {
		this.empNm = empNm;
	}
	public Integer getRewardType() {
		return rewardType;
	}
	public void setRewardType(Integer rewardType) {
		this.rewardType = rewardType;
	}
	public String getRewardTypeNm() {
		return rewardTypeNm;
	}
	public void setRewardTypeNm(String rewardTypeNm) {
		this.rewardTypeNm = rewardTypeNm;
	}
	public String getRewardMoney() {
		return rewardMoney;
	}
	public void setRewardMoney(String rewardMoney) {
		this.rewardMoney = rewardMoney;
	}
	public String getAccountMoney() {
		return accountMoney;
	}
	public void setAccountMoney(String accountMoney) {
		this.accountMoney = accountMoney;
	}
	public Integer getAccountType() {
		return accountType;
	}
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
	public String getAccountTypeNm() {
		return accountTypeNm;
	}
	public void setAccountTypeNm(String accountTypeNm) {
		this.accountTypeNm = accountTypeNm;
	}
	public Integer getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getAccountStatusNm() {
		return accountStatusNm;
	}
	public void setAccountStatusNm(String accountStatusNm) {
		this.accountStatusNm = accountStatusNm;
	}
	public String getRecognitionDay() {
		return recognitionDay;
	}
	public void setRecognitionDay(String recognitionDay) {
		this.recognitionDay = recognitionDay;
	}
	public String getRelationReward() {
		return relationReward;
	}
	public void setRelationReward(String relationReward) {
		this.relationReward = relationReward;
	}
	public String getAccountRelationReward() {
		return accountRelationReward;
	}
	public void setAccountRelationReward(String accountRelationReward) {
		this.accountRelationReward = accountRelationReward;
	}
	public String getPledgedReward() {
		return pledgedReward;
	}
	public void setPledgedReward(String pledgedReward) {
		this.pledgedReward = pledgedReward;
	}
	public String getAccountPledgedReward() {
		return accountPledgedReward;
	}
	public void setAccountPledgedReward(String accountPledgedReward) {
		this.accountPledgedReward = accountPledgedReward;
	}
	public String getSubscribedReward() {
		return subscribedReward;
	}
	public void setSubscribedReward(String subscribedReward) {
		this.subscribedReward = subscribedReward;
	}
	public String getAccountSubscribedReward() {
		return accountSubscribedReward;
	}
	public void setAccountSubscribedReward(String accountSubscribedReward) {
		this.accountSubscribedReward = accountSubscribedReward;
	}
	public String getBargainReward() {
		return bargainReward;
	}
	public void setBargainReward(String bargainReward) {
		this.bargainReward = bargainReward;
	}
	public String getAccountBargainReward() {
		return accountBargainReward;
	}
	public void setAccountBargainReward(String accountBargainReward) {
		this.accountBargainReward = accountBargainReward;
	}
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	public String getAccountCommission() {
		return accountCommission;
	}
	public void setAccountCommission(String accountCommission) {
		this.accountCommission = accountCommission;
	}
	public Integer getCommissionAccountStatus() {
		return commissionAccountStatus;
	}
	public void setCommissionAccountStatus(Integer commissionAccountStatus) {
		this.commissionAccountStatus = commissionAccountStatus;
	}

	
    
}