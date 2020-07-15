package cn.com.eju.deal.base.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class HttpClientUtil {

	private static Logger logger = LoggerFactory
			.getLogger(HttpClientUtil.class);

    
    /** 
     * 发送HttpPost请求 
     *  
     * @param strURL 
     *            服务地址 
     * @param params 
     *            json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/> 
     * @return 成功:返回json字符串<br/> 
     */  
    public static String jsonPost(String strURL, String params) {  
        
        try {  
            URL url = new URL(strURL);// 创建连接  
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
            connection.setDoOutput(true);  
            connection.setDoInput(true);  
            connection.setUseCaches(false);  
            connection.setInstanceFollowRedirects(true);  
            connection.setRequestMethod("POST"); // 设置请求方式  
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式  
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式  
            connection.connect();  
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码  
            out.append(params);  
            out.flush();  
            out.close();  
            // 读取响应  
            int length = connection.getContentLength();// 获取长度  
            InputStream is = connection.getInputStream();  
            if (length != -1) {  
                byte[] data = new byte[length];  
                byte[] temp = new byte[512];  
                int readLen = 0;  
                int destPos = 0;  
                while ((readLen = is.read(temp)) > 0) {  
                    System.arraycopy(temp, 0, data, destPos, readLen);  
                    destPos += readLen;  
                }  
                String result = new String(data, "UTF-8"); // utf-8编码  
                
                return result;  
            }  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return "error"; // 自定义错误信息  
    }

    /** 
     * 发送HttpPost请求 
     *  
     * @param strURL 
     *            服务地址 
     * @param params 
     *            json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/> 
     * @return 成功:返回json字符串<br/> 
     */  
    public static String jsonPut(String strURL, String params) {  
        
        try {  
            URL url = new URL(strURL);// 创建连接  
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
            connection.setDoOutput(true);  
            connection.setDoInput(true);  
            connection.setUseCaches(false);  
            connection.setInstanceFollowRedirects(true);  
            connection.setRequestMethod("PUT"); // 设置请求方式  
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式  
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式  
            connection.connect();  
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码  
            out.append(params);  
            out.flush();  
            out.close();  
            // 读取响应  
            int length = connection.getContentLength();// 获取长度  
            InputStream is = connection.getInputStream();  
            if (length != -1) {  
                byte[] data = new byte[length];  
                byte[] temp = new byte[512];  
                int readLen = 0;  
                int destPos = 0;  
                while ((readLen = is.read(temp)) > 0) {  
                    System.arraycopy(temp, 0, data, destPos, readLen);  
                    destPos += readLen;  
                }  
                String result = new String(data, "UTF-8"); // utf-8编码  
                
                return result;  
            }  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return "error"; // 自定义错误信息  
    }
	

	public static void main(String[] args) {
		try{
			/*String url="http://172.28.28.64:8080/OPService/op/applyfyaccount";
			Map<String, Object> obj = new HashMap<>();
			
			CompanyADto aDto = new CompanyADto();
			aDto.setCompanyNo("200574");
			aDto.setCompanyName("上海盈晟房地产经纪 有限公司");
			aDto.setCityNo("AAAD4421-21B1-46FD-9DE4-D5A3183CE260");
			aDto.setDistrictNo("3BC12B65-18AA-4392-8665-4448BB5B3582");
			aDto.setAreaNo("");
			aDto.setCompanyAddr("公司地址");
			aDto.setLinkMan("张三");
			aDto.setLinkPhone("13761408311");
			aDto.setFyCompanyId("1034e545-e32d-4e0b-925c-afe922cd7f41");
			aDto.setLongitude("0");
			aDto.setLatitude("0");
			
			List<StoreADto> listStore = new ArrayList<>();
			StoreADto storeADto = new StoreADto();
			storeADto.setStoreNo("MD01763");
			storeADto.setName("中意房产");
			storeADto.setCityNo("AAAD4421-21B1-46FD-9DE4-D5A3183CE260");
			storeADto.setDistrictNo("7D89FE4F-F63B-4BF7-A19A-62F4BA13EEC4");
			storeADto.setAreaNo("");
			storeADto.setAddress("仓场路2893号");
			storeADto.setAddressDetail("仓场路2893号");
			storeADto.setLongitude("0");
			storeADto.setLatitude("0");
			listStore.add(storeADto);
			
			obj.put("userIdUpdate", "225");
			obj.put("companyADto", aDto);
			obj.put("storeADtoList", listStore);
			String jsonStr = JSON.toJSONString(obj);
			String str =jsonPost(url, jsonStr);
			System.out.println(str);*/
			String url="http://172.28.28.64:8080/OPService/op/store";
			Map<String, String> obj = new HashMap<>();
			
			obj.put("storeNo", "MD01763");
			obj.put("name", "中意房产");
			obj.put("cityNo", "AAAD4421-21B1-46FD-9DE4-D5A3183CE260");
			obj.put("districtNo", "7D89FE4F-F63B-4BF7-A19A-62F4BA13EEC4");
			obj.put("areaNo", "");
			obj.put("address", "仓场路2893号");
			obj.put("addressDetail", "上海嘉定区仓场路2893号");
			obj.put("longitude", "");
			obj.put("latitude", "");
			obj.put("userIdUpdate", "225");
			String jsonStr = JSON.toJSONString(obj);
			String str =jsonPut(url, jsonStr);
			System.out.println(str);
			
			/*String url="http://10.0.60.41:7070/op/store";
			JSONObject obj = new JSONObject();
			
			obj.put("storeNo", "MD01763");
			obj.put("name", "中意房产");
			obj.put("cityNo", "AAAD4421-21B1-46FD-9DE4-D5A3183CE260");
			obj.put("districtNo", "7D89FE4F-F63B-4BF7-A19A-62F4BA13EEC4");
			obj.put("areaNo", "");
			obj.put("address", "仓场路2893号");
			obj.put("addressDetail", "上海嘉定区仓场路2893号");
			obj.put("longitude", "");
			obj.put("latitude", "");
			obj.put("userIdUpdate", "225");
			doPut(url, obj.toJSONString());*/
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
    public static String httpPostYF(String urlStr,String paramMap){
        String output="";
        String readMsg = "";
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            String input = "formVals="+paramMap;
            input = input.replaceAll("&","%FFFF%");
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes("UTF-8"));
            os.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream()), "UTF-8"));
            while ((output = br.readLine()) != null) {
                readMsg = output;
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return readMsg;
    }

}
