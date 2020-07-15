package cn.com.eju.deal.houseLinkage.estate.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.dto.file.FileDto;
import cn.com.eju.deal.base.dto.linkage.CityDto;
import cn.com.eju.deal.base.file.service.FilesService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.linkage.service.LinkageCityService;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.base.util.HttpClientUtil;
import cn.com.eju.deal.base.util.OkHttpClientUtil;
import cn.com.eju.deal.cashbill.service.CashBillService;
import cn.com.eju.deal.common.service.CityService;
import cn.com.eju.deal.common.service.CommonService;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.dto.contract.ContractInfoDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstatePhotosDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateReleaseCityDto;
import cn.com.eju.deal.houseLinkage.estate.dto.Data;
import cn.com.eju.deal.houseLinkage.estate.dto.YFEstateData;
import cn.com.eju.deal.houseLinkage.estate.service.EstateReleaseCityService;
import cn.com.eju.deal.houseLinkage.estate.service.EstateService;
import cn.com.eju.deal.houseLinkage.report.service.ReportInsertService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**   
* Controller层
* @author qianwei
* @date 2016年4月29日 下午9:25:30
*/
@Controller
@RequestMapping(value = "estate")
public class EstateController extends BaseController
{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "estateService")
    private EstateService estateService;
    
    @Resource(name = "linkageCityService")
    private LinkageCityService linkageCityService;
    
    @Resource(name = "filesService")
    private FilesService filesService;
    
    @Resource(name = "reportInsertService")
    private ReportInsertService reportInsertService;
    
    @Resource(name = "commonService")
    private CommonService commonService;
    
    @Resource(name = "cityService")
    private CityService cityService;
    
    @Resource(name = "estateReleaseCityService")
    private EstateReleaseCityService estateReleaseCityService;
    
    @Resource
    private CashBillService cashBillService;
    /** 
    * 初始化
    * @param request
    * @param mop
    * @return
    */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap mop, HttpServletResponse response)
    {
        UserInfo userInfo = UserInfoHolder.get();
        // 部门列表
        ResultData<?> resultGroupList = new ResultData<>();
        try
        {
            resultGroupList = estateService.getGroupList(userInfo.getGroupId());
        }
        catch (Exception e)
        {
            logger.error("houseLinkage", "EstateController", "list", "", null, "", "store toupdate getGroupList failed", e);
        }
        mop.put("deptIdList", resultGroupList.getReturnData());
      //得到归属项目部 add by zhenggang.Huang 2019/04/19 begin
        List<?> rebacklist = null;
        try{
        	Map<String,Object> reqMap = new HashMap<>();
        	String cityNo = UserInfoHolder.get().getCityNo();
        	reqMap.put("cityNo", cityNo);
        	ResultData<?> resultData = estateService.getProjectDepartment(reqMap);
        	rebacklist = (List<?>) resultData.getReturnData();
        }catch(Exception e)
        {
        	 logger.error("houseLinkage.estate", "EstateController", "toAdd", "", null, "", "创建--初始化-归属项目部", e);
        }
        mop.put("rebacklist", rebacklist);
        //end
        //读取搜索条件 add by wangkanlin 2017/08/23
        Map<String, Object> map = bindParamToMap(request);
        if(map.containsKey("searchParam") && "1".equals(map.get("searchParam"))){
            Map<String,Object> searchParamMap = getRememberSearch(request, ComConstants.ESTATE_LIST_SEARCH);
            mop.put("searchParamMap", JSON.toJSON(searchParamMap));
        }else{
            clearSearch(request, response, ComConstants.ESTATE_LIST_SEARCH);
        }
        mop.put("list_search_page", ComConstants.ESTATE_LIST_SEARCH);
        
        return "houseLinkage/estate/estateList";
    }
    
    /** 
     *获取录入人列表
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "empList/{deptId}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> empList(@PathVariable("deptId") String deptId, HttpServletRequest request, ModelMap mop)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        // 部门列表
        ResultData<?> resultUserList = new ResultData<>();
        try
        {
            if (StringUtil.isNotEmpty(deptId))
            {
                resultUserList = estateService.getUserList(deptId);
            }
            rspMap.put(Constant.RETURN_DATA_KEY, resultUserList.getReturnData());
            rspMap.put(Constant.RETURN_CODE_KEY, resultUserList.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultUserList.getReturnMsg());
        }
        catch (Exception e)
        {
            logger.error("houseLinkage", "EstateController", "empList", "", null, "", "获取录入人列表", e);
        }
        return getSearchJSONView(rspMap);
    }
    
    /** 
     *获取日期类型列表
     * @param request
     * @param mop
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
            resultDateKbnList = estateService.getDateKbnList(dateTypeKbn);
            
            rspMap.put(Constant.RETURN_DATA_KEY, resultDateKbnList.getReturnData());
            rspMap.put(Constant.RETURN_CODE_KEY, resultDateKbnList.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultDateKbnList.getReturnMsg());
        }
        catch (Exception e)
        {
            logger.error("houseLinkage", "EstateController", "dateKbnList", "", null, "", "获取日期类型列表", e);
        }
        return getSearchJSONView(rspMap);
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
        
        //获取页面显示数据
        ResultData<?> reback;
        try
        {
        	//保存搜索数据 add by wangkanlin 2017-08-23
            if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.ESTATE_LIST_SEARCH, reqMap);
            }
            reback = estateService.index(reqMap, pageInfo);
            //页面数据
            List<?> contentlist = (List<?>)reback.getReturnData();

            //存放到mop中
            mop.addAttribute("userIdList", estateService.getUserIdList());
            mop.addAttribute("contentlist", contentlist);
            mop.addAttribute("userInfo", UserInfoHolder.get());
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.estate", "EstateController", "index", "", null, "", "", e);
        }
        return "houseLinkage/estate/estateListCtx";
    }
    
    /** 
    * 创建--初始化
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "c", method = RequestMethod.GET)
    public String toAdd(HttpServletRequest request,ModelMap mop)
    {

        Map<String, Object> reqParam = bindParamToMap(request);
        mop.addAllAttributes(reqParam);

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
                logger.error("houseLinkage.estate", "EstateController", "toAdd", "", null, "", "创建--初始化", e);
            }
            mop.put("districtList", resultDistrictList.getReturnData());
        }
        
        //销售状态 salesStatusList
        mop.put("salesStatusList", SystemParam.getCodeListByKey(DictionaryConstants.SALES_STATUS));
        //合作模式cooperationTypeList
        mop.put("cooperationTypeList", SystemParam.getCodeListByKey(DictionaryConstants.COOPERATION_TYPE));
        // 业务模式
        mop.put("businessModelTypeList", SystemParam.getCodeListByKey(DictionaryConstants.BUSINESS_MODEL_TYPE));

        //合作方 partnerList
        mop.put("partnerList", SystemParam.getCodeListByKey(DictionaryConstants.PARTNER));
        //合作方 partnerList
        // 部门列表
        ResultData<?> resultUserList = new ResultData<>();
        try
        {
            resultUserList = estateService.getSceneUserList(cityNo);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.estate", "EstateController", "toAdd", "", null, "", "创建--初始化-部门列表", e);
        }
        
        //得到归属项目部
        try{
        	Map<String,Object> reqMap = new HashMap<>();
        	reqMap.put("cityNo", cityNo);
        	ResultData<?> resultData = estateService.getProjectDepartment(reqMap);
        	List<?> rebacklist = (List<?>) resultData.getReturnData();
        	mop.put("rebacklist", rebacklist);
        }catch(Exception e)
        {
        	 logger.error("houseLinkage.estate", "EstateController", "toAdd", "", null, "", "创建--初始化-归属项目部", e);
        }
        
        mop.put("sceneUserList", resultUserList.getReturnData());
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
        //提前报备期 时
        mop.put("advanceReportHHList", getHours());
        //提前报备期 分
        mop.put("advanceReportMMList", getMinutes());

        try {
            ResultData<?> resultData = estateService.queryCountryList();
            List<?> countryList = (List<?>) resultData.getReturnData();
            mop.put("countryList", countryList);
        }catch (Exception e){
            logger.error("houseLinkage.estate", "EstateController", "toAdd", "", null, "", "创建--初始化-查询国家列表错误", e);
        }
        //是否大客户 typeId 226
        mop.put("bigCustomerStatus", SystemParam.getCodeListByKey(DictionaryConstants.ESTATE_BIGCUSTOMER_TYPE));
        //是否独家 typeId 227
        mop.put("particularList", SystemParam.getCodeListByKey(DictionaryConstants.ESTATE_PARTICULAR_TYPE));
        //是否直签 typeId 228
        mop.put("directSignList", SystemParam.getCodeListByKey(DictionaryConstants.ESTATE_DIRECTSIGN_TYPE));

        //是否手动填写项目名称
        Boolean addEstateManual = null;
        ResultData<?> citySetting = null;
        try {
            citySetting = commonService.getCitySettingByCityNo(cityNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (citySetting != null && citySetting.getReturnData() != null){
            Map<?, ?> csMap = (Map<?, ?>) citySetting.getReturnData();
            addEstateManual = Boolean.valueOf(csMap.get("addEstateManual").toString());
        }
        mop.put("addEstateManual",addEstateManual);

        return "houseLinkage/estate/estateAdd";
    }
    
    /**
     *  创建
     *  @param request
     * @throws Exception 
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> create(HttpServletRequest request, ModelMap modelMap, EstatePhotosDto estatePhotosDto)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        
        try
        {
            ResultData<?> resultData = estateService.create(reqMap,estatePhotosDto);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        }
        catch (Exception e)
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "楼盘新增失败！");
            
            logger.error("estate", "EstateController", "create", "", null, "", "楼盘新增失败！", e);
            
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
        //楼盘自增编号
        mop.addAttribute("id", id);
        ResultData<?> resultData = new ResultData<>();
        try
        {
            resultData = estateService.getEstateDetailById(id);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.estate", "EstateController", "toEdit", "", null, "", "修改--初始化", e);
        }
        if(resultData != null){
        	  Map<String, Object> map = (Map<String, Object>)resultData.getReturnData();
        	  String mgtKbnList = (String) map.get("mgtKbn");
        	  if(mgtKbnList !=null && mgtKbnList !=""){
        		  String[] mgtKbn = mgtKbnList.split(",");
        		  for (String str : mgtKbn) {
					if(str.contains("13405")){
						 mop.addAttribute("houseTypeFlag", "0");
					}else{
						 mop.addAttribute("houseTypeFlag", "1");
					}
				}
        	  }
        }

        //存放到mop中
        mop.addAttribute("estate", resultData.getReturnData());

        return "houseLinkage/estate/estateEdit";
    }
    
    /** 
    * @Title: toEditDetail 
    * @Description: 初始化楼盘基本信息和详情
    * @param id
    * @param mop
    * @return     
    */
    @RequestMapping(value = "/qd/{id}/", method = RequestMethod.GET)
    public String toEditDetail(@PathVariable("id") int id, ModelMap mop)
    {
        ResultData<?> resultData = new ResultData<>();
        try
        {
            resultData = estateService.getEstateDetailById(id);
        }
        catch (Exception e)
        {
            logger.warn("estate toEditDetail getEstateDetailById failed:" + e);
            
        }
        //区域 districtList
        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>)resultData.getReturnData();
        String cityNo = (String)map.get("cityNo");
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
                logger.error("houseLinkage.estate", "EstateController", "toEditDetail", "", null, "", "初始化楼盘基本信息和详情", e);
            }
            mop.put("districtList", resultDistrictList.getReturnData());
        }
        
        
        //得到归属项目部
        try{
          	Map<String,Object> reqMap = new HashMap<>();
          	reqMap.put("cityNo", cityNo);
          	ResultData<?> resultDatas = estateService.getProjectDepartment(reqMap);
          	List<?> rebacklist = (List<?>) resultDatas.getReturnData();
          	mop.put("rebacklist", rebacklist);
          }catch(Exception e)
          {
          	 logger.error("houseLinkage.estate", "EstateController", "toAdd", "", null, "", "创建--初始化-归属项目部", e);
          }
        
        //销售状态 salesStatusList
        mop.put("salesStatusList", SystemParam.getCodeListByKey(DictionaryConstants.SALES_STATUS));
        //合作方 partnerList
        mop.put("partnerList", SystemParam.getCodeListByKey(DictionaryConstants.PARTNER));
        //案场负责人 sceneDeptIdList 联动
        // 部门列表
        ResultData<?> resultUserList = new ResultData<>();
        try
        {
            resultUserList = estateService.getSceneUserList(cityNo);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.estate", "EstateController", "toEditDetail", "", null, "", "初始化楼盘基本信息和详情-部门列表", e);
        }
        mop.put("sceneUserList", resultUserList.getReturnData());
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
        //合作模式cooperationTypeList
        mop.put("cooperationTypeList", SystemParam.getCodeListByKey(DictionaryConstants.COOPERATION_TYPE));

        // 业务模式
        mop.put("businessModelTypeList", SystemParam.getCodeListByKey(DictionaryConstants.BUSINESS_MODEL_TYPE));

        //存放到mop中
        mop.addAttribute("estate", resultData.getReturnData());

        try {
            ResultData<?> resultData1 = estateService.queryCountryList();
            List<?> countryList = (List<?>) resultData1.getReturnData();
            mop.put("countryList", countryList);
        }catch (Exception e){
            logger.error("houseLinkage.estate", "EstateController", "toAdd", "", null, "", "创建--初始化-查询国家列表错误", e);
        }
        //是否大客户 typeId 226
        mop.put("bigCustomerStatus", SystemParam.getCodeListByKey(DictionaryConstants.ESTATE_BIGCUSTOMER_TYPE));
        //是否独家 typeId 227
        mop.put("particularList", SystemParam.getCodeListByKey(DictionaryConstants.ESTATE_PARTICULAR_TYPE));
        //是否直签 typeId 228
        mop.put("directSignList", SystemParam.getCodeListByKey(DictionaryConstants.ESTATE_DIRECTSIGN_TYPE));

        //是否手动填写项目名称
        Boolean addEstateManual = null;
        ResultData<?> citySetting = null;
        try {
            citySetting = commonService.getCitySettingByCityNo(cityNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (citySetting != null && citySetting.getReturnData() != null){
            Map<?, ?> csMap = (Map<?, ?>) citySetting.getReturnData();
            addEstateManual = Boolean.valueOf(csMap.get("addEstateManual").toString());
        }
        mop.put("addEstateManual",addEstateManual);
        return "houseLinkage/estate/estateEditDetail";
    }
    
    /** 
    * @Title: toEditRule 
    * @Description: 根据楼盘编号获取联动规则
    * @param estateId
    * @param mop
    * @return     
    */
    @RequestMapping(value = "/qr/{estateId}/", method = RequestMethod.GET)
    public String toEditRule(@PathVariable("estateId") String estateId, ModelMap mop)
    {
        ResultData<?> resultData = new ResultData<>();
        Map<String, Object> reqMap = new HashMap<>();
        try
        {
            reqMap.put("estateId", estateId);
            resultData = estateService.getEstateRuleByEstateId(reqMap);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.estate", "EstateController", "toEditRule", "", null, "", "根据楼盘编号获取联动规则", e);
        }
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
        //收入结算条件 commissionConditionList
        mop.put("commissionConditionList", SystemParam.getCodeListByKey(DictionaryConstants.COMMISSIONCD_KBN));
        //提前报备期 时
        mop.put("advanceReportHHList", getHours());
        //提前报备期 分
        mop.put("advanceReportMMList", getMinutes());
        //存放到mop中
        mop.addAttribute("estateRule", resultData.getReturnData());
        return "houseLinkage/estate/estateEditRule";
    }
    
    /** 
    * @Title: toEditType 
    * @Description: 根据楼盘编号获取在售户型
    * @param estateId
    * @param mop
    * @return     
    */
    @RequestMapping(value = "/qt/{estateId}/", method = RequestMethod.GET)
    public String toEditType(@PathVariable("estateId") String estateId, ModelMap mop)
    {
        ResultData<?> resultData = new ResultData<>();
        Map<String, Object> reqMap = new HashMap<>();
        try
        {
            reqMap.put("estateId", estateId);
            resultData = estateService.getEstateTypeByEstateId(reqMap);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.estate", "EstateController", "toEditType", "", null, "", "根据楼盘编号获取在售户型", e);
        }
        //存放到mop中
        mop.addAttribute("estateType", resultData.getReturnData());
        
        return "houseLinkage/estate/estateEditType";
    }
    
    /** 
    * @Title: toEditSupport 
    * @Description: 根据楼盘编号获取周边配套
    * @param estateId
    * @param mop
    * @return     
    */
    @RequestMapping(value = "/qs/{estateId}/{auditStatus}", method = RequestMethod.GET)
    public String toEditSupport(@PathVariable("estateId") String estateId,@PathVariable("auditStatus") String auditStatus, ModelMap mop)
    {
        ResultData<?> resultData = new ResultData<>();
        Map<String, Object> reqMap = new HashMap<>();
        try
        {
            reqMap.put("estateId", estateId);
            resultData = estateService.getEstateSupportByEstateId(reqMap);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.estate", "EstateController", "toEditSupport", "", null, "", "根据楼盘编号获取周边配套", e);
        }
        //存放到mop中
        mop.addAttribute("estateSupport", resultData.getReturnData());
        mop.put("auditStatus", auditStatus);
        return "houseLinkage/estate/estateEditSupport";
    }
    
    /** 
    * @Title: toEditPhoto 
    * @Description: 根据楼盘编号获取楼盘图片
    * @param estateId
    * @param mop
    * @return     
    */
    @RequestMapping(value = "/qp/{estateId}/", method = RequestMethod.GET)
    public String toEditPhoto(@PathVariable("estateId") String estateId, ModelMap mop)
    {
        ResultData<?> resultData = new ResultData<>();
        Map<String, Object> reqMap = new HashMap<>();
        //页面数据
        List<?> contentlist = new ArrayList<>();
        String estateCoverImg = "";//户型封面图片ID
        Integer designImgsCount = 0;//效果图数量
        Integer templateImgsCount = 0;//样板间数量
        Integer mapImgsCount = 0;//地理位置数量
        Integer districtImgsCount = 0;//区域规划数量
        Integer realImgsCount = 0;//楼盘实景图数量
        Map<String, Integer> imgList = new HashMap<String,Integer>();
        try
        {
            reqMap.put("estateId", estateId);
            resultData = estateService.getPhotoByEstateId(reqMap);
            contentlist = (List<?>)resultData.getReturnData();
            
             if(contentlist!=null &&contentlist.size()>0)
            	 {for (Object object : contentlist)
			            {
			                @SuppressWarnings("unchecked")
			                Map<String, Object> map = (Map<String, Object>)object;
			                String coverFlg = (String)map.get("coverFlg");
			                if (coverFlg.equals("Y"))
			                {
			                    estateCoverImg = (String)map.get("sellFileNo");
			                }
			            }
            	 }
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.estate", "EstateController", "toEditPhoto", "", null, "", "根据楼盘编号获取楼盘图片", e);
        }
        
        imgList.put("realImgsCount",realImgsCount);
        imgList.put("templateImgsCount",templateImgsCount);
        imgList.put("designImgsCount",designImgsCount);
        imgList.put("districtImgsCount",districtImgsCount);
        imgList.put("mapImgsCount",mapImgsCount);
        
        //存放到mop中
//        mop.addAttribute("uploadPhotoId1", estateDesignImgs);
//        mop.addAttribute("uploadPhotoId2", estateTemplateImgs);
//        mop.addAttribute("uploadPhotoId3", estateMapImgs);
//        mop.addAttribute("uploadPhotoId4", estateDistrictImgs);
//        mop.addAttribute("uploadPhotoId5", estateRealImgs);
        mop.addAttribute("coverImg", estateCoverImg);
        mop.addAttribute("imgList",imgList);
        mop.addAttribute("estatePhoto", resultData.getReturnData());
        return "houseLinkage/estate/estateEditPhoto";
    }
    
    /**
     *  修改 
     *  @param request
     * @throws Exception 
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResultData<?> update(HttpServletRequest request, @PathVariable("id") String id, EstatePhotosDto estatePhotosDto)
    {
        ResultData<?> result = new ResultData<>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.remove("_method");
        try
        {
            //更新
            result = estateService.update(reqMap,estatePhotosDto);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.estate", "EstateController", "update", "", null, "", " 修改 ", e);
            result.setFail("楼盘信息更新失败！");
        }
        return result;
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
            resultData = estateService.getById(id);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.estate", "EstateController", "show", "", null, "", "查看", e);
        }
        Map<String, Object> estateInfoDto = (Map<String, Object>) resultData.getReturnData();
        if(estateInfoDto!=null && estateInfoDto.get("estate")!=null){
        	 Map<String, Object> estateDto = (Map<String, Object>) estateInfoDto.get("estate");
        	 String estateId = (String) estateDto.get("estateId");
        	 String oldCityNo = (String) estateDto.get("cityNm");
        	 String releaseCityStr = oldCityNo+"、";
        	 try {
        		 Map<String, Object> rspMap = new HashMap<String, Object>();
        		 rspMap.put("estateId", estateId);
        		 ResultData<?> reback1 = estateReleaseCityService.getByEstateId(rspMap);
        		 List<EstateReleaseCityDto> releaseCity = (List<EstateReleaseCityDto>) reback1.getReturnData();
        		 Map<String, Object> map = new HashMap<String, Object>();
        		 // 非空判断
        		 String releaseCityflag = "0";
        		 if (null != releaseCity && !releaseCity.isEmpty()) {
        			 releaseCityflag ="1";
        			 for (int i = 0; i < releaseCity.size(); i++) {
        				 map = (Map) releaseCity.get(i);
        				 if(!oldCityNo.equals(map.get("cityName").toString())){
        					 releaseCityStr += map.get("cityName").toString()+"、";
        				 }
        			 }
        			 releaseCityStr = releaseCityStr.substring(0,releaseCityStr.length()-1);
        			 mop.put("releaseCityStr", releaseCityStr);
        		 }
        		 mop.put("releaseCityflag", releaseCityflag);
        	 } catch (Exception e1) {
        		 e1.printStackTrace();
        	 }
        }
        //存放到mop中
        mop.addAttribute("estateInfo", resultData.getReturnData());
        mop.addAttribute("type", "detail");
        return "houseLinkage/estate/estateDetail";
    }
    
    /** 
     * 其他收入查看
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/otherReport/{id}", method = RequestMethod.GET)
    public String otherReportDetail(@PathVariable("id") Integer id, ModelMap mop)
    {
    	//返回map
    	ResultData<?> resultData = new ResultData<ContractInfoDto>();
    	try
    	{
    		resultData = estateService.getById(id);
    	}
    	catch (Exception e)
    	{
    		logger.error("houseLinkage.estate", "EstateController", "show", "", null, "", "查看", e);
    	}
    	Map<String, Object> estateInfoDto = (Map<String, Object>) resultData.getReturnData();
    	if(estateInfoDto!=null && estateInfoDto.get("estate")!=null){
    		Map<String, Object> estateDto = (Map<String, Object>) estateInfoDto.get("estate");
    		String estateId = (String) estateDto.get("estateId");
    		String oldCityNo = (String) estateDto.get("cityNm");
    		String releaseCityStr = oldCityNo+"、";
    		try {
    			Map<String, Object> rspMap = new HashMap<String, Object>();
    			rspMap.put("estateId", estateId);
    			ResultData<?> reback1 = estateReleaseCityService.getByEstateId(rspMap);
    			List<EstateReleaseCityDto> releaseCity = (List<EstateReleaseCityDto>) reback1.getReturnData();
    			Map<String, Object> map = new HashMap<String, Object>();
    			// 非空判断
    			String releaseCityflag = "0";
    			if (null != releaseCity && !releaseCity.isEmpty()) {
    				releaseCityflag ="1";
    				for (int i = 0; i < releaseCity.size(); i++) {
    					map = (Map) releaseCity.get(i);
    					if(!oldCityNo.equals(map.get("cityName").toString())){
    						releaseCityStr += map.get("cityName").toString()+"、";
    					}
    				}
    				releaseCityStr = releaseCityStr.substring(0,releaseCityStr.length()-1);
    				mop.put("releaseCityStr", releaseCityStr);
    			}
    			mop.put("releaseCityflag", releaseCityflag);
    		} catch (Exception e1) {
    			e1.printStackTrace();
    		}
    	}
    	//存放到mop中
    	mop.addAttribute("estateInfo", resultData.getReturnData());
    	mop.addAttribute("type", "detail");
    	mop.addAttribute("otherReportType", "1");
    	return "houseLinkage/estate/estateDetail";
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
            logger.error("houseLinkage.estate", "EstateController", "destroy", "", null, "", "删除", e);
        }
        
        try
        {
            //删除
            estateService.delete(id, updateId);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.estate", "EstateController", "destroy", "", null, "", "删除", e);
        }
        
        //响应码
        int status = response.getStatus();
        
        rspMap.put(Constant.RETURN_CODE_KEY, status);
        
        return getOperateJSONView(rspMap);
        
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
//            rspMap = this.estateService.uploadFile(request, reqMap);
//        }
//        catch (Exception e)
//        {
//            logger.error("houseLinkage.estate", "EstateController", "uploadFile", "", null, "", "创建", e);
//        }
//        
//        return getOperateJSONView(rspMap);
//    }
    
    /** 
    * @Title: toAddEstateType 
    * @Description: 跳转添加在售户型页面
    * @param mop
    * @return     
    */
    @RequestMapping(value = "toct", method = RequestMethod.GET)
    public String toAddEstateType(String estateId, ModelMap mop)
    {
        mop.put("estateId", estateId);
        //朝向 directionKbnList
        mop.put("directionKbnList", SystemParam.getCodeListByKey(DictionaryConstants.DIRECTION_KBN));
        return "houseLinkage/estate/estateTypeAdd";
    }
    
    /** 
    * @Title: addEstateType 
    * @Description: 新增在售户型
    * @param request
    * @param modelMap
    * @return     
    */
    @RequestMapping(value = "ct", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> addEstateType(HttpServletRequest request, ModelMap modelMap,EstatePhotosDto estatePhotosDto)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        try
        {
            ResultData<?> resultData = estateService.createEstateType(reqMap,estatePhotosDto);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        }
        catch (Exception e)
        {
            logger.error("estate", "EstateController", "addEstateType", "", UserInfoHolder.getUserId(), "", "新增在售户型失败", e);
            
        }
        return getOperateJSONView(rspMap);
    }
    
    /** 
    * @Title: toEditEstateType 
    * @Description: 跳转编辑在售户型页面
    * @param typeId
    * @param mop
    * @return     
    */
    @RequestMapping(value = "tout", method = RequestMethod.GET)
    public String toEditEstateType(int typeId, ModelMap mop)
    {
        ResultData<?> resultData = new ResultData<>();
        try
        {
            resultData = estateService.getEstateTypeById(typeId);
            //朝向 directionKbnList
            mop.put("directionKbnList", SystemParam.getCodeListByKey(DictionaryConstants.DIRECTION_KBN));
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.estate", "EstateController", "toEditEstateType", "", null, "", "跳转编辑在售户型页面", e);
        }
        //存放到mop中
        mop.addAttribute("estateType", resultData.getReturnData());
        
        return "houseLinkage/estate/estateTypeEdit";
    }
    
    /** 
    * @Title: editEstateType 
    * @Description: 编辑保存户型 
    * @param request
    * @return
    */
    @RequestMapping(value = "/ut", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnView<?, ?> editEstateType(HttpServletRequest request,EstatePhotosDto estatePhotosDto)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        try
        {
            estateService.editEstateType(reqMap,estatePhotosDto);
        }
        catch (Exception e)
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "户型更新失败！");
            logger.error("houseLinkage.estate", "EstateController", "editEstateType", "", null, "", "户型更新失败！", e);
        }
        return getOperateJSONView(rspMap);
    }
    
    /** 
    * @Title: delEstateType 
    * @Description: 删除在售户型
    * @param typeId
    * @param response
    * @return     
    */
    @RequestMapping(value = "/dt/{typeId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnView<?, ?> delEstateType(@PathVariable int typeId, HttpServletResponse response, HttpServletRequest request)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try
        {
            UserInfo userInfo = UserInfoHolder.get();
            
            //获取当前操作人usreId
            int updateId = userInfo.getUserId();
            //删除
            estateService.delEstateType(typeId, updateId);
        }
        catch (Exception e)
        {
            logger.error("estate", "EstateController", "delEstateType", typeId + "", UserInfoHolder.getUserId(), "", "", e);
        }
        //响应码
        int status = response.getStatus();
        rspMap.put(Constant.RETURN_CODE_KEY, status);
        return getOperateJSONView(rspMap);
        
    }
    
    /** 
    * @Title: toAudit 
    * @Description: 到审核页面
    * @param id
    * @param mop
    * @return
    * @throws Exception     
    */
    @RequestMapping(value = "/toaudit/{id}", method = RequestMethod.GET)
    public String toAudit(@PathVariable("id") Integer id, ModelMap mop)
    {
        //返回map
        ResultData<?> resultData = new ResultData<ContractInfoDto>();
        try
        {
            //TODO 判断是否有权限进入审核页面
            resultData = estateService.getById(id);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.estate", "EstateController", "toAudit", "", null, "", "到审核页面！", e);
        }
        //存放到mop中
        mop.addAttribute("estateInfo", resultData.getReturnData());
        mop.addAttribute("type", "audit");
        return "houseLinkage/estate/estateDetail";
    }
    
    /** 
    * @Title: toAuditNo 
    * @Description: 弹出审核不通过页面
    * @param id
    * @param mop
    * @return     
    */
    @RequestMapping(value = "toauditno", method = RequestMethod.GET)
    public String toAuditNo(int id, ModelMap mop)
    {
        mop.put("id", id);
        return "houseLinkage/estate/estateAuditNo";
    }
    
    /** 
    * @Title: audit 
    * @Description: 审核
    * @param request
    * @param id
    * @return     
    */
    @RequestMapping(value = "/audit/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnView<?, ?> audit(HttpServletRequest request, @PathVariable("id") String id)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.remove("_method");
        try
        {
            //更新
            estateService.audit(reqMap);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.estate", "EstateController", "audit", "", null, "", "审核", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "审核失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }
    
    /** 
    * @Title: toRelease 
    * @Description: 弹出发布页面
    * @param id
    * @param mop
    * @return     
    */
    @RequestMapping(value = "torelease", method = RequestMethod.GET)
    public String toRelease(int id, ModelMap mop)
    {
        mop.put("id", id);
        return "houseLinkage/estate/estateRelease";
    }
    
    /** 
    * @Title: release 
    * @Description: 发布
    * @param request
    * @param id
    * @return     
    */
    @RequestMapping(value = "/release/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnView<?, ?> release(HttpServletRequest request, @PathVariable("id") String id)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.remove("_method");
        
        //发布check
//        try
//        {
//        	ResultData<?>   resultData = estateService.getById(Integer.valueOf(id));
//        	Map<String,Object> map = (Map<String, Object>) resultData.getReturnData();
//        	Map<String,Object> map1 = (Map<String, Object>) map.get("estate");
//        	Integer cooperationMode = (Integer) map1.get("cooperationMode");
//        	if(cooperationMode != null)
//        	{
//        		if(cooperationMode == 20402)
//            	{
//            		Date measureDate = (Date) map1.get("measureDate");
//            		if(measureDate == null)
//            		{
//            			 rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
//            	         rspMap.put(Constant.RETURN_MSG_KEY, "整合项目并且测算完成后，才可上架！");
//            	         return getOperateJSONView(rspMap);
//            		}
//            	}
//        	}
//        }
//        catch (Exception e)
//        {
//            logger.error("release", "EstateController", "show", "", null, "", "发布", e);
//        }
        
        
        try
        {
            //更新
            estateService.release(reqMap);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.estate", "EstateController", "release", "", null, "", "发布失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "发布失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }
    
    /** 
    * @Title: toDown 
    * @Description: 弹出下架页面
    * @param id
    * @param mop
    * @return     
    */
    @RequestMapping(value = "todown", method = RequestMethod.GET)
    public String toDown(int id, ModelMap mop)
    {
        mop.put("id", id);
        return "houseLinkage/estate/estateDown";
    }
    
    /** 
    * @Title: down 
    * @Description: 下架
    * @param request
    * @param id
    * @return     
    */
    @RequestMapping(value = "/down/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnView<?, ?> down(HttpServletRequest request, @PathVariable("id") String id)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.remove("_method");
        try
        {
            //更新
            estateService.down(reqMap);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.estate", "EstateController", "down", "", null, "", "下架失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "下架失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }
    
    @RequestMapping(value = "/backoff/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnView<?, ?> backoff(HttpServletRequest request, @PathVariable("id") int id)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        //        Map<String, Object> reqMap = bindParamToMap(request);
        //        reqMap.remove("_method");
        try
        {
            //更新
            estateService.backoff(id);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.estate", "EstateController", "backoff", "", null, "", "撤回失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "撤回失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }
    
    /** 
    * @Title: startProject 
    * @Description: 跟单
    * @param request
    * @param id
    * @return     
    */
    @RequestMapping(value = "/startProject/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnView<?, ?> startProject(HttpServletRequest request, @PathVariable("id") int id)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try
        {
            //更新
            estateService.startProject(id);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.estate", "EstateController", "start", "", null, "", "跟单失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "跟单失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }

    /** 
    * @Title: startCancel
    * @Description: 取消跟单
    * @param request
    * @param id
    * @return     
    */
    @RequestMapping(value = "/startCancel/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnView<?, ?> startCancel(HttpServletRequest request, @PathVariable("id") int id)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try
        {
            //更新
            estateService.startCancel(id);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.estate", "EstateController", "start", "", null, "", "取消跟单失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "取消跟单失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }

    /** 
    * @Title: endProject 
    * @Description: 结案
    * @param request
    * @param id
    * @return     
    */
    @RequestMapping(value = "/endProject/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnView<?, ?> endProject(HttpServletRequest request, @PathVariable("id") int id)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try
        {
            //更新
            estateService.endProject(id);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.estate", "EstateController", "end", "", null, "", "结案失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "结案失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }

    /** 
    * @Title: endCancel 
    * @Description: 取消结案
    * @param request
    * @param id
    * @return     
    */
    @RequestMapping(value = "/endCancel/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnView<?, ?> endCancel(HttpServletRequest request, @PathVariable("id") int id)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try
        {
            //更新
            estateService.endCancel(id);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.estate", "EstateController", "endCancel", "", null, "", "结案失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "取消结案失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }

    /** 
    * @Title: popupCoMode 
    * @Description: 修改合作模式
    * @param request
    * @param id
    * @return    
    */
    @RequestMapping(value = "/popupCoMode/{id}/{name}/{mode}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView popupCoMode(@PathVariable("id") String id, @PathVariable("name") String name, @PathVariable("mode") String mode, ModelMap mop)
    {
        //返回视图
        mop.put("id", id);
        mop.put("name", name);
        mop.put("mode", mode);
        ModelAndView mv = new ModelAndView( "houseLinkage/estate/estateCooperationModePopup");
        return mv;
    } 

    /** 
    * @Title: changeCoMode 
    * @Description: 修改合作模式
    * @param request
    * @param modelMap
    * @return    
    */
    @RequestMapping(value = "/changeCoMode", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> changeCoMode(HttpServletRequest request, ModelMap modelMap)
    {
    	//返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        try
        {
            estateService.changeCoMode(reqMap);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.estate", "EstateController", "changeCoMode", "", null, "", "修改合作模式失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "修改合作模式失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    } 
    /** 
     * @Title: popupReleaseCity 
     * @Description:变更发布城市页面
     * @param request
     * @param id
     * @return    
     */
    @RequestMapping(value = "/popupReleaseCity/{id}/{estateId}/{cityNo}/{cityNm}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView popupReleaseCity(@PathVariable("id") String id, @PathVariable("estateId") String estateId,
    		@PathVariable("cityNo") String cityNo, @PathVariable("cityNm") String cityNm, ModelMap mop){	
    	String flag ="0";
    	try {
    		Map<String, Object> rspMap = new HashMap<String, Object>();
    		rspMap.put("estateId", estateId);
			ResultData<?> reback1 = estateReleaseCityService.getByEstateId(rspMap);
			List<EstateReleaseCityDto> releaseCity = (List<EstateReleaseCityDto>) reback1.getReturnData();
            String oldReleaseCity = cityNm +",";
            Map<String, Object> map = new HashMap<String, Object>();
           // 非空判断
            List<String> oldReleaseCityNo = new ArrayList<>();
            if (null != releaseCity && !releaseCity.isEmpty()) {
            	flag ="1";
                for (int i = 0; i < releaseCity.size(); i++) {
                    map = (Map) releaseCity.get(i);
                    if(!cityNo.equals(map.get("cityNo").toString())){
                    	oldReleaseCity += map.get("cityName").toString()+",";
                    	oldReleaseCityNo.add(map.get("cityNo").toString());
                    }
                }
                oldReleaseCity = oldReleaseCity.substring(0,oldReleaseCity.length()-1);
                mop.put("oldReleaseCity", oldReleaseCity);
            }
            oldReleaseCityNo.add(cityNo);
            mop.put("oldReleaseCityNo", oldReleaseCityNo);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	try {
        	ResultData<?> reback = cityService.queryCitySettingsList();
        	List<?> citylist = (List<?>) reback.getReturnData();
        	mop.addAttribute("citylist", citylist);
        	mop.addAttribute("citylistLenth", citylist.size());
		} catch (Exception e) {
			logger.error("houseLinkage.estate", "EstateController", "popupReleaseCity", "", null, "", "查询项目发布城市列表失败！", e);
			e.printStackTrace();
		}
    	//返回视图 reqMap.put("userCreate", UserInfoHolder.get().getUserId());
    	mop.put("id", id);
    	mop.put("estateId", estateId);
    	mop.put("cityNo", cityNo);
    	mop.put("cityNm", cityNm);
    	mop.put("flag", flag);
    	ModelAndView mv = new ModelAndView( "houseLinkage/estate/estateReleaseChangeCity");
    	return mv;
    } 
    
    /** 
     * @Title: changeCoMode 
     * @Description: 修改发布城市变更
     * @param request
     * @param modelMap
     * @return    
     */
   @RequestMapping(value = "/releaseCity", method = RequestMethod.POST)
   @ResponseBody
    public ReturnView<?, ?> releaseCity(HttpServletRequest request, ModelMap modelMap)
    {
    	//返回map
    	Map<String, Object> rspMap = new HashMap<String, Object>();
    	//请求map
    	Map<String, Object> reqMap = bindParamToMap(request);
    	String oldCityNm = reqMap.get("cityNm").toString();
    	String newReleaseCity = oldCityNm + ",";
    	String[] cityNoArray = reqMap.get("select_newRelseaseCityNo").toString().split(",");
    	for (int i = 0; i < cityNoArray.length; i++) {
			String newCityNo = cityNoArray[i];
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cityNo", newCityNo);
				ResultData<?> reback = cityService.queryCityNameByCityNo(map);
				List<CityDto> list = (List<CityDto>) reback.getReturnData();
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2 = (Map) list.get(0);
				if(!oldCityNm.equals(map2.get("cityName").toString())){
					newReleaseCity += map2.get("cityName").toString()+",";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    	newReleaseCity = newReleaseCity.substring(0,newReleaseCity.length()-1);
    	reqMap.put("newReleaseCity", newReleaseCity);
    	try
    	{
    		estateReleaseCityService.releaseCity(reqMap);
    	}
    	catch (Exception e)
    	{
    		logger.error("houseLinkage.estate", "EstateController", "releaseCity", "", null, "", "修改变更发布城市失败！", e);
    		rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
    		rspMap.put(Constant.RETURN_MSG_KEY, "修改变更发布城市失败！");
    		return getOperateJSONView(rspMap);
    	}
    	rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
    	return getOperateJSONView(rspMap);
    }
    /** 
     * @Title: popupCoMode 
     * @Description: 修改销售状态
     */
    @RequestMapping(value = "/popupStatusMode/{id}/{name}/{auditStatus}/{salesStatus}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView popupStatusMode(@PathVariable("id") String id, @PathVariable("name") String name, 
    		@PathVariable("auditStatus") String auditStatus,@PathVariable("salesStatus") String salesStatus, ModelMap mop)
    {
    	//返回视图
    	mop.put("id", id);
    	mop.put("name", name);
    	mop.put("auditStatus", auditStatus);
    	mop.put("salesStatus", salesStatus);
    	ModelAndView mv = new ModelAndView( "houseLinkage/estate/estateSaleStatusChangeModePopup");
    	return mv;
    } 
    
    /** 
     * @Title: changeStatusMode 
     * @Description: 修改销售状态
     */
    @RequestMapping(value = "/changeStatusMode", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> changeStatusMode(HttpServletRequest request, ModelMap modelMap)
    {
    	//返回map
    	Map<String, Object> rspMap = new HashMap<String, Object>();
    	//请求map
    	Map<String, Object> reqMap = bindParamToMap(request);
    	try
    	{
    		estateService.changeStatusMode(reqMap);
    	}
    	catch (Exception e)
    	{
    		logger.error("houseLinkage.estate", "EstateController", "changeStatusMode", "", null, "", "修改销售状态失败！", e);
    		rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
    		rspMap.put(Constant.RETURN_MSG_KEY, "修改销售状态失败！");
    		return getOperateJSONView(rspMap);
    	}
    	rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
    	return getOperateJSONView(rspMap);
    } 
    /**********************************private function*******************************/
    /** 
    * @Title: getHours 
    * @Description: 小时数
    * @return     
    */
    private List<String> getHours()
    {
        List<String> hours = new ArrayList<>();
        for (int i = 0; i < 24; i++)
        {
            if (String.valueOf(i).length() < 2)
            {
                hours.add("0" + String.valueOf(i));
            }
            else
            {
                hours.add(String.valueOf(i));
            }
        }
        return hours;
    }
    
    /** 
    * @Title: getMinutes 
    * @Description: 分钟数
    * @return     
    */
    private List<String> getMinutes()
    {
        List<String> minutes = new ArrayList<>();
        for (int i = 0; i < 60; i++)
        {
            if (String.valueOf(i).length() < 2)
            {
                minutes.add("0" + String.valueOf(i));
            }
            else
            {
                minutes.add(String.valueOf(i));
            }
        }
        return minutes;
    }
    
    /**********************************private function*******************************/
    
    /** 
    * 对文件数据进行组装处理
    * @param fileNo
    * @throws Exception
    */
    private String handleFile(String fileNo)
        throws Exception
    {
        //TODO 1、先从关系表中取，如果取不到 ，则通过fileNo 去渠道系统主表 拿到渠道及fileCode，去相应文件系统取出文件地址；
        
        String fileId = null;
        
        if (StringUtil.isNotEmpty(fileNo))
        {
            
            //调用渠道系统，获取文件记录信息
            ResultData<?> reback = filesService.getByFileNo(fileNo);
            Map<?, ?> mapTemppp = (Map<?, ?>)reback.getReturnData();
            if (null != mapTemppp)
            {
                
                FileDto fileDto = MapToEntityConvertUtil.convert(mapTemppp, FileDto.class, "");
                
                //fileCode
                String fileCode = fileDto.getFileCode();
                
                fileId = fileCode + "|" + fileNo;
                
            }
        }
        
        return fileId;
    }
    
    /** 
     * @Title: toReport1 
     * @Description: 跳转报备页面     -> estateReportAdd.jsp
     * @param request
     * @param mop
     * @return     
     */
    @RequestMapping(value = "toReport1/{id}", method = RequestMethod.GET)
    public String toReport1(@PathVariable("id") Integer id, HttpServletRequest request, ModelMap mop)
    {
    	/** 调用restful接口 封装的返回信息 */
        ResultData<?> resultData 		= null;
        ResultData<?> resultDataSwitch 	= null;
        try
        {
            resultData = estateService.getById(id);
            /** 获取关账信息 城市没有null的 */
            Map<String, Object> estateInfoDto = (Map<String, Object>) resultData.getReturnData();
            if(estateInfoDto!=null && estateInfoDto.get("estate")!=null){
            	 Map<String, Object> estateDto = (Map<String, Object>) estateInfoDto.get("estate");
                 String cityNo = (String) estateDto.get("cityNo");
                 mop.put("oldCityNo", cityNo);
                 String estateId = estateDto.get("estateId").toString();
                 try {
            		 Map<String, Object> rspMap = new HashMap<String, Object>();
            		 rspMap.put("estateId", estateId);
            		 ResultData<?> reback1 = estateReleaseCityService.getByEstateId(rspMap);
            		 List<EstateReleaseCityDto> releaseCity = (List<EstateReleaseCityDto>) reback1.getReturnData();
            		 String releaseCityNo = "";
            		 Map<String, Object> map = new HashMap<String, Object>();
            		 // 非空判断
            		 String releaseCityNoflag = "0";
            		 if (null != releaseCity && !releaseCity.isEmpty()) {
            			 releaseCityNoflag ="1";
            			 for (int i = 0; i < releaseCity.size(); i++) {
            				 map = (Map) releaseCity.get(i);
            				 releaseCityNo += map.get("cityNo").toString()+",";
            			 }
            			 releaseCityNo = releaseCityNo.substring(0,releaseCityNo.length()-1);
            			 mop.put("releaseCityNo", releaseCityNo);
            		 }
            		 mop.put("releaseCityNoflag", releaseCityNoflag);
            	 } catch (Exception e1) {
            		 e1.printStackTrace();
            	 }
                 
                 
                 /** 获取关账信息 */
                 resultDataSwitch = commonService.getSwitchInfo(cityNo);
                 Map<String, Object> map = (Map<String, Object>) resultDataSwitch.getReturnData();
                 mop.addAttribute("yearMonth", map.get("yearMonth"));
            }else{
            	mop.addAttribute("yearMonth", "1970-01-01");
            }
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.estate", "EstateController", "toReport1", "", null, "", "跳转报备页面", e);
        }
        mop.addAttribute("estateInfo", resultData.getReturnData());
        return "houseLinkage/estate/estateReportAdd";
    }
    
    /** 
     * @Title: createReport 
     * @Description: 新增报备
     * @param request
     * @param modelMap
     * @return     
     */
     @RequestMapping(value = "report", method = RequestMethod.POST)
     @ResponseBody
     public ReturnView<?, ?> createReport(HttpServletRequest request, ModelMap modelMap)
     {
         Map<String, Object> reqMap = bindParamToMap(request);
         Map<String, Object> rspMap = new HashMap<String, Object>();
         try
         {
             UserInfo userInfo = (UserInfo)WebUtil.getValueFromSession(request, Constant.SESSION_KEY_USERINFO);
             // 新增报备处理
             ResultData<?> resultData = this.reportInsertService.createReport(reqMap, userInfo);
             
             rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
             rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
             rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
         }
         catch (Exception e)
         {
             rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
             logger.error("report", "ReportController", "create", "", 0, "", "", e);
         }
         return getOperateJSONView(rspMap);
     }
     
     /**
      * 【描述】: 选择op项目弹出层
      *
      * @author yinkun
      * @date: 2018年3月6日 上午9:54:03
      * @param request
      * @param mop
      * @return
      */
     @RequestMapping(value = "/selectFromOp", method = RequestMethod.GET)
     public String selectFromOp(HttpServletRequest request, ModelMap mop){
         Map<String, Object> reqMap = bindParamToMap(request);
         mop.addAllAttributes(reqMap);
         return "houseLinkage/estate/selectFromOpPop";
     }
     
     @RequestMapping(value = "/selectFromOp/q", method = RequestMethod.GET)
     public String selectFromOpCtx(HttpServletRequest request, ModelMap mop){
         //获取请求参数
         Map<String, Object> reqMap = bindParamToMap(request);
         List<Map<String,Object>> estateList = new ArrayList<>();
         /*
         try{
             //String url ="http://10.122.136.5:58080/op-newhouse/crm/estate/list";
             String url = SystemParam.getWebConfigValue("op-newhouse")+"op-newhouse/crm/estate/list";
             Map<String, String> params = new HashMap<>();
             PageInfo pageInfo = getPageInfo(request);
              
             params.put("page", pageInfo.getNextPage()+"");
             params.put("pageSize", pageInfo.getPageLimit()+"");
             params.put("name", reqMap.get("estateName").toString());
             params.put("addr", reqMap.get("addr").toString());
             params.put("abroad",reqMap.get("estatePosition").toString());
             
             String result = OkHttpClientUtil.post(url, params);
             
             if(!"error".equals(result)) {
                 JSONObject parse = (JSONObject) JSONObject.parse(result);
                 JSONArray jsonArray = JSONObject.parseArray(parse.getString("result"));
                 if(!jsonArray.isEmpty()) {
                     for(int i = 0,l = jsonArray.size();i<l;i++) {
                         Map<String,String> map = JsonUtil.parseToObject(jsonArray.get(i).toString(), Map.class);
                         estateList.add(map);
                     }
                 }
                 
                 Map<String,Integer> pageMap = JsonUtil.parseToObject(parse.getString("pagination"),Map.class);
                 pageInfo.setDataCount(pageMap.get("total"));
             }
         }
         catch (Exception e){
             logger.error("estate", "EstateController", "selectFromOpCtx", "", 0, "", "", e);
         }
         */
         
         
         try{
        	 
             Map<String, String> params = new HashMap<>();
             PageInfo pageInfo = getPageInfo(request);
              
             params.put("currentPage", pageInfo.getNextPage()+"");
             params.put("pageSize", pageInfo.getPageLimit()+"");
             params.put("keyWord", reqMap.get("estateName").toString());
             params.put("address", reqMap.get("addr").toString());
             params.put("international",reqMap.get("estatePosition").toString());
             
             String result = getyFInterfaceInfo("/GetYouFangProject", JSON.toJSONString(params), null, UserInfoHolder.getUserId());
             ResultData<?> resultData;
             if(StringUtil.isNotEmpty(result)) {
            	 YFEstateData yFEstateData = JSON.parseObject(result, YFEstateData.class);
            	 for (Data data : yFEstateData.getData()) {
            		 Map<String,Object> cityMap = new HashMap<>();
            		 String cityNo = data.getCityNo()==null?"-1": data.getCityNo();
            		 resultData = this.estateService.getCityByGovCityCode(cityNo);
            		 if("200".equals(resultData.getReturnCode()) && resultData.getReturnData()!=null){
            			 cityMap = (Map<String, Object>) resultData.getReturnData();
            			 if(cityMap==null){
            				 cityMap = new HashMap<>();
            			 }
            		 }
            		 
            		 Map<String,Object> yfMap = new HashMap<>();
            		 yfMap.put("estateId", data.getEstateId());	
            		 yfMap.put("estateNm", data.getEstateNm());	
            		 yfMap.put("cityNo", cityMap.get("CityNo"));	
            		 yfMap.put("cityNm", cityMap.get("CityName"));
            		 yfMap.put("cityRmsId", cityMap.get("GovCityCode"));
            		 yfMap.put("address", data.getAddress());		
            		 yfMap.put("openTime", data.getOpenTime());	
            		 yfMap.put("houseCnt", data.getHouseCnt());	
            		 yfMap.put("parkCnt", data.getParkCnt());	
            		 yfMap.put("mgtCompany", data.getMgtCompany());	
            		 yfMap.put("rateFAR", data.getRateFAR());		
            		 yfMap.put("rateGreen", data.getRateGreen());	
            		 yfMap.put("mgtPrice", data.getMgtPrice());	
            		 yfMap.put("houseTransferTime", data.getHouseTransferTime());	
            		 
            		 estateList.add(yfMap);
				}
                 
                pageInfo.setDataCount(Integer.parseInt(yFEstateData.getDataCount()));
             }
         }
         catch (Exception e){
             logger.error("estate", "EstateController", "selectFromOpCtx", "", 0, "", "", e);
         }
         
         
         mop.addAttribute("estateList", estateList);
         
         return "houseLinkage/estate/selectFromOpPopCtx";
     }
     
     @RequestMapping(value = "selectFromOpCheck", method = RequestMethod.GET)
     @ResponseBody
     public ReturnView<?, ?> selectFromOpCheck(HttpServletRequest request, ModelMap mop)
     {
         //返回map
         Map<String, Object> rspMap = new HashMap<String, Object>();
         
         //获取请求参数
         Map<String, Object> reqMap = bindParamToMap(request);
         if(reqMap.get("estId") == null){
             reqMap.put("estId",9999999);
         }
         
         ResultData<?> resultData;
         try{
             resultData = estateService.selectFromOpCheck(reqMap);
         }
         catch (Exception e){
             logger.error("estate", "EstateController", "selectFromOpCheck", "", UserInfoHolder.getUserId(), "", "", e);
             rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
             rspMap.put(Constant.RETURN_MSG_KEY, "验证op项目是否已关联异常");
             
             return getOperateJSONView(rspMap);
         }
         
         rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
         rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
         
         rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
         
         return getOperateJSONView(rspMap);
     }
     /**
      * --初始化
      * @param request
      * @param mop
      * @return
      */
     @RequestMapping(value = "projectLeaderChoose", method = RequestMethod.GET)
     public ModelAndView toProjectLeaderList(HttpServletRequest request, ModelMap mop)
     {
         bindParamToAttrbute(request);
         mop.put("achievementCityNo", UserInfoHolder.get().getCityNo());
         ModelAndView mv = new ModelAndView("houseLinkage/estate/estateProjectLeaderListPopup");
         return mv;
     }
     /**
      * 获取项目负责人列表
      * @param request
      * @param mop
      * @return
      */
     @RequestMapping(value = "projectLeaderChoose/getList", method = RequestMethod.GET)
     public ModelAndView getProjectLeaderList(HttpServletRequest request, ModelMap mop)
     {
         Map<String, Object> reqMap = bindParamToMap(request);
         PageInfo pageInfo = getPageInfo(request);
         //返回list
         ResultData<?> resultData = null;
         List<?> linkUserList = new ArrayList<>();
         try
         {
        	 reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
        	 resultData = estateService.getSceneUserList2(reqMap,pageInfo);
        	 if(resultData != null){
                 linkUserList = (List<?>) resultData.getReturnData();
             }
         }
         catch (Exception e)
         {
        	 logger.error("houseLinkage.estate", "EstateController", "getProjectLeaderList", "", null, "", "获取负责人列表失败", e);
         }
         //存放到mop中
         mop.addAttribute("sceneUserList", linkUserList);
         ModelAndView mv = new ModelAndView("houseLinkage/estate/estateProjectLeaderListCtxPopup");
         return mv;
     }
     /**
      * 获取大客户列表
      * @param request
      * @param mop
      * @return
      */
     @RequestMapping(value = "getBigCustomerList", method = RequestMethod.GET)
     @ResponseBody
     public ReturnView<?, ?> getBigCustomerList(ModelMap mop){
         ResultData<?> resultData = new ResultData<>();
         //返回map
         Map<String, Object> rspMap = new HashMap<String, Object>();
         try {
             resultData = this.estateService.getBigCustomerList();
         }catch (Exception e){
         	logger.error("houseLinkage.estate",
                     "EstateController",
                     "estateService.getBigCustomerList",
                     "",
                     UserInfoHolder.getUserId(),
                     "",
                     "获取大客户失败",
                     e);
         }
         rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
         rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
         if (null != resultData.getReturnData()) {
             rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
         }
         return getMapView(rspMap);
     }
     
     /**
      * 获取垫佣甲方列表
      * @param request
      * @param mop
      * @return
      */
     @RequestMapping(value = "getMattressNail", method = RequestMethod.GET)
     @ResponseBody
     public ReturnView<?, ?> getMattressNail(ModelMap mop){
    	 ResultData<?> resultData = new ResultData<>();
    	 //返回map
    	 Map<String, Object> rspMap = new HashMap<String, Object>();
    	 try {
    		 resultData = this.estateService.getMattressNail();
    	 }catch (Exception e){
    		 logger.error("houseLinkage.estate",
    				 "EstateController",
    				 "estateService.getMattressNail",
    				 "",
    				 UserInfoHolder.getUserId(),
    				 "",
    				 "获取垫佣甲方失败",
    				 e);
    	 }
    	 rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
    	 rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
    	 if (null != resultData.getReturnData()) {
    		 rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
    	 }
    	 return getMapView(rspMap);
     }

     @RequestMapping(value ="popupEdit",method = RequestMethod.GET)
     public ModelAndView toUpdate(Integer id, ModelMap mop, HttpServletRequest request) {
         mop.addAllAttributes(bindParamToMap(request));
         ResultData<?> resultData = new ResultData<>();

         try {
             resultData = estateService.getEstateDetailById(id);
         } catch (Exception e) {
             logger.error("houseLinkage.estate", "EstateController", "toUpdate", "", null, "", "项目信息变更--初始化", e);
         }

         try {
             Map<String, Object> map = (Map<String, Object>) resultData.getReturnData();
             String cityNo = (String) map.get("realityCityNo");
             //查询isService=1的城市
             ResultData<?> reback = cityService.queryCityListByIsService();
             List<?> citylist = (List<?>) reback.getReturnData();
             mop.addAttribute("citylist", citylist);

             //区域列表
             ResultData<?> resultDistrictList = new ResultData<>();
             resultDistrictList = linkageCityService.getDistrictList(cityNo);
             mop.addAttribute("districtList", resultDistrictList.getReturnData());
         } catch (Exception e) {
             logger.error("houseLinkage.estate", "EstateController", "toUpdate", "", null, "", "查询地址信息", e);
         }

         //存放到mop中
         mop.addAttribute("estate", resultData.getReturnData());
         ModelAndView mv = new ModelAndView("houseLinkage/estate/estateEditPopup");
         return mv;
     }

    @RequestMapping(value = "/updatePopupDetail", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> updatePopupDetail(HttpServletRequest request)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        try
        {
            estateService.updatePopupDetail(reqMap);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage.estate", "EstateController", "updatePopupDetail", "", null, "", "修改项目详情失败！", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "修改项目详情失败！");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }
    
    public String getyFInterfaceInfo(String func,String paramMap ,String typeId,int userId) {
  		Map<String, Object> returnMap = new HashMap<String, Object>();
  		String result = null;
  		
  		String returnDataStr = null;
          String url = SystemParam.getWebConfigValue("youfangReportUrl") +func;
          
          
          logger.info("调用有房接口start#####请求#url="+url);
          try {
          	returnDataStr = HttpClientUtil.httpPostYF(url, paramMap);
  			if(StringUtil.isNotEmpty(returnDataStr)){
  				returnMap = JSON.parseObject(returnDataStr, Map.class);
  				if(returnMap.containsKey("BFlag") && returnMap.containsKey("TData")){
  					Integer bFlag = (Integer) returnMap.get("BFlag");
  					Object tData = returnMap.get("TData");
  					if(10==bFlag){
  						result = JSON.toJSONString(tData);
  					}else{
  						
  					}
  				}
  			}
  		} catch (Exception e) {
  			logger.error("houseLinkage.estate","EstateController","getyFInterfaceInfo","userId="+ userId, userId,null,
  					 "调用有房接口:#####请求参数#url="+url+"#####返回信息"+returnDataStr,e);
  		}
    
        return result;
  	}
    /** 
     * @Title: toChooseAccountProject 
     * @Description:选择考核主体页面
     * @return    
     */
    @RequestMapping(value = "/toChooseAccountProject", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView toChooseAccountProject(HttpServletRequest request, ModelMap mop){	
    	ResultData<?> resultData = null;
    	Map<String, Object> reqMap = bindParamToMap(request);
    	String cityNo = "";
    	String id = "";
    	if(reqMap.containsKey("estateCityNo")) {
    		cityNo = reqMap.get("estateCityNo").toString();
    		mop.put("estateCityNo", cityNo);
    	}
    	ModelAndView mv = new ModelAndView( "houseLinkage/estate/estateAccountProjectMapping");
    	return mv;
    } 
}
