package cn.com.eju.deal.dto.houseLinkage.estate;

import java.util.Date;

public class EstateRuleHisDto {

	private Integer id;
	private String estateId;
	//标的
	private String incomeSubject;
	//收入结算条件
	private String commissionConditionVal;
	//收入结算描述
	private String commissionMemo;
	//返佣标准
	private String rtnCommission;
	//返佣结算条件
	private String rtnCommissionMemo;
	//合作起始于终止时间
	private Date cooperationDtStart;
	private Date cooperationDtEnd;
	
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
	public String getIncomeSubject() {
		return incomeSubject;
	}
	public void setIncomeSubject(String incomeSubject) {
		this.incomeSubject = incomeSubject;
	}
	public String getCommissionConditionVal() {
		return commissionConditionVal;
	}
	public void setCommissionConditionVal(String commissionConditionVal) {
		this.commissionConditionVal = commissionConditionVal;
	}
	public String getCommissionMemo() {
		return commissionMemo;
	}
	public void setCommissionMemo(String commissionMemo) {
		this.commissionMemo = commissionMemo;
	}
	public String getRtnCommission() {
		return rtnCommission;
	}
	public void setRtnCommission(String rtnCommission) {
		this.rtnCommission = rtnCommission;
	}
	public String getRtnCommissionMemo() {
		return rtnCommissionMemo;
	}
	public void setRtnCommissionMemo(String rtnCommissionMemo) {
		this.rtnCommissionMemo = rtnCommissionMemo;
	}
	public Date getCooperationDtStart() {
		return cooperationDtStart;
	}
	public void setCooperationDtStart(Date cooperationDtStart) {
		this.cooperationDtStart = cooperationDtStart;
	}
	public Date getCooperationDtEnd() {
		return cooperationDtEnd;
	}
	public void setCooperationDtEnd(Date cooperationDtEnd) {
		this.cooperationDtEnd = cooperationDtEnd;
	}
	
	
	
}