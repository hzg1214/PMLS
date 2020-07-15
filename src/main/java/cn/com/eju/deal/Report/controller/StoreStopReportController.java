package cn.com.eju.deal.Report.controller;

import cn.com.eju.deal.Report.service.StoreStopReportService;
import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.FileUtils;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.poi.ExcelForStoreStop;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 门店终止
 * Controller层
 *
 * @author hzy
 * @date 2017年9月26日 下午17:11:30
 */
@Controller
@RequestMapping(value = "storeStopReport")
public class StoreStopReportController extends BaseController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private StoreStopReportService storeStopReportService;


    /**
     * 初始化
     *
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap mop) {
        return "Report/storeStopReport/storeStopDetailReportList";
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
        try {
            ResultData<?> reback = storeStopReportService.queryStoreStopDetailList(reqMap, pageInfo);

            //页面数据
            reportlist = (List<?>) reback.getReturnData();

        } catch (Exception e) {
            logger.error("index", "ExpendReportController", "index", "", UserInfoHolder.getUserId(), "", "门店拓展明细查询失败", e);
        }

        //存放到mop中
        mop.addAttribute("reportlist", reportlist);

        return "Report/storeStopReport/storeStopDetailReportListCxt";
    }

    //Add By QJP 2017/06/16 导出拓展明细报表 start
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());

        try {
            ResultData<?> reback = storeStopReportService.exportStoreStopDetailList(reqMap);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            String ctxPath = request.getSession().getServletContext().getRealPath("/");
            String url = ctxPath + "data" + File.separator + "expandDetail";

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

            ExcelForStoreStop instance = new ExcelForStoreStop();
            String pathName = url + File.separator + time + File.separator + "拓展明细.xlsx";

            try {
                instance.downloadSheet(contentlist, new File(pathName));
                super.setExportSuccess((String) reqMap.get("cookieName"), response);
                super.download(request, response, pathName, "拓展明细.xlsx");
            } catch (Exception e) {
                response.setCharacterEncoding("UTF-8");
                String s = "下载Excel失败" + e.getMessage();
                ServletOutputStream out = response.getOutputStream();
                out.write(s.getBytes());
                out.flush();
                out.close();

                logger.error("下载Excel失败", e);

            }

        } catch (Exception e) {
            logger.error("expandDetail", "ExpandReportController", "export", "input param: reqMap=" + reqMap.toString(), -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }
    }
}
