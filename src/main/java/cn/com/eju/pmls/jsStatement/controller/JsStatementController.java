package cn.com.eju.pmls.jsStatement.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.jsStatement.dto.PmlsJsStatementDto;
import cn.com.eju.pmls.jsStatement.dto.PmlsJsStatementVto;
import cn.com.eju.pmls.jsStatement.service.JsStatementService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("jsStatement")
public class JsStatementController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "jsStatementService")
    private JsStatementService jsStatementService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView jsStatementList(HttpServletRequest request, ModelMap mop) {

        mop.addAttribute("userId", UserInfoHolder.getUserId());
        return new ModelAndView("jsStatement/jsStatementList");
    }

    @RequestMapping(value = "queryList", method = RequestMethod.POST)
    public ResultData<?> queryList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
            //获取页面显示数据
            resultData = jsStatementService.queryList(reqMap, pageInfo);
        } catch (Exception e) {
            resultData.setFail("查询结算书发生异常");
            logger.error("查询结算书发生异常", e);
        }
        return resultData;
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView create(ModelMap mop, HttpServletRequest request) {
        mop.addAttribute("fileListInfo", new ArrayList<>());
        return new ModelAndView("jsStatement/addJsStatement");
    }

    @RequestMapping(value = "modify/{id}", method = RequestMethod.GET)
    public ModelAndView modify(@PathVariable("id") String id, ModelMap mop, HttpServletRequest request) {
        mop.addAttribute("id", id);
        try {

            logger.info("取得结算书信息,id:", id);
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            ResultData<?> resultData = jsStatementService.getJsStatementById(map);
            logger.info("取得结算书信息,id:" + id, resultData);
            mop.addAttribute("info", resultData.getReturnData());

            try {
                ResultData jsFileResultData = jsStatementService.getJsStatementFile(id);
                mop.addAttribute("fileListInfo", JSONObject.toJSON(jsFileResultData.getReturnData()));
            } catch (Exception e) {
                logger.error("取得结算书文件发生异常,id=" + id, e);
                mop.addAttribute("fileListInfo", new ArrayList<>());
            }
        } catch (Exception ex) {
            logger.error("获取结算书信息异常,id:" + id, ex);
        }
        return new ModelAndView("jsStatement/addJsStatement");
    }

    @RequestMapping(value = "toInvalid", method = RequestMethod.POST)
    public ResultData<?> toInvalid(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            reqMap.put("userIdUpdate", UserInfoHolder.getUserId());
            reqMap.put("approveStatus", "10405");
            resultData = jsStatementService.toInvalid(reqMap);
        } catch (Exception e) {
            resultData.setFail("操作发生异常，请联系管理员！");
            logger.error("jsStatement.toInvalid 作废结算书操作发生异常！", e);
        }
        return resultData;
    }

    @RequestMapping(value = "jsStatementTerminatPopup", method = RequestMethod.GET)
    public ModelAndView jsStatementTerminatPopup() {
        return new ModelAndView("jsStatement/jsStatementTerminatPopup");
    }

    @RequestMapping(value = "toTermination", method = RequestMethod.POST)
    public ResultData<?> toTermination(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            reqMap.put("userIdUpdate", UserInfoHolder.getUserId());
            reqMap.put("approveStatus", "10406");
            resultData = jsStatementService.toTermination(reqMap);
        } catch (Exception e) {
            resultData.setFail("操作发生异常，请联系管理员！");
            logger.error("jsStatement.toTermination 终止结算书操作发生异常！", e);
        }
        return resultData;
    }


    @RequestMapping(value = "jsCompanyListPopup", method = RequestMethod.GET)
    public ModelAndView jsCompanyListPopup() {
        return new ModelAndView("jsStatement/jsCompanyListPopup");
    }

    @RequestMapping(value = "jsProjectListPopup/{companyNo}", method = RequestMethod.GET)
    public ModelAndView jsProjectListPopup(@PathVariable("companyNo") String companyNo, ModelMap mop, HttpServletRequest request) {
        mop.addAttribute("companyNo", companyNo);
        return new ModelAndView("jsStatement/jsProjectListPopup");
    }

    @RequestMapping(value = "jsKhListPopup/{hsCode}", method = RequestMethod.GET)
    public ModelAndView jsKhListPopup(@PathVariable("hsCode") String hsCode, ModelMap mop, HttpServletRequest request) {
        mop.addAttribute("hsCode", hsCode);
        return new ModelAndView("jsStatement/jsKhListPopup");
    }

    @RequestMapping(value = "jsFrameOaListPopup/{hsCode}/{projectNo}", method = RequestMethod.GET)
    public ModelAndView jsFrameOaListPopup(@PathVariable("hsCode") String hsCode,
                                           @PathVariable("projectNo") String projectNo,
                                           ModelMap mop, HttpServletRequest request) {
        mop.addAttribute("hsCode", hsCode);
        mop.addAttribute("projectNo", projectNo);
        return new ModelAndView("jsStatement/jsFrameOaListPopup");
    }

    @RequestMapping(value = "jsNormalReportListPopup/{companyNo}/{projectNo}", method = RequestMethod.GET)
    public ModelAndView jsNormalReportListPopup(@PathVariable("companyNo") String companyNo, @PathVariable("projectNo") String projectNo, ModelMap mop, HttpServletRequest request) {
        mop.addAttribute("companyNo", companyNo);
        mop.addAttribute("projectNo", projectNo);
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.putAll(reqMap);
        return new ModelAndView("jsStatement/jsNormalReportListPopup");
    }

    @RequestMapping(value = "jsOffsetReportListPopup/{companyNo}/{projectNo}", method = RequestMethod.GET)
    public ModelAndView jsOffsetReportListPopup(@PathVariable("companyNo") String companyNo, @PathVariable("projectNo") String projectNo, ModelMap mop, HttpServletRequest request) {
        mop.addAttribute("companyNo", companyNo);
        mop.addAttribute("projectNo", projectNo);
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.putAll(reqMap);
        return new ModelAndView("jsStatement/jsOffsetReportListPopup");
    }

    @RequestMapping(value = "queryJsCompanyList", method = RequestMethod.POST)
    public ResultData<?> queryJsCompanyList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
            //获取页面显示数据
            resultData = jsStatementService.queryJsCompanyList(reqMap, pageInfo);
        } catch (Exception e) {
            resultData.setFail("查询结算书（公司）发生异常");
            logger.error("jsStatement.queryJsCompanyList 查询结算书（公司）发生异常", e);
        }
        return resultData;
    }

    @RequestMapping(value = "queryJsProjectList", method = RequestMethod.POST)
    public ResultData<?> queryJsProjectList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
            //获取页面显示数据
            resultData = jsStatementService.queryJsProjectList(reqMap, pageInfo);
        } catch (Exception e) {
            resultData.setFail("查询结算书（项目）发生异常");
            logger.error("jsStatement.queryJsProjectList 查询结算书（项目）发生异常", e);
        }
        return resultData;
    }

    @RequestMapping(value = "queryJsHSCodeList", method = RequestMethod.POST)
    public ResultData<?> queryJsHSCodeList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            resultData = jsStatementService.queryJsHSCodeList(reqMap);
        } catch (Exception e) {
            resultData.setFail("查询结算书（核算主体）发生异常");
            logger.error("jsStatement.queryJsHSCodeList 查询结算书（核算主体）发生异常", e);
        }
        return resultData;
    }

    @RequestMapping(value = "queryJsKHCodeList", method = RequestMethod.POST)
    public ResultData<?> queryJsKHCodeList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            //获取页面显示数据
            resultData = jsStatementService.queryJsKHCodeList(reqMap, pageInfo);
        } catch (Exception e) {
            resultData.setFail("查询结算书（考核主体）发生异常");
            logger.error("jsStatement.queryJsKHCodeList 查询结算书（考核主体）发生异常", e);
        }
        return resultData;
    }

    @RequestMapping(value = "queryJsFrameOaList", method = RequestMethod.POST)
    public ResultData<?> queryJsFrameOaList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            //获取页面显示数据
            resultData = jsStatementService.queryJsFrameOaList(reqMap, pageInfo);
        } catch (Exception e) {
            resultData.setFail("查询结算书（合同）发生异常");
            logger.error("jsStatement.queryJsFrameOaList 查询结算书（合同）发生异常", e);
        }
        return resultData;
    }

    @RequestMapping(value = "queryJsNormalReportList", method = RequestMethod.POST)
    public ResultData<?> queryJsNormalReportList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            //获取页面显示数据
            resultData = jsStatementService.queryJsNormalReportList(reqMap, pageInfo);
        } catch (Exception e) {
            resultData.setFail("查询结算书（正常结算）发生异常");
            logger.error("jsStatement.queryJsNormalReportList 查询结算书（正常结算）发生异常", e);
        }
        return resultData;
    }

    @RequestMapping(value = "queryJsOffsetReportList", method = RequestMethod.POST)
    public ResultData<?> queryJsOffsetReportList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            //获取页面显示数据
            resultData = jsStatementService.queryJsOffsetReportList(reqMap, pageInfo);
        } catch (Exception e) {
            resultData.setFail("查询结算书（冲抵结算）发生异常");
            logger.error("jsStatement.queryJsOffsetReportList 查询结算书（冲抵结算）发生异常", e);
        }
        return resultData;
    }


    /**
     * desc:结算书详情
     * 2020年5月28日
     */
    @RequestMapping(value = "jsStatementDetailPage", method = RequestMethod.GET)
    public ModelAndView jsStatementDetailPage(Model model, HttpServletRequest request) {
        String id = request.getParameter("id");
        ResultData<PmlsJsStatementDto> resultData = new ResultData<>();
        try {
            if (id != null && !"".equals(id)) {//查询开发商信息，进入修改页面
                Map<String, Object> reqMap = bindParamToMap(request);
                resultData = jsStatementService.getJsStatementDetail(reqMap);
                model.addAttribute("id", id);
                model.addAttribute("jsStatementDto", JSONObject.toJSON(resultData.getReturnData()));

                try {
                    ResultData jsFileResultData = jsStatementService.getJsStatementFile(id);
                    model.addAttribute("fileListInfo", JSONObject.toJSON(jsFileResultData.getReturnData()));
                } catch (Exception e) {
                    logger.error("取得结算书文件发生异常,id=" + id, e);
                    model.addAttribute("fileListInfo", new ArrayList<>());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("jsStatement.jsStatementDetailPage ，查看结算书详情发生异常！", e);
        }
        return new ModelAndView("jsStatement/jsStatementDetailPage");
    }

    /**
     * desc:请款单详情
     * 2020年5月29日
     */
    @RequestMapping(value = "getCashBillDeatilByCashBillNo", method = RequestMethod.GET)
    public ResultData<?> getCashBillDeatilByCashBillNo(HttpServletRequest request, ModelMap mop) {
        ResultData<?> resultData = null;
        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            if (reqMap.containsKey("cashBillNo")) {
                resultData = jsStatementService.getCashBillDeatilByCashBillNo(reqMap);
            }
        } catch (Exception e) {
            logger.error("jsStatement", "JsStatementController", "getCashBillDeatilByCashBillNo", "", cn.com.eju.deal.base.support.UserInfoHolder.getUserId(), "", "结算书详情-查看请款单详情失败", e);
        }
        return resultData;
    }

    @RequestMapping(value = "getZkInfo", method = RequestMethod.GET)
    public ResultData<?> getZkInfo(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            resultData = jsStatementService.getZkInfo(reqMap);
        } catch (Exception e) {
            resultData.setFail("查询总控相关数据发生异常！");
            logger.error("jsStatement.getZkInfo 查询总控相关数据发生异常", e);
        }
        return resultData;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResultData<?> save(HttpServletRequest request, @RequestBody PmlsJsStatementVto info) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            info.setCityNo(UserInfoHolder.get().getCityNo());
            info.setUserId(UserInfoHolder.getUserId());
            info.setUserCode(UserInfoHolder.get().getUserCode());
            info.setUserName(UserInfoHolder.get().getUserName());
            info.setCellphone(UserInfoHolder.get().getCellphone());
            //获取页面显示数据
            resultData = jsStatementService.save(info);
        } catch (Exception e) {
            resultData.setFail("保存结算书发生异常");
            logger.error("jsStatement.save 保存结算书发生异常", e);
        }
        return resultData;
    }

    @RequestMapping(value = "getJsStatementDtlById", method = RequestMethod.GET)
    public ResultData<?> getJsStatementDtlById(HttpServletRequest request) {
        ResultData<?> resultData = null;
        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            resultData = jsStatementService.getJsStatementDtlById(reqMap);
        } catch (Exception e) {
            resultData.setFail("获取结算书明细信息发生异常");
            logger.error("jsStatement.getJsStatementDtlById 获取结算书明细信息发生异常", e);
        }
        return resultData;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResultData<?> update(HttpServletRequest request, @RequestBody PmlsJsStatementVto info) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            info.setCityNo(UserInfoHolder.get().getCityNo());
            info.setUserId(UserInfoHolder.getUserId());
            info.setUserCode(UserInfoHolder.get().getUserCode());
            info.setUserName(UserInfoHolder.get().getUserName());
            info.setCellphone(UserInfoHolder.get().getCellphone());
            //获取页面显示数据
            resultData = jsStatementService.update(info);
        } catch (Exception e) {
            resultData.setFail("更新结算书发生异常");
            logger.error("jsStatement.update 更新结算书发生异常", e);
        }
        return resultData;
    }

}
