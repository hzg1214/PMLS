package cn.com.eju.deal.base.util;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.dto.user.Auth;
import cn.com.eju.deal.dto.user.Post;


/**   
* 权限工具类
* @author guopf
* @date 2017年12月5日 下午6:03:52
*/
public class CRMAuthUtil
{
    /** 
    * WebConfig名称获取值
    * @param url 当前页面url
    * @param btnName 按钮名称（与权限系统中模块编码对应）
    * @param userInf 当前登录用户session信息
    * @return 1:有权限;0:无权限;-1:出错
    */
    public static int getBtnAuth(String url, String btnName, UserInfo userInfo)
    {
        int result = 0;
        try
        {
            Post post = userInfo.getSelectPost();
            List<Auth> authList = post.getAuthList();
 
            for (int i = 0; i < authList.size(); i++) {  
            	Map map  = (Map)authList.get(i);  
            	String type=map.get("type").toString();
            	String strUrl=map.get("url").toString();
            	String authCode=map.get("authCode").toString();
            	 if (type.equals("B") && strUrl.equals(url) && authCode.equals(btnName))
                 {
                     result = 1;
                     break;
                 }
            }  
        }
        catch (Exception e)
        {
            result = -1;
        }
        return result;
    }
}
