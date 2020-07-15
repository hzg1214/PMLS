package cn.com.eju.deal.dto.oa;

import java.util.Date;

/**
 * 合同变更附件上传Model层
 * 
 * @author sunmm
 * @date 2016年8月4日 下午9:16:13
 */
public class OaAttachmentDto {
	/**
	 * ID
	 */
	private Integer id;

	/**
	 * 上传的文件编号
	 */
	private String fileCode;

	/**
	 * 文件名称
	 */
	private String fileName;

	/**
	 * 合同变更编号
	 */
	private Integer contractStopId;

	/**
	 * 上传的文件类型
	 */
	private String fileTypeCode;

	/**
	 * 创建日期
	 */
	private Date dateCreate;

	/**
	 * 创建人
	 */
	private Integer userCreate;

	/**
	 * 删除标识
	 */
	private Boolean delFlag;
	
	/**
	 * 更新日期
	 */
	private Date updateDate;

	/**
	 * 更新操作人
	 */
	private Integer updateCreate;

	/**
	 * CRIC图片ID
	 */
	private Integer fileRecordMainId;

	/** 
	* 获取CRIC图片ID
	* @return fileRecordMainId CRIC图片ID
	*/
	public Integer getFileRecordMainId() {
		return fileRecordMainId;
	}

	/** 
	* 设置CRIC图片ID
	* @param fileRecordMainId CRIC图片ID
	*/
	public void setFileRecordMainId(Integer fileRecordMainId) {
		this.fileRecordMainId = fileRecordMainId;
	}
	
	/** 
	* 获取更新日期
	* @return 更新日期
	*/
	public Date getUpdateDate() {
		return updateDate;
	}

	/** 
	* 设置更新日期
	* @param updateDate 更新日期
	*/
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/** 
	* 获取更新操作人
	* @return 更新操作人
	*/
	public Integer getUpdateCreate() {
		return updateCreate;
	}

	/** 
	* 设置更新操作人
	* @param updateCreate 更新操作人
	*/
	public void setUpdateCreate(Integer updateCreate) {
		this.updateCreate = updateCreate;
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
	 * 获取上传的文件编号
	 * 
	 * @return 上传的文件编号
	 */
	public String getFileCode() {
		return fileCode;
	}

	/**
	 * 设置上传的文件编号
	 * 
	 * @param fileNo
	 *            上传的文件编号
	 */
	public void setFileCode(String fileCode) {
		this.fileCode = fileCode == null ? null : fileCode.trim();
	}

	/**
	 * 获取文件名称
	 * 
	 * @return fileName 文件名称
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * 设置文件名称
	 * 
	 * @param fileName
	 *            文件名称
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName == null ? null : fileName.trim();
	}

	/**
	 * 获取合同变更编号
	 * 
	 * @return contractStopId 合同变更编号
	 */
	public Integer getContractStopId() {
		return contractStopId;
	}

	/**
	 * 设置合同变更编号
	 * 
	 * @param contractStopId
	 *            合同变更编号
	 */
	public void setContractStopId(Integer contractStopId) {
		this.contractStopId = contractStopId;
	}

	/**
	 * 获取上传的文件类型
	 * 
	 * @return fileTypeCode 上传的文件类型
	 */
	public String getFileTypeCode() {
		return fileTypeCode;
	}

	/**
	 * 设置上传的文件类型
	 * 
	 * @param fileTypeCode
	 *            上传的文件类型
	 */
	public void setFileTypeCode(String fileTypeCode) {
		this.fileTypeCode = fileTypeCode == null ? null : fileTypeCode.trim();
	}

	/**
	 * 获取创建日期
	 * 
	 * @return dateCreate 创建日期
	 */
	public Date getDateCreate() {
		return dateCreate;
	}

	/**
	 * 设置创建日期
	 * 
	 * @param dateCreate
	 *            创建日期
	 */
	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	/**
	 * 获取创建人
	 * 
	 * @return userCreate创建人
	 */
	public Integer getUserCreate() {
		return userCreate;
	}

	/**
	 * 设置创建人
	 * 
	 * @param userCreate
	 *            创建人
	 */
	public void setUserCreate(Integer userCreate) {
		this.userCreate = userCreate;
	}

	/**
	 * 获取删除标识
	 * 
	 * @return delFlag 删除标识
	 */
	public Boolean getDelFlag() {
		return delFlag;
	}

	/**
	 * 设置删除标识
	 * 
	 * @param delFlag
	 *            删除标识
	 */
	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}
}