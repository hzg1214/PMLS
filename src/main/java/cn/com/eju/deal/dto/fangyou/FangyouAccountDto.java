package cn.com.eju.deal.dto.fangyou;

import java.util.Date;

/**
 * Created by cning on 2017/7/6.
 * 房友账号绑定dto
 */
public class FangyouAccountDto {
	private Integer id;
	private Integer storeId;
	private String storeNo;
	private String name;
	private String fangyouNo;
	private Integer operType;
	private Date dateCreate;
	private Integer userIdCreate;
	private String userName;
	//门店房友账号关联表
	private Integer openStatus;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFangyouNo() {
		return fangyouNo;
	}
	public void setFangyouNo(String fangyouNo) {
		this.fangyouNo = fangyouNo;
	}
	public Integer getOperType() {
		return operType;
	}
	public void setOperType(Integer operType) {
		this.operType = operType;
	}
	public Date getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}
	public Integer getUserIdCreate() {
		return userIdCreate;
	}
	public void setUserIdCreate(Integer userIdCreate) {
		this.userIdCreate = userIdCreate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getOpenStatus() {
		return openStatus;
	}
	public void setOpenStatus(Integer openStatus) {
		this.openStatus = openStatus;
	}
}
