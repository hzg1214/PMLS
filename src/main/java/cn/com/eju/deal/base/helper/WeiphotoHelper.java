package cn.com.eju.deal.base.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.com.eju.deal.base.file.handle.WeiphotoHandle;
import cn.com.eju.deal.base.file.util.WeiphotoUtil;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.ImageCompressUtil;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnMsg;
import cn.com.eju.deal.core.util.StringUtil;

/**   
* weiphoto文件上传--获取文件路径、文件下载 
* @author (li_xiaodong)
* @date 2016年5月28日 下午4:46:55
*/
public class WeiphotoHelper
{
    /**
     * 日志
     */
    private static final LogHelper logger = LogHelper.getLogger(WeiphotoHelper.class);
    
    /**
     * http://183.136.160.249:2080/weiphoto/upload_pic
     */
    //    private static final String WEIPHOTO_SERVICE = "http://183.136.160.249:2080/";
    //    private static final String WEIPHOTO_SERVICE = "http://183.136.160.248:2081/";
    //    private static final String WEIPHOTO_SERVICE = "http://183.136.160.248/";
    
    /**
    * http://183.136.160.249:2080/weiphoto/upload_pic
    */
    //private static final String UPLOAD_URL = "weiphoto/upload_pic";
    
    /**
    * http://183.136.160.249:2080/pic/{pid}/{size}
    */
    //    private static final String PATH_URL = "pic/%s/%s";
    
    /** 
    * 文件上传
    * @param request(文件上传是否预处理isHandle(非必填){width、height、cutType、waterPosition、waterPic} (多文件上传用途标示("fileSign")) )
    * @return {returnCode=200,returnMsg=上传文件成功, data=[{returnCode=200, returnMsg=上传文件成功, fileCode=BHME1db22b048ab9b4b97cc743ffcc67e338.jpg,
    *  fileSign="**",fileName="**" ,
    *  pic_path=/BHME/Source_pic/18/d3/BHME1db22b048ab9b4b97cc743ffcc67e338.jpg,pic_id=BHME1db22b048ab9b4b97cc743ffcc67e338.jpg}]
    *  }
    */
    public static Map<String, Object> upload(HttpServletRequest request, String securitykey, String appId,
        String uploadUrl, String fileNo)
    {
        
        //总返回Map
        Map<String, Object> rpMap = new HashMap<String, Object>();
        
        //返回数据list
        List<Map<String, Object>> rspList = new ArrayList<Map<String, Object>>();
        
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver =
            new CommonsMultipartResolver(request.getSession().getServletContext());
        
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request))
        {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            
            while (iter.hasNext())
            {
                MultipartFile importFile = multiRequest.getFile(iter.next());
                
                //文件名
                String fileName = importFile.getOriginalFilename();
                
                File tempFile = null;
                try
                {
                    //实例化类
                    WeiphotoUtil weiphotoHelper = new WeiphotoUtil();
                    
                    //获取绝对路径
                    String absolutePath = weiphotoHelper.getTmpRealPath(fileName, request);
                    
                    //如果上传图片大于3M，进行图片压缩，否则直接上传
                    if (importFile.getSize() > 3500000)
                    {
                        //MultipartFile转File
                        CommonsMultipartFile cf = (CommonsMultipartFile)importFile;
                        DiskFileItem fi = (DiskFileItem)cf.getFileItem();
                        File temp = fi.getStoreLocation();
                        //图片压缩到文件绝对路径,生成临时文件
                        Boolean boo = ImageCompressUtil.getInstance().imageCompressRatio(temp, absolutePath, 0.5d);
                        if (!boo)
                        {
                            logger.error("WeiphotoHelper",
                                "upload",
                                "imageCompressRadio",
                                "",
                                UserInfoHolder.getUserId(),
                                "",
                                "压缩图片出错",
                                null);
                            throw new Exception("压缩图片出错");
                        }
                        //删除临时文件
                        temp.delete();
                        tempFile = new File(absolutePath);
                    }
                    else
                    {
                        //生成临时文件
                        tempFile = new File(absolutePath);
                        importFile.transferTo(tempFile);
                    }
                    //上传操作
                    rspMap = WeiphotoHandle.uploadPic(uploadUrl, absolutePath, fileName, fileNo, securitykey, appId);
                    
                    if (tempFile != null)
                    {
                        //删除临时文件
                        tempFile.delete();
                    }
                    
                    //上传返回失败
                    
                    //接口返回状态
                    String apistatus = (String)rspMap.get("apistatus");
                    
                    //接口结果返回
                    Map<String, Object> resultMap = (Map<String, Object>)rspMap.get("result");
                    String picId = (String)resultMap.get("picid");
                    
                    if (StringUtil.isEmpty(picId))
                    {
                        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                        rspMap.put(Constant.RETURN_MSG_KEY, ReturnMsg.ERROR_UPLOAD_FILE_MSG);
                        rspMap.put("fileSign", importFile.getName());
                        rspMap.put("fileName", fileName);
                        rspList.add(rspMap);
                        
                        //只要有一个上传失败，设置返回失败吗
                        rpMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                        rpMap.put(Constant.RETURN_MSG_KEY, "调用weiphoto接口异常");
                        rpMap.put(Constant.RETURN_MSG_KEY, ReturnMsg.ERROR_UPLOAD_FILE_MSG);
                        
                        return rpMap;
                    }
                    
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
                    rspMap.put(Constant.RETURN_MSG_KEY, ReturnMsg.SUCCESS_UPLOAD_FILE_MSG);
                    rspMap.put("fileSign", importFile.getName());
                    rspMap.put("fileName", fileName);
                    rspMap.put("fileCode", picId);
                    rspList.add(rspMap);
                    
                    logger.info("文件上传结果：", rspList);
                    
                }
                catch (FileNotFoundException e)
                {
                    logger.error("", e.getMessage(), e, null);
                }
                catch (IOException e)
                {
                    logger.error("", e.getMessage(), e, null);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        
        rpMap.put("data", rspList);
        
        return rpMap;
    }
    
    /** 
    * 获取文件路径
    * @param fileCode "720-960-42e972556d09a1fdc280f0eb8e391264"
    * @param size 100
    * @return http://183.136.160.249:2080/pic/{pid}/{size}
    */
    public static String getFilePath(String fileUrl, String fileCode, String size)
    {
        String filePath = String.format(fileUrl, fileCode, size);
        
        return filePath;
    }
    
    /** 
    * 文件下载操作
    * @param response
    * @param fileCode 文件码
    * @param downloadFileName 用户自定义下载文件名
    */
    public static void downloadFile(HttpServletResponse response, String fileCode, String downloadFileName)
    {
        try
        {
        }
        catch (Exception e)
        {
            logger.error("", e.getMessage(), e, null);
        }
    }
    
}
