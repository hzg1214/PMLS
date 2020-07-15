package cn.com.eju.deal.dto.houseLinkage.report;

import java.io.Serializable;
import java.util.Date;

import cn.com.eju.deal.base.support.SystemParam;

public class ReportSearchResultDto implements Serializable {
	
	private static final long serialVersionUID = 268231807297165902L;
	
	private Integer id;
    // 城市编号
    private String cityNo;
    // 城市名
    private String cityName;
    // 报备编码
    private String reportId;
    // 楼盘类型
    private String estateType;
    // 楼盘ID
    private String estateId;
    // 楼盘名
    private String estateNm;
    // 公司ID
    private String companyId;
    // 公司名
    private String companyNm;
    // 员工名
    private String empNm;
    // 客户id
    private String customerId;
    //客户来源
    private Integer customerFrom;
    // 客户名
    private String customerNm;
    // 客户手机
    private String customerTel;
    // 最新进度
    private Integer latestProgress;
	// 确认状态
    private Integer confirmStatus;
    // 报备日
    private Date reportDate;
    // 跟进日
    private Date followDate;
    // 有效期
    private Date validDate;
    
    // 以下是扩展字段
    
    // 楼盘类型名称
    private String estateTypeNm;
    // 最新进度名称
    private String latestProgressNm;
	// 确认状态名称
    private String confirmStatusNm;
    
    // 区域ID
    private String districtId;
    // 部门名
    private String deptNm;
    //客户来源名
    private String customerFromStr;
    
    private String estateCityNo;
    
    
	public String getEstateCityNo() {
		return estateCityNo;
	}
	public void setEstateCityNo(String estateCityNo) {
		this.estateCityNo = estateCityNo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getEstateType() {
		return estateType;
	}
	public void setEstateType(String estateType) {
		this.estateType = estateType;
	}
	public String getEstateId() {
		return estateId;
	}
	public void setEstateId(String estateId) {
		this.estateId = estateId;
	}
	public String getEstateNm() {
		return estateNm;
	}
	public void setEstateNm(String estateNm) {
		this.estateNm = estateNm;
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
	public String getEmpNm() {
		return empNm;
	}
	public void setEmpNm(String empNm) {
		this.empNm = empNm;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
	public Integer getLatestProgress() {
		return latestProgress;
	}
	public void setLatestProgress(Integer latestProgress) {
		this.latestProgress = latestProgress;
	}
	public Integer getConfirmStatus() {
		return confirmStatus;
	}
	public void setConfirmStatus(Integer confirmStatus) {
		this.confirmStatus = confirmStatus;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public Date getFollowDate() {
		return followDate;
	}
	public void setFollowDate(Date followDate) {
		this.followDate = followDate;
	}
	public Date getValidDate() {
		return validDate;
	}
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	public String getEstateTypeNm() {
		return estateTypeNm;
	}
	public void setEstateTypeNm(String estateTypeNm) {
		this.estateTypeNm = estateTypeNm;
	}
	public String getLatestProgressNm() {
		return latestProgressNm;
	}
	public void setLatestProgressNm(String latestProgressNm) {
		this.latestProgressNm = latestProgressNm;
	}
	public String getConfirmStatusNm() {
		return confirmStatusNm;
	}
	public void setConfirmStatusNm(String confirmStatusNm) {
		this.confirmStatusNm = confirmStatusNm;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
	
	public Integer getCustomerFrom() {
		return customerFrom;
	}
	public void setCustomerFrom(Integer customerFrom) {
		this.customerFrom = customerFrom;
	}
	public String getCustomerFromStr() {
		return customerFromStr;
	}
	public void setCustomerFromStr(String customerFromStr) {
		this.customerFromStr = customerFromStr;
	}
	
    
	
    
}