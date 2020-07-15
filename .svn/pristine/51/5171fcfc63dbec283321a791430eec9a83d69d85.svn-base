package cn.com.eju.deal.dto.follow;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.dto.store.StoreDto;

/** 
* @ClassName: FollowDto 
* @Description: 跟进接口类
* @author 陆海丹 
* @date 2016年3月24日 下午2:13:08 
*  
*/
public class FollowDto implements Serializable
{
    
    /**
    * @Fields serialVersionUID : 自动生成的 我也不晓得这是干嘛的。。。
    */
    private static final long serialVersionUID = 5416069795828184037L;
    
    private Integer followId;//跟进编号
    
    private Integer companyId;//客户编号
    
    private Integer storeId;//门店编号
    
    private String title;//主题
    
    private Integer followType;//跟进类型
    
    private Date dateCreate;//创建时间
    
    private String content;//内容
    
    private Integer userCreate;//用户编号
    
    private BigDecimal longitude;//经度
    
    private BigDecimal latitude;//纬度
    
    private Boolean isDelete;//删除标记
    
    private String followTypeName;//跟进类型
    
    private String userNameCreate;//用户姓名
    private String companyName;//客户姓名
    private String storeName;//门店名称
    
    private StoreDto storeDto;//门店信息
    
    private Date dateFollowFrom;//跟进起始日期
    
    private Date dateFollowTo;//跟进结束日期
    
    private String searchKey;//查询关键字
    
    /**
     * 我的跟进位置
     */
    private String followPosition;
    
    /**
     * 跟进编号
     * @return the followId
     */
    public Integer getFollowId()
    {
        return followId;
    }
    
    /**
     * 跟进编号
     * @param followId the followId to set
     */
    public void setFollowId(Integer followId)
    {
        this.followId = followId;
    }
    
    /**
     * 客户编号
     * @return the companyId
     */
    public Integer getCompanyId()
    {
        return companyId;
    }
    
    /**
     * 客户编号
     * @param companyId the companyId to set
     */
    public void setCompanyId(Integer companyId)
    {
        this.companyId = companyId;
    }
    
    /**
     * 门店编号
     * @return the storeId
     */
    public Integer getStoreId()
    {
        return storeId;
    }
    
    /**
     * 门店编号
     * @param storeId the storeId to set
     */
    public void setStoreId(Integer storeId)
    {
        this.storeId = storeId;
    }
    
    /**
     * 主题
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * 主题
     * @param title the title to set
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    /**
     * 跟进类型
     * @return the followType
     */
    public Integer getFollowType()
    {
        return followType;
    }
    
    /**
     * 跟进类型
     * @param followType the followType to set
     */
    public void setFollowType(Integer followType)
    {
        this.followType = followType;
    }
    
    /**
     * 创建时间
     * @return the dateCreate
     */
    public Date getDateCreate()
    {
        return dateCreate;
    }
    
    /**
     * 创建时间
     * @param dateCreate the dateCreate to set
     */
    public void setDateCreate(Date dateCreate)
    {
        this.dateCreate = dateCreate;
    }
    
    /**
     * 内容
     * @return the content
     */
    public String getContent()
    {
        return content;
    }
    
    /**
     * 内容
     * @param content the content to set
     */
    public void setContent(String content)
    {
        this.content = content;
    }
    
    /**
     * 用户编号
     * @return the userCreate
     */
    public Integer getUserCreate()
    {
        return userCreate;
    }
    
    /**
     * 用户编号
     * @param userId the userCreate to set
     */
    public void setUserCreate(Integer userCreate)
    {
        this.userCreate = userCreate;
    }
    
    /**
     * 经度
     * @return the longitude
     */
    public BigDecimal getLongitude()
    {
        return longitude;
    }
    
    /**
     * 经度
     * @param longitude the longitude to set
     */
    public void setLongitude(BigDecimal longitude)
    {
        this.longitude = longitude;
    }
    
    /**
     * 纬度
     * @return the latitude
     */
    public BigDecimal getLatitude()
    {
        return latitude;
    }
    
    /**
     * 纬度
     * @param latitude the latitude to set
     */
    public void setLatitude(BigDecimal latitude)
    {
        this.latitude = latitude;
    }
    
    /**
     * 跟进类型
     * @return the followTypeName
     */
    public String getFollowTypeName()
    {
        return SystemParam.getDicValueByDicCode(this.followType + "");
    }
    
    /**
     * 跟进类型
     * @param followTypeName the followTypeName to set
     */
    public void setFollowTypeName(String followTypeName)
    {
        this.followTypeName = followTypeName;
    }
    
    /**
     * @return the userNameCreate
     */
    public String getUserNameCreate()
    {
        return userNameCreate;
    }
    /**
     * @param userNameCreate the userNameCreate to set
     */
    public void setUserNameCreate(String userNameCreate)
    {
        this.userNameCreate = userNameCreate;
    }
    /**
     * @return the companyName
     */
    public String getCompanyName()
    {
        return companyName;
    }
    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }
    /**
     * @return the storeName
     */
    public String getStoreName()
    {
        return storeName;
    }
    /**
     * @param storeName the storeName to set
     */
    public void setStoreName(String storeName)
    {
        this.storeName = storeName;
    }

    /**
     * @return the isDelete
     */
    public Boolean getIsDelete()
    {
        return isDelete;
    }

    /**
     * @param isDelete the isDelete to set
     */
    public void setIsDelete(Boolean isDelete)
    {
        this.isDelete = isDelete;
    }

    /**
     * @return the storeDto
     */
    public StoreDto getStoreDto()
    {
        return storeDto;
    }

    /**
     * @param storeDto the storeDto to set
     */
    public void setStoreDto(StoreDto storeDto)
    {
        this.storeDto = storeDto;
    }

    /**
     * @return the dateFollowFrom
     */
    public Date getDateFollowFrom()
    {
        return dateFollowFrom;
    }

    /**
     * @param dateFollowFrom the dateFollowFrom to set
     */
    public void setDateFollowFrom(Date dateFollowFrom)
    {
        this.dateFollowFrom = dateFollowFrom;
    }

    /**
     * @return the dateFollowTo
     */
    public Date getDateFollowTo()
    {
        return dateFollowTo;
    }

    /**
     * @param dateFollowTo the dateFollowTo to set
     */
    public void setDateFollowTo(Date dateFollowTo)
    {
        this.dateFollowTo = dateFollowTo;
    }

    /**
     * @return the searchKey
     */
    public String getSearchKey()
    {
        return searchKey;
    }

    /**
     * @param searchKey the searchKey to set
     */
    public void setSearchKey(String searchKey)
    {
        this.searchKey = searchKey;
    }
    
    /** 
     * 获取我的跟进位置
     * @return followPosition 我的跟进位置
     */
     public String getFollowPosition()
     {
         return followPosition;
     }

     /** 
     * 设置我的跟进位置
     * @param followPosition 我的跟进位置
     */
     public void setFollowPosition(String followPosition)
     {
         this.followPosition = followPosition;
     }
}
