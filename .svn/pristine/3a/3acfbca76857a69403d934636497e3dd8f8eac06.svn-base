package cn.com.eju.pmls.developer.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cn.com.eju.deal.houseLinkage.estate.service.EstateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.helper.SeqNoHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.pmls.developer.dto.DeveloperBrandDto;
import cn.com.eju.pmls.developer.service.DeveloperBrandService;

@Controller
@RequestMapping(value = "developerBrand")
public class DeveloperBrandController extends BaseController {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "developerBrandService")
    private DeveloperBrandService developerBrandService;

    @Resource(name = "estateService")
    private EstateService estateService;


    //合作方品牌页面
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView developerBrandList() {
        ModelAndView mv = new ModelAndView("developer/developerBrandList");
        mv.addObject("partnerList", SystemParam.getCodeListByKey(DictionaryConstants.PARTNER));
        return mv;
    }
    //新增合作方品牌页面
    @RequestMapping(value = "addDeveloperBrandPage", method = RequestMethod.GET)
    public String addDeveloperBrandPage(Model model, HttpServletRequest request) {
        String parentId=request.getParameter("parentId");
        String flag=request.getParameter("flag");
        String id=request.getParameter("id");
        String parentName="";
        ResultData<DeveloperBrandDto> resultData = new ResultData<>();
        if(id!=null && !"".equals(id)){//查询品牌信息，进入修改页面
            Map<String, Object> reqMap = bindParamToMap(request);
            try {
                resultData = developerBrandService.getDeveloperBrandInfo(reqMap, null);
                model.addAttribute("id",id);
                model.addAttribute("parentFlag",flag);
                model.addAttribute("developerBrandDto", JSONObject.toJSON(resultData.getReturnData()));
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{//进入新增页面
        	Integer partner =null;
            if(parentId!=null && "0".equals(parentId)){
                parentName="顶级";
            }else{
                Map<String, Object> reqMap = bindParamToMap(request);
                try {
                    reqMap.put("id",parentId);
                    resultData = developerBrandService.getDeveloperBrandInfo(reqMap, null);
                    Map<?, ?> resMap = (Map<?, ?>) resultData.getReturnData();
                    parentName=resMap.get("developerBrandName").toString();
                    partner = (Integer) resMap.get("partner");
                    model.addAttribute("developerBrandDto", JSONObject.toJSON(resultData.getReturnData()));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            model.addAttribute("partner",partner);
            model.addAttribute("parentId",parentId);
            model.addAttribute("parentName",parentName);
        }

        try {
            ResultData<?> bigResultData = this.estateService.getBigCustomerList();
            ResultData<?> nResultData = this.estateService.getMattressNail();
            model.addAttribute("bigList", JSONObject.toJSON(bigResultData.getReturnData()));
            model.addAttribute("nList", JSONObject.toJSON(nResultData.getReturnData()));
        }catch (Exception e){
            logger.error("合作方品牌#新增编辑####查询大客户和垫佣甲方信息失败"
            +"input param: parentId=" + parentId+"#####id="+id);
            e.printStackTrace();
            logger.error("developerBrand",
                    "DeveloperBrandController",
                    "addDeveloperBrandPage",
                    "input param: parentId=" + parentId+"#####id="+id,
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "合作方品牌#新增编辑####查询大客户和垫佣甲方信息失败",
                    e);
        }

        //合作类型
        model.addAttribute("partnerList", SystemParam.getCodeListByKey(DictionaryConstants.PARTNER));
        return "developer/addDeveloperBrandPage";
    }

    /**
     * 查询合作方品牌列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getDeveloperBrandList", method = RequestMethod.POST)
    public ResultData<?> getDeveloperBrandList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            resultData = developerBrandService.getDeveloperBrandList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("DeveloperBrand",
                    "DeveloperBrandController",
                    "getChannelDeveloperBrandList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询合作方品牌列表异常",
                    e);
        }
        return resultData;
    }
    //获取合作方品牌信息
    @ResponseBody
    @RequestMapping(value = "getDeveloperBrandInfo", method = RequestMethod.POST)
    public ResultData<?> getDeveloperBrandInfo(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            //获取页面显示数据
            resultData = developerBrandService.getDeveloperBrandInfo(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("DeveloperBrand",
                    "DeveloperBrandController",
                    "getDeveloperBrandInfo",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询合作方品牌信息异常",
                    e);
        }
        return resultData;
    }
    //新增品牌
    @ResponseBody
    @RequestMapping(value = "addDeveloperBrand", method = RequestMethod.POST)
    public ResultData<?> addDeveloperBrand(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            DeveloperBrandDto dto = (DeveloperBrandDto) MapToEntityConvertUtil.convert(reqMap,DeveloperBrandDto.class,"");
            String DeveloperBrandCode = SeqNoHelper.getInstance().getSeqNoByTypeCode("TYPE_KFPP");//合作方编号
            UserInfo userInfo = UserInfoHolder.get();
            String userId = "";
            if (userInfo != null) {
                userId = userInfo.getUserId()+"";
            }
            dto.setUserIdCreate(userId);
            dto.setDeveloperBrandCode(DeveloperBrandCode);
            resultData = developerBrandService.addDeveloperBrand(dto);
        } catch (Exception e) {
            logger.error("DeveloperBrand",
                    "DeveloperBrandController",
                    "addDeveloperBrand",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "新增合作方品牌信息异常",
                    e);
        }
        return resultData;
    }
    //修改品牌
    @ResponseBody
    @RequestMapping(value = "updateDeveloperBrand", method = RequestMethod.POST)
    public ResultData<?> updateDeveloperBrand(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        //避免parentId为空转换异常
        if(reqMap.get("parentId")=="" || reqMap.get("parentId")==null) {
        	reqMap.remove("parentId");
        }
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            DeveloperBrandDto dto = (DeveloperBrandDto) MapToEntityConvertUtil.convert(reqMap,DeveloperBrandDto.class,"");
            UserInfo userInfo = UserInfoHolder.get();
            String userId = "";
            if (userInfo != null) {
                userId = userInfo.getUserId()+"";
                dto.setUserCode(userInfo.getUserCode());
            }
        	dto.setUserIdUpdate(userId);
        	dto.setDateUpdate(new Date());
            resultData = developerBrandService.updateDeveloperBrand(dto);
        } catch (Exception e) {
            logger.error("DeveloperBrand",
                    "DeveloperBrandController",
                    "updateDeveloperBrand",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "修改合作方品牌信息异常",
                    e);
        }
        return resultData;
    }
    //删除品牌
    @ResponseBody
    @RequestMapping(value = "deleteDeveloperBrand", method = RequestMethod.POST)
    public ResultData<?> deleteDeveloperBrand(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            DeveloperBrandDto dto = (DeveloperBrandDto) MapToEntityConvertUtil.convert(reqMap,DeveloperBrandDto.class,"");
            UserInfo userInfo = UserInfoHolder.get();
            String userId = "";
            if (userInfo != null) {
                userId = userInfo.getUserId()+"";
            }
            dto.setUserIdUpdate(userId);
            resultData = developerBrandService.deleteDeveloperBrand(dto);
        } catch (Exception e) {
            logger.error("DeveloperBrand",
                    "DeveloperBrandController",
                    "deleteDeveloperBrand",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "删除合作方品牌信息异常",
                    e);
        }
        return resultData;
    }

    @ResponseBody
    @RequestMapping(value = "getDeveloperBrandList2", method = RequestMethod.POST)
    public Object getDeveloperBrandList2(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            resultData = developerBrandService.getDeveloperBrandList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("DeveloperBrand",
                    "DeveloperlBrandController",
                    "getDeveloperBrandList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询合作方品牌列表异常",
                    e);
        }
        return JSONObject.toJSON(resultData.getReturnData());
    }
    
    //判断是否可以修改
    @ResponseBody
    @RequestMapping(value = "updateCheck", method = RequestMethod.POST)
    public ResultData<?> updateCheck(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
        	 //获取页面显示数据
            DeveloperBrandDto dto = (DeveloperBrandDto) MapToEntityConvertUtil.convert(reqMap,DeveloperBrandDto.class,"");
            resultData = developerBrandService.updateCheck(dto);
        } catch (Exception e) {
            logger.error("DeveloperBrand",
                    "DeveloperBrandController",
                    "updateCheck",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "是否可以修改合作方品牌check异常",
                    e);
        }
        return resultData;
    }
    
    @RequestMapping(value = "openDialogDeveloperBrandList", method = RequestMethod.GET)
    public ModelAndView openDialogDeveloperBrandList(){
        ModelAndView mv = new ModelAndView("developer/openDialogDeveloperBrandList");
        mv.addObject("partnerList", SystemParam.getCodeListByKey(DictionaryConstants.PARTNER));
        return mv;
    }

    /**
     * 查询合作方品牌列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getDeveloperBrandListByPage", method = RequestMethod.POST)
    public ResultData<?> getDeveloperBrandListByPage(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            //获取页面显示数据
            resultData = developerBrandService.getDeveloperBrandListByPage(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("DeveloperBrand",
                    "DeveloperBrandController",
                    "getChannelDeveloperBrandList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询合作方品牌列表异常",
                    e);
        }
        return resultData;
    }
}
