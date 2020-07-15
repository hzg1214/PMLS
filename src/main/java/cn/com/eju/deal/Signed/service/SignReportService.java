package cn.com.eju.deal.Signed.service;

import java.util.Map;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.Report.dto.BasScheduleDto;

@Service("signReportService")
public class SignReportService extends BaseService<BasScheduleDto> {
	
	    /**
	     * 查询已签门店明细
	     * @param reqMap
	     * @param pageInfo
	     * @return
	     * @throws Exception
	     */
        public ResultData<?> querySignDetailList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
		//调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "signReport" + "/querySignDetailList/{param}";
		

        ResultData<?> reback = get(url, reqMap,pageInfo);

		return reback;

	}
        
        /**
         * 根据cityID查询中心
         * @param reqMap
         * @return
         * @throws Exception
         */
        public Map<?, ?> getCenterGroupName(Map<String, Object> reqMap)
		        throws Exception
		    {
		        
		        //调用 接口
		        String url = SystemParam.getWebConfigValue("RestServer") + "signReport" + "/getCenterGroupName/{param}";
		        
		        Map<?, ?> reback = index(url, reqMap);

				return reback;
		    }
	
}
