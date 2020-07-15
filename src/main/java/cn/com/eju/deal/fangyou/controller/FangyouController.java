package cn.com.eju.deal.fangyou.controller;

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
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.company.service.CompanyService;
import cn.com.eju.deal.contract.service.ContractService;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.fangyou.service.FangyouService;

/**
 * Created by Sky on 2016/4/5.
 * 房友接口
 */
@Controller
@RequestMapping(value = "fangyou")
public class FangyouController extends BaseController
{
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "fangyouService")
    private FangyouService fangyouService;
    
    @Resource(name = "companyService")
    private CompanyService companyService;
    
    @Resource(name = "contractService")
    private ContractService contractService;
    
    /**
     * 账号列表页面
     *
     * @return 列表页面
     */
    @RequestMapping(value = "/account/{companyId}/{userCreate}", method = RequestMethod.GET)
    public String accountList(@PathVariable("companyId") Integer companyId, @PathVariable("userCreate") int userCreate, ModelMap mop)
    {
        mop.addAttribute("companyId", companyId);
        mop.addAttribute("userCreate", userCreate);
        try
        {
            // 查询房友账号
            Map<String, Object> parasMap = new HashMap<String, Object>();
            parasMap.put("companyId", companyId);
            ResultData<?> resultData = fangyouService.getFangyou(parasMap);
            List<?> resultList = (List<?>)resultData.getReturnData();
            
            // 查询公司信息
            ResultData<?> apiResult = companyService.getById(companyId);
            mop.put("companyInfo", apiResult.getReturnData());
            mop.put("accountInfo", resultList);
        }
        catch (Exception e)
        {
            logger.error("Fangyou", "FangyouController", "accountList", "", UserInfoHolder.getUserId(), "", "查询房友账号失败！", e);
        }
        return "fangyou/accountList";
    }
    
    /**
     * 查询--list
     *
     * @param request 加载列表信息
     * @param mop     请求参数
     * @return 列表数据
     * @throws Exception
     */
    @RequestMapping(value = "/account/q/", method = RequestMethod.GET)
    public String loadList(HttpServletRequest request, ModelMap mop)
    {
        Map<String, Object> stringObjectMap = bindParamToMap(request);
        
        PageInfo pageInfo = getPageInfo(request);
        
        try
        {
            ResultData<?> resultData = fangyouService.accountList(stringObjectMap, pageInfo);
            
            List<?> resultList = (List<?>)resultData.getReturnData();
            
            mop.addAttribute("contentlist", resultList);
        }
        catch (Exception e)
        {
            logger.error("Fangyou", "FangyouController", "loadList", "", UserInfoHolder.getUserId(), "", " 加载列表信息失败！", e);
        }
        
        return "fangyou/accountListCtx";
    }
    
    /**
     * 修改密码弹窗
     *
     * @return 修改密码视图
     */
    @RequestMapping(value = "/password/", method = RequestMethod.GET)
    public String passwordIndex()
    {
        return "/fangyou/changePassword";
    }
    
    /**
     * 修改密码提交
     *
     * @return 修改的结果
     */
    @RequestMapping(value = "password/", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnView<?, ?> changePassword(HttpServletRequest request)
    {
        Map<String, Object> requestMap = bindParamToMap(request);
        String newPassword = requestMap.get("newPassword").toString();
        String affirmPassword = requestMap.get("affirmPassword").toString();
        
        Map<String, Object> resultMap = new HashMap<>();
        if (!newPassword.equals(affirmPassword))
        {
            resultMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            return getOperateJSONView(resultMap);
        }
        
        try
        {
            fangyouService.changePassword(requestMap);
        }
        catch (Exception e)
        {
        	resultMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            logger.error("Fangyou", "FangyouController", "changePassword", "", UserInfoHolder.getUserId(), "", "修改密码提交失败！", e);
        }
        return getOperateJSONView(resultMap);
    }
    
    /**
     * 查询房友账号是否存在
     *
     * @param request 加载列表信息
     * @param mop     请求参数
     * @return 列表数据
     * @throws Exception
     */
    @RequestMapping(value = "fyAccount/q", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getFangyou(HttpServletRequest request)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        Map<String, Object> stringObjectMap = bindParamToMap(request);
        try
        {
            ResultData<?> resultData = fangyouService.getFangyou(stringObjectMap);
            List<?> resultList = (List<?>)resultData.getReturnData();
            rspMap.put(Constant.RETURN_DATA_KEY, resultList);
        }
        catch (Exception e)
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            logger.error("Fangyou", "FangyouController", "getFangyou", "", UserInfoHolder.getUserId(), "", "", e);
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getSearchJSONView(rspMap);
    }
    
    /** 
     * 创建房友账号
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "createfy", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> createFy(HttpServletRequest request)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        
        ResultData<?> resultData;
        try
        {
            resultData = contractService.createFangyou(reqMap);
        }
        catch (Exception e)
        {
            logger.error("contract", "ContractController", "createFangyou", "", UserInfoHolder.getUserId(), "", "", e);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            
            rspMap.put(Constant.RETURN_MSG_KEY, "创建房友账号 失败");
            
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        return getOperateJSONView(rspMap);
    }
}
