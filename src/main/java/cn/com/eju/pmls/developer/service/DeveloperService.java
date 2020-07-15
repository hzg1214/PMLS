package cn.com.eju.pmls.developer.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.developer.dto.DeveloperDto;

@Service("developerService")
public class DeveloperService extends BaseService {
	
    //获取开发商管理列表
    public ResultData<?> getDeveloperList(Map<String,Object> reqMap, PageInfo pageInfo) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "developer" + "/getDeveloperList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }

    //获取开发商信息
    public ResultData<DeveloperDto> getDeveloperInfo(DeveloperDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "developer" + "/getDeveloperInfo";
        ResultData<DeveloperDto> reback = post(url, dto);
        return reback;
    }
    
    //获取开发商信息
    public ResultData<DeveloperDto> getDeveloperInfo2(DeveloperDto dto) throws Exception{
    	String url = SystemParam.getWebConfigValue("RestServer") + "developer" + "/getDeveloperInfo2";
    	ResultData<DeveloperDto> reback = post(url, dto);
    	return reback;
    }
    
    //判断开发商是否已存在
    public ResultData getDeveloperCountByName(DeveloperDto dto) throws Exception{
    	String url = SystemParam.getWebConfigValue("RestServer") + "developer" + "/getDeveloperCountByName";
    	ResultData reback = post(url, dto);
    	return reback;
    }
    
    //新增开发商
    public ResultData<?> addDeveloper(DeveloperDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "developer" + "/addDeveloper";
        ResultData<?> reback = post(url, dto);
        return reback;
    }
    
    //修改开发商
    public ResultData<?> updateDeveloper(DeveloperDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "developer" + "/updateDeveloper";
        ResultData<?> reback = post(url, dto);
        return reback;
    }
    
    //删除开发商
    public ResultData<?> deleteDeveloper(DeveloperDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "developer" + "/deleteDeveloper";
        ResultData<?> reback = post(url, dto);
        return reback;
    }
    
    //新增开发商和城市的关系
    public ResultData<?> addDeveloperReleaseCity(DeveloperDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "developer" + "/addDeveloperReleaseCity";
        ResultData<?> reback = post(url, dto);
        return reback;
    }

    //根据开发商品牌获取垫佣和大客户
    public ResultData<?> getCustomerAndYjDy(Map<String,Object> reqMap) throws Exception{
    	String url = SystemParam.getWebConfigValue("RestServer") + "developer" + "/getCustomerAndYjDy";
    	ResultData<?> reback = post(url, reqMap);
    	return reback;
    }

}
