package cn.com.eju.deal.fangyou.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.fangyou.FangyouInfoDto;

/**
 * Created by Sky on 2016/4/5.
 * 房友接口服务层
 */
@Service("fangyouService")
public class FangyouService extends BaseService<FangyouInfoDto> {
    //private final static String REST_CONTRACT = SystemCfg.getString("fangyouRestServer");

    /**
     * 查询账号列表
     *
     * @param reqMap   查询条件
     * @param pageInfo 分页条件
     * @return 查询列表
     * @throws Exception 请求的异常信息
     */
    public ResultData<?> accountList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "fangyouApi" + "/" + reqMap.get("companyId") + "/employee";

        ResultData<?> apiResult = get(url, reqMap, pageInfo);

        return apiResult;
    }

    /**
     * 修改密码
     *
     * @param requestMap 请求参数
     * @return 修改结果
     */
    public void changePassword(Map<String, Object> requestMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "fangyouApi" + "/" + requestMap.get("companyId") + "/password/";

        FangyouInfoDto fangyouInfoDto = new FangyouInfoDto();

        fangyouInfoDto.setAdminPassword(requestMap.get("newPassword").toString());

        put(url, fangyouInfoDto);
    }
    
    /**
     * 查询房友账号是否存在
     */
    public ResultData<?> getFangyou(Map<String, Object> reqMap) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "fangyouApi" + "/" + reqMap.get("companyId") + "/employee";

        ResultData<?> apiResult = get(url, reqMap);

        return apiResult;
    }
}














