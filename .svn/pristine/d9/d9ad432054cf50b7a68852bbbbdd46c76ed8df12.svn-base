package cn.com.eju.pmls.store.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.com.eju.deal.Report.controller.GroupConfig;
import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.dto.code.CommonCodeDto;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.linkage.service.LinkageCityService;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.service.CityService;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.dto.store.StoreMaintainerDto;
import cn.com.eju.deal.oa.service.OaOperatorService;
import cn.com.eju.pmls.store.service.PmlsStoreMaintainerService;
import cn.com.eju.pmls.store.service.PmlsStoreService;

@RestController
@RequestMapping(value = "pmlsStore")
public class PmlsStoreController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    PmlsStoreService storeService;

    @Resource(name = "linkageCityService")
    private LinkageCityService linkageCityService;

    @Resource(name = "cityService")
    CityService cityService;

    @Resource(name = "oaOperatorService")
    private OaOperatorService oaOperatorService;
    
    @Resource(name = "pmlsStoreMaintainerService")
    private PmlsStoreMaintainerService storeMaintainerService;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
        // 构建ModelAndView实例，并设置跳转地址
        ModelAndView mv = new ModelAndView("store/storeList");

        UserInfo userInfo = UserInfoHolder.get();
        String cityNo = userInfo.getCityNo();
        Map<String, Object> map = bindParamToMap(request);
        if (map.containsKey("backTab")) {
            mv.addObject("backTab", (String) map.get("backTab"));
        }
        if (StringUtil.isNotEmpty(cityNo)) {
            // 区域列表
            try {
                ResultData<?> resultDistrictList = linkageCityService.getDistrictList(cityNo);

                // 将数据放置到ModelAndView对象view中,第二个参数可以是任何java类型
                mv.addObject("districtList", resultDistrictList.getReturnData());

                // 根据城市获取所属中心
                Integer typeId = GroupConfig.getLevelTypeIds()[0];
                Integer typeId2 = GroupConfig.getLevelTypeIds()[1];
                ResultData<?> resultGroupList = linkageCityService.getGroupList(cityNo, typeId, typeId2);
                mv.addObject("groupList", resultGroupList.getReturnData());

                try {
                    // 查询isService=1的城市
                    ResultData<?> reback = cityService.queryCityListByIsService();
                    List<?> citylist = (List<?>) reback.getReturnData();
                    mv.addObject("citylist", citylist);
                } catch (Exception e) {
                    logger.error("list", "PMLSStoreController", "list", "", null, "", "查询城市", e);
                }

            } catch (Exception e) {
                logger.error("Store", "PMLSStoreController", "list linkageCityService.getDistrictList", "",
                        UserInfoHolder.getUserId(), "", "查看门店列表失败", e);
            }
        }
        // Add 2017/04/07 cning ---->
        // 待续签code取得
        mv.addObject("RENEWFLAG_TYPE_DX", DictionaryConstants.RENEWFLAG_TYPE_DX);
        // 无需续签code取得
        mv.addObject("NEEDEDRENEW_TYPE_NXQ", DictionaryConstants.NEEDEDRENEW_TYPE_NXQ);
        List<CommonCodeDto> brandTypeList = SystemParam.getCodeListByKey("294");//报备来源
        mv.addObject("brandTypeList", brandTypeList);
        // Add 2017/04/07 cning <----
        return mv;

    }

    /**
     * @param request
     * @param mop
     * @return
     * @Title: querylist
     * @Description: 门店列表
     */
    @ResponseBody
    @RequestMapping(value = "querylist", method = RequestMethod.POST)
    public ResultData<?> querylist(HttpServletRequest request, ModelMap mop) {
        UserInfo userInfo = UserInfoHolder.get();
        String cityNo = userInfo.getCityNo();
        // 获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);

        // 页面数据
        try {
            // 待续签Flag取得
            reqMap.put("renewFlag", DictionaryConstants.RENEWFLAG_TYPE_DX);
            reqMap.put("cityNo", cityNo);
            // Add 2017/04/07 cning <----
            resultData = storeService.queryList(reqMap, pageInfo);
        } catch (Exception e1) {
            logger.error("Store", "PMLSStoreController", "queryList", "", UserInfoHolder.getUserId(), "", "查看门店列表失败", e1);
        }

        // 查询是否是经办人
        ResultData<?> backResult = new ResultData<>();
        try {
            backResult = oaOperatorService.getByUserCode(UserInfoHolder.get().getUserCode());
        } catch (Exception e) {
            logger.error("store", "PMLSStoreController", "querylist", "", UserInfoHolder.getUserId(), "",
                    "门店查询, 查询经办人失败", e);
        }

        // 存放到mop中
        mop.addAttribute("oaOperator", backResult.getReturnData());
        // 待续签code取得
        mop.put("RENEWFLAG_TYPE_DX", DictionaryConstants.RENEWFLAG_TYPE_DX);
        // 无需续签code取得
        mop.put("NEEDEDRENEW_TYPE_NXQ", DictionaryConstants.NEEDEDRENEW_TYPE_NXQ);
        // Add 2017/04/07 cning <----

        return resultData;

    }

    /**
     * desc:门店详情
     * 2020年2月18日
     */
    @RequestMapping(value = "storeDetail", method = RequestMethod.GET)
    public ModelAndView storeDetail(Model model, HttpServletRequest request) {
    	int id=Integer.parseInt(request.getParameter("id"));
        ResultData<?> resultData = new ResultData<>();
        try {
            resultData = storeService.getbriefById(id);
            model.addAttribute("storeDetail", JSONObject.toJSON(resultData.getReturnData()));
        } catch (Exception e) {
            logger.error("门店详情异常", e);
        }

        //返回视图
        ModelAndView mv = new ModelAndView("store/storeDetail");
        return mv;
    }

    @RequestMapping(value = "storeLogList/{storeId}", method = RequestMethod.GET)
    public ModelAndView storeLogList(@PathVariable("storeId") Integer storeId, ModelMap mop) {

        mop.addAttribute("storeId", storeId);
        //返回视图
        ModelAndView mv = new ModelAndView("store/storeLogList");
        return mv;
    }


    @RequestMapping(value = "queryStoreLogList/{storeId}", method = RequestMethod.POST)
    public ResultData<?> queryStoreLogList(@PathVariable("storeId") Integer storeId
            , HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);

        try {
            reqMap.put("storeId", storeId);
            //获取页面显示数据
            resultData = storeService.getStoreLogList(reqMap, pageInfo);

        } catch (Exception e) {
            logger.error("查询修改日志列表异常;input param: reqMap=" + reqMap.toString(), e);

            logger.error("store",
                    "PmlsStoreController",
                    "queryStoreLogList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询修改日志列表异常",
                    e);
        }

        return resultData;
    }

    @RequestMapping(value = "storeLogView/{id}/{storeId}", method = RequestMethod.GET)
    public ModelAndView storeLogView(@PathVariable("id") Integer id,
                                     @PathVariable("storeId") Integer storeId, ModelMap mop) {

        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("storeId", storeId);
        reqMap.put("id", id);
        try {
            ResultData<?> resultData = storeService.getStoreLog(reqMap);
            mop.addAttribute("storeLog", JSONObject.toJSON(resultData.getReturnData()));
        } catch (Exception e) {
            logger.error("Store", "StoreControler", "storeLogView",
                    "", null, "", "查看门店修改日志详细页面", e);
        }

        //返回视图
        ModelAndView mv = new ModelAndView("store/storeLogView");
        return mv;
    }

    @RequestMapping(value = "storeInfoEditRule", method = RequestMethod.GET)
    public ModelAndView storeInfoEditRule(HttpServletRequest request, ModelMap mop) {
        ModelAndView mv = new ModelAndView("store/storeInfoEditRule");
        return mv;
    }
    
    
    /**
     * desc:选择维护人
     * 2020年2月24日
     */
    @RequestMapping(value = "relateStoreUser", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView relateStoreUser(HttpServletRequest request, ModelMap mop) {
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.put("reqMap", reqMap);
        ModelAndView mv = new ModelAndView("store/relateStoreUser");
        return mv;
    }
     
     /**
      * desc:查询门店维护人
      * 2020年2月24日
      */
     @ResponseBody
     @RequestMapping(value = "queryStoreUser", method = RequestMethod.POST)
     public ResultData<?> queryStoreUser(HttpServletRequest request) {
         Map<String, Object> reqMap = bindParamToMap(request);
         ResultData<?> resultData = null;
         PageInfo pageInfo = getPageInfo(request);
         try {
             reqMap.put("cityNo",reqMap.get("acCityNo"));
             //获取页面显示数据
             resultData = storeService.queryStoreUser(reqMap, pageInfo);
         } catch (Exception e) {
             logger.error("store",
                     "PmlsStoreController",
                     "queryStoreUser",
                     "input param: reqMap=" + reqMap.toString(),
                     UserInfoHolder.getUserId(),
                     WebUtil.getIPAddress(request),
                     "查询门店维护人列表",
                     e);
         }

         return resultData;
     }
     
     /**
      * 
      * desc:门店修改
      * 2020年3月31日
      */
     @RequestMapping(value = "/toStoreUpdate", method = RequestMethod.GET)
     public ModelAndView toUpdate(ModelMap mop,HttpServletRequest request)
     {
         mop.addAllAttributes(bindParamToMap(request));
         Map<String, Object> reqMap = bindParamToMap(request);
         ResultData<?> resultData = new ResultData<>();
         Map<String,Object> map = new HashMap<String,Object>();

         UserInfo userInfo = UserInfoHolder.get();
         String decityNo = userInfo.getCityNo();

         Integer storeId= Integer.parseInt((String)reqMap.get("storeId"));
         String storeAddressCityNoString="-1";
         try
         {
 			resultData = storeService.getById(storeId);
 			map= (Map<String,Object>)resultData.getReturnData();
 			storeAddressCityNoString =map.get("cityNo").toString();
         }
         catch (Exception e)
         {
             logger.error("store", "PmlsStoreController", "toUpdate", "", null, "", "门店基本信息修改页面", e);
         }

         try {
         	//查询isService=1的城市
         	ResultData<?> reback = cityService.queryCityListByIsService();
         	List<?> citylist = (List<?>) reback.getReturnData();
         	mop.addAttribute("citylist", citylist);

         	//区域列表
         	ResultData<?> resultDistrictList = new ResultData<>();
             resultDistrictList = linkageCityService.getDistrictList(storeAddressCityNoString);
             mop.addAttribute("districtList", resultDistrictList.getReturnData());
 		} catch (Exception e) {
 			logger.error("store", "PmlsStoreController", "toUpdate", "", null, "", "查询城市", e);
 		}

         //存放到mop中
         mop.addAttribute("storeLog", map);
         mop.addAttribute("storeId", storeId);
         mop.addAttribute("reqMap", reqMap);
         ModelAndView mv = new ModelAndView("store/storeUpdatePopup");
         return mv;
     }
     
     /**
      * 保存门店修改
      * @param request
      * @param modelMap
      * @return
      */
     @RequestMapping(value = "/saveStoreData", method = RequestMethod.POST)
     @ResponseBody
     public ReturnView<?, ?> saveStoreData(HttpServletRequest request, ModelMap modelMap)
     {
    	 Map<String, Object> reqMap = bindParamToMap(request);
         String storeIdStr = request.getParameter("storeId");
         String storeNo = request.getParameter("storeNo");
         String brandType = request.getParameter("brandType");
         String pmlsCenterId = request.getParameter("pmlsCenterId");
         String pmlsCneterName = request.getParameter("pmlsCneterName");
         String pmlsMaintainCode = request.getParameter("pmlsMaintainCode");
         String pmlsMaintainName = request.getParameter("pmlsMaintainName");
         logger.info("门店-门店详情-门店修改，保存门店修改信息，入参:"+reqMap);
         UserInfo userInfo = UserInfoHolder.get();
         //返回map
         Map<String, Object> rspMap = new HashMap<String, Object>();
         try
         {
         		// 新增维护人
                 StoreMaintainerDto insertMaintainer = new StoreMaintainerDto();
                 insertMaintainer.setStoreId(Integer.valueOf(storeIdStr));
                 insertMaintainer.setUserCode(pmlsMaintainCode.trim());
                 insertMaintainer.setDateCreate(new Date());
                 insertMaintainer.setDelFlag("N");
                 insertMaintainer.setUserIdCreate(userInfo.getUserId());
                 insertMaintainer.setSetUserCode(userInfo.getUserCode());
                 insertMaintainer.setSetUserName(userInfo.getUserName());
                 // 开始维护时间
                 insertMaintainer.setDateMtcStart(new Date());
                 insertMaintainer.setIsNew("1");
                 ResultData<?> resultData = storeMaintainerService.create(insertMaintainer);
                 rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
                 rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
                 
                 // 更新门店维护人
                 StoreDto storeDto = new StoreDto();
                 storeDto.setStoreId(Integer.valueOf(storeIdStr));
                 storeDto.setPmlsMaintainCode(pmlsMaintainCode);
                 storeDto.setPmlsMaintainName(pmlsMaintainName);
                 storeDto.setPmlsCenterId(Integer.valueOf(pmlsCenterId));
                 storeDto.setBrandType(Integer.valueOf(brandType));
             	 //后台取中心要用到cityNo
             	 storeDto.setCityNo(UserInfoHolder.get().getCityNo());
             	 storeDto.setIsUpdateCompanyFlag(1);
             	 storeDto.setUserUpdate(UserInfoHolder.get().getUserId());
             	 storeDto.setPmlsCenterId(Integer.valueOf(pmlsCenterId));
                 storeService.updateMtcToStore(storeDto);
                 logger.info("门店-门店详情-门店修改，保存门店修改信息，保存成功！storeId:"+storeIdStr+",维护人:"+pmlsMaintainName+",维护中心:"+pmlsCenterId);
                 rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
                 rspMap.put(Constant.RETURN_MSG_KEY, "修改信息成功");
         }
         catch (Exception e)
         {
             rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
             rspMap.put(Constant.RETURN_MSG_KEY, "保存门店修改失败!");
             logger.error("store", "PmlsStoreController", "saveStoreData", "保存门店修改失败!", null,"", "", e);
         }
         return getOperateJSONView(rspMap);
     }

}
