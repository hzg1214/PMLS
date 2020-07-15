package cn.com.eju.deal.contract.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.fesb.FesbService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.service.CommonService;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.common.util.FesbMethodConstants;
import cn.com.eju.deal.contract.service.ContractService;
import cn.com.eju.deal.contract.service.ExtOmsService;
import cn.com.eju.deal.contract.service.OaService;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.contract.ContractInfoDto;
import cn.com.eju.deal.dto.contract.DepositDto;
import cn.com.eju.deal.dto.oa.OaModifyDto;
import cn.com.eju.deal.dto.store.DecorationDto;
import cn.com.eju.deal.fangyou.service.FangyouService;
import cn.com.eju.deal.oa.service.OaOperatorService;
import cn.com.eju.deal.store.service.StoreDepositService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 提交ＯＡ审核
 *
 * @author (li_xiaodong)
 * @date 2016年4月19日 下午3:12:59
 */
@Controller
@RequestMapping(value = "contracts")
public class OaController extends BaseController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());


    @Resource(name = "oaService")
    private OaService oaService;

    @Resource(name = "contractService")
    private ContractService contractService;

    @Resource(name = "oaOperatorService")
    private OaOperatorService oaOperatorService;

    @Resource(name = "fangyouService")
    private FangyouService fangyouService;

    @Resource(name = "fesbService")
    private FesbService fesbService;

    @Resource(name = "extOmsService")
    private ExtOmsService extOmsService;

    @Resource(name = "commonService")
    private CommonService commonService;
    
    @Resource(name="storeDepositService")
    private StoreDepositService storeDepositService;

    /**
     * 合同创建完毕 调OA接口 提交审核
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "oa/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> toOaAuth(@PathVariable("id") Integer id, ModelMap mop, HttpServletRequest request) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        UserInfo userInfo = UserInfoHolder.get();

        //获取请求参数
        Map<String, Object> reqqMap = bindParamToMap(request);
        String busNo = (String) reqqMap.get("busNo");
        int contractVersion = Integer.valueOf((String) reqqMap.get("contractVersion"));

        //Add by WangLei 2017/04/07 Start
        String storeId = (String) reqqMap.get("storeId");
        //Add by WangLei 2017/04/07 End
        Map<String, Object> reqMap = new HashMap<String, Object>();

//Add By Guopengfei 审核中，审核通过不能再提交OA审核 Start
        if (!cantoOaAuth(id)) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "该合同已提交OA审核，请刷新数据！");
            return getOperateJSONView(rspMap);
        }
//Add By Guopengfei 审核中，审核通过不能再提交OA审核 End

//Add By QJP B合同或A转B合同乙转甲和门店资质等级必须有值 Start        
        try {
            ResultData resultData = checkContractInfo(id,contractVersion);
            if(ReturnCode.FAILURE.equals(resultData.getReturnCode())){
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
                return getOperateJSONView(rspMap);
            }


        } catch (Exception e) {
            logger.error("contract", "OaController", "checkContractInfo", "", UserInfoHolder.getUserId(), "", "合同查询失败", e);
        }
//Add By QJP B或A转B是否乙转甲和门店资质等级必须有值 End       

        try {
            oaService.toOaAuthContracts(reqMap, id, busNo, storeId,userInfo,(String)reqqMap.get("companyId"),(String)reqqMap.get("storeIdArray"));
        } catch (Exception e) {
            logger.error("Contacts", "OaController", "toOaAuth", "", 0, "", "", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "向OA发起提交审核失败");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }

    /**
     * 获取OA审核状态
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "oa/state/{flowId}/{id}/{companyId}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getOaState(@PathVariable("flowId") String flowId, @PathVariable("id") Integer id,
                                       @PathVariable("companyId") Integer companyId) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        ResultData<?> rebackData = new ResultData<>();

        Integer reback = -1;
        try {
            reback = oaService.getOaState(flowId);
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            logger.error("Contacts", "OaController", "getOaState", "", UserInfoHolder.getUserId(), "", "", e);
            return getOperateJSONView(rspMap);
        }

        //0正常结束，/非正常结束：5 撤销、15终止 
        if (0 == reback || 5 == reback || 15 == reback) {
            boolean isSuccess = true;
            ContractInfoDto contractInfoDto = new ContractInfoDto();
            // 设置审核结果
            contractInfoDto.setAuditRst(reback);
            ContractDto contract = new ContractDto();
            contract.setFlowId(String.valueOf(flowId));
            //0正常结束，
            if (0 == reback) {
                //需要更新该合同的状态，更新为“审核通过”
                contract.setContractStatus(DictionaryConstants.CONTRACT_STATUS_AUDIT_PASS);
                // 审核通过新增审核通过时间
                contract.setPerformDate(new Date());
                contract.setDateUpdate(new Date());
                contract.setCompanyId(companyId);
                contract.setBizType("21801|21802");
                contractInfoDto.setContract(contract);
                try {
                    // 更新
                    contractService.updateContractStatusByFlowId(contractInfoDto);
                } catch (Exception e1) {
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    rspMap.put(Constant.RETURN_MSG_KEY, "更新合同状态失败");
                    logger.error("Contacts",
                            "OaController",
                            "getOaState",
                            "",
                            UserInfoHolder.getUserId(),
                            "",
                            "Oa更新合同状态异常",
                            e1);
                    return getOperateJSONView(rspMap);
                }

                //Mod By GUOPENGFEI 2017/04/07 start
//                              if(isSuccess) {
                Integer OriginalContractdistinction = 0;
                String acCityNo = null;
                try {
                    ResultData<?> rebackContract = new ResultData<>();
                    ContractDto contractDto = new ContractDto();
                    rebackContract = contractService.getContractInfoByFlowId(flowId);

                    Map<?, ?> mapTemp2 = (Map<?, ?>) rebackContract.getReturnData();

                    contractDto = MapToEntityConvertUtil.convert(mapTemp2, ContractDto.class, "");
                    OriginalContractdistinction = contractDto.getOriginalContractdistinction();
                    acCityNo = contractDto.getAcCityNo();
                } catch (Exception e) {
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    logger.error("Contacts", "OaController", "getOaState", "", UserInfoHolder.getUserId(), "", "", e);
                    return getOperateJSONView(rspMap);
                }

                ContractDto dto = new ContractDto();
                if ((isSuccess) && (OriginalContractdistinction == DictionaryConstants.OriginalContractdistinction_TYPE_N)) {    //新签
                    //Mod By GUOPENGFEI 2017/04/07 end
                    /**审核通过 则向oms写门店装修信息**/
                    try {
                        //审核通过,传递flowId，并且后台创建门店装修表数据
                        List<String> passList = new ArrayList<String>();
                        passList.add(flowId);
                        Map<String, Object> mop = new HashMap<String, Object>();
                        mop.put("pass", passList);

                        //返回创建门店装修的List<StoreDecorationDto>
                        rebackData = contractService.insertNewDecorationRecord(mop);

                        //更新门店装修List<StoreDecorationDto>装修信息到OMS
                        if (null != rebackData.getReturnData()) {
                            List<Map<String, Object>> listmap = (List<Map<String, Object>>) rebackData.getReturnData();
                            //更新到OMS，数据组装
                            List<DecorationDto> postList = new ArrayList<DecorationDto>();
                            for (int i = 0; i < listmap.size(); i++) {
                                Map tempMop = (Map) listmap.get(i);
                                DecorationDto decorationDto = new DecorationDto();
                                decorationDto.setDecorateNo((String) tempMop.get("decorationNo"));
                                decorationDto.setStoreNo((String) tempMop.get("storeNo"));
                                decorationDto.setContractNo((String) tempMop.get("contractNo"));
                                decorationDto.setDecorateStatus((int) tempMop.get("decorationStatus"));
                                decorationDto.setStoreName((String) tempMop.get("storeName"));
                                decorationDto.setStoreAddress((String) tempMop.get("storeAddress"));
                                decorationDto.setContractType((String) tempMop.get("contractTypeName"));
                                decorationDto.setCompanyName((String) tempMop.get("companyName"));
                                decorationDto.setCityNo((String) tempMop.get("cityNo"));
                                decorationDto.setAgreementNo((String) tempMop.get("agreementNo"));
                                decorationDto.setDateLifeStartStr((String) tempMop.get("dateLifeStartStr"));
                                decorationDto.setDateLifeEndStr((String) tempMop.get("dateLifeEndStr"));
                                decorationDto.setOafilpagency((String) tempMop.get("oafilpagency"));
                                postList.add(decorationDto);
                            }
                            if (!postList.isEmpty()) {
                                //调用OMS
                                ResultData<?> rebackResult = fesbService.httpPost(postList, FesbMethodConstants.FESB_METHOD_CODE_DEPOSIT_UPDATE);
                                if (!rebackResult.getReturnCode().equals(ReturnCode.SUCCESS)) {
                                    logger.error("contract", "contractStateJob", "handleJob", "list = " + listmap.toString(), null, null, "根据flowId写入门店装修数据OMS异常!", null);
                                }
                            } else {
                                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                                rspMap.put(Constant.RETURN_MSG_KEY, "根据flowId写入门店装修数据信息为空!");
                                logger.error("contract", "contractStateJob", "handleJob", "postList = " + postList.toString(), null, null, "根据flowId写入门店装修返回数据为空!", null);
                                return getOperateJSONView(rspMap);
                            }
                        }
                    } catch (Exception e) {
                        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                        rspMap.put(Constant.RETURN_MSG_KEY, "根据flowId写入门店装修信息失败!");
                        logger.error("contract", "OaController", "getOaState", "", UserInfoHolder.getUserId(), "", "根据flowId写入门店装修信息失败!", e);
                        return getOperateJSONView(rspMap);
                    }

                    /**审核通过 则向oms写保证金信息**/
                    boolean depositOpenFlag = true;
                    if(acCityNo == null) {
                        logger.error("contract", "OaController", "getOaState", "", UserInfoHolder.getUserId(), "", "根据flowId："+flowId+"查询合同acCityNo为空，默认新版保证金已开放", new NullPointerException());
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
                            logger.error("contract", "OaController", "getOaState", "", UserInfoHolder.getUserId(), "", "根据合同acCityNo查询开关异常，默认新版保证金已开放", e);
                        }
                    }
                    
                    if(!depositOpenFlag) {
                        try {
                            // 根据flowId查询合同信息
                            ResultData<?> returnDate = contractService.getDepoistNozeroCtrctByFlowId(flowId);
                            Map<?, ?> mapTemp = (Map<?, ?>) returnDate.getReturnData();
                            if (null != mapTemp) {
                                // 保证金总额
                                Long itemAmountStr = new Long(mapTemp.get("totleDepositFee").toString());
                                ContractDto contractDto = MapToEntityConvertUtil
                                        .convert(mapTemp, ContractDto.class, "");
                                if (contractDto.getContractType() != null && (contractDto.getContractType().intValue() == 10302 || contractDto.getContractType().intValue() == 10304)) {
                                    // 构造传递的保证金信息
                                    DepositDto deposit = new DepositDto();
                                    deposit.setContractNo(contractDto.getContractNo());// 合同编号
                                    deposit.setContractType(contractDto.getContractTypeName());// 合同类型(A版、B版、A转B版、B转A版)
                                    deposit.setContractState(contractDto.getContractStatusName());// 合同状态(草签、审核通过、审核驳回、作废)
                                    deposit.setCompanyName(contractDto.getPartyB());// 公司名称
                                    deposit.setItemAmount(itemAmountStr);// 保证金总额
                                    // 协议书编号
                                    deposit.setAgreementNo(contractDto.getAgreementNo());
                                    //城市NO
                                    deposit.setCityNo(contractDto.getCityNo());
                                    // 调用OMS接口
                                    ResultData<?> rebackResult = fesbService.httpPost(deposit, FesbMethodConstants.FESB_METHOD_CODE_STORE_QUERY);
                                    Integer code = Integer.valueOf(rebackResult.getReturnCode());
                                    if (code != 200) {
                                        logger.error("contract", "contractStateJob", "getOaState", "deposit = " + JsonUtil.parseToJson(deposit), null, null, "调用OMS接口新增保证金失败!", null);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                            rspMap.put(Constant.RETURN_MSG_KEY, "根据flowId查询合同信息失败!");
                            logger.error("contract", "OaController", "getOaState", "", UserInfoHolder.getUserId(), "", "根据flowId查询合同信息失败!", e);
                            return getOperateJSONView(rspMap);
                        }
                        //保证金到账
                        try {
                            extOmsService.createPerformNodeRecordByFlowId(flowId);
                        } catch (Exception e) {
                            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                            rspMap.put(Constant.RETURN_MSG_KEY, "method createPerformNodeRecordByFlowId fail!");
                            logger.error("contract", "OaController", "getOaState", "", UserInfoHolder.getUserId(), "", "method createPerformNodeRecordByFlowId fail!", e);
                            return getOperateJSONView(rspMap);
                        }
                    }
                }
            }
            //非正常结束：5 撤销、15终止 
            else if (5 == reback || 15 == reback) {
                //需要更新该合同的状态，更新为“审核未通过”
                contract.setContractStatus(DictionaryConstants.CONTRACT_STATUS_AUDIT_NO_PASS);
                contract.setDateUpdate(new Date());

                contractInfoDto.setContract(contract);
                try {
                    contractService.updateContractStatusByFlowId(contractInfoDto);
                } catch (Exception e) {
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    rspMap.put(Constant.RETURN_MSG_KEY, "更新合同状态失败");
                    logger.error("Contacts",
                            "OaController",
                            "getOaState",
                            "",
                            UserInfoHolder.getUserId(),
                            "",
                            "Oa更新合同状态异常",
                            e);
                    return getOperateJSONView(rspMap);
                }
            }
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        rspMap.put(Constant.RETURN_DATA_KEY, reback);
        return getSearchJSONView(rspMap);
    }

    /**
     * 上传附件 OA
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "oa/upload", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> upload(HttpServletRequest request) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        //合同附件上传标示
        //上传到OA系统
        try {
            //返回附件Id
            String attachmentId = oaService.oaFileAssist(request);

            //check返回值
            boolean b = isNumeric(attachmentId);
            if (!b) {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                return getOperateJSONView(rspMap);
            }

            rspMap.put(Constant.RETURN_DATA_KEY, attachmentId);
        } catch (Exception e) {
            logger.error("Contacts", "OaController", "oaupload", "", UserInfoHolder.getUserId(), "", "", e);

            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            return getOperateJSONView(rspMap);
        }

        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);

        return getOperateJSONView(rspMap);
    }

    /**
     * check返回值（正常数据-1650100010010654120，异常-1Login name does not exist）
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        if (StringUtil.isNotEmpty(str)) {
            for (int i = 2; i < str.length() - 1; i++) {
                //从第三个字符开始check,如果非数字，就不过
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
        } else {
            return false;
        }

        return true;
    }



    /**
     * 修改表单
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "oa/modify/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> oaModify(HttpServletRequest request, @PathVariable("id") Integer id) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        //获取请求参数
        Map<String, Object> reqqMap = bindParamToMap(request);
        String busNo = (String) reqqMap.get("busNo");

        String flowId = (String) reqqMap.get("flowId");
        //Add By WangLei 2017/04/07 Start
        String storeIds = (String) reqqMap.get("storeId");
        //Add By WangLei 2017/04/07 End        
        //表单数据、附件数据
        Map<String, Object> rsppMap;
        try {
            //Mod By WangLei 2017/04/07 Start
            //rsppMap = setOaAuditData(id, busNo, FLAG_MODIFY_KEY);
            //rsppMap = setOaAuditData(id, busNo, FLAG_MODIFY_KEY, storeIds);
            //Mod By WangLei 2017/04/07 Start
        } catch (Exception e) {
            logger.error("Contacts", "OaController", "oaModify", "", UserInfoHolder.getUserId(), "", "", e);

            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);

            rspMap.put(Constant.RETURN_MSG_KEY, " 修改表单，组装合同数据错误");

            return getOperateJSONView(rspMap);
        }

        //返回
        OaModifyDto oaModifyDto = null;//(OaModifyDto) rsppMap.get(MODIFY_DATA_KEY);

        //修改表单
        String reback;
        try {
            reback = oaService.modify(oaModifyDto);
        } catch (Exception e) {
            logger.error("Contacts", "OaController", "oaModify", "", UserInfoHolder.getUserId(), "", "调OA修改表单接口错误", e);

            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);

            rspMap.put(Constant.RETURN_MSG_KEY, " 修改表单，OA接口异常");

            return getOperateJSONView(rspMap);
        }

        //返回接口map
        Map<?, ?> rebackMap = new HashMap<String, Object>();

        if (StringUtil.isNotEmpty(reback)) {

            rebackMap = JsonUtil.parseToObject(reback, Map.class);
        }

        Integer BFlag = (Integer) rebackMap.get("BFlag");
        if (10 != BFlag) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);

            rspMap.put(Constant.RETURN_MSG_KEY, " 修改表单，OA接口异常");

            return getOperateJSONView(rspMap);
        }
        //修改成功修改变更合同状态为审核中
        else {
            ContractInfoDto contractInfoDto = new ContractInfoDto();
            ContractDto contract = new ContractDto();
            contract.setFlowId(String.valueOf(flowId));
            //需要更新该合同的状态，更新为“审核通过”
            contract.setContractStatus(DictionaryConstants.CONTRACT_STATUS_AUDITING);

            contractInfoDto.setContract(contract);
            try {
                contractService.updateContractStatusByFlowId(contractInfoDto);
            } catch (Exception e) {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "更新合同状态失败");
                logger.error("Contacts", "OaController", "getOaState", "", UserInfoHolder.getUserId(), "", "", e);
                return getOperateJSONView(rspMap);
            }

        }

        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);

        return getOperateJSONView(rspMap);
    }

    /**
     * 获取审核批注原因
     *
     * @param flowId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "oa/opinions/{flowId}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getOpinions(HttpServletRequest request, @PathVariable("flowId") String flowId) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        //修改表单
        String reback;
        try {
            reback = oaService.getOpinions(flowId);
        } catch (Exception e) {
            logger.error("Contacts", "OaController", "oaModify", "", UserInfoHolder.getUserId(), "", "调OA获取审核批注原因错误", e);

            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);

            rspMap.put(Constant.RETURN_MSG_KEY, "获取审核批注原因失败，OA接口异常");

            return getOperateJSONView(rspMap);
        }

        //返回接口map
        Map<?, ?> rebackMap = new HashMap<String, Object>();

        if (StringUtil.isNotEmpty(reback)) {

            rebackMap = JsonUtil.parseToObject(reback, Map.class);
        }

        Integer BFlag = (Integer) rebackMap.get("BFlag");
        String msg = (String) rebackMap.get("Msg");
        List<?> tData = (List<?>) rebackMap.get("TData");

        if (10 != BFlag) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);

            rspMap.put(Constant.RETURN_MSG_KEY, msg);

            return getOperateJSONView(rspMap);
        }

        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        rspMap.put(Constant.RETURN_DATA_KEY, tData);

        return getOperateJSONView(rspMap);
    }

    /**
     * 获取OA数据模板
     *
     * @param flowId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "oa/template/{flowId}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getTemplate(@PathVariable("flowId") String flowId, ModelMap mop,
                                        HttpServletRequest request, HttpServletResponse response) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();


        //组装表单发起数据
        //        setToOaInfo(flowId, reqMap, null);

        try {
            //返回流程template
            String templateStr = oaService.getOaTemplate("7931458001458512351");
            logger.info("OA template:", templateStr);
        } catch (Exception e) {
            logger.error("contract", "OaController", "getTemplate", "", null, "", "", e);
        }

        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);

        return getOperateJSONView(rspMap);
    }

    //Add By Guopengfei 审核中，审核通过不能再提交OA审核 Start

    /**
     * 是否可以提交OA审核
     *
     * @param id 合同ID
     * @return
     */
    private boolean cantoOaAuth(int id) {
        int contractstatus = 0;                            // 合同状态

        try {
            ResultData<?> resultData = contractService.getById(id);
            Map<?, ?> mapTemp = (Map<?, ?>) resultData.getReturnData();
            Map<?, ?> contractmaop = (Map<?, ?>) mapTemp.get("contract");

            contractstatus = Integer.parseInt(contractmaop.get("contractStatus").toString());
            if (contractstatus == 10402 || contractstatus == 10403) {    // 审核中，审核通过 不能再提交OA审批
                return false;
            }

        } catch (Exception e) {
            logger.error("contract", "OaController", "cantoOaAuth", "", null, "", "合同状态判断失败", e);
            return false;
        }

        return true;
    }
    //Add By Guopengfei 审核中，审核通过不能再提交OA审核 End

    //Add By GuoPengFei 合规性 start






    //Add By GuoPengFei 合规性 end

    /**
     * 根据合同ID查询合同信息
     *
     * @param contractId
     * @return
     */
    public ResultData checkContractInfo(int contractId,int contractVersion) {
        ResultData resultData1 = new ResultData<>();
        resultData1.setSuccess();
//        resultData1.setReturnMsg("请在合同编辑页面设置门店资质等级");
        //返回map
        ResultData<?> resultData = new ResultData<ContractInfoDto>();
        try {
            resultData = contractService.getById(contractId);
        } catch (Exception e) {
            logger.error("contract",
                    "ContractController",
                    "show",
                    "",
                    UserInfoHolder.getUserId(),
                    "",
                    "合同查看初始化,查询失败",
                    e);

        }
        Map<String, List<?>> datamap = (Map<String, List<?>>) resultData.getReturnData();

        Map<String, Object> contractMap = (Map<String, Object>) datamap.get("contract");

        int contractType = (int) contractMap.get("contractType");

        if (contractType != 10302 && contractType != 10304) {
            resultData1.setSuccess();
        }

        int contractVersionDb = (int)contractMap.get("contractVersion");
        if(contractVersionDb != contractVersion){
            String strMsg = "该合同信息微信端已修改，请返回刷新确认无误后再次提交OA审核！";
            resultData1.setReturnData(null);
            resultData1.setFail();
            resultData1.setReturnMsg(strMsg);
        }


//       //
//       if(!contractMap.containsKey("contractB2A")||contractMap.get("contractB2A")==null){
//    	   brtn = false;
//    	   return brtn;
//       }else  {
//	    	   String hasBToA =  contractMap.get("contractB2A").toString();
//	    	   if(("").equals(hasBToA) || hasBToA == null)
//	       {
//	    	   brtn = false;
//	    	   return brtn;
//	       }
//       }

//       //取出门店的值
//       List<Map<String,Object>> storeList = (List<Map<String,Object>>) datamap.get("storeDetails");
//       
//       String btypeStoreName = "";
//       for (Map<String, Object> map : storeList) 
//       {
//    	   btypeStoreName = (String) map.get("btypeStoreName");
//           if(("").equals(btypeStoreName) || btypeStoreName == null)
//           {
//        	   brtn = false;
//        	   return brtn;
//           }
//    	   
//       }

        return resultData1;
    }

    /**
     * 获取审核批注原因
     *
     * @param flowId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "oa/opinionsNew/{flowId}", method = RequestMethod.GET)
    @ResponseBody
    public ResultData<?> getOpinionsNew(HttpServletRequest request, @PathVariable("flowId") String flowId) {
        ResultData<?> resultData = new ResultData<>();
        try {
            resultData = oaService.getOpinionsNew(flowId);
        } catch (Exception e) {
            logger.error("oa", "OaController", "getOpinionsNew", "", UserInfoHolder.getUserId(), "", "调OA获取审核批注原因错误", e);
            resultData.setFail();
        }
        return resultData;
    }
}
