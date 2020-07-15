package cn.com.eju.deal.contract.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.helper.SeqNoHelper;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.base.util.XmlTransferUtil;
import cn.com.eju.deal.common.service.CommonService;
import cn.com.eju.deal.contract.service.AchievementCancelService;
import cn.com.eju.deal.contract.service.ContractService;
import cn.com.eju.deal.contract.service.OaService;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.contract.AchievementCancelDto;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.contract.ContractInfoDto;
import cn.com.eju.deal.dto.oa.OaOperatorDto;
import cn.com.eju.deal.oa.service.OaOperatorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 门店业绩撤销
* @author (wushuang)
* @date 2016年10月11日 上午10:27:32
 */
@Controller
@RequestMapping(value = "achievement")
public class AchievementCancelController extends BaseController
{
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
    
    @Resource(name = "achievementCancelService")
    private AchievementCancelService achievementCancelService;
    
    @Resource(name = "oaOperatorService")
    private OaOperatorService oaOperatorService;
    
    @Resource(name = "oaService")
    private OaService oaService;
    
    @Resource(name="contractService")
    private ContractService contractService;
    
    @Resource(name="commonService")
    private CommonService commonService;
    
    /**
     * 跳转到业绩撤销记录
     */
    @RequestMapping(value = "/cancel/{contractId}/{contractStatus}", method = RequestMethod.GET)
    public String goAchievementCancel(@PathVariable("contractId") Integer contractId,
        @PathVariable("contractStatus") Integer contractStatus, ModelMap mop)
    {
        ResultData<?> reback = new ResultData<>();
        ResultData<?> back = new ResultData<>();
        ResultData<?> rebackContractInfo = new ResultData<>();
        Boolean isOperator = true;
        
        // 1.获取门店业绩撤销历史记录
        List<?> contentlist = new ArrayList<>();
        try
        {
            // 根据合同ID查询业绩撤销记录
            reback = achievementCancelService.getAchievementCancelRecord(contractId);
            // 页面数据
            contentlist = (List<?>)reback.getReturnData();
            
            //2.根据合同Id 查询是否存在最新合同
//            back = achievementCancelService.getNewestContract(contractId);
            
            
            //3.是否是经办人
            ResultData<?> backResult = oaOperatorService.getByUserCode(UserInfoHolder.get().getUserCode());
            Map<String,Object> map = (Map<String, Object>)backResult.getReturnData();
            if("null".equals(String.valueOf(map.get("operId")))){
                isOperator = false;
            }
            
            //
    		// 根据合同ID查询合同信息
			rebackContractInfo = contractService.getContractById(contractId);
        }
        catch (Exception e)
        {
            logger.error("contract",
                "AchievementCancelController",
                "goAchievementCancel",
                "",
                UserInfoHolder.getUserId(),
                "",
                "门店业绩撤销历史记录查询失败！",
                e);
        }
        mop.addAttribute("contentlist", contentlist);
        mop.addAttribute("isExistNewest", back.getReturnData());
        mop.addAttribute("isOperator", isOperator);
        
        mop.addAttribute("contractId", contractId);
        mop.addAttribute("contractStatus", contractStatus);
        mop.addAttribute("contractInfo", rebackContractInfo.getReturnData());
        return "contract/achievementCancel";
        
    }
    
    /**
     * 跳转到业绩撤销申请页面
     */
    @RequestMapping(value = "/cancel/goAchievementCancelAdd", method = RequestMethod.GET)
    public String goachievementCancelAdd(HttpServletRequest request, ModelMap mop)
    {
        String contractId = request.getParameter("contractId");
        ResultData<?> resultData = new ResultData<>();
        List<?> storeList = new ArrayList<>();
        try
        {
            //查询申请事业区
            ResultData<?> backResult = oaOperatorService.getByUserCode(UserInfoHolder.get().getUserCode());
            
            Map<?, ?> mapTemp = (Map<?, ?>)backResult.getReturnData();
            if (null != mapTemp)
            {
                OaOperatorDto oaOperatorDto = MapToEntityConvertUtil.convert(mapTemp, OaOperatorDto.class, "");
                String busCode = oaOperatorDto.getBusCode();
                Map<String, String> busMap = strToList(busCode);
                mop.addAttribute("busMap", busMap);
                mop.addAttribute("oaOperator", oaOperatorDto);
            }
            
            //查询    
            resultData = achievementCancelService.getAchievementCancelInfo(Integer.valueOf(contractId));
            storeList = (List<?>)resultData.getReturnData();
            Map<String, Object> temp = (Map<String, Object>)storeList.get(0);
            
            mop.put("companyName", temp.get("companyName"));
            mop.put("contractTypeStr", temp.get("contractTypeStr"));
            mop.put("storeList", resultData.getReturnData());
            
        }
        catch (Exception e)
        {
            logger.error("contract",
                "AchievementCancelController",
                "goachievementCancelAdd",
                "",
                UserInfoHolder.getUserId(),
                "",
                "根据合同Id查业绩撤销门店,查询失败",
                e);
        }
        return "contract/achievementCancelAdd";
        
    }
    
    /**
     * 
    * 查看门店撤销详情
    * @param achievementCancelNo
    * @param contractId
    * @param mop
    * @return
     */
    @RequestMapping(value = "/toView/{contractStatus}/{achievementCancelNo}/{contractId}", method = RequestMethod.GET)
    public String getInfoToView(@PathVariable("contractStatus") Integer contractStatus,
        @PathVariable("achievementCancelNo") String achievementCancelNo,
        @PathVariable("contractId") Integer contractId, ModelMap mop)
    {
        ResultData<AchievementCancelDto> resultData = new ResultData<AchievementCancelDto>();
        
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("achievementCancelNo", achievementCancelNo);
        reqMap.put("contractId", contractId);
        
        // 1.获取撤销详细信息
        try
        {
            // 根据合同ID和门店撤销编号获取撤销详细信息
            resultData = (ResultData<AchievementCancelDto>)achievementCancelService.getInfoToView(reqMap);
            if (resultData.getReturnCode().equals(ReturnCode.FAILURE))
            {
                mop.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
                mop.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
                return "contract/achievementCancelDetail";
            }
        }
        catch (Exception e)
        {
            logger.error("contract",
                "AchievementCancelController",
                "getInfoToView",
                "",
                UserInfoHolder.getUserId(),
                "",
                "查看门店撤销详情查询失败！",
                e);
        }
        mop.addAttribute("content", resultData.getReturnData());
        mop.addAttribute("contractId", contractId);
        mop.addAttribute("contractStatus", contractStatus);
        return "contract/achievementCancelDetail";
    }
    
    /**
     * 
    * 门店撤销业绩申请
    * @param request
    * @param modelMap
    * @return
     * @throws Exception 
     */
    @ResponseBody
    @RequestMapping(value = "cancel/application", method = RequestMethod.POST)
    public ReturnView<?, ?> doSaveAndApplication(HttpServletRequest request, ModelMap modelMap)
    {
        
        // 返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        // OAmap
        Map<String, Object> oaMap = new HashMap<String, Object>();
        
        // 获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        
        //获取门店撤销编号
        String achievementCancelNo = SeqNoHelper.getInstance().getSeqNoByTypeCode("TYPE_BUSINESS_CANCEL");
        
        try
        {
            //查询所选门店是否已签新合同
            ResultData<?> storeBack = achievementCancelService.checkStoreSignContract(reqMap);
            if(ReturnCode.FAILURE.equals(storeBack.getReturnCode())){
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, storeBack.getReturnMsg());
                return getOperateJSONView(rspMap);
            }
        }
        catch (Exception e2)
        {
            logger.error("contract", "AchievementCancelController", "checkStoreSignContract", "", 0, "", "检查门店是否新签合同失败", e2);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "检查门店是否新签合同失败！");
            return getOperateJSONView(rspMap);
        }
        
        try{  
        	//撤销CHECK
             String mesg  = this.cancelCheck(reqMap);
             if(!"".equals(mesg))
             {
		          rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
		          rspMap.put(Constant.RETURN_MSG_KEY, mesg);
		          return getOperateJSONView(rspMap);
		     }
        }catch(Exception e3)
        {
        	logger.error("contract", "AchievementCancelController", "checkStoreSignContract", "", 0, "", "查询门店装修记录失败！", e3);
        	rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "查询门店装修记录失败！");
            return getOperateJSONView(rspMap);
        }
        
        try
        {
            //获取模板
            //            String templateStr = oaService.getOaTemplate("-4911125240892538636");
            //            System.out.println(templateStr);
        	Integer contractId = Integer.valueOf(reqMap.get("contractId").toString());
            String cancelTemplateCode = getOATemplateCode(contractId);

            //组装表单发起数据
            try
            {
                setToOaInfo(oaMap, reqMap, achievementCancelNo, cancelTemplateCode);
            }
            catch (Exception e1)
            {
                logger.error("Contract",
                    "AchievementCancelController",
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
            
            //发起申请 返回FlowId
            Long flowId;
            try
            {
                // 合同变更OA模板
                String oaTemplateCode = cancelTemplateCode;//SystemParam.getWebConfigValue("oaTemplateCode_MDCX");
                // 合同变更提交OA审核
                flowId = oaService.toOaAuth(oaMap, oaTemplateCode);
            }
            catch (Exception e)
            {
                logger.error("contract", "AchievementCancelController", "toOaAuth", "", 0, "", "", e);
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "向OA发起提交审核失败");
                return getOperateJSONView(rspMap);
            }
            reqMap.put("flowId", flowId);
            
            //保存在本地,创建Store_AchievementCancel数据和更新contractStore中的门店撤销状态
            ResultData<?> back = achievementCancelService.createNewRecord(reqMap);
            if (back.getReturnCode().equals(ReturnCode.FAILURE))
            {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, back.getReturnMsg());
                return getSearchJSONView(rspMap);
            }
            
        }
        catch (Exception e)
        {
            logger.error("contract",
                "AchievementCancelController",
                "doSaveAndApplication",
                "",
                UserInfoHolder.getUserId(),
                "",
                "保存，发起撤销申请失败！",
                e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "发起撤销申请失败！");
            return getOperateJSONView(rspMap);
        }
        return getSearchJSONView(rspMap);
    }
    
    /**
     * 
    * 跳转到门店变更页面
    * @param request
    * @param mop
    * @return
     */
    @RequestMapping(value = "/cancel/goAchievementCancelEdit", method = RequestMethod.GET)
    public String goachievementCancelEdit(HttpServletRequest request, ModelMap mop)
    {
        String contractId = request.getParameter("contractId");
        String achievementCancelNo = request.getParameter("achievementCancelNo");
        ResultData<?> resultData = new ResultData<>();
        List<?> storeList = new ArrayList<>();
        try
        {
            //查询申请事业区
            ResultData<?> backResult = oaOperatorService.getByUserCode(UserInfoHolder.get().getUserCode());
            
            Map<?, ?> mapTemp = (Map<?, ?>)backResult.getReturnData();
            if (null != mapTemp)
            {
                OaOperatorDto oaOperatorDto = MapToEntityConvertUtil.convert(mapTemp, OaOperatorDto.class, "");
                String busCode = oaOperatorDto.getBusCode();
                Map<String, String> busMap = strToList(busCode);
                mop.addAttribute("busMap", busMap);
                mop.addAttribute("oaOperator", oaOperatorDto);
            }
            
            //查询    
            resultData = achievementCancelService.getAchievementCancelInfo(Integer.valueOf(contractId));
            storeList = (List<?>)resultData.getReturnData();
            Map<String, Object> temp = (Map<String, Object>)storeList.get(0);
            
            
            mop.put("companyName", temp.get("companyName"));
            mop.put("contractTypeStr", temp.get("contractTypeStr"));
            mop.put("achievementCancelNo", achievementCancelNo);
            
            mop.put("storeList", resultData.getReturnData());
            
        }
        catch (Exception e)
        {
            logger.error("contract",
                "AchievementCancelController",
                "goachievementCancelEdit",
                "",
                UserInfoHolder.getUserId(),
                "",
                "跳转到门店编辑页面,查询失败",
                e);
        }
        return "contract/achievementCancelEdit";
    }
    
    /**
     * 
    * 门店业绩撤销变更申请
    * @param request
    * @param modelMap
    * @return
     * @throws Exception 
     */
    @ResponseBody
    @RequestMapping(value = "cancel/editApplication", method = RequestMethod.POST)
    public ReturnView<?, ?> doSaveAndEditApplication(HttpServletRequest request, ModelMap modelMap)
    {
        
        // 返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        // OAmap
        Map<String, Object> oaMap = new HashMap<String, Object>();
        
        // 获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        
        //获取门店撤销编号
        String achievementCancelNo = (String)reqMap.get("achievementCancelNo");
        
        //更新时间
        reqMap.put("updateDate",new Date());
        
        try{  
        	//撤销CHECK
             String mesg  = this.cancelCheck(reqMap);
             if(!"".equals(mesg))
             {
		          rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
		          rspMap.put(Constant.RETURN_MSG_KEY, mesg);
		          return getOperateJSONView(rspMap);
		     }
        }catch(Exception e3)
        {
        	logger.error("contract", "AchievementCancelController", "checkStoreSignContract", "", 0, "", "查询门店装修记录失败！", e3);
        	rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "查询门店装修记录失败！");
            return getOperateJSONView(rspMap);
        }
        
        try
        {
        	Integer contractId = Integer.valueOf(reqMap.get("contractId").toString());
            String cancelTemplateCode = getOATemplateCode(contractId);
            //组装表单发起数据
            try
            {
                setToOaInfo(oaMap, reqMap, achievementCancelNo, cancelTemplateCode);
            }
            catch (Exception e1)
            {
                logger.error("Contract",
                    "AchievementCancelController",
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
            
            //发起申请 返回FlowId
            Long flowId;
            try
            {
                // 合同变更OA模板
                String oaTemplateCode = cancelTemplateCode;//SystemParam.getWebConfigValue("oaTemplateCode_MDCX");
                // 合同变更提交OA审核
                flowId = oaService.toOaAuth(oaMap, oaTemplateCode);
            }
            catch (Exception e)
            {
                logger.error("contract", "AchievementCancelController", "toOaAuth", "", 0, "", "", e);
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "向OA发起提交审核失败");
                return getOperateJSONView(rspMap);
            }
            reqMap.put("flowId", flowId);
            
            //根据变更编号更新本地数据,更新Store_AchievementCancel数据和更新contractStore中的门店撤销状态
            ResultData<?> back = achievementCancelService.updateCancelRecord(reqMap);
            if (back.getReturnCode().equals(ReturnCode.FAILURE))
            {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, back.getReturnMsg());
                return getSearchJSONView(rspMap);
            }
            
        }
        catch (Exception e)
        {
            logger.error("contract",
                "AchievementCancelController",
                "doSaveAndApplication",
                "",
                UserInfoHolder.getUserId(),
                "",
                "变更，发起撤销申请失败！",
                e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "发起撤销申请失败！");
            return getOperateJSONView(rspMap);
        }
        return getSearchJSONView(rspMap);
    }
    
    /******************************** Warnning! - The-Follow-Function-Is-PrivateFunction - Warnning! ******************** *********************************************************/
    /** 
     * 组装表单发起数据
     * @param id
     * @param reqMap
     * @param reqMap2 
     * @param achievementCancelNo 
     */
    private void setToOaInfo(Map<String, Object> oaMap, Map<String, Object> reqMap, String achievementCancelNo, String templateCode)
    {
        
        //模板编号
        oaMap.put("templateCode", templateCode);//SystemParam.getWebConfigValue("oaTemplateCode_MDCX"));
        
        //为登录验证后获取的身份令牌
        //reqMap.put("token", oaToken);
        
        //发起者的登录名（登录协同的登录名）
        String senderLoginName = UserInfoHolder.get().getUserCode();
        //String senderLoginName = "14879";
        oaMap.put("senderLoginName", senderLoginName);
        
        //协同的标题
        oaMap.put("subject", "易居房产交易中介合作门店撤销申请单");
        
        //表单数据
        Map<String, Object> rspMap = setOaAuditData(achievementCancelNo, reqMap);
        
        //HTML正文流程为html内容；表单流程为XML格式的表单数据
        oaMap.put("data", rspMap.get(XML_DATA_KEY));
        
        //为控制是否流程发送。0：缺省值，发送，进入下一节点的待办（如果需要选人则保存到待发）1：不发送，保存到待发。
        //reqMap.put("param", SystemParam.getWebConfigValue("oaControlFlow"));
        oaMap.put("state", "0");
    }
    
    /** 
      * 组装发送OA审核的 表单数据、附件数据
     * @param achievementCancelNo 
      * @param id
      * @return
      */
    private Map<String, Object> setOaAuditData(String achievementCancelNo, Map<String, Object> reqMap)
    {
        
        //返回表单数据、返回附件数据
        Map<String, Object> rspMap = new HashMap<String, Object>();
        ResultData<?> resultData = new ResultData<>();
        String dateHtml = "";
        
        UserInfo userInfo = UserInfoHolder.get();
        //申请人姓名
        String userName = userInfo.getUserName();
        //申请人工号
        String userCode = userInfo.getUserCode();
        //创建时间
        Date date = new Date();
        //申请日期
        String applicationDate = DateUtil.fmtDate(date, YYYYMMDD_FORMAT);
        //撤销原因
        String cancelReason = (String)reqMap.get("cancelReason");
        //事业部编号
        String busNo = (String)reqMap.get("busNo");
        
        //获取事业部区域
        String bussineArea = this.getBussineArea(busNo);
        
        String dicValue = SystemParam.getWebConfigValue(bussineArea);
        String[] list = new String[0];
        String accountNo = "";
        String accountMain = "";
        if (StringUtils.isNotBlank(dicValue))
        {
            list = dicValue.split("\\|");
            //核算主体编号
            accountNo = list[0];
            //核算主体名称
            accountMain = list[1];
        }
        //公司名称
        String companyName = (String)reqMap.get("companyName");
        //签约合同类型名称
        String contractTypeName = (String)reqMap.get("contractTypeName");
        
        //返回map
        try
        {
            //查询申请所需数据和门店数据
            resultData = achievementCancelService.getOAAuditInfo(reqMap);
        }
        catch (Exception e)
        {
            dateHtml = "";
        }
        
        Map<?, ?> mapTemp = (Map<?, ?>)resultData.getReturnData();
        
        StringBuilder sb = new StringBuilder();
        String contractNo = "";
        String companyNo = "";
        if (null != mapTemp)
        {
            //合同编号
            contractNo = (String)mapTemp.get("contractNo");
            //公司编号
            companyNo = (String)mapTemp.get("companyNo");
            //门店列表
            Map<?, ?> mapTempp = new HashMap<String, Object>();
            List<?> storelist = (List<?>)mapTemp.get("storelist");
            if (storelist.size() > 0)
            {
                for (int i = 0; i < storelist.size(); i++)
                {
                    Map<String, Object> map = (Map<String, Object>)storelist.get(i);
                    String storeNo = (String)map.get("storeNo");
                    String storename = (String)map.get("name");
                    String storeAddress = (String)map.get("address");
                    String decorationStatusName = (String)map.get("decorationStatusName");
                    sb.append("<row>" + "<column name=\"门店店招\"><value>" + XmlTransferUtil.transfer(storename) + "</value></column>"
                        + "<column name=\"门店编号\"><value>" + XmlTransferUtil.transfer(storeNo) + "</value></column>"
                        + "<column name=\"门店地址\"><value>" + XmlTransferUtil.transfer(storeAddress) + "</value></column>"
                        + "<column name=\"装修进度\"><value>" + XmlTransferUtil.transfer(decorationStatusName) + "</value></column>" + "</row>");
                }
            }
        }
        
        //门店列表字串
        String businessAddressXml = sb.toString();
        
        dateHtml +=
            "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<formExport version=\"2.0\">"
                + "<summary id=\"2775493308299628457\" name=\"formmain_3984\"/>" + "<definitions/>" + "<values>"
                + "<column name=\"编码\">" + "<value>"
                + XmlTransferUtil.transfer(achievementCancelNo)
                + "</value>"
                + "</column>"
                + "<column name=\"申请人\">"
                + "<value>"
                + XmlTransferUtil.transfer(userName)
                + "</value>"
                + "</column>"
                + "<column name=\"工号\">"
                + "<value>"
                + XmlTransferUtil.transfer(userCode)
                + "</value>"
                + "</column>"
                + "<column name=\"申请日期\">"
                + "<value>"
                + XmlTransferUtil.transfer(applicationDate)
                + "</value>"
                + "</column>"
                + "<column name=\"核算主体\">"
                + "<value>"
                + XmlTransferUtil.transfer(accountMain)
                + "</value>"
                + "</column>"
                + "<column name=\"核算主体编码\">"
                + "<value>"
                + XmlTransferUtil.transfer(accountNo)
                + "</value>"
                + "</column>"
                + "<column name=\"事业部\">"
                + "<value>"
                + XmlTransferUtil.transfer(bussineArea)
                + "</value>"
                + "</column>"
                + "<column name=\"公司名称\">"
                + "<value>"
                + XmlTransferUtil.transfer(companyName)
                + "</value>"
                + "</column>"
                + "<column name=\"公司编码\">"
                + "<value>"
                + XmlTransferUtil.transfer(companyNo)
                + "</value>"
                + "</column>"
                + "<column name=\"签约类型\">"
                + "<value>"
                + XmlTransferUtil.transfer(contractTypeName)
                + "</value>"
                + "</column>"
                + "<column name=\"撤销原因\">"
                + "<value>"
                + XmlTransferUtil.transfer(cancelReason)
                + "</value>"
                + "</column>"
                + "<column name=\"流程结束时间\">"
                + "<value/>"
                + "</column>"
                + "<column name=\"CRM单据号\">"
                + "<value>"
                + XmlTransferUtil.transfer(contractNo)
                + "</value>"
                + "</column>"
                + "</values>"
                + "<subForms>"
                + "<subForm>"
                + "<definitions>"
                + "<column id=\"field0014\" type=\"0\" name=\"门店店招\" length=\"255\"/>"
                + "<column id=\"field0015\" type=\"0\" name=\"门店编号\" length=\"255\"/>"
                + "<column id=\"field0016\" type=\"0\" name=\"门店地址\" length=\"255\"/>"
                + "<column id=\"field0017\" type=\"0\" name=\"装修进度\" length=\"255\"/>"
                + "</definitions>"
                + "<values>"
                + businessAddressXml + "</values>" + "</subForm>" + "</subForms>" + "</formExport>";
        
        //表单数据
        rspMap.put(XML_DATA_KEY, dateHtml.toString());
        
        logger.info("CRM 门店业绩撤销,提交审核 dateHtml=", dateHtml);
        
        //添加到reqMap,用于更新到本地
        reqMap.put("achievementCancelNo", achievementCancelNo);
        reqMap.put("userCreate", userInfo.getUserId());
        reqMap.put("dateCreate", date);
        reqMap.put("accountSubject", accountNo);
        reqMap.put("busDepartment", bussineArea);
        reqMap.put("isDelete", 0);
        //审核通过后才设定审核时间
        //reqMap.put("approveDate", date);
        
        return rspMap;
    }
    
    /************************************************************************************************************/
    
    /** 
     * 附件String转List
     * @param attachList
     * @param attachStr
     */
    private Map<String, String> strToList(String str)
    {
        Map<String, String> tempMap = new HashMap<String, String>();
        
        if (StringUtil.isNotEmpty(str))
        {
            String[] strArr = str.split("\\|");
            if (null != strArr && 0 != strArr.length)
            {
                
                for (String subArr : strArr)
                {
                    
                    tempMap.put(SystemParam.getWebConfigValue(subArr), SystemParam.getDicValueByDicCode(subArr));
                    
                }
                
            }
        }
        
        return tempMap;
    }
    
    /**
     * 获取事业部区域
     * 
     * @return
     */
    private String getBussineArea(String busNo)
    {
        // 事业部区域
        String bussineArea = null;
        // 查询是否是经办人
        ResultData<?> backResult = new ResultData<>();
        try
        {
            backResult = oaOperatorService.getByUserCode(UserInfoHolder.get().getUserCode());
        }
        catch (Exception e)
        {
            logger.error("contract", "ContractChangeOAController", "getBussineArea", "", null, "", "", e);
        }
        
        Map<?, ?> mapTemp = (Map<?, ?>)backResult.getReturnData();
        
        if (null != mapTemp)
        {
            OaOperatorDto oaOperatorDto = MapToEntityConvertUtil.convert(mapTemp, OaOperatorDto.class, "");
            
            // （取经办人的事业部区域，如果是跨区的，取其选择的区域）
            // 暂时不考虑跨区的情况，后期有需求再改
            Boolean isCombine = oaOperatorDto.getIsCombine();
            if (isCombine)
            {
                bussineArea = busNo;
            }
            else
            {
                bussineArea = oaOperatorDto.getBusCode();
            }
        }
        return bussineArea;
    }
    
    
    /**
     * 获取合同撤销OA审核状态
     * 
     * @param flowId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "oa/changestate/{flowId}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getOaChangeState(HttpServletRequest request,@PathVariable("flowId") String flowId)
    {
        // 返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        Integer reback = -1;
        ResultData<?> rstData = new ResultData<>();
        // 获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        String achievementCancelNo = "";
        try
        {
            achievementCancelNo = (String)reqMap.get("achievementCancelNo");
            // 获取OA审核状态
            reback = oaService.getOaState(flowId);
            
            // 0:审核通过、5:撤销、15:终止
            if (0 == reback || 5 == reback || 15 == reback)
            {
                // 根据 流程Id及审核状态 更新合同信息
                rstData = achievementCancelService.updateCtrctRelateInfo(achievementCancelNo,flowId, reback, UserInfoHolder.getUserId());
                
            }
            
        }
        catch (Exception e)
        {
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
    
    
    public String cancelCheck(Map<String,Object> repMap) throws Exception
    {
    	Map<String,Object> resMap = new HashMap<String, Object>();
    	 String mesg = "";
    	  	
        	//查询门店装修记录
        	 ResultData<?> reback = achievementCancelService.getStoreDecorationList(repMap);
        	 
        	 //String storeIdslist = (String)reqMap.get("storeIds");
             //String[] storeIds = storeIdslist.split(",");
        	 List<Map<String,Object>> list = (List<Map<String, Object>>) reback.getReturnData();
        	 if(list.size()>0)
        	 {
				 String mesgShz = "";
				 String mesgFyw = "";
				 String mesgn = "";
				 String mesgDh = "";
	        	 for (Map<String, Object> map : list) {
	        		String storeNo = map.get("storeNo").toString();
				    String storeName = map.get("storeName").toString();
                    String decorationStatus =map.get("decorationStatus") + "";
                    String oaMdfpStatus =map.get("oaMdfpStatus") + "";
				    //装修状态=未装修并且装修审核状态=审核中报“门店翻牌申请状态为审核中，不允许合同撤销！”	
				    if("16301".equals(decorationStatus) && "16502".equals(oaMdfpStatus))
				    { 
				    	if(!"".equals(mesgShz)){
				    		mesgDh = ","; 	    		
				    	}
				    	mesgShz = mesgShz+mesgDh+storeNo + ":" + storeName;
				    }
				    // 装修状态!=未装修 && 装修状态!=翻拍验收完成
				    if(!"16301".equals(decorationStatus) &&  !"16304".equals(decorationStatus))
				    {
				    	if(!"".equals(mesgFyw)){
				    		mesgDh = ","; 		    		
				    	}
				    	mesgFyw = mesgFyw+mesgDh+storeNo + ":" + storeName;
					}
				  }

				  if(!"".equals(mesgShz)){
					  mesgShz = "【" +mesgShz +"】"+ "门店翻牌申请状态为审核中，不允许合同撤销！";
					  mesgn = "<br/>";
				    	
				  }
				  if(!"".equals(mesgFyw)){     
					  mesgFyw = mesgn + "【" +mesgFyw +"】"+ "翻牌验收完成后才能做合同撤销操作！";      
				    	
				  }
				  mesg = mesgShz +mesgFyw; 
        	 }  
        	 
        	 return mesg;
    }
    
  //合同变更OA模板取得
    private String getOATemplateCode(Integer contractId)
    {
    	String oaTemplateCode = "";
    	String acCityNo = "";
    	
		try {
			ResultData<?> resultData = contractService.getContractById(contractId);
			Map<String,Object> map = (Map<String,Object>)resultData.getReturnData();
	    	if(null != map)
	    	{
	    		acCityNo = map.get("acCityNo").toString(); 
	    	}
		} catch (Exception e) {
			logger.error("contract", "ContractController", "getOATemplateCode", "", UserInfoHolder.getUserId(), "", "业绩归属所属城市获取异常", e);
		}
		
		
		try {			
			ResultData<?> resultData = commonService.getCitySettingByCityNo(acCityNo);
			Map<String,Object> map = (Map<String,Object>)resultData.getReturnData();
	    	if(null != map)
	    	{
	    		oaTemplateCode = map.get("contractAchTpl").toString();
	    	}
		} catch (Exception e) {
			logger.error("contract", "ContractController", "getOATemplateCode", "", UserInfoHolder.getUserId(), "", "合同变更OA模板获取异常", e);
		}
		
		return oaTemplateCode;
    }

    @ResponseBody
    @RequestMapping(value = "/operateAuditCt", method = RequestMethod.POST)
    public ReturnView<?, ?> operateAuditCt(HttpServletRequest request) {
        ResultData<?> rstData = new ResultData<>();
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        String achievementCancelNo = (String)reqMap.get("achievementCancelNo");
        try {
            //更新
            rstData = achievementCancelService.operateAuditCt(achievementCancelNo);
        } catch (Exception e) {
            logger.error("Contract", "AchievementCancelController", "operateAuditCt", "", UserInfoHolder.getUserId(), "", "状态变更失败", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "状态变更失败");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        rspMap.put(Constant.RETURN_MSG_KEY, rstData.getReturnMsg());
        return getOperateJSONView(rspMap);
    }
}
