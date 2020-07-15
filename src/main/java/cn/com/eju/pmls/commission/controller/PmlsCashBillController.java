package cn.com.eju.pmls.commission.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.core.support.ResultData;

import cn.com.eju.pmls.commission.service.PmlsCashBillService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;


@Controller
@RequestMapping("pmlsCashBill")
public class PmlsCashBillController extends BaseController {

    @Autowired
    private PmlsCashBillService pmlsCashBillService;

    private final LogHelper logger = LogHelper.getLogger(this.getClass());



    /**
     * @Title: list
     * @Description: 请款单列表
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, ModelMap mop,HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("commission/pmlsCashBillList");
        String cityNo = UserInfoHolder.get().getCityNo();
        if (StringUtil.isNotEmpty(cityNo)){
            Map<String, Object> map = bindParamToMap(request);
            if (map.containsKey("searchParam") && "1".equals(map.get("searchParam"))) {
                Map<String, Object> searchParamMap = getRememberSearch(request, ComConstants.CASHBILL_LIST_SEARCH);
                mv.addObject("searchParamMap", JSON.toJSON(searchParamMap));
            } else {
                clearSearch(request, response, ComConstants.CASHBILL_LIST_SEARCH);
            }
            mop.put("list_search_page", ComConstants.CASHBILL_LIST_SEARCH);
            //存放到mop中
            mop.addAttribute("userCode", UserInfoHolder.get().getUserCode());
        }
        return mv;
    }

    /**
     * 请款单列表列表
     * @param request
     * @return
     */
    @RequestMapping(value = "getCashBillList", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> getCashBillList(HttpServletRequest request, ModelMap mop,HttpServletResponse response)
    {
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        //返回list
        ResultData resultData = null;
        //List<?> contentlist = new ArrayList<>();
        try {
            if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.CASHBILL_LIST_SEARCH, reqMap);
            }
            reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
            resultData = pmlsCashBillService.getCashBillList(reqMap,pageInfo);
//            if(resultData != null){
//                contentlist = (List<?>) resultData.getReturnData();
//            }
        }catch (Exception e){
            logger.error("PMLS", "PMLSCashBillController", "getCashBillList", reqMap.toString(), null, "", "获取请款单列表页失败", e);
        }
//        //存放到mop中
//        mop.addAttribute("userCode", UserInfoHolder.get().getUserCode());
//        mop.addAttribute("contentlist", contentlist);
        return resultData;
    }


    /**
     * @Title: listToSubmitOa
     * @Description: 提交Oa
     * @return
     * @
     */
    @RequestMapping(value = "/listToSubmitOa", method = RequestMethod.POST)
    @ResponseBody
    public String listToSubmitOa(HttpServletRequest request) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.remove("_method");
        ResultData listToSubmitOa = null;
        try {
            listToSubmitOa = pmlsCashBillService.listToSubmitOa(reqMap);
            if(ReturnCode.FAILURE.equals(listToSubmitOa.getReturnCode())) {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, listToSubmitOa.getReturnMsg());
            }
        } catch (Exception e) {
            logger.error("pmlsCashBill", "PmlsCashBillController", "listToSubmitOa", "", cn.com.eju.deal.base.support.UserInfoHolder.getUserId(), "", "提交OA失败", e);
        }
        return listToSubmitOa.toString();
    }

    /**
     * desc:删除
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> remove(HttpServletRequest request, ModelMap mop) {

        Map<String, Object> rspMap = new HashMap<String, Object>();

        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);

        try {
            pmlsCashBillService.remove(reqMap);
        } catch (Exception e) {
            logger.error("pmlsCashBill", "PmlsCashBillController", "remove", "",
                    cn.com.eju.deal.base.support.UserInfoHolder.getUserId(), "", "删除请款单失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "删除失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }


    /**
     * 根据id查询框架合同详情
     * @param comParentId
     * @param mop
     * @return
     */
    @RequestMapping(value = "getCashBillDeatilById/{comParentId}/{proParentId}", method = RequestMethod.GET)
    public ModelAndView getCashBillDeatilById(@PathVariable("comParentId") Integer comParentId,
                                              @PathVariable("proParentId") Integer proParentId, ModelMap mop){
        ResultData<?> resultData = null;
        try{
            Map<String, Object> map = new HashMap<>();
            map.put("comParentId", comParentId);
            map.put("proParentId", proParentId);
            resultData = pmlsCashBillService.getCashBillDeatilById(map);
            if(resultData != null){
                mop.addAttribute("cashBillInfo", resultData.getReturnData());
                mop.addAttribute("cashBillInfoJson", JSON.toJSON(resultData.getReturnData()));
            }
        }catch (Exception e){
            logger.error("pmlsCashBill", "PmlsCashBillController", "getCashBillDeatilById", "", cn.com.eju.deal.base.support.UserInfoHolder.getUserId(), "", "查看请款单详情失败", e);
        }
        //返回视图
//        ModelAndView mv = new ModelAndView("cashBill/cashBillDetail");
        ModelAndView mv = new ModelAndView("scene/sceneExpent/expentDetail");
        return mv;
    }
}
