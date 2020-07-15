package cn.com.eju.deal.store.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.store.service.StoreDepositService;

@RestController
@RequestMapping(value="/storeDeposit")
public class StoreDepositController extends BaseController {
    
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource
    private StoreDepositService storeDepositService;

    /**
     * 校验门店是否有未退款，在途保证金
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "checkStoreDeposit", method = RequestMethod.GET)
    public ReturnView<?, ?> checkStoreDeposit(HttpServletRequest request,HttpServletResponse response) {
        Map<String, Object> rspMap = new HashMap<String, Object>();
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        
        Map<String,Object> qParam = new HashMap<>();
        if(request.getParameter("contractId") != null) {
            Integer contractId = Integer.valueOf(request.getParameter("contractId").toString());
            qParam.put("contractId", contractId);
        }else if(request.getParameter("storeNo") != null) {
            String storeNo = request.getParameter("storeNo").toString();
            qParam.put("storeNo", storeNo);
        }
        
        try{
            ResultData<Map<String,Object>> resultRecord = this.storeDepositService.checkStoreDeposit(qParam);
            if(resultRecord.getReturnCode().equals(ReturnCode.SUCCESS)){
                Map<String, Object> rel = resultRecord.getReturnData();
                if (Integer.valueOf(rel.get("flag").toString()) > 0) {
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    rspMap.put(Constant.RETURN_MSG_KEY, "门店编号："+rel.get("storeNo")+" 存在未退款或在途保证金");
                    return getOperateJSONView(rspMap);
                }
            }
        }catch (Exception e){
            logger.error("storeDeposit", "StoreDepositController", "checkStoreDeposit", "", UserInfoHolder.getUserId(), "", "", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "校验门店是否有未退款，在途保证金失败！");
            return getOperateJSONView(rspMap);
        }
        
        return getOperateJSONView(rspMap);
    }
    
    /**
     * 校验门店是否有在途保证金
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "checkStoreDepositByStoreNo", method = RequestMethod.GET)
    public ReturnView<?, ?> checkStoreDepositByStoreNo(HttpServletRequest request,HttpServletResponse response) {
        Map<String, Object> rspMap = new HashMap<String, Object>();
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        
        String storeNo = request.getParameter("storeNo").toString();
        
        try{
            ResultData<Integer> resultRecord = this.storeDepositService.checkStoreDepositLock(storeNo);
            if(resultRecord.getReturnCode().equals(ReturnCode.SUCCESS)){
                Integer rel = resultRecord.getReturnData();
                if (rel > 0) {
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    rspMap.put(Constant.RETURN_MSG_KEY, "门店编号："+storeNo+" 存在在途保证金");
                    return getOperateJSONView(rspMap);
                }
            }
        }catch (Exception e){
            logger.error("storeDeposit", "StoreDepositController", "checkStoreDepositByStoreNo", "", UserInfoHolder.getUserId(), "", "", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "校验门店是否在途保证金失败！");
            return getOperateJSONView(rspMap);
        }
        
        return getOperateJSONView(rspMap);
    }
}
