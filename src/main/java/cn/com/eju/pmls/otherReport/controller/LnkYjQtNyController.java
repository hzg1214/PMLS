package cn.com.eju.pmls.otherReport.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.FileUtils;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.core.util.WebUtil;

import cn.com.eju.pmls.commission.service.CrmOriginalService;
import cn.com.eju.pmls.otherReport.dto.LnkYjNyDto;
import cn.com.eju.pmls.otherReport.service.LnkYjQtNyService;
import cn.com.eju.pmls.poi.ExcelForQtNy;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STUnsignedShortHex;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by eju on 2019/10/28.
 */

@Controller
@RequestMapping(value = "lnkYjQtNy")
public class LnkYjQtNyController extends BaseController {

    @Resource(name = "lnkYjQtNyService")
    private LnkYjQtNyService lnkYjQtNyService;

    @Resource(name = "crmOriginalService")
    private CrmOriginalService crmOriginalService;

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    //模板类型
    private String templateType= "";
    //业绩城市
    private String cityNo = "";

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap mop)
    {
        String cityNo = UserInfoHolder.get().getCityNo();
        try {
            //区域列表
//            ResultData<?> resultDistrictList = this.linkageCityService.getDistrictList(cityNo);
//            mop.put("districtList", resultDistrictList.getReturnData());

            //归属项目部列表
            Map<String,Object> reqMap = new HashMap<>();
            reqMap.put("cityNo", cityNo);
            ResultData<?> resultRebacklist = crmOriginalService.getProjectDepartment(reqMap);
            List<?> rebacklist = (List<?>) resultRebacklist.getReturnData();
            mop.put("rebacklist", rebacklist);


            //楼盘名称
            ResultData<?> resultEstateList = crmOriginalService.getEstateNmList(reqMap);
            List<?> estateList = (List<?>) resultEstateList.getReturnData();
            mop.put("estateList", estateList);
            mop.put("cityNo", cityNo);
            String countDateEndStr = DateUtil.fmtDate(new Date(),"yyyy-MM-dd");
            mop.put("countDateEndStr",countDateEndStr);

            mop.addAttribute("estateTypeList", SystemParam.getCodeListByKey("245"));

        } catch (Exception e) {
            logger.error("lnkYjQtNy", "LnkYjQtNyController", "", "", null, "", "查询基础信息失败！", e);
        }
        return "otherReport/lnkYjQtNyList";
    }

    @RequestMapping(value = "getLnkYjQtNyList", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> list(HttpServletRequest request, ModelMap mop) {

        String cityNo = UserInfoHolder.get().getCityNo();
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        PageInfo pageInfo = getPageInfo(request);
        //获取页面显示数据
        ResultData<?> reback = new ResultData<>();
        try {
            reqMap.put("cityNo", cityNo);
            reback = lnkYjQtNyService.getLnkYjQtNyList(reqMap, pageInfo);

        } catch (Exception e) {
            logger.error("lnkYjQtNy", "LnkYjQtNyController", "getLnkYjQtNyList", "", null, "", "", e);
            reback.setFail();
        }

/*        //页面数据
        List<?> contentlist = (List<?>) reback.getReturnData();

        //存放到mop中
        mop.addAttribute("contentlist", contentlist);*/

        return reback;
    }

    /*
     * 导出Excel
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response) {
        String cityNo = UserInfoHolder.get().getCityNo();
        Map<String, Object> rspMap = new HashMap<String, Object>();
        Map<String, Object> reqMap = bindParamToMap(request);

        try {
            reqMap.put("cityNo", cityNo);
            reqMap.put("optFlag",1);
            ResultData<?> reback = lnkYjQtNyService.getLnkYjQtNyList(reqMap, null);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            String ctxPath = request.getSession().getServletContext().getRealPath("/");
            String url = ctxPath + "data" + File.separator + "lnkYjQtNy";

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

            try {
                String templateTypeStr = "内佣模板";
                String pathName = url + File.separator + time + File.separator + templateTypeStr + ".xlsx";
                ExcelForQtNy instance = new ExcelForQtNy("qtny");
                Map<String, String> map = new HashMap<>();
                UserInfo userInfo = UserInfoHolder.get();
                map.put("UserId", String.valueOf(userInfo.getUserId()));
                map.put("UserName", userInfo.getUserName() + "(" + userInfo.getUserCode() + ")");
                map.put("templateType", templateTypeStr);
                map.put("cityNo", cityNo);

                instance.downloadSheet(map, contentlist, new File(pathName));

                String fileName = templateTypeStr + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx";
                super.download(request, response, pathName, fileName);
            } catch (Exception e) {
                response.setCharacterEncoding("GBK");
                response.getWriter().write("下载Excel失败" + e.getMessage());
                response.getWriter().close();
                logger.error("下载Excel失败", e);
                e.printStackTrace();
            }

        } catch (Exception e) {
            logger.error("lnkYjQtNy",
                    "LnkYjQtNyController",
                    "export",
                    "input param: reqMap=" + reqMap.toString(), -
                            UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "导出异常",
                    e);
        }
    }

    /*
     * 导出check
     */
    @RequestMapping(value = "exportCheck", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> exportCheck(HttpServletRequest request, HttpServletResponse response) {
        String cityNo = UserInfoHolder.get().getCityNo();
        Map<String, Object> rspMap = new HashMap<String, Object>();
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> reback;
        try {
            reqMap.put("cityNo", cityNo);
            reback = lnkYjQtNyService.getLnkYjQtNyList(reqMap, null);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();
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
        rspMap.put(Constant.RETURN_MSG_KEY, reback.getReturnMsg());
        return getOperateJSONView(rspMap);
    }


    @RequestMapping(value = "toView", method = RequestMethod.GET)
    public ModelAndView toView() {
        ModelAndView mv = new ModelAndView("otherReport/importExcellnkYjQtNyList");
        return mv;
    }

    /**
     * 导入
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "imput", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> imput(HttpServletRequest request, HttpServletResponse response) {
        // 返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

   /*     MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        MultipartFile multFile = multiRequest.getFile("historyDataFile");
        String fileName = multFile.getOriginalFilename();*/

        String estateType = reqMap.get("estateTypeImput").toString();   //模板類型

        String fileName = "";
        CommonsMultipartFile multFile = null;



        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                MultipartFile importFile = multiRequest.getFile(iter.next());
                //文件名
                fileName = importFile.getOriginalFilename();
                //MultipartFile转File
                multFile = (CommonsMultipartFile) importFile;
            }
        }

        Workbook wb;
        try {
            wb = new XSSFWorkbook(multFile.getInputStream());
            int  number= wb.getNumberOfSheets();
            if (number>1){
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "Excel文档格式错误,不允许多个sheet！");
                return getSearchJSONView(rspMap);
            }
            Sheet sheet1 = wb.getSheetAt(0);

            //导入check
            Map<String, Object> rtnMap = checkImport(sheet1, estateType);
            if (null != rtnMap) {
                return getSearchJSONView(rtnMap);
            }

            List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
            for (Row r : sheet1) {
                Integer reault = getSheetCellValue(wb, r, mapList);
                if (reault == 1) {
                    continue;
                }else if(reault == 2){
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    rspMap.put(Constant.RETURN_MSG_KEY, "调整列数据填写错误，请调整导表后重新导入！");
                    return getSearchJSONView(rspMap);
                }else if(reault==3){
                    String type = "内佣模板";
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    rspMap.put(Constant.RETURN_MSG_KEY, type+"日期不能大于当前日期！");
                    return getSearchJSONView(rspMap);
                }
            }
            //数据导入
            ResultData insertResult = insertLinkImport(mapList);
            //			Boolean insertResult = false;
            if (!"200".equals(insertResult.getReturnCode())) {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, insertResult.getReturnMsg());
                return getSearchJSONView(rspMap);
            }

            wb.close();

            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
            rspMap.put(Constant.RETURN_MSG_KEY, insertResult.getReturnMsg());
        } catch (IOException e1) {
            logger.error("lnkYjQtNy", "LnkYjQtNyController", "imput", "", null, "", "", e1);
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
    private Map<String, Object> checkImport(Sheet sheet1, String strTemplateType) {
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

        templateType = sheet1.getRow(0).getCell(2).getStringCellValue();//模板类型
        String userId = sheet1.getRow(0).getCell(0).getStringCellValue();
        cityNo = sheet1.getRow(0).getCell(4).getStringCellValue();//业绩城市

        //验证模板类型
        if (!strTemplateType.equals(templateType)) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "选择的模板类型与导入文件的模板类型不匹配！");
            return rspMap;
        }

        //判断操作人的工号是否和模板的用户工号一致
        if (!userId.equals(UserInfoHolder.getUserId().toString())) {
            String userName = sheet1.getRow(0).getCell(3).getStringCellValue();
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导入模板为" + userName + "下载的，请使用自己下载的模板进行导入！");
            return rspMap;
        }
        //判断操作人的业绩城市是否和模板的业绩城市一致
        if (!cityNo.equals(UserInfoHolder.get().getCityNo())) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导入模板对应的业绩城市和您的业绩城市不一致，请下载最新的模板进行导入！");
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
     *1,跳过列头2,调整列数据填写错误，请调整导表后重新导入
     * @param r excel行
     * @return
     */
    private Integer getSheetCellValue(Workbook wb, Row r, List<Map<String, Object>> mapList) {

        //跳过列头
        if (r.getRowNum() < 3) {
            return 1;
        }
        String reportNo = r.getCell(3).getStringCellValue();    //报备No

        Map<String, Object> map = new HashMap<>();
        //套数
        String num = getCellValue(wb, r.getCell(37));
        //税前
        String postAmountTotal = getCellValue(wb, r.getCell(7));
        //税后
        String totalAmountTotal = getCellValue(wb, r.getCell(8));
        //税前
        String postAmount = getCellValue(wb, r.getCell(9));
        //税后
        String totalAmount = getCellValue(wb, r.getCell(10));
        //日期
        String recordDate = getCellValue(wb, r.getCell(11));
        //税前
        String postAmount1 = getCellValue(wb, r.getCell(12));
        //税后
        String totalAmount1 = getCellValue(wb, r.getCell(13));
        //日期
        String recordDate1 = getCellValue(wb, r.getCell(14));
        //税前
        String postAmount2 = getCellValue(wb, r.getCell(15));
        //税后
        String totalAmount2 = getCellValue(wb, r.getCell(16));
        //日期
        String recordDate2 = getCellValue(wb, r.getCell(17));
        //税前
        String postAmount3 = getCellValue(wb, r.getCell(18));
        //税后
        String totalAmount3 = getCellValue(wb, r.getCell(19));
        //日期
        String recordDate3 = getCellValue(wb, r.getCell(20));
        //税前
        String postAmount4 = getCellValue(wb, r.getCell(21));
        //税后
        String totalAmount4 = getCellValue(wb, r.getCell(22));
        //日期
        String recordDate4 = getCellValue(wb, r.getCell(23));
        //税前
        String postAmount5 = getCellValue(wb, r.getCell(24));
        //税后
        String totalAmount5 = getCellValue(wb, r.getCell(25));
        //日期
        String recordDate5 = getCellValue(wb, r.getCell(26));
        //税前
        String postAmount6 = getCellValue(wb, r.getCell(27));
        //税后
        String totalAmount6 = getCellValue(wb, r.getCell(28));
        //日期
        String recordDate6 = getCellValue(wb, r.getCell(29));
        String switchFlag = getCellValue(wb, r.getCell(30));
        String switchFlag1 = getCellValue(wb, r.getCell(31));
        String switchFlag2 = getCellValue(wb, r.getCell(32));
        String switchFlag3 = getCellValue(wb, r.getCell(33));
        String switchFlag4 = getCellValue(wb, r.getCell(34));
        String switchFlag5 = getCellValue(wb, r.getCell(35));
        String switchFlag6 = getCellValue(wb, r.getCell(36));
        String reportId = getCellValue(wb, r.getCell(38));
        //检查数据 应收收入等列有数据，调整列才允许填写数据
        if((StringUtil.isNotEmpty(postAmount1)||StringUtil.isNotEmpty(totalAmount1)||StringUtil.isNotEmpty(postAmount2)
                ||StringUtil.isNotEmpty(totalAmount2)||StringUtil.isNotEmpty(postAmount3)
                ||StringUtil.isNotEmpty(totalAmount3)||StringUtil.isNotEmpty(recordDate1)
                ||StringUtil.isNotEmpty(recordDate2)||StringUtil.isNotEmpty(recordDate3)
                ||StringUtil.isNotEmpty(recordDate4)||StringUtil.isNotEmpty(recordDate5)||StringUtil.isNotEmpty(recordDate6)
                ||StringUtil.isNotEmpty(postAmount4)||StringUtil.isNotEmpty(postAmount5)||StringUtil.isNotEmpty(postAmount6)
                ||StringUtil.isNotEmpty(totalAmount4)||StringUtil.isNotEmpty(totalAmount5)||StringUtil.isNotEmpty(totalAmount6)
        )&&(StringUtil.isEmpty(postAmount)||StringUtil.isEmpty(totalAmount)||StringUtil.isEmpty(recordDate))){
            return 2;
        }
        /*//num为负不允许调整列有值
        if((StringUtil.isNotEmpty(befTaxAmount1)||StringUtil.isNotEmpty(aftTaxAmount1)||StringUtil.isNotEmpty(befTaxAmount2)
                ||StringUtil.isNotEmpty(aftTaxAmount2)||StringUtil.isNotEmpty(befTaxAmount3)
                ||StringUtil.isNotEmpty(aftTaxAmount3)||StringUtil.isNotEmpty(recordDate1)
                ||StringUtil.isNotEmpty(recordDate2)||StringUtil.isNotEmpty(recordDate3)
                ||StringUtil.isNotEmpty(recordDate4)||StringUtil.isNotEmpty(recordDate5)||StringUtil.isNotEmpty(recordDate6)
                ||StringUtil.isNotEmpty(befTaxAmount4)||StringUtil.isNotEmpty(befTaxAmount5)||StringUtil.isNotEmpty(befTaxAmount6)
                ||StringUtil.isNotEmpty(aftTaxAmount4)||StringUtil.isNotEmpty(aftTaxAmount5)||StringUtil.isNotEmpty(aftTaxAmount6)
            )&&("-1").equals(num)){
            return 2;
        }*/
        //日期必须小于当前日期
        try{
            if((StringUtil.isNotEmpty(recordDate) && new SimpleDateFormat("yyyy-MM-dd").parse(recordDate).after(new Date()))
                    ||(StringUtil.isNotEmpty(recordDate1) && new SimpleDateFormat("yyyy-MM-dd").parse(recordDate1).after(new Date()))
                    ||(StringUtil.isNotEmpty(recordDate2) && new SimpleDateFormat("yyyy-MM-dd").parse(recordDate2).after(new Date()))
                    ||(StringUtil.isNotEmpty(recordDate3) && new SimpleDateFormat("yyyy-MM-dd").parse(recordDate3).after(new Date()))
                    ||(StringUtil.isNotEmpty(recordDate4) && new SimpleDateFormat("yyyy-MM-dd").parse(recordDate4).after(new Date()))
                    ||(StringUtil.isNotEmpty(recordDate5) && new SimpleDateFormat("yyyy-MM-dd").parse(recordDate5).after(new Date()))
                    ||(StringUtil.isNotEmpty(recordDate6) && new SimpleDateFormat("yyyy-MM-dd").parse(recordDate6).after(new Date()))){
                return 3;
            }
        }catch (Exception e){
            logger.error("getSheetCellValue", "LnkYjQtNyController", "imput", "", null, "", "日期转换失败", e);
        }
        //只要填了值就添加数据库
        if (StringUtil.isNotEmpty(postAmountTotal) || StringUtil.isNotEmpty(totalAmountTotal)
                || StringUtil.isNotEmpty(postAmount) || StringUtil.isNotEmpty(totalAmount) || StringUtil.isNotEmpty(recordDate)) {
            //数据添加
            map.put("cityNo", cityNo);
            map.put("templateType", templateType);
            map.put("reportNo", reportNo);
            map.put("reportId",reportId);

            map.put("postAmountTotal", postAmountTotal);
            map.put("totalAmountTotal", totalAmountTotal);
            map.put("postAmount", postAmount);
            map.put("totalAmount", totalAmount);
            map.put("recordDate", recordDate);
            map.put("postAmount1", postAmount1);
            map.put("totalAmount1", totalAmount1);
            map.put("recordDate1", recordDate1);
            map.put("postAmount2", postAmount2);
            map.put("totalAmount2", totalAmount2);
            map.put("recordDate2", recordDate2);
            map.put("postAmount3", postAmount3);
            map.put("totalAmount3", totalAmount3);
            map.put("recordDate3", recordDate3);
            map.put("postAmount4", postAmount4);
            map.put("totalAmount4", totalAmount4);
            map.put("recordDate4", recordDate4);
            map.put("postAmount5", postAmount5);
            map.put("totalAmount5", totalAmount5);
            map.put("recordDate5", recordDate5);
            map.put("postAmount6", postAmount6);
            map.put("totalAmount6", totalAmount6);
            map.put("recordDate6", recordDate6);
            map.put("num",num);
            map.put("switchFlag",switchFlag);
            map.put("switchFlag1",switchFlag1);
            map.put("switchFlag2",switchFlag2);
            map.put("switchFlag3",switchFlag3);
            map.put("switchFlag4",switchFlag4);
            map.put("switchFlag5",switchFlag5);
            map.put("switchFlag6",switchFlag6);

            map.put("CrtEmpId", UserInfoHolder.getUserId());
            map.put("UptEmpId", UserInfoHolder.getUserId());
            map.put("delFlg", 0);
            mapList.add(map);
        }
        return 0;
    }

    /**
     * 导入操作
     *
     * @param mapList
     * @return
     */
    private ResultData insertLinkImport(List<Map<String, Object>> mapList) {
        ResultData resultData = new ResultData();
        try {
            List<LnkYjNyDto> yjNyDtoList = new ArrayList<LnkYjNyDto>();

            //批量处理数据
            for (Map<String, Object> map : mapList) {
                String cityNo = map.get("cityNo").toString();
                String reportNo = map.get("reportNo").toString();
                String reportId = map.get("reportId").toString();
                String CrtEmpId = map.get("CrtEmpId").toString();
                String UptEmpId = map.get("UptEmpId").toString();
                String delFlg = map.get("delFlg").toString();

                String postAmountTotal = map.get("postAmountTotal").toString();
                String totalAmountTotal = map.get("totalAmountTotal").toString();
                String postAmount = map.get("postAmount").toString();
                String totalAmount = map.get("totalAmount").toString();
                String recordDate = map.get("recordDate").toString();
                String postAmount1 = map.get("postAmount1").toString();
                String totalAmount1 = map.get("totalAmount1").toString();
                String recordDate1 = map.get("recordDate1").toString();
                String postAmount2 = map.get("postAmount2").toString();
                String totalAmount2 = map.get("totalAmount2").toString();
                String recordDate2 = map.get("recordDate2").toString();
                String postAmount3 = map.get("postAmount3").toString();
                String totalAmount3 = map.get("totalAmount3").toString();
                String recordDate3 = map.get("recordDate3").toString();
                String postAmount4 = map.get("postAmount4").toString();
                String totalAmount4 = map.get("totalAmount4").toString();
                String recordDate4 = map.get("recordDate4").toString();
                String postAmount5 = map.get("postAmount5").toString();
                String totalAmount5 = map.get("totalAmount5").toString();
                String recordDate5 = map.get("recordDate5").toString();
                String postAmount6 = map.get("postAmount6").toString();
                String totalAmount6 = map.get("totalAmount6").toString();
                String recordDate6 = map.get("recordDate6").toString();
                String num = map.get("num").toString();
                String switchFlag = map.get("switchFlag").toString();
                String switchFlag1 = map.get("switchFlag1").toString();
                String switchFlag2 = map.get("switchFlag2").toString();
                String switchFlag3 = map.get("switchFlag3").toString();
                String switchFlag4 = map.get("switchFlag4").toString();
                String switchFlag5 = map.get("switchFlag5").toString();
                String switchFlag6 = map.get("switchFlag6").toString();

                LnkYjNyDto yjNyDto = new LnkYjNyDto();
                yjNyDto.setCityNo(cityNo);
                yjNyDto.setReportNo(reportNo);
                yjNyDto.setReportId(reportId);
                yjNyDto.setCrtEmpId(CrtEmpId);
                yjNyDto.setUptEmpId(UptEmpId);
                yjNyDto.setDelFlag(delFlg);

                yjNyDto.setPostAmountTotal(postAmountTotal);
                yjNyDto.setTotalAmountTotal(totalAmountTotal);
                yjNyDto.setPostAmount(postAmount);
                yjNyDto.setTotalAmount(totalAmount);
                yjNyDto.setRecordDate(recordDate);
                yjNyDto.setPostAmount1(postAmount1);
                yjNyDto.setTotalAmount1(totalAmount1);
                yjNyDto.setRecordDate1(recordDate1);
                yjNyDto.setPostAmount2(postAmount2);
                yjNyDto.setTotalAmount2(totalAmount2);
                yjNyDto.setRecordDate2(recordDate2);
                yjNyDto.setPostAmount3(postAmount3);
                yjNyDto.setTotalAmount3(totalAmount3);
                yjNyDto.setRecordDate3(recordDate3);
                yjNyDto.setPostAmount4(postAmount4);
                yjNyDto.setTotalAmount4(totalAmount4);
                yjNyDto.setRecordDate4(recordDate4);
                yjNyDto.setPostAmount5(postAmount5);
                yjNyDto.setTotalAmount5(totalAmount5);
                yjNyDto.setRecordDate5(recordDate5);
                yjNyDto.setPostAmount6(postAmount6);
                yjNyDto.setTotalAmount6(totalAmount6);
                yjNyDto.setRecordDate6(recordDate6);
                yjNyDto.setNum(Integer.valueOf(num));
                yjNyDto.setSwitchFlag(Integer.valueOf(switchFlag));
                yjNyDto.setSwitchFlag1(Integer.valueOf(switchFlag1));
                yjNyDto.setSwitchFlag2(Integer.valueOf(switchFlag2));
                yjNyDto.setSwitchFlag3(Integer.valueOf(switchFlag3));
                yjNyDto.setSwitchFlag4(Integer.valueOf(switchFlag4));
                yjNyDto.setSwitchFlag5(Integer.valueOf(switchFlag5));
                yjNyDto.setSwitchFlag6(Integer.valueOf(switchFlag6));

                yjNyDtoList.add(yjNyDto);

            }

            resultData = lnkYjQtNyService.insertLnkQtNy(yjNyDtoList);
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


}
