package cn.com.eju.deal.dto.store;

import java.io.Serializable;

public class StoreADto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3414909930746275118L;
	
	private String storeNo;
	private String name;
	private String cityNo;
	private String districtNo;
	private String areaNo;
	private String address;
	private String addressDetail;
	private String longitude;
	private String latitude;
	
	
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
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	public String getDistrictNo() {
		return districtNo;
	}
	public void setDistrictNo(String districtNo) {
		this.districtNo = districtNo;
	}
	public String getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
