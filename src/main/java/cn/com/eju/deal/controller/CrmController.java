package cn.com.eju.deal.controller;

import cn.com.eju.deal.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xu on 2017/4/19.
 */
@Controller
@RequestMapping(value = "crm")
public class CrmController extends BaseController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap mop, HttpServletResponse response)
    {
        return "crm/storeAuditList";
    }
}
