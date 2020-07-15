/**   
* @Title: ProvinceDto.java 
* @Package cn.com.eju.deal.dto.common 
* @Description: TODO(用一句话描述该文件做什么) 
* @author lxd  
* @date 2016年3月28日 下午2:10:55 
* @version V1.0   
*/
package cn.com.eju.deal.dto.common;

/** 
* @ClassName: ProvinceDto 
* @Description: 省份--接口传输类
* @author 陆海丹 
* @date 2016年3月28日 下午2:10:55 
*  
*/
public class ProvinceDto
{
    private String  provinceNo;//省份编号
    private String provinceName;//省份名称
    /**
     * 省份编号
     * @return the provinceNo
     */
    public String getProvinceNo()
    {
        return provinceNo;
    }
    /**
     * 省份编号
     * @param provinceNo the provinceNo to set
     */
    public void setProvinceNo(String provinceNo)
    {
        this.provinceNo = provinceNo;
    }
    /**
     * 省份名称
     * @return the provinceName
     */
    public String getProvinceName()
    {
        return provinceName;
    }
    /**
     * 省份名称
     * @param provinceName the provinceName to set
     */
    public void setProvinceName(String provinceName)
    {
        this.provinceName = provinceName;
    }
    
}
