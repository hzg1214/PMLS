package cn.com.eju.deal.base.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.SystemCfg;
import cn.com.eju.deal.core.util.StringUtil;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

/**
 * Created by Administrator on 2017/6/15.
 */
public class UploadFileUtil {
    
    private static Logger logger = LoggerFactory.getLogger(UploadFileUtil.class);

    /**
     * 上传文件到服务器
     * @param URl
     * @param fileName
     * @param inputStream
     * @return
     */
    public String uploadFile(String URl,String fileName, InputStream inputStream){
        try {
            // 换行符
            final String newLine = "\r\n";
            final String boundaryPrefix = "--";
            // 定义数据分隔线
            String BOUNDARY = "========7d4a6d158c9";
            // 服务器的域名
            URL url = new URL(URl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置为POST情
            conn.setRequestMethod("POST");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求头参数
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Charsert", "UTF-8");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

            OutputStream out = new DataOutputStream(conn.getOutputStream());
            // 上传文件
//            File file = new File(fileName);
            StringBuilder sb = new StringBuilder();
            sb.append(boundaryPrefix);
            sb.append(BOUNDARY);
            sb.append(newLine);
            // 文件参数,photo参数名可以随意修改
            sb.append("Content-Disposition: form-data;name=\"photo\";filename=\"" + fileName
                    + "\"" + newLine);
            sb.append("Content-Type:application/octet-stream");
            // 参数头设置完以后需要两个换行，然后才是参数内容
            sb.append(newLine);
            sb.append(newLine);

            // 将参数头的数据写入到输出流中
            out.write(sb.toString().getBytes());

            // 数据输入流,用于读取文件数据
//            DataInputStream in = new DataInputStream(new FileInputStream(file));
            DataInputStream in = new DataInputStream(inputStream);

            byte[] bufferOut = new byte[1024];
            int bytes = 0;
            // 每次读1KB数据,并且将文件数据写入到输出流中
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            // 最后添加换行
           // out.write(newLine.getBytes());
            in.close();

            // 定义最后数据分隔线，即--加上BOUNDARY再加上--。
            byte[] end_data = (newLine + boundaryPrefix + BOUNDARY + boundaryPrefix + newLine)
                    .getBytes();
            // 写上结尾标识
            out.write(end_data);
            out.flush();
            out.close();
            // 定义BufferedReader输入流来读取URL的响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String line = null;
            String result = "";
            while ((line = reader.readLine()) != null) {
                result = new String(line.getBytes(),"UTF-8");
            }
            return result;
        } catch (Exception e) {
            System.out.println("上传文件到服务器,发送POST请求出现异常！" + e);
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 营销文件上传
     * @param request 请求
     * @return
     * @throws Exception
     */
    public ResultData upload(HttpServletRequest request)throws Exception{
        ResultData resultData = new ResultData();

        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断是否为文件上传表单
        if(commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;

            Iterator iter = multipartHttpServletRequest.getFileNames();
            if(iter.hasNext()){
                MultipartFile importFile = multipartHttpServletRequest.getFile((String)iter.next());
                String fileName = importFile.getOriginalFilename();
                String url = SystemParam.getWebConfigValue("upLoadFangYouImg");
                String fileTypeName = "图片";
                String fileType = fileName.substring((fileName.lastIndexOf(".")+1)).toLowerCase();
                String isImage = "1";

                //判断后缀是否为图片
                if(!StringUtil.isAvaliableFileFmt(fileName, SystemCfg.getString("uploadPicSuffix"))) {
                    isImage = "0";
                    fileTypeName = "文件";
                    url = SystemParam.getWebConfigValue("upLoadFangYouFile");
                }

                //                long var16 = importFile.getSize();

                //上传
                String returnJsonStr = this.uploadFile(url,importFile.getOriginalFilename(),importFile.getInputStream());

                Map<String,Object> returnMap = null;
                //                Map<String,Object> returnDataMap = null;
                if(StringUtil.isNotEmpty(returnJsonStr)){
                    returnMap = JSON.parseObject(returnJsonStr, Map.class);
                    if(returnMap.containsKey("BFlag") && returnMap.containsKey("TData")){
                        Integer bFlag = (Integer) returnMap.get("BFlag");
                        
                        if(10 == bFlag){
                            JSONObject tData = (JSONObject) returnMap.get("TData");
                            tData.put("fileName",fileName);
                            tData.put("isImage",isImage);
                            tData.put("fileSuffix",fileType);
                            if("0".equals(isImage)){
                                //文件时只返回url，将url放到url500中
                                tData.put("picUrl_20_percent","");
                                tData.put("picUrl_50_percent","");
                                tData.put("picUrl_500",tData.get("url"));
                            }
                            
                            resultData.setSuccess();
                            resultData.setReturnData(tData);
                        }else{
                            resultData.setFail("上传"+fileTypeName+"失败");
                            logger.error("返回数据异常:"+returnJsonStr);
                        }
                    }else {
                        resultData.setFail("上传"+fileTypeName+"失败");
                        logger.error("返回数据异常:"+returnJsonStr);
                    }
                }else {
                    resultData.setFail("上传"+fileTypeName+"失败");
                    logger.error("返回数据异常:"+returnJsonStr);
                }
            }
        }

        return resultData;
    }

    public static void createZip(String sourcePath, String zipPath,String zipName) {
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(zipPath);
            zos = new ZipOutputStream(fos);
            writeZip(new File(sourcePath), "", zos,zipName);
        } catch (FileNotFoundException e) {
            logger.error("创建ZIP文件失败", e);
        } finally {
            try {
                if (zos != null) {
                    zos.close();
                }
            } catch (IOException e) {
                logger.error("创建ZIP文件失败", e);
            }

        }
    }

    private static void writeZip(File file, String parentPath, ZipOutputStream zos,String zipName) {
        if (file.exists()) {
            if (file.isDirectory()) {// 处理文件夹
                parentPath += file.getName() + File.separator;
                File[] files = file.listFiles();
                if (files.length != 0) {
                    for (File f : files) {
                        if(!zipName.equals(f.getName())) {
                            writeZip(f, parentPath, zos,zipName);
                        }
                    }
                } else { // 空目录则创建当前目录
                    try {
                        zos.putNextEntry(new ZipEntry(parentPath));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(file);
                    ZipEntry ze = new ZipEntry(parentPath + file.getName());
                    zos.putNextEntry(ze);
                    byte[] content = new byte[1024];
                    int len;
                    while ((len = fis.read(content)) != -1) {
                        zos.write(content, 0, len);
                        zos.flush();
                    }

                } catch (FileNotFoundException e) {
                    logger.error("创建ZIP文件失败", e);
                } catch (IOException e) {
                    logger.error("创建ZIP文件失败", e);
                } finally {
                    try {
                        if (fis != null) {
                            fis.close();
                        }
                    } catch (IOException e) {
                        logger.error("创建ZIP文件失败", e);
                    }
                }
            }
        }
    }
    
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            File myFilePath = new File(folderPath);
            myFilePath.delete(); // 删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);// 再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }
    
    /**
     * 【描述】: 压缩文件，按分卷大小
     *
     * @author yinkun
     * @date: 2018年3月13日 下午8:49:46
     * @param sourcePath
     * @param zipPath
     * @param size    单位 M
     */
    public static void createZipWithSplit(String sourcePath, String zipPath,int size) {
        
        try {
            ZipFile zipFile = new ZipFile(zipPath);

            File[] files = new File(sourcePath).listFiles();
            ArrayList<File> filesToAdd = new ArrayList<>();
            for(File f : files) {
                filesToAdd.add(f);
            }

            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

            zipFile.createZipFile(filesToAdd, parameters, true, size*1024*1024);
        } catch (ZipException e) {
            logger.error(e.getMessage());
        }
    }
}
