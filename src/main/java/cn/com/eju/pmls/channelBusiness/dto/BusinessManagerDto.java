package cn.com.eju.pmls.channelBusiness.dto;

import java.util.ArrayList;
import java.util.List;

public class BusinessManagerDto {
    private Integer id;
    private Integer rowNum;//序号
    private String companyNo;//商户编号
    private String companyName;//商户名称
    private String isDelete;//删除标识
    private String dateCreate;//创建时间
    private String userCreate;//创建人
    private String userCreateName;//创建人姓名
    private String cityNo;//城市编号
    private String cityName;
    private String districtNo;//区域编号
    private String districtName;
    private String address;//地址
    private String businessLicenseNo;//营业执照编号
    private String legalPerson;//法定代表人
    private String contactNumber;//联系电话
    private String userUpdate;//修改人
    private String dateUpdate;//修改时间
    private String acCityNo;//归属城市
    private String dockingPeo;//对接人
    private String dockingPeoTel;//对接人电话
    private String channelType;//渠道分类
    private String channelTypeName;//渠道分类
    private String brandId;//渠道品牌ID
    private String brandName;//渠道品牌名称
    private String remark;//备注
    private String channelLevel;//渠道等级
    private String channelLevelName;//渠道等级
    private String taxRate;//增值税税率
    private String taxRateName;//增值税税率名称
    private String fileIds;//图片id字符串
    private String storeNo;//门店编号
    private String operationContent;//操作日志内容
    private Integer isFyCompany;//是否我司（0否，1是）
    private String maintainer;//维护人工号
    private String maintainerName;//维护人姓名
    private String centerId;//中心ID
    private String centerName;//中心名称

    private String approveState;//是否作废标志

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getTaxRateName() {
        return taxRateName;
    }

    public void setTaxRateName(String taxRateName) {
        this.taxRateName = taxRateName;
    }

    public String getApproveState() {
		return approveState;
	}

	public void setApproveState(String approveState) {
		this.approveState = approveState;
	}
    public String getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(String maintainer) {
        this.maintainer = maintainer;
    }

    public String getMaintainerName() {
        return maintainerName;
    }

    public void setMaintainerName(String maintainerName) {
        this.maintainerName = maintainerName;
    }

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public Integer getIsFyCompany() {
        return isFyCompany;
    }

    public void setIsFyCompany(Integer isFyCompany) {
        this.isFyCompany = isFyCompany;
    }

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public String getUserCreateName() {
        return userCreateName;
    }

    public void setUserCreateName(String userCreateName) {
        this.userCreateName = userCreateName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getChannelTypeName() {
        return channelTypeName;
    }

    public void setChannelTypeName(String channelTypeName) {
        this.channelTypeName = channelTypeName;
    }

    public String getChannelLevelName() {
        return channelLevelName;
    }

    public void setChannelLevelName(String channelLevelName) {
        this.channelLevelName = channelLevelName;
    }

    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getFileIds() {
        return fileIds;
    }

    public void setFileIds(String fileIds) {
        this.fileIds = fileIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusinessLicenseNo() {
        return businessLicenseNo;
    }

    public void setBusinessLicenseNo(String businessLicenseNo) {
        this.businessLicenseNo = businessLicenseNo;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(String userUpdate) {
        this.userUpdate = userUpdate;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getAcCityNo() {
        return acCityNo;
    }

    public void setAcCityNo(String acCityNo) {
        this.acCityNo = acCityNo;
    }

    public String getDockingPeo() {
        return dockingPeo;
    }

    public void setDockingPeo(String dockingPeo) {
        this.dockingPeo = dockingPeo;
    }

    public String getDockingPeoTel() {
        return dockingPeoTel;
    }

    public void setDockingPeoTel(String dockingPeoTel) {
        this.dockingPeoTel = dockingPeoTel;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getChannelLevel() {
        return channelLevel;
    }

    public void setChannelLevel(String channelLevel) {
        this.channelLevel = channelLevel;
    }

    private Integer brandType;

    private Integer pmlsCenterId;

    private Integer isProcuration;

    private  Integer arteryType;

    public Integer getBrandType() {
        return brandType;
    }

    public void setBrandType(Integer brandType) {
        this.brandType = brandType;
    }

    public Integer getPmlsCenterId() {
        return pmlsCenterId;
    }

    public void setPmlsCenterId(Integer pmlsCenterId) {
        this.pmlsCenterId = pmlsCenterId;
    }

    public Integer getIsProcuration() {
        return isProcuration;
    }

    public void setIsProcuration(Integer isProcuration) {
        this.isProcuration = isProcuration;
    }

    public Integer getArteryType() {
        return arteryType;
    }

    public void setArteryType(Integer arteryType) {
        this.arteryType = arteryType;
    }

}
