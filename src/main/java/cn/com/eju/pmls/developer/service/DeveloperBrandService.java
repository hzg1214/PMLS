package cn.com.eju.pmls.developer.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.developer.dto.DeveloperBrandDto;

@Service("developerBrandService")
public class DeveloperBrandService extends BaseService {
    //获取开发商品牌列表
    public ResultData<?> getDeveloperBrandList(Map<String,Object> reqMap, PageInfo pageInfo) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "developerBrand" + "/getDeveloperBrandList/{param}";
        ResultData<?> reback = get(url, reqMap, null);
        return reback;
    }
    //获取开发商品牌信息
    public ResultData<DeveloperBrandDto> getDeveloperBrandInfo(Map<String,Object> reqMap, PageInfo pageInfo) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "developerBrand" + "/getDeveloperBrandInfo/{param}";
        ResultData<DeveloperBrandDto> reback = get(url, reqMap, pageInfo);
        return reback;
    }
    //新增开发商品牌
    public ResultData<?> addDeveloperBrand(DeveloperBrandDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "developerBrand" + "/addDeveloperBrand";
        ResultData<?> reback = post(url, dto);
        return reback;
    }
    //修改开发商品牌
    public ResultData<?> updateDeveloperBrand(DeveloperBrandDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "developerBrand" + "/updateDeveloperBrand";
        ResultData<?> reback = post(url, dto);
        return reback;
    }
    //删除开发商品牌
    public ResultData<?> deleteDeveloperBrand(DeveloperBrandDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "developerBrand" + "/deleteDeveloperBrand";
        ResultData<?> reback = post(url, dto);
        return reback;
    }
    //修改开发商品牌
    public ResultData<?> updateCheck(DeveloperBrandDto dto) throws Exception{
    	String url = SystemParam.getWebConfigValue("RestServer") + "developerBrand" + "/updateCheck";
    	ResultData<?> reback = post(url, dto);
    	return reback;
    }

    public ResultData<?> getDeveloperBrandListByPage(Map<String,Object> reqMap, PageInfo pageInfo) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "developerBrand" + "/getDeveloperBrandListByPage/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }
}
