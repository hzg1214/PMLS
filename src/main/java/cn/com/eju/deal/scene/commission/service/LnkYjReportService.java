package cn.com.eju.deal.scene.commission.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;

/**
 * desc:返佣维护
 * @author :zhenggang.Huang
 * @date   :2019年4月28日
 */
@Service("lnkYjReportService")
public class LnkYjReportService extends BaseService
{
    
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    /**
     * desc:列表
     * 2019年4月29日
     * author:zhenggang.Huang
     */
    public ResultData<?> getLnkYjReportList(Map<String,Object> reqMap,PageInfo pageInfo) throws Exception
    {
    	 String url = SystemParam.getWebConfigValue("RestServer") + "lnkYjReport" + "/getLnkYjReportList/{param}";
    	 
    	  ResultData<?> reback = get(url, reqMap, pageInfo);
          
          return reback;
    }
    
    /**
     * desc:查看
     * 2019年4月29日
     * author:zhenggang.Huang
     */
    public ResultData getYjReportDeatilById(Map<String,Object> map) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "lnkYjReport" + "/getYjReportDeatilById/{param}";
        ResultData<?> backResult = get(url, map);
        return backResult;
    }
    
    /**
     * desc:保存返佣对象
     * 2019年4月30日
     * author:zhenggang.Huang
     */
    public ResultData saveMaintenance(Map<String, Object> reqMap) throws Exception {
		String url = SystemParam.getWebConfigValue("RestServer") + "lnkYjReport" + "/saveMaintenance";

        ResultData<?> reback = post(url,reqMap);
		return reback;
	}
    
    /**
     * desc:保存编辑后返佣对象
     * 2019年4月30日
     * author:zhenggang.Huang
     */
    public ResultData saveEditMaintenance(Map<String, Object> reqMap) throws Exception {
    	String url = SystemParam.getWebConfigValue("RestServer") + "lnkYjReport" + "/saveEditMaintenance";
    	
    	ResultData<?> reback = post(url,reqMap);
    	return reback;
    }

    /**
     * desc:模糊查询公司
     * 2019年5月5日
     * author:zhenggang.Huang
     */
    public ResultData<?> getCompanyByCondition(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "lnkYjReport" + "/getCompanyByCondition/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }

}
