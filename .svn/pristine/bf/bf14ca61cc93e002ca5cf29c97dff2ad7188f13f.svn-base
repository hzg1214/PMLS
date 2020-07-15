package cn.com.eju.pmls.storeMaintenance.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.Report.controller.GroupConfig;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.linkage.service.LinkageCityService;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.service.CityService;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.pmls.storeMaintenance.service.PmlsStoreMaintenanceService;

/**
 * desc:门店维护
 * @author :zhenggang.Huang
 * @date   :2020年7月10日
 */
@RestController
@RequestMapping(value = "pmlsStoreMaintenance")
public class PmlsStoreMaintenanceController extends BaseReportController {
	
    //日志
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private PmlsStoreMaintenanceService pmlsStoreMaintenanceService;
    
    @Resource(name = "linkageCityService")
    private LinkageCityService linkageCityService;
    
    @Resource(name = "cityService")
    CityService cityService;
    
    /**
     * desc:门店维护-初始化
     * 2020年7月10日
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, ModelMap mop) {
    	ModelAndView mv = new ModelAndView("storeMaintenance/storeMaintenanceList");
    	
    	String cityNo = UserInfoHolder.get().getCityNo();
    	mv.addObject("acCityNo", cityNo);

    	if (StringUtil.isNotEmpty(cityNo)) {
            try {
                // 根据城市获取所属中心-维护中心
                Integer typeId = GroupConfig.getLevelTypeIds()[0];
                Integer typeId2 = GroupConfig.getLevelTypeIds()[1];
                ResultData<?> resultGroupList = linkageCityService.getGroupList(cityNo, typeId, typeId2);
                mv.addObject("groupList", resultGroupList.getReturnData());
            }catch (Exception e) {
                logger.error("pmlsStoreMaintenance", "PmlsStoreMaintenanceController", "", "",
                        UserInfoHolder.getUserId(), "", "门店维护-根据城市获取所属中心失败", e);
            }
    	}
    	try {
            // 查询isService=1的城市-所在城市
            ResultData<?> reback = cityService.queryCityListByIsService();
            List<?> citylist = (List<?>) reback.getReturnData();
            mv.addObject("citylist", citylist);
        } catch (Exception e) {
            logger.error("pmlsStoreMaintenance", "PmlsStoreMaintenanceController", "", "", null, "", "门店维护-查询所在城市失败", e);
        }

        return mv;
    }

    /**
     * desc:列表
     * 2020年7月10日
     */
    @RequestMapping(value = "queryList", method = RequestMethod.POST)
    public ResultData<?> queryList(HttpServletRequest request) {

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        logger.info("门店维护，查询列表开始。入参:"+reqMap.toString());

        PageInfo pageInfo = getPageInfo(request);
        
        reqMap.put("userId", UserInfoHolder.getUserId());
        reqMap.put("cityNo", UserInfoHolder.get().getCityNo());

        //页面数据
        ResultData<?> reback = null;

        try {
            //获取页面显示数据
            reback = pmlsStoreMaintenanceService.queryList(reqMap, pageInfo);
            logger.info("门店维护，查询列表结束。入参:"+reqMap.toString()+",结果集:"+reback);
        } catch (Exception e) {
            logger.error("pmlsStoreMaintenance", "PmlsStoreMaintenanceController", "queryList", "", null, "", "门店维护-查询列表失败", e);
        }
        return reback;
    }
    
    /**
     * 
     * desc:批量变更维护人
     * 2020年7月8日
     */
    @RequestMapping(value = "batchChangeMaintenance", method = RequestMethod.POST)
    public ReturnView<?, ?> batchChangeMaintenance(HttpServletRequest request, ModelMap modelMap)
    {
    	Map<String, Object> reqMap = bindParamToMap(request);
    	logger.info("门店维护-批量变更维护人，入参:"+reqMap);
        UserInfo userInfo = UserInfoHolder.get();
        reqMap.put("userId", userInfo.getUserId());
        reqMap.put("userCode", userInfo.getUserCode());
        reqMap.put("userName", userInfo.getUserName());
        reqMap.put("cityNo", userInfo.getCityNo());
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try
        {
        	ResultData<?> resultData = pmlsStoreMaintenanceService.batchChangeMaintenance(reqMap);
            logger.info("门店维护-批量变更维护人，维护成功！,维护人:"+reqMap.get("maintainerName"));
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        }
        catch (Exception e)
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "批量变更维护人失败!");
            logger.error("pmlsStoreMaintenance", "PmlsStoreMaintenanceController", "batchChangeMaintenance", "", null,"", "", e);
        }
        return getOperateJSONView(rspMap);
    }
    
}
