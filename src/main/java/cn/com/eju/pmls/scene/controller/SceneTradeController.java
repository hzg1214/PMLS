package cn.com.eju.pmls.scene.controller;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.base.dto.code.CommonCodeDto;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.base.util.HttpClientUtil;
import cn.com.eju.deal.common.util.DateUtils;
import cn.com.eju.deal.common.util.FileUtils;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.dto.accountproject.AccountProjectList;
import cn.com.eju.deal.dto.houseLinkage.report.ReportDto;
import cn.com.eju.deal.dto.houseLinkage.report.ReportInfoDto;
import cn.com.eju.deal.houseLinkage.estate.dto.City;
import cn.com.eju.deal.houseLinkage.linkAchieveChange.service.AchieveChangeLogService;
import cn.com.eju.deal.scene.estate.dto.BatchSaleDetail;
import cn.com.eju.pmls.channelBusiness.service.BusinessManagerService;
import cn.com.eju.pmls.cooperation.service.CooperationService;
import cn.com.eju.pmls.poi.ExcelForBatchSale;
import cn.com.eju.pmls.poi.common.ExcelUseXmlAbstract;
import cn.com.eju.pmls.scene.dto.RoughData;
import cn.com.eju.pmls.scene.dto.YFRoughData;
import cn.com.eju.pmls.scene.service.SceneExpentService;
import cn.com.eju.pmls.scene.service.SceneTradeService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STUnsignedShortHex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "sceneTrade")
public class SceneTradeController extends BaseReportController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    SceneTradeService sceneTradeService;

    @Autowired
    SceneExpentService sceneExpentService;

    @Autowired
    BusinessManagerService businessManagerService;

    @Resource(name = "achieveChangeLogService")
    private AchieveChangeLogService achieveChangeLogService;

    @Resource(name = "cooperationService")
    private CooperationService cooperationService;


    //region 画面

    /*
     案场管理-项目列表初始化画面
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView projectList() {
        ModelAndView mv = new ModelAndView("scene/sceneTrade/projectList");
        return mv;
    }

    /*
        案场管理-项目详情初始化画面
     */
    @RequestMapping(value = "projectDetail/{estateId}", method = RequestMethod.GET)
    public ModelAndView projectDetailList(@PathVariable("estateId") String estateId, ModelMap mop, HttpServletRequest request) {

        Integer userId = UserInfoHolder.getUserId();
        String userCode = UserInfoHolder.get().getUserCode();
        String cityNo = UserInfoHolder.get().getCityNo();
        try {

            logger.info("获取楼盘详情信息,estateId:", estateId);
            ResultData<?> resultData = sceneTradeService.getByEstateId(estateId);
            logger.info("获取楼盘详情信息,estateId:" + estateId, resultData);


            mop.addAttribute("estate", resultData.getReturnData());

        } catch (Exception ex) {
            logger.error("获取楼盘详情信息异常,estateId:" + estateId, ex);
            logger.error("sceneTrade", "SceneTradeController",
                    "projectDetail", estateId, null, "", "", ex);
        }

        String oaOperatorStr = "1";
        //查询登陆人
        ResultData usercodeResult = null;
        try {
            usercodeResult = sceneTradeService.getUserMappingAccountProject(UserInfoHolder.get().getUserCode());
            if (usercodeResult.getReturnData() == null) {
                oaOperatorStr = "0";
            }
        } catch (Exception e) {
            logger.error("sceneTrade", "SceneTradeController", "qSceneRecognition/{estateId}", "", UserInfoHolder.getUserId(), "", "oaOperatorStr", e);
        }
        mop.addAttribute("oaOperatorStr", oaOperatorStr);

        try {
            Map<String, Object> queryParam = new HashMap<>();
            queryParam.put("estateId", estateId);
            queryParam.put("userCode", userCode);
            ResultData<?> expent = sceneExpentService.selectCashBill(queryParam);
            if ("200".equals(expent.getReturnCode()) && expent.getTotalCount() != null) {
                mop.put("expentCount", expent.getTotalCount());
            }

            ResultData<?> batchSale = sceneTradeService.countBatchSaleDetailAll(queryParam);
            if ("200".equals(batchSale.getReturnCode()) && batchSale.getReturnData() != null) {
                mop.put("saleCount", batchSale.getReturnData());
            }

            //批量退房套数
            ResultData<?> batchReback = sceneTradeService.countBatchRebackDetail(queryParam);
            if ("200".equals(batchReback.getReturnCode()) && batchReback.getReturnData() != null) {
                mop.put("rebackCount", batchReback.getReturnData());
            }


        } catch (Exception ex) {
            logger.error("sceneTrade", "SceneTradeController",
                    "projectDetail", estateId, userId, "", "查询批量套数异常", ex);

        }


        ModelAndView mv = new ModelAndView("scene/sceneTrade/projectDetailList");
        return mv;
    }

    /*
        案场管理-报备列表画面
    */
    @RequestMapping(value = "report", method = RequestMethod.GET)
    public ModelAndView reportList(ModelMap mop, HttpServletRequest request) {

        Map<String, Object> map = bindParamToMap(request);
        if (map.containsKey("backTab")) {
            mop.addAttribute("backTab", (String) map.get("backTab"));
        }

        ModelAndView mv = new ModelAndView("scene/sceneTrade/reportList");
        return mv;
    }

    /*
        案场管理-大定审核列表画面
    */
    @RequestMapping(value = "rought", method = RequestMethod.GET)
    public ModelAndView roughtList(ModelMap mop, HttpServletRequest request) {

        Map<String, Object> map = bindParamToMap(request);
        if (map.containsKey("backTab")) {
            mop.addAttribute("backTab", (String) map.get("backTab"));
        }

        ModelAndView mv = new ModelAndView("scene/sceneTrade/roughtList");
        return mv;
    }

    /*
       案场管理-客户信息列表画面
    */
    @RequestMapping(value = "custom", method = RequestMethod.GET)
    public ModelAndView customList() {
        ModelAndView mv = new ModelAndView("scene/sceneTrade/customList");
        return mv;
    }

    /*
        案场管理-客户详情
    */
    @RequestMapping(value = "customDetail/{customerTel}/{customerNm}/{registerCnt}/{succSaleCnt}/{vaild}", method = RequestMethod.GET)
    public ModelAndView customDetail(@PathVariable("customerTel") String customerTel
            , @PathVariable("customerNm") String customerNm
            , @PathVariable("registerCnt") String registerCnt
            , @PathVariable("succSaleCnt") String succSaleCnt
            , @PathVariable("vaild") String vaild
            , ModelMap mop, HttpServletRequest request) {
        try {
            mop.addAttribute("customerTel", customerTel);
            mop.addAttribute("customerNm", customerNm);
            mop.addAttribute("registerCnt", registerCnt);
            mop.addAttribute("succSaleCnt", succSaleCnt);
            mop.addAttribute("vaild", vaild);

        } catch (Exception ex) {
            logger.error("sceneTrade", "SceneTradeController", "customDetail", "", null, "", "客户详情 ", ex);
        }

        ModelAndView mv = new ModelAndView("scene/sceneTrade/customDetail");
        return mv;
    }

    /*
        案场管理-新增报备
     */
    @RequestMapping(value = "reportAdd/{estateId}", method = RequestMethod.GET)
    public ModelAndView reportAdd(@PathVariable("estateId") String estateId, ModelMap mop, HttpServletRequest request) {

        try {

            logger.info("新增报备-获取楼盘详情信息,estateId:", estateId);
            ResultData<?> resultData = sceneTradeService.getByEstateId(estateId);
            logger.info("新增报备-获取楼盘详情信息,estateId:" + estateId, resultData);

            mop.addAttribute("info", resultData.getReturnData());

            if (resultData != null) {
                Map<String, Object> returnDataMap = (Map<String, Object>) resultData.getReturnData();
                if (returnDataMap != null && returnDataMap.get("cityNo") != null) {
                    String cityNo = returnDataMap.get("cityNo").toString();
                    ResultData<?> resultDataSwitch = sceneTradeService.getSwitchInfo(cityNo);
                    Map<String, Object> switchMap = (Map<String, Object>) resultDataSwitch.getReturnData();
                    if (switchMap != null && switchMap.get("year") != null && switchMap.get("month") != null) {
                        String switchDate = switchMap.get("year").toString() + "-" + switchMap.get("month").toString() + "-01";
                        mop.addAttribute("switchDate", addOneMonth(switchDate));
                    }
                }
            }

        } catch (Exception ex) {
            logger.error("获取楼盘详情信息异常,estateId:" + estateId, ex);
            logger.error("sceneTrade", "SceneTradeController",
                    "projectDetail", estateId, null, "", "", ex);
        }


        ModelAndView mv = new ModelAndView("scene/sceneTrade/reportAdd");

        return mv;
    }

    /*
        案场管理-报备详情
    */
    @RequestMapping(value = "reportDetail/{estateId}/{companyId}/{customerId}/{relateId}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable("estateId") String estateId, @PathVariable("companyId") String companyId, @PathVariable("customerId") String customerId, @PathVariable("relateId") Integer relateId, ModelMap mop) {
        //返回map
        ResultData<?> resultData = new ResultData<ReportInfoDto>();
        ResultData<?> resultData1 = new ResultData<>();
        String dataSwitch = "0";
        try {
            resultData = sceneTradeService.getReport(estateId, companyId, customerId);

            ResultData<?> resultDataSwitch = null;
            if (resultData != null) {
                Map<String, Object> reportSearchResultDto = (Map<String, Object>) resultData.getReturnData();
                if (reportSearchResultDto != null && reportSearchResultDto.get("report") != null) {
                    Map<String, Object> reportMap = (Map<String, Object>) reportSearchResultDto.get("report");
                    if (reportMap != null && reportMap.get("cityNo") != null && reportMap.get("dealDate") != null) {
                        String cityNo = reportMap.get("cityNo").toString();
                        String dealDate = reportMap.get("dealDate").toString();
                        resultDataSwitch = sceneTradeService.getSwitchInfo(cityNo);
                        Map<String, Object> map = (Map<String, Object>) resultDataSwitch.getReturnData();
                        if (StringUtil.isNotEmpty(dealDate) && null != map.get("year") && null != map.get("month")) {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date yearMonthDate = new SimpleDateFormat("yyyy-MM-dd").parse(addOneMonth(map.get("year").toString() + "-" + map.get("month").toString() + "-01"));
                            Date dealDateDate = new SimpleDateFormat("yyyy-MM-dd").parse(dealDate);
                            if (dealDateDate.before(yearMonthDate)) {
                                dataSwitch = "1";
                            }
                        }
                    }
                    if (reportMap != null && reportMap.get("projectNo") != null && reportMap.get("companyId") != null) {
                        String projectNo = reportMap.get("projectNo").toString();
                        String companyNo = reportMap.get("companyId").toString();
                        //获取分销合同数据
                        ResultData cooperResult = new ResultData();
                        Map<String, Object> cooperReqMap = new HashMap<>();
                        try {
                            cooperReqMap.put("approveState", "10403");
                            cooperReqMap.put("projectNo", projectNo);
                            cooperReqMap.put("companyNo", companyNo);
                            cooperResult = cooperationService.getCooperationList(cooperReqMap, pageInfo);
                            if (cooperResult.getReturnData() != null && !CollectionUtils.isEmpty((Collection<?>) cooperResult.getReturnData())) {
                                mop.addAttribute("cooperFlag", "1");//有分销合同
                            } else {
                                mop.addAttribute("cooperFlag", "0");//没有分销合同
                            }
//                        	mop.addAttribute("auditDate","2020-03-31 23:59:59");
                            mop.addAttribute("auditDate", SystemParam.getWebConfigValue("auditDateEnd"));
                            mop.addAttribute("currDate", DateUtils.formatDate(new Date(), DateUtils.FORMAT_STR));
                            //配置时间
                            Date auditDate = DateUtils.parseDate(SystemParam.getWebConfigValue("auditDateEnd"), DateUtils.FORMAT_STR);
                            //判断当前时间是否在配置时间内(1:在         -1:不在)
                            mop.addAttribute("auditDateFlag", auditDate.compareTo(new Date()));
                        } catch (Exception e) {
                            resultData.setFail("获取分销合同数据异常!");
                            logger.error("sceneTrade", "SceneTradeController", "", null, null, "", "获取分销合同数据异常", e);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("sceneTrade", "SceneTradeController", "show", "", null, "", "查看 ", e);
        }

        try {
            resultData1 = achieveChangeLogService.getLogList(relateId);
            if (resultData1 != null) {
                mop.addAttribute("LogInfo", JSONObject.toJSON(resultData1.getReturnData()));
            }
        } catch (Exception e) {
            logger.error("sceneTrade", "SceneTradeController", "show", "", null, "", "查询业绩调整日志异常", e);
        }

        if (resultData != null) {
            Map<String, Object> reportSearchResultDto = (Map<String, Object>) resultData.getReturnData();
            if (reportSearchResultDto != null && reportSearchResultDto.get("report") != null) {
                Map<String, Object> reportMap = (Map<String, Object>) reportSearchResultDto.get("report");
                if (reportMap != null && reportMap.get("reportId") != null) {
                    String reportId = reportMap.get("reportId").toString();
                    Map<String, Object> brokerageMap = new HashMap();
                    brokerageMap.put("reportId", reportId);
                    try {
                        logger.info("报备详情取得收入&返佣入参:", brokerageMap.toString());
                        ResultData<?>  brokerageResultData = sceneTradeService.getStatistcsBrokerage(brokerageMap);
                        logger.info("报备详情取得收入&返佣结果:", resultData.toString());

                        if (brokerageResultData != null) {
                            mop.addAttribute("brokerageInfo", JSONObject.toJSON(brokerageResultData.getReturnData()));
                        }

                    } catch (Exception e) {
                        logger.error("报备详情取得收入&返佣异常;input param: reqMap=" + brokerageMap.toString(), e);
                    }

                }
            }
        }

        //存放到mop中
        mop.addAttribute("userAcCityNo", UserInfoHolder.get().getCityNo());
        mop.addAttribute("dataSwitch", dataSwitch);
        mop.addAttribute("reportInfo", JSONObject.toJSON(resultData.getReturnData()));
        //弹窗用
        mop.addAttribute("estateId", estateId);
        mop.addAttribute("companyId", companyId);
        mop.addAttribute("customerId", customerId);
        mop.addAttribute("relateId", relateId);
        return new ModelAndView("scene/sceneTrade/reportDetailPage");
    }

    /**
     * desc:业务节点详情查询
     * 2019年11月13日
     */
    @RequestMapping("toOperDetail")
    public ModelAndView toOperDetail(HttpServletRequest request, ModelMap mop) {
        Map<String, Object> reqMap = bindParamToMap(request);
        String fyFlag = (String) reqMap.get("fyFlag");
        try {
            if ("1".equals(fyFlag) || "2".equals(fyFlag)) {//成销或结佣再去查询返佣对象
                ResultData<?> resultData1 = sceneTradeService.getFyData(reqMap);
                mop.put("fyData", JSONObject.toJSON(resultData1.getReturnData()));
            }
            ResultData<?> resultData = sceneTradeService.getOperDetail(reqMap);
            mop.put("info", JSONObject.toJSON(resultData.getReturnData()));
            mop.put("reqMap", reqMap);
        } catch (Exception e) {
            logger.error("sceneTrade", "SceneTradeController", "toOperDetail", "", null, "", "业务节点详情查询 ", e);
        }

        return new ModelAndView("scene/sceneTrade/reportView");
    }


    /*
        案场管理-新增带看
     */
    @RequestMapping(value = "watchAdd/{reportId}", method = RequestMethod.GET)
    public ModelAndView watchAdd(@PathVariable("reportId") String reportId, ModelMap mop, HttpServletRequest request) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("reportNo", reportId);
        paramMap.put("progress", 13502);
        paramMap.put("confirmStatus", 13603);
        try {

            logger.info("取得报备详情信息-带看,estateId:", paramMap);
            ResultData<?> resultData = sceneTradeService.getOperDetail(paramMap);
            logger.info("取得报备详情信息-带看,paramMap:" + paramMap, resultData);

            mop.addAttribute("info", resultData.getReturnData());

            if (resultData != null) {
                Map<String, Object> returnDataMap = (Map<String, Object>) resultData.getReturnData();
                if (returnDataMap != null && returnDataMap.get("cityNo") != null) {
                    String cityNo = returnDataMap.get("cityNo").toString();
                    ResultData<?> resultDataSwitch = sceneTradeService.getSwitchInfo(cityNo);
                    Map<String, Object> switchMap = (Map<String, Object>) resultDataSwitch.getReturnData();
                    if (switchMap != null && switchMap.get("year") != null && switchMap.get("month") != null) {
                        String switchDate = switchMap.get("year").toString() + "-" + switchMap.get("month").toString() + "-01";
                        mop.addAttribute("switchDate", addOneMonth(switchDate));
                    }
                }
            }

        } catch (Exception ex) {
            logger.error("获取楼盘详情信息异常,paramMap:" + paramMap, ex);
            logger.error("sceneTrade", "SceneTradeController",
                    "watchAdd", paramMap.toString(), null, "", "", ex);
        }
        ModelAndView mv = new ModelAndView("scene/sceneTrade/watchAdd");
        return mv;
    }


    /*
        案场管理-新增大定
     */
    @RequestMapping(value = "roughtAdd/{reportId}", method = RequestMethod.GET)
    public ModelAndView roughtAdd(@PathVariable("reportId") String reportId, ModelMap mop, HttpServletRequest request) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("reportNo", reportId);
        paramMap.put("progress", 13503);
        paramMap.put("confirmStatus", 13603);
        try {

            logger.info("取得报备详情信息-大定，estateId:", paramMap);
            ResultData<?> resultData = sceneTradeService.getOperDetail(paramMap);
            logger.info("取得报备详情信息-大定,paramMap:" + paramMap, resultData);

            mop.addAttribute("info", JSONObject.toJSON(resultData.getReturnData()));

            if (resultData != null) {
                Map<String, Object> returnDataMap = (Map<String, Object>) resultData.getReturnData();
                if (returnDataMap != null && returnDataMap.get("cityNo") != null) {
                    String cityNo = returnDataMap.get("cityNo").toString();
                    ResultData<?> resultDataSwitch = sceneTradeService.getSwitchInfo(cityNo);
                    Map<String, Object> switchMap = (Map<String, Object>) resultDataSwitch.getReturnData();
                    if (switchMap != null && switchMap.get("year") != null && switchMap.get("month") != null) {
                        String switchDate = switchMap.get("year").toString() + "-" + switchMap.get("month").toString() + "-01";
                        mop.addAttribute("switchDate", addOneMonth(switchDate));
                    }
                }
            }

        } catch (Exception ex) {
            logger.error("获取楼盘详情信息异常,paramMap:" + paramMap, ex);
            logger.error("sceneTrade", "SceneTradeController",
                    "roughtAdd", paramMap.toString(), null, "", "", ex);
        }

        try {
            ResultData<?> resultDataWYTypeInfo = sceneTradeService.querylnkYjWyInfo();
            mop.addAttribute("wyTypeList", resultDataWYTypeInfo.getReturnData());
        } catch (Exception e) {
            logger.error("查询-物业类型list异常", e);
        }

        ModelAndView mv = new ModelAndView("scene/sceneTrade/roughtAdd");
        return mv;

    }

    /*
     案场管理-大定修改
    */
    @RequestMapping(value = "roughtUpt/{reportId}", method = RequestMethod.GET)
    public ModelAndView roughtUpt(@PathVariable("reportId") String reportId, ModelMap mop, HttpServletRequest request) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("reportNo", reportId);
        paramMap.put("progress", 13504);
        paramMap.put("confirmStatus", 13601);
        try {

            logger.info("取得报备详情信息-大定修改，estateId:", paramMap);
            ResultData<?> resultData = sceneTradeService.getOperDetail(paramMap);
            logger.info("取得报备详情信息-大定修改,paramMap:" + paramMap, resultData);

            mop.addAttribute("info", JSONObject.toJSON(resultData.getReturnData()));

            if (resultData != null) {
                Map<String, Object> returnDataMap = (Map<String, Object>) resultData.getReturnData();
                if (returnDataMap != null && returnDataMap.get("cityNo") != null) {
                    String cityNo = returnDataMap.get("cityNo").toString();
                    ResultData<?> resultDataSwitch = sceneTradeService.getSwitchInfo(cityNo);
                    Map<String, Object> switchMap = (Map<String, Object>) resultDataSwitch.getReturnData();
                    if (switchMap != null && switchMap.get("year") != null && switchMap.get("month") != null) {
                        String switchDate = switchMap.get("year").toString() + "-" + switchMap.get("month").toString() + "-01";
                        mop.addAttribute("switchDate", addOneMonth(switchDate));
                    }
                }
            }

        } catch (Exception ex) {
            logger.error("取得报备详情信息异常,paramMap:" + paramMap, ex);
            logger.error("sceneTrade", "SceneTradeController",
                    "roughtUpt", paramMap.toString(), null, "", "", ex);
        }

        try {
            ResultData<?> resultDataWYTypeInfo = sceneTradeService.querylnkYjWyInfo();
            mop.addAttribute("wyTypeList", resultDataWYTypeInfo.getReturnData());
        } catch (Exception e) {
            logger.error("查询-物业类型list异常", e);
        }

        ModelAndView mv = new ModelAndView("scene/sceneTrade/roughtUpt");
        return mv;
    }

    /*
        案场管理-大定审核
     */
    @RequestMapping(value = "roughtAudit/{reportId}", method = RequestMethod.GET)
    public ModelAndView roughtAudit(@PathVariable("reportId") String reportId, ModelMap mop, HttpServletRequest request) {


        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("reportNo", reportId);
        paramMap.put("progress", 13505);
        paramMap.put("confirmStatus", 13603);

        try {

            logger.info("取得报备详情信息-大定审核，estateId:", paramMap);
            ResultData<?> resultData = sceneTradeService.getOperDetail(paramMap);
            logger.info("取得报备详情信息-大定审核,paramMap:" + paramMap, resultData);

            mop.addAttribute("info", JSONObject.toJSON(resultData.getReturnData()));


            Map<String, Object> reportMap = (Map<String, Object>) resultData.getReturnData();
            if (reportMap != null && reportMap.get("cityNo") != null) {
                String cityNo = reportMap.get("cityNo").toString();
                String roughDate = reportMap.get("roughInputDate").toString();

                ResultData<?> resultDataSwitch = sceneTradeService.getSwitchInfo(cityNo);
                Map<String, Object> switchMap = (Map<String, Object>) resultDataSwitch.getReturnData();
                if (switchMap != null && switchMap.get("year") != null && switchMap.get("month") != null) {
                    String switchDate = switchMap.get("year").toString() + "-" + switchMap.get("month").toString() + "-01";
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(addOneMonth(switchDate));
                    Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(roughDate);
                    if (date2.before(date)) {
                        //不能审核通过。弹提示框
                        mop.put("passFlag", "1");
                    } else {
                        mop.put("passFlag", "0");
                    }
                }
            }


        } catch (Exception ex) {
            logger.error("获取楼盘详情信息异常,paramMap:" + paramMap, ex);
            logger.error("sceneTrade", "SceneTradeController",
                    "roughtAudit", paramMap.toString(), null, "", "", ex);
        }

        ModelAndView mv = new ModelAndView("scene/sceneTrade/roughtAudit");
        return mv;
    }


    @RequestMapping(value = "roughtAuditPassPopup", method = RequestMethod.GET)
    public ModelAndView roughtAuditPassPopup(ModelMap mop, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("scene/sceneTrade/roughtAuditPassPopup");
        return mv;
    }

    @RequestMapping(value = "roughtAuditRefusePopup", method = RequestMethod.GET)
    public ModelAndView roughtAuditRefusePopup(ModelMap mop, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("scene/sceneTrade/roughtAuditRefusePopup");
        return mv;
    }

    /*7
        案场管理-退大定
    */
    @RequestMapping(value = "backRought/{reportId}", method = RequestMethod.GET)
    public ModelAndView backRought(@PathVariable("reportId") String reportId, ModelMap mop, HttpServletRequest request) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("reportNo", reportId);
        paramMap.put("progress", 13505);
        paramMap.put("confirmStatus", 13603);
        try {

            logger.info("取得报备详情信息-退大定，estateId:", paramMap);
            ResultData<?> resultData = sceneTradeService.getOperDetail(paramMap);
            logger.info("取得报备详情信息-退大定,paramMap:" + paramMap, resultData);

            mop.addAttribute("info", resultData.getReturnData());

            if (resultData != null) {
                Map<String, Object> returnDataMap = (Map<String, Object>) resultData.getReturnData();
                if (returnDataMap != null && returnDataMap.get("cityNo") != null) {
                    String cityNo = returnDataMap.get("cityNo").toString();
                    ResultData<?> resultDataSwitch = sceneTradeService.getSwitchInfo(cityNo);
                    Map<String, Object> switchMap = (Map<String, Object>) resultDataSwitch.getReturnData();
                    if (switchMap != null && switchMap.get("year") != null && switchMap.get("month") != null) {
                        String switchDate = switchMap.get("year").toString() + "-" + switchMap.get("month").toString() + "-01";
                        mop.addAttribute("switchDate", addOneMonth(switchDate));
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("获取楼盘详情信息异常,paramMap:" + paramMap, ex);
            logger.error("sceneTrade", "SceneTradeController",
                    "backRought", paramMap.toString(), null, "", "", ex);
        }
        ModelAndView mv = new ModelAndView("scene/sceneTrade/backRought");
        return mv;

    }

    /*
        案场管理-新增成销
    */
    @RequestMapping(value = "succSaleAdd/{reportId}", method = RequestMethod.GET)
    public ModelAndView succSaleAdd(@PathVariable("reportId") String reportId, ModelMap mop, HttpServletRequest request) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("reportNo", reportId);
        paramMap.put("progress", 13505);
        paramMap.put("confirmStatus", 13603);
        paramMap.put("brokerageStatus", 22001);
        try {

            logger.info("取得报备详情信息-新增成销，estateId:", paramMap);
            ResultData<?> resultData = sceneTradeService.getOperDetail(paramMap);
            logger.info("取得报备详情信息-新增成销,paramMap:" + paramMap, resultData);

            mop.addAttribute("info", JSONObject.toJSON(resultData.getReturnData()));

            if (resultData != null) {
                Map<String, Object> returnDataMap = (Map<String, Object>) resultData.getReturnData();
                if (returnDataMap != null && returnDataMap.get("cityNo") != null) {
                    String cityNo = returnDataMap.get("cityNo").toString();
                    ResultData<?> resultDataSwitch = sceneTradeService.getSwitchInfo(cityNo);
                    Map<String, Object> switchMap = (Map<String, Object>) resultDataSwitch.getReturnData();
                    if (switchMap != null && switchMap.get("year") != null && switchMap.get("month") != null) {
                        String switchDate = switchMap.get("year").toString() + "-" + switchMap.get("month").toString() + "-01";
                        mop.addAttribute("switchDate", addOneMonth(switchDate));
                    }

                    ResultData<?> resultDataCity = sceneTradeService.getCitySettingByCityNo(cityNo);
                    mop.addAttribute("citySetting", resultDataCity.getReturnData());
                }
                if (returnDataMap != null && returnDataMap.get("projectNo") != null) {
                    String projectNo = returnDataMap.get("projectNo").toString();
                    Map<String, Object> quryMap = new HashMap<>();
                    quryMap.put("projectNo", projectNo);
                    quryMap.put("reportId", reportId);
                    ResultData<?> resultDataPlanList = sceneTradeService.queryCntYjPlanList(quryMap);
                    mop.addAttribute("planList", resultDataPlanList.getReturnData());
                }
            }

        } catch (Exception ex) {
            logger.error("取得报备详情信息-新增成销,paramMap:" + paramMap, ex);
            logger.error("sceneTrade", "SceneTradeController",
                    "succSaleAdd", paramMap.toString(), null, "", "", ex);
        }

        ModelAndView mv = new ModelAndView("scene/sceneTrade/succSaleAdd");
        return mv;
    }


    /*
    案场管理-修改成销
*/
    @RequestMapping(value = "succSaleUpt/{reportId}", method = RequestMethod.GET)
    public ModelAndView succSaleUpt(@PathVariable("reportId") String reportId, ModelMap mop, HttpServletRequest request) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("reportNo", reportId);
        paramMap.put("progress", 13505);
        paramMap.put("confirmStatus", 13601);
        paramMap.put("brokerageStatus", 22001);
        try {

            logger.info("取得报备详情信息-修改成销，estateId:", paramMap);
            ResultData<?> resultData = sceneTradeService.getOperDetail(paramMap);
            logger.info("取得报备详情信息-修改成销,paramMap:" + paramMap, resultData);

            mop.addAttribute("info", JSONObject.toJSON(resultData.getReturnData()));

            if (resultData != null) {
                Map<String, Object> returnDataMap = (Map<String, Object>) resultData.getReturnData();
                if (returnDataMap != null && returnDataMap.get("cityNo") != null) {
                    String cityNo = returnDataMap.get("cityNo").toString();
                    ResultData<?> resultDataSwitch = sceneTradeService.getSwitchInfo(cityNo);
                    Map<String, Object> switchMap = (Map<String, Object>) resultDataSwitch.getReturnData();
                    if (switchMap != null && switchMap.get("year") != null && switchMap.get("month") != null) {
                        String switchDate = switchMap.get("year").toString() + "-" + switchMap.get("month").toString() + "-01";
                        mop.addAttribute("switchDate", addOneMonth(switchDate));
                    }
                }
            }

        } catch (Exception ex) {
            logger.error("获取楼盘详情信息异常,paramMap:" + paramMap, ex);
            logger.error("sceneTrade", "SceneTradeController",
                    "succSaleUpt", paramMap.toString(), null, "", "", ex);
        }

        ModelAndView mv = new ModelAndView("scene/sceneTrade/succSaleUpt");
        return mv;
    }


    /*
          案场管理-批量新增成销
      */
    @RequestMapping(value = "batchSuccessSale/{projectNo}", method = RequestMethod.GET)
    public ModelAndView batchSuccessSale(@PathVariable("projectNo") String projectNo, ModelMap mop, HttpServletRequest request) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("projectNo", projectNo);
        //获取用户code
        String userCode = UserInfoHolder.get().getUserCode();
        paramMap.put("userCode", userCode);

        try {
            logger.info("获取批量新增成销,projectNo:", projectNo);
            ResultData<?> resultData = sceneTradeService.getBatchSale(paramMap);
            logger.info("获取批量新增成销,projectNo:" + projectNo, resultData);

            mop.addAttribute("info", JSONObject.toJSON(resultData.getReturnData()));

            if (resultData != null) {
                Map<String, Object> returnDataMap = (Map<String, Object>) resultData.getReturnData();
                if (returnDataMap != null && returnDataMap.get("cityNo") != null) {
                    String cityNo = returnDataMap.get("cityNo").toString();

                    ResultData<?> resultDataSwitch = sceneTradeService.getSwitchInfo(cityNo);
                    Map<String, Object> switchMap = (Map<String, Object>) resultDataSwitch.getReturnData();
                    if (switchMap != null && switchMap.get("year") != null && switchMap.get("month") != null) {
                        String switchDate = switchMap.get("year").toString() + "-" + switchMap.get("month").toString() + "-01";
                        mop.addAttribute("switchDate", addOneMonth(switchDate));
                    }

//                    ResultData<?> accountProjectResult = sceneTradeService.getAccountProjectByCityNo(cityNo);
                    //20200701  获取项目对应收入类合同得核算主体 begin
                    ResultData<?> accountProjectResult = sceneTradeService.getAccountProjectByProjectNo(projectNo);
                    mop.addAttribute("cityAccountProject", accountProjectResult.getReturnData());
                    //end
                    ResultData<?> resultDataCity = sceneTradeService.getCitySettingByCityNo(cityNo);
                    mop.addAttribute("citySetting", resultDataCity.getReturnData());
                }
            }

        } catch (Exception ex) {
            logger.error("获取楼盘详情信息异常,projectNo:" + projectNo, ex);
            logger.error("sceneTrade", "SceneTradeController",
                    "batchSuccessSale", projectNo, null, "", "", ex);
        }

        ModelAndView mv = new ModelAndView("scene/sceneTrade/batchSuccessSale");
        return mv;
    }

    /*
        案场管理-退成销
    */
    @RequestMapping(value = "backSale/{reportId}", method = RequestMethod.GET)
    public ModelAndView backSale(@PathVariable("reportId") String reportId, ModelMap mop, HttpServletRequest request) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("reportNo", reportId);
        paramMap.put("progress", 13505);
        paramMap.put("confirmStatus", 13601);
        try {

            logger.info("取得报备详情信息-退成销，estateId:", paramMap);
            ResultData<?> resultData = sceneTradeService.getOperDetail(paramMap);
            logger.info("取得报备详情信息-退成销,paramMap:" + paramMap, resultData);

            mop.addAttribute("info", resultData.getReturnData());

            if (resultData != null) {
                Map<String, Object> returnDataMap = (Map<String, Object>) resultData.getReturnData();
                if (returnDataMap != null && returnDataMap.get("cityNo") != null) {
                    String cityNo = returnDataMap.get("cityNo").toString();
                    ResultData<?> resultDataSwitch = sceneTradeService.getSwitchInfo(cityNo);
                    Map<String, Object> switchMap = (Map<String, Object>) resultDataSwitch.getReturnData();
                    if (switchMap != null && switchMap.get("year") != null && switchMap.get("month") != null) {
                        String switchDate = switchMap.get("year").toString() + "-" + switchMap.get("month").toString() + "-01";
                        mop.addAttribute("switchDate", addOneMonth(switchDate));
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("获取楼盘详情信息异常,paramMap:" + paramMap, ex);
            logger.error("sceneTrade", "SceneTradeController",
                    "backSale", paramMap.toString(), null, "", "", ex);
        }
        ModelAndView mv = new ModelAndView("scene/sceneTrade/backSale");
        return mv;

    }

    @RequestMapping(value = "accountProjectList", method = RequestMethod.GET)
    public ModelAndView accountProjectList(ModelMap mop, HttpServletRequest request) {

        try {
            UserInfo userInfo = UserInfoHolder.get();

            // 城市初始默认值
            Map<String, Object> queryParam = new HashMap<>();
            if (userInfo != null) {
                queryParam.put("userId", userInfo.getUserId());
                queryParam.put("cityNo", userInfo.getCityNo());
            }

            // 归属城市列表
            ResultData<List<City>> resultCity = sceneTradeService.getAreaCity(queryParam);
            mop.put("cityList", resultCity.getReturnData());

            // 核算主体列表
            ResultData<List<AccountProjectList>> resultCenter = sceneTradeService.getAccountProjectList(queryParam);
            mop.put("accountProjectList", resultCenter.getReturnData());

        } catch (Exception e) {
            logger.error("sceneTrade", "SceneTradeController", "accountProjectList", "",
                    UserInfoHolder.getUserId(), "", "查询画面初始化-核算主体维护信息失败！", e);
        }

        ModelAndView mv = new ModelAndView("scene/sceneTrade/accountProject");
        return mv;
    }

    @RequestMapping(value = "accountProjectAddPopup", method = RequestMethod.GET)
    public ModelAndView accountProjectAddPopup(ModelMap mop, HttpServletRequest request) {
        try {
            UserInfo userInfo = UserInfoHolder.get();

            // 城市初始默认值
            Map<String, Object> queryParam = new HashMap<>();
            if (userInfo != null) {
                queryParam.put("userId", userInfo.getUserId());
                queryParam.put("cityNo", userInfo.getCityNo());
            }

            // 归属城市列表
            ResultData<List<City>> resultCity = sceneTradeService.getAreaCity(queryParam);
            mop.put("cityList", resultCity.getReturnData());

            // 核算主体列表
            ResultData<List<AccountProjectList>> resultCenter = sceneTradeService.getAccountProjectList(queryParam);
            mop.put("accountProjectList", resultCenter.getReturnData());

        } catch (Exception e) {
            logger.error("sceneTrade", "SceneTradeController", "accountProjectList", "",
                    UserInfoHolder.getUserId(), "", "查询画面初始化-核算主体新增维护信息失败！", e);
        }

        ModelAndView mv = new ModelAndView("scene/sceneTrade/accountProjectAddPopup");
        return mv;
    }

    @RequestMapping(value = "accountProjectUptPopup/{cityNo}/{accountProjectNo}", method = RequestMethod.GET)
    public ModelAndView accountProjectUptPopup(ModelMap mop, HttpServletRequest request
            , @PathVariable("cityNo") String cityNo, @PathVariable("accountProjectNo") String accountProjectNo) {
        try {
            UserInfo userInfo = UserInfoHolder.get();

            // 城市初始默认值
            Map<String, Object> queryParam = new HashMap<>();
            if (userInfo != null) {
                queryParam.put("userId", userInfo.getUserId());
                queryParam.put("cityNo", userInfo.getCityNo());
            }

            mop.put("cityNo", cityNo);
            mop.put("accountProjectNo", accountProjectNo);

            // 归属城市列表
            ResultData<List<City>> resultCity = sceneTradeService.getAreaCity(queryParam);
            mop.put("cityList", resultCity.getReturnData());

            // 核算主体列表
            ResultData<List<AccountProjectList>> resultCenter = sceneTradeService.getAccountProjectList(queryParam);
            mop.put("accountProjectList", resultCenter.getReturnData());

        } catch (Exception e) {
            logger.error("sceneTrade", "SceneTradeController", "accountProjectList", "",
                    UserInfoHolder.getUserId(), "", "查询画面初始化-核算主体更新维护信息失败！", e);
        }

        ModelAndView mv = new ModelAndView("scene/sceneTrade/accountProjectUptPopup");
        return mv;
    }


    @RequestMapping(value = "selectBuildingNoPage/{projectNo}/{oldBuildingNoId}", method = RequestMethod.GET)
    public ModelAndView selectBuildingNoPage(@PathVariable("projectNo") String projectNo
            , @PathVariable("oldBuildingNoId") String oldBuildingNoId
            , ModelMap mop, HttpServletRequest request) {
        mop.put("projectNo", projectNo);
        mop.put("oldBuildingNoId", oldBuildingNoId);
        ModelAndView mv = new ModelAndView("scene/sceneTrade/selectBuildingNoPage");
        return mv;

    }

    private String addOneMonth(String date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = sdf.parse(date);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.MONTH, 1);
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        return reStr;
    }

    //endregion

    //region 事件


    /*
     * 获取公司下门店数据
     */
    @RequestMapping(value = "store/qr", method = RequestMethod.GET)
    public ResultData<?> getStoreListByCompanyId(HttpServletRequest request) {
        ResultData<?> resultData = null;
        try {
            Map<String, Object> reqMap = bindParamToMap(request);
            //获取页面显示数据
            logger.info("获取公司下门店数据");
            resultData = sceneTradeService.getStoreListByCompanyId(reqMap);
            logger.info("获取公司下门店数据:", resultData.toString());
        } catch (Exception ex) {
            logger.error("获取公司下门店数据异常", ex);
        }
        return resultData;
    }

    /*
     * 获取城市数据
     */
    @RequestMapping(value = "queryCityList", method = RequestMethod.GET)
    public ResultData<?> queryCityList(HttpServletRequest request) {
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            logger.info("获取城市数据");
            resultData = sceneTradeService.queryCityList();
            logger.info("获取城市数据:", resultData.toString());
        } catch (Exception ex) {
            logger.error("获取城市数据异常", ex);
        }
        return resultData;
    }

    /*
     * 获取城市下区域数据
     */
    @RequestMapping(value = "queryDistrictListByCityNo/{cityNo}", method = RequestMethod.GET)
    public ResultData<?> queryDistrictListByCityNo(@PathVariable("cityNo") String cityNo, HttpServletRequest request) {
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            logger.info("获取城市下-区域数据,cityNo:", cityNo);
            resultData = sceneTradeService.queryDistrictListByCityNo(cityNo);
            logger.info("获取城市下-区域数据:", resultData.toString());
        } catch (Exception ex) {
            logger.error("获取城市下-区域数据异常;input param: reqMap=" + cityNo.toString(), ex);
        }
        return resultData;
    }

    /*
     * 获取归属项目部
     */
    @RequestMapping(value = "queryProjectDepartment", method = RequestMethod.GET)
    public ResultData<?> queryProjectDepartment(HttpServletRequest request) {
        ResultData<?> resultData = null;
        String cityNo = UserInfoHolder.get().getCityNo();
        try {
            Map<String, Object> reqMap = new HashMap<>();

            reqMap.put("cityNo", cityNo);
            //获取页面显示数据
            logger.info("获取归属项目部,cityNo", cityNo);
            resultData = sceneTradeService.queryProjectDepartment(reqMap);
            logger.info("获取归属项目部:", resultData.toString());
        } catch (Exception ex) {
            logger.error("获取归属项目部异常;input param: reqMap=" + cityNo.toString(), ex);
        }
        return resultData;
    }

    /*
     * 获取归属业绩中心
     */
    @RequestMapping(value = "queryAchivCenter/{userCode}", method = RequestMethod.GET)
    public ResultData<?> queryAchivCenter(@PathVariable("userCode") Integer userCode, HttpServletRequest request) {
        ResultData<?> resultData = null;

        String cityNo = UserInfoHolder.get().getCityNo();
        try {
            Map<String, Object> reqMap = new HashMap<>();

            reqMap.put("cityNo", cityNo);
            reqMap.put("userCode", userCode);
            //获取页面显示数据
            logger.info("获取归属业绩中心,userCode", userCode);
            resultData = sceneTradeService.queryAchivCenter(cityNo);
            logger.info("获取归属业绩中心:", resultData.toString());
        } catch (Exception ex) {
            logger.error("获取归属业绩中心;input param: reqMap=" + cityNo.toString(), ex);
        }
        return resultData;
    }


    /*
     * 获取归属业绩中心
     */
    @RequestMapping(value = "queryAchivCenterLevelList/", method = RequestMethod.GET)
    public ResultData<?> queryAchivCenterLevelList(HttpServletRequest request) {
        ResultData<?> resultData = null;

        String cityNo = UserInfoHolder.get().getCityNo();
        try {
            Map<String, Object> reqMap = new HashMap<>();

            reqMap.put("cityNo", cityNo);
            //获取页面显示数据
            logger.info("获取归属业绩中心Level,cityNo", cityNo);
            resultData = sceneTradeService.queryAchivCenter(cityNo);
            logger.info("获取归属业绩中心Level:", resultData.toString());
        } catch (Exception ex) {
            logger.error("获取归属业绩中心Level;input param: reqMap=" + cityNo.toString(), ex);
        }
        return resultData;
    }


    /*
     * 获取业绩归属人
     */
    @RequestMapping(value = "queryLinkUser", method = RequestMethod.GET)
    public ResultData<?> getLinkUser(HttpServletRequest request) {

        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            String cityNo = UserInfoHolder.get().getCityNo();
            reqMap.put("orderName", "userCode");
            reqMap.put("orderType", "ASC");
            reqMap.put("cityNo", cityNo);


            //获取页面显示数据
            logger.info("获取业绩归属人");
            resultData = sceneTradeService.getLinkUser(reqMap, pageInfo);
            logger.info("获取业绩归属人:", resultData.toString());
        } catch (Exception ex) {
            logger.error("获取业绩归属人发生异常", ex);
        }
        return resultData;
    }


    /*
     * 获取字典数据
     */
    @RequestMapping(value = "queryDictionary/{typeCode}", method = RequestMethod.GET)
    public ResultData<List<CommonCodeDto>> queryDictionary(@PathVariable("typeCode") Integer typeCode, HttpServletRequest request) {
        ResultData<List<CommonCodeDto>> resultData = new ResultData<>();
        try {
            //获取页面显示数据
            logger.info("获取字典数据,typeCode:", typeCode);
            List<CommonCodeDto> dataList = SystemParam.getCodeListByKey(typeCode.toString());
            resultData.setReturnData(dataList);
            logger.info("获取字典数据:typeCode=" + typeCode, resultData.toString());
        } catch (Exception ex) {
            logger.error("获取字典数据异常;input param: typeCode=" + typeCode.toString(), ex);
        }
        return resultData;


    }


    /*
     * 获取包销房源楼室号
     */
    @RequestMapping(value = "queryBuildingNoByEstateId/{projectNo}", method = RequestMethod.POST)
    public ResultData<List<Map<String, Object>>> queryBuildingNoByEstateId(
            @PathVariable("projectNo") String projectNo,
            HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);

        ResultData<List<Map<String, Object>>> resultData = new ResultData();
        List<Map<String, Object>> list = new ArrayList<>();
        try {

            Map<String, Object> params = new HashMap<>();
            PageInfo pageInfo = getPageInfo(request);

            params.put("current_page", pageInfo.getNextPage() + "");
            params.put("page_size", pageInfo.getPageLimit() + "");
            params.put("project_no", projectNo);
            params.put("key_word", reqMap.get("buildingNo"));
            params.put("house_id", reqMap.get("oldBuildingNoId"));


            String result = getyFInterfaceInfo("/GetCanSalesBXHouseList", JSON.toJSONString(params), null, UserInfoHolder.getUserId());
            if (StringUtil.isNotEmpty(result)) {
                YFRoughData yFRoughData = JSON.parseObject(result, YFRoughData.class);
                for (RoughData data : yFRoughData.getData()) {
                    Map<String, Object> yfMap = new HashMap<>();

                    String house_id = data.getHouse_id();
                    String house_code = data.getHouse_code();
                    String area = data.getArea();
                    String base_total_money = data.getBase_total_money();
                    String inner_total_money = data.getInner_total_money();
                    String house_type = data.getHouse_type();


                    yfMap.put("buildingNoCode", house_id);
                    yfMap.put("buildingNoName", house_code);
                    yfMap.put("roughArea", area);
                    yfMap.put("roughAmount", base_total_money);
                    yfMap.put("inner_total_money", inner_total_money);
                    yfMap.put("house_type", house_type);
                    list.add(yfMap);
                }
                pageInfo.setDataCount(Integer.parseInt(yFRoughData.getDataCount()));
                resultData.setTotalCount(yFRoughData.getDataCount());
            }
        } catch (Exception e) {
            logger.error("sceneTrade", "SceneTradeController", "queryBuildingNoByEstateId", "", 0, "", "", e);
        }

        resultData.setReturnData(list);
        return resultData;
    }

    public String getyFInterfaceInfo(String func, String paramMap, String typeId, int userId) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        String result = null;

        String returnDataStr = null;
        String url = SystemParam.getWebConfigValue("youfangReportUrl") + func;


        logger.info("调用有房接口start#####请求#url=" + url);
        try {
            returnDataStr = HttpClientUtil.httpPostYF(url, paramMap);
            if (StringUtil.isNotEmpty(returnDataStr)) {
                returnMap = JSON.parseObject(returnDataStr, Map.class);
                if (returnMap.containsKey("BFlag") && returnMap.containsKey("TData")) {
                    Integer bFlag = (Integer) returnMap.get("BFlag");
                    Object tData = returnMap.get("TData");
                    if (10 == bFlag) {
                        result = JSON.toJSONString(tData);
                    } else {

                    }
                }
            }
        } catch (Exception e) {
            logger.error("sceneTrade", "SceneTradeController", "getyFInterfaceInfo", "userId=" + userId, userId, null,
                    "调用有房接口:#####请求参数#url=" + url + "#####返回信息" + returnDataStr, e);
        }

        return result;
    }


    @RequestMapping(value = "getAccountProject/{cityNo}", method = RequestMethod.GET)
    public ResultData<?> getAccountProject(@PathVariable("cityNo") String cityNo, HttpServletRequest request) {
        ResultData<?> resultData = new ResultData<>();
        try {
            //获取页面显示数据
            logger.info("获取核算主体数据典数据,cityNo:", cityNo);
            resultData = sceneTradeService.getAccountProjectByCityNo(cityNo);
            logger.info("获取核算主体数据典数据:cityNo=" + cityNo, resultData.toString());
        } catch (Exception ex) {
            logger.error("获取核算主体数据典数据异常;input param: cityNo=" + cityNo.toString(), ex);
        }
        return resultData;
    }

    @RequestMapping(value = "queryaccountProjectByProjectNo/{projectNo}", method = RequestMethod.GET)
    public ResultData<?> queryaccountProjectByProjectNo(@PathVariable("projectNo") String projectNo, HttpServletRequest request) {
        ResultData<?> resultData = new ResultData<>();
        try {
            //获取页面显示数据
            logger.info("获取核算主体数据典数据,projectNo:", projectNo);
            resultData = sceneTradeService.getOALnkAccountProjectList(projectNo);
            logger.info("获取核算主体数据典数据:projectNo=" + projectNo, resultData.toString());
        } catch (Exception ex) {
            logger.error("获取核算主体数据典数据异常;input param: projectNo=" + projectNo.toString(), ex);
        }
        return resultData;
    }

    /*
     * 案场管理-项目查询返回数据
     */
    @RequestMapping(value = "qSceneEstate", method = RequestMethod.POST)
    public ResultData<?> qSceneEstate(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);

        try {
            String cityNo = UserInfoHolder.get().getCityNo();
            reqMap.put("cityNo", cityNo);

            //获取页面显示数据
            logger.info("查询案场数据-项目列表入参:", reqMap.toString());
            resultData = sceneTradeService.qSceneEstate(reqMap, pageInfo);
            logger.info("查询案场数据-项目列表结果:", resultData.toString());

        } catch (Exception e) {
            logger.error("查询案场-项目列表内容异常;input param: reqMap=" + reqMap.toString(), e);

            logger.error("scene",
                    "SceneTradeController",
                    "qSceneEstate",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询案场-项目列表内容异常",
                    e);
        }

        return resultData;
    }


    /*
     * 案场管理-报备查询返回数据
     */
    @RequestMapping(value = "qSceneRecognition", method = RequestMethod.POST)
    public ResultData<?> qSceneRecognition(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);

        try {
            String cityNo = UserInfoHolder.get().getCityNo();
            reqMap.put("cityNo", cityNo);
            //获取页面显示数据
            logger.info("查询案场数据-项目报备列表入参:", reqMap.toString());
            resultData = sceneTradeService.qSceneRecognition(reqMap, pageInfo);
            logger.info("查询案场数据-项目报备列表结果:", resultData.toString());

        } catch (Exception e) {
            logger.error("查询案场-项目列表报备内容异常;input param: reqMap=" + reqMap.toString(), e);

            logger.error("scene",
                    "SceneTradeController",
                    "qSceneRecognition",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询案场-项目报备列表异常",
                    e);
        }

        return resultData;
    }

    /*
     * 查询订单列表
     */
    @RequestMapping(value = "queryReportList", method = RequestMethod.POST)
    public ResultData<?> queryReportList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);

        //获取请求参数
        Integer userId = UserInfoHolder.getUserId();
        String userCode = UserInfoHolder.get().getUserCode();
        String cityNo = UserInfoHolder.get().getCityNo();
        String userName = UserInfoHolder.get().getUserName();

        reqMap.put("userCode", userCode);
        reqMap.put("userId", userId);
        reqMap.put("cityNo", cityNo);
        reqMap.put("userName", userName);

        try {
            //获取页面显示数据
            logger.info("查询订单列表入参:", reqMap.toString());
            resultData = sceneTradeService.queryReportList(reqMap, pageInfo);
            logger.info("查询订单列表列表结果:", resultData.toString());

        } catch (Exception e) {
            logger.error("查询订单列表异常;input param: reqMap=" + reqMap.toString(), e);

            logger.error("scene",
                    "SceneTradeController",
                    "queryReportList",
                    "input param: reqMap=" + reqMap.toString(),
                    userId,
                    WebUtil.getIPAddress(request),
                    "查询订单列表异常",
                    e);
        }

        return resultData;
    }

    /*
     * 案场管理- 查询客户信息列表返回数据
     */
    @RequestMapping(value = "queryCustomLegList", method = RequestMethod.POST)
    public ResultData<?> queryCustomLegList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);

        try {
            String cityNo = UserInfoHolder.get().getCityNo();
            reqMap.put("cityNo", cityNo);

            //获取页面显示数据
            logger.info("查询客户信息列表数据-项目报备列表入参:", reqMap.toString());
            resultData = sceneTradeService.queryCustomLegList(reqMap, pageInfo);
            logger.info("查询客户信息列表数据-项目报备列表结果:", resultData.toString());

        } catch (Exception e) {
            logger.error("查询客户信息列表数据内容异常;input param: reqMap=" + reqMap.toString(), e);

            logger.error("scene",
                    "SceneTradeController",
                    "queryCustomLegList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询客户信息列表数据内容异常",
                    e);
        }

        return resultData;
    }

    /**
     * 案场管理- 查询客户信息详细返回数据
     */
    @RequestMapping(value = "queryCustomDetList", method = RequestMethod.POST)
    public ResultData<?> queryCustomDetList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);

        try {
            String cityNo = UserInfoHolder.get().getCityNo();
            reqMap.put("cityNo", cityNo);

            //获取页面显示数据
            logger.info("查询客户信息详细-项目报备列表入参:", reqMap.toString());
            resultData = sceneTradeService.queryCustomDetList(reqMap, pageInfo);
            logger.info("查询客户信息详细-项目报备列表结果:", resultData.toString());

        } catch (Exception e) {
            logger.error("查询客户信息详细内容异常;input param: reqMap=" + reqMap.toString(), e);

            logger.error("scene",
                    "SceneTradeController",
                    "queryCustomDetList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询客户信息详细内容异常",
                    e);
        }
        return resultData;
    }

    /*
     * 取得报备进度信息
     */
    @RequestMapping(value = "report/{reportId}/progress", method = RequestMethod.GET)
    public ResultData<?> queryReportProgress(@PathVariable("reportId") String reportId, HttpServletRequest request) {
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            logger.info("取得报备进度信息,reportId:", reportId);
            resultData = sceneTradeService.queryReportProgress(reportId);
            logger.info("取得报备进度信息:reportId:" + reportId, resultData.toString());
        } catch (Exception ex) {
            logger.error("取得报备进度信息异常;input param: reqMap=" + reportId.toString(), ex);
        }
        return resultData;

    }

    /*
     * 取得报备日志信息
     */
    @RequestMapping(value = "report/{reportId}/log", method = RequestMethod.GET)
    public ResultData<?> queryReportlog(@PathVariable("reportId") String reportId, HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            logger.info("取得报备日志信息,reportId:", reportId);
            resultData = sceneTradeService.queryReportlog(reportId);
            logger.info("取得报备日志信息:reportId:" + reportId, resultData.toString());
        } catch (Exception ex) {
            logger.error("取得报备日志信息;input param: reportId=" + reportId.toString(), ex);
        }
        return resultData;
    }

    /*
     * 报备无效处理
     */
    @RequestMapping(value = "toInvalid", method = RequestMethod.GET)
    public ResultData<?> toInvalid(HttpServletRequest request) {
        ResultData<?> resultData = new ResultData<>();
        //获取请求参数
        Integer userId = UserInfoHolder.getUserId();
        String userCode = UserInfoHolder.get().getUserCode();
        String cityNo = UserInfoHolder.get().getCityNo();
        String userName = UserInfoHolder.get().getUserName();

        //获取请求参数
        Map<String, Object> map = bindParamToMap(request);
        map.put("userCode", userCode);
        map.put("userId", userId);
        map.put("cityNo", cityNo);
        map.put("userName", userName);

        try {
            map.put("status", "13602");

            logger.info("报备无效处理,param:", map.toString());
            resultData = sceneTradeService.sceneRecognitionConfirm(map);
            logger.info("报备无效处理,in-param:" + map.toString(), resultData);

        } catch (Exception e) {
            resultData.setFail("报备无效处理异常");
            logger.error("报备无效处理异常,param:" + map.toString(), e);
            logger.error("sceneTrade", "SceneTradeController",
                    "addBatchSale", "", userId, "", "报备无效处理异常", e);
        }
        return resultData;

    }

    /*
     * 加入批量成销
     */
    @RequestMapping(value = "addBatchSale", method = RequestMethod.GET)
    public ResultData<?> addBatchSale(HttpServletRequest request) {

        ResultData<?> resultData = new ResultData<>();
        //获取请求参数
        Integer userId = UserInfoHolder.getUserId();
        String userCode = UserInfoHolder.get().getUserCode();
        String cityNo = UserInfoHolder.get().getCityNo();

        //获取请求参数
        Map<String, Object> map = bindParamToMap(request);
        map.put("userCode", userCode);
        map.put("userId", userId);
        map.put("cityNo", cityNo);

        try {

            logger.info("加入批量成销,param:", map.toString());
            resultData = sceneTradeService.addBatchSale(map);
            logger.info("加入批量成销,in-param:" + map.toString(), resultData);

        } catch (Exception e) {
            resultData.setFail("加入批量成销异常");
            logger.error("加入批量成销异常,param:" + map.toString(), e);
            logger.error("sceneTrade", "SceneTradeController",
                    "addBatchSale", "", userId, "", "加入批量成销异常", e);
        }
        return resultData;

    }

    /*
     * 查询批量成销套数
     */
    @RequestMapping(value = "countBatchSaleDetailAll", method = RequestMethod.GET)
    public ResultData<?> countBatchSaleDetailAll(HttpServletRequest request) {

        ResultData<?> resultData = new ResultData<>();

        //获取请求参数
        Integer userId = UserInfoHolder.getUserId();
        String userCode = UserInfoHolder.get().getUserCode();
        String cityNo = UserInfoHolder.get().getCityNo();

        Map<String, Object> map = bindParamToMap(request);
        map.put("userCode", userCode);
        map.put("userId", userId);
        map.put("cityNo", cityNo);

        try {
            logger.info("查询批量成销套数,param:", map.toString());
            resultData = sceneTradeService.countBatchSaleDetailAll(map);
            logger.info("查询批量成销套数,in-param:" + map.toString(), resultData);
        } catch (Exception e) {
            resultData.setFail("查询批量成销套数异常");
            logger.error("查询批量成销套数异常,param:" + map.toString(), e);
            logger.error("sceneTrade", "SceneTradeController",
                    "countBatchSaleDetailAll", "", userId, "", "查询批量成销套数", e);
        }
        return resultData;

    }

    /*
     * 查询批量退房套数
     */
    @RequestMapping(value = "countBatchRebackDetail", method = RequestMethod.GET)
    public ResultData<?> countBatchRebackDetail(HttpServletRequest request) {

        ResultData<?> resultData = new ResultData<>();

        //获取请求参数
        Integer userId = UserInfoHolder.getUserId();
        String userCode = UserInfoHolder.get().getUserCode();
        String cityNo = UserInfoHolder.get().getCityNo();

        Map<String, Object> map = bindParamToMap(request);
        map.put("userCode", userCode);
        map.put("userId", userId);
        map.put("cityNo", cityNo);

        try {
            logger.info("查询批量退房套数,param:", map.toString());
            resultData = sceneTradeService.countBatchRebackDetail(map);
            logger.info("查询批量退房套数,in-param:" + map.toString(), resultData);
        } catch (Exception e) {
            resultData.setFail("查询批量成销套数异常");
            logger.error("查询批量成销套数异常,param:" + map.toString(), e);
            logger.error("sceneTrade", "SceneTradeController",
                    "countBatchSaleDetailAll", "", userId, "", "查询批量成销套数异常", e);
        }
        return resultData;

    }


    /*
     * 新增报备
     */
    @RequestMapping(value = "saveReport", method = RequestMethod.POST)
    public ResultData<?> saveReport(HttpServletRequest request) {

        ResultData<?> resultData = new ResultData<>();

        Integer userId = UserInfoHolder.getUserId();
        String userCode = UserInfoHolder.get().getUserCode();
        String cityNo = UserInfoHolder.get().getCityNo();
        String userName = UserInfoHolder.get().getUserName();
        //获取请求参数
        Map<String, Object> map = bindParamToMap(request);
        map.put("userCode", userCode);
        map.put("userId", userId);
        map.put("userName", userName);
        map.put("cityNo", cityNo);
        try {
            logger.info("新增报备,param:", map.toString());
            resultData = sceneTradeService.saveReport(map);
            logger.info("新增报备,in-param:" + map.toString(), resultData);

        } catch (Exception e) {
            resultData.setFail("新增保存失败");
            logger.error("新增报备异常,param:" + map.toString(), e);
            logger.error("sceneTrade", "SceneTradeController",
                    "saveReport", "", userId, "", "新增报备异常", e);
        }
        return resultData;

    }


    /**
     * 新增带看
     */
    @RequestMapping(value = "saveWatch", method = RequestMethod.POST)
    public ResultData<?> saveWatch(HttpServletRequest request) {

        ResultData<?> resultData = new ResultData<>();

        Integer userId = UserInfoHolder.getUserId();
        String userCode = UserInfoHolder.get().getUserCode();
        String cityNo = UserInfoHolder.get().getCityNo();
        String userName = UserInfoHolder.get().getUserName();
        //获取请求参数
        Map<String, Object> map = bindParamToMap(request);
        map.put("userCode", userCode);
        map.put("userId", userId);
        map.put("userName", userName);
        map.put("cityNo", cityNo);
        try {
            map.put("status", "13502");

            logger.info("新增带看,param:", map.toString());
            resultData = sceneTradeService.sceneRecognitionConfirm(map);
            logger.info("新增带看,in-param:" + map.toString(), resultData);

        } catch (Exception e) {
            resultData.setFail("新增保存失败");
            logger.error("新增带看异常,param:" + map.toString(), e);
            logger.error("sceneTrade", "SceneTradeController",
                    "saveWatch", "", userId, "", "新增带看异常", e);
        }
        return resultData;

    }


    /**
     * 新增大定
     */
    @RequestMapping(value = "saveRought", method = RequestMethod.POST)
    public ResultData<?> saveRought(HttpServletRequest request) {

        ResultData<?> resultData = new ResultData<>();

        Integer userId = UserInfoHolder.getUserId();
        String userCode = UserInfoHolder.get().getUserCode();
        String cityNo = UserInfoHolder.get().getCityNo();
        String userName = UserInfoHolder.get().getUserName();
        //获取请求参数
        Map<String, Object> map = bindParamToMap(request);
        map.put("userCode", userCode);
        map.put("userId", userId);
        map.put("userName", userName);
        map.put("cityNo", cityNo);
        try {
            map.put("status", "13504");

            logger.info("新增大定,param:", map.toString());
            resultData = sceneTradeService.sceneRecognitionConfirm(map);
            logger.info("新增大定,in-param:" + map.toString(), resultData);

        } catch (Exception e) {
            resultData.setFail("新增保存失败");
            logger.error("新增大定异常,param:" + map.toString(), e);
            logger.error("sceneTrade", "SceneTradeController",
                    "saveRought", "", userId, "", "新增大定异常", e);
        }
        return resultData;

    }

    /**
     * 大定审核
     */
    @RequestMapping(value = "saveRoughtAudit", method = RequestMethod.POST)
    public ResultData<?> saveRoughtAudit(HttpServletRequest request) {

        ResultData<?> resultData = new ResultData<>();

        Integer userId = UserInfoHolder.getUserId();
        String userCode = UserInfoHolder.get().getUserCode();
        String cityNo = UserInfoHolder.get().getCityNo();
        String userName = UserInfoHolder.get().getUserName();
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userCode", userCode);
        reqMap.put("userId", userId);
        reqMap.put("userName", userName);
        reqMap.put("cityNo", cityNo);
        try {
/*            if ("1".equals(reqMap.get("roughAuditStatus"))) {
                ResultData<?> checkResultData = sceneTradeService.checkReportCooperation(reqMap.get("reportId").toString());
                if ("-1".equals(checkResultData.getReturnCode())) {
                    return checkResultData;
                }
            }*/
            ReportDto reportDto = new ReportDto();
            reportDto.setId(Integer.valueOf(reqMap.get("id").toString()));
            reportDto.setRoughAuditStatus(reqMap.get("roughAuditStatus").toString());
            reportDto.setRoughAuditId(Long.valueOf(userId));
            reportDto.setRoughAuditTime(new Date());

            if ("1".equals(reqMap.get("roughAuditStatus").toString())) {
                reportDto.setRoughDate(DateUtil.getDate(reqMap.get("roughInputDate").toString()));
                reportDto.setInComeStatus(Integer.valueOf(reqMap.get("inComeStatus").toString()));
                reportDto.setDetailId(Integer.valueOf(reqMap.get("detailId").toString()));
            }
            if ("2".equals(reqMap.get("roughAuditStatus").toString())) {
                reportDto.setRoughAuditDesc(reqMap.get("roughAuditDesc").toString());
            }

            logger.info("大定审核,param:", reqMap.toString());
            resultData = sceneTradeService.saveRoughtAudit(reportDto);
            if ("-1".equals(resultData.getReturnCode())) {
                return resultData;
            }

            if ("1".equals(reqMap.get("roughAuditStatus").toString())) {
                sceneTradeService.updateDetailRoughDate(reportDto);

                //添加返佣对象表记录
                reportDto.setReportId(reqMap.get("reportId").toString());
                reportDto.setCompanyId(reqMap.get("companyNo").toString());
                sceneTradeService.insertYjReport(reportDto);
            }
            logger.info("大定审核,in-param:" + reqMap.toString(), resultData);

        } catch (Exception e) {
            resultData.setFail("大定审核失败");
            logger.error("大定审核异常,param:" + reqMap.toString(), e);
            logger.error("sceneTrade", "SceneTradeController",
                    "saveRoughtAudit", "", userId, "", "大定审核异常", e);
        }
        return resultData;

    }

    @RequestMapping(value = "checkReportCooperation/{reportId}", method = RequestMethod.GET)
    public ResultData<?> checkReportCooperation(HttpServletRequest request, @PathVariable("reportId") String reportId) {

        ResultData<?> resultData = new ResultData<>();
        Integer userId = UserInfoHolder.getUserId();
        try {
            resultData = sceneTradeService.checkReportCooperation(reportId);
        } catch (Exception e) {
            resultData.setFail("check报备的分销合同信息异常");
            logger.error("check报备的分销合同信息异常,param:" + reportId, e);
            logger.error("sceneTrade", "SceneTradeController",
                    "checkReportCooperation", "", userId, "", "check报备的分销合同信息异常", e);
        }

        return resultData;
    }

    /**
     * 大定修改
     */
    @RequestMapping(value = "updateRought", method = RequestMethod.POST)
    public ResultData<?> updateRought(HttpServletRequest request) {

        ResultData<?> resultData = new ResultData<>();

        Integer userId = UserInfoHolder.getUserId();
        String userCode = UserInfoHolder.get().getUserCode();
        String cityNo = UserInfoHolder.get().getCityNo();
        String userName = UserInfoHolder.get().getUserName();

        //获取请求参数
        Map<String, Object> map = bindParamToMap(request);
        map.put("userCode", userCode);
        map.put("userId", userId);
        map.put("userName", userName);
        map.put("cityNo", cityNo);
        try {
            map.put("progress", 13504);

            logger.info("大定修改,param:", map.toString());
            sceneTradeService.operDetailUpdate(map);
            logger.info("大定修改,in-param:" + map.toString(), resultData);

        } catch (Exception e) {
            resultData.setFail("新增保存失败");
            logger.error("大定修改异常,param:" + map.toString(), e);
            logger.error("sceneTrade", "SceneTradeController",
                    "updateRought", "", userId, "", "大定修改异常", e);
        }
        return resultData;
    }


    /**
     * 新增成销
     */
    @RequestMapping(value = "saveSuccSale", method = RequestMethod.POST)
    public ResultData<?> saveSuccSale(HttpServletRequest request) {

        ResultData<?> resultData = new ResultData<>();

        Integer userId = UserInfoHolder.getUserId();
        String userCode = UserInfoHolder.get().getUserCode();
        String cityNo = UserInfoHolder.get().getCityNo();
        String userName = UserInfoHolder.get().getUserName();
        //获取请求参数
        Map<String, Object> map = bindParamToMap(request);
        map.put("userCode", userCode);
        map.put("userId", userId);
        map.put("userName", userName);
        map.put("cityNo", cityNo);
        try {
            map.put("status", "13505");

            logger.info("新增成销,param:", map.toString());
            resultData = sceneTradeService.sceneRecognitionConfirm(map);
            logger.info("新增成销,in-param:" + map.toString(), resultData);

        } catch (Exception e) {
            resultData.setFail("新增保存失败");
            logger.error("新增成销异常,param:" + map.toString(), e);
            logger.error("sceneTrade", "SceneTradeController",
                    "saveSuccSale", "", userId, "", "新增成销异常", e);
        }
        return resultData;

    }

    @RequestMapping(value = "updateSuccSale", method = RequestMethod.POST)
    public ResultData<?> updateSuccSale(HttpServletRequest request) {

        ResultData<?> resultData = new ResultData<>();

        Integer userId = UserInfoHolder.getUserId();
        String userCode = UserInfoHolder.get().getUserCode();
        String cityNo = UserInfoHolder.get().getCityNo();
        String userName = UserInfoHolder.get().getUserName();

        //获取请求参数
        Map<String, Object> map = bindParamToMap(request);
        map.put("userCode", userCode);
        map.put("userId", userId);
        map.put("userName", userName);
        map.put("cityNo", cityNo);
        try {
            map.put("progress", 13505);

            logger.info("成销修改,param:", map.toString());
            resultData = sceneTradeService.operDetailUpdate(map);
            logger.info("成销修改,in-param:" + map.toString(), resultData);

        } catch (Exception e) {
            resultData.setFail("成销修改异常");
            logger.error("成销修改异常,param:" + map.toString(), e);
            logger.error("sceneTrade", "SceneTradeController",
                    "updateRought", "", userId, "", "成销修改异常", e);
        }
        return resultData;

    }

    /**
     * 退大定
     */
    @RequestMapping(value = "tobackRought", method = RequestMethod.POST)
    public ResultData<?> tobackRought(HttpServletRequest request) {

        ResultData<?> resultData = new ResultData<>();

        Integer userId = UserInfoHolder.getUserId();
        String userCode = UserInfoHolder.get().getUserCode();
        String cityNo = UserInfoHolder.get().getCityNo();
        String userName = UserInfoHolder.get().getUserName();

        //获取请求参数
        Map<String, Object> map = bindParamToMap(request);
        map.put("operUserCode", userCode);
        map.put("userId", userId);
        map.put("operUserName", userName);
        map.put("cityNo", cityNo);

        map.put("status", "13504");
        try {

            logger.info("退大定,param:", map.toString());
            resultData = sceneTradeService.updateReback(map);
            logger.info("退大定,in-param:" + map.toString(), resultData);

        } catch (Exception e) {
            resultData.setFail("操作失败");
            logger.error("退大定异常,param:" + map.toString(), e);
            logger.error("sceneTrade", "SceneTradeController",
                    "tobackRought", "", userId, "", "退大定异常", e);
        }
        return resultData;

    }

    /**
     * 退成销
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "tobackSale", method = RequestMethod.POST)
    public ResultData<?> tobackSale(HttpServletRequest request) {

        ResultData<?> resultData = new ResultData<>();

        Integer userId = UserInfoHolder.getUserId();
        String userCode = UserInfoHolder.get().getUserCode();
        String cityNo = UserInfoHolder.get().getCityNo();
        String userName = UserInfoHolder.get().getUserName();

        //获取请求参数
        Map<String, Object> map = bindParamToMap(request);
        map.put("operUserCode", userCode);
        map.put("userId", userId);
        map.put("operUserName", userName);
        map.put("cityNo", cityNo);

        map.put("status", "13505");
        try {

            logger.info("退成销,param:", map.toString());
            resultData = sceneTradeService.updateReback(map);
            logger.info("退成销,in-param:" + map.toString(), resultData);

        } catch (Exception e) {
            resultData.setFail("操作失败");
            logger.error("退成销异常,param:" + map.toString(), e);
            logger.error("sceneTrade", "SceneTradeController",
                    "tobackSale", "", userId, "", "退成销异常", e);
        }

        return resultData;
    }

    private void setReportDto(Map<String, Object> reqMap, ReportDto reportDto) throws ParseException {
        String estateId = (String) reqMap.get("estateId");
        if (StringUtil.isNotEmpty(estateId)) {
            reportDto.setEstateId(estateId);
        }

        String reportId = (String) reqMap.get("reportId");
        if (StringUtil.isNotEmpty(reportId)) {
            reportDto.setReportId(reportId);
        }

        String status = (String) reqMap.get("status");
        String operateDate = (String) reqMap.get("operateDate");
        Date date = getFormatStringDate(operateDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
        if ("13503".equals(status)) {//退筹
            reportDto.setPledgedBackDate(date);
        } else if ("13504".equals(status)) {//退定
            reportDto.setRoughBackDate(date);
        } else if ("13505".equals(status)) {//退房
            reportDto.setDealBackDate(date);
        }
    }

    private Date getFormatStringDate(String sDat, String strFormat) throws ParseException {
        if (StringUtil.isNotEmpty(sDat)) {
            // 解析日期
            SimpleDateFormat myFmt = new SimpleDateFormat(strFormat);
            return myFmt.parse(sDat);
        }
        return null;
    }

    /*
     * 查询返佣对象列表
     */
    @RequestMapping(value = "getLnkYjReportList/{reportId}", method = RequestMethod.POST)
    public ResultData<?> getLnkYjReportList(@PathVariable("reportId") String reportId, HttpServletRequest request) {

        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);

        //获取请求参数
        Map<String, Object> map = bindParamToMap(request);
        map.put("reportId", reportId);
        try {
            //获取页面显示数据
            logger.info("查询返佣对象列表:", map.toString());
            resultData = sceneTradeService.getLnkYjReportList(map, pageInfo);
            logger.info("查询返佣对象列表结果:", resultData.toString());

        } catch (Exception e) {
            logger.error("查询返佣对象列表异常;input param: reqMap=" + map.toString(), e);

            logger.error("sceneTrade",
                    "SceneTradeController",
                    "queryReportList",
                    "input param: map=" + map.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询返佣对象列表异常",
                    e);
        }

        return resultData;
    }

    /**
     * @Title: editCustomerInfoMode
     * @Description: 客户信息调整弹窗
     */
    @RequestMapping(value = "/openDialogEditCustomer", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView openDialogEditCustomer(HttpServletRequest request, ModelMap mop) {
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        //报备编号
        mop.put("reportId", reqMap.get("reportId").toString());
        //客户
        mop.put("customerNm", reqMap.get("customerNm").toString());
        mop.put("customerTel", reqMap.get("customerTel").toString());
        //第二组客户
        mop.put("customerNmTwo", reqMap.get("customerNmTwo").toString());
        mop.put("customerTelTwo", reqMap.get("customerTelTwo").toString());
        //操作人
        mop.put("userCode", UserInfoHolder.get().getUserCode());
        mop.put("userName", UserInfoHolder.get().getUserName());
        mop.put("userIdCreate", UserInfoHolder.get().getUserId());
        //返回页面参数
        mop.put("estateId", reqMap.get("estateId").toString());
        mop.put("companyId", reqMap.get("companyId").toString());
        mop.put("customerId", reqMap.get("customerId").toString());
        mop.put("relateId", reqMap.get("relateId").toString());
//     mop.put("fromType", reqMap.get("fromType").toString());
        ModelAndView mv = new ModelAndView("scene/sceneTrade/openDialogEditCustomerPopup");
        return mv;
    }

    /**
     * @Title: saveCustomerInfoAdjut
     * @Description: 保存修改后的客户信息
     */
    @RequestMapping(value = "/saveCustomerInfoAdjut", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> saveCustomerInfoAdjut(HttpServletRequest request, ModelMap modelMap) {
        ReturnView returnView = new ReturnView();
        ResultData resultData = new ResultData();
        resultData.setFail();
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            resultData = sceneTradeService.saveCustomerInfoAdjut(reqMap);
        } catch (Exception e) {
            resultData.setFail("保存客户修改信息异常!");
            logger.error("sceneTrade", "SceneTradeController", "saveCustomerInfoAdjut", reqMap.toString(), null, "", "保存客户修改信息异常", e);
        }
        returnView.setOperateReturnType();
        returnView.setReturnCode(resultData.getReturnCode());
        returnView.setReturnMsg(resultData.getReturnMsg());
        returnView.setReturnValue(resultData.getReturnData());

        return returnView;
    }


    /**
     * @Title: openDialogEditAchieve
     * @Description: 业绩调整弹窗
     */
    @RequestMapping(value = "/openDialogEditAchieve", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView openDialogEditAchieve(HttpServletRequest request, ModelMap mop) {
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        //报备编号
        mop.put("reportId", reqMap.get("reportId").toString());
        //客户
        mop.put("customerNm", reqMap.get("customerNm").toString());
        mop.put("customerTel", reqMap.get("customerTel").toString());
        //联系人
        mop.put("oldContactId", reqMap.get("contactId").toString());
        mop.put("oldContactNm", reqMap.get("contactNm").toString());
        //原来所属中心
        mop.put("oldCenterGroupId", reqMap.get("centerGroupId").toString());
        mop.put("oldCenterGroupName", reqMap.get("centerGroupName").toString());

        mop.put("fyCenterId", reqMap.get("fyCenterId").toString());
        mop.put("fyCenterName", reqMap.get("fyCenterName").toString());
        mop.put("htedition", reqMap.get("htedition").toString());
        mop.put("branchId", reqMap.get("branchId").toString());
        //操作人
        mop.put("userCode", UserInfoHolder.get().getUserCode());
        mop.put("userName", UserInfoHolder.get().getUserName());
        mop.put("userIdCreate", UserInfoHolder.get().getUserId());
        //返回页面参数
        mop.put("estateId", reqMap.get("estateId").toString());
        mop.put("companyId", reqMap.get("companyId").toString());
        mop.put("customerId", reqMap.get("customerId").toString());
        mop.put("relateId", reqMap.get("relateId").toString());
        mop.put("reqMap", reqMap);

        Date auditDate = DateUtils.parseDate((String) reqMap.get("auditDate"), DateUtils.FORMAT_STR);
        Date currDate = DateUtils.parseDate((String) reqMap.get("currDate"), DateUtils.FORMAT_STR);
        //判断当前时间是否在配置时间内(1:在         -1:不在)
        mop.addAttribute("dateFlag", auditDate.compareTo(new Date()));
        //  查询项目发布城市集合
        ResultData resultData = new ResultData();
        //请求map
        try {
            resultData = sceneTradeService.getProjectCityNoList(reqMap);
            Map<String, Object> map = (Map<String, Object>) resultData.getReturnData();
            String projectCityNos = (String) map.get("projectCityNos");
            mop.put("projectCityNos", projectCityNos);
        } catch (Exception e) {
            resultData.setFail("获取项目发布城市异常!");
            logger.error("sceneTrade", "SceneTradeController", "", reqMap.toString(), null, "", "获取项目发布城市异常", e);
        }

        ModelAndView mv = new ModelAndView("scene/sceneTrade/openDialogEditAchievePopup");
        return mv;
    }

    /**
     * @Title: saveAchievementAdjut
     * @Description: 保存调整后的业绩
     */
    @RequestMapping(value = "/saveAchievementAdjut", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> saveAchievementAdjut(HttpServletRequest request, ModelMap modelMap) {
        ReturnView returnView = new ReturnView();
        ResultData resultData = new ResultData();
        resultData.setFail();
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            resultData = sceneTradeService.saveAchievementAdjut(reqMap);
        } catch (Exception e) {
            resultData.setFail("保存联动业绩调整信息异常!");
            logger.error("sceneTrade", "SceneTradeController", "saveAchievementAdjut", reqMap.toString(), null, "", "保存联动业绩调整信息异常", e);
        }
        returnView.setOperateReturnType();
        returnView.setReturnCode(resultData.getReturnCode());
        returnView.setReturnMsg(resultData.getReturnMsg());
        returnView.setReturnValue(resultData.getReturnData());

        return returnView;
    }

    /**
     * 获取业绩人中心列表
     *
     * @param mop
     * @return
     */
    @RequestMapping(value = "/getLinkUserCenter", method = RequestMethod.GET)
    @ResponseBody
    public ResultData<?> getLinkUserCenter(HttpServletRequest request, ModelMap mop) {
        ResultData<?> resultData = new ResultData<>();
        Map<String, Object> reqMap = bindParamToMap(request);
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try {
            if (reqMap.containsKey("userCode")) {
                String linkUserCode = reqMap.get("userCode").toString();
                resultData = sceneTradeService.getLinkUserCenter(linkUserCode);
            }
        } catch (Exception e) {
            logger.error("sceneTrade",
                    "SceneTradeController",
                    "getLinkUser",
                    "",
                    UserInfoHolder.getUserId(),
                    "",
                    "获取业绩人归属中心列表",
                    e);
        }
        return resultData;
    }


    /**
     * desc:退房解锁
     * 2019年11月20日
     */
    @RequestMapping(value = "/toUnlockReback", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView toUnlockReback(HttpServletRequest request, ModelMap mop) {
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        //报备编号
        mop.put("reportId", reqMap.get("reportId").toString());
        //返回页面参数
        mop.put("estateId", reqMap.get("estateId").toString());
        mop.put("companyId", reqMap.get("companyId").toString());
        mop.put("customerId", reqMap.get("customerId").toString());
        mop.put("relateId", reqMap.get("relateId").toString());
//        mop.put("fromType", reqMap.get("fromType").toString());
        ModelAndView mv = new ModelAndView("scene/sceneTrade/openDialogUnlockBackPopup");
        return mv;
    }

    @RequestMapping(value = "/unlockReback", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> unlockReback(HttpServletRequest request, ModelMap modelMap) {
        ReturnView returnView = new ReturnView();
        ResultData resultData = new ResultData();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            reqMap.put("crtUser", UserInfoHolder.getUserId());
            reqMap.put("userCode", UserInfoHolder.get().getUserCode());
            reqMap.put("userName", UserInfoHolder.get().getUserName());
            resultData = sceneTradeService.unlockReback(reqMap);
        } catch (Exception e) {
            resultData.setFail("退房解锁异常!");
            logger.error("sceneTrade", "SceneTradeController", "unlockReback", reqMap.toString(), null, "", "退房解锁异常", e);
        }
        returnView.setOperateReturnType();
        returnView.setReturnCode(resultData.getReturnCode());
        returnView.setReturnMsg(resultData.getReturnMsg());
        returnView.setReturnValue(resultData.getReturnData());

        return returnView;
    }


    /***
     * 批量成销列表
     */
    @RequestMapping(value = "getBatchSaleDetailList/{batchId}", method = RequestMethod.POST)
    public ResultData<?> getBatchSaleDetailList(@PathVariable("batchId") Integer batchId, HttpServletRequest request) {

        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            logger.info("批量成销列表:batchId", batchId);
            resultData = sceneTradeService.getBatchSaleDetailList(batchId);
            logger.info("批量请款列表结果:", resultData.toString());

        } catch (Exception e) {
            logger.error("批量成销列表表异常;input param: batchId=" + batchId, e);

            logger.error("sceneTrade",
                    "SceneTradeController",
                    "getBatchSaleList",
                    "input param: batchId=" + batchId,
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "批量请款列表异常",
                    e);
        }

        return resultData;

    }


    @RequestMapping(value = "checkAccountProject", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> checkAccountProject(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> rspMap = new HashMap<String, Object>();
        Map<String, Object> reqMap = bindParamToMap(request);
        String str = reqMap.get("accountProjectList").toString();
        List<Map<String, Object>> listMap = JSON.parseObject(str, new TypeReference<List<Map<String, Object>>>() {
        });
        Map<String, Object> queryParam = new HashMap<>();
        ResultData<?> reback;
        try {
            String cityNo = UserInfoHolder.get().getCityNo();
            queryParam.put("cityNo", cityNo);
            queryParam.put("accountProjectList", listMap);
            //20200702 校验核算主体-通过项目找到收入类合同(LNK_Estate_Srlht)对应得核算
            queryParam.put("projectNo", reqMap.get("projectNo"));
            reback = sceneTradeService.checkAccountProject(queryParam);
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "查询失败！");
            return getOperateJSONView(rspMap);
        }
        if (reback.getReturnMsg() != null) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
        } else {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        }
        rspMap.put(Constant.RETURN_MSG_KEY, reback.getReturnMsg());
        return getOperateJSONView(rspMap);
    }

    @RequestMapping(value = "/updateBatchSaleDetailAll", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> updateBatchSaleDetailAll(HttpServletRequest request) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        String str = reqMap.get("param").toString();

        List<Map<String, Object>> listMap = JSON.parseObject(str, new TypeReference<List<Map<String, Object>>>() {
        });

        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("listMap", listMap);

        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        ResultData<?> resultData = new ResultData<>();

        try {
//            resultData=sceneEstateService.updateBatchSaleDetailAll(queryParam);
            resultData = sceneTradeService.updateBatchSaleDetailAll(queryParam);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
            if (null != resultData.getReturnData()) {
                rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
            }
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, "-1");
            reqMap.put(Constant.RETURN_MSG_KEY, "接口调用异常");
        }
        return getMapView(rspMap);
    }


    @RequestMapping(value = "/submitBatchSaleAll", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> submitBatchSaleAll(HttpServletRequest request) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        String str = reqMap.get("param").toString();

        List<Map<String, Object>> listMap = JSON.parseObject(str, new TypeReference<List<Map<String, Object>>>() {
        });

        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("userId", UserInfoHolder.getUserId());
        queryParam.put("userCode", UserInfoHolder.get().getUserCode());
        queryParam.put("userName", UserInfoHolder.get().getUserName());
        queryParam.put("listMap", listMap);
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        ResultData<?> resultData = new ResultData<>();

        try {
//            resultData=sceneEstateService.submitBatchSaleAll(queryParam);
            resultData = sceneTradeService.submitBatchSaleAll(queryParam);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
            if (null != resultData.getReturnData()) {
                rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
            }
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, "-1");
            rspMap.put(Constant.RETURN_MSG_KEY, "接口调用异常！");
        }
        return getMapView(rspMap);
    }

    @RequestMapping(value = "/deleteBatchSaleDetailById", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> deleteBatchSaleDetailById(HttpServletRequest request) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        ResultData<?> resultData = new ResultData<>();
        try {
//            resultData=sceneEstateService.deleteBatchSaleDetailById(reqMap);
            resultData = sceneTradeService.deleteBatchSaleDetailById(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
            if (null != resultData.getReturnData()) {
                rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
            }
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, "-1");
            rspMap.put(Constant.RETURN_MSG_KEY, "接口调用异常");
        }

        return getMapView(rspMap);
    }


    @RequestMapping(value = "exportCheck/{batchId}", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> exportCheck(@PathVariable("batchId") Integer batchId, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> rspMap = new HashMap<String, Object>();
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userCode", UserInfoHolder.get().getUserCode());
        ResultData<?> reback;
        try {
            reback = sceneTradeService.getBatchSaleDetailList(batchId);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();
            ;
            if (contentlist == null || contentlist.isEmpty()) {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "没有数据，不能导出Excel！");
                return getOperateJSONView(rspMap);
            }
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导出失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        rspMap.put(Constant.RETURN_MSG_KEY, "导出成功");
        return getOperateJSONView(rspMap);
    }

    @RequestMapping(value = "export/{batchId}", method = RequestMethod.GET)
    public void export(@PathVariable("batchId") Integer batchId, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userCode", UserInfoHolder.get().getUserCode());

        try {
            ResultData<?> reback = sceneTradeService.getBatchSaleDetailList(batchId);
            reqMap.put("userId", UserInfoHolder.getUserId());
            reqMap.put("userName", UserInfoHolder.get().getUserName());
            List<LinkedHashMap<String, Object>> contentList = (List<LinkedHashMap<String, Object>>) reback.getReturnData();
            String str = "批量成销模板_" + DateUtils.formatDate(new Date(), "yyyyMMdd") + ".xlsx";
            downLoadExcel(request, response, contentList, reqMap, ReportConstant.BATCHSALE_CODE, str);

        } catch (Exception e) {
            logger.error("sceneTrade",
                    "SceneTradeController",
                    "export",
                    "input param: reqMap=" + reqMap.toString(), -
                            UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "导出异常",
                    e);
        }
    }


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
        if (ReportConstant.BATCHSALE_CODE.equals(fileCode)) {
            // 批量成销模板
            instance = new ExcelForBatchSale();
        }

        String pathName = url + File.separator + time + File.separator + fileName;

        try {
            instance.downloadSheet(contentList, new File(pathName));
            super.setExportSuccess((String) reqMap.get("cookieName"), response);
            super.download(request, response, pathName, fileName);
        } catch (IOException e) {

            response.setCharacterEncoding("GBK");
            response.getWriter().write("下载Excel失败" + e.getMessage());
            response.getWriter().close();
            logger.error("下载Excel失败", e);

        }
    }

    //region 导入

    @RequestMapping(value = "imput", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> imput(HttpServletRequest request, HttpServletResponse response) {
        // 返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        MultipartFile multFile = multiRequest.getFile("file");

        Workbook wb;
        try {
            wb = new XSSFWorkbook(multFile.getInputStream());
            int number = wb.getNumberOfSheets();
            if (number > 1) {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "Excel文档格式错误,不允许多个sheet！");
                return getSearchJSONView(rspMap);
            }
            Sheet sheet1 = wb.getSheetAt(0);

            //导入check
            Map<String, Object> rtnMap = checkImport(sheet1);
            if (null != rtnMap) {
                return getSearchJSONView(rtnMap);
            }

            List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
            for (Row r : sheet1) {
                Integer reault = getSheetCellValue(wb, r, mapList);
                if (reault == 1) {
                    continue;
                } else if (reault == 2) {
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    rspMap.put(Constant.RETURN_MSG_KEY, "数据不完整，请调整后再导入！");
                    return getSearchJSONView(rspMap);
                }
            }
            //数据导入
            ResultData insertResult = insertBatchImport(mapList);
            if (!"200".equals(insertResult.getReturnCode())) {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "导入数据失败！");
                return getSearchJSONView(rspMap);
            }

            wb.close();

            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
            rspMap.put(Constant.RETURN_MSG_KEY, insertResult.getReturnMsg());
            rspMap.put(Constant.RETURN_DATA_KEY, insertResult.getReturnData());
        } catch (IOException e1) {
            logger.error("sceneEstate", "SceneTradeController", "imput", "", null, "", "", e1);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导入数据失败！");
        }

        return getSearchJSONView(rspMap);
    }

    /**
     * 导入Check
     *
     * @return
     */
    private Map<String, Object> checkImport(Sheet sheet1) {
        Map<String, Object> rspMap = new HashMap<String, Object>();
        if (sheet1.getLastRowNum() == 0) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导入文件数据格式有误！");
            return rspMap;
        }

        //密码验证
        if (isSheetModify((XSSFSheet) sheet1)) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导入数据模板不对，请重新下载模板数据！");
            return rspMap;
        }

        String userId = sheet1.getRow(0).getCell(0).getStringCellValue();

        //判断操作人的工号是否和模板的用户工号一致
        if (!userId.equals(UserInfoHolder.getUserId().toString())) {
            String userName = sheet1.getRow(0).getCell(3).getStringCellValue();
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导入模板为" + userName + "下载的，请使用自己下载的模板进行导入！");
            return rspMap;
        }
        return null;
    }

    //验证excel密码
    private boolean isSheetModify(XSSFSheet sheet) {
        boolean haveModify = true;
        try {
            String pwd = sheet.getRow(0).getCell(1).getStringCellValue();
            CTSheetProtection csheet = sheet.getCTWorksheet().getSheetProtection();
            if (csheet != null) {
                STUnsignedShortHex passwordST = csheet.xgetPassword();
                if (passwordST != null || !passwordST.getStringValue().equals(pwd)) {
                    haveModify = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return haveModify;
    }

    /**
     * 取得excel值
     * 1,跳过列头2,调整列数据填写错误，请调整导表后重新导入
     *
     * @param r excel行
     * @param
     * @return
     */
    private Integer getSheetCellValue(Workbook wb, Row r, List<Map<String, Object>> mapList) {

        //跳过列头
        if (r.getRowNum() < 2) {
            return 1;
        }

        Map<String, Object> map = new HashMap<>();

        //报备编号
        String reportId = getCellValue(wb, r.getCell(0));
        //楼室号
        String floor = getCellValue(wb, r.getCell(1));
        //税前
        String saleAcreage = getCellValue(wb, r.getCell(2));
        //税后
        String saleAmount = getCellValue(wb, r.getCell(3));

        String accountProject = getCellValue(wb, r.getCell(4));
        //税前
        String befYjsrTaxAmount = getCellValue(wb, r.getCell(5));
        //税后
        String aftYjsrTaxAmount = getCellValue(wb, r.getCell(6));

        //税前
        String befYjfyTaxAmount = getCellValue(wb, r.getCell(7));
        //税后
        String aftYjfyTaxAmount = getCellValue(wb, r.getCell(8));
        //税前
        String befYjdyTaxAmount = getCellValue(wb, r.getCell(9));
        //税后
        String aftYjdyTaxAmount = getCellValue(wb, r.getCell(10));

//        // 房友返佣
//        String befFangyouYJFYAmount = getCellValue(wb, r.getCell(11));
//        String aftFangyouYJFYAmount = getCellValue(wb, r.getCell(12));

        //检查数据 应收收入等列有数据，调整列才允许填写数据
        if (StringUtil.isEmpty(accountProject) || StringUtil.isEmpty(befYjsrTaxAmount)
                || StringUtil.isEmpty(aftYjsrTaxAmount) || StringUtil.isEmpty(befYjfyTaxAmount) || StringUtil.isEmpty(aftYjfyTaxAmount)) {
            return 2;
        }
//        if(StringUtil.isEmpty(befYjdyTaxAmount)){
//            if(!StringUtil.isEmpty(aftYjdyTaxAmount)){
//                return 2;
//            }
//        }else{
//            if(StringUtil.isEmpty(aftYjdyTaxAmount)){
//                return 2;
//            }
//        }
        //只要填了值就添加
//        if (StringUtil.isNotEmpty(accountProject)
//                && StringUtil.isNotEmpty(befYjsrTaxAmount) && StringUtil.isNotEmpty(aftYjsrTaxAmount)
//                && StringUtil.isNotEmpty(befYjfyTaxAmount) && StringUtil.isNotEmpty(aftYjfyTaxAmount)
//                ) {
        //数据添加
        map.put("reportId", reportId);
        map.put("floor", floor);
        map.put("saleAcreage", saleAcreage);
        map.put("saleAmount", saleAmount);
        map.put("accountProject", accountProject);
        map.put("befYjsrTaxAmount", befYjsrTaxAmount);
        map.put("aftYjsrTaxAmount", aftYjsrTaxAmount);

//        map.put("befFangyouYJFYAmount", befFangyouYJFYAmount);
//        map.put("aftFangyouYJFYAmount", aftFangyouYJFYAmount);

        map.put("befYjfyTaxAmount", befYjfyTaxAmount);
        map.put("aftYjfyTaxAmount", aftYjfyTaxAmount);
        map.put("befYjdyTaxAmount", befYjdyTaxAmount);
        map.put("aftYjdyTaxAmount", aftYjdyTaxAmount);
        mapList.add(map);
//        }
        return 0;
    }

    /**
     * 导入操作
     *
     * @param mapList
     * @return
     */
    private ResultData<List<BatchSaleDetail>> insertBatchImport(List<Map<String, Object>> mapList) {
        ResultData<List<BatchSaleDetail>> resultData = new ResultData<List<BatchSaleDetail>>();
        try {
            List<BatchSaleDetail> batchSaleDetails = new ArrayList<BatchSaleDetail>();
            //批量处理数据
            for (Map<String, Object> map : mapList) {
                String reportId = map.get("reportId").toString();
                String floor = map.get("floor").toString();
                String saleAcreage = map.get("saleAcreage").toString();
                String saleAmount = map.get("saleAmount").toString();
                String accountProject = map.get("accountProject").toString();
                String befYjsrTaxAmount = map.get("befYjsrTaxAmount").toString();
                String aftYjsrTaxAmount = map.get("aftYjsrTaxAmount").toString();

                String befYjfyTaxAmount = map.get("befYjfyTaxAmount").toString();
                String aftYjfyTaxAmount = map.get("aftYjfyTaxAmount").toString();
                String befYjdyTaxAmount = map.get("befYjdyTaxAmount").toString();
                String aftYjdyTaxAmount = map.get("aftYjdyTaxAmount").toString();

//                String befFangyouYJFYAmount = map.get("befFangyouYJFYAmount").toString();
//                String aftFangyouYJFYAmount = map.get("aftFangyouYJFYAmount").toString();

                BatchSaleDetail batchSaleDetail = new BatchSaleDetail();
                batchSaleDetail.setReportId(reportId);
                batchSaleDetail.setFloor(floor);
                batchSaleDetail.setSaleAcreage(new BigDecimal(saleAcreage));
                batchSaleDetail.setSaleAmount(new BigDecimal(saleAmount));
                batchSaleDetail.setAccountProject(accountProject);
                batchSaleDetail.setBefYjsrTaxAmount(new BigDecimal(befYjsrTaxAmount));
                batchSaleDetail.setAftYjsrTaxAmount(new BigDecimal(aftYjsrTaxAmount));

//                if (befFangyouYJFYAmount.equals("")) {
//                    batchSaleDetail.setBefFangyouYJFYAmount(null);
//                } else {
//                    batchSaleDetail.setBefFangyouYJFYAmount(new BigDecimal(befFangyouYJFYAmount));
//                }
//
//                if (aftFangyouYJFYAmount.equals("")) {
//                    batchSaleDetail.setAftFangyouYJFYAmount(null);
//                } else {
//                    batchSaleDetail.setAftFangyouYJFYAmount(new BigDecimal(aftFangyouYJFYAmount));
//                }

                batchSaleDetail.setBefYjfyTaxAmount(new BigDecimal(befYjfyTaxAmount));
                batchSaleDetail.setAftYjfyTaxAmount(new BigDecimal(aftYjfyTaxAmount));
                if (befYjdyTaxAmount.equals("")) {
                    batchSaleDetail.setBefYjdyTaxAmount(null);
                } else {
                    batchSaleDetail.setBefYjdyTaxAmount(new BigDecimal(befYjdyTaxAmount));
                }
                if (aftYjdyTaxAmount.equals("")) {
                    batchSaleDetail.setAftYjdyTaxAmount(null);
                } else {
                    batchSaleDetail.setAftYjdyTaxAmount(new BigDecimal(aftYjdyTaxAmount));
                }
                batchSaleDetails.add(batchSaleDetail);
            }
            resultData.setReturnData(batchSaleDetails);
            resultData.setReturnMsg("导入数据成功！");
            return resultData;
        } catch (Exception e) {
            resultData.setReturnCode("-1");
            return resultData;
        }
    }

    //excel类型判断
    private String getCellValue(Workbook wb, Cell cell) {
        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
        String rtnVal = "";

        DecimalFormat df = new DecimalFormat("0.00");
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            //判断是否为日期类型
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                //用于转化为日期格式
                Date d = cell.getDateCellValue();
                DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                rtnVal = formater.format(d);
            } else {
                // 用于格式化数字，只保留数字的整数部分
                rtnVal = df.format(cell.getNumericCellValue());
            }
        }
        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            rtnVal = cell.getStringCellValue();
        }
        if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            evaluator.evaluateFormulaCell(cell);
            rtnVal = df.format(cell.getNumericCellValue());
        }
        return rtnVal;
    }

    //endregion


    /**
     * desc:查询列表
     * 2019年7月26日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/queryAccountProjectList", method = RequestMethod.POST)
    public ResultData<?> queryAccountProjectList(HttpServletRequest request, ModelMap mop, HttpServletResponse response) {

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        // 分页信息
        PageInfo pageInfo = getPageInfo(request);

        UserInfo userInfo = UserInfoHolder.get();
        if (userInfo != null) {
            reqMap.put("userId", userInfo.getUserId());
        }

        ResultData<?> resultData = new ResultData<>();
        try {

            // 归属城市列表
            ResultData<?> resultCity = sceneTradeService.getAreaCity(reqMap);
            List<Map<String, Object>> listmap = (List<Map<String, Object>>) resultCity.getReturnData();
            List<String> cityNoList = new ArrayList<>();
            for (int i = 0; i < listmap.size(); i++) {
                Map tempMop = (Map) listmap.get(i);
                String cityNo = (String) tempMop.get("cityNo");
                cityNoList.add(cityNo);
            }
            reqMap.put("cityNoList", cityNoList);

            //获取页面显示数据
            resultData = sceneTradeService.accountProjectList(reqMap, pageInfo);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("sceneTrade", "SceneTradeController", "queryAccountProjectList", "",
                    UserInfoHolder.getUserId(), "", "查询列表数据-取得核算主体列表失败", e);
        }

        return resultData;
    }


    /**
     * desc:删除
     * 2019年7月26日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/deleteAccountProject", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> deleteAccountProject(HttpServletRequest request, ModelMap mop) {

        ResultData<?> resultData = new ResultData<>();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);

        try {
            UserInfo userInfo = UserInfoHolder.get();
            if (userInfo != null) {
                reqMap.put("userIdCreate", userInfo.getUserId());
            }

            // 提交删除人员维护信息
            resultData = sceneTradeService.deleteAccountProject(reqMap);

        } catch (Exception e) {
            resultData.setFail("删除核算主体信息处理失败");
            logger.error("accountProject", "AccountProjectController", "delete", "",
                    UserInfoHolder.getUserId(), "", "删除核算主体信息处理失败！", e);

        }

        return resultData;
    }


    @RequestMapping(value = "/saveAccountProject", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> saveAccountProject(HttpServletRequest request, ModelMap mop) {

        ResultData<?> resultData = new ResultData<>();

        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);

        try {
            UserInfo userInfo = UserInfoHolder.get();
            if (userInfo != null) {
                reqMap.put("userIdCreate", userInfo.getUserId());
            }
            // 新增保存人员维护信息
            resultData = sceneTradeService.saveAccountProject(reqMap);

        } catch (Exception e) {
            resultData.setFail("保存失败!");
            logger.error("sceneTrade", "SceneTradeController", "save", "",
                    UserInfoHolder.getUserId(), "", "保存核算主体信息处理失败！", e);
        }

        return resultData;
    }


    @RequestMapping(value = "/updateAccountProject", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> updateAccountProject(HttpServletRequest request, ModelMap mop) {

        ResultData<?> resultData = new ResultData<>();

        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);

        //核算主体编号
        String accountProjectNos = (String) reqMap.get("accountProjectNos");
        if (StringUtils.isEmpty(accountProjectNos)) {
            resultData.setFail("请选择核算主体!");
            return resultData;
        }
        try {
            UserInfo userInfo = UserInfoHolder.get();
            if (userInfo != null) {
                reqMap.put("userIdCreate", userInfo.getUserId());
            }

            // 提交更新人员维护信息
            resultData = sceneTradeService.updateAccountProject(reqMap);

        } catch (Exception e) {
            resultData.setFail("更新失败!");
            logger.error("accountProject", "AccountProjectController", "update", "",
                    UserInfoHolder.getUserId(), "", "更新核算主体信息处理失败！", e);
            ;
        }

        return resultData;
    }

    /**
     * 选择业绩归属人
     *
     * @return
     */
    @RequestMapping(value = "selUser", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView selUser(HttpServletRequest request, ModelMap mop) {
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.put("reqMap", reqMap);
        ModelAndView mv = new ModelAndView("scene/sceneTrade/selUser");
        return mv;
    }

    /**
     * 模糊查询业绩人
     *
     * @param mop
     * @return
     */
    @RequestMapping(value = "getLinkUserByCondition", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> getLinkUserByCondition(HttpServletRequest request, ModelMap mop) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            reqMap.put("acCityNo", UserInfoHolder.get().getCityNo());
            //获取页面显示数据
            resultData = sceneTradeService.getLinkUserByCondition(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("sceneTrade",
                    "SceneTradeController",
                    "getLinkUserByCondition",
                    "",
                    UserInfoHolder.getUserId(),
                    "",
                    "模糊查询业绩人",
                    e);
        }

        return resultData;
    }


    /**
     * 选择合作协议
     *
     * @return
     */
    @RequestMapping(value = "selContract", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView selContract(HttpServletRequest request, ModelMap mop) {
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.put("reqMap", reqMap);
        ModelAndView mv = new ModelAndView("scene/sceneTrade/selContract");
        return mv;
    }

    /**
     * 选择房友中心
     *
     * @return
     */
    @RequestMapping(value = "selFyUser", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView selFyUser(HttpServletRequest request, ModelMap mop) {
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.put("reqMap", reqMap);
        ModelAndView mv = new ModelAndView("scene/sceneTrade/selFyUser");
        return mv;
    }
/*************************************批量退房**************************************/
    /**
     * 加入批量退房
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/addBatchReback", method = RequestMethod.GET)
    public ResultData<?> addBatchReback(HttpServletRequest request) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userCode", UserInfoHolder.get().getUserCode());
        Integer userId = UserInfoHolder.getUserId();
        //返回map
        ResultData<?> resultData = new ResultData<>();

        try {
            logger.info("加入批量退房,param:", reqMap.toString());
            resultData = sceneTradeService.addBatchReback(reqMap);
            logger.info("加入批量退房,in-param:" + reqMap.toString(), resultData);
        } catch (Exception e) {
            resultData.setFail("加入批量退房异常");
            logger.error("加入批量退房异常,param:" + reqMap.toString(), e);
            logger.error("sceneTrade", "SceneTradeController",
                    "addBatchReback", "", userId, "", "加入批量退房异常", e);
        }

        return resultData;
    }

    @RequestMapping(value = "checkSjfy", method = RequestMethod.POST)
    public ResultData<?> checkSjfy(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = new ResultData<>();
        try {
            resultData = sceneTradeService.checkSjfy(reqMap);
        } catch (Exception e) {
            resultData.setFail("校验失败！");
        }
        return resultData;
    }

    /**
     * desc:批量退房-新增
     * 2019年12月5日
     */
    @RequestMapping(value = "toBatchRebackView", method = RequestMethod.GET)
    public ModelAndView toBatchRebackView(ModelMap mop,
                                          HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        String projectNo = (String) reqMap.get("projectNo");
        // 获取用户code
        String userCode = UserInfoHolder.get().getUserCode();
        reqMap.put("userCode", userCode);
        mop.addAttribute("reqMap", reqMap);
        String cityNo = UserInfoHolder.get().getCityNo();
        try {
            logger.info("获取批量新增退房,projectNo:", projectNo);
            ResultData<?> resultData = sceneTradeService.getBatchRebackList(reqMap);
            logger.info("获取批量新增退房,projectNo:" + projectNo, resultData);

            mop.addAttribute("batchReback", JSONObject.toJSON(resultData.getReturnData()));

        } catch (Exception ex) {
            logger.error("获取楼盘详情信息异常,projectNo:" + projectNo, ex);
            logger.error("sceneTrade", "SceneTradeController", "toBatchRebackView", projectNo, null, "", "", ex);
        }

        return new ModelAndView("scene/sceneTrade/batchRebackList");
    }

    /**
     * desc:批量退房-删除
     * 2019年12月5日
     */
    @RequestMapping(value = "/deleteBatchRebackDetailById", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> deleteBatchRebackDetailById(HttpServletRequest request) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        ResultData<?> resultData = new ResultData<>();
        try {
            resultData = sceneTradeService.deleteBatchRebackDetailById(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
            if (null != resultData.getReturnData()) {
                rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
            }
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, "-1");
            rspMap.put(Constant.RETURN_MSG_KEY, "接口调用异常");
        }

        return getMapView(rspMap);
    }


    /**
     * 批量退房-保存
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateBatchRebackDetailAll", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> updateBatchRebackDetailAll(HttpServletRequest request) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        String str = reqMap.get("param").toString();

        List<Map<String, Object>> listMap = JSON.parseObject(str, new TypeReference<List<Map<String, Object>>>() {
        });

        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("listMap", listMap);

        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        ResultData<?> resultData = new ResultData<>();

        try {
            resultData = sceneTradeService.updateBatchRebackDetailAll(queryParam);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
            if (null != resultData.getReturnData()) {
                rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
            }
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, "-1");
            reqMap.put(Constant.RETURN_MSG_KEY, "接口调用异常");
        }
        return getMapView(rspMap);
    }

    /**
     * 批量退房-提交
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/submitBatchReback", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> submitBatchReback(HttpServletRequest request) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        String str = reqMap.get("param").toString();

        List<Map<String, Object>> listMap = JSON.parseObject(str, new TypeReference<List<Map<String, Object>>>() {
        });

        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("userId", UserInfoHolder.getUserId());
        queryParam.put("userCode", UserInfoHolder.get().getUserCode());
        queryParam.put("userName", UserInfoHolder.get().getUserName());
        queryParam.put("listMap", listMap);
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        ResultData<?> resultData = new ResultData<>();

        try {
            resultData = sceneTradeService.submitBatchReback(queryParam);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
            if (null != resultData.getReturnData()) {
                rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
            }
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, "-1");
            rspMap.put(Constant.RETURN_MSG_KEY, "接口调用异常！");
        }
        return getMapView(rspMap);
    }

    /**
     * 批量退房-导入
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "imputReback", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> imputReback(HttpServletRequest request) {
        // 返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        MultipartFile multFile = multiRequest.getFile("file");

        Workbook wb;
        try {
            wb = new XSSFWorkbook(multFile.getInputStream());
            int number = wb.getNumberOfSheets();
            if (number > 1) {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "Excel文档格式错误,不允许多个sheet！");
                return getSearchJSONView(rspMap);
            }
            Sheet sheet1 = wb.getSheetAt(0);

            //导入check
            Map<String, Object> rtnMap = checkImport(sheet1);
            if (null != rtnMap) {
                return getSearchJSONView(rtnMap);
            }

            List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
            for (Row r : sheet1) {
                Integer result = getSheetCellValueForReback(wb, r, mapList);
                if (result == 1) {
                    continue;
                } else if (result == 2) {
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    rspMap.put(Constant.RETURN_MSG_KEY, "退房日期数据不完整，请调整后再导入！");
                    return getSearchJSONView(rspMap);
                }
            }
            //数据导入
            ResultData insertResult = new ResultData();
            insertResult.setReturnData(mapList);
            wb.close();

            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
            rspMap.put(Constant.RETURN_MSG_KEY, insertResult.getReturnMsg());
            rspMap.put(Constant.RETURN_DATA_KEY, insertResult.getReturnData());
        } catch (IOException e1) {
            logger.error("sceneTrade", "SceneTradeController", "imputReback", "", null, "", "", e1);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导入数据失败！");
        }

        return getSearchJSONView(rspMap);
    }

    /**
     * 取得excel值
     * 1,跳过列头2,调整列数据填写错误，请调整导表后重新导入
     *
     * @param r excel行
     * @param
     * @return
     */
    private Integer getSheetCellValueForReback(Workbook wb, Row r, List<Map<String, Object>> mapList) {

        //跳过列头
        if (r.getRowNum() < 2) {
            return 1;
        }

        Map<String, Object> map = new HashMap<>();

        //退房日期
        String rebackDate = getCellValue(wb, r.getCell(9));
        if (StringUtil.isEmpty(rebackDate)) {
            return 2;
        }
        //报备编号
        String reportId = getCellValue(wb, r.getCell(0));
        //数据添加
        map.put("reportId", reportId);
        map.put("rebackDate", rebackDate);
        mapList.add(map);
        return 0;
    }

    /**
     * desc:批量退房-导出check
     * 2019年12月6日
     */
    @RequestMapping(value = "exportRebackCheck", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> exportRebackCheck(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> rspMap = new HashMap<String, Object>();
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userCode", UserInfoHolder.get().getUserCode());
        ResultData<?> reback;
        try {
            reback = sceneTradeService.getBatchRebackList(reqMap);
            reqMap.put("userId", UserInfoHolder.getUserId());
            reqMap.put("userName", UserInfoHolder.get().getUserName());
            Map<String, Object> map = (Map<String, Object>) reback.getReturnData();
            List<LinkedHashMap<String, Object>> contentList = (List<LinkedHashMap<String, Object>>) map.get("batchRebackDetails");
            if (contentList == null || contentList.isEmpty()) {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "没有数据，不能导出Excel！");
                return getOperateJSONView(rspMap);
            }
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导出失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        rspMap.put(Constant.RETURN_MSG_KEY, "导出成功");
        return getOperateJSONView(rspMap);
    }

    /**
     * desc:批量退房-导出
     * 2019年12月6日
     */
    @RequestMapping(value = "exportReback", method = RequestMethod.GET)
    public void exportReback(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userCode", UserInfoHolder.get().getUserCode());

        try {
            ResultData<?> reback = sceneTradeService.getBatchRebackList(reqMap);
            reqMap.put("userId", UserInfoHolder.getUserId());
            reqMap.put("userName", UserInfoHolder.get().getUserName());
            Map<String, Object> map = (Map<String, Object>) reback.getReturnData();
            List<LinkedHashMap<String, Object>> contentList = (List<LinkedHashMap<String, Object>>) map.get("batchRebackDetails");
            String str = "批量退房模板_" + DateUtils.formatDate(new Date(), "yyyyMMdd") + ".xlsx";
            super.downLoadExcel(request, response, contentList, reqMap, ReportConstant.BATCHREBACK_CODE, str);

        } catch (Exception e) {
            logger.error("sceneTrade",
                    "SceneTradeController",
                    "export",
                    "input param: reqMap=" + reqMap.toString(), -
                            UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "导出异常",
                    e);
        }
    }

    /*************************************批量退房**************************************/

    @RequestMapping(value = "selectBuildingNoRepeatCount", method = RequestMethod.POST)
    @ResponseBody
    public String selectBuildingNoRepeatCount(HttpServletRequest request) {
        ResultData<?> resultData = new ResultData<>();
        Map<String, Object> map = bindParamToMap(request);
        try {
            resultData = sceneTradeService.selectBuildingNoRepeatCount(map.get("buildingNo").toString(), map.get("reportId").toString());

        } catch (Exception e) {
            resultData.setFail("楼室号判重查询异常");
            logger.error("sceneTrade", "SceneTradeController", "selectBuildingNoRepeatCount"
                    , JsonUtil.parseToJson(map), null, "", "楼室号判重查询异常", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "getYHApproveCheckArteryType/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getYHApproveCheckArteryType(HttpServletRequest request, @PathVariable Integer id) {
        ResultData<?> resultData = new ResultData<>();
        try {
            resultData = sceneTradeService.getYHApproveCheckArteryType(id);

        } catch (Exception e) {
            logger.error("大定审核校验发生异常", e);
            resultData.setFail("大定审核失败");
            logger.error("sceneTrade", "SceneTradeController", "getYHApproveCheckArteryType"
                    , id.toString(), null, "", "大定审核失败", e);
        }
        return resultData.toString();
    }

    //endregion


    @RequestMapping(value = "getYJtableList/{item}/{reportId}", method = RequestMethod.POST)
    public ResultData<?> getYJtableList(@PathVariable String item,
                                        @PathVariable String reportId,
                                        HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;

        try {
            reqMap.put("item", item);
            reqMap.put("reportId", reportId);

            logger.info("报备详情取得佣金列表信息入参:", reqMap.toString());
            resultData = sceneTradeService.getYJtableList(reqMap);
            logger.info("报备详情取得佣金列表信息结果:", resultData.toString());

        } catch (Exception e) {
            logger.error("报备详情取得佣金列表信息异常;input param: reqMap=" + reqMap.toString(), e);

            logger.error("scene",
                    "SceneTradeController",
                    "getYJtableList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "报备详情取得佣金列表信息异常",
                    e);
        }
        return resultData;


    }


    @RequestMapping(value = "/uptPreBack/{id}/{preBack}/{reportId}", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> uptPreBack(HttpServletRequest request
                ,@PathVariable("id")Integer id,@PathVariable("preBack")String preBack
                ,@PathVariable("reportId")String reportId) {
        ReturnView returnView = new ReturnView();
        ResultData resultData = new ResultData();
        //请求map
        Map<String, Object> reqMap = new HashMap<>();
        try {
            reqMap.put("crtUser", UserInfoHolder.getUserId());
            reqMap.put("userCode", UserInfoHolder.get().getUserCode());
            reqMap.put("userName", UserInfoHolder.get().getUserName());
            reqMap.put("id",id);
            reqMap.put("preBack",preBack);
//            reqMap.put("preBackDate", new Date());
            reqMap.put("preBackUserCode", UserInfoHolder.get().getUserCode());
            reqMap.put("reportId",reportId);
            resultData = sceneTradeService.uptPreBack(reqMap);
        } catch (Exception e) {
            logger.error("交易管理##预退操作##异常"+JSON.toJSONString(reqMap));
            resultData.setFail("操作异常!");
            logger.error("sceneTrade", "SceneTradeController", "uptPreBack", reqMap.toString(), null, "", "交易管理预退操作", e);
        }
        returnView.setOperateReturnType();
        returnView.setReturnCode(resultData.getReturnCode());
        returnView.setReturnMsg(resultData.getReturnMsg());
        returnView.setReturnValue(resultData.getReturnData());

        return returnView;
    }



    @RequestMapping(value = "/getReport", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getReport(HttpServletRequest request) {
        ReturnView returnView = new ReturnView();
        ResultData resultData = new ResultData();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            resultData = sceneTradeService.getReport(reqMap);
        } catch (Exception e) {
            logger.error("交易管理##查询报备信息##异常"+JSON.toJSONString(reqMap));
            resultData.setFail("操作失败!");
            logger.error("sceneTrade", "SceneTradeController", "getReport", reqMap.toString(), null, "", "交易管理##查询报备信息##异常", e);
        }
        returnView.setOperateReturnType();
        returnView.setReturnCode(resultData.getReturnCode());
        returnView.setReturnMsg(resultData.getReturnMsg());
        returnView.setReturnValue(resultData.getReturnData());

        return returnView;
    }

    /**
     * desc:大定审核-获取垫佣控制规则
     * 2020年5月20日
     */
    @RequestMapping(value = "getNewDyRatio", method = RequestMethod.POST)
    public ResultData<?> getNewDyRatio(HttpServletRequest request) {

        ResultData<?> resultData = new ResultData<>();

        Integer userId = UserInfoHolder.getUserId();
        String userCode = UserInfoHolder.get().getUserCode();
        String cityNo = UserInfoHolder.get().getCityNo();
        String userName = UserInfoHolder.get().getUserName();
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userCode", userCode);
        reqMap.put("userId", userId);
        reqMap.put("userName", userName);
        reqMap.put("cityNo", cityNo);
        try {
            logger.info("大定审核-获取垫佣控制规则,param:", reqMap.toString());
            resultData = sceneTradeService.getNewDyRatio(reqMap);
            if ("-1".equals(resultData.getReturnCode())) {
                return resultData;
            }
            logger.info("大定审核-获取垫佣控制规则,in-param:" + reqMap.toString(),"返回:"+ resultData);

        } catch (Exception e) {
            resultData.setFail("大定审核-获取垫佣控制规则失败");
            logger.error("大定审核-获取垫佣控制规则异常,param:" + reqMap.toString(), e);
            logger.error("sceneTrade", "SceneTradeController",
                    "getNewDyRatio", "", userId, "", "大定审核-获取垫佣控制规则异常", e);
        }
        return resultData;

    }

    /**
     * desc:获取项目对应收入类合同得核算主体
     * 2020年7月1日
     */
    @RequestMapping(value = "getAccountProjectByProjectNo/{projectNo}", method = RequestMethod.GET)
    public ResultData<?> getAccountProjectByProjectNo(@PathVariable("projectNo") String projectNo, HttpServletRequest request) {
        ResultData<?> resultData = new ResultData<>();
        try {
            logger.info("获取项目对应收入类合同得核算主体,入参:projectNo:", projectNo);
            resultData = sceneTradeService.getAccountProjectByProjectNo(projectNo);
            logger.info("获取项目对应收入类合同得核算主体,入参:projectNo=" + projectNo+",结果集:"+ resultData.toString());
        } catch (Exception ex) {
            logger.error("获取项目对应收入类合同得核算主体异常;input param: projectNo=" + projectNo.toString(), ex);
        }
        return resultData;
    }
    
}
