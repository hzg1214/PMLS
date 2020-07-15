/**
 * Copyright (c) 2017, www.ehousechina.com.
 * Project Name:PMLSService
 * File Name:StoreBizStop.java
 * Package Name:cn.com.eju.deal.store.model
 * Date:2017年9月26日下午4:06:03
 */
package cn.com.eju.deal.dto.store;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.com.eju.deal.core.model.BaseModel;

/**
 * ClassName: StoreBizStop <br/>
 * Description: 门店停业审核 <br/>
 * 
 * @author yinkun
 * @date: 2017年9月26日 下午4:06:03 <br/>
 * @version V1.0
 */
public class StoreBizStop extends BaseModel {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 6006736360469399143L;

    private Integer stopId;
    
    private Integer storeId;
    
    private Integer status;
    
    private String followDetail;
    
    private Integer stopReason;
    
    private String stopPicId;
    
    private Integer userCreate;
    
    private String userCreateName;
    
    private Date dateCreate;
    
    private String delFlag;
    
    private Integer auditUserId;
    
    private String auditDetail;
    
    private Date auditTime;
    
    //显示字段
    private String storeNo;
    private String name;
    private String address;
    private String maintainerName;
    private String contractTypeName;
    private String contractStatusName;
    private String bussinessStatusName;
    private String searchKey;
    private String cityNo;
    private String centerIdStr;
    private Integer contractStatus;
    
    //明细字段
    private String storeManager;
    private String storeManagerPhone;
    private String groupName;
    private String storePersonNumName;
    private String abTypeStoreName;
    private String bTypeStoreName;
    private String companyName;
    private String dateLifeStart;
    private String dateLifeEnd;
    private String decorationStatusName;
    private String stopReasonName;
    private String auditUserName;
    
    private Integer contractType;
    private Integer decorationStatus;
    private Integer contractId;
    private Integer decorationId;
    private String userCode;
    
    private List<PictureDto> storePicList=new ArrayList<PictureDto>();

    /**
     * @return  the 【主键】
     */
    public Integer getStopId() {
    
        return stopId;
    }

    /**
     * @param stopId the 【主键】 to set
     */
    public void setStopId(Integer stopId) {
    
        this.stopId = stopId;
    }

    /**
     * @return  the 【门店主键】
     */
    public Integer getStoreId() {
    
        return storeId;
    }

    /**
     * @param storeId the 【门店主键】 to set
     */
    public void setStoreId(Integer storeId) {
    
        this.storeId = storeId;
    }

    /**
     * @return  the 【状态】
     * @see "" BAS_DictionaryType 门店停业审核状态
     */
    public Integer getStatus() {
    
        return status;
    }

    /**
     * @param status the 【状态】 to set
     * @see "" BAS_DictionaryType 门店停业审核状态
     */
    public void setStatus(Integer status) {
    
        this.status = status;
    }

    /**
     * @return  the 【跟进描述】
     */
    public String getFollowDetail() {
    
        return followDetail;
    }

    /**
     * @param followDetail the 【跟进描述】 to set
     */
    public void setFollowDetail(String followDetail) {
    
        this.followDetail = followDetail;
    }

    /**
     * @return  the 【停业原因】
     * @see "" BAS_DictionaryType 门店停业原因
     */
    public Integer getStopReason() {
    
        return stopReason;
    }

    /**
     * @param stopReason the 【停业原因】 to set
     * @see "" BAS_DictionaryType 门店停业原因
     */
    public void setStopReason(Integer stopReason) {
    
        this.stopReason = stopReason;
    }

    /**
     * @return  the 【停业图片id,|分隔】
     */
    public String getStopPicId() {
    
        return stopPicId;
    }

    /**
     * @param stopPicId the 【停业图片id,|分隔】 to set
     */
    public void setStopPicId(String stopPicId) {
    
        this.stopPicId = stopPicId;
    }

    /**
     * @return  the 【创建人id】
     */
    public Integer getUserCreate() {
    
        return userCreate;
    }

    /**
     * @param userCreate the 【创建人id】 to set
     */
    public void setUserCreate(Integer userCreate) {
    
        this.userCreate = userCreate;
    }

    /**
     * @return  the 【创建人名称】
     */
    public String getUserCreateName() {
    
        return userCreateName;
    }

    /**
     * @param userCreateName the 【创建人名称】 to set
     */
    public void setUserCreateName(String userCreateName) {
    
        this.userCreateName = userCreateName;
    }

    /**
     * @return  the 【创建时间】
     */
    public Date getDateCreate() {
    
        return dateCreate;
    }

    /**
     * @param dateCreate the 【创建时间】 to set
     */
    public void setDateCreate(Date dateCreate) {
    
        this.dateCreate = dateCreate;
    }

    /**
     * @return  the 【删除标识】
     */
    public String getDelFlag() {
    
        return delFlag;
    }

    /**
     * @param delFlag the 【删除标识】 to set
     */
    public void setDelFlag(String delFlag) {
    
        this.delFlag = delFlag;
    }

    /**
     * @return  the 【审核人id】
     */
    public Integer getAuditUserId() {
    
        return auditUserId;
    }

    /**
     * @param auditUserId the 【审核人id】 to set
     */
    public void setAuditUserId(Integer auditUserId) {
    
        this.auditUserId = auditUserId;
    }

    /**
     * @return  the 【审核描述】
     */
    public String getAuditDetail() {
    
        return auditDetail;
    }

    /**
     * @param auditDetail the 【审核描述】 to set
     */
    public void setAuditDetail(String auditDetail) {
    
        this.auditDetail = auditDetail;
    }

    /**
     * @return  the 【审核时间】
     */
    public Date getAuditTime() {
    
        return auditTime;
    }

    /**
     * @param auditTime the 【审核时间】 to set
     */
    public void setAuditTime(Date auditTime) {
    
        this.auditTime = auditTime;
    }

    /**
     * @return  the 【门店编号】
     */
    public String getStoreNo() {
    
        return storeNo;
    }

    /**
     * @param storeNo the 【门店编号】 to set
     */
    public void setStoreNo(String storeNo) {
    
        this.storeNo = storeNo;
    }

    /**
     * @return  the 【门店名称】
     */
    public String getName() {
    
        return name;
    }

    /**
     * @param name the 【门店名称】 to set
     */
    public void setName(String name) {
    
        this.name = name;
    }

    /**
     * @return  the 【详细地址】
     */
    public String getAddress() {
    
        return address;
    }

    /**
     * @param address the 【详细地址】 to set
     */
    public void setAddress(String address) {
    
        this.address = address;
    }

    /**
     * @return  the 【维护人名称】
     */
    public String getMaintainerName() {
    
        return maintainerName;
    }

    /**
     * @param maintainerName the 【维护人名称】 to set
     */
    public void setMaintainerName(String maintainerName) {
    
        this.maintainerName = maintainerName;
    }

    /**
     * @return  the 【合作类型名称】
     */
    public String getContractTypeName() {
    
        return contractTypeName;
    }

    /**
     * @param contractTypeName the 【合作类型名称】 to set
     */
    public void setContractTypeName(String contractTypeName) {
    
        this.contractTypeName = contractTypeName;
    }

    /**
     * @return  the 【合同状态名称】
     */
    public String getContractStatusName() {
    
        return contractStatusName;
    }

    /**
     * @param contractStatusName the 【合同状态名称】 to set
     */
    public void setContractStatusName(String contractStatusName) {
    
        this.contractStatusName = contractStatusName;
    }

    /**
     * @return  the 【营业状态名称】
     */
    public String getBussinessStatusName() {
    
        return bussinessStatusName;
    }

    /**
     * @param bussinessStatusName the 【营业状态名称】 to set
     */
    public void setBussinessStatusName(String bussinessStatusName) {
    
        this.bussinessStatusName = bussinessStatusName;
    }

    /**
     * @return  the 【关键词】
     */
    public String getSearchKey() {
    
        return searchKey;
    }

    /**
     * @param searchKey the 【关键词】 to set
     */
    public void setSearchKey(String searchKey) {
    
        this.searchKey = searchKey;
    }

    /**
     * @return  the 【城市代码】
     */
    public String getCityNo() {
    
        return cityNo;
    }

    /**
     * @param cityNo the 【城市代码】 to set
     */
    public void setCityNo(String cityNo) {
    
        this.cityNo = cityNo;
    }

    /**
     * @return  the 【中心id,多个】
     */
    public String getCenterIdStr() {
    
        return centerIdStr;
    }

    /**
     * @param centerIdStr the 【中心id,多个】 to set
     */
    public void setCenterIdStr(String centerIdStr) {
    
        this.centerIdStr = centerIdStr;
    }

    /**
     * @return  the 【合同状态】
     */
    public Integer getContractStatus() {
    
        return contractStatus;
    }

    /**
     * @param contractStatus the 【合同状态】 to set
     */
    public void setContractStatus(Integer contractStatus) {
    
        this.contractStatus = contractStatus;
    }

    /**
     * @return  the 【storeManager】
     */
    public String getStoreManager() {
    
        return storeManager;
    }

    /**
     * @param storeManager the 【storeManager】 to set
     */
    public void setStoreManager(String storeManager) {
    
        this.storeManager = storeManager;
    }

    /**
     * @return  the 【storeManagerPhone】
     */
    public String getStoreManagerPhone() {
    
        return storeManagerPhone;
    }

    /**
     * @param storeManagerPhone the 【storeManagerPhone】 to set
     */
    public void setStoreManagerPhone(String storeManagerPhone) {
    
        this.storeManagerPhone = storeManagerPhone;
    }

    /**
     * @return  the 【groupName】
     */
    public String getGroupName() {
    
        return groupName;
    }

    /**
     * @param groupName the 【groupName】 to set
     */
    public void setGroupName(String groupName) {
    
        this.groupName = groupName;
    }

    /**
     * @return  the 【storePersonNumName】
     */
    public String getStorePersonNumName() {
    
        return storePersonNumName;
    }

    /**
     * @param storePersonNumName the 【storePersonNumName】 to set
     */
    public void setStorePersonNumName(String storePersonNumName) {
    
        this.storePersonNumName = storePersonNumName;
    }

    /**
     * @return  the 【abTypeStoreName】
     */
    public String getAbTypeStoreName() {
    
        return abTypeStoreName;
    }

    /**
     * @param abTypeStoreName the 【abTypeStoreName】 to set
     */
    public void setAbTypeStoreName(String abTypeStoreName) {
    
        this.abTypeStoreName = abTypeStoreName;
    }

    /**
     * @return  the 【bTypeStoreName】
     */
    public String getbTypeStoreName() {
    
        return bTypeStoreName;
    }

    /**
     * @param bTypeStoreName the 【bTypeStoreName】 to set
     */
    public void setbTypeStoreName(String bTypeStoreName) {
    
        this.bTypeStoreName = bTypeStoreName;
    }

    /**
     * @return  the 【companyName】
     */
    public String getCompanyName() {
    
        return companyName;
    }

    /**
     * @param companyName the 【companyName】 to set
     */
    public void setCompanyName(String companyName) {
    
        this.companyName = companyName;
    }

    /**
     * @return  the 【dateLifeStart】
     */
    public String getDateLifeStart() {
    
        return dateLifeStart;
    }

    /**
     * @param dateLifeStart the 【dateLifeStart】 to set
     */
    public void setDateLifeStart(String dateLifeStart) {
    
        this.dateLifeStart = dateLifeStart;
    }

    /**
     * @return  the 【dateLifeEnd】
     */
    public String getDateLifeEnd() {
    
        return dateLifeEnd;
    }

    /**
     * @param dateLifeEnd the 【dateLifeEnd】 to set
     */
    public void setDateLifeEnd(String dateLifeEnd) {
    
        this.dateLifeEnd = dateLifeEnd;
    }

    /**
     * @return  the 【decorationStatusName】
     */
    public String getDecorationStatusName() {
    
        return decorationStatusName;
    }

    /**
     * @param decorationStatusName the 【decorationStatusName】 to set
     */
    public void setDecorationStatusName(String decorationStatusName) {
    
        this.decorationStatusName = decorationStatusName;
    }

    /**
     * @return  the 【stopReasonName】
     */
    public String getStopReasonName() {
    
        return stopReasonName;
    }

    /**
     * @param stopReasonName the 【stopReasonName】 to set
     */
    public void setStopReasonName(String stopReasonName) {
    
        this.stopReasonName = stopReasonName;
    }

    /**
     * @return  the 【auditUserName】
     */
    public String getAuditUserName() {
    
        return auditUserName;
    }

    /**
     * @param auditUserName the 【auditUserName】 to set
     */
    public void setAuditUserName(String auditUserName) {
    
        this.auditUserName = auditUserName;
    }

    /**
     * @return  the 【contractType】
     */
    public Integer getContractType() {
    
        return contractType;
    }

    /**
     * @param contractType the 【contractType】 to set
     */
    public void setContractType(Integer contractType) {
    
        this.contractType = contractType;
    }

    /**
     * @return  the 【decorationStatus】
     */
    public Integer getDecorationStatus() {
    
        return decorationStatus;
    }

    /**
     * @param decorationStatus the 【decorationStatus】 to set
     */
    public void setDecorationStatus(Integer decorationStatus) {
    
        this.decorationStatus = decorationStatus;
    }

    /**
     * @return  the 【contractId】
     */
    public Integer getContractId() {
    
        return contractId;
    }

    /**
     * @param contractId the 【contractId】 to set
     */
    public void setContractId(Integer contractId) {
    
        this.contractId = contractId;
    }

    /**
     * @return  the 【decorationId】
     */
    public Integer getDecorationId() {
    
        return decorationId;
    }

    /**
     * @param decorationId the 【decorationId】 to set
     */
    public void setDecorationId(Integer decorationId) {
    
        this.decorationId = decorationId;
    }

    /**
     * @return  the 【userCode】
     */
    public String getUserCode() {
    
        return userCode;
    }

    /**
     * @param userCode the 【userCode】 to set
     */
    public void setUserCode(String userCode) {
    
        this.userCode = userCode;
    }

    /**
     * @return  the 【storePicList】
     */
    public List<PictureDto> getStorePicList() {
    
        return storePicList;
    }

    /**
     * @param storePicList the 【storePicList】 to set
     */
    public void setStorePicList(List<PictureDto> storePicList) {
    
        this.storePicList = storePicList;
    }
    //扩展字段
    public String acCityNo;

	public String getAcCityNo() {
		return acCityNo;
	}

	public void setAcCityNo(String acCityNo) {
		this.acCityNo = acCityNo;
	}
}

