package cn.com.eju.deal.dto.kefuWj;

import java.util.Date;

public class KefuWjDDaDto {
    private Integer id;

    private Integer wjhid;

    private Integer wjdid;

    private String wjxx;

    private Integer sortIndex;

    private Integer directScore;

    private Integer userCreate;

    private Date dateCreate;

    private Boolean delFlag;
    
    private String index;
    
    public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
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

    public Integer getWjdid() {
        return wjdid;
    }

    public void setWjdid(Integer wjdid) {
        this.wjdid = wjdid;
    }

    public String getWjxx() {
        return wjxx;
    }

    public void setWjxx(String wjxx) {
        this.wjxx = wjxx == null ? null : wjxx.trim();
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public Integer getDirectScore() {
        return directScore;
    }

    public void setDirectScore(Integer directScore) {
        this.directScore = directScore;
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