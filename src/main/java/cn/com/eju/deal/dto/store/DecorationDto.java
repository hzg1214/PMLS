package cn.com.eju.deal.dto.store;

import java.math.BigDecimal;
import java.util.Date;

import cn.com.eju.deal.core.model.BaseModel;
/**
 * 专用于调用OMS接口DTO
 * @author  wushuang
 * @date 2016年8月18日 上午10:35:02
 */

public class DecorationDto extends BaseModel {

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9157299937126296377L;
	/**
	 * 装修id
	 */
	private Integer decorateId;
	/**
	 * 装修编号
	 */
	private String  decorateNo;
	/**
	 * 合同编号
	 */
	private String contractNo;
	/**
	 * 门店编号
	 */
	private String storeNo;
	/**
	 * 门店名称
	 */
	private String storeName;
	/**
	 * 门店地址
	 */
	private String storeAddress;
	/**
	 * 合同类型
	 */
	private String contractType;
	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 申请人
	 */
	private String applicant;
	/**
	 * 装修进度
	 */
	private int decorateStatus;
	/**
	 * OA翻牌申请单编码
	 */
	private String flipApplicaCode;
	/**
	 * 装修公司名称
	 */
	private String decorateCompany;
	/**
	 * 标准项目报价
	 */
	private BigDecimal standardQuotatn;
	/**
	 * 增加项目报价
	 */
	private BigDecimal addQuotation;
	/**
	 * 报价小计
	 */
	private BigDecimal quotatnsubtotal;
	/**
	 * 装修合同会签单号
	 */
	private String decorateSign;
	/**
	 * 翻牌完成日期
	 */
	private Date flipCompleDate;
	/**
	 * OA翻牌验收单号
	 */
	private String flipAcceptaceNo; 
	/**
	 * 翻牌验收通过日期
	 */
	private Date flipPassDate;
	/**
	 * 装修发票开具日期
	 */
	private Date decorateInvceDate;
	/**
     * 城市编号
     */
    private String cityNo; 
    
    /**
     * 合作协议书编号
     */
    private String agreementNo;
    /**
     * 合作期限 开始时间 Str
     */ 
    private String dateLifeStartStr;
    /**
     * 合作期限 结束时间 Str
     */
    private String dateLifeEndStr;
    /**
     * OA申请单中介 
     */
    private String oafilpagency;
    
    
	public String getOafilpagency() {
		return oafilpagency;
	}
	public void setOafilpagency(String oafilpagency) {
		this.oafilpagency = oafilpagency;
	}
	public String getCityNo()
    {
        return cityNo;
    }
    public void setCityNo(String cityNo)
    {
        this.cityNo = cityNo;
    }
    public Integer getDecorateId() {
		return decorateId;
	}
	public void setDecorateId(Integer decorateId) {
		this.decorateId = decorateId;
	}
	public String getDecorateNo() {
		return decorateNo;
	}
	public void setDecorateNo(String decorateNo) {
		this.decorateNo = decorateNo;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
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
	public String getStoreAddress() {
		return storeAddress;
	}
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public int getDecorateStatus() {
		return decorateStatus;
	}
	public void setDecorateStatus(int decorateStatus) {
		this.decorateStatus = decorateStatus;
	}
	public String getFlipApplicaCode() {
		return flipApplicaCode;
	}
	public void setFlipApplicaCode(String flipApplicaCode) {
		this.flipApplicaCode = flipApplicaCode;
	}
	public String getDecorateCompany() {
		return decorateCompany;
	}
	public void setDecorateCompany(String decorateCompany) {
		this.decorateCompany = decorateCompany;
	}
	public BigDecimal getStandardQuotatn() {
		return standardQuotatn;
	}
	public void setStandardQuotatn(BigDecimal standardQuotatn) {
		this.standardQuotatn = standardQuotatn;
	}
	public BigDecimal getAddQuotation() {
		return addQuotation;
	}
	public void setAddQuotation(BigDecimal addQuotation) {
		this.addQuotation = addQuotation;
	}
	public BigDecimal getQuotatnsubtotal() {
		return quotatnsubtotal;
	}
	public void setQuotatnsubtotal(BigDecimal quotatnsubtotal) {
		this.quotatnsubtotal = quotatnsubtotal;
	}
	public String getDecorateSign() {
		return decorateSign;
	}
	public void setDecorateSign(String decorateSign) {
		this.decorateSign = decorateSign;
	}
	public Date getFlipCompleDate() {
		return flipCompleDate;
	}
	public void setFlipCompleDate(Date flipCompleDate) {
		this.flipCompleDate = flipCompleDate;
	}
	public String getFlipAcceptaceNo() {
		return flipAcceptaceNo;
	}
	public void setFlipAcceptaceNo(String flipAcceptaceNo) {
		this.flipAcceptaceNo = flipAcceptaceNo;
	}
	public Date getFlipPassDate() {
		return flipPassDate;
	}
	public void setFlipPassDate(Date flipPassDate) {
		this.flipPassDate = flipPassDate;
	}
	public Date getDecorateInvceDate() {
		return decorateInvceDate;
	}
	public void setDecorateInvceDate(Date decorateInvceDate) {
		this.decorateInvceDate = decorateInvceDate;
	}
	public String getAgreementNo() {
		return agreementNo;
	}
	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}
	public String getDateLifeStartStr() {
		return dateLifeStartStr;
	}
	public void setDateLifeStartStr(String dateLifeStartStr) {
		this.dateLifeStartStr = dateLifeStartStr;
	}
	public String getDateLifeEndStr() {
		return dateLifeEndStr;
	}
	public void setDateLifeEndStr(String dateLifeEndStr) {
		this.dateLifeEndStr = dateLifeEndStr;
	}
	
	public DecorationDto(Integer decorateId, String decorateNo, String contractNo,
			String storeNo, String storeName, String storeAddress,
			String contractType, String companyName, String applicant,
			int decorateStatus, String flipApplicaCode, String decorateCompany,
			BigDecimal standardQuotatn, BigDecimal addQuotation,
			BigDecimal quotatnsubtotal, String decorateSign,
			Date flipCompleDate, String flipAcceptaceNo, Date flipPassDate,
			Date decorateInvceDate) {
		super();
		this.decorateId = decorateId;
		this.decorateNo = decorateNo;
		this.contractNo = contractNo;
		this.storeNo = storeNo;
		this.storeName = storeName;
		this.storeAddress = storeAddress;
		this.contractType = contractType;
		this.companyName = companyName;
		this.applicant = applicant;
		this.decorateStatus = decorateStatus;
		this.flipApplicaCode = flipApplicaCode;
		this.decorateCompany = decorateCompany;
		this.standardQuotatn = standardQuotatn;
		this.addQuotation = addQuotation;
		this.quotatnsubtotal = quotatnsubtotal;
		this.decorateSign = decorateSign;
		this.flipCompleDate = flipCompleDate;
		this.flipAcceptaceNo = flipAcceptaceNo;
		this.flipPassDate = flipPassDate;
		this.decorateInvceDate = decorateInvceDate;
	}
    public DecorationDto()
    {
    }
}
