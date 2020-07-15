package cn.com.eju.deal.common.util;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.lang.StringUtils;

/**   
* OA 附件上传下载工具类
* @author (li_xiaodong)
* @date 2016年4月19日 下午3:12:59
*/
public class OaAttachmentUtil
{
    
    /** 
    * OA附件上传方法
    * @param oaUrl
    * @param token
    * @param senderLoginName
    * @param tempFile
    * @return
    * @throws Exception
    */
    public static String oaAbupload(String oaUrl, String token, String senderLoginName, String tempFile, String fileName)
        throws Exception
    {
        //上传方法
        String uploadMethod = "/seeyon/uploadService.do?method=processUploadService";
        
        //上传参数
        String uploadParam = "&senderLoginName=" + senderLoginName + "&token=" + token;
        
        //上传请求URL
        String uploadUrl = oaUrl + uploadMethod + uploadParam;
        
        URL preUrl = new URL(uploadUrl);
        
        URLConnection uc = preUrl.openConnection();
        
        HttpURLConnection hc = (HttpURLConnection)uc;
        
        hc.setDoOutput(true);
        hc.setUseCaches(false);
        hc.setRequestProperty("contentType", "charset=utf-8");
        hc.setRequestMethod("POST");
        
        BufferedInputStream input = new BufferedInputStream(new FileInputStream(tempFile));
        
        String BOUNDARY = "---------------------------7d4a6d158c9"; // 分隔符
        
        StringBuffer sb = new StringBuffer();
        sb.append("--");
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data; \r\n name=\"1\"; filename=\"" + fileName + "\"\r\n");
        sb.append("Content-Type: application/msword\r\n\r\n");
        
        hc.setRequestProperty("Content-Type", "multipart/form-data;boundary="
            + "---------------------------7d4a6d158c9");
        
        byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
        
        DataOutputStream dos = new DataOutputStream(hc.getOutputStream());
        
        dos.write(sb.toString().getBytes("utf-8"));
        
        int cc = 0;
        while ((cc = input.read()) != -1)
        {
            dos.write(cc);
        }
        dos.write(end_data);
        dos.flush();
        dos.close();
        
        //输出
        StringBuilder strBd = new StringBuilder();
        
        InputStream is = hc.getInputStream();
        
        int ch;
        while ((ch = is.read()) != -1)
        {
            strBd.append((char)ch);
        }
        
        if (is != null)
        {
            is.close();
        }
        
        return strBd.toString().replace("\r\n", "");
        
    }

    public static String oaUpload(String oaUrl, String token, String senderLoginName, String fileUrl, String fileName)throws Exception{

        HttpURLConnection httpUrl = null;

        URL url = null;

        //上传方法
        String uploadMethod = "/seeyon/uploadService.do?method=processUploadService";

        //上传参数
        String uploadParam = "&senderLoginName=" + senderLoginName + "&token=" + token;

        //上传请求URL
        String uploadUrl = oaUrl + uploadMethod + uploadParam;

        //输出
        StringBuilder strBd = new StringBuilder();


        URL preUrl = null;

        HttpURLConnection hc = null;

        DataOutputStream dos = null;

        InputStream is = null;

        try {

            url = new URL(fileUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            // 连接指定的资源
            httpUrl.connect();


            preUrl = new URL(uploadUrl);
            URLConnection uc = preUrl.openConnection();
            hc = (HttpURLConnection)uc;

            hc.setDoOutput(true);
            hc.setUseCaches(false);
            hc.setRequestProperty("contentType", "charset=utf-8");
            hc.setRequestMethod("POST");

            BufferedInputStream input = new BufferedInputStream(httpUrl.getInputStream());

            String BOUNDARY = "---------------------------7d4a6d158c9"; // 分隔符

            StringBuffer sb = new StringBuffer();
            sb.append("--");
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data; \r\n name=\"1\"; filename=\"" + fileName + "\"\r\n");
            sb.append("Content-Type: application/msword\r\n\r\n");

            hc.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + "---------------------------7d4a6d158c9");

            byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();

            dos = new DataOutputStream(hc.getOutputStream());

            dos.write(sb.toString().getBytes("utf-8"));

            int cc = 0;
            while ((cc = input.read()) != -1)
            {
                dos.write(cc);
            }

            dos.write(end_data);

            is = hc.getInputStream();

            int ch;
            while ((ch = is.read()) != -1)
            {
                strBd.append((char)ch);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }finally {
            if(dos != null){
                dos.flush();
                dos.close();
            }
            if(is != null){
                is.close();
            }
            if(httpUrl != null){
                httpUrl.disconnect();
            }
            if(hc != null){
                hc.disconnect();
            }
        }

        return strBd.toString().replace("\r\n", "");
    }
    
    public static String oaUpload(String oaUrl, String token, String senderLoginName, String fileUrl, String fileName,String tempFilePath)throws Exception{

        HttpURLConnection httpUrl = null;

        URL url = null;

        //上传方法
        String uploadMethod = "/seeyon/uploadService.do?method=processUploadService";

        //上传参数
        String uploadParam = "&senderLoginName=" + senderLoginName + "&token=" + token;

        //上传请求URL
        String uploadUrl = oaUrl + uploadMethod + uploadParam;

        //输出
        StringBuilder strBd = new StringBuilder();


        URL preUrl = null;

        HttpURLConnection hc = null;

        DataOutputStream dos = null;

        InputStream is = null;
        
        FileOutputStream tempFile = null;

        try {
            
            if(StringUtils.isNotEmpty(tempFilePath)) {
                tempFile = new FileOutputStream(tempFilePath+fileName);
            }

            url = new URL(fileUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            // 连接指定的资源
            httpUrl.connect();


            preUrl = new URL(uploadUrl);
            URLConnection uc = preUrl.openConnection();
            hc = (HttpURLConnection)uc;

            hc.setDoOutput(true);
            hc.setUseCaches(false);
            hc.setRequestProperty("contentType", "charset=utf-8");
            hc.setRequestMethod("POST");

            BufferedInputStream input = new BufferedInputStream(httpUrl.getInputStream());

            String BOUNDARY = "---------------------------7d4a6d158c9"; // 分隔符

            StringBuffer sb = new StringBuffer();
            sb.append("--");
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data; \r\n name=\"1\"; filename=\"" + fileName + "\"\r\n");
            sb.append("Content-Type: application/msword\r\n\r\n");

            hc.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + "---------------------------7d4a6d158c9");

            byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();

            dos = new DataOutputStream(hc.getOutputStream());

            dos.write(sb.toString().getBytes("utf-8"));
            
            //同时记录本地文件
            int cc = 0;
            while ((cc = input.read()) != -1)
            {
                dos.write(cc);
                
                if(tempFile != null) {
                    tempFile.write(cc);
                }
            }

            dos.write(end_data);

            is = hc.getInputStream();

            int ch;
            while ((ch = is.read()) != -1)
            {
                strBd.append((char)ch);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }finally {
            if(dos != null){
                dos.flush();
                dos.close();
            }
            if(is != null){
                is.close();
            }
            if(httpUrl != null){
                httpUrl.disconnect();
            }
            if(hc != null){
                hc.disconnect();
            }
            if(tempFile != null) {
                tempFile.close();
            }
        }

        return strBd.toString().replace("\r\n", "");
    }
    
    public static String oaZipUpload(String oaUrl, String token, String senderLoginName, String zipUrl, String fileName){

        //上传方法
        String uploadMethod = "/seeyon/uploadService.do?method=processUploadService";

        //上传参数
        String uploadParam = "&senderLoginName=" + senderLoginName + "&token=" + token;

        //上传请求URL
        String uploadUrl = oaUrl + uploadMethod + uploadParam;

        //输出
        StringBuilder strBd = new StringBuilder();


        URL preUrl = null;

        HttpURLConnection hc = null;

        DataOutputStream dos = null;

        InputStream is = null;
        
        File zipFile = new File(zipUrl);
        BufferedInputStream input = null;

        try {
            input = new BufferedInputStream(new FileInputStream(zipFile));
            
            preUrl = new URL(uploadUrl);
            URLConnection uc = preUrl.openConnection();
            hc = (HttpURLConnection)uc;

            hc.setDoOutput(true);
            hc.setUseCaches(false);
            hc.setRequestProperty("contentType", "charset=utf-8");
            hc.setRequestMethod("POST");

            String BOUNDARY = "---------------------------7d4a6d158c9"; // 分隔符

            StringBuffer sb = new StringBuffer();
            sb.append("--");
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data; \r\n name=\"1\"; filename=\"" + fileName + "\"\r\n");
            sb.append("Content-Type: application/msword\r\n\r\n");

            hc.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + "---------------------------7d4a6d158c9");

            byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();

            dos = new DataOutputStream(hc.getOutputStream());

            dos.write(sb.toString().getBytes("utf-8"));

            int cc = 0;
            while ((cc = input.read()) != -1)
            {
                dos.write(cc);
            }

            dos.write(end_data);

            is = hc.getInputStream();

            int ch;
            while ((ch = is.read()) != -1)
            {
                strBd.append((char)ch);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(dos != null){
                    dos.flush();
                    dos.close();
                }
                if(is != null){
                    is.close();
                }
                if(input != null) {
                    input.close();
                }
            }catch(Exception e) {
                e.printStackTrace();
            }
            
            if(hc != null){
                hc.disconnect();
            }
        }

        return strBd.toString().replace("\r\n", "");
    }
}
