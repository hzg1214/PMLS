package cn.com.eju.deal.dto.kefuWj;

import java.util.Date;
import java.util.List;


/**
 * 
 * desc:题目entity
 * @author :zhenggang.Huang
 * @date   :2019年6月20日
 */
public class KefuWjDTmDto {

	private Integer id;
	
	private String index;//绑定答案

    private Integer wjhid;

    private String wjtmType;

    private Integer wjtmMaxScore;

    private String wjtmflType;

    private String wjtmValue;

    private Integer wjtmSortindex;

    private Integer userCreate;

    private Date dateCreate;

    private Boolean delFlag;
    
    private List<KefuWjDDaDto> wjDDaList;

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public List<KefuWjDDaDto> getWjDDaList() {
        return wjDDaList;
    }

    public void setWjDDaList(List<KefuWjDDaDto> wjDDaList) {
        this.wjDDaList = wjDDaList;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWjhid() {
		return wjhid;
	}

	public void setWjhid(Integer wjhid) {
		this.wjhid = wjhid;
	}

	public String getWjtmType() {
		return wjtmType;
	}

	public void setWjtmType(String wjtmType) {
		this.wjtmType = wjtmType;
	}

	public Integer getWjtmMaxScore() {
		return wjtmMaxScore;
	}

	public void setWjtmMaxScore(Integer wjtmMaxScore) {
		this.wjtmMaxScore = wjtmMaxScore;
	}

	public String getWjtmflType() {
		return wjtmflType;
	}

	public void setWjtmflType(String wjtmflType) {
		this.wjtmflType = wjtmflType;
	}

	public String getWjtmValue() {
		return wjtmValue;
	}

	public void setWjtmValue(String wjtmValue) {
		this.wjtmValue = wjtmValue;
	}

	public Integer getWjtmSortindex() {
		return wjtmSortindex;
	}

	public void setWjtmSortindex(Integer wjtmSortindex) {
		this.wjtmSortindex = wjtmSortindex;
	}

	public Integer getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(Integer userCreate) {
		this.userCreate = userCreate;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Boolean getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}
    
}
