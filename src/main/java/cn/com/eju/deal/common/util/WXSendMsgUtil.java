package cn.com.eju.deal.common.util;

import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.service.RedisService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.common.WXPushInfoDto;
import cn.com.eju.deal.service.StoreAuditService;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import sun.net.www.protocol.http.HttpURLConnection;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

/**
 * Created by xu on 2017/7/25.
 */
@Lazy(false)
@Component
public class WXSendMsgUtil {
//    public static String radisUrl="http://bus.ehousechina.com/RedisService.asmx";

    @Resource(name = "redisService")
    private RedisService redisService;
    private static WXSendMsgUtil wxSendMsgUtil;

    @PostConstruct
    public void init() {
        wxSendMsgUtil = this;
        wxSendMsgUtil.redisService = this.redisService;

    }

    /**
     * 发送https请求
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {

            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            jsonObject = JSONObject.parseObject(buffer.toString());
        } catch (ConnectException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * 发送http请求
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {

            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            jsonObject = JSONObject.parseObject(buffer.toString());
        } catch (ConnectException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 获取接口访问凭证
     * @param corpid 凭证
     * @param corpsecret 密钥
     * @return
     */
    public static String getAccessToken(String corpid, String corpsecret) {
        String token_url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=CORPID&corpsecret=CORPSECRET";
        String access_token = "";
        String requestUrl = token_url.replace("CORPID", corpid).replace("CORPSECRET", corpsecret);

        ResultData resultData =  wxSendMsgUtil.redisService.getVal(corpsecret);
        Object o = resultData.getReturnData();
        if(o != null){
            access_token = o.toString();
        }
        if(StringUtil.isEmpty(access_token)){
            // 发起GET请求获取凭证
            JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
            if (null != jsonObject) {
                try {
                    access_token = jsonObject.getString("access_token");
                } catch (JSONException e) {
                    access_token = null;
                }
            }
            wxSendMsgUtil.redisService.setVal(corpsecret,access_token,60*60);
        }


        return access_token;
    }


    // 凭证获取（GET）
    public final static String token_url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=CORPID&corpsecret=CORPSECRET";
    /**
     * @param touser 成员ID列表
     * @param toparty 部门ID列表
     * @param totag 标签ID列表
     * @param msgtype 消息类型，此时固定为：text（支持消息型应用跟主页型应用）
     * @param agentid 企业应用的id，整型。可在应用的设置页面查看
     * @param content 消息内容，最长不超过2048个字节，注意：主页型应用推送的文本消息在微信端最多只显示20个字（包含中英文）
     * @return int 表示是否是保密消息，0表示否，1表示是，默认0
     */
    public static int Send_msg(String touser,String toparty,String totag,String msgtype,int agentid,String content){



        String SEND_MSG_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";
        String corpid = SystemParam.getWebConfigValue("wechatCorpID");
        String secret = SystemParam.getWebConfigValue("msgSecret");

        String accessToken = WXSendMsgUtil.getAccessToken(corpid, secret);

        int retCode = chkWXtouser(touser, accessToken);
        if (retCode != 0) {
            return retCode;
        }

        int errCode=0;
        //拼接请求地址
        String requestUrl=SEND_MSG_URL.replace("ACCESS_TOKEN", accessToken);
        //需要提交的数据
        String outputStr="";
        JSONObject jsonObject=new JSONObject();
        JSONObject textObject=new JSONObject();//文本对象
        textObject.put("content",content);
        jsonObject.put("agentid",agentid);
        jsonObject.put("touser",touser);
        jsonObject.put("toparty",toparty);
        jsonObject.put("totag",totag);
        jsonObject.put("msgtype",msgtype);
        jsonObject.put("text",textObject);
        outputStr=jsonObject.toString();
        System.out.println(outputStr);
        //创建成员
        JSONObject object=WXSendMsgUtil.httpsRequest(requestUrl, "POST", outputStr);
        if(null!=object){
            System.out.println(object.toString()+"=====");

            WXPushInfoDto wxPushInfoDto=new WXPushInfoDto();
            wxPushInfoDto.setPushType(msgtype);
            wxPushInfoDto.setPushWechatUserId(touser);
            wxPushInfoDto.setPushContent(content);
            if(object.toString().length()>200) {
                wxPushInfoDto.setPushResult(object.toString().substring(0, 200));
            }else {
                wxPushInfoDto.setPushResult(object.toString());
            }
            try{
                StoreAuditService storeAuditService=new StoreAuditService();
                ResultData resultData= storeAuditService.addPushInfo(wxPushInfoDto);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return errCode;
    }

    public static int chkWXtouser(String touser,String accessToken){

        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=" + touser;
        String requestUrl=url.replace("ACCESS_TOKEN", accessToken);

        String outputStr = "";
        JSONObject object = WXSendMsgUtil.httpsRequest(requestUrl, "GET", outputStr);
        if (object != null) {
            String code = "-1";
            if(object.containsKey("errcode")){
                code = object.get("errcode").toString();
            }

            if("0".equals(code)){
                return 0;
            }else{
                return -1;
            }

        }
        return -1;
    }
    /**
     * @param touser 成员ID列表
     * @param toparty 部门ID列表
     * @param totag 标签ID列表
     * @param msgtype 消息类型，此时固定为：text（支持消息型应用跟主页型应用）
     * @param agentid 企业应用的id，整型。可在应用的设置页面查看
     * @param content 消息内容，最长不超过2048个字节，注意：主页型应用推送的文本消息在微信端最多只显示20个字（包含中英文）
     * @return int 表示是否是保密消息，0表示否，1表示是，默认0
     */
    public static int Send_PMLSWechat_Msg(String touser,String toparty,String totag,String msgtype,int agentid,String content){
        String SEND_MSG_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";
        String corpid = SystemParam.getWebConfigValue("wechatCorpID");
        String secret = SystemParam.getWebConfigValue("PMLSWechatMsgSecret");

        String accessToken = WXSendMsgUtil.getAccessToken(corpid, secret);

        int retCode = chkWXtouser(touser, accessToken);
        if (retCode != 0) {
            return retCode;
        }

        int errCode=0;
        //拼接请求地址
        String requestUrl=SEND_MSG_URL.replace("ACCESS_TOKEN", accessToken);
        //需要提交的数据
        String outputStr="";
        JSONObject jsonObject=new JSONObject();
        JSONObject textObject=new JSONObject();//文本对象
        textObject.put("content",content);
        jsonObject.put("agentid",agentid);
        jsonObject.put("touser",touser);
        jsonObject.put("toparty",toparty);
        jsonObject.put("totag",totag);
        jsonObject.put("msgtype",msgtype);
        jsonObject.put("text",textObject);
        outputStr=jsonObject.toString();
        System.out.println(outputStr);
        //创建成员
        JSONObject object=WXSendMsgUtil.httpsRequest(requestUrl, "POST", outputStr);
        if(null!=object){
            System.out.println(object.toString()+"=====");

            WXPushInfoDto wxPushInfoDto=new WXPushInfoDto();
            wxPushInfoDto.setPushType(msgtype);
            wxPushInfoDto.setPushWechatUserId(touser);
            wxPushInfoDto.setPushContent(content);
            if(object.toString().length()>200) {
                wxPushInfoDto.setPushResult(object.toString().substring(0, 200));
            }else {
                wxPushInfoDto.setPushResult(object.toString());
            }
            try{
                StoreAuditService storeAuditService=new StoreAuditService();
                ResultData resultData= storeAuditService.addPushInfo(wxPushInfoDto);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return errCode;
    }

    /***
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        //参数：成员ID，部门ID，标签ID，消息类型，企业应用的id，消息内容
        //注意：部门不为""或者标签不为""，将会给该组的每个成员发送消息
        //所以给某个单独的成员推送消息，请将二、三参数设为空
//        String msgStr="门店%s请求变更经纬度，请前往审核 \n <a href=\"http://www.baidu.com\">>> 查看详情</a>";
//        msgStr=String.format(msgStr, "测试门店(MD01001)");
//        Send_msg("132337","","","text",4,msgStr);

    }

}
