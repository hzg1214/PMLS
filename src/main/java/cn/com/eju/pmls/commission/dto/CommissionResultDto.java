package cn.com.eju.pmls.commission.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by eju on 2019/4/3.
 */
public class CommissionResultDto implements Serializable {
    private String rowNo;//序号
    private String companyNo;	         //公司编号
    private String companyName;
    private String detailId;
    private String projectNo;	         //项目编号
    private String estateId;             //楼盘ID
    private String reportId;             //报备ID
    private String estateNm;             //楼盘名称
    private String buildingNo;           //楼室号
    private String dealAmount;           //成销总价
    private String dealDate;             //成销日期
    private Integer num;                 //套数
    private String cityNo;//城市编号

    private Integer reportdyId;
    private String befTaxAmount;//税前
    private String aftTaxAmount;//税后
    private String recordDate;//日期

    private String crtEmpId;
    private String uptEmpId;
    private String delFlag;

    private String befTaxAmountTotal;//税前小计
    private String aftTaxAmountTotal;//税后小计

    private String befTaxAmount1;//税前
    private String aftTaxAmount1;//税后
    private String recordDate1;//日期
    private String befTaxAmount2;//税前
    private String aftTaxAmount2;//税后
    private String recordDate2;//日期
    private String befTaxAmount3;//税前
    private String aftTaxAmount3;//税后
    private String recordDate3;//日期
    private String befTaxAmount4;//税前
    private String aftTaxAmount4;//税后
    private String recordDate4;//日期
    private String befTaxAmount5;//税前
    private String aftTaxAmount5;//税后
    private String recordDate5;//日期
    private String befTaxAmount6;//税前
    private String aftTaxAmount6;//税后
    private String recordDate6;//日期

    private Integer switchFlag;
    private Integer switchFlag1;
    private Integer switchFlag2;
    private Integer switchFlag3;
    private Integer switchFlag4;
    private Integer switchFlag5;
    private Integer switchFlag6;

    private Integer estateType;

    private Integer defaultFlag;

    private String userCode;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(Integer defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public Integer getEstateType() {
        return estateType;
    }

    public void setEstateType(Integer estateType) {
        this.estateType = estateType;
    }

    public Integer getSwitchFlag() {
        return switchFlag;
    }

    public void setSwitchFlag(Integer switchFlag) {
        this.switchFlag = switchFlag;
    }

    public Integer getSwitchFlag1() {
        return switchFlag1;
    }

    public void setSwitchFlag1(Integer switchFlag1) {
        this.switchFlag1 = switchFlag1;
    }

    public Integer getSwitchFlag2() {
        return switchFlag2;
    }

    public void setSwitchFlag2(Integer switchFlag2) {
        this.switchFlag2 = switchFlag2;
    }

    public Integer getSwitchFlag3() {
        return switchFlag3;
    }

    public void setSwitchFlag3(Integer switchFlag3) {
        this.switchFlag3 = switchFlag3;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    private List<CommissionResultDto> commissionResultDtoList;

    public List<CommissionResultDto> getCommissionResultDtoList() {
        return commissionResultDtoList;
    }

    public void setCommissionResultDtoList(List<CommissionResultDto> commissionResultDtoList) {
        this.commissionResultDtoList = commissionResultDtoList;
    }

    public String getRowNo() {
        return rowNo;
    }

    public void setRowNo(String rowNo) {
        this.rowNo = rowNo;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getEstateId() {
        return estateId;
    }

    public void setEstateId(String estateId) {
        this.estateId = estateId;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getEstateNm() {
        return estateNm;
    }

    public void setEstateNm(String estateNm) {
        this.estateNm = estateNm;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(String dealAmount) {
        this.dealAmount = dealAmount;
    }

    public String getDealDate() {
        return dealDate;
    }

    public void setDealDate(String dealDate) {
        this.dealDate = dealDate;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo;
    }

    public Integer getReportdyId() {
        return reportdyId;
    }

    public void setReportdyId(Integer reportdyId) {
        this.reportdyId = reportdyId;
    }

    public String getBefTaxAmount() {
        return befTaxAmount;
    }

    public void setBefTaxAmount(String befTaxAmount) {
        this.befTaxAmount = befTaxAmount;
    }

    public String getAftTaxAmount() {
        return aftTaxAmount;
    }

    public void setAftTaxAmount(String aftTaxAmount) {
        this.aftTaxAmount = aftTaxAmount;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getCrtEmpId() {
        return crtEmpId;
    }

    public void setCrtEmpId(String crtEmpId) {
        this.crtEmpId = crtEmpId;
    }

    public String getUptEmpId() {
        return uptEmpId;
    }

    public void setUptEmpId(String uptEmpId) {
        this.uptEmpId = uptEmpId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getBefTaxAmountTotal() {
        return befTaxAmountTotal;
    }

    public void setBefTaxAmountTotal(String befTaxAmountTotal) {
        this.befTaxAmountTotal = befTaxAmountTotal;
    }

    public String getAftTaxAmountTotal() {
        return aftTaxAmountTotal;
    }

    public void setAftTaxAmountTotal(String aftTaxAmountTotal) {
        this.aftTaxAmountTotal = aftTaxAmountTotal;
    }

    public String getBefTaxAmount1() {
        return befTaxAmount1;
    }

    public void setBefTaxAmount1(String befTaxAmount1) {
        this.befTaxAmount1 = befTaxAmount1;
    }

    public String getAftTaxAmount1() {
        return aftTaxAmount1;
    }

    public void setAftTaxAmount1(String aftTaxAmount1) {
        this.aftTaxAmount1 = aftTaxAmount1;
    }

    public String getRecordDate1() {
        return recordDate1;
    }

    public void setRecordDate1(String recordDate1) {
        this.recordDate1 = recordDate1;
    }

    public String getBefTaxAmount2() {
        return befTaxAmount2;
    }

    public void setBefTaxAmount2(String befTaxAmount2) {
        this.befTaxAmount2 = befTaxAmount2;
    }

    public String getAftTaxAmount2() {
        return aftTaxAmount2;
    }

    public void setAftTaxAmount2(String aftTaxAmount2) {
        this.aftTaxAmount2 = aftTaxAmount2;
    }

    public String getRecordDate2() {
        return recordDate2;
    }

    public void setRecordDate2(String recordDate2) {
        this.recordDate2 = recordDate2;
    }

    public String getBefTaxAmount3() {
        return befTaxAmount3;
    }

    public void setBefTaxAmount3(String befTaxAmount3) {
        this.befTaxAmount3 = befTaxAmount3;
    }

    public String getAftTaxAmount3() {
        return aftTaxAmount3;
    }

    public void setAftTaxAmount3(String aftTaxAmount3) {
        this.aftTaxAmount3 = aftTaxAmount3;
    }

    public String getRecordDate3() {
        return recordDate3;
    }

    public void setRecordDate3(String recordDate3) {
        this.recordDate3 = recordDate3;
    }

    public String getBefTaxAmount4() {
        return befTaxAmount4;
    }

    public void setBefTaxAmount4(String befTaxAmount4) {
        this.befTaxAmount4 = befTaxAmount4;
    }

    public String getAftTaxAmount4() {
        return aftTaxAmount4;
    }

    public void setAftTaxAmount4(String aftTaxAmount4) {
        this.aftTaxAmount4 = aftTaxAmount4;
    }

    public String getRecordDate4() {
        return recordDate4;
    }

    public void setRecordDate4(String recordDate4) {
        this.recordDate4 = recordDate4;
    }

    public String getBefTaxAmount5() {
        return befTaxAmount5;
    }

    public void setBefTaxAmount5(String befTaxAmount5) {
        this.befTaxAmount5 = befTaxAmount5;
    }

    public String getAftTaxAmount5() {
        return aftTaxAmount5;
    }

    public void setAftTaxAmount5(String aftTaxAmount5) {
        this.aftTaxAmount5 = aftTaxAmount5;
    }

    public String getRecordDate5() {
        return recordDate5;
    }

    public void setRecordDate5(String recordDate5) {
        this.recordDate5 = recordDate5;
    }

    public String getBefTaxAmount6() {
        return befTaxAmount6;
    }

    public void setBefTaxAmount6(String befTaxAmount6) {
        this.befTaxAmount6 = befTaxAmount6;
    }

    public String getAftTaxAmount6() {
        return aftTaxAmount6;
    }

    public void setAftTaxAmount6(String aftTaxAmount6) {
        this.aftTaxAmount6 = aftTaxAmount6;
    }

    public String getRecordDate6() {
        return recordDate6;
    }

    public void setRecordDate6(String recordDate6) {
        this.recordDate6 = recordDate6;
    }

    public Integer getSwitchFlag4() {
        return switchFlag4;
    }

    public void setSwitchFlag4(Integer switchFlag4) {
        this.switchFlag4 = switchFlag4;
    }

    public Integer getSwitchFlag5() {
        return switchFlag5;
    }

    public void setSwitchFlag5(Integer switchFlag5) {
        this.switchFlag5 = switchFlag5;
    }

    public Integer getSwitchFlag6() {
        return switchFlag6;
    }

    public void setSwitchFlag6(Integer switchFlag6) {
        this.switchFlag6 = switchFlag6;
    }
}
