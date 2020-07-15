package cn.com.eju.deal.dto.houseLinkage.estate;

import java.io.Serializable;
import java.util.Date;

public class EstateReleaseCityDto implements Serializable{
	 /**
	    * TODO(用一句话描述这个变量表示什么)
	    */
	private static final long serialVersionUID = -5775427213591322713L;
	
	private Integer id;
	private String estateId;
	private String cityNo;
	private String delflag;
	private Date dateCreate;
	private Integer userCreate;
	private String cityName;
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
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
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	public Date getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}
	public Integer getUserCreate() {
		return userCreate;
	}
	public void setUserCreate(Integer userCreate) {
		this.userCreate = userCreate;
	}
	

}