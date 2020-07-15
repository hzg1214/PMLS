package cn.com.eju.deal.dto.scene;

import java.io.Serializable;


public class PadCommissionResultDto implements Serializable {

	private String rowNo;//序号
	private String projectNo;	         //项目编号
	private String estateId;             //楼盘ID
	private String reportId;             //报备ID
	private String estateNm;             //楼盘名称
	private String buildingNo;           //楼室号
	private String roughAmount;           //大定总价
	private String roughDate;             //大定日期
	private String dealAmount;           //成销总价
	private String dealDate;             //成销日期
	private Integer projectDepartmentId; //项目归属部门
	private Integer num;                 //套数
	private String cityNo;//城市编号

    private Integer reportdyId;
	private String befTaxYjAmount;//应垫佣金税前
	private String aftTaxYjAmount;//应垫佣金税后
	private String befTaxSjAmount;//实垫佣金税前
	private String aftTaxSjAmount;//实垫佣金税后
	private String recordDate;//垫佣日期

	private String yjRecordDate;//应垫日期
	private String sjRecordDate;//实垫日期

	private String crtEmpId;
	private String uptEmpId;
	private String delFlag;

	private String befTaxYjAmountTotal;//应垫佣金税前小计
	private String aftTaxYjAmountTotal;//应垫佣金税后小计
	private String befTaxSjAmountTotal;//实垫佣金税前小计
	private String aftTaxSjAmountTotal;//实垫佣金税后小计

	private String befTaxYjAmount1;//应垫佣金税前
	private String aftTaxYjAmount1;//应垫佣金税后
	private String yjRecordDate1;//应垫日期
	private String befTaxYjAmount2;//应垫佣金税前
	private String aftTaxYjAmount2;//应垫佣金税后
	private String yjRecordDate2;//应垫日期
	private String befTaxYjAmount3;//应垫佣金税前
	private String aftTaxYjAmount3;//应垫佣金税后
	private String yjRecordDate3;//应垫日期
	private String befTaxSjAmount1;//实垫佣金税前
	private String aftTaxSjAmount1;//实垫佣金税后
	private String sjRecordDate1;//实垫日期
	private String befTaxSjAmount2;//实垫佣金税前
	private String aftTaxSjAmount2;//实垫佣金税后
	private String sjRecordDate2;//实垫日期

    public Integer getReportdyId() {
        return reportdyId;
    }

    public void setReportdyId(Integer reportdyId) {
        this.reportdyId = reportdyId;
    }

    public String getCityNo() {
		return cityNo;
	}

	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
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

	public String getRowNo() {
		return rowNo;
	}

	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}

	public String getYjRecordDate() {
		return yjRecordDate;
	}

	public void setYjRecordDate(String yjRecordDate) {
		this.yjRecordDate = yjRecordDate;
	}

	public String getSjRecordDate() {
		return sjRecordDate;
	}

	public void setSjRecordDate(String sjRecordDate) {
		this.sjRecordDate = sjRecordDate;
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

	public String getRoughAmount() {
		return roughAmount;
	}

	public void setRoughAmount(String roughAmount) {
		this.roughAmount = roughAmount;
	}

	public String getRoughDate() {
		return roughDate;
	}

	public void setRoughDate(String roughDate) {
		this.roughDate = roughDate;
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

	public Integer getProjectDepartmentId() {
		return projectDepartmentId;
	}

	public void setProjectDepartmentId(Integer projectDepartmentId) {
		this.projectDepartmentId = projectDepartmentId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getBefTaxYjAmount() {
		return befTaxYjAmount;
	}

	public void setBefTaxYjAmount(String befTaxYjAmount) {
		this.befTaxYjAmount = befTaxYjAmount;
	}

	public String getAftTaxYjAmount() {
		return aftTaxYjAmount;
	}

	public void setAftTaxYjAmount(String aftTaxYjAmount) {
		this.aftTaxYjAmount = aftTaxYjAmount;
	}

	public String getBefTaxSjAmount() {
		return befTaxSjAmount;
	}

	public void setBefTaxSjAmount(String befTaxSjAmount) {
		this.befTaxSjAmount = befTaxSjAmount;
	}

	public String getAftTaxSjAmount() {
		return aftTaxSjAmount;
	}

	public void setAftTaxSjAmount(String aftTaxSjAmount) {
		this.aftTaxSjAmount = aftTaxSjAmount;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public String getBefTaxYjAmountTotal() {
		return befTaxYjAmountTotal;
	}

	public void setBefTaxYjAmountTotal(String befTaxYjAmountTotal) {
		this.befTaxYjAmountTotal = befTaxYjAmountTotal;
	}

	public String getAftTaxYjAmountTotal() {
		return aftTaxYjAmountTotal;
	}

	public void setAftTaxYjAmountTotal(String aftTaxYjAmountTotal) {
		this.aftTaxYjAmountTotal = aftTaxYjAmountTotal;
	}

	public String getBefTaxSjAmountTotal() {
		return befTaxSjAmountTotal;
	}

	public void setBefTaxSjAmountTotal(String befTaxSjAmountTotal) {
		this.befTaxSjAmountTotal = befTaxSjAmountTotal;
	}

	public String getAftTaxSjAmountTotal() {
		return aftTaxSjAmountTotal;
	}

	public void setAftTaxSjAmountTotal(String aftTaxSjAmountTotal) {
		this.aftTaxSjAmountTotal = aftTaxSjAmountTotal;
	}

	public String getBefTaxYjAmount1() {
		return befTaxYjAmount1;
	}

	public void setBefTaxYjAmount1(String befTaxYjAmount1) {
		this.befTaxYjAmount1 = befTaxYjAmount1;
	}

	public String getAftTaxYjAmount1() {
		return aftTaxYjAmount1;
	}

	public void setAftTaxYjAmount1(String aftTaxYjAmount1) {
		this.aftTaxYjAmount1 = aftTaxYjAmount1;
	}

	public String getYjRecordDate1() {
		return yjRecordDate1;
	}

	public void setYjRecordDate1(String yjRecordDate1) {
		this.yjRecordDate1 = yjRecordDate1;
	}

	public String getBefTaxYjAmount2() {
		return befTaxYjAmount2;
	}

	public void setBefTaxYjAmount2(String befTaxYjAmount2) {
		this.befTaxYjAmount2 = befTaxYjAmount2;
	}

	public String getAftTaxYjAmount2() {
		return aftTaxYjAmount2;
	}

	public void setAftTaxYjAmount2(String aftTaxYjAmount2) {
		this.aftTaxYjAmount2 = aftTaxYjAmount2;
	}

	public String getYjRecordDate2() {
		return yjRecordDate2;
	}

	public void setYjRecordDate2(String yjRecordDate2) {
		this.yjRecordDate2 = yjRecordDate2;
	}

	public String getBefTaxYjAmount3() {
		return befTaxYjAmount3;
	}

	public void setBefTaxYjAmount3(String befTaxYjAmount3) {
		this.befTaxYjAmount3 = befTaxYjAmount3;
	}

	public String getAftTaxYjAmount3() {
		return aftTaxYjAmount3;
	}

	public void setAftTaxYjAmount3(String aftTaxYjAmount3) {
		this.aftTaxYjAmount3 = aftTaxYjAmount3;
	}

	public String getYjRecordDate3() {
		return yjRecordDate3;
	}

	public void setYjRecordDate3(String yjRecordDate3) {
		this.yjRecordDate3 = yjRecordDate3;
	}

	public String getBefTaxSjAmount1() {
		return befTaxSjAmount1;
	}

	public void setBefTaxSjAmount1(String befTaxSjAmount1) {
		this.befTaxSjAmount1 = befTaxSjAmount1;
	}

	public String getAftTaxSjAmount1() {
		return aftTaxSjAmount1;
	}

	public void setAftTaxSjAmount1(String aftTaxSjAmount1) {
		this.aftTaxSjAmount1 = aftTaxSjAmount1;
	}

	public String getSjRecordDate1() {
		return sjRecordDate1;
	}

	public void setSjRecordDate1(String sjRecordDate1) {
		this.sjRecordDate1 = sjRecordDate1;
	}

	public String getBefTaxSjAmount2() {
		return befTaxSjAmount2;
	}

	public void setBefTaxSjAmount2(String befTaxSjAmount2) {
		this.befTaxSjAmount2 = befTaxSjAmount2;
	}

	public String getAftTaxSjAmount2() {
		return aftTaxSjAmount2;
	}

	public void setAftTaxSjAmount2(String aftTaxSjAmount2) {
		this.aftTaxSjAmount2 = aftTaxSjAmount2;
	}

	public String getSjRecordDate2() {
		return sjRecordDate2;
	}

	public void setSjRecordDate2(String sjRecordDate2) {
		this.sjRecordDate2 = sjRecordDate2;
	}
}


