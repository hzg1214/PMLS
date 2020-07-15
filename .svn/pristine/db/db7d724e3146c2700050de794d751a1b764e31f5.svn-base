package cn.com.eju.deal.contract.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.eju.deal.core.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.seqNo.service.SeqNoService;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.base.util.XmlTransferUtil;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.company.service.CompanyService;
import cn.com.eju.deal.company.service.CompanyStoreService;
import cn.com.eju.deal.contract.service.AchievementCancelService;
import cn.com.eju.deal.contract.service.ContractChangeService;
import cn.com.eju.deal.contract.service.ContractService;
import cn.com.eju.deal.contract.service.ExtOmsService;
import cn.com.eju.deal.contract.service.OaService;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.dto.company.CompanyDto;
import cn.com.eju.deal.dto.company.CompanyStoreDto;
import cn.com.eju.deal.dto.company.CompanyStoreDtoNew;
import cn.com.eju.deal.dto.contract.ContractChangeDto;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.contract.ContractInfoDto;
import cn.com.eju.deal.dto.contract.ContractSearchResultDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.store.service.StoreService;

/**
 * 合同变更OA提交审核
 *
 * @author sunmm
 * @date 2016年8月8日 下午4:56:45
 */
@Controller
@RequestMapping(value = "changeOAContract")
public class ContractChangeOAController extends BaseController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "stopContractService")
    private ContractChangeService stopContractService;

    @Resource(name = "contractService")
    private ContractService contractService;

    @Resource(name = "oaService")
    private OaService oaService;

    @Resource(name = "extOmsService")
    private ExtOmsService extOmsService;

    @Resource(name = "storeService")
    private StoreService storeService;

    @Resource(name = "companyService")
    private CompanyService companyService;

    @Resource(name = "companyStoreService")
    private CompanyStoreService companyStoreService;
    
    //2017/06/27 cning Add Start
    @Resource(name = "achievementCancelService")
    private AchievementCancelService achievementCancelService;
    
    @Resource(name = "seqNoService")
    private SeqNoService seqNoService;


    /**
     * 合同变更提交审核（调OA）
     *
     * @param id       合同变更ID
     * @param mop
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "oa/{id}/{contractId}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> toOaAuth(@PathVariable("id") Integer id, @PathVariable("contractId") Integer contractId,
                                     ModelMap mop, HttpServletRequest request, HttpServletResponse response) {
        // 返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        String busNo = (String) reqMap.get("busNo");
        // 门店Id集合
        List<Integer> storeIdList = new ArrayList<Integer>();
        try {
            
            // 获取此次变更勾选的门店的门店地址
            this.getStoreAddress(id, storeIdList,reqMap);
            
            //提交审核前进行门店权限检查
            //根据门店List进行查询门店合同签约历史的查询，获取最新的合同ID
            ResultData<?> resultData = stopContractService.getSignHisByStoreId(storeIdList);
            List<ContractSearchResultDto> backList = (List<ContractSearchResultDto>) resultData.getReturnData();
            String msg = "";

            Map<String, Object> map = new HashMap<String, Object>();
            //判断公司是否已发生变更
            ResultData<?> resultData2 = stopContractService.getIsRelieveCompany(contractId,storeIdList);
            List<CompanyStoreDtoNew> backList2 = (List<CompanyStoreDtoNew>) resultData2.getReturnData();
            Map<String, Object> map2 = new HashMap<String, Object>();
           // 非空判断
            if (null != backList && !backList.isEmpty()) {
                // 循环变更中的门店//只有一条数据
                for (int i = 0; i < backList.size(); i++) {
                    map = (Map) backList.get(i);
                    if (!contractId.equals(map.get("id"))) {
                    	String companyId = map.get("companyId").toString();
                    	if(null != backList2) {
                    		Map companyStoreDtoNew = (Map)backList2.get(0);
                    		//公司id是相同的
                    		if(companyId.equals(companyStoreDtoNew.get("companyId").toString())) {
                    			msg += msg + "已经有新签合同，合同编号:" + map.get("contractNo") + "创建时间:" + map.get("dateCreate") + "，请在新合同中执行终止操作!";
                    			rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    			rspMap.put(Constant.RETURN_MSG_KEY, msg);
                    			return getOperateJSONView(rspMap);
                    		}
                    	}
                    }
                }
            }
            // 存在相同门店的场合
            if (!chkStoreId(id, storeIdList)) {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "此变更单中已经有门店提交了终止申请或已终止，请勿重复提交！");
                return getOperateJSONView(rspMap);
            }
            //进行校验要变更的合同的门店是否在门店业绩撤销中，是，则提示，不允许变更
            if (!this.checkStoreCancelState(contractId, storeIdList)) {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "门店尚未完成业绩撤销，请业绩撤销审核通过后再发起终止提交！");
                return getOperateJSONView(rspMap);
            }
            //获取原来的数据
            if(storeIdList.size() ==1){
                reqMap.put("storeId", storeIdList.get(0));
            	stopContractService.toOaAuthContractChange(reqMap, id, contractId, busNo, storeIdList, UserInfoHolder.get());
            }else{
                String result = stopContractService.splitToSingle(id,storeIdList);
                if(result != null && result.length() > 0) {
                    String[] strWithIdAndStoreId = result.split("#");
                    int l = strWithIdAndStoreId.length;
                    for(int i = 0;i<l;i++) {
                        String[] arrayIds = strWithIdAndStoreId[i].split("-");
                        Integer contractChangeId = Integer.valueOf(arrayIds[0]);
                        Integer storeId = Integer.valueOf(arrayIds[1]);
                        storeIdList = new ArrayList<>();
                        //storeIdList.add(storeId);
                        
                        Map<String, Object> reqMapTemp = new HashMap<>();
                        //门店id,地址
                        reqMapTemp.put("storeId", storeId);
                        
                        // 获取此次变更勾选的门店的门店地址
                        this.getStoreAddress(contractChangeId, storeIdList,reqMapTemp);
                        
                        stopContractService.toOaAuthContractChange(reqMapTemp, contractChangeId, contractId, busNo, storeIdList, UserInfoHolder.get());
                        System.out.println("-------------------------------------------");
                    }
                }else {
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    rspMap.put(Constant.RETURN_MSG_KEY, "拆分提交OA单异常，请确认后再提交OA");
                    return getOperateJSONView(rspMap);
                }
            }
        } catch (Exception e1) {
            logger.error("contract",
                    "ContractChangeOAController",
                    "setToOaInfo",
                    "",
                    UserInfoHolder.getUserId(),
                    "",
                    "",
                    e1);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "组装合同数据错误");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }

    /**
     * 获取此次变更勾选的门店的门店地址
     *
     * @param contractChangtId 合同变更ID
     * @param storeIdList      此次变更勾选的门店ID集合
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void getStoreAddress(int contractChangtId, List<Integer> storeIdList,Map<String, Object> reqMap) {

        // 门店地址
        StringBuilder sb = new StringBuilder();
        // 门店地址Title
        StringBuilder storeTitle = new StringBuilder();

        List<StoreDto> dtoList = new ArrayList<StoreDto>();
        try {
            // 根据合同变更ID查询变更申请信息
            ResultData<?> resultData = stopContractService.getStoreInfo(contractChangtId);

            dtoList = (List<StoreDto>) resultData.getReturnData();

            Map<String, Object> map = new HashMap<String, Object>();

            // 门店地址
            String addressDetail = null;

            // 非空判断
            if (null != dtoList && !dtoList.isEmpty()) {
                for (int i = 0; i < dtoList.size(); i++) {
                    map = (Map) dtoList.get(i);
                    // 门店ID
                    storeIdList.add((Integer) map.get("storeId"));
                    // 门店地址
                    addressDetail = (String) map.get("addressDetail");
                    storeTitle.append("<column id=\"field0040\" type=\"0\" name=\"门店地址\" length=\"255\"/>");
                    sb.append("<row><column name=\"门店地址\"><value>" +  XmlTransferUtil.transfer(addressDetail) + "</value></column></row>");
                }
            } else {
                storeTitle.append("<column id=\"field0040\" type=\"0\" name=\"门店地址\" length=\"255\"/>");
                sb.append("<row><column name=\"门店地址\"><value> </value></column></row>");
            }
            // 门店地址Title
            reqMap.put("addressTitleXml",storeTitle.toString());
//            addressTitleXml = storeTitle.toString();
            // 门店地址
            reqMap.put("addressDetailXml",sb.toString());
//            addressDetailXml = sb.toString();

        } catch (Exception e) {
            logger.error("contract",
                    "ContractChangeOAController",
                    "getStoreAddressDetail",
                    "",
                    UserInfoHolder.getUserId(),
                    "",
                    "门店地址查询失败！",
                    e);
        }
    }

    /**
     * Check是否存在相同门店
     *
     * @param id          合同变更ID
     * @param storeIdList 此次所选的门店Id集合
     * @return 存在:false  不存在:true
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private boolean chkStoreId(int id, List<Integer> storeIdList) {

        // 返回标识 ( 存在:false  不存在:true )
        boolean isExist = true;

        List<ContractChangeDto> dtoList = new ArrayList<ContractChangeDto>();
        try {
            // 查询操作
            ResultData<?> resultData = stopContractService.getContractChangeById(id);

            dtoList = (List<ContractChangeDto>) resultData.getReturnData();


            Map<String, Object> map = new HashMap<String, Object>();

            // 非空判断
            if (null != dtoList && !dtoList.isEmpty()) {

                // 循环变更中的门店
                for (int i = 0; i < dtoList.size(); i++) {
                    map = (Map) dtoList.get(i);
                    // 门店ID
                    int storeIdDB = (Integer) map.get("storeId");

                    //签约主体变更
                    String changeCompany = (String) map.get("changeCompany");
                    if (changeCompany == null || changeCompany.equals("0")) {
                        continue;
                    }

                    // 循环此次变更选中的门店
                    for (int k = 0; k < storeIdList.size(); k++) {
                        // 选中门店ID
                        int storeId = storeIdList.get(k);

                        // 存在相同的门店的场合
                        if (storeIdDB == storeId) {
                            isExist = false;
                            break;
                        }
                    }

                    // 存在相同门店的场合
                    if (!isExist) {
                        break;
                    }

                }
            }

        } catch (Exception e) {
            logger.error("contract",
                    "ContractChangeOAController",
                    "chkStoreId",
                    "",
                    UserInfoHolder.getUserId(),
                    "",
                    "门店信息查询失败！",
                    e);
        }

        return isExist;
    }

    /**
     * 获取合同变更OA审核状态
     *
     * @param flowId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "oa/changestate/{flowId}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getOaChangeState(@PathVariable("flowId") String flowId) {
        // 返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        Integer reback = -1;
        ResultData<?> rstData = new ResultData<>();
        try {
            // 获取OA审核状态
            reback = oaService.getOaState(flowId);

            // 0:审核通过、5:撤销、15:终止
            if (0 == reback || 5 == reback || 15 == reback) {
                // 根据 流程Id及审核状态 更新合同信息
                rstData = stopContractService.updateCtrctRelateInfo(flowId, reback, UserInfoHolder.getUserId());

                //调用OMS更新接口，更新保证金中合同状态为终止
                List<String> templist = new ArrayList<>();
                templist.add(flowId);
                Map<String, Object> mop = new HashMap<>();
                mop.put("pass", templist);
                extOmsService.updateChgStatusToOmsSplit(mop);
            }

        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "获取审核状态失败!");
            logger.error("Contacts", "OaController", "getOaChangeState", "", UserInfoHolder.getUserId(), "", "", e);
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        rspMap.put(Constant.RETURN_MSG_KEY, rstData.getReturnMsg());
        rspMap.put(Constant.RETURN_DATA_KEY, reback);
        return getSearchJSONView(rspMap);
    }

    /**
     * 检验变更门店是否在门店业绩撤销中
     *
     * @param storeIdList
     * @param id
     * @return
     */
    private Boolean checkStoreCancelState(Integer contractId, List<Integer> storeIdList) {
        // 返回标识 ( false:撤销中 true ：正常)
        boolean b = true;

        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("contractId", contractId);
        reqMap.put("storeIdList", storeIdList);

        try {
            // 查询门店合同中撤消状态
            ResultData<?> resultData = stopContractService.getStoreCancelState(reqMap);
            if (resultData.getReturnData().equals("N") || resultData.getReturnCode().equals(ReturnCode.FAILURE)) {
                b = false;
            }
        } catch (Exception e) {
            logger.error("contract",
                    "ContractChangeOAController",
                    "checkStoreCancelState",
                    "",
                    UserInfoHolder.getUserId(),
                    "",
                    "检验变更门店是否在门店业绩撤销中失败！",
                    e);
        }

        return b;
    }

    /**
     * 合同创建完毕 调OA接口 提交审核
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "oa/btoa/{id}/{contractId}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> toOaAuthBToA(@PathVariable("id") Integer id, @PathVariable("contractId") Integer contractId,
                                         HttpServletRequest request, HttpServletResponse response) {
        // 返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        // 获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        String busNo = (String) reqMap.get("busNo");
        // 门店Id集合
        List<Integer> storeIdList = new ArrayList<Integer>();

        //2017/06/27 cning Add Start
        String chgType = (String) reqMap.get("chgType");// 17002:乙类转甲类
        if ("17002".equals(chgType)) {
            ResultData<?> reback = new ResultData<>();
            // 1.获取门店业绩撤销历史记录
            List<Map<?, ?>> contentlist = new ArrayList<>();
            int isCancel = 0;
            // 根据合同ID查询业绩撤销记录
            try {
                // 根据合同ID查询门店信息
                reback = stopContractService.queryStoreOfEdit(id, contractId);

                // 页面数据
                List<?> storeList = (List<?>) reback.getReturnData();
                String storeId = ((Map<?, ?>) storeList.get(0)).get("storeId").toString();

                reback = stopContractService.getContractStore(Integer.valueOf(storeId), contractId);
                contentlist = (List<Map<?, ?>>) reback.getReturnData();
                for (Map<?, ?> map : contentlist) {

                    if ("17302".equals(map.get("approveState")) || "17304".equals(map.get("approveState"))) {//撤销中或已撤销
                        isCancel = 1;
                        break;
                    }
                }

                if (isCancel == 1) {
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    rspMap.put(Constant.RETURN_MSG_KEY, "有撤销中或已撤销的门店记录，不能进行乙类转甲类操作！");
                    return getOperateJSONView(rspMap);
                }

            } catch (Exception e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
        }
        //2017/06/27 cning Add End

        //判断是否为空
        try {
            ContractChangeDto dto = stopContractService.getContractChangeDto(id);
            int mainchange = dto.getChangeCompany();
            if (mainchange == 1) {
                String newCompanyName = dto.getNewCompanyName();
                String newLegalPerson = dto.getNewLegalPerson();
                String newCompanyAddressCityNo = dto.getNewCompanyAddressCityNo();
                String newCompanyAddressDistrictNo = dto.getNewCompanyAddressDistrictNo();
                String newCompanyAddress = dto.getNewCompanyAddress();
                String newAgreementNo = dto.getNewAgreementNo();
                Date newDateLifeStart = dto.getNewDateLifeStart();
                Date newDateLifeEnd = dto.getNewDateLifeEnd();
                if (newCompanyName == null || newLegalPerson == null || newCompanyAddressCityNo == null || newCompanyAddressDistrictNo == null
                        || newCompanyAddress == null || newAgreementNo == null || newDateLifeStart == null || newDateLifeEnd == null) {
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    rspMap.put(Constant.RETURN_MSG_KEY, "请填写新公司相关信息");
                    return getOperateJSONView(rspMap);
                }
            }
        } catch (Exception e) {
            logger.error("contract", "ContractChangeOAController", "getContractChange", "", UserInfoHolder.getUserId(), "", "合同变更信息查询失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "chan");
            return getOperateJSONView(rspMap);
        }

        // 组装表单发起数据
        try {
            // 获取此次变更勾选的门店的门店地址
            this.getStoreAddress(id, storeIdList,reqMap);
            stopContractService.toOaAuthBToA(reqMap,id,contractId,busNo,storeIdList,UserInfoHolder.get());

        } catch (Exception e1) {
            logger.error("Contacts", "OaController", "setToOaInfoAToB", "", UserInfoHolder.getUserId(), "", "", e1);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "组装合同数据错误");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }

    /**
     * 获取合同变更OA审核状态
     *
     * @param flowId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "oa/changestate/btoa/{flowId}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getOaChangeStateB2A(@PathVariable("flowId") String flowId) {
        // 返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        Integer reback = -1;
        ResultData<?> rstData = new ResultData<>();
        try {
            // 获取OA审核状态
            reback = oaService.getOaState(flowId);

            // 0:审核通过、5:撤销、15:终止
            if (0 == reback || 5 == reback || 15 == reback) {
                // 根据 流程Id及审核状态 更新合同信息
                rstData = stopContractService.updateCtrctRelateInfo(flowId, reback, UserInfoHolder.getUserId());
            }
            
            //全部操作移到PMLSService->ContractChangeService->doPassDateUpdateAddon
            /*if(0 == reback)
            {
                // 乙类转甲类OA审批通过后处理
                updateforoa(flowId);
            }*/

        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "获取审核状态失败!");
            logger.error("Contacts", "OaController", "getOaChangeState", "", UserInfoHolder.getUserId(), "", "", e);
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        rspMap.put(Constant.RETURN_MSG_KEY, rstData.getReturnMsg());
        rspMap.put(Constant.RETURN_DATA_KEY, reback);
        return getSearchJSONView(rspMap);
    }

    /**
     * 三方变更提交OA
     * @param id
     * @param contractId
     * @param request
     * @return
     */
    @RequestMapping(value = "oa/threePart/{id}/{contractId}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> toOaAuthThreePart(@PathVariable("id") Integer id, @PathVariable("contractId") Integer contractId,HttpServletRequest request) {
        // 返回map
        Map<String, Object> rspMap = new HashMap<>();

        // 获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        String busNo = (String) reqMap.get("busNo");
        // 门店Id集合
        List<Integer> storeIdList = new ArrayList<>();

        // 组装表单发起数据
        try {
            // 获取此次变更勾选的门店的门店地址
            this.getStoreAddress(id, storeIdList,reqMap);
            stopContractService.toOaAuthThreePart(reqMap,id,contractId,busNo,storeIdList,UserInfoHolder.get());
        } catch (Exception e1) {
            logger.error("contractChangeOa", "ContractChangeOaController", "toOaAuthThreePart", JsonUtil.parseToJson(reqMap), UserInfoHolder.getUserId(), "", "", e1);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "组装合同数据错误");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }

    @RequestMapping(value = "oa/changestate/threePart/{flowId}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getOaChangeStateThreePart(@PathVariable("flowId") String flowId) {
        // 返回map
        Map<String, Object> rspMap = new HashMap<>();
        Integer reback = -1;
        ResultData<?> rstData = new ResultData<>();
        try {
            // 获取OA审核状态
            reback = oaService.getOaState(flowId);

            // 0:审核通过、5:撤销、15:终止
            if (0 == reback || 5 == reback || 15 == reback) {
                // 根据 流程Id及审核状态 更新合同信息
                rstData = stopContractService.updateInfoForThreePart(flowId, reback, UserInfoHolder.getUserId());
            }

        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "获取审核状态失败!");
            logger.error("contractChangeOa", "ContractChangeOaController", "getOaChangeStateThreePart", "return="+reback, UserInfoHolder.getUserId(), "", "", e);
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        rspMap.put(Constant.RETURN_MSG_KEY, rstData.getReturnMsg());
        rspMap.put(Constant.RETURN_DATA_KEY, reback);
        return getSearchJSONView(rspMap);
    }
}
