package cn.com.eju.deal.base.file.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.dto.file.FileDto;
import cn.com.eju.deal.base.dto.file.UploadInfoDto;
import cn.com.eju.deal.base.file.service.FileRecordService;
import cn.com.eju.deal.base.file.service.FilesService;
import cn.com.eju.deal.base.file.service.UploadInfoService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.support.SystemCfg;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.StringUtil;

/**   
* 文件渠道管理系统-Controller
* @author li_xiaodong
* @date 2016年2月2日 下午9:25:30
*/
@Controller
@RequestMapping(value = "files")
public class FileController extends BaseController
{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "filesService")
    private FilesService filesService;
    
    @Resource(name = "uploadInfoService")
    private UploadInfoService uploadInfoService;
    
    @Resource(name = "fileRecordService")
    private FileRecordService fileRecordService;
    
    
    /** 
    * 文件上传
    * @param request
    * @param modelMap
    * @return
    */
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> uploadFile(HttpServletRequest request, ModelMap modelMap)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        
        String oldFileNo = (String)reqMap.get("fileNo");
        
        //请求Map
        Map<String, Object> reqMp = new HashMap<String, Object>();
        reqMp.put("systemCode", SystemCfg.getString("systemCode"));
        
        //TODO
        //上传先判定渠道
        
        //页面数据
        UploadInfoDto uploadInfoDto = new UploadInfoDto();
        
        //获取页面显示数据
        try
        {
            ResultData<?> reback = uploadInfoService.getChannelInfo(reqMp);
            
            //页面数据
            Map<?, ?> mapTemp = (Map<?, ?>)reback.getReturnData();
            if (null != mapTemp)
            {
                uploadInfoDto = MapToEntityConvertUtil.convert(mapTemp, UploadInfoDto.class, "");
            }
            else
            {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "获取上传渠道异常");
                return getOperateJSONView(rspMap);
            }
            
        }
        catch (Exception e)
        {
            logger.error("file", "FileController", "uploadFile", "", UserInfoHolder.getUserId(), "", "", e);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            return getOperateJSONView(rspMap);
        }
        
        //接口Code
        String intefaceCode = null;
        
        //文件Id
        Integer fileId = uploadInfoDto.getFileId();
        
        //文件No
        String fileNo = uploadInfoDto.getFileNo();
        
        //上传返回
        Map<String, Object> upMap = new HashMap<String, Object>();
        
        //weiphoto文件系统
        if ("weiphotoUpload".equals(intefaceCode))
        {
            //upMap = WeiphotoHelper.upload(request, fileNo);
        }
        //CRICUpload
        else if ("CRICUpload".equals(intefaceCode))
        {
            //upMap = FileHelper.uploadFile(request);
        }
        
        List<Map<String, Object>> rspList = (ArrayList<Map<String, Object>>)upMap.get("data");
        
        if (null != rspList && !rspList.isEmpty())
        {
            for (Map<String, Object> map : rspList)
            {
                String returnCode = (String)map.get("returnCode");
                
                if (returnCode == ReturnCode.SUCCESS)
                {
                    String fileCode = (String)map.get("fileCode");
                    
                    //TODO 更新文件渠道表
                    FileDto fileDto = new FileDto();
                    //
                    fileDto.setFileId(fileId);
                    //设置文件No
                    //fileDto.setFileNo(fileNo);
                    //设置文件code
                    fileDto.setFileCode(fileCode);
                    //设置上传时间
                    fileDto.setUploadTime(new Date());
                    
                    try
                    {
                        filesService.update(fileDto);
                    }
                    catch (Exception e)
                    {
                        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                        return getOperateJSONView(rspMap);
                    }
                    
                    //设置返回文件No
                    rspMap.put(Constant.RETURN_DATA_KEY, fileNo);
                    
                }
                else
                {
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    return getOperateJSONView(rspMap);
                }
            }
            
            //如果是文件重编辑，需要删除原文件
            if (StringUtil.isNotEmpty(oldFileNo))
            {
                
                //删除文件渠道系统的原文件记录
                try
                {
                    filesService.deleteByFileNo(oldFileNo, UserInfoHolder.getUserId());
                }
                catch (Exception e)
                {
                    logger.error("file",
                        "FileController",
                        "uploadFile deleteByFileNo",
                        "",
                        UserInfoHolder.getUserId(),
                        "",
                        "",
                        e);
                    
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    rspMap.put(Constant.RETURN_MSG_KEY, "上传删除文件渠道系统的原文件记录失败");
                    return getOperateJSONView(rspMap);
                }
                
                //删除模块文件关系记录
                try
                {
                    fileRecordService.deleteByFileNo(oldFileNo, UserInfoHolder.getUserId());
                }
                catch (Exception e)
                {
                    logger.error("file",
                        "FileController",
                        "uploadFile deleteByFileNo",
                        "",
                        UserInfoHolder.getUserId(),
                        "",
                        "",
                        e);
                    
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    rspMap.put(Constant.RETURN_MSG_KEY, "上传删除模块文件关系记录失败");
                    return getOperateJSONView(rspMap);
                }
            }
            
        }
        else
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            return getOperateJSONView(rspMap);
        }
        
        return getOperateJSONView(rspMap);
    }
    
    @RequestMapping("/downloadFile")
    public void downloadFile(HttpServletResponse response,String fileName,String fileUrl)
    {
       this.filesService.downloadFile(response, fileName, fileUrl);
    }
}
