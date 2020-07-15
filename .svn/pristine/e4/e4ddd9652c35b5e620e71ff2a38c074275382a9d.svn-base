package cn.com.eju.deal.Report.service;
import java.util.Map;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.Report.model.ExcelTask;

@Service("excelTaskService")
public class ExcelTaskService extends BaseService<ExcelTask> {
    //查询列表
	public ResultData<?> queryList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
		// 调用 接口
		String url = SystemParam.getWebConfigValue("RestServer") + "excelTask" + "/queryList/{param}";

		ResultData<?> reback = get(url, reqMap,pageInfo);

		return reback;
		
		
	}
    
    //删除
	public Map<?, ?> delete(ExcelTask t)throws Exception {
		// 调用 接口
	     String url = SystemParam.getWebConfigValue("RestServer") + "excelTask" + "/delete";
     	
		Map<?, ?> backResult = restPost(url, t);
			        
		return backResult;
	}
	
	
}
