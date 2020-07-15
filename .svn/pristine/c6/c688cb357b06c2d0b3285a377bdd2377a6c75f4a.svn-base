package cn.com.eju.pmls.channelBusiness.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.channelBusiness.dto.ChannelBrandDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("channelBrandService")
public class ChannelBrandService extends BaseService {
    //获取渠道品牌列表
    public ResultData<?> getChannelBrandList(Map<String,Object> reqMap, PageInfo pageInfo) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "channelBrandController" + "/getChannelBrandList/{param}";
        ResultData<?> reback = get(url, reqMap, null);
        return reback;
    }
    //获取渠道品牌信息
    public ResultData<ChannelBrandDto> getBrandInfo(Map<String,Object> reqMap, PageInfo pageInfo) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "channelBrandController" + "/getBrandInfo/{param}";
        ResultData<ChannelBrandDto> reback = get(url, reqMap, pageInfo);
        return reback;
    }
    //新增渠道品牌
    public ResultData<?> addBrand(ChannelBrandDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "channelBrandController" + "/addBrand";
        ResultData<?> reback = post(url, dto);
        return reback;
    }
    //修改渠道品牌
    public ResultData<?> updateBrand(ChannelBrandDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "channelBrandController" + "/updateBrand";
        ResultData<?> reback = post(url, dto);
        return reback;
    }
    //删除渠道品牌
    public ResultData<?> deleteBrand(ChannelBrandDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "channelBrandController" + "/deleteBrand";
        ResultData<?> reback = post(url, dto);
        return reback;
    }
}
