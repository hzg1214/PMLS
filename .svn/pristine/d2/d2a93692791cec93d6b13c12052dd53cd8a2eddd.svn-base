package cn.com.eju.deal.gpContract.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.helper.SeqNoHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.service.CityService;
import cn.com.eju.deal.common.service.CommonService;
import cn.com.eju.deal.common.service.ProvinceService;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.contacts.ContactsDto;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.contract.ContractFileDto;
import cn.com.eju.deal.dto.contract.ContractInfoDto;
import cn.com.eju.deal.dto.gpContract.GpContractDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.gpContract.service.GpContractService;
import cn.com.eju.deal.oa.service.OaOperatorService;
import com.alibaba.fastjson.JSON;
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
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by haidan on 2018/8/30.
 */
@Controller
@RequestMapping(value = "gpContract")
public class GpContractController extends BaseController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "gpContractService")
    private GpContractService gpContractService;

    @Resource(name="provinceService")
    private ProvinceService provinceService;

    @Resource(name = "cityService")
    private CityService cityService;

    @Resource(name = "oaOperatorService")
    private OaOperatorService oaOperatorService;

    @Resource(name = "commonService")
    private CommonService commonService;

    /**
     * 列表页面
     *
     * @param request
     * @param mop
     * @param response
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap mop, HttpServletResponse response) {
        Map<String, Object> map = bindParamToMap(request);
        if (map.containsKey("searchParam") && "1".equals(map.get("searchParam"))) {
            Map<String, Object> searchParamMap = getRememberSearch(request, ComConstants.CONTRACT_LIST_SEARCH);
            mop.put("searchParamMap", JSON.toJSON(searchParamMap));
        } else {
            clearSearch(request, response, ComConstants.CONTRACT_LIST_SEARCH);
        }
        mop.put("list_search_page", ComConstants.CONTRACT_LIST_SEARCH);
        mop.put("gpAddFlag",gpContractService.getTemplateCode(UserInfoHolder.get().getCityNo(),"gpAddFlag"));
        return "gpContract/gpContractList";
    }

    /**
     * 查询--list
     *
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "q", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap mop, HttpServletResponse response) {

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);

        //获取页面显示数据
        List<?> contentlist = new ArrayList<>();
        try {
            if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.CONTRACT_LIST_SEARCH, reqMap);
            }
            reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
            ResultData<?> reback = gpContractService.queryList(reqMap, pageInfo);

            //页面数据
            contentlist = (List<?>) reback.getReturnData();
        } catch (Exception e) {
            logger.error("gpContract", "GpContractController", "index", "", UserInfoHolder.getUserId(), "", "合同查询失败", e);
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

        return "gpContract/gpContractListCtx";
    }

    /**
     * 新增页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "c/{centerId}/{centerName}", method = RequestMethod.GET)
    public String toAdd(@PathVariable("centerId") int centerId, @PathVariable("centerName") String centerName, ModelMap mop) {
        UserInfo userInfo = UserInfoHolder.get();
        try {
            ResultData<?> reback = this.provinceService.queryProvinceList();
            //页面数据
            List<?> provinceList = (List<?>) reback.getReturnData();
            mop.put("provinceList",provinceList);
        } catch (Exception e1) {
            logger.warn("获取省份列表失败");
        }
        try{
            //我方全称
            Map<String, Object> queryParam = new HashMap<>();
            queryParam.put("cityNo",userInfo.getCityNo());
            ResultData<?> fullNameList = this.commonService.queryFullNameList(queryParam);
            mop.put("fullNameList", fullNameList.getReturnData());
        }catch (Exception e){
            logger.warn("获取我方全称列表失败");
        }
        mop.addAttribute("userId", userInfo.getUserId());
        mop.addAttribute("userName", userInfo.getUserName());
        mop.addAttribute("centerId", centerId);
        mop.addAttribute("centerName", centerName);
        return "gpContract/gpContractAdd";
    }

    /**
     * 创建
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> create(HttpServletRequest request, ModelMap modelMap) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);

        GpContractDto gpContractDto = new GpContractDto();

        try {
            //获取页面字段值，转为DTO,创建合同
            setDto(reqMap, gpContractDto, "create", "");
            //创建业绩归属人信息
            ResultData<?> resultData = gpContractService.create(gpContractDto);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        } catch (Exception e) {
            logger.error("gpContract", "GpContractController", "create", "", UserInfoHolder.getUserId(), "", "创建公盘合同", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "合同创建失败");
            return getOperateJSONView(rspMap);
        }

        return getOperateJSONView(rspMap);
    }

    /**
     * 编辑页面
     *
     * @param id
     * @param mop
     * @return
     */
    @RequestMapping(value = "/u/{id}", method = RequestMethod.GET)
    public String toEdit(@PathVariable("id") int id, ModelMap mop) {
        Map<String, Object> map = new HashMap<>();

        UserInfo userInfo = UserInfoHolder.get();
        //返回map
        ResultData<?> resultData = new ResultData<ContractDto>();
        try {
            resultData = gpContractService.getById(id);
        } catch (Exception e) {
            logger.error("gpContract", "GpContractController", "toEdit", "", UserInfoHolder.getUserId(), "", "修改初始化失败", e);
        }
        mop.put("gpContract", resultData.getReturnData());

        try{
            //我方全称
            Map<String, Object> queryParam = new HashMap<>();
            queryParam.put("cityNo",userInfo.getCityNo());
            ResultData<?> fullNameList = this.commonService.queryFullNameList(queryParam);
            mop.put("fullNameList", fullNameList.getReturnData());
        }catch (Exception e){
            logger.warn("获取我方全称列表失败");
        }

        try {
            ResultData<?> reback = this.provinceService.queryProvinceList();
            //页面数据
            List<?> provinceList = (List<?>) reback.getReturnData();
            mop.put("provinceList",provinceList);
        }catch (Exception e){
            logger.error("gpContract", "GpContractController", "toEdit", id+"", null, "", "查询省份失败", e);
        }

        try {
            if(resultData.getReturnData() != null && ((Map<String, Object>) resultData.getReturnData()).get("accountProvinceNo") != null){
                Map<String,Object> reqMap = new HashMap<>();
                reqMap.put("provinceNo",((Map<String, Object>) resultData.getReturnData()).get("accountProvinceNo"));
                ResultData<?> reback = this.cityService.queryCitylist(reqMap);
                //页面数据
                List<?> cityList = (List<?>) reback.getReturnData();
                mop.put("cityList",cityList);
            }
        }catch (Exception e){
            logger.error("gpContract", "GpContractController", "toEdit", id+"", null, "", "查询城市失败", e);
        }

        return "gpContract/gpContractEdit";
    }

    /**
     * 更新公盘信息
     *
     * @param request
     * @param isform
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> update(HttpServletRequest request,  @PathVariable("id") String id) {

        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);

        reqMap.remove("_method");

        GpContractDto gpContractDto = new GpContractDto();

        try {
            setDto(reqMap, gpContractDto, "update", id);
            //更新
            ResultData<?> resultData = gpContractService.update(gpContractDto);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        } catch (Exception e) {

            logger.error("gpContract", "GpContractController", "update", "", UserInfoHolder.getUserId(), "", "", e);

            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);

            rspMap.put(Constant.RETURN_MSG_KEY, "合同修改 操作失败");

            return getOperateJSONView(rspMap);
        }
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
    public String show(@PathVariable("id") Integer id, ModelMap mop) {
        //返回map
        ResultData<?> resultData = new ResultData<ContractInfoDto>();

        try {
            resultData = gpContractService.getById(id);
        } catch (Exception e) {
            logger.error("gpContract",
                    "GpContractController",
                    "show",
                    "",
                    UserInfoHolder.getUserId(),
                    "",
                    "合同查看初始化,查询失败",
                    e);
        }

        //存放到mop中
        mop.addAttribute("gpContract", resultData.getReturnData());

//查询是否是经办人
        ResultData<?> backResult = new ResultData<>();
        try
        {
            backResult = oaOperatorService.getByUserCode(UserInfoHolder.get().getUserCode());
        }
        catch (Exception e)
        {
            logger.error("gpContract",
                    "GpContractController",
                    "show",
                    "",
                    UserInfoHolder.getUserId(),
                    "",
                    "公盘合同详情, 查询经办人失败",
                    e);
        }

        //存放到mop中
        mop.addAttribute("oaOperator", backResult.getReturnData());

        return "gpContract/gpContractDetail";
    }

    /**
     * 公盘合同作废
     * @param id
     * @return
     */
    @RequestMapping(value = "cancel/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> cancel(@PathVariable Integer id)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        GpContractDto gpContractDto = new GpContractDto();
        gpContractDto.setId(id);
        gpContractDto.setUserUpdate(UserInfoHolder.getUserId());
        gpContractDto.setDateUpdate(new Date());
        gpContractDto.setContractStatus(DictionaryConstants.CONTRACT_STATUS_CANCEL);

        try
        {
            gpContractService.updateStatus(gpContractDto);
        }
        catch (Exception e)
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "更新公盘合同状态失败");
            logger.error("gpContract", "GpContractController", "updateStatus", "", UserInfoHolder.getUserId(), "", "", e);
            return getOperateJSONView(rspMap);
        }

        try
        {
            gpContractService.invalid(id);
        }
        catch (Exception e)
        {
            logger.error("gpContract", "GpContractController", "cancel", "", UserInfoHolder.getUserId(), "", "", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "合同撤销失败");
            return getOperateJSONView(rspMap);
        }

        return getOperateJSONView(rspMap);
    }

    private static void setDto(Map<String, Object> reqMap, GpContractDto gpContractDto, String type, String id) throws Exception {
        UserInfo userInfo = UserInfoHolder.get();
        List<StoreDto> storeDetails = new ArrayList<>();
        List<ContactsDto> contactsDtoList = new ArrayList<>();
        String gpContractNo = "";
        String companyId = (String)reqMap.get("companyId");// 公司ID
        String partyB = (String)reqMap.get("partyB");// 甲方名称
        String legalPerson = (String)reqMap.get("legalPerson");// 法人代表人
        String partyBcityNo = (String)reqMap.get("partyBcityNo");// 甲方住址
        String partyBdistrictNo = (String)reqMap.get("partyBdistrictNo");// 甲方住址
        String partyBCityName = (String)reqMap.get("partyBCityName");// 甲方住址
        String partyBDistrictName = (String)reqMap.get("partyBDistrictName");// 甲方住址
        String partyBAddress = (String)reqMap.get("partyBAddress");// 甲方住址
        String registerId = (String)reqMap.get("registerId");// 统一社会信用代码
        String ourFullId = (String)reqMap.get("ourFullId");// 我方全称
        String ourFullName = (String)reqMap.get("ourFullName");// 我方全称
        String gpAgreementNo = (String)reqMap.get("gpAgreementNo");// 公盘协议书编号
        String storeNum = (String)reqMap.get("storeNum");// 门店数
        String depositFee = (String)reqMap.get("depositFee");// 保证金
        String dateLifeStart = (String)reqMap.get("dateLifeStart");// 合同生效日期
        String dateLifeEnd = (String)reqMap.get("dateLifeEnd");// 合同到期日期
        String partyBNm = (String)reqMap.get("partyBNm");// 甲方授权代表
        String partyBTel = (String)reqMap.get("partyBTel");// 甲方联系方式
        
        String exPersonId = (String)reqMap.get("exPersonId");// 业绩归属人
        String exPerson = (String)reqMap.get("exPerson");// 业绩归属人
        String centerId = (String)reqMap.get("centerId");// 公司名称=甲方名称
        String centerName = (String)reqMap.get("centerName");// 业绩归属中心
        
        String accountNm = (String)reqMap.get("accountNm");// 开户名
        String accountProvinceNo = (String)reqMap.get("accountProvinceNo");// 开户省市
        String accountProvinceNm = (String)reqMap.get("accountProvinceNm");// 开户省市
        String accountCityNo = (String)reqMap.get("accountCityNo");// 开户省市
        String accountCityNm = (String)reqMap.get("accountCityNm");// 开户省市
        String bankAccountNm = (String)reqMap.get("bankAccountNm");// 开户行
        String bankAccount = (String)reqMap.get("bankAccount");// 银行帐号
        String remark = (String)reqMap.get("remark");// 合同备注
        String companyName = (String)reqMap.get("partyB");// 公司名称=甲方名称
        String cityNo = userInfo.getCityNo();//业绩归属城市
        Integer contractType = DictionaryConstants.OriginalContractdistinction_TYPE_N;

        if ("create".equals(type))
        {
            //默认新签
            contractType = DictionaryConstants.OriginalContractdistinction_TYPE_N;
            gpContractDto.setUserCreate(userInfo.getUserId());
            gpContractDto.setDateCreate(new Date());

        }else if ("update".equals(type))
        {
            gpContractNo = (String)reqMap.get("gpContractNo");
            gpContractDto.setId(Integer.valueOf(id));
            gpContractDto.setUserUpdate(userInfo.getUserId());
            gpContractDto.setDateUpdate(new Date());
        }
        gpContractDto.setGpContractNo(gpContractNo);
        gpContractDto.setPartyB(partyB);
        gpContractDto.setPartyBCityNo(partyBcityNo);
        gpContractDto.setPartyBCityName(partyBCityName);
        gpContractDto.setPartyBDistrictNo(partyBdistrictNo);
        gpContractDto.setPartyBDistrictName(partyBDistrictName);
        gpContractDto.setPartyBAddress(partyBAddress);
        gpContractDto.setLegalPerson(legalPerson);
        gpContractDto.setGpAgreementNo(gpAgreementNo.toUpperCase());//大写
        gpContractDto.setRegisterId(registerId);
        gpContractDto.setOurFullId(Integer.valueOf(ourFullId));
        gpContractDto.setOurFullName(ourFullName);
        gpContractDto.setStoreNum(Integer.valueOf(storeNum));
        gpContractDto.setDepositFee(new BigDecimal(depositFee));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtil.isNotEmpty(dateLifeStart))
        {
            gpContractDto.setDateLifeStart(format.parse(dateLifeStart));
        }
        if (StringUtil.isNotEmpty(dateLifeEnd))
        {
            gpContractDto.setDateLifeEnd(format.parse(dateLifeEnd));
        }
        gpContractDto.setExPersonId(exPersonId);
        gpContractDto.setExPerson(exPerson);
        gpContractDto.setCenterId(Integer.valueOf(centerId));
        gpContractDto.setCenterName(centerName);
        gpContractDto.setAccountNm(accountNm);
        gpContractDto.setAccountProvinceNo(accountProvinceNo);
        gpContractDto.setAccountProvinceNm(accountProvinceNm);
        gpContractDto.setAccountCityNo(accountCityNo);
        gpContractDto.setAccountCityNm(accountCityNm);
        gpContractDto.setBankAccountNm(bankAccountNm);
        gpContractDto.setBankAccount(bankAccount);
        gpContractDto.setPartyBNm(partyBNm);
        gpContractDto.setPartyBTel(partyBTel);
        gpContractDto.setRemark(remark);
        gpContractDto.setCompanyId(Integer.valueOf(companyId));
        gpContractDto.setCompanyName(companyName);
        gpContractDto.setCityNo(cityNo);
        gpContractDto.setContractType(contractType);
        Date date = new Date();
        long nd = date.getTime();
        long ds = gpContractDto.getDateLifeStart().getTime();
        long de = gpContractDto.getDateLifeEnd().getTime();
        if(nd >= ds && ds <= de)
        {
            gpContractDto.setValid(DictionaryConstants.VALID_TYPE_SX);//有效标识
        }else if(nd > de){
            gpContractDto.setValid(DictionaryConstants.VALID_TYPE_GQ);//过期标识
        }else{
            gpContractDto.setValid(DictionaryConstants.VALID_TYPE_DSX);//待生效标识
        }
        //关联门店
        String storeIds = (String)reqMap.get("storeIdArray");// 公司ID
        if (storeIds != null && StringUtil.isNotEmpty(storeIds))
        {
            String[] arrays = storeIds.split(",");
            for (String array : arrays) {
                Integer storeId = Integer.valueOf(array);
//                String storetype = (String) reqMap.get("storetype" + storeId);
//                String bstoretype = (String) reqMap.get("storetypeBlst" + storeId);
                StoreDto storeDto = new StoreDto();
                storeDto.setStoreId(storeId);
//                if (!storetype.isEmpty()) {
//                    storeDto.setABTypeStore(Integer.parseInt(storetype));
//                }
//                storeDto.setBTypeStore(bstoretype.replace(";", ","));
                storeDetails.add(storeDto);

                if ("create".equals(type) || "update".equals(type)) {
                    // 联系人姓名
                    String contactName = (String)reqMap.get("contactName"+storeId);
                    // 联系人电话
                    String contactPhoneNo = (String)reqMap.get("contactPhoneNo"+storeId);
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
                }
            }
            gpContractDto.setStoreDetails(storeDetails);
            gpContractDto.setContactsDtoList(contactsDtoList);
        }
        //图片
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
            gpContractDto.setFileRecordMain(fileList);
        }
        if ("update".equals(type))
        {
            List<ContractFileDto> oldFileList = new ArrayList<ContractFileDto>();
            String oldFile = (String)reqMap.get("oldfileRecordMainIds");
            if (oldFile != null && StringUtil.isNotEmpty(oldFile))
            {
                String[] arrays = oldFile.split(",");
                for (int i = 0; i < arrays.length; i++)
                {
                    ContractFileDto fileDto = new ContractFileDto();
                    fileDto.setFileRecordMainId(arrays[i]);
                    oldFileList.add(fileDto);
                }
                gpContractDto.setOldFileRecordMain(oldFileList);
            }
        }
    }

    /**
     * 公盘合同审批
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "oa/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> toOaGPAuth(@PathVariable("id") Integer id, HttpServletRequest request) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        UserInfo userInfo = UserInfoHolder.get();

        //获取请求参数
        Map<String, Object> reqqMap = bindParamToMap(request);
        String busNo = (String) reqqMap.get("busNo");

        Map<String, Object> reqMap = new HashMap<>();

        //审核中，审核通过不能再提交OA审核 Start
        if (!cantoOaAuth(id)) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "该公盘合同已提交OA审核，请刷新数据！");
            return getOperateJSONView(rspMap);
        }

        try {
            gpContractService.toOaGPAuth(reqMap, id, busNo, userInfo, (String) reqqMap.get("companyId"), (String) reqqMap.get("storeIdArray"));
        } catch (Exception e) {
            logger.error("gpContract", "GpContractController", "toOaGPAuth", "", 0, "", "", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "向OA发起提交审核失败");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }

    /**
     * 是否可以提交OA审核
     *
     * @param id 合同ID
     * @return
     */
    private boolean cantoOaAuth(int id) {
        int contractstatus = 0;// 合同状态

        try {
            ResultData<?> resultData = gpContractService.getById(id);
            Map<?, ?> mapTemp = (Map<?, ?>) resultData.getReturnData();

            contractstatus = Integer.parseInt(mapTemp.get("contractStatus").toString());
            if (contractstatus == 10402 || contractstatus == 10403) {    // 审核中，审核通过 不能再提交OA审批
                return false;
            }

        } catch (Exception e) {
            logger.error("gpContract", "GpContractController", "cantoOaAuth", "", null, "", "公盘合同状态判断失败", e);
            return false;
        }
        return true;
    }
    /** 
     * @Title: editCustomerInfoMode 
     * @Description: 客户信息调整弹窗
     */
    @RequestMapping(value = "/editBankInfoMode", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editBankInfoMode(HttpServletRequest request,ModelMap mop){
   	 //请求map
   	 Map<String, Object> reqMap = bindParamToMap(request);
   	 //公盘id
   	 mop.put("gpContractId", reqMap.get("gpContractId").toString());
   	 mop.put("companyName", reqMap.get("companyName").toString());
   	 mop.put("partyB", reqMap.get("partyB").toString());
   	 mop.put("companyId", reqMap.get("companyId").toString());
   	 //银行信息
   	 mop.put("accountNm", reqMap.get("accountNm").toString());
   	 mop.put("bankAccountNm", reqMap.get("bankAccountNm").toString());
   	 mop.put("bankAccount", reqMap.get("bankAccount").toString());
   	 mop.put("accountProvinceNo", reqMap.get("accountProvinceNo").toString());
   	 mop.put("accountProvinceNm", reqMap.get("accountProvinceNm").toString());
   	 mop.put("accountCityNo", reqMap.get("accountCityNo").toString());
   	 mop.put("accountCityNm", reqMap.get("accountCityNm").toString());
   	 try {
         ResultData<?> reback = this.provinceService.queryProvinceList();
         //页面数据
         List<?> provinceList = (List<?>) reback.getReturnData();
         mop.put("provinceList",provinceList);
     } catch (Exception e1) {
         logger.warn("获取省份列表失败");
     }
   	 
   	 //操作人
   	 mop.put("userCode", UserInfoHolder.get().getUserCode());
   	 mop.put("userName", UserInfoHolder.get().getUserName());
   	 mop.put("userIdCreate", UserInfoHolder.get().getUserId());
   	 ModelAndView mv = new ModelAndView( "gpContract/editBankModePopup");
   	 return mv;
    } 
    /** 
     * @Title: saveBankInfoAdjut 
     * @Description: 保存修改后的银行信息
     */
    @RequestMapping(value = "/saveBankInfoAdjut", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> saveBankInfoAdjut(HttpServletRequest request, ModelMap modelMap){
    	ReturnView returnView = new ReturnView();
        ResultData resultData = new ResultData();
        resultData.setFail();
   	 	Map<String, Object> rspMap = new HashMap<String, Object>();
	   	 //请求map
	   	Map<String, Object> reqMap = bindParamToMap(request);
	   	try{
	   		resultData = gpContractService.saveBankInfoAdjut(reqMap);
	   	} catch (Exception e){
	   		resultData.setFail("保存银行信息变更异常!");
            logger.error("CRM", "GpContractController", "saveBankInfoAdjut", reqMap.toString(), null, "", "保存银行信息变更异常", e);
        }
	   	returnView.setOperateReturnType();
        returnView.setReturnCode(resultData.getReturnCode());
        returnView.setReturnMsg(resultData.getReturnMsg());
        returnView.setReturnValue(resultData.getReturnData());

        return returnView;
    } 
    /**
     * 联动业绩归属人--初始化
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "toLinkUserList", method = RequestMethod.GET)
    public String toLinkUserList(HttpServletRequest request, ModelMap mop){
        bindParamToAttrbute(request);
        mop.put("adjutFlag2", 2);
        return "houseLinkage/linkAchieveChange/achieveMentUserListPopup2";
    }
    /**
     * 获取业绩人中心列表
     * @param mop
     * @return
     */
    @RequestMapping(value = "getLinkUserCenter", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getLinkUserCenter(HttpServletRequest request,ModelMap mop){
    	ResultData<?> resultData = new ResultData<>();
    	Map<String, Object> reqMap = bindParamToMap(request);
    	//返回map
    	Map<String, Object> rspMap = new HashMap<String, Object>();
    	try {
    		if(reqMap.containsKey("userId")) {
    			Integer userId = Integer.valueOf(reqMap.get("userId").toString());
    			resultData = this.gpContractService.getLinkUserCenter(userId);
    		}
    	}catch (Exception e){
    		logger.error("CRM","GpContractController","getLinkUser","",UserInfoHolder.getUserId(),"","获取业绩人归属中心列表",e);
    	}
    	rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
    	rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
    	if (null != resultData.getReturnData()) {
    		rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
    	}
    	return getMapView(rspMap);
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
        GpContractDto gpContractDto = new GpContractDto();
        if(reqMap.containsKey("id")){
            gpContractDto.setId(Integer.valueOf(reqMap.get("id").toString()));
            gpContractDto.setSubmitOAUserId(UserInfoHolder.getUserId());
        }
        try {
            //更新
            gpContractService.operateChangeCt(gpContractDto);
        } catch (Exception e) {
            logger.error("gpContract", "GpContractController", "operateChangeCt", "", UserInfoHolder.getUserId(), "", "状态变更失败", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "状态变更失败");
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }

    @RequestMapping(value = "queryDeposit", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView queryDeposit(HttpServletRequest request, ModelMap mop){
        ReturnView jv = new ReturnView();
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> reback = new ResultData<>();
        try {
            reback = gpContractService.queryDeposit(reqMap);
        } catch (Exception e) {
            logger.error("gpContract", "GpContractController", "queryDeposit", "", null, "", "", e);
            reback.setFail();
        }
        BigDecimal deposit = new BigDecimal(reback.getReturnData().toString());
        jv.put("deposit", deposit);
        return jv;
    }

    @RequestMapping(value = "queryFileList", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView queryFileList(HttpServletRequest request, ModelMap mop){
        ReturnView jv = new ReturnView();
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            resultData = gpContractService.queryFileList(reqMap);
        } catch (Exception e) {
            logger.error("gpContract", "GpContractController", "queryFileList", "", null, "", "", e);
        }
        jv.put("Files", resultData);
        return jv;
    }
}
