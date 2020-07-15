package cn.com.eju.deal.store.controller;

import cn.com.eju.deal.Report.controller.GroupConfig;
import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.dto.code.CommonCodeDto;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.helper.SeqNoHelper;
import cn.com.eju.deal.base.linkage.service.LinkageCityService;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.base.util.UploadFileUtil;
import cn.com.eju.deal.common.service.CityService;
import cn.com.eju.deal.common.service.SHBigDistrictService;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.contacts.service.ContactsService;
import cn.com.eju.deal.contract.service.ContractChangeService;
import cn.com.eju.deal.contract.service.ContractService;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.contacts.ContactsDto;
import cn.com.eju.deal.dto.op.OpOperatorDto;
import cn.com.eju.deal.dto.store.PictureDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.dto.store.StoreMaintainerDto;
import cn.com.eju.deal.gpContract.service.GpContractService;
import cn.com.eju.deal.oa.service.OaOperatorService;
import cn.com.eju.deal.store.service.StoreMaintainerService;
import cn.com.eju.deal.store.service.StoreService;
import cn.com.eju.deal.user.service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
* @Description: 门店controller
* @author 陆海丹
*/
@RestController
@RequestMapping(value = "store")
public class StoreController extends BaseController
{
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    private static final String TYPE_MAINTAINER = "1";

    private static final String TYPE_DECORATION = "2";

    private static final String TYPE_DEPOSIT = "3";

    private static final String TYPE_DECORATIONFEE = "4";

    @Resource(name = "storeService")
    private StoreService storeService;

    @Resource(name = "linkageCityService")
    private LinkageCityService linkageCityService;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "oaOperatorService")
    private OaOperatorService oaOperatorService;

    @Resource(name = "contractService")
    private ContractService contractService;

    @Resource(name = "sHBigDistrictService")
    private SHBigDistrictService sHBigDistrictService;

    @Resource(name = "storeMaintainerService")
    private StoreMaintainerService storeMaintainerService;

    @Resource(name = "contactsService")
    private ContactsService contactsService;

    @Resource(name = "stopContractService")
    private ContractChangeService contactsChangeService;

    @Resource(name = "cityService")
    CityService cityService;

    @Resource
    private GpContractService gpContractService;

    /**
    * @Title: list
    * @Description: 门店列表页面
    * @param request
    * @param mop
    * @return
    */
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response)
    {
        //构建ModelAndView实例，并设置跳转地址
        ModelAndView mv = new ModelAndView("store/storeList");

        UserInfo userInfo = UserInfoHolder.get();
        String cityNo = userInfo.getCityNo();
        if (StringUtil.isNotEmpty(cityNo)){
            //区域列表
            try{
                // 城市为上海时加载区域信息
    /*            if (cityNo.equals("AAAD4421-21B1-46FD-9DE4-D5A3183CE260")) {
                    // 获取默认岗位Id
                    Integer selectedPostId = userInfo.getSelectpostId();
                    // 查询大区信息
                    List<SHBigDistrictDto> bDistrictList = new ArrayList<SHBigDistrictDto>();
                    StringBuffer districts = new StringBuffer("");
                    ResultData<?> resultData = sHBigDistrictService.getBigDistrictBySelectedPostId(selectedPostId);
                    List<Map<?,?>> bigDistrictList = (List<Map<?,?>>)resultData.getReturnData();
                    for(Map<?,?> map : bigDistrictList) {
                        SHBigDistrictDto sHBigDistrictDto = MapToEntityConvertUtil.convert(map, SHBigDistrictDto.class, "");
                        districts.append(sHBigDistrictDto.getDistrictNo()).append(",");
                        bDistrictList.add(sHBigDistrictDto);
                    }
                    mv.addObject("districtList", bDistrictList);
                    String districtsNo = districts.substring(0,districts.length()-1);
                    mv.addObject("districtsNo", districtsNo);
                } else {*/
                    ResultData<?> resultDistrictList = linkageCityService.getDistrictList(cityNo);

                    //将数据放置到ModelAndView对象view中,第二个参数可以是任何java类型
                    mv.addObject("districtList", resultDistrictList.getReturnData());

                    //根据城市获取所属中心
                    Integer typeId = GroupConfig.getLevelTypeIds()[0];
                    Integer typeId2 = GroupConfig.getLevelTypeIds()[1];
                    ResultData<?> resultGroupList = linkageCityService.getGroupList(cityNo, typeId, typeId2);
                    mv.addObject("groupList", resultGroupList.getReturnData());

                try {
                    	//查询isService=1的城市
                    	ResultData<?> reback = cityService.queryCityListByIsService();
                    	List<?> citylist = (List<?>) reback.getReturnData();
                    	mv.addObject("citylist", citylist);
                } catch (Exception e) {
        			logger.error("list", "StoreControler", "list", "", null, "", "查询城市", e);
        		}

                //保存搜索条件 add by haidan 2017/08/11
                Map<String, Object> map = bindParamToMap(request);
                if (map.containsKey("searchParam") && "1".equals(map.get("searchParam"))) {
                    Map<String, Object> searchParamMap = getRememberSearch(request, ComConstants.STORE_LIST_SEARCH);
                    mv.addObject("searchParamMap", JSON.toJSON(searchParamMap));
                } else {
                    clearSearch(request, response, ComConstants.STORE_LIST_SEARCH);
                }
                mv.addObject("list_search_page", ComConstants.STORE_LIST_SEARCH);
            }
//            }
            catch (Exception e)
            {
                logger.error("Store",
                    "StoreController",
                    "list linkageCityService.getDistrictList",
                    "",
                    UserInfoHolder.getUserId(),
                    "",
                    "查看门店列表失败",
                    e);
            }
        }
        //Add 2017/04/07 cning ---->
        //待续签code取得
        mv.addObject("RENEWFLAG_TYPE_DX", DictionaryConstants.RENEWFLAG_TYPE_DX);
        //无需续签code取得
        mv.addObject("NEEDEDRENEW_TYPE_NXQ", DictionaryConstants.NEEDEDRENEW_TYPE_NXQ);
        //Add 2017/04/07 cning <----
        return mv;

    }

    /**
    * @Title: querylist
    * @Description: 门店列表
    * @param request
    * @param mop
    * @return
    */
    @RequestMapping(value = "q", method = RequestMethod.GET)
    public ModelAndView querylist(HttpServletRequest request, ModelMap mop, HttpServletResponse response)
    {
        UserInfo userInfo = UserInfoHolder.get();
        String cityNo = userInfo.getCityNo();
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        //页面数据
        try
        {
            //保存搜索条件 add by haidan 2017/08/11
            if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.STORE_LIST_SEARCH, reqMap);
            }
            // 城市为上海时加载区域信息 
       /*     if (cityNo.equals("AAAD4421-21B1-46FD-9DE4-D5A3183CE260")) {
                String districtsNoTmp = (String)reqMap.get("districtsNo");
                List<String> districtNoList = new ArrayList<String>();
                String[] districts = districtsNoTmp.split(",");
                for(String str : districts) {
                    districtNoList.add(str);
                }
                reqMap.put("districtNoList", districtNoList);
            }*/

        	 //Add 2017/04/07 cning ---->
            //待续签Flag取得
            reqMap.put("renewFlag", DictionaryConstants.RENEWFLAG_TYPE_DX);
            //Add 2017/04/07 cning <----
            List<?> contentlist = storeService.queryList(reqMap, pageInfo);
            //存放到mop中
            mop.addAttribute("contentlist", contentlist);
        }
        catch (Exception e1)
        {
            logger.error("Store", "StoreController", "queryList", "", UserInfoHolder.getUserId(), "", "查看门店列表失败", e1);
        }

        //查询是否是经办人
        ResultData<?> backResult = new ResultData<>();
        try
        {
            backResult = oaOperatorService.getByUserCode(UserInfoHolder.get().getUserCode());
        }
        catch (Exception e)
        {
            logger.error("contract",
                "StoreController",
                "querylist",
                "",
                UserInfoHolder.getUserId(),
                "",
                "门店查询, 查询经办人失败",
                e);
        }

        //存放到mop中
        mop.addAttribute("oaOperator", backResult.getReturnData());
        //获取当前用户及其下属用户Id集合, 用于页面权限过滤
        //List<Integer> idsList = storeService.getUserIdList();

        //存放到mop中
        //mop.addAttribute("userIdList", idsList);

        //Add 2017/04/07 cning ---->
        //待续签code取得
        mop.put("RENEWFLAG_TYPE_DX", DictionaryConstants.RENEWFLAG_TYPE_DX);
        //无需续签code取得
        mop.put("NEEDEDRENEW_TYPE_NXQ", DictionaryConstants.NEEDEDRENEW_TYPE_NXQ);
        //Add 2017/04/07 cning <----

        //返回视图
        ModelAndView mv = new ModelAndView("store/storeListCtx");

        return mv;

    }

    /**
     * @Title: queryRelatelist
     * @Description: 查询关联门店列表
     * @param request
     * @param mop
     * @return
     * @
     */
    @RequestMapping(value = "qr", method = RequestMethod.GET)
    public ReturnView<?, ?> queryRelatelist(HttpServletRequest request, ModelMap mop)

    {
        Map<String, Object> reqMap = bindParamToMap(request);
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try
        {
//            UserInfo userInfo = UserInfoHolder.get();
//            String userAcCityNo = userInfo.getCityNo();
//            reqMap.put("userAcCityNo", userAcCityNo);
            ResultData<?> resultData = storeService.queryRelatelist(reqMap);

            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        }
        catch (Exception e)
        {
            logger.error("Store", "StoreController", "update", "", UserInfoHolder.getUserId(), "", "查询关联门店列表失败", e);

            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);

            rspMap.put(Constant.RETURN_MSG_KEY, "查询关联门店列表失败");
        }
        return getMapView(rspMap);
    }

    //Add By NingChao 2017/04/07 Start
      /**
       * @Title: queryRelatelist
       * @Description: 查询关联门店列表
       * @param request
       * @param mop
       * @return
       * @
       */
      @RequestMapping(value = "qrRenew", method = RequestMethod.GET)
      public ReturnView<?, ?> queryRelatelistByRenew(HttpServletRequest request, ModelMap mop)

      {
          //获取请求参数
          Map<String, Object> reqMap = bindParamToMap(request);
          //返回map
          Map<String, Object> rspMap = new HashMap<String, Object>();
          try
          {
              //页面数据
              ResultData<?> resultData = storeService.queryRelatelistByRenew(reqMap);

              rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
              rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
          }
          catch (Exception e)
          {
              logger.error("Store", "StoreController", "update", "", UserInfoHolder.getUserId(), "", "查询关联门店列表失败", e);

              rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);

              rspMap.put(Constant.RETURN_MSG_KEY, "查询关联门店列表失败");
          }

          return getSearchJSONView(rspMap);
      }
    //Add By NingChao 2017/04/07 End

      /**
       * 续签 查询所属中心
       * @param id
       * @return
       * @throws Exception
       */
       @RequestMapping(value = "/rc", method = RequestMethod.GET)
       @ResponseBody
       public ReturnView<?, ?> renewCenter(HttpServletRequest request)
       {
           //返回map
           Map<String, Object> rspMap = new HashMap<String, Object>();
           //判断门店的资质等级 2019-04-04改都可以续签
            /*try{
                Map<String, Object> reqMap = bindParamToMap(request);
                ResultData<?> resultData = storeService.checkGrade(reqMap);
                if(resultData.getReturnCode().equals(ReturnCode.FAILURE)){
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
                    return getOperateJSONView(rspMap);
                }

            }catch (Exception e){
                logger.error("contract", "ContractController", "renewCenter", "", UserInfoHolder.getUserId(), "", "", e);

                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);

                rspMap.put(Constant.RETURN_MSG_KEY, "续签 判断门店资质等级失败");

                return getOperateJSONView(rspMap);
            }*/
           try
           {
        	   //取得所属中心
               ResultData<?> resultDataCenter = contractService.getCenterListByUserId(UserInfoHolder.getUserId());
               List<?> list = (List<?>)resultDataCenter.getReturnData();
               if(list.size()<=0)
               {
            	   rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                   rspMap.put(Constant.RETURN_MSG_KEY, "登录用户没有所属中心不能新签合同！");
                   return getOperateJSONView(rspMap);
               }
               if(list.size() == 1)
               {
            		/*Map<String,Object> map = (Map<String,Object>)list.get(0);
	               	if(null != map){*/
	               		rspMap.put(Constant.RETURN_DATA_KEY,resultDataCenter.getReturnData());
	               	//}
               }else{
            	   rspMap.put(Constant.RETURN_DATA_KEY, -1);  //表示查询出多个所属中心
               }

           }
           catch (Exception e)
           {
               logger.error("contract", "ContractController", "renewCenter", "", UserInfoHolder.getUserId(), "", "", e);

               rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);

               rspMap.put(Constant.RETURN_MSG_KEY, "续签 查询所属中心 操作失败");

               return getOperateJSONView(rspMap);
           }

           rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
           return getOperateJSONView(rspMap);

       }


    /**
    * @Title: showdetail
    * @Description: 查看门店详情
    * @param id
    * @param mop
    * @return
    * @
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView showdetail(@PathVariable("id") Integer id, ModelMap mop)

    {
        try
        {
//            ResultData<?> resultData = storeService.getById(id);
            ResultData<?> resultData = storeService.getbriefById(id);
           /* //取得所属中心
            ResultData<?> resultDataCenter = contractService.getCenterListByUserId(UserInfoHolder.getUserId());
            List<?> list = (List<?>)resultDataCenter.getReturnData();
            if(list.size() == 1)
            {
            	Map<String,Object> map = (Map<String,Object>)list.get(0);
            	if(null != map){
            		mop.addAttribute("centerId", map.get("exchangeCenterId").toString());
            	}
            }
            //存放到mop中
            mop.addAttribute("centerCount", list.size());*/
            mop.addAttribute("storeDetail", resultData.getReturnData());
        }
        catch (Exception e)
        {
            logger.error("Store", "StoreController", "showdetail", "", UserInfoHolder.getUserId(), "", "查看门店详情失败", e);

        }

        //查询是否是经办人
        try
        {
            ResultData<?> backResult = oaOperatorService.getByUserCode(UserInfoHolder.get().getUserCode());

            //存放到mop中
            mop.addAttribute("oaOperator", backResult.getReturnData());
        }
        catch (Exception e)
        {
            logger.error("contract",
                "StoreController",
                "querylist",
                "",
                UserInfoHolder.getUserId(),
                "",
                "门店查询, 查询经办人失败",
                e);
        }
        //Add By GuoPengFei 2017/05/25 合规性 start
        try
        {
            ResultData<?> resultData = contractService.getContractByStoreId(id);

            //存放到mop中
            mop.addAttribute("storecontract", resultData.getReturnData());
        }
        catch (Exception e)
        {
            logger.error("Store", "StoreController", "showdetail", "", UserInfoHolder.getUserId(), "", "根据门店ID取得该门店审核通过的B和A2B合同信息失败", e);

        }

        try
        {
            ResultData<?> resultData = contactsChangeService.getContractChangeByStoreId(id);
            if(resultData.getTotalCount().equals("1") )
            {
                //存放到mop中
                mop.addAttribute("contractchange", "1");

            }
            else
            {
                //存放到mop中
                mop.addAttribute("contractchange", "0");

            }

        }
        catch (Exception e)
        {
            logger.error("Store", "StoreController", "showdetail", "", UserInfoHolder.getUserId(), "", "根据门店ID取得该门店合同变更信息", e);

        }
        //Add By GuoPengFei 2017/05/25 合规性 end       
        mop.addAttribute("storeId", id);

        //Add 2017/04/07 cning 门店详细传递合同ID----->
        //待续签code取得
        mop.addAttribute("RENEWFLAG_TYPE_DX", DictionaryConstants.RENEWFLAG_TYPE_DX);
        //Add 2017/04/07 cning 门店详细传递合同ID<-----

        //返回视图
        ModelAndView mv = new ModelAndView("store/storeDetail");

        return mv;
    }

    /**
    * @Title: toAdd
    * @Description: 门店新增页面
    * @return
    * @
    */
    @RequestMapping(value = "c", method = RequestMethod.GET)
    public ModelAndView toCreate(HttpServletRequest request, ModelMap mop)

    {
        UserInfo userInfo = UserInfoHolder.get();
        String cityNo = userInfo.getCityNo();
        if (StringUtil.isNotEmpty(cityNo))
        {
            //区域列表
            ResultData<?> resultDistrictList = new ResultData<>();

            try
            {
                // 城市为上海时加载区域信息
        /*        if (cityNo.equals("AAAD4421-21B1-46FD-9DE4-D5A3183CE260")) {
                    // 获取默认岗位Id
                    Integer selectedPostId = userInfo.getSelectpostId();
                    // 查询大区信息
                    resultDistrictList = sHBigDistrictService.getBigDistrictBySelectedPostId(selectedPostId);
                } else {*/
                    resultDistrictList = linkageCityService.getDistrictList(cityNo);
//                }
            }
            catch (Exception e)
            {
                logger.error("contract",
                    "StoreController",
                    "toCreate",
                    "",
                    UserInfoHolder.getUserId(),
                    "",
                    "store tocreate getDistrictList or getBigDistrictBySelectedPostId failed",
                    e);
            }
            mop.put("districtList", resultDistrictList.getReturnData());
        }

        //Add 2017/04/07 cning 门店详细传递合同ID----->
        //待续签code取得
        mop.addAttribute("RENEWFLAG_TYPE_DX", DictionaryConstants.RENEWFLAG_TYPE_DX);
      //Add 2017/04/07 cning 门店详细传递合同ID<-----
        //返回视图
        ModelAndView mv = new ModelAndView("store/storeAdd");

        return mv;

    }

    /**
    * @Title: create
    * @Description: 新增门店
    * @param request
    * @param modelMap
    * @return
    * @
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ReturnView<?, ?> create(HttpServletRequest request, ModelMap modelMap)
    {
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try
        {
            ResultData<?> resultData = storeService.create(reqMap);
            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        }
        catch (Exception e)
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            logger.error("store", "StoreController", "create", "", UserInfoHolder.getUserId(), "", "", e);
        }
        return getOperateJSONView(rspMap);
    }

    /**
    * @Title: toupdate
    * @Description: 门店更新页面
    * @param u ？？？
    * @param id 门店编号
    * @param mop
    * @return
    * @
    */

    //Mod By WangLei 2017/04/07 Start
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "u/{id}", method = RequestMethod.GET)
    public ModelAndView toupdate(HttpServletRequest request, @PathVariable("id") int id,
        ModelMap mop)
    /*
    @RequestMapping(value = "/{u}/{id}", method = RequestMethod.GET)
    public ModelAndView toupdate(HttpServletRequest request, @PathVariable("u") String u, @PathVariable("id") int id,
        ModelMap mop)
    */

    //Mod By WangLei 2017/04/07 End

    {
        Map<String, Object> map = new HashMap<>();
        //返回map
        ResultData<?> resultData = new ResultData<>();
        try
        {
            resultData = storeService.getById(id);
        }
        catch (Exception e)
        {
            logger.warn("store toupdate getStoreDetail failed");
        }

        map = (Map<String, Object>)resultData.getReturnData();
        Object cityNo = map.get("cityNo");
        //门店详情存放到mop中
        mop.addAttribute("storeDetail", map);
        if (null != cityNo)
        {
        	UserInfo userInfo = UserInfoHolder.get();
            //区域列表
            ResultData<?> resultDistrictList = new ResultData<>();
            try{
            	// 城市为上海时加载区域信息
      /*          if (cityNo.equals("AAAD4421-21B1-46FD-9DE4-D5A3183CE260")) {
                    // 获取默认岗位Id
                    Integer selectedPostId = userInfo.getSelectpostId();
                    // 查询大区信息
                    resultDistrictList = sHBigDistrictService.getBigDistrictBySelectedPostId(selectedPostId);
                } else {*/
                    resultDistrictList = linkageCityService.getDistrictList((String)cityNo);
                    //获取品牌列表
                    ResultData<?> rd = new ResultData<>();
                    rd = linkageCityService.getBrandList(cityNo.toString());
                    mop.put("chainBrandList", rd.getReturnData());

//                }
                mop.put("districtList", resultDistrictList.getReturnData());
            } catch (Exception e) {
            	logger.error("contract",
                        "StoreController",
                        "toupdate",
                        "",
                        UserInfoHolder.getUserId(),
                        "",
                        "store toupdate getDistrictList or getBigDistrictBySelectedPostId failed",
                        e);
            }

        }
        //查询是否是经办人
        ResultData<?> backResult = new ResultData<>();
        try
        {
            backResult = oaOperatorService.getByUserCode(UserInfoHolder.get().getUserCode());
        }
        catch (Exception e)
        {
            logger.error("contract",
                "StoreController",
                "querylist",
                "",
                UserInfoHolder.getUserId(),
                "",
                "门店查询, 查询经办人失败",
                e);
        }
        //编辑是否限制
        try {
        	Boolean banFlag = storeService.getEditBanFlag(id);
        	mop.addAttribute("banFlag", banFlag);
		} catch (Exception e) {
			logger.error("contract",
                    "StoreController",
                    "toupdate",
                    "",
                    UserInfoHolder.getUserId(),
                    "",
                    "store toupdate getEditBanFlag  failed",
                    e);
		}

        try {
            ResultData<?> data = storeService.checkBToAAlert(id);
            if("200".equals(data.getReturnCode()) && "1".equals(data.getReturnData().toString())) {
                mop.addAttribute("btoAAlertFalg", "1");
            }else {
                mop.addAttribute("btoAAlertFalg", "0");
            }
        }catch(Exception e) {
            logger.error("contract",
                    "StoreController",
                    "checkBToAAlert",
                    "id="+id,
                    UserInfoHolder.getUserId(),
                    "",
                    "检查是否乙转甲提醒失败",
                    e);
        }

        //存放到mop中
        mop.addAttribute("oaOperator", backResult.getReturnData());

        //枚举列表
        List<CommonCodeDto> mainBusinessList = SystemParam.getCodeListByKey("189");//主营业务
        List<CommonCodeDto> transactionModeList = SystemParam.getCodeListByKey("190");//交易方式
       // List<CommonCodeDto> natureList = SystemParam.getCodeListByKey("193");//营业执照性质
        List<CommonCodeDto> personList = SystemParam.getCodeListByKey("194");//经纪人数
        List<CommonCodeDto> neededRenewList = SystemParam.getCodeListByKey("185");//续签

        mop.addAttribute("mainBusinessList", JSON.parseArray(JSON.toJSONString(mainBusinessList)));
        mop.addAttribute("transactionModeList",JSON.parseArray(JsonUtil.parseToJson(transactionModeList)));
        //mop.addAttribute("natureList", JsonUtil.parseToJson(natureList));
        mop.addAttribute("storePersonList", JSON.parseArray(JsonUtil.parseToJson(personList)));
        mop.addAttribute("neededRenewList",JSON.parseArray(JsonUtil.parseToJson(neededRenewList)));
        //门店经营场所类型
        mop.put("businessPlaceTypeList", SystemParam.getCodeListByKey(DictionaryConstants.STOREMANAGEMENTPLACE_TYPE));
        //门店规模
        mop.put("storeSizeScaleList", SystemParam.getCodeListByKey(DictionaryConstants.STORESIZESCALE_TYPE));

        //返回视图
        ModelAndView mv = new ModelAndView("store/storeEdit");

        return mv;

    }

    /**
    * @Title: update
    * @Description: 门店信息更新
    * @param request
    * @param isform
    * @param id 门店编号
    * @return
    * @
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ReturnView<?, ?> update(HttpServletRequest request,
        @RequestParam(value = "isform", required = false) String isform, @PathVariable("id") String id)

    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.remove("_method");
        try
        {
            //上传
            List<PictureDto> pictureDtoList = upload(request);
            reqMap.put("storePicListJson",JSON.toJSONString(pictureDtoList));
            //更新
            storeService.update(reqMap);
        }
        catch (Exception e)
        {
            logger.error("Store", "StoreController", "update", "", UserInfoHolder.getUserId(), "", "更新门店失败", e);

            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);

            rspMap.put(Constant.RETURN_MSG_KEY, "更新门店失败");
        }
        return getOperateJSONView(rspMap);
    }

    /**
     * 上传图片
     * @param request
     * @return
     */
    private List<PictureDto> upload(HttpServletRequest request){
        List<PictureDto> pictureDtoList = new ArrayList<PictureDto>();
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver =
                new CommonsMultipartResolver(request.getSession().getServletContext());

        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();

            while (iter.hasNext()) {
                MultipartFile importFile = multiRequest.getFile(iter.next());
                //文件名
                String fileName = importFile.getOriginalFilename();

               // importFile.getInputStream();

                //MultipartFile转File
                CommonsMultipartFile cf = (CommonsMultipartFile)importFile;
                DiskFileItem fi = (DiskFileItem)cf.getFileItem();
                File temp = fi.getStoreLocation();
                //上传
                PictureDto pictureDto =  uploadFileImage("",fileName,importFile);
                if(pictureDto!=null){
                    pictureDtoList.add(pictureDto);
                }
            }
        }
        return pictureDtoList;
    }

    /**
     * 上传文件到服务器
     * @param URL
     * @param fileName
     * @param file
     * @return
     */
    private PictureDto uploadFileImage(String URL,String fileName,MultipartFile multipartFile){
        //上传图片
        URL = "http://pic.ehousechina.com/Service/UpLoadFangYouImg";

        PictureDto pictureDto = null;

        UploadFileUtil uploadFileUtil = new UploadFileUtil();
        try {
            String result = uploadFileUtil.uploadFile(URL,fileName,multipartFile.getInputStream());
            JSONObject resultJson = JSON.parseObject(result);
            JSONObject dataJson = resultJson.getJSONObject("TData");

            pictureDto = new PictureDto();
            pictureDto.setBigPictureUrl(dataJson.getString("picUrl_500"));
            pictureDto.setMiddlePictureUrl(dataJson.getString("picUrl_50_percent"));
            pictureDto.setSmallPictureUrl(dataJson.getString("picUrl_20_percent"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pictureDto;
    }



    /**
    * @Title: destroy
    * @Description: 删除门店
    * @param id
    * @param response
    * @return
    * @
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ReturnView<?, ?> destroy(@PathVariable int id, HttpServletResponse response)

    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        try
        {
            //删除
            storeService.delete(id);
        }
        catch (Exception e)
        {
            logger.error("Store", "StoreController", "destroy", "", UserInfoHolder.getUserId(), "", "删除门店失败", e);

            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);

            rspMap.put(Constant.RETURN_MSG_KEY, "删除门店失败");

        }

        return getOperateJSONView(rspMap);
    }

    /**
     * 关联合同初始化
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/contract/{storeId}", method = RequestMethod.GET)
    public ModelAndView toSignContract(@PathVariable("storeId") Integer storeId, ModelMap mop) throws Exception
    {
        mop.addAttribute("storeId", storeId);
     // Add By guopengfei 签约历史页面 续签要否按钮追加 start
        ResultData<?> storeData = new ResultData<>();
        storeData = storeService.getById(storeId);
        mop.put("storedata", storeData.getReturnData());
//Add By guopengfei 签约历史页面 续签要否按钮追加 end            


        //返回视图
        ModelAndView mv = new ModelAndView("store/signContractList");

        return mv;
    }

    /**
     *  根据门店Id查询签约的合同
     * @param storeId 门店Id
     * @return
     */
    @RequestMapping(value = "/contract/q", method = RequestMethod.GET)
    public ModelAndView getSignHisByStoreId(HttpServletRequest request, ModelMap mop)
    {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.addAllAttributes(reqMap);
        ResultData<?> resultData = new ResultData<>();
        PageInfo pageInfo = getPageInfo(request);
        try
        {
            resultData = contractService.getSignHisByStoreId(reqMap, pageInfo);
            resultData.setSuccess();
            mop.put("contentlist", resultData.getReturnData());
        }
        catch (Exception e)
        {
            logger.warn("getSignHisByStoreId failed");
        }

        //返回视图
        ModelAndView mv = new ModelAndView("store/signContractListCtx");

        return mv;
    }

    /**
     *  根据门店Id查询收款
     * @param storeId 门店Id
     * @return
     */
    @RequestMapping(value = "/receive/{storeId}", method = RequestMethod.GET)
    public ModelAndView getReceivedMoneyByStoreId(@PathVariable("storeId") Integer storeId, ModelMap mop)
    {
        try
        {
            ResultData<?> resultData = storeService.getReceivedMoneyByStoreId(storeId);
            resultData.setSuccess();
            mop.put("contentlist", resultData.getReturnData());
        }
        catch (Exception e)
        {
            logger.warn("getReceivedMoneyByStoreId failed");
        }

        mop.addAttribute("storeId", storeId);
        //返回视图
        ModelAndView mv = new ModelAndView("store/storeReceivedMoney");

        return mv;
    }

    /**
     * 跟进页面
     * @return
     */
    @RequestMapping(value = "/follow/{storeId}", method = RequestMethod.GET)
    public ModelAndView toStoreTrailList(@PathVariable("storeId") Integer storeId, ModelMap mop)
    {
        mop.addAttribute("storeId", storeId);

        //返回视图
        ModelAndView mv = new ModelAndView("store/storeTrailList");

        return mv;
    }

    /**
     * @Title: update
     * @Description: 更新门店维护人
     * @param request
     * @param isform
     * @param id 门店编号
     * @return
     * @
     */
    @RequestMapping(value = "maintainer/{storeId}", method = RequestMethod.PUT)
    public ReturnView<?, ?> updateMaintainer(HttpServletRequest request, @PathVariable("storeId") String storeId)

    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        try
        {
            //更新
            storeService.updateStoreRelateInfo(reqMap, storeId, TYPE_MAINTAINER);
        }
        catch (Exception e)
        {
            logger.error("store", "StoreController", "updateMaintainer", storeId, UserInfoHolder.getUserId(), "", "", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }

    /**
     * 设置装修状态页面
     * @return
     */
    @RequestMapping(value = "toDecoration/{storeId}", method = RequestMethod.GET)
    public ModelAndView toDecorationState(@PathVariable("storeId") Integer storeId, ModelMap mop)
    {
        try
        {
            mop.addAttribute("storeId", storeId);
            ResultData<?> returnData = storeService.getById(storeId);
            mop.put("store", returnData.getReturnData());
            // 装修状态
            mop.put("dcrtStateList", SystemParam.getCodeListByKey(DictionaryConstants.DECORATION_STATE + ""));
        }
        catch (Exception e)
        {
            logger.error("store",
                "StoreController",
                "toDecorationState",
                storeId + "",
                UserInfoHolder.getUserId(),
                "",
                "",
                e);
        }

        //返回视图
        ModelAndView mv = new ModelAndView("store/storeDecorationState");

        return mv;
    }

    /**
     * @Title: update
     * @Description: 更新门店装修状况
     * @param request
     * @param isform
     * @param id 门店编号
     * @return
     * @
     */
    @RequestMapping(value = "decorationUpt/{storeId}", method = RequestMethod.PUT)
    public ReturnView<?, ?> updateDecoration(HttpServletRequest request, @PathVariable("storeId") String storeId)

    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        try
        {
            //更新
            storeService.updateStoreRelateInfo(reqMap, storeId, TYPE_DECORATION);
        }
        catch (Exception e)
        {
            logger.error("store", "StoreController", "updateDecoration", storeId, UserInfoHolder.getUserId(), "", "", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }

    /**
     * 设置装修状态页面
     * @return
     */
    @RequestMapping(value = "toDeposit/{storeId}", method = RequestMethod.GET)
    public ModelAndView toDeposit(@PathVariable("storeId") Integer storeId, ModelMap mop)
    {
        try
        {
            mop.addAttribute("storeId", storeId);
            ResultData<?> returnData = storeService.getById(storeId);
            mop.put("store", returnData.getReturnData());
        }
        catch (Exception e)
        {
            logger.error("store", "StoreController", "toDeposit", storeId + "", UserInfoHolder.getUserId(), "", "", e);
        }

        //返回视图
        ModelAndView mv = new ModelAndView("store/storeDeposit");

        return mv;

    }

    /**
     * @Title: update
     * @Description: 更新保证金
     * @param request
     * @param isform
     * @param id 门店编号
     * @return
     * @
     */
    @RequestMapping(value = "depositUpt/{storeId}", method = RequestMethod.PUT)
    public ReturnView<?, ?> updateDeposit(HttpServletRequest request, @PathVariable("storeId") String storeId)

    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        try
        {
            //更新
            storeService.updateStoreRelateInfo(reqMap, storeId, TYPE_DEPOSIT);
        }
        catch (Exception e)
        {
            logger.error("store", "StoreController", "updateDeposit", storeId, UserInfoHolder.getUserId(), "", "", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }

    /**
     * 设置装修状态页面
     * @return
     */
    @RequestMapping(value = "toDecoractionFee/{storeId}", method = RequestMethod.GET)
    public ModelAndView toDecoractionFee(@PathVariable("storeId") Integer storeId, ModelMap mop)
    {
        try
        {
            mop.addAttribute("storeId", storeId);
            ResultData<?> returnData = storeService.getById(storeId);
            mop.put("store", returnData.getReturnData());
        }
        catch (Exception e)
        {
            logger.error("store",
                "StoreController",
                "toDecoractionFee",
                storeId + "",
                UserInfoHolder.getUserId(),
                "",
                "",
                e);
        }

        //返回视图
        ModelAndView mv = new ModelAndView("store/storeDecorationFee");

        return mv;
    }

    /**
     * @Title: create
     * @Description: 插入保证金
     * @param request
     * @param isform
     * @param id 门店编号
     * @return
     * @
     */
    @RequestMapping(value = "decorationFeeUpt/{storeId}", method = RequestMethod.POST)
    public ReturnView<?, ?> createDecoractionFee(HttpServletRequest request, @PathVariable("storeId") String storeId)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);

        UserInfo userInfo = UserInfoHolder.get();
        Integer userCreate = userInfo.getUserCreate();
        reqMap.put("userCreate", userCreate);
        reqMap.put("dateCreate", new Date());
        reqMap.put("delFlag", "N");

        try
        {
            //更新
            storeService.createStoreRelateInfo(reqMap, storeId, TYPE_DECORATIONFEE);
        }
        catch (Exception e)
        {
            logger.error("store",
                "StoreController",
                "updateDecoractionFee",
                storeId,
                UserInfoHolder.getUserId(),
                "",
                "",
                e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }

    /**
     * @Title: update
     * @Description: 更新保证金
     * @param request
     * @param isform
     * @param id 门店编号
     * @return
     * @
     */
    @RequestMapping(value = "decorationFeeUpt/{storeId}", method = RequestMethod.PUT)
    public ReturnView<?, ?> updateDecoractionFee(HttpServletRequest request, @PathVariable("storeId") String storeId)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        try
        {
            //更新
            storeService.updateStoreRelateInfo(reqMap, storeId, TYPE_DECORATIONFEE);
        }
        catch (Exception e)
        {
            logger.error("store",
                "StoreController",
                "updateDecoractionFee",
                storeId,
                UserInfoHolder.getUserId(),
                "",
                "",
                e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }

    /**
     * 设置维护人页面
     * @return
     */
    @RequestMapping(value = "toMaintainer", method = RequestMethod.GET)
    public ModelAndView toMaintainer(HttpServletRequest request,ModelMap mop)
    {
        String storeId = request.getParameter("storeId");
        mop.addAttribute("storeId", storeId);
        //返回视图
        ModelAndView mv = new ModelAndView("store/storeMaintainerPopup");
        return mv;
    }


    /**
     * 保存维护人  联系人
     * @param request
     * @param storeId
     * @return
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "maintainerSave", method = RequestMethod.GET)
    public ReturnView<?, ?> updateMtcAndContactInfo(HttpServletRequest request)
    {
    	UserInfo userInfo = UserInfoHolder.get();
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        String storeId = (String)reqMap.get("storeId");
        String maintainer = (String)reqMap.get("maintainer");
    	String maintainerName = (String)reqMap.get("maintainerName");
    	String dateMtcStart = (String)reqMap.get("dateMtcStart");
    	String contactName = (String)reqMap.get("contactName");
    	String contactPhoneNo = (String)reqMap.get("contactPhoneNo");
    	// 0. check输入的维护人信息
    	try {
			ResultData<?> resultData = userService.getUserByCode(maintainer.trim());
			Map<String, Object> map = (Map<String, Object>)resultData.getReturnData();
			if(null == map){
				rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
				rspMap.put(Constant.RETURN_MSG_KEY, "维护人工号不存在!");
				return getSearchJSONView(rspMap);
			}else{
				String userName = (String)map.get("userName");
				String userCode = (String)map.get("userCode");
				if (!maintainerName.trim().equals(userName)) {
					rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
					rspMap.put(Constant.RETURN_MSG_KEY, "维护人工号与姓名不一致!");
					return getSearchJSONView(rspMap);
				}

				//判断拓展人和城市
				Map<String, Object> validMap = new HashMap<>();
				validMap.put("orderType", "DESC");//方法共用
				validMap.put("orderName", "dateCreate");//方法共用
				validMap.put("cityNo", userInfo.getCityNo());
				validMap.put("userName", userName);
				validMap.put("userCode", userCode);
				ResultData<?> validReback = userService.getExpanderUser(validMap, new PageInfo(request));
				List<?> contentlist = (List<?>)validReback.getReturnData();
				if(CollectionUtils.isEmpty(contentlist)){
					rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
					rspMap.put(Constant.RETURN_MSG_KEY, "门店维护人只能是该门店所在城市的拓展人员!");
					return getSearchJSONView(rspMap);
				}
			}
		} catch (Exception e) {
			logger.error("store",
	                "StoreController",
	                "updateMtcAndContactInfo",
	                "",
	                UserInfoHolder.getUserId(),
	                "",
	                "查找维护人失败！",
	                e);
	            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
	            rspMap.put(Constant.RETURN_MSG_KEY, "查找维护人失败！");
	            return getSearchJSONView(rspMap);
		}
    	// 1.更新维护人到store表
		StoreDto storeDto = new StoreDto();
		storeDto.setStoreId(Integer.valueOf(storeId));
		storeDto.setMaintainer(maintainer.trim());
		storeDto.setMaintainerName(maintainerName.trim());
    	try{
    		storeService.updateMtcToStore(storeDto);
    	}catch(Exception e){
    		logger.error("store",
                    "StoreController",
                    "updateMtcAndContactInfo",
                    "",
                    UserInfoHolder.getUserId(),
                    "",
                    "更新维护人到门店失败！",
                    e);
    		rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "更新维护人到门店失败！");
            return getSearchJSONView(rspMap);
    	}
    	// 2.插入维护人表
    	StoreMaintainerDto storeMaintainer = new StoreMaintainerDto();
    	storeMaintainer.setStoreId(Integer.valueOf(storeId));
        storeMaintainer.setUserCode(maintainer.trim());
        storeMaintainer.setUserIdCreate(userInfo.getUserId());
        storeMaintainer.setDelFlag("N");
        storeMaintainer.setDateCreate(new Date());
        storeMaintainer.setSetUserCode(userInfo.getUserCode());
        storeMaintainer.setSetUserName(userInfo.getUserName());
    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
        	if (StringUtil.isNotEmpty(dateMtcStart))
            {
            	storeMaintainer.setDateMtcStart(format.parse(dateMtcStart));
            }
        	storeMaintainerService.create(storeMaintainer);
        }catch(Exception e) {
    		logger.error("store",
                    "StoreController",
                    "updateMtcAndContactInfo",
                    "",
                    UserInfoHolder.getUserId(),
                    "",
                    "保存维护人失败！",
                    e);
    		rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "保存维护人失败！");
            return getSearchJSONView(rspMap);
    	}
    	// 3.插入联系人表
        ContactsDto contactsDto = new ContactsDto();
        String contactsNo = SeqNoHelper.getInstance().getSeqNoByTypeCode("TYPE_CONTACT");
        contactsDto.setStoreId(Integer.valueOf(storeId));
        contactsDto.setUserCreate(userInfo.getUserId());
        contactsDto.setContactsNo(contactsNo);
        contactsDto.setName(contactName.trim());
        contactsDto.setMobilePhone(contactPhoneNo.trim());
        contactsDto.setDateCreate(new Date());
        try
        {
        	contactsService.create(contactsDto);
        }
        catch (Exception e)
        {
            logger.error("store",
                "StoreController",
                "updateDecoractionFee",
                storeId,
                UserInfoHolder.getUserId(),
                "",
                "保存联系人失败！",
                e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "保存联系人失败！");
            return getSearchJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getSearchJSONView(rspMap);
    }

    //Add By WangLei 2017/04/07 Start
    /**
     * 更新是否无需续签Flag
     * @param storeId
     * @param neededRenew
     * @param mop
     * @return
     */
    @RequestMapping(value = "ndr/{storeId}/{neededRenew}" , method = RequestMethod.GET)
    public ReturnView<?, ?> updateNeededRenewFlg(@PathVariable("storeId") Integer storeId
    		,@PathVariable("neededRenew") Integer neededRenew, ModelMap mop)
    {
    	 //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        try
        {
            //更新
            storeService.updateNeededRenewFlag(storeId,neededRenew);
        }
        catch (Exception e)
        {
            logger.error("Store", "StoreController", "update", "", UserInfoHolder.getUserId(), "", "更新待续签Flag失败", e);

            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);

            rspMap.put(Constant.RETURN_MSG_KEY, "更新是否无需续签Flag失败");
        }

        return getOperateJSONView(rspMap);
    }
	// Add By WangLei 2017/04/07 End

    //Add By cning 2017/07/10 Start
    /**
     * 获取门店审核状态
     * @param URL
     * @param fileName
     * @param file
     * @return
     */
    @RequestMapping(value = "status/{storeId}" , method = RequestMethod.GET)
    private ReturnView<?, ?> getAuditStatus(@PathVariable("storeId") Integer storeId){
    	//返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        ResultData<?> resultData = new ResultData<>();

    	try {
    		  resultData = storeService.getAuditStatus(storeId);
              rspMap.put("auditStatus", resultData.getReturnData());
		} catch (Exception e) {
			  logger.error("Store", "StoreController", "getAuditStatus", "", UserInfoHolder.getUserId(), "", "获取门店审核状态失败", e);
			  rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
			  rspMap.put(Constant.RETURN_MSG_KEY, "获取门店审核状态失败");

			  return getSearchJSONView(rspMap);
		}
    	 rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
         rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
         return getSearchJSONView(rspMap);
    }
    //Add By cning 2017/07/10 End

    //Add By QJP 2017/07/11 Start
    /**
     * 门店基本信息修改页面
     * @param id  门店ID
     * @param mop
     * @return
     */
    @RequestMapping(value = "/ed", method = RequestMethod.GET)
    public ModelAndView toupdate(Integer storeId, ModelMap mop,HttpServletRequest request)
    {
        mop.addAllAttributes(bindParamToMap(request));
        ResultData<?> resultData = new ResultData<>();
        Map<String,Object> map = new HashMap<String,Object>();

        UserInfo userInfo = UserInfoHolder.get();
        String decityNo = userInfo.getCityNo();

        String storeAddressCityNoString="-1";
        try
        {
			resultData = storeService.getById(storeId);
			map= (Map<String,Object>)resultData.getReturnData();
			storeAddressCityNoString =map.get("cityNo").toString();
        }
        catch (Exception e)
        {
            logger.error("storeDetail", "StoreControler", "toupdate", "", null, "", "门店基本信息修改页面", e);
        }

        try {
        	//查询isService=1的城市
        	ResultData<?> reback = cityService.queryCityListByIsService();
        	List<?> citylist = (List<?>) reback.getReturnData();
        	mop.addAttribute("citylist", citylist);

        	//区域列表
        	ResultData<?> resultDistrictList = new ResultData<>();
           // resultDistrictList = linkageCityService.getDistrictList(decityNo);
            resultDistrictList = linkageCityService.getDistrictList(storeAddressCityNoString);
            mop.addAttribute("districtList", resultDistrictList.getReturnData());
		} catch (Exception e) {
			logger.error("storeDetail", "StoreControler", "toupdate", "", null, "", "查询城市", e);
		}

        //存放到mop中
        mop.addAttribute("storeLog", map);
        mop.addAttribute("storeId", storeId);
        ModelAndView mv = new ModelAndView("relate/storeDetailPopup");
        return mv;
    }

    /**
     * 运营修改门店信息
     * @param storeId
     * @param mop
     * @param request
     * @return
     */
    @RequestMapping(value = "/toOperationUpdate", method = RequestMethod.GET)
    public ModelAndView toOperationUpdate(Integer storeId, ModelMap mop,HttpServletRequest request)
    {
        mop.addAllAttributes(bindParamToMap(request));
        ResultData<?> resultData;
        Map<String,Object> map = new HashMap<>();

        UserInfo userInfo = UserInfoHolder.get();
        String decityNo = userInfo.getCityNo();

        try
        {
            resultData = storeService.getById(storeId);
            map= (Map<String,Object>)resultData.getReturnData();
        }
        catch (Exception e)
        {
            logger.error("storeDetail", "StoreControler", "toOperationUpdate", "", null, "", "门店基本信息修改页面", e);
        }

        try {
            //查询isService=1的城市
            ResultData<?> reback = cityService.queryCityListByIsService();
            List<?> citylist = (List<?>) reback.getReturnData();
            mop.addAttribute("citylist", citylist);

            //区域列表
            ResultData<?> resultDistrictList = new ResultData<>();
            resultDistrictList = linkageCityService.getDistrictList(decityNo);
            mop.addAttribute("districtList", resultDistrictList.getReturnData());
        } catch (Exception e) {
            logger.error("storeDetail", "StoreControler", "toOperationUpdate", "", null, "", "查询城市", e);
        }

        //查询最新合同
        try{
            ResultData<?> reback = contractService.selectNewestContract(storeId);
            mop.addAttribute("contract", reback.getReturnData());
        }catch (Exception e) {
            logger.error("storeDetail", "StoreControler", "toOperationUpdate", "", null, "", "查询最新合同", e);
        }
        //查询最新公盘合同
        try{
        	ResultData<?> reback = gpContractService.selectNewestGpContract(storeId);
        	mop.addAttribute("gpContract", reback.getReturnData());
        }catch (Exception e) {
        	logger.error("storeDetail", "StoreControler", "toOperationUpdate", "", null, "", "查询最新公盘合同失败", e);
        }


        //存放到mop中
        mop.addAttribute("storeLog", map);
        mop.addAttribute("storeId", storeId);
        ModelAndView mv = new ModelAndView("relate/storeDetailPopupForOperation");
        return mv;
    }


    /**
     * 跳转修改日志画面
     * @return
     */
    @RequestMapping(value = "/log/{storeId}", method = RequestMethod.GET)
    public ModelAndView toLogList(@PathVariable("storeId") Integer storeId, ModelMap mop)
    {
    	mop.addAttribute("storeId", storeId);

        //返回视图
        ModelAndView mv = new ModelAndView("store/storeLogList");

        return mv;
    }


   //Add By QJP 2017/07/11 Start
    /**
     * 门店修改日志页面
     * @param storeId
     * @param mop
     * @return
     */
    @RequestMapping(value = "/updateLog", method = RequestMethod.GET)
    public ModelAndView getStoreLogList(HttpServletRequest request, ModelMap mop)
    {
    	 //获取map
        Map<String, Object> rsqMap = bindParamToMap(request);
        String storeId = (String)rsqMap.get("storeId");
        ResultData<?> resultData = new ResultData<>();
        Map<String, Object> reqMap = new HashMap<>();
        PageInfo pageInfo = getPageInfo(request);
        reqMap.put("storeId", Integer.valueOf(storeId));
        reqMap.put("Id", null);
        try {
			resultData = storeService.getStoreLogList(reqMap,pageInfo);
			resultData.setSuccess();
	        mop.put("contentlist", resultData.getReturnData());
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("getStoreLogList failed");
		}
        mop.addAttribute("storeId", storeId);
        //返回视图
        ModelAndView mv = new ModelAndView("store/storeViewLog");

        return mv;
    }

  //Add By QJP 2017/07/12 Start
    /**
     * 查看修改日志详情
     * @param id  门店ID
     * @param mop
     * @return
     */
    @RequestMapping(value = "/viewLog", method = RequestMethod.GET)
    public ModelAndView getStoreLogDetail(Integer Id,Integer StoreId, ModelMap mop)
    {
        ResultData<?> resultData = new ResultData<>();
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("storeId", StoreId);
        reqMap.put("Id", Id);
        try
        {
			resultData = storeService.getStoreLogList(reqMap,null);
			 List<Map<String, Object>> contentlist = (List<Map<String, Object>>)resultData.getReturnData();
		        //存放到mop中
		     mop.addAttribute("StoreLog", contentlist.get(0));
        }
        catch (Exception e)
        {
            logger.error("getStoreLogDetail", "StoreControler", "getStoreLogDetail", "", null, "", "查看门店修改日志详细页面", e);
        }

        //查询更新后门店图片
        try{
        	 ResultData<?> reback = storeService.getById(StoreId);
        	//存放到mop中
             mop.addAttribute("storeDetail", reback.getReturnData());
        }catch(Exception e1)
        {
        	logger.error("Store", "StoreController", "showdetail", "", UserInfoHolder.getUserId(), "", "查看门店详情失败", e1);
        }

        ModelAndView mv = new ModelAndView("relate/storeLogListCtxPopup");
        return mv;
    }
   //Add By QJP 2017/07/12 End


    /**
     * @Title: update
     * @Description: 门店基本信息修改点击保存
     * @param request
     * @param
     * @param id 门店编号
     * @return
     * @
     */
     @RequestMapping(value = "/updateDetail/{id}/{no}", method = RequestMethod.POST)
     public ReturnView<?, ?> updateDetail(HttpServletRequest request,@PathVariable("id") String id,@PathVariable("no") String no)
     {
         //返回map
         Map<String, Object> rspMap = new HashMap<String, Object>();
         //获取map
         Map<String, Object> reqMap = bindParamToMap(request);
         String name = ((String) reqMap.get("newName")).trim();
         String oldName = (String) reqMap.get("oldName");
         String oldCitiNo = (String) reqMap.get("oldStoreCity");
         String oldDistrictNo = (String) reqMap.get("oldStoreDistrict");
         String oldStoreAddress = (String) reqMap.get("oldStoreAddress");
         String newCityNo = (String) reqMap.get("newStoreCity");
         String newDistrictNo = (String) reqMap.get("newStoreDistrict");
         String newStoreAddress = (String) reqMap.get("newStoreAddress");
         newStoreAddress = newStoreAddress.trim();
         String pictureRefId = (String)reqMap.get("pictureRefId");
         String changeName = (String) reqMap.get("storeName");
         String changeAddress = (String) reqMap.get("storeAddress");
         String longitude = (String) reqMap.get("longitude");
         String latitude = (String) reqMap.get("latitude");
         String cityName = (String) reqMap.get("cityName");
         String districtName = (String) reqMap.get("districtName");
         String oldAddressDetail = (String) reqMap.get("oldAddressDetail");

         String businessPlaceType = (String) reqMap.get("businessPlaceType");
         String storeSizeScale = (String) reqMap.get("storeSizeScale");
         String storeType = (String) reqMap.get("storeType");

         StoreDto storeDto = new StoreDto();
         storeDto.setStoreNo(no);
         storeDto.setUserUpdate(UserInfoHolder.getUserId());
         storeDto.setDateUpdate(new Date());
         storeDto.setStoreId(Integer.valueOf(id));
         //String转换为BigDecimal
         BigDecimal bd=new BigDecimal(longitude);
         BigDecimal db=new BigDecimal(latitude);
         storeDto.setLongitude(bd);
         storeDto.setLatitude(db);
         if(name != null && !"".equals(name))
         {
        	 String newNameStr = name.replace("(", "（");;
        	 newNameStr = newNameStr.replace(")", "）");
        	 storeDto.setName(newNameStr);
         }else
         {
        	 storeDto.setName(null);
         }
         if(changeAddress != null)
         {
	         if(newDistrictNo != null && !"".equals(newDistrictNo))
	         {

	        	 storeDto.setDistrictNo(newDistrictNo);
	         }else
	         {
	        	 storeDto.setAddressDetail(null);
	         }
	         if(newStoreAddress != null && !"".equals(newStoreAddress))
	         {
	        	 storeDto.setAddress(newStoreAddress);
	        	 String addressDetail = cityName.trim() + districtName.trim() + newStoreAddress.trim();
        		 storeDto.setAddressDetail(addressDetail);
	         }else
	         {
	        	 storeDto.setAddress(null);
	        	 storeDto.setAddressDetail(null);
	         }

	         if(newCityNo != null && !"".equals(newCityNo))
	         {
	        	 storeDto.setCityNo(newCityNo);
	         }else
	         {
	        	 storeDto.setCityNo(null);
	         }
          }

         if(businessPlaceType != null){
             storeDto.setChangeBusinessPlaceType(businessPlaceType);
             if("".equals(reqMap.get("oldBusinessPlace").toString())){
                 storeDto.setOldBusinessPlace(0);
             }else{
                 storeDto.setOldBusinessPlace(Integer.valueOf(reqMap.get("oldBusinessPlace").toString()));
             }

             storeDto.setNewBusinessPlace(Integer.valueOf(reqMap.get("newBusinessPlace").toString()));
         }
         if(storeSizeScale != null){
             storeDto.setChangeStoreSizeScale(storeSizeScale);
             if("".equals(reqMap.get("oldStoreSize"))){
                 storeDto.setOldStoreSize(0);
             }else{
                 storeDto.setOldStoreSize(Integer.valueOf(reqMap.get("oldStoreSize").toString()));
             }
             storeDto.setNewStoreSize(Integer.valueOf(reqMap.get("newStoreSize").toString()));
         }
         if(storeType != null){
             storeDto.setStoreType(storeType);
             if("".equals(reqMap.get("oldStoreType"))){
                 storeDto.setOldStoreType(0);
             }else{
                 storeDto.setOldStoreType(Integer.valueOf(reqMap.get("oldStoreType").toString()));
             }
             storeDto.setNewStoreType(Integer.valueOf(reqMap.get("newStoreType").toString()));
         }

         storeDto.setPictureRefId(pictureRefId);
         storeDto.setOldStoreLogName(oldName);
         storeDto.setOldCityNo(oldCitiNo);
         storeDto.setOldDistrictNo(oldDistrictNo);
         storeDto.setOldStoreAddress(oldStoreAddress);
         storeDto.setOldAddressDetail(oldAddressDetail);
         if(changeName != null && !"".equals(changeName))
         {
        	 storeDto.setChangeName(Integer.valueOf(changeName));
         }else
         {
        	 storeDto.setChangeName(0);
         }
         if(changeAddress != null && !"".equals(changeAddress))
         {
        	 storeDto.setChangeAddress(Integer.valueOf(changeAddress));
         }else
         {
        	 storeDto.setChangeAddress(0);
         }
         reqMap.remove("_method");

         //判断地址是否重复
         if(changeAddress != null)
         {
         if(changeAddress.equals("1"))
         {
        	 Map<String,Object> map = new HashMap<>();
        	 map.put("CityNo", newCityNo);
        	 map.put("DistrictNo", newDistrictNo);
        	 map.put("Address", newStoreAddress);
	         try {
				ResultData<?> resultDate = storeService.checkAddress(map);
				if(resultDate.getReturnCode().equals(ReturnCode.FAILURE))
				{
					 rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
					 rspMap.put(Constant.RETURN_MSG_KEY, "系统中已存在相同的门店地址或者门店地址已被锁定，请重新填写!");
					 return getOperateJSONView(rspMap);
				}
			 } catch (Exception e) {
				e.printStackTrace();
			 }
       }

         }

         //营业状态修改时地址判重
         if(reqMap.get("businessStatus") != null && !"".equals(reqMap.get("businessStatus"))) {
             Map<String,Object> map = new HashMap<>();
             map.put("CityNo", oldCitiNo);
             map.put("DistrictNo", oldDistrictNo);
             map.put("Address", oldStoreAddress);
             map.put("storeId", id);
             try {
                ResultData<?> resultDate = storeService.checkAddress(map);
                if(resultDate.getReturnCode().equals(ReturnCode.FAILURE)){
                     rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                     rspMap.put(Constant.RETURN_MSG_KEY, "系统中已存在相同的门店地址，该门店重新营业失败!");
                     return getOperateJSONView(rspMap);
                }
             } catch (Exception e) {
                e.printStackTrace();
             }
             storeDto.setBusinessStatus(Integer.valueOf(reqMap.get("businessStatus").toString()));
         }

         try
         {
             //上传
             List<PictureDto> pictureDtoList = upload(request);
             reqMap.put("storePicListJson",JSON.toJSONString(pictureDtoList));
             //更新
             storeDto.setStorePicListJson(JSON.toJSONString(pictureDtoList));

             if(reqMap.get("flag") != null){
                 storeDto.setFlag(reqMap.get("flag").toString());
             }
             if(reqMap.get("contractId") != null){
                 storeDto.setContractId(Integer.valueOf(reqMap.get("contractId").toString()));
             }
             if(reqMap.get("gpContractId") != null){
            	 storeDto.setGpContractId(Integer.valueOf(reqMap.get("gpContractId").toString()));
             }
             storeService.updateD(storeDto);

         } catch (Exception e){
             logger.error("Store", "StoreController", "update", "", UserInfoHolder.getUserId(), "", "更新门店失败", e);

             rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);

             rspMap.put(Constant.RETURN_MSG_KEY, "更新门店失败");
             return getOperateJSONView(rspMap);
         }
        /* try {
        	 //跟新公盘合同中门店信息
        	 Map<String,Object> hashMap = new HashMap<>();
        	 hashMap.put("storeId", id);
			gpContractService.updateByGpStoreId(hashMap);
		} catch (Exception e) {
			logger.error("Store", "StoreController", "update", "", UserInfoHolder.getUserId(), "", "更新公盘合同中门店信息失败", e);
		}*/
         return getOperateJSONView(rspMap);
     }



     /**
      *  根据门店Id查询的合同状态check是否能修改
      * @param storeId 门店Id
      * @return
      */
     @RequestMapping(value = "/checkStatus", method = RequestMethod.GET)
     public ReturnView<?, ?> getContractStatus(HttpServletRequest request, ModelMap mop)
     {
    	 Map<String, Object> rspMap = new HashMap<String, Object>();
    	 ResultData<?> resultData  = new ResultData<>();
         //获取请求参数
         Map<String, Object> reqMap = bindParamToMap(request);
         try
         {
         	 resultData = contractService.getSignHisByStoreId(reqMap, null);
             List<Map<String,Object>> list = (List<Map<String, Object>>) resultData.getReturnData();
             if(!list.isEmpty() && list.size()>0)
             {
            	DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            	String dateLifeEnd = (String) list.get(0).get("dateLifeEnd");
            	Date enddate = df.parse(dateLifeEnd);
            	Date nowdate = new Date();
            	if(nowdate.getTime() < enddate.getTime())
            	{
            		String contractStatus=list.get(0).get("contractStatus").toString();
    				Integer storeState = Integer.valueOf(list.get(0).get("storeState")==null?"0":list.get(0).get("storeState").toString());
    				if(("10402").equals(contractStatus) || ("10403").equals(contractStatus))
                	{
                		if(storeState != 2)
                		{
                		rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
    	                rspMap.put(Constant.RETURN_MSG_KEY, "该门店签署的合同正在审批中或审批通过，门店信息不允许修改!");
    	                return getOperateJSONView(rspMap);
                		}
                	}
            	}
            	rspMap.put("contractStatus", list.get(0).get("contractStatus"));
             }
         }
         catch (Exception e)
         {
             logger.warn("getSignHisByStoreId failed");
         }
         rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
         rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
         return getOperateJSONView(rspMap);
     }

     //组装OP数据
     public OpOperatorDto setToOaInfo(StoreDto storeDto)
     {
    	 OpOperatorDto opOperatorDto = new OpOperatorDto();
    	 opOperatorDto.setStoreNo(storeDto.getStoreNo());
    	 if(storeDto.getChangeName() == 1)
    	 {
    		 opOperatorDto.setName(storeDto.getName());
    	 }else
    	 {
    		 opOperatorDto.setName(storeDto.getOldStoreLogName());
    	 }
    	 if(storeDto.getChangeAddress() == 1)
    	 {
    		 opOperatorDto.setCityNo(storeDto.getCityNo());
        	 opOperatorDto.setDistrictNo(storeDto.getDistrictNo());
        	 opOperatorDto.setAddress(storeDto.getAddress());
        	 opOperatorDto.setAddressDetail(storeDto.getAddressDetail());
    	 }else
    	 {
    		 opOperatorDto.setCityNo(storeDto.getOldCityNo());
        	 opOperatorDto.setDistrictNo(storeDto.getOldDistrictNo());
        	 opOperatorDto.setAddress(storeDto.getOldStoreAddress());
        	 opOperatorDto.setAddressDetail(storeDto.getOldAddressDetail());
    	 }
    	 opOperatorDto.setAreaNo(storeDto.getAreaNo());
    	 opOperatorDto.setLongitude(storeDto.getLongitude().toString());
    	 opOperatorDto.setLatitude(storeDto.getLatitude().toString());
    	 opOperatorDto.setUserIdUpdate(storeDto.getUserUpdate());
         return  opOperatorDto;
     }


     @RequestMapping(value="/q/businessStatus")
     public @ResponseBody List<?> queryStore(@RequestBody List<String> storeIdList) {
    	 Map<String,Object> param = new HashMap<String,Object>();
    	 param.put("storeIdList", storeIdList);
    	 List<?> result = null;
    	 try {
    		 result = storeService.queryList(param, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	 return result;
     }

     @RequestMapping("/toServiceFrameContract/{storeId}")
     public ModelAndView toServiceFrameContract(@PathVariable Integer storeId){
         ModelAndView modelAndView = new ModelAndView("store/storeServiceFrameContract");
         modelAndView.addObject("storeId",storeId);

         try {
             ResultData resultData = storeService.queryServiceFrameContract(storeId);
             if(resultData != null && resultData.getReturnCode().equals("200")){
                 modelAndView.addObject("file",resultData.getReturnData());
             }
         }catch (Exception e){
             logger.error("Store",
                     "StoreController",
                     "toServiceFrameContract",
                     storeId.toString(),
                     UserInfoHolder.getUserId(),
                     "",
                     "查询门店服务框架合同异常",
                     e);
         }

         return modelAndView;
     }

    @RequestMapping(value = "gpqr", method = RequestMethod.GET)
    public ReturnView<?, ?> queryGpRelatelist(HttpServletRequest request, ModelMap mop)

    {
        Map<String, Object> reqMap = bindParamToMap(request);
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try
        {
            ResultData<?> resultData = storeService.queryGpRelatelist(reqMap);

            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        }
        catch (Exception e)
        {
            logger.error("Store", "StoreController", "queryGpRelatelist", "", UserInfoHolder.getUserId(), "", "查询公盘关联门店列表失败", e);

            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);

            rspMap.put(Constant.RETURN_MSG_KEY, "查询公盘关联门店列表失败");
        }
        return getOperateJSONView(rspMap);
    }

    @RequestMapping(value = "/selectChangePop", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView selectChangePop(HttpServletRequest request,ModelMap mop){
        ModelAndView modelAndView = new ModelAndView("store/selectChangePop");
        Map<String, Object> map = bindParamToMap(request);
        modelAndView.addAllObjects(map);
        return modelAndView;
    }

    @RequestMapping(value = "/toStoreStopCancel", method = RequestMethod.GET)
    public ModelAndView toStoreStopCancel(Integer storeId, ModelMap mop){
        ResultData<?> resultData;

        try{
            resultData = storeService.getPlainInfoById(storeId);
            mop.addAttribute("store",resultData.getReturnData());
        }
        catch (Exception e){
            logger.error("storeDetail", "StoreControler", "getPlainInfoById", "", UserInfoHolder.getUserId(), "storeId="+storeId, "查询门店异常", e);
        }

        //存放到mop中
        mop.addAttribute("storeId", storeId);
        ModelAndView mv = new ModelAndView("store/storeStopCancelAdd");
        return mv;
    }
}
