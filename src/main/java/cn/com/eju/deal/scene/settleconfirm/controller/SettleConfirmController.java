package cn.com.eju.deal.scene.settleconfirm.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.DateUtils;
import cn.com.eju.deal.common.util.FileUtils;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.dto.houseLinkage.report.ReportDto;
import cn.com.eju.deal.dto.houseLinkage.report.ReportInfoDto;
import cn.com.eju.deal.houseLinkage.estate.service.EstateService;
import cn.com.eju.deal.poi.ExcelForSettleConfirm;
import cn.com.eju.deal.scene.settleconfirm.service.SettleConfirmService;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yinkun on 2018/5/3.
 */
@Controller
@RequestMapping(value = "settleConfirm")
public class SettleConfirmController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "estateService")
    private EstateService estateService;

    @Resource(name = "settleConfirmService")
    private SettleConfirmService settleConfirmService;

    //模板类型
    private String templateType= "";
    //业绩城市
    private String cityNo= "";

    /**
     * 跳转页面
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap mop)
    {
        String cityNo = UserInfoHolder.get().getCityNo();

        //归属项目部列表
        try{
            Map<String,Object> reqMap = new HashMap<>();
            reqMap.put("cityNo", cityNo);
            ResultData<?> resultData = estateService.getProjectDepartment(reqMap);
            List<?> rebacklist = (List<?>) resultData.getReturnData();
            mop.put("rebacklist", rebacklist);
        }catch(Exception e)
        {
            logger.error("settleConfirm", "SettleConfirmController", "index", "", null, "", "创建--初始化-归属项目部", e);
        }

        try{
            Map<String,Object> reqMap = new HashMap<>();
            reqMap.put("cityNo", cityNo);
            ResultData<?> resultData = estateService.getEstateNmList(reqMap);
            List<?> estateList = (List<?>) resultData.getReturnData();
            mop.put("estateList", estateList);
        } catch (Exception e) {
            logger.error("sceneInCommission", "SceneInCommissionController", "", "", null, "", "创建--初始化-楼盘名称", e);
        }

        return "scene/settleconfirm/settleConfirmList";
    }

    /**
     * 获取列表数据
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "/listCtx", method = RequestMethod.GET)
    public String listCtx(HttpServletRequest request, ModelMap mop){
        String cityNo = UserInfoHolder.get().getCityNo();
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        PageInfo pageInfo = getPageInfo(request);
        //获取页面显示数据
        ResultData<?> reback = new ResultData<>();
        try
        {
            reqMap.put("cityNo",cityNo);
            reback = settleConfirmService.getSettleConfirmList(reqMap, pageInfo);
            mop.addAttribute("contentlist", reback.getReturnData());
        }
        catch (Exception e)
        {
            logger.error("settleConfirm", "SettleConfirmController", "listCtx", "", null, "", "", e);
            reback.setFail();
        }

        return "scene/settleconfirm/settleConfirmListCtx";
    }

    @RequestMapping(value = "exportCheck", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> exportCheck(HttpServletRequest request, HttpServletResponse response) {
        String cityNo = UserInfoHolder.get().getCityNo();
        Map<String, Object> rspMap = new HashMap<>();
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> reback;
        try {
            reqMap.put("cityNo",cityNo);
            reback = settleConfirmService.getSettleConfirmList(reqMap, null);
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
        rspMap.put(Constant.RETURN_MSG_KEY,reback.getReturnMsg());
        return getOperateJSONView(rspMap);
    }

    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response) {
        String cityNo = UserInfoHolder.get().getCityNo();
        Map<String, Object> reqMap = bindParamToMap(request);

        try {
            reqMap.put("cityNo",cityNo);
            ResultData<?> reback = settleConfirmService.getSettleConfirmList(reqMap, null);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            String ctxPath = request.getSession().getServletContext().getRealPath("/");
            String url = ctxPath + "data" + File.separator + "settleConfirm";

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
                String templateType = reqMap.get("estateType").toString();
                String templateTypeStr = SystemParam.getDicValueByDicCode(templateType);
                String pathName = url + File.separator + time + File.separator + templateTypeStr + ".xlsx";
                ExcelForSettleConfirm instance = new ExcelForSettleConfirm(Integer.valueOf(templateType));
                Map<String, String> map = new HashMap<>();
                UserInfo userInfo = UserInfoHolder.get();
                map.put("UserId", String.valueOf(userInfo.getUserId()));
                map.put("UserName", userInfo.getUserName() + "(" + userInfo.getUserCode() + ")");
                map.put("templateType", templateTypeStr);
                map.put("cityNo",cityNo);

                instance.downloadSheet(map, contentlist, new File(pathName));

                String fileName = templateTypeStr + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx";
                super.download(request, response, pathName, fileName);
            }catch(Exception e){
                response.setCharacterEncoding("GBK");
                response.getWriter().write("下载Excel失败" + e.getMessage());
                response.getWriter().close();
                logger.error("settleConfirm", "SettleConfirmController", "export", "", null, "", "下载Excel失败", e);
            }

        }catch(Exception e){
            logger.error("settleConfirm",
                    "SettleConfirmController",
                    "export",
                    "input param: reqMap=" + reqMap.toString(), -
                            UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "导出异常",
                    e);
        }
    }

    @RequestMapping(value = "dataImport", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> dataImport(HttpServletRequest request){
        Map<String, Object> rspMap = new HashMap<>();

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        MultipartFile multFile = multiRequest.getFile("historyDataFile");
        String estateType = reqMap.get("estateTypeImport").toString();

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
            if(null != rtnMap){
                return getSearchJSONView(rtnMap);
            }

            List<Map<String, Object>> mapList = new ArrayList<>();
            for (Row r : sheet1) {
                Boolean reault = getSheetCellValue(wb, r, mapList);
                if(reault == false)
                {
                    continue;
                }
            }
            //数据导入
            ResultData insertResult = updateReportDetail(mapList);
            if(!"200".equals(insertResult.getReturnCode()))
            {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "导入数据失败！");
                return getSearchJSONView(rspMap);
            }

            wb.close();

            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
            rspMap.put(Constant.RETURN_MSG_KEY, insertResult.getReturnMsg());
        } catch (IOException e1) {
            logger.error("sceneInCommission", "SceneInCommissionController", "imput", "", null, "", "", e1);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导入数据失败！");
        }

        return getSearchJSONView(rspMap);
    }

    private Map<String, Object> checkImport(Sheet sheet1, String strTemplateType){
        Map<String, Object> rspMap = new HashMap<String,Object>();
        if(sheet1.getLastRowNum()==0)
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导入文件数据格式有误！");
            return rspMap;
        }

        //密码验证
        if(isSheetModify((XSSFSheet)sheet1)){
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导入数据模板不对，请重新下载模板数据！");
            return rspMap;
        }

        templateType = sheet1.getRow(0).getCell(2).getStringCellValue();//模板类型
        String userId= sheet1.getRow(0).getCell(0).getStringCellValue();
        cityNo= sheet1.getRow(0).getCell(4).getStringCellValue();//业绩城市

        //验证模板类型
        if(!strTemplateType.equals(templateType))
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "选择的模板类型与导入文件的模板类型不匹配！");
            return rspMap;
        }

        //判断操作人的工号是否和模板的用户工号一致
        if(!userId.equals(UserInfoHolder.getUserId().toString()))
        {
            String userName= sheet1.getRow(0).getCell(3).getStringCellValue();
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导入模板为"+userName+"下载的，请使用自己下载的模板进行导入！");
            return rspMap;
        }
        //判断操作人的业绩城市是否和模板的业绩城市一致
        if(!cityNo.equals(UserInfoHolder.get().getCityNo()))
        {
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
            if(csheet!=null) {
                STUnsignedShortHex passwordST = csheet.xgetPassword();
                if(passwordST!=null || !passwordST.getStringValue().equals(pwd)) {
                    haveModify = false;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return haveModify;
    }
    /**
     *  取得excel值
     * @param r excel行
     * @return
     */
    private Boolean getSheetCellValue(Workbook wb, Row r, List<Map<String, Object>> mapList) {

        //跳过列头
        if(r.getRowNum()<2){
            return false;
        }
        String reportId= r.getCell(2).getStringCellValue();	//报备ID

        Map<String, Object> map = new HashMap<>();
        //结算确认日期
        String settleConfirmDate= getCellValue(wb, r.getCell(8));

        //只要填了值就添加数据库
        if(StringUtil.isNotEmpty(settleConfirmDate)){
            map.put("reportId", reportId);
            map.put("settleConfirmDate", settleConfirmDate);
            mapList.add(map);
        }
        return true;
    }

    /**
     * 导入操作
     * @param mapList
     * @return
     */
    private ResultData updateReportDetail(List<Map<String, Object>> mapList)
    {
        ResultData resultData=new ResultData();
        try {
            ReportInfoDto reportInfoDto = new ReportInfoDto();
            List<ReportDto> reportList=new ArrayList<>();

            //批量处理数据
            for (Map<String, Object> map : mapList) {
                String reportId = map.get("reportId").toString();
                Date settleConfirmDate = DateUtils.parseDate(map.get("settleConfirmDate").toString(),"yyyy-MM-dd");

                ReportDto dto=new ReportDto();
                dto.setReportId(reportId);
                dto.setCityNo(cityNo);
                dto.setSettleConfirmDate(settleConfirmDate);
                dto.setUptEmpId(UserInfoHolder.getUserId());
                reportList.add(dto);
            }

            reportInfoDto.setReportDtoList(reportList);
            resultData=settleConfirmService.batchUpdateSettleConfirmDate(reportInfoDto);
            return resultData;
        } catch (Exception e) {
            resultData.setReturnCode("-1");
            return resultData;
        }
    }



    //excel类型判断
    private String getCellValue(Workbook wb, Cell cell)
    {
        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
        String rtnVal="";

        DecimalFormat df = new DecimalFormat("0.00");
        if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC) {
            //判断是否为日期类型
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                //用于转化为日期格式
                Date d = cell.getDateCellValue();
                rtnVal = DateUtils.formatDate(d,"yyyy-MM-dd");
            }
        }
        if(cell.getCellType()==Cell.CELL_TYPE_STRING)
        {
            rtnVal = cell.getStringCellValue();
        }
        if(cell.getCellType()==Cell.CELL_TYPE_FORMULA)
        {
            evaluator.evaluateFormulaCell(cell);
            rtnVal = df.format(cell.getNumericCellValue());
        }
        return rtnVal;
    }
}
