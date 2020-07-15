package cn.com.eju.pmls.cooperation.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.helper.SeqNoHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.pmls.channelBusiness.dto.BusinessManagerDto;
import cn.com.eju.pmls.cooperation.dto.CooperationDto;
import cn.com.eju.pmls.cooperation.service.CooperationService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

@Controller
@RequestMapping(value = "cooperationController")
public class CooperationController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource(name = "cooperationService")
    private CooperationService cooperationService;


    /**
     * 合作确认函管理页面
     * @return
     */
    @RequestMapping(value = "cooperationList", method = RequestMethod.GET)
    public ModelAndView cooperationList() {
        ModelAndView mv = new ModelAndView("cooperation/cooperationList");
        return mv;
    }

    /**
     * 合作确认函列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getCooperationList", method = RequestMethod.POST)
    public ResultData<?> getCooperationList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
            //获取页面显示数据
            resultData = cooperationService.getCooperationList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("Cooperation",
                    "CooperationController",
                    "getCooperationList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询合作确认函列表异常",
                    e);
        }
        return resultData;
    }


    /**
     * 新增合作确认函页面
     * @return
     */
    @RequestMapping(value = "addCooperationView", method = RequestMethod.GET)
    public ModelAndView addCooperationView(HttpServletRequest request, ModelMap mop) {
        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            if(reqMap.containsKey("companyId")){
                BusinessManagerDto dto = new BusinessManagerDto();
                dto.setId(Integer.parseInt(reqMap.get("companyId").toString()));
                ResultData resultData = cooperationService.getCompanyInfo(dto);
                mop.addAttribute("isCanSelect",false);
                mop.addAttribute("companyInfo", JSONObject.toJSON(resultData.getReturnData()));
            }else{
                mop.addAttribute("isCanSelect",true);
            }
            //合同版本 typeId 283
            mop.put("hteditionList", SystemParam.getCodeListByKey(DictionaryConstants.COOPERATION_HT_EDITION));
            mop.addAttribute("signTypeList", SystemParam.getCodeListByKey(DictionaryConstants.COOPERATION_SIGN_TYPE));
        }catch (Exception e){
            logger.error("cooperation",
                    "CooperationController",
                    "addCooperationView",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "新增合作确认函页面异常",
                    e);
        }
        ModelAndView mv = new ModelAndView("cooperation/cooperationAdd");
        return mv;
    }

    /**
     * 选择渠道公司弹出层
     * @return
     */
    @RequestMapping(value = "selectCompany", method = RequestMethod.GET)
    public ModelAndView selectCompany() {
        ModelAndView mv = new ModelAndView("cooperation/selectCompany");
        return mv;
    }

    /**
     * 选择项目弹出层
     * @return
     */
    @RequestMapping(value = "selectProject", method = RequestMethod.GET)
    public ModelAndView selectProject() {
        ModelAndView mv = new ModelAndView("cooperation/selectProject");
        return mv;
    }

    /**
     * 新增合作确认函
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addCooperation", method = RequestMethod.POST)
    public ResultData<?> addCooperation(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            CooperationDto dto = (CooperationDto) MapToEntityConvertUtil.convert(reqMap,CooperationDto.class,"");
            String dybl = (String)reqMap.get("dybl");
            BigDecimal bd = new BigDecimal(dybl);
            dto.setDybl(bd);

            BigDecimal bj = new BigDecimal("100");
            if(bd.compareTo(bj)>0){
                resultData = new ResultData<>();
                resultData.setFail("垫佣比例必须小于等于100%");
                return  resultData;
            }



            String contractNo = SeqNoHelper.getInstance().getSeqNoByTypeCode("TYPE_PMLS_HZ");//合作确认函合同编号
            dto.setContractNo(contractNo);
            dto.setUserIdCreate(UserInfoHolder.getUserId().toString());
            dto.setCityNo(UserInfoHolder.get().getCityNo());
            resultData = cooperationService.addCooperation(dto);
        } catch (Exception e) {
            logger.error("cooperation",
                    "CooperationController",
                    "addCooperation",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "新增合作确认函异常",
                    e);
        }
        return resultData;
    }

    /**
     * 合作确认函修改页面
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "updateCooperationPage", method = RequestMethod.GET)
    public String updateCooperationPage(Model model, HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            String id=request.getParameter("id");
            if(id!=null && !"".equals(id)){//查询合作确认函
                CooperationDto dto = (CooperationDto) MapToEntityConvertUtil.convert(reqMap,CooperationDto.class,"");
                ResultData resultData = cooperationService.getCooperationInfo(dto);
                model.addAttribute("id",id);
                model.addAttribute("cooperationInfo", JSONObject.toJSON(resultData.getReturnData()));
            }
            //合同版本 typeId 283
            model.addAttribute("hteditionList", SystemParam.getCodeListByKey(DictionaryConstants.COOPERATION_HT_EDITION));
            model.addAttribute("signTypeList", SystemParam.getCodeListByKey(DictionaryConstants.COOPERATION_SIGN_TYPE));
        }catch (Exception e){
            logger.error("cooperation",
                    "CooperationController",
                    "updateCooperationPage",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "合作确认函修改页面异常",
                    e);
        }
        return "cooperation/cooperationEdit";
    }

    /**
     * 修改合作确认函
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateCooperation", method = RequestMethod.POST)
    public ResultData<?> updateCooperation(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            CooperationDto dto = (CooperationDto) MapToEntityConvertUtil.convert(reqMap,CooperationDto.class,"");
            dto.setUserIdUpt(UserInfoHolder.getUserId().toString());
            String dybl = (String)reqMap.get("dybl");
            BigDecimal bd = new BigDecimal(dybl);
            dto.setDybl(bd);

            BigDecimal bj = new BigDecimal("100");
            if(bd.compareTo(bj)>0){
                resultData = new ResultData<>();
                resultData.setFail("垫佣比例必须小于等于100%");
                return  resultData;
            }

            resultData = cooperationService.updateCooperation(dto);
        } catch (Exception e) {
            logger.error("cooperation",
                    "CooperationController",
                    "updateCooperation",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "修改合作确认函异常",
                    e);
        }
        return resultData;
    }

    /**
     * 合作确认函详情页面
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "cooperationInfoPage", method = RequestMethod.GET)
    public String cooperationInfoPage(Model model, HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            String id=request.getParameter("id");
            if(id!=null && !"".equals(id)){//查询合作确认函
                CooperationDto dto = (CooperationDto) MapToEntityConvertUtil.convert(reqMap,CooperationDto.class,"");
                ResultData resultData = cooperationService.getCooperationInfo(dto);
                model.addAttribute("id",id);
                model.addAttribute("cooperationInfo", JSONObject.toJSON(resultData.getReturnData()));
            }
        }catch (Exception e){
            logger.error("cooperation",
                    "CooperationController",
                    "cooperationInfoPage",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "合作确认函详情页面异常",
                    e);
        }
        return "cooperation/cooperationInfo";
    }

    /**
     * 作废合作确认函
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "invalidCooperation", method = RequestMethod.POST)
    public ResultData<?> invalidCooperation(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            CooperationDto dto = (CooperationDto) MapToEntityConvertUtil.convert(reqMap,CooperationDto.class,"");
            dto.setUserIdUpt(UserInfoHolder.getUserId().toString());
            resultData = cooperationService.invalidCooperation(dto);
        } catch (Exception e) {
            logger.error("cooperation",
                    "CooperationController",
                    "invalidCooperation",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "作废合作确认函异常",
                    e);
        }
        return resultData;
    }


    /**
     * 获取经纪公司列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getCompanyList", method = RequestMethod.POST)
    public ResultData<?> getCompanyList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            //reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
            //获取页面显示数据
            resultData = cooperationService.getCompanyList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("Cooperation",
                    "CooperationController",
                    "getCompanyList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "获取经纪公司列表异常",
                    e);
        }
        return resultData;
    }

    /**
     * 检查经纪公司是否可选
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "checkCompany", method = RequestMethod.POST)
    public ResultData<?> checkCompany(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            BusinessManagerDto dto = (BusinessManagerDto) MapToEntityConvertUtil.convert(reqMap,BusinessManagerDto.class,"");
            resultData = cooperationService.checkCompany(dto);
        } catch (Exception e) {
            logger.error("cooperation",
                    "CooperationController",
                    "checkCompany",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "检查经纪公司是否可选异常",
                    e);
        }
        return resultData;
    }

    /**
     * 选择项目合同 弹出层
     * @return
     */
    @RequestMapping(value = "selectEstateHt", method = RequestMethod.GET)
    public ModelAndView selectEstateHt(HttpServletRequest request, ModelMap mop) {
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.addAllAttributes(reqMap);
        ModelAndView mv = new ModelAndView("cooperation/selectEstateHt");
        return mv;

    }

    /**
     * 获取项目合同列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getEstateHtRecord", method = RequestMethod.POST)
    public ResultData<?> getEstateHtRecord(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            //获取页面显示数据
            resultData = cooperationService.getEstateHtRecord(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("Cooperation",
                    "CooperationController",
                    "getEstateHtRecord",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "获取项目合同列表异常",
                    e);
        }
        return resultData;
    }

    /**
     * 发起OA申请合作协议
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "sendOACooperation", method = RequestMethod.POST)
    public ResultData<?> sendOACooperation(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            CooperationDto dto = (CooperationDto) MapToEntityConvertUtil.convert(reqMap,CooperationDto.class,"");
            String oaNo = SeqNoHelper.getInstance().getSeqNoByTypeCode("TYPE_PMLS_HZ_OA_NO");//合作确认函OA编码
            dto.setOaNo(oaNo);
            dto.setUserIdUpt(UserInfoHolder.getUserId().toString());
            dto.setSendUserId(UserInfoHolder.get().getUserCode());
            dto.setSendUserName(UserInfoHolder.get().getUserName());
            resultData = cooperationService.sendOACooperation(dto);
        } catch (Exception e) {
            logger.error("cooperation",
                    "CooperationController",
                    "sendOACooperation",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "发起OA申请合作协议",
                    e);
        }
        return resultData;
    }


}
