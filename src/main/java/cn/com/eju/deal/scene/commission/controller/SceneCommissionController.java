package cn.com.eju.deal.scene.commission.controller;

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

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.linkage.service.LinkageCityService;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.contract.ContractInfoDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateInfoDto;
import cn.com.eju.deal.houseLinkage.estate.service.EstateService;
import cn.com.eju.deal.scene.commission.service.SceneCommissionService;

/**   
* Controller层
* @author qianwei
* @date 2016年4月29日 下午9:25:30
*/
@Controller
@RequestMapping(value = "sceneCommission")
public class SceneCommissionController extends BaseController
{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "sceneCommissionService")
    private SceneCommissionService sceneCommissionService;
    
    @Resource(name = "linkageCityService")
    private LinkageCityService linkageCityService;
    
    @Resource(name = "estateService")
    private EstateService estateService;
    /** 
    * 初始化
    * @param request
    * @param model
    * @return
    */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap mop)
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
            logger.error("sceneCommission", "SceneCommissionController", "", "", null, "", "", e);
        }
      //归属项目部列表
        try{
        	Map<String,Object> reqMap = new HashMap<>();
        	reqMap.put("cityNo", cityNo);
        	ResultData<?> resultData = estateService.getProjectDepartment(reqMap);
        	List<?> rebacklist = (List<?>) resultData.getReturnData();
        	mop.put("rebacklist", rebacklist);
        }catch(Exception e)
        {
        	 logger.error("sceneInCommission", "SceneInCommissionController", "", "", null, "", "创建--初始化-归属项目部", e);
        }
        
        mop.put("districtList", resultDistrictList.getReturnData());

        try{
            Map<String,Object> reqMap = new HashMap<>();
            reqMap.put("cityNo", cityNo);
            ResultData<?> resultData = estateService.getEstateNmList(reqMap);
            List<?> estateList = (List<?>) resultData.getReturnData();
            mop.put("estateList", estateList);
            mop.addAttribute("estateTypeList", SystemParam.getCodeListByKey("245"));
        } catch (Exception e) {
            logger.error("sceneInCommission", "SceneInCommissionController", "", "", null, "", "创建--初始化-楼盘名称", e);
        }
        return "scene/commission/sceneCommissionList";
    }
    
    /** 
    * 查询--佣金楼盘list
    * @param request
    * @param mop
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "qSceneCommission", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap mop)
    {
        
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        if(reqMap.containsKey("estateNm") && null!=reqMap.get("estateNm")){
        	String estateNm = reqMap.get("estateNm").toString();
        	estateNm = estateNm.trim();
        	reqMap.put("estateNm", estateNm);
        }
        
        PageInfo pageInfo = getPageInfo(request);
        reqMap.put(Constant.DATA_AUTH_KEY, true);
        //获取页面显示数据
        ResultData<?> reback = new ResultData<>();
        try
        {
            reback = sceneCommissionService.sceneCommission(reqMap, pageInfo);
        }
        catch (Exception e)
        {
            logger.error("sceneCommission", "SceneCommissionController", "qSceneCommission", "", null, "", "", e);
        }
        
        //页面数据
        List<?> contentlist = (List<?>)reback.getReturnData();
        
        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        
        return "scene/commission/sceneCommissionListCtx";
    }
    
    /** 
     * 查询-佣金明细
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "qSceneCommissionDetail/{estateId}", method = RequestMethod.GET)
    public String sceneRecognition(HttpServletRequest request, ModelMap mop, @PathVariable("estateId") String estateId)
    {
        String cityNo = UserInfoHolder.get().getCityNo();
        mop.addAttribute("cityNo", cityNo);
        mop.addAttribute("estateId", estateId);
        //返回map
        ResultData<?> resultData = new ResultData<ContractDto>();
        try
        {
            resultData = sceneCommissionService.getByEstateId(estateId);
        }
        catch (Exception e)
        {
            logger.error("sceneCommission", "SceneCommissionController", "qSceneCommissionDetail/{estateId}", "", null, "", "", e);
        }
        //存放到mop中
        mop.addAttribute("estateNm", resultData.getReturnData());
        return "scene/commission/sceneCommissionDetailList";
    }
    
    /** 
     * 查询--佣金明细list
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "qSceneCommissionDetail/q", method = RequestMethod.GET)
    public String sceneStatisticCompany(HttpServletRequest request, ModelMap mop)
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
            reback = sceneCommissionService.sceneCommissionDetail(reqMap, pageInfo);
        }
        catch (Exception e)
        {
            logger.error("sceneCommission", "SceneCommissionController", "qSceneCommission/q", "", null, "", "", e);
        }
        
        //页面数据
        List<?> contentlist = (List<?>)reback.getReturnData();
        
        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        
        return "scene/commission/sceneCommissionDetailListCtx";
    }
    
    /** 
     * 修改佣金list
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "qSceneCommissionDetail/modify", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> sceneRecognitionModify(HttpServletRequest request, ModelMap mop)
    {
        
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        
        ResultData<?> resultData = new ResultData<>();
        try
        {
            resultData = this.sceneCommissionService.sceneCommissionDetailModify(reqMap);
        }
        catch (Exception e)
        {
            logger.error("sceneCommission", "SceneCommissionController", "qSceneCommission/q", "", null, "", "", e);
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
     * 确认结算list
     * 
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "qSceneCommissionDetail/confirm", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> sceneRecognitionConfirm(HttpServletRequest request, ModelMap mop)
    {
        
        // 返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        // 获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        
        ResultData<?> resultData = new ResultData<>();
        try
        {
            resultData = this.sceneCommissionService.sceneCommissionDetailConfirm(reqMap);
        }
        catch (Exception e)
        {
            logger.error("sceneCommission", "SceneCommissionController", "qSceneCommission/confirm", "", null, "", "", e);
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
     * 验证处理
     * 
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "check", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> checkStoreLock(HttpServletRequest request, ModelMap mop)
    {
        // 返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        // 获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = new ResultData<>();
        try
        {
            resultData = this.sceneCommissionService.checkStoreLock(reqMap);
        }
        catch (Exception e)
        {
            logger.error("sceneCommission", "SceneCommissionController", "check", "", null, "", "", e);
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
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "c", method = RequestMethod.GET)
    public String toAdd(ModelMap mop)
    {
        UserInfo userInfo = UserInfoHolder.get();
        mop.addAttribute("userInfo", userInfo);
        // 区域 districtList
        String cityNo = UserInfoHolder.get().getCityNo();
        if (StringUtil.isNotEmpty(cityNo))
        {
            // 区域列表
            ResultData<?> resultDistrictList = new ResultData<>();
            try
            {
                resultDistrictList = this.linkageCityService.getDistrictList(cityNo);
            }
            catch (Exception e)
            {
                logger.error("sceneCommission", "SceneCommissionController", "toAdd", "", null, "", "", e);
            }
            mop.put("districtList", resultDistrictList.getReturnData());
        }
        // 销售状态 salesStatusList
        mop.put("salesStatusList", SystemParam.getCodeListByKey(DictionaryConstants.SALES_STATUS));
        // 合作方 partnerList
        mop.put("partnerList", SystemParam.getCodeListByKey(DictionaryConstants.PARTNER));
        // TODO 案场负责人 sceneDeptIdList 联动
        // mop.put("sceneDeptIdList",
        // SystemParam.getCodeListByKey(DictionaryConstants));
        // 认证类型 authenticationKbnList
        mop.put("authenticationKbnList", SystemParam.getCodeListByKey(DictionaryConstants.AUTHENTICATION_KBN));
        // 佣金方式 commissionKbnList
        mop.put("commissionKbnList", SystemParam.getCodeListByKey(DictionaryConstants.COMMISSION_KBN));
        // 结佣方式 payKbnList
        mop.put("payKbnList", SystemParam.getCodeListByKey(DictionaryConstants.PAY_KBN));
        // 销售方式 saleKbnList
        mop.put("saleKbnList", SystemParam.getCodeListByKey(DictionaryConstants.SALE_KBN));
        // 报备方式 reportKbnList
        mop.put("reportKbnList", SystemParam.getCodeListByKey(DictionaryConstants.REPORT_KBN));
        // 朝向 directionKbnList
        mop.put("directionKbnList", SystemParam.getCodeListByKey(DictionaryConstants.DIRECTION_KBN));
        // 物业类型 mgtKbnList
        mop.put("mgtKbnList", SystemParam.getCodeListByKey(DictionaryConstants.MGT_KBN));
        // 产权年限 ownYearKbnList
        mop.put("ownYearKbnList", SystemParam.getCodeListByKey(DictionaryConstants.OWNYEAR_KBN));
        // 装修情况 decorationKbnList
        mop.put("decorationKbnList", SystemParam.getCodeListByKey(DictionaryConstants.DECORATION_KBN));
        // 建筑类型 typeKbnList
        mop.put("typeKbnList", SystemParam.getCodeListByKey(DictionaryConstants.TYPE_KBN));
        // 供暖方式 heatKbnList
        mop.put("heatKbnList", SystemParam.getCodeListByKey(DictionaryConstants.HEAT_KBN));
        // 水电燃气 hydropowerGasKbnList
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
        ResultData<?> resultData = new ResultData<>();
        try
        {
            resultData = sceneCommissionService.create(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        }
        catch (Exception e)
        {
            logger.error("sceneCommission", "SceneCommissionController", "", "post", null, "", "", e);
        }
        
        return getOperateJSONView(rspMap);
    }
    
    /**
     * 修改--初始化
     * 
     * @param id
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "statistic/{u}/{id}", method = RequestMethod.GET)
    public String toEdit(@PathVariable("u") String u, @PathVariable("id") int id, ModelMap mop)
    {
        Map<String, Object> map = new HashMap<>();
        // 返回map
        ResultData<?> resultData = new ResultData<ContractDto>();
        
        try
        {
            resultData = sceneCommissionService.getById(id);
        }
        catch (Exception e)
        {
            logger.error("sceneCommission", "SceneCommissionController", "statistic/{u}/{id}", "", null, "", "", e);
        }
        
        // 存放到mop中
        mop.addAttribute("estateInfo", resultData.getReturnData());
        return "scene/statistic/estateEdit";
    }
    
    /**
     * 修改
     * 
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnView<?, ?> update(HttpServletRequest request, @RequestParam(value = "isform", required = false) String isform, @PathVariable("id") String id)
    {
        
        // 返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        // 获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.remove("_method");
        try
        {
            // 更新
            sceneCommissionService.update(reqMap);
        }
        catch (Exception e)
        {
            
            logger.error("sceneCommission", "SceneCommissionController", "{id}", "put", null, "", "", e);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        
        return getOperateJSONView(rspMap);
    }
    
    /**
     * 查看
     * 
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Integer id, ModelMap mop)
    {
        // 返回map
        ResultData<?> resultData = new ResultData<ContractInfoDto>();
        
        try
        {
            resultData = sceneCommissionService.getById(id);
        }
        catch (Exception e)
        {
            logger.error("sceneCommission", "SceneCommissionController", "{id}", "get", null, "", "", e);
        }
        
        // 存放到mop中
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
        int updateId = UserInfoHolder.getUserId();
        try
        {
            //删除
            sceneCommissionService.delete(id, updateId);
        }
        catch (Exception e)
        {
            logger.error("sceneCommission", "SceneCommissionController", "{id}", "delete", null, "", "", e);
        }
        
        //响应码
        int status = response.getStatus();
        
        rspMap.put(Constant.RETURN_CODE_KEY, status);
        
        return getOperateJSONView(rspMap);
        
    }
    
    /**
     * 审核
     * 
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "/audit/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnView<?, ?> audit(HttpServletRequest request, @RequestParam(value = "isform", required = false) String isform, @PathVariable("id") String id)
    {
        // 获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        // 返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        ContractInfoDto contractInfoDto = new ContractInfoDto();
        UserInfo userInfo = UserInfoHolder.get();
        // 记录人Id
        Integer userIdCreate = userInfo.getUserId();
        EstateInfoDto contractDto = new EstateInfoDto();
        try
        {
            // 更新
            sceneCommissionService.audit(contractDto);
        }
        catch (Exception e)
        {
            logger.error("sceneCommission", "SceneCommissionController", "audit/{id}", "", null, "", "", e);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }
    
    /**
     * 获取页面字段值，转为DTO
     * 
     * @param reqMap
     * @param contractInfoDto
     */
    public static void setDto(Map<String, Object> reqMap, ContractInfoDto contractInfoDto, String type, String id)
        throws Exception
    {
        UserInfo userInfo = UserInfoHolder.get();
        
    }
    
    /**
     * 创建
     * 
     * @param request
     * @throws Exception
     */
//    @RequestMapping(value = "upload", method = RequestMethod.POST)
//    @ResponseBody
//    public ReturnView<?, ?> uploadFile(HttpServletRequest request, ModelMap modelMap)
//        throws Exception
//    {
//        // 返回map
//        Map<String, Object> rspMap = new HashMap<String, Object>();
//        try
//        {
//            // 请求map
//            Map<String, Object> reqMap = bindParamToMap(request);
//            rspMap = this.sceneCommissionService.uploadFile(request, reqMap);
//        }
//        catch (Exception e)
//        {
//            throw e;
//        }
//        
//        return getOperateJSONView(rspMap);
//    }
}
