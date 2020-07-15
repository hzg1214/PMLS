package cn.com.eju.deal.dto.contract;

import cn.com.eju.deal.dto.store.StoreDto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 
* 门店业绩撤销Dto
* @author  wushuang
* @date 2016年10月12日 上午10:44:35
 */
public class AchievementCancelDto implements Serializable
{ 
    private static final long serialVersionUID = 1L;
    
 private Integer id;
    
    /**
     * 门店业绩撤销编号
     */
    private String achievementCancelNo;
    
    /**
     * 合同Id
     */
    private int contractId;
    
    /**
     * 撤销原因
     */
    private String cancelReason;
    
    /**
     * 备注
     */
    private String remarks;
    
    /**
     * 创建时间
     */
    private Date dateCreate;
    
    /**
     * 创建人
     */
    private int userCreate;
    
    /**
     * 门店撤销审核状态
     */
    private int approveState;
    
    
    /**
     * 审核时间
     */
    private Date approveDate;
    
    /**
     * 申请编号
     */
    private String applicationNo;
    
    /**
     * OA申请编号
     */
    private String flowId;
    
    /**
     * 更新时间
     */
    private Date updateDate;
    
    /**
     * 更新人
     */
    private Integer updateUser;
    
    /**
     * 删除标记：1-删除 ,0-未删除
     */
    private boolean isDelete;
    
    /**
     * 核算主体
     */
    private String accountSubject;
    
    /**
     * 事业部区域
     */
    private String busDepartment;
    
    private String storeNo;//门店编号
    
    private String storeName;//门店店招
    
    private String districtName;//区域名称
    
    private String address;//门店地址
    
    private String contractTypeStr;//签约合同类型名称
    
    private String decorationStatusStr;//装修进度名称
    
    private String updateDateStr;//装修时间
    
    private String isCancel;//门店撤销标记
    
    private String companyName;//公司名称
    
    private Integer storeId;//门店id
    
    private boolean delFlag;//删除标记
    
    private String userCreateName;//创建人名称
    
    private String userCode;//创建人工号
    
    private String ContractNo;//合同编号
    
    private String CompanyNo;//公司编号
    
    private String approveStateName;//审核状态名称
    
    private List<StoreDto> storelist;//门店List
    
    private String changeCompany;//签约主体变更
    
    private String contractStopNo;//合同终止编号
    
    private Integer businessStatus;//门店营业状态

    private Integer changeType;//变更类型typeId=170

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    public String getApproveStateName()
    {
        return approveStateName;
    }

    public void setApproveStateName(String approveStateName)
    {
        this.approveStateName = approveStateName;
    }

    public String getAccountSubject()
    {
        return accountSubject;
    }

    public void setAccountSubject(String accountSubject)
    {
        this.accountSubject = accountSubject;
    }

    public String getBusDepartment()
    {
        return busDepartment;
    }

    public void setBusDepartment(String busDepartment)
    {
        this.busDepartment = busDepartment;
    }

    public String getContractNo()
    {
        return ContractNo;
    }

    public void setContractNo(String contractNo)
    {
        ContractNo = contractNo;
    }

    public String getCompanyNo()
    {
        return CompanyNo;
    }

    public void setCompanyNo(String companyNo)
    {
        CompanyNo = companyNo;
    }

    public List<StoreDto> getStorelist()
    {
        return storelist;
    }

    public void setStorelist(List<StoreDto> storelist)
    {
        this.storelist = storelist;
    }

    public String getUserCreateName()
    {
        return userCreateName;
    }

    public void setUserCreateName(String userCreateName)
    {
        this.userCreateName = userCreateName;
    }

    public String getUserCode()
    {
        return userCode;
    }

    public void setUserCode(String userCode)
    {
        this.userCode = userCode;
    }

    public Date getApproveDate()
    {
        return approveDate;
    }

    public void setApproveDate(Date approveDate)
    {
        this.approveDate = approveDate;
    }

    public String getApplicationNo()
    {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo)
    {
        this.applicationNo = applicationNo;
    }

    public String getFlowId()
    {
        return flowId;
    }

    public void setFlowId(String flowId)
    {
        this.flowId = flowId;
    }

    public Date getUpdateDate()
    {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate)
    {
        this.updateDate = updateDate;
    }

    public Integer getUpdateUser()
    {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser)
    {
        this.updateUser = updateUser;
    }

    public Integer getStoreId()
    {
        return storeId;
    }

    public void setStoreId(Integer storeId)
    {
        this.storeId = storeId;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getAchievementCancelNo()
    {
        return achievementCancelNo;
    }

    public void setAchievementCancelNo(String achievementCancelNo)
    {
        this.achievementCancelNo = achievementCancelNo;
    }

    public int getContractId()
    {
        return contractId;
    }

    public void setContractId(int contractId)
    {
        this.contractId = contractId;
    }

    public String getCancelReason()
    {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason)
    {
        this.cancelReason = cancelReason;
    }

    public String getRemarks()
    {
        return remarks;
    }

    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }

    public Date getDateCreate()
    {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate)
    {
        this.dateCreate = dateCreate;
    }

    public int getUserCreate()
    {
        return userCreate;
    }

    public void setUserCreate(int userCreate)
    {
        this.userCreate = userCreate;
    }

    public int getApproveState()
    {
        return approveState;
    }

    public void setApproveState(int approveState)
    {
        this.approveState = approveState;
    }

    public boolean isDelete()
    {
        return isDelete;
    }

    public void setDelete(boolean isDelete)
    {
        this.isDelete = isDelete;
    }

    public String getStoreNo()
    {
        return storeNo;
    }

    public void setStoreNo(String storeNo)
    {
        this.storeNo = storeNo;
    }

    public String getStoreName()
    {
        return storeName;
    }

    public void setStoreName(String storeName)
    {
        this.storeName = storeName;
    }

    public String getDistrictName()
    {
        return districtName;
    }

    public void setDistrictName(String districtName)
    {
        this.districtName = districtName;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getContractTypeStr()
    {
        return contractTypeStr;
    }

    public void setContractTypeStr(String contractTypeStr)
    {
        this.contractTypeStr = contractTypeStr;
    }

    public String getDecorationStatusStr()
    {
        return decorationStatusStr;
    }

    public void setDecorationStatusStr(String decorationStatusStr)
    {
        this.decorationStatusStr = decorationStatusStr;
    }

    public String getUpdateDateStr()
    {
        return updateDateStr;
    }

    public void setUpdateDateStr(String updateDateStr)
    {
        this.updateDateStr = updateDateStr;
    }

    public String getIsCancel()
    {
        return isCancel;
    }

    public void setIsCancel(String isCancel)
    {
        this.isCancel = isCancel;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public boolean isDelFlag()
    {
        return delFlag;
    }

    public void setDelFlag(boolean delFlag)
    {
        this.delFlag = delFlag;
    }

	public String getChangeCompany() {
		return changeCompany;
	}

	public void setChangeCompany(String changeCompany) {
		this.changeCompany = changeCompany;
	}

	public String getContractStopNo() {
		return contractStopNo;
	}

	public void setContractStopNo(String contractStopNo) {
		this.contractStopNo = contractStopNo;
	}

    /**
     * @return  the 【businessStatus】
     */
    public Integer getBusinessStatus() {
    
        return businessStatus;
    }

    /**
     * @param businessStatus the 【businessStatus】 to set
     */
    public void setBusinessStatus(Integer businessStatus) {
    
        this.businessStatus = businessStatus;
    }
    
   }


