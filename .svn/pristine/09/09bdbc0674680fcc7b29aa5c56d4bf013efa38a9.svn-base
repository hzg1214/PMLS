package cn.com.eju.pmls.statisticsReport.linkDetail.service;

import java.io.File;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.com.eju.deal.Report.model.ReportQueueAjax;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.util.FileUtils;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.houseLinkage.LinkDetail.LinkDetailDto;
import cn.com.eju.pmls.poi.ExcelForLinkProjectDetailBigData;
import cn.com.eju.pmls.report.service.PmlsReportQueueService;

/**
 * Service层
 *
 * @author 
 */
@Service("pmlsLinkProjectDetailService")
public class PmlsLinkProjectDetailService extends BaseService<LinkDetailDto> {

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
    public ResultData<?> queryLinkProjectDetailList(Map<String, Object> reqMap, PageInfo pageInfo)
            throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "linkDetail" + "/queryLinkProjectDetailList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;

    }
    
    @Async("threadPoolTaskExecutorPmlsLinkProjectDetail")
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

            ExcelForLinkProjectDetailBigData instance = null;
            instance = new ExcelForLinkProjectDetailBigData();
            String pathName = url + File.separator + time + File.separator + fileName;
            String yearly = "2019";
            if (reqMap.get("organization") != null)
                yearly = reqMap.get("organization").toString();
            //业绩查询
            ((ExcelForLinkProjectDetailBigData) instance).downloadSheet(reqMap, new File(pathName), yearly);
            ReportQueueAjax reportDb = new ReportQueueAjax();
            reportDb.setDelFlag("N");
            reportDb.setFilePath(pathName);
            reportDb.setId(id);
            pmlsReportQueueService.uptReportQueueAjax(reportDb);

        } catch (Exception e) {
            logger.error("linkProjectDetail", "linkProjectDetailService", "downLoadExcelAjax", JSON.toJSONString(reqMap), null, "", "下载Excel失败", e);
        } finally {
            //减
        	pmlsReportQueueService.updateReportQueueTotal("linkProjectDetail_filepath", 2);
        }
    }
}
