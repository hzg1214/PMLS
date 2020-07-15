package cn.com.eju.deal.permission.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.dto.dataAuthority.CityRelationDto;
import cn.com.eju.deal.dto.permission.PermissionDto;
import cn.com.eju.deal.permission.service.PermissionService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "permission")
public class PermissionController extends BaseController{
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    PermissionService permissionService;


    @RequestMapping(value = "permissionList", method = RequestMethod.GET)
    public String getDataAuthorityList(HttpServletRequest request, ModelMap mop, HttpServletResponse response)
    {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);

        //获取页面显示数据
        ResultData<?> reback;
        try
        {
            reback = permissionService.getPermissionList(reqMap,pageInfo);
            //页面数据
            List<?> contentlist = (List<?>)reback.getReturnData();

            //存放到mop中

            mop.addAttribute("contentlist", contentlist);
            mop.addAttribute("userInfo", UserInfoHolder.get());
        }
        catch (Exception e)
        {
            logger.error("permission", "PermissionController", "permissionList", "", null, "", "", e);
        }
        return "permission/permissionList";
    }

    @RequestMapping(value = "SearchPermissionList", method = RequestMethod.GET)
    public String SearchPermissionList(HttpServletRequest request, ModelMap mop, HttpServletResponse response)
    {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        //获取页面显示数据
        ResultData<?> reback;
        try
        {
            reback = permissionService.getPermissionList(reqMap,pageInfo);
            //页面数据
            List<?> contentlist = (List<?>)reback.getReturnData();
            //存放到mop中
            mop.addAttribute("contentlist", contentlist);
            mop.addAttribute("userInfo", UserInfoHolder.get());
        }
        catch (Exception e)
        {
            logger.error("permission", "PermissionController", "permissionList", "", null, "", "", e);
        }
        return "permission/permissionListCtx";
    }

    @RequestMapping(value = "deletePermission", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView deletePermission(HttpServletRequest request, HttpServletResponse response) {
        ReturnView returnView = new ReturnView();
        ResultData resultData = new ResultData();
        resultData.setFail();
        //请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        try {

            resultData = permissionService.deletePermission(reqMap);

        } catch (Exception e) {
            resultData.setFail("保存维护人异常!");
            logger.error("CRM", "PermissionController", "deletePermission", reqMap.toString(), null, "", "维护人变更保存异常", e);
        }
        returnView.setOperateReturnType();
        returnView.setReturnCode(resultData.getReturnCode());
        returnView.setReturnMsg(resultData.getReturnMsg());
        returnView.setReturnValue(resultData.getReturnData());
        return returnView;
    }

    @RequestMapping(value = "insertPermission", method = RequestMethod.GET)
    public String insertData(HttpServletRequest request)
    {
        return "permission/insertPermission";
    }

    @ResponseBody
    @RequestMapping(value = "searchList", method = RequestMethod.POST)
    public ResultData searchList(HttpServletRequest request, ModelMap mop, HttpServletResponse response)
    {
        Map<String, Object> reqMap = bindParamToMap(request);
        //获取页面显示数据
        ResultData<?> rebackQy = new ResultData<PermissionDto>();

        try
        {
            rebackQy = permissionService.searchList(reqMap);
        }
        catch (Exception e)
        {
            logger.error("permission", "PermissionController", "searchList", "", null, "", "", e);
        }
        return rebackQy;
    }

    @RequestMapping(value = "savePermission", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView savePermission(HttpServletRequest request, ModelMap mop, HttpServletResponse response) {
        ReturnView returnView = new ReturnView();
        ResultData resultData = new ResultData();
        resultData.setFail();
        //请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        try {

            resultData = permissionService.savePermission(reqMap);

        } catch (Exception e) {
            resultData.setFail("保存维护人异常!");
            logger.error("CRM", "PermissionController", "savePermission", reqMap.toString(), null, "", "模块添加异常", e);
        }

        returnView.setOperateReturnType();
        returnView.setReturnCode(resultData.getReturnCode());
        returnView.setReturnMsg(resultData.getReturnMsg());
        returnView.setReturnValue(resultData.getReturnData());
        return returnView;
    }

}