package cn.com.eju.deal.dto.contract;

import java.math.BigDecimal;

/**
 * 合同变更/门店关联表Model层
 * 
 * @author sunmm
 * @date 2016年8月4日 下午9:09:38
 */
public class ContractChangeStore {
	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 合同变更ID
	 */
	private Integer contractStopId;
	/**
	 * 门店ID
	 */
	private Integer storeId;
	/**
	 * 是否删除标识(0:true 1:false)
	 */
	private Boolean delFlag;
	
	/**
	 * 合同ID
	 */
	private Integer contractId;

	/** 
	* 获取合同ID
	* @return contractId 合同ID
	*/
	public Integer getContractId() {
		return contractId;
	}

	/** 
	* 设置合同ID
	* @param contractId 合同ID
	*/
	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	/**
	 * 审核状态(0:未审核 1:审核中 2:审核通过 3:审核失败)
	 */
	private Integer approveState;

	/**
	 * 获取审核状态(0:未审核 1:审核中 2:审核通过 3:审核失败)
	 * 
	 * @return approveState 审核状态
	 */
	public Integer getApproveState() {
		return approveState;
	}

	/**
	 * 设置审核状态(0:未审核 1:审核中 2:审核通过 3:审核失败)
	 * 
	 * @param approveState
	 *            审核状态
	 */
	public void setApproveState(Integer approveState) {
		this.approveState = approveState;
	}

	/**
	 * 获取ID
	 * 
	 * @return ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置ID
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取门店变更ID
	 * 
	 * @return contractStopId 门店变更ID
	 */
	public Integer getContractStopId() {
		return contractStopId;
	}

	/**
	 * 设置门店变更ID
	 * 
	 * @param contractStopId
	 *            门店变更ID
	 */
	public void setContractStopId(Integer contractStopId) {
		this.contractStopId = contractStopId;
	}

	/**
	 * 获取门店ID
	 * 
	 * @return storeId 门店ID
	 */
	public Integer getStoreId() {
		return storeId;
	}

	/**
	 * 设置门店ID
	 * 
	 * @param storeId
	 *            门店ID
	 */
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	/**
	 * 获取是否删除标识
	 * 
	 * @return delFlag 是否删除标识
	 */
	public Boolean getDelFlag() {
		return delFlag;
	}

	/**
	 * 设置是否删除标识
	 * 
	 * @param delFlag
	 *            是否删除标识
	 */
	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}
	private BigDecimal receivedAmount;
    /**
   	 * 保证金退还金额
   	 */
   	private BigDecimal depositBackMoney;
   	//保证金处理方式
   	private Integer depositBalance;
   	
   	private Integer decorationType;
   	
   	private String decorationTypeName;
   	
   	private Integer decorateSituate;
   	
   	private String decorateSituateName;
   	
   	private String decorateCompany;

	/**
     * @return  the 【decorationType】
     */
    public Integer getDecorationType() {
    
        return decorationType;
    }

    /**
     * @param decorationType the 【decorationType】 to set
     */
    public void setDecorationType(Integer decorationType) {
    
        this.decorationType = decorationType;
    }

    /**
     * @return  the 【decorationTypeName】
     */
    public String getDecorationTypeName() {
    
        return decorationTypeName;
    }

    /**
     * @param decorationTypeName the 【decorationTypeName】 to set
     */
    public void setDecorationTypeName(String decorationTypeName) {
    
        this.decorationTypeName = decorationTypeName;
    }

    /**
     * @return  the 【decorateSituate】
     */
    public Integer getDecorateSituate() {
    
        return decorateSituate;
    }

    /**
     * @param decorateSituate the 【decorateSituate】 to set
     */
    public void setDecorateSituate(Integer decorateSituate) {
    
        this.decorateSituate = decorateSituate;
    }

    /**
     * @return  the 【decorateSituateName】
     */
    public String getDecorateSituateName() {
    
        return decorateSituateName;
    }

    /**
     * @param decorateSituateName the 【decorateSituateName】 to set
     */
    public void setDecorateSituateName(String decorateSituateName) {
    
        this.decorateSituateName = decorateSituateName;
    }

    /**
     * @return  the 【decorateCompany】
     */
    public String getDecorateCompany() {
    
        return decorateCompany;
    }

    /**
     * @param decorateCompany the 【decorateCompany】 to set
     */
    public void setDecorateCompany(String decorateCompany) {
    
        this.decorateCompany = decorateCompany;
    }

    public BigDecimal getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(BigDecimal receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

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
   	

}