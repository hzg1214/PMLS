package cn.com.eju.deal.common.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.dto.file.FileDto;
import cn.com.eju.deal.base.dto.file.UploadInfoDto;
import cn.com.eju.deal.base.file.service.FilesService;
import cn.com.eju.deal.base.file.service.UploadInfoService;
import cn.com.eju.deal.base.helper.FileHelper;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.helper.WeiphotoHelper;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.base.util.UploadFileUtil;
import cn.com.eju.deal.common.service.FileRecordMainService;
import cn.com.eju.deal.core.support.*;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/** 
* @ClassName: FileRecordMainController 
* @Description: 文件controller
* @author 陆海丹
* @date 2016年3月25日 下午3:46:30 
*/
@Controller
@RequestMapping(value = "file")
public class FileRecordMainController extends BaseController
{
    @Resource(name = "fileService")
    private FileRecordMainService fileService;
    
    @Resource(name = "uploadInfoService")
    private UploadInfoService uploadInfoService;
    
    @Resource(name = "filesService")
    private FilesService filesService;
    
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    /**
     * 删除文件
     * @return URL
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ReturnView<?, ?> delFile(@PathVariable int id, HttpServletResponse response)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        try
        {
            //删除
            this.fileService.delete(id);
        }
        catch (Exception e)
        {
            logger.error("FileRecordMain", "FileRecordMainController", "delFile", "", null, "", "", e);
        }
        //响应码
        int status = response.getStatus();
        rspMap.put(Constant.RETURN_CODE_KEY, status);
        return getOperateJSONView(rspMap);
    }
    
    /**
     * 文件下载
     */
    @RequestMapping("/downloadFile")
    public void downloadFile(Integer fileRecordMainId, HttpServletResponse response, String downloadUrl, String permitCode)
    {
        this.fileService.downloadFile(response, fileRecordMainId, null, downloadUrl, permitCode);
    }
    
    /** 
     * 改造上传方法--
     * 1、上传判定渠道，文件传往CRIC或weiphoto，拿到fileCode,存储在渠道主表，返回fileNo；
     * 2、存储fileNo到关系表fileRecordMain，返回记录主键fileRecordMainId，设到form表单；
     * @param request--fileTypeId,fileSourceId,refId,fileId,fileFullName,companyId(可选)
     * @param modelMap
     * @return     
     */
    @RequestMapping(value = "uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> uploadFileNew(HttpServletRequest request, ModelMap modelMap)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        
        //文件关联主键
        //String oldFileRecordMainId = (String)reqMap.get("fileRecordMainId");
        String oldFileNo = (String)reqMap.get("fileNo");
        
        //TODO 1、上传判定渠道，文件传往CRIC或weiphoto，拿到fileCode,存储在渠道主表，返回fileNo；
        
        //请求Map
        Map<String, Object> reqMp = new HashMap<String, Object>();
        reqMp.put("systemCode", SystemCfg.getString("systemCode"));
        
        //页面数据
        UploadInfoDto uploadInfoDto = new UploadInfoDto();
        
        //获取渠道信息
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
            logger.error("FileRecordMain",
                "FileRecordMainController",
                "uploadFileNew",
                "",
                UserInfoHolder.getUserId(),
                "",
                "获取上传渠道异常",
                e);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "获取上传渠道异常");
            return getOperateJSONView(rspMap);
        }
        
        //获取渠道配置信息
        Map<?, ?> mapTemp = null;
        try
        {
            ResultData<?> reback = filesService.getFileConfig(null);
            
            //页面数据
            mapTemp = (Map<?, ?>)reback.getReturnData();
            if (null == mapTemp)
            {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "获取渠道配置异常");
                return getOperateJSONView(rspMap);
            }
            
        }
        catch (Exception e)
        {
            logger.error("FileRecordMain",
                "FileRecordMainController",
                "uploadFileNew",
                "",
                UserInfoHolder.getUserId(),
                "",
                "获取渠道配置异常",
                e);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "获取渠道配置异常");
            return getOperateJSONView(rspMap);
        }
        
        //接口Code
        String channelCode = uploadInfoDto.getChannelCode();
        
        //文件Id
        Integer fileId = uploadInfoDto.getFileId();
        
        //文件No
        String fileNo = uploadInfoDto.getFileNo();
        
        //上传返回
        Map<String, Object> upMap = new HashMap<String, Object>();
        
        //weiphoto文件系统
        if ("weiphoto".equals(channelCode))
        {
            String securitykey = (String)mapTemp.get("wp_securitykey");
            String appId = (String)mapTemp.get("wp_H-appId");
            String uploadUrl = (String)mapTemp.get("wp_upload_url");
            
            upMap = WeiphotoHelper.upload(request, securitykey, appId, uploadUrl, fileNo);
        }
        //CRICUpload
        else if ("CRIC".equals(channelCode))
        {
            String permitCode = (String)mapTemp.get("CRIC_file_permit_code");
            String fileCategory = (String)mapTemp.get("CRIC_file_category");
            String uploadUrl = (String)mapTemp.get("CRIC_uploadfile_path");
            
            upMap = FileHelper.uploadFile(request, permitCode, fileCategory, uploadUrl);
        }
        
        List<Map<String, Object>> rspList = (ArrayList<Map<String, Object>>)upMap.get("data");
        
        if (null != rspList && !rspList.isEmpty())
        {
            //创建关系记录返回
            ResultData<?> resultData = null;
            
            FileRecordMainDto fileRecordMainDto = null;
            
            Integer fileTypeId = Integer.valueOf((String)reqMap.get("fileTypeId"));
            Integer fileSourceId = Integer.valueOf((String)reqMap.get("fileSourceId"));
            String companyIdStr = (String)reqMap.get("companyId");
            Integer companyId = null;
            if (StringUtil.isNotEmpty(companyIdStr))
            {
                companyId = Integer.valueOf(companyIdStr);
            }
            
            for (Map<String, Object> map : rspList)
            {
                String returnCode = (String)map.get("returnCode");
                
                if (returnCode == ReturnCode.SUCCESS)
                {
                    //1、更新文件渠道表记录
                    
                    String fileCode = (String)map.get("fileCode");
                    
                    //更新文件渠道表
                    FileDto fileDto = new FileDto();
                    //设置文件主键
                    fileDto.setFileId(fileId);
                    //设置文件code
                    fileDto.setFileCode(fileCode);
                    //设置文件状态
                    fileDto.setFileState(10002);
                    //设置上传时间
                    fileDto.setUploadTime(new Date());
                    
                    try
                    {
                        filesService.update(fileDto);
                    }
                    catch (Exception e)
                    {
                        logger.error("FileRecordMain",
                            "FileRecordMainController",
                            "uploadFileNew filesService.update(fileDto)",
                            "",
                            UserInfoHolder.getUserId(),
                            "",
                            "更新文件渠道表记录异常",
                            e);
                        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                        rspMap.put(Constant.RETURN_MSG_KEY, "更新文件渠道表记录异常");
                        return getOperateJSONView(rspMap);
                    }
                    
                    //2、创建文件关系记录
                    fileRecordMainDto = new FileRecordMainDto();
                    fileRecordMainDto.setCompanyId(companyId);
                    fileRecordMainDto.setFileFullName((String)map.get("fileName"));
                    //fileRecordMainDto.setFileId(fileCode);
                    fileRecordMainDto.setFileSourceId(fileSourceId);
                    fileRecordMainDto.setFileTypeId(fileTypeId);
                    fileRecordMainDto.setUserCreate(UserInfoHolder.getUserId());
                    fileRecordMainDto.setFileNo(fileNo);
                    
                    try
                    {
                        resultData = fileService.create(fileRecordMainDto);
                        if (resultData.getReturnCode().equals(ReturnCode.SUCCESS))
                        {
                            Map<String, Object> rebackData = (Map<String, Object>)resultData.getReturnData();
                            rspMap.put("fileRecordMainId", rebackData.get("fileRecordMainId"));
                        }
                    }
                    catch (Exception e)
                    {
                        logger.error("FileRecordMain",
                            "FileRecordMainController",
                            "uploadFileNew fileService.create(fileRecordMainDto)",
                            "",
                            UserInfoHolder.getUserId(),
                            "",
                            "更新文件渠道表记录异常",
                            e);
                        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                        rspMap.put(Constant.RETURN_MSG_KEY, "创建关系记录异常");
                        return getOperateJSONView(rspMap);
                    }
                    
                    //设置返回文件No
                    rspMap.put("fileNo", fileNo);
                    
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
                        "FileRecordMainController",
                        "uploadFileNew deleteByFileNo",
                        "",
                        UserInfoHolder.getUserId(),
                        "",
                        "",
                        e);
                    
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    rspMap.put(Constant.RETURN_MSG_KEY, "上传删除文件渠道系统的原文件记录失败");
                    return getOperateJSONView(rspMap);
                }
            }
            
            //删除模块文件关系记录
//            if (StringUtil.isNotEmpty(oldFileRecordMainId))
//            {
//                try
//                {
//                    fileService.delete(Integer.valueOf(oldFileRecordMainId));
//                }
//                catch (Exception e)
//                {
//                    logger.error("file",
//                        "FileController",
//                        "uploadFile fileService.delet",
//                        "",
//                        UserInfoHolder.getUserId(),
//                        "",
//                        "上传删除模块文件关系记录失败",
//                        e);
//                    
//                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
//                    rspMap.put(Constant.RETURN_MSG_KEY, "上传删除模块文件关系记录失败");
//                    return getOperateJSONView(rspMap);
//                }
//            }
//            
        }
        else
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "上传文件失败");
            return getOperateJSONView(rspMap);
        }
        
        return getOperateJSONView(rspMap);
    }
    
    /** 
     * 改造上传方法--
     * 1、上传判定渠道，文件传往CRIC或weiphoto，拿到fileCode,存储在渠道主表，返回fileNo；
     * 2、存储fileNo，fileCode，设到form表单；
     * @param request-
     * @param modelMap
     * @return     
     */
    @RequestMapping(value = "uploadFile/linkage", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> uploadFileForLinkage(HttpServletRequest request, ModelMap modelMap)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        
        //文件关联主键
        String oldFileRecordMainId = (String)reqMap.get("fileRecordMainId");
        String oldFileNo = (String)reqMap.get("fileNo");
        
        //TODO 1、上传判定渠道，文件传往CRIC或weiphoto，拿到fileCode,存储在渠道主表，返回fileNo；
        
        //请求Map
        Map<String, Object> reqMp = new HashMap<String, Object>();
        reqMp.put("systemCode", SystemCfg.getString("systemCode"));
        
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
            logger.error("FileRecordMain",
                "FileRecordMainController",
                "uploadFileNew",
                "",
                UserInfoHolder.getUserId(),
                "",
                "",
                e);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "获取上传渠道异常");
            return getOperateJSONView(rspMap);
        }
        
        //获取渠道配置信息
        Map<?, ?> mapTemp = null;
        try
        {
            ResultData<?> reback = filesService.getFileConfig(null);
            
            //页面数据
            mapTemp = (Map<?, ?>)reback.getReturnData();
            if (null == mapTemp)
            {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "获取渠道配置异常");
                return getOperateJSONView(rspMap);
            }
            
        }
        catch (Exception e)
        {
            logger.error("FileRecordMain",
                "FileRecordMainController",
                "uploadFileNew",
                "",
                UserInfoHolder.getUserId(),
                "",
                "获取渠道配置异常",
                e);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "获取渠道配置异常");
            return getOperateJSONView(rspMap);
        }
        
        
        //渠道Code
        String channelCode = uploadInfoDto.getChannelCode();
        
        //文件Id
        Integer fileId = uploadInfoDto.getFileId();
        
        //文件No
        String fileNo = uploadInfoDto.getFileNo();
        
        //上传返回
        Map<String, Object> upMap = new HashMap<String, Object>();
        
        //weiphoto文件系统
        if ("weiphoto".equals(channelCode))
        {
            String securitykey = (String)mapTemp.get("wp_securitykey");
            String appId = (String)mapTemp.get("wp_H-appId");
            String uploadUrl = (String)mapTemp.get("wp_upload_url");
            
            upMap = WeiphotoHelper.upload(request, securitykey, appId, uploadUrl, fileNo);
        }
        //CRICUpload
        else if ("CRIC".equals(channelCode))
        {
            String permitCode = (String)mapTemp.get("CRIC_file_permit_code");
            String fileCategory = (String)mapTemp.get("CRIC_file_category");
            String uploadUrl = (String)mapTemp.get("CRIC_uploadfile_path");
            
            upMap = FileHelper.uploadFile(request, permitCode, fileCategory, uploadUrl);
        }
        
        List<Map<String, Object>> rspList = (ArrayList<Map<String, Object>>)upMap.get("data");
        
        if (null != rspList && !rspList.isEmpty())
        {
            //创建关系记录返回
            for (Map<String, Object> map : rspList)
            {
                String returnCode = (String)map.get("returnCode");
                
                if (returnCode == ReturnCode.SUCCESS)
                {
                    //1、更新文件渠道表记录
                    
                    String fileCode = (String)map.get("fileCode");
                    
                    //更新文件渠道表
                    FileDto fileDto = new FileDto();
                    //设置文件主键
                    fileDto.setFileId(fileId);
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
                        logger.error("FileRecordMain",
                            "FileRecordMainController",
                            "uploadFileNew filesService.update(fileDto)",
                            "",
                            UserInfoHolder.getUserId(),
                            "",
                            "更新文件渠道表记录异常",
                            e);
                        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                        rspMap.put(Constant.RETURN_MSG_KEY, "更新文件渠道表记录异常");
                        return getOperateJSONView(rspMap);
                    }
                    
                    //2、创建文件关系记录
                    //设置返回fileCode/fileNo
                    rspMap.put(Constant.RETURN_DATA_KEY, fileCode + "|" + fileNo);
                    
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
                        "FileRecordMainController",
                        "uploadFileNew deleteByFileNo",
                        "",
                        UserInfoHolder.getUserId(),
                        "",
                        "",
                        e);
                    
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    rspMap.put(Constant.RETURN_MSG_KEY, "上传删除文件渠道系统的原文件记录失败");
                    return getOperateJSONView(rspMap);
                }
            }
            
            //删除模块文件关系记录
            if (StringUtil.isNotEmpty(oldFileRecordMainId))
            {
                try
                {
                    fileService.delete(Integer.valueOf(oldFileRecordMainId));
                }
                catch (Exception e)
                {
                    logger.error("file",
                        "FileController",
                        "uploadFile fileService.delet",
                        "",
                        UserInfoHolder.getUserId(),
                        "",
                        "上传删除模块文件关系记录失败",
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
            rspMap.put(Constant.RETURN_MSG_KEY, "上传文件失败");
            return getOperateJSONView(rspMap);
        }
        
        return getOperateJSONView(rspMap);
    }


    @RequestMapping(value = "upload/linkage", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> uploadForLinkage(HttpServletRequest request, ModelMap modelMap){

        ReturnView<?, ?> returnView = new ReturnView<>();

        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        UploadFileUtil uploadFileUtil = new UploadFileUtil();

        ResultData resultData = null;
        try {
            resultData = uploadFileUtil.upload(request);
            returnView.setReturnValue(resultData.getReturnData());
            returnView.setReturnCode(resultData.getReturnCode());
            returnView.setReturnMsg(resultData.getReturnMsg());
        } catch (Exception e) {
            returnView.setFail();
            returnView.setReturnMsg("上传图片异常");
            logger.error("FileRecordMain", "FileRecordMainController", "upload",
                    "input param: reqMap=" + reqMap,
                    UserInfoHolder.getUserId(), null, "上传图片异常", e);
        }

        return returnView;
    }

    /**
     * (保存FIL_FileRecordMain)
     * 将文件生成到服务器指定的路径中，返回其路径
     * @return URL
     */
    @RequestMapping("/uploadCommonFile")
    @ResponseBody
    public ReturnView<?, ?> uploadCommonFile(HttpServletRequest request)
	{
		// 返回map
		Map<String, Object> rspMap = new HashMap<String, Object>();

		// 获取请求参数
		Map<String, Object> reqMap = bindParamToMap(request);

		Integer userId = UserInfoHolder.getUserId();
		try {
//			String oldFileRecordMainId = (String) reqMap.get("fileRecordMainId");
			Integer fileTypeId = Integer.valueOf((String) reqMap.get("fileTypeId"));
			Integer fileSourceId = Integer.valueOf((String) reqMap.get("fileSourceId"));
			String companyIdStr = (String) reqMap.get("companyId");

			// 上传附件
			UploadFileUtil uploadFileUtil = new UploadFileUtil();
			ResultData uploadData = uploadFileUtil.upload(request);
			if (!ReturnCode.SUCCESS.equals(uploadData.getReturnCode())) {
				rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
				rspMap.put(Constant.RETURN_MSG_KEY, uploadData.getReturnMsg());
				return getOperateJSONView(rspMap);
			}

			// 创建关系记录返回
			ResultData<?> resultData = null;

			FileRecordMainDto fileRecordMainDto = null;

			Integer companyId = null;
			if (StringUtil.isNotEmpty(companyIdStr)) {
				companyId = Integer.valueOf(companyIdStr);
			}
			Map<String,Object> marketFileMap = (Map) uploadData.getReturnData();
			
			String fileName=String.valueOf(marketFileMap.get("fileName"));
			// 2、创建文件关系记录
			fileRecordMainDto = new FileRecordMainDto();
			fileRecordMainDto.setCompanyId(companyId);
			// fileRecordMainDto.setFileId(fileCode);
			fileRecordMainDto.setFileSourceId(fileSourceId);
			fileRecordMainDto.setFileTypeId(fileTypeId);
			fileRecordMainDto.setUserCreate(UserInfoHolder.getUserId());
			fileRecordMainDto.setDateCreate(new Date());
			fileRecordMainDto.setIsDelete(false);
			fileRecordMainDto.setFileAbbrUrl(String.valueOf(marketFileMap.get("picUrl_20_percent")));
			fileRecordMainDto.setFileFullName(fileName);
			fileRecordMainDto.setFileNo("");
			fileRecordMainDto.setFileSuffix(String.valueOf(marketFileMap.get("fileSuffix")));
			fileRecordMainDto.setFileUrl(String.valueOf(marketFileMap.get("picUrl_500")));
			fileRecordMainDto.setSfImage(StringUtil.isAvaliableFileFmt(fileName, SystemCfg.getString("uploadPicSuffix")));
			fileRecordMainDto.setRefId(reqMap.get("refId") != null?Integer.valueOf(reqMap.get("refId").toString()):null);
			fileRecordMainDto.setSellFileNo(String.valueOf(marketFileMap.get("file_id")));
			fileRecordMainDto.setUrl50(String.valueOf(marketFileMap.get("picUrl_50_percent")));
			fileRecordMainDto.setUserCreate(userId);

			try {
				resultData = fileService.create(fileRecordMainDto);
				if (resultData.getReturnCode().equals(ReturnCode.SUCCESS)) {
					Map<String, Object> rebackData = (Map<String, Object>) resultData.getReturnData();
					rspMap.put("fileRecordMainId", rebackData.get("fileRecordMainId"));
				}


				rspMap.put("fileName", fileName);
				rspMap.put("isImage", fileRecordMainDto.getSfImage());
				rspMap.put("flag", true);
				rspMap.put("fileNo", String.valueOf(marketFileMap.get("file_id")));
				rspMap.put("fileIcon", "default.png");
				rspMap.put("fileUrl", String.valueOf(marketFileMap.get("picUrl_500")));
				rspMap.put("fileAbbrUrl", String.valueOf(marketFileMap.get("picUrl_20_percent")));
				rspMap.put("url50", String.valueOf(marketFileMap.get("picUrl_50_percent")));
				rspMap.put("fileSuffix", String.valueOf(marketFileMap.get("fileSuffix")));

			} catch (Exception e) {
				logger.error("FileRecordMain", "FileRecordMainController",
						"uploadFileNew fileService.create(fileRecordMainDto)", "", UserInfoHolder.getUserId(), "",
						"更新文件渠道表记录异常", e);
				rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
				rspMap.put(Constant.RETURN_MSG_KEY, "创建关系记录异常");
				return getOperateJSONView(rspMap);
			}

			// 如果是文件重编辑，需要删除原文件
			/*if (null != fileRecordMainDto && fileRecordMainDto.getFileRecordMainId() != null) {
				if (StringUtil.isNotEmpty(oldFileRecordMainId)) {
					// 删除文件渠道系统的原文件记录
					try {
						fileService.delete(StringUtil.toInteger(oldFileRecordMainId));// 删除已有的
					} catch (Exception e) {
						logger.error("file", "FileRecordMainController", "uploadFileNew deleteByFileNo", "",
								UserInfoHolder.getUserId(), "", "", e);

						rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
						rspMap.put(Constant.RETURN_MSG_KEY, "上传删除文件渠道系统的原文件记录失败");
						return getOperateJSONView(rspMap);
					}
				}
			}*/
		} catch (Exception e) {
			// TODO: handle exception
			rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
			rspMap.put(Constant.RETURN_MSG_KEY, "上传图片异常");
			logger.error("file", "FileRecordMainController", "uploadCommonFile", null, userId, null, "上传图片异常", e);
		}
		return getOperateJSONView(rspMap);
	}

    /**
     * 删除文件公共方法(1:文件是存在FIL_FileRecordMain表的页面;2:合同报备页面) 如果1和2都不满足 请扩充此方法  
     * @return URL
     */
    @RequestMapping("/delFileCommon")
    @ResponseBody
    public ReturnView<?, ?>  delFileCommon(HttpServletRequest request)
	{
		Map<String, Object> rspMap = new HashMap<String, Object>();
		try {
			// 获取请求参数
			Map<String, Object> map = bindParamToMap(request);
			String fileNo = (String) map.get("fileNo");
			if (map.get("fileRecordMainId") != null && !map.get("fileRecordMainId").toString().equals("")) {// 文件是存在FIL_FileRecordMain表的页面
				Integer fileRecordMainId = Integer.valueOf(map.get("fileRecordMainId").toString());
				fileService.delete(fileRecordMainId);
			}
			/*if (fileNo != null) {
				filesService.deleteByFileNo(fileNo, UserInfoHolder.getUserId());
			}*/
			rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
			rspMap.put(Constant.RETURN_MSG_KEY, "删除图片成功!");
		} catch (Exception e) {
			logger.error("file", "FileRecordMainController", "delFileCommon", null, UserInfoHolder.getUserId(), null, "删除图片异常",
					e);
			rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
			rspMap.put(Constant.RETURN_MSG_KEY, "上传图片异常");
		}
		return getOperateJSONView(rspMap);
	}
    
}
