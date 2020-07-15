package cn.com.eju.deal.dto.kefuOrder;

import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.dto.contract.ContractFileDto;

import java.util.List;

/**
 * 客服工单
 */
public class KeFuOrderDto extends KeFuOrder {
	
	private Integer operatorId;//经办人id
	private String userName;//经办人姓名
	private String companyName;//经纪公司
	private String createName;//创建人姓名
	private String dealStatusNm;//问题状态
	private String checkStatusNm;//核验状态
	private String categoryOneNm;//一级分类名称
	private String categoryTwoNm;//二级分类名称
	private String questionLevelNm;  // 工单优先级
	private String storeNo;//门店编号
	private String storeName;//门店名称
	private String storeCityNo;
	private String storeCityName;//门店城市
	private String storeAcCityNo;
	private String storeAddress;
	private String storeAcCityName;//门店业绩城市
	private String cityName;//归属城市
	private String fileRecordMainIds;// 文件ID（数组）
	private List<ContractFileDto> oldFileRecordMain;//编辑保存用原文件
	private List<ContractFileDto> orderFileList;// 附件列表
	private String dealStatusStr;//问题状态str
	private String checkStatusStr;//校验状态str
	private String consultProductNm; //咨询产品名称
	private Integer keFuOrderReplyId;//客户回复id
	private Integer verifyUserCode;
	private String verifyUserName;
	private String questionTopic;

	
	public Integer getKeFuOrderReplyId() {
		return keFuOrderReplyId;
	}
	public void setKeFuOrderReplyId(Integer keFuOrderReplyId) {
		this.keFuOrderReplyId = keFuOrderReplyId;
	}
	public String getDealStatusStr() {
		return dealStatusStr;
	}
	public void setDealStatusStr(String dealStatusStr) {
		this.dealStatusStr = dealStatusStr;
	}
	public String getCheckStatusStr() {
		return checkStatusStr;
	}
	public void setCheckStatusStr(String checkStatusStr) {
		this.checkStatusStr = checkStatusStr;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Integer getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getDealStatusNm() {
		return dealStatusNm;
	}
	public void setDealStatusNm(String dealStatusNm) {
		this.dealStatusNm = dealStatusNm;
	}
	public String getCategoryOneNm() {
		return categoryOneNm;
	}
	public void setCategoryOneNm(String categoryOneNm) {
		this.categoryOneNm = categoryOneNm;
	}
	public String getCategoryTwoNm() {
		return categoryTwoNm;
	}
	public void setCategoryTwoNm(String categoryTwoNm) {
		this.categoryTwoNm = categoryTwoNm;
	}
	public String getQuestionLevelNm() {
		return questionLevelNm;
	}
	public void setQuestionLevelNm(String questionLevelNm) {
		this.questionLevelNm = questionLevelNm;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreCityName() {
		return  storeCityName;//SystemParam.getCityNameByCityNo(storeCityNo)
	}
	public void setStoreCityName(String storeCityName) {
		this.storeCityName = storeCityName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getFileRecordMainIds() {
		return fileRecordMainIds;
	}
	public void setFileRecordMainIds(String fileRecordMainIds) {
		this.fileRecordMainIds = fileRecordMainIds;
	}
	public List<ContractFileDto> getOldFileRecordMain() {
		return oldFileRecordMain;
	}
	public void setOldFileRecordMain(List<ContractFileDto> oldFileRecordMain) {
		this.oldFileRecordMain = oldFileRecordMain;
	}
	public List<ContractFileDto> getOrderFileList() {
		return orderFileList;
	}
	public void setOrderFileList(List<ContractFileDto> orderFileList) {
		this.orderFileList = orderFileList;
	}
	public String getCheckStatusNm() {
		return checkStatusNm;
	}
	public void setCheckStatusNm(String checkStatusNm) {
		this.checkStatusNm = checkStatusNm;
	}
	public void setStoreAcCityName(String storeAcCityName) {
		this.storeAcCityName = storeAcCityName;
	}
	public String getStoreCityNo() {
		return storeCityNo;
	}
	public void setStoreCityNo(String storeCityNo) {
		this.storeCityNo = storeCityNo;
	}
	public String getStoreAcCityNo() {
		return storeAcCityNo;
	}
	public void setStoreAcCityNo(String storeAcCityNo) {
		this.storeAcCityNo = storeAcCityNo;
	}
	public String getStoreAddress() {
		return storeAddress;
	}
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	public String getConsultProductNm() {
		return consultProductNm;
	}
	public void setConsultProductNm(String consultProductNm) {
		this.consultProductNm = consultProductNm;
	}
	public Integer getVerifyUserCode() {
		return verifyUserCode;
	}
	public void setVerifyUserCode(Integer verifyUserCode) {
		this.verifyUserCode = verifyUserCode;
	}
	public String getVerifyUserName() {
		return verifyUserName;
	}
	public void setVerifyUserName(String verifyUserName) {
		this.verifyUserName = verifyUserName;
	}

	@Override
	public String getQuestionTopic() {
		return questionTopic;
	}

	@Override
	public void setQuestionTopic(String questionTopic) {
		this.questionTopic = questionTopic;
	}
}
  