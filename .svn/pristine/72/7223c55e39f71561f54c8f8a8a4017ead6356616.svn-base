package cn.com.eju.deal.houseLinkage.statistic.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.linkage.service.LinkageCityService;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.contract.ContractInfoDto;
import cn.com.eju.deal.dto.houseLinkage.statistic.StatisticInfoDto;
import cn.com.eju.deal.houseLinkage.statistic.service.StatisticService;

/**   
* Controller层
* @author qianwei
* @date 2016年4月29日 下午9:25:30
*/
@Controller
@RequestMapping(value = "statistic")
public class StatisticController extends BaseController
{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "statisticService")
    private StatisticService statisticService;
    
    @Resource(name = "linkageCityService")
    private LinkageCityService linkageCityService;
    
    /** 
    * 初始化
    * @param request
    * @param model
    * @return
    */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap mop, HttpServletResponse response)
    {
        UserInfo userInfo = UserInfoHolder.get();
        String cityNo = userInfo.getCityNo();
        if (StringUtil.isNotEmpty(cityNo))
        {
            //区域列表
            ResultData<?> resultDistrictList = new ResultData<>();
            try
            {
                resultDistrictList = this.linkageCityService.getDistrictList(cityNo);
            }
            catch (Exception e)
            {
                logger.error("houseLinkage.statistic", "StatisticController", "list", "", null, "", "初始化", e);
            }
            mop.put("districtList", resultDistrictList.getReturnData());
        }
        
        //读取搜索条件 add by wangkanlin 2017/08/23
        Map<String, Object> map = bindParamToMap(request);
        if(map.containsKey("searchParam") && "1".equals(map.get("searchParam"))){
            Map<String,Object> searchParamMap = getRememberSearch(request, ComConstants.ESTATE_STAT_LIST_SEARCH);
            mop.put("searchParamMap", JSON.toJSON(searchParamMap));
        }else{
            clearSearch(request, response, ComConstants.ESTATE_STAT_LIST_SEARCH);
        }
        mop.put("list_search_page", ComConstants.ESTATE_STAT_LIST_SEARCH);
        
        return "houseLinkage/statistic/statisticList";
    }
    
    /** 
    * 查询--list
    * @param request
    * @param mop
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "q", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap mop, HttpServletResponse response)
    {
        
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        if(reqMap.containsKey("estateNm") && null!=reqMap.get("estateNm")){
        	String estateNm = reqMap.get("estateNm").toString();
        	estateNm = estateNm.trim();
        	reqMap.put("estateNm", estateNm);
        }
        
        PageInfo pageInfo = getPageInfo(request);
        
        //页面数据
        List<?> contentlist = null;
        try
        {
        	//保存搜索数据 add by wangkanlin 2017-08-23
            if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.ESTATE_STAT_LIST_SEARCH, reqMap);
            }
            //获取页面显示数据
            ResultData<?> reback = statisticService.index(reqMap, pageInfo);
            
            contentlist = (List<?>)reback.getReturnData();
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.statistic", "StatisticController", "index", "", null, "", "查询--list", e);
        }
        
        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        
        return "houseLinkage/statistic/statisticListCtx";
    }
    
    /** 
     *获取日期类型列表
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "dateKbnList/{dateTypeKbn}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> dateKbnList(@PathVariable("dateTypeKbn") String dateTypeKbn, HttpServletRequest request, ModelMap mop)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        // 部门列表
        ResultData<?> resultDateKbnList = new ResultData<>();
        try
        {
            resultDateKbnList = statisticService.getDateKbnList(dateTypeKbn);
            
            rspMap.put(Constant.RETURN_DATA_KEY, resultDateKbnList.getReturnData());
            rspMap.put(Constant.RETURN_CODE_KEY, resultDateKbnList.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultDateKbnList.getReturnMsg());
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.statistic", "StatisticController", "index", "", null, "", "获取日期类型列表", e);
        }
        return getSearchJSONView(rspMap);
    }
    
    /** 
     * 查询--公司统计初始化
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "qCompanyInit/{estateId}/{countDateStart}/{countDateEnd}/{countDateTypeKbn}", method = RequestMethod.GET)
    public String initCompanyList(HttpServletRequest request, ModelMap mop, HttpServletResponse response, @PathVariable("estateId") String estateId, @PathVariable("countDateStart") String countDateStart, @PathVariable("countDateEnd") String countDateEnd, @PathVariable("countDateTypeKbn") String countDateTypeKbn)
    {
        UserInfo userInfo = UserInfoHolder.get();
        mop.put("cityNo", userInfo.getCityNo());
        mop.put("estateId", estateId);
        mop.put("prevCountDateStart", countDateStart);
        mop.put("prevCountDateEnd", countDateEnd);
        mop.put("prevCountDateTypeKbn", countDateTypeKbn);
        mop.put("countDateStart", countDateStart);
        mop.put("countDateEnd", countDateEnd);
        mop.put("countDateTypeKbn", countDateTypeKbn);
        
        //读取搜索条件 add by wangkanlin 2017/08/24
        Map<String, Object> map = bindParamToMap(request);
        if(map.containsKey("searchParam") && "1".equals(map.get("searchParam"))){
            Map<String,Object> searchParamMap = getRememberSearch(request, ComConstants.ESTATE_STAT_COMPANY_LIST_SEARCH);
            mop.put("searchParamMap", JSON.toJSON(searchParamMap));
        }else{
            clearSearch(request, response, ComConstants.ESTATE_STAT_COMPANY_LIST_SEARCH);
        }
        mop.put("list_search_page", ComConstants.ESTATE_STAT_COMPANY_LIST_SEARCH);
        
        return "houseLinkage/statistic/statisticCompanyList";
    }
    
    /** 
     * 查询--公司统计list
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "qCompany", method = RequestMethod.GET)
    public String companyList(HttpServletRequest request, ModelMap mop, HttpServletResponse response)
    {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        
        try
        {
        	//保存搜索数据 add by wangkanlin 2017-08-24
            if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.ESTATE_STAT_COMPANY_LIST_SEARCH, reqMap);
            }
            //获取页面显示数据
            ResultData<?> reback = statisticService.companyList(reqMap, pageInfo);
            
            //页面数据
            List<?> contentlist = (List<?>)reback.getReturnData();
            
            //存放到mop中
            mop.addAttribute("contentlist", contentlist);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.statistic", "StatisticController", "companyList", "", null, "", "查询--公司统计list", e);
        }
        
        return "houseLinkage/statistic/statisticCompanyListCtx";
    }
    
    /** 
     * 查询--统计明细初始化
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "qStatisticDetailInit/{estateId}/{companyId}/{countDateStart}/{countDateEnd}/{countDateTypeKbn}", method = RequestMethod.GET)
    public String initStatisticDetailList(HttpServletRequest request, ModelMap mop, @PathVariable("estateId") String estateId, @PathVariable("companyId") String companyId, @PathVariable("countDateStart") String countDateStart, @PathVariable("countDateEnd") String countDateEnd, @PathVariable("countDateTypeKbn") String countDateTypeKbn)
    {
        UserInfo userInfo = UserInfoHolder.get();
        mop.put("cityNo", userInfo.getCityNo());
        mop.put("estateId", estateId);
        mop.put("companyId", companyId);
        mop.put("prevCountDateStart", countDateStart);
        mop.put("prevCountDateEnd", countDateEnd);
        mop.put("prevCountDateTypeKbn", countDateTypeKbn);
        if (StringUtil.isNotEmpty(userInfo.getCityNo()))
        {
            //区域列表
            ResultData<?> resultDistrictList = new ResultData<>();
            try
            {
                resultDistrictList = this.linkageCityService.getDistrictList(userInfo.getCityNo());
            }
            catch (Exception e)
            {
                logger.error("houseLinkage.statistic", "StatisticController", "initStatisticDetailList", "", null, "", " 查询--统计明细初始化", e);
            }
            mop.put("districtList", resultDistrictList.getReturnData());
        }
        return "houseLinkage/statistic/statisticDetailList";
    }
    
    /** 
     * 查询--统计明细list
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "qStatisticDetail", method = RequestMethod.GET)
    public String statisticDetailList(HttpServletRequest request, ModelMap mop)
    {
        
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        
        PageInfo pageInfo = getPageInfo(request);
        
        try
        {
            //获取页面显示数据
            ResultData<?> reback = statisticService.statisticDetailList(reqMap, pageInfo);
            
            //页面数据
            List<?> contentlist = (List<?>)reback.getReturnData();
            
            //存放到mop中
            mop.addAttribute("contentlist", contentlist);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.statistic", "StatisticController", "statisticDetailList", "", null, "", "查询--统计明细list", e);
        }
        
        return "houseLinkage/statistic/statisticDetailListCtx";
    }
    
    /** 
    * 创建--初始化
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "c", method = RequestMethod.GET)
    public String toAdd(ModelMap mop)
    {
        //返回map
        ResultData<?> resultData = new ResultData<ContractDto>();
        try
        {
            UserInfo userInfo = UserInfoHolder.get();
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.statistic", "StatisticController", "toAdd", "", null, "", "创建--初始化", e);
        }
        return "houseLinkage/statistic/statisticAdd";
    }
    
    /**
     *  创建
     *  @param request
     * @throws Exception 
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> create(HttpServletRequest request, ModelMap modelMap)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        
        ContractInfoDto contractInfoDto = new ContractInfoDto();
        try
        {
            //获取页面字段值，转为DTO
            setDto(reqMap, contractInfoDto, "create", "");
            
            ResultData<?> resultData = statisticService.create(contractInfoDto);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.statistic", "StatisticController", "create", "", null, "", "创建", e);
        }
        
        return getOperateJSONView(rspMap);
    }
    
    /** 
    * 修改--初始化
    * @param id
    * @param mop
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/{u}/{id}", method = RequestMethod.GET)
    public String toEdit(@PathVariable("u") String u, @PathVariable("id") int id, ModelMap mop)
    {
        Map<String, Object> map = new HashMap<>();
        //返回map
        ResultData<?> resultData = new ResultData<ContractDto>();
        
        try
        {
            resultData = statisticService.getById(id);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.statistic", "StatisticController", "toEdit", "", null, "", "修改--初始化", e);
        }
        
        //存放到mop中
        mop.addAttribute("statisticInfo", resultData.getReturnData());
        return "houseLinkage/statistic/statisticEdit";
    }
    
    /**
     *  修改 
     *  @param request
     * @throws Exception 
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnView<?, ?> update(HttpServletRequest request, @RequestParam(value = "isform", required = false) String isform, @PathVariable("id") String id)
    {
        
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.remove("_method");
        ContractInfoDto contractInfoDto = new ContractInfoDto();
        try
        {
            setDto(reqMap, contractInfoDto, "update", id);
            //更新
            statisticService.update(contractInfoDto);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.statistic", "StatisticController", "update", "", null, "", "修改 ", e);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        
        return getOperateJSONView(rspMap);
    }
    
    /** 
    * 查看
    * @param id
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/{estateId}/{companyId}/{customerId}", method = RequestMethod.GET)
    public String show(@PathVariable("estateId") String estateId, @PathVariable("companyId") String companyId, @PathVariable("customerId") String customerId, ModelMap mop)
    {
        //返回map
        ResultData<?> resultData = new ResultData<StatisticInfoDto>();
        
        try
        {
            resultData = statisticService.getstatistic(estateId, companyId, customerId);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.statistic", "StatisticController", "show", "", null, "", "查看 ", e);
        }
        
        //存放到mop中
        mop.addAttribute("statisticInfo", resultData.getReturnData());
        
        return "houseLinkage/statistic/statisticDetail";
    }
    
    /** 
    * 删除
    * @param id
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnView<?, ?> destroy(@PathVariable int id, HttpServletResponse response)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        //获取当前操作人usreId
        int updateId = 0;
        
        try
        {
            updateId = UserInfoHolder.getUserId();
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.statistic", "StatisticController", "destroy", "", null, "", "删除 ", e);
        }
        
        try
        {
            //删除
            statisticService.delete(id, updateId);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.statistic", "StatisticController", "destroy", "", null, "", "删除 ", e);
            
        }
        
        //响应码
        int status = response.getStatus();
        
        rspMap.put(Constant.RETURN_CODE_KEY, status);
        
        return getOperateJSONView(rspMap);
        
    }
    
    /** 
     * 获取页面字段值，转为DTO
     * @param reqMap
     * @param contractInfoDto
     */
    public static void setDto(Map<String, Object> reqMap, ContractInfoDto contractInfoDto, String type, String id)
        throws Exception
    {
        UserInfo userInfo = UserInfoHolder.get();
        
    }
    
    
    /** 
     * 查询--统计明细初始化(无公司编码，根据项目编码和 城市Id查询)
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "qStatisticDetailByEstateIdInit/{estateId}", method = RequestMethod.GET)
    public String initStatisticDetailByEstateIdList(HttpServletRequest request, ModelMap mop, @PathVariable("estateId") String estateId)
    {
        UserInfo userInfo = UserInfoHolder.get();
        mop.put("cityNo", userInfo.getCityNo());
        mop.put("estateId", estateId);
        if (StringUtil.isNotEmpty(userInfo.getCityNo()))
        {
            //区域列表
            ResultData<?> resultDistrictList = new ResultData<>();
            try
            {
                resultDistrictList = this.linkageCityService.getDistrictList(userInfo.getCityNo());
            }
            catch (Exception e)
            {
                logger.error("houseLinkage.statistic", "StatisticController", "initStatisticDetailList", "", null, "", " 查询--统计明细初始化", e);
            }
            mop.put("districtList", resultDistrictList.getReturnData());
        }
        return "houseLinkage/statistic/statisticDetailByEstateIdList";
    }
    
    /** 
     * 查询--统计明细list(无公司编码，根据项目编码和 城市Id查询)
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "qStatisticDetailByEstateId", method = RequestMethod.GET)
    public String statisticDetailByEstateIdList(HttpServletRequest request, ModelMap mop)
    {
        
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        
        PageInfo pageInfo = getPageInfo(request);
        
        try
        {
            //获取页面显示数据
            ResultData<?> reback = statisticService.statisticDetailList(reqMap, pageInfo);
            
            //页面数据
            List<?> contentlist = (List<?>)reback.getReturnData();
            
            //存放到mop中
            mop.addAttribute("contentlist", contentlist);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.statistic", "StatisticController", "statisticDetailList", "", null, "", "查询--统计明细list", e);
        }
        
        return "houseLinkage/statistic/statisticDetailByEstateIdListCtx";
    }
    
    
}
