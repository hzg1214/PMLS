package cn.com.eju.deal.dto.kefuWj;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by eju on 2019/7/15.
 */
public class KeFuWjAnalyseDto {

    private String acCityName;

    private String cityName;

    private String centerName;

    private String companyNo;

    private String companyName;

    private String storeNo;

    private String storeName;

    private String storeAddress;

    private String storeManager;

    private String storeManagerPhone;

    private String wjfkTel;

    private Date dateCreate;

    private String storeStatusName;

    private String wjType;

    private List<WjAnalyseTmScoreDto> wjAnalyseTmScores;

    private List<WjAnalyseFlScoreDto> wjAnalyseFlScores;

    private BigDecimal wjdcTotalscore;

    public List<WjAnalyseFlScoreDto> getWjAnalyseFlScores() {
        return wjAnalyseFlScores;
    }

    public void setWjAnalyseFlScores(List<WjAnalyseFlScoreDto> wjAnalyseFlScores) {
        this.wjAnalyseFlScores = wjAnalyseFlScores;
    }

    public String getWjType() {
        return wjType;
    }

    public void setWjType(String wjType) {
        this.wjType = wjType;
    }

    public String getAcCityName() {
        return acCityName;
    }

    public void setAcCityName(String acCityName) {
        this.acCityName = acCityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreManager() {
        return storeManager;
    }

    public void setStoreManager(String storeManager) {
        this.storeManager = storeManager;
    }

    public String getStoreManagerPhone() {
        return storeManagerPhone;
    }

    public void setStoreManagerPhone(String storeManagerPhone) {
        this.storeManagerPhone = storeManagerPhone;
    }

    public String getWjfkTel() {
        return wjfkTel;
    }

    public void setWjfkTel(String wjfkTel) {
        this.wjfkTel = wjfkTel;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getStoreStatusName() {
        return storeStatusName;
    }

    public void setStoreStatusName(String storeStatusName) {
        this.storeStatusName = storeStatusName;
    }

    public List<WjAnalyseTmScoreDto> getWjAnalyseTmScores() {
        return wjAnalyseTmScores;
    }

    public void setWjAnalyseTmScores(List<WjAnalyseTmScoreDto> wjAnalyseTmScores) {
        this.wjAnalyseTmScores = wjAnalyseTmScores;
    }

    public BigDecimal getWjdcTotalscore() {
        return wjdcTotalscore;
    }

    public void setWjdcTotalscore(BigDecimal wjdcTotalscore) {
        this.wjdcTotalscore = wjdcTotalscore;
    }
}
