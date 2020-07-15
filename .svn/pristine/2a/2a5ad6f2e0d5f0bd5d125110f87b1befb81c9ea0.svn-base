package cn.com.eju.pmls.store.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
import cn.com.eju.pmls.store.service.PmlsStoreMaintainerService;
import cn.com.eju.pmls.store.service.PmlsStoreService;

/**   
* 门店维护人关系
*/
@RestController
@RequestMapping(value = "pmlsStoreMaintainer")
public class PmlsStoreMaintainerController extends BaseController
{
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "pmlsStoreMaintainerService")
    private PmlsStoreMaintainerService storeMaintainerService;
    
    @Resource(name = "pmlsStoreService")
    private PmlsStoreService pmlsStoreService;

    
    /**
     * 查询门店维护人历史
     */
	@RequestMapping(value = "queryMaintainerHis/{storeId}", method = RequestMethod.POST)
	public ResultData<?> querylist(@PathVariable("storeId") Integer storeId,HttpServletRequest request, ModelMap mop) {
		//获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = new ResultData<>();
        PageInfo pageInfo = getPageInfo(request);
        try
        {
        	reqMap.put("storeId", storeId);
        	reqMap.put("isNew", "1");
            resultData = storeMaintainerService.getStoreMaintainerHisList(reqMap, pageInfo);
        }
        
        catch (Exception e)
        {
            logger.error("store", "PlmsStoreMaintainerController", "queryMaintainerHis", "", null, "", "", e);
        }
		return resultData;
	}
	
	/**
	 * desc:保存维护人
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
        try
        {
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
                pmlsStoreService.updateMtcToStore(storeDto);
                logger.info("门店-门店详情-选择维护人，保存成功！storeId:"+storeIdStr+",维护人:"+maintainerName);
                rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
                rspMap.put(Constant.RETURN_MSG_KEY, "分配维护人成功");
        }
        catch (Exception e)
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "保存维护人失败!");
            logger.error("store", "PmlsStoreMaintainerController", "savemt", "", null,"", "", e);
        }
        return getOperateJSONView(rspMap);
    }

}
