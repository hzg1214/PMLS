package cn.com.eju.deal.permission.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("permissionService")
public class PermissionService extends BaseService {
    public ResultData<?> getPermissionList(Map<String, Object> reqMap,PageInfo pageInfo)
            throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "permission" + "/getPermissionList/{param}/";
        ResultData<?> reback = get(url, reqMap,pageInfo);
        return reback;
    }

    public ResultData deletePermission(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "permission" + "/deletePermission";

        ResultData<?> reback = post(url,reqMap);
        return reback;
    }

    public ResultData<?> searchList(Map<String, Object> reqMap)
            throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "permission" + "/searchList/{param}/";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }


    public ResultData savePermission(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "permission" + "/savePermission";

        ResultData<?> reback = post(url,reqMap);
        return reback;
    }
}