package cn.com.eju.deal.dto.contract;

import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.dto.store.StoreDto;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 合同变更信息 Dto
 * 
 * @author sunmm
 * @date 2016年8月4日 下午2:46:31
 */
public class ContractChangeDto implements Serializable
{
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 合同终止ID
     */
    private Integer id;
    
    /**
     * 合同终止No
     */
    private String contractStopNo;
    
    /**
     * 合同ID
     */
    private Integer contractId;
    
    /**
     * 变更类型
     */
    private Integer changeType;
    
    /**
     * 终止类型
     */
    private Integer stopType;
    
    /**
     * 终止原因
     */
    private String stopReason;
    
    /**
     * 三方装修合同情况
     */
    private Integer decorateCNTSituate;
    
    /**
     * 装修情况
     */
    private Integer decorateSituate;
    
    /**
     * 装修公司
     */
    private String decorateCompany;
    
    /**
     * 翻牌模式
     */
    private Integer flopMode;
    
    /**
     * 装修费用总金额
     */
    private BigDecimal decorateAmount;
    
    /**
     * 已支付金额
     */
    private BigDecimal payAmount;
    
    /**
     * 合作协议书是否一并解除
     */
    private Integer isReleaseFlag;
    
    /**
     * 合作终止时间
     */
    private Date stopDate;
    
    /**
     * 是否B转A
     */
    private Integer isaTob;
    
    /**
     * 终止方案简述
     */
    private String stopDescribe;
    
    /**
     * 备注
     */
    private String remarks;
    
    /**
     * 流程结束日期
     */
    private Date flowEndDate;
    
    /**
     * 创建人
     */
    private Integer userIdCreate;
    
    /**
     * 是否删除
     */
    private Boolean delFlag;
    
    /**
     * 创建日期
     */
    private Date dateCreate;
    
    /**
     * 更新人
     */
    private Integer updateCreate;
    
    /**
     * 更新日期
     */
    private Date updateDate;
    
    /**
     * 已收保证金额数
     */
    private BigDecimal receivedAmount;
    
    /**
     * 审核状态(0:未审核 1:审核中 2:审核通过 3:审核失败)
     */
    private Integer approveState;
    
    /**
    * 变更合同中终止门店数量
    */
    private Integer storeNumber;
    
    /**
    * 创建人名称
    */
    private String userCreateName;
    
    /**
    * 合同变更类型名称名称
    */
    private String changeTypeName;
    
    /**
     * 审核状态名称(0:未审核 1:审核中 2:审核通过 3:审核失败)
     */
    private String approveStateName;
    
    /**
     * 流程Id
     */
    private String flowId;

    private String accountProject;
    private String accountProjectNo;

    private Integer cancelFlag;

	public Integer getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(Integer cancelFlag) {
		this.cancelFlag = cancelFlag;
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
	// -----------扩展字段 start---------//
    
    private List<ContractFileDto> stopContractFileList;//终止合作协议书
    
    private List<ContractFileDto> surrenderFileList;//三方解约协议
    
    private List<ContractFileDto> receiptFileList; //保证金收据
    
    private List<ContractFileDto> returnProveFileList;//装修款退还证明
    
    private List<ContractFileDto> cancellateFileList;//注销证明
    
    private List<ContractFileDto> photosFileList;//照片
    
    private List<ContractFileDto> cooperateFileList;//新A合作协议书
    
    private List<ContractFileDto> oneStopFileList;//一事一议终止方案
    
    private List<ContractFileDto> othersFileList;//合同变更其他照片
    
    private String fileRecordMainIds;//页面图片合集
    
    private String contractChangePicIds;//页面图片PicIds合集
    
    /**
     * 变更类型(文字描述)
     */
    private String changeTypeStr;
    
    /**
     * 终止类型(文字描述)
     */
    private String stopTypeStr;
    
    /**
     * 三方装修合同情况(文字描述)
     */
    private String decorateCNTSituateStr;
    
    /**
     * 装修情况(文字描述)
     */
    private String decorateSituateStr;
    
    /**
     * 翻牌模式(文字描述)
     */
    private String flopModeStr;
    
    /**
     * 合作协议书是否一并解除(文字描述)
     */
    private String isReleaseFlagStr;
    
    /**
     * 是否B转A(文字描述)
     */
    private String isaTobStr;
    
    /**
     * 上传至OA返回图片Id(文字描述)
     */
    private String fileCode;
    
    /**
     * 文件名称
     */
    private String fileName;
    
    /**
     * 上传的文件类型
     */
    private String fileTypeCode;
    
    /**
     * 创建人
     */
    private Integer userCreate;
    
    /**
     * OAattachment图片Id
     */
    private Integer contractChangePicId;
    
    /**
     * 门店ID
     */
    private Integer storeId;
    
//Add By QJP 2015/05/25 合规性 start
    /**
	 * 公司经营范围变更
	 */
	private Integer changeCompanyName;
	
	/**
	 * 公司注册地址变更
	 */
	private Integer changeCompanyAdress;
	
	/**
	 * 门店地址变更
	 */
	private Integer changeStoreAdress;
	
	
	/**
	 * 签约主体变更
	 */
	private Integer changeCompany;
	
	/**
	 * 变更后公司注册地址城市
	 */
	private String companyCity;
	
	/**
	 * 变更后公司注册地址区域
	 */
	private String companyDistrict;
	
	/**
	 * 变更后公司注册地址
	 */
	private String companyAdresss;
	
	/**
	 * 变更后门店地址城市
	 */
	private String storeCity;
	
	/**
	 * 变更后门店地址区域
	 */
	private String storeDistrict;
	
	/**
	 * 变更后门店注册地址
	 */
	private String storeAdresss;
	
	
	/**
	 * 变更公司城市所在名称
	 */
	private String changeCompanyCityName;
	
	/**
	 * 变更公司城市所在区域
	 */
	private String changeCompanyDistrictName;
	
	/**
	 * 变更门店城市所在名称
	 */
	private String changeStoreCityName;
	
	/**
	 * 变更门店城市所在区域
	 */
	private String changeStoreDistrictName;
	
//Add By QJP 2015/06/23 合规性主题变更新增字段 start
	/**
	 * 新公司名称
	 */
	private String newCompanyName;	

	/**
	 * 新公司法定代表人
	 */
	private String newLegalPerson;
	
	/**
	 * 新公司注册城市
	 */
	private String newCompanyAddressCityNo;
	
	/**
	 * 新公司注册区域
	 */
	private String newCompanyAddressDistrictNo;
	
	/**
	 * 新公司注册具体地址
	 */
	private String newCompanyAddress;
	
	/**
	 * 新合作协议书编号
	 */
	private String newAgreementNo;
	
	/**
	 * 新合同合作生效日期
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date newDateLifeStart;
	
	/**
	 * 新合同合作终止日期
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date newDateLifeEnd;
	
	 
	/**
	 * 新公司注册城市名称
	 */
	private String newCompanyAddressCityName;
	
	/**
	 * 新公司注册区域
	 */
	private String newCompanyAddressDistrictName;
//Add By QJP 2015/06/23 合规性 end
	
	private List<ContractFileDto> changeSupplementFileList; //变更补充条款
    private List<ContractFileDto> newBusinessLicenseFileList; //新营业执照
    private List<ContractFileDto> informationPublicityFileList; //国家企业信用信息公示系统网站截图
    private List<ContractFileDto> confirmationSheetFileList; //房友确认单
    private List<ContractFileDto> storePhotosFileList; //门店照片
   
    private List<ContractFileDto> transferRightsFileList;  //权利义务转让三方协议书(主体变更)
    private List<ContractFileDto> newSigningFileList;  //新签合作协议书及材料(主体变更)
    private List<ContractFileDto> changeOthersFileList;//合同乙转甲其他照片
    private List<ContractFileDto> mainInformationPublicityFileList; //国家企业信用信息公示系统网站截图(主体变更)
    private List<ContractFileDto> mainChangeOthersFileList; //合同乙转甲其他照片(主体变更)
    private List<ContractFileDto> fileRecordMainNotice;
    
    private Integer oldCompanyId;
    private Integer newCompanyId;
    private Integer updateCompanyName;
    private String newUpdateCompanyName;
    //更名前的公司
    private String oldUpdateCompanyName;
    //原门店地址
    private String oldStoreAddressDetail;
    
    //合作协议书类型
    private Integer agreementType;
    //新合作协议书编号
    private String authRepresentative;
    //联系方式
    private String agentContact;
    //保证金金额
    private BigDecimal totleDepositFee;
    //违约保证金金额
    private BigDecimal penaltyFee;
    //客户公司账号
    private String companyBankNo;
    //开户银行
    private String bankAccount;
    //开户名
    private String accountName;
    //甲方收件人
    private String recipients;
    //甲方收件地址
    private String cityNo;
    private String districtNo;
    private String recipientsAddress;

	//提交OA状态
	private Integer submitOAStatus;
	
	//公司营业执照号
	private String registrId;
  /**
	 * 保证金退还金额
	 */
	private BigDecimal depositBackMoney;
	//保证金处理方式
	private Integer depositBalance;

	//三方变更类型
	private Integer threePartChangeType;

	//合作模式
	private Integer contractType;

	//门店列表
	private List<StoreDto> storeList;
	
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

	/**
     * @return  the 【registrId】
     */
    public String getRegistrId() {
    
        return registrId;
    }

    /**
     * @param registrId the 【registrId】 to set
     */
    public void setRegistrId(String registrId) {
    
        this.registrId = registrId;
    }

    public List<ContractFileDto> getFileRecordMainNotice() {
		return fileRecordMainNotice;
	}

	public void setFileRecordMainNotice(List<ContractFileDto> fileRecordMainNotice) {
		this.fileRecordMainNotice = fileRecordMainNotice;
	}

	public Integer getSubmitOAStatus() {
		return submitOAStatus;
	}

	public void setSubmitOAStatus(Integer submitOAStatus) {
		this.submitOAStatus = submitOAStatus;
	}

	public String getNewCompanyAddressCityName() {
		return newCompanyAddressCityName;
	}

	public void setNewCompanyAddressCityName(String newCompanyAddressCityName) {
		this.newCompanyAddressCityName = newCompanyAddressCityName;
	}

	public String getNewCompanyAddressDistrictName() {
		return newCompanyAddressDistrictName;
	}

	public void setNewCompanyAddressDistrictName(String newCompanyAddressDistrictName) {
		this.newCompanyAddressDistrictName = newCompanyAddressDistrictName;
	}
    
    public String getNewCompanyName() {
		return newCompanyName;
	}

	public void setNewCompanyName(String newCompanyName) {
		this.newCompanyName = newCompanyName;
	}

	public String getNewLegalPerson() {
		return newLegalPerson;
	}

	public void setNewLegalPerson(String newLegalPerson) {
		this.newLegalPerson = newLegalPerson;
	}

	public String getNewCompanyAddressCityNo() {
		return newCompanyAddressCityNo;
	}

	public void setNewCompanyAddressCityNo(String newCompanyAddressCityNo) {
		this.newCompanyAddressCityNo = newCompanyAddressCityNo;
	}

	public String getNewCompanyAddressDistrictNo() {
		return newCompanyAddressDistrictNo;
	}

	public void setNewCompanyAddressDistrictNo(String newCompanyAddressDistrictNo) {
		this.newCompanyAddressDistrictNo = newCompanyAddressDistrictNo;
	}

	public String getNewCompanyAddress() {
		return newCompanyAddress;
	}

	public void setNewCompanyAddress(String newCompanyAddress) {
		this.newCompanyAddress = newCompanyAddress;
	}

	public String getNewAgreementNo() {
		return newAgreementNo;
	}

	public void setNewAgreementNo(String newAgreementNo) {
		this.newAgreementNo = newAgreementNo;
	}

	public Date getNewDateLifeStart() {
		return newDateLifeStart;
	}

	public void setNewDateLifeStart(Date newDateLifeStart) {
		this.newDateLifeStart = newDateLifeStart;
	}

	public Date getNewDateLifeEnd() {
		return newDateLifeEnd;
	}

	public void setNewDateLifeEnd(Date newDateLifeEnd) {
		this.newDateLifeEnd = newDateLifeEnd;
	}
    
    public List<ContractFileDto> getChangeOthersFileList() {
		return changeOthersFileList;
	}

	public void setChangeOthersFileList(List<ContractFileDto> changeOthersFileList) {
		this.changeOthersFileList = changeOthersFileList;
	}

	public List<ContractFileDto> getMainInformationPublicityFileList() {
		return mainInformationPublicityFileList;
	}

	public void setMainInformationPublicityFileList(List<ContractFileDto> mainInformationPublicityFileList) {
		this.mainInformationPublicityFileList = mainInformationPublicityFileList;
	}

	public List<ContractFileDto> getMainChangeOthersFileList() {
		return mainChangeOthersFileList;
	}

	public void setMainChangeOthersFileList(List<ContractFileDto> mainChangeOthersFileList) {
		this.mainChangeOthersFileList = mainChangeOthersFileList;
	}

	public String getChangeCompanyCityName() {
		return changeCompanyCityName;
	}

	public void setChangeCompanyCityName(String changeCompanyCityName) {
		this.changeCompanyCityName = changeCompanyCityName;
	}

	public String getChangeCompanyDistrictName() {
		return changeCompanyDistrictName;
	}

	public void setChangeCompanyDistrictName(String changeCompanyDistrictName) {
		this.changeCompanyDistrictName = changeCompanyDistrictName;
	}

	public String getChangeStoreCityName() {
		return changeStoreCityName;
	}

	public void setChangeStoreCityName(String changeStoreCityName) {
		this.changeStoreCityName = changeStoreCityName;
	}

	public String getChangeStoreDistrictName() {
		return changeStoreDistrictName;
	}

	public void setChangeStoreDistrictName(String changeStoreDistrictName) {
		this.changeStoreDistrictName = changeStoreDistrictName;
	}
    
    
	public Integer getChangeCompanyName() {
		return changeCompanyName;
	}

	public void setChangeCompanyName(Integer changeCompanyName) {
		this.changeCompanyName = changeCompanyName;
	}

	public Integer getChangeCompanyAdress() {
		return changeCompanyAdress;
	}

	public void setChangeCompanyAdress(Integer changeCompanyAdress) {
		this.changeCompanyAdress = changeCompanyAdress;
	}

	public Integer getChangeStoreAdress() {
		return changeStoreAdress;
	}

	public void setChangeStoreAdress(Integer changeStoreAdress) {
		this.changeStoreAdress = changeStoreAdress;
	}

	

	public Integer getChangeCompany() {
		return changeCompany;
	}

	public void setChangeCompany(Integer changeCompany) {
		this.changeCompany = changeCompany;
	}

	public String getCompanyCity() {
		return companyCity;
	}

	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}

	public String getCompanyDistrict() {
		return companyDistrict;
	}

	public void setCompanyDistrict(String companyDistrict) {
		this.companyDistrict = companyDistrict;
	}

	public String getCompanyAdresss() {
		return companyAdresss;
	}

	public void setCompanyAdresss(String companyAdresss) {
		this.companyAdresss = companyAdresss;
	}

	public String getStoreCity() {
		return storeCity;
	}

	public void setStoreCity(String storeCity) {
		this.storeCity = storeCity;
	}

	public String getStoreDistrict() {
		return storeDistrict;
	}

	public void setStoreDistrict(String storeDistrict) {
		this.storeDistrict = storeDistrict;
	}

	public String getStoreAdresss() {
		return storeAdresss;
	}

	public void setStoreAdresss(String storeAdresss) {
		this.storeAdresss = storeAdresss;
	}

	public List<ContractFileDto> getChangeSupplementFileList() {
		return changeSupplementFileList;
	}

	public void setChangeSupplementFileList(List<ContractFileDto> changeSupplementFileList) {
		this.changeSupplementFileList = changeSupplementFileList;
	}

	public List<ContractFileDto> getNewBusinessLicenseFileList() {
		return newBusinessLicenseFileList;
	}

	public void setNewBusinessLicenseFileList(List<ContractFileDto> newBusinessLicenseFileList) {
		this.newBusinessLicenseFileList = newBusinessLicenseFileList;
	}

	public List<ContractFileDto> getInformationPublicityFileList() {
		return informationPublicityFileList;
	}

	public void setInformationPublicityFileList(List<ContractFileDto> informationPublicityFileList) {
		this.informationPublicityFileList = informationPublicityFileList;
	}

	public List<ContractFileDto> getConfirmationSheetFileList() {
		return confirmationSheetFileList;
	}

	public void setConfirmationSheetFileList(List<ContractFileDto> confirmationSheetFileList) {
		this.confirmationSheetFileList = confirmationSheetFileList;
	}

	public List<ContractFileDto> getStorePhotosFileList() {
		return storePhotosFileList;
	}

	public void setStorePhotosFileList(List<ContractFileDto> storePhotosFileList) {
		this.storePhotosFileList = storePhotosFileList;
	}

	public List<ContractFileDto> getTransferRightsFileList() {
		return transferRightsFileList;
	}

	public void setTransferRightsFileList(List<ContractFileDto> transferRightsFileList) {
		this.transferRightsFileList = transferRightsFileList;
	}

	public List<ContractFileDto> getNewSigningFileList() {
		return newSigningFileList;
	}

	public void setNewSigningFileList(List<ContractFileDto> newSigningFileList) {
		this.newSigningFileList = newSigningFileList;
	}
//Add By QJP 2015/05/25 合规性 end
		
	
    
    
    // -----------扩展字段 end---------//
    
    public Integer getContractChangePicId()
    {
        return contractChangePicId;
    }
    
    public void setContractChangePicId(Integer contractChangePicId)
    {
        this.contractChangePicId = contractChangePicId;
    }
    
    public String getFileCode()
    {
        return fileCode;
    }
    
    public void setFileCode(String fileCode)
    {
        this.fileCode = fileCode;
    }
    
    public String getFileName()
    {
        return fileName;
    }
    
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
    
    public String getFileTypeCode()
    {
        return fileTypeCode;
    }
    
    public void setFileTypeCode(String fileTypeCode)
    {
        this.fileTypeCode = fileTypeCode;
    }
    
    public Integer getUserCreate()
    {
        return userCreate;
    }
    
    public void setUserCreate(Integer userCreate)
    {
        this.userCreate = userCreate;
    }
    
    /**
     * 获取变更类型(文字描述)
     * 
     * @return changeTypeStr 变更类型(文字描述)
     */
    public String getChangeTypeStr()
    {
        return SystemParam.getDicValueByDicCode(this.changeType + "");
    }
    
    /**
     * 设置变更类型(文字描述)
     * 
     * @param changeTypeStr
     *            变更类型(文字描述)
     */
    public void setChangeTypeStr(String changeTypeStr)
    {
        this.changeTypeStr = changeTypeStr;
    }
    
    /**
     * 获取终止类型(文字描述)
     * 
     * @return 终止类型(文字描述)
     */
    public String getStopTypeStr()
    {
        return SystemParam.getDicValueByDicCode(this.stopType + "");
    }
    
    /**
     * 设置终止类型(文字描述)
     * 
     * @param stopTypeStr
     *            终止类型(文字描述)
     */
    public void setStopTypeStr(String stopTypeStr)
    {
        this.stopTypeStr = stopTypeStr;
    }
    
    /**
     * 获取三方装修合同情况(文字描述)
     * 
     * @return 三方装修合同情况(文字描述)
     */
    public String getDecorateCNTSituateStr()
    {
        return SystemParam.getDicValueByDicCode(this.decorateCNTSituate + "");
    }
    
    /**
     * 设置三方装修合同情况(文字描述)
     * 
     * @param decorateCNTSituateStr
     *            三方装修合同情况(文字描述)
     */
    public void setDecorateCNTSituateStr(String decorateCNTSituateStr)
    {
        this.decorateCNTSituateStr = decorateCNTSituateStr;
    }
    
    /**
     * 获取装修情况(文字描述)
     * 
     * @return decorateSituateStr 装修情况(文字描述)
     */
    public String getDecorateSituateStr()
    {
        return SystemParam.getDicValueByDicCode(this.decorateSituate + "");
    }
    
    /**
     * 设置装修情况(文字描述)
     * 
     * @param decorateSituateStr
     *            装修情况(文字描述)
     */
    public void setDecorateSituateStr(String decorateSituateStr)
    {
        this.decorateSituateStr = decorateSituateStr;
    }
    
    /**
     * 获取翻牌模式(文字描述)
     * 
     * @return 翻牌模式(文字描述)
     */
    public String getFlopModeStr()
    {
        return SystemParam.getDicValueByDicCode(this.flopMode + "");
    }
    
    /**
     * 设置翻牌模式(文字描述)
     * 
     * @param flopModeStr
     *            翻牌模式(文字描述)
     */
    public void setFlopModeStr(String flopModeStr)
    {
        this.flopModeStr = flopModeStr;
    }
    
    /**
     * 获取合作协议书是否一并解除(文字描述)
     * 
     * @return isReleaseFlagStr 合作协议书是否一并解除(文字描述)
     */
    public String getIsReleaseFlagStr()
    {
        return SystemParam.getDicValueByDicCode(this.isReleaseFlag + "");
    }
    
    /**
     * 设置合作协议书是否一并解除(文字描述)
     * 
     * @param isReleaseFlagStr
     *            合作协议书是否一并解除(文字描述)
     */
    public void setIsReleaseFlagStr(String isReleaseFlagStr)
    {
        this.isReleaseFlagStr = isReleaseFlagStr;
    }
    
    /**
         * 获取是否B转A(文字描述)
     * 
         * @return isaTobStr 是否B转A(文字描述)
     */
    public String getIsaTobStr()
    {
        return isaTobStr;
    }
    
    /**
         * 设置是否B转A(文字描述)
     * 
         * @param isaTobStr
         *            是否B转A(文字描述)
     */
    public void setIsaTobStr(String isaTobStr)
    {
        this.isaTobStr = isaTobStr;
    }
    
    /**
     * 审核状态名称(0:未审核 1:审核中 2:审核通过 3:审核失败)
     */
    public String getApproveStateName()
    {
        return approveStateName;
    }
    
    /**
     * 审核状态名称(0:未审核 1:审核中 2:审核通过 3:审核失败)
     */
    public void setApproveStateName(String approveStateName)
    {
        this.approveStateName = approveStateName;
    }
    
    /**
     * 合同变更类型名称名称
     */
    public String getChangeTypeName()
    {
        return changeTypeName;
    }
    
    /**
     * 合同变更类型名称名称
     */
    public void setChangeTypeName(String changeTypeName)
    {
        this.changeTypeName = changeTypeName;
    }
    
    /**
    * 变更合同中终止门店数量
    */
    public Integer getStoreNumber()
    {
        return storeNumber;
    }
    
    /**
     * 变更合同中终止门店数量
     */
    public void setStoreNumber(Integer storeNumber)
    {
        this.storeNumber = storeNumber;
    }
    
    /**
    * 创建人名称
    */
    public String getUserCreateName()
    {
        return userCreateName;
    }
    
    /**
    * 创建人名称
    */
    public void setUserCreateName(String userCreateName)
    {
        this.userCreateName = userCreateName;
    }
    
    /**
     * 获取审核状态(0:未审核 1:审核中 2:审核通过 3:审核失败)
     * 
     * @return approveState 审核状态
     */
    public Integer getApproveState()
    {
        return approveState;
    }
    
    /**
     * 设置审核状态(0:未审核 1:审核中 2:审核通过 3:审核失败)
     * 
     * @param approveState
     *            审核状态
     */
    public void setApproveState(Integer approveState)
    {
        this.approveState = approveState;
    }
    
    /**
     * 获取合同终止ID
     * 
     * @return id 合同终止ID
     */
    public Integer getId()
    {
        return id;
    }
    
    /**
     * 设置合同终止ID
     * 
     * @param id
     *            合同终止ID
     */
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    /**
     * 获取合同终止No
     * 
     * @return contractStopNo 合同终止No
     */
    public String getContractStopNo()
    {
        return contractStopNo;
    }
    
    /**
     * 设置合同终止No
     * 
     * @param contractStopNo
     *            合同终止No
     */
    public void setContractStopNo(String contractStopNo)
    {
        this.contractStopNo = contractStopNo;
    }
    
    /**
     * 获取合同ID
     * 
     * @return contractId 合同No
     */
    public Integer getContractId()
    {
        return contractId;
    }
    
    /**
     * 设置合同ID
     * 
     * @param contractId
     *            合同ID
     */
    public void setContractId(Integer contractId)
    {
        this.contractId = contractId;
    }
    
    /**
     * 获取变更类型
     * 
     * @return changeType 变更类型
     */
    public Integer getChangeType()
    {
        return changeType;
    }
    
    /**
     * 设置变更类型
     * 
     * @param changeType
     *            变更类型
     */
    public void setChangeType(Integer changeType)
    {
        this.changeType = changeType;
    }
    
    /**
     * 获取终止类型
     * 
     * @return stopType 终止类型
     */
    public Integer getStopType()
    {
        return stopType;
    }
    
    /**
     * 设置终止类型
     * 
     * @param stopType
     *            终止类型
     */
    public void setStopType(Integer stopType)
    {
        this.stopType = stopType;
    }
    
    /**
     * 获取终止原因
     * 
     * @return stopReason 终止原因
     */
    public String getStopReason()
    {
        return stopReason;
    }
    
    /**
     * 设置终止原因
     * 
     * @param stopReason
     *            终止原因
     */
    public void setStopReason(String stopReason)
    {
        this.stopReason = stopReason;
    }
    
    /**
     * 获取三方装修合同情况
     * 
     * @return decorateCNTSituate 三方装修合同情况
     */
    public Integer getDecorateCNTSituate()
    {
        return decorateCNTSituate;
    }
    
    /**
     * 设置三方装修合同情况
     * 
     * @param decorateCNTSituate
     *            三方装修合同情况
     */
    public void setDecorateCNTSituate(Integer decorateCNTSituate)
    {
        this.decorateCNTSituate = decorateCNTSituate;
    }
    
    /**
     * 获取装修情况
     * 
     * @return decorateSituate 装修情况
     */
    public Integer getDecorateSituate()
    {
        return decorateSituate;
    }
    
    /**
     * 设置装修情况
     * 
     * @param decorateSituate
     *            装修情况
     */
    public void setDecorateSituate(Integer decorateSituate)
    {
        this.decorateSituate = decorateSituate;
    }
    
    /**
     * 获取装修公司
     * 
     * @return decorateCompany 装修公司
     */
    public String getDecorateCompany()
    {
        return decorateCompany;
    }
    
    /**
     * 设置装修公司
     * 
     * @param decorateCompany
     *            装修公司
     */
    public void setDecorateCompany(String decorateCompany)
    {
        this.decorateCompany = decorateCompany;
    }
    
    /**
     * 获取翻牌模式
     * 
     * @return flopMode 翻牌模式
     */
    public Integer getFlopMode()
    {
        return flopMode;
    }
    
    /**
     * 设置翻牌模式
     * 
     * @param flopMode
     *            翻牌模式
     */
    public void setFlopMode(Integer flopMode)
    {
        this.flopMode = flopMode;
    }
    
    /**
     * 获取装修费用总金额
     * 
     * @return decorateAmount 装修费用总金额
     */
    public BigDecimal getDecorateAmount()
    {
        return decorateAmount;
    }
    
    /**
     * 设置装修费用总金额
     * 
     * @param decorateAmount
     *            装修费用总金额
     */
    public void setDecorateAmount(BigDecimal decorateAmount)
    {
        this.decorateAmount = decorateAmount;
    }
    
    /**
     * 获取已支付金额
     * 
     * @return payAmount 已支付金额
     */
    public BigDecimal getPayAmount()
    {
        return payAmount;
    }
    
    /**
     * 设置已支付金额
     * 
     * @param payAmount
     *            已支付金额
     */
    public void setPayAmount(BigDecimal payAmount)
    {
        this.payAmount = payAmount;
    }
    
    /**
     * 获取合作协议书是否一并解除
     * 
     * @return isReleaseFlag 合作协议书是否一并解除
     */
    public Integer getIsReleaseFlag()
    {
        return isReleaseFlag;
    }
    
    /**
     * 设置合作协议书是否一并解除
     * 
     * @param isReleaseFlag
     *            合作协议书是否一并解除
     */
    public void setIsReleaseFlag(Integer isReleaseFlag)
    {
        this.isReleaseFlag = isReleaseFlag;
    }
    
    /**
     * 获取合作终止时间
     * 
     * @return stopDate 合作终止时间
     */
    public Date getStopDate()
    {
        return stopDate;
    }
    
    /**
     * 设置合作终止时间
     * 
     * @param stopDate
     *            合作终止时间
     */
    public void setStopDate(Date stopDate)
    {
        this.stopDate = stopDate;
    }
    
    /**
     * 获取是否B转A
     * 
     * @return isaTob 是否B转A
     */
    public Integer getIsaTob()
    {
        return isaTob;
    }
    
    /**
     * 设置是否B转A
     * 
     * @param isaTob
     *            是否B转A
     */
    public void setIsaTob(Integer isaTob)
    {
        this.isaTob = isaTob;
    }
    
    /**
     * 获取终止方案简述
     * 
     * @return stopDescribe 终止方案简述
     */
    public String getStopDescribe()
    {
        return stopDescribe;
    }
    
    /**
     * 设置终止方案简述
     * 
     * @param stopDescribe
     *            终止方案简述
     */
    public void setStopDescribe(String stopDescribe)
    {
        this.stopDescribe = stopDescribe;
    }
    
    /**
     * 获取备注
     * 
     * @return remarks 备注
     */
    public String getRemarks()
    {
        return remarks;
    }
    
    /**
     * 设置备注
     * 
     * @param remarks
     *            备注
     */
    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }
    
    /**
     * 获取流程结束日期
     * 
     * @return flowEndDate 流程结束日期
     */
    public Date getFlowEndDate()
    {
        return flowEndDate;
    }
    
    /**
     * 设置流程结束日期
     * 
     * @param flowEndDate
     *            流程结束日期
     */
    public void setFlowEndDate(Date flowEndDate)
    {
        this.flowEndDate = flowEndDate;
    }
    
    /**
     * 获取创建日期
     * 
     * @return dateCreate 创建日期
     */
    public Date getDateCreate()
    {
        return dateCreate;
    }
    
    /**
     * 设置创建日期
     * 
     * @param dateCreate
     *            创建日期
     */
    public void setDateCreate(Date dateCreate)
    {
        this.dateCreate = dateCreate;
    }
    
    /**
     * 获取创建人
     * 
     * @return userIdCreate 创建人
     */
    public Integer getUserIdCreate()
    {
        return userIdCreate;
    }
    
    /**
     * 设置创建人
     * 
     * @param userIdCreate
     *            创建人
     */
    public void setUserIdCreate(Integer userIdCreate)
    {
        this.userIdCreate = userIdCreate;
    }
    
    /**
     * 获取更新日期
     * 
     * @return updateDate 更新日期
     */
    public Date getUpdateDate()
    {
        return updateDate;
    }
    
    /**
     * 设置更新日期
     * 
     * @param updateDate
     *            更新日期
     */
    public void setUpdateDate(Date updateDate)
    {
        this.updateDate = updateDate;
    }
    
    /**
     * 获取更新人
     * 
     * @return updateCreate 更新人
     */
    public Integer getUpdateCreate()
    {
        return updateCreate;
    }
    
    /**
     * 设置更新人
     * 
     * @param updateCreate
     *            更新人
     */
    public void setUpdateCreate(Integer updateCreate)
    {
        this.updateCreate = updateCreate;
    }
    
    /**
     * 获取删除标记
     * 
     * @return delFlag 删除标记
     */
    public Boolean getDelFlag()
    {
        return delFlag;
    }
    
    /**
     * 设置删除标记
     * 
     * @param delFlag
     *            删除标记
     */
    public void setDelFlag(Boolean delFlag)
    {
        this.delFlag = delFlag;
    }
    
    /**
     * 获取已收保证金额数
     * 
     * @return receivedAmount 已收保证金额数
     */
    public BigDecimal getReceivedAmount()
    {
        return receivedAmount;
    }
    
    /**
     * 设置已收保证金额数
     * 
     * @param receivedAmount
     *            已收保证金额数
     */
    public void setReceivedAmount(BigDecimal receivedAmount)
    {
        this.receivedAmount = receivedAmount;
    }
    
    /** 
    * 获取流程ID
    * @return flowId 流程ID
    */
    public String getFlowId()
    {
        return flowId;
    }
    
    /** 
    * 设置流程ID
    * @param flowId 流程ID
    */
    public void setFlowId(String flowId)
    {
        this.flowId = flowId;
    }
    
    public List<ContractFileDto> getStopContractFileList()
    {
        return stopContractFileList;
    }
    
    public void setStopContractFileList(List<ContractFileDto> stopContractFileList)
    {
        this.stopContractFileList = stopContractFileList;
    }
    
    public List<ContractFileDto> getSurrenderFileList()
    {
        return surrenderFileList;
    }
    
    public void setSurrenderFileList(List<ContractFileDto> surrenderFileList)
    {
        this.surrenderFileList = surrenderFileList;
    }
    
    public List<ContractFileDto> getReceiptFileList()
    {
        return receiptFileList;
    }
    
    public void setReceiptFileList(List<ContractFileDto> receiptFileList)
    {
        this.receiptFileList = receiptFileList;
    }
    
    public List<ContractFileDto> getReturnProveFileList()
    {
        return returnProveFileList;
    }
    
    public void setReturnProveFileList(List<ContractFileDto> returnProveFileList)
    {
        this.returnProveFileList = returnProveFileList;
    }
    
    public List<ContractFileDto> getCancellateFileList()
    {
        return cancellateFileList;
    }
    
    public void setCancellateFileList(List<ContractFileDto> cancellateFileList)
    {
        this.cancellateFileList = cancellateFileList;
    }
    
    public List<ContractFileDto> getPhotosFileList()
    {
        return photosFileList;
    }
    
    public void setPhotosFileList(List<ContractFileDto> photosFileList)
    {
        this.photosFileList = photosFileList;
    }
    
    public List<ContractFileDto> getCooperateFileList()
    {
        return cooperateFileList;
    }
    
    public void setCooperateFileList(List<ContractFileDto> cooperateFileList)
    {
        this.cooperateFileList = cooperateFileList;
    }
    
    public List<ContractFileDto> getOneStopFileList()
    {
        return oneStopFileList;
    }
    
    public void setOneStopFileList(List<ContractFileDto> oneStopFileList)
    {
        this.oneStopFileList = oneStopFileList;
    }
    
    public List<ContractFileDto> getOthersFileList()
    {
        return othersFileList;
    }
    
    public void setOthersFileList(List<ContractFileDto> othersFileList)
    {
        this.othersFileList = othersFileList;
    }
    
    public String getFileRecordMainIds()
    {
        return fileRecordMainIds;
    }
    
    public void setFileRecordMainIds(String fileRecordMainIds)
    {
        this.fileRecordMainIds = fileRecordMainIds;
    }
    
    public String getContractChangePicIds()
    {
        return contractChangePicIds;
    }
    
    public void setContractChangePicIds(String contractChangePicIds)
    {
        this.contractChangePicIds = contractChangePicIds;
    }

	/** 
	* 获取门店ID
	* @return storeId 门店ID
	*/
	public Integer getStoreId() {
		return storeId;
	}

	/** 
	* 设置门店ID
	* @param storeId 门店ID
	*/
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

    /**
     * @return  the 【oldCompanyId】
     */
    public Integer getOldCompanyId() {
    
        return oldCompanyId;
    }

    /**
     * @param oldCompanyId the 【oldCompanyId】 to set
     */
    public void setOldCompanyId(Integer oldCompanyId) {
    
        this.oldCompanyId = oldCompanyId;
    }

    /**
     * @return  the 【newCompanyId】
     */
    public Integer getNewCompanyId() {
    
        return newCompanyId;
    }

    /**
     * @param newCompanyId the 【newCompanyId】 to set
     */
    public void setNewCompanyId(Integer newCompanyId) {
    
        this.newCompanyId = newCompanyId;
    }

    /**
     * @return  the 【updateCompanyName】
     */
    public Integer getUpdateCompanyName() {
    
        return updateCompanyName;
    }

    /**
     * @param updateCompanyName the 【updateCompanyName】 to set
     */
    public void setUpdateCompanyName(Integer updateCompanyName) {
    
        this.updateCompanyName = updateCompanyName;
    }

    /**
     * @return  the 【newUpdateCompanyName】
     */
    public String getNewUpdateCompanyName() {
    
        return newUpdateCompanyName;
    }

    /**
     * @param newUpdateCompanyName the 【newUpdateCompanyName】 to set
     */
    public void setNewUpdateCompanyName(String newUpdateCompanyName) {
    
        this.newUpdateCompanyName = newUpdateCompanyName;
    }

    /**
     * @return  the 【oldUpdateCompanyName】
     */
    public String getOldUpdateCompanyName() {
    
        return oldUpdateCompanyName;
    }

    /**
     * @param oldUpdateCompanyName the 【oldUpdateCompanyName】 to set
     */
    public void setOldUpdateCompanyName(String oldUpdateCompanyName) {
    
        this.oldUpdateCompanyName = oldUpdateCompanyName;
    }

    /**
     * @return  the 【oldStoreAddressDetail】
     */
    public String getOldStoreAddressDetail() {
    
        return oldStoreAddressDetail;
    }

    /**
     * @param oldStoreAddressDetail the 【oldStoreAddressDetail】 to set
     */
    public void setOldStoreAddressDetail(String oldStoreAddressDetail) {
    
        this.oldStoreAddressDetail = oldStoreAddressDetail;
    }

    /**
     * @return  the 【agreementType】
     */
    public Integer getAgreementType() {
    
        return agreementType;
    }

    /**
     * @param agreementType the 【agreementType】 to set
     */
    public void setAgreementType(Integer agreementType) {
    
        this.agreementType = agreementType;
    }

    /**
     * @return  the 【authRepresentative】
     */
    public String getAuthRepresentative() {
    
        return authRepresentative;
    }

    /**
     * @param authRepresentative the 【authRepresentative】 to set
     */
    public void setAuthRepresentative(String authRepresentative) {
    
        this.authRepresentative = authRepresentative;
    }

    /**
     * @return  the 【agentContact】
     */
    public String getAgentContact() {
    
        return agentContact;
    }

    /**
     * @param agentContact the 【agentContact】 to set
     */
    public void setAgentContact(String agentContact) {
    
        this.agentContact = agentContact;
    }

    /**
     * @return  the 【totleDepositFee】
     */
    public BigDecimal getTotleDepositFee() {
    
        return totleDepositFee;
    }

    /**
     * @param totleDepositFee the 【totleDepositFee】 to set
     */
    public void setTotleDepositFee(BigDecimal totleDepositFee) {
    
        this.totleDepositFee = totleDepositFee;
    }

    /**
     * @return  the 【penaltyFee】
     */
    public BigDecimal getPenaltyFee() {
    
        return penaltyFee;
    }

    /**
     * @param penaltyFee the 【penaltyFee】 to set
     */
    public void setPenaltyFee(BigDecimal penaltyFee) {
    
        this.penaltyFee = penaltyFee;
    }

    /**
     * @return  the 【companyBankNo】
     */
    public String getCompanyBankNo() {
    
        return companyBankNo;
    }

    /**
     * @param companyBankNo the 【companyBankNo】 to set
     */
    public void setCompanyBankNo(String companyBankNo) {
    
        this.companyBankNo = companyBankNo;
    }

    /**
     * @return  the 【bankAccount】
     */
    public String getBankAccount() {
    
        return bankAccount;
    }

    /**
     * @param bankAccount the 【bankAccount】 to set
     */
    public void setBankAccount(String bankAccount) {
    
        this.bankAccount = bankAccount;
    }

    /**
     * @return  the 【accountName】
     */
    public String getAccountName() {
    
        return accountName;
    }

    /**
     * @param accountName the 【accountName】 to set
     */
    public void setAccountName(String accountName) {
    
        this.accountName = accountName;
    }

    /**
     * @return  the 【recipients】
     */
    public String getRecipients() {
    
        return recipients;
    }

    /**
     * @param recipients the 【recipients】 to set
     */
    public void setRecipients(String recipients) {
    
        this.recipients = recipients;
    }

    /**
     * @return  the 【cityNo】
     */
    public String getCityNo() {
    
        return cityNo;
    }

    /**
     * @param cityNo the 【cityNo】 to set
     */
    public void setCityNo(String cityNo) {
    
        this.cityNo = cityNo;
    }

    /**
     * @return  the 【districtNo】
     */
    public String getDistrictNo() {
    
        return districtNo;
    }

    /**
     * @param districtNo the 【districtNo】 to set
     */
    public void setDistrictNo(String districtNo) {
    
        this.districtNo = districtNo;
    }

    /**
     * @return  the 【recipientsAddress】
     */
    public String getRecipientsAddress() {
    
        return recipientsAddress;
    }

    /**
     * @param recipientsAddress the 【recipientsAddress】 to set
     */
    public void setRecipientsAddress(String recipientsAddress) {
    
        this.recipientsAddress = recipientsAddress;
    }

	public Integer getThreePartChangeType() {
		return threePartChangeType;
	}

	public void setThreePartChangeType(Integer threePartChangeType) {
		this.threePartChangeType = threePartChangeType;
	}

	public Integer getContractType() {
		return contractType;
	}

	public void setContractType(Integer contractType) {
		this.contractType = contractType;
	}

	public List<StoreDto> getStoreList() {
		return storeList;
	}

	public void setStoreList(List<StoreDto> storeList) {
		this.storeList = storeList;
	}
}
