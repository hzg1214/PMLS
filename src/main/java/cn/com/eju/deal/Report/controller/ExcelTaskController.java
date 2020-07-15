package cn.com.eju.deal.Report.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import cn.com.eju.deal.Report.model.ExcelTask;
import cn.com.eju.deal.Report.service.ExcelTaskService;
import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.base.util.DownloadFileUtil;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnView;

/**   
* Controller层
* @author guopengfei
* @date 2017年5月12日 下午9:25:30
*/
@Controller
@RequestMapping(value = "excelTask")
public class ExcelTaskController extends BaseController
{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
	@Resource
	private ExcelTaskService excelTaskService;
    
    
    /** 
    * 初始化
    * @param request
    * @param model
    * @return
    */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap mop)
    {
        return "Report/excelTask/excelTaskList";
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
		// 获取请求参数
		Map<String, Object> reqMap = bindParamToMap(request);
		reqMap.put("createUserId", UserInfoHolder.getUserId());
		PageInfo pageInfo = getPageInfo(request);

        //获取页面显示数据
        List<?> reportlist = new ArrayList<>();
		try {
			ResultData<?> reback =  excelTaskService.queryList(reqMap, pageInfo);
			
            //页面数据
            reportlist = (List<?>)reback.getReturnData();

		} catch (Exception e) 
		{
			logger.error("ExcelTask", "ExcelTaskController", "index", "", UserInfoHolder.getUserId(), "", "导出文件列表查询失败", e);
		}
		
        //存放到mop中
        mop.addAttribute("reportlist", reportlist);
		

        return "Report/excelTask/excelTaskListCxt";        

    }
    
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public ReturnView delete(HttpServletRequest request,HttpServletResponse response) throws SQLServerException 
	{
		Map<String, Object> map = bindParamToMap(request);
		String name = map.get("name").toString();
		int id = Integer.valueOf(map.get("id").toString());
		ExcelTask excelTask = new ExcelTask();
		excelTask.setId(id);
		excelTask.setDownloadurl(name);
		Map<?, ?> reback = new HashMap<String, Object>();
		try {
			reback = excelTaskService.delete(excelTask);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getOperateJSONView(reback);
	}
	
	@RequestMapping(value = "/export", method = RequestMethod.POST)
	@ResponseBody
	public void export(HttpServletRequest request,
			HttpServletResponse response) throws SQLServerException {
		System.out.println(request.getParameter("filePath"));
		Map<String, Object> map = bindParamToMap(request);
		String fileName = map.get("fileName").toString();
		String filePath = map.get("filePath").toString();
		File file=new File(filePath);
		DownloadFileUtil.DownloadFile(response, fileName, DownloadFileUtil.ISDELFILE_NO, file);
	}    
    

}
