package cn.com.eju.deal.NoSigned.service;

import java.util.Map;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.Report.dto.BasScheduleDto;

@Service("noSignReportService")
public class NoSignReportService extends BaseService<BasScheduleDto> {
	
	    /**
	     * 查询已签门店明细
	     * @param reqMap
	     * @param pageInfo
	     * @return
	     * @throws Exception
	     */
        public ResultData<?> queryNoSignDetailList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
		//调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "noSignReport" + "/queryNoSignDetailList/{param}";
		

        ResultData<?> reback = get(url, reqMap,pageInfo);

		return reback;

	}
	
}
