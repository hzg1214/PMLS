package cn.com.eju.deal.store.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.helper.SeqNoHelper;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.common.util.WXSendMsgUtil;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.dto.store.StoreStopCancel;
import cn.com.eju.deal.store.service.StoreStopCancelService;
import cn.com.eju.deal.user.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/StoreStopCancel")
public class StoreStopCancelController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private StoreStopCancelService storeStopCancelService;

    @Resource(name="userService")
    private UserService userService;

    @RequestMapping(value = "/storeStopCancelAdd", method = RequestMethod.POST)
    @ResponseBody
    public String storeStopCancelAdd(ModelMap mop, StoreStopCancel storeStopCancel){
        ResultData<?> resultData = new ResultData<>();

        try{
            storeStopCancel.setUserCreate(UserInfoHolder.getUserId());
            storeStopCancel.setCancelNo(SeqNoHelper.getInstance().getSeqNoByTypeCode("TYPE_SSCANCEL"));
            resultData = storeStopCancelService.storeStopCancelAdd(storeStopCancel);
            mop.addAttribute("store",resultData.getReturnData());
        }
        catch (Exception e){
            resultData.setFail("保存门店停业撤销异常");
            logger.error("StoreStopCancel", "StoreStopCancelController", "storeStopCancelAdd", "", UserInfoHolder.getUserId(), JsonUtil.parseToJson(storeStopCancel), "保存门店停业撤销异常", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView toStopCancelAudit(){
        ModelAndView modelAndView = new ModelAndView("store/storeStopCancelList");
        modelAndView.addObject("list_search_page", ComConstants.STORE_STOP_CANCEL_LIST_SEARCH);
        return modelAndView;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mv = new ModelAndView("store/storeStopCancelListCtx");

        //获取页面请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            UserInfo userInfo = UserInfoHolder.get();
            reqMap.put("userId",userInfo.getUserId());

            if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.STORE_STOP_CANCEL_LIST_SEARCH, reqMap);
            }

            Map<String, Object> map = bindParamToMap(request);
            if (map.containsKey("searchParam") && "1".equals(map.get("searchParam"))) {
                Map<String, Object> searchParamMap = getRememberSearch(request, ComConstants.STORE_LIST_SEARCH);
                mv.addObject("searchParamMap", JSON.toJSON(searchParamMap));
            } else {
                clearSearch(request, response, ComConstants.STORE_LIST_SEARCH);
            }
            mv.addObject("list_search_page", ComConstants.STORE_LIST_SEARCH);

            ResultData<?> resultData = storeStopCancelService.queryList(reqMap, getPageInfo(request));
            mv.addObject("data", (List<?>)resultData.getReturnData());
        } catch (Exception e) {
            logger.error("storeStopCancel",
                    "StoreStopCancelController",
                    "list",
                    reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询门店停业撤销列表",
                    e);
        }

        return mv;
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable("id") Integer id, HttpServletRequest request){
        ResultData<?> resultData = new ResultData<>();
        ModelAndView mv = new ModelAndView("store/storeStopCancelDetail");
        try{
            resultData = storeStopCancelService.getStopCancelById(id);
            resultData.setSuccess();
            mv.addObject("detail", resultData.getReturnData());
        }
        catch (Exception e){
            logger.error("storeBizStop",
                    "StoreBizStopController",
                    "view",
                    "id="+id,
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查看停业撤销申请明细异常",
                    e);
        }
        return mv;
    }

    @RequestMapping(value = "toRejectStopCancel", method = RequestMethod.GET)
    public ModelAndView toRejectStopCancel(HttpServletRequest request,ModelMap mop){
        Map<String, Object> map = bindParamToMap(request);
        ModelAndView mv = new ModelAndView("store/storeStopCancelReject");
        mv.addAllObjects(map);
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "rejectStopCancel", method = RequestMethod.GET)
    public ResultData<?> rejectStopCancel(HttpServletRequest request,HttpServletResponse response) {
        ResultData<?> result = new ResultData<>();
        Map<String, Object> map = bindParamToMap(request);

        try {
            StoreStopCancel storeStopCancel = new StoreStopCancel();
            storeStopCancel.setUserUpdate(UserInfoHolder.getUserId());
            storeStopCancel.setApproveId(UserInfoHolder.getUserId());
            storeStopCancel.setId(Integer.valueOf(map.get("id").toString()));
            storeStopCancel.setStoreId(Integer.valueOf(map.get("storeId").toString()));
            storeStopCancel.setApproveStatus(21003);
            storeStopCancel.setApproveDesc(map.get("approveDesc").toString());
            result = storeStopCancelService.rejectStopCancel(storeStopCancel);

            if(ReturnCode.SUCCESS.equals(result.getReturnCode())) {

                ResultData<?> data = userService.getUserInfoById(Integer.valueOf(map.get("userId").toString()));
                if(ReturnCode.SUCCESS.equals(data.getReturnCode())) {
                    Map user = (Map) data.getReturnData();

                    String storeNo = map.get("storeNo").toString();
                    String storeName = map.get("storeName").toString();
                    String address = map.get("addressDetail").toString();
                    Integer agentId=Integer.parseInt(SystemParam.getWebConfigValue("msgAgentId"));
                    String msgStr = storeNo+" "+storeName+"("+address+")"+" 门店停业撤销申请被驳回,驳回原因:"+storeStopCancel.getApproveDesc()+"。";
                    WXSendMsgUtil.Send_msg(user.get("userCode").toString(),"","","text",agentId,msgStr);
                }
            }
        }catch (Exception e) {
            result.setFail();
            logger.error("storeStopCancel",
                    "StoreStopCancelController",
                    "rejectStopCancel",
                    JsonUtil.parseToJson(map),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "审核驳回异常",
                    e);
        }

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "auditPass", method = RequestMethod.GET)
    public ResultData<?> auditPass(HttpServletRequest request,HttpServletResponse response) {
        ResultData<?> result = new ResultData<>();
        Map<String, Object> map = bindParamToMap(request);

        try {
            StoreStopCancel storeStopCancel = new StoreStopCancel();
            storeStopCancel.setUserUpdate(UserInfoHolder.getUserId());
            storeStopCancel.setApproveId(UserInfoHolder.getUserId());
            storeStopCancel.setStoreId(Integer.valueOf(map.get("storeId").toString()));
            storeStopCancel.setId(Integer.valueOf(map.get("id").toString()));
            storeStopCancel.setApproveStatus(21002);
            result = storeStopCancelService.auditPass(storeStopCancel);

            if(ReturnCode.SUCCESS.equals(result.getReturnCode())) {

                ResultData<?> data = userService.getUserInfoById(Integer.valueOf(map.get("userId").toString()));
                if(ReturnCode.SUCCESS.equals(data.getReturnCode())) {
                    Map user = (Map) data.getReturnData();

                    String storeNo = map.get("storeNo").toString();
                    String storeName = map.get("storeName").toString();
                    String address = map.get("addressDetail").toString();
                    Integer agentId=Integer.parseInt(SystemParam.getWebConfigValue("msgAgentId"));
                    String msgStr = storeNo+" "+storeName+"("+address+")"+" 门店停业撤销申请审核通过。";
                    WXSendMsgUtil.Send_msg(user.get("userCode").toString(),"","","text",agentId,msgStr);
                }
            }
        }catch (Exception e) {
            result.setFail();
            logger.error("storeStopCancel",
                    "StoreStopCancelController",
                    "auditPass",
                    JsonUtil.parseToJson(map),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "审核通过异常",
                    e);
        }

        return result;
    }
}
