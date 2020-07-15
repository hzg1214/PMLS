package cn.com.eju.pmls.statisticsReport.company.service;

import java.io.File;
import java.util.LinkedHashMap;
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
import cn.com.eju.pmls.poi.ExcelForCompanyDetailBigData;
import cn.com.eju.pmls.report.service.PmlsReportQueueService;

/**
 * Service层
 *
 */
@Service("rptCompanyDetailService")
public class RptCompanyDetailService extends BaseService {

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
    public ResultData<?> queryCompanyDetailList(Map<String, Object> reqMap, PageInfo pageInfo)
            throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "rptCompanyDetail" + "/queryCompanyDetailList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;

    }
    
	/**
	 * desc:获取表头
	 * 2020年6月18日
	 */
    public ResultData<?> getTitle(Map<String, Object> reqMap) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "rptCompanyDetail" + "/getTitle";
        ResultData<?> reback = post(url, reqMap);
        return reback;
    }

    @Async("threadPoolTaskExecutorRptCompanyDetail")
    public void downLoadExcelAjax(Map<String, Object> reqMap, String fileCode, String fileName, int id) throws Exception {
    	ResultData<?> resultData = null;
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

            ExcelForCompanyDetailBigData instance = null;
            instance = new ExcelForCompanyDetailBigData();
            String pathName = url + File.separator + time + File.separator + fileName;
            Map<String, Object> titleMap = new LinkedHashMap<>();
            
            if ("weekDimension".equals(reqMap.get("roughDimension").toString())) {
            	resultData = getTitle(reqMap);
            	LinkedHashMap<String, Object> companyTitle = (LinkedHashMap<String, Object>) resultData.getReturnData();
            	logger.info("经纪公司明细-导出文件表头:"+companyTitle);
            	
            	titleMap.putAll(companyTitle);
            }

            //业绩查询
            ((ExcelForCompanyDetailBigData) instance).downloadSheet(reqMap, new File(pathName), titleMap);
            ReportQueueAjax reportDb = new ReportQueueAjax();
            reportDb.setDelFlag("N");
            reportDb.setFilePath(pathName);
            reportDb.setId(id);
            pmlsReportQueueService.uptReportQueueAjax(reportDb);

        } catch (Exception e) {
            logger.error("rptCompanyDetail", "rptCompanyDetailService", "downLoadExcelAjax", JSON.toJSONString(reqMap), null, "", "下载Excel失败", e);
        } finally {
            //减
        	pmlsReportQueueService.updateReportQueueTotal("companyDetail_filepath", 2);
        }
    }

}
