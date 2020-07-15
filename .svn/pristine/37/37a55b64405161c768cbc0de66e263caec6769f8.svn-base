package cn.com.eju.pmls.keFuOrder.controller;


import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.service.CommonService;

import cn.com.eju.deal.core.support.ResultData;

import cn.com.eju.pmls.keFuOrder.service.PmlsKeFuOrderContractService;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 客服反馈工单
 */
@Controller
@RequestMapping(value = "pmlsKeFuOrder")
public class PmlsKeFuOrderContractController extends BaseReportController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private PmlsKeFuOrderContractService pmlsKeFuOrderContractService;

    @Resource(name = "commonService")
    private CommonService commonService;


    /**
     * 根据storeId查看客服反馈工单列表
     * @return
     */
    @RequestMapping(value = "getKeFuOrderListByStoreId/{storeId}", method = RequestMethod.POST)
    @ResponseBody
    public ResultData getKeFuOrderListByStoreId(@PathVariable("storeId") Integer storeId, ModelMap mop){
        ResultData<?> resultData = null;
        try{
            resultData = pmlsKeFuOrderContractService.getKeFuOrderListByStoreId(storeId);
//            if(resultData != null){
//                mop.addAttribute("content", resultData.getReturnData());
//            }
        }catch (Exception e){
            logger.error("pmlsKeFuOrder", "PmlsKeFuOrderContractController", "getKeFuOrderListByStoreId", "", UserInfoHolder.getUserId(), "", "根据门店Id查看客服反馈工单列表失败", e);
        }
        //返回视图
//        ModelAndView mv = new ModelAndView("keFuOrder/storeKeFuOrder");
        return resultData;
    }



    /**
     * 根据id查看客服反馈工单详情
     * @param id
     * @param mop
     * @return
     */
    @RequestMapping(value = "getKeFuOrderById/{id}", method = RequestMethod.GET)
    public ModelAndView getKeFuOrderById(@PathVariable("id") Integer id, ModelMap mop){
        ResultData<?> resultData = null;
        try{
            resultData = pmlsKeFuOrderContractService.getKeFuOrderById(id);
            if(resultData != null){
                mop.addAttribute("keFuOrder", resultData.getReturnData());
                mop.addAttribute("keFuOrderJson", JSONObject.toJSON(resultData.getReturnData()));
            }
        }catch (Exception e){
            logger.error("pmlsKeFuOrder", "PmlsKeFuOrderContractController", "getKeFuOrderById", "", UserInfoHolder.getUserId(), "", "查看客服反馈工单详情失败", e);
        }

        //返回视图
        ModelAndView mv = new ModelAndView("keFuOrder/keFuOrderDetail");
        return mv;
    }


    /**
     * 再次提醒经办人
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/reAlert",method = RequestMethod.POST)
    public ResultData<?> reAlert(HttpServletRequest request){
        ResultData<?> resultData = new ResultData<>();
        // 获取map
        Map<String, Object> reqMap = bindParamToMap(request);

        try{
            resultData = pmlsKeFuOrderContractService.reAlert(reqMap);
        }catch (Exception e){
            resultData.setFail("提醒经办人失败");
            logger.error("keFuOrder", "KeFuOrderContractController", "reAlert", "",UserInfoHolder.getUserId(), "", "提醒经办人失败！", e);
        }
        return resultData;
    }

}
