package cn.com.eju.pmls.frontPanel.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.pmls.frontPanel.service.FrontPanelService;

@RestController
@RequestMapping(value = "frontPanel")
public class FrontPanelController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource(name = "frontPanelService")
    private FrontPanelService frontPanelService;


    /**
     * 工作台-PC看板页面
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView cooperationList() {
        return new ModelAndView("frontPanel/frontPanelList");
    }

    /**
     * PC看板数据
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getFrontPanelData", method = RequestMethod.POST)
    public ResultData<?> getFrontPanelData(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
            //获取页面显示数据
            resultData = frontPanelService.getFrontPanelData(reqMap);
        } catch (Exception e) {
            logger.error("frontPanel",
                    "FrontPanelController",
                    "getFrontPanelData",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询PC看板数据异常",
                    e);
        }
        return resultData;
    }

}
