package cn.com.eju.pmls.yfLogin;

import cn.com.eju.deal.base.helper.LogHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "dashboard")
public class DashboardController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request, ModelMap mop) {

        ModelAndView mv = new ModelAndView("dashboard");
        return mv;
    }
}
