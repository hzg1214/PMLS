package cn.com.eju.deal.dto.store;


/**
 * 
* @Title: StoreMaintenanceDto
* @date:2017年12月21日下午3:13:20
* @version V1.0
 */
public class StoreMaintenanceDto {
	private Integer id;
	//门店id
	private Integer storeId;
	//门店编号
	private String storeNo;
	//门店名称
	private String name;
	//门店详情地址
	private String addressDetail;
	//合作模式
	private Integer contractType;
	private String contractTypeName;
	//合同状态
	private Integer ContractStatus;
	private String contractStatusName;
	//门店所属中心
	private String groupName;
	//维护人
	private String maintainer;
	private String maintainerName;
	//维护人所属中心
	private String centername;
	//员工在职状态
	private Integer inStatus;
	//门店中心id
	private Integer storeCenterId;
	
	//标识
	private Integer sortindex;
	//显示字体
	private String titleLabel;
	
	
	public Integer getSortindex() {
		return sortindex;
	}
	public void setSortindex(Integer sortindex) {
		this.sortindex = sortindex;
	}
	public String getTitleLabel() {
		return titleLabel;
	}
	public void setTitleLabel(String titleLabel) {
		this.titleLabel = titleLabel;
	}
	public Integer getStoreCenterId() {
		return storeCenterId;
	}
	public void setStoreCenterId(Integer storeCenterId) {
		this.storeCenterId = storeCenterId;
	}
	public Integer getInStatus() {
		return inStatus;
	}
	public void setInStatus(Integer inStatus) {
		this.inStatus = inStatus;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public Integer getContractType() {
		return contractType;
	}
	public void setContractType(Integer contractType) {
		this.contractType = contractType;
	}
	public String getContractTypeName() {
		return contractTypeName;
	}
	public void setContractTypeName(String contractTypeName) {
		this.contractTypeName = contractTypeName;
	}
	public Integer getContractStatus() {
		return ContractStatus;
	}
	public void setContractStatus(Integer contractStatus) {
		ContractStatus = contractStatus;
	}
	public String getContractStatusName() {
		return contractStatusName;
	}
	public void setContractStatusName(String contractStatusName) {
		this.contractStatusName = contractStatusName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public String getMaintainer() {
		return maintainer;
	}
	public void setMaintainer(String maintainer) {
		this.maintainer = maintainer;
	}
	public String getMaintainerName() {
		return maintainerName;
	}
	public void setMaintainerName(String maintainerName) {
		this.maintainerName = maintainerName;
	}
	public String getCentername() {
		return centername;
	}
	public void setCentername(String centername) {
		this.centername = centername;
	}
	
	
}
  