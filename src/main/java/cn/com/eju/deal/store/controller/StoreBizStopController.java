/**
 * Copyright (c) 2017, www.ehousechina.com.
 * Project Name:PMLSWeb
 * File Name:StoreBizStopController.java
 * Package Name:cn.com.eju.deal.store.controller
 * Date:2017年9月26日下午5:45:05
 */
package cn.com.eju.deal.store.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.service.CityService;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.common.util.WXSendMsgUtil;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.dto.store.StoreBizStop;
import cn.com.eju.deal.store.service.StoreBizStopService;
import cn.com.eju.deal.user.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * ClassName: StoreBizStopController <br/>
 * Description: 门店停业上报 <br/>
 * 
 * @author yinkun
 * @date: 2017年9月26日 下午5:45:05 <br/>
 * @version V1.0
 */
@RestController
@RequestMapping(value="/storeBizStop")
public class StoreBizStopController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource
    private StoreBizStopService storeBizStopService;
    
    @Resource(name = "cityService")
    private CityService cityService;
    
    @Resource(name="userService")
    private UserService userService;
    
    /**
     * 【描述】: 跳转门店停业上报列表页面
     *
     * @author yinkun
     * @date: 2017年9月26日 下午5:57:52
     * @param request
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView page(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("store/storeBizStop");
        modelAndView.addObject("list_search_page", ComConstants.STORE_BIZ_STOP_LIST_SEARCH);
        return modelAndView;
    }
    
    /**
     * 【描述】: 查询门店停业上报列表
     *
     * @author yinkun
     * @date: 2017年9月26日 下午6:13:53
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request,HttpServletResponse response) {
        
        ModelAndView mv = new ModelAndView("store/storeBizStopData");
        
        //获取页面请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            UserInfo userInfo = UserInfoHolder.get();
            reqMap.put("userId",userInfo.getUserId());

            if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.STORE_BIZ_STOP_LIST_SEARCH, reqMap);
            }
            
            Map<String, Object> map = bindParamToMap(request);
            if (map.containsKey("searchParam") && "1".equals(map.get("searchParam"))) {
                Map<String, Object> searchParamMap = getRememberSearch(request, ComConstants.STORE_LIST_SEARCH);
                mv.addObject("searchParamMap", JSON.toJSON(searchParamMap));
            } else {
                clearSearch(request, response, ComConstants.STORE_LIST_SEARCH);
            }
            mv.addObject("list_search_page", ComConstants.STORE_LIST_SEARCH);
            
            ResultData<?> resultData = storeBizStopService.queryList(reqMap, getPageInfo(request));
            mv.addObject("data", (List<?>)resultData.getReturnData());
        } catch (Exception e) {
            logger.error("storeBizStop",
                    "StoreBizStopController",
                    "list",
                    reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询门店停业上报列表",
                    e);
        }
        
        return mv;
    }
    
    /**
     * 【描述】: 查看停业上报明细
     *
     * @author yinkun
     * @date: 2017年9月27日 上午11:53:22
     * @param stopId
     * @param request
     * @return
     */
    @RequestMapping(value = "/view/{stopId}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable("stopId") Integer stopId,HttpServletRequest request){
        ResultData<?> resultData = new ResultData<>();
        ModelAndView mv = new ModelAndView("store/storeBizStopDetail");
        try{
            resultData = storeBizStopService.getStopById(stopId);
            resultData.setSuccess();
            mv.addObject("detail", resultData.getReturnData());
        }
        catch (Exception e){
            logger.error("storeBizStop",
                    "StoreBizStopController",
                    "view",
                    "stopId="+stopId,
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查看停业上报明细异常",
                    e);
        }
        return mv;
    }
    
    /**
     * 【描述】: 跳转停业上报驳回页面
     *
     * @author yinkun
     * @date: 2017年9月27日 下午5:35:29
     * @param stopId Integer
     * @param storeId Integer
     * @param mop ModelMap
     * @return
     */
    @RequestMapping(value = "toRejectStop", method = RequestMethod.GET)
    public ModelAndView toRejectStop(HttpServletRequest request,ModelMap mop){
        Map<String, Object> map = bindParamToMap(request);
        ModelAndView mv = new ModelAndView("store/storeStopReject");
        mv.addAllObjects(map);
        return mv;
    }
    
    /**
     * 【描述】: 审核驳回
     *
     * @author yinkun
     * @date: 2017年9月27日 下午5:41:22
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "rejectStop", method = RequestMethod.GET)
    public ResultData<?> rejectStop(HttpServletRequest request,HttpServletResponse response) {
        ResultData<?> result = new ResultData<>();
        Map<String, Object> map = bindParamToMap(request);
        
        try {
            StoreBizStop storeBizStop = new StoreBizStop();
            storeBizStop.setStopId(Integer.valueOf(map.get("stopId").toString()));
            storeBizStop.setStoreId(Integer.valueOf(map.get("storeId").toString()));
            storeBizStop.setAuditUserId(UserInfoHolder.getUserId());
            storeBizStop.setAuditDetail(map.get("auditDetail").toString());
            storeBizStop.setStatus(21003);
            result = storeBizStopService.rejectStop(storeBizStop);
            
            if(ReturnCode.SUCCESS.equals(result.getReturnCode())) {
                
                ResultData<?> data = userService.getUserInfoById(Integer.valueOf(map.get("userId").toString()));
                if(ReturnCode.SUCCESS.equals(data.getReturnCode())) {
                    Map user = (Map) data.getReturnData();
                    
                    String storeName = map.get("storeName").toString();
                    String address = map.get("address").toString();
                    Integer agentId=Integer.parseInt(SystemParam.getWebConfigValue("msgAgentId"));
                    String msgStr = "门店停业上报审核结果通知门店  "+storeName+"("+address+")"+" 审核被驳回,驳回原因:"+storeBizStop.getAuditDetail()+"。";
                    WXSendMsgUtil.Send_msg(user.get("userCode").toString(),"","","text",agentId,msgStr);
                }
            }
        }catch (Exception e) {
            result.setFail();
            logger.error("storeBizStop",
                    "StoreBizStopController",
                    "rejectStop",
                    map.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "审核驳回异常",
                    e);
        }
        
        return result;
    }
    
    /**
     * 【描述】: 审核通过
     *
     * @author yinkun
     * @date: 2017年9月28日 上午10:45:16
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "auditPass", method = RequestMethod.GET)
    public ResultData<?> auditPass(HttpServletRequest request,HttpServletResponse response) {
        ResultData<?> result = new ResultData<>();
        Map<String, Object> map = bindParamToMap(request);
        
        try {
            StoreBizStop storeBizStop = new StoreBizStop();
            storeBizStop.setStopId(Integer.valueOf(map.get("stopId").toString()));
            storeBizStop.setStoreId(Integer.valueOf(map.get("storeId").toString()));
            storeBizStop.setAuditUserId(UserInfoHolder.getUserId());
            storeBizStop.setStatus(21002);
            storeBizStop.setUserCode(UserInfoHolder.get().getUserCode());
            result = storeBizStopService.auditPass(storeBizStop);
            
            if(ReturnCode.SUCCESS.equals(result.getReturnCode())) {
                
                ResultData<?> data = userService.getUserInfoById(Integer.valueOf(map.get("userId").toString()));
                if(ReturnCode.SUCCESS.equals(data.getReturnCode())) {
                    Map user = (Map) data.getReturnData();
                    
                    String storeName = map.get("storeName").toString();
                    String address = map.get("address").toString();
                    Integer agentId=Integer.parseInt(SystemParam.getWebConfigValue("msgAgentId"));
                    String msgStr = "门店停业上报审核结果通知门店  "+storeName+"("+address+")"+" 审核通过。";
                    WXSendMsgUtil.Send_msg(user.get("userCode").toString(),"","","text",agentId,msgStr);
                }
            }
        }catch (Exception e) {
            result.setFail();
            logger.error("storeBizStop",
                    "StoreBizStopController",
                    "auditPass",
                    map.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "审核通过异常",
                    e);
        }
        
        return result;
    }
}

