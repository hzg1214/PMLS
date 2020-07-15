package cn.com.eju.pmls.statisticsReport.linkZjcbDetail.service;

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
import cn.com.eju.pmls.poi.common.ExcelUseXmlAbstract;
import cn.com.eju.pmls.poi.ExcelForLinkMarginDetailBigData;
import cn.com.eju.pmls.report.service.PmlsReportQueueService;

/**
 * desc:service 联动资金成本(保证金、诚意金)
 * @author :zhenggang.Huang
 * @date   :2019年9月12日
 */
@Service("pmlsLinkMarginDetailService")
public class PmlsLinkMarginDetailService extends BaseService<LinkDetailDto> {

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
    public ResultData<?> queryLinkMarginDetailList(Map<String, Object> reqMap, PageInfo pageInfo)
            throws Exception {
        reqMap.put("optFlag", "search");

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "linkMarginDetail" + "/queryLinkMarginDetailList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;

    }
    
    /**
     * desc:成本中心列表
     * 2019年9月24日
     * author:zhenggang.Huang
     */
    public ResultData<?> queryCostCenterList(String organization) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "linkMarginDetail" + "/queryCostCenterList/{organization}";
        ResultData<?> backResult = get(url, organization);
        return backResult;
    }

    @Async("threadPoolTaskExecutorLinkZjcbDetail")
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

            ExcelForLinkMarginDetailBigData instance = null;
            instance = new ExcelForLinkMarginDetailBigData();
            String pathName = url + File.separator + time + File.separator + fileName;
            String yearly = "2019";
            if (reqMap.get("organization") != null)
                yearly = reqMap.get("organization").toString();
            //业绩查询
            ((ExcelForLinkMarginDetailBigData) instance).downloadSheet(reqMap, new File(pathName), yearly);
            ReportQueueAjax reportDb = new ReportQueueAjax();
            reportDb.setDelFlag("N");
            reportDb.setFilePath(pathName);
            reportDb.setId(id);
            pmlsReportQueueService.uptReportQueueAjax(reportDb);

        } catch (Exception e) {
            logger.error("linkMarginDetail", "linkMarginDetailService", "downLoadExcelAjax", JSON.toJSONString(reqMap), null, "", "下载Excel失败", e);
        } finally {
            //减
        	pmlsReportQueueService.updateReportQueueTotal("linkMarginDetail_filepath", 2);
        }
    }
}
