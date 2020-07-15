package cn.com.eju.deal.dto.scene;

import java.util.List;
/**
 * 
 * desc:佣金管理对象  查看返回数据
 * @author :zhenggang.Huang
 * @date   :2019年4月30日
 */
public class LnkYjReportInfo {
    private String reportId;
    private String estateNm;
    private String companyNm;
    private String addressDetail;
    private String customerNm;
    private String customerTel;
    private String dicValue;
    private Integer defaultFlag;
    private Integer delFlg;
    private List<CompanyEntity> companys;
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getEstateNm() {
		return estateNm;
	}
	public void setEstateNm(String estateNm) {
		this.estateNm = estateNm;
	}
	public String getCompanyNm() {
		return companyNm;
	}
	public void setCompanyNm(String companyNm) {
		this.companyNm = companyNm;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
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
	public String getDicValue() {
		return dicValue;
	}
	public void setDicValue(String dicValue) {
		this.dicValue = dicValue;
	}
	public Integer getDefaultFlag() {
		return defaultFlag;
	}
	public void setDefaultFlag(Integer defaultFlag) {
		this.defaultFlag = defaultFlag;
	}
	public Integer getDelFlg() {
		return delFlg;
	}
	public void setDelFlg(Integer delFlg) {
		this.delFlg = delFlg;
	}
	public List<CompanyEntity> getCompanys() {
		return companys;
	}
	public void setCompanys(List<CompanyEntity> companys) {
		this.companys = companys;
	}
    
}
