package cn.com.eju.deal.dto.contract;
/**   
* 保证金Mode 用于给OMS接口传数据
* @author wenhui.zhang
* @date 2016年8月18日 上午11:10
*/
public class DepositDto {
    /**
     * 合同编号
     */
    private String contractNo;

    /**
     * 合同类型(A版、B版、A转B版、B转A版)
     */
    private String contractType;

    /**
     * 合同状态(草签、审核通过、审核驳回、作废)
     */
    private String contractState;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 保证金总额
     */
    private Long itemAmount;
    
    /**
     * 合作协议书编号
     */
    private String agreementNo;
    
    /**
     *  城市No
     */
    private String cityNo;

	public String getCityNo() {
		return cityNo;
	}

	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}

	public String getAgreementNo()
    {
        return agreementNo;
    }

    public void setAgreementNo(String agreementNo)
    {
        this.agreementNo = agreementNo;
    }

    public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getContractState() {
		return contractState;
	}

	public void setContractState(String contractState) {
		this.contractState = contractState;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Long getItemAmount() {
		return itemAmount;
	}

	public void setItemAmount(Long itemAmount) {
		this.itemAmount = itemAmount;
	}
}