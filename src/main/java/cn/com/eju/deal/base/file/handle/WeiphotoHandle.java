package cn.com.eju.deal.base.file.handle;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.eju.deal.base.file.weiphoto.FormFieldKeyValuePair;
import cn.com.eju.deal.base.file.weiphoto.HttpPostEmulator;
import cn.com.eju.deal.base.file.weiphoto.SignEncrypter;
import cn.com.eju.deal.base.file.weiphoto.UploadFileItem;
import cn.com.eju.deal.core.support.SystemCfg;
import cn.com.eju.deal.core.util.JsonUtil;

/**   
* weiphoto文件系统-文件处理入口
* @author (li_xiaodong)
* @date 2016年6月29日 下午9:35:39
*/
public class WeiphotoHandle
{
    
    /** 
    * weiphoto 图片上传
    * @throws Exception
    */
    public static Map<String, Object> uploadPic(String uploadUrl, String absolutePath, String fileName, String fileNo, String securitykey, String appId)
        throws Exception
    {
        //响应map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        // 设定要上传的普通Form Field及其对应的value  
        ArrayList<FormFieldKeyValuePair> ffkvp = new ArrayList<FormFieldKeyValuePair>();
        
        //自命名的上传图片Id
        ffkvp.add(new FormFieldKeyValuePair("id", fileNo));//其他参数  
        
        // 设定要上传的文件  
        ArrayList<UploadFileItem> ufi = new ArrayList<UploadFileItem>();
        
        //file类型(图片)
        ufi.add(new UploadFileItem("pic", absolutePath));
        
        //渠道号
        ArrayList<FormFieldKeyValuePair> headers = new ArrayList<FormFieldKeyValuePair>();
        headers.add(new FormFieldKeyValuePair("H-appId", appId));
        
        //签名时间(默认 单位秒)
        long timestamp = System.currentTimeMillis() / 1000;
        headers.add(new FormFieldKeyValuePair("H-timeStamp", timestamp + ""));
        
        //签名
        String sign = getSign(headers, ffkvp, securitykey);
        headers.add(new FormFieldKeyValuePair("H-sign", sign));
        
        //上传操作
        HttpPostEmulator hpe = new HttpPostEmulator();
        String response = hpe.sendHttpPostRequest(uploadUrl, ffkvp, ufi, headers);
        
        System.out.println("Responsefrom server is: " + response);
        
        rspMap = JsonUtil.parseToObject(response, Map.class);
        
        return rspMap;
        
    }
    
    
    /**
     * 数字签名
     * @param headers
     * @param timeStamp
     * @return
     */
    public static String getSign(ArrayList<FormFieldKeyValuePair> headers, List<FormFieldKeyValuePair> parameters,
        String singKey)
    {
        Map<String, String> pMap = new HashMap<String, String>();
        for (FormFieldKeyValuePair header : headers)
        {
            String name = header.getKey();
            String value = header.getValue();
            pMap.put(name, value);
        }
        for (FormFieldKeyValuePair parameter : parameters)
        {
            String name = parameter.getKey();
            String value = parameter.getValue();
            pMap.put(name, value);
        }
        String sing = "";
        try
        {
            sing = SignEncrypter.sha1Sign(pMap, singKey);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return sing;
    }
    
    /**
     * 测试图片服务
     * @throws Exception
     */
    public static void testPic()
        throws Exception
    {
        // 设定服务地址  
//        String serverUrl = "http://183.136.160.248/weiphoto/upload_pic";//上传地址  
        String serverUrl = "http://img.17shihui.com/weiphoto/upload_pic";//上传地址  
        
        // 设定要上传的普通Form Field及其对应的value  
        ArrayList<FormFieldKeyValuePair> ffkvp = new ArrayList<FormFieldKeyValuePair>();
        ffkvp.add(new FormFieldKeyValuePair("id", "1283901830912"));//其他参数  
        
        // 设定要上传的文件  
        ArrayList<UploadFileItem> ufi = new ArrayList<UploadFileItem>();
        ufi.add(new UploadFileItem("pic", "G:/fang.jpg"));
        
        HttpPostEmulator hpe = new HttpPostEmulator();
        ArrayList<FormFieldKeyValuePair> headers = new ArrayList<FormFieldKeyValuePair>();
        headers.add(new FormFieldKeyValuePair("H-appId", "fileService"));
        long timestamp = System.currentTimeMillis() / 1000;
        headers.add(new FormFieldKeyValuePair("H-timeStamp", timestamp + ""));
        
        //签名
        String sign = getSign(headers, ffkvp, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=");
        headers.add(new FormFieldKeyValuePair("H-sign", sign));
        String response = hpe.sendHttpPostRequest(serverUrl, ffkvp, ufi, headers);
        System.out.println("Responsefrom server is: " + response);
    }
    
    /**
     * 测试文件上传接口
     * @throws Exception
     */
    public static void testFile()
        throws Exception
    {
        // 设定服务地址  
        String serverUrl = "http://183.136.160.248:2081/weiphoto/upload_file";//上传地址  
        
        // 设定要上传的普通Form Field及其对应的value  
        ArrayList<FormFieldKeyValuePair> ffkvp = new ArrayList<FormFieldKeyValuePair>();
        //	        ffkvp.add(new FormFieldKeyValuePair("phone", "123456789"));//其他参数  
        String receive1 = "";
        //String receive2=RandomUtils.getRandomPhone();  
        ffkvp.add(new FormFieldKeyValuePair("id", "676767"));
        //ffkvp.add(new FormFieldKeyValuePair("type", "png"));  
        
        // 设定要上传的文件  
        ArrayList<UploadFileItem> ufi = new ArrayList<UploadFileItem>();
        ufi.add(new UploadFileItem("file", "F:/importData.xlsx"));
        HttpPostEmulator hpe = new HttpPostEmulator();
        ArrayList<FormFieldKeyValuePair> headers = new ArrayList<FormFieldKeyValuePair>();
        headers.add(new FormFieldKeyValuePair("H-appId", "android"));
        long timestamp = System.currentTimeMillis() / 1000;
        headers.add(new FormFieldKeyValuePair("H-timeStamp", timestamp + ""));
        /**
         * 签名
         */
        String sign = getSign(headers, ffkvp, SystemCfg.getString("android.securitykey"));
        headers.add(new FormFieldKeyValuePair("H-sign", sign));
        String response = hpe.sendHttpPostRequest(serverUrl, ffkvp, ufi, headers);
        System.out.println("Responsefrom server is: " + response);
    }
    
    /**
     * 测试图片服务    没有返回PicdIDs
     * @throws Exception
     */
    public static void testuploadFacePic()
        throws Exception
    {
        // 设定服务地址  
        String serverUrl = "http://192.168.33.251:8080/photo-service/weiphoto/upload_face_pic";//上传地址  upload_face_pic
        
        // 设定要上传的普通Form Field及其对应的value  
        ArrayList<FormFieldKeyValuePair> ffkvp = new ArrayList<FormFieldKeyValuePair>();
        //	        ffkvp.add(new FormFieldKeyValuePair("phone", "123456789"));//其他参数  
        String receive1 = "";
        //String receive2=RandomUtils.getRandomPhone();  
        //	        ffkvp.add(new FormFieldKeyValuePair("id", "676767"));
        ffkvp.add(new FormFieldKeyValuePair("faceId", "12"));
        ffkvp.add(new FormFieldKeyValuePair("emojiGroupId", "123"));
        ffkvp.add(new FormFieldKeyValuePair("uid", "749819023"));
        
        // 设定要上传的文件  
        ArrayList<UploadFileItem> ufi = new ArrayList<UploadFileItem>();
        ufi.add(new UploadFileItem("pic", "F:/pic/sfsdf.jpg"));
        HttpPostEmulator hpe = new HttpPostEmulator();
        ArrayList<FormFieldKeyValuePair> headers = new ArrayList<FormFieldKeyValuePair>();
        headers.add(new FormFieldKeyValuePair("H-appId", "android"));
        long timestamp = System.currentTimeMillis() / 1000;
        headers.add(new FormFieldKeyValuePair("H-timeStamp", timestamp + ""));
        /**
         * 签名
         */
        String sign = getSign(headers, ffkvp, SystemCfg.getString("android.securitykey"));
        headers.add(new FormFieldKeyValuePair("H-sign", sign));
        String response = hpe.sendHttpPostRequest(serverUrl, ffkvp, ufi, headers);
        System.out.println("Responsefrom server is: " + response);
    }
    
    /**
     * 测试
     * @throws Exception
     */
    public static void testuploadLimitPic()
        throws Exception
    {
        // 设定服务地址  
        String serverUrl = "http://192.168.33.251:8080/photo-service/weiphoto/upload_limit_pic";//上传地址  upload_face_pic
        
        // 设定要上传的普通Form Field及其对应的value  
        ArrayList<FormFieldKeyValuePair> ffkvp = new ArrayList<FormFieldKeyValuePair>();
        //	        ffkvp.add(new FormFieldKeyValuePair("phone", "123456789"));//其他参数  
        String receive1 = "";
        ffkvp.add(new FormFieldKeyValuePair("file_Type", "pic"));
        ffkvp.add(new FormFieldKeyValuePair("uid", "749819023"));
        
        // 设定要上传的文件  
        ArrayList<UploadFileItem> ufi = new ArrayList<UploadFileItem>();
        ufi.add(new UploadFileItem("pic", "F:/pic/sfsdf.jpg"));
        HttpPostEmulator hpe = new HttpPostEmulator();
        ArrayList<FormFieldKeyValuePair> headers = new ArrayList<FormFieldKeyValuePair>();
        headers.add(new FormFieldKeyValuePair("H-appId", "android"));
        long timestamp = System.currentTimeMillis() / 1000;
        headers.add(new FormFieldKeyValuePair("H-timeStamp", timestamp + ""));
        /**
         * 签名
         */
        String sign = getSign(headers, ffkvp, SystemCfg.getString("android.securitykey"));
        headers.add(new FormFieldKeyValuePair("H-sign", sign));
        String response = hpe.sendHttpPostRequest(serverUrl, ffkvp, ufi, headers);
        System.out.println("Responsefrom server is: " + response);
    }
    
    /**
     * 测试图片服务  多次测试  只测试了一次访问
     * @throws Exception
     */
    public static void testuploadAvatar()
        throws Exception
    {
        // 设定服务地址  
        String serverUrl = "http://192.168.33.251:8080/photo-service/weiphoto/upload_avatar";//上传地址  upload_face_pic
        
        // 设定要上传的普通Form Field及其对应的value  
        ArrayList<FormFieldKeyValuePair> ffkvp = new ArrayList<FormFieldKeyValuePair>();
        //	        ffkvp.add(new FormFieldKeyValuePair("phone", "123456789"));//其他参数  
        String receive1 = "";
        //String receive2=RandomUtils.getRandomPhone();  
        //	        ffkvp.add(new FormFieldKeyValuePair("id", "676767"));
        ffkvp.add(new FormFieldKeyValuePair("type", "user"));
        ffkvp.add(new FormFieldKeyValuePair("picid", "444"));
        ffkvp.add(new FormFieldKeyValuePair("uid", "749819023"));
        
        // 设定要上传的文件  
        ArrayList<UploadFileItem> ufi = new ArrayList<UploadFileItem>();
        ufi.add(new UploadFileItem("pic", "F:/pic/sfsdf.jpg"));
        HttpPostEmulator hpe = new HttpPostEmulator();
        ArrayList<FormFieldKeyValuePair> headers = new ArrayList<FormFieldKeyValuePair>();
        headers.add(new FormFieldKeyValuePair("H-appId", "android"));
        long timestamp = System.currentTimeMillis() / 1000;
        headers.add(new FormFieldKeyValuePair("H-timeStamp", timestamp + ""));
        /**
         * 签名
         */
        String sign = getSign(headers, ffkvp, SystemCfg.getString("android.securitykey"));
        headers.add(new FormFieldKeyValuePair("H-sign", sign));
        String response = hpe.sendHttpPostRequest(serverUrl, ffkvp, ufi, headers);
        System.out.println("Responsefrom server is: " + response);
    }
    
    /**
     * 测试
     * @throws Exception
     */
    public static void testToJavaAvatar()
        throws Exception
    {
        // 设定服务地址  
        String serverUrl = "http://192.168.33.251:8080/photo-service/weiphoto/to_java_avatar";//上传地址  upload_face_pic
        
        // 设定要上传的普通Form Field及其对应的value  
        ArrayList<FormFieldKeyValuePair> ffkvp = new ArrayList<FormFieldKeyValuePair>();
        //	        ffkvp.add(new FormFieldKeyValuePair("phone", "123456789"));//其他参数  
        String receive1 = "";
        //String receive2=RandomUtils.getRandomPhone();  
        //	        ffkvp.add(new FormFieldKeyValuePair("id", "676767"));
        //	        ffkvp.add(new FormFieldKeyValuePair("uid","12"));
        ffkvp.add(new FormFieldKeyValuePair("uid", "829789312839"));
        ffkvp.add(new FormFieldKeyValuePair("picid", "123456789"));
        
        // 设定要上传的文件  
        //	        ArrayList<UploadFileItem> ufi = new ArrayList<UploadFileItem>();  
        //	        ufi.add(new UploadFileItem("pic", "F:/pic/sfsdf.jpg"));  
        HttpPostEmulator hpe = new HttpPostEmulator();
        ArrayList<FormFieldKeyValuePair> headers = new ArrayList<FormFieldKeyValuePair>();
        headers.add(new FormFieldKeyValuePair("H-appId", "android"));
        long timestamp = System.currentTimeMillis() / 1000;
        headers.add(new FormFieldKeyValuePair("H-timeStamp", timestamp + ""));
        /**
         * 签名
         */
        String sign = getSign(headers, ffkvp, SystemCfg.getString("android.securitykey"));
        headers.add(new FormFieldKeyValuePair("H-sign", sign));
        String response = hpe.sendHttpPostRequest(serverUrl, ffkvp, null, headers);
        System.out.println("Responsefrom server is: " + response);
    }
    
    public static void main(String args[])
    {
        try
        {
            testPic();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
