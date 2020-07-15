package cn.com.eju.deal.dto.oa;

import java.util.List;

/**   
* Model类
* @author (li_xiaodong)
* @date 2016年5月3日 下午9:15:09
*/
public class OaModifyDto
{
    
    /**
    * ID
    */
    private Long id;
    
    /**
    * 事业部区域
    */
    private String field0003;
    
    /**
    * 业绩归属拓展人员姓名
    */
    private String field0004;
    
    /**
    * 协议书编号
    */
    private String field0005;
    
    /**
    * 特殊情况
    */
    private String field0006;
    
    /**
    * 合作协议书类型
    */
    private String field0007;
    
    /**
    * 流程列别
    */
    private String field0008;
    
    /**
    * 中介全称
    */
    private String field0009;
    
    /**
    * 原店招名称
    */
    private String field0010;
    
    /**
    * 法定代表人
    */
    private String field0011;
    
    /**
    * 中介授权代表
    */
    private String field0012;
    
    /**
    * 中介住所
    */
    private String field0013;
    
    /**
    * 中介联系方式
    */
    private String field0014;
    
    /**
    * 合作模式
    */
    private String field0015;
    
    /**
    * 合作期限
    */
    private String field0016;
    
    /**
    * 合作门店数
    */
    private String field0017;
    
    /**
    * 违约金数额
    */
    private String field0018;
    
    /**
    * 每店保证金数额
    */
    private String field0019;
    
    /**
    * 总保证金数额
    */
    private String field0020;
    
    /**
    * 补充条款
    */
    private String field0021;
    
    /**
    * 其他
    */
    private String field0027;
    
    /**
    * 公司名称
    */
    private String field0029;
    
    /**
     * 业绩归属拓展人员工号
     */
    private String field0031;
    
    /**
    * 公司编码
    */
    private int field0032;
    
    /**
     * PMLS单据号
     */
    private String field0035;
    
    /**
    * 经营地址
    */
    private List<String> field0030;
    
    /**
    * 营业执照
    */
    private List<String> licenses;
    
    /**
    * 合作协议书
    */
    private List<String> agreements;
    
    /**
     * 中介法定代表人/负责人身份证或名片
     */
    private List<String> idCards;
    
    /**
    * 房友系统申请安装单
    */
    private List<String> installations;
    
    /**
    * 门店照片
    */
    private List<String> storePhotos;
    /**
     * 重要提示函
     */
    private List<String> noticePhotos;
    
    /**
     * 补充协议
     * @return
     */
    private List<String> complementPhotos;
    
    public List<String> getComplementPhotos() {
		return complementPhotos;
	}

	public void setComplementPhotos(List<String> complementPhotos) {
		this.complementPhotos = complementPhotos;
	}

	public List<String> getNoticePhotos() {
		return noticePhotos;
	}

	public void setNoticePhotos(List<String> noticePhotos) {
		this.noticePhotos = noticePhotos;
	}

	/**
     * 其它照片
     */
    private List<String> others;
  //Add by WangLei 2017/04/07 Start
    
    /**
     * 合同类别
     */
    private String field0038;
    
	
	/**
     * 续签合作协议书类型
     */
    private String field0037;
    
    /**
     * 续签合作协议书编号
     */
    private String field0040;
    
    /**
     * 原合作签约报批单编号
     */
    private String field0041;
    
    /**
     * 门店到账保证金
     */
    private String field0039;
    
    public Long getId()
    {
        return id;
    }
    
    public void setId(Long id)
    {
        this.id = id;
    }
    
    public String getField0003()
    {
        return field0003;
    }
    
    public void setField0003(String field0003)
    {
        this.field0003 = field0003;
    }
    
    public String getField0004()
    {
        return field0004;
    }
    
    public void setField0004(String field0004)
    {
        this.field0004 = field0004;
    }
    
    public String getField0005()
    {
        return field0005;
    }
    
    public void setField0005(String field0005)
    {
        this.field0005 = field0005;
    }
    
    public String getField0006()
    {
        return field0006;
    }
    
    public void setField0006(String field0006)
    {
        this.field0006 = field0006;
    }
    
    public String getField0007()
    {
        return field0007;
    }
    
    public void setField0007(String field0007)
    {
        this.field0007 = field0007;
    }
    
    public String getField0008()
    {
        return field0008;
    }
    
    public void setField0008(String field0008)
    {
        this.field0008 = field0008;
    }
    
    public String getField0009()
    {
        return field0009;
    }
    
    public void setField0009(String field0009)
    {
        this.field0009 = field0009;
    }
    
    public String getField0010()
    {
        return field0010;
    }
    
    public void setField0010(String field0010)
    {
        this.field0010 = field0010;
    }
    
    public String getField0011()
    {
        return field0011;
    }
    
    public void setField0011(String field0011)
    {
        this.field0011 = field0011;
    }
    
    public String getField0012()
    {
        return field0012;
    }
    
    public void setField0012(String field0012)
    {
        this.field0012 = field0012;
    }
    
    public String getField0013()
    {
        return field0013;
    }
    
    public void setField0013(String field0013)
    {
        this.field0013 = field0013;
    }
    
    public String getField0014()
    {
        return field0014;
    }
    
    public void setField0014(String field0014)
    {
        this.field0014 = field0014;
    }
    
    public String getField0015()
    {
        return field0015;
    }
    
    public void setField0015(String field0015)
    {
        this.field0015 = field0015;
    }
    
    public String getField0016()
    {
        return field0016;
    }
    
    public void setField0016(String field0016)
    {
        this.field0016 = field0016;
    }
    
    public String getField0017()
    {
        return field0017;
    }
    
    public void setField0017(String field0017)
    {
        this.field0017 = field0017;
    }
    
    public String getField0018()
    {
        return field0018;
    }
    
    public void setField0018(String field0018)
    {
        this.field0018 = field0018;
    }
    
    public String getField0019()
    {
        return field0019;
    }
    
    public void setField0019(String field0019)
    {
        this.field0019 = field0019;
    }
    
    public String getField0020()
    {
        return field0020;
    }
    
    public void setField0020(String field0020)
    {
        this.field0020 = field0020;
    }
    
    public String getField0021()
    {
        return field0021;
    }
    
    public void setField0021(String field0021)
    {
        this.field0021 = field0021;
    }
    
    public String getField0027()
    {
        return field0027;
    }
    
    public void setField0027(String field0027)
    {
        this.field0027 = field0027;
    }
    
    public String getField0029()
    {
        return field0029;
    }
    
    public void setField0029(String field0029)
    {
        this.field0029 = field0029;
    }
    
    public int getField0032()
    {
        return field0032;
    }
    
    public void setField0032(int field0032)
    {
        this.field0032 = field0032;
    }
    
    public List<String> getField0030()
    {
        return field0030;
    }
    
    public void setField0030(List<String> field0030)
    {
        this.field0030 = field0030;
    }
    
    public List<String> getLicenses()
    {
        return licenses;
    }
    
    public void setLicenses(List<String> licenses)
    {
        this.licenses = licenses;
    }
    
    public List<String> getAgreements()
    {
        return agreements;
    }
    
    public void setAgreements(List<String> agreements)
    {
        this.agreements = agreements;
    }
    
    public List<String> getInstallations()
    {
        return installations;
    }
    
    public void setInstallations(List<String> installations)
    {
        this.installations = installations;
    }
    
    public List<String> getStorePhotos()
    {
        return storePhotos;
    }
    
    public void setStorePhotos(List<String> storePhotos)
    {
        this.storePhotos = storePhotos;
    }
    
    public String getField0031()
    {
        return field0031;
    }
    
    public void setField0031(String field0031)
    {
        this.field0031 = field0031;
    }
    
    public String getField0035()
    {
        return field0035;
    }
    
    public void setField0035(String field0035)
    {
        this.field0035 = field0035;
    }
    
    public List<String> getIdCards()
    {
        return idCards;
    }
    
    public void setIdCards(List<String> idCards)
    {
        this.idCards = idCards;
    }
    
    public List<String> getOthers()
    {
        return others;
    }
    
    public void setOthers(List<String> others)
    {
        this.others = others;
    }

	public String getField0038() {
		return field0038;
	}

	public void setField0038(String field0038) {
		this.field0038 = field0038;
	}

	public String getField0037() {
		return field0037;
	}

	public void setField0037(String field0037) {
		this.field0037 = field0037;
	}

	public String getField0040() {
		return field0040;
	}

	public void setField0040(String field0040) {
		this.field0040 = field0040;
	}

	public String getField0041() {
		return field0041;
	}

	public void setField0041(String field0041) {
		this.field0041 = field0041;
	}

	public String getField0039() {
		return field0039;
	}

	public void setField0039(String field0039) {
		this.field0039 = field0039;
	}
    
}