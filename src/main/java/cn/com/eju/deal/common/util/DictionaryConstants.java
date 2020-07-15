package cn.com.eju.deal.common.util;

/**
 * 数据字典对应的常量
 *
 * @author (li_xiaodong)
 * @date 2016年4月7日 下午6:43:04
 */
public interface DictionaryConstants {
    /**
     * 客户状态
     */
    int DIC_CODE_COMPANY_STATUS = 125;

    /**
     * 客户状态--已签约
     */
    int DIC_CODE_COMPANY_STATUS_Y = 12501;

    /**
     * 客户状态--未签约
     */
    int DIC_CODE_COMPANY_STATUS_N = 12502;

    /**
     * 客户来源
     */
    int DIC_CODE_COMPANY_SOURCE = 105;

    /**
     * 客户来源--400电话
     */
    int DIC_CODE_COMPANY_SOURCE_TEL = 10501;

    /**
     * 客户来源--推荐Recommend
     */
    int DIC_CODE_COMPANY_SOURCE_RECOMMEND = 10502;

    /**
     * 客户来源--扫街streets
     */
    int DIC_CODE_COMPANY_SOURCE_STREETS = 10503;

    /**
     * 客户来源--OA
     */
    int DIC_CODE_COMPANY_SOURCE_OA = 10504;

    /**
     * 合同类型
     */
    int CONTRACT_TYPE = 103;

    /**
     * 合同类型--A版本
     */
    int CONTRACT_TYPE_A = 10301;

    /**
     * 合同类型--B版本
     */
    int CONTRACT_TYPE_B = 10302;

    /**
     * 合同类型--C版本
     */
    int CONTRACT_TYPE_C = 10303;

    /**
     * 合同类型--A转B版本
     */
    int CONTRACT_TYPE_A_2_B = 10304;

    /**
     * 合同类型--B转A版本
     */
    int CONTRACT_TYPE_B_2_A = 10305;

    /**
     * OA审批流程类别
     */
    int OA_APPROVE_TYPE = 171;

    /**
     * OA审批流程类别 -- 标准
     */
    int OA_APPROVE_TYPE_STANDARD = 17101;

    /**
     * OA审批流程类别 -- 特殊
     */
    int OA_APPROVE_TYPE_SPECIAL = 17102;
    /**
     * 授牌类型
     */
    int SHOUPAI_TYPE = 246;
    /**
     * 授牌类型-门店
     */
    int SHOUPAI_TYPE_MD = 24601;
    /**
     * 授牌类型-社区
     */
    int SHOUPAI_TYPE_SQ = 24602;
    /**
     * 授牌类型-渠道
     */
    int SHOUPAI_TYPE_QD = 24603;

    /**
     * 合作模式
     */
    int COOP_TYPE = 109;

    /**
     * 合作模式--A
     */
    int COOP_TYPE_A = 10901;

    /**
     * 合作模式--B
     */
    int COOP_TYPE_B = 10902;

    /**
     * 合作模式--A转B
     */
    int COOP_TYPE_A_2_B = 10903;

    //Add By tong 2017/04/07 Start

    /**
     * 合同协议类型
     */
    int AGREEMENT_TYPE = 110;

    /**
     * 合同协议类型 _简易A版
     */
    int AGREEMENT_TYPE_A = 11001;

    /**
     * 合同协议类型 _AB版
     */
    int AGREEMENT_TYPE_AB = 11002;

    /**
     * 合同协议类型 _续签协议
     */
    int AGREEMENT_TYPE_XQ = 11003;

    /**
     * 合同协议类型 _A版
     */
    int AGREEMENT_TYPE_NEWA = 11004;

    /**
     * 合同协议类型 _B版
     */
    int AGREEMENT_TYPE_NEWB = 11005;

    /**
     * 门店待续签
     */
    int RENEWFLAG_TYPE = 183;

    /**
     * 门店待续签_正常
     */
    int RENEWFLAG_TYPE_OK = 18301;

    /**
     * 门店待续签_待续签
     */
    int RENEWFLAG_TYPE_DX = 18302;

    /**
     * 合同有效标识
     */
    int VALID_TYPE = 184;

    /**
     * 合同有效标识 _待生效
     */
    int VALID_TYPE_DSX = 18401;

    /**
     * 合同有效标识 _生效
     */
    int VALID_TYPE_SX = 18402;

    /**
     * 合同有效标识 _过期
     */
    int VALID_TYPE_GQ = 18403;

    /**
     * 门店是否无需续签
     */
    int NEEDEDRENEW_TYPE = 185;

    /**
     * 门店是否无需续签 _需要续签
     */
    int NEEDEDRENEW_TYPE_YXQ = 18501;

    /**
     * 门店是否无需续签 _不要续签
     */
    int NEEDEDRENEW_TYPE_NXQ = 18502;

    /**
     * 合同类别(新签，续签)
     */
    int OriginalContractdistinction_TYPE = 186;

    /**
     * 合同类别(新签，续签)_新签
     */
    int OriginalContractdistinction_TYPE_N = 18601;

    /**
     * 合同类别(新签，续签)_续签
     */
    int OriginalContractdistinction_TYPE_R = 18602;

    //Add By tong 2017/04/07 End


    /**
     * 性别类型
     */
    int SEX_TYPE = 100;

    // 合同状态-待提交审核--> 草签
    int CONTRACT_STATUS_PENDING = 10401;

    // 合同状态-审核中
    int CONTRACT_STATUS_AUDITING = 10402;

    // 合同状态-审核通过
    int CONTRACT_STATUS_AUDIT_PASS = 10403;

    // 合同状态-审核未通过
    int CONTRACT_STATUS_AUDIT_NO_PASS = 10404;

    // 合同状态-作废
    int CONTRACT_STATUS_CANCEL = 10405;

    // 合同状态-终止
    int CONTRACT_STATUS_ENDED = 10406;

    //新房联动中的字段

    /**
     * 销售状态
     */
    String SALES_STATUS = String.valueOf(144);

    /**
     * 结佣方式
     */
    String PAY_KBN = String.valueOf(146);

    /**
     * 认证类型
     */
    String AUTHENTICATION_KBN = String.valueOf(147);

    /**
     * 佣金方式
     */
    String COMMISSION_KBN = String.valueOf(148);

    /**
     * 销售方式
     */
    String SALE_KBN = String.valueOf(149);

    /**
     * 报备方式
     */
    String REPORT_KBN = String.valueOf(150);

    /**
     * 朝向
     */
    String DIRECTION_KBN = String.valueOf(151);

    /**
     * 产权年限
     */
    String OWNYEAR_KBN = String.valueOf(153);

    /**
     * 装修情况
     */
    String DECORATION_KBN = String.valueOf(154);

    /**
     * 建筑类型
     */
    String TYPE_KBN = String.valueOf(155);

    /**
     * 供暖方式
     */
    String HEAT_KBN = String.valueOf(156);

    /**
     * 水电燃气
     */
    String HYDROPOWERGAS_KBN = String.valueOf(157);

    /**
     * 合作方
     */
    String PARTNER = String.valueOf(128);

    /**
     * 物业类型
     */
    String MGT_KBN = String.valueOf(134);

    //楼盘审核状态
    /**
     * 楼盘审核状态-未审核
     */
    int ESTATE_AUDIT_NEED = 12901;
    /**
     * 楼盘审核状态-不通过
     */
    int ESTATE_AUDIT_NO_PASS = 12902;
    /**
     * 楼盘审核状态-通过
     */
    int ESTATE_AUDIT_PASS = 12903;
    /**
     * 楼盘审核状态-未提交
     */
    int ESTATE_AUDIT_NO_PENDING = 12904;
    //楼盘发布状态
    /**
     * 楼盘发布状态-已发布
     */
    int ESTATE_PUBLISH_YES = 13001;
    /**
     * 楼盘发布状态-未发布
     */
    int ESTATE_PUBLISH_NO = 13002;

    //楼盘图片
    /**
     * 楼盘效果图
     */
    int ESTATE_DESIGN_IMG = 15901;

    /**
     * 楼盘样板间
     */
    int ESTATE_TEMPLATE_IMG = 15902;

    /**
     * 楼盘地理位置
     */
    int ESTATE_MAP_IMG = 15903;

    /**
     * 楼盘区域规划
     */
    int ESTATE_DISTRICT_IMG = 15904;

    /**
     * 楼盘实景图
     */
    int ESTATE_REAL_IMG = 15905;

    /**
     * 楼盘户型图
     */
    int HOUSE_TYPE_IMG = 15906;

    /**
     * 楼盘样板间
     */
    int HOUSE_TEMPLATE_IMG = 15907;

    /**
     * 审核区域
     */
    int AUDIT_DISTRICT = 160;

    /**
     * 门店装修状况
     */
    int DECORATION_STATE = 163;


// Add By GuoPengFei 2017/05/26 合规性 start

    /**
     * 变更类型
     */
    int Contract_ChangeType = 170;

    /**
     * 终止
     */
    int Contract_ChangeType_END = 17001;

    /**
     * 乙转甲
     */
    int Contract_ChangeType_B2A = 17002;


    /**
     * 门店资质等级
     */
    int Store_QualityGrade = 199;


    /**
     * 甲类门店
     */
    int Store_QualityGrade_A = 19901;

    /**
     * 乙类门店
     */
    int Store_QualityGrade_B = 19902;


    /**
     * 乙类门店等级
     */
    int Store_QualityGrade_AType = 200;

    /**
     * 门店（乙1）
     */
    int Store_QualityGrade_AType_1 = 20001;


    /**
     * 门店（乙2）
     */
    int Store_QualityGrade_AType_2 = 20002;

    /**
     * 门店（乙3）
     */
    int Store_QualityGrade_AType_3 = 20003;

    /**
     * 门店（乙4）
     */
    int Store_QualityGrade_AType_4 = 20004;

    /**
     * 门店乙类转甲类 类型
     */
    int ChangeType_B2A_Type = 201;

    /**
     * 公司经营范围变更
     */
    int ChangeType_B2A_Type_Company = 20101;

    /**
     * 公司注册地址变更
     */
    int ChangeType_B2A_Type_CompanyAdress = 20102;

    /**
     * 门店地址变更
     */
    int ChangeType_B2A_Type_StoreAdress = 20103;

    /**
     * 签约主体变更
     */
    int ChangeType_B2A_Type_Main = 20104;

    /**
     * 是否乙类转甲类
     */
    int Contract_TypeB2A = 202;

    /**
     * 是乙类转甲类
     */
    int Contract_TypeB2A_YES = 20201;

    /**
     * 不是乙类转甲类
     */
    int Contract_TypeB2A_NO = 20202;
//Add By GuoPengFei 2017/05/26 合规性 end

    //Add By QJP 2017/08/203 新房联动合作模式  start
    String COOPERATION_TYPE = String.valueOf(204);
//Add By QJP 2017/08/203 新房联动合作模式  end

    String BUSINESS_MODEL_TYPE = String.valueOf(255);

//Add By WangKanLin 2017/08/03 项目状态 start

    /**
     * 项目状态
     */
    int Project_Status = 203;

    /**
     * 项目状态：跟单
     */
    int Project_Status_Start = 20301;

    /**
     * 项目状态：签约
     */
    int Project_Status_Sign = 20302;

    /**
     * 项目状态：结案
     */
    int Project_Status_End = 20303;

    /**
     * 项目状态：取消跟单
     */
    int Project_Status_Start_Cancel = 20304;

    /**
     * 草稿
     */
    int Project_Status_Draft = 20305;

    //Add By WangKanLin 2017/08/03 项目状态 end

//Add By WangKanLin 2017/08/07 合作模式 start

    /**
     * 合作模式
     */
    int Cooperation_Mode = 204;

    /**
     * 合作模式：整合
     */
    int Cooperation_Mode_Comformity = 20402;

    /**
     * 项目变更：分销
     */
    int Cooperation_Mode_Distributional = 20401;

    //Add By WangKanLin 2017/08/07 合作模式 end

//Add By WangKanLin 2017/08/07 项目变更类型 start

    /**
     * 项目变更
     */
    int Project_Change = 205;

    /**
     * 项目变更：合作模式变更
     */
    int Project_Change_Cooperation_Mode = 20501;

    /**
     * 项目变更：项目状态变更
     */
    int Project_Change_Status = 20502;

    /**
     * 项目变更：上架
     */
    int Project_Change_Open = 20503;

    /**
     * 项目变更：下架
     */
    int Project_Change_Close = 20504;

    /**
     * 项目变更：撤回
     */
    int Project_Change_Backoff = 20505;

    /**
     * 项目变更：撤回
     */
    int Project_Change_Audit = 20506;

    //Add By WangKanLin 2017/08/07 项目变更类型 end
    /**
     * 项目变更：销售状态变更
     */
    int Project_Change_SaleStatus = 20510;
    /**
     * 项目变更：项目发布城市变更
     */
    int Project_Change_EstateReleaseCity = 20511;

    /**
     * 楼盘名称变更
     */
    int Project_Change_EstateNm = 20512;
    /**
     * 项目地址变更
     */
    int Project_Change_Address = 20513;
    /**
     * 合作方变更
     */
    int Project_Change_PartnerNm = 20514;
    /**
     * 开发商变更
     */
    int Project_Change_DevCompany = 20515;

    /**
     * 项目合作意向状态变更
     */
    int Project_Change_CooperationStatus = 20516;


    /**
     * 收入结算条件
     */
    String COMMISSIONCD_KBN = String.valueOf(219);
    /**
     * 框架协议类型
     */
    String FRAMECONTRACT_TYPE = String.valueOf(221);
    /**
     * 是否续签
     */
    String FRAMECONTRACT_REAGREEFLAG_TYPE = String.valueOf(222);
    /**
     * 经营场所类型
     */
    String STOREMANAGEMENTPLACE_TYPE = String.valueOf(224);
    /**
     * 是否大客户
     */
    String ESTATE_BIGCUSTOMER_TYPE = String.valueOf(226);
    /**
     * 是否独家
     */
    String ESTATE_PARTICULAR_TYPE = String.valueOf(227);
    /**
     * 是否直签
     */
    String ESTATE_DIRECTSIGN_TYPE = String.valueOf(228);
    /**
     * 门店规模
     */
    String STORESIZESCALE_TYPE = String.valueOf(229);
    /**
     * 终止类型
     */
    String GPCONTRACTSTOP_TYPE = String.valueOf(231);
    /**
     * 合同类型--授牌
     */
    int CONTRACT_TYPE_AWARDING = 10307;
    /**
     * 合同协议类型 _年度合作协议书
     */
    int AGREEMENT_TYPE_YEAR = 11006;
    /**
     * 咨询产品分类
     */
    String KEFU_ORDERPRODUCT_TYPE = String.valueOf(239);
    /**
     * 一级分类
     */
    String KEFU_CATEGORYONE_TYPE = String.valueOf(240);
    /**
     * 诚意金&保证金-类型
     */
    String LINK_MARGIN_TYPE = String.valueOf(267);
    /**
     *工单优先级
     */
    String KEFU_ORDERDESC_TYPE = String.valueOf(244);
    
    //问卷-业务类型
    String Wj_wjtmflType_pp = "25101";//	品牌服务	
    
    String Wj_wjtmflType_px = "25102";//	培训服务	
    
    String Wj_wjtmflType_zp = "25103";//	招聘服务	
    
    String Wj_wjtmflType_xt = "25104";//	系统服务	
    
    String Wj_wjtmflType_jy = "25105";//	交易服务	
    
    String Wj_wjtmflType_gp = "25106";//	公盘业务	
    
    String Wj_wjtmflType_ld = "25107";//	联动业务	
    
    String Wj_wjtmflType_qt = "25108";//	其他	
    
    //问卷-题型
    String Wj_wjtmType_dx = "25201";//	单选题	
    
    String Wj_wjtmType_dxs = "25202";//	多选题	
    
    String Wj_wjtmType_wd = "25203";//	问答题

    String WJDCSTATUS_CJ = "25001";//	已创建

    String WJDCSTATUS_WC = "25002";//	已完成

    /**
     * 刷筹
     */
    String ESTATE_BRUSHRAISE_TYPE = String.valueOf(277);

    /**
     * 共场
     */
    String ESTATE_TOTALFIELD_TYPE = String.valueOf(278);

    /**
     * 是否借佣
     */
    String ESTATE_EXCUSECOMMISION_TYPE = String.valueOf(279);

    /**
     * 合作协议——合同版本
     */
    String COOPERATION_HT_EDITION = String.valueOf(283);

    String COOPERATION_SIGN_TYPE = String.valueOf(292);

}
