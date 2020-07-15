package cn.com.eju.deal.Report.model;

import java.util.Date;

public class ReportQueueTotal {
    private Integer id;

    private String reportKey;

    private Integer positiveSize;

    private Integer minusSize;

    private Date dateCreate;

    private Integer userIdCreate;

    private String delFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReportKey() {
        return reportKey;
    }

    public void setReportKey(String reportKey) {
        this.reportKey = reportKey == null ? null : reportKey.trim();
    }

    public Integer getPositiveSize() {
        return positiveSize;
    }

    public void setPositiveSize(Integer positiveSize) {
        this.positiveSize = positiveSize;
    }

    public Integer getMinusSize() {
        return minusSize;
    }

    public void setMinusSize(Integer minusSize) {
        this.minusSize = minusSize;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Integer getUserIdCreate() {
        return userIdCreate;
    }

    public void setUserIdCreate(Integer userIdCreate) {
        this.userIdCreate = userIdCreate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}