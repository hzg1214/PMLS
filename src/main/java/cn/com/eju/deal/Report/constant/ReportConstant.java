package cn.com.eju.deal.Report.constant;

import cn.com.eju.deal.common.util.DateUtils;

import java.util.Date;

/**
 * Created by eju on 2018/1/22.
 */
public interface ReportConstant {


    /**
     * 拓展明细
     */
    String EXPANDDETAIL_CODE = "expandDetail";
    String EXPANDDETAIL_NAME = "拓展明细.xlsx";

    /**
     * 业绩周汇总
     */
    String PERFORMANCESUM_CODE = "performanceSum";
    String PERFORMANCESUM_WEEK_NAME = "业绩周汇总.xlsx";

    /**
     * 业绩周汇总
     */
    String PERFORMANCESUM_MONTH_NAME = "业绩月汇总.xlsx";

    /**
     * 业绩周汇总
     */
    String PERFORMANCESUM_YEAR_NAME = "业绩年汇总.xlsx";
    /**
     * 业绩周汇总
     */
    String PERFORMANCESUM_CURRENT_NAME = "业绩当期汇总.xlsx";

    /**
     * 已签门店明细
     */
    String SIGNDETAIL_CODE = "signDetail";
    String SIGNDETAIL_NAME = "已签门店明细.xlsx";

    /**
     * 未签门店明细
     */
    String NOSIGNDETAIL_CODE = "noSignDetail";
    String NOSIGNDETAIL_NAME = "未签门店明细.xlsx";


    /**
     * 跟进明细
     */
    String FOLLOWDETAIL_CODE = "followDetail";
    String FOLLOWDETAIL_NAME = "跟进明细.xlsx";

    /**
     * 联动明细
     */
    String LINKDETAIL_CODE = "linkDetail";
    String LINKDETAIL_NAME = "联动明细.xlsx";
    String LINKDETAIL_NAME_CSV = "linkdetail.csv";
    
    /**
     * 联动资金成本(垫佣资金)
     */
    String LINKZJCBDETAIL_CODE = "linkZjcbDetail";
    String LINKZJCBDETAIL_NAME = "联动资金成本(垫佣资金).xlsx";
    
    /**
     * 联动资金成本(保证金、诚意金)
     */
    String LINKMARGINDETAIL_CODE = "linkMarginDetail";
    String LINKMARGINDETAIL_NAME = "联动资金成本(保证金&诚意金).xlsx";

    /**
     * 联动项目明细
     */
    String LINKPROJECTDETAIL_CODE = "linkProjectDetail";
    String LINKPROJECTDETAIL_NAME = "联动项目明细.xlsx";
    
    /**
     * 门店信息明细
     */
    String STOREINFORMATIONDETAIL_CODE = "storeInformationDetailDetail";
    String STOREINFORMATIONDETAIL_NAME = "门店信息明细.xlsx";

    /**
     * 门店结构
     */
    String STORESTRUCTURE_CODE = "storeStructure";
    String STORESTRUCTURE_NAME = "门店结构.xlsx";

    /**
     * 门店结构
     */
    String ACHIEVECHANGE_CODE = "achieveChange";
    String ACHIEVECHANGE_NAME = "业绩变更.xlsx";

    /**
     * 业绩查询
     */
    String EXCELFORPERFORMANCEQUERY_CODE = "performanceQuery";
    String EXCELFORPERFORMANCEQUERY_NAME = "业绩查询.xlsx";

    /**
     * 联动项目跟踪
     */
    String LINKPROJECTTRACE_CODE = "linkProjecteTrace";
    String LINKPROJECTTRACE_NAME = "联动项目跟踪.xlsx";

    /**
     * 联动项目跟踪
     */
    String LINKPROJECTPARTATRACE_CODE = "linkProjectePartATrace";
    String LINKPROJECTPARTATRACE_NAME = "联动项目甲方跟踪.xlsx";

    /**
     * 保证金明细StoreDepositDeatil
     */
    String STOREDEPOSITDEATIL_CODE = "storeDepositDeatil";
    String STOREDEPOSITDEATIL_NAME = "保证金明细.xlsx";
    /**
     * 保证金流水StoreDepositDeatil
     */
    String STOREDEPOSITSERIAL_CODE = "storeDepositSerial";
    String STOREDEPOSITSERIAL_NAME = "保证金流水.xlsx";
    /**
     * 保证金流水StoreDepositDeatil
     */
    String LNKACHIEVEMENTSUM_CODE = "lnkAchievementSum";
    String LNKACHIEVEMENTSUM_NAME = "联动业绩汇总.xlsx";

    /**
     * 维护门店StorePreserve
     */
    String STOREPRESERVE_CODE = "storePreserve";
    String STOREPRESERVE_NAME = "维护门店明细.xlsx";

    /**
     * 维护门店汇总 storePreserveSummary
     */
    String STOREPRESERVESUMMARY_CODE = "storePreserveSummary";
    String STOREPRESERVESUMMARY_NAME = "维护门店汇总.xlsx";
    
    /**
     * 公司信息明细表companyInfoDetail
     */
    String COMPANYINFODETAIL_CODE = "companyInfoDetail";
    String COMPANYINFODETAIL_NAME = "公司信息明细.xlsx";

    /**
     * 公盘会员明细
     */
    String MEMBERSHIP_CODE = "membershipDetail";
    String MEMBERSHIP_NAME = "公盘会员明细.xlsx";
    /**
     * 工单管理
     */
    String KEFUORDER_CODE = "kefuOrder";
    String KEFUORDER_NAME = "客服工单.xlsx";

    /**
     * 批量成销
     */
    String BATCHSALE_CODE = "batchSale";
    String BATCHSALE_NAME = "批量成销模板.xlsx";
    
    /**
     * 批量退房
     */
    String BATCHREBACK_CODE = "batchReback";
    String BATCHREBACK_NAME = "批量退房模板.xlsx";
    
    /**
     * 问卷调查
     */
    String KEFUWJ_CODE = "kefuWj";
    String KEFUWJ_NAME = "问卷模板"+DateUtils.formatDate(new Date(),"yyyyMMdd")+".xlsx";

    /**
     * 批量成销
     */
    String KEFUWJANALYSE_CODE = "keFuWjAnalyse";
    String KEFUWJANALYSE_NAME = "满意度调查统计表.xlsx";

    /**
     * 联动转化率分析
     */
    String LINKCONVERSIONRATE_CODE="linkConversion";
    String LINKCONVERSIONRATE_NAME="联动转化率分析报表.xlsx";

    /**
     * 公盘会员明细
     */
    String STORECONTRIBUTION_CODE = "storeContribution";
    String STORECONTRIBUTION_NAME = "门店贡献分析表.xlsx";

    String KEFUWJEVALUATION_CODE = "keFuWjEvaluation";
    String KEFUWJEVALUATION_NAME = "门店测评数据统计表.xlsx";


    /**
     * 联动明细
     */
    String REPORT_ORDER_DETAIL_CODE = "reportOrderDetail";
    String REPORT_ORDER_DETAIL_NAME = "订单明细.xlsx";
    String REPORT_ORDER_DETAIL_NAME_CSV = "reportOrderDetail.csv";

    /**
     * 开发项目明细
     */
    String PROJECTDETAIL_CODE="projectDetail";
    String PROJECTDETAIL_NAME="开发项目明细表.xlsx";
    
    /**
     * 经纪公司明细
     */
    String COMPANYDETAIL_CODE = "companyDetail";
    String COMPANYDETAIL_NAME = "经纪公司明细.xlsx";
    
    /**
     * 门店明细
     */
    String STOREDETAIL_CODE = "storeDetail";
    String STOREDETAIL_NAME = "门店明细.xlsx";
}
