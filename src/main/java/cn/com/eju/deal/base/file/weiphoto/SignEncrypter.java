package cn.com.eju.deal.base.file.weiphoto;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**   
* weiphoto文件系统 -- 生成签名
* @author (li_xiaodong)
* @date 2016年6月29日 下午9:46:13
*/
public class SignEncrypter
{
    /**
     * sha1 数字签名
     * @param params     所有参数
     * @param timestamp  时间戳，从1970-1-1 到现在的秒数
     * @return 签名的二进制字符串
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String sha1Sign(Map<String, String> params, String signKey)
        throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        String temp = prepareParams(params, signKey);
        
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(temp.getBytes("utf-8"));
        byte b[] = md.digest();
        String str = bytesToString(b);
        
        return str;
    }
    
    /**
     * 组成 a=b&c=d&timestamp=...&key=...格式字符串
     * 最后两个字段是timestamp和key
     * @param params
     * @param timestamp
     * @return
     */
    private static String prepareParams(Map<String, String> params, String signKey)
    {
        
        //组成 － 内容为null或“”的排除掉 a=b&c=d&timestamp=111030983&key=yousay...
        StringBuffer stringBuffer = new StringBuffer();
        
        if (params != null && params.size() > 0)
        {
            Set<String> paramKeys = params.keySet();
            
            //排序 － 按照字母序排序
            Set<String> sortedSet = new TreeSet<String>();
            
            sortedSet.addAll(paramKeys);
            
            Iterator<String> it = sortedSet.iterator();
            while (it.hasNext())
            {
                String paramKey = it.next();
                String paramValue = params.get(paramKey);
                if (paramValue != null && paramValue.length() >= 0)
                    //key=value&
                    stringBuffer.append(paramKey).append("=").append(paramValue).append("&");
            }
        }
        stringBuffer.append("&key=").append(signKey);
        
        return stringBuffer.toString();
    }
    
    /**
     * byte 转成16进制字符串
     * @param bytes
     * @return
     */
    private static String bytesToString(byte[] bytes)
    {
        if (bytes == null || bytes.length == 0)
            return null;
        
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < bytes.length; offset++)
        {
            i = bytes[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }
    
    public static void main(String[] args)
    {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("userId", "1010");
        params.put("deviceId", "abcdefgaedalfdjakf");
        params.put("clientVersion", "aaaa-bbbbb-cccdddd");
        
        long timestamp = System.currentTimeMillis() / 1000;
        System.out.println("timestamp:" + timestamp);
        
        try
        {
            //            String md5 = SignEncrypter.md5Sign(params,"");
            //            System.out.println("md5:"+md5);
            String sha1 = SignEncrypter.sha1Sign(params, "123456789087451231211313mkljooiuo====123123123");
            System.out.println("sha1:" + sha1);
            
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        
    }
}
