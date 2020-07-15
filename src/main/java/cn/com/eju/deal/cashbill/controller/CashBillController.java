package cn.com.eju.deal.cashbill.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.cashbill.model.CashBillProject;
import cn.com.eju.deal.cashbill.service.CashBillService;
import cn.com.eju.deal.common.service.CommonService;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.common.util.DateEditor;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yinkun on 2018/8/13.
 */
@Controller
@RequestMapping("cashBill")
public class CashBillController extends BaseController {

    @Autowired
    private CashBillService cashBillService;

    @Resource(name = "commonService")
    private CommonService commonService;

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        //对于需要转换为Date类型的属性，使用DateEditor进行处理
        binder.registerCustomEditor(Date.class, new DateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    @RequestMapping("/toCashBillPop")
    public String toCashBillPop(HttpServletRequest request, ModelMap modelMap){

        ResultData<?> resultData = new ResultData<>();

        try{
            Map<String, Object> map = bindParamToMap(request);
            map.put("userCode",UserInfoHolder.get().getUserCode());
            resultData = cashBillService.selectCashBillForPop(map);
            if("200".equals(resultData.getReturnCode())){
                modelMap.addAttribute("project",resultData.getReturnData());

                if(resultData != null){
                    Map<String, Object> resultDataMap = (Map<String, Object>) resultData.getReturnData();
                    if(resultDataMap!=null && resultDataMap.get("cityNo")!=null){
                        String cityNo = resultDataMap.get("cityNo").toString();
                        modelMap.addAttribute("estateCityNo", cityNo);
                        ResultData<?> resultDataSwitch = commonService.getSwitchInfo(cityNo);
                        Map<String, Object> mapSwitch = (Map<String, Object>) resultDataSwitch.getReturnData();
                        if(null != mapSwitch.get("yearMonth")){
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(mapSwitch.get("yearMonth").toString());
                            String dateString = formatter.format(date);
                            modelMap.addAttribute("yearMonth", dateString);
                        }

                    }
                }
            }
        }catch (Exception e){
            logger.error("cashBill", "CashBillController", "toCashBillPop", "", UserInfoHolder.getUserId(), "", "查询批量请款信息异常", e);
        }

        return "cashBill/cashBillPop";
    }

    @RequestMapping("/addToBatchCash")
    @ResponseBody
    public String addToBatchCash(HttpServletRequest request){
        ResultData<?> resultData = new ResultData<>();

        try{
            Map<String, Object> map = bindParamToMap(request);
            map.put("userCode", UserInfoHolder.get().getUserCode());
            map.put("userId", UserInfoHolder.getUserId());
            map.put("cityNo", UserInfoHolder.get().getCityNo());
            resultData = cashBillService.addToBatchCash(map);
        }catch (Exception e){
            resultData.setFail("加入批量请款异常");
            logger.error("cashBill", "CashBillController", "addToBatchCash", "", UserInfoHolder.getUserId(), "", "加入批量请款异常", e);
        }

        return resultData.toString();
    }

    @RequestMapping("/removeFromCashBill")
    @ResponseBody
    public String removeFromCashBill(HttpServletRequest request){
        ResultData<?> resultData = new ResultData<>();

        try{
            Map<String, Object> map = bindParamToMap(request);
            resultData = cashBillService.removeFromCashBill(map);
        }catch (Exception e){
            resultData.setFail("移除批量请款异常");
            logger.error("cashBill", "CashBillController", "removeFromCashBill", "", UserInfoHolder.getUserId(), "", "移除批量请款异常", e);
        }

        return resultData.toString();
    }

    @RequestMapping("/getBatchCashNumber")
    @ResponseBody
    public String getBatchCashNumber(HttpServletRequest request){
        ResultData<String> resultData = new ResultData<>();

        try {
            Map<String,Object> queryParam = bindParamToMap(request);
            queryParam.put("userCode",UserInfoHolder.get().getUserCode());
            ResultData<?> cashBill = cashBillService.selectCashBill(queryParam);
            if("200".equals(cashBill.getReturnCode())&&cashBill.getTotalCount() != null){
                resultData.setReturnData(cashBill.getTotalCount());
            }
        }catch (Exception e){
            resultData.setFail("查询批量请款套数异常");
            logger.error("CashBill", "CashBillController", "getBatchCashNumber", "", null, "", "查询批量请款套数异常", e);
        }

        return resultData.toString();
    }

    @RequestMapping("/saveCashBill")
    @ResponseBody
    public String saveCashBill(HttpServletRequest request, CashBillProject project){
        ResultData<String> resultData = new ResultData<>();

        String msg = "保存";
        if(1 == project.getSubmitStatus().intValue()){
            msg = "提交";
        }

        try {
            project.setUserCode(UserInfoHolder.get().getUserCode());
            resultData = cashBillService.saveCashBill(project);
        }catch (Exception e){
            resultData.setFail("批量请款"+msg+"异常");
            logger.error("CashBill", "CashBillController", "saveCashBill", "", null, "", "批量请款"+msg+"异常", e);
        }

        return resultData.toString();
    }
    /**
     * @Title: list
     * @Description: 请款单列表
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, ModelMap mop,HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("cashBill/cashBillList");
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
        }
        return mv;
    }

    /**
     * 请款单列表列表
     * @param request
     * @return
     */
    @RequestMapping(value = "getCashBillList", method = RequestMethod.GET)
    public ModelAndView getCashBillList(HttpServletRequest request, ModelMap mop,HttpServletResponse response)
    {
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        //返回list
        ResultData resultData = null;
        List<?> contentlist = new ArrayList<>();
        try {
            if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.CASHBILL_LIST_SEARCH, reqMap);
            }
            reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
            resultData = cashBillService.getCashBillList(reqMap,pageInfo);
            if(resultData != null){
                contentlist = (List<?>) resultData.getReturnData();
            }
        }catch (Exception e){
            logger.error("PMLS", "CashBillController", "getCashBillList", reqMap.toString(), null, "", "获取请款单列表页失败", e);
        }
        //存放到mop中
        mop.addAttribute("userCode",UserInfoHolder.get().getUserCode());
        mop.addAttribute("contentlist", contentlist);
        ModelAndView mv = new ModelAndView("cashBill/cashBillListCtx");
        return mv;
    }
    /**
     * 根据id查询框架合同详情
     * @param comParentId
     * @param mop
     * @return
     */
    @RequestMapping(value = "/{comParentId}/{proParentId}", method = RequestMethod.GET)
    public ModelAndView getCashBillDeatilById(@PathVariable("comParentId") Integer comParentId,
                                              @PathVariable("proParentId") Integer proParentId, ModelMap mop){
        ResultData<?> resultData = null;
        try{
            Map<String, Object> map = new HashMap<>();
            map.put("comParentId", comParentId);
            map.put("proParentId", proParentId);
            resultData = cashBillService.getCashBillDeatilById(map);
            if(resultData != null){
                mop.addAttribute("cashBillInfo", resultData.getReturnData());
            }
        }catch (Exception e){
            logger.error("Store", "CashBillController", "getCashBillDeatilById", "", UserInfoHolder.getUserId(), "", "查看请款单详情失败", e);
        }
        //返回视图
//        ModelAndView mv = new ModelAndView("cashBill/cashBillDetail");
        ModelAndView mv = new ModelAndView("cashBill/cashBillNewDetail");
        return mv;
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
            listToSubmitOa = cashBillService.listToSubmitOa(reqMap);
            if(ReturnCode.FAILURE.equals(listToSubmitOa.getReturnCode())) {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, listToSubmitOa.getReturnMsg());
            }
        } catch (Exception e) {
            logger.error("Store", "CashBillController", "listToSubmitOa", "", UserInfoHolder.getUserId(), "", "提交OA失败", e);
        }
        return listToSubmitOa.toString();
    }
    /**
     * 获取联动核算主体
     * @param mop
     * @return
     */
    @RequestMapping(value = "getLnkAccountProjectList", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getLnkAccountProjectList(HttpServletRequest request,ModelMap mop){
        ResultData<?> resultData = new ResultData<>();
        Map<String, Object> reqMap = bindParamToMap(request);
        String cityNo = "";
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try {
            if(reqMap.containsKey("estateCityNo")) {
                cityNo = reqMap.get("estateCityNo").toString();
                resultData = this.cashBillService.getLnkAccountProjectList(cityNo);
            }
        }catch (Exception e){
            logger.error("PMLS","CashBillController","getLnkAccountProjectList","",UserInfoHolder.getUserId(),"","获取联动核算主体列表",e);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        if (null != resultData.getReturnData()) {
            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        }
        return getMapView(rspMap);
    }


    @RequestMapping("/toCashBillView/{estateId}")
    public String toCashBillView(@PathVariable("estateId") String estateId, ModelMap modelMap){

        ResultData<?> resultData = new ResultData<>();

        try{
            Map<String, Object> map = new HashMap<>();
            map.put("estateId",estateId);
            map.put("userCode",UserInfoHolder.get().getUserCode());
            resultData = cashBillService.selectCashBillForPop(map);
            if("200".equals(resultData.getReturnCode())){
                modelMap.addAttribute("project",resultData.getReturnData());

                if(resultData != null){
                    Map<String, Object> resultDataMap = (Map<String, Object>) resultData.getReturnData();
                    if(resultDataMap!=null && resultDataMap.get("cityNo")!=null){
                        String cityNo = resultDataMap.get("cityNo").toString();
                        modelMap.addAttribute("estateCityNo", cityNo);
                        ResultData<?> resultDataSwitch = commonService.getSwitchInfo(cityNo);
                        Map<String, Object> mapSwitch = (Map<String, Object>) resultDataSwitch.getReturnData();
                        if(null != mapSwitch.get("yearMonth")){
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(mapSwitch.get("yearMonth").toString());
                            String dateString = formatter.format(date);
                            modelMap.addAttribute("yearMonth", dateString);
                        }

                    }
                }
            }
        }catch (Exception e){
            logger.error("cashBill", "CashBillController", "toCashBillView", "", UserInfoHolder.getUserId(), "", "查询批量请款信息异常", e);
        }

        return "cashBill/cashBillView";
    }

    /**
     * desc:框架协议
     * 2019年8月9日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/getOAFrmAgreement", method = RequestMethod.GET)
    public String getFrmAgreement(HttpServletRequest request, ModelMap mop)
    {
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.put("accountProjectCode", reqMap.get("accountProjectCode"));//核算主体code
        mop.putAll(reqMap);
        return "cashBill/frmAgreementListPopup";
    }
    /**
     * desc:收款银行信息
     * 2019年8月9日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/getOAReceiveBank", method = RequestMethod.GET)
    public String getReceiveBank(HttpServletRequest request, ModelMap mop)
    {
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.put("vendorId", reqMap.get("vendorId"));//供应商ID
        mop.putAll(reqMap);
        return "cashBill/receiveBankListPopup";
    }

    /**
     * 获取联动核算主体
     * @param mop
     * @return
     */
    @RequestMapping(value = "getOALnkAccountProjectList", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getOALnkAccountProjectList(HttpServletRequest request,ModelMap mop){
        ResultData<?> resultData = new ResultData<>();
        Map<String, Object> reqMap = bindParamToMap(request);
        String projectNo = "";
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try {
            if(reqMap.containsKey("projectNo")) {
                projectNo = reqMap.get("projectNo").toString();
                resultData = this.cashBillService.getOALnkAccountProjectList(projectNo);
            }
        }catch (Exception e){
            logger.error("CRM","CashBillController","getOALnkAccountProjectList","",UserInfoHolder.getUserId(),"","获取联动核算主体列表",e);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        if (null != resultData.getReturnData()) {
            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        }
        return getMapView(rspMap);
    }


    /**
     * desc: 查询框架协议相关信息
     * 2019年8月9日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/getOAFrmAgreementList/q", method = RequestMethod.GET)
    public String getOAFrmAgreementList(HttpServletRequest request, ModelMap mop)
    {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        List<?> contentlist = new ArrayList<>();
        try
        {
            PageInfo pageInfo = getPageInfo(request);
            ResultData<?> reback = cashBillService.getOAFrmAgreementList(reqMap, pageInfo);
            //页面数据
            contentlist = (List<?>)reback.getReturnData();
            //存放到mop中
            mop.addAttribute("contentlist", contentlist);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        mop.addAttribute("contentlist", contentlist);
        return "cashBill/frmAgreementListCtxPopup";
    }

    /**
     * desc:银行信息查询
     * 2019年8月9日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/getOAReceiveBankList/q", method = RequestMethod.GET)
    public String getOAReceiveBankList(HttpServletRequest request, ModelMap mop)
    {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        List<?> contentlist = new ArrayList<>();
        try
        {
            PageInfo pageInfo = getPageInfo(request);
            ResultData<?> reback = cashBillService.getOAReceiveBankList(reqMap, pageInfo);
            //页面数据
            contentlist = (List<?>)reback.getReturnData();
            //存放到mop中
            mop.addAttribute("contentlist", contentlist);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        mop.addAttribute("contentlist", contentlist);
        return "cashBill/receiveBankListCtxPopup";
    }


    /**
     * desc:考核主体查询
     * 2019年8月28日
     * author:huangmc
     */
    @RequestMapping(value = "/getOACheckBodyList", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getOACheckBodyList(HttpServletRequest request, ModelMap mop)
    {
        ResultData<?> resultData = new ResultData<>();
        Map<String, Object> reqMap = bindParamToMap(request);
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try
        {
            resultData = cashBillService.getOACheckBodyList(reqMap);

        }
        catch (Exception e)
        {
            logger.error("CRM","CashBillController","getOACheckBodyList","",UserInfoHolder.getUserId(),"","获取考核主体列表",e);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        if (null != resultData.getReturnData()) {
            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        }
        return getMapView(rspMap);

    }


    @RequestMapping("/saveOACashBill")
    @ResponseBody
    public String saveOACashBill(HttpServletRequest request, CashBillProject project){
        ResultData<String> resultData = new ResultData<>();

        String msg = "保存";
        if(1 == project.getSubmitStatus().intValue()){
            msg = "提交";
        }

        try {
            project.setUserCode(UserInfoHolder.get().getUserCode());
            resultData = cashBillService.saveOACashBill(project);
        }catch (Exception e){
            resultData.setFail("批量请款"+msg+"异常");
            logger.error("CashBill", "CashBillController", "saveOACashBill", "", null, "", "批量请款"+msg+"异常", e);
        }

        return resultData.toString();
    }
    
    /**
     * 
     * desc:删除
     * 2019年9月23日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> remove(HttpServletRequest request, ModelMap mop) {

        Map<String, Object> rspMap = new HashMap<String, Object>();

        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);

        try {
        	cashBillService.remove(reqMap);
        } catch (Exception e) {
            logger.error("CashBill", "CashBillController", "remove", "",
                    UserInfoHolder.getUserId(), "", "删除请款单失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "删除失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }
}
