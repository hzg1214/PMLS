package cn.com.eju.deal.store.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import cn.com.eju.deal.Report.controller.GroupConfig;
import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.linkage.service.LinkageCityService;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.service.CityService;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.store.service.StoreMaintenanceService;
import cn.com.eju.deal.user.service.UserService;

/** 
* @Description: 门店controller
*/
@RestController
@RequestMapping(value = "storeMaintenance")
public class StoreMaintenanceController extends BaseController
{
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "linkageCityService")
    private LinkageCityService linkageCityService;
    
    @Resource(name = "cityService")
    CityService cityService;
    
    @Resource(name = "userService")
    private UserService userService;
    
    @Resource
    private StoreMaintenanceService storeMaintenanceService;
    
    /** 
    * @Title: list 
    * @Description: 门店维护列表页面
    */
	@RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response)
    {
        //构建ModelAndView实例，并设置跳转地址
        ModelAndView mv = new ModelAndView("store/storeMaintenanceList");
        
        UserInfo userInfo = UserInfoHolder.get();
        String cityNo = userInfo.getCityNo();
        if (StringUtil.isNotEmpty(cityNo)){
            //区域列表
            try{
                
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
            }
//            }
            catch (Exception e)
            {
                logger.error("Store",
                    "StoreMaintenanceController",
                    "list linkageCityService.getDistrictList",
                    "",
                    UserInfoHolder.getUserId(),
                    "",
                    "查看门店列表失败",
                    e);
            }
        }
        //待续签code取得
        mv.addObject("RENEWFLAG_TYPE_DX", DictionaryConstants.RENEWFLAG_TYPE_DX);
        //无需续签code取得
        mv.addObject("NEEDEDRENEW_TYPE_NXQ", DictionaryConstants.NEEDEDRENEW_TYPE_NXQ);
        //Add 2017/04/07 cning <----
        return mv;
        
    }
    
	/**
     * 获取门店维护人列表页
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public ModelAndView getStoreMaintenanceList(HttpServletRequest request, ModelMap mop)
    {
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);

        //返回list
        ResultData<?> resultData = null;
        List<?> contentlist = new ArrayList<>();
        try
        {
            resultData = storeMaintenanceService.getStoreMaintenanceList(reqMap,pageInfo);
            if(resultData != null){
                contentlist = (List<?>) resultData.getReturnData();
            }
        }
        catch (Exception e)
        {
            logger.error("PMLS", "StoreMaintenanceController", "getStoreMaintenanceList", reqMap.toString(), null, "", "获取联动业绩调整列表页", e);
        }

        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        ModelAndView mv = new ModelAndView("store/storeMaintenanceListCtx");
        return mv;
    }
    /**
     * --初始化
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "maintenanceUser", method = RequestMethod.GET)
    public ModelAndView toMaintenanceList(HttpServletRequest request, ModelMap mop)
    {
        bindParamToAttrbute(request);
       /* Map<String, Object> reqMap = bindParamToMap(request);
        mop.put("params", reqMap);*/
        ModelAndView mv = new ModelAndView("store/storeMaintenanceListPopup");
        return mv;
    }
    /**
     * 获取维护人列表
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "maintenanceUser/getList", method = RequestMethod.GET)
    public ModelAndView getMaintenanceList(HttpServletRequest request, ModelMap mop)
    {
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);

        //返回list
        ResultData<?> resultData = null;
        List<?> linkUserList = new ArrayList<>();
        try
        {
            reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
            resultData = userService.getMaintenanceUser(reqMap,pageInfo);
            if(resultData != null){
                linkUserList = (List<?>) resultData.getReturnData();
            }
        }
        catch (Exception e)
        {
            logger.error("PMLS", "StoreMaintenanceController", "getLinkUserList", reqMap.toString(), null, "", "获取维护人列表", e);
        }

        //存放到mop中
        mop.addAttribute("maintenanceList", linkUserList);
        ModelAndView mv = new ModelAndView("store/maintenanceUserListCtxPopup");
        return mv;
    }
    
    /**
     * 保存维护人变更信息
     * @param request
     * @return
     */
    @RequestMapping(value="saveMaintenance", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView saveMaintenance (HttpServletRequest request){
        ReturnView returnView = new ReturnView();
        ResultData resultData = new ResultData();
        resultData.setFail();
        //请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("createUserId",UserInfoHolder.get().getUserId());
        reqMap.put("createUserCode",UserInfoHolder.get().getUserCode());
        reqMap.put("createUserName",UserInfoHolder.get().getUserName());
        try {

            resultData = storeMaintenanceService.saveMaintenance(reqMap);

        } catch (Exception e) {
            resultData.setFail("保存维护人异常!");
            logger.error("PMLS", "StoreMaintenanceController", "saveMaintenance", reqMap.toString(), null, "", "维护人变更保存异常", e);
        }

        returnView.setOperateReturnType();
        returnView.setReturnCode(resultData.getReturnCode());
        returnView.setReturnMsg(resultData.getReturnMsg());
        returnView.setReturnValue(resultData.getReturnData());

        return returnView;

    }
    
   
}
