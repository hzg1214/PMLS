package cn.com.eju.deal.contract.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.eju.deal.core.util.JsonUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.helper.SeqNoHelper;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.base.util.XmlTransferUtil;
import cn.com.eju.deal.common.service.CommonService;
import cn.com.eju.deal.common.service.FileRecordMainService;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.contract.enums.FileTypeEnum;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.contract.ContractChangeDto;
import cn.com.eju.deal.dto.contract.ContractChangeStore;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.contract.ContractInfoDto;
import cn.com.eju.deal.dto.oa.OaAttachmentDto;
import cn.com.eju.deal.dto.oa.OaOperatorDto;
import cn.com.eju.deal.oa.service.OaOperatorService;

/**
 * 合同终止Service层
 *
 * @author sunmm
 * @date 2016年8月3日 下午6:17:51
 */
@Service("stopContractService")
public class ContractChangeService extends BaseService<ContractInfoDto> {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    private static final String YYYYMMDD_FORMAT = "yyyy-MM-dd";

    // 附件数据Map key
    private final static String ATTACH_KEY = "attachment";

    // 表单数据Map key
    private final static String XML_DATA_KEY = "xmlData";

    // ------------ 门店地址部分 ------------//
    /*// 门店地址Title拼接Xml
    private String addressTitleXml = null;

    // 门店地址拼接Xml
    private String addressDetailXml = null;*/

    // ------------上传附件部分 ------------//
    // 终止合作协议书
    private String oaFile1Xml = null;

    // 三方解约协议
    private String oaFile2Xml = null;

    // 保证金收据
    private String oaFile3Xml = null;

    // 已付装修款退还证明
    private String oaFile4Xml = null;

    // 注销证明
    private String oaFile5Xml = null;

    // 照片
    private String oaFile6Xml = null;

    // 新A合作协议书
    private String oaFile7Xml = null;

    // 一事一议终止方案
    private String oaFile8Xml = null;

    // 其他
    private String oaFile9Xml = null;

    //Add By Sucen 2017/05/27 Start
    // 变更补充条款
    private String oaFile10Xml = null;

    // 新营业执照
    private String oaFile11Xml = null;

    // 国家信息公示
    private String oaFile12Xml = null;

    // 房友确认单
    private String oaFile13Xml = null;

    // 门店照片
    private String oaFile14Xml = null;

    // 其他
    private String oaFile15Xml = null;

    // 转让三方权利(主体变更)
    private String oaFile16Xml = null;

    // 新签协议书及材料(主体变更)
    private String oaFile17Xml = null;

    // 国家信息公示(主体变更)
    private String oaFile18Xml = null;

    // 其他(主体变更)
    private String oaFile19Xml = null;
    //Add By Sucen 2017/05/27 End

    @Resource(name = "contractService")
    private ContractService contractService;

    @Resource(name = "commonService")
    private CommonService commonService;

    @Resource(name = "oaService")
    private OaService oaService;

    @Resource(name = "oaOperatorService")
    private OaOperatorService oaOperatorService;

    @Resource(name = "fileService")
    private FileRecordMainService fileService;

    /**
     * 根据合同ID查询门店信息 (合同变更-新增页面用)
     *
     * @param contractId 合同ID
     * @return
     * @throws Exception
     */
    public ResultData<?> queryStoreOfAdd(Integer contractId) throws Exception {
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/add/{contractId}";
        ResultData<?> reback = get(url, contractId);
        return reback;
    }

    /**
     * 根据合同变更ID和合同ID查询门店信息 (合同变更-更新页面用)
     *
     * @param id         合同变更ID
     * @param contractId 合同ID
     * @return
     * @throws Exception
     */
    public ResultData<?> queryStoreOfEdit(Integer id, Integer contractId) throws Exception {
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/edit/{id}/{contractId}";
        ResultData<?> reback = get(url, id, contractId);
        return reback;
    }

    /**
     * 根据合同变更ID查询变更申请信息
     *
     * @param id 合同变更ID
     * @return
     * @throws Exception
     */
    public ResultData<?> queryById(Integer id) throws Exception {
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/q/{id}";
        ResultData<?> reback = get(url, id);
        return reback;
    }

    /**
     * 为【变更记录页面】提供   根据合同ID查询变更申请信息
     *
     * @return
     * @throws Exception
     */
    public ResultData<?> getContractChange(Integer qContractId) throws Exception {
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/get/{contractId}";
        ResultData<?> reback = get(url, qContractId);
        return reback;
    }

    /**
     * 保存--合同变更信息
     *
     * @param contractInfoDto 合同变更信息
     * @return
     * @throws Exception
     */
    public ResultData<?> create(ContractInfoDto contractInfoDto)
            throws Exception {
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "";
        ResultData<?> backResult = post(url, contractInfoDto);
        return backResult;
    }

    /**
     * 更新--合同变更信息
     *
     * @param contractInfoDto
     * @throws Exception
     */
    public void update(ContractInfoDto contractInfoDto)
            throws Exception {
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "";
        put(url, contractInfoDto);
    }

    /**
     * @return
     * @throws Exception
     * @Title: createOAPicIdRecord
     * @Description: 保存oa返回Id到图片关系表
     */
    public ResultData<?> createOAPicIdRecord(ContractInfoDto changeDto)
            throws Exception {
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/oa/upload";
        ResultData<?> backResult = post(url, changeDto);
        return backResult;
    }

    /**
     * 根据flowID查询合同变更中的门店
     *
     * @param flowId   流程ID
     * @param auditRst 返回结果
     * @param userId   操作人ID
     * @return 合同门店关系列表
     * @throws Exception
     */
    public ResultData<?> updateCtrctRelateInfo(String flowId, Integer auditRst, Integer userId) throws Exception {
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/flowId/auditRst/{flowId}/{auditRst}/{userId}";
        ResultData<?> reback = get(url, flowId, auditRst, userId);
        return reback;
    }

    /**
     * 获取合同变更状态为审核中的FlowIdList
     *
     * @param approveState = 1
     * @return
     * @throws Exception
     */
    public ResultData<?> queryByApproveState(Map<String, Object> param) throws Exception {
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/approveState/{param}";
        ResultData<?> reback = get(url, param);
        return reback;
    }

    /**
     * 定时批量更新状态
     *
     * @param
     * @return
     * @throws Exception
     */
    public ResultData<?> updateChgStatusByParam(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/job/chgState/{param}";
        ResultData<?> backResult = get(url, reqMap);
        return backResult;
    }

    /**
     * 根据合同变更ID查询门店数
     *
     * @param id 合同变更ID
     * @return
     * @throws Exception
     */
    public ResultData<?> getStopStoreNum(Integer id) throws Exception {
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/getstorenum/{id}";
        ResultData<?> reback = get(url, id);
        return reback;
    }

    /**
     * 根据合同变更ID查询门店地址
     *
     * @param contractStopId 合同变更ID
     * @return
     * @throws Exception
     */
    public ResultData<?> getStoreInfo(Integer contractStopId) throws Exception {
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/getstore/{contractStopId}";
        ResultData<?> reback = get(url, contractStopId);
        return reback;
    }

    /**
     * 根据contractStopId和fileTypeCode查询附件关联信息
     *
     * @param param 合同变更ID、文件类型
     * @return
     * @throws Exception
     */
    public ResultData<?> getOaAttachment(Integer contractStopId, String fileTypeCode) throws Exception {
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/oaAttachment/{contractStopId}/{fileTypeCode}";
        ResultData<?> reback = get(url, contractStopId, fileTypeCode);
        return reback;
    }

    /**
     * 更新--合同门店关联表中的StoreState
     *
     * @param storeId    门店ID
     * @param contractId 合同ID
     * @throws Exception
     */
    public ResultData<?> updateStoreState(Integer storeId, Integer contractId) throws Exception {
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/update/{storeId}/{contractId}";
        ResultData<?> reback = get(url, storeId, contractId);
        return reback;
    }

    /**
     * 更新--将flowId和审核状态(approveState)更新到变更记录中
     *
     * @param contractInfoDto
     * @throws Exception
     */
    public void updateFlowId(Integer id, UserInfo userInfo, Long flowId,Map<String,Object> rspMap) throws Exception {
        ContractInfoDto dto = new ContractInfoDto();
        ContractChangeDto contractChangeDto = new ContractChangeDto();
        // 合同变更ID
        contractChangeDto.setId(id);
        // 提交审核状态 (1:审核中)
        contractChangeDto.setApproveState(1);
        // 更新日期
        contractChangeDto.setUpdateDate(new Date());
        // 更新人
        contractChangeDto.setUpdateCreate(userInfo.getUserId());
        // 流程ID
        contractChangeDto.setFlowId((String.valueOf(flowId)));
        //更新为OA已提交
        contractChangeDto.setSubmitOAStatus(21203);

        contractChangeDto.setAccountProject(rspMap.get("companyName").toString());
        contractChangeDto.setAccountProjectNo(rspMap.get("companyNo").toString());

        dto.setContractChangeDto(contractChangeDto);
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/updateFlowId";
        put(url, dto);
    }

    /**
     * 查询该合同ID对应的所有approveState是变更中的门店信息
     *
     * @param id 合同变更ID
     * @return
     * @throws Exception
     */
    public ResultData<?> getContractChangeById(Integer id) throws Exception {
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/get/store/{id}";
        ResultData<?> reback = get(url, id);
        return reback;
    }

    /**
     * 根据合同变更ID
     *
     * @param id         合同变更ID
     * @param contractId 合同ID
     * @return
     * @throws Exception
     */
    public ResultData<?> queryStoreByContractChangeId(Integer id, Integer contractId) throws Exception {
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/view/{id}/{contractId}";
        ResultData<?> reback = get(url, id, contractId);
        return reback;
    }

    /**
     * 根据门店Id查询签约的合同
     *
     * @param storeId
     * @return
     * @throws Exception
     */
    public ResultData<?> getSignHisByStoreId(List<Integer> storeIdList)
            throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/store/{param}";
        ResultData<?> reback = get(url, storeIdList);
        return reback;
    }

    /**
     * 检验变更门店是否在门店业绩撤销中
     *
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData<?> getStoreCancelState(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/contractstore/getcancelstate/{param}";
        ResultData<?> backResult = get(url, reqMap);
        return backResult;
    }

    /**
     * 根据门店ID查找合同门店变更信息
     *
     * @param storeid
     * @return
     * @throws Exception
     */
    public ResultData<?> getContractChangeByStoreId(Integer storeid) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/getstorechange/{storeid}";
        ResultData<?> backResult = get(url, storeid);
        return backResult;
    }

    /**
     * 获取该合同变更的门店地址
     *
     * @param contractStopId 合同变更ID
     * @return
     * @throws Exception
     */
    public ResultData<?> getChgStoreAddr(Integer contractStopId) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/getchgstoreaddr/{contractStopId}";
        ResultData<?> backResult = get(url, contractStopId);
        return backResult;
    }

    /**
     * 根据flowId查询合同变更信息
     *
     * @param flowId
     * @return
     * @throws Exception
     */
    public ResultData<?> getContractChangeByFlowId(String flowId) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/getContractChangeByFlowId/{flowId}";
        ResultData<?> backResult = get(url, flowId);
        return backResult;
    }

    /**
     * 根据flowId查询合同变更门店表信息
     *
     * @param flowId
     * @return
     * @throws Exception
     */
    public ResultData<?> getContractChangeStoreByFlowId(String flowId) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/getContractChangeStoreByFlowId/{flowId}";
        ResultData<?> backResult = get(url, flowId);
        return backResult;
    }

    //Add by cning 2017/07/04 Start

    /**
     * 根据门店ID和合同ID查找合同对应的门店是否撤销
     *
     * @param storeId
     * @param contractId
     * @return
     * @throws Exception
     */
    public ResultData<?> getContractStore(Integer storeId, Integer contractId)
            throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/getContractStoreByParm/{storeId}/{contractId}";
        ResultData<?> reback = get(url, storeId, contractId);
        return reback;
    }
    //Add by cning 2017/07/04 End

    @Async("threadPoolTaskExecutor")
    public ResultData toOaAuthContractChange(Map<String, Object> reqMap, Integer id, Integer contractId, String busNo, List<Integer> storeIdList, UserInfo userInfo) throws Exception {
        //返回结果
        ResultData resultData = new ResultData();
        resultData.setSuccess();
        //准备发起OA
        updateOASubmit(id,21202);

        // 1.组装XML表单发起数据
        Map<String,Object> rspMap = this.setToOaInfo(id, reqMap, busNo, contractId, userInfo);
        /*addressTitleXml = reqMap.get("addressTitleXml").toString();
        addressDetailXml = reqMap.get("addressDetailXml").toString();*/
        // 2.提交OA审核，获取FlowId
        // 返回流程Id
        Long flowId;
        try {
            // 合同变更OA模板
            //String oaTemplateCode = getOATemplateCode(contractId, "contractEndTpl");
        	
            // 合同变更提交OA审核
            flowId = oaService.toOaAuth(reqMap, rspMap.get("templateCode").toString());
        } catch (Exception e) {
            updateOASubmit(id,21204);
            logger.error("contract", "OaController", "toOaAuth", "", 0, "", "", e);
            resultData.setFail("向OA发起提交审核失败");
            return resultData;
        }
        
        if(flowId == null) {
            updateOASubmit(id,21204);
            logger.error("contract", "OaController", "toOaAuth", "", 0, "", "OA返回flowId为空", new NullPointerException());
            resultData.setFail("向OA发起提交审核失败");
            return resultData;
        }
        
        // 3.更新ContractChange表中的FlowId、approveState
        try {
            // 将flowId更新到ContractChange表中
            this.updateFlowId(id, userInfo, flowId,rspMap);
        } catch (Exception e) {
            logger.error("contract", "ContractChangeOAController", "toOaAuth", "", userInfo.getUserId(), "", "更新合同变更信息失败！", e);
        }
        // 4.更新ContractStore表中的StoreState
        try {
            if (null != storeIdList && !storeIdList.isEmpty()) {
                for (Integer aStoreIdList : storeIdList) {
                    int storeId = aStoreIdList;
                    // 更新操作
                    this.updateStoreState(storeId, contractId);
                }
            }
        } catch (Exception e) {
            logger.error("contract", "ContractChangeOAController", "toOaAuth", "", userInfo.getUserId(), "", "更新合同门店关联信息失败！", e);
        }
        return resultData;
    }

    @Async("threadPoolTaskExecutor")
    public ResultData toOaAuthBToA(Map<String, Object> reqMap, Integer id, Integer contractId, String busNo, List<Integer> storeIdList, UserInfo userInfo) throws Exception {
        //返回结果
        ResultData resultData = new ResultData();
        resultData.setSuccess();

        //准备发起OA
        updateOASubmit(id,21202);

        // 组装发送数据
        Map<String,Object> rspMap = setToOaInfoBToA(id, reqMap, busNo, contractId, userInfo);
        /*addressTitleXml = reqMap.get("addressTitleXml").toString();
        addressDetailXml = reqMap.get("addressDetailXml").toString();*/
        // 返回流程Id
        Long flowId;
        try {
            String oaTemplateCode = getOATemplateCode(contractId, "contractChgTpl");
            flowId = oaService.toOaAuth(reqMap, oaTemplateCode);
        } catch (Exception e) {
            updateOASubmit(id,21204);
            logger.error("Contacts", "OaController", "toOaAuth", "", 0, "", "", e);
            resultData.setFail("向OA发起提交审核失败");
            return resultData;
        }
        
        if(flowId == null) {
            updateOASubmit(id,21204);
            logger.error("Contacts", "OaController", "toOaAuth", "", 0, "", "OA返回flowId为空", new NullPointerException());
            resultData.setFail("向OA发起提交审核失败");
            return resultData;
        }

        try {
            // 更新ContractChange表中的FlowId、approveState
            // 将flowId更新到ContractChange表中
            this.updateFlowId(id, userInfo, flowId,rspMap);
        } catch (Exception e) {
            logger.error("contract", "ContractChangeController", "updateFlowId", "", userInfo.getUserId(), "",
                    "更新合同变更信息失败！", e);
        }
        // 更新ContractStore表中的StoreState
        try {

            // 根据flowId查询合同变更信息
            ResultData<?> resultDatasc = new ResultData<>();
            resultDatasc = this.getContractChangeByFlowId(flowId.toString());
            Map<?, ?> stopContract = (Map<?, ?>) resultDatasc.getReturnData();
            //签约主体变更
            String changeCompany = (String) stopContract.get("changeCompany").toString();

            //签约主体变更
            if (changeCompany != null && changeCompany.equals("1")) {
                // 非空判断
                if (null != storeIdList && !storeIdList.isEmpty()) {
                    for (int i = 0; i < storeIdList.size(); i++) {
                        int storeId = storeIdList.get(i);
                        // 更新操作
                        this.updateStoreState(storeId, contractId);
                    }
                }
            }

        } catch (Exception e) {
            logger.error("contract", "ContractChangeOAController", "toOaAuthBToA", "", userInfo.getUserId(), "",
                    "更新合同门店关联信息失败！", e);
        }
        return resultData;
    }

    /**
     * 组装表单发起数据
     *
     * @param id     合同变更ID
     * @param reqMap 返回提交OA的组装信息
     */
    private Map<String, Object> setToOaInfo(Integer id, Map<String, Object> reqMap, String busNo, Integer contractId, UserInfo userInfo) throws Exception {
        // 合同变更OA模板
        //String oaTemplateCode = getOATemplateCode(contractId, "contractEndTpl");

        // 模板编号
        //reqMap.put("templateCode", oaTemplateCode);

        // 发起者的登录名（登录协同的登录名）
        String senderLoginName = userInfo.getUserCode();
        reqMap.put("senderLoginName", senderLoginName);

        // 协同的标题
        reqMap.put("subject", "易居服务集团中介合作终止报批单");

        String addressTitleXml = reqMap.get("addressTitleXml").toString();
        String addressDetailXml = reqMap.get("addressDetailXml").toString();

        // 组装向OA发送的参数信息
        Map<String, Object> rspMap = this.setOaAuditData(id, busNo, userInfo, addressTitleXml, addressDetailXml,reqMap.get("storeId").toString());
        reqMap.put("templateCode", rspMap.get("templateCode").toString());
        // 附件，Long型数组，值为附件的Id。
        reqMap.put("attachments", rspMap.get(ATTACH_KEY));
        
        // HTML正文流程为html内容；表单流程为XML格式的表单数据
        reqMap.put("data", rspMap.get(XML_DATA_KEY));

        // 为控制是否流程发送。0：缺省值，发送，进入下一节点的待办（如果需要选人则保存到待发）1：不发送，保存到待发。
        reqMap.put("state", "0");

        return rspMap;
    }

    //合同变更OA模板取得
    private String getOATemplateCode(Integer contractId, String contractTplFlag) {
        String oaTemplateCode = "";
        String acCityNo = "";

        try {
            ResultData<?> resultData = contractService.getContractById(contractId);
            Map<String, Object> map = (Map<String, Object>) resultData.getReturnData();
            if (null != map) {
                acCityNo = map.get("acCityNo").toString();
            }
        } catch (Exception e) {
            logger.error("contract", "ContractController", "getOATemplateCode", "", null, "", "业绩归属所属城市获取异常", e);
        }

        try {
            ResultData<?> resultData = commonService.getCitySettingByCityNo(acCityNo);
            Map<String, Object> map = (Map<String, Object>) resultData.getReturnData();
            if (null != map) {
                oaTemplateCode = map.get(contractTplFlag).toString();
            }
        } catch (Exception e) {
            logger.error("contract", "ContractController", "getOATemplateCode", "", null, "", "合同变更OA模板获取异常", e);
        }

        return oaTemplateCode;
    }

    /**
     * 组装向OA发送的参数信息
     *
     * @param id 合同变更ID
     * @return
     */
    private Map<String, Object> setOaAuditData(Integer id, String busNo, UserInfo userInfo, String addressTitleXml, String addressDetailXml,String storeId) throws Exception {
        // 返回表单数据、返回附件数据
        Map<String, Object> rspMap = new HashMap<String, Object>();
        String dateHtml = "";
        // 1. 获取变更申请信息
        ContractChangeDto contractChangeDto = this.getContractChangeDto(id);
        
        //获取终止关联门店
        ResultData<ContractChangeStore> stopStopInfo = getContractChangeStoreById(Integer.valueOf(storeId), id);
        Map<String,Object> contractChangeStore = (Map<String, Object>)stopStopInfo.getReturnData();
        BigDecimal depositBackMoney = new BigDecimal(contractChangeStore.get("depositBackMoney").toString());
        String decorationTypeName = "-1";
        if(contractChangeStore.get("decorationType") != null) {
            decorationTypeName = SystemParam.getWebConfigValue(contractChangeStore.get("decorationType").toString());
        }
        
        // 合同ID
        int contractId = 0;
        // 合同变更ID
        int contractChangtId = 0;
        if (null != contractChangeDto) {
            contractId = contractChangeDto.getContractId();
            contractChangtId = contractChangeDto.getId();
        }

        // 2. 获取终止门店数
        int stopStoreNum = 0;
        ResultData<?> reback = this.getStopStoreNum(contractChangtId);
        stopStoreNum = (Integer) reback.getReturnData();

        // 3.获取合同信息
        ContractDto ctDto = this.getContract(contractId);
        String acCityNo = ctDto.getAcCityNo();
        rspMap.put("acCityNo", acCityNo);
        Map<String, Object> templateCodeAndBusCode = getTemplateCodeAndBusCode(acCityNo);
        if(null != templateCodeAndBusCode) {
        	rspMap.put("templateCode", templateCodeAndBusCode.get("templateCode").toString());
        	rspMap.put("busCode", templateCodeAndBusCode.get("busCode").toString());
        }
        // 4.获取事业部区域
        String bussineArea = templateCodeAndBusCode.get("busCode").toString();
        //String bussineArea = this.getBussineArea(busNo, userInfo);
        
        /**
         * ########################################################
         * #################### 获取表单中的参数内容 ###################
         * ########################################################
         */
        // 编码--取合同变更编号
        String contractStopNo = contractChangeDto.getContractStopNo();
        // 报批日期
        String submitDate = DateUtil.fmtDate(new Date(), YYYYMMDD_FORMAT);
        // 业绩归属拓展人员姓名
        String ownName = ctDto.getExpandingPersonnel();
        // 业绩归属拓展人员工号
        String ownId = ctDto.getExpandingPersonnelId();
        // 协议书编号
        String agreementNo = ctDto.getAgreementNo();
        //获取合同协议书类型
       // Integer agreementType = ctDto.getAgreementType();
        
        

        // ----------- 获取合作协议书类型 和 合作模式 部分 start----------- //
        // 合作协议书类型Code
        Integer contractTp = ctDto.getContractType();
        // 合作协议书类型Name
        String contractType = null;
        // 合作模式
        String coopType = null;
        if (DictionaryConstants.CONTRACT_TYPE_A == contractTp) {
            contractType = SystemParam.getWebConfigValue("oaContractChangeTypeA");
            coopType = SystemParam.getWebConfigValue("oaCoopTypeA");
        } else if (DictionaryConstants.CONTRACT_TYPE_B == contractTp) {
            contractType = SystemParam.getWebConfigValue("oaContractChangeTypeB");
            coopType = SystemParam.getWebConfigValue("oaCoopTypeB");
        } else if (DictionaryConstants.CONTRACT_TYPE_A_2_B == contractTp) {
            contractType = SystemParam.getWebConfigValue("oaContractChangeTypeA");
            coopType = SystemParam.getWebConfigValue("oaCoopTypeA2B");
        } else if (DictionaryConstants.CONTRACT_TYPE_B_2_A == contractTp) {
            contractType = SystemParam.getWebConfigValue("oaContractChangeTypeA");
            coopType = SystemParam.getWebConfigValue("oaCoopTypeB2A");
        } else if (DictionaryConstants.CONTRACT_TYPE_AWARDING == contractTp)  {
        	contractType = SystemParam.getWebConfigValue("oaRenewCooperationNewAwarding");
        	coopType = SystemParam.getWebConfigValue("oaCoopTypeAwarding");
        }else {
            contractType = SystemParam.getWebConfigValue("oaContractChangeTypeA");
            coopType = SystemParam.getWebConfigValue("oaCoopTypeA");
        }
        // ----------- 获取合作协议书类型 和 合作模式 部分 end----------- //

        // 中介全称
        String partyB = ctDto.getPartyB();
        // 合作期限
        String cooperationPeriod =
                DateUtil.fmtDate(ctDto.getDateLifeStart(), YYYYMMDD_FORMAT) + "至"
                        + DateUtil.fmtDate(ctDto.getDateLifeEnd(), YYYYMMDD_FORMAT);
        // 合作门店数
        Integer storeNum = ctDto.getStoreNum();
        // 已收保证金数额
        BigDecimal receivedAmount = new BigDecimal(0);
        receivedAmount = new BigDecimal(contractChangeStore.get("receivedAmount").toString());
        // 经办人工号
        String operatorId = userInfo.getUserCode();
        // 经办人
        String operatorName = userInfo.getUserName();
        //公司编码
        String configValue = SystemParam.getWebConfigValue(bussineArea);
        String[] values = configValue.split("\\|");
        String companyNo = values[0];
        //公司名称
        String companyName = values[1];
        rspMap.put("companyNo",companyNo);
        rspMap.put("companyName",companyName);
        // 流程结束时间
        String flowEndDate = "";
        // CRM单据号--取合同编号
        String crmNo = ctDto.getContractNo();
        // 终止原因
        String stopReason = contractChangeDto.getStopReason();
        // 装修公司
        String decorateCompany = "";
        if(contractChangeStore.get("decorateCompany") != null) {
            decorateCompany = contractChangeStore.get("decorateCompany").toString();
        }
        // 装修费用总金额
        BigDecimal decorateAmount = new BigDecimal(0);
        decorateAmount = new BigDecimal(contractChangeStore.get("decorateAmount").toString());
        // 已支付
//        BigDecimal payAmount = new BigDecimal(0);
//        payAmount = contractChangeDto.getPayAmount();

        // 合作终止时间
        String stopDate = DateUtil.fmtDate(contractChangeDto.getStopDate(), YYYYMMDD_FORMAT);
        // 终止方案简述
        String stopDescribe = contractChangeDto.getStopDescribe();
        // 备注
        String remarks = contractChangeDto.getRemarks();

        // ============= 数据字典下拉框内容 部分 start  ============= //
        
        // 终止类型
        String stopTypeStr = SystemParam.getWebConfigValue(contractChangeDto.getStopType() + "");
        // 三方装修合同情况
        //String decorateCNTSituate = SystemParam.getWebConfigValue(contractChangeDto.getDecorateCNTSituate() + "");
        // 装修情况
        String decorateSituate = "-1";
        if(contractChangeStore.get("decorateSituate") != null) {
            decorateSituate = SystemParam.getWebConfigValue(contractChangeStore.get("decorateSituate").toString());
        }
        // 翻牌模式
        //String flopMode = SystemParam.getWebConfigValue(contractChangeDto.getFlopMode() + "");
        // 是否B转A
        //String aConvertBFlag = SystemParam.getWebConfigValue(contractChangeDto.getIsaTob() + "");
        // 是否一并解除
        String isReleaseFlag = SystemParam.getWebConfigValue(contractChangeDto.getIsReleaseFlag() + "");
        //保证金处理方式
        String depositBalance = SystemParam.getDicValueByDicCode(contractChangeStore.get("depositBalance").toString());
        // ============= 数据字典下拉框内容 部分 end  ============= //

        // ================ 上传的附件 部分 start =============== //
        //附件集合汇总
        List<Long> attachList = new ArrayList<Long>();
        

        // 终止合作协议书
        this.getOaAttachment(attachList, contractChangeDto.getId(), FileTypeEnum.ZZXYS.getCode(), userInfo,"0");
        // 三方解约协议
        this.getOaAttachment(attachList, contractChangeDto.getId(), FileTypeEnum.SFJYXY.getCode(), userInfo,"0");
        // 保证金收据
        this.getOaAttachment(attachList, contractChangeDto.getId(), FileTypeEnum.BZJSJ.getCode(), userInfo,"0");
        // 已付装修款退还证明
        this.getOaAttachment(attachList, contractChangeDto.getId(), FileTypeEnum.YFTHZM.getCode(), userInfo,"0");
        // 注销证明
        this.getOaAttachment(attachList, contractChangeDto.getId(), FileTypeEnum.ZXZM.getCode(), userInfo,"0");
        // 照片
        //this.getOaAttachment(attachList, contractChangeDto.getId(), FileTypeEnum.ZP.getCode(), userInfo);
        // 门店照片
        this.getOaAttachment(attachList, contractChangeDto.getId(), FileTypeEnum.MDZPZTW.getCode(), userInfo,"0");
        // 新A合作协议书
       // this.getOaAttachment(attachList, contractChangeDto.getId(), FileTypeEnum.XAHZXYS.getCode(), userInfo);
        // 一事一议终止方案
       // this.getOaAttachment(attachList, contractChangeDto.getId(), FileTypeEnum.ZZFA.getCode(), userInfo);
        // 重要提示函
       // this.getOaAttachment(attachList, contractChangeDto.getId(), FileTypeEnum.ZYTSH.getCode(), userInfo);
        // 其他
        this.getOaAttachment(attachList, contractChangeDto.getId(), FileTypeEnum.QT.getCode(), userInfo,"0");
        // ================ 上传的附件 部分 end =============== //

        dateHtml +=
                "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<formExport version=\"2.0\">"
                        + "<summary id=\"-870556978151276052\" name=\"formmain_3736\"/>" + "<definitions/>" + "<values>"
                        + "<column name=\"编码\"><value>"
                        + XmlTransferUtil.transfer(contractStopNo)
                        + "</value></column>"
                        + "<column name=\"报批日期\"><value>"
                        + XmlTransferUtil.transfer(submitDate)
                        + "</value></column>"
                        + "<column name=\"事业部区域\"><value>"
                        + XmlTransferUtil.transfer(bussineArea)
                        + "</value></column>"
                        + "<column name=\"业绩归属拓展人员姓名\"><value>"
                        + XmlTransferUtil.transfer(ownName)
                        + "</value></column>"
                        + "<column name=\"业绩归属拓展人员工号\"><value>"
                        + XmlTransferUtil.transfer(ownId)
                        + "</value></column>"
                        + "<column name=\"协议书编号\"><value>"
                        + XmlTransferUtil.transfer(agreementNo)
                        + "</value></column>"
                        + "<column name=\"合作协议书类型\"><value>"
                        + contractType
                        //+ ""
                        + "</value></column>"
                        + "<column name=\"中介全称\"><value>"
                        + XmlTransferUtil.transfer(partyB)
                        + "</value></column>"
                        + "<column name=\"合作模式\"><value>"
                        + XmlTransferUtil.transfer(coopType)
                        + "</value></column>"
                        + "<column name=\"合作期限\"><value>"
                        + XmlTransferUtil.transfer(cooperationPeriod)
                        + "</value></column>"
                        + "<column name=\"合作门店数\"><value>"
                        + storeNum
                        + "</value></column>"
                        + "<column name=\"已收保证金\"><value>"
                        + receivedAmount
                        + "</value></column>"
                        + "<column name=\"经办人工号\"><value>"
                        + XmlTransferUtil.transfer(operatorId)
                        + "</value></column>"
                        + "<column name=\"经办人\"><value>"
                        + XmlTransferUtil.transfer(operatorName)
                        + "</value></column>"
                        + "<column name=\"公司名称\"><value>"
                        + XmlTransferUtil.transfer(companyName)
                        + "</value></column>"
                        + "<column name=\"公司编码\"><value>"
                        + XmlTransferUtil.transfer(companyNo)
                        + "</value></column>"
                        + "<column name=\"终止合作协议书\"><value>"
                        // + oaFile1Xml
                        + "-1"
                        + "</value></column>"
                        + "<column name=\"保证金收据\"><value>"
                        // + oaFile3Xml
                        + "-1"
                        + "</value></column>"
                        + "<column name=\"已付装修款退还证明\"><value>"
                        // + oaFile4Xml
                        + "-1"
                        + "</value></column>"
                        + "<column name=\"注销单\"><value>"
                        // + oaFile5Xml
                        + "-1"
                        + "</value></column>"
                        + "<column name=\"其他\"><value>"
                        //  + oaFile9Xml
                        + "-1"
                        + "</value></column>"
                        + "<column name=\"流程结束时间\"><value>"
                        + XmlTransferUtil.transfer(flowEndDate)
                        + "</value></column>"
                        + "<column name=\"CRM单据号\"><value>"
                        + XmlTransferUtil.transfer(crmNo)
                        + "</value></column>"
                        + "<column name=\"终止类型\"><value>"
                        + XmlTransferUtil.transfer(stopTypeStr)
                        + "</value></column>"
                        + "<column name=\"终止原因\"><value>"
                        + XmlTransferUtil.transfer(stopReason)
                        + "</value></column>"
                        + "<column name=\"终止门店数\"><value>"
                        + stopStoreNum
                        + "</value></column>"
                        /*+ "<column name=\"三方装修合同情况\"><value>"
                        + decorateCNTSituate
                        + "</value></column>"*/
                        + "<column name=\"装修情况\"><value>"
                        + XmlTransferUtil.transfer(decorateSituate)
                        + "</value></column>"
                        + "<column name=\"装修公司\"><value>"
                        + XmlTransferUtil.transfer(decorateCompany)
                        + "</value></column>"
                        /*+ "<column name=\"翻牌模式\"><value>"
                        + flopMode
                        + "</value></column>"*/
                        + "<column name=\"我司承担的装修费用\"><value>"
                        + String.valueOf(decorateAmount)
                        + "</value></column>"
                        /*+ "<column name=\"已支付\"><value>"
                        + String.valueOf(payAmount)
                        + "</value></column>"*/
                        + "<column name=\"是否一并解除\"><value>"
                        + XmlTransferUtil.transfer(isReleaseFlag)
                        + "</value></column>"
                        + "<column name=\"合作终止时间\"><value>"
                        + XmlTransferUtil.transfer(stopDate)
                        + "</value></column>"
                        
                        //新增字段
                        + "<column name=\"装修类型\"><value>"
                        + XmlTransferUtil.transfer(decorationTypeName)
                        + "</value></column>"
                        + "<column name=\"保证金处理方式\"><value>"
                        + XmlTransferUtil.transfer(depositBalance)
                        + "</value></column>"
                        + "<column name=\"保证金退还金额\"><value>"
                        + depositBackMoney
                        + "</value></column>"
                        
                        //停用字段，不能删除
                        + "<column name=\"停-三方装修合同情况\"><value>"
                        + "-1"
                        + "</value></column>"
                        + "<column name=\"停-是否B转A\"><value>"
                        + "-1"
                        + "</value></column>"
                        + "<column name=\"停-新A合作协议书\"><value>"
                        + "-1"
                        + "</value></column>"
                        + "<column name=\"停-一事一议终止方案\"><value>"
                        + "-1"
                        + "</value></column>"
                        
                        
                        + "<column name=\"终止合作协议书\"><value>"
                        // + oaFile2Xml
                        + "-1"
                        + "</value></column>"
                        + "<column name=\"合同权利义务转让三方协议\"><value>"
                        // + oaFile6Xml
                        + "-1"
                        + "</value></column>"
                        + "<column name=\"门店照片\"><value>"
                        // + oaFile8Xml
                        + "-1"
                        + "</value></column>"
                        + "<column name=\"终止方案简述\"><value>"
                        + XmlTransferUtil.transfer(stopDescribe)
                        + "</value></column>"
                        + "<column name=\"备注\"><value>"
                        + XmlTransferUtil.transfer(remarks)
                        + "</value></column>"
                        + "</values>"
                        + "<subForms>"
                        + "<subForm>"
                        + "<definitions>"
                        + addressTitleXml
                        + "</definitions>"
                        + "<values>"
                        + addressDetailXml
                        + "</values>" + "</subForm>" + "</subForms>" + "</formExport>";
        //附件数据
        rspMap.put(ATTACH_KEY, attachList.toArray());
        //表单数据
        rspMap.put(XML_DATA_KEY, dateHtml.toString());
        logger.info("CRM 提交审核 dateHtml=", dateHtml);
        return rspMap;
    }

    /**
     * 根据合同变更ID获取变更申请信息
     *
     * @param contractChangtId 合同变更ID
     */
    public ContractChangeDto getContractChangeDto(int contractChangtId) {

        ResultData<?> resultData = new ResultData<>();
        ContractChangeDto contractChangeDto = new ContractChangeDto();
        try {
            // 根据合同变更ID查询变更申请信息
            resultData = queryById(contractChangtId);

            Map<?, ?> mapTemp = (Map<?, ?>) resultData.getReturnData();

            if (null != mapTemp) {
                contractChangeDto = MapToEntityConvertUtil.convert(mapTemp, ContractChangeDto.class, "");
                // 装修费用总金额
                BigDecimal decorateAmount = new BigDecimal(0);
                if (mapTemp.containsKey("decorateAmount") && mapTemp.get("decorateAmount") != null) {
                    // 装修费用总金额
                    decorateAmount = new BigDecimal(mapTemp.get("decorateAmount").toString());
                }
                // 已支付金额
//                BigDecimal payAmount = new BigDecimal((Integer)mapTemp.get("payAmount"));
                BigDecimal payAmount = new BigDecimal(0);
                if (mapTemp.containsKey("payAmount") && mapTemp.get("payAmount") != null) {
                    // 装修费用总金额
                    payAmount = new BigDecimal(mapTemp.get("payAmount").toString());
                }
                // 已收保证金额数
//                BigDecimal receivedAmount = new BigDecimal((String)mapTemp.get("receivedAmount"));
                BigDecimal receivedAmount = new BigDecimal(0);
                if (mapTemp.containsKey("receivedAmount") && mapTemp.get("receivedAmount") != null) {
                    receivedAmount = new BigDecimal(mapTemp.get("receivedAmount").toString());
                }

                contractChangeDto.setDecorateAmount(decorateAmount);
                contractChangeDto.setPayAmount(payAmount);
                contractChangeDto.setReceivedAmount(receivedAmount);
            }

        } catch (Exception e) {
            logger.error("contract",
                    "ContractChangeOAController",
                    "getContractChange",
                    "",
                    null,
                    "",
                    "合同变更信息查询失败！",
                    e);
        }

        return contractChangeDto;
    }

    /**
     * 根据合同ID获取合同信息
     *
     * @param contractId 合同Id
     * @return
     */
    private ContractDto getContract(int contractId) {

        ResultData<?> resultData = new ResultData<>();

        Map<?, ?> mapTempp = new HashMap<String, Object>();

        // 合同基础信息
        ContractDto ctDto = new ContractDto();

        try {
            // 根据合同ID获取合同信息
            resultData = contractService.getById(contractId);

            Map<?, ?> mapTemp = (Map<?, ?>) resultData.getReturnData();

            if (null != mapTemp) {

                mapTempp = (Map<?, ?>) mapTemp.get("contract");
                int depositFeeState = 0;
                int depositFee = 0;
                if (mapTempp.containsKey("depositFeeState") && mapTempp.get("depositFeeState") != null) {
                    depositFeeState = Integer.valueOf(mapTempp.get("depositFeeState").toString());
                }
                if (mapTempp.containsKey("depositFee") && mapTempp.get("depositFee") != null) {
                    depositFee = Integer.valueOf(mapTempp.get("depositFee").toString());
                }
                ctDto = MapToEntityConvertUtil.convert(mapTempp, ContractDto.class, "");

                if (depositFeeState == 17503) {
                    BigDecimal recevieAcount = new BigDecimal(Integer.toString(depositFee));
                    ctDto.setDepositFee(recevieAcount);
                }
            }

        } catch (Exception e) {
            logger.error("contract",
                    "ContractChangeOAController",
                    "getContract",
                    "",
                    null,
                    "",
                    "合同信息查询失败！",
                    e);
        }

        return ctDto;
    }

    /**
     * 获取事业部区域
     *
     * @return
     */
    private String getBussineArea(String busNo, UserInfo userInfo) {
        // 事业部区域
        String bussineArea = null;
        // 查询是否是经办人
        ResultData<?> backResult = new ResultData<>();
        try {
            backResult = oaOperatorService.getByUserCode(userInfo.getUserCode());
        } catch (Exception e) {
            logger.error("contract", "ContractChangeOAController", "getBussineArea", "", null, "", "", e);
        }

        Map<?, ?> mapTemp = (Map<?, ?>) backResult.getReturnData();

        if (null != mapTemp) {
            OaOperatorDto oaOperatorDto = MapToEntityConvertUtil.convert(mapTemp, OaOperatorDto.class, "");

            // （取经办人的事业部区域，如果是跨区的，取其选择的区域）
            // 暂时不考虑跨区的情况，后期有需求再改
            Boolean isCombine = oaOperatorDto.getIsCombine();
            if (isCombine) {
                bussineArea = busNo;
            } else {
                bussineArea = oaOperatorDto.getBusCode();
            }
        }
        return bussineArea;
    }

    /**
     * 组装表单发起数据[乙转甲变更报批单]
     *
     * @param id     合同变更ID
     * @param reqMap 返回提交OA的组装信息
     */
    private Map<String, Object> setToOaInfoBToA(Integer id, Map<String, Object> reqMap, String busNo, Integer contractId, UserInfo userInfo) throws Exception {

        // 合同变更OA模板
        //String oaTemplateCode = SystemParam.getWebConfigValue("oaTemplateCode_ctrctChg_btoa");
        String oaTemplateCode = getOATemplateCode(contractId, "contractChgTpl");

        // 模板编号
        reqMap.put("templateCode", oaTemplateCode);

        // 发起者的登录名（登录协同的登录名）
        String senderLoginName = userInfo.getUserCode();
        reqMap.put("senderLoginName", senderLoginName);

        // 协同的标题
        reqMap.put("subject", "合同变更报批单");

        // 组装向OA发送的参数信息
        Map<String, Object> rspMap = this.setOaAuditDataBToA(id, busNo, userInfo);

        // 附件，Long型数组，值为附件的Id。
        reqMap.put("attachments", rspMap.get(ATTACH_KEY));

        // HTML正文流程为html内容；表单流程为XML格式的表单数据
        reqMap.put("data", rspMap.get(XML_DATA_KEY));

        // 为控制是否流程发送。0：缺省值，发送，进入下一节点的待办（如果需要选人则保存到待发）1：不发送，保存到待发。
        reqMap.put("state", "0");

        return rspMap;
    }

    /**
     * 组装向OA发送的参数信息
     *
     * @param id 合同变更ID
     * @return
     */
    private Map<String, Object> setOaAuditDataBToA(Integer id, String busNo, UserInfo userInfo) throws Exception {
        // 返回表单数据、返回附件数据
        Map<String, Object> rspMap = new HashMap<String, Object>();
        String dateHtml = "";
        // 1. 获取变更申请信息
        ContractChangeDto contractChangeDto = this.getContractChangeDto(id);
        // 合同ID
        int contractId = contractChangeDto.getContractId();
        // 3.获取合同信息
        ContractDto ctDto = this.getContract(contractId);
        // 4.获取事业部区域
        String bussineArea = this.getBussineArea(busNo, userInfo);
        // 合作门店地址
        String addressDetail = "";
        try {
            ResultData<?> resultData = this.getChgStoreAddr(id);
            Object addressObj = resultData.getReturnData();
            if (null != addressObj) {
                addressDetail = addressObj.toString();
            }
        } catch (Exception e) {
            logger.error("contract", "ContractChangeOAController", "getChgStoreAddr", "", userInfo.getUserId(), "", "查询变更门店地址！", e);
        }

        /**
         * ########################################################
         * #################### 获取表单中的参数内容 ###################
         * ########################################################
         */
        // 编码--取合同变更编号
        String contractStopNo = contractChangeDto.getContractStopNo();
        // 报批日期
        String submitDate = DateUtil.fmtDate(new Date(), YYYYMMDD_FORMAT);
        //公司编码
        String configValue = SystemParam.getWebConfigValue(bussineArea);
        String[] values = configValue.split("\\|");
        String companyNo = values[0];
        //公司名称
        String companyName = values[1];

        rspMap.put("companyNo",companyNo);
        rspMap.put("companyName",companyName);

        // 业绩归属拓展人员姓名
        String ownName = ctDto.getExpandingPersonnel();
        // 业绩归属拓展人员工号
        String ownId = ctDto.getExpandingPersonnelId();
        // 经办人工号
        String operatorId = userInfo.getUserCode();
        // 经办人
        String operatorName = userInfo.getUserName();
        // 公司经营范围变更
        Integer changeCompanyName = contractChangeDto.getChangeCompanyName() == null ? 0:contractChangeDto.getChangeCompanyName();
        // 公司注册地址变更
        Integer changeCompanyAdress = contractChangeDto.getChangeCompanyAdress() == null ? 0:contractChangeDto.getChangeCompanyAdress();
        // 门店地址变更
        Integer changeStoreAdress = contractChangeDto.getChangeStoreAdress() == null ? 0:contractChangeDto.getChangeStoreAdress();
        // 签约主体变更
        Integer changeCompany = contractChangeDto.getChangeCompany() == null ? 0:contractChangeDto.getChangeCompany();

        if (changeStoreAdress != null && changeStoreAdress == 1) {
            addressDetail = contractChangeDto.getChangeStoreCityName() + contractChangeDto.getChangeStoreDistrictName() + contractChangeDto.getStoreAdresss();
        }

        String cooperationPeriod;  // 合作期限
        String partyB;  // 中介全称
        String lealPerson;  // 法定代表人
        String companyAdresss; // 执照注册地址
        String agreementNo;    // 新签协议书编号
        if (contractChangeDto.getChangeType().intValue() == 17003 || (changeCompany != null && changeCompany == 1)) {

            partyB = contractChangeDto.getNewCompanyName();
            lealPerson = contractChangeDto.getNewLegalPerson();

            companyAdresss = contractChangeDto.getNewCompanyAddressCityName() + contractChangeDto.getNewCompanyAddressDistrictName() + contractChangeDto.getNewCompanyAddress();

            agreementNo = contractChangeDto.getNewAgreementNo();

            cooperationPeriod =
                    DateUtil.fmtDate(contractChangeDto.getNewDateLifeStart(), YYYYMMDD_FORMAT) + "至"
                            + DateUtil.fmtDate(contractChangeDto.getNewDateLifeEnd(), YYYYMMDD_FORMAT);
        } else {
            if (changeCompanyAdress != null && changeCompanyAdress == 1) {
                companyAdresss = contractChangeDto.getChangeCompanyCityName() + contractChangeDto.getChangeCompanyDistrictName() + contractChangeDto.getCompanyAdresss();
            } else {
                companyAdresss = ctDto.getPartyAddressDetail();
            }
            partyB = ctDto.getPartyB();
            lealPerson = ctDto.getLealPerson();

            agreementNo = ctDto.getAgreementNo();
            cooperationPeriod =
                    DateUtil.fmtDate(ctDto.getDateLifeStart(), YYYYMMDD_FORMAT) + "至"
                            + DateUtil.fmtDate(ctDto.getDateLifeEnd(), YYYYMMDD_FORMAT);
        }
        // 保证金金额
        BigDecimal receivedAmount = new BigDecimal(0);
        BigDecimal receivedAmountDb = ctDto.getDepositFee();
        if (null != receivedAmountDb) {
            receivedAmount = receivedAmountDb;
        }

        String changeTypeNm = "";
        if(contractChangeDto.getChangeType().intValue() == 17002){
            changeTypeNm = "乙类转甲类";
        }else if(contractChangeDto.getChangeType().intValue() == 17003){
            changeTypeNm = "三方变更";
        }

        String threePartChangeTypeNm="";
        if(contractChangeDto.getThreePartChangeType() != null){
            threePartChangeTypeNm = SystemParam.getDicValueByDicCode(contractChangeDto.getThreePartChangeType().toString());
        }

        // 流程结束时间
        String flowEndDate = "";
        // CRM单据号--取合同编号
        String crmNo = ctDto.getContractNo();

        // ================ 上传的附件 部分 start =============== //
        dateHtml +=
                "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<formExport version=\"2.0\">"
                        + "<summary id=\"6984485710218611336\" name=\"formmain_3884\"/>" + "<definitions/>" + "<values>"
                        + "<column name=\"编号\"><value>"
                        + XmlTransferUtil.transfer(contractStopNo)
                        + "</value></column>"
                        + "<column name=\"报批日期\"><value>"
                        + XmlTransferUtil.transfer(submitDate)
                        + "</value></column>"
                        + "<column name=\"核算主体名称\"><value>"
                        + companyName
                        + "</value></column>"
                        + "<column name=\"核算主体编码\"><value>"
                        + XmlTransferUtil.transfer(companyNo)
                        + "</value></column>"
                        + "<column name=\"区域\"><value>"
                        + XmlTransferUtil.transfer(bussineArea)
                        + "</value></column>"
                        + "<column name=\"业绩归属人员\"><value>"
                        + XmlTransferUtil.transfer(ownName)
                        + "</value></column>"
                        + "<column name=\"归属人员工号\"><value>"
                        + XmlTransferUtil.transfer(ownId)
                        + "</value></column>"
                        + "<column name=\"经办人\"><value>"
                        + XmlTransferUtil.transfer(operatorName)
                        + "</value></column>"
                        + "<column name=\"经办人工号\"><value>"
                        + XmlTransferUtil.transfer(operatorId)
                        + "</value></column>"
                        + "<column name=\"合同变更类型\"><value>"
                        + changeTypeNm
                        + "</value></column>"
                        + "<column name=\"公司经营范围变更\"><value>"
                        + changeCompanyName
                        + "</value></column>"
                        + "<column name=\"公司注册地址变更\"><value>"
                        + changeCompanyAdress
                        + "</value></column>"
                        + "<column name=\"门店地址变更\"><value>"
                        + changeStoreAdress
                        + "</value></column>"
                        + "<column name=\"签约主体变更\"><value>"
                        + changeCompany
                        + "</value></column>"
                        + "<column name=\"变更内容三方变更\"><value>"
                        + threePartChangeTypeNm
                        + "</value></column>"
                        + "<column name=\"中介全称\"><value>"
                        + XmlTransferUtil.transfer(partyB)
                        + "</value></column>"
                        + "<column name=\"法定代表人\"><value>"
                        + XmlTransferUtil.transfer(lealPerson)
                        + "</value></column>"
                        + "<column name=\"执照注册地址\"><value>"
                        + XmlTransferUtil.transfer(companyAdresss)
                        + "</value></column>"
                        + "<column name=\"新签协议书编号\"><value>"
                        + XmlTransferUtil.transfer(agreementNo)
                        + "</value></column>"
                        + "<column name=\"合作期限\"><value>"
                        + XmlTransferUtil.transfer(cooperationPeriod)
                        + "</value></column>"
                        + "<column name=\"保证金金额\"><value>"
                        + receivedAmount
                        + "</value></column>"
                        + "<column name=\"门店经营地址\"><value>"
                        + XmlTransferUtil.transfer(addressDetail)
                        + "</value></column>"
                        + "<column name=\"营业执照注册号\"><value>"
                        +  XmlTransferUtil.transfer(contractChangeDto.getRegistrId())
                        + "</value></column>"
                        + "<column name=\"备注\"><value>"
                        + XmlTransferUtil.transfer(contractChangeDto.getRemarks())
                        + "</value></column>";
       
        //附件集合汇总
        List<Long> attachList = new ArrayList<>();
        // 签约主体变更
       if(contractChangeDto.getChangeType().intValue() == 17002){
        if (changeCompany != null && changeCompany == 1 ) {
            // 转让三方权利(主体变更)
            this.getOaAttachment(attachList, contractChangeDto.getId(), FileTypeEnum.ZRSFQLZT.getCode(), userInfo,"0");
            // 新签协议书(主体变更)
            this.getOaAttachment(attachList, contractChangeDto.getId(), FileTypeEnum.XQXYSCLZT.getCode(), userInfo,"0");
            // 新营业执照(主体变更)
            this.getOaAttachment(attachList, contractChangeDto.getId(), FileTypeEnum.XYYZZZTW.getCode(), userInfo,"0");
            // 法人身份证(主体变更)
            this.getOaAttachment(attachList, contractChangeDto.getId(), FileTypeEnum.FRSFZ.getCode(), userInfo,"0");
            // 国家信息公示(主体变更)
            this.getOaAttachment(attachList, contractChangeDto.getId(), FileTypeEnum.GJXXGSZT.getCode(), userInfo,"0");
            // 房友确认单(主体变更)
            this.getOaAttachment(attachList, contractChangeDto.getId(), FileTypeEnum.FYQRDZTW.getCode(), userInfo,"0");
            // 门店照片(主体变更)
            this.getOaAttachment(attachList, contractChangeDto.getId(), FileTypeEnum.MDZPZTW.getCode(), userInfo,"0");
            //重要提示函
            this.getOaAttachment(attachList, contractChangeDto.getId(), FileTypeEnum.ZYTSH.getCode(), userInfo,"0");
            // 其他(主体变更)
            this.getOaAttachment(attachList, contractChangeDto.getId(), FileTypeEnum.QTZTWZT.getCode(), userInfo,"0");

            dateHtml +=
                    "<column name=\"转让三方权利\"><value>"
                            // + oaFile16Xml
                            + "-1"
                            + "</value></column>"
                            + "<column name=\"新签协议书及材料\"><value>"
                            //  + oaFile17Xml
                            + "-1"
                            + "</value></column>"
                            + "<column name=\"国家信息公示\"><value>"
                            // + oaFile18Xml
                            + "-1"
                            + "</value></column>"
                            + "<column name=\"其它\"><value>"
                            // + oaFile19Xml
                            + "-1"
                            + "</value></column>";
        }
        // 公司经营范围变更,公司注册地址变更,门店地址变更
        else if ((changeCompanyName != null && changeCompanyName == 1)
                || (changeCompanyAdress != null && changeCompanyAdress == 1)
                || (changeStoreAdress != null && changeStoreAdress == 1)) {
            // 变更补充条款
            this.getOaAttachment(attachList, contractChangeDto.getId(), FileTypeEnum.BGBCTKZTW.getCode(), userInfo,"0");
            // 新营业执照
            this.getOaAttachment(attachList, contractChangeDto.getId(), FileTypeEnum.XYYZZZTW.getCode(), userInfo,"0");
            // 国家信息公示
            this.getOaAttachment(attachList, contractChangeDto.getId(), FileTypeEnum.GJXXGSZTW.getCode(), userInfo,"0");
            // 房友确认单
            this.getOaAttachment(attachList, contractChangeDto.getId(), FileTypeEnum.FYQRDZTW.getCode(), userInfo,"0");
            // 门店照片
            this.getOaAttachment(attachList, contractChangeDto.getId(), FileTypeEnum.MDZPZTW.getCode(), userInfo,"0");
            // 其他
            this.getOaAttachment(attachList, contractChangeDto.getId(), FileTypeEnum.QTZTW.getCode(), userInfo,"0");

            dateHtml +=
                    "<column name=\"变更补充条款\"><value>"
                            //+ oaFile10Xml
                            + "-1"
                            + "</value></column>"
                            + "<column name=\"新营业执照\"><value>"
                            //+ oaFile11Xml
                            + "-1"
                            + "</value></column>"
                            + "<column name=\"国家信息公示\"><value>"
                            //+ oaFile12Xml
                            + "-1"
                            + "</value></column>"
                            + "<column name=\"房友确认单\"><value>"
                            //+ oaFile13Xml
                            + "-1"
                            + "</value></column>"
                            + "<column name=\"门店照片\"><value>"
                            //+ oaFile14Xml
                            + "-1"
                            + "</value></column>"
                            + "<column name=\"其它\"><value>"
                            //+ oaFile15Xml
                            + "-1"
                            + "</value></column>";
        }
       }else if(contractChangeDto.getChangeType().intValue() == 17003){
            //三方变更图片
            // 受让方营业执照
            this.getOaAttachment(attachList, contractChangeDto.getId(), "1033", userInfo,"1");
            // 国家企业信用信息公示系统网站截图
            this.getOaAttachment(attachList, contractChangeDto.getId(), "1034", userInfo,"1");
            // 受让方法人身份证
            this.getOaAttachment(attachList, contractChangeDto.getId(), "1035", userInfo,"1");
            // 三方变更协议
            this.getOaAttachment(attachList, contractChangeDto.getId(), "1036", userInfo,"1");
            // 转让方保证金收据
            this.getOaAttachment(attachList, contractChangeDto.getId(), "1037", userInfo,"1");
            // 其他
            this.getOaAttachment(attachList, contractChangeDto.getId(), "1038", userInfo,"1");

            dateHtml +=
                    "<column name=\"转让三方权利\"><value>"
                            // + oaFile16Xml
                            + "-1"
                            + "</value></column>"
                            + "<column name=\"新签协议书及材料\"><value>"
                            //  + oaFile17Xml
                            + "-1"
                            + "</value></column>"
                            + "<column name=\"国家信息公示\"><value>"
                            // + oaFile18Xml
                            + "-1"
                            + "</value></column>"
                            + "<column name=\"其它\"><value>"
                            // + oaFile19Xml
                            + "-1"
                            + "</value></column>";
        }

        dateHtml +=
                "<column name=\"流程结束时间\"><value>"
                        + XmlTransferUtil.transfer(flowEndDate)
                        + "</value></column>"
                        + "<column name=\"CRM合同编号\"><value>"
                        + XmlTransferUtil.transfer(crmNo)
                        + "</value></column>"
                        + "</values>"
                        + "</formExport>";
        // ================ 上传的附件 部分 end =============== //

        //附件数据
        rspMap.put(ATTACH_KEY, attachList.toArray());
        //表单数据
        rspMap.put(XML_DATA_KEY, dateHtml.toString());

        logger.info("CRM 提交审核 dateHtml=", dateHtml);

        return rspMap;
    }

    /**
     * 根据contractStopId和fileTypeCode查询附件关联信息
     *
     * @param attachList     附件集合汇总
     * @param contractStopId 合同变更ID
     * @param fileTypeCode   文件类型 (9种类型)
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void getOaAttachment(List<Long> attachList, int contractStopId, String fileTypeCode, UserInfo userInfo,String flag) throws Exception {
        StringBuilder oaFile = new StringBuilder();
        // 文件Code
        String fileCode = null;
        String sbFileCode = null;
        List<OaAttachmentDto> dtoList = new ArrayList<OaAttachmentDto>();
        try {
            Map<String, Object> param = new HashMap<>();
            String attachStrs = "";
            param.put("refId", contractStopId);
            param.put("fileTypeId", Integer.valueOf(fileTypeCode));
            ResultData<?> backResult;
            if("1".equals(flag)){
                backResult = fileService.getContractChangeNewFile(param);
            }else{
                backResult = fileService.getContractChangeFile(param);
            }

            if (null != backResult && "200".equals(backResult.getReturnCode())) {
                List<Map> filesRel = (List<Map>) backResult.getReturnData();
                if (null != filesRel && filesRel.size() > 0) {
                    for (Map map : filesRel) {
                        String fileUrl = map.get("fileUrl").toString();
                        
                        String fileFullName = map.get("fileFullName").toString();
                        String filePrefixName = this.spellFileTypeName(fileTypeCode + "");
                        String fileName = new StringBuffer(filePrefixName).append(fileFullName).toString();
                       
                        //临时修改
                        String attachmentId = oaService.oaAttachmentUpload(fileUrl, fileName, userInfo.getUserCode(),"");
                        if (StringUtil.isNotEmpty(attachmentId)) {
                            // 信息拼接
                            sbFileCode = "<value>" + attachmentId + "</value>";
                            oaFile.append(sbFileCode);

                            attachList.add(Long.valueOf(attachmentId));
                            //// TODO: 2017/10/17 还要不要存表了
                            if (StringUtil.isEmpty(attachStrs)) {
                                attachStrs = attachmentId;
                            } else {
                                attachStrs = attachStrs + "|" + attachmentId;
                            }
                        }
                    }
                }
            } else {//有可能是老数据 还用老方法
                // 查询操作
                ResultData<?> resultData = this.getOaAttachment(contractStopId, fileTypeCode);
                dtoList = (List<OaAttachmentDto>) resultData.getReturnData();
                Map<String, Object> map = new HashMap<String, Object>();
                // 非空判断
                if (null != dtoList && !dtoList.isEmpty()) {
                    for (int i = 0; i < dtoList.size(); i++) {
                        map = (Map) dtoList.get(i);
                        fileCode = (String) map.get("fileCode");
                        // 汇总附件
                        setAttachList(attachList, fileCode);
                        // 信息拼接
                        sbFileCode = "<value>" + fileCode + "</value>";
                        oaFile.append(sbFileCode);
                    }
                } else {
                    fileCode = "-1";
                    // 汇总附件
                    setAttachList(attachList, fileCode);
                    // 信息拼接
                    sbFileCode = "<value>" + fileCode + "</value>";
                    oaFile.append(sbFileCode);
                }
            }

            // 【终止合作协议书】的场合
            if (fileTypeCode.equals(FileTypeEnum.ZZXYS.getCode())) {

                oaFile1Xml = oaFile.toString();

                //【三方解约协议】的场合
            } else if (fileTypeCode.equals(FileTypeEnum.SFJYXY.getCode())) {

                oaFile2Xml = oaFile.toString();

                //【保证金收据】的场合
            } else if (fileTypeCode.equals(FileTypeEnum.BZJSJ.getCode())) {

                oaFile3Xml = oaFile.toString();

                //【已付装修款退还证明】的场合
            } else if (fileTypeCode.equals(FileTypeEnum.YFTHZM.getCode())) {

                oaFile4Xml = oaFile.toString();

                //【注销证明】的场合
            } else if (fileTypeCode.equals(FileTypeEnum.ZXZM.getCode())) {

                oaFile5Xml = oaFile.toString();

                //【照片】的场合
            } else if (fileTypeCode.equals(FileTypeEnum.ZP.getCode())) {

                oaFile6Xml = oaFile.toString();

                //【新A合作协议书】的场合
            } else if (fileTypeCode.equals(FileTypeEnum.XAHZXYS.getCode())) {

                oaFile7Xml = oaFile.toString();

                //【一事一议终止方案】的场合
            } else if (fileTypeCode.equals(FileTypeEnum.YFTHZM.getCode())) {

                oaFile8Xml = oaFile.toString();

                //【其他】的场合
            } else if (fileTypeCode.equals(FileTypeEnum.QT.getCode())) {
                oaFile9Xml = oaFile.toString();
            } else if (fileTypeCode.equals(FileTypeEnum.BGBCTKZTW.getCode())) {
                // 变更补充条款
                oaFile10Xml = oaFile.toString();
            } else if (fileTypeCode.equals(FileTypeEnum.XYYZZZTW.getCode())) {
                // 新营业执照
                oaFile11Xml = oaFile.toString();
            } else if (fileTypeCode.equals(FileTypeEnum.GJXXGSZTW.getCode())) {
                // 国家信息公示
                oaFile12Xml = oaFile.toString();
            } else if (fileTypeCode.equals(FileTypeEnum.FYQRDZTW.getCode())) {
                // 房友确认单
                oaFile13Xml = oaFile.toString();
            } else if (fileTypeCode.equals(FileTypeEnum.MDZPZTW.getCode())) {
                // 门店照片
                oaFile14Xml = oaFile.toString();
            } else if (fileTypeCode.equals(FileTypeEnum.QTZTW.getCode())) {
                // 其他
                oaFile15Xml = oaFile.toString();
            } else if (fileTypeCode.equals(FileTypeEnum.ZRSFQLZT.getCode())) {
                // 转让三方权利[主体变更]
                oaFile16Xml = oaFile.toString();
            } else if (fileTypeCode.equals(FileTypeEnum.XQXYSCLZT.getCode())) {
                // 新签协议书及材料[主体变更]
                oaFile17Xml = oaFile.toString();
            } else if (fileTypeCode.equals(FileTypeEnum.GJXXGSZT.getCode())) {
                // 国家信息公示[主体变更]
                oaFile18Xml = oaFile.toString();
            } else if (fileTypeCode.equals(FileTypeEnum.QTZTWZT.getCode())) {
                // 其他[主体变更]
                oaFile19Xml = oaFile.toString();
            }

        } catch (Exception e) {
            logger.error("contract",
                    "ContractChangeOAController",
                    "getOaAttachment",
                    "",
                    UserInfoHolder.getUserId(),
                    "",
                    "上传文件Code查询失败！",
                    e);
        }
    }

    private String spellFileTypeName(String fileTypeCode) {
    	 StringBuffer sbf = new StringBuffer();
         switch (fileTypeCode) {
             case "1":
                 sbf.append("营业执照_");
                 break;
             case "2":
                 sbf.append("法人身份证_");
                 break;
             case "3":
                 sbf.append("合同照片_");
                 break;
             case "4":
                 sbf.append("门店照片_");
                 break;
             case "5":
                 sbf.append("房友确认单_");
                 break;
             case "6":
                 sbf.append("其他_");
                 break;
             case "1001":
            	 sbf.append("终止合作协议书_");
            	 break;
             case "1002":
            	 sbf.append("三方解约协议_");
            	 break;
             case "1003":
            	 sbf.append("保证金收据_");
            	 break;
             case "1004":
            	 sbf.append("已付装修款退还证明_");
            	 break;
             case "1005":
            	 sbf.append("照片_");
            	 break;
             case "1006":
            	 sbf.append("照片_");
            	 break;
             case "1007":
            	 sbf.append("新A合作协议书_");
            	 break;
             case "1008":
            	 sbf.append("一事一议终止方案_");
            	 break;
             case "1009":
            	 sbf.append("其他_");
            	 break;
             case "1010":
            	 sbf.append("变更补充条款_");
            	 break;
             case "1011":
            	 sbf.append("新营业执照_");
            	 break;
             case "1012":
            	 sbf.append("国家企业信用信息公示系统网站截图_");
            	 break;
             case "1013":
            	 sbf.append("房友确认单_");
            	 break;
             case "1014":
            	 sbf.append("门店照片_");
            	 break;
             case "1015":
            	 sbf.append("其他_");
            	 break;
             case "1016":
            	 sbf.append("权利义务转让三方协议书_");
            	 break;
             case "1017":
            	 sbf.append("新签合作协议书_");
            	 break;
             case "1018":
            	 sbf.append("国家企业信用信息公示系统网站截图_");
            	 break;
             case "1019":
            	 sbf.append("其他_");
            	 break;
             case "1020":
            	 sbf.append("重要提示函_");
            	 break;
             case "1033":
                 sbf.append("受让方营业执照_");
                 break;
             case "1034":
                 sbf.append("国家企业信用信息公示系统网站截图_");
                 break;
             case "1035":
                 sbf.append("受让方法人身份证_");
                 break;
             case "1036":
                 sbf.append("三方变更协议_");
                 break;
             case "1037":
                 sbf.append("转让方保证金收据_");
                 break;
             case "1038":
                 sbf.append("其他_");
                 break;
         }
         return sbf.toString();
	}

	/**
     * 汇总附件，把所有图片加到附件集合中，附件String转List
     *
     * @param attachList 附件集合
     * @param attachStr  单个文件
     */
    private void setAttachList(List<Long> attachList, String attachStr) {
        if (StringUtil.isNotEmpty(attachStr) && !"-1Login name does not exist".equals(attachStr)) {
            String[] attach = attachStr.split("\\|");
            if (null != attach && 0 != attach.length) {
                for (String subAttach : attach) {
                    attachList.add(Long.valueOf(subAttach));
                }
            }
        }
    }

    private void updateOASubmit(Integer id,Integer submitOAStatus) throws Exception {
        ContractInfoDto dto = new ContractInfoDto();
        ContractChangeDto contractChangeDto = new ContractChangeDto();
        // 合同变更ID
        contractChangeDto.setId(id);
        //更新为OA已提交
        contractChangeDto.setSubmitOAStatus(submitOAStatus);
        dto.setContractChangeDto(contractChangeDto);
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/updateFlowId";
        put(url, dto);
    }

	public ResultData<ContractChangeStore> getContractChangeStoreById(Integer storeId, Integer contractStopId)
    		throws Exception {
    	// 调用 接口
    	String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/getContractChangeStoreById/{storeId}/{contractStopId}";
    	ResultData<ContractChangeStore> backResult = (ResultData<ContractChangeStore>) get(url, storeId,contractStopId);
    	return backResult;
    }

    public String splitToSingle(Integer contractChangeId, List<Integer> storeIdList) throws Exception {
        
        int size = storeIdList.size();
        List<String> preCodeList = new ArrayList<>();
        for(int i = 0;i<size;i++) {
            preCodeList.add(SeqNoHelper.getInstance().getSeqNoByTypeCode("TYPE_CONTRACTCHANGE"));
        }
        
        String storeIds = StringUtils.join(storeIdList,",");
        String codes = StringUtils.join(preCodeList,",");
        
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/splitToSingle/{id}/{storeIds}/{codes}";
        ResultData<?> reback = get(url,contractChangeId,storeIds,codes);
        String str = (String) reback.getReturnData();
        return str;
    }

	public int queryStoreOfCountByConractId(Integer contractId) throws Exception {
		 String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/queryStoreOfCountByConractId/{contractId}";
	     ResultData<?> reback = get(url,contractId);
	     int i = (Integer) reback.getReturnData();
		return i;
	}

    /**
     * 删除变更记录
     * @param id
     * @return
     * @throws Exception
     */
    public ResultData<?> delete(Integer id) throws Exception {
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/del/{id}";
        ResultData<?> reback = get(url, id);
        return reback;
    }
    /**
     *	根据门店和合同查询已解除关联的门店公司
     * @param storeIdList
     * @throws Exception
     */
    public ResultData<?> getIsRelieveCompany(Integer contractId,List<Integer> storeIdList)
            throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/getIsRelieveCompany/{contractId}/{param}";
        ResultData<?> reback = get(url,contractId, storeIdList);
        return reback;
    }

    @Async("threadPoolTaskExecutor")
    public ResultData toOaAuthThreePart(Map<String, Object> reqMap, Integer id, Integer contractId, String busNo, List<Integer> storeIdList, UserInfo userInfo) throws Exception {
        //返回结果
        ResultData resultData = new ResultData();

        //准备发起OA
        updateOASubmit(id,21202);

        // 组装发送数据
        Map<String,Object> rspMap = setToOaInfoBToA(id, reqMap, busNo, contractId, userInfo);
        // 返回流程Id
        Long flowId;
        try {
            String oaTemplateCode = getOATemplateCode(contractId, "contractChgTpl");
            flowId = oaService.toOaAuth(reqMap, oaTemplateCode);
        } catch (Exception e) {
            updateOASubmit(id,21204);
            logger.error("contractChangeOa", "ContractChangeService", "toOaAuthThreePart", JsonUtil.parseToJson(reqMap), 0, "", "", e);
            resultData.setFail("向OA发起提交审核失败");
            return resultData;
        }

        if(flowId == null) {
            updateOASubmit(id,21204);
            logger.error("contractChangeOa", "ContractChangeService", "toOaAuthThreePart", JsonUtil.parseToJson(reqMap), 0, "", "OA返回flowId为空", new NullPointerException());
            resultData.setFail("向OA发起提交审核失败");
            return resultData;
        }

        try {
            // 更新ContractChange表中的FlowId、approveState
            // 将flowId更新到ContractChange表中
            this.updateFlowId(id, userInfo, flowId,rspMap);
        } catch (Exception e) {
            logger.error("contractChangeOa", "ContractChangeService", "updateFlowId", "", userInfo.getUserId(), "",
                    "更新合同变更信息失败！", e);
        }
        // 更新ContractStore表中的StoreState
        try {
             this.updateStoreState(storeIdList.get(0), contractId);
        } catch (Exception e) {
            logger.error("contractChangeOa", "ContractChangeOAController", "toOaAuthThreePart.updateStoreState", "storeId="+storeIdList.get(0), userInfo.getUserId(), "",
                    "更新合同门店关联信息失败！", e);
        }
        return resultData;
    }

    public ResultData<?> updateInfoForThreePart(String flowId, Integer auditRst, Integer userId) throws Exception {
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/updateInfoForThreePart/{flowId}/{auditRst}/{userId}";
        ResultData<?> reback = get(url, flowId, auditRst, userId);
        return reback;
    }

    public ResultData<?> operateAuditCt(Integer id) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "stopcontract" + "/operateAuditCt/{id}";
        ResultData<?> reback = get(url, id);
        return reback;
    }
    //获取模板编号
    private Map<String, Object> getTemplateCodeAndBusCode(String cityNo) {
    	Map<String, Object> hashMap = new HashMap<>();
    	String templateCode = "";
    	String busCode ="";
    	try {
    		ResultData<?> reback = commonService.getCitySettingByCityNo(cityNo);
    		Map<String, Object> reposeMap = (Map<String, Object>) reback.getReturnData();
    		if (reposeMap != null) {
    			templateCode = (String) reposeMap.get("contractEndTpl");
    			busCode = reposeMap.get("busCode").toString();
    			hashMap.put("templateCode", templateCode);
    			hashMap.put("busCode", busCode);
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return hashMap;
    }
}
