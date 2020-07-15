package cn.com.eju.deal.dto.user;

import java.util.Date;

public class Role {
    private Integer roleId;

    private String roleName;
    
    private String roleCode;

    private String roleDesc;

    private Date dateCreate;

    private Integer userIdCreate;

    private String delFlag;
    
    private Integer checked;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
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
 
    public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}



	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}
	
}