package cn.com.eju.pmls.borrowMoney.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.dto.code.CommonCodeDto;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.pmls.borrowMoney.dto.BorrowMoneyDto;
import cn.com.eju.pmls.borrowMoney.dto.HkPlanDto;
import cn.com.eju.pmls.borrowMoney.service.BorrowMoneyService;
import cn.com.eju.pmls.channelBusiness.dto.BusinessManagerDto;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "borrowMoneyController")
public class BorrowMoneyController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource(name = "borrowMoneyService")
    private BorrowMoneyService borrowMoneyService;

    //借佣申请页面
    @RequestMapping(value = "borrowMoneyList", method = RequestMethod.GET)
    public String borrowMoneyList(Model model, HttpServletRequest request) {
        List<CommonCodeDto> progressStatusList = SystemParam.getCodeListByKey("282");//借佣进度
        model.addAttribute("progressStatusList", JsonUtil.parseToJson(progressStatusList));
        return "borrowMoney/borrowMoneyList";
    }

    //借佣详情页面
    @RequestMapping(value = "borrowMoneyDetailPage", method = RequestMethod.GET)
    public String borrowMoneyDetailPage(Model model, HttpServletRequest request) {
        String id=request.getParameter("id");
        ResultData<BusinessManagerDto> resultData = new ResultData<BusinessManagerDto>();
        try {
            Map<String, Object> reqMap = bindParamToMap(request);
            BorrowMoneyDto dto = (BorrowMoneyDto) MapToEntityConvertUtil.convert(reqMap,BorrowMoneyDto.class,"");
            resultData = borrowMoneyService.getBorrowMoneyInfo(dto);
            model.addAttribute("id",id);
            model.addAttribute("borrowMoneyDto", JSONObject.toJSON(resultData.getReturnData()));

        }catch (Exception e){
            e.printStackTrace();
        }
        return "borrowMoney/borrowMoneyDetailPage";
    }

    //还款计划列表页面
    @RequestMapping(value = "hkPlanList", method = RequestMethod.GET)
    public String hkPlanList(Model model, HttpServletRequest request) {
        return "borrowMoney/hkPlanList";
    }

    //还款计划详情页面
    @RequestMapping(value = "hkPlanDetailPage", method = RequestMethod.GET)
    public String hkPlanDetailPage(Model model, HttpServletRequest request) {
        String id=request.getParameter("id");
        ResultData<HkPlanDto> resultData = new ResultData<HkPlanDto>();
        try {
            Map<String, Object> reqMap = bindParamToMap(request);
            HkPlanDto dto = (HkPlanDto) MapToEntityConvertUtil.convert(reqMap,HkPlanDto.class,"");
            resultData = borrowMoneyService.getHkPlanInfo(dto);
            model.addAttribute("id",id);
            model.addAttribute("hkPlanDto", JSONObject.toJSON(resultData.getReturnData()));

        }catch (Exception e){
            e.printStackTrace();
        }
        return "borrowMoney/hkPlanDetailPage";
    }
    //还款页面
    @RequestMapping(value = "/updateHkPlanPage", method = RequestMethod.GET)
    public String updateHkPlanPage(HttpServletRequest request, Model model){
        String id=request.getParameter("id");
        String jkDate=request.getParameter("jkDate");
        List<CommonCodeDto> progressStatusList = SystemParam.getCodeListByKey("285");//还款类型
        model.addAttribute("hkTypeList", JsonUtil.parseToJson(progressStatusList));
        model.addAttribute("id", id);
        model.addAttribute("jkDate", jkDate);
        return "borrowMoney/updateHkPlanPage";
    }

    /**
     * 查询借佣申请列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getBorrowMoneyList", method = RequestMethod.POST)
    public ResultData<?> getBorrowMoneyList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            reqMap.put("acCityNo", UserInfoHolder.get().getCityNo());
            //获取页面显示数据
            resultData = borrowMoneyService.getBorrowMoneyList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("BorrowMoneyController",
                    "BorrowMoneyController",
                    "getBorrowMoneyList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询借佣申请列表",
                    e);
        }

        return resultData;
    }

    /**
     * 查询借佣进度列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getBorrowMoneyProgressList", method = RequestMethod.POST)
    public ResultData<?> getBorrowMoneyProgressList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            reqMap.put("acCityNo", UserInfoHolder.get().getCityNo());
            //获取页面显示数据
            resultData = borrowMoneyService.getBorrowMoneyProgressList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("BorrowMoneyController",
                    "BorrowMoneyController",
                    "getBorrowMoneyProgressList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询借佣进度列表",
                    e);
        }

        return resultData;
    }

    /**
     * 查询借佣申请明细列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getBorrowMoneyDetailList", method = RequestMethod.POST)
    public ResultData<?> getBorrowMoneyDetailList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            reqMap.put("acCityNo", UserInfoHolder.get().getCityNo());
            //获取页面显示数据
            resultData = borrowMoneyService.getBorrowMoneyDetailList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("BorrowMoneyController",
                    "BorrowMoneyController",
                    "getBorrowMoneyDetailList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询借佣申请明细列表",
                    e);
        }

        return resultData;
    }

    /**
     * 获取还款计划列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getHkPlanList", method = RequestMethod.POST)
    public ResultData<?> getHkPlanList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            reqMap.put("acCityNo", UserInfoHolder.get().getCityNo());
            //获取页面显示数据
            resultData = borrowMoneyService.getHkPlanList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("BorrowMoneyController",
                    "BorrowMoneyController",
                    "getHkPlanList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "获取还款计划列表",
                    e);
        }

        return resultData;
    }
    /**
     * 获取还款利息列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getHkPlanInterestList", method = RequestMethod.POST)
    public ResultData<?> getHkPlanInterestList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            reqMap.put("acCityNo", UserInfoHolder.get().getCityNo());
            //获取页面显示数据
            resultData = borrowMoneyService.getHkPlanInterestList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("BorrowMoneyController",
                    "BorrowMoneyController",
                    "getHkPlanInterestList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "获取还款利息列表",
                    e);
        }

        return resultData;
    }

    /**
     * 还款操作
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateHkPlan", method = RequestMethod.POST)
    public ResultData<?> updateHkPlan(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            HkPlanDto dto = (HkPlanDto) MapToEntityConvertUtil.convert(reqMap,HkPlanDto.class,"");
            dto.setUserIdUpdate(UserInfoHolder.getUserId().toString());
            resultData = borrowMoneyService.updateHkPlan(dto);
        } catch (Exception e) {
            logger.error("BorrowMoneyController",
                    "BorrowMoneyController",
                    "updateHkPlan",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "还款操作",
                    e);
        }

        return resultData;
    }
}
