package cn.com.eju.deal.store.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.dto.store.StoreMaintainerDto;
import cn.com.eju.deal.store.service.StoreMaintainerService;
import cn.com.eju.deal.store.service.StoreService;

/**   
* 门店维护人关系
* @author 张文辉
* @date 2016年6月7日 下午2:58:57
*/
@Controller
@RequestMapping(value = "storeMaintainer")
public class StoreMaintainerController extends BaseController
{
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "storeMaintainerService")
    private StoreMaintainerService storeMaintainerService;
    
    @Resource(name="storeService")
    private StoreService storeService;
    /**
     * @param request
     * @return
     * @Title: create
     * @Description: 新增门店维护人关系
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ReturnView<?, ?> create(HttpServletRequest request, ModelMap modelMap)
    {
        String storeIdStr = request.getParameter("storeId");
        String maintainer = request.getParameter("maintainer");
        UserInfo userInfo = UserInfoHolder.get();
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try
        {
            StoreMaintainerDto insertMaintainer = new StoreMaintainerDto();
            insertMaintainer.setStoreId(Integer.valueOf(storeIdStr));
            insertMaintainer.setUserCode(maintainer.trim());
            insertMaintainer.setDateCreate(new Date());
            insertMaintainer.setDelFlag("N");
            insertMaintainer.setUserIdCreate(userInfo.getUserId());
            insertMaintainer.setSetUserCode(userInfo.getUserCode());
            insertMaintainer.setSetUserName(userInfo.getUserName());
            ResultData<?> resultData = storeMaintainerService.create(insertMaintainer);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        }
        catch (Exception e)
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            logger.error("store", "StoreMaintainerController", "create", "", null,"", "", e);
        }
        return getOperateJSONView(rspMap);
    }
    
    /** 
     * 查询门店维护人历史
     * @return
     */
    @RequestMapping(value = "/maintainerHis/{storeId}", method = RequestMethod.GET)
    public String toStoreMaintainerHisList(@PathVariable("storeId") Integer storeId, ModelMap mop)
    {
        mop.addAttribute("storeId", storeId);
        
        return "store/storeMaintainerList";
    }
    
    /**
     * 查询门店维护人历史
     */
    @RequestMapping(value = "/qMaintainerHis", method = RequestMethod.GET)
    public String getStoreMaintainerHisList(HttpServletRequest request, ModelMap mop)
    {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = new ResultData<>();
        PageInfo pageInfo = getPageInfo(request);
        try
        {
            resultData = storeMaintainerService.getStoreMaintainerHisList(reqMap, pageInfo);
            resultData.setSuccess();
            mop.put("contentlist", resultData.getReturnData());
        }
        catch (Exception e)
        {
            logger.error("store", "StoreMaintainerController", "getStoreMaintainerHisList", "", null, "", "", e);
        }
        return "store/storeMaintainerListCtx";
    }
    
    /**
     * 门店 分配维护人保存  并且更新到门店
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "savemt", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> saveMaintainer(HttpServletRequest request, ModelMap modelMap)
    {
    	Map<String, Object> reqMap = bindParamToMap(request);
    	logger.info("门店-门店详情-选择维护人，入参:"+reqMap);
        String storeIdStr = request.getParameter("storeId");
        String maintainer = request.getParameter("maintainer");
        String maintainerName = request.getParameter("maintainerName");
        String pmlsCenterId = request.getParameter("pmlsCenterId");
        String pmlsCenterName = request.getParameter("pmlsCenterName");
        UserInfo userInfo = UserInfoHolder.get();
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        Boolean existMtner = true;
        // check 选择的维护人是否当前的维护人[根据storeId和pmlsMaintainCode匹配store表]
//        Map<String,Object> checkMap = new HashMap<String,Object>();
//        checkMap.put("storeId", Integer.valueOf(storeIdStr));
//        checkMap.put("maintainer", maintainer);
//        try {
//        	logger.info("门店-门店详情-选择维护人，维护人CHECK,选择的维护人为:"+maintainerName);
////        	existMtner = storeService.checkMtner(checkMap);
//        	rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
//			rspMap.put(Constant.RETURN_MSG_KEY, "维护人CHECK成功!");
//		} catch (Exception e1) {
//			rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
//			rspMap.put(Constant.RETURN_MSG_KEY, "维护人CHECK失败!");
//            logger.error("store", "storeMaintainer", "checkMtner", "维护人CHECK失败", null,"", "", e1);
//		}
        existMtner = false;
        try
        {
        	// 选择的维护人不是当前维护人
        	if(!existMtner){
        		// 新增维护人
                StoreMaintainerDto insertMaintainer = new StoreMaintainerDto();
                insertMaintainer.setStoreId(Integer.valueOf(storeIdStr));
                insertMaintainer.setUserCode(maintainer.trim());
                insertMaintainer.setDateCreate(new Date());
                insertMaintainer.setDelFlag("N");
                insertMaintainer.setUserIdCreate(userInfo.getUserId());
                insertMaintainer.setSetUserCode(userInfo.getUserCode());
                insertMaintainer.setSetUserName(userInfo.getUserName());
                // 开始维护时间
                insertMaintainer.setDateMtcStart(new Date());
                insertMaintainer.setIsNew("1");
                ResultData<?> resultData = storeMaintainerService.create(insertMaintainer);
                logger.info("门店-门店详情-选择维护人，新增维护人成功！维护人:"+maintainerName);
                rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
                rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
                
                // 更新门店维护人
                StoreDto storeDto = new StoreDto();
                storeDto.setStoreId(Integer.valueOf(storeIdStr));
                storeDto.setPmlsMaintainCode(maintainer);
                storeDto.setPmlsMaintainName(maintainerName);
            	storeDto.setCityNo(UserInfoHolder.get().getCityNo());
            	storeDto.setUserUpdate(UserInfoHolder.get().getUserId());
                storeDto.setIsUpdateCompanyFlag(0);
                storeDto.setPmlsCenterId(Integer.valueOf(pmlsCenterId));
                storeService.updateMtcToStore(storeDto);
                logger.info("门店-门店详情-选择维护人，保存成功！storeId:"+storeIdStr+",维护人:"+maintainerName);
                rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
                rspMap.put(Constant.RETURN_MSG_KEY, "分配维护人成功");
        	}else{
        		 rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
        		 rspMap.put(Constant.RETURN_MSG_KEY, "您分配的人员与当前维护人相同，请选择其他人员！");
        		 logger.info("门店-门店详情-选择维护人，保存维护人，您分配的人员与当前维护人相同！选择的维护人:"+maintainerName);
        	}
        	
        }
        catch (Exception e)
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "保存维护人失败!");
            logger.error("store", "StoreMaintainerController", "create", "", null,"", "", e);
        }
        return getOperateJSONView(rspMap);
    }


    /**
     * 门店维护人的check
     */
    @RequestMapping(value = "/chkmaintainer", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> chkMaintainer(HttpServletRequest request, ModelMap mop)
    {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        Map<String, Object> respMap = new HashMap<>();
        ResultData<?> resultData = null;
        try
        {
            resultData = storeMaintainerService.chkMaintainer(reqMap);
            respMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            respMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        }
        catch (Exception e)
        {
            logger.error("store", "StoreMaintainerController", "chkMaintainer", "", null, "", "", e);
            respMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            respMap.put(Constant.RETURN_MSG_KEY, "检查门店维护人异常");
        }
//        return getOperateJSONView(respMap);
        return getSearchJSONView(respMap);
    }
}
