package cn.com.eju.deal.otherReport.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;

import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.linkage.service.LinkageCityService;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.cashbill.service.CashBillService;
import cn.com.eju.deal.common.service.CommonService;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.frameContract.service.FrameContractService;
import cn.com.eju.deal.houseLinkage.estate.service.EstateService;
import cn.com.eju.deal.houseLinkage.report.service.ReportService;
import cn.com.eju.deal.oa.service.OaOperatorService;
import cn.com.eju.deal.otherReport.service.QtProjectService;
import cn.com.eju.deal.scene.estate.service.BatchSaleService;

/**
 * desc:其他收入-项目
 * @author :zhenggang.Huang
 * @date   :2019年10月11日
 */
@Controller
@RequestMapping(value = "qtProject")
public class QtProjectController extends BaseReportController
{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "qtProjectService")
    private QtProjectService qtProjectService;
    
    @Resource(name = "linkageCityService")
    private LinkageCityService linkageCityService;
    
    @Resource(name = "commonService")
    private CommonService commonService;

    @Resource(name = "reportService")
    private ReportService reportService;

    @Resource(name="cashBillService")
    private CashBillService cashBillService;

    @Resource(name = "oaOperatorService")
    private OaOperatorService oaOperatorService;

    @Resource(name = "batchSaleService")
    private BatchSaleService batchSaleService;
    @Resource
    private FrameContractService frameContractService;
    
    @Resource(name = "estateService")
    private EstateService estateService;

    /** 
    * 初始化
    * @param request
    * @param mop
    * @return
    */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap mop, HttpServletResponse response)
    {
        String cityNo = UserInfoHolder.get().getCityNo();
        //区域列表
        ResultData<?> resultDistrictList = new ResultData<>();
        try
        {
            resultDistrictList = this.linkageCityService.getDistrictList(cityNo);
        }
        catch (Exception e)
        {
            logger.error("qtProject", "QtProjectController", "list", "", null, "", "", e);
        }
        
      //得到归属项目部 add by zhenggang.Huang 2019/07/12 begin
        List<?> rebacklist = null;
        try{
        	Map<String,Object> reqMap = new HashMap<>();
        	reqMap.put("cityNo", cityNo);
        	ResultData<?> resultData = estateService.getProjectDepartment(reqMap);
        	rebacklist = (List<?>) resultData.getReturnData();
        }catch(Exception e)
        {
        	 logger.error("houseLinkage.report", "ReportController", "list", "", null, "", "创建--初始化-归属项目部", e);
        }
        mop.put("rebacklist", rebacklist);
        //end
        mop.put("districtList", resultDistrictList.getReturnData());

        //读取搜索条件 add by wangkanlin 2017/08/23
        Map<String, Object> map = bindParamToMap(request);
        if(map.containsKey("searchParam") && "1".equals(map.get("searchParam"))){
            Map<String,Object> searchParamMap = getRememberSearch(request, ComConstants.QTPROJECT_LIST_SEARCH);
            mop.put("searchParamMap", JSON.toJSON(searchParamMap));
        }else{
            clearSearch(request, response, ComConstants.QTPROJECT_LIST_SEARCH);
        }
        mop.put("list_search_page", ComConstants.QTPROJECT_LIST_SEARCH);
        
        return "otherReport/qtProjectList";
    }
    
    /** 
    * 查询--list
    * @param request
    * @param mop
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "q", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap mop, HttpServletResponse response)
    {
        
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        if(reqMap.containsKey("estateNm") && null!=reqMap.get("estateNm")){
        	String estateNm = reqMap.get("estateNm").toString();
        	estateNm = estateNm.trim();
        	reqMap.put("estateNm", estateNm);
        }
        
        PageInfo pageInfo = getPageInfo(request);
        //需要数据权限
        reqMap.put(Constant.DATA_AUTH_KEY, true);
        ResultData<?> reback = new ResultData<>();
        try
        {
        	//保存搜索数据 add by wangkanlin 2017-08-23
            if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.QTPROJECT_LIST_SEARCH, reqMap);
            }
            //获取页面显示数据
            reback = qtProjectService.index(reqMap, pageInfo);
        }
        catch (Exception e)
        {
            logger.error("qtProject", "QtProjectController", "q", "", null, "", "", e);
        }
        //页面数据
        List<?> contentlist = (List<?>)reback.getReturnData();
        
        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        
        return "otherReport/qtProjectListCtx";
    }
    
    /**
     * desc:项目-查看
     * 2019年10月12日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "qSceneRecognition/{estateId}", method = RequestMethod.GET)
    public String sceneRecognition(HttpServletRequest request,HttpServletResponse response, ModelMap mop, @PathVariable("estateId") String estateId)
    {
        String cityNo = UserInfoHolder.get().getCityNo();
        mop.addAttribute("cityNo", cityNo);
        mop.addAttribute("estateId", estateId);
        //返回map
        ResultData<?> resultData = new ResultData<ContractDto>();
        ResultData<?> resultDataSwitch = new ResultData<HashMap<String,Object>>();
        try
        {
            resultData = qtProjectService.getByEstateId(estateId);
            //获取关账信息
            resultDataSwitch = commonService.getSwitchInfo(cityNo);
            Map<String, Object> map = (Map<String, Object>) resultDataSwitch.getReturnData();
            mop.addAttribute("yearMonth", map.get("yearMonth"));
            mop.addAttribute("isShowCurrDate", map.get("isShowCurrDate"));
            
        }
        catch (Exception e)
        {
            logger.error("sceneestate", "SceneEstateController", "qSceneRecognition/{estateId}", "", null, "", "", e);
        }
        //存放到mop中
        mop.addAttribute("estate", resultData.getReturnData());
        //end
        Map<String, Object> map = bindParamToMap(request);
        if(map.containsKey("searchParam") && "1".equals(map.get("searchParam"))){
            Map<String,Object> searchParamMap = getRememberSearch(request, ComConstants.QTPROJECT_LIST_SEARCH);
            mop.put("searchParamMap", JSON.toJSON(searchParamMap));
        }else{
            clearSearch(request, response, ComConstants.QTPROJECT_LIST_SEARCH);
        }
        mop.put("list_search_page", ComConstants.QTPROJECT_LIST_SEARCH);
        mop.put("qtType", "qtProject");
        return "otherReport/qtProjectDetailList";
    }
    
}
