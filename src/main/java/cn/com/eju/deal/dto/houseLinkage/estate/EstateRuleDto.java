package cn.com.eju.deal.dto.houseLinkage.estate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import cn.com.eju.deal.base.support.SystemParam;

/**   
* EstateRuleDto  Dto
* @author (qianwei)
* @date 2016年3月22日 下午4:42:54
*/
public class EstateRuleDto implements Serializable
{
    /**
    * TODO(用一句话描述这个变量表示什么)
    */
    private static final long serialVersionUID = -5775427213591322713L;
    
    private Integer id;
    
    // 楼盘ID
    private String estateId;
    
    // 规则ID
    private String ruleId;
    
    // 认证类型
    private Integer authenticationKbn;
    
    // 带看保护期
    private BigDecimal relationProtectPeriod;
    
    // 带看奖励
    private BigDecimal relationReward;
    
    // 带看起始日期
    private Date relationDtStart;
    
    // 带看截止日期
    private Date relationDtEnd;
    
    // 认筹奖励
    private BigDecimal pledgedReward;
    
    // 认筹起始日期
    private Date pledgedDtStart;
    
    // 认筹截止日期
    private Date pledgedDtEnd;
    
    // 认购奖励
    private BigDecimal subscribedReward;
    
    // 认购起始日期
    private Date subscribedDtStart;
    
    // 认购截止日期
    private Date subscribedDtEnd;
    
    // 成交奖励
    private BigDecimal bargainReward;
    
    // 结佣期限
    private BigDecimal commissionPeriod;
    
    // 佣金方式
    private Integer commissionKbn;
    
    // 佣金
    private BigDecimal commission;
    
    // 佣金(排序用）
    private BigDecimal commissionSort;
    
    // 收入结算描述
    private String commissionMemo;
    
    // 结佣方式
    private Integer payKbn;
    
    // 销售方式
    private Integer saleKbn;
    
    // 报备开始日
    private Date reportDtStart;
    
    // 报备截止日
    private Date reportDtEnd;
    
    // 报备模式
    private Integer reportKbn;
    
    // 隐号报备
    private String hideNumberFlg;
    
    // 报备规则
    private String reportRule;
    
    // 结佣规则
    private String commissionRule;
    
    // 提前报备期(时)
    private String advanceReportHH;
    
    // 提前报备期(分)
    private String advanceReportMM;
    
    private Integer crtEmpId;
    
    private Integer uptEmpId;
    
    private Boolean delFlg;
    
    private Date crtDt;
    
    private Date uptDt;// 日期类型转换
    
    private String authenticationKbnStr;//认证类型
    
    private String commissionKbnStr;//佣金方式
    
    private String payKbnStr;//结佣方式
    
    private String saleKbnStr;//销售方式
    
    private String reportKbnStr;//报备模式
    
	private String rtnCommission;  //返佣标准
    
    private String rtnCommissionMemo;  //返佣结算条件
    
    private String incomeSubject; //收入标的
    
    public String getIncomeSubject() {
		return incomeSubject;
	}

	public void setIncomeSubject(String incomeSubject) {
		this.incomeSubject = incomeSubject;
	}

	public String getRtnCommission() {
		return rtnCommission;
	}

	public void setRtnCommission(String rtnCommission) {
		this.rtnCommission = rtnCommission;
	}

	public String getRtnCommissionMemo() {
		return rtnCommissionMemo;
	}

	public void setRtnCommissionMemo(String rtnCommissionMemo) {
		this.rtnCommissionMemo = rtnCommissionMemo;
	}
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public String getEstateId()
    {
        return estateId;
    }
    
    public void setEstateId(String estateId)
    {
        this.estateId = estateId;
    }
    
    public String getRuleId()
    {
        return ruleId;
    }
    
    public void setRuleId(String ruleId)
    {
        this.ruleId = ruleId;
    }
    
    public BigDecimal getRelationProtectPeriod()
    {
        return relationProtectPeriod;
    }
    
    public void setRelationProtectPeriod(BigDecimal relationProtectPeriod)
    {
        this.relationProtectPeriod = relationProtectPeriod;
    }
    
    public BigDecimal getRelationReward()
    {
        return relationReward;
    }
    
    public void setRelationReward(BigDecimal relationReward)
    {
        this.relationReward = relationReward;
    }
    
    public Date getRelationDtStart()
    {
        return relationDtStart;
    }
    
    public void setRelationDtStart(Date relationDtStart)
    {
        this.relationDtStart = relationDtStart;
    }
    
    public Date getRelationDtEnd()
    {
        return relationDtEnd;
    }
    
    public void setRelationDtEnd(Date relationDtEnd)
    {
        this.relationDtEnd = relationDtEnd;
    }
    
    public BigDecimal getPledgedReward()
    {
        return pledgedReward;
    }
    
    public void setPledgedReward(BigDecimal pledgedReward)
    {
        this.pledgedReward = pledgedReward;
    }
    
    public Date getPledgedDtStart()
    {
        return pledgedDtStart;
    }
    
    public void setPledgedDtStart(Date pledgedDtStart)
    {
        this.pledgedDtStart = pledgedDtStart;
    }
    
    public Date getPledgedDtEnd()
    {
        return pledgedDtEnd;
    }
    
    public void setPledgedDtEnd(Date pledgedDtEnd)
    {
        this.pledgedDtEnd = pledgedDtEnd;
    }
    
    public BigDecimal getSubscribedReward()
    {
        return subscribedReward;
    }
    
    public void setSubscribedReward(BigDecimal subscribedReward)
    {
        this.subscribedReward = subscribedReward;
    }
    
    public Date getSubscribedDtStart()
    {
        return subscribedDtStart;
    }
    
    public void setSubscribedDtStart(Date subscribedDtStart)
    {
        this.subscribedDtStart = subscribedDtStart;
    }
    
    public Date getSubscribedDtEnd()
    {
        return subscribedDtEnd;
    }
    
    public void setSubscribedDtEnd(Date subscribedDtEnd)
    {
        this.subscribedDtEnd = subscribedDtEnd;
    }
    
    public BigDecimal getBargainReward()
    {
        return bargainReward;
    }
    
    public void setBargainReward(BigDecimal bargainReward)
    {
        this.bargainReward = bargainReward;
    }
    
    public BigDecimal getCommissionPeriod()
    {
        return commissionPeriod;
    }
    
    public void setCommissionPeriod(BigDecimal commissionPeriod)
    {
        this.commissionPeriod = commissionPeriod;
    }
    
    public BigDecimal getCommission()
    {
        return commission;
    }
    
    public void setCommission(BigDecimal commission)
    {
        this.commission = commission;
    }
    
    public BigDecimal getCommissionSort()
    {
        return commissionSort;
    }
    
    public void setCommissionSort(BigDecimal commissionSort)
    {
        this.commissionSort = commissionSort;
    }
    
    public String getCommissionMemo()
    {
        return commissionMemo;
    }
    
    public void setCommissionMemo(String commissionMemo)
    {
        this.commissionMemo = commissionMemo;
    }
    
    public Date getReportDtStart()
    {
        return reportDtStart;
    }
    
    public void setReportDtStart(Date reportDtStart)
    {
        this.reportDtStart = reportDtStart;
    }
    
    public Date getReportDtEnd()
    {
        return reportDtEnd;
    }
    
    public void setReportDtEnd(Date reportDtEnd)
    {
        this.reportDtEnd = reportDtEnd;
    }
    
    public String getHideNumberFlg()
    {
        return hideNumberFlg;
    }
    
    public void setHideNumberFlg(String hideNumberFlg)
    {
        this.hideNumberFlg = hideNumberFlg;
    }
    
    public String getReportRule()
    {
        return reportRule;
    }
    
    public void setReportRule(String reportRule)
    {
        this.reportRule = reportRule;
    }
    
    public String getAdvanceReportHH()
    {
        return advanceReportHH;
    }
    
    public void setAdvanceReportHH(String advanceReportHH)
    {
        this.advanceReportHH = advanceReportHH;
    }
    
    public String getAdvanceReportMM()
    {
        return advanceReportMM;
    }
    
    public void setAdvanceReportMM(String advanceReportMM)
    {
        this.advanceReportMM = advanceReportMM;
    }
    
    public Integer getCrtEmpId()
    {
        return crtEmpId;
    }
    
    public void setCrtEmpId(Integer crtEmpId)
    {
        this.crtEmpId = crtEmpId;
    }
    
    public Integer getUptEmpId()
    {
        return uptEmpId;
    }
    
    public void setUptEmpId(Integer uptEmpId)
    {
        this.uptEmpId = uptEmpId;
    }
    
    public Boolean getDelFlg()
    {
        return delFlg;
    }
    
    public void setDelFlg(Boolean delFlg)
    {
        this.delFlg = delFlg;
    }
    
    public Date getCrtDt()
    {
        return crtDt;
    }
    
    public void setCrtDt(Date crtDt)
    {
        this.crtDt = crtDt;
    }
    
    public Date getUptDt()
    {
        return uptDt;
    }
    
    public void setUptDt(Date uptDt)
    {
        this.uptDt = uptDt;
    }
    
    /**
     * @return the commissionRule
     */
    public String getCommissionRule()
    {
        return commissionRule;
    }
    
    /**
     * @param commissionRule the commissionRule to set
     */
    public void setCommissionRule(String commissionRule)
    {
        this.commissionRule = commissionRule;
    }
    
    /**
     * @return the authenticationKbn
     */
    public Integer getAuthenticationKbn()
    {
        return authenticationKbn;
    }
    
    /**
     * @param authenticationKbn the authenticationKbn to set
     */
    public void setAuthenticationKbn(Integer authenticationKbn)
    {
        this.authenticationKbn = authenticationKbn;
    }
    
    /**
     * @return the commissionKbn
     */
    public Integer getCommissionKbn()
    {
        return commissionKbn;
    }
    
    /**
     * @param commissionKbn the commissionKbn to set
     */
    public void setCommissionKbn(Integer commissionKbn)
    {
        this.commissionKbn = commissionKbn;
    }
    
    /**
     * @return the payKbn
     */
    public Integer getPayKbn()
    {
        return payKbn;
    }
    
    /**
     * @param payKbn the payKbn to set
     */
    public void setPayKbn(Integer payKbn)
    {
        this.payKbn = payKbn;
    }
    
    /**
     * @return the saleKbn
     */
    public Integer getSaleKbn()
    {
        return saleKbn;
    }
    
    /**
     * @param saleKbn the saleKbn to set
     */
    public void setSaleKbn(Integer saleKbn)
    {
        this.saleKbn = saleKbn;
    }
    
    /**
     * @return the reportKbn
     */
    public Integer getReportKbn()
    {
        return reportKbn;
    }
    
    /**
     * @param reportKbn the reportKbn to set
     */
    public void setReportKbn(Integer reportKbn)
    {
        this.reportKbn = reportKbn;
    }

    /**
     * @return the authenticationKbnStr
     */
    public String getAuthenticationKbnStr()
    {
        return SystemParam.getDicValueByDicCode(authenticationKbn+"");
    }

    /**
     * @param authenticationKbnStr the authenticationKbnStr to set
     */
    public void setAuthenticationKbnStr(String authenticationKbnStr)
    {
        this.authenticationKbnStr = authenticationKbnStr;
    }

    /**
     * @return the commissionKbnStr
     */
    public String getCommissionKbnStr()
    {
        return SystemParam.getDicValueByDicCode(commissionKbn+"");
    }

    /**
     * @param commissionKbnStr the commissionKbnStr to set
     */
    public void setCommissionKbnStr(String commissionKbnStr)
    {
        this.commissionKbnStr = commissionKbnStr;
    }

    /**
     * @return the payKbnStr
     */
    public String getPayKbnStr()
    {
        return SystemParam.getDicValueByDicCode(payKbn+"");
    }

    /**
     * @param payKbnStr the payKbnStr to set
     */
    public void setPayKbnStr(String payKbnStr)
    {
        this.payKbnStr = payKbnStr;
    }

    /**
     * @return the saleKbnStr
     */
    public String getSaleKbnStr()
    {
        return SystemParam.getDicValueByDicCode(saleKbn+"");
    }

    /**
     * @param saleKbnStr the saleKbnStr to set
     */
    public void setSaleKbnStr(String saleKbnStr)
    {
        this.saleKbnStr = saleKbnStr;
    }

    /**
     * @return the reportKbnStr
     */
    public String getReportKbnStr()
    {
        return SystemParam.getDicValueByDicCode(reportKbn+"");
    }

    /**
     * @param reportKbnStr the reportKbnStr to set
     */
    public void setReportKbnStr(String reportKbnStr)
    {
        this.reportKbnStr = reportKbnStr;
    }
    //结算条件 21901签约 21902成销 21903结算确认
    private Integer commissionCondition;

	public Integer getCommissionCondition() {
		return commissionCondition;
	}
	public void setCommissionCondition(Integer commissionCondition) {
		this.commissionCondition = commissionCondition;
	}
    private String commissionConditionNm;
	public String getCommissionConditionNm() {
		return commissionConditionNm;
	}
	public void setCommissionConditionNm(String commissionConditionNm) {
		this.commissionConditionNm = commissionConditionNm;
	}
    
}
