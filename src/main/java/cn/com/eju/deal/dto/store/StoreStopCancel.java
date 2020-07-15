package cn.com.eju.deal.dto.store;

import cn.com.eju.deal.dto.common.FileRecordMainDto;

import java.util.Date;
import java.util.List;

public class StoreStopCancel{
    private Integer id;
    private String cancelNo;
    private Integer storeId;
    private String cancelReason;
    private Integer approveStatus;
    private Date approveDate;
    private Integer approveId;
    private String approveDesc;
    private String fileRecordMainIds;

    private Integer userCreate;
    private Date dateCreate;
    private Integer userUpdate;
    private Date dateUpdate;
    private Integer delFlag;

    private String storeNo;
    private String storeName;
    private String addressDetail;
    private String maintainerName;
    private String centerName;
    private String userCreateNm;
    private String approveStatusNm;
    private String userCode;
    private String userName;
    private List<FileRecordMainDto> fileList;

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

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public Integer getApproveId() {
        return approveId;
    }

    public void setApproveId(Integer approveId) {
        this.approveId = approveId;
    }

    public String getApproveDesc() {
        return approveDesc;
    }

    public void setApproveDesc(String approveDesc) {
        this.approveDesc = approveDesc;
    }

    public String getFileRecordMainIds() {
        return fileRecordMainIds;
    }

    public void setFileRecordMainIds(String fileRecordMainIds) {
        this.fileRecordMainIds = fileRecordMainIds;
    }

    public Integer getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(Integer userCreate) {
        this.userCreate = userCreate;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Integer getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(Integer userUpdate) {
        this.userUpdate = userUpdate;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
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

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getMaintainerName() {
        return maintainerName;
    }

    public void setMaintainerName(String maintainerName) {
        this.maintainerName = maintainerName;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getUserCreateNm() {
        return userCreateNm;
    }

    public void setUserCreateNm(String userCreateNm) {
        this.userCreateNm = userCreateNm;
    }

    public String getApproveStatusNm() {
        return approveStatusNm;
    }

    public void setApproveStatusNm(String approveStatusNm) {
        this.approveStatusNm = approveStatusNm;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<FileRecordMainDto> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileRecordMainDto> fileList) {
        this.fileList = fileList;
    }

    public String getCancelNo() {
        return cancelNo;
    }

    public void setCancelNo(String cancelNo) {
        this.cancelNo = cancelNo;
    }
}
