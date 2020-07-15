package cn.com.eju.deal.dto.store;

import cn.com.eju.deal.base.support.SystemParam;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** 
* @ClassName: StoreDto 
* @Description: 接口间传输使用类
* @author 陆海丹 
* @date 2016年3月24日 下午2:06:43 
*  
*/
public class StoreDto implements Serializable
{
    /**
     * 序列化
     */
    private static final long serialVersionUID = 9050138878168116517L;
    
    private Integer storeId;//门店编号
    
    private String storeNo;//门店编号
    
    private String BTypeStoreName; //门店等级
    
    private String name;//门店招牌
    
    private String namePinyin;//门店招牌拼音
    
    private BigDecimal longitude;//经度
    
    private BigDecimal latitude;//纬度
    
    private Boolean isDelete;//删除标记
    
    private String cityNo;//城市编号
    
    private String districtNo;//区域编号
    
    private String areaNo;//板块编号
    
    private String address;//地址
    
    private String addressDetail;//详细地址
    
    private Integer storeStatus;//门店状态
    
    private Integer UserCreate;//创建人
    
    private Date dateCreate;//创建时间
    
    private Integer UserUpdate;//最近修改人
    
    private Date dateUpdate;//最近修改时间
    
    private Integer contractType;//签约合同类型
    
    //以下是扩展字段
    
    private String cityName;//城市
    
    private String districtName;//区域
    
    private String areaName;//板块
    
    private String provinceNo;//省份编号
    
    private String provinceName;//省份
    
    private String storeStatusName;//门店状态
    
    private Date dateCreateFrom;//录入起始时间（查询用）
    
    private Date dateCreateTo;//录入结束时间（查询用）
    
    private String contractTypeName;//签约合同类型
    
    private String companyName;//所属公司名称
    
    private String UserNameCreate;//录入人
    
    private String userNameUpdate;//最近修改人
    
    private String searchKey;//查询关键字
    
    private Integer fangyouId;//绑定房友账号ID
    
    //目前门店图片是单张图片    
    private String fileName;//文件名
    
    private String fileUrl;//原图地址
    
    private String fileAbbrUrl;//缩略图地址
    
    private String fileRecordMainId;//文件编号
    
    private String fileNo;//渠道系统关系fileNo
    
    private Integer companyId;//所属公司编号
    
    private Integer userIdFollow;//最新跟进人员
    
    private String userNameFollow;//最新跟进人员
    
    private Integer companyStatus;//客户状态
    
    private String companyStatusStr;//客户状态
    
    private Date dateFollow;//最后跟进日期
    
    private Date dateFollowFrom;//跟进日期范围
    
    private Date dateFollowTo;//跟进日期范围
    
    private Integer relatedCompanyId;//自己建的关联的客户编号
    
    private String contractDept;//签约事业部
    
    private String contractDate;//签约时间
    
    private String subName;//分店名称
    
    private String maintainer;//门店维护人
    
    private String contacts;//门店联系人
    
    private Integer storeScale;//门店规模
    
    private String storeScaleName;
    
    private String maintainerName;//门店维护人姓名
    
    private Integer decorationState; // 维修状态
    
    private String decorationStateNm; // 维修状态
    
    private BigDecimal deposit; // 门店保证金
    
    private BigDecimal decoractionFeeOne; // 装修翻牌费1
    
    private BigDecimal decoractionFeeTwo; // 装修翻牌费2
    
    private Integer contractStatus; // 合同状态
    
    private String contractStatusName; // 合同状态名
    
    private Date datePayDcrtFeeOne; // 装修翻牌费支付日期1 
    
    private Date datePayDcrtFeeTwo; // 装修翻牌费支付日期2 
    
    private String pleasePayNoOne; //装修翻牌费请款单号1
    
    private String pleasePayNoTwo; //装修翻牌费请款单号2
    
    private Date dateAccountDeposit; //保证金到期日期
    
    private String decorationCompNm; // 装修公司名称
    
    private String decorationContractNo; // 装修合同会签单号
    
    private Date dateDecorationBill; // 装修发票开具日期
    
    private String oaFlopNo; // OA翻牌验收单号
    
    private Date dateFlopCkAccept; // 翻牌验收通过日期
    
    private String inputSource; // 区分是来自微信还是PC 
    
    private String storeStateName; // 合同下的门店状态 名称
    // 合同下的门店状态 0:正常 , 1:变更中, 2:终止
    private Integer storeState;
    
    private String decorationStatusName; // 门店装修状态名称
    
    // 门店的保证金 是否到账
    private Integer isArrivalAct;
    // 门店的保证金 到账日期
    private Date dateArrivalAct;
    
    private String updateDateStr;//更新日期字符类型
    

    //Add by WangLei 2017/04/07 Start
    private BigDecimal refundAmount;// 退款总金额
    
    private Integer neededRenew;//是否续签

    private BigDecimal chargeStandard;//收费标准
    
//Add By QJP 合规性 2017/05/25 Start
    /**
     * 类型
     */
    private Integer ABTypeStore;

    private String ABTypeStoreName;
    
    /**
     * 乙类合同等级
     */
    private String BTypeStore;
    
  //Add By WJJ 合规性 2017/06/07 Start
  	private String changeCompany;//主体变更
    
    private String approveState;//变更审核状态
    
    private String contractStopNo;//合同终止编号   
  //Add By WJJ 合规性 2017/06/07 End


    //新增字段
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
    private String mainBusiness;//主营业务
    private String mainBusinessName;//主营业务名称
    private String transactionMode;//交易方式
    private String transactionModeName;//交易方式名称
    private Integer auditStatus;//审核状态
    private String auditStatusName;//审核状态名称
    //private List<WXPictureDto> storePicList=new ArrayList<WXPictureDto>();//门店图片
    private String pictureRefId;
    private String storePicListJson;
    private List<PictureDto> storePicList = new ArrayList<PictureDto>();

    private Integer centerId;//门店中心Id
    private String centerName;//门店中心名称
    
//Add By QJP 门店修改 2017/06/07 Start  
    private String oldStoreLogName;  //原门店店招   门店修改日志用
    private String oldCityNo;  //原门店城市No   门店修改日志用
    private String oldDistrictNo;  //原门店区域No  门店修改日志用
    private String oldStoreAddress;  //原门店地址
    private String oldAddressDetail;  //原门店详细地址
    private int changeName;    //门店修改门店店招是否被更改   
    private int changeAddress;  //门店修改门店地址是否被更改
//Add By QJP 门店修改 2017/06/07 End

    private Integer businessStatus;
    
    private Integer BToAAlert;
    private String BToAAlertDesc;
    
    private String acCityNo;

    private String flag;
    private Integer contractId;

    private String changeBusinessPlaceType;
    private Integer oldBusinessPlace;
    private Integer newBusinessPlace;
    private String changeStoreSizeScale;
    private String StoreType;
    private Integer oldStoreSize;
    private Integer newStoreSize;
    private Integer oldStoreType;
    private Integer newStoreType;
    
    private Integer brandType;//渠道类型
    private String brandTypeStr;
    private String isFyStoreStr;//是否房友门店
    private Integer pmlsCenterId;//联动中心
    private String pmlsGroupName;//联动中心名称
    private String pmlsMaintainCode;//联动维护人
    private String pmlsMaintainName;
    private Integer isUpdateCompanyFlag;//1更新

    public Integer getIsUpdateCompanyFlag() {
		return isUpdateCompanyFlag;
	}

	public void setIsUpdateCompanyFlag(Integer isUpdateCompanyFlag) {
		this.isUpdateCompanyFlag = isUpdateCompanyFlag;
	}

	public Integer getBrandType() {
		return brandType;
	}

	public void setBrandType(Integer brandType) {
		this.brandType = brandType;
	}

	public String getBrandTypeStr() {
		return brandTypeStr;
	}

	public void setBrandTypeStr(String brandTypeStr) {
		this.brandTypeStr = brandTypeStr;
	}

	public String getIsFyStoreStr() {
		return isFyStoreStr;
	}

	public void setIsFyStoreStr(String isFyStoreStr) {
		this.isFyStoreStr = isFyStoreStr;
	}

	public Integer getPmlsCenterId() {
		return pmlsCenterId;
	}

	public void setPmlsCenterId(Integer pmlsCenterId) {
		this.pmlsCenterId = pmlsCenterId;
	}

	public String getPmlsGroupName() {
		return pmlsGroupName;
	}

	public void setPmlsGroupName(String pmlsGroupName) {
		this.pmlsGroupName = pmlsGroupName;
	}

	public String getPmlsMaintainCode() {
		return pmlsMaintainCode;
	}

	public void setPmlsMaintainCode(String pmlsMaintainCode) {
		this.pmlsMaintainCode = pmlsMaintainCode;
	}

	public String getPmlsMaintainName() {
		return pmlsMaintainName;
	}

	public void setPmlsMaintainName(String pmlsMaintainName) {
		this.pmlsMaintainName = pmlsMaintainName;
	}

	public String getStoreType() {
        return StoreType;
    }

    public void setStoreType(String storeType) {
        StoreType = storeType;
    }

    public Integer getOldStoreType() {
        return oldStoreType;
    }

    public void setOldStoreType(Integer oldStoreType) {
        this.oldStoreType = oldStoreType;
    }

    public Integer getNewStoreType() {
        return newStoreType;
    }

    public void setNewStoreType(Integer newStoreType) {
        this.newStoreType = newStoreType;
    }

    /**
     * @return  the 【bToAAlert】
     */
    public Integer getBToAAlert() {
    
        return BToAAlert;
    }

    /**
     * @param bToAAlert the 【bToAAlert】 to set
     */
    public void setBToAAlert(Integer bToAAlert) {
    
        BToAAlert = bToAAlert;
    }

    /**
     * @return  the 【bToAAlertDesc】
     */
    public String getBToAAlertDesc() {
    
        return BToAAlertDesc;
    }

    /**
     * @param bToAAlertDesc the 【bToAAlertDesc】 to set
     */
    public void setBToAAlertDesc(String bToAAlertDesc) {
    
        BToAAlertDesc = bToAAlertDesc;
    }

    public String getOldAddressDetail() {
		return oldAddressDetail;
	}

	public void setOldAddressDetail(String oldAddressDetail) {
		this.oldAddressDetail = oldAddressDetail;
	}

	public int getChangeName() {
		return changeName;
	}

	public void setChangeName(int changeName) {
		this.changeName = changeName;
	}

	public int getChangeAddress() {
		return changeAddress;
	}

	public void setChangeAddress(int changeAddress) {
		this.changeAddress = changeAddress;
	}

	public String getOldStoreLogName() {
		return oldStoreLogName;
	}

	public void setOldStoreLogName(String oldStoreLogName) {
		this.oldStoreLogName = oldStoreLogName;
	}
	public String getOldCityNo() {
		return oldCityNo;
	}

	public void setOldCityNo(String oldCityNo) {
		this.oldCityNo = oldCityNo;
	}

	public String getOldDistrictNo() {
		return oldDistrictNo;
	}

	public void setOldDistrictNo(String oldDistrictNo) {
		this.oldDistrictNo = oldDistrictNo;
	}

	public String getOldStoreAddress() {
		return oldStoreAddress;
	}

	public void setOldStoreAddress(String oldStoreAddress) {
		this.oldStoreAddress = oldStoreAddress;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public Integer getCenterId() {
		return centerId;
	}

	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}

	public String getStorePicListJson() {
        return storePicListJson;
    }

    public void setStorePicListJson(String storePicListJson) {
        this.storePicListJson = storePicListJson;
    }

    public String getPictureRefId() {
        return pictureRefId;
    }

    public void setPictureRefId(String pictureRefId) {
        this.pictureRefId = pictureRefId;
    }

    public List<PictureDto> getStorePicList() {
        return storePicList;
    }

    public void setStorePicList(List<PictureDto> storePicList) {
        this.storePicList = storePicList;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
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

    public String getStoreLeaseDueTime() {
        return storeLeaseDueTime;
    }

    public void setStoreLeaseDueTime(String storeLeaseDueTime) {
        this.storeLeaseDueTime = storeLeaseDueTime;
    }

    public String getStorePersonNum() {
        return storePersonNum;
    }

    public void setStorePersonNum(String storePersonNum) {
        this.storePersonNum = storePersonNum;
    }

    public String getStorePersonNumName() {
        return storePersonNumName;
    }

    public void setStorePersonNumName(String storePersonNumName) {
        this.storePersonNumName = storePersonNumName;
    }

    public String getNowUserSystem() {
        return nowUserSystem;
    }

    public void setNowUserSystem(String nowUserSystem) {
        this.nowUserSystem = nowUserSystem;
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

    public Integer getABTypeStore() {
		return ABTypeStore;
	}

    public String getBTypeStoreName() {
		return BTypeStoreName;
	}

	public void setBTypeStoreName(String bTypeStoreName) {
		BTypeStoreName = bTypeStoreName;
	}
	public void setABTypeStore(Integer aBTypeStore) {
		ABTypeStore = aBTypeStore;
	}

	public String getBTypeStore() {
		return BTypeStore;
	}

	public void setBTypeStore(String bTypeStore) {
		BTypeStore = bTypeStore;
	}
//Add By QJP 合规性 2017/05/25 Start

	public BigDecimal getChargeStandard() {
        return chargeStandard;
    }

    public void setChargeStandard(BigDecimal chargeStandard) {
        this.chargeStandard = chargeStandard;
    }

    public Integer getNeededRenew() {
		return neededRenew;
	}

	public void setNeededRenew(Integer neededRenew) {
		this.neededRenew = neededRenew;
	}

	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}
	
	//Add by WangLei 2017/04/07 End
    
    public String getUpdateDateStr()
    {
        return updateDateStr;
    }

    public void setUpdateDateStr(String updateDateStr)
    {
        this.updateDateStr = updateDateStr;
    }

    public Integer getIsArrivalAct() {
        return isArrivalAct;
    }

    public void setIsArrivalAct(Integer isArrivalAct) {
        this.isArrivalAct = isArrivalAct;
    }

    public Date getDateArrivalAct() {
        return dateArrivalAct;
    }

    public void setDateArrivalAct(Date dateArrivalAct) {
        this.dateArrivalAct = dateArrivalAct;
    }

    /**
     * 合同变更页面中的门店状态（正常、变更中）
     */
    private String contractChangeState;
    
    public String getDecorationStatusName()
    {
        return decorationStatusName;
    }

    public void setDecorationStatusName(String decorationStatusName)
    {
        this.decorationStatusName = decorationStatusName;
    }

    public Integer getStoreState() {
        return storeState;
    }

    public void setStoreState(Integer storeState) {
        this.storeState = storeState;
    }
    
	public String getStoreStateName() {
		return storeStateName;
	}

	public void setStoreStateName(String storeStateName) {
		this.storeStateName = storeStateName;
	}
    public String getInputSource()
    {
        return inputSource;
    }
    
    public void setInputSource(String inputSource)
    {
        this.inputSource = inputSource;
    }
    
    public String getDecorationCompNm()
    {
        return decorationCompNm;
    }
    
    public void setDecorationCompNm(String decorationCompNm)
    {
        this.decorationCompNm = decorationCompNm;
    }
    
    public String getDecorationContractNo()
    {
        return decorationContractNo;
    }
    
    public void setDecorationContractNo(String decorationContractNo)
    {
        this.decorationContractNo = decorationContractNo;
    }
    
    public String getOaFlopNo()
    {
        return oaFlopNo;
    }
    
    public void setOaFlopNo(String oaFlopNo)
    {
        this.oaFlopNo = oaFlopNo;
    }
    
    public Date getDateDecorationBill()
    {
        return dateDecorationBill;
    }
    
    public void setDateDecorationBill(Date dateDecorationBill)
    {
        this.dateDecorationBill = dateDecorationBill;
    }
    
    public Date getDateFlopCkAccept()
    {
        return dateFlopCkAccept;
    }
    
    public void setDateFlopCkAccept(Date dateFlopCkAccept)
    {
        this.dateFlopCkAccept = dateFlopCkAccept;
    }
    
    public Date getDateAccountDeposit()
    {
        return dateAccountDeposit;
    }
    
    public void setDateAccountDeposit(Date dateAccountDeposit)
    {
        this.dateAccountDeposit = dateAccountDeposit;
    }
    
    public Date getDatePayDcrtFeeOne()
    {
        return datePayDcrtFeeOne;
    }
    
    public void setDatePayDcrtFeeOne(Date datePayDcrtFeeOne)
    {
        this.datePayDcrtFeeOne = datePayDcrtFeeOne;
    }
    
    public Date getDatePayDcrtFeeTwo()
    {
        return datePayDcrtFeeTwo;
    }
    
    public void setDatePayDcrtFeeTwo(Date datePayDcrtFeeTwo)
    {
        this.datePayDcrtFeeTwo = datePayDcrtFeeTwo;
    }
    
    public String getPleasePayNoOne()
    {
        return pleasePayNoOne;
    }
    
    public void setPleasePayNoOne(String pleasePayNoOne)
    {
        this.pleasePayNoOne = pleasePayNoOne;
    }
    
    public String getPleasePayNoTwo()
    {
        return pleasePayNoTwo;
    }
    
    public void setPleasePayNoTwo(String pleasePayNoTwo)
    {
        this.pleasePayNoTwo = pleasePayNoTwo;
    }
    
    public String getContractStatusName()
    {
        return contractStatusName;
    }
    
    public void setContractStatusName(String contractStatusName)
    {
        this.contractStatusName = contractStatusName;
    }
    
    public Integer getContractStatus()
    {
        return contractStatus;
    }
    
    public void setContractStatus(Integer contractStatus)
    {
        this.contractStatus = contractStatus;
    }
    
    public BigDecimal getDecoractionFeeOne()
    {
        return decoractionFeeOne;
    }
    
    public void setDecoractionFeeOne(BigDecimal decoractionFeeOne)
    {
        this.decoractionFeeOne = decoractionFeeOne;
    }
    
    public BigDecimal getDecoractionFeeTwo()
    {
        return decoractionFeeTwo;
    }
    
    public void setDecoractionFeeTwo(BigDecimal decoractionFeeTwo)
    {
        this.decoractionFeeTwo = decoractionFeeTwo;
    }
    
    public BigDecimal getDeposit()
    {
        return deposit;
    }
    
    public void setDeposit(BigDecimal deposit)
    {
        this.deposit = deposit;
    }
    
    public String getDecorationStateNm()
    {
        return decorationStateNm;
    }
    
    public void setDecorationStateNm(String decorationStateNm)
    {
        this.decorationStateNm = decorationStateNm;
    }
    
    public Integer getDecorationState()
    {
        return decorationState;
    }
    
    public void setDecorationState(Integer decorationState)
    {
        this.decorationState = decorationState;
    }
    
    public String getMaintainerName()
    {
        return maintainerName;
    }
    
    public void setMaintainerName(String maintainerName)
    {
        this.maintainerName = maintainerName;
    }
    
    public String getStoreScaleName()
    {
        return storeScaleName;
    }
    
    public void setStoreScaleName(String storeScaleName)
    {
        this.storeScaleName = storeScaleName;
    }
    
    public Integer getStoreScale()
    {
        return storeScale;
    }
    
    public void setStoreScale(Integer storeScale)
    {
        this.storeScale = storeScale;
    }
    
    public String getSubName()
    {
        return subName;
    }
    
    public void setSubName(String subName)
    {
        this.subName = subName;
    }
    
    public String getMaintainer()
    {
        return maintainer;
    }
    
    public void setMaintainer(String maintainer)
    {
        this.maintainer = maintainer;
    }
    
    public String getContacts()
    {
        return contacts;
    }
    
    public void setContacts(String contacts)
    {
        this.contacts = contacts;
    }
    
    /**
     * 门店编号
     * @return the storeId
     */
    public Integer getStoreId()
    {
        return storeId;
    }
    
    /**
     * 门店编号
     * @param storeId the storeId to set
     */
    public void setStoreId(Integer storeId)
    {
        this.storeId = storeId;
    }
    
    /**
     * 门店编号
     * @return the storeNo
     */
    public String getStoreNo()
    {
        return storeNo;
    }
    
    /**
     * 门店编号
     * @param storeNo the storeNo to set
     */
    public void setStoreNo(String storeNo)
    {
        this.storeNo = storeNo;
    }
    
    /**
     * 门店编号
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    
    /**门店编号
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * 经度
     * @return the longitude
     */
    public BigDecimal getLongitude()
    {
        return longitude;
    }
    
    /**
     * 经度
     * @param longitude the longitude to set
     */
    public void setLongitude(BigDecimal longitude)
    {
        this.longitude = longitude;
    }
    
    /**
     * 纬度
     * @return the latitude
     */
    public BigDecimal getLatitude()
    {
        return latitude;
    }
    
    /**
     * 纬度
     * @param latitude the latitude to set
     */
    public void setLatitude(BigDecimal latitude)
    {
        this.latitude = latitude;
    }
    
    /**
     * 删除标记
     * @return the isDelete
     */
    public Boolean getIsDelete()
    {
        return isDelete;
    }
    
    /**
     * 删除标记
     * @param isDelete the isDelete to set
     */
    public void setIsDelete(Boolean isDelete)
    {
        this.isDelete = isDelete;
    }
    
    /**
     * 城市编号
     * @return the cityNo
     */
    public String getCityNo()
    {
        return cityNo;
    }
    
    /**
     * 城市编号
     * @param cityNo the cityNo to set
     */
    public void setCityNo(String cityNo)
    {
        this.cityNo = cityNo;
    }
    
    /**
     * 城市
     * @return the cityName
     */
    public String getCityName()
    {
        return cityName;
    }
    
    /**
     * 城市
     * @param cityName the cityName to set
     */
    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }
    
    /**
     * 区域
     * @return the districtNo
     */
    public String getDistrictNo()
    {
        return districtNo;
    }
    
    /**
     * 区域
     * @param districtNo the districtNo to set
     */
    public void setDistrictNo(String districtNo)
    {
        this.districtNo = districtNo;
    }
    
    /**
     * 区域
     * @return the districtName
     */
    public String getDistrictName()
    {
        return districtName;
    }
    
    /**
     * 区域
     * @param districtName the districtName to set
     */
    public void setDistrictName(String districtName)
    {
        this.districtName = districtName;
    }
    
    /**
     * 板块
     * @return the areaNo
     */
    public String getAreaNo()
    {
        return areaNo;
    }
    
    /**
     * 板块
     * @param areaNo the areaNo to set
     */
    public void setAreaNo(String areaNo)
    {
        this.areaNo = areaNo;
    }
    
    /**
     * 板块
     * @return the areaName
     */
    public String getAreaName()
    {
        return areaName;
    }
    
    /**
     * 板块
     * @param areaName the areaName to set
     */
    public void setAreaName(String areaName)
    {
        this.areaName = areaName;
    }
    
    /**
     * 地址
     * @return the address
     */
    public String getAddress()
    {
        return address;
    }
    
    /**
     * 地址
     * @param address the address to set
     */
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    /**
     * 详细地址
     * @return the addressDetail
     */
    public String getAddressDetail()
    {
        return addressDetail;
    }
    
    /**
     * 详细地址
     * @param addressDetail the addressDetail to set
     */
    public void setAddressDetail(String addressDetail)
    {
        this.addressDetail = addressDetail;
    }
    
    /* *//**
            * 跟进记录
            * @return the lstFollowDtos
            */
    /*
    public List<FollowDto> getLstFollowDtos()
    {
     return lstFollowDtos;
    }
    
    *//**
      * 跟进记录
      * @param lstFollowDtos the lstFollowDtos to set
      */
    /*
    public void setLstFollowDtos(List<FollowDto> lstFollowDtos)
    {
     this.lstFollowDtos = lstFollowDtos;
    }*/
    
    /**
     * 门店状态
     * @return the storeStatus
     */
    public Integer getStoreStatus()
    {
        return storeStatus;
    }
    
    /**
     * 门店状态
     * @param storeStatus the storeStatus to set
     */
    public void setStoreStatus(Integer storeStatus)
    {
        this.storeStatus = storeStatus;
    }
    
    /**
     * 门店状态
     * @return the storeStatusName
     */
    public String getStoreStatusName()
    {
        return storeStatusName;
    }
    
    /**
     * 门店状态
     * @param storeStatusName the storeStatusName to set
     */
    public void setStoreStatusName(String storeStatusName)
    {
        this.storeStatusName = storeStatusName;
    }
    
    /**
     * 录入人员
     * @return the userCreate
     */
    public Integer getUserCreate()
    {
        return UserCreate;
    }
    
    /**
     * 录入人员
     * @param userCreate the userCreate to set
     */
    public void setUserCreate(Integer userCreate)
    {
        UserCreate = userCreate;
    }
    
    /**
     * 新增时间
     * @return the dateCreate
     */
    public Date getDateCreate()
    {
        return dateCreate;
    }
    
    /**
     * 新增时间
     * @param dateCreate the dateCreate to set
     */
    public void setDateCreate(Date dateCreate)
    {
        this.dateCreate = dateCreate;
    }
    
    /**
     * 最近更新人员
     * @return the userUpdate
     */
    public Integer getUserUpdate()
    {
        return UserUpdate;
    }
    
    /**
     * 最近更新人员
     * @param userUpdate the userUpdate to set
     */
    public void setUserUpdate(Integer userUpdate)
    {
        UserUpdate = userUpdate;
    }
    
    /**
     * 最近修改时间
     * @return the dateUpdate
     */
    public Date getDateUpdate()
    {
        return dateUpdate;
    }
    
    /**
     * 最近修改时间
     * @param dateUpdate the dateUpdate to set
     */
    public void setDateUpdate(Date dateUpdate)
    {
        this.dateUpdate = dateUpdate;
    }
    
    /**
     * 录入起始时间（查询用）
     * @return the dateCreateFrom
     */
    public Date getDateCreateFrom()
    {
        return dateCreateFrom;
    }
    
    /**
     * 录入起始时间（查询用）
     * @param dateCreateFrom the dateCreateFrom to set
     */
    public void setDateCreateFrom(Date dateCreateFrom)
    {
        this.dateCreateFrom = dateCreateFrom;
    }
    
    /**
     * 录入结束时间（查询用）
     * @return the dateCreateTo
     */
    public Date getDateCreateTo()
    {
        return dateCreateTo;
    }
    
    /**
     * 录入结束时间（查询用）
     * @param dateCreateTo the dateCreateTo to set
     */
    public void setDateCreateTo(Date dateCreateTo)
    {
        this.dateCreateTo = dateCreateTo;
    }
    
    /**
     * 所属公司名称
     * @return the companyName
     */
    public String getCompanyName()
    {
        return companyName;
    }
    
    /**
     * 所属公司名称
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }
    
    /**
     * 录入人
     * @return the userNameCreate
     */
    public String getUserNameCreate()
    {
        return UserNameCreate;
    }
    
    /**
     * 录入人
     * @param userNameCreate the userNameCreate to set
     */
    public void setUserNameCreate(String userNameCreate)
    {
        UserNameCreate = userNameCreate;
    }
    
    /**
     * 最近修改人
     * @return the userNameUpdate
     */
    public String getUserNameUpdate()
    {
        return userNameUpdate;
    }
    
    /**
     * 最近修改人
     * @param userNameUpdate the userNameUpdate to set
     */
    public void setUserNameUpdate(String userNameUpdate)
    {
        this.userNameUpdate = userNameUpdate;
    }
    
    /**
     * @return the searchKey
     */
    public String getSearchKey()
    {
        return searchKey;
    }
    
    /**
     * @param searchKey the searchKey to set
     */
    public void setSearchKey(String searchKey)
    {
        this.searchKey = searchKey;
    }
    
    /**
     * 身份编号
     * @return the provinceNo
     */
    public String getProvinceNo()
    {
        return provinceNo;
    }
    
    /**
     * 身份编号
     * @param provinceNo the provinceNo to set
     */
    public void setProvinceNo(String provinceNo)
    {
        this.provinceNo = provinceNo;
    }
    
    /**
     * 省份
     * @return the provinceName
     */
    public String getProvinceName()
    {
        return provinceName;
    }
    
    /**
     * 省份
     * @param provinceName the provinceName to set
     */
    public void setProvinceName(String provinceName)
    {
        this.provinceName = provinceName;
    }
    
    /**
     * 图片列表
     * @return the fileLst
     */
    /*
    public List<FileRecordMainDto> getFileLst()
    {
     return fileLst;
    }

    *//**
      * 图片列表
      * @param fileLst the fileLst to set
      */
    /*
    public void setFileLst(List<FileRecordMainDto> fileLst)
    {
     this.fileLst = fileLst;
    }

    *//**
      * 图片地址列表
      * @return the imageUrlLst
      */
    /*
    public List<String> getImageUrlLst()
    {
     return imageUrlLst;
    }

    *//**
      * 图片地址列表
      * @param imageUrlLst the imageUrlLst to set
      */
    /*
    public void setImageUrlLst(List<String> imageUrlLst)
    {
     this.imageUrlLst = imageUrlLst;
    }*/
    
    /**
     * 门店招牌拼音
     * @return the namePinyin
     */
    public String getNamePinyin()
    {
        return namePinyin;
    }
    
    /**
     * 门店招牌拼音
     * @param namePinyin the namePinyin to set
     */
    public void setNamePinyin(String namePinyin)
    {
        this.namePinyin = namePinyin;
    }
    
    /**
     * 文件名
     * @return the fileName
     */
    public String getFileName()
    {
        return fileName;
    }
    
    /**
     * 文件名
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
    
    /**
     * 原图路径
     * @return the fileUrl
     */
    public String getFileUrl()
    {
        return fileUrl;
    }
    
    /**
     * 原图路径
     * @param fileUrl the fileUrl to set
     */
    public void setFileUrl(String fileUrl)
    {
        this.fileUrl = fileUrl;
    }
    
    /**
     * 缩略图路径
     * @return the fileAbbrUrl
     */
    public String getFileAbbrUrl()
    {
        return fileAbbrUrl;
    }
    
    /**
     * 缩略图路径
     * @param fileAbbrUrl the fileAbbrUrl to set
     */
    public void setFileAbbrUrl(String fileAbbrUrl)
    {
        this.fileAbbrUrl = fileAbbrUrl;
    }
    
    /**
     * 文件id
     * @return the fileRecordMainId
     */
    public String getFileRecordMainId()
    {
        return fileRecordMainId;
    }
    
    /**
     * 文件id
     * @param fileRecordMainId the fileRecordMainId to set
     */
    public void setFileRecordMainId(String fileRecordMainId)
    {
        this.fileRecordMainId = fileRecordMainId;
    }
    
    /**
     * 所属公司编号
     * @return the companyId
     */
    public Integer getCompanyId()
    {
        return companyId;
    }
    
    /**
     * 所属公司编号
     * @param companyId the companyId to set
     */
    public void setCompanyId(Integer companyId)
    {
        this.companyId = companyId;
    }
    
    /**
     * 跟进人员编号
     * @return the userIdFollow
     */
    public Integer getUserIdFollow()
    {
        return userIdFollow;
    }
    
    /**
     * 跟进人员编号
     * @param userIdFollow the userIdFollow to set
     */
    public void setUserIdFollow(Integer userIdFollow)
    {
        this.userIdFollow = userIdFollow;
    }
    
    /**
     * 跟进人员姓名
     * @return the userNameFollow
     */
    public String getUserNameFollow()
    {
        return userNameFollow;
    }
    
    /**
     * 跟进人员姓名
     * @param userNameFollow the userNameFollow to set
     */
    public void setUserNameFollow(String userNameFollow)
    {
        this.userNameFollow = userNameFollow;
    }
    
    /**
     * 客户状态
     * @return the companyStatus
     */
    public Integer getCompanyStatus()
    {
        return companyStatus;
    }
    
    /**
     * 客户状态
     * @param companyStatus the companyStatus to set
     */
    public void setCompanyStatus(Integer companyStatus)
    {
        this.companyStatus = companyStatus;
    }
    
    /**
     * 客户状态
     * @return the companyStatusStr
     */
    public String getCompanyStatusStr()
    {
        return companyStatusStr;
    }
    
    /**
     * 客户状态
     * @param companyStatusStr the companyStatusStr to set
     */
    public void setCompanyStatusStr(String companyStatusStr)
    {
        this.companyStatusStr = companyStatusStr;
    }
    
    /**
     * 最新跟进日期
     * @return the dateFollow
     */
    public Date getDateFollow()
    {
        return dateFollow;
    }
    
    /**
     * 最新跟进日期
     * @param dateFollow the dateFollow to set
     */
    public void setDateFollow(Date dateFollow)
    {
        this.dateFollow = dateFollow;
    }
    
    /**
     * 跟进日期范围
     * @return the dateFollowFrom
     */
    public Date getDateFollowFrom()
    {
        return dateFollowFrom;
    }
    
    /**
     * 跟进日期范围
     * @param dateFollowFrom the dateFollowFrom to set
     */
    public void setDateFollowFrom(Date dateFollowFrom)
    {
        this.dateFollowFrom = dateFollowFrom;
    }
    
    /**
     * 跟进日期范围
     * @return the dateFollowTo
     */
    public Date getDateFollowTo()
    {
        return dateFollowTo;
    }
    
    /**
     * 跟进日期范围
     * @param dateFollowTo the dateFollowTo to set
     */
    public void setDateFollowTo(Date dateFollowTo)
    {
        this.dateFollowTo = dateFollowTo;
    }
    
    /**
     * @return the relatedCompanyId
     */
    public Integer getRelatedCompanyId()
    {
        return relatedCompanyId;
    }
    
    /**
     * @param relatedCompanyId the relatedCompanyId to set
     */
    public void setRelatedCompanyId(Integer relatedCompanyId)
    {
        this.relatedCompanyId = relatedCompanyId;
    }
    
    /**
     * 签约事业部
     * @return the contractDept
     */
    public String getContractDept()
    {
        return contractDept;
    }
    
    /**
     * 签约事业部
     * @param contractDept the contractDept to set
     */
    public void setContractDept(String contractDept)
    {
        this.contractDept = contractDept;
    }
    
    /**
     * 签约时间
     * @return the contractDate
     */
    public String getContractDate()
    {
        return contractDate;
    }
    
    /**
     * 签约时间
     * @param contractDate the contractDate to set
     */
    public void setContractDate(String contractDate)
    {
        this.contractDate = contractDate;
    }
    
    /**
     * @return the contractType
     */
    public Integer getContractType()
    {
        return contractType;
    }
    
    /**
     * @param contractType the contractType to set
     */
    public void setContractType(Integer contractType)
    {
        this.contractType = contractType;
    }
    
    /**
     * @return the contractTypeName
     */
    public String getContractTypeName()
    {
        if (null == contractType || 0 == contractType)
        {
            contractTypeName = "未签";
        }
        else
        {
            contractTypeName = SystemParam.getDicValueByDicCode(contractType + "");
        }
        return contractTypeName;
    }
    
    /**
     * @param contractTypeName the contractTypeName to set
     */
    public void setContractTypeName(String contractTypeName)
    {
        this.contractTypeName = contractTypeName;
    }
    
    public String getFileNo()
    {
        return fileNo;
    }
    
    public void setFileNo(String fileNo)
    {
        this.fileNo = fileNo;
    }
    
    /** 
	* 获取合同变更页面中的门店状态（正常、变更中）
	* @return
	*/
	public String getContractChangeState() {
		return contractChangeState;
	}

	/** 
	* 设置合同变更页面中的门店状态（正常、变更中）
	* @param contractChangeState
	*/
	public void setContractChangeState(String contractChangeState) {
		this.contractChangeState = contractChangeState;
	}

	public String getChangeCompany() {
		return changeCompany;
	}

	public void setChangeCompany(String changeCompany) {
		this.changeCompany = changeCompany;
	}

	public String getApproveState() {
		return approveState;
	}

	public void setApproveState(String approveState) {
		this.approveState = approveState;
	}

	public String getContractStopNo() {
		return contractStopNo;
	}

	public void setContractStopNo(String contractStopNo) {
		this.contractStopNo = contractStopNo;
	}

	public Integer getFangyouId() {
		return fangyouId;
	}

	public void setFangyouId(Integer fangyouId) {
		this.fangyouId = fangyouId;
	}

    private String mapMarkerStyle;//地图上图片名字

    public String getMapMarkerStyle() {
        return mapMarkerStyle;
    }

    public void setMapMarkerStyle(String mapMarkerStyle) {
        this.mapMarkerStyle = mapMarkerStyle;
    }

    /**
     * @return  the 【营业状态】
     */
    public Integer getBusinessStatus() {
    
        return businessStatus;
    }

    /**
     * @param businessStatus the 【营业状态】 to set
     */
    public void setBusinessStatus(Integer businessStatus) {
    
        this.businessStatus = businessStatus;
    }
    private Integer decorateStatus;
    private Integer decorationType;
    private Integer oaMdysSumJsj;
    private String decorateCompany;
    
    private BigDecimal receivedAmount;
    /**
   	 * 保证金退还金额
   	 */
   	private BigDecimal depositBackMoney;
   	//保证金处理方式
   	private Integer depositBalance;

	public Integer getDecorateStatus() {
		return decorateStatus;
	}

	public void setDecorateStatus(Integer decorateStatus) {
		this.decorateStatus = decorateStatus;
	}

	public Integer getDecorationType() {
		return decorationType;
	}

	public void setDecorationType(Integer decorationType) {
		this.decorationType = decorationType;
	}

	public Integer getOaMdysSumJsj() {
		return oaMdysSumJsj;
	}

	public void setOaMdysSumJsj(Integer oaMdysSumJsj) {
		this.oaMdysSumJsj = oaMdysSumJsj;
	}

	public String getDecorateCompany() {
		return decorateCompany;
	}

	public void setDecorateCompany(String decorateCompany) {
		this.decorateCompany = decorateCompany;
	}

	public BigDecimal getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(BigDecimal receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public BigDecimal getDepositBackMoney() {
		return depositBackMoney;
	}

	public void setDepositBackMoney(BigDecimal depositBackMoney) {
		this.depositBackMoney = depositBackMoney;
	}

	public Integer getDepositBalance() {
		return depositBalance;
	}

	public void setDepositBalance(Integer depositBalance) {
		this.depositBalance = depositBalance;
	}
   	private Integer oldId;

	public Integer getOldId() {
		return oldId;
	}

	public void setOldId(Integer oldId) {
		this.oldId = oldId;
	}
	private Integer decorateSituate;
	private BigDecimal decorateAmount;

	public Integer getDecorateSituate() {
		return decorateSituate;
	}

	public void setDecorateSituate(Integer decorateSituate) {
		this.decorateSituate = decorateSituate;
	}

	public BigDecimal getDecorateAmount() {
		return decorateAmount;
	}

	public void setDecorateAmount(BigDecimal decorateAmount) {
		this.decorateAmount = decorateAmount;
	}
	private Integer contractStopId;

	public Integer getContractStopId() {
		return contractStopId;
	}

	public void setContractStopId(Integer contractStopId) {
		this.contractStopId = contractStopId;
	}
	//经纪公司的城市
	private String companyCityNo;
	private String companyCityName;
	
	public String getCompanyCityName() {
		return companyCityName;
	}

	public void setCompanyCityName(String companyCityName) {
		this.companyCityName = companyCityName;
	}

	public String getCompanyCityNo() {
		return companyCityNo;
	}

	public void setCompanyCityNo(String companyCityNo) {
		this.companyCityNo = companyCityNo;
	}
	//店招编号
    private String signageNo;

	public String getSignageNo() {
		return signageNo;
	}
	public void setSignageNo(String signageNo) {
		this.signageNo = signageNo;
	}
	//应收金额
	private BigDecimal totalAmount;
	//已收
	private BigDecimal receiveAmount;
	//已退
	private BigDecimal paymentAmount;
	//本次收款
	private BigDecimal amount;

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getReceiveAmount() {
		return receiveAmount;
	}

	public void setReceiveAmount(BigDecimal receiveAmount) {
		this.receiveAmount = receiveAmount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getAcCityNo() {
		return acCityNo;
	}

	public void setAcCityNo(String acCityNo) {
		this.acCityNo = acCityNo;
	}
	//供应商编码
	private String providerCode;
	//供应商名称
	private String providerName;
	//支出类别编码
	private String payoutId;
	//支出类别名称
	private String payoutName;
	public String getProviderCode() {
		return providerCode;
	}

	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getPayoutId() {
		return payoutId;
	}

	public void setPayoutId(String payoutId) {
		this.payoutId = payoutId;
	}

	public String getPayoutName() {
		return payoutName;
	}

	public void setPayoutName(String payoutName) {
		this.payoutName = payoutName;
	}

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }
    //商铺经营类型
  	private Integer businessPlaceType;
  	private Integer businessPlaceEditFlag;
  	private String businessPlaceTypeVal;
  	
  	
	public Integer getBusinessPlaceType() {
		return businessPlaceType;
	}

	public void setBusinessPlaceType(Integer businessPlaceType) {
		this.businessPlaceType = businessPlaceType;
	}

	public String getBusinessPlaceTypeVal() {
		return businessPlaceTypeVal;
	}

	public void setBusinessPlaceTypeVal(String businessPlaceTypeVal) {
		this.businessPlaceTypeVal = businessPlaceTypeVal;
	}

	public Integer getBusinessPlaceEditFlag() {
		return businessPlaceEditFlag;
	}

	public void setBusinessPlaceEditFlag(Integer businessPlaceEditFlag) {
		this.businessPlaceEditFlag = businessPlaceEditFlag;
	}
	
	private Integer storeSizeScale;//门店规模，大型、小型、微型
	    
	private String storeSizeScaleName;//门店规模名称

	public Integer getStoreSizeScale() {
		return storeSizeScale;
	}

	public void setStoreSizeScale(Integer storeSizeScale) {
		this.storeSizeScale = storeSizeScale;
	}

	public String getStoreSizeScaleName() {
		return storeSizeScaleName;
	}

	public void setStoreSizeScaleName(String storeSizeScaleName) {
		this.storeSizeScaleName = storeSizeScaleName;
	}
	private Boolean deleteFlag;//公司门店取消关联标识

	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	//公盘合同id
	private Integer gpContractId;

	public Integer getGpContractId() {
		return gpContractId;
	}

	public void setGpContractId(Integer gpContractId) {
		this.gpContractId = gpContractId;
	}
	//房友账号
	private String fyAccount;

	public String getFyAccount() {
		return fyAccount;
	}

	public void setFyAccount(String fyAccount) {
		this.fyAccount = fyAccount;
	}

    public String getABTypeStoreName() {
        return ABTypeStoreName;
    }

    public void setABTypeStoreName(String ABTypeStoreName) {
        this.ABTypeStoreName = ABTypeStoreName;
    }

    public String getChangeBusinessPlaceType() {
        return changeBusinessPlaceType;
    }

    public void setChangeBusinessPlaceType(String changeBusinessPlaceType) {
        this.changeBusinessPlaceType = changeBusinessPlaceType;
    }

    public Integer getOldBusinessPlace() {
        return oldBusinessPlace;
    }

    public void setOldBusinessPlace(Integer oldBusinessPlace) {
        this.oldBusinessPlace = oldBusinessPlace;
    }

    public Integer getNewBusinessPlace() {
        return newBusinessPlace;
    }

    public void setNewBusinessPlace(Integer newBusinessPlace) {
        this.newBusinessPlace = newBusinessPlace;
    }

    public String getChangeStoreSizeScale() {
        return changeStoreSizeScale;
    }

    public void setChangeStoreSizeScale(String changeStoreSizeScale) {
        this.changeStoreSizeScale = changeStoreSizeScale;
    }

    public Integer getOldStoreSize() {
        return oldStoreSize;
    }

    public void setOldStoreSize(Integer oldStoreSize) {
        this.oldStoreSize = oldStoreSize;
    }

    public Integer getNewStoreSize() {
        return newStoreSize;
    }

    public void setNewStoreSize(Integer newStoreSize) {
        this.newStoreSize = newStoreSize;
    }
    //门店是否可以迁址标识
  	private String storeRelocationStatus;
	public String getStoreRelocationStatus() {
		return storeRelocationStatus;
	}
	public void setStoreRelocationStatus(String storeRelocationStatus) {
		this.storeRelocationStatus = storeRelocationStatus;
	}
  	private String storePartyChangeStatus;

	public String getStorePartyChangeStatus() {
		return storePartyChangeStatus;
	}

	public void setStorePartyChangeStatus(String storePartyChangeStatus) {
		this.storePartyChangeStatus = storePartyChangeStatus;
	}
	//门店是否可以乙转甲标识
	private String storeB2AChangeStatus;
	public String getStoreB2AChangeStatus() {
		return storeB2AChangeStatus;
	}
	public void setStoreB2AChangeStatus(String storeB2AChangeStatus) {
		this.storeB2AChangeStatus = storeB2AChangeStatus;
	}
	//授牌验收状态
	private String authCheckStatusNm;
	public String getAuthCheckStatusNm() {
		return authCheckStatusNm;
	}

	public void setAuthCheckStatusNm(String authCheckStatusNm) {
		this.authCheckStatusNm = authCheckStatusNm;
	}
	//门店类型
    private String storeTypeNm;
	public String getStoreTypeNm() {
		return storeTypeNm;
	}
	public void setStoreTypeNm(String storeTypeNm) {
		this.storeTypeNm = storeTypeNm;
	}
	
}
