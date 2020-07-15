package cn.com.eju.deal.dto.storeAudit;

/**
 * Created by xu on 2017/4/17.
 */
public class StoreNewDto {
    private Integer storeId;//门店编号
    private String storeNo;//门店编号
    private String storeName;//门店招牌
    private String longitude;//经度
    private String latitude;//纬度
    private String isDelete;//删除标记
    private String cityNo;//城市编号
    private String districtNo;//区域编号
    private String address;//地址
    private Integer userCreate;//录入人
    private String dateCreate;//创建时间
    private Integer userUpdate;//最近修改人
    private String dateUpdate;//最近修改时间
    private Integer contractType;//签约合同类型
    private String cityName;//城市
    private String districtName;//区域
    private String addressDetail;//详细地址
    private String userNameCreate;//录入人
    private String userNameUpdate;//最近修改人
    private String inputSource;//数据来源 PMLSWeb,CRWWechat,WX

    private String storeManager;//门店负责人
    private String storeManagerPhone;//门店负责人联系电话
    private String linkageSituation;//连锁情况
    private String chainBrand;//连锁品牌
    private String chainBrandName;//连锁品牌名称
    private String storeDueTime;//加盟到期时间
    private String storeLeaseDueTime;//门店租赁到期时间
    private String storePersonNum;//经纪人数
    private String storePersonNumName;//经纪人数名称
    private String nowUserSystem;//当前使用系统
    private String nowUserSystemName;//当前使用系统名称
    private String mainBusiness;//主营业务
    private String mainBusinessName;//主营业务名称
    private String transactionMode;//交易方式
    private String transactionModeName;//交易方式名称
    private Integer auditStatus;//审核状态
    private String auditStatusName;//审核状态名称
    private String auditDate;
    private String pictureRefId;//图片关联id
    private String auditReturnReason;//审核退回原因
    private String createLongitude;//创建人经度
    private String createLatitude;//创建人纬度
    private String distance;//距离
    private String userCode;//用户工号

    private Integer businessStatus;//门店营业状态

    private String checkCityNo;//待判断的城市

    public String getCheckCityNo() {
        return checkCityNo;
    }

    public void setCheckCityNo(String checkCityNo) {
        this.checkCityNo = checkCityNo;
    }

    public Integer getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(Integer businessStatus) {
        this.businessStatus = businessStatus;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(String auditDate) {
        this.auditDate = auditDate;
    }

    public String getStoreLeaseDueTime() {
        return storeLeaseDueTime;
    }

    public void setStoreLeaseDueTime(String storeLeaseDueTime) {
        this.storeLeaseDueTime = storeLeaseDueTime;
    }

    public String getStorePersonNumName() {
        return storePersonNumName;
    }

    public void setStorePersonNumName(String storePersonNumName) {
        this.storePersonNumName = storePersonNumName;
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

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
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

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
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

    public Integer getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(Integer userCreate) {
        this.userCreate = userCreate;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Integer getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(Integer userUpdate) {
        this.userUpdate = userUpdate;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Integer getContractType() {
        return contractType;
    }

    public void setContractType(Integer contractType) {
        this.contractType = contractType;
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

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getUserNameCreate() {
        return userNameCreate;
    }

    public void setUserNameCreate(String userNameCreate) {
        this.userNameCreate = userNameCreate;
    }

    public String getUserNameUpdate() {
        return userNameUpdate;
    }

    public void setUserNameUpdate(String userNameUpdate) {
        this.userNameUpdate = userNameUpdate;
    }

    public String getInputSource() {
        return inputSource;
    }

    public void setInputSource(String inputSource) {
        this.inputSource = inputSource;
    }

    public String getStoreManager() {
        return storeManager;
    }

    public void setStoreManager(String storeManager) {
        this.storeManager = storeManager;
    }

    public String getStoreManagerPhone() {
        return storeManagerPhone;
    }

    public void setStoreManagerPhone(String storeManagerPhone) {
        this.storeManagerPhone = storeManagerPhone;
    }

    public String getLinkageSituation() {
        return linkageSituation;
    }

    public void setLinkageSituation(String linkageSituation) {
        this.linkageSituation = linkageSituation;
    }

    public String getChainBrand() {
        return chainBrand;
    }

    public void setChainBrand(String chainBrand) {
        this.chainBrand = chainBrand;
    }

    public String getChainBrandName() {
        return chainBrandName;
    }

    public void setChainBrandName(String chainBrandName) {
        this.chainBrandName = chainBrandName;
    }

    public String getStoreDueTime() {
        return storeDueTime;
    }

    public void setStoreDueTime(String storeDueTime) {
        this.storeDueTime = storeDueTime;
    }

    public String getStorePersonNum() {
        return storePersonNum;
    }

    public void setStorePersonNum(String storePersonNum) {
        this.storePersonNum = storePersonNum;
    }

    public String getNowUserSystem() {
        return nowUserSystem;
    }

    public void setNowUserSystem(String nowUserSystem) {
        this.nowUserSystem = nowUserSystem;
    }

    public String getNowUserSystemName() {
        return nowUserSystemName;
    }

    public void setNowUserSystemName(String nowUserSystemName) {
        this.nowUserSystemName = nowUserSystemName;
    }

    public String getMainBusiness() {
        return mainBusiness;
    }

    public void setMainBusiness(String mainBusiness) {
        this.mainBusiness = mainBusiness;
    }

    public String getMainBusinessName() {
        return mainBusinessName;
    }

    public void setMainBusinessName(String mainBusinessName) {
        this.mainBusinessName = mainBusinessName;
    }

    public String getTransactionMode() {
        return transactionMode;
    }

    public void setTransactionMode(String transactionMode) {
        this.transactionMode = transactionMode;
    }

    public String getTransactionModeName() {
        return transactionModeName;
    }

    public void setTransactionModeName(String transactionModeName) {
        this.transactionModeName = transactionModeName;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditStatusName() {
        return auditStatusName;
    }

    public void setAuditStatusName(String auditStatusName) {
        this.auditStatusName = auditStatusName;
    }

    public String getPictureRefId() {
        return pictureRefId;
    }

    public void setPictureRefId(String pictureRefId) {
        this.pictureRefId = pictureRefId;
    }

    public String getAuditReturnReason() {
        return auditReturnReason;
    }

    public void setAuditReturnReason(String auditReturnReason) {
        this.auditReturnReason = auditReturnReason;
    }

    public String getCreateLongitude() {
        return createLongitude;
    }

    public void setCreateLongitude(String createLongitude) {
        this.createLongitude = createLongitude;
    }

    public String getCreateLatitude() {
        return createLatitude;
    }

    public void setCreateLatitude(String createLatitude) {
        this.createLatitude = createLatitude;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
