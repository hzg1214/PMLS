package cn.com.eju.deal.dto.contract;

import java.io.Serializable;
import java.util.List;

import cn.com.eju.deal.dto.contacts.ContactsDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.dto.store.StoreMaintainerDto;

/**   
* ContractInfoDto  Dto
* @author (qianwei)
* @date 2016年3月22日 下午4:42:54
*/
public class ContractInfoDto implements Serializable
{
    /**
    * 
    */ 
    private static final long serialVersionUID = -5775427213591322713L;
    // 合同详情
    private ContractDto contract;
    // 产品列表
    private List<ProductDto> productDetails;
    // 关联门店列表
	private List<StoreDto> storeDetails;
	
	
	// 营业证文件列表
	private List<ContractFileDto> fileRecordMainBusiness;
	// 身份证文件列表
	private List<ContractFileDto> fileRecordMainCard;
	// 合同照片文件列表
	private List<ContractFileDto> fileRecordMainPhoto;
	// 门店照片文件列表
	private List<ContractFileDto> fileRecordMainStore;
	// 房友系统申请安装单文件列表
	private List<ContractFileDto> fileRecordMainInstall;
	//重要提示函列表
	private List<ContractFileDto> fileRecordMainNotice;
	//合同补充协议列表
	private List<ContractFileDto> fileRecordMainComplement;
	// 其他文件列表
	private List<ContractFileDto> fileRecordMainOther;
	// 文件ID（数组）
	private String fileRecordMainIds;
	
	// 文件列表
	private List<ContractFileDto> fileRecordMain;
	// 文件列表（保存用）
	private List<ContractFileDto> oldFileRecordMain;
	// 关联门店列表（保存用）
	private String oldStoreIdArray;
	// 联系人列表
	private List<ContactsDto> contactsDtoList;
	// 维护人列表
	private List<StoreMaintainerDto> StoreMaintainerDtoList;

	/**
	 * 合同变更信息
	 */
	private ContractChangeDto contractChangeDto;
	
	/**
	 * 审核结果(0:正常结束、5:撤销、15:终止 )
	 */
	private Integer auditRst;
	
    // 图片ID（数组）
    private String contractChangePicIds;

    
    
    public List<ContractFileDto> getFileRecordMainNotice() {
		return fileRecordMainNotice;
	}

	public void setFileRecordMainNotice(List<ContractFileDto> fileRecordMainNotice) {
		this.fileRecordMainNotice = fileRecordMainNotice;
	}

	public String getContractChangePicIds()
    {
        return contractChangePicIds;
    }

    public void setContractChangePicIds(String contractChangePicIds)
    {
        this.contractChangePicIds = contractChangePicIds;
    }

    public List<StoreMaintainerDto> getStoreMaintainerDtoList() {
		return StoreMaintainerDtoList;
	}

	public void setStoreMaintainerDtoList(
			List<StoreMaintainerDto> storeMaintainerDtoList) {
		StoreMaintainerDtoList = storeMaintainerDtoList;
	}

	public List<ContactsDto> getContactsDtoList() {
		return contactsDtoList;
	}

	public void setContactsDtoList(List<ContactsDto> contactsDtoList) {
		this.contactsDtoList = contactsDtoList;
	}

	public ContractDto getContract() {
		return contract;
	}

	public void setContract(ContractDto contract) {
		this.contract = contract;
	}

	public List<ProductDto> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(List<ProductDto> productDetails) {
		this.productDetails = productDetails;
	}

	public List<StoreDto> getStoreDetails() {
		return storeDetails;
	}

	public void setStoreDetails(List<StoreDto> storeDetails) {
		this.storeDetails = storeDetails;
	}

	public List<ContractFileDto> getFileRecordMainBusiness() {
		return fileRecordMainBusiness;
	}

	public void setFileRecordMainBusiness(List<ContractFileDto> fileRecordMainBusiness) {
		this.fileRecordMainBusiness = fileRecordMainBusiness;
	}

	public List<ContractFileDto> getFileRecordMainCard() {
		return fileRecordMainCard;
	}

	public void setFileRecordMainCard(List<ContractFileDto> fileRecordMainCard) {
		this.fileRecordMainCard = fileRecordMainCard;
	}

	public List<ContractFileDto> getFileRecordMainPhoto() {
		return fileRecordMainPhoto;
	}

	public void setFileRecordMainPhoto(List<ContractFileDto> fileRecordMainPhoto) {
		this.fileRecordMainPhoto = fileRecordMainPhoto;
	}

	public String getFileRecordMainIds() {
		return fileRecordMainIds;
	}

	public void setFileRecordMainIds(String fileRecordMainIds) {
		this.fileRecordMainIds = fileRecordMainIds;
	}

	public List<ContractFileDto> getFileRecordMain() {
		return fileRecordMain;
	}

	public void setFileRecordMain(List<ContractFileDto> fileRecordMain) {
		this.fileRecordMain = fileRecordMain;
	}

	public List<ContractFileDto> getOldFileRecordMain() {
		return oldFileRecordMain;
	}

	public void setOldFileRecordMain(List<ContractFileDto> oldFileRecordMain) {
		this.oldFileRecordMain = oldFileRecordMain;
	}

	public String getOldStoreIdArray() {
		return oldStoreIdArray;
	}

	public void setOldStoreIdArray(String oldStoreIdArray) {
		this.oldStoreIdArray = oldStoreIdArray;
	}

	public List<ContractFileDto> getFileRecordMainStore() {
		return fileRecordMainStore;
	}

	public void setFileRecordMainStore(List<ContractFileDto> fileRecordMainStore) {
		this.fileRecordMainStore = fileRecordMainStore;
	}

	public List<ContractFileDto> getFileRecordMainInstall() {
		return fileRecordMainInstall;
	}

	public void setFileRecordMainInstall(List<ContractFileDto> fileRecordMainInstall) {
		this.fileRecordMainInstall = fileRecordMainInstall;
	}

	public List<ContractFileDto> getFileRecordMainOther() {
		return fileRecordMainOther;
	}

	public void setFileRecordMainOther(List<ContractFileDto> fileRecordMainOther) {
		this.fileRecordMainOther = fileRecordMainOther;
	}

	/** 
	* 获取合同变更信息
	* @return contractChangeDto 合同变更信息
	*/
	public ContractChangeDto getContractChangeDto() {
		return contractChangeDto;
	}

	/** 
	* 设置合同变更信息
	* @param contractChangeDto 合同变更信息
	*/
	public void setContractChangeDto(ContractChangeDto contractChangeDto) {
		this.contractChangeDto = contractChangeDto;
	}

	/** 
	* 获取审核结果(0:正常结束、5:撤销、15:终止 )
	* @return auditRst 审核结果(0:正常结束、5:撤销、15:终止 )
	*/
	public Integer getAuditRst() {
		return auditRst;
	}

	/** 
	* 设置审核结果(0:正常结束、5:撤销、15:终止 )
	* @param auditRst 审核结果(0:正常结束、5:撤销、15:终止 )
	*/
	public void setAuditRst(Integer auditRst) {
		this.auditRst = auditRst;
	}
	private Integer stamp;



	public Integer getStamp() {
		return stamp;
	}

	public void setStamp(Integer stamp) {
		this.stamp = stamp;
	}

	public List<ContractFileDto> getFileRecordMainComplement() {
		return fileRecordMainComplement;
	}

	public void setFileRecordMainComplement(List<ContractFileDto> fileRecordMainComplement) {
		this.fileRecordMainComplement = fileRecordMainComplement;
	}
	
}
