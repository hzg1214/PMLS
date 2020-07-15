package cn.com.eju.deal.Report.controller;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.model.ReportQueueAjax;
import cn.com.eju.deal.Report.service.ExpendReportService;
import cn.com.eju.deal.Report.service.ExportReportBaseService;
import cn.com.eju.deal.Report.service.ReportQueueService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.core.util.WebUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

/**
 * Controller层
 *
 * @author guopengfei
 * @date 2017年5月12日 下午9:25:30
 */
@Controller
@RequestMapping(value = "expendReport")
public class ExpendReportController extends BaseReportController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private ExpendReportService expendReportService;

    @Resource
    private ReportQueueService reportQueueService;

    @Resource
    private ExportReportBaseService exportReportBaseService;

    @Autowired
    @Qualifier("threadPoolTaskExecutorExpend")
    private ThreadPoolTaskExecutor threadPoolTaskExecutorExpend;


    /**
     * 初始化
     *
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap mop) {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("reportKey", "exReport_filepath");
        reqMap.put("userId", UserInfoHolder.get().getUserId());
        ResultData<?> reback = new ResultData<>();
        try {
            reback = reportQueueService.selectReportListByUser(reqMap);
        } catch (Exception e) {
            logger.error("expendReport", "ExpendReportController", "list", "", null, "", "", e);
            reback.setFail();
        }
        List<?> contentlist = (List<?>) reback.getReturnData();
        int userReportSize = 0;
        if (!CollectionUtils.isEmpty(contentlist)) {
            userReportSize = contentlist.size();
        }
        mop.put("userReportSize", userReportSize);
        return "Report/expandDetailReport";
    }


    /**
     * 查询--list
     *
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "query", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap mop) {
        // 获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        reqMap.put("userId", UserInfoHolder.getUserId());

        //获取页面显示数据
        List<?> reportlist = new ArrayList<>();

        changeParam(reqMap);

        try {

            ResultData<?> reback = expendReportService.queryExpandDetailList(reqMap, pageInfo);
            //页面数据
            reportlist = (List<?>) reback.getReturnData();

        } catch (Exception e) {
            logger.error("index", "ExpendReportController", "index", "", UserInfoHolder.getUserId(), "", "门店拓展明细查询失败", e);
        }

        //存放到mop中
        mop.addAttribute("reportlist", reportlist);

        return "Report/expandDetailReportListCxt";
    }


    //Add By QJP 2017/06/16 导出拓展明细报表 start
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());

        changeParam(reqMap);

        try {
            ResultData<?> reback = expendReportService.exportExpandDetailList(reqMap);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            downLoadExcel(request, response, contentlist, reqMap, ReportConstant.EXPANDDETAIL_CODE, ReportConstant.EXPANDDETAIL_NAME);

        } catch (Exception e) {
            logger.error("expandDetail", "ExpandReportController", "export", "input param: reqMap=" + reqMap.toString(), -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }
    }

    //Add By QJP 2017/06/16 导出拓展明细报表 start
    @RequestMapping(value = "exportAjax", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView exportAjax(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        UserInfo userInfo = UserInfoHolder.get();
        if (userInfo == null) {
            map.put("message", "获取用户失败");
            return getOperateJSONView(map);
        }
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());

        changeParam(reqMap);
        List<LinkedHashMap<String, Object>> contentlist = new ArrayList<LinkedHashMap<String, Object>>();
        int queueSize = 0;
        try {
            //加
            reportQueueService.updateReportQueueTotal("exReport_filepath", 1);
            ReportQueueAjax reportDb = new ReportQueueAjax();
            reportDb.setDateCreate(new Date());
            reportDb.setDelFlag("N");
            //reportDb.setFilePath(pathName);   生成后更新
            reportDb.setReportKey("exReport_filepath");
            reportDb.setUserId(UserInfoHolder.getUserId());
            reportDb.setUserIdCreate(UserInfoHolder.getUserId());
            int id = reportQueueService.addReportQueueAjax(reportDb);
            /*ResultData<?> reback = expendReportService.exportExpandDetailList(reqMap);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            downLoadExcel(request, response, contentlist, reqMap, ReportConstant.EXPANDDETAIL_CODE, ReportConstant.EXPANDDETAIL_NAME);*/
            String ctxPath = request.getSession().getServletContext().getRealPath("/");
            reqMap.put("ctxPath", ctxPath);
            exportReportBaseService.downLoadExcelAjax(reqMap, ReportConstant.EXPANDDETAIL_CODE, ReportConstant.EXPANDDETAIL_NAME, id);

            ResultData resultData = reportQueueService.selectReportQueueTotalTopOne("exReport_filepath");
            LinkedHashMap<String, Object> linkedHashMap = (LinkedHashMap<String, Object>) resultData.getReturnData();
            if (resultData.getReturnData() != null) {
                queueSize = Integer.valueOf(linkedHashMap.get("positiveSize") + "") - Integer.valueOf(linkedHashMap.get("minusSize") + "") - threadPoolTaskExecutorExpend.getCorePoolSize();
            }

            if (queueSize <= 0) {
                queueSize = 0;
            }
        } catch (Exception e) {
            logger.error("expandDetail", "ExpandReportController", "export", "input param: reqMap=" + reqMap.toString(), -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }
        map.put("message", "您前面还有" + queueSize + "人排队处理；正在排队处理请稍后(排队过程中可离开当前页面;下次进入拓展明细表页面会自动下载)");
        return getOperateJSONView(map);
    }

    @RequestMapping(value = "/fileIntervalByName/{reportName}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView fileIntervalByName(@PathVariable("reportName") String reportName,
                                         ModelMap model) {
        ReturnView jv = new ReturnView();
        //  	  String filePathKey = ConstantsCode.EXCHANGEDETAILREPORT_FILEPATH + String.valueOf(UserInfoHolder.get().getUserId());
        //	  String filePath = "";
        //	  if(redisTemplate.opsForValue().get(filePathKey)!=null){
        //		  filePath = (String) redisTemplate.opsForValue().get(filePathKey);
        //		  redisTemplate.delete(filePathKey);
        //	  }

        Integer fileId = null;
        try {
        	//TODO
//            ResultData<?> resultData = reportQueueService.selectReportQueueAjaxTopOne(reportName, 156);
            ResultData<?> resultData = reportQueueService.selectReportQueueAjaxTopOne(reportName, UserInfoHolder.get().getUserId());
            LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) resultData.getReturnData();
            if (resultData.getReturnData() != null && StringUtil.isNotEmpty(map.get("FilePath") + "")) {
                fileId = Integer.valueOf(map.get("id") + "");
                reportQueueService.delReportQueueAjax(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jv.put("fileId", fileId);
        return jv;
    }

    @RequestMapping(value = "/queryReportSize/{reportName}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView queryReportSize(@PathVariable("reportName") String reportName,
                                      ModelMap model) {
        ReturnView jv = new ReturnView();
        Map<String, Object> reqMap = new HashMap<>();
//        reqMap.put("reportKey", "exReport_filepath");
        reqMap.put("reportKey", reportName);
        reqMap.put("userId", UserInfoHolder.get().getUserId());
        ResultData<?> reback = new ResultData<>();
        try {
            reback = reportQueueService.selectReportListByUser(reqMap);
        } catch (Exception e) {
            logger.error("expendReport", "ExpendReportController", "list", "", null, "", "", e);
            reback.setFail();
        }
        List<?> contentlist = (List<?>) reback.getReturnData();
        int userReportSize = 0;
        if (!CollectionUtils.isEmpty(contentlist)) {
            userReportSize = contentlist.size();
        }
        jv.put("userReportSize", userReportSize);
        return jv;
    }

    /**
     * 导出交易明细报表
     */
    @RequestMapping(value = "/downLoadExcel2", method = RequestMethod.GET)
    public void downLoadExcel2(HttpServletRequest request, HttpServletResponse response) {
        //        // 获取请求参数
        Map<String, Object> map = bindParamToMap(request);
        String fileId = (String) map.get("fileId");
        if (StringUtil.isEmpty(fileId)) {
            fileId = "0";
        }

        ResultData<?> resultData = null;
        try {
            resultData = reportQueueService.selectByPrimaryKey(Integer.parseInt(fileId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String filePath = "";
        String fileName = "";
        if (resultData != null) {
            LinkedHashMap<String, Object> returnMap = (LinkedHashMap<String, Object>) resultData.getReturnData();
            filePath = returnMap.get("filePath") + "";
            Integer startIndex = filePath.lastIndexOf("\\");
            if(startIndex<=0){
                startIndex =filePath.lastIndexOf("/");
            }
            fileName = filePath.substring(startIndex + 1);

            /*if ("exReport_filepath".equals(returnMap.get("reportKey" + ""))) {
                fileName = "拓展明细.xlsx";
            } else if ("linkDetail_filepath".equals(returnMap.get("reportKey" + ""))) {
                fileName = "联动明细.xlsx";
            }*/
        }

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            if (StringUtil.isNotEmpty(filePath)) {
                response.reset();
                response.setContentType("textml;charset=UTF-8");
                request.setCharacterEncoding("UTF-8");

                long fileLength = new File(filePath).length();
                response.setContentType("application/octet-stream");
                response.setHeader("Content-disposition", "attachment; filename="
                        + new String(fileName.getBytes("GBK"), "ISO8859-1"));
                response.setHeader("Content-Length", String.valueOf(fileLength));

                bis = new BufferedInputStream(new FileInputStream(filePath));
                bos = new BufferedOutputStream(response.getOutputStream());
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } else {
                response.reset();
                response.setContentType("textml;charset=UTF-8");
                request.setCharacterEncoding("UTF-8");

                response.setContentType("application/octet-stream");
                response.setHeader("Content-disposition", "attachment; filename="
                        + new String(fileName.getBytes("GBK"), "ISO8859-1"));
                response.setHeader("Content-Length", "0");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //**********************加载城市************************************
    @RequestMapping(value = "getCityByIsService", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView getCityByIsService(HttpServletRequest request, ModelMap mop) {
        // 获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        Map<?, ?> reback = new HashMap<String, Object>();

        //设置用户id
        reqMap.put("userId", UserInfoHolder.getUserId());
        // 获取页面显示数据
        try {
            reback = expendReportService.getUserCenterAuthCityName(reqMap);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        for (Entry<?, ?> m : reback.entrySet()) {
            map.put((String) m.getKey(), m.getValue());
        }
        map.put("oldLevelTypeNames", StringUtils.join(GroupConfig.getOldLevelTypeNames(), ","));
        map.put("levelTypeNames", StringUtils.join(GroupConfig.getLevelTypeNames(), ","));
        return getOperateJSONView(map);
    }

    //选择城市查询事业部
    @RequestMapping(value = "getAllGroupByTypeIdAndCityId", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView getAllGroupByTypeIdAndCityId(HttpServletRequest request, ModelMap mop) {

        // 获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        //设置用户id
        reqMap.put("userId", UserInfoHolder.getUserId());
        Map<?, ?> reback = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();

        // 获取页面显示数据
        try {
            reback = expendReportService.getAreaGroupName(reqMap);

            if (null != reback.get("list")) {
                List<LinkedHashMap<String, Object>> list = (List<LinkedHashMap<String, Object>>) reback.get("list");

                for (Entry<?, ?> m : reback.entrySet()) {
                    map.put((String) m.getKey(), m.getValue());
                }

                map.put("list", list);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return getOperateJSONView(map);
    }


    //选择事业部查询部门
    @RequestMapping(value = "getAllGroupByOrgIdAndTypeId", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView getAllGroupByOrgIdAndTypeId(HttpServletRequest request, ModelMap mop) {
        Map<?, ?> reback = new HashMap<String, Object>();

        // 获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());
        Map<String, Object> map = new HashMap<String, Object>();

        // 获取页面显示数据
        try {
            reback = expendReportService.getCenterGroupName(reqMap);

            if (null != reback.get("list")) {
                List<LinkedHashMap<String, Object>> list = (List<LinkedHashMap<String, Object>>) reback.get("list");

                for (Entry<?, ?> m : reback.entrySet()) {
                    map.put((String) m.getKey(), m.getValue());
                }

                map.put("list", list);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return getOperateJSONView(reback);

    }



    @RequestMapping(value = "/fileIntervalByNameCSV/{reportName}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView fileIntervalByNameCSV(@PathVariable("reportName") String reportName,
                                         ModelMap model) {
        ReturnView jv = new ReturnView();
        String filePath = null;
        int userReportSize = 0;
        try {
            //file
            ResultData<?> resultData = reportQueueService.selectReportQueueAjaxTopOne(reportName, UserInfoHolder.get().getUserId());
            LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) resultData.getReturnData();
            if (resultData.getReturnData() != null && StringUtil.isNotEmpty((String) map.get("filePath"))) {
                filePath = (String) map.get("filePath");
                logger.info("联动项目周回款跟踪：导出轮询文件##userId="+ UserInfoHolder.get().getUserId()
                                +"#filePath="+filePath);
                reportQueueService.delReportQueueAjax(map);
            }


            Map<String, Object> reqMap = new HashMap<>();
            reqMap.put("reportKey", reportName);
            reqMap.put("userId", UserInfoHolder.get().getUserId());
            ResultData<?> reback = new ResultData<>();
            reback = reportQueueService.selectReportListByUser(reqMap);

            List<?> contentlist = (List<?>) reback.getReturnData();

            if (!CollectionUtils.isEmpty(contentlist)) {
                userReportSize = contentlist.size();
            }
        } catch (Exception e) {
            logger.error("联动项目周回款跟踪：导出轮询文件error##userId="+ UserInfoHolder.get().getUserId()
                    +"#filePath="+filePath);
            e.printStackTrace();
            logger.error("expendReport", "ExpendReportController", "fileIntervalByNameCSV", "", null, "", "", e);
        }
        jv.put("userReportSize", userReportSize);
        jv.put("filePath", filePath);
        return jv;
    }
}
