package cn.com.eju.deal.user.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.shiro.AuthShiroRealm;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.dto.user.Post;
import cn.com.eju.deal.user.service.LoginService;
import cn.com.eju.deal.user.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController extends BaseController
{
    
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    private static final String POSTCG_TYPE="postCg";
    
    @Resource(name = "loginService")
    private LoginService loginService;
    
    @Resource(name = "userService")
    private UserService userService;

    
    @RequestMapping(value = "users/center", method = RequestMethod.GET)
    public String loginForm(HttpServletRequest request, ModelMap mop)
    {
        mop.put("headMenuIdSelect", "201");
        return "user/userCenter";
    }
    
    /** 
    * 初始化
    * @param request
    * @return
    */
    @RequestMapping(value = "/pwd", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap mop)
    {
        return "user/resetPwdPopup";
    }
    
    /**
    * 短信获取验证码
    * @param request
    * @return
    */
    @RequestMapping(value = "/code", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> getVerificationC(HttpServletRequest request)
    {
        
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        Map<String, Object> reqMap = bindParamToMap(request);
        
        String userCode = (String)reqMap.get("userCode");
        
        if (StringUtil.isEmpty(userCode))
        {
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            
            return getSearchJSONView(rspMap);
        }
        
        CloseableHttpClient client = HttpClientBuilder.create().build();
        
        // 封装要发送的内容
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        
        // 建立HTTPost对象
        String url = SystemParam.getWebConfigValue("office_url") + "/office/user/getVerificationC?userCode=" + userCode;
        
        HttpPost httpRequest = new HttpPost(url);
        
        try
        {
            // 添加请求参数到请求对象
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
            httpRequest.setEntity(entity);
            
            // 发送请求并等待响应
            HttpResponse response = client.execute(httpRequest);
            if (200 != response.getStatusLine().getStatusCode())
            {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                
                return getSearchJSONView(rspMap);
            }
            
        }
        catch (Exception e)
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            
            return getSearchJSONView(rspMap);
        }
        
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        
        return getOperateJSONView(rspMap);
    }
    
    /**
     * 重置密码
     * @param request
     * @return
     */
    @RequestMapping(value = "/pwd", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> setNewPassWord(HttpServletRequest request)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        Map<String, Object> reqMap = bindParamToMap(request);
        
        String userCode = (String)reqMap.get("userCode");
        
        String authCode = (String)reqMap.get("authCode");
        String password = (String)reqMap.get("password");
        
        if (StringUtil.isEmpty(userCode) || StringUtil.isEmpty(password) || StringUtil.isEmpty(authCode))
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            
            return getSearchJSONView(rspMap);
        }
        
        CloseableHttpClient client = HttpClientBuilder.create().build();
        
        // 封装要发送的内容
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        
        // 建立HTTPost对象
        String url =
            SystemParam.getWebConfigValue("office_url") + "/office/user/setNewPassWord?userCode=" + userCode
                + "&password=" + password + "&verificationCode=" + authCode;
        HttpPost httpRequest = new HttpPost(url);
        String responseJson = null;
        try
        {
            // 添加请求参数到请求对象
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
            httpRequest.setEntity(entity);
            // 发送请求并等待响应
            HttpResponse response = client.execute(httpRequest);
            if (200 == response.getStatusLine().getStatusCode())
            {
                responseJson = EntityUtils.toString(response.getEntity(), "UTF-8");
                JSONObject resultJson = JSONObject.parseObject(responseJson);
                Integer returnCode = resultJson.getInteger("returnCode");
                if (returnCode != 200)
                {
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    
                    return getSearchJSONView(rspMap);
                }
                
            }
            else
            {
                
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                
                return getSearchJSONView(rspMap);
            }
            
        }
        catch (Exception e)
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            
            return getSearchJSONView(rspMap);
        }
        
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        
        return getOperateJSONView(rspMap);
        
    }
    
    /**
     * 改变岗位
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("user/changePost")
    @ResponseBody
    public ReturnView<?, ?> changePost(HttpServletRequest request)
    {
        UserInfo userInfo = UserInfoHolder.get();
        Integer userId = userInfo.getUserId();
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        String selectedPostId = (String)reqMap.get("selectedPostId");
        try{
            ResultData<?> resultData = userService.getNewUserInfo(userId, Integer.valueOf(selectedPostId));
            //存放userInfo
            UserInfo newuserInfo = null;
            Map<?, ?> mapTemp = (Map<?, ?>)resultData.getReturnData();
            if (null != mapTemp)
            {
               newuserInfo = MapToEntityConvertUtil.convert(mapTemp, UserInfo.class, "");
               if (null == newuserInfo.getSelectPost()) {
                   List<Post> postList = newuserInfo.getPostList();
                   if (null != postList && !postList.isEmpty()) {
                       for (int i=0;i<postList.size();i++){
                           Map map=(Map)postList.get(i);
                           Integer postId=(Integer)map.get("postId");
                           if (postId.equals(Integer.valueOf(selectedPostId))) {
                               Post post=MapToEntityConvertUtil.convert(map, Post.class, "");
                               newuserInfo.setSelectPost(post);
                           }
                       }
                   }
               }
            }
            UserInfoHolder.set(newuserInfo);
            //用户信息放session
            WebUtil.addSession(request, Constant.SESSION_KEY_USERINFO, newuserInfo);
            
           // LoginController.isHaveAuth(newuserInfo, request, POSTCG_TYPE);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
            rspMap.put(Constant.RETURN_DATA_KEY, WebUtil.getValueFromSession(request, "headUrl"));
            
            //清除原有权限列表
            RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();  
            AuthShiroRealm shiroRealm = (AuthShiroRealm)rsm.getRealms().iterator().next();  
            String principal=(String) SecurityUtils.getSubject().getPrincipal();
            shiroRealm.clearCachedAuthorizationInfo(principal);
        }catch(Exception e) {
            logger.error("login", "LoginController", "changPost", "", userInfo.getUserId(), "", "", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            return getOperateJSONView(rspMap);
        }
        return getOperateJSONView(rspMap);
    }



    /**
     * 改变岗位
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("user/changeCity")
    @ResponseBody
    public ReturnView<?, ?> changeCity(HttpServletRequest request)
    {
        UserInfo userInfo = UserInfoHolder.get();
        Integer userId = userInfo.getUserId();
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        String selectedCityNo = (String)reqMap.get("selectedCityNo");
        try{
            ResultData<?> resultData = userService.getNewPmlsUserInfo(userId,selectedCityNo);
            //存放userInfo
            UserInfo newuserInfo = null;
            Map<?, ?> mapTemp = (Map<?, ?>)resultData.getReturnData();
            if (null != mapTemp)
            {
                newuserInfo = MapToEntityConvertUtil.convert(mapTemp, UserInfo.class, "");
            }
            UserInfoHolder.set(newuserInfo);
            //用户信息放session
            WebUtil.addSession(request, Constant.SESSION_KEY_USERINFO, newuserInfo);


            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
            rspMap.put(Constant.RETURN_DATA_KEY, WebUtil.getValueFromSession(request, "headUrl"));

            //清除原有权限列表
            RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
            AuthShiroRealm shiroRealm = (AuthShiroRealm)rsm.getRealms().iterator().next();
            String principal=(String) SecurityUtils.getSubject().getPrincipal();
            shiroRealm.clearCachedAuthorizationInfo(principal);
        }catch(Exception e) {
            logger.error("login", "LoginController", "changeCity", "", userInfo.getUserId(), "", "", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            return getOperateJSONView(rspMap);
        }
        return getOperateJSONView(rspMap);
    }
}
