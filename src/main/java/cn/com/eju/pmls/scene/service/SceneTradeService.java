package cn.com.eju.pmls.scene.service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.util.BuildingNoUtil;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.accountproject.AccountProjectList;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import cn.com.eju.deal.dto.houseLinkage.report.ReportDto;
import cn.com.eju.deal.dto.houseLinkage.report.ReportInsertDto;
import cn.com.eju.deal.houseLinkage.estate.dto.City;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("sceneTradeService")
public class SceneTradeService extends BaseService {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    /*
     * 获取公司下门店数据
     */
    public ResultData<?> getStoreListByCompanyId(Map<String, Object> reqMap)
            throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "store" + "/qr/{param}";
        ResultData<?> backResult = get(url, reqMap);
        return backResult;
    }

    /*
        获取城市数据
     */
    public ResultData<?> queryCityList() throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "citycascade" + "/queryCityListByIsLnkService";
        ResultData<?> reback = get(url);
        return reback;
    }

    /*
        获取城市数据
    */
    public ResultData<?> queryDistrictListByCityNo(String cityNo) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "linkages" + "/district/{cityNo}";
        ResultData<?> reback = get(url, cityNo);
        return reback;
    }

    /*
        获取归属项目部
    */
    public ResultData<?> queryProjectDepartment(Map<String, Object> reqMap) throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/getProjectDepartment/{param}";
        ResultData<?> backResult = get(url, reqMap);
        return backResult;
    }


    /**
     * 查询归属城市下的归属中心
     */
    public ResultData<?> queryAchivCenter(String cityNo)
            throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "qtReport" + "/getAchivAchievementLevelSettingList/{cityNo}";

        ResultData<?> backResult = get(url, cityNo);

        return backResult;
    }

    public ResultData<?> getAccountProjectByCityNo(String cityNo) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/getAccountProject/{cityNo}";
        ResultData<?> backResult = get(url, cityNo);
        return backResult;
    }

    /**
     * 获取联动核算主体
     *
     * @throws Exception
     */
    public ResultData<?> getOALnkAccountProjectList(String projectNo) throws Exception { //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/getOALnkAccountProjectList/{projectNo}";
        ResultData<?> backResult = get(url, projectNo);
        return backResult;
    }

    /*
        查询项目列表数据
     */
    public ResultData<?> qSceneEstate(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "sceneEstate" + "/qSceneEstate/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;

    }

    /*
         查询项目列表数据
      */
    public ResultData<?> getByEstateId(String estateId)
            throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "sceneEstate" + "/estateId/{estateId}";

        ResultData<?> backResult = get(url, estateId);

        return backResult;
    }


    /*
        取得报备详情信息
     */
    public ResultData<?> getOperDetail(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/getOperDetail/{param}";
        ResultData<?> backResult = get(url, reqMap);
        return backResult;
    }

    public ResultData<?> getCitySettingByCityNo(String cityNo) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "commons" + "/qtemplatecode/{cityNo}";
        ResultData<?> backResult = get(url, cityNo);
        return backResult;
    }

    /*
        查询项目报备列表数据
     */
    public ResultData<?> qSceneRecognition(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        String uuId = UUID.randomUUID().toString();
        //调用 接口
        logger.info("查询项目报备列表数据-" + uuId + "-qSceneRecognition-start, " + reqMap.toString());
        String url = SystemParam.getWebConfigValue("RestServer") + "sceneEstate" + "/qSceneRecognition/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        logger.info("查询项目报备列表数据-" + uuId + "-qSceneRecognition-end ");
        return reback;

    }


    /*
     查询订单列表
     */
    public ResultData<?> queryReportList(Map<String, Object> reqMap, PageInfo pageInfo)
            throws Exception {

        String uuId = UUID.randomUUID().toString();
        logger.info("查询订单列表-" + uuId + "-queryReportList-start, " + reqMap.toString());
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/q/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);
        logger.info("查询订单列表-" + uuId + "-queryReportList-end ");
        return reback;

    }

    /*
        查询客户信息列表数据
    */
    public ResultData<?> queryCustomLegList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "sceneTrade" + "/getCustomLegList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;
    }

    public ResultData<?> queryCustomDetList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "sceneTrade" + "/getCustomDetList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;
    }

    /*
      取得报备详情
     */
    public ResultData<?> getByReportId(String reportId)
            throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/{id}";

        ResultData<?> backResult = get(url, reportId);

        return backResult;
    }

    /*
      取得报备进度信息
     */
    public ResultData<?> queryReportProgress(String reportId)
            throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "achieveChangeLog" + "/getList/{reportId}";

        ResultData<?> backResult = get(url, reportId);

        return backResult;
    }

    /*
        取得报备日志信息
     */
    public ResultData<?> queryReportlog(String reportId)
            throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "achieveChangeLog" + "/getList/{reportId}";

        ResultData<?> reback = get(url, reportId);

        return reback;
    }

    /*
      提交无效、带看、大定、成销
     */
    public ResultData<?> sceneRecognitionConfirm(Map<String, Object> reqMap)
            throws Exception {

        reqMap = BuildingNoUtil.parse(reqMap);
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "sceneEstate" + "/qSceneRecognition/confirm";

        ResultData<?> reback = post(url, reqMap);

        return reback;

    }

    /*
      加入批量成销
     */
    public ResultData<?> addBatchSale(Map<String, Object> map) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "batchSaleController" + "/addBatchSaleDetail";
        ResultData<?> resultData = post(url, map);
        return resultData;
    }

    public ResultData getUserMappingAccountProject(String userCode) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "frameContract" + "/getUserMappingAccountProject/{userCode}";
        ResultData<?> backResult = get(url, userCode);
        return backResult;
    }

    /*
      批量成销总套数
     */
    public ResultData<?> countBatchSaleDetailAll(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "batchSaleController" + "/countBatchSaleDetail";
        ResultData<?> resultData = post(url, reqMap);
        return resultData;
    }

    public ResultData<?> countBatchRebackDetail(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "batchRebackController" + "/countBatchRebackDetail";
        ResultData<?> resultData = post(url, reqMap);
        return resultData;
    }

    public ResultData<?> saveReport(Map<String, Object> reqMap) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "APIHouseLink" + "/insertReportBack2";
        ReportInsertDto reportInsertDto = this.convertDto(reqMap);
        ResultData<?> resultData = post(url, reportInsertDto);
        return resultData;
    }


    /**
     * 转换参数数据
     */
    private ReportInsertDto convertDto(Map<String, Object> reqMap) {
        ReportInsertDto reportInsertDto = new ReportInsertDto();

        String estateId = (String) reqMap.get("estateId");
        String estateNm = (String) reqMap.get("estateNm");
        String customerName = (String) reqMap.get("customerName");
        String customerPhone = (String) reqMap.get("customerPhone");
        String customerNum = (String) reqMap.get("customerNum");
        String validRelationDate = (String) reqMap.get("validRelationDate");
        String reportDate = (String) reqMap.get("reportDate");


        if (StringUtil.isNotEmpty(validRelationDate)) {
            validRelationDate = validRelationDate + " 00:00:00";
        }
        if (StringUtil.isNotEmpty(reportDate)) {
            reportDate = reportDate + " 00:00:00";
        }
//        if (StringUtil.isNotEmpty(validRelationDate))
//        {
//            validRelationDate = validRelationDate.replace("T", " ");
//            validRelationDate = validRelationDate + ":01";//需要到秒才能存
//        }
        String customerRequire = (String) reqMap.get("customerRequire");
        String memo = (String) reqMap.get("memo");


        reportInsertDto.setCityId((String) reqMap.get("cityNo"));
        reportInsertDto.setCompanyId((String) reqMap.get("reportCompanyId"));
        reportInsertDto.setCompanyName((String) reqMap.get("reportCompanyNm"));
        if (!reqMap.containsKey("branchId") || StringUtil.isEmpty((String) reqMap.get("branchId"))) {
            reportInsertDto.setBranchId(null);
        } else {
            reportInsertDto.setBranchId(Integer.parseInt((String) reqMap.get("branchId")));
        }

        if (reqMap.containsKey("estateCenterId") || StringUtil.isNotEmpty((String) reqMap.get("estateCenterId"))) {
            reportInsertDto.setEstateCenterId(Integer.parseInt((String) reqMap.get("estateCenterId")));
        }

        reportInsertDto.setCreateId((int) reqMap.get("userId"));
        reportInsertDto.setEmployeeId((String) reqMap.get("userCode"));
        reportInsertDto.setEmployeeName((String) reqMap.get("userName"));


        String storeId = (String) reqMap.get("storeId");
        String storeName = (String) reqMap.get("storeName");
        String storeCityNo = (String) reqMap.get("storeCityNo");

        reportInsertDto.setStoreId(storeId);
        reportInsertDto.setStoreName(storeName);
        reportInsertDto.setStoreCityNo(storeCityNo);

        //附件信息
        if (reqMap.get("fileRecordMainIds") != null && !"".equals(reqMap.get("fileRecordMainIds").toString())) {
            String[] fileIdArr = reqMap.get("fileRecordMainIds").toString().split(",");
            FileRecordMainDto fileRecordMainDto;
            List<FileRecordMainDto> list = new ArrayList<>();

            for (int i = 0, l = fileIdArr.length; i < l; i++) {
                fileRecordMainDto = new FileRecordMainDto();
                fileRecordMainDto.setFileRecordMainId(Integer.valueOf(fileIdArr[i]));
                list.add(fileRecordMainDto);
            }

            reportInsertDto.setFileList(list);
        }

        reportInsertDto.setCustomerId(RandomStringUtils.randomNumeric(10));
        reportInsertDto.setCustomerName(customerName);
        reportInsertDto.setCustomerPhone(customerPhone);
        reportInsertDto.setCustomerNum(customerNum);
        reportInsertDto.setCustomerRequire(customerRequire);
        reportInsertDto.setEstateId(estateId);
        reportInsertDto.setEstateName(estateNm);
        reportInsertDto.setMemo(memo);
        reportInsertDto.setSource("web");
        reportInsertDto.setValidRelationDate(validRelationDate);
        reportInsertDto.setReportDate(reportDate);

        //重新设置业绩归属
        reportInsertDto.setContactId((String) reqMap.get("contactId"));
        reportInsertDto.setContactNm((String) reqMap.get("contactNm"));
        //设置业绩归属中心
        if (reqMap.containsKey("centerGroupId")) {
            String centerGroupId = (String) reqMap.get("centerGroupId");
            String centerGroupName = (String) reqMap.get("centerGroupName");
            reportInsertDto.setCenterGroupId(Integer.valueOf(centerGroupId));
            reportInsertDto.setCenterGroupName(centerGroupName);
        }
        String reportAgent = (String) reqMap.get("reportAgent");
        String reportAgentTel = (String) reqMap.get("reportAgentTel");
        String customerNmTwo = (String) reqMap.get("customerNmTwo");
        String customerTelTwo = (String) reqMap.get("customerTelTwo");
        reportInsertDto.setReportAgent(reportAgent);
        reportInsertDto.setReportAgentTel(reportAgentTel);
        reportInsertDto.setCustomerIdTwo(RandomStringUtils.randomNumeric(10));
        reportInsertDto.setCustomerNmTwo(customerNmTwo);
        reportInsertDto.setCustomerTelTwo(customerTelTwo);
        return reportInsertDto;
    }

    public ResultData<?> saveRoughtAudit(ReportDto reportDto)
            throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "";

        ResultData<?> backResult = post(url, reportDto);

        return backResult;
    }

    public void updateDetailRoughDate(ReportDto reportDto) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/updateDetailRoughDate";
        put(url, reportDto);
    }

    /**
     * 添加返佣对象表记录
     *
     * @param reportDto
     * @throws Exception
     */
    public void insertYjReport(ReportDto reportDto) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/insertYjReport";
        put(url, reportDto);
    }

    public ResultData<?> operDetailUpdate(Map<String, Object> reqMap) throws Exception {
        reqMap = BuildingNoUtil.parse(reqMap);
        //String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/operDetailUpdate/{param}";
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/operDetailUpdate";
        ResultData<?> backResult = post(url, reqMap);
        return backResult;
    }

    public ResultData<?> getReport(String estateId, String companyId, String customerId)
            throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/{estateId}/{companyId}/{customerId}";

        ResultData<?> backResult = get(url, estateId, companyId, customerId);

        return backResult;
    }


    /**
     * 退回带看已确认的状态
     */
    public ResultData<?> updateReback(Map<String, Object> reqMap) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "report/reback";

        ResultData<?> reback = post(url, reqMap);
        return reback;

    }

    /**
     * desc:业务节点-查看
     * 2019年10月17日
     */
    public ResultData<?> getFyData(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/getFyData/{param}";
        ResultData<?> backResult = get(url, reqMap);
        return backResult;
    }

    /**
     * (获取关账信息)
     *
     * @return
     */
    public ResultData<?> getSwitchInfo(String cityNo) throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "pmlsCommon" + "/getCitySwitchMonth/{cityNo}";

        ResultData<?> backResult = get(url, cityNo);

        return backResult;
    }

    /**
     * 取得返佣对象列表
     */
    public ResultData<?> getLnkYjReportList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "lnkYjReport" + "/getLnkYjReportList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;
    }

    public ResultData<?> getLinkUser(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "users" + "/link/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }

    /**
     * 保存客户调整信息
     *
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData saveCustomerInfoAdjut(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/saveCustomerInfoAdjut";
        ResultData<?> reback = post(url, reqMap);
        return reback;

    }

    /**
     * 保存联动业绩调整信息
     *
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData saveAchievementAdjut(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/saveAchievementAdjut";
        ResultData<?> reback = post(url, reqMap);
        return reback;
    }

    /**
     * 获取业绩人的中心
     *
     * @throws Exception
     */
    public ResultData<?> getLinkUserCenter(String userCode)
            throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/getLinkUserCenter/{userCode}";
        ResultData<?> backResult = get(url, userCode);
        return backResult;
    }

    /**
     * 模糊查询联动业绩人员
     */
    public ResultData<?> getLinkUserByCondition(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "users" + "/getLinkUserByCondition/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }


    public ResultData unlockReback(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/unlockReback";
        ResultData<?> backResult = post(url, reqMap);
        return backResult;
    }

    public ResultData<?> getBatchSale(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "batchSaleController" + "/getBatchSale";
        ResultData<?> resultData = post(url, reqMap);
        return resultData;
    }

    /**
     * 批量成销列表
     */
    public ResultData<?> getBatchSaleDetailList(Integer batchId) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "batchSaleController" + "/getBatchSaleDetailList/" + batchId;
        ResultData<?> resultData = get(url);
        return resultData;
    }

    public ResultData<?> checkAccountProject(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "batchSaleController" + "/checkAccountProject";
        ResultData<?> resultData = post(url, reqMap);
        return resultData;
    }

    public ResultData<?> updateBatchSaleDetailAll(Map<String, Object> repMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "batchSaleController" + "/updateBatchSaleDetailAll";
        ResultData<?> resultData = post(url, repMap);
        return resultData;
    }

    public ResultData<?> submitBatchSaleAll(Map<String, Object> repMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "batchSaleController" + "/submitBatchSaleAll";
        ResultData<?> resultData = post(url, repMap);
        return resultData;
    }

    public ResultData<?> deleteBatchSaleDetailById(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "batchSaleController" + "/deleteBatchSaleDetailById";
        ResultData<?> resultData = post(url, reqMap);
        return resultData;
    }

    public ResultData<List<City>> getAreaCity(Map<String, Object> map) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "yfCenterUser" + "/getAreaCity/{param}";

        ResultData<List<City>> backResult = get(url, map);
        return backResult;
    }

    public ResultData<List<AccountProjectList>> getAccountProjectList(Map<String, Object> map) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "accountProject" + "/getAccountProjectList/{param}";

        ResultData<List<AccountProjectList>> backResult = get(url, map);

        return backResult;
    }

    public ResultData<?> accountProjectList(Map<String, Object> reqMap, PageInfo pageInfo)
            throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "accountProject" + "/q/{param}";
//        putCenterIds(reqMap);
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }


    public ResultData<?> deleteAccountProject(Map<String, Object> map) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "accountProject" + "/delete";
        ResultData<?> backResult = post(url, map);
        return backResult;
    }

    public ResultData<?> saveAccountProject(Map<String, Object> map) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "accountProject" + "/save";
        putAccountProjectNos(map);
        ResultData<?> backResult = post(url, map);

        return backResult;
    }

    public ResultData<?> getAccountProjectById(int id)
            throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "accountProject" + "/{id}";

        ResultData<?> backResult = get(url, id);

        return backResult;
    }

    public ResultData<?> updateAccountProject(Map<String, Object> map) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "accountProject" + "/update";
        putAccountProjectNos(map);
        ResultData<?> backResult = post(url, map);
        return backResult;

    }

    private void putAccountProjectNos(Map<String, Object> reqMap) {
        if (reqMap.containsKey("accountProjectNos") && StringUtil.isNotEmpty((String) reqMap.get("accountProjectNos"))) {
            String accountProjectNos = (String) reqMap.get("accountProjectNos");
//        	String[] accountProjectNoArr = accountProjectNos.split(";");
            String[] accountProjectNoArr = accountProjectNos.split(",");
            reqMap.put("accountProjectNos", Arrays.asList(accountProjectNoArr));
        }
    }

/*************************************批量退房**************************************/
    /**
     * 添加批量退房
     *
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData<?> addBatchReback(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "batchRebackController" + "/addBatchReback";
        ResultData<?> resultData = post(url, reqMap);
        return resultData;
    }

    /**
     * 批量成退房列表
     *
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData<?> getBatchRebackList(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "batchRebackController" + "/getBatchRebackList";
        ResultData<?> resultData = post(url, reqMap);
        return resultData;
    }

    /**
     * 删除批量退房子表信息
     *
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData<?> deleteBatchRebackDetailById(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "batchRebackController" + "/deleteBatchRebackDetailById";
        ResultData<?> resultData = post(url, reqMap);
        return resultData;
    }

    public ResultData<?> updateBatchRebackDetailAll(Map<String, Object> repMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "batchRebackController" + "/updateBatchRebackDetailAll";
        ResultData<?> resultData = post(url, repMap);
        return resultData;
    }

    public ResultData<?> submitBatchReback(Map<String, Object> repMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "batchRebackController" + "/submitBatchReback";
        ResultData<?> resultData = post(url, repMap);
        return resultData;
    }

    public ResultData<?> checkSjfy(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "sceneEstate" + "/checkSjfy";
        ResultData<?> resultData = post(url, reqMap);
        return resultData;
    }

    /*************************************批量退房**************************************/


    public ResultData<?> checkReportCooperation(String reportId)
            throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "cooperationController" + "/checkReportCooperation/{reportId}";

        ResultData<?> backResult = get(url, reportId);

        return backResult;
    }

    /**
     * 获取项目发布城市
     */
    public ResultData<?> getProjectCityNoList(Map<String, Object> repMap)
            throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/getProjectCityNoList";

        ResultData<?> backResult = post(url, repMap);

        return backResult;
    }


    public ResultData<?> selectBuildingNoRepeatCount(String buildingNo, String reportId)
            throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/selectBuildingNoRepeatCount";
        ReportDto reportDto = new ReportDto();
        reportDto.setBuildingNo(buildingNo);
        reportDto.setReportId(reportId);
        ResultData<?> backResult = post(url, reportDto);

        return backResult;
    }

    public ResultData<?> queryIncomeplanList(Map<String, Object> reqMap) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "lnkEstateIncomeplan" + "/queryIncomeplanList/{param}";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }

    public ResultData<?> getYHApproveCheckArteryType(Integer id) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/getYHApproveCheckArteryType/{id}";
        ResultData<?> backResult = get(url, id);
        return backResult;
    }


    public ResultData<?> querylnkYjWyInfo() throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "lnkYjWyInfo" + "/list";
        ResultData<?> reback = get(url);
        return reback;
    }

    public ResultData<?> queryCntYjPlanList(Map<String, Object> map) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "lnkYjPlan" + "/queryCntYjPlanList/{param}";
        ResultData<?> reback = get(url, map);
        return reback;
    }


    public ResultData<?> getYJtableList(Map<String, Object> map) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/getYJtableList";
        ResultData<?> reback = post(url, map);
        return reback;
    }
    public ResultData<?> getStatistcsBrokerage(Map<String, Object> map) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/getStatistcsBrokerage";
        ResultData<?> reback = post(url, map);
        return reback;
    }


    public ResultData uptPreBack(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/uptPreBack";
        ResultData<?> backResult = post(url, reqMap);
        return backResult;
    }

    public ResultData getReport(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/getReport";
        ResultData<?> backResult = post(url, reqMap);
        return backResult;
    }
    
    /**
     * desc:大定审核-获取垫佣控制规则
     * 2020年5月20日
     */
    public ResultData<?> getNewDyRatio(Map<String, Object> reqMap)throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/getNewDyRatio";
        ResultData<?> backResult = post(url, reqMap);
        return backResult;
    }

    /**
     * desc:获取项目对应收入类合同得核算主体
     * 2020年7月1日
     */
    public ResultData<?> getAccountProjectByProjectNo(String projectNo) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/getAccountProjectByProjectNo/{projectNo}";
        ResultData<?> backResult = get(url, projectNo);
        return backResult;
    }
}
