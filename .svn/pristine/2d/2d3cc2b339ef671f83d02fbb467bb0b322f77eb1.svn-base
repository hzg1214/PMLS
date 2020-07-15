package cn.com.eju.pmls.yfLogin;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.common.constant.CommConstant;
import cn.com.eju.pmls.utils.DesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.com.eju.deal.common.util.DateUtils;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "yfLogin")
public class YfLoginController extends BaseController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String login(HttpServletRequest request, ModelMap mop){


        UserInfo userInfo = UserInfoHolder.get();
        try {
            String yftLoginUrl = SystemParam.getWebConfigValue("yftLoginUrl");

            Date date = new Date();
            String dateStr = DateUtils.formatDate(date,"yyyy/MM/dd HH:mm:ss");
            String plaintext = userInfo.getUserCode()+"_"+dateStr;
            DesUtil tools = new DesUtil(CommConstant.YX_DES_LOGIN_KEY, CommConstant.YX_DES_LOGIN_IV);
            String desSign = tools.encode(plaintext);
            logger.info(desSign);

            mop.put("yftLoginUrl",yftLoginUrl);
            mop.put("desSign",desSign);
        } catch (Exception e) {
            logger.error("yfLogin", "YfLoginController", "", "", null, "", "跳转登录生成密文错误！", e);
        }
        mop.put("userInfo",userInfo);
        return "yfLogin/yfLogin";
    }


    @RequestMapping(value = "ajaxYfLogin", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<Map<String,Object>> ajaxYf(HttpServletRequest request){
        ResultData<Map<String,Object>> resultData = new ResultData<>();
        Map<String,Object> map = new HashMap<>();
        UserInfo userInfo = UserInfoHolder.get();
        try {
            String yftLoginUrl = SystemParam.getWebConfigValue("yftLoginUrl");

            Date date = new Date();
            String dateStr = DateUtils.formatDate(date,"yyyy/MM/dd HH:mm:ss");
            String plaintext = userInfo.getUserCode()+"_"+dateStr;
            DesUtil tools = new DesUtil(CommConstant.YX_DES_LOGIN_KEY, CommConstant.YX_DES_LOGIN_IV);
            String desSign = tools.encode(plaintext);
            logger.info(desSign);

            map.put("yftLoginUrl",yftLoginUrl);
            map.put("desSign",desSign);
        } catch (Exception e) {
            logger.error("ajaxYfLogin", "YfLoginController", "", "", null, "", "跳转友房通登录组装报文错误！", e);
        }
        resultData.setSuccess();
        resultData.setReturnData(map);
        return resultData;
    }
}
