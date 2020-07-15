package cn.com.eju.deal.cashbill.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CashBillCompany implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer proParentId;

    private String cashBillNo;

    private String oaNo;

    private String frameOaNo;

    private String frameOaName;

    private Integer companyId;

    private String companyNo;

    private String companyName;

    private BigDecimal amountNoTax;

    private BigDecimal amountTax;

    private BigDecimal amountTotal;

    private String remark;

    private Integer submitOaStatus;

    private String userCode;

    private String userName;

    private Date applyTime;

    private String vendorName;

    private String vendorCode;

    private String accountProject;

    private String accountProjectNo;

    private String flowId;

    private Integer approveStatus;

    private Date approveTime;

    private Date dateCreate;

    private Integer userIdCreate;

    private Boolean delFlag;


    private String businessLicenseNo;

    private String bankName;

    private String bankAccount;

    private String oaProjectNo;

    private String oaProjectName;

    private String frameFileUrl;

    private String frameFileName;

    private String ISKJ;

    private String receiveBankName;

    private String receiveBankAccountCardCode;

    private String receiveBankAccountName;

    private String receiveBankProvinceName;

    private String receiveBankSerialNo;

    private String vendorId;

    private String receiveBankCityName;

    private Date recordDate;

    private int syncFlag;

    private String remarks;

    private Date predictPayTime;//预计付款日期 非表字段
    private Integer payType;//付款方式 非表字段

    public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	private BigDecimal areaTotal;

    private BigDecimal roughAmountTotal;

    private BigDecimal dealAmountTotal;

    private BigDecimal sqYjsrAmountTotal;

    private BigDecimal sqYjfyAmountTotal;

    private BigDecimal sqYjdyAmountTotal;


    private BigDecimal sqSjsrAmountTotal;

    private BigDecimal sqSjfyAmountTotal;

    private BigDecimal sqSjdyAmountTotal;

    private BigDecimal requestAmountTotal;

    private BigDecimal taxAmountTotal;

    private List<CashBillReport> reportList;

    private List<CashBillReport> offSetList;

    /**
     * 是否包含冲抵请款，0：不包含，1：包含
     */
    private Boolean offSetFlag;


    private Integer formState;//发送oa状态码

    private String errmsg;//oa返回msg

    private Date oaStartDate;//oa返回时间

    private String templateCode;//模板编号

    private String templateName;//模板名称

    private String pjsNostr;//结算书编号

    private String inValid;//作废：1

    private String fileRecordMainIds;

    public Boolean getOffSetFlag() {
        return offSetFlag;
    }

    public void setOffSetFlag(Boolean offSetFlag) {
        this.offSetFlag = offSetFlag;
    }

    public List<CashBillReport> getOffSetList() {
        return offSetList;
    }

    public void setOffSetList(List<CashBillReport> offSetList) {
        this.offSetList = offSetList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProParentId() {
        return proParentId;
    }

    public void setProParentId(Integer proParentId) {
        this.proParentId = proParentId;
    }

    public String getCashBillNo() {
        return cashBillNo;
    }

    public void setCashBillNo(String cashBillNo) {
        this.cashBillNo = cashBillNo;
    }

    public String getOaNo() {
        return oaNo;
    }

    public void setOaNo(String oaNo) {
        this.oaNo = oaNo;
    }

    public String getFrameOaNo() {
        return frameOaNo;
    }

    public void setFrameOaNo(String frameOaNo) {
        this.frameOaNo = frameOaNo;
    }

    public String getFrameOaName() {
        return frameOaName;
    }

    public void setFrameOaName(String frameOaName) {
        this.frameOaName = frameOaName;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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

    public BigDecimal getAmountNoTax() {
        return amountNoTax;
    }

    public void setAmountNoTax(BigDecimal amountNoTax) {
        this.amountNoTax = amountNoTax;
    }

    public BigDecimal getAmountTax() {
        return amountTax;
    }

    public void setAmountTax(BigDecimal amountTax) {
        this.amountTax = amountTax;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public Integer getSubmitOaStatus() {
        return submitOaStatus;
    }

    public void setSubmitOaStatus(Integer submitOaStatus) {
        this.submitOaStatus = submitOaStatus;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
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

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
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

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public List<CashBillReport> getReportList() {
        return reportList;
    }

    public void setReportList(List<CashBillReport> reportList) {
        this.reportList = reportList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getBusinessLicenseNo() {
        return businessLicenseNo;
    }

    public void setBusinessLicenseNo(String businessLicenseNo) {
        this.businessLicenseNo = businessLicenseNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getOaProjectNo() {
        return oaProjectNo;
    }

    public void setOaProjectNo(String oaProjectNo) {
        this.oaProjectNo = oaProjectNo;
    }

    public String getOaProjectName() {
        return oaProjectName;
    }

    public void setOaProjectName(String oaProjectName) {
        this.oaProjectName = oaProjectName;
    }

    public String getFrameFileUrl() {
        return frameFileUrl;
    }

    public void setFrameFileUrl(String frameFileUrl) {
        this.frameFileUrl = frameFileUrl;
    }

    public String getFrameFileName() {
        return frameFileName;
    }

    public void setFrameFileName(String frameFileName) {
        this.frameFileName = frameFileName;
    }

    public String getISKJ() {
        return ISKJ;
    }

    public void setISKJ(String ISKJ) {
        this.ISKJ = ISKJ;
    }

    public String getReceiveBankName() {
        return receiveBankName;
    }

    public void setReceiveBankName(String receiveBankName) {
        this.receiveBankName = receiveBankName;
    }

    public String getReceiveBankAccountCardCode() {
        return receiveBankAccountCardCode;
    }

    public void setReceiveBankAccountCardCode(String receiveBankAccountCardCode) {
        this.receiveBankAccountCardCode = receiveBankAccountCardCode;
    }

    public String getReceiveBankAccountName() {
        return receiveBankAccountName;
    }

    public void setReceiveBankAccountName(String receiveBankAccountName) {
        this.receiveBankAccountName = receiveBankAccountName;
    }

    public String getReceiveBankProvinceName() {
        return receiveBankProvinceName;
    }

    public void setReceiveBankProvinceName(String receiveBankProvinceName) {
        this.receiveBankProvinceName = receiveBankProvinceName;
    }

    public String getReceiveBankSerialNo() {
        return receiveBankSerialNo;
    }

    public void setReceiveBankSerialNo(String receiveBankSerialNo) {
        this.receiveBankSerialNo = receiveBankSerialNo;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getReceiveBankCityName() {
        return receiveBankCityName;
    }

    public void setReceiveBankCityName(String receiveBankCityName) {
        this.receiveBankCityName = receiveBankCityName;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public int getSyncFlag() {
        return syncFlag;
    }

    public void setSyncFlag(int syncFlag) {
        this.syncFlag = syncFlag;
    }

    public BigDecimal getAreaTotal() {
        return areaTotal;
    }

    public void setAreaTotal(BigDecimal areaTotal) {
        this.areaTotal = areaTotal;
    }

    public BigDecimal getRoughAmountTotal() {
        return roughAmountTotal;
    }

    public void setRoughAmountTotal(BigDecimal roughAmountTotal) {
        this.roughAmountTotal = roughAmountTotal;
    }

    public BigDecimal getDealAmountTotal() {
        return dealAmountTotal;
    }

    public void setDealAmountTotal(BigDecimal dealAmountTotal) {
        this.dealAmountTotal = dealAmountTotal;
    }

    public BigDecimal getSqYjsrAmountTotal() {
        return sqYjsrAmountTotal;
    }

    public void setSqYjsrAmountTotal(BigDecimal sqYjsrAmountTotal) {
        this.sqYjsrAmountTotal = sqYjsrAmountTotal;
    }

    public BigDecimal getSqYjfyAmountTotal() {
        return sqYjfyAmountTotal;
    }

    public void setSqYjfyAmountTotal(BigDecimal sqYjfyAmountTotal) {
        this.sqYjfyAmountTotal = sqYjfyAmountTotal;
    }

    public BigDecimal getSqYjdyAmountTotal() {
        return sqYjdyAmountTotal;
    }

    public void setSqYjdyAmountTotal(BigDecimal sqYjdyAmountTotal) {
        this.sqYjdyAmountTotal = sqYjdyAmountTotal;
    }

    public BigDecimal getSqSjsrAmountTotal() {
        return sqSjsrAmountTotal;
    }

    public void setSqSjsrAmountTotal(BigDecimal sqSjsrAmountTotal) {
        this.sqSjsrAmountTotal = sqSjsrAmountTotal;
    }

    public BigDecimal getSqSjfyAmountTotal() {
        return sqSjfyAmountTotal;
    }

    public void setSqSjfyAmountTotal(BigDecimal sqSjfyAmountTotal) {
        this.sqSjfyAmountTotal = sqSjfyAmountTotal;
    }

    public BigDecimal getSqSjdyAmountTotal() {
        return sqSjdyAmountTotal;
    }

    public void setSqSjdyAmountTotal(BigDecimal sqSjdyAmountTotal) {
        this.sqSjdyAmountTotal = sqSjdyAmountTotal;
    }

    public BigDecimal getRequestAmountTotal() {
        return requestAmountTotal;
    }

    public void setRequestAmountTotal(BigDecimal requestAmountTotal) {
        this.requestAmountTotal = requestAmountTotal;
    }

    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    public Integer getFormState() {
        return formState;
    }

    public void setFormState(Integer formState) {
        this.formState = formState;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Date getOaStartDate() {
        return oaStartDate;
    }

    public void setOaStartDate(Date oaStartDate) {
        this.oaStartDate = oaStartDate;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getPjsNostr() {
        return pjsNostr;
    }

    public void setPjsNostr(String pjsNostr) {
        this.pjsNostr = pjsNostr;
    }

    public String getInValid() {
        return inValid;
    }

    public void setInValid(String inValid) {
        this.inValid = inValid;
    }

    public Date getPredictPayTime() {
        return predictPayTime;
    }

    public void setPredictPayTime(Date predictPayTime) {
        this.predictPayTime = predictPayTime;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getFileRecordMainIds() {
        return fileRecordMainIds;
    }

    public void setFileRecordMainIds(String fileRecordMainIds) {
        this.fileRecordMainIds = fileRecordMainIds;
    }
}