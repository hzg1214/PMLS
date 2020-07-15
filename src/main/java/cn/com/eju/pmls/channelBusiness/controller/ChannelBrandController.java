package cn.com.eju.pmls.channelBusiness.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.pmls.channelBusiness.dto.ChannelBrandDto;
import cn.com.eju.pmls.channelBusiness.service.ChannelBrandService;

@Controller
@RequestMapping(value = "channelBrandController")
public class ChannelBrandController extends BaseController {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource(name = "channelBrandService")
    private ChannelBrandService channelBrandService;

    //渠道品牌页面
    @RequestMapping(value = "channelBrandList", method = RequestMethod.GET)
    public ModelAndView channelBrandList() {
        ModelAndView mv = new ModelAndView("channelBusiness/channelBrandList");
        return mv;
    }
    //新增品牌页面
    @RequestMapping(value = "addBrandPage", method = RequestMethod.GET)
    public String addBrandPage(Model model, HttpServletRequest request) {
        String parentId=request.getParameter("parentId");
        String id=request.getParameter("id");
        String parentName="";
        ResultData<ChannelBrandDto> resultData = new ResultData<ChannelBrandDto>();
        if(id!=null && !"".equals(id)){//查询品牌信息，进入修改页面
            Map<String, Object> reqMap = bindParamToMap(request);
            try {
                resultData = channelBrandService.getBrandInfo(reqMap, null);
                model.addAttribute("id",id);
                model.addAttribute("brandDto", JSONObject.toJSON(resultData.getReturnData()));
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{//进入新增页面
            if(parentId!=null && "0".equals(parentId)){
                parentName="顶级";
            }else{
                Map<String, Object> reqMap = bindParamToMap(request);
                try {
                    reqMap.put("id",parentId);
                    resultData = channelBrandService.getBrandInfo(reqMap, null);
                    Map<?, ?> resMap = (Map<?, ?>) resultData.getReturnData();
                    parentName=resMap.get("brandName").toString();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            model.addAttribute("parentId",parentId);
            model.addAttribute("parentName",parentName);
        }
        return "channelBusiness/addBrandPage";
    }

    /**
     * 查询渠道品牌列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getChannelBrandList", method = RequestMethod.POST)
    public ResultData<?> getChannelBrandList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            resultData = channelBrandService.getChannelBrandList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("ChannelBrandDto",
                    "ChannelBrandController",
                    "getChannelBrandList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询渠道品牌列表异常",
                    e);
        }
        return resultData;
    }
    @ResponseBody
    @RequestMapping(value = "getChannelBrandList2", method = RequestMethod.POST)
    public Object getChannelBrandList2(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            resultData = channelBrandService.getChannelBrandList(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("ChannelBrandDto",
                    "ChannelBrandController",
                    "getChannelBrandList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询渠道品牌列表异常",
                    e);
        }
        return JSONObject.toJSON(resultData.getReturnData());
    }
    //获取渠道品牌信息
    @ResponseBody
    @RequestMapping(value = "getBrandInfo", method = RequestMethod.POST)
    public ResultData<?> getBrandInfo(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            //获取页面显示数据
            resultData = channelBrandService.getBrandInfo(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("ChannelBrandDto",
                    "ChannelBrandController",
                    "getBrandInfo",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询渠道品牌信息异常",
                    e);
        }
        return resultData;
    }
    //新增品牌
    @ResponseBody
    @RequestMapping(value = "addBrand", method = RequestMethod.POST)
    public ResultData<?> addBrand(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            ChannelBrandDto dto = (ChannelBrandDto) MapToEntityConvertUtil.convert(reqMap,ChannelBrandDto.class,"");
            String brandCode = SeqNoHelper.getInstance().getSeqNoByTypeCode("TYPE_LDQDPP");//品牌编号
            UserInfo userInfo = UserInfoHolder.get();
            String userId = "";
            if (userInfo != null) {
                userId = userInfo.getUserId()+"";
            }
            dto.setUserIdCreate(userId);
            dto.setBrandCode(brandCode);
            resultData = channelBrandService.addBrand(dto);
        } catch (Exception e) {
            logger.error("ChannelBrandDto",
                    "ChannelBrandController",
                    "addBrand",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "新增渠道品牌信息异常",
                    e);
        }
        return resultData;
    }
    //修改品牌
    @ResponseBody
    @RequestMapping(value = "updateBrand", method = RequestMethod.POST)
    public ResultData<?> updateBrand(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            ChannelBrandDto dto = (ChannelBrandDto) MapToEntityConvertUtil.convert(reqMap,ChannelBrandDto.class,"");
            resultData = channelBrandService.updateBrand(dto);
        } catch (Exception e) {
            logger.error("ChannelBrandDto",
                    "ChannelBrandController",
                    "updateBrand",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "修改渠道品牌信息异常",
                    e);
        }
        return resultData;
    }
    //删除品牌
    @ResponseBody
    @RequestMapping(value = "deleteBrand", method = RequestMethod.POST)
    public ResultData<?> deleteBrand(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            //获取页面显示数据
            ChannelBrandDto dto = (ChannelBrandDto) MapToEntityConvertUtil.convert(reqMap,ChannelBrandDto.class,"");
            resultData = channelBrandService.deleteBrand(dto);
        } catch (Exception e) {
            logger.error("ChannelBrandDto",
                    "ChannelBrandController",
                    "deleteBrand",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "删除渠道品牌信息异常",
                    e);
        }
        return resultData;
    }

}
