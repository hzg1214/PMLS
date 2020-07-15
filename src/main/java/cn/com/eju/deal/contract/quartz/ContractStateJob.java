package cn.com.eju.deal.contract.quartz;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.eju.deal.base.fesb.FesbService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.common.util.FesbMethodConstants;
import cn.com.eju.deal.contract.service.ContractService;
import cn.com.eju.deal.contract.service.ExtOmsService;
import cn.com.eju.deal.contract.service.OaService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.contract.ContractInfoDto;
import cn.com.eju.deal.dto.contract.ContractSearchResultDto;
import cn.com.eju.deal.dto.contract.DepositDto;
import cn.com.eju.deal.dto.store.DecorationDto;
import cn.com.eju.deal.store.service.StoreDepositService;

/**   
* 每天凌晨拉取合同状态
* @author (li_xiaodong)
* @date 2016年5月2日 下午5:31:59
*/
public class ContractStateJob
{
    @Resource(name = "contractService")
    private ContractService contractService;
    
    @Resource(name = "oaService")
    private OaService oaService;
    
    @Resource(name = "fesbService")
    private FesbService fesbService;
    
    @Resource(name = "extOmsService")
    private ExtOmsService extOmsService;
    
    @Resource(name="storeDepositService")
    private StoreDepositService storeDepositService;
    
    //标示线程执行标示
    private static Boolean isExcute = false;
    
    private static long oldTime;
    
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    /** 
    * 拉取合同主方法
    */
    public void handleJob()
    {
        
//        logger.error("Contract", "ContractStateJob", "handleJob", null, 0, null, "定时任务测试执行,当前线程Id" + Thread.currentThread().getId() + ";step0", null);
        
        //180秒后解锁
        long dif = (System.currentTimeMillis() - oldTime) / 1000;
        if (dif > 180)
        {
            isExcute = false;
        }
        
        if (isExcute)
        {
            return;
        }
        else
        {
            //拉取合同
            handle();
        }
        
    }
    
    /** 
    * 拉取合同
    */
    private synchronized void handle()
    {
        isExcute = true;
        
        oldTime = System.currentTimeMillis(); //这是获取毫秒数
        
//        logger.error("Contract", "ContractStateJob", "handleJob", null, 0, null, "定时任务测试执行,当前线程Id" + Thread.currentThread().getId() + ";step1", null);
        
        ResultData<?> resultData = null;
        ResultData<?> reback = null;
        //1、获取“审核中” 状态的list
        List<String> flowSheetList = getFlowIdStateList();
        
        //2、OA审核结果集
        Map<String, Object> reqMap = getOaAuditMap(flowSheetList);
        
        //审核通过List
        @SuppressWarnings("unchecked")
        List<String> passList = (List<String>)reqMap.get("pass");
        
        //作废List
        @SuppressWarnings("unchecked")
        List<String> noPassList = (List<String>)reqMap.get("noPass");
        
        //3、更新合同状态
        try
        {
            if (!passList.isEmpty() || !noPassList.isEmpty())
            {
                
                logger.error("Contract", "ContractStateJob", "handleJob", reqMap.toString(), 0, null, "定时任务测试执行,当前线程Id"
                    + Thread.currentThread().getId() + ";step2", null);
                
                //1、更新到合同表合同状态
                resultData = contractService.updateStatusByParam(reqMap);
                if (ReturnCode.SUCCESS.equals(resultData.getReturnCode()))
                {
                    // 定时创建门店装修数据，返回门店装修表数据用于调用OMS
                    resultData = contractService.insertNewDecorationRecord(reqMap);
                    
                    //2、将返回的数据更新到OMS门店装修表
                    if (ReturnCode.SUCCESS.equals(resultData.getReturnCode()))
                    {
                        List<Map<String, Object>> listmap = (List<Map<String, Object>>)resultData.getReturnData();
                        
                        //更新到OMS，数据组装
                        List<DecorationDto> postList = new ArrayList<DecorationDto>();
                        for (int i = 0; i < listmap.size(); i++)
                        {
                            Map<String, Object> mop = listmap.get(i);
                            DecorationDto decorationDto = new DecorationDto();
                            decorationDto.setDecorateNo((String)mop.get("decorationNo"));
                            decorationDto.setStoreNo((String)mop.get("storeNo"));
                            decorationDto.setContractNo((String)mop.get("contractNo"));
                            decorationDto.setDecorateStatus((int)mop.get("decorationStatus"));
                            decorationDto.setStoreName((String)mop.get("storeName"));
                            decorationDto.setStoreAddress((String)mop.get("storeAddress"));
                            decorationDto.setContractType((String)mop.get("contractTypeName"));
                            decorationDto.setCompanyName((String)mop.get("companyName"));
                            decorationDto.setCityNo((String)mop.get("cityNo"));
                            decorationDto.setAgreementNo((String)mop.get("agreementNo"));
                            decorationDto.setDateLifeStartStr((String)mop.get("dateLifeStartStr"));
                            decorationDto.setDateLifeEndStr((String)mop.get("dateLifeEndStr"));
                            decorationDto.setOafilpagency((String)mop.get("oafilpagency"));
                            postList.add(decorationDto);
                        }
                        if (!postList.isEmpty())
                        {
                            //返回数据直接调用OMS定时任务接口更新
                            reback = fesbService.httpPost(postList, FesbMethodConstants.FESB_METHOD_CODE_DEPOSIT_UPDATE);
                            if (!reback.getReturnCode().equals(ReturnCode.SUCCESS))
                            {
                                logger.error("contract",
                                    "contractStateJob",
                                    "handleJob",
                                    "list = " + postList.toString(),
                                    null,
                                    null,
                                    "定时批量更新合同状态,OMS返回值异常",
                                    null);
                            }
                        }
                        else
                        {
                            logger.error("contract",
                                "contractStateJob",
                                "handleJob",
                                "list = " + postList.toString(),
                                null,
                                null,
                                "定时批量更新合同状态,返回数据异常",
                                null);
                        }
                    }
                    
                }
                
            }
            
        }
        catch (Exception e)
        {
            logger.error("contract", "contractStateJob", "handleJob", null, null, null, "定时批量更新合同状态异常", e);
        }
        
//        logger.error("Contract", "ContractStateJob", "handleJob", reqMap.toString(), 0, null, "定时任务测试执行,当前线程Id" + Thread.currentThread().getId()
//            + ";step3", null);
        
    }
    
    /** 
    * 组装 审核状态
    * @param flowSheetList
    * @param flowIdStatePass
    */
    private Map<String, Object> getOaAuditMap(List<String> flowSheetList)
    {
        Map<String, Object> flowIdState = new HashMap<String, Object>();
        
        //审核通过List
        List<String> passList = new ArrayList<String>();
        
        //作废List
        List<String> noPassList = new ArrayList<String>();
        
        if (null != flowSheetList && !flowSheetList.isEmpty())
        {
            
            for (String flowId : flowSheetList)
            {
                
                Integer reback = -1;
                try
                {
                    reback = oaService.getOaState(flowId);
                }
                catch (Exception e)
                {
                    // 记录日志
                    logger.error("", "contract-quartz", "getOaAuditMap", "", -1, "", "获取OA审核状态异常!", e);
                }
                //审核通过 （ 0正常结束）
                if (0 == reback)
                {
                    passList.add(flowId);
                    Boolean isSuccess = false;
                    ContractInfoDto contractInfoDto = new ContractInfoDto();
                    // 设置审核结果
                    contractInfoDto.setAuditRst(reback);
                    ContractDto contract = new ContractDto();
                    // 更新该合同为"审核通过"
                    contract.setContractStatus(DictionaryConstants.CONTRACT_STATUS_AUDIT_PASS);
                    contract.setFlowId(String.valueOf(flowId));
                    // 审核通过时间
                    contract.setPerformDate(new Date());
                    contract.setDateUpdate(new Date());
                    contractInfoDto.setContract(contract);
                    try
                    {
                        contractService.updateContractStatusByFlowId(contractInfoDto);
                        isSuccess = true;
                    }
                    catch (Exception e1)
                    {
                        logger.error("Contacts", "OaController", "getOaState", "", UserInfoHolder.getUserId(), "", "Oa更新合同状态异常", e1);
                    }
                    
                    if (isSuccess)
                    {
                        /**审核通过 则向oms写 保证金信息**/
                        
                        // 根据flowId查询合同信息
                        ResultData<?> returnDate;
                        Map<?, ?> mapTemp = null;
                        ContractDto contractDto = null;
                        try {
                            returnDate = contractService.getDepoistNozeroCtrctByFlowId(flowId);
                            mapTemp = (Map<?, ?>)returnDate.getReturnData();
                            contractDto = MapToEntityConvertUtil.convert(mapTemp, ContractDto.class, "");
                        } catch (Exception e1) {
                            logger.error("Contacts", "ContractStateJob", "getOaAuditMap", "", UserInfoHolder.getUserId(), "", "根据flowId查询合同异常", e1);
                        }
                        String acCityNo = contractDto.getAcCityNo();
                        boolean depositOpenFlag = true;
                        if(acCityNo == null) {
                            logger.error("contract", "ContractStateJob", "getOaAuditMap", "", UserInfoHolder.getUserId(), "", "根据flowId："+flowId+"查询合同acCityNo为空，默认新版保证金已开放", new NullPointerException());
                        }else {
                            try {
                                ResultData<String> data = storeDepositService.openFlag(acCityNo);
                                if(data != null && "200".equals(data.getReturnCode())) {
                                    if(data.getReturnData() != null && data.getReturnData().equals("0")) {
                                        //未开通新版保证金流程
                                        depositOpenFlag = false;
                                    }
                                }
                            } catch (Exception e) {
                                logger.error("contract", "ContractStateJob", "getOaAuditMap", "", UserInfoHolder.getUserId(), "", "根据合同acCityNo查询开关异常，默认新版保证金已开放", e);
                            }
                        }
                        
                        if(!depositOpenFlag) {
                            try
                            {
                                if (null != mapTemp)
                                {
                                    // 保证金总额
                                    Long itemAmountStr = new Long(mapTemp.get("totleDepositFee").toString());
                                    
                                    if(contractDto.getContractType()!=null||(contractDto.getContractType().intValue()==10302||contractDto.getContractType().intValue()==10304)){
                                    // 构造传递的保证金信息
                                    DepositDto deposit = new DepositDto();
                                    deposit.setContractNo(contractDto.getContractNo());// 合同编号
                                    deposit.setContractType(contractDto.getContractTypeName());// 合同类型(A版、B版、A转B版、B转A版)
                                    deposit.setContractState(contractDto.getContractStatusName());// 合同状态(草签、审核通过、审核驳回、作废)
                                    deposit.setCompanyName(contractDto.getPartyB());// 公司名称
                                    deposit.setItemAmount(itemAmountStr);// 保证金总额
                                    // 协议书编号
                                    deposit.setAgreementNo(contractDto.getAgreementNo());
                                    // 调用OMS接口
                                    ResultData<?> rebackResult = fesbService.httpPost(deposit, FesbMethodConstants.FESB_METHOD_CODE_STORE_QUERY);
                                    Integer code = Integer.valueOf(rebackResult.getReturnCode());
                                    if (code != 200)
                                    {
                                        logger.error("contract",
                                            "contractStateJob",
                                            "getOaState",
                                            "deposit = " + JsonUtil.parseToJson(deposit),
                                            null,
                                            null,
                                            "调用OMS接口新增保证金失败!",
                                            null);
                                    }
                                    }
                                }
                            }
                            catch (Exception e)
                            {
                                logger.error("Contract", "ContractStateJob", "getOaAuditMap", "", UserInfoHolder.getUserId(), "", "根据flowId查询合同信息失败!", e);
                            }
                            //保证金到账
                            try
                            {
                                extOmsService.createPerformNodeRecordByFlowId(flowId);
                            }
                            catch (Exception e)
                            {
                                logger.error("contract",
                                    "ContractStateJob",
                                    "getOaAuditMap",
                                    "",
                                    UserInfoHolder.getUserId(),
                                    "",
                                    "method createPerformNodeRecordByFlowId fail!",
                                    e);
                            }
                        }
                    }
                }
                //作废 （  非正常结束：5 撤销、15终止） 
                else if (5 == reback || 15 == reback)
                {
                    noPassList.add(flowId);
                }
            }
            
            flowIdState.put("pass", passList);
            flowIdState.put("noPass", noPassList);
            
        }
        return flowIdState;
    }
    
    /** 
    * 组装flowId集合List
    * @param flowSheetList
    */
    private List<String> getFlowIdStateList()
    {
        //返回组装List
        List<String> flowSheetList = new ArrayList<String>();
        
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("contractStatus", DictionaryConstants.CONTRACT_STATUS_AUDITING);
        
        //获取页面显示数据
        ResultData<?> reback = null;
        try
        {
            reback = contractService.index(reqMap);
        }
        catch (Exception e)
        {
        }
        
        //查询到合同数据
        List<?> contentlist = (List<?>)reback.getReturnData();
        if (null != contentlist && !contentlist.isEmpty())
        {
            
            String flowId = null;
            
            ContractSearchResultDto contractSearchResultDto;
            
            for (Object rebac : contentlist)
            {
                contractSearchResultDto = MapToEntityConvertUtil.convert((Map<?, ?>)rebac, ContractSearchResultDto.class, "");
                
                flowId = contractSearchResultDto.getFlowId();
                
                flowSheetList.add(flowId);
            }
        }
        
        return flowSheetList;
    }
    
}
