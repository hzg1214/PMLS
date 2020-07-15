package cn.com.eju.deal.storeRelocation.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.helper.SeqNoHelper;
import cn.com.eju.deal.base.linkage.service.LinkageCityService;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.service.CityService;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.oa.service.OaOperatorService;
import cn.com.eju.deal.storeRelocation.service.StoreRelocationService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 门店迁址
 */
@Controller
@RequestMapping(value = "storeRelocation")
public class StoreRelocationController extends BaseController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private StoreRelocationService storeRelocationService;

    @Resource(name = "cityService")
    private CityService cityService;

    @Resource(name = "oaOperatorService")
    private OaOperatorService oaOperatorService;
    
    @Resource(name = "linkageCityService")
	private LinkageCityService linkageCityService;

    /**
     * check该门店是否可以迁址
     * @param request
     * @return
     */
    @RequestMapping(value = "checkDecorationStatus/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String checkDecorationStatus(HttpServletRequest request, @PathVariable Integer id) {
        ResultData<?> resultData = new ResultData<>();
        try {
            resultData = storeRelocationService.checkDecorationStatus(id);
        } catch (Exception e) {
            resultData.setFail("check门店装修状态失败");
            logger.error("storeRelocation", "StoreRelocationController", "checkDecorationStatus"
           		 , id.toString(), null, "", "check门店装修状态失败", e);
        }
        return resultData.toString();
    }
    /**
     * check该门店是否可以迁址
     * @param request
     * @return
     */
    @RequestMapping(value = "checkStoreAddress", method = RequestMethod.GET)
    @ResponseBody
    public String checkStoreAddress(HttpServletRequest request) {
    	ResultData<?> resultData = new ResultData<>();
    	// 获取map
     	Map<String, Object> reqMap = bindParamToMap(request);
    	try {
    		resultData = storeRelocationService.checkStoreAddress(reqMap);
    	} catch (Exception e) {
    		resultData.setFail("check门店地址失败");
    		logger.error("storeRelocation", "StoreRelocationController", "checkStoreAddress"
    				, reqMap.toString(), null, "", "check门店地址失败", e);
    	}
    	return resultData.toString();
    }
    /**
     * 跳转新增门店迁址
     * @return
     */
    @RequestMapping(value = "toAddStoreRelocation/{storeId}/{contractId}/{contractStatus}",method = RequestMethod.GET)
    public ModelAndView toAddChange(@PathVariable("storeId") Integer storeId,
    		@PathVariable("contractId") Integer contractId,@PathVariable("contractStatus") Integer contractStatus)	{
        ModelAndView modelAndView = new ModelAndView("storeRelocation/storeRelocationAdd");
        try {
            //查询isService=1的城市
            ResultData<?> reback = cityService.queryCityListByIsService();
            List<?> citylist = (List<?>) reback.getReturnData();
            modelAndView.addObject("citylist", citylist);
            modelAndView.addObject("contractStatus", contractStatus);
        } catch (Exception e) {
            logger.error("storeRelocation", "StoreRelocationController", "toAddStoreRelocation", "contractId="+contractId+",storeId="+storeId,
                    UserInfoHolder.getUserId(), "", "查询城市列表失败！", e);
       }
        try{
            ResultData<?> info = storeRelocationService.getContractAndStoreInfo(storeId,contractId);
            if(info != null && info.getReturnCode().equals("200")){
            	 modelAndView.addObject("storeRelocation",info.getReturnData());
            }
        }catch (Exception e){
            logger.error("storeRelocation", "StoreRelocationController", "toAddStoreRelocation", "contractId="+contractId+",storeId="+storeId,
                    UserInfoHolder.getUserId(), "", "合同及门店信息查询失败！", e);
        }
        return modelAndView;
    }
    
    /**
     * 保存门店迁址
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveStoreRelocation",method = RequestMethod.POST)
    public ResultData<?> saveStoreRelocation(HttpServletRequest request){
        ResultData<?> resultData = new ResultData<>();
        // 获取map
     	Map<String, Object> reqMap = bindParamToMap(request);
     	reqMap.put("userIdCreate", UserInfoHolder.get().getUserId());
     	reqMap.put("userCode", UserInfoHolder.get().getUserCode());
     	//判断更新或保存
        if(!reqMap.containsKey("id")){//不包含为保存，包含为更新
            String contractStopNo = SeqNoHelper.getInstance().getSeqNoByTypeCode("TYPE_CONTRACTCHANGE");
            reqMap.put("contractStopNo", contractStopNo);
            reqMap.put("type", "create");
        }else {
        	reqMap.put("type", "update");
        }
        try{
           resultData = storeRelocationService.saveStoreRelocation(reqMap);
        }catch (Exception e){
            resultData.setFail("保存门店迁址申请失败");
            logger.error("storeRelocation", "StoreRelocationController", "saveStoreRelocation", "",
                    UserInfoHolder.getUserId(), "", "保存门店迁址申请失败！", e);
        }
        return resultData;
    }
    /**
     * 跳转门店迁址查看页面
     * @param id
     * @param contractId
     * @param contractStatus
     * @return
     */
    @RequestMapping(value = "toView/{id}/{contractId}/{contractStatus}", method = RequestMethod.GET)
    public ModelAndView toView(@PathVariable("id") Integer id,
                         @PathVariable("contractId") int contractId,
                         @PathVariable("contractStatus") Integer contractStatus) {
        ModelAndView modelAndView = new ModelAndView("storeRelocation/storeRelocationDetail");
        modelAndView.addObject("contractStatus", contractStatus);
        ResultData<?> resultData = new ResultData<>();
        try {
            resultData = storeRelocationService.getStoreRelocationById(id);
            if (resultData != null && resultData.getReturnCode().equals("200")) {
                modelAndView.addObject("contractChange", resultData.getReturnData());
            }
        }catch (Exception e){
            resultData.setFail("查询门店迁址失败");
            logger.error("storeRelocation", "StoreRelocationController", "toView", "contractId="+contractId+",id="+id,
                    UserInfoHolder.getUserId(), "", "查询门店迁址失败", e);
        }

        return modelAndView;
    }

    /**
     * 编辑门店迁址页面
     * @param id
     * @param contractId
     * @param contractStatus
     * @return
     */
    @RequestMapping(value = "toStoreRelocation/{id}/{contractId}/{contractStatus}", method = RequestMethod.GET)
    public ModelAndView toStoreRelocation(@PathVariable("id") int id,
                         @PathVariable("contractId") Integer contractId,
                         @PathVariable("contractStatus") Integer contractStatus) {
        ModelAndView modelAndView = new ModelAndView("storeRelocation/storeRelocationEdit");
        modelAndView.addObject("contractStatus", contractStatus);
        try{
            ResultData<?> cityList = cityService.queryCityListByIsService();
            if(cityList != null && "200".equals(cityList.getReturnCode())){
                modelAndView.addObject("citylist",cityList.getReturnData());
            }
        }catch (Exception e){
            logger.error("storeRelocation", "ContractChangeNewController", "toStoreRelocation", "contractId="+contractId+",id="+id,
                    UserInfoHolder.getUserId(), "", "查询城市列表失败", e);
        }

        ResultData<?> resultData = new ResultData<>();
        try {
            resultData = storeRelocationService.getStoreRelocationById(id);
            if (resultData != null && resultData.getReturnCode().equals("200")) {
                modelAndView.addObject("contractChange", resultData.getReturnData());
            }
        }catch (Exception e){
            resultData.setFail("查询门店迁址失败");
            logger.error("storeRelocation", "StoreRelocationController", "toStoreRelocation", "contractId="+contractId+",id="+id,
                    UserInfoHolder.getUserId(), "", "查询门店迁址失败", e);
        }

        return modelAndView;
    }
    /** 
     * @Title: listToSubmitOa
     * @Description: 提交Oa
     * @return
     * @     
     */
    @RequestMapping(value = "/toOaStoreRelocationSubmit/{id}/{contractId}", method = RequestMethod.GET)
    @ResponseBody
    public String toOaStoreRelocationSubmit(@PathVariable("id") Integer id,
   		 @PathVariable("contractId") Integer contractId,
   		 HttpServletRequest request) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.remove("_method");
        reqMap.put("id", id);
        reqMap.put("contractId", contractId);
        reqMap.put("userIdCreate", UserInfoHolder.getUserId());
	    reqMap.put("userCode", UserInfoHolder.get().getUserCode());
	    reqMap.put("userName", UserInfoHolder.get().getUserName());
	    reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
	    reqMap.put("cityName", UserInfoHolder.get().getCityName());
        ResultData toOaStoreRelocationSubmit = null;
         try {
        	 toOaStoreRelocationSubmit = storeRelocationService.storeRelocationSubmitOa(reqMap);
	       	  if(ReturnCode.FAILURE.equals(toOaStoreRelocationSubmit.getReturnCode())) {
	       		  rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
	       		  rspMap.put(Constant.RETURN_MSG_KEY, toOaStoreRelocationSubmit.getReturnMsg());
	       	  }
         } catch (Exception e) {
             logger.error("gpContractChange", "GpContractChangeController", "toOaGPStopSubmit", "", UserInfoHolder.getUserId(), "", "提交OA失败", e);
         }
         return toOaStoreRelocationSubmit.toString();
     }
}
