package cn.com.eju.pmls.statisticsReport.linkDetail.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.pmls.poi.ExcelForReportOrderBigDataSxCsv;
import cn.com.eju.pmls.poi.common.ExcelSXSSFCommon;
import cn.com.eju.pmls.utils.CurlUtils;
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
import cn.com.eju.pmls.report.service.PmlsReportQueueService;

/**
 * Service层
 *
 * @author tanlang
 */
@Service("pmlsReportOrderDetailService")
public class PmlsReportOrderDetailService extends BaseService<LinkDetailDto> {

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
    public ResultData<?> queryList(Map<String, Object> reqMap, PageInfo pageInfo)
            throws Exception {

        reqMap.put("optFlag", "search");

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "pmlsReportOrderDetail" + "/queryList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;

    }


    @Async("threadPoolTaskExecutorPmlsOrderDetail")
    public void downLoadExcelAjax(Map<String, Object> reqMap, String fileCode, String fileName, int id) throws Exception {
        logger.info("订单明细导出##Service downLoadExcelAjax 开始！reqMap="+JSON.toJSONString(reqMap));
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

            ExcelSXSSFCommon instances = null;

            instances = new ExcelForReportOrderBigDataSxCsv();
            String pathName = url + File.separator + time + File.separator + fileName;
            String yearly = "2018";
            if (reqMap.get("organization") != null) {
                yearly = reqMap.get("organization").toString();
            }
            //csv
            String csvFileUrl = this.export(reqMap);
            StringBuffer errSb = new StringBuffer();
            String pathNameCsv = url + File.separator + time + File.separator + ReportConstant.REPORT_ORDER_DETAIL_NAME_CSV;
            String crulPath = SystemParam.getWebConfigValue("crulPath");

            String crulCommand1 = SystemParam.getWebConfigValue("crulCommand1");
            String crulCommand2 = SystemParam.getWebConfigValue("crulCommand2");
            String crulCommand3 = SystemParam.getWebConfigValue("crulCommand3");
            Map<String,String> crulCommandMap = new HashMap<>();
            crulCommandMap.put("crulCommand1",crulCommand1);
            crulCommandMap.put("crulCommand2",crulCommand2);
            crulCommandMap.put("crulCommand3",crulCommand3);
            CurlUtils.curl(csvFileUrl,null, pathNameCsv,errSb,crulPath,crulCommandMap);
            logger.info("订单明细CSV下载##curl end ##url="+ csvFileUrl+"#savePath="+pathNameCsv+"#crulPath"+crulPath
            +"#crulCommandMap="+crulCommandMap
            +"#reqMap="+JSON.toJSONString(reqMap));
            reqMap.put("pathNameCsv",pathNameCsv);
            reqMap.put("templatePath",ctxPath);
            instances.downloadSheet(reqMap, new File(pathName));
            //csv
            logger.info("订单明细导出##Service downloadSheet-end！reqMap="+JSON.toJSONString(reqMap));
            ReportQueueAjax reportDb = new ReportQueueAjax();
            reportDb.setDelFlag("N");
            reportDb.setFilePath(pathName);
            reportDb.setId(id);
            pmlsReportQueueService.uptReportQueueAjax(reportDb);

        } catch (Exception e) {
            logger.error("订单明细导出##Service downLoadExcelAjax 异常"+"##reqMap="+JSON.toJSONString(reqMap),e);
            logger.error("pmlsReportOrderDetailService", "PmlsReportOrderDetailService", "downLoadExcelAjax", JSON.toJSONString(reqMap), null, "", "下载Excel失败", e);
        } finally {
            //减
            pmlsReportQueueService.updateReportQueueTotal("reportOrderDetail_filepath", 2);
        }
        logger.info("订单明细导出##Service downLoadExcelAjax 结束！reqMap="+JSON.toJSONString(reqMap));
    }


    public String export(Map<String, Object> reqMap) throws Exception {
        logger.info("订单明细CSV生成##export start ##reqMap="+JSON.toJSONString(reqMap));
        ResultData<?> reback = null;
        String csvFileUrl = null;
        try {
            //调用 接口
            String url = SystemParam.getWebConfigValue("RestServer") + "pmlsReportOrderDetail" + "/export/{param}";
            reback = get(url, reqMap);
            csvFileUrl = (String)reback.getReturnData();

        }catch (Exception e){
            logger.error("订单明细CSV生成##export error"+"##reqMap="+JSON.toJSONString(reqMap),e);
            logger.error("pmlsReportOrderDetailService", "PmlsReportOrderDetailService", "export", JSON.toJSONString(reqMap), null, "", "生成Excel csv 失败", e);
        }
        logger.info("订单明细CSV生成##export end ##reqMap="+JSON.toJSONString(reqMap)+"#csvFileUrl="+csvFileUrl);
        return  csvFileUrl;
    }
}
