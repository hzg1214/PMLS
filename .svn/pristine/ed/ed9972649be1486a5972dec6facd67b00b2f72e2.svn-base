package cn.com.eju.deal.common.service;

import cn.com.eju.deal.base.file.util.FileAssist;
import cn.com.eju.deal.base.helper.FileHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
* @ClassName: FileRecordMainService 
* @Description: 文件service
* @author 陆海丹
* @date 2016年3月25日 下午3:48:42 
*/
@Service("fileService")
public class FileRecordMainService extends BaseService<FileRecordMainDto>
{
    //private final static String SystemParam.getWebConfigValue("RestServer") + "file" = SystemCfg.getString("fileRestServer");
    
    /** 
     * @Title: delete 
     * @Description: 删除文件
     * @param id
     * @throws Exception     
     */
    public void delete(int id)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "file" + "/{id}";
        delete(url, id);
    }
    
    /** 
    * @Title: downloadFile 
    * @Description: 文件下载
    * @param response
    * @param fileRecordMainId
    * @param downloadFileName     
    */
    public void downloadFile(HttpServletResponse response, Integer fileRecordMainId, String downloadFileName,
        String downloadUrl, String permitCode)
    {
        int id = (int)fileRecordMainId;
        ResultData<?> resultData = new ResultData<>();
        try
        {
            resultData = this.getById(id);
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Map<String, Object> file = (Map<String, Object>)resultData.getReturnData();
        FileHelper.downloadFile(response, String.valueOf(file.get("fileId")), null, downloadUrl, permitCode);
    }
    
    /** 
    * @Title: uploadFile 
    * @Description: 文件上传
    * @param request
    * @param reqMap--fileTypeId,fileSourceId,refId,fileId,fileFullName,companyId(可选)
    * @return     
    */
    public Map<String, Object> uploadFile(HttpServletRequest request, Map<String, Object> reqMap, String permitCode,
        String fileCategory, String uploadUrl, String queryUrl, String downloadUrl)
    {
        Map<String, Object> resultMap = new HashMap<>();
        
        ResultData<?> resultData = new ResultData<>();
        
        FileRecordMainDto fileRecordMainDto = new FileRecordMainDto();
        
        Map<String, Object> upMap = FileHelper.uploadFile(request, permitCode, fileCategory, uploadUrl);
        
        List<Map<String, Object>> rspList = (ArrayList<Map<String, Object>>)upMap.get("data");
        
        //如果原先有图 先删掉
        String oldFileRecordMainId = null;
        if (null != (String)reqMap.get("fileRecordMainId") && !"".equals((String)reqMap.get("fileRecordMainId")))
        {
            oldFileRecordMainId = ((String)reqMap.get("fileRecordMainId")).trim();//获取之前的文件ID
            if (null != oldFileRecordMainId && !oldFileRecordMainId.isEmpty())
            {
                try
                {
                    this.delete(Integer.valueOf(oldFileRecordMainId));
                }
                catch (NumberFormatException e)
                {
                    e.printStackTrace();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        Integer fileTypeId = Integer.valueOf((String)reqMap.get("fileTypeId"));
        Integer fileSourceId = Integer.valueOf((String)reqMap.get("fileSourceId"));
        Integer companyId = null;
        if (null != (String)reqMap.get("companyId") && !"".equals((String)reqMap.get("companyId")))
        {
            companyId = Integer.valueOf((String)reqMap.get("companyId"));
        }
        
        if (null != rspList && !rspList.isEmpty())
        {
            for (Map<String, Object> map : rspList)
            {
                String returnCode = (String)map.get("returnCode");
                if (returnCode == ReturnCode.SUCCESS)
                {
                    fileRecordMainDto = new FileRecordMainDto();
                    
                    String fileId = (String)map.get("fileCode");
                    String fileFullName = (String)map.get("fileName");
                    
                    fileRecordMainDto.setCompanyId(companyId);
                    fileRecordMainDto.setFileFullName(fileFullName);
                    fileRecordMainDto.setFileId(fileId);
                    fileRecordMainDto.setFileSourceId(fileSourceId);
                    fileRecordMainDto.setFileTypeId(fileTypeId);
                    fileRecordMainDto.setUserCreate(UserInfoHolder.getUserId());
                    
                    //获取图片路径
                    String fileUrl = FileHelper.getFilePath(fileId, queryUrl, downloadUrl, permitCode);
                    
                    Map<String, Object> handleMap = new HashMap<>();
                    handleMap.put("width", "100");
                    handleMap.put("height", "100");
                    handleMap.put("waterPic", "0");// 无水印图片
                    handleMap.put("waterPosition", "0");//  3:左下角
                    handleMap.put("cutType", "0");//0-不裁剪
                    String fileAbbrUrl =
                        FileHelper.getFilePath(fileId,
                            FileAssist.UPLOAD_FILE_IS_HANDLE_YES,
                            queryUrl,
                            downloadUrl,
                            permitCode,
                            handleMap);
                    try
                    {
                        resultData = this.create(fileRecordMainDto);
                        if (resultData.getReturnCode().equals(ReturnCode.SUCCESS))
                        {
                            Map<String, Object> file = (Map<String, Object>)resultData.getReturnData();
                            map.put("fileRecordMainId", file.get("fileRecordMainId"));
                            map.put("fileUrl", fileUrl);
                            map.put("fileAbbrUrl", fileAbbrUrl);
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    resultData.setFail();
                    break;
                }
            }
        }
        else
        {
            resultData.setFail();
        }
        
        resultMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        resultMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        if (resultData.getReturnCode().equals(ReturnCode.SUCCESS))
        {
            resultMap.put(Constant.RETURN_DATA_KEY, rspList.get(0));
        }
        return resultMap;
    }
    
    /** 
    * @Title: getById 
    * @Description: 获取文件
    * @param id
    * @return
    * @throws Exception     
    */
    public ResultData<?> getById(int id)
        throws Exception
    {
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "file" + "/{id}";
        
        ResultData<?> backResult = get(url, id);
        
        return backResult;
    }
    
    /** 
    * @Title: create 
    * @Description: 插入记录至文件表
    * @param fileRecordMainDto
    * @return
    * @throws Exception     
    */
    public ResultData<?> create(FileRecordMainDto fileRecordMainDto)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "file" + "";
        
        ResultData<?> backResult = post(url, fileRecordMainDto);
        
        return backResult;
    }
    
    /** 
    * @Title: update 
    * @Description: 更新文件表信息
    * @param fileRecordMainDto
    * @throws Exception     
    */
    public void update(FileRecordMainDto fileRecordMainDto)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "file" + "";
        put(url, fileRecordMainDto);
    }

    /**
     * 获取合同相关的所有文件
     * @param param
     * @return
     * @throws Exception
     */
    public ResultData<?> getContractFile(Map<String,Object> param) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "file" + "/q/contract/{param}";
        ResultData<?> backResult = get(url, param);
        return backResult;
    }

    /**
     * 获取合同变更的文件
     * @param param
     * @return
     * @throws Exception
     */
    public ResultData<?> getContractChangeFile(Map<String,Object> param) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "file" + "/q/contractchange/{param}";
        ResultData<?> backResult = get(url, param);
        return backResult;
    }

    /**
     * 公盘图片
     * @param param
     * @return
     * @throws Exception
     */
    public ResultData<?> getGpContractFile(Map<String,Object> param) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "file" + "/q/gpContract/{param}";
        ResultData<?> backResult = get(url, param);
        return backResult;
    }

    public ResultData<?> getContractChangeNewFile(Map<String,Object> param) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "file" + "/q/contractChangeNew/{param}";
        ResultData<?> backResult = get(url, param);
        return backResult;
    }
}
