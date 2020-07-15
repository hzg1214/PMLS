package cn.com.eju.deal.company.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.dto.code.CommonCodeDto;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.helper.SeqNoHelper;
import cn.com.eju.deal.base.linkage.service.LinkageCityService;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.base.util.CRMAuthUtil;
import cn.com.eju.deal.common.service.CityService;
import cn.com.eju.deal.common.service.CommonService;
import cn.com.eju.deal.common.service.SHBigDistrictService;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.common.util.FileUtils;
import cn.com.eju.deal.company.service.CompanyLogService;
import cn.com.eju.deal.company.service.CompanyService;
import cn.com.eju.deal.company.service.CompanyStoreService;
import cn.com.eju.deal.company.service.GpCompanyStoreService;
import cn.com.eju.deal.contacts.controller.ContactsController;
import cn.com.eju.deal.contacts.service.ContactsService;
import cn.com.eju.deal.contract.service.ContractService;
import cn.com.eju.deal.core.support.*;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.PinyinUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.company.CompanyDto;
import cn.com.eju.deal.dto.company.CompanyLogDto;
import cn.com.eju.deal.dto.company.CompanyStoreDto;
import cn.com.eju.deal.dto.company.GpCompanyStoreDto;
import cn.com.eju.deal.dto.contacts.ContactsDto;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.contract.ContractFileDto;
import cn.com.eju.deal.dto.contract.ContractInfoDto;
import cn.com.eju.deal.dto.gpContract.GpContractDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.dto.student.StudentDto;
import cn.com.eju.deal.follow.service.FollowService;
import cn.com.eju.deal.frameContract.service.FrameContractService;
import cn.com.eju.deal.gpContract.service.GpContractService;
import cn.com.eju.deal.poi.ExcelForCompany;
import cn.com.eju.deal.store.service.StoreService;
import cn.com.eju.deal.user.service.UserService;
import com.alibaba.fastjson.JSON;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 客户Controller
 *
 * @author (li_xiaodong)
 * @date 2016年3月24日 上午10:19:23
 */
@Controller
@RequestMapping(value = "companys")
public class CompanyController extends BaseController {

    private final static String SYS_NAME = SystemCfg.getString("systemName");

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "linkageCityService")
    private LinkageCityService linkageCityService;

    @Resource(name = "companyService")
    private CompanyService companyService;

    @Resource(name = "contactsService")
    private ContactsService contactsService;

    @Resource(name = "followService")
    private FollowService followService;

    @Resource(name = "contractService")
    private ContractService contractService;

    @Resource(name = "storeService")
    private StoreService storeService;

    @Resource(name = "sHBigDistrictService")
    private SHBigDistrictService sHBigDistrictService;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "companyLogService")
    private CompanyLogService companyLogService;

    @Resource(name = "companyStoreService")
    private CompanyStoreService companyStoreService;
    @Resource(name = "gpcompanyStoreService")
    private GpCompanyStoreService gpcompanyStoreService;

    @Resource(name = "cityService")
    private CityService cityService;
    
    @Resource(name = "frameContractService")
    private FrameContractService frameContractService;
    @Resource
    private GpContractService gpContractService;

    @Resource(name="commonService")
    private CommonService commonService;

    /**
     * 初始化
     *
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap mop, HttpServletResponse response) {
        //客户来源
        mop.put("sourceList", SystemParam.getCodeListByKey(DictionaryConstants.DIC_CODE_COMPANY_SOURCE + ""));
        //客户状态
        mop.put("companyStatusList", SystemParam.getCodeListByKey(DictionaryConstants.DIC_CODE_COMPANY_STATUS + ""));
        //合作协议书类型
        mop.put("contractTypeList", SystemParam.getCodeListByKey(DictionaryConstants.CONTRACT_TYPE + ""));

        //保存搜索条件 add by haidan 2017/08/10
        Map<String, Object> map = bindParamToMap(request);
        if (map.containsKey("searchParam") && "1".equals(map.get("searchParam"))) {
            Map<String, Object> searchParamMap = getRememberSearch(request, ComConstants.COMPANY_LIST_SEARCH);
            mop.put("searchParamMap", JSON.toJSON(searchParamMap));
        } else {
            clearSearch(request, response, ComConstants.COMPANY_LIST_SEARCH);
        }
        mop.put("list_search_page", ComConstants.COMPANY_LIST_SEARCH);
        return "company/companyList";
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

        List<?> contentlist = new ArrayList<Object>();
        if(reqMap.containsKey("switchType")){
        	String switchType = reqMap.get("switchType").toString();
        	if(null !=switchType && !"".equals(switchType) &&("1".equals(switchType) || "2".equals(switchType))){
        		mop.addAttribute("switchTypeValue", 1);
        	}
        }
        //获取页面显示数据
        try {
            //保存搜索条件 add by haidan 2017/08/10
            if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.COMPANY_LIST_SEARCH, reqMap);
            }
            UserInfo userInfo = UserInfoHolder.get();
            Integer selectPostId = userInfo.getSelectpostId();
            ResultData<?> result = companyService.queryOrgIdByPostId(selectPostId);
            Map map = (Map) result.getReturnData();
            String selectOrgIdStr = (String) map.get("orgIdStr");
            String selectOrgId = selectOrgIdStr.replaceAll("/", "Z");
            reqMap.put("selectOrgId", selectOrgId);
            reqMap.put("cityNo", userInfo.getCityNo());
            reqMap.put("userCreate", userInfo.getUserId());
            PageInfo pageInfo = getPageInfo(request);

            //------------------调用权限接口 start------------------//
            int rtnAuth = CRMAuthUtil.getBtnAuth("/companys", "COMPANY_VIEWALL", userInfo);
            reqMap.put("auth", rtnAuth);

            ResultData<?> reback = companyService.index(reqMap, pageInfo);

            //页面数据
            contentlist = (List<?>) reback.getReturnData();
        } catch (Exception e) {
            logger.error("Company", "CompanyController", "index", JsonUtil.parseToJson(reqMap), UserInfoHolder.getUserId(), "", "查询公司列表失败", e);
        }

        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        return "company/companyListCtx";
    }

    @RequestMapping(value = "exportCompany", method = RequestMethod.GET)
    @ResponseBody
    public String exportCompany(HttpServletRequest request, HttpServletResponse response) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        try {
            UserInfo userInfo = UserInfoHolder.get();
            Integer selectPostId = userInfo.getSelectpostId();
            ResultData<?> result = companyService.queryOrgIdByPostId(selectPostId);
            Map map = (Map) result.getReturnData();
            String selectOrgIdStr = (String) map.get("orgIdStr");
            String selectOrgId = selectOrgIdStr.replaceAll("/", "Z");
            reqMap.put("selectOrgId", selectOrgId);
            reqMap.put("cityNo", userInfo.getCityNo());
            //PageInfo pageInfo = getPageInfo(request);
            ResultData<?> reback = companyService.index(reqMap, null);

            //页面数据
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            String url = SystemCfg.getString("tempExcelPath") + File.separator + "company";
            File direct = new File(url);
            if (!direct.isDirectory()) {
                direct.mkdir();
            }

            long time = System.currentTimeMillis();
            //删掉历史文件
            FileUtils.deleteFile(direct, time);

            File directory = new File(url + File.separator + time);
            if (!directory.isDirectory()) {
                directory.mkdir();
            }

            ExcelForCompany instance = new ExcelForCompany();
            try {
                instance.downloadSheet(contentlist, new File(url + File.separator + time + File.separator + "公司信息.xlsx"));
            } catch (IOException e) {
                logger.error("下载Excel失败", e);
            }

            return "/data/company/" + time + File.separator + "公司信息.xlsx";
        } catch (Exception e) {
            logger.error("Company", "CompanyController", "index", JsonUtil.parseToJson(reqMap), UserInfoHolder.getUserId(), "", "查询公司列表失败", e);
        }

        return null;
    }

    /**
     * 创建--初始化
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "c", method = RequestMethod.GET)
    public String toAdd(ModelMap mop) {
        UserInfo userInfo = UserInfoHolder.get();
        String cityNo = userInfo.getCityNo();

        //获取页面显示数据
        try {
            if (StringUtil.isNotEmpty(cityNo)) {
                //区域列表
                ResultData<?> resultDistrictList = new ResultData<>();

                resultDistrictList = this.linkageCityService.getDistrictList(cityNo);

                mop.put("districtList", resultDistrictList.getReturnData());
            }
            //存放到mop中
            List<CommonCodeDto> sexList = new ArrayList<CommonCodeDto>();
            List<CommonCodeDto> commonCodeDtos = SystemParam.getCodeListByKey(DictionaryConstants.SEX_TYPE + "");
            for (int i = 0; i < commonCodeDtos.size(); i++) {
                CommonCodeDto dto = new CommonCodeDto();
                BeanUtils.copyProperties(commonCodeDtos.get(i), dto);
                sexList.add(dto);
            }
            mop.put("sexList", sexList);
        } catch (Exception e) {
            logger.error("Company", "CompanyController", "toAdd", "", UserInfoHolder.getUserId(), "", "", e);
        }
        return "company/companyAdd";
    }

    /**
     * 创建--初始化
     *
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "cs/{storeId}", method = RequestMethod.GET)
    public String toAddForStore(ModelMap mop, @PathVariable("storeId") int storeId) {
        //返回map
        ResultData<?> resultData = new ResultData<StudentDto>();
        UserInfo userInfo = UserInfoHolder.get();

        String cityNo = userInfo.getCityNo();

        if (StringUtil.isNotEmpty(cityNo)) {
            //区域列表
            ResultData<?> resultDistrictList = new ResultData<>();
            try {
                resultDistrictList = this.linkageCityService.getDistrictList(cityNo);
            } catch (Exception e) {
                logger.error("Company", "CompanyController", "toAddForStore", "", UserInfoHolder.getUserId(), "", " 创建--初始化", e);
            }
            mop.put("districtList", resultDistrictList.getReturnData());
        }

        try {
            resultData = storeService.getById(storeId);
        } catch (Exception e) {
            logger.error("company", "CompanyController", "toAddForStore", "storeId=" + storeId, UserInfoHolder.getUserId(), "", "", e);
        }

        Map<String, Object> map = new HashMap<>();

        map = (Map<String, Object>) resultData.getReturnData();

        String htmls = "<tr><td style='display:none'><input name='storeIds' id='storeIds" + map.get("storeId") + "' type='hidden' value='" + map.get("storeId") + "'></td><td>" + map.get("name") + "</td><td>" + map.get("districtName") + "</td><td>" + map.get("addressDetail") + "</td><td>" + map.get("dateCreate") + "</td><td><a href='javascript:void(0)' onclick='removeTr(this)'>删除</a></td></tr>";

        mop.put("htmls", htmls);

        //存放到mop中
        List<CommonCodeDto> sexList = new ArrayList<CommonCodeDto>();
        List<CommonCodeDto> commonCodeDtos = SystemParam.getCodeListByKey(DictionaryConstants.SEX_TYPE + "");

        for (int i = 0; i < commonCodeDtos.size(); i++) {
            CommonCodeDto dto = new CommonCodeDto();
            BeanUtils.copyProperties(commonCodeDtos.get(i), dto);
            sexList.add(dto);
        }

        mop.put("sexList", sexList);

        return "company/companyAdd";
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

        //获取页面字段值，转为DTO
        CompanyDto companyDto = new CompanyDto();
        companyDto.setUserCreate(UserInfoHolder.getUserId());

        try {
            setDto(reqMap, companyDto, "create");
        } catch (Exception e1) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            logger.error("Company", "CompanyController", "setDto", "", UserInfoHolder.getUserId(), "", "", e1);
            return getOperateJSONView(rspMap);
        }

        //获取页面"联系人"字段值，转为DTO
        ContactsDto contactsDto = new ContactsDto();
        ContactsController.setDto(reqMap, contactsDto);
        List<ContactsDto> contactList = new ArrayList<ContactsDto>();
        contactList.add(contactsDto);

        companyDto.setContactList(contactList);

        try {
            UserInfo userInfo = UserInfoHolder.get();
            Integer selectPostId = userInfo.getSelectpostId();
            ResultData<?> result = companyService.queryOrgIdByPostId(selectPostId);
            Map reback = (Map) result.getReturnData();
            String selectOrgIdStr = (String) reback.get("orgIdStr");
            companyDto.setSelectOrgIdStr(selectOrgIdStr);

            companyDto.setInputSource(SYS_NAME);
            ResultData<?> resultData = companyService.create(companyDto);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            logger.error("Company", "CompanyController", "create", "", UserInfoHolder.getUserId(), "", "", e);
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
    @RequestMapping(value = "/{u}/{id}", method = RequestMethod.GET)
    public String toEdit(HttpServletRequest request, @PathVariable("u") String u, @PathVariable("id") int id, ModelMap mop) {
        //初始化客户信息
        try {
            initCompanyInfo(id, mop, false);
        } catch (Exception e) {
            logger.error("Company", "CompanyController", "toEdit", "", UserInfoHolder.getUserId(), "", "", e);
        }

        PageInfo pageInfo = getPageInfo(request);

        //初始化查询出联系人列表
        try {
            initContactsInfo(id, mop, pageInfo);
        } catch (Exception e) {
            logger.error("Company", "CompanyController", "toEdit", "", UserInfoHolder.getUserId(), "", "", e);
        }
        //存放到mop中
        List<CommonCodeDto> sexList = new ArrayList<CommonCodeDto>();
        List<CommonCodeDto> commonCodeDtos = SystemParam.getCodeListByKey(DictionaryConstants.SEX_TYPE + "");
        for (int i = 0; i < commonCodeDtos.size(); i++) {
            CommonCodeDto dto = new CommonCodeDto();
            BeanUtils.copyProperties(commonCodeDtos.get(i), dto);
            sexList.add(dto);
        }
        mop.put("sexList", sexList);
        return "company/companyEdit";
    }

    /**
     * 修改
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnView<?, ?> update(HttpServletRequest request, @RequestParam(value = "isform", required = false) String isform, @PathVariable("id") String id) {

        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);

        //check
        String storeIds = (String) reqMap.get("storeIdArray");
        if (StringUtil.isEmpty(storeIds)) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);

            return getOperateJSONView(rspMap);
        }

        reqMap.remove("_method");

        //获取页面字段值，转为DTO
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(Integer.valueOf(id));

        try {
            setDto(reqMap, companyDto, "update");
        } catch (Exception e1) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            logger.error("Company", "CompanyController", "update", "", UserInfoHolder.getUserId(), "", "", e1);
            return getOperateJSONView(rspMap);
        }

        //获取页面"联系人"字段值，转为DTO
        ContactsDto contactsDto = new ContactsDto();
        try {
            ContactsController.setDto(reqMap, contactsDto);
        } catch (Exception e2) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            logger.error("Company", "CompanyController", "update", "", UserInfoHolder.getUserId(), "", "", e2);
            return getOperateJSONView(rspMap);
        }
        List<ContactsDto> contactList = new ArrayList<ContactsDto>();
        contactList.add(contactsDto);

        companyDto.setContactList(contactList);

        try {
            //更新
            companyService.update(companyDto);
        } catch (Exception e) {

            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            logger.error("Company", "CompanyController", "update", "", UserInfoHolder.getUserId(), "", "", e);
            return getOperateJSONView(rspMap);
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
    public String show(HttpServletRequest request, @PathVariable("id") Integer id, ModelMap mop) {
        //初始化客户信息
        try {
            initCompanyInfo(id, mop, true);
        } catch (Exception e) {
            logger.error("Company", "CompanyController", "show", "", UserInfoHolder.getUserId(), "", "", e);
        }

        return "company/companyDetail";
    }

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnView<?, ?> destroy(@PathVariable int id, HttpServletResponse response) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        try {
            //删除
            companyService.delete(id);
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            logger.error("Company", "CompanyController", "destroy", "", UserInfoHolder.getUserId(), "", "", e);
            return getOperateJSONView(rspMap);
        }

        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);

        return getOperateJSONView(rspMap);

    }

    /**
     * 关联跟进初始化
     *
     * @param companyId
     * @param userCreate
     * @return
     */
    @RequestMapping(value = "/follow/{companyId}/{userCreate}", method = RequestMethod.GET)
    public String follow(@PathVariable("companyId") int companyId, @PathVariable("userCreate") int userCreate, ModelMap mop) {
        mop.addAttribute("companyId", companyId);
        mop.addAttribute("userCreate", userCreate);
        return "company/trailList";
    }

    /**
     * 关联跟进
     *
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/follow/q", method = RequestMethod.GET)
    public String followQuerylist(HttpServletRequest request, ModelMap mop) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);

        List<?> contentlist = new ArrayList<>();

        //获取页面显示数据
        try {
            ResultData<?> reback = followService.queryList(reqMap, pageInfo);

            //页面数据
            contentlist = (List<?>) reback.getReturnData();
        } catch (Exception e) {
            logger.error("Company", "CompanyController", "followQuerylist", "", UserInfoHolder.getUserId(), "", "", e);
        }

        //存放到mop中
        mop.addAttribute("contentlist", contentlist);

        return "company/trailListCtx";
    }

    /**
     * 关联门店初始化
     *
     * @param userCreate
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/store/{companyId}/{userCreate}", method = RequestMethod.GET)
    public String store(@PathVariable("companyId") int companyId, @PathVariable("userCreate") int userCreate, ModelMap mop) {
        mop.addAttribute("companyId", companyId);
        mop.addAttribute("userCreate", userCreate);
        try {
            ResultData<?> resultData = companyService.getBriefById(companyId);
            Map<String, Object> map = (Map<String, Object>) resultData.getReturnData();
            if (null != map) {
                mop.addAttribute("companyName", map.get("companyName").toString());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        UserInfo userInfo = UserInfoHolder.get();
        String cityNo = userInfo.getCityNo();
        if (StringUtil.isNotEmpty(cityNo)) {
            // 城市为上海时加载区域信息
/*            if (cityNo.equals("AAAD4421-21B1-46FD-9DE4-D5A3183CE260"))
            {
                // 获取默认岗位Id
                Integer selectedPostId = userInfo.getSelectpostId();
                try
                {
                    // 查询大区信息
                    StringBuffer districts = new StringBuffer("");
                    ResultData<?> resultData = sHBigDistrictService.getBigDistrictBySelectedPostId(selectedPostId);
                    List<Map<?, ?>> bigDistrictList = (List<Map<?, ?>>)resultData.getReturnData();
                    for (Map<?, ?> map : bigDistrictList)
                    {
                        SHBigDistrictDto sHBigDistrictDto = MapToEntityConvertUtil.convert(map, SHBigDistrictDto.class, "");
                        districts.append(sHBigDistrictDto.getDistrictNo()).append(",");
                    }
                    String districtsNo = districts.substring(0, districts.length() - 1);
                    mop.put("districtsNo", districtsNo);
                }
                catch (Exception e)
                {
                    logger.error("Company", "CompanyController", "store", "", UserInfoHolder.getUserId(), "", "查询上海事业部区域信息失败！", e);
                }
            }*/
        }
        return "company/storeList";
    }
    
  

    /**
     * 关联门店
     *
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/store/q", method = RequestMethod.GET)
    public String storeQuerylist(HttpServletRequest request, ModelMap mop) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.put("companyId", request.getParameter("companyId"));
        mop.put("userCreate", request.getParameter("userCreate"));
        List<?> contentlist = new ArrayList<>();
        PageInfo pageInfo = getPageInfo(request);
        try {
            UserInfo userInfo = UserInfoHolder.get();
            String cityNo = userInfo.getCityNo();
            // 城市为上海时加载区域信息
            /*if (cityNo.equals("AAAD4421-21B1-46FD-9DE4-D5A3183CE260"))
            {
                String districtsNoTmp = (String)reqMap.get("districtsNo");
                List<String> districtNoList = new ArrayList<String>();
                String[] districts = districtsNoTmp.split(",");
                for (String str : districts)
                {
                    districtNoList.add(str);
                }
                reqMap.put("districtNoList", districtNoList);
            }*/
            reqMap.put("cityNo", cityNo);
            //页面数据
            contentlist = storeService.querylistByCompanyId(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("Company", "CompanyController", "storeQuerylist", "", UserInfoHolder.getUserId(), "", "", e);
        }
        //存放到mop中
        mop.addAttribute("info", contentlist);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDate = sdf.format(new Date());
        mop.addAttribute("nowDate", nowDate);
        return "company/storeListCtx";
    }

    /**
     * 关联合同初始化
     *
     * @param companyId
     * @param userCreate
     * @return
     */
    @RequestMapping(value = "/contract/{companyId}/{userCreate}", method = RequestMethod.GET)
    public String contract(@PathVariable("companyId") int companyId, @PathVariable("userCreate") int userCreate, ModelMap mop) {
        mop.addAttribute("companyId", companyId);
        mop.addAttribute("userCreate", userCreate);
        return "company/contractList";
    }

    /**
     * 关联合同
     *
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/contract/q", method = RequestMethod.GET)
    public String contractQuerylist(HttpServletRequest request, ModelMap mop) {

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        List<?> contentlist = new ArrayList<>();

        PageInfo pageInfo = getPageInfo(request);
        //获取页面显示数据
        try {

            //------------------调用权限接口 start------------------//
            int rtnAuth = CRMAuthUtil.getBtnAuth("/contract", "CONTRACT_VIEWALL", UserInfoHolder.get());
            reqMap.put("auth", rtnAuth);
            reqMap.put("userCreate", UserInfoHolder.get().getUserId());
            reqMap.put("cityNo", UserInfoHolder.get().getCityNo());

            ResultData<?> reback = contractService.index(reqMap, pageInfo);

            //页面数据
            contentlist = (List<?>) reback.getReturnData();
        } catch (Exception e) {
            logger.error("Company", "CompanyController", "contractQuerylist", "", UserInfoHolder.getUserId(), "", "", e);
        }

        //存放到mop中
        mop.addAttribute("contentlist", contentlist);

        return "company/contractListCtx";
    }

    /**
     * 初始化客户信息
     *
     * @param id
     * @param mop
     * @throws Exception
     */
    private void initCompanyInfo(int id, ModelMap mop, boolean isBrief) {
        //返回map
        ResultData<?> resultData = new ResultData<StudentDto>();

        try {
            if (isBrief) {
                resultData = companyService.getBriefById(id);
            } else {
                resultData = companyService.getById(id);
            }
        } catch (Exception e) {
            logger.error("Company", "CompanyController", "initCompanyInfo", "", null, "", "store toupdate getAreaList failed", e);
        }

        Map<?, ?> resMap = (Map<?, ?>) resultData.getReturnData();

        //存放到mop中
        mop.addAttribute("info", resMap);

        Integer companyId = (Integer) resMap.get("id");
        mop.addAttribute("companyId", companyId);
        Integer userCreate = (Integer) resMap.get("userCreate");
        mop.addAttribute("userCreate", userCreate);
        String fyCompanyId = (String) resMap.get("fangyouCompanyId");
        mop.addAttribute("fyCompanyId", fyCompanyId);
        ResultData<?> reback = new ResultData<>();
        try {
            reback = contractService.getContractsByCompanyId(Integer.valueOf(companyId));
        } catch (Exception e1) {
            logger.error("Company", "CompanyController", "initCompanyInfo", "", null, "", "store toupdate getAreaList failed", e1);
        }
        Integer num = (Integer) reback.getReturnData();
        mop.put("num", num);

        UserInfo userInfo = UserInfoHolder.get();

        String cityNo = userInfo.getCityNo();

        if (StringUtil.isNotEmpty(cityNo)) {
            //区域列表
            ResultData<?> resultDistrictList = new ResultData<>();
            try {
                resultDistrictList = this.linkageCityService.getDistrictList(cityNo);
            } catch (Exception e) {
                logger.error("Company", "CompanyController", "initCompanyInfo", "", null, "", "初始化客户信息", e);
            }
            mop.put("districtList", resultDistrictList.getReturnData());
        }

//        //获取创建人所属大区
//        String selectOrgId = (String) resMap.get("selectOrgIdStr");
//        ResultData<?> result1 = new ResultData<>();
//        try {
//            if (!StringUtil.isEmpty(selectOrgId)) {
//                result1 = userService.queryGNBySelectOrgId(selectOrgId);
//            }
//        } catch (Exception e) {
//            logger.error("Company", "CompanyController", "initCompanyInfo", "", null, "", "获取创建人所属大区", e);
//        }
        mop.put("group", "");

    }

    /**
     * 初始化查询出联系人列表
     *
     * @param id
     * @param mop
     * @param pageInfo
     * @throws Exception
     */
    private void initContactsInfo(Integer id, ModelMap mop, PageInfo pageInfo) {
        //获取请求参数
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("companyId", id);
        reqMap.put("orderName", "dateCreate");
        reqMap.put("orderType", "DESC");

        try {
            //获取页面显示数据
            ResultData<?> reback = contactsService.index(reqMap, pageInfo);

            //页面数据
            List<?> contentlist = (List<?>) reback.getReturnData();

            //存放到mop中
            mop.addAttribute("contactslist", contentlist);
        } catch (Exception e) {
            logger.error("Company", "CompanyController", "initContactsInfo", "", null, "", "初始化查询出联系人列表", e);
        }
    }

    /**
     * 获取页面字段值，转为DTO
     *
     * @param reqMap
     * @param companyDto
     */
    private void setDto(Map<String, Object> reqMap, CompanyDto companyDto, String type) throws Exception {
        //获取页面字段值，转为DTO

        String storeName = (String) reqMap.get("storeName");

        String originalVersions = (String) reqMap.get("originalVersions");

        String cityNo = (String) reqMap.get("cityNo");
        String districtNo = (String) reqMap.get("districtNo");
        String areaNo = (String) reqMap.get("areaNo");

        String address = (String) reqMap.get("address");

        String businessLicenseNo = (String) reqMap.get("businessLicenseNo");
        String legalPerson = (String) reqMap.get("legalPerson");
        String contactNumber = (String) reqMap.get("contactNumber");
        String fangyouCompanyId = (String) reqMap.get("fangyouCompanyId");
        if (StringUtil.isNotEmpty(fangyouCompanyId)) {
            companyDto.setFangyouCompanyId(fangyouCompanyId);
        }
        //客户来源
        String sourceIdStr = (String) reqMap.get("sourceId");
        if (StringUtil.isNotEmpty(sourceIdStr)) {
            companyDto.setSourceId(Integer.valueOf(sourceIdStr));
        }

        //公司规模
        String companyScaleStr = (String) reqMap.get("companyScale");
        if (StringUtil.isNotEmpty(companyScaleStr)) {
            companyDto.setCompanyScale(Integer.valueOf(companyScaleStr));
        }

        //客户状态
        String companyStatusStr = (String) reqMap.get("companyStatus");
        if (StringUtil.isNotEmpty(companyStatusStr)) {
            companyDto.setCompanyStatus(Integer.valueOf(companyStatusStr));
        } else {
            companyDto.setCompanyStatus(DictionaryConstants.DIC_CODE_COMPANY_STATUS_N);
        }

        //账号数量
        String predictAccountCountStr = (String) reqMap.get("predictAccountCount");
        if (StringUtil.isNotEmpty(predictAccountCountStr)) {
            companyDto.setPredictAccountCount(Integer.valueOf(predictAccountCountStr));
        }

        //协议书类型
        String contractTypeStr = (String) reqMap.get("contractType");
        if (StringUtil.isNotEmpty(contractTypeStr)) {
            companyDto.setContractType(Integer.valueOf(contractTypeStr));
        }

        //客户编号
        String companyNo = (String) reqMap.get("companyNo");
        if (StringUtil.isEmpty(companyNo)) {
            companyNo = SeqNoHelper.getInstance().getSeqNoByTypeCode("TYPE_COMPANY");
        }
        companyDto.setCompanyNo(companyNo);

        String companyName = (String) reqMap.get("companyName");
        String companyNamePinyin = (String) reqMap.get("companyNamePinyin");

        if (StringUtil.isNotEmpty(companyName)) {
            companyName = companyName.trim();
            //公司拼音
            PinyinUtil pinyinUtil = new PinyinUtil();
            try {
                companyNamePinyin = pinyinUtil.toPinYin(companyName);

            } catch (BadHanyuPinyinOutputFormatCombination e) {
                logger.warn("company create setDto pinyinUtil.toPinYin failure");
            } catch (Exception e) {
                logger.warn("company create setDto pinyinUtil.toPinYin failure");
            }
        } else {
            companyNamePinyin = null;
        }

        companyDto.setCompanyNamePinyin(companyNamePinyin);

        companyDto.setCompanyName(companyName);
        companyDto.setStoreName(storeName);
        companyDto.setOriginalVersions(originalVersions);

        companyDto.setCityNo(cityNo);
        companyDto.setDistrictNo(districtNo);
        companyDto.setAreaNo(areaNo);
        companyDto.setAddress(address);

        companyDto.setBusinessLicenseNo(businessLicenseNo);
        companyDto.setLegalPerson(legalPerson);
        companyDto.setContactNumber(contactNumber);

        String storeCount = (String) reqMap.get("storeCount");

        if (StringUtil.isNotEmpty(storeCount)) {

            companyDto.setStoreCount(Integer.valueOf(storeCount));
        }

        // 新增的场合
        if ("create".equals(type)) {
            int userCreate = UserInfoHolder.getUserId();
            companyDto.setUserCreate(userCreate);
        }

        //关联门店ids
        String storeIds = (String) reqMap.get("storeIdArray");
        if (StringUtil.isNotEmpty(storeIds)) {

            List<StoreDto> storeList = new ArrayList<StoreDto>();

            StoreDto storeDto = new StoreDto();

            String[] arrays = storeIds.split(",");
            for (int i = 0; i < arrays.length; i++) {
                storeDto = new StoreDto();

                storeDto.setStoreId(Integer.valueOf(arrays[i]));

                storeList.add(storeDto);
            }

            companyDto.setStoreList(storeList);
        }
    }

    /**
     * 初始化--修改公司信息画面跳转
     *
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "ed", method = RequestMethod.GET)
    public String toEditCompany(HttpServletRequest request, ModelMap mop) {

        int companyId = Integer.valueOf(request.getParameter("companyId").toString());
        mop.addAttribute("flag",request.getParameter("flag"));

        UserInfo userInfo = UserInfoHolder.get();
        String cityNo = userInfo.getCityNo();
        String companyAddressCityNoString="-1";
        try {
            ResultData<?> resultData = companyService.getById(companyId);
            Map<?, ?> resMap = (Map<?, ?>) resultData.getReturnData();
            companyAddressCityNoString=resMap.get("cityNo").toString();
            mop.addAttribute("info", resMap);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            //查询isService=1的城市
            ResultData<?> reback = cityService.queryCityListByIsService();
            List<?> citylist = (List<?>) reback.getReturnData();
            mop.addAttribute("citylist", citylist);
            //得到门店区域列表
            //ResultData<?> resultCompanyDistrictList = linkageCityService.getDistrictList(cityNo);
            ResultData<?> resultCompanyDistrictList = linkageCityService.getDistrictList(companyAddressCityNoString);
            mop.addAttribute("companyDistrictList", resultCompanyDistrictList.getReturnData());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "company/companyEditPopup";
    }

    @RequestMapping(value = "toOperationEdit", method = RequestMethod.GET)
    public String toOperationEdit(HttpServletRequest request, ModelMap mop) {

        int companyId = Integer.valueOf(request.getParameter("companyId").toString());

        UserInfo userInfo = UserInfoHolder.get();
        String cityNo = userInfo.getCityNo();
        try {
            ResultData<?> resultData = companyService.getById(companyId);
            Map<?, ?> resMap = (Map<?, ?>) resultData.getReturnData();
            mop.addAttribute("info", resMap);
        } catch (Exception e) {
            logger.error("Company", "CompanyController", "toOperationEdit", "", null, "", "查询公司信息异常", e);
        }

        try {
            ResultData<?> reback = cityService.queryCityListByIsService();
            List<?> citylist = (List<?>) reback.getReturnData();
            mop.addAttribute("citylist", citylist);
            //得到门店区域列表
            ResultData<?> resultCompanyDistrictList = linkageCityService.getDistrictList(cityNo);
            mop.addAttribute("companyDistrictList", resultCompanyDistrictList.getReturnData());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //查询最新合同
        try{
            ResultData<?> reback = contractService.selectNewestContractByCompanyId(companyId);
            mop.addAttribute("contractList", (List<ContractDto>)reback.getReturnData());
        }catch (Exception e) {
            logger.error("storeDetail", "StoreControler", "toOperationUpdate", "", null, "", "查询最新合同", e);
        }

        //查询最新框架合同
        try{
            ResultData<?> reback = frameContractService.selectNewestContractByCompanyId(companyId);
            mop.addAttribute("frameContractList", reback.getReturnData());
        }catch (Exception e) {
            logger.error("storeDetail", "StoreControler", "toOperationUpdate", "", null, "", "查询最新框架合同", e);
        }
        //查询最新公盘合同
        try{
        	ResultData<?> reback = gpContractService.selectNewestGpContractByCompanyId(companyId);
        	mop.addAttribute("gpContractList", reback.getReturnData());
        }catch (Exception e) {
        	logger.error("storeDetail", "StoreControler", "toOperationUpdate", "", null, "", "查询最新公盘合同失败", e);
        }

        return "company/companyOperationEditPopup";
    }

    /* **
      * 验证处理--该公司的所有合同是否有审核中或者审核通过的,如果有，不允许修改公司信息
      * @param request
      * @param mop
      * @return
      * @throws Exception
      **/
    @RequestMapping(value = "checkCompany", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> checkCompanyContract(HttpServletRequest request, ModelMap mop) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        ResultData<?> resultData;
        try {
            resultData = contractService.checkCompanyContract(Integer.valueOf(reqMap.get("companyId").toString()));
            List<?> list = (List<?>) resultData.getReturnData();
            if (!list.isEmpty() || list.size() > 0) {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
                rspMap.put(Constant.RETURN_DATA_KEY, 300);
                rspMap.put(Constant.RETURN_MSG_KEY, "该公司有审批中或审批通过的合同或者公盘合同，公司信息不允许修改！");

                return getSearchJSONView(rspMap);
            }
        } catch (Exception e) {
            logger.error("contract", "ContractController", "checkCompany", "", UserInfoHolder.getUserId(), "", "", e);

            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "修改公司合同信息验证失败");

            return getSearchJSONView(rspMap);
        }

        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        rspMap.put(Constant.RETURN_DATA_KEY, 200);

        return getSearchJSONView(rspMap);
    }

    /**
     * 修改公司信息
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "ec", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnView<?, ?> editCompany(HttpServletRequest request) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);

        String updateItem = "";
        int chb1 = 0;
        int chb2 = 0;
        int chb3 = 0;
        int chb4 = 0;
        int chb5 = 0;
        //获取页面字段值，转为DTO
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(Integer.valueOf(reqMap.get("companyId").toString()));
        if (reqMap.containsKey("chb1")) {
        	String newCompanyName = reqMap.get("newCompanyName").toString().trim();
        	if(!"".equals(newCompanyName)) {
        		String newCompanyNameStr = newCompanyName.replace("(", "（");;
        		newCompanyNameStr = newCompanyNameStr.replace(")", "）");
        		System.out.println(newCompanyNameStr);
        		companyDto.setCompanyName(newCompanyNameStr);
        	}
            updateItem = "公司名称,";
            chb1 = 1;
        }

        if (reqMap.containsKey("chb2")) {
            companyDto.setCityNo(reqMap.get("newCityNo").toString().trim());
            companyDto.setDistrictNo(reqMap.get("newDistrictNo").toString().trim());
            companyDto.setAddress(reqMap.get("newAddress").toString().trim());
            updateItem += "公司注册地址,";
            chb2 = 1;
        }

        if (reqMap.containsKey("chb3")) {
            companyDto.setBusinessLicenseNo(reqMap.get("newBusinessLicenseNo").toString().trim());
            updateItem += "营业执照,";
            chb3 = 1;
        }

        if (reqMap.containsKey("chb4")) {
            companyDto.setLegalPerson(reqMap.get("newLegalPerson").toString().trim());
            updateItem += "法定代表人,";
            chb4 = 1;
        }

        if (reqMap.containsKey("chb5")) {
            companyDto.setContactNumber(reqMap.get("contactNumber").toString().trim());
            updateItem += "法定代表人电话,";
            chb5 = 1;
        }
        updateItem = updateItem.substring(0, updateItem.length() - 1);

        if(reqMap.get("flag") != null){
            updateItem = "运维修改:"+updateItem;
            String tempStr = "";
            if(reqMap.get("contractId") != null){
                tempStr="修改合同、";
            }
            if(reqMap.get("frameContractId") != null){
                tempStr+="修改框架合同、";
            }
            if(reqMap.get("gpContractId") != null){
            	tempStr+="修改公盘合同、";
            }
            if(!"".equals(tempStr)){
                tempStr = tempStr.substring(0, tempStr.length() - 1);
                updateItem += "("+tempStr+")";
            }
        }

        if (reqMap.containsKey("chb3")) {
            try {
                //公司营业执照重复验证

                String strBusinessNo = "";
                ResultData<?> resultList = companyService.checkBusinessLicenseNo(reqMap.get("newBusinessLicenseNo").toString());
                List<Map<String, Object>> listMap = (List<Map<String, Object>>) resultList.getReturnData();
                for (Map<String, Object> map : listMap) {
                    if (!map.get("id").toString().equals(reqMap.get("companyId").toString())) {
                        strBusinessNo += map.get("companyNo").toString() + "、";
                    }
                }

                if (strBusinessNo.length() > 0) {
                    strBusinessNo = strBusinessNo.substring(0, strBusinessNo.length() - 1);

                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    rspMap.put(Constant.RETURN_MSG_KEY, "系统中已存在相同的营业执照号公司信息记录，请联系管理员合并相关公司信息（公司编号：" + strBusinessNo + "）！");
                    return getSearchJSONView(rspMap);
                }

            } catch (Exception e) {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                logger.error("Company", "CompanyController", "editCompany", "", UserInfoHolder.getUserId(), "", "", e);
            }
        }

        try {
            CompanyLogDto companyLogDto = new CompanyLogDto();
            companyLogDto.setCompanyId(Integer.valueOf(reqMap.get("companyId").toString()));
            companyLogDto.setUpdateItem(updateItem);
            companyLogDto.setUpdateUserId(UserInfoHolder.getUserId());
            companyLogDto.setOldCompanyName(reqMap.get("companyName").toString());
            companyLogDto.setOldAddress(reqMap.get("address").toString());
            companyLogDto.setOldLegalPerson(reqMap.get("legalPerson").toString());
            companyLogDto.setOldBusinessLicenseNo(reqMap.get("businessLicenseNo").toString());
            String newCompanyName = reqMap.get("newCompanyName").toString().trim();
            String newCompanyNameStr = newCompanyName.replace("(", "（");;
            newCompanyNameStr = newCompanyNameStr.replace(")", "）");
            companyLogDto.setNewCompanyName(newCompanyNameStr);
            companyLogDto.setNewAddress(reqMap.get("newAddress").toString().trim());
            companyLogDto.setNewLegalPerson(reqMap.get("newLegalPerson").toString());
            companyLogDto.setNewBusinessLicenseNo(reqMap.get("newBusinessLicenseNo").toString().toUpperCase());
            companyLogDto.setChbCompanyName(chb1);
            companyLogDto.setChbAddress(chb2);
            companyLogDto.setChbLegalPerson(chb4);
            companyLogDto.setChbBusinessLicenseNo(chb3);
            companyLogDto.setOldCityNo(reqMap.get("oldCityNo").toString());
            companyLogDto.setOldAreaNo(reqMap.get("oldDistrictNo").toString());
            companyLogDto.setNewCityNo(reqMap.get("newCityNo").toString());
            companyLogDto.setNewAreaNo(reqMap.get("newDistrictNo").toString());

            companyLogDto.setChbContactNumber(chb5);
            companyLogDto.setOldContactNumber(reqMap.get("oldContactNumber").toString());
            companyLogDto.setNewContactNumber(reqMap.get("contactNumber").toString());

            //添加修改日志
            companyLogService.createCompanyLog(companyLogDto);

            // 营业执照
            List<ContractFileDto> fileList = new ArrayList<ContractFileDto>();
            String file = (String) reqMap.get("fileRecordMainIds");
            if (file != null && StringUtil.isNotEmpty(file)) {
                String[] arrays = file.split(",");
                for (int i = 0; i < arrays.length; i++) {
                    ContractFileDto fileDto = new ContractFileDto();
                    fileDto.setFileRecordMainId(arrays[i]);
                    fileList.add(fileDto);
                }
                companyDto.setFileRecordMain(fileList);
            }

            if (null != companyDto.getBusinessLicenseNo()) {
                companyDto.setBusinessLicenseNo(companyDto.getBusinessLicenseNo().toUpperCase());//小写转大写
            }
            //公司信息修改 begin by wang kanlin
            companyDto.setUserUpdate(UserInfoHolder.get().getUserId());
            companyDto.setUserUpdateName(UserInfoHolder.get().getUserName());
            //            companyDto.setUserCreateName(UserInfoHolder.get().getUserName());
            //公司信息修改 end by wang kanlin
            companyService.updateCompany(companyDto);
            logger.error("Company", "CompanyController", "editCompany", "CompanyId="+reqMap.get("companyId").toString(), UserInfoHolder.getUserId(), "", "修改公司信息。",null);

            //修改合同的公司信息
            ResultData<?> resultData = contractService.getContractByCompanyId(Integer.valueOf(reqMap.get("companyId").toString()));
            List<?> list = (List<?>) resultData.getReturnData();
            if (!list.isEmpty() || list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Map<String, Object> map = (Map<String, Object>) list.get(i);
                    //草签和审核未通过的合同，修改公司信息
                    String contractStatus = map.get("contractStatus").toString();
                    if ("10401".equals(contractStatus) || "10404".equals(contractStatus) ||"10403".equals(contractStatus)) {
                        ContractInfoDto contractInfoDto = new ContractInfoDto();
                        ContractDto contract = new ContractDto();
                        contract.setId(Integer.valueOf(map.get("id").toString()));
                        if (reqMap.containsKey("chb1")) {
                        	if(null == reqMap.get("newCompanyName")) {
                        		contract.setPartyB(null);
                        	}else {
                        		contract.setPartyB(newCompanyNameStr);
                        	}
                        }
                        if (reqMap.containsKey("chb2")) {
                            contract.setPartyBcityNo(reqMap.get("newCityNo") == null ? null : reqMap.get("newCityNo").toString());
                            contract.setPartyBdistrictNo(reqMap.get("newDistrictNo") == null ? null : reqMap.get("newDistrictNo").toString());
//                            contract.setPartyBareaNo(reqMap.get("newAreaNo") == null ? null : reqMap.get("newAreaNo").toString());
                            contract.setPartyBAddress(reqMap.get("newAddress") == null ? null : reqMap.get("newAddress").toString());
                        }
                        if (reqMap.containsKey("chb4")) {
                            contract.setLealPerson(reqMap.get("newLegalPerson") == null ? null : reqMap.get("newLegalPerson").toString());
                        }
                        if (reqMap.containsKey("chb3")) {
                            contract.setRegistrId(reqMap.get("newBusinessLicenseNo") == null ? null : reqMap.get("newBusinessLicenseNo").toString().toUpperCase());
                        }
                        contractInfoDto.setContract(contract);

                        if(reqMap.get("flag") == null && ("10401".equals(contractStatus) || "10404".equals(contractStatus))){
                            contractService.updateByContractId(contractInfoDto);
                        }else if(reqMap.get("flag") != null && reqMap.get("contractId") != null){
                            String[] contractIds = request.getParameterValues("contractId");
                            //运营维护
                            if(StringUtils.join(contractIds,",").contains(map.get("id").toString())){
                            	logger.error("Company", "CompanyController", "editCompany", "contractInfoDto="+JsonUtil.parseToJson(contractInfoDto), UserInfoHolder.getUserId(), "", "修改公司的拓展草签合同",null);
                                contractService.updateByContractId(contractInfoDto);
                            }
                        }

                    }
                }
            } else {
                logger.error("Company", "CompanyController", "editCompany", "CompanyId="+reqMap.get("companyId").toString(), UserInfoHolder.getUserId(), "", "无草签、审核不通过合同，不修改合同公司信息",null);
            }
            //修改框架协议的公司信息
            ResultData<?> resultData2 = frameContractService.getFrameContractByCompanyNo(reqMap.get("companyNo").toString());
            List<?> list2 = (List<?>) resultData2.getReturnData();
            if (!list2.isEmpty() || list2.size() > 0) {
                for (int i = 0; i < list2.size(); i++) {
                    Map<String, Object> map = (Map<String, Object>) list2.get(i);
                    //草签和审核未通过的合同，修改公司信息
                    String approveState = map.get("approveState").toString();
                    if ("10401".equals(approveState) || "10404".equals(approveState) || "10403".equals(approveState)) {
                        Map<String, Object> hashMap = new HashMap<>();
                        //框架协议id
                        Integer frameContractId = Integer.valueOf(map.get("id").toString());
                        hashMap.put("id", frameContractId);
                        hashMap.put("userIdUpt", UserInfoHolder.getUserId());
                        if (reqMap.containsKey("chb1")) {
                        	if(null == reqMap.get("newCompanyName")){
                        		 hashMap.put("companyName", null);
                        	} else {
                        		hashMap.put("companyName", newCompanyNameStr);
                        	}
                        }
                        if (reqMap.containsKey("chb2")) {
                            hashMap.put("companyCityNo", reqMap.get("newCityNo") == null ? null : reqMap.get("newCityNo").toString());
                            hashMap.put("companyDistrictNo", reqMap.get("newDistrictNo") == null ? null : reqMap.get("newDistrictNo").toString());
                            hashMap.put("companyAddress", reqMap.get("newAddress") == null ? null : reqMap.get("newAddress").toString());
                        }
                        if (reqMap.containsKey("chb4")) {
                            hashMap.put("lealPerson", reqMap.get("newLegalPerson") == null ? null : reqMap.get("newLegalPerson").toString());
                        }
                        if (reqMap.containsKey("chb3")) {
                            String upperCase = reqMap.get("newBusinessLicenseNo").toString().toUpperCase();
                            hashMap.put("businessLicenseNo", reqMap.get("newBusinessLicenseNo") == null ? null : reqMap.get("newBusinessLicenseNo").toString().toUpperCase());
                        }
                        if (reqMap.containsKey("chb5")) {
                            String contactNumber = reqMap.get("contactNumber").toString();
                            hashMap.put("companyContactTel", reqMap.get("contactNumber") == null ? null : reqMap.get("contactNumber").toString());
                        }

                        if(reqMap.get("flag") == null && ("10401".equals(approveState) || "10404".equals(approveState))){
                            frameContractService.updateCompanyInfo(hashMap);
                        }else if(reqMap.get("flag") != null && reqMap.get("frameContractId") != null){
                            String[] frameContractIds = request.getParameterValues("frameContractId");
                            //运营维护
                            if(StringUtils.join(frameContractIds,",").contains(map.get("id").toString())){
                            	logger.error("Company", "CompanyController", "editCompany", "contractInfoDto="+JsonUtil.parseToJson(hashMap), UserInfoHolder.getUserId(), "", "修改公司的 框架协议",null);
                                frameContractService.updateCompanyInfo(hashMap);
                            }
                        }
                    }
                }
            } else {
              //  logger.error("Company", "CompanyController", "editCompany", "", UserInfoHolder.getUserId(), "", "无草签、审核不通过框架协议，不修改框架协议里的公司信息",null);
            }
            //修改公盘合同的公司信息
            ResultData<?> resultData3 = gpContractService.getGpContractByCompanyId(Integer.valueOf(reqMap.get("companyId").toString()));
            List<?> list3 = (List<?>) resultData3.getReturnData();
            if (!list3.isEmpty() || list3.size() > 0) {
            	for (int i = 0; i < list3.size(); i++) {
            		Map<String, Object> map = (Map<String, Object>) list3.get(i);
            		//草签和审核未通过的公盘合同，修改其里面的公司信息
            		String contractStatus = map.get("contractStatus").toString();
                    if ("10401".equals(contractStatus) || "10404".equals(contractStatus) ||"10403".equals(contractStatus)) {
                    	GpContractDto gpContract = new GpContractDto();
                    	gpContract.setId(Integer.valueOf(map.get("id").toString()));
                    	if (reqMap.containsKey("chb1")) {
                         	if(null == reqMap.get("newCompanyName")) {
                         		gpContract.setPartyB(null);
                         		gpContract.setCompanyName(null);
                         	}else {
                         		gpContract.setPartyB(newCompanyNameStr);
                         		gpContract.setCompanyName(newCompanyNameStr);
                         	}
                        }
                    	if (reqMap.containsKey("chb2")) {
                    		gpContract.setPartyBCityNo(reqMap.get("newCityNo") == null ? null : reqMap.get("newCityNo").toString());
                    		gpContract.setPartyBDistrictNo(reqMap.get("newDistrictNo") == null ? null : reqMap.get("newDistrictNo").toString());
                    		gpContract.setPartyBAddress(reqMap.get("newAddress") == null ? null : reqMap.get("newAddress").toString());
                        }
                    	if (reqMap.containsKey("chb3")) {
                        	gpContract.setRegisterId(reqMap.get("newBusinessLicenseNo") == null ? null : reqMap.get("newBusinessLicenseNo").toString().toUpperCase());
                        }
                        if (reqMap.containsKey("chb4")) {
                        	gpContract.setLegalPerson(reqMap.get("newLegalPerson") == null ? null : reqMap.get("newLegalPerson").toString());
                        }
                        if( null== reqMap.get("flag") && ("10401".equals(contractStatus) || "10404".equals(contractStatus))){
                        	gpContractService.updateByGpContractId(gpContract);
                        }else if(null != reqMap.get("flag") && null != reqMap.get("gpContractId")){
                            String[] contractIds = request.getParameterValues("gpContractId");
                            //运营维护
                            if(StringUtils.join(contractIds,",").contains(map.get("id").toString())){
                            	logger.error("Company", "CompanyController", "editCompany", "contractInfoDto="+JsonUtil.parseToJson(gpContract), UserInfoHolder.getUserId(), "", "修改公司的草签公盘合同",null);
                            	gpContractService.updateByGpContractId(gpContract);
                            }
                        }
                    }
            	}
            }else {
                logger.error("Company", "CompanyController", "editCompany", "CompanyId="+reqMap.get("companyId").toString(), UserInfoHolder.getUserId(), "", "无草签、审核不通过的公盘合同，不修改合同公司信息",null);
            }
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            logger.error("Company", "CompanyController", "editCompany", "", UserInfoHolder.getUserId(), "", "", e);
        }

        return getOperateJSONView(rspMap);
    }

    /**
     * 跳转修改日志画面
     *
     * @return
     */
    @RequestMapping(value = "/log/{companyId}/{userCreate}", method = RequestMethod.GET)
    public ModelAndView toLogList(@PathVariable("companyId") Integer companyId, @PathVariable("userCreate") Integer userCreate, ModelMap mop) {
        mop.addAttribute("companyId", companyId);
        mop.addAttribute("userCreate", userCreate);

        //返回视图
        ModelAndView mv = new ModelAndView("company/logList");

        return mv;
    }

    /**
     * 修改日志查询
     *
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/log/q", method = RequestMethod.GET)
    public ModelAndView logQuerylist(HttpServletRequest request, ModelMap mop) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.put("companyId", request.getParameter("companyId"));

        List<?> contentlist = new ArrayList<>();
        PageInfo pageInfo = getPageInfo(request);
        try {
            //页面数据
            ResultData<?> reback = companyService.queryLoglistByCompanyId(reqMap, pageInfo);

            contentlist = (List<?>) reback.getReturnData();
        } catch (Exception e) {
            logger.error("Company", "CompanyController", "logQuerylist", "", UserInfoHolder.getUserId(), "", "", e);
        }
        //存放到mop中
        mop.addAttribute("logList", contentlist);

        //返回视图
        ModelAndView mv = new ModelAndView("company/logListCtx");
        return mv;
    }

    /**
     * 跳转查看日志画面
     *
     * @return
     */
    @RequestMapping(value = "/lg", method = RequestMethod.GET)
    public ModelAndView toLogDetail(Integer logId, ModelMap mop) {
        try {
            ResultData<?> reultData = companyLogService.getById(logId);
            mop.addAttribute("info", reultData.getReturnData());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //返回视图
        ModelAndView mv = new ModelAndView("company/logDetail");

        return mv;
    }

    /**
     * 关联门店Popup初始化
     *
     * @param companyId
     * @param userCreate
     * @return
     */
    @RequestMapping(value = "/companyStore/{companyId}/{userCreate}", method = RequestMethod.GET)
    public String companyStore(@PathVariable("companyId") int companyId, @PathVariable("userCreate") Integer userCreate, ModelMap mop) {
        mop.addAttribute("companyId", companyId);
        mop.addAttribute("userCreate", userCreate);
        return "company/companyStoreList";
    }

    /**
     * 关联门店Popup列表
     *
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/companyStore/q", method = RequestMethod.GET)
    public String companyStoreQuerylist(HttpServletRequest request, ModelMap mop) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.put("companyId", request.getParameter("companyId"));
        List<?> contentlist = new ArrayList<>();
        PageInfo pageInfo = getPageInfo(request);
        try {
            UserInfo userInfo = UserInfoHolder.get();
            reqMap.put("cityNo", userInfo.getCityNo());
            //页面数据
            contentlist = storeService.queryCompanyStore(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("Company", "CompanyController", "companyStoreQuerylist", "", UserInfoHolder.getUserId(), "", "", e);
        }
        //存放到mop中
        mop.addAttribute("info", contentlist);

        return "company/companyStoreListCtx";
    }

    /**
     * 关联门店添加
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "/companyStore/add", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnView<?, ?> companyStoreAdd(HttpServletRequest request) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);

        try {
            String companyId = reqMap.get("companyId").toString();
            String storeIds = reqMap.get("storeIds").toString();
            if (storeIds.length() > 0) {
                String strStoreIds[] = storeIds.split(",");
                for (int i = 0; i < strStoreIds.length; i++) {
                    Integer storeId = Integer.parseInt(strStoreIds[i].toString());
                    CompanyStoreDto companyStoreDto = new CompanyStoreDto();
                    companyStoreDto.setCompanyId(Integer.parseInt(companyId));
                    companyStoreDto.setStoreId(storeId);
                    companyStoreDto.setDelete(false);
                    companyStoreService.createCompanyStore(companyStoreDto);
                }
            }
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            logger.error("Company", "CompanyController", "companyStoreAdd", "关联门店添加", UserInfoHolder.getUserId(), "", "", e);
        }

        return getOperateJSONView(rspMap);
    }

    @RequestMapping(value = "/detailJson/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String detailJson(HttpServletRequest request, @PathVariable("id") Integer id) {
        ResultData<?> result = new ResultData<>();
        try {
            result = companyService.getBriefById(id);
        } catch (Exception e) {
            result.setFail();
            logger.error("Company", "CompanyController", "detailJson", "", UserInfoHolder.getUserId(), "", "", e);
        }

        return result.toString();
    }
    @RequestMapping(value = "/queryCompanyName", method = RequestMethod.GET)
    @ResponseBody
    public Object queryCompanyName(HttpServletRequest request) {
    	Map<String, Object> reqMap = bindParamToMap(request);
    	String companyName = reqMap.get("companyName").toString();
    	ResultData<?> resultData = new ResultData<>();
    	Object result = null;
    	try {
    		resultData = companyService.queryCompanyName(companyName);
    		if (resultData != null &&  resultData.getReturnCode().equals(ReturnCode.SUCCESS)) {
    			result = resultData.getReturnData();
			}
    	} catch (Exception e) {
    		if(result == null){
    			resultData.setFail();
    		}
    		logger.error("Company", "CompanyController", "queryCompanyName", "", UserInfoHolder.getUserId(), "", "", e);
    	}
    	return result;
    }

    /*************公盘************/
    /**
     * 关联门店Popup初始化
     *
     * @param companyId
     * @param userCreate
     * @return
     */
    @RequestMapping(value = "/gpcompanyStore/{companyId}/{userCreate}", method = RequestMethod.GET)
    public String gpcompanyStore(@PathVariable("companyId") int companyId, @PathVariable("userCreate") Integer userCreate, ModelMap mop) {
        mop.addAttribute("companyId", companyId);
        mop.addAttribute("userCreate", userCreate);
        return "company/gpcompanyStoreList";
    }
    
    /**
     * 关联门店添加
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "/gpcompanyStore/add", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnView<?, ?> gpcompanyStoreAdd(HttpServletRequest request) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);

        try {
            String companyId = reqMap.get("companyId").toString();
            String storeIds = reqMap.get("storeIds").toString();
            if (storeIds.length() > 0) {
                String strStoreIds[] = storeIds.split(",");
                for (int i = 0; i < strStoreIds.length; i++) {
                    Integer storeId = Integer.parseInt(strStoreIds[i].toString());
                    GpCompanyStoreDto companyStoreDto = new  GpCompanyStoreDto();
                    companyStoreDto.setCompanyId(Integer.parseInt(companyId));
                    companyStoreDto.setStoreId(storeId);
                    companyStoreDto.setDelete(false);
                    gpcompanyStoreService.createGpCompanyStore(companyStoreDto);
                }
            }
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        } catch (Exception e) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            logger.error("Company", "CompanyController", "companyStoreAdd", "关联门店添加", UserInfoHolder.getUserId(), "", "", e);
        }

        return getOperateJSONView(rspMap);
    }
    /**
     * 关联门店初始化
     *
     * @param userCreate
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/gpstore/{companyId}/{userCreate}", method = RequestMethod.GET)
    public String qpstore(@PathVariable("companyId") int companyId, @PathVariable("userCreate") int userCreate, ModelMap mop) {
        mop.addAttribute("companyId", companyId);
        mop.addAttribute("userCreate", userCreate);
        try {
            ResultData<?> resultData = companyService.getBriefById(companyId);
            Map<String, Object> map = (Map<String, Object>) resultData.getReturnData();
            if (null != map) {
                mop.addAttribute("companyName", map.get("companyName").toString());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Boolean gpCompanyStore = null;
        //区域 districtList
        String cityNo = UserInfoHolder.get().getCityNo();
        ResultData<?> citySetting = null;
        try {
            citySetting = commonService.getCitySettingByCityNo(cityNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (citySetting != null && citySetting.getReturnData() != null){
            Map<?, ?> csMap = (Map<?, ?>) citySetting.getReturnData();
            gpCompanyStore = Boolean.valueOf(csMap.get("gpCompanyStore").toString());
        }
        mop.addAttribute("gpCompanyStore", gpCompanyStore);
        return "company/gpstoreList";
    }


    /**
     * 关联门店
     *
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/gpstore/q", method = RequestMethod.GET)
    public String gpstoreQuerylist(HttpServletRequest request, ModelMap mop) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.put("companyId", request.getParameter("companyId"));
        mop.put("userCreate", request.getParameter("userCreate"));
        List<?> contentlist = new ArrayList<>();
        PageInfo pageInfo = getPageInfo(request);
        try {
            UserInfo userInfo = UserInfoHolder.get();
            String cityNo = userInfo.getCityNo();
           
            reqMap.put("cityNo", cityNo);
            //页面数据
            contentlist = storeService.querygplistByCompanyId(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("Company", "CompanyController", "storeQuerylist", "", UserInfoHolder.getUserId(), "", "", e);
        }
        //存放到mop中
        mop.addAttribute("info", contentlist);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDate = sdf.format(new Date());
        mop.addAttribute("nowDate", nowDate);
        return "company/gpstoreListCtx";
    }
    
    /**
     * 关联门店Popup列表
     *
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/gpcompanyStore/q", method = RequestMethod.GET)
    public String gpcompanyStoreQuerylist(HttpServletRequest request, ModelMap mop) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.put("companyId", request.getParameter("companyId"));
        List<?> contentlist = new ArrayList<>();
        PageInfo pageInfo = getPageInfo(request);
        try {
            UserInfo userInfo = UserInfoHolder.get();
            reqMap.put("cityNo", userInfo.getCityNo());
            //页面数据
            contentlist = storeService.querygpCompanyStore(reqMap, pageInfo);
        } catch (Exception e) {
            logger.error("Company", "CompanyController", "companyStoreQuerylist", "", UserInfoHolder.getUserId(), "", "", e);
        }
        //存放到mop中
        mop.addAttribute("info", contentlist);

        return "company/gpcompanyStoreListCtx";
    }
}
