package cn.com.eju.pmls.statisticsReport.linkDetail.service;

import cn.com.eju.deal.Report.model.ReportQueueAjax;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.util.FileUtils;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.statisticsReport.common.dto.LinkDetailDto;
import cn.com.eju.pmls.poi.ExcelForLinkProjectTraceBigData;
import cn.com.eju.pmls.poi.common.ExcelUseXmlAbstract;
import cn.com.eju.pmls.report.service.PmlsReportQueueService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.Map;

/**
 * Service层
 *
 * @author tanlang
 */
@Service("pmlsLinkProjectTraceService")
public class PmlsLinkProjectTraceService extends BaseService<LinkDetailDto> {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private PmlsReportQueueService pmlsReportQueueService;

    /**
     * 查询-list
     *
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData<?> queryLinkProjectTraceList(Map<String, Object> reqMap, PageInfo pageInfo)
            throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "linkDetail" + "/queryLinkProjectTraceList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;

    }

    public ResultData<?> queryCityList(Map<String, Object> map) throws Exception{
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "linkDetail" + "/queryCityList/{param}";

        ResultData<?> reback = get(url, map);

        return reback;
    }

    @Async("threadPoolTaskExecutorPmlsLinkProjectTrace")
    public void downLoadExcelAjax(Map<String, Object> reqMap, String fileCode, String fileName, int id) throws Exception {
        try {
            String ctxPath = (String) reqMap.get("ctxPath");
            String url = ctxPath + "data" + File.separator + fileCode;
            reqMap.put("ctxPath", null);

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
            //联动明细
            instance = new ExcelForLinkProjectTraceBigData();
            String pathName = url + File.separator + time + File.separator + fileName;
            ((ExcelForLinkProjectTraceBigData)instance).downloadSheet(reqMap, new File(pathName));

            ReportQueueAjax reportDb = new ReportQueueAjax();
            reportDb.setDelFlag("N");
            reportDb.setFilePath(pathName);
            reportDb.setId(id);
            pmlsReportQueueService.uptReportQueueAjax(reportDb);


        } catch (Exception e) {

            logger.error("下载Excel失败", e);

        } finally {
            //减
            pmlsReportQueueService.updateReportQueueTotal("linkProjectTrace_filepath", 2);
        }
    }
}
