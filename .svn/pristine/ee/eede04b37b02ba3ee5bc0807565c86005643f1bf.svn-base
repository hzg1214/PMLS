package cn.com.eju.pmls.scene.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.cashbill.model.CashBillCompany;
import cn.com.eju.deal.cashbill.model.CashBillProject;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.pmls.commission.service.PmlsCashBillService;
import cn.com.eju.pmls.scene.service.SceneExpentService;
import cn.com.eju.pmls.scene.service.SceneTradeService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping(value = "sceneExpent")
public class SceneExpentController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    SceneTradeService sceneTradeService;

    @Autowired
    SceneExpentService sceneExpentService;

    @Autowired
    private PmlsCashBillService pmlsCashBillService;

    @RequestMapping(value = "batchExpent/{estateId}", method = RequestMethod.GET)
    public ModelAndView batchExpent(@PathVariable("estateId") String estateId, ModelMap mop, HttpServletRequest request) {

        try {
            logger.info("获取楼盘详情信息,estateId:", estateId);
            ResultData<?> resultData = sceneTradeService.getByEstateId(estateId);
            logger.info("获取楼盘详情信息,estateId:" + estateId, resultData);

            mop.addAttribute("estate", resultData.getReturnData());

            Map<String, Object> resultDataMap = (Map<String, Object>) resultData.getReturnData();
            if (resultDataMap != null && resultDataMap.containsKey("projectNo")) {
                String projectNo = resultDataMap.get("projectNo").toString();
                ResultData<?> result = sceneExpentService.getOALnkAccountProjectList(projectNo);
                mop.addAttribute("oaAccountProjectList", result.getReturnData());
            }

        } catch (Exception ex) {
            logger.error("获取楼盘详情信息异常,estateId:" + estateId, ex);
            logger.error("scene", "SceneTradeController",
                    "batchExpent", estateId, null, "", "", ex);
        }

        ModelAndView mv = new ModelAndView("scene/sceneExpent/batchExpent");
        return mv;
    }

    @RequestMapping(value = "getBatchExpentInfo/{estateId}", method = RequestMethod.GET)
    @ResponseBody
    public ResultData<?> getBatchExpentInfo(@PathVariable("estateId") String estateId, HttpServletRequest request) {
        ResultData<?> resultData = new ResultData<>();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("estateId", estateId);
            map.put("userCode", UserInfoHolder.get().getUserCode());
            resultData = sceneExpentService.selectCashBillForPop(map);

        } catch (Exception ex) {
            logger.error("scene", "SceneExpentController",
                    "batchExpent", estateId, null, "", "", ex);
        }

        return resultData;
    }

    @RequestMapping(value = "expentDetail/{id}", method = RequestMethod.GET)
    public ModelAndView expentDetail(@PathVariable("id") String id, ModelMap mop, HttpServletRequest request) {

        ModelAndView mv = new ModelAndView("scene/sceneExpent/expentDetail");
        return mv;
    }

    /**
     * 加入批量请款
     */
    @RequestMapping(value = "addBatchExpent", method = RequestMethod.GET)
    public ResultData<?> addBatchExpent(HttpServletRequest request) {

        ResultData<?> resultData = new ResultData<>();

        Integer userId = UserInfoHolder.getUserId();
        String userCode = UserInfoHolder.get().getUserCode();
        String cityNo = UserInfoHolder.get().getCityNo();

        Map<String, Object> map = bindParamToMap(request);
        map.put("userCode", userCode);
        map.put("userId", userId);
        map.put("cityNo", cityNo);

        try {

            logger.info("加入批量请款,param:", map.toString());
            resultData = sceneExpentService.addBatchExpent(map);
            logger.info("加入批量请款,in-param:" + map.toString(), resultData);

        } catch (Exception e) {
            resultData.setFail("加入批量请款异常");
            logger.error("加入批量请款异常,param:" + map.toString(), e);
            logger.error("sceneExpent", "SceneExpentController",
                    "addBatchExpent", "", userId, "", "加入批量请款异常", e);
        }
        return resultData;
    }

    @RequestMapping(value = "getBatchCashNumber", method = RequestMethod.GET)
    public ResultData<String> getBatchCashNumber(HttpServletRequest request) {

        ResultData<String> resultData = new ResultData<>();

        Map<String, Object> map = bindParamToMap(request);

        Integer userId = UserInfoHolder.getUserId();
        String userCode = UserInfoHolder.get().getUserCode();

        map.put("userCode", userCode);

        try {
            logger.info("查询批量请款套数,param:", map.toString());
            ResultData<?> data = sceneExpentService.selectCashBill(map);
            resultData.setReturnData(data.getTotalCount());
            logger.info("查询批量请款套数,in-param:" + map.toString(), resultData);
        } catch (Exception e) {
            resultData.setFail("查询批量请款套数");
            logger.error("查询批量请款套数异常,param:" + map.toString(), e);
            logger.error("sceneExpent", "SceneExpentController",
                    "getBatchCashNumber", "", userId, "", "查询批量请款套数", e);
        }
        return resultData;
    }

    @RequestMapping(value = "/getOAFrmAgreement/{accountProjectCode}", method = RequestMethod.GET)
    public ModelAndView getFrmAgreement(@PathVariable("accountProjectCode") String accountProjectCode, HttpServletRequest request, ModelMap mop) {
        //Map<String, Object> reqMap = bindParamToMap(request);
        mop.put("accountProjectCode", accountProjectCode);//核算主体code
        //mop.putAll(reqMap);

        ModelAndView mv = new ModelAndView("scene/sceneExpent/frmAgreementListPopup");

        return mv;
    }

    @RequestMapping(value = "/getOAReceiveBank/{vendorId}", method = RequestMethod.GET)
    public ModelAndView getReceiveBank(@PathVariable("vendorId") String vendorId, HttpServletRequest request, ModelMap mop) {
        mop.put("vendorId", vendorId);//供应商ID
        ModelAndView mv = new ModelAndView("scene/sceneExpent/receiveBankListPopup");
        return mv;
    }

    @RequestMapping(value = "/getOffsetInfo", method = RequestMethod.GET)
    public ModelAndView getOffsetInfo(HttpServletRequest request, ModelMap mop) {
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.putAll(reqMap);
        ModelAndView mv = new ModelAndView("scene/sceneExpent/offsetInfoListPopup");
        return mv;
    }


    @RequestMapping(value = "getOALnkAccountProjectList", method = RequestMethod.GET)
    @ResponseBody
    public ResultData<?> getOALnkAccountProjectList(HttpServletRequest request, ModelMap mop) {
        ResultData<?> resultData = new ResultData<>();
        Map<String, Object> reqMap = bindParamToMap(request);

        try {
            if (reqMap.containsKey("projectNo")) {
                String projectNo = reqMap.get("projectNo").toString();
                resultData = sceneExpentService.getOALnkAccountProjectList(projectNo);
            }
        } catch (Exception e) {
            logger.error("scene", "SceneExpentController", "getOALnkAccountProjectList",
                    "", UserInfoHolder.getUserId(), "", "获取联动核算主体列表", e);
        }

        return resultData;
    }

    @RequestMapping(value = "/getOAFrmAgreementList/q", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> getOAFrmAgreementList(HttpServletRequest request, ModelMap mop) {
        ResultData<?> resultData = new ResultData<>();
        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            PageInfo pageInfo = getPageInfo(request);
            resultData = sceneExpentService.getOAFrmAgreementList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("scene", "SceneExpentController", "getOAFrmAgreementList",
                    "", UserInfoHolder.getUserId(), "", "获取框架协议列表", e);
        }

        return resultData;
    }

    @RequestMapping(value = "/getOAReceiveBankList/q", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> getOAReceiveBankList(HttpServletRequest request, ModelMap mop) {
        ResultData<?> resultData = new ResultData<>();
        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            PageInfo pageInfo = getPageInfo(request);
            resultData = sceneExpentService.getOAReceiveBankList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("scene", "SceneExpentController", "getOAReceiveBankList",
                    "", UserInfoHolder.getUserId(), "", "获取银行信息列表", e);
        }

        return resultData;
    }

    @RequestMapping(value = "/getOACheckBodyList", method = RequestMethod.GET)
    @ResponseBody
    public ResultData<?> getOACheckBodyList(HttpServletRequest request, ModelMap mop) {

        ResultData<?> resultData = new ResultData<>();
        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            resultData = sceneExpentService.getOACheckBodyList(reqMap);
        } catch (Exception e) {
            logger.error("scene", "SceneExpentController", "getOACheckBodyList",
                    "", UserInfoHolder.getUserId(), "", "获取考核主体列表", e);
        }

        return resultData;

    }

    @RequestMapping(value = "/saveOACashBill", method = RequestMethod.POST)
    public ResultData<?> saveOACashBill(HttpServletRequest request, @RequestBody CashBillProject project) {
        ResultData<?> resultData = new ResultData<>();

        String msg = "保存";
        if (1 == project.getSubmitStatus().intValue()) {
            msg = "提交";
        }

        try {
            project.setUserCode(UserInfoHolder.get().getUserCode());
            resultData = sceneExpentService.saveOACashBill(project);
        } catch (Exception e) {
            resultData.setFail("批量请款" + msg + "异常");
            logger.error("scene", "SceneExpentController", "saveOACashBill",
                    "", null, "", "批量请款" + msg + "异常", e);
        }

        return resultData;
    }

    @RequestMapping(value = "/removeFromCashBill", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> removeFromCashBill(HttpServletRequest request, ModelMap mop) {

        ResultData<?> resultData = new ResultData<>();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);

        try {
            resultData = sceneExpentService.removeFromCashBill(reqMap);
        } catch (Exception e) {
            resultData.setFail("移除批量请款异常");
            logger.error("scene", "SceneExpentController", "removeFromCashBill", "",
                    UserInfoHolder.getUserId(), "", "移除批量请款异常！", e);
        }
        return resultData;
    }

    /*

     */
    @RequestMapping(value = "/getOffsetInfoList/q", method = RequestMethod.POST)
    public ResultData<?> getOffsetInfoList(HttpServletRequest request) {
        ResultData<?> resultData = new ResultData<>();
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        List<?> contentlist = new ArrayList<>();
        try {
            PageInfo pageInfo = getPageInfo(request);
            resultData = sceneExpentService.getOffsetInfoList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("scene", "SceneExpentController", "getOffsetInfoList",
                    "", UserInfoHolder.getUserId(), "", "获取冲抵房源", e);
        }
        return resultData;
    }



    /**
     * new
     * 编辑页
     * */
    @RequestMapping(value = "batchExpentNewJs/{comParentId}/{proParentId}", method = RequestMethod.GET)
    public ModelAndView batchExpentNewJs(@PathVariable("comParentId") Integer comParentId
                ,@PathVariable("proParentId") Integer proParentId, ModelMap mop) {
        ResultData<?> resultData = null;
        try{
            if(comParentId!=0){
                Map<String, Object> map = new HashMap<>();
                map.put("comParentId", comParentId);
                map.put("proParentId", proParentId);
                resultData = sceneExpentService.getCashBillDeatilByIdNew(map);
                if(resultData != null){
                    mop.addAttribute("cashBillInfo", resultData.getReturnData());
                    mop.addAttribute("cashBillInfoJson", JSON.toJSON(resultData.getReturnData()));
                }
                mop.addAttribute("isEdit", "1");
            }else {
                String nowDate = DateUtil.fmtDate(new Date(),"yyyy-MM-dd");
                mop.addAttribute("nowDate", nowDate);
                mop.addAttribute("isEdit", "2");
            }
        }catch (Exception e){
            logger.error("查询请款单信息失败##comParentId="+comParentId, e);
            logger.error("sceneExpent", "SceneExpentController", "getCashBillDeatilById", "", cn.com.eju.deal.base.support.UserInfoHolder.getUserId(), "", "查询请款单信息失败", e);
        }
        ModelAndView mv = new ModelAndView("scene/sceneExpent/batchExpentNewJs");
        return mv;
    }

    /**
     * new
     * 选择结算单
     * */
    @RequestMapping(value = "jssListPopup", method = RequestMethod.GET)
    public ModelAndView jssListPopup() {
        return new ModelAndView("scene/sceneExpent/jssListPopup");
    }


    /**
     * new
     * 结算单明细
     * */
    @RequestMapping(value = "jssDtlListPopup", method = RequestMethod.GET)
    public ModelAndView jssDtlListPopup(ModelMap mop,HttpServletRequest request) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.addAttribute("reqMap", reqMap);
        return new ModelAndView("scene/sceneExpent/jssDtlListPopup");
    }
    @RequestMapping(value = "/selectJsStatementDtlListCanQk", method = RequestMethod.POST)
    public ResultData<?> selectJsStatementDtlListCanQk(HttpServletRequest request) {
        ResultData<?> resultData = new ResultData<>();
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            PageInfo pageInfo = getPageInfo(request);
            resultData = sceneExpentService.selectJsStatementDtlListCanQk(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("获取结算单明细##reqMap="+JSON.toJSONString(reqMap), e);
            logger.error("scene", "SceneExpentController", "selectJsStatementDtlListCanQk",
                    "", UserInfoHolder.getUserId(), "", "获取结算单明细", e);
        }
        return resultData;
    }

    /**
     * new
     * desc:保存
     */
    @RequestMapping(value = "/saveOACashBillNew", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> saveOACashBillNew(HttpServletRequest request, @RequestBody CashBillCompany cashBillCompany) {
        ResultData<?> resultData = new ResultData<>();

        String msg = "请款保存:";
        if (1 == cashBillCompany.getSubmitOaStatus().intValue()) {
            msg = "请款提交:";
        }
        try {
            cashBillCompany.setUserCode(UserInfoHolder.get().getUserCode());
            cashBillCompany.setUserIdCreate(UserInfoHolder.get().getUserId());
            resultData = sceneExpentService.saveOACashBillNew(cashBillCompany);
        } catch (Exception e) {
            logger.error("请款" + msg + "异常", e);
            resultData.setFail("请款" + msg + "失败");
            logger.error("scene", "SceneExpentController", "saveOACashBillNew",
                    "", null, "", "请款" + msg + "异常", e);
        }
        resultData.setReturnMsg(msg+resultData.getReturnMsg());
        return resultData;
    }


    /**
     * new
     * desc:作废
     */
    @RequestMapping(value = "/invalidBill", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> invalidBill(HttpServletRequest request, ModelMap mop) {
        ResultData<?> reback = new ResultData<>();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            reback = sceneExpentService.invalidBill(reqMap);
        } catch (Exception e) {
            reback.setFail("请款单作废失败");
            logger.error("请款单作废失败"+JSON.toJSONString(reqMap),e);
            logger.error("pmlsCashBill", "PmlsCashBillController", "invalidBill", "",
                    cn.com.eju.deal.base.support.UserInfoHolder.getUserId(), "", "请款单作废失败！", e);

        }
        return reback;
    }
}
