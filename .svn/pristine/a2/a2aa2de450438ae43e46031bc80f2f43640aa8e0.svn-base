/**
 * Copyright (c) 2018, www.ehousechina.com.
 * Project Name:CRMWeb
 * File Name:OkHttpClientUtil.java
 * Package Name:cn.com.eju.deal.base.util
 * Date:2018年3月7日上午10:13:43
 */
package cn.com.eju.deal.base.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.FormBody;
import okhttp3.FormBody.Builder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * ClassName: OkHttpClientUtil <br/>
 * Description: 使用OkHttpClient工具实在html请求 <br/>
 * 
 * @author yinkun
 * @date: 2018年3月7日 上午10:13:43 <br/>
 * @version V1.0
 */
public class OkHttpClientUtil {
    
    private static Logger logger = LoggerFactory.getLogger(OkHttpClientUtil.class);

    public static String post(String url,Map<String,String> params) {
        
        try {
            OkHttpClient client = new OkHttpClient();
            
            //设置请求参数
            Builder builder = new FormBody.Builder();
            Iterator<Entry<String, String>> iterator = params.entrySet().iterator();
            while(iterator.hasNext()) {
                Entry<String, String> entry = iterator.next();
                builder.add(entry.getKey(), entry.getValue());
            }
            RequestBody body = builder.build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        }catch(Exception e) {
            logger.error(e.getMessage());
        }
        
        return "error";
    }
}

