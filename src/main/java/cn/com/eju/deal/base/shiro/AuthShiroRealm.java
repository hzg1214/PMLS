package cn.com.eju.deal.base.shiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.util.WXPushCodeUtil;
import cn.com.eju.deal.core.support.SystemCfg;
import com.eju.core.util.EncryptUtil;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.user.Auth;
import cn.com.eju.deal.dto.user.UserDto;
import cn.com.eju.deal.user.service.UserService;

@Service
@Transactional
public class AuthShiroRealm extends AuthorizingRealm
{
    
    @Inject
    private UserService userService;
    
    protected Logger logger = Logger.getLogger(AuthShiroRealm.class);
    
    /** 
    * 授权查询回调函数，进行鉴权但缓存中无用户的授权信息时调用
    * @param principalCollection
    * @return
    */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection)
    {
        logger.info("授权认证：" + principalCollection.getRealmNames());
        
//        //获取登录时输入的用户名
//        String loginName = (String)principalCollection.fromRealm(getName()).iterator().next();
        
        List<String> authList = new ArrayList<String>();
        List<String> permissionList = new ArrayList<String>();
        
        //从数据库中获取当前登录用户的详细信息  
        if (null != UserInfoHolder.get())
        {
            //获取当前登录用户的权限  
            ResultData<List<Auth>> allAuthbr = new ResultData<List<Auth>>();
            try
            {
//                allAuthbr = (ResultData<List<Auth>>)userService.getListByPostId(UserInfoHolder.getUserId(), UserInfoHolder.get().getSelectpostId());
                allAuthbr.setReturnData(UserInfoHolder.get().getAuths());
            }
            catch (Exception e)
            {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
            
            List<Auth> auths = allAuthbr.getReturnData();
            if (null != auths && auths.size() > 0)
            {
                for (int i=0;i<auths.size();i++){
                {
                    Map map=(Map)auths.get(i);
                    //无角色，暂时为权限名称的集合
                    authList.add(map.get("authName").toString());
                 
                    //类型是按钮，并且权限编码不能为空，组成权限，供jsp页面调用
                    if (StringUtil.isNotEmpty(map.get("type").toString()) && "B".equals(map.get("type").toString())
                        && StringUtil.isNotEmpty(map.get("authCode").toString()))
                    {
                        permissionList.add(map.get("url").toString() + ":" + map.get("authCode").toString()); //权限=url+权限编码
                    }
                }
                }
            }
        }
        else
        {
            throw new AuthorizationException();
        }
        
        //为当前用户设置权限
        //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        
        //无角色，暂时为权限名称的集合
        info.addRoles(authList);
        
        info.addStringPermissions(permissionList);
        
        return info;
        
    }
    
    /** 
    * 认证回调函数，登录时调用
    * @param authenticationToken
    * @return
    * @throws AuthenticationException
    */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
        throws AuthenticationException
    {
        //UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        
        logger.info("authc name:" + token.getUsername());
        
        //查出是否有此用户
        ResultData<?> resultData = new ResultData<UserDto>();
        
        try
        {
            resultData = userService.queryShiro(token.getUsername());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        //        String jsonDto = JsonUtil.parseToJson(map.get("UserDto"));
        //        
        //        UserDto user = JsonUtil.parseToObject(jsonDto, UserDto.class);
        
        UserDto userDto = new UserDto();
        
        Map<?, ?> mapTemp = (Map<?, ?>)resultData.getReturnData();
        
        userDto = MapToEntityConvertUtil.convert(mapTemp, UserDto.class, "");
        
        
        if (userDto != null)
        {
            try{
                //登录方式 0密码登录，1验证码登录
                String loginModeFlag=SystemParam.getWebConfigValue("LoginModeFlag");
                if("1".equals(loginModeFlag)){
                    //用户密码改成从radis中记录的验证码
                    String systemName= SystemCfg.getString("systemCode");
                    String idCode=WXPushCodeUtil.getIdCode(systemName,userDto.getUserCode());
                    String passwordMd5 = EncryptUtil.encrypt(idCode, EncryptUtil.EncryptType.AES);
                    userDto.setPassword(passwordMd5);
                }
            }catch (Exception e){
                return null;
            }

            //若存在，将此用户存放到登录认证info中
            this.setSession("currentUser", userDto);
            
            return new SimpleAuthenticationInfo(userDto.getUserCode(), userDto.getPassword(), getName());
        }
        
        //没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常  
        return null;
    }
    
    /** 
     * 将一些数据放到ShiroSession中,以便于其它地方使用 
     * @see  比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到 
     */
    private void setSession(Object key, Object value)
    {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser)
        {
            Session session = currentUser.getSession();
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
            if (null != session)
            {
                session.setAttribute(key, value);
            }
        }
    }
    
    /**
     * 清空当前用户权限
     * @param principal
     */
    public void clearCachedAuthorizationInfo(String principal)
    {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }
    
}