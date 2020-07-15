package cn.com.eju.deal.scene.statistic.controller;

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
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.contract.ContractInfoDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateInfoDto;
import cn.com.eju.deal.scene.statistic.service.SceneStatisticEstateService;

/**   
* Controller层
* @author qianwei
* @date 2016年4月29日 下午9:25:30
*/
@Controller
@RequestMapping(value = "sceneStatisticEstate")
public class SceneStatisticEstateController extends BaseController
{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "sceneStatisticEstateService")
    private SceneStatisticEstateService sceneStatisticEstateService;
    
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
        String cityNo = UserInfoHolder.get().getCityNo();
        //区域列表
        ResultData<?> resultDistrictList = new ResultData<>();
        try
        {
            resultDistrictList = this.linkageCityService.getDistrictList(cityNo);
        }
        catch (Exception e)
        {
            logger.error("statistic", "SceneStatisticEstateController", "list", "", null, "", "", e);
        }
        mop.put("districtList", resultDistrictList.getReturnData());
        
        //读取搜索条件 add by wangkanlin 2017/08/23
        Map<String, Object> map = bindParamToMap(request);
        if(map.containsKey("searchParam") && "1".equals(map.get("searchParam"))){
            Map<String,Object> searchParamMap = getRememberSearch(request, ComConstants.SCENE_STAT_LIST_SEARCH);
            mop.put("searchParamMap", JSON.toJSON(searchParamMap));
        }else{
            clearSearch(request, response, ComConstants.SCENE_STAT_LIST_SEARCH);
        }
        mop.put("list_search_page", ComConstants.SCENE_STAT_LIST_SEARCH);
        return "scene/statistic/sceneStatisticEstateList";
    }
    
    /** 
    * 查询--统计楼盘list
    * @param request
    * @param mop
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "qSceneStatisticEstate", method = RequestMethod.GET)
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
        
        //需要数据权限
        reqMap.put(Constant.DATA_AUTH_KEY, true);
        
        //获取页面显示数据
        ResultData<?> reback = new ResultData<>();
        try
        {
        	//保存搜索数据 add by wangkanlin 2017-08-23
            if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.SCENE_STAT_LIST_SEARCH, reqMap);
            }
            reback = sceneStatisticEstateService.sceneStatisticEstate(reqMap, pageInfo);
        }
        catch (Exception e)
        {
            logger.error("statistic", "SceneStatisticEstateController", "index", "", null, "", "查询--统计楼盘list失败", e);
        }
        List<?> contentlist = (List<?>)reback.getReturnData();
        
        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        
        return "scene/statistic/sceneStatisticEstateListCtx";
    }
    
    /** 
     * 查询--统计公司list初始化
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "qSceneStatisticCompanyInit/{estateId}/{countDateStart}/{countDateEnd}/{countDateTypeKbn}", method = RequestMethod.GET)
    public String initSceneStatisticCompany(HttpServletRequest request, ModelMap mop, HttpServletResponse response, @PathVariable("estateId") String estateId, @PathVariable("countDateStart") String countDateStart, @PathVariable("countDateEnd") String countDateEnd, @PathVariable("countDateTypeKbn") String countDateTypeKbn)
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
        
        //返回map
        ResultData<?> resultData = new ResultData<ContractDto>();
        try
        {
            resultData = sceneStatisticEstateService.getByEstateId(estateId);
        }
        catch (Exception e)
        {
            logger.error("sceneStatisticEstate", "SceneStatisticEstateController", "qSceneStatisticCompanyInit/{estateId}/{countDateStart}/{countDateEnd}/{countDateTypeKbn}", "", null,"", "", e);
        }
        //存放到mop中
        mop.addAttribute("estateNm", resultData.getReturnData());
        
        //读取搜索条件 add by wangkanlin 2017/08/24
        Map<String, Object> map = bindParamToMap(request);
        if(map.containsKey("searchParam") && "1".equals(map.get("searchParam"))){
            Map<String,Object> searchParamMap = getRememberSearch(request, ComConstants.SCENE_STAT_COMPANY_LIST_SEARCH);
            mop.put("searchParamMap", JSON.toJSON(searchParamMap));
        }else{
            clearSearch(request, response, ComConstants.SCENE_STAT_COMPANY_LIST_SEARCH);
        }
        mop.put("list_search_page", ComConstants.SCENE_STAT_COMPANY_LIST_SEARCH);
        
        return "scene/statistic/sceneStatisticCompanyList";
    }
    
    /** 
     * 查询--统计公司list
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "qSceneStatisticCompany", method = RequestMethod.GET)
    public String sceneStatisticCompany(HttpServletRequest request, ModelMap mop, HttpServletResponse response)
    {
        
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        
        PageInfo pageInfo = getPageInfo(request);
        
        //需要数据权限
        reqMap.put(Constant.DATA_AUTH_KEY, true);
        
        //获取页面显示数据
        ResultData<?> reback = new ResultData<>();
        try
        {
        	//保存搜索数据 add by wangkanlin 2017-08-24
            if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.SCENE_STAT_COMPANY_LIST_SEARCH, reqMap);
            }
            reback = sceneStatisticEstateService.sceneStatisticCompany(reqMap, pageInfo);
        }
        catch (Exception e)
        {
            logger.error("statistic", "SceneStatisticEstateController", "sceneStatisticCompany", "", null, "", " 查询--统计公司list", e);
        }
        
        //页面数据
        List<?> contentlist = (List<?>)reback.getReturnData();
        
        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        
        return "scene/statistic/sceneStatisticCompanyListCtx";
    }
    
    /** 
     * 查询--统计明细list初始化
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "qSceneStatisticDetailInit/{estateId}/{companyId}/{countDateStart}/{countDateEnd}/{countDateTypeKbn}", method = RequestMethod.GET)
    public String initQuerySceneStatisticDetail(HttpServletRequest request, ModelMap mop, @PathVariable("estateId") String estateId, @PathVariable("companyId") String companyId, @PathVariable("countDateStart") String countDateStart, @PathVariable("countDateEnd") String countDateEnd, @PathVariable("countDateTypeKbn") String countDateTypeKbn)
    {
        UserInfo userInfo = UserInfoHolder.get();
        mop.put("cityNo", userInfo.getCityNo());
        mop.put("estateId", estateId);
        mop.put("companyId", companyId);
        mop.put("prevCountDateStart", countDateStart);
        mop.put("prevCountDateEnd", countDateEnd);
        mop.put("prevCountDateTypeKbn", countDateTypeKbn);
        mop.put("countDateStart", countDateStart);
        mop.put("countDateEnd", countDateEnd);
        mop.put("countDateTypeKbn", countDateTypeKbn);
        
        //返回map
        ResultData<?> resultData = new ResultData<ContractDto>();
        try
        {
            resultData = sceneStatisticEstateService.getByEstateId(estateId);
        }
        catch (Exception e)
        {
            logger.error("sceneStatisticEstate", "SceneStatisticEstateController", "initQuerySceneStatisticDetail", "", null, "", "", e);
        }
        //存放到mop中
        mop.addAttribute("estateNm", resultData.getReturnData());
        
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
                logger.error("statistic", "SceneStatisticEstateController", "list", "", null, "", "", e);
            }
            mop.put("districtList", resultDistrictList.getReturnData());
        }
        return "scene/statistic/sceneStatisticDetailList";
    }
    
    /** 
     * 查询--统计明细list
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "qSceneStatisticDetail", method = RequestMethod.GET)
    public String querySceneStatisticDetail(HttpServletRequest request, ModelMap mop)
    {
        
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        
        PageInfo pageInfo = getPageInfo(request);
        
        try
        {
            //需要数据权限
            reqMap.put(Constant.DATA_AUTH_KEY, true);
            
            //获取页面显示数据
            ResultData<?> reback = sceneStatisticEstateService.querySceneStatisticDetail(reqMap, pageInfo);
            
            //页面数据
            List<?> contentlist = (List<?>)reback.getReturnData();
            
            //存放到mop中
            mop.addAttribute("contentlist", contentlist);
        }
        catch (Exception e)
        {
            logger.error("statistic", "SceneStatisticEstateController", "querySceneStatisticDetail", "", null, "", "查询--统计明细list", e);
        }
        
        return "scene/statistic/sceneStatisticDetailListCtx";
    }
    
    /** 
     * 验证处理
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "check", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> checkStoreLock(HttpServletRequest request, ModelMap mop)
    
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = new ResultData<>();
        try
        {
            resultData = this.sceneStatisticEstateService.checkStoreLock(reqMap);
        }
        catch (Exception e)
        {
            logger.error("statistic", "SceneStatisticEstateController", "checkStoreLock", "", null, "", "验证处理", e);
        }
        
        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        if (null != resultData.getReturnData())
        {
            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        }
        return getMapView(rspMap);
    }
    
    /** 
    * 创建--初始化
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "c", method = RequestMethod.GET)
    public String toAdd(ModelMap mop)
    
    {
        UserInfo userInfo = UserInfoHolder.get();
        mop.addAttribute("userInfo", userInfo);
        //区域 districtList
        String cityNo = UserInfoHolder.get().getCityNo();
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
                logger.error("statistic", "SceneStatisticEstateController", "toAdd", "", null, "", "创建--初始化-区域列表", e);
            }
            mop.put("districtList", resultDistrictList.getReturnData());
        }
        //销售状态 salesStatusList
        mop.put("salesStatusList", SystemParam.getCodeListByKey(DictionaryConstants.SALES_STATUS));
        //合作方 partnerList
        mop.put("partnerList", SystemParam.getCodeListByKey(DictionaryConstants.PARTNER));
        //TODO 案场负责人 sceneDeptIdList 联动
        //        mop.put("sceneDeptIdList", SystemParam.getCodeListByKey(DictionaryConstants));
        //认证类型 authenticationKbnList
        mop.put("authenticationKbnList", SystemParam.getCodeListByKey(DictionaryConstants.AUTHENTICATION_KBN));
        //佣金方式 commissionKbnList
        mop.put("commissionKbnList", SystemParam.getCodeListByKey(DictionaryConstants.COMMISSION_KBN));
        //结佣方式 payKbnList
        mop.put("payKbnList", SystemParam.getCodeListByKey(DictionaryConstants.PAY_KBN));
        //销售方式 saleKbnList
        mop.put("saleKbnList", SystemParam.getCodeListByKey(DictionaryConstants.SALE_KBN));
        //报备方式 reportKbnList
        mop.put("reportKbnList", SystemParam.getCodeListByKey(DictionaryConstants.REPORT_KBN));
        //朝向 directionKbnList
        mop.put("directionKbnList", SystemParam.getCodeListByKey(DictionaryConstants.DIRECTION_KBN));
        //物业类型 mgtKbnList
        mop.put("mgtKbnList", SystemParam.getCodeListByKey(DictionaryConstants.MGT_KBN));
        //产权年限 ownYearKbnList
        mop.put("ownYearKbnList", SystemParam.getCodeListByKey(DictionaryConstants.OWNYEAR_KBN));
        //装修情况 decorationKbnList
        mop.put("decorationKbnList", SystemParam.getCodeListByKey(DictionaryConstants.DECORATION_KBN));
        //建筑类型 typeKbnList
        mop.put("typeKbnList", SystemParam.getCodeListByKey(DictionaryConstants.TYPE_KBN));
        //供暖方式 heatKbnList
        mop.put("heatKbnList", SystemParam.getCodeListByKey(DictionaryConstants.HEAT_KBN));
        //水电燃气 hydropowerGasKbnList
        mop.put("hydropowerGasKbnList", SystemParam.getCodeListByKey(DictionaryConstants.HYDROPOWERGAS_KBN));
        return "scene/estate/estateAdd";
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
        try
        {
            ResultData<?> resultData = sceneStatisticEstateService.create(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        }
        catch (Exception e)
        {
            logger.error("statistic", "SceneStatisticEstateController", "create", "", null, "", "创建", e);
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
            resultData = sceneStatisticEstateService.getById(id);
        }
        catch (Exception e)
        {
            logger.error("statistic", "SceneStatisticEstateController", "toEdit", "", null, "", "修改--初始化", e);
        }
        
        //存放到mop中
        mop.addAttribute("estateInfo", resultData.getReturnData());
        return "scene/statistic/estateEdit";
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
        try
        {
            //更新
            sceneStatisticEstateService.update(reqMap);
        }
        catch (Exception e)
        {
            logger.error("statistic", "SceneStatisticEstateController", "update", "", null, "", "修改 ", e);
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
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Integer id, ModelMap mop)
    {
        //返回map
        ResultData<?> resultData = new ResultData<ContractInfoDto>();
        
        try
        {
            resultData = sceneStatisticEstateService.getById(id);
        }
        catch (Exception e)
        {
            logger.error("statistic", "SceneStatisticEstateController", "show", "", null, "", "查看 ", e);
        }
        
        //存放到mop中
        mop.addAttribute("estateInfo", resultData.getReturnData());
        
        return "scene/statistic/estateDetail";
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
            logger.error("statistic", "SceneStatisticEstateController", "destroy", "", null, "", " updateId ", e);
        }
        
        try
        {
            //删除
            sceneStatisticEstateService.delete(id, updateId);
        }
        catch (Exception e)
        {
            logger.error("statistic", "SceneStatisticEstateController", "destroy", "", null, "", "", e);
        }
        
        //响应码
        int status = response.getStatus();
        
        rspMap.put(Constant.RETURN_CODE_KEY, status);
        
        return getOperateJSONView(rspMap);
        
    }
    
    /**
     *  审核
     *  @param request
     * @throws Exception 
     */
    @RequestMapping(value = "/audit/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnView<?, ?> audit(HttpServletRequest request, @RequestParam(value = "isform", required = false) String isform, @PathVariable("id") String id)
    {
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        ContractInfoDto contractInfoDto = new ContractInfoDto();
        UserInfo userInfo = UserInfoHolder.get();
        //记录人Id
        Integer userIdCreate = userInfo.getUserId();
        EstateInfoDto contractDto = new EstateInfoDto();
        try
        {
            //更新
            sceneStatisticEstateService.audit(contractDto);
        }
        catch (Exception e)
        {
            logger.error("statistic", "SceneStatisticEstateController", "audit", "", null, "", "", e);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
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
     *  创建
     *  @param request
     * @throws Exception 
     */
//    @RequestMapping(value = "upload", method = RequestMethod.POST)
//    @ResponseBody
//    public ReturnView<?, ?> uploadFile(HttpServletRequest request, ModelMap modelMap)
//    {
//        //返回map
//        Map<String, Object> rspMap = new HashMap<String, Object>();
//        try
//        {
//            //请求map
//            Map<String, Object> reqMap = bindParamToMap(request);
//            rspMap = this.sceneStatisticEstateService.uploadFile(request, reqMap);
//        }
//        catch (Exception e)
//        {
//            logger.error("statistic", "SceneStatisticEstateController", "uploadFile", "", null, "", "", e);
//        }
//        
//        return getOperateJSONView(rspMap);
//    }
}
