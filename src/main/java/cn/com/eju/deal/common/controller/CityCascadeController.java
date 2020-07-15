package cn.com.eju.deal.common.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.service.AreaService;
import cn.com.eju.deal.common.service.CityService;
import cn.com.eju.deal.common.service.DistrictService;
import cn.com.eju.deal.common.service.ProvinceService;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnView;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
* @ClassName: CityCascadeController 
* @Description: 省市区级联Controller
* @author 陆海丹 
* @date 2016年3月28日 下午2:28:51 
*/
@Controller
@RequestMapping(value = "cityCascade")
public class CityCascadeController extends BaseController
{
	private final LogHelper logger = LogHelper.getLogger(this.getClass());
	 
    @Resource(name="provinceService")
    private ProvinceService provinceService;
    
    @Resource(name="cityService")
    private CityService cityService;
    
    @Resource(name="districtService")
    private DistrictService districtService;
    
    @Resource(name="areaService")
    private AreaService areaService;
    
    /** 
    * @Title: provincelist 
    * @Description: 获取省份列表
    * @param request
    * @param mop
    * @return
    * @throws Exception     
    */
    @RequestMapping(value = "province", method = RequestMethod.GET)
    public JSONArray provincelist(HttpServletRequest request, ModelMap mop)
        throws Exception
    {
        JSONArray j = new JSONArray();
        //获取页面显示数据
        ResultData<?> reback = this.provinceService.queryProvinceList();
        //页面数据
        List<?> provinceList = (List<?>)reback.getReturnData();
        j.addAll(provinceList);
        return j;
    }
    
    /** 
    * @Title: citylist 
    * @Description: 根据省份编号获取城市列表
    * @param request--provinceNo
    * @param mop
    * @return
    * @throws Exception     
    */
    @RequestMapping(value = "city", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> citylist(HttpServletRequest request, ModelMap mop)
        throws Exception
    {
        JSONArray j = new JSONArray();
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        //获取页面显示数据
        ResultData<?> reback = this.cityService.queryCitylist(reqMap);

        Map<String, Object> rspMap = new HashMap<>();
        rspMap.put(Constant.RETURN_CODE_KEY, reback.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, reback.getReturnMsg());
        if (null != reback.getReturnData())
        {
            rspMap.put(Constant.RETURN_DATA_KEY, reback.getReturnData());
        }
        return getMapView(rspMap);
    }
    
    /** 
    * @Title: districtlist 
    * @Description: 根据城市编号获取区域列表
    * @param request--cityNo
    * @param mop
    * @return
    * @throws Exception     
    */
    @RequestMapping(value = "district", method = RequestMethod.GET)
    public JSONArray districtlist(HttpServletRequest request, ModelMap mop)
        throws Exception
    {
        JSONArray j = new JSONArray();
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        //获取页面显示数据
        ResultData<?> reback = this.districtService.queryDistrictlist(reqMap);
        //页面数据
        List<?> districtList = (List<?>)reback.getReturnData();
        j.addAll(districtList);
        return j;
    }
    
    /** 
    * @Title: arealist 
    * @Description: 根据区域编号获取板块列表
    * @param request--districtNo
    * @param mop
    * @return
    * @throws Exception     
    */
    @RequestMapping(value = "area", method = RequestMethod.GET)
    public JSONArray arealist(HttpServletRequest request, ModelMap mop)
        throws Exception
    {
        JSONArray j = new JSONArray();
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        //获取页面显示数据
        ResultData<?> reback = this.areaService.queryArealist(reqMap);
        //页面数据
        List<?> areaList = (List<?>)reback.getReturnData();
        j.addAll(areaList);
        return j;
    }
    
    /** 
    * @Title: queryCityListByIsService 
    * @Description: 获取已开通城市
    * ResultData<List<CityDto>>  
    */
    @RequestMapping(value = "queryCityListByIsService", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> queryCityListByIsService(HttpServletRequest request, ModelMap mop)
    {
        ResultData<?> resultData = new ResultData<>();
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try
        {
            resultData = this.cityService.queryCityListByIsLnkService();
        }
        catch (Exception e)
        {
        	logger.error("cityCascade",
                    "CityCascadeController",
                    "cityService.queryCityListByIsService",
                    "",
                    UserInfoHolder.getUserId(),
                    "",
                    "获取已开通城市失败",
                    e);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        if (null != resultData.getReturnData())
        {
            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        }
        return getMapView(rspMap);
    }

    @RequestMapping(value = "queryCityListByUserId", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> queryCityListByUserId(HttpServletRequest request, ModelMap mop)
    {
        ResultData<?> resultData = new ResultData<>();
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try
        {
            UserInfo userInfo=UserInfoHolder.get();
            rspMap.put("userId",userInfo.getUserId());
            resultData = this.cityService.queryCityListByUserId(rspMap);
        }
        catch (Exception e)
        {
        	logger.error("cityCascade",
                    "CityCascadeController",
                    "cityService.queryCityListByIsService",
                    "",
                    UserInfoHolder.getUserId(),
                    "",
                    "获取已开通城市失败",
                    e);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        if (null != resultData.getReturnData())
        {
            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        }
        return getMapView(rspMap);
    }
}
