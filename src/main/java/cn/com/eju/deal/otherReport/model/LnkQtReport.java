package cn.com.eju.deal.otherReport.model;

import java.math.BigDecimal;
import java.util.Date;

public class LnkQtReport {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String reportNo;

    private String estateId;

    private String estateNm;

    private String reportStatus;

    private String dealAuditStatus;

    private String brokerageStatus;

    private Integer validStatus;

    private Integer reportUserId;

    private Date reportDate;

    private Integer dealUserId;

    private Date dealDate;

    private Integer backDealUserId;

    private Date backDealDate;

    private Integer dealAuditUserId;

    private Date dealAuditTime;

    private String dealAuditDesc;

    private Integer brokerageUserId;

    private Date brokerageDate;

    private Integer validUserId;

    private Date validDate;

    private String partnerNm;

    private Integer srType;

    private BigDecimal srAmount;

    private String memo;

    private BigDecimal dealAmount;

    private BigDecimal befYJSRAmount;

    private BigDecimal aftYJSRAmount;

    private BigDecimal befYJFYAmount;

    private BigDecimal aftYJFYAmount;

    private String accountProject;

    private String accountProjectNo;

    private String accCityNo;

    private Integer centerId;

    private String centerName;

    private Integer crtUserId;

    private Date crtDate;

    private Integer uptUserId;

    private Date uptDate;

    private Boolean delFlg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo == null ? null : reportNo.trim();
    }

    public String getEstateId() {
        return estateId;
    }

    public void setEstateId(String estateId) {
        this.estateId = estateId == null ? null : estateId.trim();
    }

    public String getEstateNm() {
        return estateNm;
    }

    public void setEstateNm(String estateNm) {
        this.estateNm = estateNm == null ? null : estateNm.trim();
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus == null ? null : reportStatus.trim();
    }

    public String getDealAuditStatus() {
        return dealAuditStatus;
    }

    public void setDealAuditStatus(String dealAuditStatus) {
        this.dealAuditStatus = dealAuditStatus == null ? null : dealAuditStatus.trim();
    }

    public String getBrokerageStatus() {
        return brokerageStatus;
    }

    public void setBrokerageStatus(String brokerageStatus) {
        this.brokerageStatus = brokerageStatus == null ? null : brokerageStatus.trim();
    }

    public Integer getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(Integer validStatus) {
        this.validStatus = validStatus;
    }

    public Integer getReportUserId() {
        return reportUserId;
    }

    public void setReportUserId(Integer reportUserId) {
        this.reportUserId = reportUserId;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Integer getDealUserId() {
        return dealUserId;
    }

    public void setDealUserId(Integer dealUserId) {
        this.dealUserId = dealUserId;
    }

    public Date getDealDate() {
        return dealDate;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }

    public Integer getBackDealUserId() {
        return backDealUserId;
    }

    public void setBackDealUserId(Integer backDealUserId) {
        this.backDealUserId = backDealUserId;
    }

    public Date getBackDealDate() {
        return backDealDate;
    }

    public void setBackDealDate(Date backDealDate) {
        this.backDealDate = backDealDate;
    }

    public Integer getDealAuditUserId() {
        return dealAuditUserId;
    }

    public void setDealAuditUserId(Integer dealAuditUserId) {
        this.dealAuditUserId = dealAuditUserId;
    }

    public Date getDealAuditTime() {
        return dealAuditTime;
    }

    public void setDealAuditTime(Date dealAuditTime) {
        this.dealAuditTime = dealAuditTime;
    }

    public String getDealAuditDesc() {
        return dealAuditDesc;
    }

    public void setDealAuditDesc(String dealAuditDesc) {
        this.dealAuditDesc = dealAuditDesc == null ? null : dealAuditDesc.trim();
    }

    public Integer getBrokerageUserId() {
        return brokerageUserId;
    }

    public void setBrokerageUserId(Integer brokerageUserId) {
        this.brokerageUserId = brokerageUserId;
    }

    public Date getBrokerageDate() {
        return brokerageDate;
    }

    public void setBrokerageDate(Date brokerageDate) {
        this.brokerageDate = brokerageDate;
    }

    public Integer getValidUserId() {
        return validUserId;
    }

    public void setValidUserId(Integer validUserId) {
        this.validUserId = validUserId;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public String getPartnerNm() {
        return partnerNm;
    }

    public void setPartnerNm(String partnerNm) {
        this.partnerNm = partnerNm == null ? null : partnerNm.trim();
    }

    public Integer getSrType() {
        return srType;
    }

    public void setSrType(Integer srType) {
        this.srType = srType;
    }

    public BigDecimal getSrAmount() {
        return srAmount;
    }

    public void setSrAmount(BigDecimal srAmount) {
        this.srAmount = srAmount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public BigDecimal getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(BigDecimal dealAmount) {
        this.dealAmount = dealAmount;
    }

    public BigDecimal getBefYJSRAmount() {
        return befYJSRAmount;
    }

    public void setBefYJSRAmount(BigDecimal befYJSRAmount) {
        this.befYJSRAmount = befYJSRAmount;
    }

    public BigDecimal getAftYJSRAmount() {
        return aftYJSRAmount;
    }

    public void setAftYJSRAmount(BigDecimal aftYJSRAmount) {
        this.aftYJSRAmount = aftYJSRAmount;
    }

    public BigDecimal getBefYJFYAmount() {
        return befYJFYAmount;
    }

    public void setBefYJFYAmount(BigDecimal befYJFYAmount) {
        this.befYJFYAmount = befYJFYAmount;
    }

    public BigDecimal getAftYJFYAmount() {
        return aftYJFYAmount;
    }

    public void setAftYJFYAmount(BigDecimal aftYJFYAmount) {
        this.aftYJFYAmount = aftYJFYAmount;
    }

    public String getAccountProject() {
        return accountProject;
    }

    public void setAccountProject(String accountProject) {
        this.accountProject = accountProject == null ? null : accountProject.trim();
    }

    public String getAccountProjectNo() {
        return accountProjectNo;
    }

    public void setAccountProjectNo(String accountProjectNo) {
        this.accountProjectNo = accountProjectNo == null ? null : accountProjectNo.trim();
    }

    public String getAccCityNo() {
        return accCityNo;
    }

    public void setAccCityNo(String accCityNo) {
        this.accCityNo = accCityNo == null ? null : accCityNo.trim();
    }

    public Integer getCenterId() {
        return centerId;
    }

    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public Integer getCrtUserId() {
        return crtUserId;
    }

    public void setCrtUserId(Integer crtUserId) {
        this.crtUserId = crtUserId;
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public Integer getUptUserId() {
        return uptUserId;
    }

    public void setUptUserId(Integer uptUserId) {
        this.uptUserId = uptUserId;
    }

    public Date getUptDate() {
        return uptDate;
    }

    public void setUptDate(Date uptDate) {
        this.uptDate = uptDate;
    }

    public Boolean getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(Boolean delFlg) {
        this.delFlg = delFlg;
    }
}