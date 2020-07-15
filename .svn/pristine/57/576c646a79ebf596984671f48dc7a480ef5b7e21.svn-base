package cn.com.eju.deal.contract.service;

import cn.com.eju.deal.base.file.util.FileAssist;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.base.util.UploadFileUtil;
import cn.com.eju.deal.base.util.XmlTransferUtil;
import cn.com.eju.deal.common.service.CommonService;
import cn.com.eju.deal.common.service.FileRecordMainService;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.common.util.ImageCompressUtil;
import cn.com.eju.deal.common.util.OaAttachmentUtil;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.SystemCfg;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.contract.ContractInfoDto;
import cn.com.eju.deal.dto.oa.OaModifyDto;
import cn.com.eju.deal.dto.oa.OaOperatorDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.oa.service.OaOperatorService;
import com.seeyon.client.CTPRestClient;
import com.seeyon.client.CTPServiceClientManager;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FilenameFilter;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 提交ＯＡ审核
 *
 * @author (li_xiaodong)
 * @date 2016年4月19日 下午7:45:30
 */
@Service("oaService")
public class OaService extends OaBaseService<OaModifyDto> {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    private static final String YYYYMMDD_FORMAT = "yyyy-MM-dd";

    //附件数据Map key
    private final static String ATTACH_KEY = "attachment";

    //表单数据Map key
    private final static String XML_DATA_KEY = "xmlData";

    //修改表单数据Map key
    private final static String MODIFY_DATA_KEY = "modifyData";

    //组装表单数据 -修改表单 Map key
    private final static String FLAG_MODIFY_KEY = "oaModify";

    @Resource(name = "contractService")
    private ContractService contractService;

    @Resource(name = "commonService")
    private CommonService commonService;

    @Resource(name = "oaOperatorService")
    private OaOperatorService oaOperatorService;

    @Resource(name = "fileService")
    private FileRecordMainService fileService;

    /**
     * 合同提交OA审核
     *
     * @param reqMap
     * @return
     * @throws Exception
     */
    public Long toOaAuth(Map<String, Object> reqMap, String oaTemplateCode)
            throws Exception {
        logger.info("CRM 提交审核 toOaAuth reqMap=", reqMap);

        // 取得REST动态客户机实例
        CTPRestClient client = getClient();

        // token认证
        client.authenticate(SystemParam.getWebConfigValue("oaUserName"),
                SystemParam.getWebConfigValue("oaPassword"));

        // 表单post
        String url = "flow/" + oaTemplateCode;
        Long flowId = client.post(url, reqMap, Long.class);

        return flowId;
    }

    /**
     * 合同提交OA审核
     *
     * @param reqMap
     * @return
     * @throws Exception
     */
    @Async("threadPoolTaskExecutor")
    public ResultData toOaAuthContracts(Map<String, Object> reqMap, Integer id, String busNo, String storeId, UserInfo userInfo,String companyId,String storeIdArray) throws Exception {
        //返回结果
        ResultData resultData = new ResultData();
        resultData.setSuccess();
        ContractInfoDto contractInfoDto = new ContractInfoDto();
        ContractDto contract = new ContractDto();
        contract.setId(id);

        Map<String,Object> respMap = null;
        //组装表单发起数据
        try {
            //准备发起OA
            contract.setSubmitOAStatus(21202);//处理中
            contractInfoDto.setContract(contract);
            contractService.updateByContractId(contractInfoDto);
            respMap = setToOaInfo(id, reqMap, busNo, storeId, userInfo);
        } catch (Exception e1) {
            contract.setSubmitOAStatus(21204);//处理失败
            contractInfoDto.setContract(contract);
            contractService.updateByContractId(contractInfoDto);
            resultData.setFail("组装合同数据错误");
            logger.error("ContractChange", "OaService", "setToOaInfo", "contractId:"+id, userInfo.getUserId(), "", "", e1);
            return resultData;
        }
        logger.info("CRM 提交审核 toOaAuth reqMap=", reqMap);

        String templateCode = reqMap.get("templateCode").toString();

        Long flowId;
        try {
            flowId = this.toOaAuth(reqMap, templateCode);
        }catch (Exception e) {
            contract.setSubmitOAStatus(21204);//处理失败
            contractInfoDto.setContract(contract);
            contractService.updateByContractId(contractInfoDto);
            resultData.setFail("向OA发起提交审核失败");
            logger.error("ContractChange", "OaService", "toOaAuth", reqMap.toString(), userInfo.getUserId(), "", "向OA发起提交审核失败", e);
            return resultData;
        }
        
        //需要入库
        contract.setFlowId(String.valueOf(flowId));
        contract.setSubmitOAStatus(21203);//已提交
        contractInfoDto.setContract(contract);
        try {
            contractService.updateFlowIdById(contractInfoDto);
        } catch (Exception e) {
            logger.error("Contacts", "OaService", "toOaAuth", "flowId:"+flowId, userInfo.getUserId(), "", "", e);
            resultData.setFail();
            return resultData;
        }
        //审核通过，锁客、锁门店、创建房友账号、更新状态为审核中
        contract.setCompanyId(Integer.valueOf(companyId));
        List<StoreDto> storeDetails = new ArrayList<StoreDto>();
        if (StringUtil.isNotEmpty(storeIdArray))
        {
            String[] arrays = storeIdArray.split(",");
            for (int i = 0; i < arrays.length; i++)
            {
                StoreDto storeDto = new StoreDto();
                storeDto.setStoreId(Integer.valueOf(arrays[i]));
                storeDetails.add(storeDto);
            }
            contractInfoDto.setStoreDetails(storeDetails);
        }
        try
        {
            //更新
            contractService.audit(contractInfoDto);
        }
        catch (Exception e)
        {
            logger.error("contract", "ContractController", "audit", "", UserInfoHolder.getUserId(), "", "", e);
            resultData.setFail("提交审核CRM操作失败");
            return resultData;
        }
        return resultData;
    }

    /**
     * 获取OA审核状态
     *
     * @param flowId
     * @return
     * @throws Exception
     */
    public Integer getOaState(String flowId) throws Exception {

        // 取得REST动态客户机实例
        CTPRestClient client = getClient();
        // token认证
        client.authenticate(SystemParam.getWebConfigValue("oaUserName"),
                SystemParam.getWebConfigValue("oaPassword"));

        String url = "flow/state/" + flowId;

        // get流程状态
        Integer state = client.get(url, Integer.class);

        return state;
    }

    /**
     * 获取OA模板
     *
     * @param
     * @return
     * @throws Exception
     */
    public String getOaTemplate(String flowId) throws Exception {

        // 取得REST动态客户机实例
        CTPRestClient client = getClient();
        // token认证
        client.authenticate(SystemParam.getWebConfigValue("oaUserName"),
                SystemParam.getWebConfigValue("oaPassword"));

        String url = "flow/data/" + flowId;

        // get流程正文数据
        String xmlStr = client.get(url, String.class);

        return xmlStr;
    }

    /**
     * OA附件上传
     *
     * @param absolutePath 上传附件的绝对路径
     * @return
     * @throws Exception
     */
    public String oaAbsolutePathUpload(String absolutePath, String fileName, String senderLoginName) throws Exception {

        // 取得REST动态客户机实例
        CTPRestClient client = getClient();
        // 获取token认证
        String token = client.get("token/" + SystemParam.getWebConfigValue("oaUserName") + "/" + SystemParam.getWebConfigValue("oaPassword"), String.class, "text/plain");

        String attachmentId = OaAttachmentUtil.oaUpload(SystemParam.getWebConfigValue("oaUrl"), token, senderLoginName, absolutePath, fileName);

        return attachmentId;
    }

    /**
     * OA附件上传
     *
     * @param absolutePath 上传附件的绝对路径
     * @return
     * @throws Exception
     */
    public String oaAttachmentUpload(String fileUrl, String fileName, String senderLoginName,String tempFilePath) throws Exception {

        // 取得REST动态客户机实例
        CTPRestClient client = getClient();
        // 获取token认证
        String token = client.get("token/" + SystemParam.getWebConfigValue("oaUserName") + "/" + SystemParam.getWebConfigValue("oaPassword"), String.class, "text/plain");

        String attachmentId = OaAttachmentUtil.oaUpload(SystemParam.getWebConfigValue("oaUrl"), token, senderLoginName, fileUrl, fileName,tempFilePath);

        return attachmentId;
    }

    /**
     * 修改表单
     *
     * @param contractInfoDto
     * @return
     * @throws Exception
     */
    public String modify(OaModifyDto oaModifyDto) throws Exception {

        String url = "http://10.0.3.5:88/fcjy_return/fcjy_update.asmx";

        // 参数对象转json字符串
        String jsonsStr = JsonUtil.parseToJson(oaModifyDto);

        String jsonStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                + "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">"
                + "<soap12:Body><fcjy_Update xmlns=\"http://tempuri.org/\"><formVals>"
                + jsonsStr + "</formVals></fcjy_Update></soap12:Body>"
                + "</soap12:Envelope>";

        String backResult = post(url, jsonStr);

        return backResult;
    }

    /**
     * 获取审核批注原因
     *
     * @param contractInfoDto
     * @return
     * @throws Exception
     */
    public String getOpinions(String flowId) throws Exception {

        String url = SystemParam.getWebConfigValue("oaApiUrl") + "fcjy_update.asmx";

        String jsonStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                + "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">"
                + "<soap12:Body>"
                + "<fcjy_getOpinions xmlns=\"http://tempuri.org/\">" + "<id>"
                + flowId + "</id>" + "</fcjy_getOpinions>" + "</soap12:Body>"
                + "</soap12:Envelope>";

        String backResult = post(url, jsonStr);

        return backResult;
    }

    /**
     * 取得REST动态客户机实例
     *
     * @return
     */
    private CTPRestClient getClient() {

        // 指定协议、IP和端口，获取ClientManager
        CTPServiceClientManager clientManager = CTPServiceClientManager
                .getInstance(SystemParam.getWebConfigValue("oaUrl"));

        // 取得REST动态客户机实例
        CTPRestClient client = clientManager.getRestClient();
        return client;
    }

    public static void main(String[] args) throws Exception {
        OaService oaService = new OaService();
        String flowIds = "-9199279135699155752 ,2910749234478399981 ,-4571578795560580484 ,9066950192172450713 ,-8126769831657455395 ,8473907710594992080 ,8804861274799296358 ,-3577991705510525100 ,6965480226361263814 ,-8615795806668566998 ,8287869575552912723 ,5546605676797513508 ,-228575833154305930 ,-849132168560095382 ,2118592569858474972 ,-1263806801638899484 ,-6956467731872593065 ,-7377259000141559478 ,-7324446060858752073 ,-6226988647819949558 ,5130999427482210109 ,3317225590389687027 ,2837012977429857548 ,-494163198690179610 ,5174193992044660256 ,5329763057616309864 ,-8927067773704189471 ,8317800260330670347 ,-882013808002121393 ,7364935332723967180 ,-3556650525380624002 ,2901191123775867821 ,-628438612637891210 ,5527515353558764029 ,-5084239025011536317 ,-6417518667094866596 ,5778569779660409240 ,-3007556559184575543 ,3937114546879152339 ,1694536956076638600 ,-8797239115687442882 ,6307210962658150047 ,1793901967570062899 ,4191352162669383405 ,-8072687040941109651 ,-7763844055292952470 ,-1745401376458390986 ,6381260373255340218 ,-8435656495039165831 ,7599199122140581167 ,-5695011579110340867 ,-4274202418543964762 ,-4711761415876426096 ,-3603175276712671329 ,3133481857927302063 ,565881327978106477 ,953148569463768886 ,7339621395927025550 ,505610159972108212 ,8233920324332899822 ,-3179370555672802681 ,7746469997962346624 ,-8999786034326649300 ,-8600956087792154841 ,-2488019939140024854 ,5438490235564671018 ,-4149308169082353905 ,2113962231361195328 ,4232854817704266264 ,1525495465448391558 ,2339940449605471753 ,4719261683523272802 ,5441521095142424589 ,1573152621822365395 ,-5235120311439307326 ,7137030737217997388 ,-8656773858528770359 ,8416066701409992970 ,-5970962336672233579 ,-8479974012618453919 ,-3429795426486434979 ,6365764084464698953 ,-4709074163793050278 ,5776331671263660274 ,7893341209105681411 ,-8161934833854492971 ,-4148364120346048848 ,8447738696782005338 ,-5419007035803417955 ,-3670551735975720355 ,8798780857738740428 ,-3250177601747007616 ,5675151180751652376 ,-2697432896628175714 ,-7687783509549954669 ,-5136078205498211483 ,-4926045160250914654 ,-8571353921568421471 ,74333126425919794 ,-3701928245546104467 ,-1470612377725481600 ,63568208115027674 ,-3855818844611196871 ,-1549401731928077069 ,6607326006274445243 ,-3409905177893517435 ,-4047620574037522040 ,-6028801309039178818 ,-4816093545688054940 ,-8948571241806803029 ,-767901231431925748 ,-1305812131229776003 ,7349680201543011508 ,5741673902032046566 ,-559250736860740218 ,8714001987609969515 ,-1375055386285553477 ,-8329734772560081188 ,396850363561558173 ,8311445633615743609 ,-6464250796852884438";
        String[] flowIdArr = flowIds.split(",");
        StringBuilder dateCreateStr = new StringBuilder();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        List<Date> dates = new ArrayList<Date>();
        for (int i = 0; i < flowIdArr.length; i++) {
            // List<Date>
            String flowId = flowIdArr[i];
            String backResult = oaService.getOpinions(flowId);
            backResult = backResult.substring(0, backResult.indexOf("<"));
            Map<?, ?> rebackMap = JsonUtil.parseToObject(backResult, Map.class);
            List<?> tData = (List<?>) rebackMap.get("TData");
            for (int index = 0; index < tData.size(); index++) {
                Map<?, ?> data = (Map) tData.get(index);
                String date = (String) data.get("create_date");
                Date d = format.parse(date.substring(0, 19));
                dates.add(d);
            }
            Collections.sort(dates);
            dateCreateStr.append(format1.format(dates.get(dates.size() - 1))
                    + ",");
            dates.removeAll(dates);
        }
        String returnDates = dateCreateStr.subSequence(0,
                dateCreateStr.length() - 1).toString();

        System.out.println(returnDates);
    }

    private Map<String, Object> setToOaInfo(Integer id, Map<String, Object> reqMap, String busNo, String storeId, UserInfo userInfo) throws Exception {
        //发起者的登录名（登录协同的登录名）
        String senderLoginName = userInfo.getUserCode();
        //String senderLoginName = "14879";
        reqMap.put("senderLoginName", senderLoginName);

        //协同的标题
        reqMap.put("subject", "中介合作签约报批单");

        //Mod by WangLei 2017/04/07 Start
        //表单数据、附件数据
        Map<String, Object> rspMap = setOaAuditData(id, busNo, "", storeId, userInfo);
        String acCityNo = rspMap.get("acCityNo").toString();

        //Mod by WangLei 2017/04/07 End
        reqMap.put("templateCode", rspMap.get("templateCode").toString());//从map里面取 rspMap.get("templateCode").toString(); 
        //附件，Long型数组，值为附件的Id。
        reqMap.put("attachments", rspMap.get(ATTACH_KEY));

        //HTML正文流程为html内容；表单流程为XML格式的表单数据
        reqMap.put("data", rspMap.get(XML_DATA_KEY));

        //为控制是否流程发送。0：缺省值，发送，进入下一节点的待办（如果需要选人则保存到待发）1：不发送，保存到待发。
        reqMap.put("state", "0");

        // 取得REST动态客户机实例
        CTPRestClient client = getClient();
        String token = client.get("token/" + SystemParam.getWebConfigValue("oaUserName") + "/" + SystemParam.getWebConfigValue("oaPassword"), String.class, "text/plain");
        //为登录验证后获取的身份令牌
        reqMap.put("token", token);

        return rspMap;
    }

    /**
     * 组装发送OA审核的 表单数据、附件数据
     *
     * @param id
     * @return
     */
    private Map<String, Object> setOaAuditData(Integer id, String busNo, String type, String storeId, UserInfo userInfo) throws Exception
    //Mod By WangLei 2017/04/07 Start
    {
        //返回表单数据、返回附件数据
        Map<String, Object> rspMap = new HashMap<String, Object>();
        String dateHtml = "";

        //合同汇总信息
        ContractInfoDto ctaDto = new ContractInfoDto();

        //合同基础信息
        ContractDto ctDto = new ContractDto();

        Map<?, ?> mapTempp = new HashMap<String, Object>();

        //返回map
        try {
            ResultData<?> resultData = contractService.getById(id);
            Map<?, ?> mapTemp = (Map<?, ?>) resultData.getReturnData();
            if (null != mapTemp) {
                ctaDto = MapToEntityConvertUtil.convert(mapTemp, ContractInfoDto.class, "");
                mapTempp = (Map<?, ?>) mapTemp.get("contract");
                if (null != mapTempp) {
                    ctDto = MapToEntityConvertUtil.convert(mapTempp, ContractDto.class, "");
                }
                mapTempp.get("depositFee");
            }
        } catch (Exception e) {
            dateHtml = "";
        }

        String acCityNo = ctDto.getAcCityNo();
        rspMap.put("acCityNo", acCityNo);
        Map<String, Object> templateCodeAndBusCode = getTemplateCodeAndBusCode(acCityNo);
        if(null != templateCodeAndBusCode) {
        	rspMap.put("templateCode", templateCodeAndBusCode.get("templateCode").toString());
        	rspMap.put("busCode", templateCodeAndBusCode.get("busCode").toString());
        }
        //合同编号
        String contractNo = ctDto.getContractNo();
        //违约金
        BigDecimal penaltyFee = new BigDecimal((Integer) mapTempp.get("penaltyFee"));//BigDecimal类型特殊处理
        //合作门店数
        Integer storeNum = ctDto.getStoreNum();
        //每门店保证金
        BigDecimal depositFee = new BigDecimal((Integer) mapTempp.get("depositFee"));//BigDecimal类型特殊处理
        //总保证金
        BigDecimal totleDepositFee = new BigDecimal((Integer) mapTempp.get("totleDepositFee"));//BigDecimal类型特殊处理
        //合作期限2016.4.1-2017.3.31
        String cooperationPeriod =
                DateUtil.fmtDate(ctDto.getDateLifeStart(), YYYYMMDD_FORMAT) + "至" + DateUtil.fmtDate(ctDto.getDateLifeEnd(), YYYYMMDD_FORMAT);
        //协议书编号
        String agreementNo = ctDto.getAgreementNo();
        //中介全称
        String partyB = ctDto.getPartyB();
        //法定代表人
        String lealPerson = ctDto.getLealPerson();
        //中介授权代表
        String authRepresentative = ctDto.getAuthRepresentative();
        //中介住所（目前取公司地址，需要取实际经营地址？）
        String shelter = ctDto.getPartyAddressDetail();
        //合作模式
        String coopType = null;
        //合作协议书类型
        String contractType;
        Integer contractTp = ctDto.getContractType();
        if (DictionaryConstants.CONTRACT_TYPE_A == contractTp) {
            contractType = SystemParam.getWebConfigValue("oaCoopTypeA");
        } else if (DictionaryConstants.CONTRACT_TYPE_B == contractTp) {
            contractType = SystemParam.getWebConfigValue("oaCoopTypeB");
        } else if (DictionaryConstants.CONTRACT_TYPE_A_2_B == contractTp) {
            contractType = SystemParam.getWebConfigValue("oaCoopTypeA2B");
        } else if (DictionaryConstants.CONTRACT_TYPE_AWARDING == contractTp)  {
            contractType = SystemParam.getWebConfigValue("oaCoopTypeAwarding");
        } else {
            contractType = SystemParam.getWebConfigValue("oaCoopTypeA");
        }

        //中介联系方式(客户下的首要联系人)
        String contactNo = ctDto.getAgentContact();
        //原店招名称(门店店招名称（若客户有多个门店，则随机取一个店招）)
        String formerStoreName = "";

        //合作门店实际经营地址
        StringBuilder sb = new StringBuilder();

        //实际经营地址List
        List<String> addrList = new ArrayList<String>();

        List<StoreDto> storeList = ctaDto.getStoreDetails();

        if (null != storeList && !storeList.isEmpty()) {
            Map<String, Object> tempMap = new HashMap<>();
            String addressDetailTemp = null;

            for (int i = 0; i < storeList.size(); i++) {
                tempMap = (Map<String, Object>) storeList.get(i);

                addressDetailTemp = (String) tempMap.get("addressDetail");

                //set经营地址
                addrList.add(addressDetailTemp);

                //取其第一个门店店招名
                formerStoreName = (String) tempMap.get("name");

                sb.append("<row><column name=\"经营地址\"><value>" + XmlTransferUtil.transfer(addressDetailTemp) + "</value></column></row>");
            }

        } else {
            sb.append("<row><column name=\"经营地址\"><value> </value></column></row>");
        }

        //合作门店实际经营地址
        String businessAddressXml = sb.toString();

        //编码--取合同编号
        String contractCode = contractNo;
        //CRM单据号--取合同编号
        String crmNo = contractNo;
        //流程列别 0标准(默认) 1特殊
        String flowSort = SystemParam.getWebConfigValue(ctDto.getOaApproveType());

        //附件集合
        List<Long> attachList = new ArrayList<Long>();

        //modify by haidan 2017/10/17 上传附件统一在这里处理
        
        String tempFilePath = SystemCfg.getString("tempFilePath");
        tempFilePath = MessageFormat.format(tempFilePath, contractNo); 
        File tempDir = new File(ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath(tempFilePath));
        if (!tempDir.exists()){
            tempDir.mkdirs();
        }
        tempFilePath = tempDir + File.separator;
        
        //中介营业执照
        List<String> businessLicensePicList = sendAttachMentToOa(attachList, ctDto.getId(), 1, userInfo, ctDto.getOaFileIdBusiness(),tempFilePath);
        
        //合作协议书
        List<String> cooperationTypePicList = sendAttachMentToOa(attachList, ctDto.getId(), 2, userInfo, ctDto.getOaFileIdPhoto(),tempFilePath);

        //房友系统申请安装单
        List<String> fangyouPicList = sendAttachMentToOa(attachList, ctDto.getId(), 3, userInfo, ctDto.getOaFileIdInstall(),tempFilePath);

        //法定代表人身份证名片
        List<String> idCardPicList = sendAttachMentToOa(attachList, ctDto.getId(), 4, userInfo, ctDto.getOaFileIdCard(),tempFilePath);

        //中介门店照片
        List<String> storePicList = sendAttachMentToOa(attachList, ctDto.getId(), 5, userInfo, ctDto.getOaFileIdStore(),tempFilePath);

        //重要提示函
        List<String>  remindePicList = sendAttachMentToOa(attachList, ctDto.getId(), 1020, userInfo, ctDto.getOaFileIdNotice(),tempFilePath);
        List<String>  complementPicList = sendAttachMentToOa(attachList, ctDto.getId(), 1026, userInfo, ctDto.getOaFileIdComplement(),tempFilePath);
        
        //其它照片
        List<String> otherPicList = sendAttachMentToOa(attachList, ctDto.getId(), 6, userInfo, ctDto.getOaFileIdOther(),tempFilePath);
        
        //文件压缩包上传
        sendZipAttachMentToOa(attachList,tempFilePath,userInfo.getUserCode(),contractNo,"中介合作签约报批单");
        
        //补充条款/特别约定
        String subsidiary = "";
        //特殊情况
        String specialCase = "";

        //备注
        String remark = ctDto.getRemark();

        //业绩归属拓展人员工号
        String ownId = ctDto.getExpandingPersonnelId();
        //业绩归属拓展人员姓名
        String ownName = ctDto.getExpandingPersonnel();

        //经办人工号
        String operatorId = userInfo.getUserCode();
        //经办人 姓名
        String operatorName = userInfo.getUserName();

        //事业部区域 （取经办人的事业部区域，如果是跨区的，取其选择的区域）
        String bussineArea = rspMap.get("busCode").toString();
        //查询是否是经办人
        /*ResultData<?> backResult = new ResultData<>();
        try {
            backResult = oaOperatorService.getByUserCode(operatorId);
        } catch (Exception e) {
            logger.error("contract", "OaService", "setOaAuditData", "", null, "", "", e);
        }

        Map<?, ?> mapTemp = (Map<?, ?>) backResult.getReturnData();

        if (null != mapTemp) {
            OaOperatorDto oaOperatorDto = MapToEntityConvertUtil.convert(mapTemp, OaOperatorDto.class, "");

            Boolean isCombine = oaOperatorDto.getIsCombine();
            if (isCombine) {
                bussineArea = busNo;
            } else {
                bussineArea = oaOperatorDto.getBusCode();
            }

        }*/

        String configValue = SystemParam.getWebConfigValue(bussineArea);
        String companyNo = "";
        String companyName = "";
        if (configValue != null && "" != configValue && configValue.contains("|")) {
            String[] values = configValue.split("\\|");
            //公司编码
            companyNo = values[0];
            //公司名称
            companyName = values[1];

            rspMap.put("companyNo",companyNo);
            rspMap.put("companyName",companyName);
        }
        //Add by Wanglei 2017/04/07 Start
        Integer isArrivalAct = 0;
        BigDecimal refundAmount = new BigDecimal(0);
        String bufwebConfigName = ""; // buffer
        Integer coopTp = 0;        //合作协议书类型
        // 原合作签约报批单编号(原合同编号)
        String originalContractNo = ctDto.getOriginalContractNo();

        // 续签合作协议书类型(11001：简易A版； 11002：AB版；11003：续签协议)
        Integer reNewAgreementType = null;
        String reNewAgreementTypeVal = "";

        // 续签合作协议书编号
        String reNewAgreement = "";
        // 合同类别  (新签:18601 续签:18602)
        Integer originalContractdistinction = null;
        String originalContractdistinctionVal = "";
        // 到账保证金
        BigDecimal storeDepositFee = new BigDecimal(0);


        // 合同类别  (新签:18601 续签:18602)
        originalContractdistinction = ctDto.getOriginalContractdistinction();
        switch (originalContractdistinction) {
            case DictionaryConstants.OriginalContractdistinction_TYPE_N:    //新签
                bufwebConfigName = "oaContractTypeNew";
                break;
            case DictionaryConstants.OriginalContractdistinction_TYPE_R:    //续签
                bufwebConfigName = "oaContractTypeRenew";
                break;
            default:                                                        //其他
                bufwebConfigName = "";
        }
        originalContractdistinctionVal = SystemParam.getWebConfigValue(bufwebConfigName);
//Add By GuoPengFei 合规性 start
        //是否乙转甲
        Integer contractB2A = ctDto.getContractB2A();
        String contractB2AStr = "";
        if (contractB2A.equals(DictionaryConstants.Contract_TypeB2A_YES)) {//乙转甲
            //contractB2AStr = "5699872488764622024";
            contractB2AStr = SystemParam.getWebConfigValue("contractTypeB2AYes");
        } else {//非乙转甲
            //contractB2AStr = "3043816519054936160";
            contractB2AStr = SystemParam.getWebConfigValue("contractTypeB2ANo");
        }

        String contractB2AChangeNo = "";
        if (contractB2A.equals(DictionaryConstants.Contract_TypeB2A_YES)) {//乙转甲
            contractB2AChangeNo = getcontractB2AChangeNo(ctDto.getContractNo());
        }

        //门店资质等级
        String abTypeStore = getabTypeStore(ctDto.getId(),contractTp);

//Add By GuoPengFei 合规性 end

        //门店id
        Integer storeIds = Integer.parseInt(storeId);

        try {
            if (null != originalContractNo) {// 续签
                // 获取到账保证金
                ResultData<?> resultData = contractService.getOrgContractInfo(storeIds, originalContractNo);

                Map<?, ?> mapTmp = (Map<?, ?>) resultData.getReturnData();
                if (null != mapTmp) {
                    // 是否到账
                    if (mapTmp.get("isArrivalAct") != null) {
                        isArrivalAct = Integer.valueOf(mapTmp.get("isArrivalAct").toString());
                    }
                    //退款总金额
                    if (mapTmp.get("refundAmount") != null) {
                        refundAmount = BigDecimal.valueOf(Double.valueOf(mapTmp.get("refundAmount").toString()));
                    }

                    // 门店保证金
                    if (mapTmp.get("depositFee") != null) {
                        storeDepositFee = BigDecimal.valueOf(Double.valueOf(mapTmp.get("depositFee").toString()));
                    }
                }

                if (null != isArrivalAct && isArrivalAct == 1) {
                    storeDepositFee = storeDepositFee.subtract(refundAmount);
                } else {
                    storeDepositFee = new BigDecimal(0);
                }
                // 原合同续签合作协议书No
                agreementNo = mapTmp.get("agreementNo").toString();
                // 原合同续签合作协议类型
                coopTp = Integer.valueOf(mapTmp.get("agreementType").toString());
                switch (coopTp) {
                    case DictionaryConstants.AGREEMENT_TYPE_A:    //简易A版
                        bufwebConfigName = "oaRenewCooperationA";
                        break;
                    case DictionaryConstants.AGREEMENT_TYPE_AB:    //AB版
                        bufwebConfigName = "oaRenewCooperationAB";
                        break;
                    case DictionaryConstants.AGREEMENT_TYPE_XQ:    //续签协议
                        bufwebConfigName = "oaRenewCooperationReNew";
                        break;
                    case DictionaryConstants.AGREEMENT_TYPE_NEWA:    //A版
                        bufwebConfigName = "oaRenewCooperationNewA";
                        break;
                    case DictionaryConstants.AGREEMENT_TYPE_NEWB:    //B版
                        bufwebConfigName = "oaRenewCooperationNewB";
                        break;
                    case DictionaryConstants.AGREEMENT_TYPE_YEAR:    //年度合作协议书
                    	bufwebConfigName = "oaRenewCooperationNewAwarding";
                    	break;
                    default:                                        //其他
                        bufwebConfigName = "";
                }
                coopType = SystemParam.getWebConfigValue(bufwebConfigName);


                // 续签合作协议书类型(11001：简易A版； 11002：AB版；11003：续签协议)
                reNewAgreementType = ctDto.getAgreementType();
                switch (reNewAgreementType) {
                    case DictionaryConstants.AGREEMENT_TYPE_A:    //简易A版
                        bufwebConfigName = "oaRenewCooperationA";
                        break;
                    case DictionaryConstants.AGREEMENT_TYPE_AB:    //AB版
                        bufwebConfigName = "oaRenewCooperationAB";
                        break;
                    case DictionaryConstants.AGREEMENT_TYPE_XQ:    //续签协议
                        bufwebConfigName = "oaRenewCooperationReNew";
                        break;
                    case DictionaryConstants.AGREEMENT_TYPE_NEWA:    //A版
                        bufwebConfigName = "oaRenewCooperationNewA";
                        break;
                    case DictionaryConstants.AGREEMENT_TYPE_NEWB:    //B版
                        bufwebConfigName = "oaRenewCooperationNewB";
                        break;
                    case DictionaryConstants.AGREEMENT_TYPE_YEAR:    //年度合作协议书
                    	bufwebConfigName = "oaRenewCooperationNewAwarding";
                    	break;
                    default:                                        //其他
                        bufwebConfigName = "";
                }
                reNewAgreementTypeVal = SystemParam.getWebConfigValue(bufwebConfigName);

                // 续签合作协议书编号
                reNewAgreement = ctDto.getAgreementNo();
            } else {//新签约
                originalContractNo = "";
                reNewAgreementTypeVal = "";
                reNewAgreement = "";
                //Add By GuoPengFei 2017/04/18 Start
                //合作协议书类型
                coopTp = ctDto.getAgreementType();
                switch (coopTp) {
                    case DictionaryConstants.AGREEMENT_TYPE_A:    //简易A版
                        bufwebConfigName = "oaRenewCooperationA";
                        break;
                    case DictionaryConstants.AGREEMENT_TYPE_AB:    //AB版
                        bufwebConfigName = "oaRenewCooperationAB";
                        break;
                    case DictionaryConstants.AGREEMENT_TYPE_XQ:    //续签协议
                        bufwebConfigName = "oaRenewCooperationReNew";
                        break;
                    case DictionaryConstants.AGREEMENT_TYPE_NEWA:    //A版
                        bufwebConfigName = "oaRenewCooperationNewA";
                        break;
                    case DictionaryConstants.AGREEMENT_TYPE_NEWB:    //B版
                        bufwebConfigName = "oaRenewCooperationNewB";
                        break;
                    case DictionaryConstants.AGREEMENT_TYPE_YEAR:    //年度合作协议书
                    	bufwebConfigName = "oaRenewCooperationNewAwarding";
                    	break;
                    default:                                        //其他
                        bufwebConfigName = "";
                }
                coopType = SystemParam.getWebConfigValue(bufwebConfigName);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        //Add by WangLei 2017/04/07 End
        dateHtml +=
                "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<formExport version=\"2.0\">"
                        + "<summary id=\"2980199554847034444\" name=\"formmain_2555\"/>" + "<definitions/>" + "<values>"
                        + "<column name=\"编码\"><value>"
                        + XmlTransferUtil.transfer(contractCode)
                        + "</value></column>"
                        + "<column name=\"事业部区域\"><value>"
                        + XmlTransferUtil.transfer(bussineArea) 
                        + "</value></column>"
                        + "<column name=\"协议书编号\"><value>"
                        + XmlTransferUtil.transfer(agreementNo)
                        + "</value></column>"
                        + "<column name=\"特殊情况\"><value>"
                        + XmlTransferUtil.transfer(specialCase)
                        + "</value></column>"
                        //Mod By GuoPengFei 2017/04/18 Start
//              + "<column name=\"合作协议书类型\"><value>"
                        + "<column name=\"合作模式\"><value>"
//Mod By GuoPengFei 2017/04/18 End
                        + XmlTransferUtil.transfer(contractType)
                        + "</value></column>"
                        + "<column name=\"流程列别\"><value>"
                        + XmlTransferUtil.transfer(flowSort)
                        + "</value></column>"
                        + "<column name=\"中介全称\"><value>"
                        + XmlTransferUtil.transfer(partyB)
                        + "</value></column>"
                        + "<column name=\"原店招名称\"><value>"
                        + XmlTransferUtil.transfer(formerStoreName)
                        + "</value></column>"
                        + "<column name=\"法定代表人\"><value>"
                        + XmlTransferUtil.transfer(lealPerson)
                        + "</value></column>"
                        + "<column name=\"中介授权代表\"><value>"
                        + XmlTransferUtil.transfer(authRepresentative)
                        + "</value></column>"
                        + "<column name=\"中介住所\"><value>"
                        + XmlTransferUtil.transfer(shelter)
                        + "</value></column>"
                        + "<column name=\"中介联系方式\"><value>"
                        + XmlTransferUtil.transfer(contactNo)
                        + "</value></column>"
                        //Mod By GuoPengFei 2017/04/18 Start
//              + "<column name=\"合作模式\"><value>"
                        + "<column name=\"合作协议书类型\"><value>"
//Mod By GuoPengFei 2017/04/18 End
                        + XmlTransferUtil.transfer(coopType)
                        + "</value></column>"
                        + "<column name=\"合作期限\"><value>"
                        + XmlTransferUtil.transfer(cooperationPeriod)
                        + "</value></column>"
                        + "<column name=\"合作门店数\"><value>"
                        + storeNum
                        + "</value></column>"
                        + "<column name=\"违约金数额\"><value>"
                        + penaltyFee
                        + "</value></column>"
                        + "<column name=\"每店保证金数额\"><value>"
                        + depositFee
                        + "</value></column>"
                        + "<column name=\"总保证金数额\"><value>"
                        + totleDepositFee
                        + "</value></column>"
                        + "<column name=\"补充条款\"><value>"
                        + XmlTransferUtil.transfer(subsidiary)
                        + "</value></column>"
                        + "<column name=\"中介营业执照\"><value>"
                        // + businessLicensePic
                        + "-1"
                        + "</value></column>"
                        + "<column name=\"合作协议书\"><value>"
                        //+ cooperationTypePic
                        + "-1"
                        + "</value></column>"
                        + "<column name=\"房友系统申请安装单\"><value>"
                        // + fangyouPic
                        + "-1"
                        + "</value></column>"
                        + "<column name=\"法定代表人身份证名片\"><value>"
                        // + idCardPic
                        + "-1"
                        + "</value></column>"
                        + "<column name=\"中介门店照片\"><value>"
                        //  + storePic
                        + "-1"
                        + "</value></column>"
                        + "<column name=\"其他\"><value>"
                        //  + otherPic
                        + "-1"
                        + "</value></column>"
                        + "<column name=\"经办人\"><value>"
                        + XmlTransferUtil.transfer(operatorName)
                        + "</value></column>"
                        + "<column name=\"业绩归属拓展人员姓名\"><value>"
                        + XmlTransferUtil.transfer(ownName)
                        + "</value></column>"
                        + "<column name=\"公司名称\"><value>"
                        + XmlTransferUtil.transfer(companyName)
                        + "</value></column>"
                        + "<column name=\"业绩归属拓展人员工号\"><value>"
                        + XmlTransferUtil.transfer(ownId)
                        + "</value></column>"
                        + "<column name=\"公司编码\"><value>"
                        + XmlTransferUtil.transfer(companyNo)
                        + "</value></column>"
                        + "<column name=\"经办人工号\"><value>"
                        + XmlTransferUtil.transfer(operatorId)
                        + "</value></column>"
                        + "<column name=\"CRM单据号\"><value>"
                        + XmlTransferUtil.transfer(crmNo)
                        + "</value></column>"
//Add By GuoPengFei 合规性 start
                        + "<column name=\"是否乙类转甲类\"><value>"
                        + XmlTransferUtil.transfer(contractB2AStr)
                        + "</value></column>"
                        + "<column name=\"乙类转甲类变更单号\"><value>"
                        + XmlTransferUtil.transfer(contractB2AChangeNo)
                        + "</value></column>"
                        + "<column name=\"合作门店资质等级\"><value>"
                        + XmlTransferUtil.transfer(abTypeStore)
                        + "</value></column>"
//Add By GuoPengFei 合规性 end
                        + "<column name=\"备注\"><value>"
                        + XmlTransferUtil.transfer(remark)
                        + "</value></column>"
                        //Add by WangLei 2017/04/07 Start
                        + "<column name=\"合同类别\"><value>"
                        + XmlTransferUtil.transfer(originalContractdistinctionVal) //合同类别 新签 续签
                        + "</value></column>"
                        + "<column name=\"续签合作协议书类型\"><value>"  //简易A版   AB版   续签协议
                        + XmlTransferUtil.transfer(reNewAgreementTypeVal)
                        + "</value></column>"
                        + "<column name=\"续签合作协议书编号\"><value>"//
                        + XmlTransferUtil.transfer(reNewAgreement) //续签合作协议书编号
                        + "</value></column>"
                        + "<column name=\"门店到账保证金\"><value>"
                        + storeDepositFee
                        + "</value></column>"
                        + "<column name=\"原合作签约报批单编号\"><value>"
                        + XmlTransferUtil.transfer(originalContractNo)  //原合同编号
                        + "</value></column>"
                        + "<column name=\"营业执照注册号\"><value>"
                        + XmlTransferUtil.transfer(ctDto.getRegistrId()) //营业执照注册号
                        + "</value></column>"
                        + "</values>"
                        + "<subForms>"
                        + "<subForm>"
                        + "<definitions><column id=\"field0030\" type=\"0\" name=\"经营地址\" length=\"255\"/></definitions>"
                        + "<values>" + businessAddressXml+ "</values>" + "</subForm>" + "</subForms>" + " </formExport>";

        //附件数据
        rspMap.put(ATTACH_KEY, attachList.toArray());
        //表单数据
        rspMap.put(XML_DATA_KEY, dateHtml.toString());
        logger.info("CRM 提交审核 dateHtml=", dateHtml);

        //OA修改表单
        if (FLAG_MODIFY_KEY.equals(type)) {

            //审核不同过的合同再次提交编辑，组装数据
            OaModifyDto oaModifyDto = new OaModifyDto();
            oaModifyDto.setId(Long.parseLong(ctDto.getFlowId()));
            oaModifyDto.setField0003(bussineArea);
            oaModifyDto.setField0004(ownName);
            oaModifyDto.setField0005(agreementNo);
            oaModifyDto.setField0006(specialCase);
            oaModifyDto.setField0007(contractType);
            oaModifyDto.setField0008(flowSort);
            oaModifyDto.setField0009(partyB);
            oaModifyDto.setField0010(formerStoreName);
            oaModifyDto.setField0011(lealPerson);
            oaModifyDto.setField0012(authRepresentative);
            oaModifyDto.setField0013(shelter);
            oaModifyDto.setField0014(contactNo);
            oaModifyDto.setField0015(coopType);
            oaModifyDto.setField0016(cooperationPeriod);
            oaModifyDto.setField0017(String.valueOf(storeNum));
            oaModifyDto.setField0018(String.valueOf(penaltyFee));
            oaModifyDto.setField0019(String.valueOf(depositFee));
            oaModifyDto.setField0020(String.valueOf(totleDepositFee));
            oaModifyDto.setField0021(subsidiary);
            oaModifyDto.setField0027(null);
            oaModifyDto.setField0029(companyName);
            oaModifyDto.setField0031(ownId);
            oaModifyDto.setField0032(Integer.valueOf(companyNo));
            oaModifyDto.setField0035(crmNo);

            //Add by WangLei 2017/04/07 Start
            oaModifyDto.setField0037(reNewAgreementTypeVal);
            oaModifyDto.setField0038(originalContractdistinctionVal);
            oaModifyDto.setField0039(String.valueOf(storeDepositFee));
            oaModifyDto.setField0040(reNewAgreement);
            oaModifyDto.setField0041(originalContractNo);
            //Add by WangLei 2017/04/07 End
            //经营地址
            oaModifyDto.setField0030(addrList);
            //营业执照
            oaModifyDto.setLicenses(businessLicensePicList);
            //合作协议书
            oaModifyDto.setAgreements(cooperationTypePicList);
            //中介法定代表人/负责人身份证或名片
            oaModifyDto.setIdCards(idCardPicList);
            //房友系统申请安装单
            oaModifyDto.setInstallations(fangyouPicList);
            //门店照片
            oaModifyDto.setStorePhotos(storePicList);
            //重要提示函
            oaModifyDto.setNoticePhotos(remindePicList);
            //补充协议
            oaModifyDto.setComplementPhotos(complementPicList);
            //其它
            oaModifyDto.setOthers(otherPicList);
            
            //表单数据
            rspMap.put(MODIFY_DATA_KEY, oaModifyDto);
        }

        return rspMap;

    }

    //获取模板编号
    private String getTemplateCode(String cityNo) {
        String templateCode = "";
        try {
            ResultData<?> reback = commonService.getCitySettingByCityNo(cityNo);
            Map<String, Object> map = (Map<String, Object>) reback.getReturnData();
            if (map != null) {
                templateCode = (String) map.get("contractAprTpl");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return templateCode;
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
    			templateCode = (String) reposeMap.get("contractAprTpl");
    			busCode = reposeMap.get("busCode").toString();
    			hashMap.put("templateCode", templateCode);
    			hashMap.put("busCode", busCode);
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return hashMap;
    }

    /**
     * 根据合同No查找该合同门店的乙转甲变更单号
     *
     * @param contractNo
     * @return
     * @throws Exception
     */
    private String getcontractB2AChangeNo(String contractNo) {
        String contractChangeNo = "";

        //根据合同标号取得门店信息（门店唯一）
        //根据门店信息取得该门店的 变更单号
        try {
            ResultData<?> resultData = contractService.getcontractB2AChangeNo(contractNo);
            Map<?, ?> mapTemp = (Map<?, ?>) resultData.getReturnData();
            contractChangeNo = mapTemp.get("contractStopNo").toString();
        } catch (Exception e) {
            logger.error("contract", "OaService", "getcontractB2AChangeNo", "", null, "", "根据合同No查找该合同门店的乙转甲变更单号", e);
            return "";

        }
        return contractChangeNo;
    }

    /**
     * 更具合同ID取得门店资质等级
     *
     * @param contractId
     * @return
     */
    private String getabTypeStore(Integer contractId,Integer contractTp) {
        String abTypeStore = "";

        //根据合同标号取得门店信息（门店唯一）
        //根据门店信息取得该门店的 变更单号
        try {
            ResultData<?> resultData = contractService.getById(contractId);
            Map<?, ?> mapTemp = (Map<?, ?>) resultData.getReturnData();
            List<Map<?, ?>> storeListmaop = (List<Map<?, ?>>) mapTemp.get("storeDetails");
            for (Map<?, ?> map : storeListmaop) {
               /// String abtype = map.get("abtypeStore").toString();
            	 String abtype = "";
            	 if (map.containsKey("abtypeStore")&&map.get("abtypeStore")!=null){
            		 abtype = map.get("abtypeStore").toString();
            	 }

                String storeNo = map.get("storeNo").toString();
                String storeAddress = map.get("addressDetail").toString();

                abTypeStore = abTypeStore + storeNo + " " + storeAddress;

                //如果是授牌模式，不传甲类乙类
                if (DictionaryConstants.CONTRACT_TYPE_AWARDING != contractTp){
                    if (abtype.equals("")) {
                        continue;
                    }
                    Integer type = Integer.parseInt(abtype);
                    if (DictionaryConstants.Store_QualityGrade_A == type.intValue()) {
                        abTypeStore = abTypeStore + " 甲类";
                    } else {
                        abTypeStore = abTypeStore + " 乙类(" + map.get("btypeStoreName").toString() + ")";
                    }
                }
            }
        } catch (Exception e) {
            logger.error("contract", "OaController", "getcontractB2AChangeNo", "", null, "", "根据合同ID取得门店资质等级", e);
            return "";

        }
        return abTypeStore;
    }

    /**
     * 上传操作-调OA文件上传系统
     *
     * @param request
     * @return
     * @throws Exception
     */
    public String oaFileAssist(HttpServletRequest request)
            throws Exception {
        String attachmentId = "";
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver =
                new CommonsMultipartResolver(request.getSession().getServletContext());

        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            /** 获取到传递到后台的参数 根据不同的fileTypeId 去判断不同的上传图片的类型 比如身份证 营业执照等 */
            String fileTypeId = multiRequest.getParameter("fileTypeId");
            String fileTypeName = this.spellFileTypeName(fileTypeId);
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                MultipartFile importFile = multiRequest.getFile(iter.next());

                //文件名
                String fileName = importFile.getOriginalFilename();
                /** 重新处理文件名 拼接上文件业务类型 营业执照 身份证等信息 */
                fileName = new StringBuffer(fileTypeName).append(fileName).toString();
                //实例化类
                FileAssist fileAssist = new FileAssist();

                File tempFile = null;

                //获取绝对路径
                String tempFilePath = SystemParam.getWebConfigValue("oaTempFilePath");
                String absolutePath = fileAssist.getTmpRealPath(request, fileName, tempFilePath);

                //如果上传图片大于3M，进行图片压缩，否则直接上传
                if (importFile.getSize() > 3500000) {
                    //MultipartFile转File
                    CommonsMultipartFile cf = (CommonsMultipartFile) importFile;
                    DiskFileItem fi = (DiskFileItem) cf.getFileItem();
                    File temp = fi.getStoreLocation();
                    //图片压缩到文件绝对路径,生成临时文件
                    Boolean boo = ImageCompressUtil.getInstance().imageCompressRatio(temp, absolutePath, 0.5d);
                    if (!boo) {
                        logger.error("Contacts", "OaController", "imageCompressRadio", "", UserInfoHolder.getUserId(), "", "", null);
                        throw new Exception("压缩图片出错");
                    }
                    //删除临时文件
                    temp.delete();
                    tempFile = new File(absolutePath);
                } else {
                    //生成临时文件
                    tempFile = new File(absolutePath);
                    importFile.transferTo(tempFile);
                }
                //上传ＯＡ系统此处有验证，需是主办人才行
                String senderLoginName = UserInfoHolder.get().getUserCode();
                //查询是否是经办人
                ResultData<?> backResult = oaOperatorService.getByUserCode(senderLoginName);

                Map<?, ?> mapTemp = (Map<?, ?>) backResult.getReturnData();

                OaOperatorDto oaOperatorDto = new OaOperatorDto();

                if (null != mapTemp) {
                    oaOperatorDto = MapToEntityConvertUtil.convert(mapTemp, OaOperatorDto.class, "");
                }

                String operCode = oaOperatorDto.getOperCode();
                // 如果当前用户不是经办人 则使用数据库中配置的默认值
                if (StringUtil.isEmpty(operCode)) {
                    senderLoginName = SystemParam.getWebConfigValue("operCode");
                }
                //上传操作-调OA文件上传系统
                attachmentId = oaAbsolutePathUpload(absolutePath, fileName, senderLoginName);

                if (tempFile != null) {
                    //删除临时文件
                    String delfilePath = tempFile.getParent();
                    tempFile.delete();
                    if (delfilePath != null) {
                        (new File(delfilePath)).delete();
                    }
                }
            }
        }

        return attachmentId;
    }

    /**
     * @param fileTypeId
     * @return 根据不同的fileTypeId 拼接不同的文件类型
     */
    private final String spellFileTypeName(String fileTypeId) {
        StringBuffer sbf = new StringBuffer();
        switch (fileTypeId) {
            case "1":
                sbf.append("营业执照_");
                break;
            case "2":
                sbf.append("身份证_");
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
            case "1020":
            	sbf.append("重要提示函_");
            	break;
            case "1026":
            	sbf.append("补充协议_");
            	break;
            case "6":
                sbf.append("其他_");
                break;
                

        }
        return sbf.toString();
    }

    /**
     * 上传附件至OA并取得返回值
     *
     * @param attachList
     * @return
     */
    private List<String> sendAttachMentToOa(List<Long> attachList, Integer contractId, Integer fileTypeId, UserInfo userInfo,String picStr,String tempFilePath) throws Exception {
        List<String> picList = new ArrayList<>();
        Map<String, Object> param = new HashMap<>();
        String attachStrs = "";
        param.put("refId", contractId);
        param.put("fileTypeId", fileTypeId);

        ResultData<?> backResult = fileService.getContractFile(param);
        if (null != backResult && "200".equals(backResult.getReturnCode())) {
            List<Map> filesRel = (List<Map>) backResult.getReturnData();
            if (null != filesRel && filesRel.size() > 0) {
                for (Map map : filesRel) {
                    String fileUrl = map.get("fileUrl").toString();
                    String fileFullName = map.get("fileFullName").toString();
                    /** 获取到传递到后台的参数 根据不同的fileTypeId 去判断不同的上传图片的类型 比如身份证 营业执照等 */
                    String fileFirstName = this.spellFileTypeName(fileTypeId + "");
                    /** 重新处理文件名 拼接上文件业务类型 营业执照 身份证等信息 */
                    String fileName = new StringBuffer(fileFirstName).append(fileFullName).toString();
                    String attachmentId = oaAttachmentUpload(fileUrl, fileName, userInfo.getUserCode(),tempFilePath);
                    if (StringUtil.isNotEmpty(attachmentId)) {
                        picList.add(attachmentId);
                        attachList.add(Long.valueOf(attachmentId));
                        if (StringUtil.isEmpty(attachStrs)) {
                            attachStrs = attachmentId;
                        } else {
                            attachStrs = attachStrs + "|" + attachmentId;
                        }
                        //将返回的附件编号存入表
                        ContractInfoDto contractInfoDto = new ContractInfoDto();
                        ContractDto contract = new ContractDto();
                        contract.setId(contractId);
                        switch (fileTypeId){
                            case 1://营业执照
                                contract.setOaFileIdBusiness(attachStrs);
                                break;
                            case 2://身份证
                                contract.setOaFileIdCard(attachStrs);
                                break;
                            case 3://合同照片
                                contract.setOaFileIdPhoto(attachStrs);
                                break;
                            case 4://门店照片
                                contract.setOaFileIdStore(attachStrs);
                                break;
                            case 5://房友确认单
                                contract.setOaFileIdInstall(attachStrs);
                                break;
                            case 1020://重要提示函
                                contract.setOaFileIdNotice(attachStrs);
                                break;
                            case 1026://补充协议
                            	contract.setOaFileIdComplement(attachStrs);
                            	break;
                            case 6://其它
                                contract.setOaFileIdOther(attachStrs);
                                break;
                        }
                        contractInfoDto.setContract(contract);
                        contractService.updateByContractId(contractInfoDto);
                    }
                }
            }
        }else{//有可能是老数据
            if (StringUtil.isEmpty(picStr)) {
                picStr = "-1";
            }
            setAttachList(attachList, picStr);
            //转list
            picList = setPicList(picStr);
        }
        return picList;
    }

    /**
     * 汇总附件，把所有图片加到附件集合中，附件String转List
     * @param attachList
     * @param attachStr
     */
    public void setAttachList(List<Long> attachList, String attachStr)
    {
        if (StringUtil.isNotEmpty(attachStr) && !"-1Login name does not exist".equals(attachStr))
        {
            String[] attach = attachStr.split("\\|");
            if (null != attach && 0 != attach.length)
            {
                for (String subAttach : attach)
                {
                    attachList.add(Long.valueOf(subAttach));
                }
            }
        }
    }

    /**
     * 单个附件String转List
     * @param attachStr
     */
    public List<String> setPicList(String attachStr)
    {
        List<String> attachList = new ArrayList<String>();

        if (StringUtil.isNotEmpty(attachStr) && !"-1Login name does not exist".equals(attachStr))
        {
            String[] attach = attachStr.split("\\|");
            if (null != attach && 0 != attach.length)
            {
                for (String subAttach : attach)
                {
                    attachList.add(String.valueOf(subAttach));
                }
            }
        }

        return attachList;
    }
    
    public void sendZipAttachMentToOa(List<Long> attachList, String tempFilePath, String userCode, String contractNo, String type) {
        
        String zipName = type + contractNo+".zip";
        
        UploadFileUtil.createZipWithSplit(tempFilePath, tempFilePath+File.separator+zipName,40);
        
        // 取得REST动态客户机实例
        CTPRestClient client = getClient();
        // 获取token认证
        String token = client.get("token/" + SystemParam.getWebConfigValue("oaUserName") + "/" + SystemParam.getWebConfigValue("oaPassword"), String.class, "text/plain");

        File d = new File(tempFilePath+File.separator);
        File[] files = d.listFiles(new ZipFileFiltger(type + contractNo));
        for(File f : files) {
            String attachmentId = OaAttachmentUtil.oaZipUpload(SystemParam.getWebConfigValue("oaUrl"), token, userCode,f.getAbsolutePath(), f.getName());
            
            if(StringUtil.isNotEmpty(attachmentId)) {
                attachList.add(Long.valueOf(attachmentId));
            }
        }
        
        UploadFileUtil.delFolder(tempFilePath);
    }

    public ResultData<?> getOpinionsNew(String flowId) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "/oa/getOAAuditInfo/{flowId}";
        ResultData<?> result = get(url, flowId);
        return result;
    }
}

class ZipFileFiltger implements FilenameFilter{
    
    private String preName;

    public ZipFileFiltger(String preName) {
        this.preName = preName;
    }

    @Override
    public boolean accept(File dir, String name) {
        
        return name.startsWith(preName);
        
    }
    
}