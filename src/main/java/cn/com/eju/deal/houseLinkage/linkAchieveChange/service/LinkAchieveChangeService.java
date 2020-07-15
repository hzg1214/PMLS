package cn.com.eju.deal.houseLinkage.linkAchieveChange.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 联动业绩调整
 * Created by hzy on 2017/10/23.
 */
@Service("linkAchieveChangeService")
public class LinkAchieveChangeService extends BaseService {

    /**
     * 查询联动业绩调整列表
     * @param reqMap
     * @param pageInfo
     * @return
     * @throws Exception
     */
    public ResultData getLinkAchieveChangeList(Map<String, Object> reqMap, PageInfo pageInfo)throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "linkAchieveChange" + "/getList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;

    }

    /**
     * 保存联动业绩调整信息
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData saveLinkAchieve(Map<String, Object> reqMap)throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "linkAchieveChange" + "/saveLinkAchieve";

        ResultData<?> reback = post(url,reqMap);

        return reback;

    }
    /**
     * 保存联动业绩调整信息
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData saveAchievementAdjut(Map<String, Object> reqMap)throws Exception {
    	
    	String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/saveAchievementAdjut";
    	
    	ResultData<?> reback = post(url,reqMap);
    	
    	return reback;
    	
    }
    /**
     * 保存客户调整信息
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData saveCustomerInfoAdjut(Map<String, Object> reqMap)throws Exception {
    	
    	String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/saveCustomerInfoAdjut";
    	
    	ResultData<?> reback = post(url,reqMap);
    	
    	return reback;
    	
    }
}
