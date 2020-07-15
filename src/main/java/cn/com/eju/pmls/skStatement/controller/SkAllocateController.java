package cn.com.eju.pmls.skStatement.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.FileUtils;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.pmls.poi.ExcelForAllocate;
import cn.com.eju.pmls.skStatement.service.SkAllocateService;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STUnsignedShortHex;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
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

@RestController
@RequestMapping("skAllocate")
public class SkAllocateController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "skAllocateService")
    private SkAllocateService skAllocateService;

    /**
     * 按成销日期优先--预览
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "previewAllocate", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> previewAllocate(HttpServletRequest request) {

        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        logger.info("拆分订单预览，查询列表（按成销日期优先），入参:" + reqMap);
        try {
            //获取页面显示数据
            resultData = skAllocateService.getAllocateListForDeal(reqMap);
            logger.info("拆分订单预览，查询列表成功（按成销日期优先），返回数据:" + resultData);
        } catch (Exception e) {
            logger.error("skAllocate", "SkAllocateController", "previewAllocate", "", UserInfoHolder.getUserId(), "", "按成销日期优先查询拆分订单预览列表失败", e);

        }
        return resultData;
    }

    /**
     * 按房源定义下载模板
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "downloadTemplate", method = RequestMethod.GET)
    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) {
        String cityNo = UserInfoHolder.get().getCityNo();
        Map<String, Object> rspMap = new HashMap<String, Object>();
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> reback = new ResultData<>();
        try {
            reqMap.put("cityNo", cityNo);
            reback = skAllocateService.getAllocateListForBuilding(reqMap);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();
            if (contentlist == null || contentlist.isEmpty()) {
                //response.setCharacterEncoding("GBK");
                response.getWriter().write("没有可拆分的订单数据，无法导出模板！");
                response.getWriter().close();
                return;
            }
            String reportNos = "";
            for (LinkedHashMap<String, Object> content : contentlist) {
                String reportId = (String) content.get("reportId");
                if (StringUtil.isEmpty(reportNos)) {
                    reportNos = reportId;
                } else {
                    reportNos = reportNos + "," + reportId;
                }
            }
            String ctxPath = request.getSession().getServletContext().getRealPath("/");
            String url = ctxPath + "data" + File.separator + "allocateList";

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
                String pathName = url + File.separator + time + File.separator + "收入拆分导入模板.xlsx";
                ExcelForAllocate instance = new ExcelForAllocate();
                Map<String, String> map = new HashMap<>();
                UserInfo userInfo = UserInfoHolder.get();


                map.put("UserId", String.valueOf(userInfo.getUserId()));
                map.put("UserName", userInfo.getUserName() + "(" + userInfo.getUserCode() + ")");
                map.put("cityNo", cityNo);
                map.put("reportNos", reportNos);

                instance.downloadSheet(map, contentlist, new File(pathName));

                String fileName = "收入拆分导入模板.xlsx";
                super.download(request, response, pathName, fileName);
            } catch (Exception e) {
                response.setCharacterEncoding("GBK");
                response.getWriter().write("下载按房源自定义模板失败" + e.getMessage());
                response.getWriter().close();
                logger.error("下载按房源自定义模板失败", e);
                e.printStackTrace();
            }
        } catch (Exception e) {
            logger.error("skAllocate", "SkAllocateController", "downloadTemplate", "input param: reqMap=" + reqMap.toString(), UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "下载按房源自定义模板失败", e);
        }
    }

    /**
     * 上传附件弹窗
     *
     * @return
     */
    @RequestMapping(value = "toView", method = RequestMethod.GET)
    public ModelAndView toView() {
        ModelAndView mv = new ModelAndView("skStatement/skAllocateUpload");
        return mv;
    }

    /**
     * 上传拆分订单附件
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "uploadTemplate", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> uploadTemplate(HttpServletRequest request) {
        // 返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

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
            int number = wb.getNumberOfSheets();
            if (number > 1) {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "Excel文档格式错误,不允许多个sheet！");
                return getSearchJSONView(rspMap);
            }
            Sheet sheet1 = wb.getSheetAt(0);

            //上传check
            Map<String, Object> rtnMap = this.checkUpload(sheet1);
            if (null != rtnMap) {
                return getSearchJSONView(rtnMap);
            }
            List<Map<String, Object>> mapList = new ArrayList<>();
            String reportNos = sheet1.getRow(0).getCell(4).getStringCellValue();//所有报备
            for (Row r : sheet1) {
                Integer result = this.getSheetCellValue(wb, r, mapList);
                if (result == 1) {
                    continue;
                } else if (result == 2) {
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    rspMap.put(Constant.RETURN_MSG_KEY, "本次拆分金额不能大于未回款金额，请调整后重新导入！");
                    return getSearchJSONView(rspMap);
                }
                String reportId = r.getCell(0).getStringCellValue();
                if (reportNos.indexOf(reportId) == -1) {
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    rspMap.put(Constant.RETURN_MSG_KEY, reportId + "该条报备记录不应出现在模板中，请调整后重新导入！");
                    return getSearchJSONView(rspMap);
                }
            }
            wb.close();

            if(CollectionUtils.isEmpty(mapList)){
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "您未做任何拆分，请调整后重新导入！");
                return getSearchJSONView(rspMap);
            }
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
            rspMap.put(Constant.RETURN_MSG_KEY, "上传成功");
            rspMap.put(Constant.RETURN_DATA_KEY, mapList);
        } catch (IOException e1) {
            logger.error("skAllocate", "SkAllocateController", "uploadTemplate", "", null, "", "", e1);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "上传拆分订单附件失败！");
        }
        return getSearchJSONView(rspMap);
    }

    /**
     * 上传一般校验
     *
     * @param sheet1
     * @return
     */
    private Map<String, Object> checkUpload(Sheet sheet1) {
        Map<String, Object> rspMap = new HashMap<>();
        if (sheet1.getLastRowNum() == 0) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "上传文件数据格式有误！");
            return rspMap;
        }
        //密码验证
        if (isSheetModify((XSSFSheet) sheet1)) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "上传拆分订单模板不对，请重新下载模板数据！");
            return rspMap;
        }

        String userId = sheet1.getRow(0).getCell(0).getStringCellValue();
        String cityNo = sheet1.getRow(0).getCell(3).getStringCellValue();//业绩城市

        //判断操作人的工号是否和模板的用户工号一致
        if (!userId.equals(UserInfoHolder.getUserId().toString())) {
            String userName = sheet1.getRow(0).getCell(2).getStringCellValue();
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "上传模板为" + userName + "下载的，请使用自己下载的模板进行上传！");
            return rspMap;
        }
        //判断操作人的业绩城市是否和模板的业绩城市一致
        if (!cityNo.equals(UserInfoHolder.get().getCityNo())) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "上传模板对应的业绩城市和您的业绩城市不一致，请下载最新的模板进行导入！");
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
     *
     * @param wb
     * @param r
     * @param mapList
     * @return 1, 跳过列头2, 调整列数据填写错误，请调整导表后重新导入
     */
    private Integer getSheetCellValue(Workbook wb, Row r, List<Map<String, Object>> mapList) {
        //跳过列头
        if (r.getRowNum() < 3) {
            return 1;
        }
        Map<String, Object> map = new HashMap<>();
        //订单编号
        String reportId = r.getCell(0).getStringCellValue();
        //客户姓名
        String customerName = r.getCell(1).getStringCellValue();
        //楼室号
        String buildingNo = r.getCell(2).getStringCellValue();
        //大定面积
        String roughtArea = getCellValue(wb, r.getCell(3));
        //大定总价
        String roughtAmount = getCellValue(wb, r.getCell(4));
        //大定日期
        String roughDate = getCellValue(wb, r.getCell(5));
        //大定过审日期
        String roughAuditTime = getCellValue(wb, r.getCell(6));
        //成销面积
        String cxArea = getCellValue(wb, r.getCell(7));
        //成销总价
        String cxAmount = getCellValue(wb, r.getCell(8));
        //成销日期
        String dealDate = getCellValue(wb, r.getCell(9));
        //成销收入(税前)
        String yjAmount_bef = getCellValue(wb, r.getCell(10));
        //成销实收金额(税前)
        String sjAmount_bef = getCellValue(wb, r.getCell(11));
        //未回款金额(税前)
        String unBackAmount_befStr = getCellValue(wb, r.getCell(12));
        //本次拆分金额(元)
        String allocatAmount_befStr = getCellValue(wb, r.getCell(13));
        if(StringUtil.isEmpty(allocatAmount_befStr)){
            return 1;
        }
        //剩余拆分金额(元)
        String stayAmount_bef = getCellValue(wb, r.getCell(14));
        //----以上是表中需要显示的字段

        //正记录的本次拆分最大金额
        String allocatAmount_maxStr = getCellValue(wb, r.getCell(15));
        //负记录的本次拆分最大金额
        String neAllocatAmount_maxStr = getCellValue(wb, r.getCell(16));

        Double unBackAmount_bef = Double.parseDouble(unBackAmount_befStr);
        Double allocatAmount_bef = Double.parseDouble(allocatAmount_befStr);
        Double allocatAmount_max = null;
        if (unBackAmount_bef > 0) {
            allocatAmount_max = Double.parseDouble(allocatAmount_maxStr);
        } else if (unBackAmount_bef < 0) {
            allocatAmount_max = Double.parseDouble(neAllocatAmount_maxStr);
        }

        if (Math.abs(allocatAmount_bef) > 0 && Math.abs(allocatAmount_bef) > Math.abs(allocatAmount_max)) {
            return 2;
        }

        if (Math.abs(allocatAmount_bef) > 0) {
            map.put("reportId", reportId);
            map.put("customerName", customerName);
            map.put("buildingNo", buildingNo);
            map.put("roughtArea", roughtArea);
            map.put("roughtAmount", roughtAmount);
            map.put("roughtDate", roughDate);
            map.put("roughAuditTime", roughAuditTime);
            map.put("cxArea", cxArea);
            map.put("cxAmount", cxAmount);
            map.put("dealDate", dealDate);
            map.put("yjAmount_bef", yjAmount_bef);
            map.put("sjAmount_bef", sjAmount_bef);
            map.put("unBackAmount_bef", unBackAmount_befStr);
            map.put("allocatAmount_bef", allocatAmount_befStr);
            map.put("stayAmount_bef", stayAmount_bef);
            mapList.add(map);
        }
        return 0;
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

    /**
     * 新增
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "submitAllocate", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> submitAllocate(HttpServletRequest request) {
        String cityNo = UserInfoHolder.get().getCityNo();
        // 返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            reqMap.put("userId", UserInfoHolder.getUserId());
            reqMap.put("cityNo", cityNo);
            ResultData<?> resultData = skAllocateService.submitAllocate(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        } catch (Exception e) {
            logger.error("skAllocate", "SkAllocateController", "submitAllocate", "", UserInfoHolder.getUserId(), "", "拆分记录提交失败", e);

            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);

            rspMap.put(Constant.RETURN_MSG_KEY, "提交失败");

            logger.error("【submitAllocate】拆分记录提交失败,rspMap:" + rspMap.toString(), e);
        }
        return getSearchJSONView(rspMap);
    }


    /**
     * 收入查看页面获取收入拆分明细
     *
     * @param id
     * @param mop
     * @param request
     * @return
     */
    @RequestMapping(value = "showDetail/{id}", method = RequestMethod.GET)
    public ModelAndView showDetail(@PathVariable("id") String id, ModelMap mop, HttpServletRequest request) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            ResultData<?> resultData = skAllocateService.getSkAllocateDtlListById(map);
            mop.addAttribute("info", resultData.getReturnData());
        } catch (Exception ex) {
            logger.error("【showDetail】获取收入拆分明细异常,id:" + id, ex);
        }
        return new ModelAndView("skStatement/skAllocateDetailPage");
    }

    @RequestMapping(value = "cancelAll", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> cancelAll(HttpServletRequest request) {
        ResultData<?> resultData = null;
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            reqMap.put("userId", UserInfoHolder.getUserId());
            resultData = skAllocateService.cancelAll(reqMap);
            resultData.setReturnMsg("整单撤销成功");
        } catch (Exception e) {
            resultData.setFail("整单撤销失败");
            logger.error("【cancelAll】拆分记录整单撤销失败,reqMap:" + reqMap.toString(), e);
        }
        return resultData;
    }

    @RequestMapping(value = "cancelDtl", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> cancelDtl(HttpServletRequest request) {
        ResultData<?> resultData = null;
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            reqMap.put("userId", UserInfoHolder.getUserId());
            //更新
            resultData = skAllocateService.cancelDtl(reqMap);
            resultData.setReturnMsg("撤销成功");
        } catch (Exception e) {
            resultData.setFail("撤销失败");
            logger.error("【cancelDtl】拆分记录单条撤销失败,reqMap:" + reqMap.toString(), e);
        }
        return resultData;
    }


    @RequestMapping(value = "recordList", method = RequestMethod.POST)
    public ResultData<?> recordList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            //获取页面显示数据
            resultData = skAllocateService.recordList(reqMap, pageInfo);
        } catch (Exception e) {
            resultData.setFail("查询拆分记录发生异常");
            logger.error("查询拆分记录发生异常", e);
        }
        return resultData;
    }

    @RequestMapping(value = "logList", method = RequestMethod.POST)
    public ResultData<?> logList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            //获取页面显示数据
            resultData = skAllocateService.logList(reqMap, pageInfo);
        } catch (Exception e) {
            resultData.setFail("查询拆分日志发生异常");
            logger.error("查询拆分日志发生异常", e);
        }
        return resultData;
    }

    @RequestMapping(value = "querySkAllocateDtlList", method = RequestMethod.POST)
    public ResultData<?> querySkAllocateDtlList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            //获取页面显示数据
            resultData = skAllocateService.querySkAllocateDtlList(reqMap, pageInfo);
        } catch (Exception e) {
            resultData.setFail("查询收入拆分明细发生异常");
            logger.error("查询收入拆分明细发生异常", e);
        }
        return resultData;
    }

}
