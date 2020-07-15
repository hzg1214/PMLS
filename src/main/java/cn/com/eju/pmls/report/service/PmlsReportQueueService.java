package cn.com.eju.pmls.report.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.Report.model.ReportQueueAjax;
import cn.com.eju.deal.Report.model.ReportQueueTotal;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;

/**
 * Created by eju on 2019/5/7.
 */
@Service("pmlsReportQueueService")
public class PmlsReportQueueService extends BaseService {

    public ResultData<?> selectReportListByUser(Map<String, Object> reqMap) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "reportQueue" + "/selectReportListByUser/{param}";

        ResultData<?>  reback = get(url, reqMap);

        return reback;
    }

    public void updateReportQueueTotal(String ReportKey, int type) throws Exception {
        ReportQueueTotal reportQueueTotal = new ReportQueueTotal();
        reportQueueTotal.setReportKey(ReportKey);
        if(type==1){
            reportQueueTotal.setPositiveSize(1);
        }else{
            reportQueueTotal.setMinusSize(1);
        }
        String url = SystemParam.getWebConfigValue("RestServer") + "reportQueue" + "/updateReportQueueTotal";
        post(url, reportQueueTotal);
    }

    public int addReportQueueAjax(ReportQueueAjax reportDb) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "reportQueue" + "/addReportQueueAjax";
        ResultData<?> reback = post(url, reportDb);
        return Integer.valueOf(reback.getReturnData().toString());
    }

    public ResultData<?> selectReportQueueTotalTopOne(String reportKey) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "reportQueue/selectReportQueueTotalTopOne/{reportKey}";
        ResultData<?> resultData = get(url,reportKey);
        return resultData;
    }

    public void uptReportQueueAjax(ReportQueueAjax reportQueueAjax) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "reportQueue" + "/uptReportQueueAjax";
        post(url, reportQueueAjax);
    }

    public ResultData<?> selectReportQueueAjaxTopOne(String reportName, Integer userId) throws Exception {
        ReportQueueAjax query = new ReportQueueAjax();
        query.setUserId(userId);
        query.setReportKey(reportName);
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "reportQueue" + "/selectReportQueueAjaxTopOne";

        ResultData<?>  reback = post(url, query);

        return reback;
    }

    public void delReportQueueAjax(LinkedHashMap<String, Object> map) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        map.put("dateCreate",null);
        ReportQueueAjax reportQueueAjax = mapper.convertValue(map, ReportQueueAjax.class);
        String url = SystemParam.getWebConfigValue("RestServer") + "reportQueue" + "/delReportQueueAjax";
        post(url, reportQueueAjax);
    }

    public ResultData<?> selectByPrimaryKey(int id) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "reportQueue" + "/selectByPrimaryKey/{id}";

        ResultData<?>  reback = get(url,id);

        return reback;
    }
}
