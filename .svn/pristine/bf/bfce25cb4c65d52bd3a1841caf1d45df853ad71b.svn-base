package cn.com.eju.deal.scene.estate.controller;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.linkage.service.LinkageCityService;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.cashbill.service.CashBillService;
import cn.com.eju.deal.common.service.CommonService;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.common.util.DateUtils;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.contract.ContractInfoDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateInfoDto;
import cn.com.eju.deal.dto.oa.OaOperatorDto;
import cn.com.eju.deal.frameContract.service.FrameContractService;
import cn.com.eju.deal.houseLinkage.estate.service.EstateService;
import cn.com.eju.deal.houseLinkage.report.service.ReportService;
import cn.com.eju.deal.oa.service.OaOperatorService;
import cn.com.eju.deal.scene.estate.dto.BatchSaleDetail;
import cn.com.eju.deal.scene.estate.service.BatchSaleService;
import cn.com.eju.deal.scene.estate.service.SceneEstateService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STUnsignedShortHex;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**   
* Controller层
* @author qianwei
* @date 2016年4月29日 下午9:25:30
*/
@Controller
@RequestMapping(value = "sceneEstate")
public class SceneEstateController extends BaseReportController
{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "sceneEstateService")
    private SceneEstateService sceneEstateService;
    
    @Resource(name = "linkageCityService")
    private LinkageCityService linkageCityService;
    
    @Resource(name = "commonService")
    private CommonService commonService;

    @Resource(name = "reportService")
    private ReportService reportService;

    @Resource(name="cashBillService")
    private CashBillService cashBillService;

    @Resource(name = "oaOperatorService")
    private OaOperatorService oaOperatorService;

    @Resource(name = "batchSaleService")
    private BatchSaleService batchSaleService;
    @Resource
    private FrameContractService frameContractService;
    
    @Resource(name = "estateService")
    private EstateService estateService;

    /** 
    * 初始化
    * @param request
    * @param mop
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
            logger.error("sceneestate", "SceneEstateController", "list", "", null, "", "", e);
        }
        
      //得到归属项目部 add by zhenggang.Huang 2019/07/12 begin
        List<?> rebacklist = null;
        try{
        	Map<String,Object> reqMap = new HashMap<>();
        	reqMap.put("cityNo", cityNo);
        	ResultData<?> resultData = estateService.getProjectDepartment(reqMap);
        	rebacklist = (List<?>) resultData.getReturnData();
        }catch(Exception e)
        {
        	 logger.error("houseLinkage.report", "ReportController", "list", "", null, "", "创建--初始化-归属项目部", e);
        }
        mop.put("rebacklist", rebacklist);
        //end
        mop.put("districtList", resultDistrictList.getReturnData());

        //读取搜索条件 add by wangkanlin 2017/08/23
        Map<String, Object> map = bindParamToMap(request);
        if(map.containsKey("searchParam") && "1".equals(map.get("searchParam"))){
            Map<String,Object> searchParamMap = getRememberSearch(request, ComConstants.SCENE_ESTATE_LIST_SEARCH);
            mop.put("searchParamMap", JSON.toJSON(searchParamMap));
        }else{
            clearSearch(request, response, ComConstants.SCENE_ESTATE_LIST_SEARCH);
        }
        mop.put("list_search_page", ComConstants.SCENE_ESTATE_LIST_SEARCH);
        
        return "scene/estate/sceneEstateList";
    }
    
    /** 
    * 查询--list
    * @param request
    * @param mop
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "qSceneEstate", method = RequestMethod.GET)
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
        ResultData<?> reback = new ResultData<>();
        try
        {
        	//保存搜索数据 add by wangkanlin 2017-08-23
            if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.SCENE_ESTATE_LIST_SEARCH, reqMap);
            }
            //获取页面显示数据
            reback = sceneEstateService.index(reqMap, pageInfo);
        }
        catch (Exception e)
        {
            logger.error("sceneestate", "SceneEstateController", "qSceneEstate", "", null, "", "", e);
        }
        //页面数据
        List<?> contentlist = (List<?>)reback.getReturnData();
        
        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        
        return "scene/estate/sceneEstateListCtx";
    }
    
    /** 
     * 查询-未认定报备
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "qSceneRecognition/{estateId}", method = RequestMethod.GET)
    public String sceneRecognition(HttpServletRequest request,HttpServletResponse response, ModelMap mop, @PathVariable("estateId") String estateId)
    {
        String cityNo = UserInfoHolder.get().getCityNo();
        mop.addAttribute("cityNo", cityNo);
        mop.addAttribute("estateId", estateId);
        //返回map
        ResultData<?> resultData = new ResultData<ContractDto>();
        ResultData<?> resultDataSwitch = new ResultData<HashMap<String,Object>>();
        try
        {
            resultData = sceneEstateService.getByEstateId(estateId);
            //获取关账信息
            resultDataSwitch = commonService.getSwitchInfo(cityNo);
            Map<String, Object> map = (Map<String, Object>) resultDataSwitch.getReturnData();
            mop.addAttribute("yearMonth", map.get("yearMonth"));
            mop.addAttribute("isShowCurrDate", map.get("isShowCurrDate"));
            
//            Map<String, Object> map = getSwitchInfo(request,cityNo);
//            mop.addAttribute("yearMonth", map.get("yearMonth"));
//            mop.addAttribute("isShowCurrDate", map.get("isShowCurrDate"));
        }
        catch (Exception e)
        {
            logger.error("sceneestate", "SceneEstateController", "qSceneRecognition/{estateId}", "", null, "", "", e);
        }
        //存放到mop中
        mop.addAttribute("estate", resultData.getReturnData());
      //得到归属项目部 add by zhenggang.Huang 2019/07/12 begin
        List<?> rebacklist = null;
        try{
        	Map<String,Object> reqMap = new HashMap<>();
        	reqMap.put("cityNo", cityNo);
        	ResultData<?> projectDepartmentresultData = estateService.getProjectDepartment(reqMap);
        	rebacklist = (List<?>) projectDepartmentresultData.getReturnData();
        }catch(Exception e)
        {
        	 logger.error("sceneestate", "SceneEstateController", "qSceneRecognition/{estateId}", "", null, "", "创建--初始化-归属项目部", e);
        }
        mop.put("rebacklist", rebacklist);
        //end
        Map<String, Object> map = bindParamToMap(request);
        if(map.containsKey("searchParam") && "1".equals(map.get("searchParam"))){
            Map<String,Object> searchParamMap = getRememberSearch(request, ComConstants.SCENERECOGNITIONLIST);
            mop.put("searchParamMap", JSON.toJSON(searchParamMap));
        }else{
            clearSearch(request, response, ComConstants.SCENERECOGNITIONLIST);
        }
        mop.put("list_search_page", ComConstants.SCENERECOGNITIONLIST);

        try {
            Map<String,Object> queryParam = new HashMap<>();
            queryParam.put("estateId",estateId);
            queryParam.put("userCode",UserInfoHolder.get().getUserCode());
            ResultData<?> cashBill = cashBillService.selectCashBill(queryParam);
            if("200".equals(cashBill.getReturnCode())&&cashBill.getTotalCount() != null){
                mop.put("cashCount",cashBill.getTotalCount());
            }

//            ResultData<?> batchSale=sceneEstateService.countBatchSaleDetailAll(queryParam);
            ResultData<?> batchSale=batchSaleService.countBatchSaleDetailAll(queryParam);
            if ("0000".equals(batchSale.getReturnCode())&& batchSale.getReturnData()!=null){
                mop.put("batchSaleCount",batchSale.getReturnData());
            }

        }catch (Exception e){
            logger.error("sceneestate", "SceneEstateController", "qSceneRecognition/{estateId}", "", null, "", "查询批量请款套数异常", e);
        }
        String oaOperatorStr = "1";
        //查询登陆人的核算主体数量
        ResultData usercodeResult = null;
        try {
			usercodeResult = frameContractService.getUserMappingAccountProject(UserInfoHolder.get().getUserCode());
			if(usercodeResult.getReturnData() == null){
				oaOperatorStr = "0";
            }
		} catch (Exception e) {
			logger.error("sceneestate", "SceneEstateController", "qSceneRecognition/{estateId}", "", UserInfoHolder.getUserId(), "", "查看登陆人的核算主体失败", e);
		}
        mop.addAttribute("oaOperatorStr", oaOperatorStr);
        //查询是否是经办人
        OaOperatorDto oaOperatorDto = new OaOperatorDto();
        try{
            ResultData<?> backResult = oaOperatorService.getByUserCode(UserInfoHolder.get().getUserCode());
            Map<?, ?> mapTemp = (Map<?, ?>)backResult.getReturnData();
            if (null != mapTemp){
                oaOperatorDto = MapToEntityConvertUtil.convert(mapTemp, OaOperatorDto.class, "");
            }
        }
        catch (Exception e)
        {
            logger.error("sceneEstate",
                    "SceneEstateController",
                    "sceneRecognition",
                    "",
                    UserInfoHolder.getUserId(),
                    "",
                    "查询是否是经办人失败",
                    e);
        }

        //存放到mop中
        mop.addAttribute("oaOperator", oaOperatorDto);

        return "scene/estate/sceneRecognitionList";
    }
    
    /** 
     * 查询--未认定报备list
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "qSceneRecognition/q", method = RequestMethod.GET)
    public String sceneRecognitionDetail(HttpServletRequest request,HttpServletResponse response, ModelMap mop)
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
            if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.SCENERECOGNITIONLIST, reqMap);
            }
            reback = sceneEstateService.sceneRecognition(reqMap, pageInfo);
        }
        catch (Exception e)
        {
            logger.error("sceneestate", "SceneEstateController", "qSceneRecognition/q", "", null, "", "", e);
        }
        
        //页面数据
        List<?> contentlist = (List<?>)reback.getReturnData();
        
        //存放到mop中
        mop.addAttribute("userAcCityNo",  UserInfoHolder.get().getCityNo());
        mop.addAttribute("contentlist", contentlist);
        String oaOperatorStr = "1";
        //查询登陆人的核算主体数量
        ResultData usercodeResult = null;
        try {
			usercodeResult = frameContractService.getUserMappingAccountProject(UserInfoHolder.get().getUserCode());
			if(usercodeResult.getReturnData() == null){
				oaOperatorStr = "0";
            }
		} catch (Exception e) {
			logger.error("sceneestate", "SceneEstateController", "qSceneRecognition/q", "", UserInfoHolder.getUserId(), "", "查看登陆人的核算主体失败", e);
		}
        mop.addAttribute("oaOperatorStr", oaOperatorStr);
        //查询是否是经办人
        OaOperatorDto oaOperatorDto = new OaOperatorDto();
        try{
            ResultData<?> backResult = oaOperatorService.getByUserCode(UserInfoHolder.get().getUserCode());
            Map<?, ?> mapTemp = (Map<?, ?>)backResult.getReturnData();
            if (null != mapTemp){
                oaOperatorDto = MapToEntityConvertUtil.convert(mapTemp, OaOperatorDto.class, "");
            }
        }
        catch (Exception e)
        {
            logger.error("sceneEstate",
                    "SceneEstateController",
                    "sceneRecognition",
                    "",
                    UserInfoHolder.getUserId(),
                    "",
                    "查询是否是经办人失败",
                    e);
        }

        //存放到mop中
        mop.addAttribute("oaOperator", oaOperatorDto);
        
        return "scene/estate/sceneRecognitionListCtx";
    }
    
    /** 
     * 认定list
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "qSceneRecognition/confirm", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> sceneRecognitionConfirm(HttpServletRequest request, ModelMap mop)
    {
        
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userCode", UserInfoHolder.get().getUserCode());
        reqMap.put("userName", UserInfoHolder.get().getUserName());
        reqMap.put("userId",UserInfoHolder.get().getUserId());
        
        ResultData<?> resultData = new ResultData<>();
        try
        {
            resultData = this.sceneEstateService.sceneRecognitionConfirm(reqMap);
        }
        catch (Exception e)
        {
            logger.error("sceneestate", "SceneEstateController", "qSceneRecognition/confirm", "", null, "", "", e);
        }
        
        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        if (null != resultData.getReturnData())
        {
            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        }
        return getMapView(rspMap);
    }
    @RequestMapping(value = "qSceneRecognition/confirm/post", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> sceneRecognitionConfirmPost(HttpServletRequest request, ModelMap mop)
    {

        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId",UserInfoHolder.getUserId());
        reqMap.put("userCode", UserInfoHolder.get().getUserCode());
        reqMap.put("userName", UserInfoHolder.get().getUserName());

        //logger.error("sceneestate", "SceneEstateController", "qSceneRecognition/confirm", JsonUtil.parseToJson(reqMap), null, "", "大定重复记录记错误日志", new Exception());

        ResultData<?> resultData = new ResultData<>();
        try
        {
            resultData = this.sceneEstateService.sceneRecognitionConfirm(reqMap);
        }
        catch (Exception e)
        {
            logger.error("sceneestate", "SceneEstateController", "qSceneRecognition/confirm", "", null, "", "", e);
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
            resultData = this.sceneEstateService.checkStoreLock(reqMap);
        }
        catch (Exception e)
        {
            logger.error("sceneestate", "SceneEstateController", "check", "", null, "", "", e);
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
                logger.error("sceneestate", "SceneEstateController", "toAdd", "", null, "", "", e);
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
        ResultData<?> resultData = new ResultData<>();
        try
        {
            resultData = sceneEstateService.create(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        }
        catch (Exception e)
        {
            logger.error("sceneestate", "SceneEstateController", "", "", null, "", "", e);
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
        //返回map
        ResultData<?> resultData = new ResultData<ContractDto>();
        
        try
        {
            resultData = sceneEstateService.getById(id);
        }
        catch (Exception e)
        {
            logger.error("sceneestate", "SceneEstateController", "{u}/{id}", "", null, "", "", e);
        }
        
        //存放到mop中
        mop.addAttribute("estateInfo", resultData.getReturnData());
        return "scene/estate/estateEdit";
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
            sceneEstateService.update(reqMap);
        }
        catch (Exception e)
        {
            logger.error("sceneestate", "SceneEstateController", "{id}", "put", null, "", "", e);
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
            resultData = sceneEstateService.getById(id);
        }
        catch (Exception e)
        {
            logger.error("sceneestate", "SceneEstateController", "{id}", "get", null, "", "", e);
        }
        
        //存放到mop中
        mop.addAttribute("estateInfo", resultData.getReturnData());
        
        return "scene/estate/estateDetail";
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
        
        updateId = UserInfoHolder.getUserId();
        
        try
        {
            //删除
            sceneEstateService.delete(id, updateId);
        }
        catch (Exception e)
        {
            logger.error("sceneestate", "SceneEstateController", "{id}", "delete", null, "", "", e);
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
        Map<String, Object> rspMap = new HashMap<String, Object>();
        EstateInfoDto contractDto = new EstateInfoDto();
        try
        {
            //更新
            sceneEstateService.audit(contractDto);
        }
        catch (Exception e)
        {
            logger.error("sceneestate", "SceneEstateController", "audit/{id}", "", null, "", "", e);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }
    
    /**
     * 点击带看和认筹
     * @param mop
     * @param status
     * @param request
     * @return
     */
    @RequestMapping(value = "toSceneHouseInfoAddOne/{status}", method = RequestMethod.GET)
    public String toSceneHouseInfoAddOne(ModelMap mop,@PathVariable("status") Integer status,HttpServletRequest request)
    {
        Map<String, Object> reqMap = bindParamToMap(request);
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("reportNo",reqMap.get("reportId"));
            paramMap.put("progress",status);
            paramMap.put("confirmStatus",13603);
            ResultData<?> resultData = reportService.getOperDetail(paramMap);
            mop.put("info", resultData.getReturnData());
            mop.put("progress",status);
        }catch (Exception e) {
            logger.error("sceneEstate", "SceneEstateController", "toOperDetail", "", null, "", "业务节点详情查询 ", e);
        }
    	mop.addAttribute("status", status);	
    	mop.addAttribute("currDate", DateUtil.fmtDate(new Date(),"yyyy-MM-dd"));
    	mop.put("random_vs", new Random().nextLong());
        return "scene/estate/toSceneHouseInfoAddOne";
    }
    
    
    /**
     * 
     * 点击大定
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "toSceneHouseInfoAdd", method = RequestMethod.GET)
    public String toSceneHouseInfoAdd(HttpServletRequest request, ModelMap mop)
    {
    	//获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        try
        {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("reportNo",reqMap.get("reportId"));
            paramMap.put("progress",13504);
            paramMap.put("confirmStatus",13603);
            ResultData<?> resultData = reportService.getOperDetail(paramMap);
            mop.put("houseInfo", resultData.getReturnData());
            mop.put("progress",13504);
            ResultData<?> resultDataSwitch 	= null;
            if(resultData != null){
            	Map<String, Object> reportSearchResultDto = (Map<String, Object>) resultData.getReturnData();
            	if(reportSearchResultDto!=null && reportSearchResultDto.get("estateCityNo")!=null){
            		String cityNo = reportSearchResultDto.get("estateCityNo").toString();
            		 resultDataSwitch = commonService.getSwitchInfo(cityNo);
                     Map<String, Object> map = (Map<String, Object>) resultDataSwitch.getReturnData();
                     if(null != map.get("yearMonth")){
                    	 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                    	 Date date = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("yearMonth").toString()); 
                    	 String dateString = formatter.format(date);  
                    	 mop.put("yearMonth", dateString);
                     }
                     
            	}
            }
        }
        catch (Exception e)
        {
            logger.error("sceneEstate", "SceneEstateController", "toSceneHouseInfoAdd", "", null, "", "", e);
        }

        return "scene/estate/sceneHouseInfoAdd";
    }
    
    /**
     * 
     * 点击成销
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "toSceneHouseInfoEdit", method = RequestMethod.GET)
    public String toSceneHouseInfoEdit(HttpServletRequest request, ModelMap mop)
    {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        try
        {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("reportNo",reqMap.get("reportId"));
            paramMap.put("progress",13505);
            paramMap.put("confirmStatus",13603);
            ResultData<?> resultData = reportService.getOperDetail(paramMap);
            mop.put("houseInfo", resultData.getReturnData());
            mop.put("progress",13505);
            mop.put("reportNo",reqMap.get("reportId"));

            //关账信息
            Map<String,String> dto = (Map<String,String>)resultData.getReturnData();
            String cityNo = dto.get("cityNo");
            ResultData<?> resultDataSwitch = commonService.getSwitchInfo(cityNo);
            Map<String, Object> map = (Map<String, Object>) resultDataSwitch.getReturnData();
            mop.addAttribute("yearMonth", map.get("yearMonth"));
            List<Map<String,Object>> accountProjectList =  (List<Map<String,Object>>)reportService.getAccountProject(UserInfoHolder.get().getCityNo()).getReturnData();
            mop.put("accountProjectList", accountProjectList);
        }
        catch (Exception e)
        {
            logger.error("sceneEstate", "SceneEstateController", "toSceneHouseInfoEdit", "", null, "", "", e);
        }
        
        return "scene/estate/sceneHouseInfoEdit";
    }
    
    /**
               * 点击退筹、退定、退房弹出框
     * @param status
     * @param mop
     * @return
     */
    @RequestMapping(value = "rebackView/{status}", method = RequestMethod.GET)
    public String rebackView(ModelMap mop,@PathVariable("status") Integer status,HttpServletRequest request)
    {
    	 String cityNo = UserInfoHolder.get().getCityNo();
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultDataSwitch = new ResultData<HashMap<String,Object>>();
        try
        {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("reportNo",reqMap.get("reportId"));
            paramMap.put("progress",status);
            paramMap.put("confirmStatus",13601);
            ResultData<?> resultData = reportService.getOperDetail(paramMap);
            mop.put("houseInfo", resultData.getReturnData());
            
            //关账信息 20181112 zhenggang.Huang  退定:13504 退房:13505  begin
            /*
             * 1、做退定操作时，大定日期未关账，退定页面的退定日期默认为大定日期，可修改。如关账，不初始化默认值。
			 * 2、做退房操作时，成销日期未关账，退房页面的退房日期默认为成销日期，可修改。如关账，不初始化默认值。
             */
            if(resultData != null){
            	resultDataSwitch = commonService.getSwitchInfo(cityNo);
            	@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) resultDataSwitch.getReturnData();
            	@SuppressWarnings("unchecked")
				Map<String, Object> reportSearchResultDto = (Map<String, Object>) resultData.getReturnData();
            	if(reportSearchResultDto!=null && map!=null){
            		//关账日期
            		String yearMonth = (String) map.get("yearMonth") == null ? null:(String) map.get("yearMonth")+" 00:00:00";
            		String closeStatus = "";
            		//退定
            		if(status==13504 && reportSearchResultDto.get("roughDate")!=null) {
            			//大定日期
            			String roughDate = reportSearchResultDto.get("roughDate").toString();
            			closeStatus = DateUtils.contrastDate(yearMonth,roughDate,DateUtils.FORMAT_STR);
            		}
            		//退房
            		if(status==13505 && reportSearchResultDto.get("bizOperDate")!=null) {
            			//成销日期
            			String bizOperDate = reportSearchResultDto.get("bizOperDate").toString();
            			closeStatus = DateUtils.contrastDate(yearMonth,bizOperDate,DateUtils.FORMAT_STR);
            		}
            		//关账
            		if("1".equals(closeStatus)) {
            			mop.addAttribute("closeStatus","1");
            		}else {
            			mop.addAttribute("closeStatus","0");
            		}
            		
            		mop.addAttribute("yearMonth", map.get("yearMonth"));
            		mop.addAttribute("isShowCurrDate", map.get("isShowCurrDate"));
            	}
            }
            //end
        }
        catch (Exception e)
        {
            logger.error("sceneEstate", "SceneEstateController", "rebackView", "", null, "", "", e);
        }

    	mop.addAttribute("status", status);	
    	mop.addAttribute("currDate", DateUtil.fmtDate(new Date(),"yyyy-MM-dd"));	
        return "scene/estate/rebackView";
    }

    /**
     * 批量成销列表
     * @param mop
     * @param request
     * @return
     */
    @RequestMapping(value = "openBatchSaleList", method = RequestMethod.GET)
    public String openBatchSaleList(ModelMap mop,HttpServletRequest request){

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        //获取用户code
        String userCode=UserInfoHolder.get().getUserCode();


        reqMap.put("userCode",userCode);
        mop.addAllAttributes(reqMap);
        try {
//            ResultData<?> resultData=sceneEstateService.getBatchSaleList(reqMap);
            ResultData<?> resultData=batchSaleService.getBatchSaleList(reqMap);
            mop.addAttribute("batchSale",resultData.getReturnData());
            if(resultData != null){
                Map<String, Object> resultDataMap = (Map<String, Object>) resultData.getReturnData();
                if(resultDataMap!=null && resultDataMap.get("cityNo")!=null){
                    String cityNo = resultDataMap.get("cityNo").toString();
                    ResultData<?> resultDataSwitch = commonService.getSwitchInfo(cityNo);
                    Map<String, Object> mapSwitch = (Map<String, Object>) resultDataSwitch.getReturnData();
                    if(null != mapSwitch.get("yearMonth")){
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(mapSwitch.get("yearMonth").toString());
                        String dateString = formatter.format(date);
                        mop.addAttribute("yearMonth", dateString);
                    }
                }
            }
        }catch (Exception e){

        }
        return "scene/estate/batchSaleList";
    }

    @RequestMapping(value = "/addBatchSale",  method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> addBatchSale(HttpServletRequest request){
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userCode",UserInfoHolder.get().getUserCode());
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        ResultData<?> resultData = new ResultData<>();

        try {
//            resultData=sceneEstateService.addBatchSaleDetail(reqMap);
            resultData=batchSaleService.addBatchSaleDetail(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
            if (null != resultData.getReturnData())
            {
                rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
            }
        }catch (Exception e){
            rspMap.put(Constant.RETURN_CODE_KEY, "-1");
            rspMap.put(Constant.RETURN_MSG_KEY, "接口调用异常");
        }

        return getMapView(rspMap);
    }

    @RequestMapping(value = "/deleteBatchSaleDetailById",  method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> deleteBatchSaleDetailById(HttpServletRequest request){
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        ResultData<?> resultData = new ResultData<>();
        try {
//            resultData=sceneEstateService.deleteBatchSaleDetailById(reqMap);
            resultData=batchSaleService.deleteBatchSaleDetailById(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
            if (null != resultData.getReturnData())
            {
                rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
            }
        }catch (Exception e){
            rspMap.put(Constant.RETURN_CODE_KEY, "-1");
            rspMap.put(Constant.RETURN_MSG_KEY, "接口调用异常");
        }

        return getMapView(rspMap);
    }

    @RequestMapping(value = "/updateBatchSaleDetailAll",  method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?,?> updateBatchSaleDetailAll(HttpServletRequest request){
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        String str=reqMap.get("param").toString();

        List<Map<String, Object>> listMap = JSON.parseObject(str, new TypeReference<List<Map<String,Object>>>(){});

        Map<String,Object> queryParam =new HashMap<>();
        queryParam.put("listMap",listMap);

        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        ResultData<?> resultData = new ResultData<>();

        try {
//            resultData=sceneEstateService.updateBatchSaleDetailAll(queryParam);
            resultData=batchSaleService.updateBatchSaleDetailAll(queryParam);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
            if (null != resultData.getReturnData())
            {
                rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
            }
        }catch (Exception e){
            rspMap.put(Constant.RETURN_CODE_KEY,"-1");
            reqMap.put(Constant.RETURN_MSG_KEY,"接口调用异常");
        }
        return getMapView(rspMap);
    }


    @RequestMapping(value = "/countBatchSaleDetailAll",  method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?,?> countBatchSaleDetailAll(HttpServletRequest request){
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userCode",UserInfoHolder.get().getUserCode());

        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        ResultData<?> resultData = new ResultData<>();
        try {
//            resultData=sceneEstateService.countBatchSaleDetailAll(reqMap);
            resultData=batchSaleService.countBatchSaleDetailAll(reqMap);
        }catch (Exception e){
            rspMap.put(Constant.RETURN_CODE_KEY,"-1");
            reqMap.put(Constant.RETURN_MSG_KEY,"接口调用异常");
        }
        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        if (null != resultData.getReturnData())
        {
            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        }
        return getMapView(rspMap);
    }


    @RequestMapping(value = "/submitBatchSaleAll",  method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?,?> submitBatchSaleAll(HttpServletRequest request){
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        String str=reqMap.get("param").toString();

        List<Map<String, Object>> listMap = JSON.parseObject(str, new TypeReference<List<Map<String,Object>>>(){});

        Map<String,Object> queryParam =new HashMap<>();
        queryParam.put("userId",UserInfoHolder.getUserId());
        queryParam.put("userCode", UserInfoHolder.get().getUserCode());
        queryParam.put("userName", UserInfoHolder.get().getUserName());
        queryParam.put("listMap",listMap);
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        ResultData<?> resultData = new ResultData<>();

        try {
//            resultData=sceneEstateService.submitBatchSaleAll(queryParam);
            resultData =batchSaleService.submitBatchSaleAll(queryParam);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
            if (null != resultData.getReturnData())
            {
                rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
            }
        }catch (Exception e){
            rspMap.put(Constant.RETURN_CODE_KEY,"-1");
            rspMap.put(Constant.RETURN_MSG_KEY,"接口调用异常！");
        }
        return getMapView(rspMap);
    }

    @RequestMapping(value = "exportCheck", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> exportCheck(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> rspMap = new HashMap<String, Object>();
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userCode",UserInfoHolder.get().getUserCode());
        ResultData<?> reback;
        try {
            reback = batchSaleService.getBatchSaleList(reqMap);
            Map<String, Object> map = (Map<String, Object>) reback.getReturnData();
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) map.get("batchSaleDetails");
            if (contentlist == null || contentlist.isEmpty()) {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "没有数据，不能导出Excel！");
                return getOperateJSONView(rspMap);
            }
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导出失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        rspMap.put(Constant.RETURN_MSG_KEY, "导出成功");
        return getOperateJSONView(rspMap);
    }

    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userCode",UserInfoHolder.get().getUserCode());

        try {
            ResultData<?> reback = batchSaleService.getBatchSaleList(reqMap);
            reqMap.put("userId", UserInfoHolder.getUserId());
            reqMap.put("userName", UserInfoHolder.get().getUserName());
            Map<String, Object> map = (Map<String, Object>) reback.getReturnData();
            List<LinkedHashMap<String, Object>> contentList = (List<LinkedHashMap<String, Object>>) map.get("batchSaleDetails");
            String str = "批量成销模板_"+DateUtils.formatDate(new Date(),"yyyyMMdd")+".xlsx";
            downLoadExcel(request, response, contentList, reqMap, ReportConstant.BATCHSALE_CODE, str);
        } catch (Exception e) {
            logger.error("sceneEstate",
                    "SceneEstateController",
                    "export",
                    "input param: reqMap=" + reqMap.toString(), -
                            UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "导出异常",
                    e);
        }
    }

    /**
     * 导入
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "imput", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> imput(HttpServletRequest request, HttpServletResponse response) {
        // 返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        MultipartFile multFile = multiRequest.getFile("historyDataFile");

        Workbook wb;
        try {
            wb = new XSSFWorkbook(multFile.getInputStream());
            int  number= wb.getNumberOfSheets();
            if (number>1){
            	 rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                 rspMap.put(Constant.RETURN_MSG_KEY, "Excel文档格式错误,不允许多个sheet！");
                 return getSearchJSONView(rspMap);
            }
            Sheet sheet1 = wb.getSheetAt(0);

            //导入check
            Map<String, Object> rtnMap = checkImport(sheet1);
            if (null != rtnMap) {
                return getSearchJSONView(rtnMap);
            }

            List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
            for (Row r : sheet1) {
                Integer reault = getSheetCellValue(wb, r, mapList);
                if (reault == 1) {
                    continue;
                }else if(reault == 2){
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    rspMap.put(Constant.RETURN_MSG_KEY, "数据不完整，请调整后再导入！");
                    return getSearchJSONView(rspMap);
                }
            }
            //数据导入
            ResultData insertResult = insertBatchImport(mapList);
            if (!"200".equals(insertResult.getReturnCode())) {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "导入数据失败！");
                return getSearchJSONView(rspMap);
            }

            wb.close();

            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
            rspMap.put(Constant.RETURN_MSG_KEY, insertResult.getReturnMsg());
            rspMap.put(Constant.RETURN_DATA_KEY,insertResult.getReturnData());
        } catch (IOException e1) {
            logger.error("sceneEstate", "SceneEstateController", "imput", "", null, "", "", e1);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导入数据失败！");
        }

        return getSearchJSONView(rspMap);
    }

    /**
     * 导入Check
     *
     * @return
     */
    private Map<String, Object> checkImport(Sheet sheet1) {
        Map<String, Object> rspMap = new HashMap<String, Object>();
        if (sheet1.getLastRowNum() == 0) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导入文件数据格式有误！");
            return rspMap;
        }

        //密码验证
        if (isSheetModify((XSSFSheet) sheet1)) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导入数据模板不对，请重新下载模板数据！");
            return rspMap;
        }

        String userId = sheet1.getRow(0).getCell(0).getStringCellValue();

        //判断操作人的工号是否和模板的用户工号一致
        if (!userId.equals(UserInfoHolder.getUserId().toString())) {
            String userName = sheet1.getRow(0).getCell(3).getStringCellValue();
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导入模板为" + userName + "下载的，请使用自己下载的模板进行导入！");
            return rspMap;
        }
        return null;
    }

    //验证excel密码
    private boolean isSheetModify(XSSFSheet sheet) {
        boolean haveModify = true;
        try {
            String pwd = sheet.getRow(0).getCell(1).getStringCellValue();
            CTSheetProtection csheet = sheet.getCTWorksheet().getSheetProtection();
            if (csheet != null) {
                STUnsignedShortHex passwordST = csheet.xgetPassword();
                if (passwordST != null || !passwordST.getStringValue().equals(pwd)) {
                    haveModify = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return haveModify;
    }

    /**
     * 取得excel值
     *1,跳过列头2,调整列数据填写错误，请调整导表后重新导入
     * @param r excel行
     * @param
     * @return
     */
    private Integer getSheetCellValue(Workbook wb, Row r, List<Map<String, Object>> mapList) {

        //跳过列头
        if (r.getRowNum() < 2) {
            return 1;
        }

        Map<String, Object> map = new HashMap<>();

        //报备编号
        String reportId = getCellValue(wb, r.getCell(0));
        //楼室号
        String floor = getCellValue(wb, r.getCell(1));
        //税前
        String saleAcreage = getCellValue(wb, r.getCell(2));
        //税后
        String saleAmount = getCellValue(wb, r.getCell(3));

        String accountProject = getCellValue(wb, r.getCell(4));
        //税前
        String befYjsrTaxAmount = getCellValue(wb, r.getCell(5));
        //税后
        String aftYjsrTaxAmount = getCellValue(wb, r.getCell(6));
        //税前
        String befYjfyTaxAmount = getCellValue(wb, r.getCell(7));
        //税后
        String aftYjfyTaxAmount = getCellValue(wb, r.getCell(8));
        //税前
        String befYjdyTaxAmount = getCellValue(wb, r.getCell(9));
        //税后
        String aftYjdyTaxAmount = getCellValue(wb, r.getCell(10));
        //检查数据 应收收入等列有数据，调整列才允许填写数据
        if(StringUtil.isEmpty(accountProject)||StringUtil.isEmpty(befYjsrTaxAmount)||StringUtil.isEmpty(aftYjsrTaxAmount)||StringUtil.isEmpty(befYjfyTaxAmount)||StringUtil.isEmpty(aftYjfyTaxAmount)){
            return 2;
        }
        if(StringUtil.isEmpty(befYjdyTaxAmount)){
            if(!StringUtil.isEmpty(aftYjdyTaxAmount)){
                return 2;
            }
        }else{
            if(StringUtil.isEmpty(aftYjdyTaxAmount)){
                return 2;
            }
        }
        //只要填了值就添加
        if (StringUtil.isEmpty(accountProject)||StringUtil.isNotEmpty(befYjsrTaxAmount)&&StringUtil.isNotEmpty(aftYjsrTaxAmount)&&StringUtil.isNotEmpty(befYjfyTaxAmount)&&StringUtil.isNotEmpty(aftYjfyTaxAmount)) {
            //数据添加
            map.put("reportId", reportId);
            map.put("floor", floor);
            map.put("saleAcreage", saleAcreage);
            map.put("saleAmount", saleAmount);
            map.put("accountProject", accountProject);
            map.put("befYjsrTaxAmount", befYjsrTaxAmount);
            map.put("aftYjsrTaxAmount", aftYjsrTaxAmount);
            map.put("befYjfyTaxAmount", befYjfyTaxAmount);
            map.put("aftYjfyTaxAmount", aftYjfyTaxAmount);
            map.put("befYjdyTaxAmount", befYjdyTaxAmount);
            map.put("aftYjdyTaxAmount", aftYjdyTaxAmount);
            mapList.add(map);
        }
        return 0;
    }

    /**
     * 导入操作
     *
     * @param mapList
     * @return
     */
    private ResultData<List<BatchSaleDetail>> insertBatchImport(List<Map<String, Object>> mapList) {
        ResultData<List<BatchSaleDetail>> resultData = new ResultData<List<BatchSaleDetail>>();
        try {
            List<BatchSaleDetail> batchSaleDetails = new ArrayList<BatchSaleDetail>();
            //批量处理数据
            for (Map<String, Object> map : mapList) {
                String reportId = map.get("reportId").toString();
                String floor = map.get("floor").toString();
                String saleAcreage = map.get("saleAcreage").toString();
                String saleAmount = map.get("saleAmount").toString();
                String accountProject = map.get("accountProject").toString();
                String befYjsrTaxAmount = map.get("befYjsrTaxAmount").toString();
                String aftYjsrTaxAmount = map.get("aftYjsrTaxAmount").toString();
                String befYjfyTaxAmount = map.get("befYjfyTaxAmount").toString();
                String aftYjfyTaxAmount = map.get("aftYjfyTaxAmount").toString();
                String befYjdyTaxAmount = map.get("befYjdyTaxAmount").toString();
                String aftYjdyTaxAmount = map.get("aftYjdyTaxAmount").toString();

                BatchSaleDetail batchSaleDetail = new BatchSaleDetail();
                batchSaleDetail.setReportId(reportId);
                batchSaleDetail.setFloor(floor);
                batchSaleDetail.setSaleAcreage(new BigDecimal(saleAcreage));
                batchSaleDetail.setSaleAmount(new BigDecimal(saleAmount));
                batchSaleDetail.setAccountProject(accountProject);
                batchSaleDetail.setBefYjsrTaxAmount(new BigDecimal(befYjsrTaxAmount));
                batchSaleDetail.setAftYjsrTaxAmount(new BigDecimal(aftYjsrTaxAmount));
                batchSaleDetail.setBefYjfyTaxAmount(new BigDecimal(befYjfyTaxAmount));
                batchSaleDetail.setAftYjfyTaxAmount(new BigDecimal(aftYjfyTaxAmount));
                if(befYjdyTaxAmount.equals("")){
                    batchSaleDetail.setBefYjdyTaxAmount(null);
                }else{
                    batchSaleDetail.setBefYjdyTaxAmount(new BigDecimal(befYjdyTaxAmount));
                }
                if(aftYjdyTaxAmount.equals("")){
                    batchSaleDetail.setAftYjdyTaxAmount(null);
                }else{
                    batchSaleDetail.setAftYjdyTaxAmount(new BigDecimal(aftYjdyTaxAmount));
                }
                batchSaleDetails.add(batchSaleDetail);
            }
            resultData.setReturnData(batchSaleDetails);
            resultData.setReturnMsg("导入数据成功！");
            return resultData;
        } catch (Exception e) {
            resultData.setReturnCode("-1");
            return resultData;
        }
    }

    //excel类型判断
    private String getCellValue(Workbook wb, Cell cell) {
        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
        String rtnVal = "";

        DecimalFormat df = new DecimalFormat("0.00");
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            //判断是否为日期类型
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                //用于转化为日期格式
                Date d = cell.getDateCellValue();
                DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                rtnVal = formater.format(d);
            } else {
                // 用于格式化数字，只保留数字的整数部分
                rtnVal = df.format(cell.getNumericCellValue());
            }
        }
        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            rtnVal = cell.getStringCellValue();
        }
        if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            evaluator.evaluateFormulaCell(cell);
            rtnVal = df.format(cell.getNumericCellValue());
        }
        return rtnVal;
    }

    @RequestMapping(value = "checkAccountProject", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> checkAccountProject(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> rspMap = new HashMap<String, Object>();
        Map<String, Object> reqMap = bindParamToMap(request);
        String str = reqMap.get("accountProjectList").toString();
        List<Map<String, Object>> listMap = JSON.parseObject(str, new TypeReference<List<Map<String,Object>>>(){});
        Map<String,Object> queryParam =new HashMap<>();
        ResultData<?> reback;
        try {
            String cityNo = UserInfoHolder.get().getCityNo();
            queryParam.put("cityNo",cityNo);
            queryParam.put("accountProjectList",listMap);
            reback = batchSaleService.checkAccountProject(queryParam);
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "查询失败！");
            return getOperateJSONView(rspMap);
        }
        if(reback.getReturnMsg()!=null){
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
        }else{
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        }
        rspMap.put(Constant.RETURN_MSG_KEY, reback.getReturnMsg());
        return getOperateJSONView(rspMap);
    }

}
