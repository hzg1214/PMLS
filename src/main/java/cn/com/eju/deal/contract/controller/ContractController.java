package cn.com.eju.deal.contract.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.helper.SeqNoHelper;
import cn.com.eju.deal.base.linkage.service.LinkageCityService;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.base.util.CRMAuthUtil;
import cn.com.eju.deal.common.service.CityService;
import cn.com.eju.deal.common.service.FileRecordMainService;
import cn.com.eju.deal.common.service.ProvinceService;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.company.service.CompanyService;
import cn.com.eju.deal.contacts.service.ContactsService;
import cn.com.eju.deal.contract.service.CntrctService;
import cn.com.eju.deal.contract.service.ContractChangeService;
import cn.com.eju.deal.contract.service.ContractService;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.contacts.ContactsDto;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.contract.ContractFileDto;
import cn.com.eju.deal.dto.contract.ContractInfoDto;
import cn.com.eju.deal.dto.oa.OaOperatorDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.dto.store.StoreMaintainerDto;
import cn.com.eju.deal.oa.service.OaOperatorService;
import cn.com.eju.deal.store.service.StoreMaintainerService;
import cn.com.eju.deal.store.service.StoreService;

/**   
* Controller层
* @author qianwei
* @date 2016年3月23日 下午9:25:30
*/
@Controller
@RequestMapping(value = "contract")
public class ContractController extends BaseController
{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "contractService")
    private ContractService contractService;
    
    @Resource(name = "storeService")
    private StoreService storeService;
    
    @Resource(name = "companyService")
    private CompanyService companyService;
    
    @Resource(name = "linkageCityService")
    private LinkageCityService linkageCityService;
    
    @Resource(name = "oaOperatorService")
    private OaOperatorService oaOperatorService;
    
    @Resource(name = "cntrctService")
    private CntrctService cntrctService;
    
    @Resource(name = "stopContractService")
	private ContractChangeService stopContractService;
    //ADD By cning 2017/04/07 start
    @Resource(name = "contactsService")
   	private ContactsService contactsService;
    
    @Resource(name = "storeMaintainerService")
    private StoreMaintainerService storeMaintainerService;
    //ADD By cning 2017/04/07 end
    
    @Resource(name = "cityService")
    private CityService cityService;
    
    @Resource(name = "fileService")
    private FileRecordMainService fileService;
    
    @Resource
    private ProvinceService provinceService;
    
    /** 
    * 初始化
    * @param request
    * @param model
    * @return
    */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap mop, HttpServletResponse response)
    {
        //保存搜索条件 add by haidan 2017/08/11
        Map<String, Object> map = bindParamToMap(request);
        if(map.containsKey("searchParam") && "1".equals(map.get("searchParam"))){
            Map<String,Object> searchParamMap = getRememberSearch(request, ComConstants.CONTRACT_LIST_SEARCH);
            mop.put("searchParamMap", JSON.toJSON(searchParamMap));
        }else{
            clearSearch(request, response, ComConstants.CONTRACT_LIST_SEARCH);
        }
        mop.put("list_search_page", ComConstants.CONTRACT_LIST_SEARCH);
        return "contract/contractList";
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
        
        //需要数据权限
        reqMap.put(Constant.DATA_AUTH_KEY, true);
        reqMap.put("userCode", UserInfoHolder.get().getUserCode());
        PageInfo pageInfo = getPageInfo(request);
        
        //获取页面显示数据
        List<?> contentlist = new ArrayList<>();
        try
        {
            //保存搜索条件 add by haidan 2017/08/11
            if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.CONTRACT_LIST_SEARCH, reqMap);
            }
            //------------------调用权限接口 start------------------//
            int rtnAuth = CRMAuthUtil.getBtnAuth("/contract", "CONTRACT_VIEWALL", UserInfoHolder.get());
            reqMap.put("auth", rtnAuth);
            reqMap.put("userCreate", UserInfoHolder.get().getUserId());
            reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
            ResultData<?> reback = contractService.index(reqMap, pageInfo);
            
            //页面数据
            contentlist = (List<?>)reback.getReturnData();
        }
        catch (Exception e)
        {
            logger.error("contract", "ContractController", "index", "", UserInfoHolder.getUserId(), "", "合同查询失败", e);
        }
        
        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        
        //查询是否是经办人
        ResultData<?> backResult = new ResultData<>();
        try
        {
            backResult = oaOperatorService.getByUserCode(UserInfoHolder.get().getUserCode());
        }
        catch (Exception e)
        {
            logger.error("contract",
                "ContractController",
                "index",
                "",
                UserInfoHolder.getUserId(),
                "",
                "合同查询, 查询经办人失败",
                e);
        }
        
        //存放到mop中
        mop.addAttribute("oaOperator", backResult.getReturnData());
        
        //获取当前用户及其下属用户Id集合, 用于页面权限过滤
        List<Integer> idsList = new ArrayList<>();
        try
        {
            idsList = contractService.getUserIdList();
        }
        catch (Exception e)
        {
            logger.error("contract",
                "ContractController",
                "index",
                "",
                UserInfoHolder.getUserId(),
                "",
                "合同查询, 查询userList失败",
                e);
        }
        
        //存放到mop中
        mop.addAttribute("userIdList", idsList);
        
        return "contract/contractListCtx";
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
        
        ResultData<?> resultData;
        try
        {
            resultData = contractService.checkStoreLock(reqMap);
        }
        catch (Exception e)
        {
            logger.error("contract", "ContractController", "check", "", UserInfoHolder.getUserId(), "", "", e);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            
            rspMap.put(Constant.RETURN_MSG_KEY, "验证门店是否被锁失败");
            
            return getOperateJSONView(rspMap);
        }
        
        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        
        rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        
        return getOperateJSONView(rspMap);
    }
    
    /** 
    * 创建--初始化
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "c/{centerId}/{centerName}", method = RequestMethod.GET)
    public String toAdd(@PathVariable("centerId") int centerId,@PathVariable("centerName") String centerName,ModelMap mop)
    {
        //返回map
        ResultData<?> resultData = new ResultData<ContractDto>();
        UserInfo userInfo = UserInfoHolder.get();
        try {
        	//城市列表
            ResultData<?> resultCityList = new ResultData<>();
            resultCityList = cityService.queryCityListByIsService();
            
            mop.put("citylist", resultCityList.getReturnData());
		} catch (Exception e1) {
			logger.warn("store toupdate getDistrictList failed");
		}
        
      //开户省市
        try {
            ResultData<?> provinceData = provinceService.queryProvinceList();
            mop.addAttribute("provinceList", provinceData.getReturnData());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try
        {
            resultData = contractService.getProductInfo();
        }
        catch (Exception e)
        {
            logger.error("contract", "ContractController", "toAdd", "", UserInfoHolder.getUserId(), "", "合同创建初始化失败", e);
        }
        
        //合同类型
        mop.put("contractTypeList", SystemParam.getCodeListByKey(DictionaryConstants.CONTRACT_TYPE + ""));
        //OA审批流程类别
        mop.put("oaApproveTypeList", SystemParam.getCodeListByKey(DictionaryConstants.OA_APPROVE_TYPE + ""));
        //授牌类型
        mop.put("shoupaiTypeList", SystemParam.getCodeListByKey(DictionaryConstants.SHOUPAI_TYPE + ""));
        //合作模式
        mop.put("coopTypeList", SystemParam.getCodeListByKey(DictionaryConstants.COOP_TYPE + ""));
        
        //Add By tong 2017/04/07 Start
        //合同协议类型
        mop.put("agreementTypeList", SystemParam.getCodeListByKey(DictionaryConstants.AGREEMENT_TYPE + ""));
        //Add By tong 2017/04/07 End
        
        // Add By GuoPengFei 合规性 start
        //是否乙类转甲类
        mop.put("ContractTypeB2AList", SystemParam.getCodeListByKey(DictionaryConstants.Contract_TypeB2A + ""));
        // Add By GuoPengFei 合规性 end
        
        mop.addAttribute("contractInfo", resultData.getReturnData());
        mop.addAttribute("userId", userInfo.getUserId());
        mop.addAttribute("userName", userInfo.getUserName());
        mop.addAttribute("centerId", centerId);
        mop.addAttribute("centerName", centerName);
        return "contract/contractAdd";
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
        
        //获取页面字段值，转为DTO,创建合同
        try
        {
            setDto(reqMap, contractInfoDto, "create", "");
        }
        catch (Exception e1)
        {
            logger.error("contract", "ContractController", "create", "", UserInfoHolder.getUserId(), "", "", e1);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            
            rspMap.put(Constant.RETURN_MSG_KEY, "合同创建 设置参数 失败");
            
            return getOperateJSONView(rspMap);
        }
        
        try
        {
            //创建业绩归属人信息
            ResultData<?> resultData = contractService.create(contractInfoDto);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        }
        catch (Exception e)
        {
            logger.error("contract", "ContractController", "create", "", UserInfoHolder.getUserId(), "", "创建业绩归属人信息", e);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            
            rspMap.put(Constant.RETURN_MSG_KEY, "合同创建失败");
            
            return getOperateJSONView(rspMap);
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
    @RequestMapping(value = "/u/{id}", method = RequestMethod.GET)
    public String toEdit(@PathVariable("id") int id, ModelMap mop)
    {
        Map<String, Object> map = new HashMap<>();
        //返回map
        ResultData<?> resultData = new ResultData<ContractDto>();
        
        try
        {
            resultData = contractService.getById(id);
        }
        catch (Exception e)
        {
            logger.error("contract", "ContractController", "toEdit", "", UserInfoHolder.getUserId(), "", "修改初始化失败", e);
        }
        mop.put("contractInfo", resultData.getReturnData());
        //OA审批流程类别
        mop.put("oaApproveTypeList", SystemParam.getCodeListByKey(DictionaryConstants.OA_APPROVE_TYPE + ""));
        //授牌类型 hzg  2019/05/29
        mop.put("shoupaiTypeList", SystemParam.getCodeListByKey(DictionaryConstants.SHOUPAI_TYPE + ""));
        map = (Map<String, Object>)resultData.getReturnData();
        
        Map<String, Object> mapContract = new HashMap<>();
        
        mapContract = (Map<String, Object>)map.get("contract");
        
        UserInfo userInfo = UserInfoHolder.get();
      
      //开户省市
        try {
            ResultData<?> provinceData = provinceService.queryProvinceList();
            mop.addAttribute("provinceList", provinceData.getReturnData());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
        	//城市列表
            ResultData<?> resultCityList = new ResultData<>();
            resultCityList = cityService.queryCityListByIsService();            
            mop.put("citylist", resultCityList.getReturnData());
            
            //区域列表
            ResultData<?> resultDistrictList = new ResultData<>();
        	String cityNo= mapContract.get("cityNo").toString();
            resultDistrictList = linkageCityService.getDistrictList(cityNo);
            
            mop.put("districtList", resultDistrictList.getReturnData());
		} catch (Exception e1) {
			logger.warn("store toupdate getDistrictList failed");
		}
        
        //        if (null != (String)mapContract.get("districtNo")
        //            && StringUtil.isNotEmpty((String)mapContract.get("districtNo")))
        //        {
        //            //板块列表
        //            ResultData<?> resultAreaList = new ResultData<>();
        //            try
        //            {
        //                resultAreaList = linkageCityService.getAreaList((String)mapContract.get("districtNo"));
        //            }
        //            catch (Exception e)
        //            {
        //                logger.warn("store toupdate getAreaList failed");
        //            }
        //            mop.put("areaList", resultAreaList.getReturnData());
        //        }
        //        if (null != (String)mapContract.get("partyBdistrictNo")
        //            && StringUtil.isNotEmpty((String)mapContract.get("partyBdistrictNo")))
        //        {
        //            //板块列表
        //            ResultData<?> resultAreaList = new ResultData<>();
        //            try
        //            {
        //                resultAreaList = linkageCityService.getAreaList((String)mapContract.get("partyBdistrictNo"));
        //            }
        //            catch (Exception e)
        //            {
        //                logger.warn("store toupdate getAreaList failed");
        //            }
        //            mop.put("partyBareaList", resultAreaList.getReturnData());
        //        }
        //        if (null != (String)mapContract.get("actualOperationDistrictNo")
        //            && StringUtil.isNotEmpty((String)mapContract.get("actualOperationDistrictNo")))
        //        {
        //            //板块列表
        //            ResultData<?> resultAreaList = new ResultData<>();
        //            try
        //            {
        //                resultAreaList = linkageCityService.getAreaList((String)mapContract.get("actualOperationDistrictNo"));
        //            }
        //            catch (Exception e)
        //            {
        //                logger.warn("store toupdate getAreaList failed");
        //            }
        //            mop.put("actualOperationAreaList", resultAreaList.getReturnData());
        //        }
        
        //合同类型
        //        mop.put("contractTypeList", SystemParam.getCodeListByKey(DictionaryConstants.CONTRACT_TYPE + ""));
        //        List<CommonCodeDto> codeList = SystemParam.getCodeListByKey(DictionaryConstants.CONTRACT_TYPE + "");
        //        for (CommonCodeDto codeDto : codeList) {
        //            if (codeDto.getDicCode() == contractInfo.getContract().getContractType()) {
        //                mop.put("contractTypeValue", codeDto.getDicValue());
        //                mop.put("contractTypeCode", codeDto.getDicCode());
        //            }
        //        }
        //合作模式
        mop.put("coopTypeList", SystemParam.getCodeListByKey(DictionaryConstants.COOP_TYPE + ""));
        //Add By tong 2017/04/07 Start 
        //合作模式
        mop.put("contractTypeList", SystemParam.getCodeListByKey(DictionaryConstants.CONTRACT_TYPE + ""));
        
        //合同协议类型
        mop.put("agreementTypeList", SystemParam.getCodeListByKey(DictionaryConstants.AGREEMENT_TYPE + ""));
        //Add By tong 2017/04/07 End
        //是否乙类转甲类
        mop.put("ContractTypeB2AList", SystemParam.getCodeListByKey(DictionaryConstants.Contract_TypeB2A + ""));
        
        return "contract/contractEdit";
    }
    
 // Add 2017/04/06 cning 新建合同续签画面 start----------->
    /** 
     * @Title: toRenewAdd 
     * @Description: 新建合同续签画面初始化
     * @param r
     * @param id 原合同ID
     * @param storeId 门店ID
     * @param mop
     * @return
     * @     
     */     
    @RequestMapping(value = "/r/{id}/{storeId}/{centerId}/{centerName}", method = RequestMethod.GET)
    public String toRenewAdd(@PathVariable("id") int id, @PathVariable("storeId") int storeId,@PathVariable("centerId") int centerId,@PathVariable("centerName") String centerName, ModelMap mop)
    {
        Map<String, Object> map = new HashMap<>();
        //返回map
        ResultData<?> resultData = new ResultData<ContractInfoDto>();

        ResultData<?> resultDateLifeEnd = new ResultData<ContractInfoDto>();
        try
        {
            resultData = contractService.getById(id, storeId);
            resultDateLifeEnd = contractService.selectDateLifeEnd(storeId);

        }
        catch (Exception e)
        {
            logger.error("contract", "ContractController", "toEdit", "", UserInfoHolder.getUserId(), "", "修改初始化失败", e);
        }
        Map<String,Object> contractMap = (Map<String, Object>) resultData.getReturnData();
        try {
            ResultData<?> data = companyService.getBriefById((Integer.valueOf(((Map<String,Object>)contractMap.get("contract")).get("companyId").toString())));
            if(data != null && data.getReturnCode().equals("200")) {
                mop.put("company", data.getReturnData());
            }
        } catch (Exception e) {
            logger.error("contract", "ContractController", "toEdit", "", UserInfoHolder.getUserId(), "", "获取公司信息失败", e);
        }
        mop.put("contractDateLifeEnd", resultDateLifeEnd.getReturnData());
        mop.put("contractInfo", resultData.getReturnData());
        //OA审批流程类别
        mop.put("oaApproveTypeList", SystemParam.getCodeListByKey(DictionaryConstants.OA_APPROVE_TYPE + ""));
      //授牌类型
        mop.put("shoupaiTypeList", SystemParam.getCodeListByKey(DictionaryConstants.SHOUPAI_TYPE + ""));
        map = (Map<String, Object>)resultData.getReturnData();
        
        Map<String, Object> mapContract = new HashMap<>();
        
        mapContract = (Map<String, Object>)map.get("contract");
        
        UserInfo userInfo = UserInfoHolder.get();
        
        try {
        	//城市列表
            ResultData<?> resultCityList = new ResultData<>();
            resultCityList = cityService.queryCityListByIsService();            
            mop.put("citylist", resultCityList.getReturnData());
            
            //区域列表
            ResultData<?> resultDistrictList = new ResultData<>();
        	String cityNo= mapContract.get("cityNo").toString();
            resultDistrictList = linkageCityService.getDistrictList(cityNo);
            
            mop.put("districtList", resultDistrictList.getReturnData());
		} catch (Exception e1) {
			logger.warn("store toupdate getDistrictList failed");
		}
       
        //合作模式
        mop.put("coopTypeList", SystemParam.getCodeListByKey(DictionaryConstants.COOP_TYPE + ""));
        
        //Add By tong 2017/04/07 Start
        //合作模式
        mop.put("contractTypeList", SystemParam.getCodeListByKey(DictionaryConstants.CONTRACT_TYPE + ""));
        
        //合同协议类型
        mop.put("agreementTypeList", SystemParam.getCodeListByKey(DictionaryConstants.AGREEMENT_TYPE + ""));
        
        mop.put("storeId", storeId);
        mop.put("centerId", centerId);
        mop.put("centerName", centerName);
        //Add By tong 2017/04/07 End
        return "contract/contractRenewAdd";
    }
    // Add 2017/04/06 cning End <-----------
    
    /**
     *  修改 
     *  @param request
     * @throws Exception 
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnView<?, ?> update(HttpServletRequest request,
        @RequestParam(value = "isform", required = false) String isform, @PathVariable("id") String id)
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
        }
        catch (Exception e1)
        {
            logger.error("contract", "ContractController", "update", "", UserInfoHolder.getUserId(), "", "", e1);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            
            rspMap.put(Constant.RETURN_MSG_KEY, "合同修改 设置参数 失败");
            
            return getOperateJSONView(rspMap);
        }
        
        try
        {
            //更新
        	ResultData<?> resultData = contractService.update(contractInfoDto);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
            
        }
        catch (Exception e)
        {
            
            logger.error("contract", "ContractController", "update", "", UserInfoHolder.getUserId(), "", "", e);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            
            rspMap.put(Constant.RETURN_MSG_KEY, "合同修改 操作失败");
            
            return getOperateJSONView(rspMap);
        }
        
        
        
        return getOperateJSONView(rspMap);
    }
    
    /** 
    * 查看
    * @param id
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/{id}/{contractStatus}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Integer id, @PathVariable("contractStatus") Integer contractStatus, ModelMap mop)
    {
        //返回map
        ResultData<?> resultData = new ResultData<ContractInfoDto>();
        
        try
        {
            resultData = contractService.getById(id);
        }
        catch (Exception e)
        {
            logger.error("contract",
                "ContractController",
                "show",
                "",
                UserInfoHolder.getUserId(),
                "",
                "合同查看初始化,查询失败",
                e);
            
        }
        
        //存放到mop中
        mop.addAttribute("contractInfo", resultData.getReturnData());
        
        //查询是否是经办人
        ResultData<?> backResult = new ResultData<OaOperatorDto>();
        
        OaOperatorDto oaOperatorDto = new OaOperatorDto();
        
        try
        {
            backResult = oaOperatorService.getByUserCode(UserInfoHolder.get().getUserCode());
            
            Map<?, ?> mapTemp = (Map<?, ?>)backResult.getReturnData();
            
            if (null != mapTemp)
            {
                oaOperatorDto = MapToEntityConvertUtil.convert(mapTemp, OaOperatorDto.class, "");
            }
            
        }
        catch (Exception e)
        {
            logger.error("contract",
                "ContractController",
                "show",
                "",
                UserInfoHolder.getUserId(),
                "",
                "合同查看初始化,查询是否是经办人失败",
                e);
        }
        
        String operCode = oaOperatorDto.getOperCode();
        String busCode = oaOperatorDto.getBusCode();
        
        //存放到mop中
        mop.addAttribute("oaOperator", oaOperatorDto);
        
        //获取当前用户及其下属用户Id集合, 用于页面权限过滤
        List<Integer> idsList = new ArrayList<Integer>();
        try
        {
            idsList = contractService.getUserIdList();
        }
        catch (Exception e)
        {
            logger.error("contract",
                "ContractController",
                "show",
                "",
                UserInfoHolder.getUserId(),
                "",
                "合同查看初始化,查询UserIdList失败",
                e);
        }
        
        //存放到mop中
        mop.addAttribute("userIdList", idsList);
        mop.addAttribute("contractId", id);
        mop.addAttribute("contractStatus",contractStatus);
        return "contract/contractDetail";
    }
    
    /** 
    * 合同作废后，释放门店
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
        
        try
        {
            //合同作废后，释放门店
            contractService.invalid(id, UserInfoHolder.getUserId());
        }
        catch (Exception e)
        {
            logger.error("contract", "ContractController", "destroy", "", UserInfoHolder.getUserId(), "", "", e);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            
            rspMap.put(Constant.RETURN_MSG_KEY, "合同作废后，释放门店 操作失败");
            
            return getOperateJSONView(rspMap);
        }
        
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        
        return getOperateJSONView(rspMap);
        
    }
    
    /**
     *  审核
     *  @param request
     * @throws Exception 
     */
    @RequestMapping(value = "/audit/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnView<?, ?> audit(HttpServletRequest request,
        @RequestParam(value = "isform", required = false) String isform, @PathVariable("id") String id)
    {
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        ContractInfoDto contractInfoDto = new ContractInfoDto();
        
        ContractDto contractDto = new ContractDto();
        contractDto.setId(Integer.valueOf(id));
        contractDto.setCompanyId(Integer.valueOf((String)reqMap.get("companyId")));
        contractInfoDto.setContract(contractDto);
        
        List<StoreDto> storeDetails = new ArrayList<StoreDto>();
        String storeIds = (String)reqMap.get("storeIdArray");// 公司ID
        if (storeIds != null)
        {
            String[] arrays = storeIds.split(",");
            for (int i = 0; i < arrays.length; i++)
            {
                StoreDto storeDto = new StoreDto();
                storeDto.setStoreId(Integer.valueOf(arrays[i]));
                storeDetails.add(storeDto);
            }
            contractInfoDto.setStoreDetails(storeDetails);
        }
        try
        {
            //更新
            contractService.audit(contractInfoDto);
        }
        catch (Exception e)
        {
            logger.error("contract", "ContractController", "audit", "", UserInfoHolder.getUserId(), "", "", e);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            
            rspMap.put(Constant.RETURN_MSG_KEY, "提交审核CRM操作失败");
            
            return getOperateJSONView(rspMap);
        }
        
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        
        return getOperateJSONView(rspMap);
    }
    
    /** 
     * 撤销--草签状态
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "cancel/{contractId}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> cancel(HttpServletRequest request, @PathVariable Integer contractId)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        ContractDto contract = new ContractDto();
        contract.setId(contractId);
        contract.setDateUpdate(new Date());
        //需要更新该合同的状态，更新为“作废”
        contract.setContractStatus(DictionaryConstants.CONTRACT_STATUS_CANCEL);
        try
        {
            cntrctService.update(contract);
            cntrctService.updateStoreReNewFlag(contractId);
        }
        catch (Exception e)
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "更新合同状态失败");
            logger.error("Contacts", "OaController", "getOaState", "", UserInfoHolder.getUserId(), "", "", e);
            return getOperateJSONView(rspMap);
        }
        
        try
        {
            contractService.invalid(contractId, UserInfoHolder.getUserId());
        }
        catch (Exception e)
        {
            logger.error("contract", "ContractController", "cancel", "", UserInfoHolder.getUserId(), "", "", e);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            
            rspMap.put(Constant.RETURN_MSG_KEY, "合同撤销失败");
            
            return getOperateJSONView(rspMap);
        }
        
        return getOperateJSONView(rspMap);
    }
    
    /** 
     * 根据公司Id查询审核通过的合同
     * @param  companyId 公司Id
     * @return
     */
    @RequestMapping(value = "getAuditpassContract/{companyId}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getAuditpassByCompanyId(@PathVariable Integer companyId)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try
        {
            //查询审核通过的合同
            ResultData<?> resultData = contractService.getAuditpassByCompanyId(companyId);
            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        }
        catch (Exception e)
        {
            logger.error("contract",
                "ContractController",
                "getAuditpassByCompanyId",
                "",
                UserInfoHolder.getUserId(),
                "",
                "",
                e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "查询审核通过的合同失败");
            return getOperateJSONView(rspMap);
        }
        
        return getSearchJSONView(rspMap);
    }
    
    /**
     * 人工开通房友账号
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "createFangyou", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> creatFangyou(HttpServletRequest request, ModelMap mop)
    {
        Map<String, Object> rspMap = new HashMap<String, Object>();
        String companyId = request.getParameter("companyId");
        String contractId = request.getParameter("contractId");
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("companyId", companyId);
        queryMap.put("contractId", contractId);
        try
        {
            contractService.createFangyou(queryMap);
        }
        catch (Exception e)
        {
            logger.error("contract",
                "ContractController",
                "creatFangyou",
                "",
                UserInfoHolder.getUserId(),
                "",
                "调OA修改表单接口错误",
                e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "创建房友账号失败");
            return getOperateJSONView(rspMap);
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
        String companyId = (String)reqMap.get("companyId");// 公司ID
        String partyB = (String)reqMap.get("partyB");// 甲方名称
        String partyBAddress = (String)reqMap.get("partyBAddress");// 甲方住址
        String lealPerson = (String)reqMap.get("lealPerson");// 法人代表人

        ContractDto contractDto = new ContractDto();
        
        // 合同编号
        String contractNo = "";
        if ("create".equals(type))
        {
            contractNo = SeqNoHelper.getInstance().getSeqNoByTypeCode("TYPE_CONTRACT");
            contractDto.setUserCreate(UserInfoHolder.getUserId());
            contractDto.setUserCode(UserInfoHolder.get().getUserCode());
            contractDto.setUserName(UserInfoHolder.get().getUserName());
            String centerId = (String)reqMap.get("centerId");
        	contractDto.setCenterId(Integer.parseInt(centerId));
        	//业绩拓展人 ，只新增合同的时候赋值
        	contractDto.setExpandingPersonnelId(userInfo.getUserCode());
            contractDto.setExpandingPersonnel(userInfo.getUserName());
            //需要设公司编号给合同门店的房友账号
            if(reqMap.containsKey("companyNo")) {
            	String companyNo = (String)reqMap.get("companyNo");// 公司CODE
            	contractDto.setCompanyNo(companyNo);
            }
        }
        else if ("update".equals(type))
        {
            contractNo = (String)reqMap.get("contractNo");
        }
        String authRepresentative = (String)reqMap.get("authRepresentative");// 甲方授权代表
        String dateLifeStart = (String)reqMap.get("dateLifeStart");// 生效开始日期
        String dateLifeEnd = (String)reqMap.get("dateLifeEnd");// 生效到期日期
        String agreementNo = ((String)reqMap.get("agreementNo")).trim();// 合作协议书编号
        String registrId = (String)reqMap.get("registrId");// 营业执照注册号
        
        String contractType = (String)reqMap.get("contractType");// 合作协议书类型
        String oaApproveType = (String)reqMap.get("oaApproveType");//oa审批流程类别
        String coopType = (String)reqMap.get("coopType");// 合作模式
        
        String depositFee = (String)reqMap.get("depositFee");// 每门店保证金
        String storeNum = (String)reqMap.get("storeNum");// 合作门店数
        String totleDepositFee = (String)reqMap.get("totleDepositFee");// 总保证金
        String penaltyFee = (String)reqMap.get("penaltyFee");// 违约金金额
        String recipients = (String)reqMap.get("recipients");// 甲方收件人
        String companyBankNo = (String)reqMap.get("companyBankNo");// 客户公司账号
        String bankAccount = (String)reqMap.get("bankAccount");// 开户银行
        String accountName = (String)reqMap.get("accountName");// 开户名
        //开户省市   2019/05/29 hzg
        String shoupaiType = (String)reqMap.get("shoupaiType");// 授牌类型
        String accountProvinceNo = (String)reqMap.get("accountProvinceNo");// 开户省编号
        String accountProvinceName = (String)reqMap.get("accountProvinceName");// 开户省名称
        String accountCityNo = (String)reqMap.get("accountCityNo");// 开户市编号
        String accountCityName = (String)reqMap.get("accountCityName");// 开户市编号
        //end
        
        
        String cityNo = (String)reqMap.get("cityNo");// 城市
        String districtNo = (String)reqMap.get("districtNo");// 区域
        String areaNo = (String)reqMap.get("areaNo");// 板块
        String recipientsAddress = (String)reqMap.get("recipientsAddress");// 甲方收件地址
        String remark = (String)reqMap.get("remark");// 合同备注
        String partyBcityNo = (String)reqMap.get("partyBcityNo");// 甲方住址城市
        String partyBdistrictNo = (String)reqMap.get("partyBdistrictNo");// 甲方住址区域
        String partyBareaNo = (String)reqMap.get("partyBareaNo");// 甲方住址板块
       /* String expandingPersonnelId = (String)reqMap.get("expandingPersonnelId");// 业绩归属拓展人员
        String expandingPersonnel = (String)reqMap.get("expandingPersonnel");// 业绩归属拓展人员
*/        int ContractTypeB2A = Integer.parseInt(reqMap.get("ContractTypeB2A").toString());// 是否乙类转甲类
        String contractVersionStr ="0";
        
        //TODO 注掉
        //        String actualOperationCityNo = (String)reqMap.get("actualOperationCityNo");// 实际经营地址城市
        //        String actualOperationDistrictNo = (String)reqMap.get("actualOperationDistrictNo");// 实际经营地址区域
        //        String actualOperationAreaNo = (String)reqMap.get("actualOperationAreaNo");// 实际经营地址板块
        //        String actualOperationAddress = (String)reqMap.get("actualOperationAddress");// 实际经营地址
        
        
        if (!"create".equals(type))
        {
            contractVersionStr = (String)reqMap.get("contractVersion");
            contractDto.setId(Integer.valueOf(id));
        }
        contractDto.setCompanyId(Integer.valueOf(companyId));
        contractDto.setPartyB(partyB);
        contractDto.setPartyBAddress(partyBAddress);
        contractDto.setLealPerson(lealPerson);
        contractDto.setContractNo(contractNo);
        contractDto.setAuthRepresentative(authRepresentative);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtil.isNotEmpty(dateLifeStart))
        {
            contractDto.setDateLifeStart(format.parse(dateLifeStart));
        }
        if (StringUtil.isNotEmpty(dateLifeEnd))
        {
            contractDto.setDateLifeEnd(format.parse(dateLifeEnd));
        }
        contractDto.setAgreementNo(agreementNo);
        contractDto.setRegistrId(registrId);
        
        //合作协议书类型
        contractDto.setContractType(Integer.valueOf(contractType));
        //OA审批流程类别
        contractDto.setOaApproveType(oaApproveType);
        //合作模式
        contractDto.setCoopType(coopType);
        //Add 2017/04/07 cning start---->
        //原合同编号
        contractDto.setOriginalContractNo((String)reqMap.get("originalContractNo"));
        String originalContractNo=contractDto.getOriginalContractNo();
        //合同类别
        if(null== originalContractNo){
        	contractDto.setOriginalContractdistinction(DictionaryConstants.OriginalContractdistinction_TYPE_N);//合同类别(新签)
        }else{
        	contractDto.setOriginalContractdistinction(DictionaryConstants.OriginalContractdistinction_TYPE_R);//合同类别(续签)
        	storeNum = "1";
        }
        String agreementType= "";
        if("10307".equals(contractType)){
        	agreementType = "11006";
        }else {
        	agreementType = (String)reqMap.get("agreementType");
        }
        contractDto.setAgreementType(Integer.valueOf(agreementType));//合同协议类型
        //Add 2017/04/07 cning end<----
        contractDto.setDepositFee(new BigDecimal(depositFee));
        contractDto.setStoreNum(Integer.valueOf(storeNum));
        contractDto.setPenaltyFee(new BigDecimal(penaltyFee));
        contractDto.setRecipients(recipients);
        contractDto.setCompanyBankNo(companyBankNo);//银行账号
        contractDto.setBankAccount(bankAccount);//开户行
        contractDto.setAccountName(accountName);//开户名
        //hzg 2019/05/29 增加授牌类型 开户省市
        contractDto.setShoupaiType(shoupaiType);
        contractDto.setAccountProvinceNo(accountProvinceNo);
        contractDto.setAccountProvinceName(accountProvinceName);
        contractDto.setAccountCityNo(accountCityNo);
        contractDto.setAccountCityName(accountCityName);
        //end
        contractDto.setRecipientsAddress(recipientsAddress);
        contractDto.setRemark(remark);
        
        contractDto.setCityNo(cityNo);
        contractDto.setDistrictNo(districtNo);
        contractDto.setAreaNo(areaNo);
        contractDto.setTotleDepositFee(new BigDecimal(totleDepositFee));
        contractDto.setPartyBcityNo(partyBcityNo);
        contractDto.setPartyBdistrictNo(partyBdistrictNo);
        contractDto.setPartyBareaNo(partyBareaNo);
       /* contractDto.setExpandingPersonnelId(expandingPersonnelId);
        contractDto.setExpandingPersonnel(expandingPersonnel);*/

        
        // 是否乙类转甲类
        contractDto.setContractB2A(ContractTypeB2A);
        contractDto.setContractVersion(Integer.valueOf(contractVersionStr));

        //        contractDto.setActualOperationCityNo(actualOperationCityNo);
        //        contractDto.setActualOperationDistrictNo(actualOperationDistrictNo);
        //        contractDto.setActualOperationAreaNo(actualOperationAreaNo);
        //        contractDto.setActualOperationAddress(actualOperationAddress);
        
        //OA 营业证,身份证,合同照片,门店照片,房友系统申请安装单,其它
       /* String oaFileIdBusiness = (String)reqMap.get("oaFileIdBusiness");
        if (StringUtil.isNotEmpty(oaFileIdBusiness))
        {
            oaFileIdBusiness = oaFileIdBusiness.replace(",", "|");
        }
        String oaFileIdCard = (String)reqMap.get("oaFileIdCard");
        if (StringUtil.isNotEmpty(oaFileIdCard))
        {
            oaFileIdCard = oaFileIdCard.replace(",", "|");
        }
        String oaFileIdPhoto = (String)reqMap.get("oaFileIdPhoto");
        if (StringUtil.isNotEmpty(oaFileIdPhoto))
        {
            oaFileIdPhoto = oaFileIdPhoto.replace(",", "|");
        }
        String oaFileIdStore = (String)reqMap.get("oaFileIdStore");
        if (StringUtil.isNotEmpty(oaFileIdStore))
        {
            oaFileIdStore = oaFileIdStore.replace(",", "|");
        }
        String oaFileIdInstall = (String)reqMap.get("oaFileIdInstall");
        if (StringUtil.isNotEmpty(oaFileIdInstall))
        {
            oaFileIdInstall = oaFileIdInstall.replace(",", "|");
        }
        String oaFileIdOther = (String)reqMap.get("oaFileIdOther");
        if (StringUtil.isNotEmpty(oaFileIdOther))
        {
            oaFileIdOther = oaFileIdOther.replace(",", "|");
        }
        
        contractDto.setOaFileIdBusiness(oaFileIdBusiness);
        contractDto.setOaFileIdCard(oaFileIdCard);
        contractDto.setOaFileIdPhoto(oaFileIdPhoto);
        contractDto.setOaFileIdStore(oaFileIdStore);
        contractDto.setOaFileIdInstall(oaFileIdInstall);
        contractDto.setOaFileIdOther(oaFileIdOther);*/
        
        // 中介联系方式
        String agentContact = (String)reqMap.get("agentContact");
        contractDto.setAgentContact(agentContact);
        // 是否有变更
        contractDto.setIsChanged(false);
        contractInfoDto.setContract(contractDto);
        
        List<StoreDto> storeDetails = new ArrayList<StoreDto>();
        List<ContactsDto> contactsDtoList = new ArrayList<ContactsDto>();
        List<StoreMaintainerDto> smtnDtoList = new ArrayList<StoreMaintainerDto>();
        String storeIds = (String)reqMap.get("storeIdArray");// 公司ID
        if (storeIds != null && StringUtil.isNotEmpty(storeIds))
        {
            String[] arrays = storeIds.split(",");
            for (int i = 0; i < arrays.length; i++)
            {
                Integer storeId = Integer.valueOf(arrays[i]);
//                // 维护人id
//                String maintainerId = (String)reqMap.get("maintainerId"+storeId);
//                // 维护人Code
//                String maintainer = (String)reqMap.get("maintainer"+storeId);
//                // 维护人姓名
//                String maintainerName = (String)reqMap.get("maintainerName"+storeId);
                // 联系人姓名
                String contactName = (String)reqMap.get("contactName"+storeId);
                // 联系人电话
                String contactPhoneNo = (String)reqMap.get("contactPhoneNo"+storeId);
              //Add By GUOPENGFEI 2017/05/25 合规性 start                
                String storetype = (String)reqMap.get("storetype"+storeId);
                
                String bstoretype = (String)reqMap.get("storetypeBlst"+storeId);    
              //Add By GUOPENGFEI 2017/05/25 合规性 end
                if ((DictionaryConstants.Store_QualityGrade_A + "").equals(storetype)) {
                    bstoretype = "";
                }
                
                StoreDto storeDto = new StoreDto();
                storeDto.setStoreId(storeId);
              //Add By GUOPENGFEI 2017/05/25 合规性 start      
                if(storetype.isEmpty() == false)
                {
                	storeDto.setABTypeStore(Integer.parseInt(storetype));
                }
                
                storeDto.setBTypeStore(bstoretype.replace(";", ","));
              //Add By GUOPENGFEI 2017/05/25 合规性 end                    
                
                
                if ("create".equals(type)) {
                	// 创建联系人
                	ContactsDto contactsDto = new ContactsDto();
                	String contactsNo = SeqNoHelper.getInstance().getSeqNoByTypeCode("TYPE_CONTACT");
                	contactsDto.setContactsNo(contactsNo);
                    contactsDto.setStoreId(storeId);
                    contactsDto.setName(contactName);
                    contactsDto.setMobilePhone(contactPhoneNo);
                    contactsDto.setUserCreate(UserInfoHolder.getUserId());
                    contactsDto.setDateCreate(new Date());
                    contactsDtoList.add(contactsDto);
                    // 创建维护人
//                    StoreMaintainerDto smtnDto = new StoreMaintainerDto();
//                    smtnDto.setStoreId(storeId);
//                    smtnDto.setUserCode(maintainer);
//                    smtnDto.setSetUserCode(userInfo.getUserCode());
//                    smtnDto.setSetUserName(userInfo.getUserName());
//                    smtnDto.setUserIdCreate(userInfo.getUserId());
//                    smtnDto.setDateCreate(new Date());
//                    smtnDto.setDelFlag("N");
//                    smtnDto.setDateMtcStart(new Date());
//                    smtnDtoList.add(smtnDto);
                    
//                    storeDto.setMaintainer(maintainer);
//                    storeDto.setMaintainerName(maintainerName);
                }
                storeDetails.add(storeDto);
             }
            contractInfoDto.setContactsDtoList(contactsDtoList);
            contractInfoDto.setStoreMaintainerDtoList(smtnDtoList);
            contractInfoDto.setStoreDetails(storeDetails);
        }
        
        // fangyou营业证,身份证,合同照片,门店照片,房友系统申请安装单
        List<ContractFileDto> fileList = new ArrayList<ContractFileDto>();
        String file = (String)reqMap.get("fileRecordMainIds");
        if (file != null && StringUtil.isNotEmpty(file))
        {
            String[] arrays = file.split(",");
            for (int i = 0; i < arrays.length; i++)
            {
                ContractFileDto fileDto = new ContractFileDto();
                fileDto.setFileRecordMainId(arrays[i]);
                fileList.add(fileDto);
            }
            contractInfoDto.setFileRecordMain(fileList);
        }
        
        
        //Add By NingChao 2017/04/07 Start
          //续签时添加
          if(null != originalContractNo){
  	        List<ContractFileDto> oldFileList = new ArrayList<ContractFileDto>();
  	        String oldFile = (String)reqMap.get("oldfileRecordMainIds");// 营业证,身份证,合同照片,门店照片,房友系统申请安装单
  	        LogHelper logger = LogHelper.getLogger(ContractController.class);
  	        if (oldFile != null && StringUtil.isNotEmpty(oldFile))
  	        {
  	            String[] arrays = oldFile.split(",");
  	            for (int i = 0; i < arrays.length; i++)
  	            {
  	                ContractFileDto fileDto = new ContractFileDto();
  	                fileDto.setFileRecordMainId(arrays[i]);
  	                oldFileList.add(fileDto);
  	            }
  	            contractInfoDto.setOldFileRecordMain(oldFileList);
  	        }
          }
        //Add By NingChao 2017/04/07 End
          
        //OA
        
        if ("update".equals(type))
        {
            List<ContractFileDto> oldFileList = new ArrayList<ContractFileDto>();
            String oldFile = (String)reqMap.get("oldfileRecordMainIds");// 营业证,身份证,合同照片,门店照片,房友系统申请安装单
            if (oldFile != null && StringUtil.isNotEmpty(oldFile))
            {
                String[] arrays = oldFile.split(",");
                for (int i = 0; i < arrays.length; i++)
                {
                    ContractFileDto fileDto = new ContractFileDto();
                    fileDto.setFileRecordMainId(arrays[i]);
                    oldFileList.add(fileDto);
                }
                contractInfoDto.setOldFileRecordMain(oldFileList);
            }
        }
    }
    
    /** 
    * 初始化
    * @param request
    * @param model
    * @return
    */
    @RequestMapping(value = "changeRecord/{id}/{contractStatus}", method = RequestMethod.GET)
    public String showChangeRecord(@PathVariable("id") Integer id, @PathVariable("contractStatus") Integer contractStatus,ModelMap mop)
    {
    	ResultData<?> reback = new ResultData<>();
    	
    	// 1.获取合同变更信息
		List<?> contentlist = new ArrayList<>();
		try {
			// 根据合同ID查询合同变更申请记录
			reback = stopContractService.getContractChange(id);

			// 页面数据
			contentlist = (List<?>) reback.getReturnData();
		} catch (Exception e) {
			logger.error("contract", "ContractController", "showChangeRecord", "",
					UserInfoHolder.getUserId(), "", "合同变更信息查询失败！", e);
		}
		mop.addAttribute("contentlist", contentlist);
		
		// 2.获取合同状态信息
		try {
			// 根据合同ID查询合同信息
			reback = contractService.getContractById(id);

		} catch (Exception e) {
			logger.error("contract", "ContractChangeController", "showChangeRecord", "",
					UserInfoHolder.getUserId(), "", "合同信息查询失败！", e);
		}
		// 存放到mop中
		mop.addAttribute("contractInfo", reback.getReturnData());
		
		//3.TODO 跨区域经办人。。。
		//查询是否是经办人
        ResultData<?> backResult = new ResultData<OaOperatorDto>();
        
        OaOperatorDto oaOperatorDto = new OaOperatorDto();
        
        try
        {
            backResult = oaOperatorService.getByUserCode(UserInfoHolder.get().getUserCode());
            
            Map<?, ?> mapTemp = (Map<?, ?>)backResult.getReturnData();
            
            if (null != mapTemp)
            {
                oaOperatorDto = MapToEntityConvertUtil.convert(mapTemp, OaOperatorDto.class, "");
            }
            
        }
        catch (Exception e)
        {
            logger.error("contract",
                "ContractController",
                "show",
                "",
                UserInfoHolder.getUserId(),
                "",
                "合同查看初始化,查询是否是经办人失败",
                e);
        }
        //4. 获取合同关联门店的撤消状态
        ResultData<?> storeResult = new ResultData<String>();
        try
        {
            storeResult = contractService.getContractStoreIsCancel(id);
            
            if(storeResult.getReturnCode().equals(ReturnCode.SUCCESS)){
                if(storeResult.getReturnData().equals("Y")){
                    mop.addAttribute("storeCancelSate", storeResult.getReturnData());
                }else{
                    mop.addAttribute("storeCancelSate", "N");
                }
            }else{
                
                logger.error("contract",
                    "ContractController",
                    "getContractStoreIsCancel",
                    "",
                    UserInfoHolder.getUserId(),
                    "",
                    "获取合同门店关联表的业绩撤销状态失败",
                    null);
            }
        }
        catch (Exception e)
        {
            logger.error("contract",
                "ContractController",
                "getContractStoreIsCancel",
                "",
                UserInfoHolder.getUserId(),
                "",
                "获取合同门店关联表的业绩撤销状态失败",
                e);
        }
        
        String operCode = oaOperatorDto.getOperCode();
        String busCode = oaOperatorDto.getBusCode();
        
        //存放到mop中
        mop.addAttribute("oaOperator", oaOperatorDto);
        mop.addAttribute("contractStatus", contractStatus);
        // 查询变更记录数据返回
        mop.addAttribute("contractId", id);
        return "contract/contractChangeRecord";

    }
    
    /** 
     * 跳转到分账页面
     * @return
     */
    @RequestMapping(value = "toSplit", method = RequestMethod.GET)
    public ModelAndView toSplit(HttpServletRequest request,ModelMap mop)
    {
        String contractId = request.getParameter("contractId");
        // 根据合同编号查询 合同信息 以及关联门店信息
        ResultData<?> resultData = new ResultData<ContractInfoDto>();
        try
        {
            resultData = contractService.getSplitInfo(Integer.valueOf(contractId));
            mop.put("contractInfo", resultData.getReturnData());
        }
        catch (Exception e)
        {
            logger.error("contract",
                "ContractController",
                "toSplit",
                "",
                UserInfoHolder.getUserId(),
                "",
                "根据合同Id查合同,查询失败",
                e);
        }
        //返回视图
        ModelAndView mv = new ModelAndView("contract/splitPopup");
        return mv;
    }
    
    /** 
     * 保存分账页面
     * @return
     */
    @RequestMapping(value = "saveSplit", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> saveSplit(HttpServletRequest request,ModelMap mop)
    {
        String contractId = request.getParameter("contractId");
        String storeIds = request.getParameter("storeIds");
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        // 根据合同编号查询 合同信息 以及关联门店信息
        ResultData<?> resultData = new ResultData<>();
        try
        {
            // 更新 合同门店 关系 表的 是否到账和到账日期
        	resultData = contractService.updateCtctStore(Integer.valueOf(contractId),storeIds);
        	rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        	rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        }
        catch (Exception e)
        {
        	logger.error("contract",
                    "ContractController",
                    "saveSplit",
                    "",
                    UserInfoHolder.getUserId(),
                    "",
                    "根据合同Id查合同,查询失败",
                    e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
            return getOperateJSONView(rspMap);
        }
        return getOperateJSONView(rspMap);
    }
    
    /**
     * 
    * 获取合同剩余未分账保证金-用于校验
    * @param request
    * @param mop
    * @return
     */
    @RequestMapping(value = "checkRestDeposit", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> checkRestDeposit(HttpServletRequest request,ModelMap mop)
    {
        String contractId = request.getParameter("contractId");
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        // 根据合同Id查询合同剩余未分账保证金
        ResultData<?> resultData = new ResultData<>();
        try
        {
            // 更新 合同门店 关系 表的 是否到账和到账日期
            resultData = contractService.checkRestDeposit(Integer.valueOf(contractId));
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        }
        catch (Exception e)
        {
            logger.error("contract",
                    "ContractController",
                    "checkRestDeposit",
                    "",
                    UserInfoHolder.getUserId(),
                    "",
                    "获取合同剩余未分账保证金-用于校验,查询失败",
                    e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            return getSearchJSONView(rspMap);
        }
        return getSearchJSONView(rspMap);
    }
    
    /** 
     *  获取审核批注原因
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "oa/opinions/{flowId}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getOpinions(HttpServletRequest request, @PathVariable("flowId") String flowId)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        ResultData<?> resultData = new ResultData<>();
        
        try
        {
        	resultData = contractService.getOpinionsCrm(flowId);
        }
        catch (Exception e)
        {
            logger.error("Contacts", "OaController", "oaModify", "", UserInfoHolder.getUserId(), "", "调OA获取审核批注原因错误", e);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            
            rspMap.put(Constant.RETURN_MSG_KEY, "获取审核批注原因失败，OA接口异常");
            
            return getOperateJSONView(rspMap);
        }
        
    	if(resultData.getTotalCount() == "0")
    	{
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "没有审核批注原因");
            return getOperateJSONView(rspMap);
    		
    	}
    	
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        
        return getOperateJSONView(rspMap);
    }    

    /** 
     * 根据登录用户获取所属中心
     * @param request
     * @param model
     * @return
     */
     @RequestMapping(value = "/center", method = RequestMethod.GET)
     @ResponseBody
     public ReturnView<?, ?>  getCenterListByUserId(HttpServletRequest request, ModelMap mop)
     {
    	 //返回map
         Map<String, Object> rspMap = new HashMap<String, Object>();
         
    	 try {
			 ResultData<?> resultData = contractService.getCenterListByUserId(UserInfoHolder.getUserId());
			 rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
             rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
         }
         catch (Exception e)
         {
             logger.error("Contract", "ContractController", "getCenterListByUserId", "", UserInfoHolder.getUserId(), "", "根据登录用户获取所属中心失败", e);

             rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
             rspMap.put(Constant.RETURN_MSG_KEY, "根据登录用户获取所属中心失败");
         }
    	return getSearchJSONView(rspMap);
     }
     
     /**
      * 所属中心初始化
      * @param mop
      * @return
      */
     @RequestMapping(value = "/center/list", method = RequestMethod.GET)
     public String toExchangeCenter(ModelMap mop)
     {
    	 List<?> contentlist = new ArrayList<>();
    	 try {
			ResultData<?> resultData = contractService.getCenterListByUserId(UserInfoHolder.getUserId());
			contentlist = (List<?>)resultData.getReturnData();
		 } catch (Exception e) {
			 logger.error("Contract", "ContractController", "toExchangeCenter", "", UserInfoHolder.getUserId(), "", "", e);
		 }
    	 //存放到mop中
         mop.addAttribute("info", contentlist);
         return "contract/exchangeCenter";
     }
     
     @RequestMapping(value = "/contractDetailPop/{id}/{contractStatus}", method = RequestMethod.GET)
     public String contractDetailPop(@PathVariable("id") Integer id, @PathVariable("contractStatus") Integer contractStatus, ModelMap mop)
     {
         //返回map
         ResultData<?> resultData = new ResultData<ContractInfoDto>();
         
         try
         {
             resultData = contractService.getById(id);
         }
         catch (Exception e)
         {
             logger.error("contract",
                 "ContractController",
                 "show",
                 "",
                 UserInfoHolder.getUserId(),
                 "",
                 "合同查看初始化,查询失败",
                 e);
             
         }
         
         //存放到mop中
         mop.addAttribute("contractInfo", resultData.getReturnData());
         
         //查询是否是经办人
         ResultData<?> backResult = new ResultData<OaOperatorDto>();
         
         OaOperatorDto oaOperatorDto = new OaOperatorDto();
         
         try
         {
             backResult = oaOperatorService.getByUserCode(UserInfoHolder.get().getUserCode());
             
             Map<?, ?> mapTemp = (Map<?, ?>)backResult.getReturnData();
             
             if (null != mapTemp)
             {
                 oaOperatorDto = MapToEntityConvertUtil.convert(mapTemp, OaOperatorDto.class, "");
             }
             
         }
         catch (Exception e)
         {
             logger.error("contract",
                 "ContractController",
                 "show",
                 "",
                 UserInfoHolder.getUserId(),
                 "",
                 "合同查看初始化,查询是否是经办人失败",
                 e);
         }
         
         //存放到mop中
         mop.addAttribute("oaOperator", oaOperatorDto);
         
         //获取当前用户及其下属用户Id集合, 用于页面权限过滤
         List<Integer> idsList = new ArrayList<Integer>();
         try
         {
             idsList = contractService.getUserIdList();
         }
         catch (Exception e)
         {
             logger.error("contract",
                 "ContractController",
                 "show",
                 "",
                 UserInfoHolder.getUserId(),
                 "",
                 "合同查看初始化,查询UserIdList失败",
                 e);
         }
         
         //存放到mop中
         mop.addAttribute("userIdList", idsList);
         mop.addAttribute("contractId", id);
         mop.addAttribute("contractStatus",contractStatus);
         return "contract/contractDetailPop";
     }
     
     @RequestMapping(value = "/contractDetailPopPre/{contractNo}", method = RequestMethod.GET)
     @ResponseBody
     public ReturnView<?, ?>  contractDetailPop(HttpServletRequest request, ModelMap mop,@PathVariable("contractNo") String contractNo)
     {
         //返回map
         Map<String, Object> rspMap = new HashMap<String, Object>();
         
         try {
             ResultData<?> resultData = contractService.getByNo(contractNo);
             rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
             rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
         }
         catch (Exception e)
         {
             logger.error("Contract", "ContractController", "contractDetailPop", "", UserInfoHolder.getUserId(), "", "根据合同编号查询信息异常", e);

             rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
             rspMap.put(Constant.RETURN_MSG_KEY, "根据合同编号查询信息异常");
         }
        return getSearchJSONView(rspMap);
     }
     
     /**
      * 【描述】: 跳转上传补充协议
      *
      * @author yinkun
      * @date: 2018年2月28日 上午10:47:29
      * @param mop
      * @param contractId
      * @param request
      * @return
      */
     @RequestMapping(value = "toUploadAdditional/{contractId}", method = RequestMethod.GET)
     public String toUploadAdditional(ModelMap mop,@PathVariable("contractId") Integer contractId,HttpServletRequest request){
         mop.addAttribute("contractId", contractId);
         
         Map<String, Object> param = new HashMap<>();
         param.put("refId", contractId);
         param.put("fileTypeId", 1026);

         try {
            ResultData<?> backResult = fileService.getContractFile(param);
            if (null != backResult && "200".equals(backResult.getReturnCode())) {
                mop.addAttribute("fileList", backResult.getReturnData());
            }
        } catch (Exception e) {
            logger.error("Contract", "ContractController", "toUploadAdditional", "", UserInfoHolder.getUserId(), "", "查询补充协议异常", e);
        }
         
         return "contract/uploadAdditionalPop";
     }
     
     /**
      * 【描述】:保存合同补充协议 
      *
      * @author yinkun
      * @date: 2018年2月28日 上午11:26:09
      * @param request
      * @return
      */
     @RequestMapping(value = "/uploadAdditional", method = RequestMethod.GET)
     @ResponseBody
     public ReturnView<?, ?>  uploadAdditional(HttpServletRequest request){
         
         String contractId = request.getParameter("contractId");
         String fileRecordMainIds = request.getParameter("fileRecordMainIds");
         
         //返回map
         Map<String, Object> rspMap = new HashMap<String, Object>();
         
         try {
             ResultData<?> resultData = contractService.uploadAdditional(contractId,fileRecordMainIds);
             rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
         }
         catch (Exception e){
             logger.error("Contract", "ContractController", "uploadAdditional", "", UserInfoHolder.getUserId(), "", "上传补充协议异常", e);
             rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
             rspMap.put(Constant.RETURN_MSG_KEY, "上传补充协议异常");
         }
        return getSearchJSONView(rspMap);
     }
     /** 
      * @Title: operateChangeCt 
      * @Description: 运营变更合同状态并补记录
      */
      @ResponseBody
      @RequestMapping(value = "/operateChangeCt", method = RequestMethod.POST)
      public ReturnView<?, ?> operateChangeCt(HttpServletRequest request) {
          //返回map
          Map<String, Object> rspMap = new HashMap<String, Object>();
          //获取map
          Map<String, Object> reqMap = bindParamToMap(request);
          ContractInfoDto contractInfoDto = new ContractInfoDto();
          ContractDto contractDto = new ContractDto();
          if(reqMap.containsKey("id")){
        	  //合同id
        	  contractDto.setId(Integer.valueOf(reqMap.get("id").toString()));
        	  //合同状态设置为10404
        	  //contractDto.setContractStatus(10404);
        	 Integer userId = UserInfoHolder.getUserId();
          }
          contractInfoDto.setContract(contractDto);
          try {
              //更新
        	  contractService.operateChangeCt(contractInfoDto);
          } catch (Exception e) {
              logger.error("Contract", "ContractController", "operateChangeCt", "", UserInfoHolder.getUserId(), "", "状态变更失败", e);
              rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
              rspMap.put(Constant.RETURN_MSG_KEY, "状态变更失败");
              return getOperateJSONView(rspMap);
          }
          rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
          return getOperateJSONView(rspMap);
      }
}
