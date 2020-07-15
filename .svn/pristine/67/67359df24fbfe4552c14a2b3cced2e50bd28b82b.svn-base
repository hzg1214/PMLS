package cn.com.eju.deal.oa.controller;

import java.util.ArrayList;
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

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.dto.oa.OaOperatorDto;
import cn.com.eju.deal.dto.student.StudentDto;
import cn.com.eju.deal.oa.service.OaOperatorService;

/**   
* Controller层
* @author li_xiaodong
* @date 2016年2月2日 下午9:25:30
*/
@Controller
@RequestMapping(value = "oaOperator")
public class OaOperatorController extends BaseController
{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "oaOperatorService")
    private OaOperatorService oaOperatorService;
    
    /** 
    * 初始化
    * @param request
    * @param model
    * @return
    */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap mop)
    {
        return "student/studentList";
    }
    
    /** 
    * 查询--list
    * @param request
    * @param mop
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "q", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap mop)
    {
        
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        
        //页面数据
        List<?> contentlist = new ArrayList<>();
        
        PageInfo pageInfo = getPageInfo(request);
        
        //获取页面显示数据
        ResultData<?> reback;
        try
        {
            reback = oaOperatorService.index(reqMap, pageInfo);
            
            //页面数据
            contentlist = (List<?>)reback.getReturnData();
            
        }
        catch (Exception e)
        {
            logger.error("oa", "OaOperatorController", "index", "", 0, "", "", e);
        }
        
        //存放到mop中
        mop.addAttribute("contentlist", contentlist);
        
        return "student/studentListCtx";
    }
    
    /** 
    * 创建--初始化
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "c", method = RequestMethod.GET)
    public String toAdd()
    {
        
        return "student/studentAdd";
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
        
        OaOperatorDto oaOperatorDto = new OaOperatorDto();
        
        try
        {
            setDto(reqMap, oaOperatorDto);
        }
        catch (Exception e1)
        {
            logger.error("oa", "OaOperatorController", "create", "", 0, "", "", e1);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            return getOperateJSONView(rspMap);
        }
        
        try
        {
            ResultData<?> resultData = oaOperatorService.create(oaOperatorDto);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        }
        catch (Exception e)
        {
            logger.error("oa", "OaOperatorController", "create", "", 0, "", "", e);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            return getOperateJSONView(rspMap);
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
        
        //返回map
        ResultData<?> resultData = new ResultData<StudentDto>();
        
        try
        {
            resultData = oaOperatorService.getById(id);
        }
        catch (Exception e)
        {
            logger.error("oa", "OaOperatorController", "toEdit", "", 0, "", "", e);
        }
        
        //存放到mop中
        mop.addAttribute("studentInfo", resultData.getReturnData());
        
        return "student/studentEdit";
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
        
        OaOperatorDto oaOperatorDto = new OaOperatorDto();
        
        try
        {
            oaOperatorDto.setOperId(Integer.valueOf(id));
            
            setDto(reqMap, oaOperatorDto);
        }
        catch (Exception e1)
        {
            logger.error("oa", "OaOperatorController", "update", "", 0, "", "", e1);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            return getOperateJSONView(rspMap);
        }
        
        try
        {
            //更新
            oaOperatorService.update(oaOperatorDto);
        }
        catch (Exception e)
        {
            
            logger.error("oa", "OaOperatorController", "update", "", 0, "", "", e);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            return getOperateJSONView(rspMap);
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
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Integer id, ModelMap mop)
    {
        //返回map
        ResultData<?> resultData = new ResultData<StudentDto>();
        
        try
        {
            resultData = oaOperatorService.getById(id);
        }
        catch (Exception e)
        {
            logger.error("oa", "OaOperatorController", "show", "", 0, "", "", e);
        }
        
        //存放到mop中
        mop.addAttribute("studentInfo", resultData.getReturnData());
        
        return "student/studentDetail";
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
        
        try
        {
            //获取当前操作人usreId
            
            //删除
            oaOperatorService.delete(id);
        }
        catch (Exception e)
        {
            logger.error("oa", "OaOperatorController", "destroy", "", 0, "", "", e);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            return getOperateJSONView(rspMap);
        }
        
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        
        return getOperateJSONView(rspMap);
        
    }
    
    /** 
    * 转换 map--Dto
    * @param reqMap
    * @param studentDto
    */
    private void setDto(Map<String, Object> reqMap, OaOperatorDto oaOperatorDto)
        throws Exception
    {
        String operName = (String)reqMap.get("operName");
        oaOperatorDto.setOperName(operName);
        
    }
    
}
