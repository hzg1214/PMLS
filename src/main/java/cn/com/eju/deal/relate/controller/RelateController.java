package cn.com.eju.deal.relate.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.dto.code.CommonCodeDto;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.base.util.CRMAuthUtil;
import cn.com.eju.deal.common.service.SHBigDistrictService;
import cn.com.eju.deal.company.service.CompanyService;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.oa.OaOperatorDto;
import cn.com.eju.deal.oa.service.OaOperatorService;
import cn.com.eju.deal.store.service.StoreService;
import cn.com.eju.deal.user.service.UserService;

/**   
* 关联门店、客户 Controller
* @author (li_xiaodong)
* @date 2016年3月31日 上午10:19:23
*/
@Controller
@RequestMapping(value = "relate")
public class RelateController extends BaseController
{
    
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "storeService")
    private StoreService storeService;
    
    @Resource(name = "companyService")
    private CompanyService companyService;
    
    @Resource(name = "userService")
    private UserService userService;
    
    @Resource(name = "oaOperatorService")
    private OaOperatorService oaOperatorService;
    
    @Resource(name = "sHBigDistrictService")
    private SHBigDistrictService sHBigDistrictService;
    
    /** 
    * 初始化
    * @param request
    * @param model
    * @return
    */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap mop)
    {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.put("type", reqMap.get("type"));
        UserInfo userInfo = UserInfoHolder.get();
        String cityNo = userInfo.getCityNo();
        if (StringUtil.isNotEmpty(cityNo))
        {
            mop.put("cityNo", cityNo);
            try
            {
                // 城市为上海时加载区域信息
/*                if (cityNo.equals("AAAD4421-21B1-46FD-9DE4-D5A3183CE260"))
                {
                    // 获取默认岗位Id
                    Integer selectedPostId = userInfo.getSelectpostId();
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
                }*/
            }
            catch (Exception e)
            {
                logger.error("Relate", "RelateController", "list", "", UserInfoHolder.getUserId(), "", "查看门店列表失败", e);
            }
        }
        return "relate/storesListPopup";
    }
    
    /** 
    * 初始化
    * @param request
    * @param model
    * @return
    */
    @RequestMapping(value = "/q", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap mop)
    {
        UserInfo userInfo = UserInfoHolder.get();
        String cityNo = userInfo.getCityNo();
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        
        //TODO
        reqMap.put("orderName", "storeId");
        reqMap.put("orderType", "DESC");
        
        if (!"fromContract".equals(reqMap.get("type")))
        {
            //当前登录人的所在城市的门店
            //reqMap.remove("cityNo");
            reqMap.remove("type");
        }
        List<?> contentlist = new ArrayList<>();
        // 城市为上海时加载区域信息
/*        if (cityNo.equals("AAAD4421-21B1-46FD-9DE4-D5A3183CE260"))
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
        try
        {
            PageInfo pageInfo = getPageInfo(request);
            contentlist = storeService.queryList(reqMap, pageInfo);
        }
        catch (Exception e)
        {
            logger.error("Relate", "RelateController", "index", "", UserInfoHolder.getUserId(), "", "查看门店列表失败", e);
        }
        
        mop.addAttribute("contentlist", contentlist);
        
        return "relate/storesListCtxPopup";
    }
    
    /** 
     * 公用的弹出   公司信息列表
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public String companyList(HttpServletRequest request, ModelMap mop)
    {
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.put("type", reqMap.get("type"));
        mop.putAll(reqMap);
        return "relate/companyListPopup";
    }
    
    /** 
    * 客户初始化
    * @param request
    * @param model
    * @return
    */
    @RequestMapping(value = "/company/q", method = RequestMethod.GET)
    public String companyIndex(HttpServletRequest request, ModelMap mop)
    {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        List<?> contentlist = new ArrayList<>();
        try
        {
            UserInfo userInfo = UserInfoHolder.get();
            Integer selectPostId = userInfo.getSelectpostId();
            ResultData<?> result = companyService.queryOrgIdByPostId(selectPostId);
            Map map = (Map)result.getReturnData();
            String selectOrgIdStr = (String)map.get("orgIdStr");
            String selectOrgId = selectOrgIdStr.replaceAll("/", "Z");
            // 下面type标识用于区分【岗位限制】
            // 目前CRM新增合同页面的公司列表信息是有岗位限制的，新房联动报备页面公司列表信息没有岗位限制
            if (reqMap.get("type") != null && !"".equals(reqMap.get("type")) && !"fromLinkage".equals(reqMap.get("type"))) {
                reqMap.put("selectOrgId", selectOrgId);
                reqMap.put("userCreate", userInfo.getUserId());
            }
            
            //报备添加公司时，不限制公司的创建者或维护人为当前用户 added by wang kanlin 2017/09/15
            //公盘添加公司
            int rtnAuth = 1;
            if (reqMap.get("type") != null && !"".equals(reqMap.get("type"))
                    && !"fromLinkage".equals(reqMap.get("type").toString())
                    && !"fromGpContract".equals(reqMap.get("type").toString())
            ){
	          //------------------调用权限接口 start------------------//
	            rtnAuth = CRMAuthUtil.getBtnAuth("/companys", "COMPANY_VIEWALL", userInfo);
            }
            reqMap.put("auth", rtnAuth);
            //releaseCityNoflag为1表示发布城市发生过变更，0表示未变更使用业绩归属城市
            String releaseCityNoflag = reqMap.get("releaseCityNoflag").toString();
            List<String> cityNoList = new ArrayList<>();
            if (reqMap.get("type") != null && "keFuOrder".equals(reqMap.get("type"))) {
                String keFuOrderCity = reqMap.get("keFuOrderCity").toString();
                cityNoList.add(keFuOrderCity);
            }else {
                if(releaseCityNoflag.equals("1")){
                    String[] string = reqMap.get("releaseCityNo").toString().split(",");
                    for (int i = 0; i < string.length; i++) {
                        if(!string[i].equals(userInfo.getCityNo()) ){
                            cityNoList.add(string[i]);
                        }
                    }
                }
                //原业绩城市
                if(releaseCityNoflag.equals("0")){
                    String oldCityNo = reqMap.get("oldCityNo").toString();
                    if(!oldCityNo.equals(userInfo.getCityNo())){
                        cityNoList.add(oldCityNo);
                    }
                }
                cityNoList.add(userInfo.getCityNo());
            }
            reqMap.put("cityNoList", cityNoList);
            //reqMap.put("cityNo", userInfo.getCityNo());
            reqMap.put("userCreate", userInfo.getUserId());
            
            PageInfo pageInfo = getPageInfo(request);
            ResultData<?> reback = companyService.indexOwn(reqMap, pageInfo);
            //页面数据
            contentlist = (List<?>)reback.getReturnData();
            //存放到mop中
            mop.addAttribute("contentlist", contentlist);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        mop.addAttribute("contentlist", contentlist);
        
        return "relate/companyListCtxPopup";
    }
    
    /** 
     * 事业部员工 初始化
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userInit(HttpServletRequest request, ModelMap mop)
    {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        
        return "relate/userListPopup";
    }
    
    /** 
    * 事业部员工 list
    * @param request
    * @param model
    * @return
    */
    @RequestMapping(value = "/user/q", method = RequestMethod.GET)
    public String userList(HttpServletRequest request, ModelMap mop)
    {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        UserInfo userInfo = UserInfoHolder.get();
        String cityNo = userInfo.getCityNo();
        reqMap.put("cityNo", cityNo);
        List<?> contentlist = new ArrayList<>();
        
        try
        {
            PageInfo pageInfo = getPageInfo(request);
            // 查询拓展人员
            ResultData<?> reback = userService.getExpanderUser(reqMap, pageInfo);
            
            //页面数据
            contentlist = (List<?>)reback.getReturnData();
            
            //存放到mop中
            mop.addAttribute("contentlist", contentlist);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        mop.addAttribute("contentlist", contentlist);
        
        return "relate/userListCtxPopup";
    }
    
    /** 
     * 事业部员工 初始化
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/storeuser", method = RequestMethod.GET)
    public String userStoreInit(HttpServletRequest request, ModelMap mop)
    {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.put("storeCenterId", reqMap.get("storeCenterId"));
        return "relate/userListMtPopup";
    }
    
    /** 
     * 门店维护人
     * @param request
     * @param model
     * @return
     */
     @RequestMapping(value = "/storeuser/q", method = RequestMethod.GET)
     public String userListStore(HttpServletRequest request, ModelMap mop)
     {
         //获取请求参数
         Map<String, Object> reqMap = bindParamToMap(request);
         UserInfo userInfo = UserInfoHolder.get();
         String cityNo = userInfo.getCityNo();
         reqMap.put("cityNo", cityNo);
         List<?> contentlist = new ArrayList<>();
         
         try
         {
             PageInfo pageInfo = getPageInfo(request);
             // 查询拓展人员
             ResultData<?> reback = userService.getStoreExpanderUser(reqMap, pageInfo);
             
             //页面数据
             contentlist = (List<?>)reback.getReturnData();
             
             //存放到mop中
             mop.addAttribute("contentlist", contentlist);
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
         
         mop.addAttribute("contentlist", contentlist);
         
         return "relate/userListCtxMtPopup";
     }
    
    /** 
     * 经办人跨区审批，跨区列表
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/oaoperate", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getOaOperate(HttpServletRequest request, ModelMap mop)
    {
    	//返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
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
            logger.error("contract", "ContractController", "show", "", UserInfoHolder.getUserId(), "", "合同查看初始化,查询是否是经办人失败", e);
        }
        
        String busCode = oaOperatorDto.getBusCode();
        
        String busMap = strToList(busCode);        
        
        //存放到mop中
        //mop.addAttribute("busMap", busMap);
        
        //return "oa/oaOperatePopup";
        rspMap.put(Constant.RETURN_DATA_KEY, busMap);
        return getSearchJSONView(rspMap);
    }
    
    /** 
    * 附件String转List
    * @param attachList
    * @param attachStr
    */
    private String strToList(String str)
    {
        //Map<String, String> tempMap = new HashMap<String, String>();
        String rtnCode="";
        if (StringUtil.isNotEmpty(str))
        {
            String[] strArr = str.split("\\|");
            if (null != strArr && 0 != strArr.length)
            {
                
                for (String subArr : strArr)
                {
                	try {
						ResultData<?> resultData = oaOperatorService.getByDicCode(subArr);
						Map<String, String> dto = (Map<String, String>)resultData.getReturnData();
						if(null != dto)
						{
							if(dto.get("dicGroup").equals(UserInfoHolder.get().getCityNo()))
							{
								rtnCode = SystemParam.getWebConfigValue(subArr);
								break;
							}
						}
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	
                    //tempMap.put(SystemParam.getWebConfigValue(subArr), SystemParam.getDicValueByDicCode(subArr));
                    
                }
                
            }
        }
        
        return rtnCode;
    }
}
