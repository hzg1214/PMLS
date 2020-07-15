package cn.com.eju.deal.Report.model;

import java.util.Date;

public class Group {
    private Integer groupId;

    private String groupName;

    private Integer managerId;

    private Integer typeId;

    private String typeName;
    
    private Integer parentId;

    private Date dateCreate;

    private Integer userIdCreate;

    private String  delFlag;

    private String orgIdStr;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

	public String getOrgIdStr() {
		return orgIdStr;
	}

	public void setOrgIdStr(String orgIdStr) {
		this.orgIdStr = orgIdStr;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

   
}