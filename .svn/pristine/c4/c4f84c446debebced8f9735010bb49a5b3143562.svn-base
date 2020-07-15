package cn.com.eju.pmls.student.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.pmls.student.service.StudentTestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eju on 2019/12/25.
 */
@Controller
@RequestMapping("/jszylnk")
//@RequestMapping("/test")
public class StudentTestController extends BaseController {

    @Resource(name = "studentTestService")
    private StudentTestService studentTestService;

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    /**
     * (初始化)
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping("")
    public String list(HttpServletRequest request, ModelMap mop)
    {
        return "student/testMain";
    }

    /**
     * (新增、修改 保存操作)
     * @param request
     * @return
     */
    @RequestMapping("/oaFrameContract")
    @ResponseBody
    public ReturnView<?, ?> oaFrameContract(HttpServletRequest request)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);


        int backNum = 0;
        ResultData<?> resultData =  new ResultData<>();

        try {
            resultData = studentTestService.oaFrameContract(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "框架协议审批失败！");
            logger.error("test", "StudentTestController", "oaFrameContract", "", null, "", "框架协议审批失败！", e);
        }
        return getOperateJSONView(rspMap);
    }

    @RequestMapping("/oaProject")
    @ResponseBody
    public ReturnView<?, ?> oaProject(HttpServletRequest request)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);


        int backNum = 0;
        ResultData<?> resultData =  new ResultData<>();

        try {
            resultData = studentTestService.oaProject(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "立项失败！");
            logger.error("test", "StudentTestController", "oaProject", "", null, "", "立项失败！", e);
        }
        return getOperateJSONView(rspMap);
    }

    @RequestMapping("/oaSignOrApproach")
    @ResponseBody
    public ReturnView<?, ?> oaSignOrApproach(HttpServletRequest request)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);


        int backNum = 0;
        ResultData<?> resultData =  new ResultData<>();

        try {
            resultData = studentTestService.oaSignOrApproach(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "新签/进场确认失败！");
            logger.error("test", "StudentTestController", "oaSignOrApproach", "", null, "", "新签/进场确认失败！", e);
        }
        return getOperateJSONView(rspMap);
    }

    @RequestMapping("/oaDistribution")
    @ResponseBody
    public ReturnView<?, ?> oaDistribution(HttpServletRequest request)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);


        int backNum = 0;
        ResultData<?> resultData =  new ResultData<>();

        try {
            resultData = studentTestService.oaDistribution(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "分销合同审核失败！");
            logger.error("test", "StudentTestController", "oaDistribution", "", null, "", "分销合同审核失败！", e);
        }
        return getOperateJSONView(rspMap);
    }


    @RequestMapping("/oaStatement")
    @ResponseBody
    public ReturnView<?, ?> oaStatement(HttpServletRequest request)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);


        int backNum = 0;
        ResultData<?> resultData =  new ResultData<>();

        try {
            resultData = studentTestService.oaStatement(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "结算书审核失败！");
            logger.error("test", "StudentTestController", "oaStatement", "", null, "", "结算书审核失败！", e);
        }
        return getOperateJSONView(rspMap);
    }

    @RequestMapping("/oaAgreement")
    @ResponseBody
    public ReturnView<?, ?> oaAgreement(HttpServletRequest request)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);


        int backNum = 0;
        ResultData<?> resultData =  new ResultData<>();

        try {
            resultData = studentTestService.oaAgreement(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "借佣三方协议审核失败！");
            logger.error("test", "StudentTestController", "oaAgreement", "", null, "", "借佣三方协议审核失败！", e);
        }
        return getOperateJSONView(rspMap);
    }

    @RequestMapping("/oaReceivables")
    @ResponseBody
    public ReturnView<?, ?> oaReceivables(HttpServletRequest request)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);


        int backNum = 0;
        ResultData<?> resultData =  new ResultData<>();

        try {
            resultData = studentTestService.oaReceivables(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "借佣请款单审核失败！");
            logger.error("test", "StudentTestController", "oaReceivables", "", null, "", "借佣请款单审核失败！", e);
        }
        return getOperateJSONView(rspMap);
    }

    @RequestMapping("/oaExpenditure")
    @ResponseBody
    public ReturnView<?, ?> oaExpenditure(HttpServletRequest request)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);


        int backNum = 0;
        ResultData<?> resultData =  new ResultData<>();

        try {
            resultData = studentTestService.oaExpenditure(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "借佣支出单审核失败！");
            logger.error("test", "StudentTestController", "oaExpenditure", "", null, "", "借佣支出单审核失败！", e);
        }
        return getOperateJSONView(rspMap);
    }

    @RequestMapping("/oaQk")
    @ResponseBody
    public ReturnView<?, ?> oaQk(HttpServletRequest request)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);


        int backNum = 0;
        ResultData<?> resultData =  new ResultData<>();

        try {
            resultData = studentTestService.oaQk(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "请款单审核失败！");
            logger.error("test", "StudentTestController", "oaQk", "", null, "", "请款单审核失败！", e);
        }
        return getOperateJSONView(rspMap);
    }

}
