package cn.com.eju.deal.Report.controller;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.model.ReportQueueAjax;
import cn.com.eju.deal.Report.service.ExpendReportService;
import cn.com.eju.deal.Report.service.ReportQueueService;
import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.BaseUtils;
import cn.com.eju.deal.common.util.FileUtils;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.poi.*;
import cn.com.eju.deal.poi.common.ExcelUseXmlAbstract;
import cn.com.eju.pmls.poi.ExcelForBatchReback;
import org.springframework.scheduling.annotation.Async;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by eju on 2018/1/22.
 */
public class BaseReportController extends BaseController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private ExpendReportService expendReportService;

    @Resource
    private ReportQueueService reportQueueService;

    /**
     * 报表查询条件处理
     *
     * @param queryParam
     */
    public Map<String, Object> changeParam(Map<String, Object> queryParam) {
        try {
            //业务阶段
            if (queryParam.containsKey("stages")) {
                queryParam.put("stageStr", BaseUtils.parseSplitToString((String) queryParam.get("stages"), ";", ",", false));
            }
            queryParam.remove("stages");

            //合作模式
            if (queryParam.containsKey("contractTypes")) {
                queryParam.put("contractTypeStr", BaseUtils.parseSplitToString((String) queryParam.get("contractTypes"), ";", ",", false));
            }
            queryParam.remove("contractTypes");
            //经验场所类型
            if (queryParam.containsKey("businessPlaceTypes")) {
                queryParam.put("businessPlaceTypeStr", BaseUtils.parseSplitToString((String) queryParam.get("businessPlaceTypes"), ";", ",", false));
            }
            queryParam.remove("businessPlaceTypes");

            //签约状态
            if (queryParam.containsKey("storeSignStatuses")) {
                queryParam.put("storeSignStatusStr", BaseUtils.parseSplitToString((String) queryParam.get("storeSignStatuses"), ";", ",", false));
            }
            queryParam.remove("storeSignStatuses");

            //门店规模
            if (queryParam.containsKey("storeSizeScales")) {
                queryParam.put("storeSizeScaleStr", BaseUtils.parseSplitToString((String) queryParam.get("storeSizeScales"), ";", ",", false));
            }
            queryParam.remove("storeSizeScales");

            //门店类型
            if (queryParam.containsKey("storeTypes")) {
                queryParam.put("storeTypeStr", BaseUtils.parseSplitToString((String) queryParam.get("storeTypes"), ";", ",", false));
            }
            queryParam.remove("storeTypes");

            //归属区域
            if (queryParam.containsKey("regionCodes")) {
                queryParam.put("regionCodeStr", BaseUtils.parseSplitToString((String) queryParam.get("regionCodes"), ";", ",", false));
            }
            queryParam.remove("regionCodes");

            //归属城市
            if (queryParam.containsKey("areaCityCodes")) {
                queryParam.put("areaCityCodeStr", BaseUtils.parseSplitToString((String) queryParam.get("areaCityCodes"), ";", ",", false));
            }
            queryParam.remove("areaCityCodes");

            //所在城市
            if (queryParam.containsKey("cityIds")) {
                queryParam.put("cityIdStr", BaseUtils.parseSplitToString((String) queryParam.get("cityIds"), ";", ",", false));
            }
            queryParam.remove("cityIds");

            //归属中心
            if (queryParam.containsKey("centerIds")) {
                queryParam.put("centerIdStr", BaseUtils.parseSplitToString((String) queryParam.get("centerIds"), ";", ",", false));
            }
            queryParam.remove("centerIds");

            //城市编号
            if (queryParam.containsKey("cityNos")) {
                queryParam.put("cityNoStr", BaseUtils.parseSplitToString((String) queryParam.get("cityNos"), ";", ",", false));
            }
            queryParam.remove("cityNos");

            //项目状态
            if (queryParam.containsKey("projectStatus")) {
                queryParam.put("projectStatusStr", BaseUtils.parseSplitToString((String) queryParam.get("projectStatus"), ";", ",", false));
            }
            queryParam.remove("projectStatus");

            //跟进目的
            if (queryParam.containsKey("followAimCodes")) {
                String followAimCodeStr = "";
                String followAimCodes = (String) queryParam.get("followAimCodes");
                String[] followAimCodesArr = followAimCodes.split(";");
                Arrays.sort(followAimCodesArr);
                for (String s : followAimCodesArr) {
                    followAimCodeStr += s + '%';
                }
                followAimCodeStr = followAimCodeStr.substring(0, followAimCodeStr.length() - 1);
                queryParam.put("followAimCodeStr", followAimCodeStr);
                //queryParam.put("followAimCodeStr", BaseUtils.parseSplitToString((String) queryParam.get("followAimCodes"), ";", ",", false));
            }
            queryParam.remove("followAimCodes");

            //项目归属部门
            if (queryParam.containsKey("deptIds")) {
                queryParam.put("deptIdStr", BaseUtils.parseSplitToString((String) queryParam.get("deptIds"), ";", ",", false));
            }
            queryParam.remove("deptIds");

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        return queryParam;
    }

    public Map<String, Object> changeParam(Map<String, Object> queryParam, String split, String toSplit) {
        try {
            //业务阶段
            if (queryParam.containsKey("stages")) {
                queryParam.put("stageStr", BaseUtils.parseSplitToString((String) queryParam.get("stages"), split, toSplit, false));
            }
            queryParam.remove("stages");

            //合作模式
            if (queryParam.containsKey("contractTypes")) {
                queryParam.put("contractTypeStr", BaseUtils.parseSplitToString((String) queryParam.get("contractTypes"), split, toSplit, false));
            }
            queryParam.remove("contractTypes");
            //经验场所类型
            if (queryParam.containsKey("businessPlaceTypes")) {
                queryParam.put("businessPlaceTypeStr", BaseUtils.parseSplitToString((String) queryParam.get("businessPlaceTypes"), split, toSplit, false));
            }
            queryParam.remove("businessPlaceTypes");

            //签约状态
            if (queryParam.containsKey("storeSignStatuses")) {
                queryParam.put("storeSignStatusStr", BaseUtils.parseSplitToString((String) queryParam.get("storeSignStatuses"), split, toSplit, false));
            }
            queryParam.remove("storeSignStatuses");

            //门店规模
            if (queryParam.containsKey("storeSizeScales")) {
                queryParam.put("storeSizeScaleStr", BaseUtils.parseSplitToString((String) queryParam.get("storeSizeScales"), split, toSplit, false));
            }
            queryParam.remove("storeSizeScales");

            //门店类型
            if (queryParam.containsKey("storeTypes")) {
                queryParam.put("storeTypeStr", BaseUtils.parseSplitToString((String) queryParam.get("storeTypes"), split, toSplit, false));
            }
            queryParam.remove("storeTypes");

            //归属区域
            if (queryParam.containsKey("regionCodes")) {
                queryParam.put("regionCodeStr", BaseUtils.parseSplitToString((String) queryParam.get("regionCodes"), split, toSplit, false));
            }
            queryParam.remove("regionCodes");

            //归属城市
            if (queryParam.containsKey("areaCityCodes")) {
                queryParam.put("areaCityCodeStr", BaseUtils.parseSplitToString((String) queryParam.get("areaCityCodes"), split, toSplit, false));
            }
            queryParam.remove("areaCityCodes");

            //所在城市
            if (queryParam.containsKey("cityIds")) {
                queryParam.put("cityIdStr", BaseUtils.parseSplitToString((String) queryParam.get("cityIds"), split, toSplit, false));
            }
            queryParam.remove("cityIds");

            //归属中心
            if (queryParam.containsKey("centerIds")) {
                queryParam.put("centerIdStr", BaseUtils.parseSplitToString((String) queryParam.get("centerIds"), split, toSplit, false));
            }
            queryParam.remove("centerIds");

            //城市编号
            if (queryParam.containsKey("cityNos")) {
                queryParam.put("cityNoStr", BaseUtils.parseSplitToString((String) queryParam.get("cityNos"), split, toSplit, false));
            }
            queryParam.remove("cityNos");

            //项目状态
            if (queryParam.containsKey("projectStatus")) {
                queryParam.put("projectStatusStr", BaseUtils.parseSplitToString((String) queryParam.get("projectStatus"), split, toSplit, false));
            }
            queryParam.remove("projectStatus");

            //跟进目的
            if (queryParam.containsKey("followAimCodes")) {
                String followAimCodeStr = "";
                String followAimCodes = (String) queryParam.get("followAimCodes");
                String[] followAimCodesArr = followAimCodes.split(split);
                Arrays.sort(followAimCodesArr);
                for (String s : followAimCodesArr) {
                    followAimCodeStr += s + '%';
                }
                followAimCodeStr = followAimCodeStr.substring(0, followAimCodeStr.length() - 1);
                queryParam.put("followAimCodeStr", followAimCodeStr);
                //queryParam.put("followAimCodeStr", BaseUtils.parseSplitToString((String) queryParam.get("followAimCodes"), ";", ",", false));
            }
            queryParam.remove("followAimCodes");

            //项目归属部门
            if (queryParam.containsKey("deptIds")) {
                queryParam.put("deptIdStr", BaseUtils.parseSplitToString((String) queryParam.get("deptIds"), split, toSplit, false));
            }
            queryParam.remove("deptIds");

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        return queryParam;
    }

    public Map<String, Object> changeParamToList(Map<String, Object> queryParam) {
        try {
            //核算主体
            if (queryParam.containsKey("accountProjectNos")) {
                queryParam.put("accountProjectList", BaseUtils.changeArrayToList((String) queryParam.get("accountProjectNos"), ";"));
            }
            queryParam.remove("accountProjectNos");

            //归属区域
            if (queryParam.containsKey("regionCodes")) {
                queryParam.put("regionCodeList", BaseUtils.changeArrayToList((String) queryParam.get("regionCodes"), ";"));
            }
            queryParam.remove("regionCodes");

            //查询维度
            if (queryParam.containsKey("serachKeys")) {
                String serachKeys = (String) queryParam.get("serachKeys");
                if (!StringUtil.isEmpty(serachKeys)) {
                    String[] serachKeysList = serachKeys.split(";");
                    String serachKey = "";
                    for (String serachKeyParam : serachKeysList) {
                        serachKey += serachKeyParam;
                    }
                    queryParam.put("serachKey", serachKey);
                }
            }
            queryParam.remove("serachKeys");
            //归属城市
            if (queryParam.containsKey("areaCityCodes")) {
                queryParam.put("areaCityCodeList", BaseUtils.changeArrayToList((String) queryParam.get("areaCityCodes"), ";"));
            }
            queryParam.remove("areaCityCodes");

            //所在城市
            if (queryParam.containsKey("cityIds")) {
                queryParam.put("cityIdList", BaseUtils.changeArrayToList((String) queryParam.get("cityIds"), ";"));
            }
            queryParam.remove("cityIds");

            //归属中心
            if (queryParam.containsKey("centerIds")) {
                queryParam.put("centerIdList", BaseUtils.changeArrayToList((String) queryParam.get("centerIds"), ";"));
            }
            queryParam.remove("centerIds");

            //城市编号
            if (queryParam.containsKey("cityNos")) {
                queryParam.put("cityNoList", BaseUtils.changeArrayToList((String) queryParam.get("cityNos"), ";"));
            }
            queryParam.remove("cityNos");

            //合作模式
            if (queryParam.containsKey("contractTypes")) {
                queryParam.put("contractTypeList", BaseUtils.changeArrayToList((String) queryParam.get("contractTypes"), ";"));
            }
            queryParam.remove("contractTypes");

            //所有城市
            if (queryParam.containsKey("allCityId")) {
                queryParam.put("allCityIdList", BaseUtils.changeArrayToList((String) queryParam.get("allCityId"), ","));
            }
            queryParam.remove("allCityId");

            //合同结束原因
            if (queryParam.containsKey("dueCause")) {
                queryParam.put("dueCauseList", BaseUtils.changeArrayToList((String) queryParam.get("dueCause"), ";"));
            }
            queryParam.remove("dueCause");

            //成本中心
            if (queryParam.containsKey("costCodes")) {
                queryParam.put("costCodeList", BaseUtils.changeArrayToList((String) queryParam.get("costCodes"), ";"));
            }
            queryParam.remove("costCodes");


        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        return queryParam;
    }


    public Map<String, Object> changeParamToList(Map<String, Object> queryParam, String split) {
        try {
            //核算主体
            if (queryParam.containsKey("accountProjectNos")) {
                queryParam.put("accountProjectList", BaseUtils.changeArrayToList((String) queryParam.get("accountProjectNos"), split));
            }
            queryParam.remove("accountProjectNos");

            //归属区域
            if (queryParam.containsKey("regionCodes")) {
                queryParam.put("regionCodeList", BaseUtils.changeArrayToList((String) queryParam.get("regionCodes"), split));
            }
            queryParam.remove("regionCodes");

            //查询维度
            if (queryParam.containsKey("serachKeys")) {
                String serachKeys = (String) queryParam.get("serachKeys");
                if (!StringUtil.isEmpty(serachKeys)) {
                    String[] serachKeysList = serachKeys.split(split);
                    String serachKey = "";
                    for (String serachKeyParam : serachKeysList) {
                        serachKey += serachKeyParam;
                    }
                    queryParam.put("serachKey", serachKey);
                }
            }
            queryParam.remove("serachKeys");
            //归属城市
            if (queryParam.containsKey("areaCityCodes")) {
                queryParam.put("areaCityCodeList", BaseUtils.changeArrayToList((String) queryParam.get("areaCityCodes"), split));
            }
            queryParam.remove("areaCityCodes");

            //所在城市
            if (queryParam.containsKey("cityIds")) {
                queryParam.put("cityIdList", BaseUtils.changeArrayToList((String) queryParam.get("cityIds"), split));
            }
            queryParam.remove("cityIds");

            //归属中心
            if (queryParam.containsKey("centerIds")) {
                queryParam.put("centerIdList", BaseUtils.changeArrayToList((String) queryParam.get("centerIds"), split));
            }
            queryParam.remove("centerIds");

            //城市编号
            if (queryParam.containsKey("cityNos")) {
                queryParam.put("cityNoList", BaseUtils.changeArrayToList((String) queryParam.get("cityNos"), split));
            }
            queryParam.remove("cityNos");

            //合作模式
            if (queryParam.containsKey("contractTypes")) {
                queryParam.put("contractTypeList", BaseUtils.changeArrayToList((String) queryParam.get("contractTypes"), split));
            }
            queryParam.remove("contractTypes");

            //所有城市
            if (queryParam.containsKey("allCityId")) {
                queryParam.put("allCityIdList", BaseUtils.changeArrayToList((String) queryParam.get("allCityId"), split));
            }
            queryParam.remove("allCityId");

            //合同结束原因
            if (queryParam.containsKey("dueCause")) {
                queryParam.put("dueCauseList", BaseUtils.changeArrayToList((String) queryParam.get("dueCause"), split));
            }
            queryParam.remove("dueCause");

            //成本中心
            if (queryParam.containsKey("costCodes")) {
                queryParam.put("costCodeList", BaseUtils.changeArrayToList((String) queryParam.get("costCodes"), split));
            }
            queryParam.remove("costCodes");


        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        return queryParam;
    }


    public Map<String, Object> getAllCity(Map<String, Object> reqMap) throws Exception {

        reqMap.put("userId", UserInfoHolder.getUserId());
        Map<?, ?> reback = expendReportService.getUserCenterAuthCityName(reqMap);

        List<Map<String, Object>> list = (List<Map<String, Object>>) reback.get("list");

        String cityId = "";
        for (Map<String, Object> map : list) {
            cityId = cityId + "," + map.get("cityId");
        }
        String allCityId = (String) cityId.subSequence(1, cityId.length());

        reqMap.put("allCityId", allCityId);

        return reqMap;
    }

    /**
     * 报表Excel下载
     *
     * @param request
     * @param response
     * @param contentList
     * @param reqMap
     * @param fileCode
     * @param fileName
     * @throws Exception
     */
    public void downLoadExcel(HttpServletRequest request, HttpServletResponse response, List<LinkedHashMap<String, Object>> contentList, Map<String, Object> reqMap, String fileCode, String fileName) throws Exception {

        String ctxPath = request.getSession().getServletContext().getRealPath("/");
        String url = ctxPath + "data" + File.separator + fileCode;

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
        if (ReportConstant.EXPANDDETAIL_CODE.equals(fileCode)) {
            //拓展明细
            instance = new ExcelForExpend();
        } else if (ReportConstant.STOREPRESERVE_CODE.equals(fileCode)) {
            //门店维护
            instance = new ExcelStorePreserve();
        } else if (ReportConstant.PERFORMANCESUM_CODE.equals(fileCode)) {
            //中心汇总
            instance = new ExcelForPerformanceSum();
        } else if (ReportConstant.SIGNDETAIL_CODE.equals(fileCode)) {
            //已签门店
            instance = new ExcelForSign();
        } else if (ReportConstant.NOSIGNDETAIL_CODE.equals(fileCode)) {
            //未签门店
            instance = new ExcelForNoSign();
        } else if (ReportConstant.FOLLOWDETAIL_CODE.equals(fileCode)) {
            //跟进明细
            instance = new ExcelForFollowDetail();
        } else if (ReportConstant.LINKDETAIL_CODE.equals(fileCode)) {
            //联动明细
            instance = new ExcelForLinkDetail();
        } else if (ReportConstant.LINKPROJECTDETAIL_CODE.equals(fileCode)) {
            //联动项目明细
            instance = new ExcelForLinkProjectDetail();
        } else if (ReportConstant.STORESTRUCTURE_CODE.equals(fileCode)) {
            //门店结构
            instance = new ExcelForStoreStructure();
        } else if (ReportConstant.ACHIEVECHANGE_CODE.equals(fileCode)) {
            //业绩变更
            instance = new ExcelForAchieveChange();
        } else if (ReportConstant.EXCELFORPERFORMANCEQUERY_CODE.equals(fileCode)) {
            //业绩查询
            instance = new ExcelForPerformanceQuery();
        } else if (ReportConstant.LINKPROJECTTRACE_CODE.equals(fileCode)) {
            //联动项目跟踪
            instance = new ExcelForLinkProjectTrace();
        } else if (ReportConstant.STOREDEPOSITDEATIL_CODE.equals(fileCode)) {
            //保证金明细表
            instance = new ExcelForStoreDepositDeatil();
        } else if (ReportConstant.STOREDEPOSITSERIAL_CODE.equals(fileCode)) {
            //保证金明细表
            instance = new ExcelForStoreDepositSerial();
        } else if (ReportConstant.LNKACHIEVEMENTSUM_CODE.equals(fileCode)) {
            //联动业绩表
            instance = new ExcelForLnkAchievementSum(reqMap.get("startDate").toString(), reqMap.get("endDate").toString());
        } else if (ReportConstant.STOREPRESERVESUMMARY_CODE.equals(fileCode)) {
            instance = new ExcelStorePreserveSummary(reqMap.get("quarterIds").toString());
        } else if (ReportConstant.STOREINFORMATIONDETAIL_CODE.equals(fileCode)) {
            //门店信息明细表
            instance = new ExcelForStoreInformationDetail();
        } else if (ReportConstant.COMPANYINFODETAIL_CODE.equals(fileCode)) {
            //公司信息明细表
            instance = new ExcelForCompanyInfoDetail();
        } else if (ReportConstant.MEMBERSHIP_CODE.equals(fileCode)) {
            // 公盘会员明细表
            instance = new ExcelForMembershipDetail();
        } else if (ReportConstant.KEFUORDER_CODE.equals(fileCode)) {
            // 客服工单
            instance = new ExcelForKeFuOrder();
        } else if (ReportConstant.BATCHSALE_CODE.equals(fileCode)) {
            // 批量成销模板
            instance = new ExcelForBatchSale();
        } else if (ReportConstant.KEFUWJ_CODE.equals(fileCode)) {
            // 问卷调查
            instance = new ExcelForKefuWj();
        } else if (ReportConstant.KEFUWJANALYSE_CODE.equals(fileCode)) {
            // 问卷分析
            instance = new ExcelForKefuWjAnalyse(reqMap.get("wjCode").toString());
        } else if (ReportConstant.LINKCONVERSIONRATE_CODE.equals(fileCode)) {
            //联动转化率分析
            instance = new ExcelForLinkConversionRate(reqMap.get("yearly").toString());
        } else if (ReportConstant.KEFUWJEVALUATION_CODE.equals(fileCode)) {
            //门店测评
            instance = new ExcelForKefuWjEvaluation();
        } else if (ReportConstant.BATCHREBACK_CODE.equals(fileCode)) {
            // 批量退房模板
            instance = new ExcelForBatchReback();
        }else if (ReportConstant.PROJECTDETAIL_CODE.equals(fileCode)) {
            //开发项目明细
            instance = new ExcelForProjectDetail();
        }

        String pathName = url + File.separator + time + File.separator + fileName;

        try {
            if (ReportConstant.PERFORMANCESUM_CODE.equals(fileCode)) {
                //中心汇总
                instance.downloadSheet(contentList.get(0), new File(pathName));

            } else if (ReportConstant.EXCELFORPERFORMANCEQUERY_CODE.equals(fileCode)) {
                String sumBy = "center";
                if (reqMap.get("sumBy") != null)
                    sumBy = reqMap.get("sumBy").toString();
                //业绩查询
                ((ExcelForPerformanceQuery) instance).downloadSheet(contentList, new File(pathName), sumBy);

            } else if (ReportConstant.LINKDETAIL_CODE.equals(fileCode)) {
                String yearly = "2018";
                if (reqMap.get("organization") != null)
                    yearly = reqMap.get("organization").toString();
                //业绩查询
                ((ExcelForLinkDetail) instance).downloadSheet(contentList, new File(pathName), yearly);

            } else if (ReportConstant.LINKPROJECTDETAIL_CODE.equals(fileCode)) {
                String searchType = (String) reqMap.get("searchType");
                ((ExcelForLinkProjectDetail) instance).downloadSheet(contentList, new File(pathName), searchType);
            } else {
                instance.downloadSheet(contentList, new File(pathName));

            }

            super.setExportSuccess((String) reqMap.get("cookieName"), response);
            super.download(request, response, pathName, fileName);

        } catch (IOException e) {

            response.setCharacterEncoding("GBK");
            response.getWriter().write("下载Excel失败" + e.getMessage());
            response.getWriter().close();
            logger.error("下载Excel失败", e);

        }
    }

    @Async("threadPoolTaskExecutorExpend")
    public void downLoadExcelAjax(HttpServletRequest request, UserInfo userInfo, HttpServletResponse response, List<LinkedHashMap<String, Object>> contentList, Map<String, Object> reqMap, String fileCode, String fileName, int id) throws Exception {
        try {
            ResultData<?> reback = expendReportService.exportExpandDetailList(reqMap);
            contentList = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            if (contentList == null) {
                contentList = new ArrayList<LinkedHashMap<String, Object>>();
            }
            String ctxPath = request.getSession().getServletContext().getRealPath("/");
            String url = ctxPath + "data" + File.separator + fileCode;

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
            instance = new ExcelForExpend();
            String pathName = url + File.separator + time + File.separator + fileName;
            instance.downloadSheet(contentList, new File(pathName));

            ReportQueueAjax reportDb = new ReportQueueAjax();
            reportDb.setDelFlag("N");
            reportDb.setFilePath(pathName);
            reportDb.setId(id);
            reportQueueService.uptReportQueueAjax(reportDb);


        } catch (IOException e) {

            logger.error("下载Excel失败", e);

        } finally {
            //减
            reportQueueService.updateReportQueueTotal("exReport_filepath", 2);
        }
    }

}
