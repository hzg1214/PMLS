package cn.com.eju.deal.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.WXSendMsgUtil;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.dto.storeAudit.StoreNewDto;
import cn.com.eju.deal.service.StoreAuditService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by xu on 2017/4/19.
 */
@Controller
@RequestMapping(value = "storeAudit")
public class StoreAuditController extends BaseController{
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource(name = "storeAuditService")
    private StoreAuditService storeAuditService;

    @RequestMapping(value = "/getStoreList", method = RequestMethod.GET)
    public String getStoreList(HttpServletRequest request, ModelMap mop, HttpServletResponse response)
    {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        UserInfo userInfo = UserInfoHolder.get();
//        reqMap.put("userCode",userInfo.getUserCode());
        reqMap.put("userId",userInfo.getUserId());
        ResultData<?> resultData = new ResultData<>();
        PageInfo pageInfo = getPageInfo(request);
        try
        {
            resultData = storeAuditService.getStoreList(reqMap, pageInfo);
            resultData.setSuccess();
            mop.put("contentlist", resultData.getReturnData());
        }
        catch (Exception e)
        {
            logger.error("PMLSWeb", "StoreAuditController", "getStoreList", "", null, "", "获取门店列表", e);
        }
        return "crm/storeAuditListCtx";
    }
    @RequestMapping(value = "/getStoreById/{storeId}", method = RequestMethod.GET)
    public String getStoreInfo(@PathVariable("storeId") Integer storeId, ModelMap mop)
    {
        StoreNewDto storeNewDto=new StoreNewDto();
        storeNewDto.setStoreId(storeId);
        ResultData<?> resultData = new ResultData<>();
        try
        {
            resultData = storeAuditService.getStoreById(storeNewDto);
            resultData.setSuccess();
            mop.put("storeDetail", resultData.getReturnData());
        }
        catch (Exception e)
        {
            logger.error("PMLSWeb", "StoreAuditController", "getStoreById", storeId+"", null, "", "获取门店信息", e);
        }
        return "crm/storeDetail";
    }

    @ResponseBody
    @RequestMapping(value = "/auditPass", method = RequestMethod.GET)
    public ResultData auditPass(HttpServletRequest request, ModelMap mop)
    {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData resultData = new ResultData();
        UserInfo userinfo = UserInfoHolder.get();
        try {
            String pushInfoUrl= SystemParam.getWebConfigValue("pushInfoUrl");
            Integer agentId= Integer.parseInt(SystemParam.getWebConfigValue("agentId"));

            StoreNewDto storeNewDto = (StoreNewDto) MapToEntityConvertUtil.convert(reqMap,StoreNewDto.class,"");
            storeNewDto.setAuditStatus(20);
            storeNewDto.setUserCreate(userinfo.getUserId());
            //审核通过，门店营业状态置为正常营业
            storeNewDto.setBusinessStatus(20901);
            resultData = storeAuditService.updateStoreAuditStatus(storeNewDto);

            String msgStr="门店审核结果通知\n门店 %s 审核已通过。";
            msgStr=String.format(msgStr, storeNewDto.getStoreName()+"("+storeNewDto.getStoreNo()+")",storeNewDto.getAuditReturnReason());
            String userCode=storeNewDto.getUserCode();
            if(pushInfoUrl.indexOf("demo")>=0){
                if(!"14029".equals(userCode) && !"115096".equals(userCode)&& !"75672".equals(userCode)&& !"11543".equals(userCode)){
                    userCode="75672";
                }
               // WXSendMsgUtil.Send_msg(userCode,"","","text",agentId,msgStr);

                WXSendMsgUtil.Send_PMLSWechat_Msg(userCode,"","","text",agentId, msgStr);
            }else{
               // WXSendMsgUtil.Send_msg(userCode,"","","text",agentId,msgStr);
                WXSendMsgUtil.Send_PMLSWechat_Msg(userCode,"","","text",agentId, msgStr);
            }
        }
        catch (Exception e)
        {
            logger.error("PMLSWeb", "StoreAuditController", "auditPass", request.toString(), null, "", "审核通过", e);
        }
        return resultData;
    }
    @ResponseBody
    @RequestMapping(value = "/auditReturn", method = RequestMethod.GET)
    public ResultData auditReturn(HttpServletRequest request, ModelMap mop)
    {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData resultData = new ResultData();
        UserInfo userinfo = UserInfoHolder.get();
        try
        {
            String pushInfoUrl= SystemParam.getWebConfigValue("pushInfoUrl");
            Integer agentId= Integer.parseInt(SystemParam.getWebConfigValue("agentId"));

            StoreNewDto storeNewDto =  MapToEntityConvertUtil.convert(reqMap,StoreNewDto.class,"");
            storeNewDto.setAuditStatus(30);
            storeNewDto.setUserCreate(userinfo.getUserId());
            resultData = storeAuditService.updateStoreAuditStatus(storeNewDto);

            String msgStr="门店审核结果通知\n门店 %s 审核被驳回，驳回原因：%s 。";
            msgStr=String.format(msgStr, storeNewDto.getStoreName()+"("+storeNewDto.getStoreNo()+")",storeNewDto.getAuditReturnReason());
            String userCode=storeNewDto.getUserCode();
            if(pushInfoUrl.indexOf("demo")>=0){
                if(!"14029".equals(userCode) && !"115096".equals(userCode)&& !"75672".equals(userCode)&& !"11543".equals(userCode)){
                    userCode="75672";
                }
               // WXSendMsgUtil.Send_msg(userCode,"","","text",agentId,msgStr);

                WXSendMsgUtil.Send_PMLSWechat_Msg(userCode,"","","text",agentId, msgStr);
            }else{
               // WXSendMsgUtil.Send_msg(userCode,"","","text",agentId,msgStr);

                WXSendMsgUtil.Send_PMLSWechat_Msg(userCode,"","","text",agentId, msgStr);
            }
        }
        catch (Exception e)
        {
            logger.error("PMLSWeb", "StoreAuditController", "auditReturn", request.toString(), null, "", "审核驳回", e);
        }
        return resultData;
    }
    @RequestMapping(value = "toDeposit/{storeId}/{storeName}/{storeNo}/{userCode}", method = RequestMethod.GET)
    public String toDeposit(@PathVariable("storeId") Integer storeId,@PathVariable("storeName") String storeName,
                            @PathVariable("storeNo") String storeNo,@PathVariable("userCode") String userCode, ModelMap mop)
    {
        mop.put("storeId",storeId);
        mop.put("storeName",storeName);
        mop.put("storeNo",storeNo);
        mop.put("userCode",userCode);
        return "crm/storeDeposit";

    }
}
