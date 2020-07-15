package cn.com.eju.deal.user.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.shiro.AuthShiroRealm;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.WXPushCodeUtil;
import cn.com.eju.deal.core.support.*;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.dto.user.Auth;
import cn.com.eju.deal.dto.user.Post;
import cn.com.eju.deal.dto.user.UserDto;
import cn.com.eju.deal.user.service.LoginService;
import cn.com.eju.deal.user.service.UserService;
import com.alibaba.fastjson.JSONObject;
import com.eju.core.util.EncryptUtil;
import com.eju.core.util.EncryptUtil.EncryptType;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController extends BaseController
{
    
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    private final static String SYSTEM_NAME = SystemCfg.getString("systemCode");
    
    private final static String LOGIN_TYPE = "Login";
    
    @Resource(name = "loginService")
    private LoginService loginService;
    
    @Resource(name = "userService")
    private UserService userService;
    
    
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginForm(Model model)
    {
        model.addAttribute("user", new UserDto());
        
        return "user/login";
    }
    
    /** 
     * 用户登录 
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> login(HttpServletRequest request)
    {
        
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        
        String username = (String)reqMap.get("userCode");
        String password = (String)reqMap.get("password");
        
        // 验证码校验
        /*String randCode = (String)reqMap.get("randCode");
        if (!StringUtil.isEmpty(randCode))
        {
            String loginRandCode = (String)WebUtil.getValueFromSession(request, "LOGIN_RAND"); // session储存的校验码
            if ((StringUtil.isEmpty(randCode) || !randCode.equals(loginRandCode)))
            {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "验证码校验失败");
                return getOperateJSONView(rspMap);
            }
        }*/
        
        //判空
        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password))
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "用户工号或密码不正确");
            return getOperateJSONView(rspMap);
        }
        
        //
        
        String ip = WebUtil.getRealIpAddress(request);
        
        ResultData<?> resultData = new ResultData<UserInfo>();
        
        try
        {
            resultData = loginService.login(username, password, SYSTEM_NAME, ip);
        }
        catch (Exception e)
        {
            
            logger.error("Login", "LoginController", "login", "", UserInfoHolder.getUserId(), "", "登录异常", e);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            
            rspMap.put(Constant.RETURN_MSG_KEY, "登录异常");
            
            return getMapView(rspMap);
            
        }
        
        UserInfo userInfo = new UserInfo();
        
        String passwordMd5 = null;
        if (ReturnCode.SUCCESS.equals(resultData.getReturnCode()))
        {
        	passwordMd5 = EncryptUtil.encrypt(password, EncryptType.AES);
            
            //shiro登录认证
            //SecurityUtils.getSubject().login(new UsernamePasswordToken(username, passwordMd5));
            
            Map<?, ?> mapTemp = (Map<?, ?>)resultData.getReturnData();
            
            if (null != mapTemp)
            {
                userInfo = MapToEntityConvertUtil.convert(mapTemp, UserInfo.class, "");
              
            }
            
        }
        else
        {
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
            return getMapView(rspMap);
            
        }
        
        UsernamePasswordToken token = new UsernamePasswordToken(username, passwordMd5);
        token.setRememberMe(true);
        
        logger.info("为了验证登录用户而封装的token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        
        //获取当前的Subject  
        Subject currentUser = SecurityUtils.getSubject();
        
        try
        {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查  
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应  
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法  
            logger.info("对用户[" + username + "]进行登录验证..验证开始");
            
            logger.info("username=" + username + ";passwordMd5=" + passwordMd5 + ";token=" + token.toString());
            
            currentUser.login(token);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
            logger.info("对用户[" + username + "]进行登录验证..验证通过");
        }
        catch (UnknownAccountException uae)
        {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "未知账户");
        }
        catch (IncorrectCredentialsException ice)
        {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            String loginModeFlag = SystemParam.getWebConfigValue("LoginModeFlag");
            if ("1".equals(loginModeFlag)) {
            	rspMap.put(Constant.RETURN_MSG_KEY, "验证码不正确！");
            }else {
            	rspMap.put(Constant.RETURN_MSG_KEY, "密码不正确！");
            }
        }
        catch (LockedAccountException lae)
        {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "账户已锁定");
            
        }
        catch (ExcessiveAttemptsException eae)
        {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "用户名或密码错误次数过多");
            
        }
        catch (AuthenticationException ae)
        {
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "用户工号或密码不正确");
            
        }
        //验证是否登录成功  
        if (currentUser.isAuthenticated())
        {
            logger.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
        }
        else
        {
            token.clear();
        }
        
//        //是否有系统权限
//        Boolean isHaveAuth = false;
//        try
//        {
//            isHaveAuth = isHaveAuth(userInfo, request, LOGIN_TYPE);
//        }
//        catch (Exception e1)
//        {
//            logger.error("Login", "LoginController", "isHaveAuth", "", UserInfoHolder.getUserId(), "", "判断是否有系统权限异常", e1);
//            
//            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
//            rspMap.put(Constant.RETURN_MSG_KEY, "判断是否有系统权限异常");
//            return getMapView(rspMap);
//        }
//        
//        if (!isHaveAuth)
//        {
//            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
//            rspMap.put(Constant.RETURN_MSG_KEY, "无系统权限");
//            return getMapView(rspMap);
//        }
        
        //存放userInfo
        UserInfoHolder.set(userInfo);

        //清除原有权限列表
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        AuthShiroRealm shiroRealm = (AuthShiroRealm)rsm.getRealms().iterator().next();
        String principal=(String) SecurityUtils.getSubject().getPrincipal();
        shiroRealm.clearCachedAuthorizationInfo(principal);

        //用户信息放session
        WebUtil.addSession(request, Constant.SESSION_KEY_USERINFO, userInfo);
        
        ResultData<?> auth = new ResultData<List<Auth>>();
        try
        {
            auth = userService.getAuth(SYSTEM_NAME);
        }
        catch (Exception e)
        {
            logger.error("Login", "LoginController", "getAuth", "", UserInfoHolder.getUserId(), "", "获取Auth权限异常", e);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "获取Auth权限异常");
            return getMapView(rspMap);
        }
        
        if (ReturnCode.SUCCESS.equals(auth.getReturnCode()))
        {
            WebUtil.addSession(request, Constant.SESSION_KEY_AUTH_URL, auth.getReturnData());
        }
        
        rspMap.put(Constant.RETURN_DATA_KEY, WebUtil.getValueFromSession(request, "headUrl"));
        
        return getMapView(rspMap);
    }
    
    /**
     * 退出登录页面
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request)
    {
        
        //request.getSession().invalidate();
        
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        
        request.getSession().invalidate();
        
        return "redirect:/index.jsp";
        
        //        return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/";
    }
    
    @RequestMapping("/403")
    public String unauthorizedRole()
    {
        return "/403";
    }
    
    /** 
    * 顶部菜单点击存session
    * @param request
    * @return
    */
    @RequestMapping(value = "user/clickmenu", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> setMenuSession(HttpServletRequest request)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        
        //获取
        String menuId = (String)reqMap.get("authId");
        String authName = (String)reqMap.get("authName");
        
        //用户信息放session
        WebUtil.addSession(request, "headMenuIdSelect", menuId);
        
        //用户信息放session
        WebUtil.addSession(request, "headMenuNameSelect", authName);
        
        //return getOperateJSONView(rspMap);
        return getSearchJSONView(rspMap);
        
    }
    
//    /**
//     * 提供给APP端的登录接口
//     * 
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "appLogin", method = RequestMethod.GET)
//    public String appLogin(HttpServletRequest request, ModelMap mop)
//    {
//        // 公司编号
//        String userId = request.getParameter("userId");
//        // 公司名称
//        String userCode = request.getParameter("userCode");
//        // 城市编号
//        String cityNo = request.getParameter("cityNo");
//        
//        //先判定session是否存在
//        UserInfo userInfo = (UserInfo)WebUtil.getValueFromSession(request, Constant.SESSION_KEY_USERINFO);
//        if (null == userInfo)
//        {
//            userInfo = new UserInfo();
//            // 设置用户userInfo
//            userInfo.setUserId(Integer.valueOf(userId));
//            userInfo.setUserCode(userCode);
//            userInfo.setCityNo(cityNo);
//            
//            // 存放userInfo
//            UserInfoHolder.set(userInfo);
//            
//            // 用户信息放session
//            WebUtil.addSession(request, Constant.SESSION_KEY_USERINFO, userInfo);
//        }
//        
//        // 记入日志
//        logger.info("userInfo", userInfo.toString());
//        
//        
//        return "redirect:/companys";
//        
//    }
    
//    /** 
//    * 是否有系统权限
//    * @param userInfo
//    */
//    public static Boolean isHaveAuth(UserInfo userInfo, HttpServletRequest request, String type)
//    {
//        //岗位
//        List<?> postList = userInfo.getPostList();
//        if (null != postList && !postList.isEmpty())
//        {
//            Integer selectPostId = userInfo.getSelectpostId();
//            List<Auth> authList = null;
//            Map<?, ?> temp;
//            Post post;
//            for (Object obj : postList)
//            {
//                temp = (Map<?, ?>)obj;
//                if (null != temp)
//                {
//                    post = MapToEntityConvertUtil.convert(temp, Post.class, "");
//                    authList = post.getAuthList();
//                    if (null != authList && !authList.isEmpty())
//                    {
//                        if (type.equals(LOGIN_TYPE))
//                        {
//                            if (selectPostId.intValue() == post.getPostId().intValue())
//                            {
//                                Map<?, ?> authTemp;
//                                Auth auth;
//                                for (Object objj : authList)
//                                {
//                                    authTemp = (Map<?, ?>)objj;
//                                    if (null != (Map<?, ?>)objj)
//                                    {
//                                        auth = MapToEntityConvertUtil.convert(authTemp, Auth.class, "");
//                                        if (0 == auth.getParentId())
//                                        {
//                                            //动态设置首页session
//                                            WebUtil.addSession(request, "headUrl", auth.getUrl());
//                                            //用户信息放session
//                                            WebUtil.addSession(request, "headMenuIdSelect", auth.getAuthId());
//                                            //用户信息放session
//                                            WebUtil.addSession(request, "headMenuNameSelect", auth.getAuthName());
//                                            return true;
//                                        }
//                                    }
//                                }
//                            }
//                            else
//                            {
//                                Map<?, ?> authTemp;
//                                Auth auth;
//                                for (Object objj : authList)
//                                {
//                                    authTemp = (Map<?, ?>)objj;
//                                    if (null != (Map<?, ?>)objj)
//                                    {
//                                        auth = MapToEntityConvertUtil.convert(authTemp, Auth.class, "");
//                                        if (0 == auth.getParentId())
//                                        {
//                                            //动态设置首页session
//                                            WebUtil.addSession(request, "headUrl", auth.getUrl());
//                                            //用户信息放session
//                                            WebUtil.addSession(request, "headMenuIdSelect", auth.getAuthId());
//                                            //用户信息放session
//                                            WebUtil.addSession(request, "headMenuNameSelect", auth.getAuthName());
//                                            return true;
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                        else
//                        {
//                            if (selectPostId.intValue() == post.getPostId().intValue())
//                            {
//                                Map<?, ?> authTemp;
//                                Auth auth;
//                                for (Object objj : authList)
//                                {
//                                    authTemp = (Map<?, ?>)objj;
//                                    if (null != (Map<?, ?>)objj)
//                                    {
//                                        auth = MapToEntityConvertUtil.convert(authTemp, Auth.class, "");
//                                        if (0 == auth.getParentId())
//                                        {
//                                            //动态设置首页session
//                                            WebUtil.addSession(request, "headUrl", auth.getUrl());
//                                            //用户信息放session
//                                            WebUtil.addSession(request, "headMenuIdSelect", auth.getAuthId());
//                                            //用户信息放session
//                                            WebUtil.addSession(request, "headMenuNameSelect", auth.getAuthName());
//                                            return true;
//                                        }
//                                    }
//                                }
//                            }
//                            continue;
//                        }
//                    }
//                }
//            }
//        }
//        return false;
//    }

    /**
     * 获取验证码
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/getIdCode", method = RequestMethod.POST)
    public JSONObject getIdCode(HttpServletRequest request)
    {
        JSONObject object=new JSONObject();
        String referer =  request.getHeader("referer");
        String userCode=request.getParameter("userName");
        if(StringUtil.isEmpty(userCode)){
            object.put("code", "-1");
            object.put("msg", "请输入正确的工号");
            object.put("data", null);
            return object;
        }
        String refererCheckFlg=  SystemParam.getWebConfigValue("loginrefererCheckFlg");
        String refererCheckStr= SystemParam.getWebConfigValue("loginrefererpmlsStr");
        if("1".equals(refererCheckFlg)){
 /*           if(StringUtil.isEmpty(referer)){
                object.put("code", "-1");
                object.put("msg", "请求不合法");
                object.put("data", null);
                return object;
            }else{
                if(!referer.startsWith(refererCheckStr)&&!referer.startsWith("http://localhost")&&!referer.startsWith("http://127.0.0.1")){
                    object.put("code", "-1");
                    object.put("msg", "请求不合法");
                    object.put("data", null);
                    return object;
                }
            }*/
        }
        try {
            ResultData resultData=userService.getUserByCode(userCode);
            if("200".equals(resultData.getReturnCode()) && resultData.getReturnData()!=null){
                object=WXPushCodeUtil.sendCodeToWX(SYSTEM_NAME,userCode);
            }else{
                object.put("code","-1");
                object.put("msg","用户工号不存在！");
                object.put("data",null);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return object;
    }
}
