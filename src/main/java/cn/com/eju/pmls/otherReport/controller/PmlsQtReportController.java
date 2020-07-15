package cn.com.eju.pmls.otherReport.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cn.com.eju.deal.core.util.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.dto.student.StudentDto;
import cn.com.eju.deal.houseLinkage.estate.service.EstateService;
import cn.com.eju.pmls.otherReport.model.LnkQtReport;
import cn.com.eju.pmls.otherReport.service.PmlsQtReportService;

/**
 * @author :zhenggang.Huang
 * @date   :2019年10月11日
 */
@Controller
@RequestMapping(value = "pmlsQtReport")
public class PmlsQtReportController extends BaseController
{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "pmlsQtReportService")
    private PmlsQtReportService pmlsQtReportService;

    @Resource(name = "estateService")
    private EstateService estateService;

    /**
     * desc:初始化
     * 2019年10月12日
     * author:zhenggang.Huang
     */
     @RequestMapping(value = "", method = RequestMethod.GET)
     public ModelAndView list(){
         return new ModelAndView("otherReport/qtReportList");
     }
     
     /**
      * desc:列表
      * 2019年10月12日
      * author:zhenggang.Huang
      */
      @RequestMapping(value = "qtReportList", method = RequestMethod.POST)
      @ResponseBody
      public ResultData<?> qtReportList(HttpServletRequest request, ModelMap mop ){
          //获取请求参数
          Map<String, Object> reqMap = bindParamToMap(request);
          
          //页面数据
          ResultData<?> reback = null;
          try{
              PageInfo pageInfo = getPageInfo(request);
              reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
              //获取页面显示数据
              reback = pmlsQtReportService.queryList(reqMap, pageInfo);
          }catch (Exception e){
              logger.error("qtReport", "PmlsQtReportController", "qtReportList", "", null, "", "查询--list", e);
          }
          //存放到mop中
          mop.addAttribute("userAcCityNo", UserInfoHolder.get().getCityNo());
          String qtType = (String) reqMap.get("qtType") == null?"qtReport":(String) reqMap.get("qtType");
          mop.addAttribute("qtType", qtType);
          return reback;
      }





    /**
     * @Description: 其他收入新增报备
     */
    @RequestMapping(value = "toQtReport/{id}", method = RequestMethod.GET)
    public String toReport1(@PathVariable("id") Integer id, HttpServletRequest request, ModelMap mop){
        ResultData<?> resultData = null;
        ResultData<?> cityList = null;
        try
        {
            resultData = estateService.getById(id);

            cityList = pmlsQtReportService.getBasCitySettingList();

        }
        catch (Exception e)
        {
            logger.error("pmlsQtReport", "PmlsQtReportQtReportController", "toQtReport", "", null, "", "跳转其他收入报备页面", e);
        }
        mop.addAttribute("estateInfo", resultData.getReturnData());
        mop.addAttribute("cityList", cityList.getReturnData());
        String countDateEndStr = DateUtil.fmtDate(new Date(),"yyyy-MM-dd");
        mop.put("countDateEndStr",countDateEndStr);
        return "otherReport/qtReportAdd";
    }


    /**
     * 查询归属城市下的归属中心
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getBasCitySettingList", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getBasCitySettingList(ModelMap mop)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        //返回map
        ResultData<?> resultData = new ResultData<StudentDto>();

        try{
            resultData = pmlsQtReportService.getBasCitySettingList();
        }
        catch (Exception e){
            logger.error("pmlsQtReport", "PmlsQtReportQtReportController", "getBasCitySettingList", "", null, "", "查询归属城市下的归属城市失败！", e);
        }

        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        return getSearchJSONView(rspMap);
    }


    /**
     * 查询归属城市下的归属中心
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getAchivAchievementLevelSettingList/{cityNo}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getAchivAchievementLevelSettingList(ModelMap mop,@PathVariable("cityNo") String cityNo)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        //返回map
        ResultData<?> resultData = new ResultData<StudentDto>();

        try{
            resultData = pmlsQtReportService.getAchivAchievementLevelSettingList(cityNo);
        }
        catch (Exception e){
            logger.error("pmlsQtReport", "PmlsQtReportQtReportController", "getAchivAchievementLevelSettingList", "", null, "", "查询归属城市下的归属中心失败！", e);
        }

        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        return getSearchJSONView(rspMap);
    }


    /**
     * @Title: createReport
     * @Description: 新增报备
     */
    @RequestMapping(value = "createReport", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> createReport(HttpServletRequest request, ModelMap modelMap,@RequestBody LnkQtReport lnkQtReport){
        Map<String, Object> reqMap = bindParamToMap(request);
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try{
            UserInfo userInfo = (UserInfo) WebUtil.getValueFromSession(request, Constant.SESSION_KEY_USERINFO);
            // 新增报备处理
            lnkQtReport.setCrtUserId(userInfo.getUserId());
            ResultData<?> resultData = this.pmlsQtReportService.createReport(lnkQtReport);

            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        }
        catch (Exception e)
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "报备失败！");
            logger.error("pmlsQtReport", "PmlsQtReportQtReportController", "createReport", "", null, "", "创建报备失败！", e);
        }
        return getOperateJSONView(rspMap);
    }
    
    /**
     * 查看
     * @param estateId
     * @param id
     * @param mop
     * @return
     */
    @RequestMapping(value = "qtReportDetail/{estateId}/{id}/{qtType}", method = RequestMethod.GET)
	public ModelAndView qtReportDetail(@PathVariable("estateId") String estateId, @PathVariable("id") Integer id, @PathVariable("qtType") String qtType,ModelMap mop) {
		// 返回map
		ResultData<?> resultData = null;
		Map<String, Object> reqMap = new HashMap<>();
		reqMap.put("id", id);
		try {
			resultData = pmlsQtReportService.getQtReportById(reqMap);
		} catch (Exception e) {
			logger.error("qtReport", "QtReportController", "getQtReportById", "", null, "", "查看 ", e);
		}
		mop.addAttribute("qtReportInfo", JSONObject.toJSON(resultData.getReturnData()));
		mop.addAttribute("estateId", estateId);
		mop.addAttribute("qtType", qtType);
		return new ModelAndView("otherReport/qtReportDetailPage");
	}



    /**
     * @Title: validQtReport
     * @Description:
     */
    @RequestMapping(value = "validQtReport/{reportId}", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> validQtReport(HttpServletRequest request, ModelMap modelMap
            ,@PathVariable("reportId") Integer reportId){
        Map<String, Object> reqMap = bindParamToMap(request);
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try{
            UserInfo userInfo = (UserInfo) WebUtil.getValueFromSession(request, Constant.SESSION_KEY_USERINFO);

            ResultData<?> resultData = this.pmlsQtReportService.validQtReport(reportId,userInfo.getUserId());

            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        }
        catch (Exception e)
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            logger.error("qtReport", "QtReportController", "validQtReport", "", null, "", "报备无效操作失败！", e);
        }
        return getOperateJSONView(rspMap);
    }
    
    /**
     * desc:业务节点-查看
     * 2019年10月17日
     * author:zhenggang.Huang
     */
    @RequestMapping("toOperDetail")
    public ModelAndView toOperDetail(HttpServletRequest request,ModelMap mop) {
        Map<String, Object> reqMap = bindParamToMap(request);
        
        try {
            ResultData<?> resultData = pmlsQtReportService.getQtReportById(reqMap);
            ResultData<?> resultData1 = pmlsQtReportService.getOperDetail(reqMap);
            mop.put("info", JSONObject.toJSON(resultData.getReturnData()));
            mop.put("yjReport", JSONObject.toJSON(resultData1.getReturnData()));
            mop.put("reqMap", reqMap);
        }catch (Exception e) {
            logger.error("pmlsQtReport", "PmlsQtReportController", "toOperDetail", "", null, "", "业务节点详情查询 ", e);
        }
        
        return new ModelAndView("otherReport/qtReportOperDetail");
    }
}
