package cn.com.eju.pmls.commission.dto;

import cn.com.eju.deal.core.model.BaseModel;

import java.math.BigDecimal;
import java.util.List;

public class CommissionImportDto extends BaseModel {

    private int id;
    private String year;
    private String cityNo;
    private String cityName;
    private String dateMonth;
    private String businessTypeCode;//业务类型Code
    private String businessTypeName;//业务类型名称

    private String importType;

    private String date;
    /**
     * 核算主体
     */
    private String accountProjectCode;
    private String accountProjectName;
    /**
     * 考核主体
     */
    private String checkBodyCode;
    private String checkBodyName;
    /**
     * 成本中心
     */
    private String costCode;
    private String costName;
    private String projectNo;
    private String projectName;
    private String serviceType;
    private String userCode;
    private String userName;
    /**
     * 岗位佣金
     */
    private BigDecimal jobBonus;
    /**
     * 团奖
     */
    private BigDecimal teamBonus;

    /**
     * 计提日期
     */
    private String countDate;

    /**
     * 工资单
     */
    private String payrollCode;
    private String payrollName;
    /**
     * 工资单核算主体
     */
    private String payrollAccountProjectCode;
    private String payrollAccountProjectName;
    /**
     * 可发日期
     */
    private String canSendDate;
    /**
     * 实发日期
     */
    private String actualSendDate;

    private String createUser;

    private List<CommissionImportDto> importDtos;

    public String getCountDate() {
        return countDate;
    }

    public void setCountDate(String countDate) {
        this.countDate = countDate;
    }

    public List<CommissionImportDto> getImportDtos() {
        return importDtos;
    }

    public void setImportDtos(List<CommissionImportDto> importDtos) {
        this.importDtos = importDtos;
    }

    public String getImportType() {
        return importType;
    }

    public void setImportType(String importType) {
        this.importType = importType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDateMonth() {
        return dateMonth;
    }

    public void setDateMonth(String dateMonth) {
        this.dateMonth = dateMonth;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAccountProjectCode() {
        return accountProjectCode;
    }

    public void setAccountProjectCode(String accountProjectCode) {
        this.accountProjectCode = accountProjectCode;
    }

    public String getAccountProjectName() {
        return accountProjectName;
    }

    public void setAccountProjectName(String accountProjectName) {
        this.accountProjectName = accountProjectName;
    }

    public String getCheckBodyCode() {
        return checkBodyCode;
    }

    public void setCheckBodyCode(String checkBodyCode) {
        this.checkBodyCode = checkBodyCode;
    }

    public String getCheckBodyName() {
        return checkBodyName;
    }

    public void setCheckBodyName(String checkBodyName) {
        this.checkBodyName = checkBodyName;
    }

    public String getCostCode() {
        return costCode;
    }

    public void setCostCode(String costCode) {
        this.costCode = costCode;
    }

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
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

    public BigDecimal getJobBonus() {
        return jobBonus;
    }

    public void setJobBonus(BigDecimal jobBonus) {
        this.jobBonus = jobBonus;
    }

    public BigDecimal getTeamBonus() {
        return teamBonus;
    }

    public void setTeamBonus(BigDecimal teamBonus) {
        this.teamBonus = teamBonus;
    }

    public String getPayrollCode() {
        return payrollCode;
    }

    public void setPayrollCode(String payrollCode) {
        this.payrollCode = payrollCode;
    }

    public String getPayrollName() {
        return payrollName;
    }

    public void setPayrollName(String payrollName) {
        this.payrollName = payrollName;
    }

    public String getPayrollAccountProjectCode() {
        return payrollAccountProjectCode;
    }

    public void setPayrollAccountProjectCode(String payrollAccountProjectCode) {
        this.payrollAccountProjectCode = payrollAccountProjectCode;
    }

    public String getPayrollAccountProjectName() {
        return payrollAccountProjectName;
    }

    public void setPayrollAccountProjectName(String payrollAccountProjectName) {
        this.payrollAccountProjectName = payrollAccountProjectName;
    }

    public String getCanSendDate() {
        return canSendDate;
    }

    public void setCanSendDate(String canSendDate) {
        this.canSendDate = canSendDate;
    }

    public String getActualSendDate() {
        return actualSendDate;
    }

    public void setActualSendDate(String actualSendDate) {
        this.actualSendDate = actualSendDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getBusinessTypeCode() {
        return businessTypeCode;
    }

    public void setBusinessTypeCode(String businessTypeCode) {
        this.businessTypeCode = businessTypeCode;
    }

    public String getBusinessTypeName() {
        return businessTypeName;
    }

    public void setBusinessTypeName(String businessTypeName) {
        this.businessTypeName = businessTypeName;
    }
}
