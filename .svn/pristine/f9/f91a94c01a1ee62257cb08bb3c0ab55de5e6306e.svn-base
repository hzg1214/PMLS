package cn.com.eju.deal.followDetail.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.followDetail.FollowDetailDto;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 陆海丹
 * @ClassName: followService
 * @Description: 跟进Service
 * @date 2016年3月29日 下午9:00:04
 */
@Service("followDetailService")
public class FollowDetailService extends BaseService {

    public ResultData<?> query(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "followDetail" + "/query/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }

    public ResultData<?> getMapInfo(Map<String, Object> reqMap) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "followDetail" + "/getMapInfo/{param}";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }

    public ResultData<?> getSignDetail(FollowDetailDto dto) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "followDetail" + "/getSignDetail";
        ResultData<?> reback = post(url, dto);
        return reback;
    }
}
