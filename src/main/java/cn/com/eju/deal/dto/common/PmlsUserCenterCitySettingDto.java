package cn.com.eju.deal.dto.common;

import cn.com.eju.deal.core.model.BaseModel;

/** 
* @ClassName: City 
* @Description: 城市
* @author 陆海丹
* @date 2016年3月28日 上午9:34:01 
*  
*/
public class PmlsUserCenterCitySettingDto extends BaseModel
{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cityNo;//城市编号
    private String cityName;//城市名称
    private String userCode;//城市名称
    private String userName;
    private Integer cityId;
    private Integer centerId;
    
    public String getUserCode() {
		return userCode;
	}
	public String getUserName() {
		return userName;
	}
	public Integer getCityId() {
		return cityId;
	}
	public Integer getCenterId() {
		return centerId;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}
	/**
     * 城市编号
     * @return the cityNo
     */
    public String getCityNo()
    {
        return cityNo;
    }
    /**
     * 城市编号
     * @param cityNo the cityNo to set
     */
    public void setCityNo(String cityNo)
    {
        this.cityNo = cityNo;
    }
    /**
     * 城市名称
     * @return the cityName
     */
    public String getCityName()
    {
        return cityName;
    }
    /**
     * 城市名称
     * @param cityName the cityName to set
     */
    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }
    
}
