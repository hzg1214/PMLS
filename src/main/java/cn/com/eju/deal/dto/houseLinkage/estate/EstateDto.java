package cn.com.eju.deal.dto.houseLinkage.estate;

import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.util.StringUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
* EstateDto  Dto
* @author (qianwei)
* @date 2016年3月22日 下午4:42:54
*/
public class EstateDto implements Serializable
{
    /**
    * TODO(用一句话描述这个变量表示什么)
    */
    private static final long serialVersionUID = -5775427213591322713L;

    private Integer id;

    private String estateId;

    // 区域
    private String districtId;

    // 板块
    private String areaId;

    // 楼盘名
    private String estateNm;

    // 审核状态
    private Integer auditStatus;

    private String auditMemo;

    // 发布状态
    private Integer releaseStatus;

    // 下架说明
    private String releaseOffMemo;

    // 发布时间
    private String releaseDt;

    // 销售状态
    private Integer salesStatus;

    // 合作方
    private Integer partner;

    // 合作人
    private String partnerNm;

    // 合作期
    private Date cooperationDtStart;

    private Date cooperationDtEnd;

    private String cityNo;
    //项目所在地
    private String realityCityNo;

    private Integer deptId;

    private Integer empId;

    // 均价
    private BigDecimal salePriceUnit;
    // 总价段
 	private BigDecimal salePriceUnitMin;
 	private BigDecimal salePriceUnitMax;

    // 地址
    private String address;

    // 楼盘坐标X
    private String addressCoordinateX;

    // 楼盘坐标Y
    private String addressCoordinateY;

    // 标签
    private String mark;

    //开盘状态
    private Integer openKbn;

    // 开盘时间
    private Date openTime;

    //交房状态
    private Integer houseTransferKbn;

    // 交房时间
    private String houseTransferTime;

    // 项目简介
    private String projectDescription;

    // 户型(查询用）
    private String estateTypeSearch;

    // 开发商
    private String devCompany;
    //大客户时，开发商id
    private Integer bigCustomerId;

    // 案场地址
    private String fieldAddress;

    // 合作有效期
    private BigDecimal cooperationExpDt;

    // 预售许可
    private Integer preSalePermitKbn;

    // 建筑类型
    private String typeKbn;

    // 物业类型
    private String mgtKbn;

    // 产权年限
    private String ownYearKbn;

    // 装修情况
    private String decorationKbn;

    // 规划户数
    private Integer houseCnt;

    // 车位情况
    private Integer parkCnt;

    // 停车费
    private BigDecimal parkFee;

    // 物业公司
    private String mgtCompany;

    // 容积率
    private String rateFAR;

    // 绿化率
    private String rateGreen;

    // 物业费用
    private BigDecimal mgtPrice;

    // 梯户
    private String staircaseHousehold;

    // 供暖方式
    private Integer heatKbn;

    // 水电燃气
    private Integer hydropowerGasKbn;

    // 案场归属部门
    private Integer sceneDeptId;

    // 案场归属人
    private Integer sceneEmpId;

    private Integer crtEmpId;

    private Integer uptEmpId;

    private Boolean delFlg;

    private Date crtDt;

    private Date uptDt;// 日期类型转换

    //以下是扩展字段
    private String cityNm;

    // 区域名称
    private String districtNm;

    // 板块名称
    private String areaNm;

    // 合作期
    private String strCooperationDtStart;

    private String strCooperationDtEnd;

    // 开盘时间
    private String strOpenTime;

    // 带看日期
    private String strRelationDtStart;

    private String strRelationDtEnd;

    // 认筹日期
    private String strPledgedDtStart;

    private String strPledgedDtEnd;

    // 认购日期
    private String strSubscribedDtStart;

    private String strSubscribedDtEnd;

    // 报备日期
    private String strReportDtStart;

    private String strReportDtEnd;

    private String strCrtDt;

    private String strUptDt;

    // 案场归属人
    private String sceneEmpName;

    private String auditStatusStr;//审核状态

    private String releaseStatusStr;//发布状态

    private String salesStatusStr;//销售状态

    private String partnerStr;//合作方

    private String sceneDeptNm;//案场负责人部门

    private String mgtKbnStr;//物业类型

    private String ownYearKbnStr;//产权年限

    private String decorationKbnStr;//装修情况

    private String typeKbnStr;//建筑类型

    private String heatKbnStr;//供暖方式

    private String hydropowerGasKbnStr;//水电燃气

    private List<String> markLst;//标签列表

    private List<Map<String, String>> tiHus;//梯户

    // 备案名
    private String recordName;
    //推广名
    private String promotionName;
    //签约名
    private String signName;
    //项目所在地
    private String realityCityNm;

    private Integer projectDepartmentId;   //归属项目部ID
	private String projectDepartment; //归属项目部名称
	private Integer cooperationMode;  //合作模式
	private Date endDate;     //结案日期
	private Date startDate;     //跟单日期
	//Add By QJP 2017/08/03 oa返回字段 start
    private Integer projectStatus; //项目状态
    private Date signDate; //签约日期
    private String oaSignNo;  //oa签约单据号
   	private Date measureDate;   //测算日期
    private String oaMeasureNo;   //oa测算单据号
    private Date budgetDate;     //预算日期
	private String oaBudgetNo;    //oa预算单据号
    //Add By QJP 2017/08/03 oa返回字段  end
    private String projectNo;   //项目编号
    private String devCompanyBroker; //开发商对接人
    private String devCompanyBrokerTel; //开发商对接人电话
    private String empTel; //开发负责人电话

    private String partnerContactNm;
    private String partnerContactTel;

    private String opEstateId;
    private String opEstateNm;

    private Integer estatePosition;
    private String countryNo;

    private Boolean isPureDy;

    //是否可垫佣甲方
    private Integer custodyFlg;

    //开发商全称
    private String developerName;

    //垫佣甲方简称
    private Integer mattressNailId;
    //开发商ID
    private String devCompanyId;
    //刷筹
    private Integer brushRaiseFlag;
    private String brushRaiseFlagStr;
    //共场
    private Integer totalFieldFlag;
    private String totalFieldFlagStr;

    //是否借佣
    private Integer isExcuseCommision;
    private String isExcuseCommisionStr;
    
    //项目负责人
    private Integer projectLeaderEmpId;
    //项目负责人电话
    private String projectLeaderTel;

    private String partnerAbbreviationNm;
    private String partnerAbbreviationCode;
    private Integer developerBrandId;
    private String developerBrandName;

    private BigDecimal vendibilityAmount;
    private BigDecimal monthRoughAmount;
    private String cooperationStatus;
    private String projectInfoDesc;
    private String requiredForSupport;

    public Integer getProjectLeaderEmpId() {
		return projectLeaderEmpId;
	}

	public void setProjectLeaderEmpId(Integer projectLeaderEmpId) {
		this.projectLeaderEmpId = projectLeaderEmpId;
	}

	public String getProjectLeaderTel() {
		return projectLeaderTel;
	}

	public void setProjectLeaderTel(String projectLeaderTel) {
		this.projectLeaderTel = projectLeaderTel;
	}

	public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getBrushRaiseFlag() {
        return brushRaiseFlag;
    }

    public void setBrushRaiseFlag(Integer brushRaiseFlag) {
        this.brushRaiseFlag = brushRaiseFlag;
    }

    public Integer getTotalFieldFlag() {
        return totalFieldFlag;
    }

    public void setTotalFieldFlag(Integer totalFieldFlag) {
        this.totalFieldFlag = totalFieldFlag;
    }

    public Integer getMattressNailId() {
		return mattressNailId;
	}

	public void setMattressNailId(Integer mattressNailId) {
		this.mattressNailId = mattressNailId;
	}

	public String getDeveloperName() {
		return developerName;
	}

	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}

	public Integer getCustodyFlg() {
		return custodyFlg;
	}

	public void setCustodyFlg(Integer custodyFlg) {
		this.custodyFlg = custodyFlg;
	}

	public Boolean getPureDy() {
        return isPureDy;
    }

    public void setPureDy(Boolean pureDy) {
        isPureDy = pureDy;
    }

    /**
     * @return  the 【partnerContactNm】
     */
    public String getPartnerContactNm() {

        return partnerContactNm;
    }

    /**
     * @param partnerContactNm the 【partnerContactNm】 to set
     */
    public void setPartnerContactNm(String partnerContactNm) {

        this.partnerContactNm = partnerContactNm;
    }

    /**
     * @return  the 【partnerContactTel】
     */
    public String getPartnerContactTel() {

        return partnerContactTel;
    }

    /**
     * @param partnerContactTel the 【partnerContactTel】 to set
     */
    public void setPartnerContactTel(String partnerContactTel) {

        this.partnerContactTel = partnerContactTel;
    }

    public String getEmpTel() {
		return empTel;
	}

	public void setEmpTel(String empTel) {
		this.empTel = empTel;
	}

	public String getDevCompanyBroker() {
		return devCompanyBroker;
	}

	public void setDevCompanyBroker(String devCompanyBroker) {
		this.devCompanyBroker = devCompanyBroker;
	}

	public String getDevCompanyBrokerTel() {
		return devCompanyBrokerTel;
	}

	public void setDevCompanyBrokerTel(String devCompanyBrokerTel) {
		this.devCompanyBrokerTel = devCompanyBrokerTel;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getOaSignNo() {
		return oaSignNo;
	}

	public void setOaSignNo(String oaSignNo) {
		this.oaSignNo = oaSignNo;
	}

	public Date getBudgetDate() {
		return budgetDate;
	}

	public void setBudgetDate(Date budgetDate) {
		this.budgetDate = budgetDate;
	}

	public String getOaBudgetNo() {
		return oaBudgetNo;
	}

	public void setOaBudgetNo(String oaBudgetNo) {
		this.oaBudgetNo = oaBudgetNo;
	}
    public Integer getProjectDepartmentId() {
		return projectDepartmentId;
	}

	public void setProjectDepartmentId(Integer projectDepartmentId) {
		this.projectDepartmentId = projectDepartmentId;
	}
    public String getProjectDepartment() {
		return projectDepartment;
	}

	public void setProjectDepartment(String projectDepartment) {
		this.projectDepartment = projectDepartment;
	}
    public String getRecordName()
    {
        return recordName;
    }

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	public Date getMeasureDate() {
		return measureDate;
	}

	public void setMeasureDate(Date measureDate) {
		this.measureDate = measureDate;
	}

	public String getOaMeasureNo() {
		return oaMeasureNo;
	}

	public void setOaMeasureNo(String oaMeasureNo) {
		this.oaMeasureNo = oaMeasureNo;
	}
	public void setRecordName(String recordName)
    {
        this.recordName = recordName;
    }

    public String getPromotionName()
    {
        return promotionName;
    }

    public void setPromotionName(String promotionName)
    {
        this.promotionName = promotionName;
    }

    public String getSignName()
    {
        return signName;
    }

    public void setSignName(String signName)
    {
        this.signName = signName;
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

    public String getDistrictId()
    {
        return districtId;
    }

    public void setDistrictId(String districtId)
    {
        this.districtId = districtId;
    }

    public String getAreaId()
    {
        return areaId;
    }

    public void setAreaId(String areaId)
    {
        this.areaId = areaId;
    }

    public String getEstateNm()
    {
        return estateNm;
    }

    public void setEstateNm(String estateNm)
    {
        this.estateNm = estateNm;
    }

    public Integer getAuditStatus()
    {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus)
    {
        this.auditStatus = auditStatus;
    }

    public Integer getProjectStatus()
    {
        return projectStatus;
    }

    public void setProjectStatus(Integer projectStatus)
    {
        this.projectStatus = projectStatus;
    }

    public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

    public String getAuditMemo()
    {
        return auditMemo;
    }

    public void setAuditMemo(String auditMemo)
    {
        this.auditMemo = auditMemo;
    }

    public Integer getReleaseStatus()
    {
        return releaseStatus;
    }

    public void setReleaseStatus(Integer releaseStatus)
    {
        this.releaseStatus = releaseStatus;
    }

    public String getReleaseOffMemo()
    {
        return releaseOffMemo;
    }

    public void setReleaseOffMemo(String releaseOffMemo)
    {
        this.releaseOffMemo = releaseOffMemo;
    }

    public String getReleaseDt()
    {
        return releaseDt;
    }

    public void setReleaseDt(String releaseDt)
    {
        this.releaseDt = releaseDt;
    }

    /**
     * @return the salesStatus
     */
    public Integer getSalesStatus()
    {
        return salesStatus;
    }

    /**
     * @param salesStatus the salesStatus to set
     */
    public void setSalesStatus(Integer salesStatus)
    {
        this.salesStatus = salesStatus;
    }

    /**
     * @return the partner
     */
    public Integer getPartner()
    {
        return partner;
    }

    /**
     * @param partner the partner to set
     */
    public void setPartner(Integer partner)
    {
        this.partner = partner;
    }

    /**
     * @return the partnerNm
     */
    public String getPartnerNm()
    {
        return partnerNm;
    }

    /**
     * @param partnerNm the partnerNm to set
     */
    public void setPartnerNm(String partnerNm)
    {
        this.partnerNm = partnerNm;
    }

    /**
     * @return the cooperationDtStart
     */
    public Date getCooperationDtStart()
    {
        return cooperationDtStart;
    }

    /**
     * @param cooperationDtStart the cooperationDtStart to set
     */
    public void setCooperationDtStart(Date cooperationDtStart)
    {
        this.cooperationDtStart = cooperationDtStart;
    }

    /**
     * @return the cooperationDtEnd
     */
    public Date getCooperationDtEnd()
    {
        return cooperationDtEnd;
    }

    /**
     * @param cooperationDtEnd the cooperationDtEnd to set
     */
    public void setCooperationDtEnd(Date cooperationDtEnd)
    {
        this.cooperationDtEnd = cooperationDtEnd;
    }

    /**
     * @return the cityNo
     */
    public String getCityNo()
    {
        return cityNo;
    }

    /**
     * @param cityNo the cityNo to set
     */
    public void setCityNo(String cityNo)
    {
        this.cityNo = cityNo;
    }

    /**
     * @return the deptId
     */
    public Integer getDeptId()
    {
        return deptId;
    }

    /**
     * @param deptId the deptId to set
     */
    public void setDeptId(Integer deptId)
    {
        this.deptId = deptId;
    }

    /**
     * @return the empId
     */
    public Integer getEmpId()
    {
        return empId;
    }

    /**
     * @param empId the empId to set
     */
    public void setEmpId(Integer empId)
    {
        this.empId = empId;
    }

    /**
     * @return the salePriceUnit
     */
    public BigDecimal getSalePriceUnit()
    {
        return salePriceUnit;
    }

    /**
     * @param salePriceUnit the salePriceUnit to set
     */
    public void setSalePriceUnit(BigDecimal salePriceUnit)
    {
        this.salePriceUnit = salePriceUnit;
    }

    /**
     * @return the address
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    /**
     * @return the addressCoordinateX
     */
    public String getAddressCoordinateX()
    {
        return addressCoordinateX;
    }

    /**
     * @param addressCoordinateX the addressCoordinateX to set
     */
    public void setAddressCoordinateX(String addressCoordinateX)
    {
        this.addressCoordinateX = addressCoordinateX;
    }

    /**
     * @return the addressCoordinateY
     */
    public String getAddressCoordinateY()
    {
        return addressCoordinateY;
    }

    /**
     * @param addressCoordinateY the addressCoordinateY to set
     */
    public void setAddressCoordinateY(String addressCoordinateY)
    {
        this.addressCoordinateY = addressCoordinateY;
    }

    /**
     * @return the mark
     */
    public String getMark()
    {
        return mark;
    }

    /**
     * @param mark the mark to set
     */
    public void setMark(String mark)
    {
        this.mark = mark;
    }

    /**
     * @return the openKbn
     */
    public Integer getOpenKbn()
    {
        return openKbn;
    }

    /**
     * @param openKbn the openKbn to set
     */
    public void setOpenKbn(Integer openKbn)
    {
        this.openKbn = openKbn;
    }

    /**
     * @return the openTime
     */
    public Date getOpenTime()
    {
        return openTime;
    }

    /**
     * @param openTime the openTime to set
     */
    public void setOpenTime(Date openTime)
    {
        this.openTime = openTime;
    }

    /**
     * @return the houseTransferKbn
     */
    public Integer getHouseTransferKbn()
    {
        return houseTransferKbn;
    }

    /**
     * @param houseTransferKbn the houseTransferKbn to set
     */
    public void setHouseTransferKbn(Integer houseTransferKbn)
    {
        this.houseTransferKbn = houseTransferKbn;
    }

    /**
     * @return the houseTransferTime
     */
    public String getHouseTransferTime()
    {
        return houseTransferTime;
    }

    /**
     * @param houseTransferTime the houseTransferTime to set
     */
    public void setHouseTransferTime(String houseTransferTime)
    {
        this.houseTransferTime = houseTransferTime;
    }

    /**
     * @return the projectDescription
     */
    public String getProjectDescription()
    {
        return projectDescription;
    }

    /**
     * @param projectDescription the projectDescription to set
     */
    public void setProjectDescription(String projectDescription)
    {
        this.projectDescription = projectDescription;
    }

    /**
     * @return the estateTypeSearch
     */
    public String getEstateTypeSearch()
    {
        return estateTypeSearch;
    }

    /**
     * @param estateTypeSearch the estateTypeSearch to set
     */
    public void setEstateTypeSearch(String estateTypeSearch)
    {
        this.estateTypeSearch = estateTypeSearch;
    }

    /**
     * @return the devCompany
     */
    public String getDevCompany()
    {
        return devCompany;
    }

    /**
     * @param devCompany the devCompany to set
     */
    public void setDevCompany(String devCompany)
    {
        this.devCompany = devCompany;
    }

    /**
     * @return the fieldAddress
     */
    public String getFieldAddress()
    {
        return fieldAddress;
    }

    /**
     * @param fieldAddress the fieldAddress to set
     */
    public void setFieldAddress(String fieldAddress)
    {
        this.fieldAddress = fieldAddress;
    }

    /**
     * @return the cooperationExpDt
     */
    public BigDecimal getCooperationExpDt()
    {
        return cooperationExpDt;
    }

    /**
     * @param cooperationExpDt the cooperationExpDt to set
     */
    public void setCooperationExpDt(BigDecimal cooperationExpDt)
    {
        this.cooperationExpDt = cooperationExpDt;
    }

    /**
     * @return the preSalePermitKbn
     */
    public Integer getPreSalePermitKbn()
    {
        return preSalePermitKbn;
    }

    /**
     * @param preSalePermitKbn the preSalePermitKbn to set
     */
    public void setPreSalePermitKbn(Integer preSalePermitKbn)
    {
        this.preSalePermitKbn = preSalePermitKbn;
    }

    /**
     * @return the typeKbn
     */
    public String getTypeKbn()
    {
        return typeKbn;
    }

    /**
     * @param typeKbn the typeKbn to set
     */
    public void setTypeKbn(String typeKbn)
    {
        this.typeKbn = typeKbn;
    }

    /**
     * @return the mgtKbn
     */
    public String getMgtKbn()
    {
        return mgtKbn;
    }

    /**
     * @param mgtKbn the mgtKbn to set
     */
    public void setMgtKbn(String mgtKbn)
    {
        this.mgtKbn = mgtKbn;
    }

    /**
     * @return the ownYearKbn
     */
    public String getOwnYearKbn()
    {
        return ownYearKbn;
    }

    /**
     * @param ownYearKbn the ownYearKbn to set
     */
    public void setOwnYearKbn(String ownYearKbn)
    {
        this.ownYearKbn = ownYearKbn;
    }

    /**
     * @return the decorationKbn
     */
    public String getDecorationKbn()
    {
        return decorationKbn;
    }

    /**
     * @param decorationKbn the decorationKbn to set
     */
    public void setDecorationKbn(String decorationKbn)
    {
        this.decorationKbn = decorationKbn;
    }

    /**
     * @return the houseCnt
     */
    public Integer getHouseCnt()
    {
        return houseCnt;
    }

    /**
     * @param houseCnt the houseCnt to set
     */
    public void setHouseCnt(Integer houseCnt)
    {
        this.houseCnt = houseCnt;
    }

    /**
     * @return the parkCnt
     */
    public Integer getParkCnt()
    {
        return parkCnt;
    }

    /**
     * @param parkCnt the parkCnt to set
     */
    public void setParkCnt(Integer parkCnt)
    {
        this.parkCnt = parkCnt;
    }

    /**
     * @return the parkFee
     */
    public BigDecimal getParkFee()
    {
        return parkFee;
    }

    /**
     * @param parkFee the parkFee to set
     */
    public void setParkFee(BigDecimal parkFee)
    {
        this.parkFee = parkFee;
    }

    /**
     * @return the mgtCompany
     */
    public String getMgtCompany()
    {
        return mgtCompany;
    }

    /**
     * @param mgtCompany the mgtCompany to set
     */
    public void setMgtCompany(String mgtCompany)
    {
        this.mgtCompany = mgtCompany;
    }

    /**
     * @return the rateFAR
     */
    public String getRateFAR()
    {
        return rateFAR;
    }

    /**
     * @param rateFAR the rateFAR to set
     */
    public void setRateFAR(String rateFAR)
    {
        this.rateFAR = rateFAR;
    }

    /**
     * @return the rateGreen
     */
    public String getRateGreen()
    {
        return rateGreen;
    }

    /**
     * @param rateGreen the rateGreen to set
     */
    public void setRateGreen(String rateGreen)
    {
        this.rateGreen = rateGreen;
    }

    /**
     * @return the mgtPrice
     */
    public BigDecimal getMgtPrice()
    {
        return mgtPrice;
    }

    /**
     * @param mgtPrice the mgtPrice to set
     */
    public void setMgtPrice(BigDecimal mgtPrice)
    {
        this.mgtPrice = mgtPrice;
    }

    /**
     * @return the staircaseHousehold
     */
    public String getStaircaseHousehold()
    {
        return staircaseHousehold;
    }

    /**
     * @param staircaseHousehold the staircaseHousehold to set
     */
    public void setStaircaseHousehold(String staircaseHousehold)
    {
        this.staircaseHousehold = staircaseHousehold;
    }

    /**
     * @return the heatKbn
     */
    public Integer getHeatKbn()
    {
        return heatKbn;
    }

    /**
     * @param heatKbn the heatKbn to set
     */
    public void setHeatKbn(Integer heatKbn)
    {
        this.heatKbn = heatKbn;
    }

    /**
     * @return the hydropowerGasKbn
     */
    public Integer getHydropowerGasKbn()
    {
        return hydropowerGasKbn;
    }

    /**
     * @param hydropowerGasKbn the hydropowerGasKbn to set
     */
    public void setHydropowerGasKbn(Integer hydropowerGasKbn)
    {
        this.hydropowerGasKbn = hydropowerGasKbn;
    }

    /**
     * @return the sceneDeptId
     */
    public Integer getSceneDeptId()
    {
        return sceneDeptId;
    }

    /**
     * @param sceneDeptId the sceneDeptId to set
     */
    public void setSceneDeptId(Integer sceneDeptId)
    {
        this.sceneDeptId = sceneDeptId;
    }

    /**
     * @return the sceneEmpId
     */
    public Integer getSceneEmpId()
    {
        return sceneEmpId;
    }

    /**
     * @param sceneEmpId the sceneEmpId to set
     */
    public void setSceneEmpId(Integer sceneEmpId)
    {
        this.sceneEmpId = sceneEmpId;
    }

    /**
     * @return the crtEmpId
     */
    public Integer getCrtEmpId()
    {
        return crtEmpId;
    }

    /**
     * @param crtEmpId the crtEmpId to set
     */
    public void setCrtEmpId(Integer crtEmpId)
    {
        this.crtEmpId = crtEmpId;
    }

    /**
     * @return the uptEmpId
     */
    public Integer getUptEmpId()
    {
        return uptEmpId;
    }

    /**
     * @param uptEmpId the uptEmpId to set
     */
    public void setUptEmpId(Integer uptEmpId)
    {
        this.uptEmpId = uptEmpId;
    }

    /**
     * @return the delFlg
     */
    public Boolean getDelFlg()
    {
        return delFlg;
    }

    /**
     * @param delFlg the delFlg to set
     */
    public void setDelFlg(Boolean delFlg)
    {
        this.delFlg = delFlg;
    }

    /**
     * @return the crtDt
     */
    public Date getCrtDt()
    {
        return crtDt;
    }

    /**
     * @param crtDt the crtDt to set
     */
    public void setCrtDt(Date crtDt)
    {
        this.crtDt = crtDt;
    }

    /**
     * @return the uptDt
     */
    public Date getUptDt()
    {
        return uptDt;
    }

    /**
     * @param uptDt the uptDt to set
     */
    public void setUptDt(Date uptDt)
    {
        this.uptDt = uptDt;
    }

    /**
     * @return the districtNm
     */
    public String getDistrictNm()
    {
        return districtNm;
    }

    /**
     * @param districtNm the districtNm to set
     */
    public void setDistrictNm(String districtNm)
    {
        this.districtNm = districtNm;
    }

    /**
     * @return the areaNm
     */
    public String getAreaNm()
    {
        return areaNm;
    }

    /**
     * @param areaNm the areaNm to set
     */
    public void setAreaNm(String areaNm)
    {
        this.areaNm = areaNm;
    }

    /**
     * @return the strCooperationDtStart
     */
    public String getStrCooperationDtStart()
    {
        return strCooperationDtStart;
    }

    /**
     * @param strCooperationDtStart the strCooperationDtStart to set
     */
    public void setStrCooperationDtStart(String strCooperationDtStart)
    {
        this.strCooperationDtStart = strCooperationDtStart;
    }

    /**
     * @return the strCooperationDtEnd
     */
    public String getStrCooperationDtEnd()
    {
        return strCooperationDtEnd;
    }

    /**
     * @param strCooperationDtEnd the strCooperationDtEnd to set
     */
    public void setStrCooperationDtEnd(String strCooperationDtEnd)
    {
        this.strCooperationDtEnd = strCooperationDtEnd;
    }

    /**
     * @return the strOpenTime
     */
    public String getStrOpenTime()
    {
        return strOpenTime;
    }

    /**
     * @param strOpenTime the strOpenTime to set
     */
    public void setStrOpenTime(String strOpenTime)
    {
        this.strOpenTime = strOpenTime;
    }

    /**
     * @return the strRelationDtStart
     */
    public String getStrRelationDtStart()
    {
        return strRelationDtStart;
    }

    /**
     * @param strRelationDtStart the strRelationDtStart to set
     */
    public void setStrRelationDtStart(String strRelationDtStart)
    {
        this.strRelationDtStart = strRelationDtStart;
    }

    /**
     * @return the strRelationDtEnd
     */
    public String getStrRelationDtEnd()
    {
        return strRelationDtEnd;
    }

    /**
     * @param strRelationDtEnd the strRelationDtEnd to set
     */
    public void setStrRelationDtEnd(String strRelationDtEnd)
    {
        this.strRelationDtEnd = strRelationDtEnd;
    }

    /**
     * @return the strPledgedDtStart
     */
    public String getStrPledgedDtStart()
    {
        return strPledgedDtStart;
    }

    /**
     * @param strPledgedDtStart the strPledgedDtStart to set
     */
    public void setStrPledgedDtStart(String strPledgedDtStart)
    {
        this.strPledgedDtStart = strPledgedDtStart;
    }

    /**
     * @return the strPledgedDtEnd
     */
    public String getStrPledgedDtEnd()
    {
        return strPledgedDtEnd;
    }

    /**
     * @param strPledgedDtEnd the strPledgedDtEnd to set
     */
    public void setStrPledgedDtEnd(String strPledgedDtEnd)
    {
        this.strPledgedDtEnd = strPledgedDtEnd;
    }

    /**
     * @return the strSubscribedDtStart
     */
    public String getStrSubscribedDtStart()
    {
        return strSubscribedDtStart;
    }

    /**
     * @param strSubscribedDtStart the strSubscribedDtStart to set
     */
    public void setStrSubscribedDtStart(String strSubscribedDtStart)
    {
        this.strSubscribedDtStart = strSubscribedDtStart;
    }

    /**
     * @return the strSubscribedDtEnd
     */
    public String getStrSubscribedDtEnd()
    {
        return strSubscribedDtEnd;
    }

    /**
     * @param strSubscribedDtEnd the strSubscribedDtEnd to set
     */
    public void setStrSubscribedDtEnd(String strSubscribedDtEnd)
    {
        this.strSubscribedDtEnd = strSubscribedDtEnd;
    }

    /**
     * @return the strReportDtStart
     */
    public String getStrReportDtStart()
    {
        return strReportDtStart;
    }

    /**
     * @param strReportDtStart the strReportDtStart to set
     */
    public void setStrReportDtStart(String strReportDtStart)
    {
        this.strReportDtStart = strReportDtStart;
    }

    /**
     * @return the strReportDtEnd
     */
    public String getStrReportDtEnd()
    {
        return strReportDtEnd;
    }

    /**
     * @param strReportDtEnd the strReportDtEnd to set
     */
    public void setStrReportDtEnd(String strReportDtEnd)
    {
        this.strReportDtEnd = strReportDtEnd;
    }

    /**
     * @return the strCrtDt
     */
    public String getStrCrtDt()
    {
        return strCrtDt;
    }

    /**
     * @param strCrtDt the strCrtDt to set
     */
    public void setStrCrtDt(String strCrtDt)
    {
        this.strCrtDt = strCrtDt;
    }

    /**
     * @return the strUptDt
     */
    public String getStrUptDt()
    {
        return strUptDt;
    }

    /**
     * @param strUptDt the strUptDt to set
     */
    public void setStrUptDt(String strUptDt)
    {
        this.strUptDt = strUptDt;
    }

    /**
     * @return the auditStatusStr
     */
    public String getAuditStatusStr()
    {
        return SystemParam.getDicValueByDicCode(auditStatus + "");
    }

    /**
     * @param auditStatusStr the auditStatusStr to set
     */
    public void setAuditStatusStr(String auditStatusStr)
    {
        this.auditStatusStr = auditStatusStr;
    }

    /**
     * @return the releaseStatusStr
     */
    public String getReleaseStatusStr()
    {
        return SystemParam.getDicValueByDicCode(releaseStatus + "");
    }

    /**
     * @param releaseStatusStr the releaseStatusStr to set
     */
    public void setReleaseStatusStr(String releaseStatusStr)
    {
        this.releaseStatusStr = releaseStatusStr;
    }

    /**
     * @return the markLst
     */
    public List<String> getMarkLst()
    {
        markLst = new ArrayList<>();
        if (StringUtil.isNotEmpty(mark))
        {
            String[] markArr = mark.split(",");
            for (String string : markArr)
            {
                markLst.add(string);
            }
        }
        return markLst;
    }

    /**
     * @param markLst the markLst to set
     */
    public void setMarkLst(List<String> markLst)
    {
        this.markLst = markLst;
    }

    /**
     * @return the cityNm
     */
    public String getCityNm()
    {
        return cityNm;
    }

    /**
     * @param cityNm the cityNm to set
     */
    public void setCityNm(String cityNm)
    {
        this.cityNm = cityNm;
    }

    /**
     * @return the sceneEmpName
     */
    public String getSceneEmpName()
    {
        return sceneEmpName;
    }

    /**
     * @param sceneEmpName the sceneEmpName to set
     */
    public void setSceneEmpName(String sceneEmpName)
    {
        this.sceneEmpName = sceneEmpName;
    }

    /**
     * @return the tiHus
     */
    public List<Map<String, String>> getTiHus()
    {
        List<Map<String, String>> tiHus = new ArrayList<>();
        if (StringUtil.isNotEmpty(staircaseHousehold))
        {
            String[] sh = staircaseHousehold.split(",");
            Map<String, String> tiHu = new HashMap<String, String>();
            for (String str : sh)
            {
                tiHu = new HashMap<String, String>();
                if (str.length() == 3)
                {
                    tiHu.put("Ti", str.substring(0, 1));
                    tiHu.put("Hu", str.substring(2, 3));
                    tiHus.add(tiHu);
                }
            }
        }
        return tiHus;
    }

    /**
     * @param tiHus the tiHus to set
     */
    public void setTiHus(List<Map<String, String>> tiHus)
    {
        this.tiHus = tiHus;
    }

    /**
     * @return the salesStatusStr
     */
    public String getSalesStatusStr()
    {
        return SystemParam.getDicValueByDicCode(salesStatus + "");
    }

    /**
     * @param salesStatusStr the salesStatusStr to set
     */
    public void setSalesStatusStr(String salesStatusStr)
    {
        this.salesStatusStr = salesStatusStr;
    }

    /**
     * @return the partnerStr
     */
    public String getPartnerStr()
    {
        return SystemParam.getDicValueByDicCode(partner + "");
    }

    /**
     * @param partnerStr the partnerStr to set
     */
    public void setPartnerStr(String partnerStr)
    {
        this.partnerStr = partnerStr;
    }

    /**
     * @return the sceneDeptNm
     */
    public String getSceneDeptNm()
    {
        return sceneDeptNm;
    }

    /**
     * @param sceneDeptNm the sceneDeptNm to set
     */
    public void setSceneDeptNm(String sceneDeptNm)
    {
        this.sceneDeptNm = sceneDeptNm;
    }

    /**
     * @return the mgtKbnStr
     */
    public String getMgtKbnStr()
    {
        return getStrByCode(mgtKbn);
    }

    /**
     * @param mgtKbnStr the mgtKbnStr to set
     */
    public void setMgtKbnStr(String mgtKbnStr)
    {
        this.mgtKbnStr = mgtKbnStr;
    }

    /**
     * @return the ownYearKbnStr
     */
    public String getOwnYearKbnStr()
    {
        return getStrByCode(ownYearKbn);
    }

    /**
     * @param ownYearKbnStr the ownYearKbnStr to set
     */
    public void setOwnYearKbnStr(String ownYearKbnStr)
    {
        this.ownYearKbnStr = ownYearKbnStr;
    }

    /**
     * @return the decorationKbnStr
     */
    public String getDecorationKbnStr()
    {
        return getStrByCode(decorationKbn);
    }

    /**
     * @param decorationKbnStr the decorationKbnStr to set
     */
    public void setDecorationKbnStr(String decorationKbnStr)
    {
        this.decorationKbnStr = decorationKbnStr;
    }

    /**
     * @return the typeKbnStr
     */
    public String getTypeKbnStr()
    {
        return getStrByCode(typeKbn);
    }

    /**
     * @param typeKbnStr the typeKbnStr to set
     */
    public void setTypeKbnStr(String typeKbnStr)
    {
        this.typeKbnStr = typeKbnStr;
    }

    /**
     * @return the heatKbnStr
     */
    public String getHeatKbnStr()
    {
        return SystemParam.getDicValueByDicCode(heatKbn + "");
    }

    /**
     * @param heatKbnStr the heatKbnStr to set
     */
    public void setHeatKbnStr(String heatKbnStr)
    {
        this.heatKbnStr = heatKbnStr;
    }

    /**
     * @return the hydropowerGasKbnStr
     */
    public String getHydropowerGasKbnStr()
    {
        return SystemParam.getDicValueByDicCode(hydropowerGasKbn + "");
    }

    /**
     * @param hydropowerGasKbnStr the hydropowerGasKbnStr to set
     */
    public void setHydropowerGasKbnStr(String hydropowerGasKbnStr)
    {
        this.hydropowerGasKbnStr = hydropowerGasKbnStr;
    }

	public BigDecimal getSalePriceUnitMin() {
		return salePriceUnitMin;
	}

	public void setSalePriceUnitMin(BigDecimal salePriceUnitMin) {
		this.salePriceUnitMin = salePriceUnitMin;
	}

	public BigDecimal getSalePriceUnitMax() {
		return salePriceUnitMax;
	}

	public void setSalePriceUnitMax(BigDecimal salePriceUnitMax) {
		this.salePriceUnitMax = salePriceUnitMax;
	}

	public String getRealityCityNo() {
		return realityCityNo;
	}

	public void setRealityCityNo(String realityCityNo) {
		this.realityCityNo = realityCityNo;
	}

	public String getRealityCityNm() {
		return realityCityNm;
	}

	public void setRealityCityNm(String realityCityNm) {
		this.realityCityNm = realityCityNm;
	}
	public Integer getCooperationMode() {
		return cooperationMode;
	}

	public void setCooperationMode(Integer cooperationMode) {
		this.cooperationMode = cooperationMode;
	}


    private String getStrByCode(String code) {
        String value = "";
        if (StringUtil.isNotEmpty(code)) {
            String[] typeArr = code.split(",");
            for (String type : typeArr) {
                if (StringUtil.isEmpty(value)) {
                    value = SystemParam.getDicValueByDicCode(type);
                } else {
                    value = value + "," + SystemParam.getDicValueByDicCode(type);
                }
            }
        }
        return value;
    }
    /**
     * 实际开盘时间
     */
    private Date realOpenTime;

	public Date getRealOpenTime() {
		return realOpenTime;
	}

	public void setRealOpenTime(Date realOpenTime) {
		this.realOpenTime = realOpenTime;
	}

    /**
     * @return  the 【opEstateId】
     */
    public String getOpEstateId() {

        return opEstateId;
    }

    /**
     * @param opEstateId the 【opEstateId】 to set
     */
    public void setOpEstateId(String opEstateId) {

        this.opEstateId = opEstateId;
    }

    /**
     * @return  the 【opEstateNm】
     */
    public String getOpEstateNm() {

        return opEstateNm;
    }

    /**
     * @param opEstateNm the 【opEstateNm】 to set
     */
    public void setOpEstateNm(String opEstateNm) {

        this.opEstateNm = opEstateNm;
    }
    private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

    public Integer getEstatePosition() {
        return estatePosition;
    }

    public void setEstatePosition(Integer estatePosition) {
        this.estatePosition = estatePosition;
    }

    public String getCountryNo() {
        return countryNo;
    }

    public void setCountryNo(String countryNo) {
        this.countryNo = countryNo;
    }
    // 是否独家
    private Integer particularFlag;
    private String particularFlagStr;
    //是否直签
    private Integer directSignFlag;
    private String directSignFlagStr;
    //是否大客户
    private Integer bigCustomerFlag;
    private String bigCustomerFlagStr;

	public Integer getParticularFlag() {
		return particularFlag;
	}

	public void setParticularFlag(Integer particularFlag) {
		this.particularFlag = particularFlag;
	}

	public String getParticularFlagStr() {
		return particularFlagStr;
	}

	public void setParticularFlagStr(String particularFlagStr) {
		this.particularFlagStr = particularFlagStr;
	}

	public Integer getDirectSignFlag() {
		return directSignFlag;
	}

	public void setDirectSignFlag(Integer directSignFlag) {
		this.directSignFlag = directSignFlag;
	}

	public String getDirectSignFlagStr() {
		return directSignFlagStr;
	}

	public void setDirectSignFlagStr(String directSignFlagStr) {
		this.directSignFlagStr = directSignFlagStr;
	}

	public Integer getBigCustomerFlag() {
		return bigCustomerFlag;
	}

	public void setBigCustomerFlag(Integer bigCustomerFlag) {
		this.bigCustomerFlag = bigCustomerFlag;
	}

	public String getBigCustomerFlagStr() {
		return bigCustomerFlagStr;
	}

	public void setBigCustomerFlagStr(String bigCustomerFlagStr) {
		this.bigCustomerFlagStr = bigCustomerFlagStr;
	}

	public Integer getBigCustomerId() {
		return bigCustomerId;
	}

	public void setBigCustomerId(Integer bigCustomerId) {
		this.bigCustomerId = bigCustomerId;
	}
	//核算主体
	private String accountProject;
	private String accountProjectNo;

	public String getAccountProject() {
		return accountProject;
	}

	public void setAccountProject(String accountProject) {
		this.accountProject = accountProject;
	}

	public String getAccountProjectNo() {
		return accountProjectNo;
	}

	public void setAccountProjectNo(String accountProjectNo) {
		this.accountProjectNo = accountProjectNo;
	}

	private Integer businessModel;

    public Integer getBusinessModel() {
        return businessModel;
    }

    public void setBusinessModel(Integer businessModel) {
        this.businessModel = businessModel;
    }

    //预计当季大定金额
    private BigDecimal subscribedThisQuarter;
    //预计第一季度大定金额
    private BigDecimal subscribedQuarter1;
    //预计第二季度大定金额
    private BigDecimal subscribedQuarter2;
    //预计第三季度大定金额
    private BigDecimal subscribedQuarter3;
    //预计第四季度大定金额
    private BigDecimal subscribedQuarter4;

    //预计当年大定金额
    private BigDecimal subscribedThisYear;
    //预计明年大定金额
    private BigDecimal subscribedNextYear;

    public BigDecimal getSubscribedThisQuarter() {
        return subscribedThisQuarter;
    }

    public void setSubscribedThisQuarter(BigDecimal subscribedThisQuarter) {
        this.subscribedThisQuarter = subscribedThisQuarter;
    }

    public BigDecimal getSubscribedQuarter1() {
        return subscribedQuarter1;
    }

    public void setSubscribedQuarter1(BigDecimal subscribedQuarter1) {
        this.subscribedQuarter1 = subscribedQuarter1;
    }

    public BigDecimal getSubscribedQuarter2() {
        return subscribedQuarter2;
    }

    public void setSubscribedQuarter2(BigDecimal subscribedQuarter2) {
        this.subscribedQuarter2 = subscribedQuarter2;
    }

    public BigDecimal getSubscribedQuarter3() {
        return subscribedQuarter3;
    }

    public void setSubscribedQuarter3(BigDecimal subscribedQuarter3) {
        this.subscribedQuarter3 = subscribedQuarter3;
    }

    public BigDecimal getSubscribedQuarter4() {
        return subscribedQuarter4;
    }

    public void setSubscribedQuarter4(BigDecimal subscribedQuarter4) {
        this.subscribedQuarter4 = subscribedQuarter4;
    }

    public BigDecimal getSubscribedThisYear() {
        return subscribedThisYear;
    }

    public void setSubscribedThisYear(BigDecimal subscribedThisYear) {
        this.subscribedThisYear = subscribedThisYear;
    }

    public BigDecimal getSubscribedNextYear() {
        return subscribedNextYear;
    }

    public void setSubscribedNextYear(BigDecimal subscribedNextYear) {
        this.subscribedNextYear = subscribedNextYear;
    }

    public String getDevCompanyId() {
        return devCompanyId;
    }

    public void setDevCompanyId(String devCompanyId) {
        this.devCompanyId = devCompanyId;
    }


    public String getBrushRaiseFlagStr() {
        return brushRaiseFlagStr;
    }

    public void setBrushRaiseFlagStr(String brushRaiseFlagStr) {
        this.brushRaiseFlagStr = brushRaiseFlagStr;
    }

    public String getTotalFieldFlagStr() {
        return totalFieldFlagStr;
    }

    public void setTotalFieldFlagStr(String totalFieldFlagStr) {
        this.totalFieldFlagStr = totalFieldFlagStr;
    }

    public Integer getIsExcuseCommision() {
        return isExcuseCommision;
    }

    public void setIsExcuseCommision(Integer isExcuseCommision) {
        this.isExcuseCommision = isExcuseCommision;
    }

    public String getIsExcuseCommisionStr() {
        return isExcuseCommisionStr;
    }

    public String getPartnerAbbreviationNm() {
        return partnerAbbreviationNm;
    }

    public void setPartnerAbbreviationNm(String partnerAbbreviationNm) {
        this.partnerAbbreviationNm = partnerAbbreviationNm;
    }

    public String getPartnerAbbreviationCode() {
        return partnerAbbreviationCode;
    }

    public void setPartnerAbbreviationCode(String partnerAbbreviationCode) {
        this.partnerAbbreviationCode = partnerAbbreviationCode;
    }

    public Integer getDeveloperBrandId() {
        return developerBrandId;
    }

    public void setDeveloperBrandId(Integer developerBrandId) {
        this.developerBrandId = developerBrandId;
    }

    public String getDeveloperBrandName() {
        return developerBrandName;
    }

    public void setDeveloperBrandName(String developerBrandName) {
        this.developerBrandName = developerBrandName;
    }

    public BigDecimal getVendibilityAmount() {
        return vendibilityAmount;
    }

    public void setVendibilityAmount(BigDecimal vendibilityAmount) {
        this.vendibilityAmount = vendibilityAmount;
    }

    public BigDecimal getMonthRoughAmount() {
        return monthRoughAmount;
    }

    public void setMonthRoughAmount(BigDecimal monthRoughAmount) {
        this.monthRoughAmount = monthRoughAmount;
    }

    public String getCooperationStatus() {
        return cooperationStatus;
    }

    public void setCooperationStatus(String cooperationStatus) {
        this.cooperationStatus = cooperationStatus;
    }

    public String getProjectInfoDesc() {
        return projectInfoDesc;
    }

    public void setProjectInfoDesc(String projectInfoDesc) {
        this.projectInfoDesc = projectInfoDesc;
    }

    public String getRequiredForSupport() {
        return requiredForSupport;
    }

    public void setRequiredForSupport(String requiredForSupport) {
        this.requiredForSupport = requiredForSupport;
    }
}


