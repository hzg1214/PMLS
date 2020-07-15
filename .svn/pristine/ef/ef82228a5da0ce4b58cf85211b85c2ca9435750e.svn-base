package cn.com.eju.pmls.otherReport.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.linkage.service.LinkageCityService;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.service.CommonService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.houseLinkage.estate.service.EstateService;
import cn.com.eju.pmls.otherReport.service.PmlsQtProjectService;


/**
 * desc:其他收入-项目
 * @author :zhenggang.Huang
 * @date   :2019年10月11日
 */
@Controller
@RequestMapping(value = "pmlsQtProject")
public class PmlsQtProjectController extends BaseReportController
{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "pmlsQtProjectService")
    private PmlsQtProjectService pmlsQtProjectService;
    
    @Resource(name = "linkageCityService")
    private LinkageCityService linkageCityService;
    
    @Resource(name = "commonService")
    private CommonService commonService;
    
    @Resource(name = "estateService")
    private EstateService estateService;

    /** 
    * 项目-初始化
    * @param request
    * @param mop
    * @return
    */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(){
        return new ModelAndView("otherReport/qtProjectList");
    }
    
    /** 
    * 项目--list
    * @param request
    * @param mop
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "qtProjectList", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> qtProjectList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);

        try {
            String cityNo = UserInfoHolder.get().getCityNo();
            reqMap.put("cityNo", cityNo);

            //获取页面显示数据
            logger.info("查询其他收-项目列表入参:", reqMap.toString());
            resultData = pmlsQtProjectService.qtProjectList(reqMap, pageInfo);
            logger.info("查询其他收入-项目列表结果:", resultData.toString());

        } catch (Exception e) {
            logger.error("查询其他收入-项目列表内容异常;input param: reqMap=" + reqMap.toString(), e);

            logger.error("qtreport",
                    "PmlsQtReportController",
                    "qtProjectList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询其他收入-项目列表内容异常",
                    e);
        }

        return resultData;
    }

    
    /**
     * desc:项目-查看
     * 2019年10月12日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "qSceneRecognition", method = RequestMethod.GET)
    public String sceneRecognition(HttpServletRequest request, ModelMap mop)
    {
    	String estateId=request.getParameter("estateId");
        String cityNo = UserInfoHolder.get().getCityNo();
        mop.addAttribute("cityNo", cityNo);
        mop.addAttribute("estateId", estateId);
        //返回map
        ResultData<?> resultData = new ResultData<ContractDto>();
        try
        {
            resultData = pmlsQtProjectService.getByEstateId(estateId);
            //获取关账信息
        }
        catch (Exception e)
        {
            logger.error("sceneestate", "SceneEstateController", "qSceneRecognition/{estateId}", "", null, "", "", e);
        }
        //存放到mop中
        mop.addAttribute("estate", resultData.getReturnData());
        //end
        mop.put("qtType", "qtProject");
        return "otherReport/qtReportList";
    }
    
}
