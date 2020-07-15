package cn.com.eju.pmls.borrowMoneyFrameContract.dto;

import java.io.Serializable;
/**
 * 
 * desc:借佣框架协议关联公司entity
 * @date   :2020年4月29日
 */
public class BorrowMoneyCompany implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String contractNo;//借佣框架协议编号
	private String companyNo;//关联公司编号
	private String companyName;//公司名称
	private String address;//公司地址
	private String dateCreate;
	private String userIdCreate;
	private Integer delFlag;//删除标识
	private String userCode;
	private String userName;
	private String dateUpdate;
	private String userIdUpdate;
	
	public String getDateUpdate() {
		return dateUpdate;
	}
	public void setDateUpdate(String dateUpdate) {
		this.dateUpdate = dateUpdate;
	}
	public String getUserIdUpdate() {
		return userIdUpdate;
	}
	public void setUserIdUpdate(String userIdUpdate) {
		this.userIdUpdate = userIdUpdate;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getCompanyNo() {
		return companyNo;
	}
	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(String dateCreate) {
		this.dateCreate = dateCreate;
	}
	public String getUserIdCreate() {
		return userIdCreate;
	}
	public void setUserIdCreate(String userIdCreate) {
		this.userIdCreate = userIdCreate;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	
	
}
