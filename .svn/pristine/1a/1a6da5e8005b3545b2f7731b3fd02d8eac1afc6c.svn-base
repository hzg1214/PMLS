package cn.com.eju.pmls.statisticsReport.refund.service;

import cn.com.eju.deal.Report.model.ReportQueueAjax;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.report.service.PmlsReportQueueService;
import com.alibaba.fastjson.JSON;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service("pmlsRefundTraceService")
public class PmlsRefundTraceService extends BaseService {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private PmlsReportQueueService pmlsReportQueueService;


    public ResultData<?> queryCityList(Map<String, Object> map) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "linkDetail" + "/queryCityList/{param}";

        ResultData<?> reback = get(url, map);

        return reback;
    }

    /**
     * 查询-list
     */
    public ResultData<?> queryList(Map<String, Object> reqMap, PageInfo pageInfo)
            throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "pmlsRefundTraceController" + "/queryList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;

    }

    @Async("threadPoolTaskExecutorForPartWeek")
    public void export(Map<String, Object> reqMap,Integer id) throws Exception {
        ResultData<?> reback = null;
        try {
            //调用 接口
            String url = SystemParam.getWebConfigValue("RestServer") + "pmlsRefundTraceController" + "/export";

            reback = post(url, reqMap);

            ReportQueueAjax reportDb = new ReportQueueAjax();
            reportDb.setDelFlag("N");
            reportDb.setFilePath((String)reback.getReturnData());
            reportDb.setId(id);
            pmlsReportQueueService.uptReportQueueAjax(reportDb);

        }catch (Exception e){
            logger.error("pmlsRefundTraceService", "PmlsRefundTraceService", "export", JSON.toJSONString(reqMap), null, "", "生成Excel失败", e);
        } finally {
            //减
            pmlsReportQueueService.updateReportQueueTotal("linkProjectPartAWeek_filepath", 2);
        }

    }

}
