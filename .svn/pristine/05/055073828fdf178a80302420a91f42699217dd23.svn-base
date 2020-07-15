package cn.com.eju.pmls.remittanceTrack.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.FileUtils;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.pmls.estate.service.PmlsEstateService;
import cn.com.eju.pmls.poi.ExcelForRemittanceTrack;
import cn.com.eju.pmls.remittanceTrack.service.RemittanceTrackService;

@RestController
@RequestMapping("remittanceTrack")
public class RemittanceTrackController extends BaseController {
	
    @Resource
    private RemittanceTrackService remittanceTrackService;
    
    @Resource(name = "pmlsEstateService")
    private PmlsEstateService pmlsEstateService;

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    /**
     * desc:回款跟踪-初始化
     * 2020年4月7日
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView remittanceTrackList(ModelMap mop) {
    	List<?> rebacklist = null;
    	ResultData reback=null;
        try {
            UserInfo userInfo = UserInfoHolder.get();
            Map<String, Object> reqMap = new HashMap<>();
            String cityNo = UserInfoHolder.get().getCityNo();
            reqMap.put("cityNo", cityNo);
            mop.put("cityNo", cityNo);
            ResultData<?> resultData = pmlsEstateService.getProjectDepartment(reqMap);
            rebacklist = (List<?>) resultData.getReturnData();
            reqMap.put("relateSystem", 29301);//先固定写死
    		reback = remittanceTrackService.querySwitchWeek(reqMap);
    		mop.put("performSwitch", reback.getReturnData());
    		logger.info("回款跟踪数据，初始化获取城市["+UserInfoHolder.get().getCityName()+"]关账版本为:"+reback);
        } catch (Exception e) {
            logger.error("remittanceTrack", "RemittanceTrackController", "", "", null, "", "初始化-归属项目部", e);
        }
        mop.put("rebacklist", rebacklist);
        return new ModelAndView("remittanceTrack/remittanceTrackList");
    }
    
    /**
     * desc:回款跟踪数据初始化-年月-周
     * 2020年04月07日
     */
    @RequestMapping(value = "/getWeeks", method = RequestMethod.GET)
    @ResponseBody
    public ResultData<?> getWeeks(HttpServletRequest request) {
    	//返回map
    	Map<String, Object> queryParam = bindParamToMap(request);
    	//返回map
    	ResultData resultData = new ResultData<>();
    	
    	try {
    		resultData = remittanceTrackService.getWeeks(queryParam);
    	} catch (Exception e) {
    		logger.error("remittanceTrack", "RemittanceTrackController", "getWeeks", "", null, "", "回款跟踪数据初始化-年月-周", e);
    	}
    	
    	return resultData;
    }
    
    
    /**
     * desc:列表
     * 2020年4月7日
     */
    @RequestMapping(value = "/queryRemitanceTrackList", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> queryRemitanceTrackList(HttpServletRequest request) {

    	Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        String cityNo = UserInfoHolder.get().getCityNo();
        reqMap.put("cityNo", cityNo);
        PageInfo pageInfo = getPageInfo(request);
        logger.info("回款跟踪数据，查询列表，入参:"+reqMap);
        try {
            //获取页面显示数据
            resultData = remittanceTrackService.queryRemitanceTrackList(reqMap, pageInfo);
            logger.info("回款跟踪数据，查询列表成功，返回数据:"+resultData);
        } catch (Exception e) {
            logger.error("remittanceTrack",
                    "RemittanceTrackController",
                    "queryRemitanceTrackList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询回款跟踪数据列表异常",
                    e);
        }
        return resultData;
    }
    
    /**
     * 
     * desc:获取表头
     * 2020年4月24日
     */
    @ResponseBody
    @RequestMapping(value = "getTitle", method = RequestMethod.POST)
    public ResultData<?> getTitle(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        logger.info("回款跟踪数据，获取列表表头，入参:"+reqMap);
        try {
            resultData = remittanceTrackService.getTitle(reqMap);
            logger.info("回款跟踪数据，获取列表表头:"+resultData);
        } catch (Exception e) {
        	 logger.error("remittanceTrack",
                     "RemittanceTrackController",
                     "queryRemitanceTrackList",
                     "input param: reqMap=" + reqMap.toString(),
                     UserInfoHolder.getUserId(),
                     WebUtil.getIPAddress(request),
                     "获取回款跟踪数据表头异常",
                     e);
        }
        return resultData;
    }
    
    /**
     * desc:导出check
     * 2020年4月8日
     */
    @RequestMapping(value = "exportCheck", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> exportCheck(HttpServletRequest request, HttpServletResponse response) {
        String cityNo = UserInfoHolder.get().getCityNo();
        Map<String, Object> rspMap = new HashMap<String, Object>();
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> reback=null;
        try {
            reback = remittanceTrackService.queryRemitanceTrackList(reqMap, null);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();
            if (contentlist == null || contentlist.isEmpty()) {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "没有数据，不能导出Excel！");
                return getOperateJSONView(rspMap);
            }
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导出失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        rspMap.put(Constant.RETURN_MSG_KEY, reback.getReturnMsg());
        return getOperateJSONView(rspMap);
    }
    
    /**
     * desc:导出
     * 2020年4月8日
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response) {
    	logger.info("回款跟踪数据-导出start。。。");
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> reback = new ResultData<>();
        ResultData<?> resultData = null;
        ResultData<?> getWeekNo = null;
        String cityNo = UserInfoHolder.get().getCityNo();
        try {
            reback = remittanceTrackService.queryRemitanceTrackList(reqMap, null);
            logger.info("回款跟踪数据-导出数据:"+reback+"入参:"+reqMap);

            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            String ctxPath = request.getSession().getServletContext().getRealPath("/");
            String url = ctxPath + "data" + File.separator + "remittanceTrack";

            File dataFile = new File(ctxPath + "data");
            if (!dataFile.isDirectory()) {
                dataFile.mkdir();
            }

            File direct = new File(url);
            if (!direct.isDirectory()) {
                direct.mkdir();
            }

            long time = System.currentTimeMillis();
            //删掉历史文件
            FileUtils.deleteFile(direct, time);

            File directory = new File(url + File.separator + time);
            if (!directory.isDirectory()) {
                directory.mkdir();
            }

            try {
            	
                String trackType = reqMap.get("trackType").toString();
                String templateTypeStr = "回款跟踪数据";
                if("0".equals(trackType)) {
                	templateTypeStr = "预计回款跟踪数据";
                }else if("1".equals(trackType)){
                	templateTypeStr = "实际回款跟踪数据";
                }
                String pathName = url + File.separator + time + File.separator + templateTypeStr + ".xlsx";
                logger.info("回款跟踪数据-导出文件名:"+pathName);
                ExcelForRemittanceTrack instance = new ExcelForRemittanceTrack(trackType);
                Map<String, Object> map = new LinkedHashMap<>();

                resultData = remittanceTrackService.getTitle(reqMap);
                LinkedHashMap<String, Object> remittanceTracklist = (LinkedHashMap<String, Object>) resultData.getReturnData();
                logger.info("回款跟踪数据-导出文件表头:"+remittanceTracklist);
                
                map.putAll(remittanceTracklist);
                map.put("trackType", trackType);
                map.put("yearMonthVersion", reqMap.get("yearMonthVersion"));
                map.put("weekVersion", reqMap.get("weekVersion"));
                map.put("sortNo", reqMap.get("sortNo"));
                map.put("year", reqMap.get("year"));
                map.put("month", reqMap.get("month"));
                map.put("templateType", templateTypeStr);
                Map<String, Object> weekMap = new HashMap<>();
                weekMap.put("year", reqMap.get("year"));
                weekMap.put("month", reqMap.get("month"));
                //获取表头周数 start
                logger.info("回款跟踪数据-导出文件表头周数:start...");
                getWeekNo = remittanceTrackService.getNums(weekMap);
                logger.info("回款跟踪数据-导出文件表头周数:end..."+getWeekNo.getReturnData());
                //end
                map.put("weekNo", getWeekNo.getReturnData());
                map.put("cityNo", cityNo);
                instance.downloadSheet(map, contentlist, new File(pathName));

                logger.info("回款跟踪数据-导出文件下载开始。。。");
                String fileName = templateTypeStr + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx";
                super.download(request, response, pathName, fileName);
                logger.info("回款跟踪数据-导出文件结束end。。。");
            } catch (Exception e) {
            	logger.error("回款跟踪数据-下载Excel失败");
                response.setCharacterEncoding("GBK");
                response.getWriter().write("下载Excel失败" + e.getMessage());
                response.getWriter().close();
                logger.error("下载Excel失败", e);
                e.printStackTrace();
            }

        } catch (Exception e) {

        	logger.error("回款跟踪数据-导出异常");
            logger.error("remittanceTrack",
                    "RemittanceTrackController",
                    "export",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "导出异常",
                    e);
        }
    }
    
    /**
     * desc:导入界面
     * 2020年4月14日
     */
    @RequestMapping(value = "toView", method = RequestMethod.GET)
    public ModelAndView toView() {
        ModelAndView mv = new ModelAndView("remittanceTrack/remittanceTrackImport");
        return mv;
    }
    
    /**
     * 导入
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "imput", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> imput(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> rspMap =remittanceTrackService.importRemittanceTrack(request);
        return getSearchJSONView(rspMap);
    }
}
