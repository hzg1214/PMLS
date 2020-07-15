package cn.com.eju.deal.store.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import cn.com.eju.deal.base.fesb.FesbService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.helper.SeqNoHelper;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.store.StoreDecorationDto;
import cn.com.eju.deal.oa.service.OaOperatorService;
import cn.com.eju.deal.store.service.StoreDecorationService;


/**
 * 门店装修
 * @author  wushuang
 * @date 2016年8月16日 下午3:48:10
 */
@Controller
@RequestMapping(value = "storeDecoration")
public class StoreDecorationController extends BaseController
{
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "storeDecorationService")
    private StoreDecorationService storeDecorationService;
    
    @Resource(name = "oaOperatorService")
    private OaOperatorService oaOperatorService;

    @Resource(name = "fesbService")
    private FesbService fesbService;
    
    
    /** 
     * 初始化查询门店装修记录
     * @return
     */
    @RequestMapping(value = "toView/{storeId}", method = RequestMethod.GET)
    public String toStoreDecorationRecord(@PathVariable("storeId") String storeId, ModelMap mop)
    {
        Map<String, Object> reqMap = new HashMap<String, Object>();
        
        ResultData<?> resultData = new ResultData<>();
        reqMap.put("storeId", storeId);
        String isOperator = new String();
        try
        {
            //验证当前人是否是经办人
            String userCode = UserInfoHolder.get().getUserCode();
            //查询是否是经办人
            ResultData<?> backResult = oaOperatorService.getByUserCode(userCode);
            Map<?, ?> mapTemp = (Map<?, ?>)backResult.getReturnData();
            String operCode = (String)mapTemp.get("operCode");
            if(StringUtil.isNotEmpty(operCode)){
                isOperator = "yes";
            }else{
                isOperator = "no";
            }
            
            //根据storeId查询装修记录
            resultData = storeDecorationService.getStoreDecoration(reqMap);
        }
        catch (Exception e)
        {
            logger.error("Store", "StoreDecorationController", "toStoreDecorationRecord", "parameter = "+ storeId, null, null, "初始化查询门店装修记录", e);
            resultData.setFail();
        }
        if(ReturnCode.SUCCESS.equals(resultData.getReturnCode())){
            
            List<Map<?,?>> maptemp = (List<Map<?, ?>>)resultData.getReturnData();
            if(maptemp.size() > 0){
                mop.put("upToDateDecorationStatus", maptemp.get(0).get("upToDateDecorationStatus"));
                mop.put("contractStatus", maptemp.get(0).get("contractStatus"));
            }
            mop.put("content", resultData.getReturnData());
        }else{
            resultData.setFail();
            logger.error("Store", "StoreDecorationController", "toStoreDecorationRecord", "parameter = "+ storeId, null, null, "初始化查询门店装修记录", null);
        }
        
        mop.put("isOperator", isOperator);
        mop.put("storeId", storeId);
        return "store/storeDecorationRecord";
    }
    
    /** 
     * 申请装修,保存到门店装修表并返回数据调用OMS接口
     * @return
     */
    @RequestMapping(value = "toAdd/{storeId}/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> toOMSStoreDecoration(HttpServletRequest request, @PathVariable("storeId") Integer storeId,@PathVariable("userId") Integer userId, ModelMap mop)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        ResultData<?> resultData = new ResultData<>();
        //创建门店装修记录到store_Decoration
        StoreDecorationDto storeDecorationDto  = new StoreDecorationDto();
        storeDecorationDto.setStoreId(storeId);
        storeDecorationDto.setUserCreate(userId);
        storeDecorationDto.setDateCreate(new Date());
        String decorationNo = SeqNoHelper.getInstance().getSeqNoByTypeCode("TYPE_STOREDECORATION");
        storeDecorationDto.setDecorationNo(decorationNo);//门店装修编号
        storeDecorationDto.setDelFlag(false);
       
        //返回装修数据
        try
        {
            resultData = storeDecorationService.addSecondRecord(storeDecorationDto);
            Object num = resultData.getReturnData();
            //调用OMS
            if(null == num || Integer.valueOf(num.toString()) == 0) {
            	rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
            	rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            	return getOperateJSONView(rspMap);
            }
        }
        catch (Exception e)
        {
            logger.error("Store", "StoreDecorationController", "toOMSStoreDecoration", "resultData = "+ resultData.toString(), null, null, "再次插入装修数据出错",e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
        }
        return getOperateJSONView(rspMap);
    }
    
}
