package cn.com.eju.deal.base.file.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.dto.file.FileDto;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;

/**   
* 文件渠道管理系统-Service
* @author (li_xiaodong)
* @date 2016年2月2日 下午9:30:27
*/
@Service("filesService")
public class FilesService extends BaseService<FileDto>
{
    
//    private final static String SystemParam.getWebConfigValue("FileServiceRestServer") = SystemCfg.getString("filesRestServer");
    
    /** 
     * 查询-根据fileNo
     * @param id
     * @return
     * @throws Exception
     */
    public ResultData<?> getByFileNo(String fileNo)
        throws Exception
    {
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("FileServiceRestServer") + "/fileNo/{fileNo}";
        
        ResultData<?> backResult = get(url, fileNo);
        
        return backResult;
    }
    
    /** 
    * 更新
    * @param studentDto
    * @return
    * @throws Exception
    */
    public void update(FileDto fileDto)
        throws Exception
    {
        
        String url = SystemParam.getWebConfigValue("FileServiceRestServer") + "";
        
        put(url, fileDto);
        
    }
    
    /** 
    * 删除
    * @param id
    * @param updateId
    * @return
    * @throws Exception
    */
    public void deleteByFileNo(String fileNo, int operateId)
        throws Exception
    {
        
        String url = SystemParam.getWebConfigValue("FileServiceRestServer") + "/fileNo/{fileNo}/{operateId}";
        
        delete(url, fileNo, operateId);
        
    }
    
    /** 
     * 查询-获取渠道配置
     * @param id
     * @return
     * @throws Exception
     */
    public ResultData<?> getFileConfig(String channelCode)
        throws Exception
    {
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("FileServiceRestServer") + "/config";
        
        ResultData<?> backResult = get(url);
        
        return backResult;
    }
    
    public void downloadFile(HttpServletResponse response, String fileName, String fileUrl) {

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        HttpURLConnection httpUrl = null;
        URL url = null;
        // 建立链接
        try {
            fileUrl = fileUrl.replace("_thum_50", "");
            fileName += ".jpg";
            response.reset();// 清空输出流
            // 下面是对中文文件名的处理
            response.setCharacterEncoding("UTF-8");// 设置相应内容的编码格式
            fileName = new String(fileName.getBytes("Gbk"), "iso-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setContentType("application/msexcel");// 定义输出类型
            url = new URL(fileUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            // 连接指定的资源

            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.flush();
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
                httpUrl.disconnect();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
    
}
