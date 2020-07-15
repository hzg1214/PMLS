package cn.com.eju.deal.Report.model;


import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.com.eju.deal.core.model.BaseModel;
import cn.com.eju.deal.core.util.StringUtil;

public class ExcelTask implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4824529314245467106L;

	private Integer id;

    private String fileName; //excel名称

    private Integer status; //状态：1下载中，2已完成，3删除

    private String downloadurl; //下载地址
    
    private String urlParam; //导出参数
    
    private String exportType; //下载类型：联动明细、联动汇总日、周...

    private Integer createuserid; //操作人

    private Date createtime; //操作时间

    private String statusZh;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDownloadurl() {
        return downloadurl;
    }

    public void setDownloadurl(String downloadurl) {
        this.downloadurl = downloadurl;
    }

    public Integer getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(Integer createuserid) {
        this.createuserid = createuserid;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUrlParam() {
		return urlParam;
	}

	public void setUrlParam(String urlParam) {
		this.urlParam = urlParam;
	}

	public String getExportType() {
		return exportType;
	}

	public void setExportType(String exportType) {
		this.exportType = exportType;
	}

	public String getStatusZh() {
		return statusZh;
	}

	public void setStatusZh(String statusZh) {
		this.statusZh = statusZh;
	}
}
