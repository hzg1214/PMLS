package cn.com.eju.deal.base.file.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.dto.file.UploadInfoDto;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;

/**   
* 文件渠道管理系统--渠道信息-Service
* @author (li_xiaodong)
* @date 2016年2月2日 下午9:30:27
*/
@Service("uploadInfoService")
public class UploadInfoService extends BaseService<UploadInfoDto>
{
    
//    private final static String REST_SERVICE = SystemCfg.getString("filesRestServer");
    
    /** 
    * 文件上传-获取渠道信息
    * @param reqMap
    * @return
    * @throws Exception
    */
    public ResultData<?> getChannelInfo(Map<String, Object> reqMap)
        throws Exception
    {
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("FileServiceRestServer") + "/upload/channelInfo/{param}";
        
        ResultData<?> reback = get(url, reqMap);
        
        return reback;
        
    }
    
}
