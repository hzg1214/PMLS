package cn.com.eju.deal.user.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.user.UserDto;

/**   
* Service层
* @author (li_xiaodong)
* @date 2016年2月2日 下午9:30:27
*/
@Service("loginService")
public class LoginService extends BaseService<UserDto>
{
    //private final static String REST_SERVICE = SystemParam.getWebConfigValue("RestServer") + "logins";
    //private final static String REST_SERVICE = SystemCfg.getString("loginRestServer");
    /** 
    * 查询
    * @param id
    * @return
    * @throws Exception
    */
    public ResultData<?> login(String loginName, String password, String systemName, String ip)
        throws Exception
    {
        Map<String, Object> params = new HashMap<String, Object>();
        
        params.put("loginName", loginName);
        params.put("password", password);
        params.put("systemName", systemName);
        //params.put("ip", ip);
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "logins" + "/{params}";
        
        ResultData<?> backResult = get(url, params);
        
        return backResult;
    }
    
    
}
