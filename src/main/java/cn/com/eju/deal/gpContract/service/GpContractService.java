package cn.com.eju.deal.gpContract.service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.base.util.XmlTransferUtil;
import cn.com.eju.deal.common.service.CommonService;
import cn.com.eju.deal.common.service.FileRecordMainService;
import cn.com.eju.deal.contract.service.OaService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.SystemCfg;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.gpContract.GpContractDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.oa.service.OaOperatorService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;

import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.*;

/**
 * Created by haidan on 2018/8/30.
 */

@Service("gpContractService")
public class GpContractService extends BaseService{

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

    @Resource(name = "commonService")
    private CommonService commonService;

    @Resource(name = "oaService")
    private OaService oaService;

    @Resource(name = "fileService")
    private FileRecordMainService fileService;

    @Resource(name = "oaOperatorService")
    private OaOperatorService oaOperatorService;


    /**
     * 新增
     * @param gpContractDto
     * @return
     * @throws Exception
     */
    public ResultData<?> create(GpContractDto gpContractDto) throws Exception
    {

        String url = SystemParam.getWebConfigValue("RestServer") + "gpContract" + "";

        ResultData<?> backResult = post(url, gpContractDto);

        return backResult;
    }

    /**
     * 更新
     * @param gpContractDto
     * @return
     * @throws Exception
     */
    public ResultData<?> update(GpContractDto gpContractDto) throws Exception
    {

        String url = SystemParam.getWebConfigValue("RestServer") + "gpContract" + "/u";

        ResultData<?> backResult = post(url, gpContractDto);

        return backResult;
    }

    /**
     * 更新公盘合同状态
     * @param gpContractDto
     * @throws Exception
     */
    public void updateStatus(GpContractDto gpContractDto) throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "gpContract" + "/status";

        put(url, gpContractDto);
    }

    /**
     * 只更新公盘合同表信息
     * @param gpContractDto
     * @throws Exception
     */
    public void updateBrief(GpContractDto gpContractDto) throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "gpContract" + "/brief";

        put(url, gpContractDto);
    }

    /**
     * 公盘合同撤销
     * @param id
     * @throws Exception
     */
    public void invalid(Integer id) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "gpContract" + "/invalid/{id}";

        delete(url, id);
    }

    /**
     *
     * @param reqMap
     * @param pageInfo
     * @return
     * @throws Exception
     */
    public ResultData<?> queryList(Map<String, Object> reqMap, PageInfo pageInfo)
            throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "gpContract" + "/q/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;

    }
        /**
         * 详情
         * @param id
         * @return
         * @throws Exception
         */
    public ResultData<?> getById (int id) throws Exception
    {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "gpContract" + "/{id}";

        ResultData<?> backResult = get(url, id);

        return backResult;
    }

    /**
     * 更新FlowId OA专用
     * @param gpContractDto
     * @return
     * @throws Exception
     */
    public void updateFlowIdById(GpContractDto gpContractDto)
            throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "gpContract" + "/flowId";
        put(url, gpContractDto);
    }

    /**
     * 审核
     * @param gpContractDto
     * @throws Exception
     */
    public void audit(GpContractDto gpContractDto)
            throws Exception
    {

        String url = SystemParam.getWebConfigValue("RestServer") + "gpContract" + "/audit";

        put(url, gpContractDto);

    }

    /**
     * 公盘提交OA审核
     * @param reqMap
     * @param id
     * @param busNo
     * @param storeId
     * @param userInfo
     * @param companyId
     * @param storeIdArray
     * @return
     * @throws Exception
     */
    @Async("threadPoolTaskExecutor")
    public ResultData toOaGPAuth(Map<String, Object> reqMap, Integer id, String busNo, UserInfo userInfo, String companyId, String storeIdArray) throws Exception {
        //返回结果
        ResultData resultData = new ResultData();
        resultData.setSuccess();
        GpContractDto gpContractDto = new GpContractDto();
        gpContractDto.setId(id);
        String oaNo= "";
        //组装表单发起数据
        try {
            //准备发起OA
            gpContractDto.setSubmitOAStatus(21202);//处理中
            gpContractDto.setSubmitOAUserId(userInfo.getUserId());
            this.updateBrief(gpContractDto);
            Map<String, Object> rsMap = setToOaInfo(id, reqMap, busNo, userInfo);
            if(null != rsMap && rsMap.containsKey("oaNo")){
            	oaNo = rsMap.get("oaNo").toString();
            }
        } catch (Exception e1) {
            logger.error("gpContract", "GpContractService", "setToOaInfo", "", userInfo.getUserId(), "", "", e1);
            gpContractDto.setSubmitOAStatus(21204);//处理失败
            gpContractDto.setSubmitOAUserId(userInfo.getUserId());
            this.updateBrief(gpContractDto);
            resultData.setFail("组装公盘数据错误");
            return resultData;
        }
        logger.info("CRM 提交审核 toOaGPAuth reqMap=", reqMap);
        String templateCode = reqMap.get("templateCode").toString();
        Long flowId;
        try {
            flowId = oaService.toOaAuth(reqMap, templateCode);
        }catch (Exception e) {
            logger.error("gpContract", "GpContractService", "toOaAuth", "", userInfo.getUserId(), "", "向OA发起提交审核失败", e);
            gpContractDto.setSubmitOAStatus(21204);//处理失败
            gpContractDto.setSubmitOAUserId(userInfo.getUserId());
            this.updateBrief(gpContractDto);
            resultData.setFail("向OA发起提交审核失败");
            return resultData;
        }
        //需要入库
        gpContractDto.setFlowId(String.valueOf(flowId));
        gpContractDto.setSubmitOAStatus(21203);//已提交
        gpContractDto.setSubmitOAUserId(userInfo.getUserId());
        if(!"".equals(oaNo)) {
        	gpContractDto.setOaNo(oaNo);
        }
        try {
            this.updateFlowIdById(gpContractDto);
        } catch (Exception e) {
            logger.error("gpContract", "GpContractService", "toOaAuth", "", userInfo.getUserId(), "", "", e);
            resultData.setFail();
            return resultData;
        }
        //审核通过，锁客、锁门店、创建房友账号、更新状态为审核中
        gpContractDto.setCompanyId(Integer.valueOf(companyId));
        List<StoreDto> storeDetails = new ArrayList<StoreDto>();
        if (StringUtil.isNotEmpty(storeIdArray))
        {
            String[] arrays = storeIdArray.split(",");
            for (String array : arrays) {
                StoreDto storeDto = new StoreDto();
                storeDto.setStoreId(Integer.valueOf(array));
                storeDetails.add(storeDto);
            }
            gpContractDto.setStoreDetails(storeDetails);
        }
        try
        {
            //更新
            this.audit(gpContractDto);
        }
        catch (Exception e)
        {
            logger.error("gpContract", "GpContractController", "audit", "", UserInfoHolder.getUserId(), "", "", e);
            resultData.setFail("提交审核CRM操作失败");
            return resultData;
        }
        return resultData;
    }

    /**
     * 组装数据
     * @param id
     * @param reqMap
     * @param busNo
     * @param userInfo
     * @return
     * @throws Exception
     */
    private Map<String, Object> setToOaInfo(Integer id, Map<String, Object> reqMap, String busNo, UserInfo userInfo) throws Exception {
        //发起者的登录名（登录协同的登录名）
        String senderLoginName = userInfo.getUserCode();
        reqMap.put("senderLoginName", senderLoginName);

        //协同的标题
        reqMap.put("subject", "公盘协议报批单");

        //表单数据、附件数据
        Map<String, Object> rspMap = setOaAuditData(id, busNo, userInfo);

        String cityNo = rspMap.get("cityNo").toString();
        reqMap.put("templateCode", getTemplateCode(cityNo,"gpContractTpl"));

        //附件，Long型数组，值为附件的Id。
        reqMap.put("attachments", rspMap.get(ATTACH_KEY));

        //HTML正文流程为html内容；表单流程为XML格式的表单数据
        reqMap.put("data", rspMap.get(XML_DATA_KEY));

        //为控制是否流程发送。0：缺省值，发送，进入下一节点的待办（如果需要选人则保存到待发）1：不发送，保存到待发。
        reqMap.put("state", "0");

        return rspMap;
    }

    private Map<String, Object> setOaAuditData(Integer id, String busNo, UserInfo userInfo) throws Exception
    {
        //返回表单数据、返回附件数据
        Map<String, Object> rspMap = new HashMap<>();
        String dateHtml = "";
        GpContractDto ctDto = new GpContractDto();
        Map<?, ?> mapTemp = null;

        //返回map
        try {
            ResultData<?> resultData = this.getById(id);
            mapTemp = (Map<?, ?>) resultData.getReturnData();
            if (null != mapTemp) {
                ctDto = MapToEntityConvertUtil.convert(mapTemp, GpContractDto.class, "");
            }
        } catch (Exception e) {
            dateHtml = "";
        }

        String oaNo = "FCJY_GPXY"+DateUtil.fmtDate(new Date(),"yyyyMMddHHmmss");
        rspMap.put("oaNo",oaNo);
        //经办人工号
        String operatorId = userInfo.getUserCode();
        //事业部/区域
        String bussineArea = getTemplateCode(ctDto.getCityNo(),"busCode");
        /*//查询是否是经办人
        ResultData<?> backResult = new ResultData<>();
        try {
            backResult = oaOperatorService.getByUserCode(operatorId);
        } catch (Exception e) {
            logger.error("gpContract", "GpContractService", "setOaAuditData", "", null, "", "", e);
        }
        Map<?, ?> mapBusiness = (Map<?, ?>) backResult.getReturnData();
        if (null != mapBusiness) {
            OaOperatorDto oaOperatorDto = MapToEntityConvertUtil.convert(mapBusiness, OaOperatorDto.class, "");
            Boolean isCombine = oaOperatorDto.getIsCombine();
            if (isCombine) {
                bussineArea = busNo;
            } else {
                bussineArea = oaOperatorDto.getBusCode();
            }
        }*/
        //我方签约单位
        String configValue = SystemParam.getWebConfigValue(bussineArea);
        String companyNo = "";
        String companyName = "";
        if (configValue != null && "" != configValue && configValue.contains("|")) {
            String[] values = configValue.split("\\|");
            //公司编码
            companyNo = values[0];
            //公司名称
            companyName = values[1];
        }
        //我方全称
        String ourFullName = ctDto.getOurFullName();
        //合作中介全称
        String partyB = ctDto.getPartyB();
        //公盘合作期限
        String cooperationPeriod = DateUtil.fmtDate(ctDto.getDateLifeStart(), YYYYMMDD_FORMAT) + "至" + DateUtil.fmtDate(ctDto.getDateLifeEnd(), YYYYMMDD_FORMAT);
        //备注
        String remark = ctDto.getRemark();
        //保证金
        BigDecimal depositFee = BigDecimal.valueOf(Double.valueOf(mapTemp.get("depositFee").toString()));//BigDecimal类型特殊处理
        //公盘门店数
        Integer storeNum = ctDto.getStoreNum();
        //公盘门店
        String storeDetail = "";
        List<StoreDto> storeList = ctDto.getStoreDetails();
        if (null != storeList && !storeList.isEmpty()) {
            for (int i = 0; i < storeList.size(); i++) {
                Map<String, Object> storeMap = (Map<String, Object>) storeList.get(i);
                StringBuffer sd = new StringBuffer();
                String addressDetail = (String) storeMap.get("addressDetail");
                String storeNo = (String) storeMap.get("storeNo");
                /*String grade = "";
                Integer ABTypeStore = (Integer) storeMap.get("abtypeStore");
                if (null != ABTypeStore){
                    if(ABTypeStore == DictionaryConstants.Store_QualityGrade_A){
                        grade = "甲类";
                    }else if(ABTypeStore == DictionaryConstants.Store_QualityGrade_B){
                        grade = "乙类(" + storeMap.get("btypeStoreName") + ")";
                    }
                }*/
                sd.append(storeNo).append(" ").append(addressDetail);//.append(" ").append(grade);
                if(StringUtil.isEmpty(storeDetail)){
                    storeDetail = sd.toString();
                }else{
                    storeDetail = storeDetail + System.getProperty("line.separator") + sd.toString();
                }
            }
        }

        //附件集合
        List<Long> attachList = new ArrayList<Long>();

        String tempFilePath = SystemCfg.getString("tempFilePath");
        tempFilePath = MessageFormat.format(tempFilePath, ctDto.getGpContractNo());
        File tempDir = new File(ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath(tempFilePath));
        if (!tempDir.exists()){
            tempDir.mkdirs();
        }
        tempFilePath = tempDir + File.separator;

        //营业执照
        List<String> businessPicList = sendAttachMentToOa(attachList, ctDto.getId(), 1, userInfo, tempFilePath);

        //法人身份证
        List<String> idCardPicList = sendAttachMentToOa(attachList, ctDto.getId(), 2, userInfo, tempFilePath);

        //公盘系统服务协议
        List<String> contractPicList = sendAttachMentToOa(attachList, ctDto.getId(), 3, userInfo, tempFilePath);

        //直联盘勾选表
        List<String> fileCheckList = sendAttachMentToOa(attachList, ctDto.getId(), 1061, userInfo, tempFilePath);

        //易居房友经纪业务共享平台规则
        List<String> fileRuleList = sendAttachMentToOa(attachList, ctDto.getId(), 1062, userInfo, tempFilePath);
        
        //其它照片
        List<String> otherPicList = sendAttachMentToOa(attachList, ctDto.getId(), 6, userInfo, tempFilePath);

        //文件压缩包上传
        oaService.sendZipAttachMentToOa(attachList,tempFilePath,userInfo.getUserCode(),ctDto.getGpContractNo(),"公盘协议报批单");

        dateHtml +=
                "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<formExport version=\"2.0\">"
                        + "<summary id=\"-7825661111783176028\" name=\"formmain_6760\"/>" + "<definitions/>" + "<values>"
                        + "<column name=\"经办人\"><value>"
                        + XmlTransferUtil.transfer(userInfo.getUserName())
                        + "</value></column>"
                        + "<column name=\"经办人工号\"><value>"
                        + XmlTransferUtil.transfer(userInfo.getUserCode())
                        + "</value></column>"
                        + "<column name=\"城市\"><value>"
                        + XmlTransferUtil.transfer(userInfo.getCityName())
                        + "</value></column>"
                        + "<column name=\"城市编码\"><value>"
                        + XmlTransferUtil.transfer(userInfo.getCityNo())
                        + "</value></column>"
                        + "<column name=\"事业部区域\"><value>"
                        + XmlTransferUtil.transfer(bussineArea)
                        + "</value></column>"
                        + "<column name=\"我方签约单位\"><value>"
                        + XmlTransferUtil.transfer(companyName)
                        + "</value></column>"
                        + "<column name=\"我方全称\"><value>"
                        + XmlTransferUtil.transfer(ourFullName)
                        + "</value></column>"
                        + "<column name=\"合作中介全称\"><value>"
                        + XmlTransferUtil.transfer(partyB)
                        + "</value></column>"
                        + "<column name=\"公盘合作期限\"><value>"
                        + XmlTransferUtil.transfer(cooperationPeriod)
                        + "</value></column>"
                        + "<column name=\"公盘协议编号\"><value>"
                        + XmlTransferUtil.transfer(ctDto.getGpAgreementNo())
                        + "</value></column>"
                        + "<column name=\"备注\"><value>"
                        + XmlTransferUtil.transfer(remark)
                        + "</value></column>"
                        + "<column name=\"公盘系统服务协议\"><value>"
                        + "-1"
                        + "</value></column>"
                        + "<column name=\"合作中介营业执照\"><value>"
                        + "-1"
                        + "</value></column>"
                        + "<column name=\"合作中介法定代表人身份证\"><value>"
                        + "-1"
                        + "</value></column>"
                        + "<column name=\"其他\"><value>"
                        + "-1"
                        + "</value></column>"
                        + "<column name=\"保证金金额\"><value>"
                        + depositFee
                        + "</value></column>"
                        + "<column name=\"公盘门店数\"><value>"
                        + storeNum
                        + "</value></column>"
                        + "<column name=\"公盘门店\"><value>"
                        + XmlTransferUtil.transfer(storeDetail)
                        + "</value></column>"
                        + "<column name=\"OA编号\"><value>"
                        + XmlTransferUtil.transfer(oaNo)
                        + "</value></column>"
                        + "<column name=\"供应商性质\"><value>"
                        + XmlTransferUtil.transfer("企业法人")//写死
                        + "</value></column>"
                        + "<column name=\"CRM合同编号\"><value>"
                        + XmlTransferUtil.transfer(ctDto.getGpContractNo())
                        + "</value></column>"
                        + "<column name=\"银行所在省份\"><value>"
                        + XmlTransferUtil.transfer(ctDto.getAccountProvinceNm())
                        + "</value></column>"
                        + "<column name=\"银行账号\"><value>"
                        + XmlTransferUtil.transfer(ctDto.getBankAccount())
                        + "</value></column>"
                        + "<column name=\"开户银行全称\"><value>"
                        + XmlTransferUtil.transfer(ctDto.getBankAccountNm())
                        + "</value></column>"
                        + "<column name=\"统一社会信用代码\"><value>"
                        + XmlTransferUtil.transfer(ctDto.getRegisterId())
                        + "</value></column>"
                        + "<column name=\"银行所在城市\"><value>"
                        + XmlTransferUtil.transfer(ctDto.getAccountCityNm())
                        + "</value></column>"
                        + "<column name=\"供应商分类ID\"><value>"
                        + XmlTransferUtil.transfer("22007")// 写死
                        + "</value></column>"
                        + "<column name=\"我方签约单位编码\"><value>"
                        + XmlTransferUtil.transfer(companyNo)
                        + "</value></column>"
                        + "</values>"
                        + "</formExport>";

        //附件数据
        rspMap.put(ATTACH_KEY, attachList.toArray());
        //表单数据
        rspMap.put(XML_DATA_KEY, dateHtml.toString());
        rspMap.put("cityNo",ctDto.getCityNo());
        logger.info("CRM 提交审核 dateHtml=", dateHtml);
        return rspMap;

    }

    private List<String> sendAttachMentToOa(List<Long> attachList, Integer contractId, Integer fileTypeId, UserInfo userInfo,String tempFilePath) throws Exception {
        List<String> picList = new ArrayList<>();
        Map<String, Object> param = new HashMap<>();
        param.put("refId", contractId);
        param.put("fileTypeId", fileTypeId);

        ResultData<?> backResult = fileService.getGpContractFile(param);
        if (null != backResult && "200".equals(backResult.getReturnCode())) {
            List<Map> filesRel = (List<Map>) backResult.getReturnData();
            if (null != filesRel && filesRel.size() > 0) {
                for (Map map : filesRel) {
                    String fileUrl = map.get("fileUrl").toString();
                    String fileFullName = map.get("fileFullName").toString();
                    /** 获取到传递到后台的参数 根据不同的fileTypeId 去判断不同的上传图片的类型 比如身份证 营业执照等 */
                    String fileFirstName = "";
                    if(fileTypeId ==1){
                        fileFirstName = "营业执照_";
                    }else if(fileTypeId==2){
                        fileFirstName = "身份证_";
                    }else if(fileTypeId==3){
                        fileFirstName = "公盘系统服务协议_";
                    }else if(fileTypeId==6){
                        fileFirstName = "其他_";
                    } else if(fileTypeId==1061){
                        fileFirstName = "直联盘勾选表_";
                    }else if(fileTypeId==1062){
                        fileFirstName = "易居房友经纪业务共享平台规则_";
                    }
                    /** 重新处理文件名 拼接上文件业务类型 营业执照 身份证等信息 */
                    String fileName = new StringBuffer(fileFirstName).append(fileFullName).toString();
                    String attachmentId = oaService.oaAttachmentUpload(fileUrl, fileName, userInfo.getUserCode(),tempFilePath);
                    if (StringUtil.isNotEmpty(attachmentId)) {
                        picList.add(attachmentId);
                        attachList.add(Long.valueOf(attachmentId));
                    }
                }
            }
        }
        return picList;
    }
    //获取模板编号
    public String getTemplateCode(String cityNo,String code) {
        String templateCode = "";
        try {
            ResultData<?> reback = commonService.getCitySettingByCityNo(cityNo);
            Map<String, Object> map = (Map<String, Object>) reback.getReturnData();
            if (map != null) {
                templateCode = (String) map.get(code);//"gpContractTpl");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return templateCode;
    }
    /** 
     * 根据公司ID查询合同状态为未签，审核未通过的公盘合同
     */
    public ResultData<?> getGpContractByCompanyId(Integer companyId)
            throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "gpContract" + "/getGpContractByCompanyId/{companyId}";
        ResultData<?> backResult = get(url, companyId);
        return backResult;
    }
    /**
     * 更新公盘合同中的公司信息
     * @param gpContractDto
     * @return
     * @throws Exception
     */
    public ResultData<?> updateByGpContractId(GpContractDto gpContractDto)
            throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "gpContract" + "/updateByGpContractId";
        ResultData<?> backResult = post(url, gpContractDto);
        return backResult;
    }
    /**
     * 根据门店id更新公盘合同门店表中的地址
     * @param gpContractDto
     * @throws Exception
     */
    public ResultData<?> updateByGpStoreId(Map<String,Object> hashMap)
            throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "gpContract" + "/updateByGpStoreId";
        ResultData<?> backResult = post(url, hashMap);
        return backResult;
    }
    /**
     * 根据公司编号查询其编号
     * @param companyId
     * @throws Exception
     */
    public ResultData<?> selectNewestGpContractByCompanyId(int companyId) throws Exception{
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "gpContract" + "/selectNewestGpContractByCompanyId/{companyId}";

        ResultData<?> backResult = get(url, companyId);

        return backResult;
    }
    public ResultData<?> selectNewestGpContract(Integer storeId) throws Exception{
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "gpContract" + "/selectNewestGpContract/{storeId}";

        ResultData<?> backResult = get(url, storeId);

        return backResult;
    }
    /**
     * 保存银行变更信息
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData saveBankInfoAdjut(Map<String, Object> reqMap)throws Exception {
    	String url = SystemParam.getWebConfigValue("RestServer") + "gpContract" + "/saveBankInfoAdjut";
    	ResultData<?> reback = post(url,reqMap);
    	return reback;
    	
    }
    /**
     * 获取业绩人的中心
     * @throws Exception
     */
    public ResultData<?> getLinkUserCenter(Integer userId)
            throws Exception{
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "gpContract" + "/getLinkUserCenter/{userId}";
        ResultData<?> backResult = get(url, userId);
        return backResult;
   }

    /**
     * @Title: operateChangeCt
     * @Description: 运营维护合同状态
     * @throws Exception
     */
    public void operateChangeCt(GpContractDto gpContractDto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "gpContract" + "/operateChangeCt";
        put(url, gpContractDto);
    }

    public ResultData<?> queryDeposit(Map<String, Object> reqMap) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "gpContract" + "/queryDeposit/{param}";
        ResultData<?> backResult = get(url, reqMap);
        return backResult;
    }

    public ResultData<?> queryFileList(Map<String, Object> reqMap) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "gpContract" + "/queryFileList/{param}";
        ResultData<?> backResult = get(url, reqMap);
        return backResult;
    }
}
