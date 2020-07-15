package cn.com.eju.deal.houseLinkage.custom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.contract.ContractInfoDto;
import cn.com.eju.deal.dto.houseLinkage.custom.CustomInfoDto;
import cn.com.eju.deal.houseLinkage.custom.service.CustomService;
import cn.com.eju.deal.houseLinkage.estate.service.EstateService;

/**   
* Controller层
* @author qianwei
* @date 2016年4月29日 下午9:25:30
*/
@Controller
@RequestMapping(value = "custom")
public class CustomController extends BaseController
{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "customService")
    private CustomService customService;
    
    @Resource(name = "estateService")
    private EstateService estateService;
    
    /** 
    * 初始化
    * @param request
    * @param model
    * @return
    */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap mop, HttpServletResponse response)
    {
        // 进度列表
        ResultData<?> resultProcessList = new ResultData<>();
        try
        {
            resultProcessList = customService.getProcessList();
        }
        catch (Exception e)
        {
            logger.warn("store toupdate getProcessList failed");
        }
        mop.put("processList", resultProcessList.getReturnData());
        
        //读取搜索条件 add by wangkanlin 2017/08/23
        Map<String, Object> map = bindParamToMap(request);
        if(map.containsKey("searchParam") && "1".equals(map.get("searchParam"))){
            Map<String,Object> searchParamMap = getRememberSearch(request, ComConstants.CUSTOM_LIST_SEARCH);
            mop.put("searchParamMap", JSON.toJSON(searchParamMap));
        }else{
            clearSearch(request, response, ComConstants.CUSTOM_LIST_SEARCH);
        }
        mop.put("list_search_page", ComConstants.CUSTOM_LIST_SEARCH);
      //得到归属项目部 add by zhenggang.Huang 2019/07/12 begin
        List<?> rebacklist = null;
        try{
        	Map<String,Object> reqMap = new HashMap<>();
        	String cityNo = UserInfoHolder.get().getCityNo();
        	reqMap.put("cityNo", cityNo);
        	ResultData<?> resultData = estateService.getProjectDepartment(reqMap);
        	rebacklist = (List<?>) resultData.getReturnData();
        }catch(Exception e)
        {
        	 logger.error("houseLinkage.report", "ReportController", "list", "", null, "", "创建--初始化-归属项目部", e);
        }
        mop.put("rebacklist", rebacklist);
        //end
        return "houseLinkage/custom/customList";
    }
    
    /** 
    * 查询--list
    * @param request
    * @param mop
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "q", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap mop, HttpServletResponse response)
    {
        
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        
        PageInfo pageInfo = getPageInfo(request);
        
        try
        {
        	//保存搜索数据 add by wangkanlin 2017-08-23
            if (reqMap.containsKey("searchParmCache") && "1".equals(reqMap.get("searchParmCache"))) {
                rememberSearch(request, response, ComConstants.CUSTOM_LIST_SEARCH, reqMap);
            }
            //获取页面显示数据
            ResultData<?> reback = customService.index(reqMap, pageInfo);
            
            //页面数据
            List<?> contentlist = (List<?>)reback.getReturnData();
            
            //存放到mop中
            mop.addAttribute("contentlist", contentlist);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage", "CustomController", "index", "", null, "", "", e);
        }
        
        return "houseLinkage/custom/customListCtx";
    }
    
    /** 
    * 创建--初始化
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "c", method = RequestMethod.GET)
    public String toAdd(ModelMap mop)
    {
        //返回map
        ResultData<?> resultData = new ResultData<ContractDto>();
        UserInfo userInfo = UserInfoHolder.get();
        return "houseLinkage/custom/customAdd";
    }
    
    /**
     *  创建
     *  @param request
     * @throws Exception 
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> create(HttpServletRequest request, ModelMap modelMap)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        
        ContractInfoDto contractInfoDto = new ContractInfoDto();
        try
        {
            //获取页面字段值，转为DTO
            setDto(reqMap, contractInfoDto, "create", "");
            ResultData<?> resultData = customService.create(contractInfoDto);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        }
        catch (Exception e)
        {
            logger.error("houseLinkage", "CustomController", "create", "", null, "", "", e);
        }
        
        return getOperateJSONView(rspMap);
    }
    
    /** 
    * 修改--初始化
    * @param id
    * @param mop
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/{u}/{id}", method = RequestMethod.GET)
    public String toEdit(@PathVariable("u") String u, @PathVariable("id") int id, ModelMap mop)
    {
        Map<String, Object> map = new HashMap<>();
        //返回map
        ResultData<?> resultData = new ResultData<ContractDto>();
        
        try
        {
            resultData = customService.getById(id);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage", "CustomController", "toEdit", "", null, "", "", e);
        }
        
        //存放到mop中
        mop.addAttribute("customInfo", resultData.getReturnData());
        return "houseLinkage/custom/customEdit";
    }
    
    /**
     *  修改 
     *  @param request
     * @throws Exception 
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnView<?, ?> update(HttpServletRequest request, @RequestParam(value = "isform", required = false) String isform, @PathVariable("id") String id)
    {
        
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.remove("_method");
        ContractInfoDto contractInfoDto = new ContractInfoDto();
        try
        {
            setDto(reqMap, contractInfoDto, "update", id);
            //更新
            customService.update(contractInfoDto);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage", "CustomController", "update", "", null, "", "", e);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        
        return getOperateJSONView(rspMap);
    }
    
    /** 
    * 查看
    * @param id
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/{estateId}/{companyId}/{customerId}", method = RequestMethod.GET)
    public String show(@PathVariable("estateId") String estateId, @PathVariable("companyId") String companyId, @PathVariable("customerId") String customerId, ModelMap mop)
    {
        //返回map
        ResultData<?> resultData = new ResultData<CustomInfoDto>();
        
        try
        {
            resultData = customService.getCustom(estateId, companyId, customerId);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage", "CustomController", "show", "", null, "", "", e);
        }
        
        //存放到mop中
        mop.addAttribute("customInfo", resultData.getReturnData());
        
        return "houseLinkage/custom/customDetail";
    }
    
    /** 
    * 删除
    * @param id
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnView<?, ?> destroy(@PathVariable int id, HttpServletResponse response)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        //获取当前操作人usreId
        int updateId = 0;
        
        try
        {
            updateId = UserInfoHolder.getUserId();
        }
        catch (Exception e)
        {
            logger.error("houseLinkage", "CustomController", "destroy", "", null, "", "", e);
        }
        
        try
        {
            //删除
            customService.delete(id, updateId);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage", "CustomController", "destroy", "", null, "", "", e);
        }
        
        //响应码
        int status = response.getStatus();
        
        rspMap.put(Constant.RETURN_CODE_KEY, status);
        
        return getOperateJSONView(rspMap);
        
    }
    
    /** 
     * 获取页面字段值，转为DTO
     * @param reqMap
     * @param contractInfoDto
     */
    public static void setDto(Map<String, Object> reqMap, ContractInfoDto contractInfoDto, String type, String id)
        throws Exception
    {
        UserInfo userInfo = UserInfoHolder.get();
        
    }
}
