package cn.com.eju.deal.common.util;

import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.service.RedisService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.util.StringUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 微信推送验证码到企业应用
 * Created by xu on 2017/8/16.
 */
@Lazy(false)
@Component
public class WXPushCodeUtil {
//    public static String radisUrl="http://bus.ehousechina.com/RedisService.asmx";

    @Resource(name = "redisService")
    private RedisService redisService;
    private static WXPushCodeUtil wxPushCodeUtil;

    @PostConstruct
    public void init() {
        wxPushCodeUtil = this;
        wxPushCodeUtil.redisService = this.redisService;

    }

    //发送验证码到微信并记录到radis缓存，有效时间30分钟
    public static JSONObject sendCodeToWX(String systemName,String userCode){
        JSONObject jsonObject=new JSONObject();
        try {

            String idCode = (int)((Math.random()*9+1)*100000) + "";
            //将验证码记录到radis
            ResultData resultData = wxPushCodeUtil.redisService.setVal(systemName +"_"+ userCode,idCode,30*60);

            if( null!=resultData && ReturnCode.SUCCESS.equals(resultData.getReturnCode())){
                //推送验证码给用户
                String msgStr="验证码："+idCode+"，您正在登录"+systemName+"系统，若非本人操作，请勿泄露";
                System.out.println("推送内容："+msgStr);

                String loginModeFlag=SystemParam.getWebConfigValue("LoginModeFlag");
                Integer agentId=Integer.parseInt(SystemParam.getWebConfigValue("msgAgentId"));

                if("1".equals(loginModeFlag)){
                    WXSendMsgUtil.Send_msg(userCode,"","","text",agentId,msgStr);
                }else{
                    //密码登录如果点击了获取验证码推送给75672
                    WXSendMsgUtil.Send_msg("75672","","","text",agentId,msgStr);
                }

                jsonObject.put("code", "200");
                jsonObject.put("msg", "");
                jsonObject.put("data", "");
            }else{
                jsonObject.put("code", "-1");
                jsonObject.put("msg", "");
                jsonObject.put("data", null);
            }
        }catch (Exception e){
            jsonObject.put("code","-1");
            jsonObject.put("msg","获取验证码失败");
            jsonObject.put("data",null);
        }

        return jsonObject;
    }

    //校验验证码是否正确
    public static JSONObject checkCode(String systemName,String userCode,String idCode){
        JSONObject jsonObject=new JSONObject();
        try {

            String qRCode = getIdCode(systemName + "_" + userCode, userCode);

            if (StringUtil.isNotEmpty(qRCode)) {
                if (qRCode.equals(idCode)) {
                    jsonObject.put("code", "200");
                    jsonObject.put("msg", "");
                    jsonObject.put("data", true);
                } else {
                    jsonObject.put("code", "-1");
                    jsonObject.put("msg", "验证码不正确");
                    jsonObject.put("data", false);
                }

            } else {

                jsonObject.put("code", "-1");
                jsonObject.put("msg", "验证码已失效，请重新发送");
                jsonObject.put("data", false);

            }
        }catch (Exception e){
            jsonObject.put("code","-1");
            jsonObject.put("msg","校验验证码失败");
            jsonObject.put("data",null);
        }
        return  jsonObject;
    }

    //获取验证码
    public static String getIdCode(String systemName,String userCode){
        String idCode="";
        try {

            ResultData resultData = wxPushCodeUtil.redisService.getVal(systemName +"_"+userCode);

            if (resultData != null && ReturnCode.SUCCESS.equals(resultData.getReturnCode())) {

                idCode = (String) resultData.getReturnData();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  idCode;
    }

    //测试
    public static void main(String[] args) {
        //发送验证码
        //sendCodeToWX("CRMWeb","75672");
        //校验验证码
        checkCode("CRMWeb","75672","962689");
    }
}
