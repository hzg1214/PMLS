package cn.com.eju.pmls.developer.dto;

import java.util.Date;

public class DeveloperBrandDto {
    private Integer id;//主键ID
    private String developerBrandCode;//开发商品牌编号
    private String developerBrandName;//开发商品牌名称
    private Integer parentId;//父级ID
    private String orgId;//架构ID
    private String dateCreate;//创建时间
    private Date dateUpdate;//更新时间
    private String userIdCreate;//创建人
    private String userIdUpdate;//更新人
    private String createUserName;//创建人姓名
    private String updateUserName;//更新人姓名
    private Integer delFlag;//删除标识
    private String remark;//备注
    private String name;
    private Integer partner;//合作方类型
    private Integer oldBigCustomerFlag;
	private Integer oldBigCustomerId;
	private String oldBigCustomerName;
	private Integer oldIsYjDy;
	private Integer oldMattressNailId;
	private String oldMattressNailName;
	
	private Integer bigCustomerFlag;
	private Integer bigCustomerId;
	private String bigCustomerName;
	private Integer isYjDy;
	private Integer mattressNailId;
	private String mattressNailName;
	
	private String userCode;
	
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Integer getOldBigCustomerFlag() {
		return oldBigCustomerFlag;
	}

	public void setOldBigCustomerFlag(Integer oldBigCustomerFlag) {
		this.oldBigCustomerFlag = oldBigCustomerFlag;
	}

	public Integer getOldBigCustomerId() {
		return oldBigCustomerId;
	}

	public void setOldBigCustomerId(Integer oldBigCustomerId) {
		this.oldBigCustomerId = oldBigCustomerId;
	}

	public String getOldBigCustomerName() {
		return oldBigCustomerName;
	}

	public void setOldBigCustomerName(String oldBigCustomerName) {
		this.oldBigCustomerName = oldBigCustomerName;
	}

	public Integer getOldIsYjDy() {
		return oldIsYjDy;
	}

	public void setOldIsYjDy(Integer oldIsYjDy) {
		this.oldIsYjDy = oldIsYjDy;
	}

	public Integer getOldMattressNailId() {
		return oldMattressNailId;
	}

	public void setOldMattressNailId(Integer oldMattressNailId) {
		this.oldMattressNailId = oldMattressNailId;
	}

	public String getOldMattressNailName() {
		return oldMattressNailName;
	}

	public void setOldMattressNailName(String oldMattressNailName) {
		this.oldMattressNailName = oldMattressNailName;
	}

	public Integer getBigCustomerFlag() {
		return bigCustomerFlag;
	}

	public void setBigCustomerFlag(Integer bigCustomerFlag) {
		this.bigCustomerFlag = bigCustomerFlag;
	}

	public Integer getBigCustomerId() {
		return bigCustomerId;
	}

	public void setBigCustomerId(Integer bigCustomerId) {
		this.bigCustomerId = bigCustomerId;
	}

	public String getBigCustomerName() {
		return bigCustomerName;
	}

	public void setBigCustomerName(String bigCustomerName) {
		this.bigCustomerName = bigCustomerName;
	}

	public Integer getIsYjDy() {
		return isYjDy;
	}

	public void setIsYjDy(Integer isYjDy) {
		this.isYjDy = isYjDy;
	}

	public Integer getMattressNailId() {
		return mattressNailId;
	}

	public void setMattressNailId(Integer mattressNailId) {
		this.mattressNailId = mattressNailId;
	}

	public String getMattressNailName() {
		return mattressNailName;
	}

	public void setMattressNailName(String mattressNailName) {
		this.mattressNailName = mattressNailName;
	}

	public String getUserIdUpdate() {
		return userIdUpdate;
	}

	public void setUserIdUpdate(String userIdUpdate) {
		this.userIdUpdate = userIdUpdate;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getDeveloperBrandCode() {
		return developerBrandCode;
	}

	public void setDeveloperBrandCode(String developerBrandCode) {
		this.developerBrandCode = developerBrandCode;
	}

	public String getDeveloperBrandName() {
		return developerBrandName;
	}

	public void setDeveloperBrandName(String developerBrandName) {
		this.developerBrandName = developerBrandName;
	}

	public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getUserIdCreate() {
        return userIdCreate;
    }

    public void setUserIdCreate(String userIdCreate) {
        this.userIdCreate = userIdCreate;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

	public Integer getPartner() {
		return partner;
	}

	public void setPartner(Integer partner) {
		this.partner = partner;
	}

}
