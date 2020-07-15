package cn.com.eju.deal.student.controller;

import java.util.ArrayList;
import java.util.Date;
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
import cn.com.eju.deal.base.fesb.FesbService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.student.StudentDto;
import cn.com.eju.deal.student.service.StudentService;

/**   
* Controller层
* @author li_xiaodong
* @date 2016年2月2日 下午9:25:30
*/
@Controller
@RequestMapping(value = "students")
public class StudentController extends BaseController
{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "studentService")
    private StudentService studentService;
    
    @Resource(name = "fesbService")
    private FesbService fesbService;
    
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
//            reback = studentService.index(reqMap, pageInfo);
            
            
            reback = fesbService.httpGet(reqMap, pageInfo, "CRM-student-list");
            
            
            //页面数据
            contentlist = (List<?>)reback.getReturnData();
            
        }
        catch (Exception e)
        {
            logger.error("Student", "StudentController", "index", "", 0, "", "", e);
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
        
        StudentDto studentDto = new StudentDto();
        
        try
        {
            setDto(reqMap, studentDto);
        }
        catch (Exception e1)
        {
            logger.error("Student", "StudentController", "create", "", 0, "", "", e1);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            return getOperateJSONView(rspMap);
        }
        
        try
        {
//            ResultData<?> resultData = studentService.create(studentDto);
            
            studentDto.setUpdateTime(new Date());
            
            String resourceCode = "CRM-student-create";
            
            ResultData<?> resultData = fesbService.httpPost(studentDto, resourceCode);
            
            
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        }
        catch (Exception e)
        {
            logger.error("Student", "StudentController", "create", "", 0, "", "", e);
            
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
            resultData = studentService.getById(id);
        }
        catch (Exception e)
        {
            logger.error("Student", "StudentController", "toEdit", "", 0, "", "", e);
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
    public ReturnView<?, ?> update(HttpServletRequest request,
        @RequestParam(value = "isform", required = false) String isform, @PathVariable("id") String id)
    {
        
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        
        reqMap.remove("_method");
        
        StudentDto studentDto = new StudentDto();
        
        try
        {
            studentDto.setId(Integer.valueOf(id));
            
            setDto(reqMap, studentDto);
        }
        catch (Exception e1)
        {
            logger.error("Student", "StudentController", "update", "", 0, "", "", e1);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            return getOperateJSONView(rspMap);
        }
        
        try
        {
            //更新
            studentService.update(studentDto);
        }
        catch (Exception e)
        {
            
            logger.error("Student", "StudentController", "update", "", 0, "", "", e);
            
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
            resultData = studentService.getById(id);
        }
        catch (Exception e)
        {
            logger.error("Student", "StudentController", "show", "", 0, "", "", e);
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
            int updateId = UserInfoHolder.getUserId();
            
            //删除
            studentService.delete(id, updateId);
        }
        catch (Exception e)
        {
            logger.error("Student", "StudentController", "destroy", "", 0, "", "", e);
            
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
    private void setDto(Map<String, Object> reqMap, StudentDto studentDto)
        throws Exception
    {
        String stuName = (String)reqMap.get("stuName");
        studentDto.setStuName(stuName);
        
        String stuNoStr = (String)reqMap.get("stuNo");
        if (StringUtil.isNotEmpty(stuNoStr))
        {
            studentDto.setStuNo(Integer.valueOf(stuNoStr));
        }
        
        String stuAgeStr = (String)reqMap.get("stuAge");
        if (StringUtil.isNotEmpty(stuAgeStr))
        {
            studentDto.setStuAge(Integer.valueOf(stuAgeStr));
        }
        
    }
    
}
