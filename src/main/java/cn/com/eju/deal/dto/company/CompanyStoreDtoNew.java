package cn.com.eju.deal.dto.company;

import java.io.Serializable;

/**
 * 终止check公司和门店是否已取消关联
* @Title: CompanyDtoNew
 */
public class CompanyStoreDtoNew implements Serializable{
	private Integer id;
	private Integer companyId;
	private Integer storeId;
	/**
     * 是否删除
     */
    private boolean isDelete;
    private Integer datacount;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public boolean isDelete() {
		return isDelete;
	}
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getDatacount() {
		return datacount;
	}
	public void setDatacount(Integer datacount) {
		this.datacount = datacount;
	}
    
}
  