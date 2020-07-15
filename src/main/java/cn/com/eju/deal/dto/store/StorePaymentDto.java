package cn.com.eju.deal.dto.store;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.com.eju.deal.core.model.BaseModel;

public class StorePaymentDto extends BaseModel{

    /**
     * 
     */
    private static final long serialVersionUID = -5487808672458022298L;

    private Integer id;
    private Integer orderType;
    private String paymentNo;
    private String contractNo;
    private Integer paymentType;
    private BigDecimal totalAmount;
    private Integer approveStatus;
    private Integer submitOaStatus;
    private Integer status;
    private Date confirmTime;
    private String flowId;
    private Date dateCreate;
    private Integer userIdCreate;
    private Date dateUpt;
    private Integer userIdUpt;
    private String delFlag;
    private List<StorePaymentDtlDto> dtlList;
    
    private Date refundDate;
    private String companyName;
    private String oaStatusName;
    private String userName;
    private String agreementNo;
    private String contractTypeName;
    private String approveStatusNm;
    private String paymentTypeNm;;
    
    
    public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getOaStatusName() {
		return oaStatusName;
	}
	public void setOaStatusName(String oaStatusName) {
		this.oaStatusName = oaStatusName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAgreementNo() {
		return agreementNo;
	}
	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}
	public String getContractTypeName() {
		return contractTypeName;
	}
	public void setContractTypeName(String contractTypeName) {
		this.contractTypeName = contractTypeName;
	}
	public String getApproveStatusNm() {
		return approveStatusNm;
	}
	public void setApproveStatusNm(String approveStatusNm) {
		this.approveStatusNm = approveStatusNm;
	}
	/**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * @return the orderType
     */
    public Integer getOrderType() {
        return orderType;
    }
    /**
     * @param orderType the orderType to set
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
    /**
     * @return the paymentNo
     */
    public String getPaymentNo() {
        return paymentNo;
    }
    /**
     * @param paymentNo the paymentNo to set
     */
    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }
    /**
    /**
     * @return the paymentType
     */
    public Integer getPaymentType() {
        return paymentType;
    }
    /**
     * @param paymentType the paymentType to set
     */
    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }
    /**
     * @return the totalAmount
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    /**
     * @param totalAmount the totalAmount to set
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    /**
     * @return the approveStatus
     */
    public Integer getApproveStatus() {
        return approveStatus;
    }
    /**
     * @param approveStatus the approveStatus to set
     */
    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }
    /**
     * @return the submitOaStatus
     */
    public Integer getSubmitOaStatus() {
        return submitOaStatus;
    }
    /**
     * @param submitOaStatus the submitOaStatus to set
     */
    public void setSubmitOaStatus(Integer submitOaStatus) {
        this.submitOaStatus = submitOaStatus;
    }
    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    /**
     * @return the confirmTime
     */
    public Date getConfirmTime() {
        return confirmTime;
    }
    /**
     * @param confirmTime the confirmTime to set
     */
    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }
    /**
     * @return the flowId
     */
    public String getFlowId() {
        return flowId;
    }
    /**
     * @param flowId the flowId to set
     */
    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }
    /**
     * @return the dateCreate
     */
    public Date getDateCreate() {
        return dateCreate;
    }
    /**
     * @param dateCreate the dateCreate to set
     */
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }
    /**
     * @return the userIdCreate
     */
    public Integer getUserIdCreate() {
        return userIdCreate;
    }
    /**
     * @param userIdCreate the userIdCreate to set
     */
    public void setUserIdCreate(Integer userIdCreate) {
        this.userIdCreate = userIdCreate;
    }
    /**
     * @return the dateUpt
     */
    public Date getDateUpt() {
        return dateUpt;
    }
    /**
     * @param dateUpt the dateUpt to set
     */
    public void setDateUpt(Date dateUpt) {
        this.dateUpt = dateUpt;
    }
    /**
     * @return the userIdUpt
     */
    public Integer getUserIdUpt() {
        return userIdUpt;
    }
    /**
     * @param userIdUpt the userIdUpt to set
     */
    public void setUserIdUpt(Integer userIdUpt) {
        this.userIdUpt = userIdUpt;
    }
    /**
     * @return the delFlag
     */
    public String getDelFlag() {
        return delFlag;
    }
    /**
     * @param delFlag the delFlag to set
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
    /**
     * @return the dtlList
     */
    public List<StorePaymentDtlDto> getDtlList() {
        return dtlList;
    }
    /**
     * @param dtlList the dtlList to set
     */
    public void setDtlList(List<StorePaymentDtlDto> dtlList) {
        this.dtlList = dtlList;
    }
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getPaymentTypeNm() {
		return paymentTypeNm;
	}
	public void setPaymentTypeNm(String paymentTypeNm) {
		this.paymentTypeNm = paymentTypeNm;
	}
	public Date getRefundDate() {
		return refundDate;
	}
	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}
    
}
