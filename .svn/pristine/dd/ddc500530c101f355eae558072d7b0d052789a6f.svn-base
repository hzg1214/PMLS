package cn.com.eju.deal.houseLinkage.linkAchieveChange.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.houseLinkage.linkAchieveChange.service.LinkAchieveChangeService;
import cn.com.eju.deal.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 联动业绩调整
 * Created by eju on 2017/10/23.
 */
@Controller
@RequestMapping(value = "linkAchieveChange")
public class LinkAchieveChangeController extends BaseController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "linkAchieveChangeService")
    LinkAchieveChangeService linkAchieveChangeService;

    @Resource(name = "userService")
    UserService userService;

    /**
     * 联动业绩调整列表页--初始化
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String toLinkAchieveChangeList(HttpServletRequest request, ModelMap mop)
    {
        return "houseLinkage/linkAchieveChange/linkAchieveChangeList";
    }

    /**
     * 获取联动业绩调整列表页
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public String getLinkAchieveChangeList(HttpServletRequest request, ModelMap mop)
    {
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);

        //返回list
        ResultData<?> resultData = null;
        List<?> contentlist = new ArrayList<>();
        try
        {
            reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
            resultData = linkAchieveChangeService.getLinkAchieveChangeList(reqMap,pageInfo);
            if(resultData != null){
                contentlist = (List<?>) resultData.getReturnData();
            }
        }
        catch (Exception e)
        {
            logger.error("PMLS", "linkAchieveChange", "getLinkAchieveChangeList", reqMap.toString(), null, "", "获取联动业绩调整列表页", e);
        }

        //存放到mop中
        mop.addAttribute("contentlist", contentlist);

        return "houseLinkage/linkAchieveChange/linkAchieveChangeListCtx";
    }

    /**
     * 联动业绩归属人--初始化
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "linkUser", method = RequestMethod.GET)
    public String toLinkUserList(HttpServletRequest request, ModelMap mop)
    {
        bindParamToAttrbute(request);
        return "houseLinkage/linkAchieveChange/achieveMentUserListPopup";
    }

    /**
     * 获取联动业绩归属人
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "linkUser/getList", method = RequestMethod.GET)
    public String getLinkUserList(HttpServletRequest request, ModelMap mop)
    {
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);

        //返回list
        ResultData<?> resultData = null;
        List<?> linkUserList = new ArrayList<>();
        try
        {
            reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
            resultData = userService.getLinkUser(reqMap,pageInfo);
            if(resultData != null){
                linkUserList = (List<?>) resultData.getReturnData();
            }
        }
        catch (Exception e)
        {
            logger.error("PMLS", "linkAchieveChange", "getLinkUserList", reqMap.toString(), null, "", "获取联动业绩归属人", e);
        }

        //存放到mop中
        mop.addAttribute("linkUserList", linkUserList);

        return "houseLinkage/linkAchieveChange/achieveMentUserListCtxPopup";
    }

    /**
     * 保存联动业绩调整信息
     * @param request
     * @return
     */
    @RequestMapping(value="saveLinkAchieve", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView saveLinkAchieve (HttpServletRequest request){
        ReturnView returnView = new ReturnView();
        ResultData resultData = new ResultData();
        resultData.setFail();
        //请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("createUserCode",UserInfoHolder.get().getUserCode());
        reqMap.put("createUserName",UserInfoHolder.get().getUserName());
        try {

            resultData = linkAchieveChangeService.saveLinkAchieve(reqMap);

        } catch (Exception e) {
            resultData.setFail("保存联动业绩调整信息异常!");
            logger.error("PMLS", "linkAchieveChange", "saveLinkAchieve", reqMap.toString(), null, "", "保存联动业绩调整信息异常", e);
        }

        returnView.setOperateReturnType();
        returnView.setReturnCode(resultData.getReturnCode());
        returnView.setReturnMsg(resultData.getReturnMsg());
        returnView.setReturnValue(resultData.getReturnData());

        return returnView;

    }
    
    /**
     * 联动业绩归属人--初始化
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "toLinkUserList2", method = RequestMethod.GET)
    public String toLinkUserList2(HttpServletRequest request, ModelMap mop){
        bindParamToAttrbute(request);
        mop.put("adjutFlag2", 1);
        return "houseLinkage/linkAchieveChange/achieveMentUserListPopup2";
    }

    /**
     * 获取联动业绩归属人
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "linkUser/getLinkUserList2", method = RequestMethod.GET)
    public String getLinkUserList2(HttpServletRequest request, ModelMap mop){
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        //返回list
        ResultData<?> resultData = null;
        List<?> linkUserList = new ArrayList<>();
        try{
            reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
            reqMap.put("adjutFlag", 1);
            resultData = userService.getLinkUser(reqMap,pageInfo);
            if(resultData != null){
                linkUserList = (List<?>) resultData.getReturnData();
            }
        }catch (Exception e){
            logger.error("PMLS", "linkAchieveChange", "getLinkUserList2", reqMap.toString(), null, "", "获取联动业绩归属人", e);
        }
        //存放到mop中
        mop.addAttribute("linkUserList", linkUserList);
        return "houseLinkage/linkAchieveChange/achieveMentUserListCtxPopup2";
    }
}
