package cn.com.eju.pmls.commission.dto;

import java.util.Date;

/**
 * 开关
 */
public class PerformSwitchDto
{
    /**
     * 主键
     */
    private Integer id;
    /**
     * 关联系统
     */
    private Integer relateSystem;
    /**
     * 城市编码
     */
    private String cityNo;

    private String cityNoStr;



    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 选择年份
     */
    private String switchYear;
    /**
     * 选择月份
     */
    private String switchMonth;
    /**
     * 关账日期
     */
    private Date closeDate;
    /**
     * 关账用户名
     */
    private String closeUserName;
    /**
     * 关账用户Code
     */
    private String closeUserCode;
    /**
     * 开账日期
     */
    private Date openDate;
    /**
     * 开账用户名
     */
    private String openUserName;
    /**
     * 开账用户code
     */
    private String openUserCode;
    /**
     * 开关状态
     */
    private Integer switchState;
    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 创建用户名
     */
    private String createUserName;
    /**
     * 创建用户code
     */
    private String createUserCode;
    /**
     * 更新日期
     */
    private Date updateDate;
    /**
     * 更新用户名
     */
    private String updateUserName;
    /**
     * 更新用户code
     */
    private String updateUserCode;
    /**
     * 交易中心id
     */
    private Integer exchangeCenterId;
    /**
     * 交易中心名称
     */
    private String exchangeCenterName;


    private  String monthStr;
    /**
     * 业务类型
     */
    private String businessTypeCode;
    public String getCityNoStr() {
        return cityNoStr;
    }

    public void setCityNoStr(String cityNoStr) {
        this.cityNoStr = cityNoStr;
    }
    public String getMonthStr() {
        return monthStr;
    }

    public void setMonthStr(String monthStr) {
        this.monthStr = monthStr;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getExchangeCenterName() {
		return exchangeCenterName;
	}
	public void setExchangeCenterName(String exchangeCenterName) {
		this.exchangeCenterName = exchangeCenterName;
	}
	public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    public Integer getRelateSystem()
    {
        return relateSystem;
    }
    public void setRelateSystem(Integer relateSystem)
    {
        this.relateSystem = relateSystem;
    }
    public String getCityNo()
    {
        return cityNo;
    }
    public void setCityNo(String cityNo)
    {
        this.cityNo = cityNo;
    }
    public String getSwitchYear()
    {
        return switchYear;
    }
    public void setSwitchYear(String switchYear)
    {
        this.switchYear = switchYear;
    }
    public String getSwitchMonth()
    {
        return switchMonth;
    }
    public void setSwitchMonth(String switchMonth)
    {
        this.switchMonth = switchMonth;
    }
    public Date getCloseDate()
    {
        return closeDate;
    }
    public void setCloseDate(Date closeDate)
    {
        this.closeDate = closeDate;
    }
    public String getCloseUserName()
    {
        return closeUserName;
    }
    public void setCloseUserName(String closeUserName)
    {
        this.closeUserName = closeUserName;
    }
    public String getCloseUserCode()
    {
        return closeUserCode;
    }
    public void setCloseUserCode(String closeUserCode)
    {
        this.closeUserCode = closeUserCode;
    }
    public Date getOpenDate()
    {
        return openDate;
    }
    public void setOpenDate(Date openDate)
    {
        this.openDate = openDate;
    }
    public String getOpenUserName()
    {
        return openUserName;
    }
    public void setOpenUserName(String openUserName)
    {
        this.openUserName = openUserName;
    }
    public String getOpenUserCode()
    {
        return openUserCode;
    }
    public void setOpenUserCode(String openUserCode)
    {
        this.openUserCode = openUserCode;
    }
    public Integer getSwitchState()
    {
        return switchState;
    }
    public void setSwitchState(Integer switchState)
    {
        this.switchState = switchState;
    }
    public Date getCreateDate()
    {
        return createDate;
    }
    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }
    public String getCreateUserName()
    {
        return createUserName;
    }
    public void setCreateUserName(String createUserName)
    {
        this.createUserName = createUserName;
    }
    public String getCreateUserCode()
    {
        return createUserCode;
    }
    public void setCreateUserCode(String createUserCode)
    {
        this.createUserCode = createUserCode;
    }
    public Date getUpdateDate()
    {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate)
    {
        this.updateDate = updateDate;
    }
    public String getUpdateUserName()
    {
        return updateUserName;
    }
    public void setUpdateUserName(String updateUserName)
    {
        this.updateUserName = updateUserName;
    }
	public String getUpdateUserCode() {
		return updateUserCode;
	}
	public void setUpdateUserCode(String updateUserCode) {
		this.updateUserCode = updateUserCode;
	}
    public Integer getExchangeCenterId()
    {
        return exchangeCenterId;
    }
    public void setExchangeCenterId(Integer exchangeCenterId)
    {
        this.exchangeCenterId = exchangeCenterId;
    }


    public String getBusinessTypeCode() {
        return businessTypeCode;
    }

    public void setBusinessTypeCode(String businessTypeCode) {
        this.businessTypeCode = businessTypeCode;
    }
}
