package cn.com.eju.pmls.otherReport.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.houseLinkage.report.service.ReportService;
import cn.com.eju.pmls.otherReport.service.PmlsQtReportService;
import cn.com.eju.pmls.otherReport.service.PmlsQtSuccessSaleService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "pmlsQtSuccessSale")
public class PmlsQtSuccessSaleController extends BaseController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "pmlsQtSuccessSaleService")
    private PmlsQtSuccessSaleService pmlsQtSuccessSaleService;

    @Resource(name = "pmlsQtReportService")
    private PmlsQtReportService pmlsQtReportService;

    @Resource(name = "reportService")
    private ReportService reportService;

    @RequestMapping(value = "qtSuccessSaleHandle/{id}", method = RequestMethod.GET)
    public String qtSuccessSaleHandle(@PathVariable("id") Integer id, HttpServletRequest request, ModelMap mop, HttpServletResponse response)
    {
        ResultData<?> resultData = null;
        ResultData<?> accountProjectList = null;
        try{
            // 报备信息
            Map<String, Object> reqMap = new HashMap<>();
            reqMap.put("id", id);
            resultData = pmlsQtReportService.getQtReportById(reqMap);

            // 核算主体
            String cityNo =UserInfoHolder.get().getCityNo();
            accountProjectList = reportService.getAccountProject(cityNo);



        }catch (Exception ex){
            logger.error("qtSuccessSale", "QtSuccessSaleController", "qtSuccessSaleHandle", "", null, "", "跳转其他收入成销页面", ex);

        }
        mop.addAttribute("qtReportInfo", resultData.getReturnData());
        mop.addAttribute("qtReportInfoJson", JSON.toJSON(resultData.getReturnData()));
        mop.addAttribute("accountProjectList", accountProjectList.getReturnData());
        String countDateEndStr = DateUtil.fmtDate(new Date(),"yyyy-MM-dd");
        mop.put("countDateEndStr",countDateEndStr);
        return "otherReport/qtSuccessSaleHandle";
    }

    /**
     * 成销保存
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "successSale", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> successSale(HttpServletRequest request, ModelMap mop)
    {

        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("dealUserId",UserInfoHolder.getUserId());

        ResultData<?> resultData = new ResultData<>();
        try
        {
            resultData = pmlsQtSuccessSaleService.successSale(reqMap);
        }
        catch (Exception e)
        {
            logger.error("qtSuccessSale", "QtSuccessSaleController", "successSale", reqMap.toString(), null, "", "其他收入成销保存失败", e);
        }

        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        if (null != resultData.getReturnData())
        {
            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        }
        return getMapView(rspMap);
    }

    /**
     * 跳转退成销页面
     *
     * @param id
     * @param mop
     * @return
     */
    @RequestMapping(value = "toDealBack/{id}", method = RequestMethod.GET)
    public String toDealBack(@PathVariable("id") Integer id, ModelMap mop) {
        ResultData<?> resultData = null;
        try {
            resultData = pmlsQtSuccessSaleService.getDealRecord(id);
        } catch (Exception e) {
            logger.error("qtSuccessSale", "QtSuccessSaleController", "toDealBack", id.toString(), null, "", "其他收入跳转退成销页面", e);
        }
        mop.addAttribute("qtReport", resultData.getReturnData());
        String countDateEndStr = DateUtil.fmtDate(new Date(),"yyyy-MM-dd");
        mop.put("countDateEndStr",countDateEndStr);
        return "otherReport/qtDealBack";
    }

    @RequestMapping(value = "dealBack", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> dealBack(HttpServletRequest request) {
        Map<String, Object> rspMap = new HashMap<String, Object>();
        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            reqMap.put("backDealUserId", UserInfoHolder.getUserId());
            ResultData<?> resultData = pmlsQtSuccessSaleService.dealBack(reqMap);
            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            logger.error("qtSuccessSale", "QtSuccessSaleController", "dealBack", reqMap.toString(), null, "", "其他收入创建退成销记录失败！", e);
        }
        return getOperateJSONView(rspMap);
    }



}
