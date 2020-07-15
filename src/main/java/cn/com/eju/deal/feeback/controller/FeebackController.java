/**
 * Copyright (c) 2017, www.ehousechina.com.
 * Project Name:PMLSWeb
 * File Name:FeebackController.java
 * Package Name:cn.com.eju.deal.feeback.controller
 * Date:2017年9月19日下午3:21:24
 */
package cn.com.eju.deal.feeback.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.dto.feeback.Feeback;
import cn.com.eju.deal.feeback.service.FeebackService;

/**
 * ClassName: FeebackController <br/>
 * Description: 意见反馈控制层 <br/>
 * 
 * @author yinkun
 * @date: 2017年9月19日 下午3:21:24 <br/>
 * @version V1.0
 */
@Controller
@RequestMapping("/feeback")
public class FeebackController extends BaseController {
    
    @Resource
    private FeebackService feebackService;
    
    public final LogHelper logger = LogHelper.getLogger(this.getClass());

    /**
     * 【描述】: 显示页面
     *
     * @author yinkun
     * @date: 2017年9月19日 下午3:22:20
     * @param pageName
     * @return
     */
    @RequestMapping("{pageName}")
    public String showPage(@PathVariable("pageName") String pageName){
        
        return "feeback/" + pageName;
    }
    
    /**
     * 【描述】: 新增意见反馈
     *
     * @author yinkun
     * @date: 2017年9月19日 下午3:22:12
     * @param request
     * @param feeback
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public ReturnView<?, ?> save(HttpServletRequest request,Feeback feeback) {
        
        ReturnView<?,?> jv = new ReturnView<>();
        
        try {
            feebackService.save(feeback);
        }catch(Exception e) {
            jv.setFail();
            jv.put("returnMsg", "系统未知错误,请联系管理人员");
            
            logger.error("feeback",
                    "FeebackController",
                    "save()",
                    null,
                    UserInfoHolder.getUserId(),
                    WebUtil.getRealIpAddress(request),
                    null,
                    e);
        }
        
        return jv;
    }
}

