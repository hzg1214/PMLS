package cn.com.eju.pmls.remittanceTrack.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * desc:导入 entity
 * @date   :2020年4月7日
 */
public class RemittanceTrackDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 43493298524702214L;
	private Integer Id;
	private Integer year;
	private Integer sortNo;//排序
	private String projectNo;//项目编号
	private Integer yearMonthVersion;//关账版本年月
	private Integer weekVersion;//关账版本周
	private String partnerName;//合作方名称
	private String trackType;//0预计 1实际
	//数据范围
	private String dataMonth;
	
	private String xjAccount1;//现金
	private String xjAccount2;
	private String xjAccount3;
	private String xjAccount4;
	private String xjAccount5;
	private String xjAccount6;
	
	private String dfAccount1;//抵房
	private String dfAccount2;
	private String dfAccount3;
	private String dfAccount4;
	private String dfAccount5;
	private String dfAccount6;
	
	private Date dateCreate;
	private Date dateUpdate;
	private Integer userIdCreate;
	private Integer userIdUpdate;
	private Boolean delFlag;
	public String getDataMonth() {
		return dataMonth;
	}
	public void setDataMonth(String dataMonth) {
		this.dataMonth = dataMonth;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getSortNo() {
		return sortNo;
	}
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public Integer getYearMonthVersion() {
		return yearMonthVersion;
	}
	public void setYearMonthVersion(Integer yearMonthVersion) {
		this.yearMonthVersion = yearMonthVersion;
	}
	public Integer getWeekVersion() {
		return weekVersion;
	}
	public void setWeekVersion(Integer weekVersion) {
		this.weekVersion = weekVersion;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getTrackType() {
		return trackType;
	}
	public void setTrackType(String trackType) {
		this.trackType = trackType;
	}
	public String getXjAccount1() {
		return xjAccount1;
	}
	public void setXjAccount1(String xjAccount1) {
		this.xjAccount1 = xjAccount1;
	}
	public String getXjAccount2() {
		return xjAccount2;
	}
	public void setXjAccount2(String xjAccount2) {
		this.xjAccount2 = xjAccount2;
	}
	public String getXjAccount3() {
		return xjAccount3;
	}
	public void setXjAccount3(String xjAccount3) {
		this.xjAccount3 = xjAccount3;
	}
	public String getXjAccount4() {
		return xjAccount4;
	}
	public void setXjAccount4(String xjAccount4) {
		this.xjAccount4 = xjAccount4;
	}
	public String getXjAccount5() {
		return xjAccount5;
	}
	public void setXjAccount5(String xjAccount5) {
		this.xjAccount5 = xjAccount5;
	}
	public String getXjAccount6() {
		return xjAccount6;
	}
	public void setXjAccount6(String xjAccount6) {
		this.xjAccount6 = xjAccount6;
	}
	public String getDfAccount1() {
		return dfAccount1;
	}
	public void setDfAccount1(String dfAccount1) {
		this.dfAccount1 = dfAccount1;
	}
	public String getDfAccount2() {
		return dfAccount2;
	}
	public void setDfAccount2(String dfAccount2) {
		this.dfAccount2 = dfAccount2;
	}
	public String getDfAccount3() {
		return dfAccount3;
	}
	public void setDfAccount3(String dfAccount3) {
		this.dfAccount3 = dfAccount3;
	}
	public String getDfAccount4() {
		return dfAccount4;
	}
	public void setDfAccount4(String dfAccount4) {
		this.dfAccount4 = dfAccount4;
	}
	public String getDfAccount5() {
		return dfAccount5;
	}
	public void setDfAccount5(String dfAccount5) {
		this.dfAccount5 = dfAccount5;
	}
	public String getDfAccount6() {
		return dfAccount6;
	}
	public void setDfAccount6(String dfAccount6) {
		this.dfAccount6 = dfAccount6;
	}
	public Date getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}
	public Date getDateUpdate() {
		return dateUpdate;
	}
	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}
	public Integer getUserIdCreate() {
		return userIdCreate;
	}
	public void setUserIdCreate(Integer userIdCreate) {
		this.userIdCreate = userIdCreate;
	}
	public Integer getUserIdUpdate() {
		return userIdUpdate;
	}
	public void setUserIdUpdate(Integer userIdUpdate) {
		this.userIdUpdate = userIdUpdate;
	}
	public Boolean getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}
	
}
