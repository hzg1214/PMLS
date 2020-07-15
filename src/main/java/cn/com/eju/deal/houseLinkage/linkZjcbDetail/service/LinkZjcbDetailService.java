package cn.com.eju.deal.houseLinkage.linkZjcbDetail.service;

import cn.com.eju.deal.Report.model.ReportQueueAjax;
import cn.com.eju.deal.Report.service.ReportQueueService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.util.FileUtils;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.houseLinkage.LinkDetail.LinkDetailDto;
import cn.com.eju.deal.poi.ExcelForLinkZjcbDetailBigDataNew;
import cn.com.eju.deal.poi.common.ExcelUseXmlAbstract;
import com.alibaba.fastjson.JSON;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * desc:service 联动明细(资金成本)
 * @author :zhenggang.Huang
 * @date   :2019年7月18日
 */
@Service("linkZjcbDetailService")
public class LinkZjcbDetailService extends BaseService<LinkDetailDto> {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private ReportQueueService reportQueueService;

    /**
     * 查询-list
     *
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData<?> queryLinkZjcbDetailList(Map<String, Object> reqMap, PageInfo pageInfo)
            throws Exception {
        reqMap.put("optFlag", "search");

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "linkZjcbDetail" + "/queryLinkZjcbDetailList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;

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

            ExcelUseXmlAbstract instance = null;
            //联动明细
            instance = new ExcelForLinkZjcbDetailBigDataNew();
            String pathName = url + File.separator + time + File.separator + fileName;
            String yearly = "2019";
            if (reqMap.get("organization") != null) {
                yearly = reqMap.get("organization").toString();
            }

            // 起初时间
            String qcTimeStage = "";
            String zljTimeStage = "";
            String dnyqTimeStage = "";
            String dnxzTimeStage = "";

            String dnTimeStr = yearly + "-01-01";
            LocalDate dnld = LocalDate.parse(dnTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate dnyqld = dnld.plusDays(-1);
            // 起初没有选择的场合
            if (!reqMap.containsKey("dateStage") || "".equals(reqMap.get("dateStage").toString())) {

                dnyqTimeStage = "(" + dnyqld.toString() + "及以前)";
                dnxzTimeStage = "("+ dnTimeStr + "及以后)" ;
            }
            // 选择起初的场合
            else {
                String qcDateStr = reqMap.get("dateStage").toString() + "-01";
                LocalDate qcld = LocalDate.parse(qcDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate qcyqld = qcld.plusDays(-1);
                qcTimeStage = "("+ qcyqld.toString() + "及以前)";
                zljTimeStage = "(" + qcDateStr + "及以后)";

                // 起初<年度
                if (qcld.isBefore(dnld)) {
                    dnyqTimeStage = "("+ qcDateStr + "至" + dnyqld.toString() +")";
                    dnxzTimeStage = "("+ dnTimeStr + "及以后)";
                }
                // 起初>年度
                else {
                    dnyqTimeStage = "";
                    dnxzTimeStage = "("+ qcDateStr + "及以后)";
                }
            }
            //业绩查询
//            ((ExcelForLinkDetail)instance).downloadSheet(allList, new File(pathName), yearly);
            ((ExcelForLinkZjcbDetailBigDataNew) instance).downloadSheet(reqMap, new File(pathName), yearly, qcTimeStage, zljTimeStage, dnyqTimeStage, dnxzTimeStage);
            ReportQueueAjax reportDb = new ReportQueueAjax();
            reportDb.setDelFlag("N");
            reportDb.setFilePath(pathName);
            reportDb.setId(id);
            reportQueueService.uptReportQueueAjax(reportDb);

        } catch (Exception e) {
            logger.error("linkZjcbDetail", "linkZjcbDetailService", "downLoadExcelAjax", JSON.toJSONString(reqMap), null, "", "下载Excel失败", e);
        } finally {
            //减
            reportQueueService.updateReportQueueTotal("linkZjcbDetail_filepath", 2);
        }
    }
}
