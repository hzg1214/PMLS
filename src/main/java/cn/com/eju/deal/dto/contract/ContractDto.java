package cn.com.eju.deal.dto.contract;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**   
* ContractDto  Dto
* @author (qianwei)
* @date 2016年3月22日 下午4:42:54
*/
public class ContractDto implements Serializable
{
    /**
    * TODO(用一句话描述这个变量表示什么)
    */
    private static final long serialVersionUID = -5775427213591322713L;
    
    private Integer id;
    
    // 合同编号
    private String contractNo;
    
    // 合同类型
    private Integer contractType;
    
    // 客户编号
    private Integer companyId;
    
    // 用户编号
    private Integer userCreate;
    
    // 甲方名称
    private String partyB;
    
    // 甲方地址
    private String partyBAddress;
    
    // 法人
    private String lealPerson;
    
    // 合同生效时间
    private Date dateLifeStart;
    
    // 合同到期时间
    private Date dateLifeEnd;
    
    // 创建时间
    private Date dateCreate;
    
    // 每门店保证金
    private BigDecimal depositFee;
    
    // 违约金
    private BigDecimal penaltyFee;
    
    // 收件人
    private String recipients;
    
    // 收件地址
    private String recipientsAddress;
    
    // 备注
    private String remark;
    
    // 客户公司账号
    private String companyBankNo;
    
    // 开户银行
    private String bankAccount;
    
    // 开户名
    private String accountName;
    
    // 合作协议书编号
    private String agreementNo;
    
    // 营业执照注册号
    private String registrId;
    
    // 合同状态
    private Integer contractStatus;
    
    // 甲方授权代表
    private String authRepresentative;
    
    // 总保证金
    private BigDecimal totleDepositFee;
    
    // 合作门店数
    private Integer storeNum;
    
    // 城市No
    private String cityNo;
    
    // 区域No
    private String districtNo;
    
    // 板块No
    private String areaNo;
    
    // 甲方住所地城市No
    private String partyBcityNo;
    
    // 甲方住所地区域No
    private String partyBdistrictNo;
    
    // 甲方住所地板块No
    private String partyBareaNo;
    
    // 业绩归属拓展人员Id
    private String expandingPersonnelId;
    
    // 业绩归属拓展人员
    private String expandingPersonnel;
    
    // 实际经营地址城市No
    private String actualOperationCityNo;
    
    // 实际经营地址区域No
    private String actualOperationDistrictNo;
    
    // 实际经营地址板块No
    private String actualOperationAreaNo;
    
    // 实际经营地址
    private String actualOperationAddress;
    
    //中介联系方式
    private String agentContact;
    
    // 与OA对接使用
    private String flowId;
    
    // 中介营业执照 
    private String oaFileIdBusiness;
    
    // 中介法定代表人/负责人身份证或名片
    private String oaFileIdCard;
    
    // 合作协议书 
    private String oaFileIdPhoto;
    
    // 中介门店照片（门店外景和室内）
    private String oaFileIdStore;
    
    // 房友系统申请安装单/确认单 
    private String oaFileIdInstall;
    
    // 重要提示函
    private String oaFileIdNotice;
    // 补充协议
    private String oaFileIdComplement;
    // 其它 
    private String oaFileIdOther;
    
    //下边是拓展字段
    
    // 甲方住所地详细地址(供查看用)
    private String partyAddressDetail;
    
    // 甲方收件详细地址(供查看用)
    private String recipientsAddressDetail;
    
    // 合同状态名
    private String contractStatusName;
    
    // 甲方住所地城市Name
    private String partyBCityName;
    
    // 甲方住所地区域Name
    private String partyBDistrictName;
    
    // 甲方住所地板块Name
    private String partyBAreaName;
    
    // 合作模式
    private String coopType;
    
    private String contractTypeName;
    
    //OA审批流程类别
    private String oaApproveType;
    //OA审批流程类别
    private String oaApproveTypeName;
    
    //业绩确认时间
    private Date performDate;
    
    //合同状态更新时间
    private Date dateUpdate;
    
    //合同业绩归属部门
    private String pfmcAtbtDepmt;
    
    //合同业绩归属部门名称
    private String pfmcAtbtDepmtName;
    // 是否变更标识 isChanged
    private Boolean isChanged;
    // 是否变更标识: 是  or 否
    private String isChangedStr;
    
    //Add 2017/04/05 cning start------->
    //原合同编号
    private String originalContractNo;

    //合同类别(新签，续签)
    private Integer originalContractdistinction;

    //合作协议书类型
    private Integer agreementType;
    
 // Add By GuoPengFei 2017/05/25 合规性 start    
    // 是否乙转甲（ 20201；是 20202；否）
    private Integer contractB2A;
    
    // 是否乙转甲表示名称
    private String contractB2AName;
    
 // Add By GuoPengFei 2017/05/25 合规性 end    
    
 // Add By QJP 2017/07/20 start
    private Integer originalId; //原合同编号
// Add By QJP 2017/07/20  end  

    private Integer centerId;
    
    private String acCityNo;
    
    //合同变更id
    private Integer contractChangeId;

    private Integer contractVersion;

    private String accountProject;

    private String accountProjectNo;

    private String bizType;
    
    //2019/05/29 hzg 添加授牌类型和开户省市
    private String shoupaiType;//授牌类型
    private String accountProvinceNo;//开户省编号
    private String accountProvinceName;//开户省名称
    private String accountCityNo;//开户市编号
    private String accountCityName;//开户市名称

    public String getShoupaiType() {
		return shoupaiType;
	}

	public void setShoupaiType(String shoupaiType) {
		this.shoupaiType = shoupaiType;
	}

	public String getAccountProvinceNo() {
		return accountProvinceNo;
	}

	public void setAccountProvinceNo(String accountProvinceNo) {
		this.accountProvinceNo = accountProvinceNo;
	}

	public String getAccountProvinceName() {
		return accountProvinceName;
	}

	public void setAccountProvinceName(String accountProvinceName) {
		this.accountProvinceName = accountProvinceName;
	}

	public String getAccountCityNo() {
		return accountCityNo;
	}

	public void setAccountCityNo(String accountCityNo) {
		this.accountCityNo = accountCityNo;
	}

	public String getAccountCityName() {
		return accountCityName;
	}

	public void setAccountCityName(String accountCityName) {
		this.accountCityName = accountCityName;
	}

	public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getAccountProject() {
        return accountProject;
    }

    public void setAccountProject(String accountProject) {
        this.accountProject = accountProject;
    }

    public String getAccountProjectNo() {
        return accountProjectNo;
    }

    public void setAccountProjectNo(String accountProjectNo) {
        this.accountProjectNo = accountProjectNo;
    }

    public String getOaFileIdNotice() {
		return oaFileIdNotice;
	}

	public void setOaFileIdNotice(String oaFileIdNotice) {
		this.oaFileIdNotice = oaFileIdNotice;
	}

	/**
     * @return  the 【contractChangeId】
     */
    public Integer getContractChangeId() {
    
        return contractChangeId;
    }

    /**
     * @param contractChangeId the 【contractChangeId】 to set
     */
    public void setContractChangeId(Integer contractChangeId) {
    
        this.contractChangeId = contractChangeId;
    }

    //提交OA状态
    private Integer submitOAStatus;

    public Integer getSubmitOAStatus() {
        return submitOAStatus;
    }

    public void setSubmitOAStatus(Integer submitOAStatus) {
        this.submitOAStatus = submitOAStatus;
    }

    public String getAcCityNo() {
		return acCityNo;
	}

	public void setAcCityNo(String acCityNo) {
		this.acCityNo = acCityNo;
	}

	public Integer getCenterId() {
		return centerId;
	}

	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}

	public Integer getAgreementType() {
		return agreementType;
	}

	public Integer getOriginalId() {
		return originalId;
	}

	public void setOriginalId(Integer originalId) {
		this.originalId = originalId;
	}

	public void setAgreementType(Integer agreementType) {
		this.agreementType = agreementType;
	}

	public Integer getOriginalContractdistinction() {
		return originalContractdistinction;
	}

	public void setOriginalContractdistinction(Integer originalContractdistinction) {
		this.originalContractdistinction = originalContractdistinction;
	}

	public String getOriginalContractNo() {
		return originalContractNo;
	}

	public void setOriginalContractNo(String originalContractNo) {
		this.originalContractNo = originalContractNo;
	}
	//Add 2017/04/05 cning end<-------
    public String getIsChangedStr() {
		return isChangedStr;
	}

	public void setIsChangedStr(String isChangedStr) {
		this.isChangedStr = isChangedStr;
	}
    public Boolean getIsChanged() {
		return isChanged;
	}

	public void setIsChanged(Boolean isChanged) {
		this.isChanged = isChanged;
	}
    public String getPfmcAtbtDepmtName() {
		return pfmcAtbtDepmtName;
	}

	public void setPfmcAtbtDepmtName(String pfmcAtbtDepmtName) {
		this.pfmcAtbtDepmtName = pfmcAtbtDepmtName;
	}

    public String getPfmcAtbtDepmt() {
		return pfmcAtbtDepmt;
	}

	public void setPfmcAtbtDepmt(String pfmcAtbtDepmt) {
		this.pfmcAtbtDepmt = pfmcAtbtDepmt;
	}

	public Date getDateUpdate()
    {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate)
    {
        this.dateUpdate = dateUpdate;
    }

    public String getContractTypeName()
    {
        return contractTypeName;
    }

    public void setContractTypeName(String contractTypeName)
    {
        this.contractTypeName = contractTypeName;
    }
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public String getContractNo()
    {
        return contractNo;
    }
    
    public void setContractNo(String contractNo)
    {
        this.contractNo = contractNo;
    }
    
    public Integer getContractType()
    {
        return contractType;
    }
    
    public void setContractType(Integer contractType)
    {
        this.contractType = contractType;
    }
    
    public Integer getCompanyId()
    {
        return companyId;
    }
    
    public void setCompanyId(Integer companyId)
    {
        this.companyId = companyId;
    }
    
    public Integer getUserCreate()
    {
        return userCreate;
    }
    
    public void setUserCreate(Integer userCreate)
    {
        this.userCreate = userCreate;
    }
    
    public String getPartyB()
    {
        return partyB;
    }
    
    public void setPartyB(String partyB)
    {
        this.partyB = partyB;
    }
    
    public String getPartyBAddress()
    {
        return partyBAddress;
    }
    
    public void setPartyBAddress(String partyBAddress)
    {
        this.partyBAddress = partyBAddress;
    }
    
    public String getLealPerson()
    {
        return lealPerson;
    }
    
    public void setLealPerson(String lealPerson)
    {
        this.lealPerson = lealPerson;
    }
    
    public Date getDateLifeStart()
    {
        return dateLifeStart;
    }
    
    public void setDateLifeStart(Date dateLifeStart)
    {
        this.dateLifeStart = dateLifeStart;
    }
    
    public Date getDateLifeEnd()
    {
        return dateLifeEnd;
    }
    
    public void setDateLifeEnd(Date dateLifeEnd)
    {
        this.dateLifeEnd = dateLifeEnd;
    }
    
    public Date getDateCreate()
    {
        return dateCreate;
    }
    
    public void setDateCreate(Date dateCreate)
    {
        this.dateCreate = dateCreate;
    }
    
    public BigDecimal getDepositFee()
    {
        return depositFee;
    }
    
    public void setDepositFee(BigDecimal depositFee)
    {
        this.depositFee = depositFee;
    }
    
    public BigDecimal getPenaltyFee()
    {
        return penaltyFee;
    }
    
    public void setPenaltyFee(BigDecimal penaltyFee)
    {
        this.penaltyFee = penaltyFee;
    }
    
    public String getRecipients()
    {
        return recipients;
    }
    
    public void setRecipients(String recipients)
    {
        this.recipients = recipients;
    }
    
    public String getRecipientsAddress()
    {
        return recipientsAddress;
    }
    
    public void setRecipientsAddress(String recipientsAddress)
    {
        this.recipientsAddress = recipientsAddress;
    }
    
    public String getRemark()
    {
        return remark;
    }
    
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    
    public String getCompanyBankNo()
    {
        return companyBankNo;
    }
    
    public void setCompanyBankNo(String companyBankNo)
    {
        this.companyBankNo = companyBankNo;
    }
    
    public String getAgreementNo()
    {
        return agreementNo;
    }
    
    public void setAgreementNo(String agreementNo)
    {
        this.agreementNo = agreementNo;
    }
    
    public String getRegistrId()
    {
        return registrId;
    }
    
    public void setRegistrId(String registrId)
    {
        this.registrId = registrId;
    }
    
    public Integer getContractStatus()
    {
        return contractStatus;
    }
    
    public void setContractStatus(Integer contractStatus)
    {
        this.contractStatus = contractStatus;
    }
    
    public String getAuthRepresentative()
    {
        return authRepresentative;
    }
    
    public void setAuthRepresentative(String authRepresentative)
    {
        this.authRepresentative = authRepresentative;
    }
    
    public BigDecimal getTotleDepositFee()
    {
        return totleDepositFee;
    }
    
    public void setTotleDepositFee(BigDecimal totleDepositFee)
    {
        this.totleDepositFee = totleDepositFee;
    }
    
    public Integer getStoreNum()
    {
        return storeNum;
    }
    
    public void setStoreNum(Integer storeNum)
    {
        this.storeNum = storeNum;
    }
    
    public String getCityNo()
    {
        return cityNo;
    }
    
    public void setCityNo(String cityNo)
    {
        this.cityNo = cityNo;
    }
    
    public String getDistrictNo()
    {
        return districtNo;
    }
    
    public void setDistrictNo(String districtNo)
    {
        this.districtNo = districtNo;
    }
    
    public String getAreaNo()
    {
        return areaNo;
    }
    
    public void setAreaNo(String areaNo)
    {
        this.areaNo = areaNo;
    }
    
    public String getRecipientsAddressDetail()
    {
        return recipientsAddressDetail;
    }
    
    public void setRecipientsAddressDetail(String recipientsAddressDetail)
    {
        this.recipientsAddressDetail = recipientsAddressDetail;
    }
    
    public String getPartyBcityNo()
    {
        return partyBcityNo;
    }
    
    public void setPartyBcityNo(String partyBcityNo)
    {
        this.partyBcityNo = partyBcityNo;
    }
    
    public String getPartyBdistrictNo()
    {
        return partyBdistrictNo;
    }
    
    public void setPartyBdistrictNo(String partyBdistrictNo)
    {
        this.partyBdistrictNo = partyBdistrictNo;
    }
    
    public String getPartyBareaNo()
    {
        return partyBareaNo;
    }
    
    public void setPartyBareaNo(String partyBareaNo)
    {
        this.partyBareaNo = partyBareaNo;
    }
    
    public String getPartyAddressDetail()
    {
        return partyAddressDetail;
    }
    
    public void setPartyAddressDetail(String partyAddressDetail)
    {
        this.partyAddressDetail = partyAddressDetail;
    }
    
    public String getBankAccount()
    {
        return bankAccount;
    }
    
    public void setBankAccount(String bankAccount)
    {
        this.bankAccount = bankAccount;
    }
    
    public String getAccountName()
    {
        return accountName;
    }
    
    public void setAccountName(String accountName)
    {
        this.accountName = accountName;
    }
    
    public String getContractStatusName()
    {
        return contractStatusName;
    }
    
    public void setContractStatusName(String contractStatusName)
    {
        this.contractStatusName = contractStatusName;
    }
    
    public String getExpandingPersonnel()
    {
        return expandingPersonnel;
    }
    
    public void setExpandingPersonnel(String expandingPersonnel)
    {
        this.expandingPersonnel = expandingPersonnel;
    }
    
    public String getActualOperationCityNo()
    {
        return actualOperationCityNo;
    }
    
    public void setActualOperationCityNo(String actualOperationCityNo)
    {
        this.actualOperationCityNo = actualOperationCityNo;
    }
    
    public String getActualOperationDistrictNo()
    {
        return actualOperationDistrictNo;
    }
    
    public void setActualOperationDistrictNo(String actualOperationDistrictNo)
    {
        this.actualOperationDistrictNo = actualOperationDistrictNo;
    }
    
    public String getActualOperationAreaNo()
    {
        return actualOperationAreaNo;
    }
    
    public void setActualOperationAreaNo(String actualOperationAreaNo)
    {
        this.actualOperationAreaNo = actualOperationAreaNo;
    }
    
    public String getActualOperationAddress()
    {
        return actualOperationAddress;
    }
    
    public void setActualOperationAddress(String actualOperationAddress)
    {
        this.actualOperationAddress = actualOperationAddress;
    }
    
    public String getFlowId()
    {
        return flowId;
    }
    
    public void setFlowId(String flowId)
    {
        this.flowId = flowId;
    }
    
    public String getPartyBCityName()
    {
        return partyBCityName;
    }
    
    public void setPartyBCityName(String partyBCityName)
    {
        this.partyBCityName = partyBCityName;
    }
    
    public String getPartyBDistrictName()
    {
        return partyBDistrictName;
    }
    
    public void setPartyBDistrictName(String partyBDistrictName)
    {
        this.partyBDistrictName = partyBDistrictName;
    }
    
    public String getPartyBAreaName()
    {
        return partyBAreaName;
    }
    
    public void setPartyBAreaName(String partyBAreaName)
    {
        this.partyBAreaName = partyBAreaName;
    }
    
    public String getOaFileIdBusiness()
    {
        return oaFileIdBusiness;
    }
    
    public void setOaFileIdBusiness(String oaFileIdBusiness)
    {
        this.oaFileIdBusiness = oaFileIdBusiness;
    }
    
    public String getOaFileIdCard()
    {
        return oaFileIdCard;
    }
    
    public void setOaFileIdCard(String oaFileIdCard)
    {
        this.oaFileIdCard = oaFileIdCard;
    }
    
    public String getOaFileIdPhoto()
    {
        return oaFileIdPhoto;
    }
    
    public void setOaFileIdPhoto(String oaFileIdPhoto)
    {
        this.oaFileIdPhoto = oaFileIdPhoto;
    }
    
    public String getOaFileIdStore()
    {
        return oaFileIdStore;
    }
    
    public void setOaFileIdStore(String oaFileIdStore)
    {
        this.oaFileIdStore = oaFileIdStore;
    }
    
    public String getOaFileIdInstall()
    {
        return oaFileIdInstall;
    }
    
    public void setOaFileIdInstall(String oaFileIdInstall)
    {
        this.oaFileIdInstall = oaFileIdInstall;
    }
    
    public String getOaFileIdOther()
    {
        return oaFileIdOther;
    }
    
    public void setOaFileIdOther(String oaFileIdOther)
    {
        this.oaFileIdOther = oaFileIdOther;
    }
    
    public String getAgentContact()
    {
        return agentContact;
    }
    
    public void setAgentContact(String agentContact)
    {
        this.agentContact = agentContact;
    }
    
    public String getExpandingPersonnelId()
    {
        return expandingPersonnelId;
    }
    
    public void setExpandingPersonnelId(String expandingPersonnelId)
    {
        this.expandingPersonnelId = expandingPersonnelId;
    }
    
    public String getCoopType()
    {
        return coopType;
    }
    
    public void setCoopType(String coopType)
    {
        this.coopType = coopType;
    }

    public Date getPerformDate()
    {
        return performDate;
    }

    public void setPerformDate(Date performDate)
    {
        this.performDate = performDate;
    }

	public String getOaApproveType() {
		return oaApproveType;
	}

	public void setOaApproveType(String oaApproveType) {
		this.oaApproveType = oaApproveType;
	}

	public String getOaApproveTypeName() {
		return oaApproveTypeName;
	}

	public void setOaApproveTypeName(String oaApproveTypeName) {
		this.oaApproveTypeName = oaApproveTypeName;
	}

	public Integer getContractB2A() {
		return contractB2A;
	}

	public void setContractB2A(Integer contractB2A) {
		this.contractB2A = contractB2A;
	}

	public String getContractB2AName() {
		return contractB2AName;
	}

	public void setContractB2AName(String contractB2AName) {
		this.contractB2AName = contractB2AName;
	}
	private String companyCityName;

	public String getCompanyCityName() {
		return companyCityName;
	}

	public void setCompanyCityName(String companyCityName) {
		this.companyCityName = companyCityName;
	}

	public String getOaFileIdComplement() {
		return oaFileIdComplement;
	}

	public void setOaFileIdComplement(String oaFileIdComplement) {
		this.oaFileIdComplement = oaFileIdComplement;
	}

    public Integer getContractVersion() {
        return contractVersion;
    }

    public void setContractVersion(Integer contractVersion) {
        this.contractVersion = contractVersion;
    }
    private String companyLealPerson;

	public String getCompanyLealPerson() {
		return companyLealPerson;
	}

	public void setCompanyLealPerson(String companyLealPerson) {
		this.companyLealPerson = companyLealPerson;
	}
	private String contractCityName;

	public String getContractCityName() {
		return contractCityName;
	}

	public void setContractCityName(String contractCityName) {
		this.contractCityName = contractCityName;
	}
	private String companyNo;
	private String userName;
	private String userCode;
	public String getCompanyNo() {
		return companyNo;
	}
	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	
}
