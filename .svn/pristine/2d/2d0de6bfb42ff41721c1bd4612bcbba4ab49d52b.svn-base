package cn.com.eju.deal.Report.service;

import cn.com.eju.deal.Report.model.ReportQueueAjax;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.common.util.FileUtils;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.poi.ExcelForExpend;
import cn.com.eju.deal.poi.ExcelForExpendBigData;
import cn.com.eju.deal.poi.common.ExcelUseXmlAbstract;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by eju on 2019/5/8.
 */
@Service("exportReportBaseService")
public class ExportReportBaseService {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private ExpendReportService expendReportService;

    @Resource
    private ReportQueueService reportQueueService;


    @Async("threadPoolTaskExecutorExpend")
    public void downLoadExcelAjax(Map<String, Object> reqMap, String fileCode, String fileName, int id) throws Exception {
        try {
            String ctxPath = (String) reqMap.get("ctxPath");
            String url = ctxPath + "data" + File.separator + fileCode;
            reqMap.put("ctxPath",null);

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

            ExcelUseXmlAbstract instance = null;
            //拓展明细
            instance = new ExcelForExpendBigData();
            String pathName = url + File.separator + time + File.separator + fileName;
            ((ExcelForExpendBigData)instance).downloadSheet(reqMap, new File(pathName));

            ReportQueueAjax reportDb = new ReportQueueAjax();
            reportDb.setDelFlag("N");
            reportDb.setFilePath(pathName);
            reportDb.setId(id);
            reportQueueService.uptReportQueueAjax(reportDb);


        } catch (IOException e) {

            logger.error("下载Excel失败", e);

        }finally{
            //减
            reportQueueService.updateReportQueueTotal("exReport_filepath", 2);
        }
    }
}
