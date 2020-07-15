package cn.com.eju.deal.keFuOrder.service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.WXSendMsgUtil;
import cn.com.eju.deal.core.support.ResultData;

import java.util.Map;

import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.kefuOrder.KeFuOrderDto;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.json.JsonObject;


@Service("keFuOrderContractService")
public class KeFuOrderContractService extends BaseService {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
	/**
     * 查询列表
     */
    public ResultData getKeFuOrderList(Map<String, Object> reqMap, PageInfo pageInfo)throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "keFuOrder" + "/getKeFuOrderList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    } 
    /**
     * 根据id查询客服反馈工单详情
     * @throws Exception
     */
    public ResultData<?> getKeFuOrderById(Integer id) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "keFuOrder" + "/getKeFuOrderById/{id}";
        ResultData<?> backResult = get(url, id);
        return backResult;
    }
    /**
     * 保存或更新客服反馈工单
     * @param reqMap
     * @throws Exception
     */
    public ResultData<?> saveKeFuOrder(Map<String, Object> reqMap) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "keFuOrder" + "/saveKeFuOrder";
        ResultData<?> reback = post(url,reqMap);

        if("200".equals(reback.getReturnCode())){
            try{
                // 保存后返回的信息
                KeFuOrderDto dto = JSONObject.parseObject(JsonUtil.parseToJson(reback.getReturnData()), KeFuOrderDto.class);

                // 判断 经办人与当前操作者是否一致
                if(dto!=null && dto.getUserCode() != null && !dto.getUserCode().equals(UserInfoHolder.get().getUserCode())) {
                    // 当工单提交为未处理或处理中状态
                    if (dto.getDealStatus() != null && (dto.getDealStatus() == 24201 || dto.getDealStatus() == 24202)) {
                        // 判断 经办人与当前操作者是否一致
                        if (dto.getUserCode() != null && !dto.getUserCode().equals(UserInfoHolder.get().getUserCode())) {
                            String msgStr ="";
                            String userCode = dto.getUserCode() ;
                            String pushInfoUrl= SystemParam.getWebConfigValue("pushInfoUrl");
                            Integer agentId= Integer.parseInt(SystemParam.getWebConfigValue("agentId"));
                            if (dto.getStoreId() != null) {
                                // 如果门店已选择，系统自动微信通知工单的经办人。
                                // 通知内容格式：“经纪公司：”经纪公司+一级问题类型，“问题概要：”问题概要内容，“请点击回复！”
                                 msgStr = "<a href=\"" +pushInfoUrl+ "/kefuOrder/index?kfgdid=" + dto.getId() +"\"> "+
                                         dto.getCompanyName()+dto.getCategoryOneNm()+",问题概要:"+dto.getQuestionTopic()+",请点击回复！</a>";

                            } else {
                                // 如果门店未选择，系统自动微信通知工单的经办人。
                                // 通知内容格式：“客户”：客户姓名+（电话）一级问题类型，“问题概要：”问题概要内容，“请点击回复！”
                                msgStr = "<a href=\"" +pushInfoUrl+ "/kefuOrder/index?kfgdid=" + dto.getId() +"\"> "+
                                        dto.getCustomerName()+"("+ dto.getCustomerTel()+")"+dto.getCategoryOneNm()+",问题概要:"+dto.getQuestionTopic()+",请点击回复！</a>";
                            }

                            if(pushInfoUrl.indexOf("demo")>=0){
                                if(!"14029".equals(userCode) && !"115096".equals(userCode)&& !"75672".equals(userCode)&& !"11543".equals(userCode)
                                        && !"117960".equals(userCode) && !"104967".equals(userCode)&&!"132337".equals(userCode)
                                        ){
                                    userCode = "14220";
                                }
                                WXSendMsgUtil.Send_PMLSWechat_Msg(userCode,"","","text",agentId, msgStr);
                            }else{
                                WXSendMsgUtil.Send_PMLSWechat_Msg(userCode,"","","text",agentId, msgStr);
                            }
                        }
                    }
                }
            }catch (Exception ex){
                logger.error("keFuOrder", "KeFuOrderContractService", "saveKeFuOrder", reqMap.toString(), null, "", "系统自动微信通知工单发生异常", ex);
            }
        }


        return reback;
    }
    /**
     * 查询经办人列表
     */
    public ResultData<?> getOperatorList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "keFuOrder" + "/getOperatorList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }
    /**
     * 根据一级分类查询二级分类列表
     * @throws Exception
     */
    public ResultData<?> getCategoryTwo(Map<String, Object> reqMap)throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "keFuOrder" + "/getCategoryTwo/{param}";
        ResultData<?> backResult = get(url, reqMap);
        return backResult;
    }
    
    /**
     * 
     * desc:回复
     * 2019年3月20日
     * author:zhenggang.Huang
     */
    public ResultData<?> saveKeFuReply(Map<String, Object> reqMap) throws Exception{
    	String url = SystemParam.getWebConfigValue("RestServer") + "keFuOrder" + "/saveKeFuReply";
    	ResultData<?> reback = post(url,reqMap);
        if("200".equals(reback.getReturnCode())){
            try{
                // 保存后返回的信息
                KeFuOrderDto dto = JSONObject.parseObject(JsonUtil.parseToJson(reback.getReturnData()), KeFuOrderDto.class);

                // 判断 经办人与当前操作者是否一致
                if(dto!=null && dto.getUserCode() != null && !dto.getUserCode().equals(UserInfoHolder.get().getUserCode())) {
                    // 处理中的场合（当工单提交为未处理或处理中状态）
                    if (dto.getDealStatus() != null && (dto.getDealStatus() == 24201 || dto.getDealStatus() == 24202)) {
                        String msgStr ="";
                        String userCode = dto.getUserCode() ;
                        String pushInfoUrl= SystemParam.getWebConfigValue("pushInfoUrl");
                        Integer agentId= Integer.parseInt(SystemParam.getWebConfigValue("agentId"));

                        if (dto.getStoreId() != null) {
                            // 如果门店已选择，系统自动微信通知工单的经办人。
                            // 通知内容格式：“经纪公司：”经纪公司+一级问题类型，“问题概要：”问题概要内容，“请点击回复！”
                            msgStr = "<a href=\"" +pushInfoUrl+ "/kefuOrder/index?kfgdid=" + dto.getId() +"\"> "+
                                    dto.getCompanyName()+dto.getCategoryOneNm()+",问题概要:"+dto.getQuestionTopic()+",请点击回复！</a>";

                        } else {
                            // 如果门店未选择，系统自动微信通知工单的经办人。
                            // 通知内容格式：“客户”：客户姓名+（电话）一级问题类型，“问题概要：”问题概要内容，“请点击回复！”
                            msgStr = "<a href=\"" +pushInfoUrl+ "/kefuOrder/index?kfgdid=" + dto.getId() +"\"> "+
                                    dto.getCustomerName()+"("+ dto.getCustomerTel()+")"+dto.getCategoryOneNm()+",问题概要:"+dto.getQuestionTopic()+",请点击回复！</a>";
                        }

                        if(pushInfoUrl.indexOf("demo")>=0){
                            if(!"14029".equals(userCode) && !"115096".equals(userCode)&& !"75672".equals(userCode)&& !"11543".equals(userCode)
                                    && !"117960".equals(userCode) && !"104967".equals(userCode)&&!"132337".equals(userCode)
                                    ){     userCode = "14220";
                            }
                            WXSendMsgUtil.Send_PMLSWechat_Msg(userCode,"","","text",agentId, msgStr);
                        }else{
                            WXSendMsgUtil.Send_PMLSWechat_Msg(userCode,"","","text",agentId, msgStr);
                        }
                    }
                }
            }catch (Exception ex){
                logger.error("keFuOrder", "KeFuOrderContractService", "saveKeFuReply", reqMap.toString(), null, "", "系统自动微信通知工单发生异常", ex);
            }
        }

    	return reback;
    }

    /**
     *
     * desc:工单核验
     */
    public ResultData<?> saveKeFuVerify(Map<String, Object> reqMap) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "keFuOrder" + "/saveKeFuVerify";
        ResultData<?> reback = post(url,reqMap);

        if("200".equals(reback.getReturnCode())){
            try{
                // 保存后返回的信息
                KeFuOrderDto dto = JSONObject.parseObject(JsonUtil.parseToJson(reback.getReturnData()), KeFuOrderDto.class);

                // 判断 经办人与当前操作者是否一致或工单创建人与经办人是否一致
                if(dto!=null && dto.getUserCode() != null
                        && (!dto.getUserCode().equals(UserInfoHolder.get().getUserCode()) || !dto.getUserName().equals(dto.getCreateName()))
                ) {
                    String msgStr ="";
                    String passMsgStr ="";
                    String userCode = dto.getUserCode() ;
                    String pushInfoUrl= SystemParam.getWebConfigValue("pushInfoUrl");
                    Integer agentId= Integer.parseInt(SystemParam.getWebConfigValue("agentId"));
                    String checkDesc = (String)reqMap.get("checkDesc");
                    if(checkDesc.length()>55){
                        checkDesc= checkDesc.substring(0,50) + "...";
                    }
                    String questionTopic = dto.getQuestionTopic();
                    if(questionTopic.length()>55){
                        questionTopic= questionTopic.substring(0,50) + "...";
                    }
                    if (dto.getStoreId() != null) {
                        // 如果门店已选择，系统自动微信通知工单的经办人。
                        // 通知内容格式：“经纪公司：”经纪公司“的客户反馈内容核验不通过”，“具体描述：”+核验内容，“请点击回复！”
                        msgStr = "<a href=\"" +pushInfoUrl+ "/kefuOrder/index?kfgdid=" + dto.getId() +"\"> "+
                                dto.getCompanyName()+dto.getCategoryOneNm()+"的客户反馈内容核验不通过，具体描述："+checkDesc+",请点击回复！</a>";
                        passMsgStr = "<a href=\"" +pushInfoUrl+ "/kefuOrder/index?kfgdid=" + dto.getId() +"\"> "+
                                dto.getCompanyName()+dto.getCategoryOneNm()+"，问题概要："+questionTopic+",已核验通过，请点击查看！</a>";
                    } else {
                        // 如果门店未选择，则系统自动微信通知工单的经办人。
                        // 通知内容格式：“客户”：客户姓名+（电话）的客户反馈内容核验不通过”，“具体描述：”+核验内容，“请点击回复！”
                        msgStr = "<a href=\"" +pushInfoUrl+ "/kefuOrder/index?kfgdid=" + dto.getId() +"\"> "+
                                dto.getCustomerName()+"("+ dto.getCustomerTel()+")"+dto.getCategoryOneNm()+"的客户反馈内容核验不通过，具体描述："+checkDesc+",请点击回复！</a>";
                        passMsgStr = "<a href=\"" +pushInfoUrl+ "/kefuOrder/index?kfgdid=" + dto.getId() +"\"> "+
                                dto.getCustomerName()+"("+ dto.getCustomerTel()+")"+dto.getCategoryOneNm()+"，问题概要："+questionTopic+",已核验通过，请点击查看！</a>";
                    }
                    if(pushInfoUrl.indexOf("demo")>=0){
                        if(!"14029".equals(userCode) && !"115096".equals(userCode)&& !"75672".equals(userCode)&& !"11543".equals(userCode)
                                && !"117960".equals(userCode) && !"104967".equals(userCode)&& !"132337".equals(userCode)
                                ){
                            userCode = "14220";
                        }

                        if (dto.getCheckStatus() != null && dto.getCheckStatus() == 24303) {
                            // 驳回的场合（当集团客服对工单核验不通过后）
                            WXSendMsgUtil.Send_PMLSWechat_Msg(userCode, "", "", "text", agentId, msgStr);
                        }else if(dto.getCheckStatus() != null && dto.getCheckStatus() == 24302){
                            //核验通过
                            WXSendMsgUtil.Send_PMLSWechat_Msg(userCode, "", "", "text", agentId, passMsgStr);
                        }
                    }else{
                        if (dto.getCheckStatus() != null && dto.getCheckStatus() == 24303) {
                            // 驳回的场合（当集团客服对工单核验不通过后）
                            WXSendMsgUtil.Send_PMLSWechat_Msg(userCode, "", "", "text", agentId, msgStr);
                        }else if(dto.getCheckStatus() != null && dto.getCheckStatus() == 24302){
                            //核验通过
                            WXSendMsgUtil.Send_PMLSWechat_Msg(userCode, "", "", "text", agentId, passMsgStr);
                        }
                    }
                }
            }catch (Exception ex){
                logger.error("keFuOrder", "KeFuOrderContractService", "saveKeFuVerify", reqMap.toString(), null, "", "系统自动微信通知工单发生异常", ex);
            }
        }

        return reback;
    }

    /**
     * 根据storeId查看客服反馈工单列表
     * @throws Exception
     */
    public ResultData<?> getKeFuOrderListByStoreId(Integer storeId) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "keFuOrder" + "/getKeFuOrderListByStoreId/{storeId}";
        ResultData<?> backResult = get(url, storeId);
        return backResult;
    }

    /**
     * 再次提醒经办人
     * @param reqMap
     * @return
     */
    public ResultData<?> reAlert(Map<String, Object> reqMap) throws Exception{

        ResultData<?> reback = new ResultData<>();
        ResultData<?> resultData = this.getKeFuOrderById(Integer.valueOf(reqMap.get("id").toString()));
        if(!"200".equals(resultData.getReturnCode())){
            reback.setFail("提醒经办人失败");
            return reback;
        }

        KeFuOrderDto dto = JSONObject.parseObject(JsonUtil.parseToJson(resultData.getReturnData()), KeFuOrderDto.class);

        if(dto.getDealStatus().intValue() == 24203){
            reback.setFail("工单已处理完成，无法再次提醒经办人");
            return reback;
        }

        String msgStr ="";
        String userCode = dto.getUserCode() ;
        String pushInfoUrl= SystemParam.getWebConfigValue("pushInfoUrl");
        Integer agentId= Integer.parseInt(SystemParam.getWebConfigValue("agentId"));
        if (dto.getStoreId() != null) {
            // 如果门店已选择，系统自动微信通知工单的经办人。
            // 通知内容格式：“经纪公司：”经纪公司+一级问题类型，“问题概要：”问题概要内容，“请点击回复！”
            msgStr = "<a href=\"" +pushInfoUrl+ "/kefuOrder/index?kfgdid=" + dto.getId() +"\"> "+
                    dto.getCompanyName()+dto.getCategoryOneNm()+",问题概要:"+dto.getQuestionTopic()+",请点击回复！</a>";

        } else {
            // 如果门店未选择，系统自动微信通知工单的经办人。
            // 通知内容格式：“客户”：客户姓名+（电话）一级问题类型，“问题概要：”问题概要内容，“请点击回复！”
            msgStr = "<a href=\"" +pushInfoUrl+ "/kefuOrder/index?kfgdid=" + dto.getId() +"\"> "+
                    dto.getCustomerName()+"("+ dto.getCustomerTel()+")"+dto.getCategoryOneNm()+",问题概要:"+dto.getQuestionTopic()+",请点击回复！</a>";
        }

        if(pushInfoUrl.indexOf("demo")>=0){
            if(!"14029".equals(userCode) && !"115096".equals(userCode)&& !"75672".equals(userCode)&& !"11543".equals(userCode)
                    && !"117960".equals(userCode) && !"104967".equals(userCode)&&!"132337".equals(userCode)
            ){
                userCode = "14220";
            }
            WXSendMsgUtil.Send_PMLSWechat_Msg(userCode,"","","text",agentId, msgStr);
        }else{
            WXSendMsgUtil.Send_PMLSWechat_Msg(userCode,"","","text",agentId, msgStr);
        }

        reback.setReturnMsg("提醒经办人成功");
        return reback;
    }
}
