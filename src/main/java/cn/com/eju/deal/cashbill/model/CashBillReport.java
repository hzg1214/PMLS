package cn.com.eju.deal.cashbill.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CashBillReport implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer proParentId;

    private Integer comParentId;

    private Integer reportId;

    private String reportNo;

    private String buildingNo;

    private String customerNm;

    private BigDecimal area;

    private BigDecimal roughAmount;

    private BigDecimal dealAmount;

    private Date dateCreate;

    private Integer userIdCreate;

    private Boolean delFlag;
    
    private Integer reportDetailId;
    
    private BigDecimal sqYjsrAmount;//应计收入
    
    private BigDecimal sqYjfyAmount;//应计返佣
    
    private BigDecimal sqYjdyAmount;//应计垫佣
    
    private BigDecimal sqSjsrAmount;//实收收入
    
    private BigDecimal sqSjfyAmount;//实际返佣
    
    private BigDecimal sqSjdyAmount;//实际垫佣
    
    private BigDecimal requestAmount;//含税请款金额
    
    private BigDecimal taxAmount;//税额 
    
    private Integer requestType;//请款类别: 1-返佣 ,2-垫佣

    private Integer checkBodyId; // 考核主体编号

    private String checkBodyName; // 考核主体名称

    private String memo;//说明

    /**
     * 是否为冲抵请款记录，0：不是，1：是
     */
    private Boolean offSetFlag;

    private String accountProject;

    private String accountProjectNo;

    private Integer pjsdId;
    private String isGlobalControl;
    private Integer jssId;

    public Boolean getOffSetFlag() {
        return offSetFlag;
    }

    public void setOffSetFlag(Boolean offSetFlag) {
        this.offSetFlag = offSetFlag;
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

    public Integer getComParentId() {
        return comParentId;
    }

    public void setComParentId(Integer comParentId) {
        this.comParentId = comParentId;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getCustomerNm() {
        return customerNm;
    }

    public void setCustomerNm(String customerNm) {
        this.customerNm = customerNm;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getRoughAmount() {
        return roughAmount;
    }

    public void setRoughAmount(BigDecimal roughAmount) {
        this.roughAmount = roughAmount;
    }

    public BigDecimal getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(BigDecimal dealAmount) {
        this.dealAmount = dealAmount;
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

	public Integer getReportDetailId() {
		return reportDetailId;
	}

	public void setReportDetailId(Integer reportDetailId) {
		this.reportDetailId = reportDetailId;
	}

	public BigDecimal getSqYjsrAmount() {
		return sqYjsrAmount;
	}

	public void setSqYjsrAmount(BigDecimal sqYjsrAmount) {
		this.sqYjsrAmount = sqYjsrAmount;
	}

	public BigDecimal getSqYjfyAmount() {
		return sqYjfyAmount;
	}

	public void setSqYjfyAmount(BigDecimal sqYjfyAmount) {
		this.sqYjfyAmount = sqYjfyAmount;
	}

	public BigDecimal getSqYjdyAmount() {
		return sqYjdyAmount;
	}

	public void setSqYjdyAmount(BigDecimal sqYjdyAmount) {
		this.sqYjdyAmount = sqYjdyAmount;
	}

	public BigDecimal getSqSjsrAmount() {
		return sqSjsrAmount;
	}

	public void setSqSjsrAmount(BigDecimal sqSjsrAmount) {
		this.sqSjsrAmount = sqSjsrAmount;
	}

	public BigDecimal getSqSjfyAmount() {
		return sqSjfyAmount;
	}

	public void setSqSjfyAmount(BigDecimal sqSjfyAmount) {
		this.sqSjfyAmount = sqSjfyAmount;
	}

	public BigDecimal getSqSjdyAmount() {
		return sqSjdyAmount;
	}

	public void setSqSjdyAmount(BigDecimal sqSjdyAmount) {
		this.sqSjdyAmount = sqSjdyAmount;
	}

	public BigDecimal getRequestAmount() {
		return requestAmount;
	}

	public void setRequestAmount(BigDecimal requestAmount) {
		this.requestAmount = requestAmount;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Integer getRequestType() {
		return requestType;
	}

	public void setRequestType(Integer requestType) {
		this.requestType = requestType;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

    public Integer getCheckBodyId() {
        return checkBodyId;
    }

    public void setCheckBodyId(Integer checkBodyId) {
        this.checkBodyId = checkBodyId;
    }

    public String getCheckBodyName() {
        return checkBodyName;
    }

    public void setCheckBodyName(String checkBodyName) {
        this.checkBodyName = checkBodyName;
    }

    public Integer getPjsdId() {
        return pjsdId;
    }

    public void setPjsdId(Integer pjsdId) {
        this.pjsdId = pjsdId;
    }

    public String getIsGlobalControl() {
        return isGlobalControl;
    }

    public void setIsGlobalControl(String isGlobalControl) {
        this.isGlobalControl = isGlobalControl;
    }

    public Integer getJssId() {
        return jssId;
    }

    public void setJssId(Integer jssId) {
        this.jssId = jssId;
    }
}