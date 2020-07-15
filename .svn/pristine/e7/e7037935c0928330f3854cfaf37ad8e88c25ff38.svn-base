package cn.com.eju.deal.dataAuthority.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dataAuthority.service.DataAuthorityService;
import cn.com.eju.deal.dto.dataAuthority.CityRelationDto;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping(value = "dataAuthority")
public class DataAuthorityController extends BaseController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    DataAuthorityService dataAuthorityService;
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap mop, HttpServletResponse response)
    {

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);

        //获取页面显示数据
        ResultData<?> reback;
        try
        {
            //保存搜索数据 add by wangkanlin 2017-08-23
            if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                //rememberSearch(request, response, ComConstants.ESTATE_LIST_SEARCH, reqMap);
            }
            reback = dataAuthorityService.getDataAuthorityList(reqMap,pageInfo);
            //页面数据
            List<?> contentlist = (List<?>)reback.getReturnData();

            //存放到mop中

            mop.addAttribute("contentlist", contentlist);
            mop.addAttribute("userInfo", UserInfoHolder.get());
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.estate", "EstateController", "index", "", null, "", "", e);
        }
        return "dataAuthority/dataAuthorityListCtx";
    }
    @RequestMapping(value = "dataAuthorityList", method = RequestMethod.GET)
    public String getDataAuthorityList(HttpServletRequest request, ModelMap mop, HttpServletResponse response)
    {

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
                    PageInfo pageInfo = getPageInfo(request);

        //获取页面显示数据
        ResultData<?> reback;
        try
        {

            reback = dataAuthorityService.getDataAuthorityList(reqMap,pageInfo);
            //页面数据
            List<?> contentlist = (List<?>)reback.getReturnData();

            //存放到mop中

            mop.addAttribute("contentlist", contentlist);
            mop.addAttribute("userInfo", UserInfoHolder.get());
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.estate", "EstateController", "index", "", null, "", "", e);
        }
        return "dataAuthority/dataAuthorityList";
    }

    /**
     * (绑定参数到Map)
     * @param request
     * @return
     */
    public Map<String, Object> bindParamToMap(HttpServletRequest request)
    {
        Enumeration<?> enumer = request.getParameterNames();
        Map<String, Object> map = new HashMap<String, Object>();
        while (enumer.hasMoreElements())
        {
            String key = (String)enumer.nextElement();
            String val = request.getParameter(key);
            if (!"randomId".equals(key))
            {
                if ("orderBy".equals(key))
                {
                    if (!StringUtil.isEmpty(val))
                    {
                        Object orderByList = JsonUtil.parseToObject(val, List.class);
                        map.put(key, orderByList);
                    }
                    continue;
                }
                map.put(key, val);
            }
        }
        return map;
    }


    @RequestMapping(value = "insertData", method = RequestMethod.GET)
    public String insertData(HttpServletRequest request, ModelMap mop, HttpServletResponse response)
    {
        return "dataAuthority/insertData";
    }

    @ResponseBody
    @RequestMapping(value = "queryCityData", method = RequestMethod.POST)
    public String queryCityData(HttpServletRequest request, ModelMap mop, HttpServletResponse response)
    {
        Map<String, Object> reqMap = bindParamToMap(request);
        //获取页面显示数据
        ResultData<?> rebackQy;

        String data="";
        try
        {
            List<CityRelationDto> listQy = new ArrayList<CityRelationDto>();

            rebackQy = dataAuthorityService.getQyInfo(reqMap);
            //页面数据
            List<?> contentlistQy = (List<?>)rebackQy.getReturnData();

            String jsonStrQy = JSONArray.toJSONString(contentlistQy);
            return jsonStrQy;
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.estate", "EstateController", "index", "", null, "", "", e);
        }
        return data;
    }


    @RequestMapping(value = "saveData", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView saveData(HttpServletRequest request, ModelMap mop, HttpServletResponse response) {
        ReturnView returnView = new ReturnView();
        ResultData resultData = new ResultData();
        resultData.setFail();
        //请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        try {

            resultData = dataAuthorityService.saveData(reqMap);

        } catch (Exception e) {
            resultData.setFail("保存维护人异常!");
            logger.error("PMLS", "dataAuthorityController", "dataAuthority", reqMap.toString(), null, "", "维护人变更保存异常", e);
        }

        returnView.setOperateReturnType();
        returnView.setReturnCode(resultData.getReturnCode());
        returnView.setReturnMsg(resultData.getReturnMsg());
        returnView.setReturnValue(resultData.getReturnData());
        return returnView;
    }

    @RequestMapping(value = "deleteData", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView deleteData(HttpServletRequest request, HttpServletResponse response) {
        ReturnView returnView = new ReturnView();
        ResultData resultData = new ResultData();
        resultData.setFail();
        //请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        try {

            resultData = dataAuthorityService.deleteData(reqMap);

        } catch (Exception e) {
            resultData.setFail("保存维护人异常!");
            logger.error("PMLS", "dataAuthorityController", "deleteData", reqMap.toString(), null, "", "维护人变更保存异常", e);
        }
        System.out.println(20.00-11.00);
        returnView.setOperateReturnType();
        returnView.setReturnCode(resultData.getReturnCode());
        returnView.setReturnMsg(resultData.getReturnMsg());
        returnView.setReturnValue(resultData.getReturnData());
        return returnView;
    }


}


