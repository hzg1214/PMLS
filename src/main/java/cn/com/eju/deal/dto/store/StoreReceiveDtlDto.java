package cn.com.eju.deal.dto.store;

import java.math.BigDecimal;
import java.util.Date;

import cn.com.eju.deal.core.model.BaseModel;

public class StoreReceiveDtlDto extends BaseModel{

    /**
     * 
     */
    private static final long serialVersionUID = 5179715003746488696L;

    private Integer id;
    private Integer receiveId;
    private String storeNo;
    private BigDecimal amount;
    private String providerCode;
    private String providerName;
    private Date dateCreate;
    private Integer userIdCreate;
    private Date dateUpt;
    private Integer userIdUpt;
    private String delFlag;
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * @return the receiveId
     */
    public Integer getReceiveId() {
        return receiveId;
    }
    /**
     * @param receiveId the receiveId to set
     */
    public void setReceiveId(Integer receiveId) {
        this.receiveId = receiveId;
    }
    /**
     * @return the storeNo
     */
    public String getStoreNo() {
        return storeNo;
    }
    /**
     * @param storeNo the storeNo to set
     */
    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }
    /**
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }
    /**
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    /**
     * @return the providerCode
     */
    public String getProviderCode() {
        return providerCode;
    }
    /**
     * @param providerCode the providerCode to set
     */
    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }
    /**
     * @return the providerName
     */
    public String getProviderName() {
        return providerName;
    }
    /**
     * @param providerName the providerName to set
     */
    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
    /**
     * @return the dateCreate
     */
    public Date getDateCreate() {
        return dateCreate;
    }
    /**
     * @param dateCreate the dateCreate to set
     */
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }
    /**
     * @return the userIdCreate
     */
    public Integer getUserIdCreate() {
        return userIdCreate;
    }
    /**
     * @param userIdCreate the userIdCreate to set
     */
    public void setUserIdCreate(Integer userIdCreate) {
        this.userIdCreate = userIdCreate;
    }
    /**
     * @return the dateUpt
     */
    public Date getDateUpt() {
        return dateUpt;
    }
    /**
     * @param dateUpt the dateUpt to set
     */
    public void setDateUpt(Date dateUpt) {
        this.dateUpt = dateUpt;
    }
    /**
     * @return the userIdUpt
     */
    public Integer getUserIdUpt() {
        return userIdUpt;
    }
    /**
     * @param userIdUpt the userIdUpt to set
     */
    public void setUserIdUpt(Integer userIdUpt) {
        this.userIdUpt = userIdUpt;
    }
    /**
     * @return the delFlag
     */
    public String getDelFlag() {
        return delFlag;
    }
    /**
     * @param delFlag the delFlag to set
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
