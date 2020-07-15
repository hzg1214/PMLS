package cn.com.eju.pmls.commission.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.pmls.commission.dto.CommissionImportDto;
import cn.com.eju.pmls.commission.service.PmlsKfCommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping(value = "pmlsKfCommission")
public class PmlsKfCommissionController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private PmlsKfCommissionService pmlsKfCommissionService;

    @RequestMapping(value = "yjIndex", method = RequestMethod.GET)
    public ModelAndView yjIndex() {
        ModelAndView mv = new ModelAndView("commission/commissionListKf");
        mv.addObject("importType", "yj");
        return mv;
    }

    @RequestMapping(value = "kfIndex", method = RequestMethod.GET)
    public ModelAndView kfIndex() {
        ModelAndView mv = new ModelAndView("commission/commissionListKf");
        mv.addObject("importType", "kf");
        return mv;
    }

    @RequestMapping(value = "toView", method = RequestMethod.GET)
    public ModelAndView toView() {
        ModelAndView mv = new ModelAndView("commission/commissionImportKf");
        return mv;
    }

    @RequestMapping(value = "queryCityList", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> queryCityList(HttpServletRequest request) {

        Map<String, Object> reqMap = bindParamToMap(request);
        String userCode = UserInfoHolder.get().getUserCode();
        reqMap.put("userCode", userCode);
        ResultData<?> resultData = new ResultData<>();
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>(3);
        try {
            resultData = pmlsKfCommissionService.queryCityList(reqMap);
        } catch (Exception e) {
            logger.error("pmlsKfCommission", "PmlsKfCommissionController", "queryCityList",
                    "", UserInfoHolder.getUserId(), "", "获取城市失败", e);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        if (null != resultData.getReturnData()) {
            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        }

        return getMapView(rspMap);
    }


    @RequestMapping(value = "queryBusinessTypeList", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> queryBusinessTypeList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        String userCode = UserInfoHolder.get().getUserCode();
        reqMap.put("userCode", userCode);
        ResultData<?> resultData = new ResultData<>();
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>(3);
        try {
            resultData = pmlsKfCommissionService.queryBusinessTypeList(reqMap);
        } catch (Exception e) {
            logger.error("pmlsKfCommission", "PmlsKfCommissionController", "queryCityList",
                    "", UserInfoHolder.getUserId(), "", "获取城市失败", e);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        if (null != resultData.getReturnData()) {
            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        }

        return getMapView(rspMap);
    }

    /**
     * 查询列表-内容
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "queryList", method = RequestMethod.POST)
    public ResultData<?> queryList(HttpServletRequest request) {
        //获取页面请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        String userCode = UserInfoHolder.get().getUserCode();
        reqMap.put("loginUser", userCode);

        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            resultData = pmlsKfCommissionService.queryList(reqMap, getPageInfo(request));

        } catch (Exception e) {
            logger.error("pmlsKfCommission",
                    "PmlsKfCommissionController",
                    "queryList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询列表内容异常",
                    e);
        }
        return resultData;
    }

    @RequestMapping(value = "exportTemplate", method = RequestMethod.GET)
    public void exportTemplate(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            this.setExportSuccess((String) reqMap.get("cookieName"), response);

            String importType = reqMap.get("importType").toString();
            String filename;

            String filename2;
            if ("yj".equals(importType)) {
                filename = "yjdrb.xlsx";
                filename2 ="应计导入表.xlsx";
            } else {
                filename = "kfdrb.xlsx";
                filename2 ="可发导入表.xlsx";
            }
            
          

            //获取文件的路径
            String excelPath = request.getSession().getServletContext().getRealPath("/template/" + filename);
            // 读到流中
            InputStream inStream = new FileInputStream(excelPath);
            // 设置输出的格式
            response.setContentType("bin");
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(filename2, "UTF-8"));

            byte[] b = new byte[1024];
            int len;
            while ((len = inStream.read(b)) != -1) {
                response.getOutputStream().write(b, 0, len);
            }
            inStream.close();
        } catch (Exception e) {
            logger.error("pmlsKfCommission",
                    "PmlsKfCommissionController",
                    "export",
                    "input param: reqMap=",
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "导出异常",
                    e);
        }

    }

    @RequestMapping(value = "uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public ResultData uploadFile(HttpServletRequest request) {
        ResultData<?> resultData = new ResultData<>();
        try {
            String importType = request.getParameter("importType");
            String dateMonth = request.getParameter("dateMonth");
            String cityNo = request.getParameter("cityNo");
            String businessTypeCode = request.getParameter("businessTypeCode");

            // 验证关账
            CommissionImportDto checkDto = new CommissionImportDto();
            checkDto.setDateMonth(dateMonth);
            checkDto.setCityNo(cityNo);
            checkDto.setImportType(importType);
            checkDto.setBusinessTypeCode(businessTypeCode);
            ResultData<?> checkResult = pmlsKfCommissionService.checkAccount(checkDto);
            if ("-1".equals(checkResult.getReturnCode())) {
                return checkResult;
            }

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
                    String fileName = importFile.getOriginalFilename();
                    //MultipartFile转File
                    CommonsMultipartFile file = (CommonsMultipartFile) importFile;

                    resultData = pmlsKfCommissionService.importExcel(file, checkDto, request);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData.setFail("导入异常");
        }
        return resultData;
    }
}
