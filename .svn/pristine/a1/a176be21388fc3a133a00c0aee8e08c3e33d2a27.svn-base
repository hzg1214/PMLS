package cn.com.eju.deal.otherReport.service;

import java.util.List;
import java.util.Map;


import cn.com.eju.deal.otherReport.model.LnkQtReport;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.util.BaseUtils;
import cn.com.eju.deal.core.support.ResultData;

/**
 * @author :zhenggang.Huang
 * @date :2019年10月11日
 */
@Service("qtReportService")
public class QtReportService extends BaseService {

	/**
	 * desc:列表
	 * 2019年10月12日
	 * author:zhenggang.Huang
	 */
	public ResultData<?> queryList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {

		 //成本中心
        if (reqMap.containsKey("progress") && reqMap.get("progress") !="") {
        	reqMap.put("progressList", BaseUtils.changeArrayToList((String)reqMap.get("progress"),","));
        }
        reqMap.remove("progress");
		// 调用 接口
		String url = SystemParam.getWebConfigValue("RestServer") + "qtReport" + "/q/{param}";

		ResultData<?> reback = get(url, reqMap, pageInfo);

		return reback;

	}




    /**
     * 查询所有归属城市
     */
    public ResultData<?> getBasCitySettingList()
            throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "qtReport" + "/getBasCitySettingList";

        ResultData<?> backResult = get(url);

        return backResult;
    }


    /**
     * 查询归属城市下的归属中心
     * */
    public ResultData<?> getAchivAchievementLevelSettingList(String cityNo)
            throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "qtReport" + "/getAchivAchievementLevelSettingList/{cityNo}";

        ResultData<?> backResult = get(url,cityNo);

        return backResult;
    }


    /**
     * @Title: createReport
     * @Description: 新增其他收入报备
     */
    public ResultData<?> createReport(LnkQtReport lnkQtReport)
            throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "qtReport" + "/createReport";
        ResultData<?> backResult = post(url, lnkQtReport);//TODO 接口返回的resultData类型不一样
        return backResult;
    }
    
    /**
     * desc:查看详情
     * 2019年10月15日
     * author:zhenggang.Huang
     */
    public ResultData<?> getQtReportById(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "qtReport" + "/getQtReportById/{param}";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }


    /**
     * @Title: createReport
     * @Description: 报备无效
     */
    public ResultData<?> validQtReport(Integer reportId,Integer userId)
            throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "qtReport" + "/validQtReport/{reportId}/{userId}";
        ResultData<?> backResult = get(url,reportId,userId);//TODO 接口返回的resultData类型不一样
        return backResult;
    }
    
    /**
     * desc:业务节点-查看
     * 2019年10月17日
     * author:zhenggang.Huang
     */
    public ResultData<?> getOperDetail(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "qtReport" + "/getOperDetail/{param}";
        ResultData<?> backResult =get(url, reqMap);
        return backResult;
    }
}
