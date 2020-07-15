package cn.com.eju.deal.houseLinkage.storeDeposit.controller;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.houseLinkage.storeDeposit.service.StoreDepositDeatilService;

import cn.com.eju.deal.houseLinkage.storeDepositSerial.service.StoreDepositSerialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller层
 * 保证金明细
 */
@Controller
@RequestMapping(value = "storeDepositDeatil")
public class StoreDepositDeatilController extends BaseReportController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private StoreDepositDeatilService storeDepositDeatilService;
    @Autowired
    private StoreDepositSerialService storeDepositSerialService;
    /**
     * 初始化
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String storeDepositDeatilList(ModelMap mop) {
        try{
            ResultData resultData = storeDepositSerialService.queryAccountProject();
            mop.addAttribute("accountList",resultData.getReturnData());
        }
        catch (Exception e){
            logger.error("crm", "StoreDepositDeatilController", "queryAccountProject", "", null, "", "根据城市No获取其核算主体", e);
        }

        return "houseLinkage/storeDepositDeatil/storeDepositDeatilList";
    }
    /** 
     * 根据城市CityNo获取其核算主体
     * @throws Exception
     */
     @RequestMapping(value = "/queryAccountProject/{cityNo}", method = RequestMethod.GET)
     @ResponseBody
     public ReturnView<?, ?> queryAccountProject(@PathVariable("cityNo") String cityNo) {
         //返回map
         Map<String, Object> rspMap = new HashMap<String, Object>();
         
         //返回map
         ResultData resultData = new ResultData();
         
         try{
             resultData = storeDepositDeatilService.queryAccountProject(cityNo);
         }
         catch (Exception e){
             logger.error("crm", "StoreDepositDeatilController", "queryAccountProject", "", null, "", "根据城市No获取其核算主体", e);
         }
         
         rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
         rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
         rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
         
         return getSearchJSONView(rspMap);
     }

    /**
     * 查询--list
     */
    @RequestMapping(value = "queryStoreDepositDeatilList", method = RequestMethod.GET)
    public String queryStoreDepositDeatilList(HttpServletRequest request, ModelMap mop) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        PageInfo pageInfo = getPageInfo(request);
        reqMap.put("userId", UserInfoHolder.getUserId());

        //页面数据
        List<?> contentlist = null;
        try {
            //获取页面显示数据
            ResultData<?> reback = storeDepositDeatilService.queryStoreDepositDeatilList(reqMap, pageInfo);
            contentlist = (List<?>) reback.getReturnData();
        } catch (Exception e) {
            logger.error("storeDepositDeatil", "StoreDepositDeatilController", "queryStoreDepositDeatilList", "", null, "", "查询--list", e);
        }
        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        return "houseLinkage/storeDepositDeatil/storeDepositDeatilListCtx";
    }

    /**
     * 导出EXCEL
     */
    @RequestMapping(value = "exportStoreDepositDeatil", method = RequestMethod.GET)
    public void exportStoreDepositDeatil(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        reqMap.put("optFlag", "export");
        try {
            ResultData<?> reback = storeDepositDeatilService.queryStoreDepositDeatilList(reqMap, null);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            downLoadExcel(request, response, contentlist, reqMap, ReportConstant.STOREDEPOSITDEATIL_CODE, ReportConstant.STOREDEPOSITDEATIL_NAME);

        } catch (Exception e) {
            logger.error("storeDepositDeatil", "StoreDepositDeatilController", "export", "input param: reqMap=" + reqMap.toString(), -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }

    }
    
}
