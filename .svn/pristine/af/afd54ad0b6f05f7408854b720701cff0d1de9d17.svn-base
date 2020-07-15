package cn.com.eju.deal.dto.houseLinkage.estate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.util.StringUtil;

/**   
* EstateTypeDto  Dto
* @author (qianwei)
* @date 2016年3月22日 下午4:42:54
*/
public class EstateTypeDto implements Serializable
{
    /**
    * TODO(用一句话描述这个变量表示什么)
    */ 
    private static final long serialVersionUID = -5775427213591322713L;

    private Integer id;
	private String typeId;
	private String estateId;
	// 主力户型
	private Integer countFlg;
	// 户型(室)
	private String countF;
	// 户型(厅)
	private String countT;
	// 户型(卫)
	private String countW;
	// 户型(阳)
	private String countY;
	// 面积
	private BigDecimal buildSquare;
	private BigDecimal useSquare;
	// 朝向
	private Integer directionKbn;
	// 标签
	private String label;
	private Integer crtEmpId;
	private Integer uptEmpId;
	private Boolean delFlg;
	private Date crtDt;
	private Date uptDt;
	
	private String directionKbnStr;
	
	private List<String> houseTypeImgs;//在售户型户型图
    
    private List<String> houseTemplateImgs;//在售户型样板间
    
    private List<PhotoDto> houseImgs;//在售户型图片
    
    private List<String> labelLst;//标签列表
    
    private String houseTypeImgIds;//户型图图片编号序列
    
    private String houseTemplateImgIds;//样板间图片编号序列
    
    private String coverImgId;//封面图图片编号
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getEstateId() {
		return estateId;
	}
	public void setEstateId(String estateId) {
		this.estateId = estateId;
	}
	public Integer getCountFlg() {
		return countFlg;
	}
	public void setCountFlg(Integer countFlg) {
		this.countFlg = countFlg;
	}
	public String getCountF() {
		return countF;
	}
	public void setCountF(String countF) {
		this.countF = countF;
	}
	public String getCountT() {
		return countT;
	}
	public void setCountT(String countT) {
		this.countT = countT;
	}
	public String getCountW() {
		return countW;
	}
	public void setCountW(String countW) {
		this.countW = countW;
	}
	public String getCountY() {
		return countY;
	}
	public void setCountY(String countY) {
		this.countY = countY;
	}
	public BigDecimal getBuildSquare() {
		return buildSquare;
	}
	public void setBuildSquare(BigDecimal buildSquare) {
		this.buildSquare = buildSquare;
	}
	public BigDecimal getUseSquare() {
		return useSquare;
	}
	public void setUseSquare(BigDecimal useSquare) {
		this.useSquare = useSquare;
	}
	
	/**
     * @return the directionKbn
     */
    public Integer getDirectionKbn()
    {
        return directionKbn;
    }
    /**
     * @param directionKbn the directionKbn to set
     */
    public void setDirectionKbn(Integer directionKbn)
    {
        this.directionKbn = directionKbn;
    }
    public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Integer getCrtEmpId() {
		return crtEmpId;
	}
	public void setCrtEmpId(Integer crtEmpId) {
		this.crtEmpId = crtEmpId;
	}
	public Integer getUptEmpId() {
		return uptEmpId;
	}
	public void setUptEmpId(Integer uptEmpId) {
		this.uptEmpId = uptEmpId;
	}
	public Boolean getDelFlg() {
		return delFlg;
	}
	public void setDelFlg(Boolean delFlg) {
		this.delFlg = delFlg;
	}
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	public Date getUptDt() {
		return uptDt;
	}
	public void setUptDt(Date uptDt) {
		this.uptDt = uptDt;
	}
    /**
     * @return the directionKbnStr
     */
    public String getDirectionKbnStr()
    {
        return SystemParam.getDicValueByDicCode(directionKbn+"");
    }
    /**
     * @param directionKbnStr the directionKbnStr to set
     */
    public void setDirectionKbnStr(String directionKbnStr)
    {
        this.directionKbnStr = directionKbnStr;
    }
    /**
     * @return the houseTypeImgs
     */
    public List<String> getHouseTypeImgs()
    {
        return houseTypeImgs;
    }
    /**
     * @param houseTypeImgs the houseTypeImgs to set
     */
    public void setHouseTypeImgs(List<String> houseTypeImgs)
    {
        this.houseTypeImgs = houseTypeImgs;
    }
    /**
     * @return the houseTemplateImgs
     */
    public List<String> getHouseTemplateImgs()
    {
        return houseTemplateImgs;
    }
    /**
     * @param houseTemplateImgs the houseTemplateImgs to set
     */
    public void setHouseTemplateImgs(List<String> houseTemplateImgs)
    {
        this.houseTemplateImgs = houseTemplateImgs;
    }
    /**
     * @return the houseImgs
     */
    public List<PhotoDto> getHouseImgs()
    {
        return houseImgs;
    }
    /**
     * @param houseImgs the houseImgs to set
     */
    public void setHouseImgs(List<PhotoDto> houseImgs)
    {
        this.houseImgs = houseImgs;
    }
    /**
     * @return the labelLst
     */
    public List<String> getLabelLst()
    {
        labelLst=new ArrayList<>();
        if(StringUtil.isNotEmpty(label)){
            String[] markArr=label.split(",");
            for (String string : markArr)
            {
                labelLst.add(string);
            }
        }
        return labelLst;
    }
    /**
     * @param labelLst the labelLst to set
     */
    public void setLabelLst(List<String> labelLst)
    {
        this.labelLst = labelLst;
    }
    /**
     * @return the houseTypeImgIds
     */
    public String getHouseTypeImgIds()
    {
        return houseTypeImgIds;
    }
    /**
     * @param houseTypeImgIds the houseTypeImgIds to set
     */
    public void setHouseTypeImgIds(String houseTypeImgIds)
    {
        this.houseTypeImgIds = houseTypeImgIds;
    }
    /**
     * @return the houseTemplateImgIds
     */
    public String getHouseTemplateImgIds()
    {
        return houseTemplateImgIds;
    }
    /**
     * @param houseTemplateImgIds the houseTemplateImgIds to set
     */
    public void setHouseTemplateImgIds(String houseTemplateImgIds)
    {
        this.houseTemplateImgIds = houseTemplateImgIds;
    }
    /**
     * @return the coverImgId
     */
    public String getCoverImgId()
    {
        return coverImgId;
    }
    /**
     * @param coverImgId the coverImgId to set
     */
    public void setCoverImgId(String coverImgId)
    {
        this.coverImgId = coverImgId;
    }
	
}
