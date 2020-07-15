package cn.com.eju.pmls.utils;

import cn.com.eju.deal.common.util.ExcelUtil;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * curl下载
 * @author lw
 *
 */
public class CurlUtils {
    private static Logger logger = LoggerFactory.getLogger(CurlUtils.class);
    private static Pattern p = Pattern.compile(".*?state=(\\d+)size=(.*?)end.*?");

    public static String DEFAULT_UA = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:11.0) Gecko/20100101 Firefox/11.0";

    public static String TEMP_SUFFIX = ".curltemp";

    // 连接超时时间
    private static final int CONNECT_TIME_OUT = 1*60;

    // 下载超时时间
    private static final int DOWNLOAD_TIME_OUT = 10 * 60;

    /**
     * curl下载
     * @param url 下载地址
     * @param extraMap 下载附加信息
     * @param savePath 下载保存路径，包含文件名称
     * @param errSb 错误信息Buffer
     * @return 1表示下载成功， 0表示下载失败, -1表示下载超时, -2下载返回结果中没有找到Http状态和文件大小
     * @throws IOException
     * @throws InterruptedException
     */
    public static int curl(String url, Map<String, String> extraMap,
                           String savePath, StringBuffer errSb,String crulPath,
                           Map<String,String> crulCommandMap) throws IOException, InterruptedException{
        logger.info("CSV##下载curl start ##url="+ url+"#savePath="+savePath+"#crulPath"+crulPath);
        int state = 0;
        url = encodeUrl(url);
        String user_agent = DEFAULT_UA;

        if(null!=extraMap){
            String ua = extraMap.get("user-agent");
            if(null!=ua && !"".equals(ua)){
                user_agent = ua;
            }
        }

        String tempFilePath = savePath + ".curltemp";

        // 拼写下载命令
        String resultStr = null;
        if("windows".equals(crulPath)){
            String[] cmdString =  {"cmd", "/c", "D:\\java\\curl-7.70.0-win64-mingw\\bin\\curl "
                    + " --connect-timeout " + CONNECT_TIME_OUT + " -m " + DOWNLOAD_TIME_OUT
                    + " -L -w state=%{http_code}size=%{size_download}end"
                    + " -A " + "\"" + user_agent + "\""
                    + " -o " + tempFilePath +" "
                    + url};
            resultStr = exec(Arrays.asList(cmdString));
        }else{
            String crulCommand1 = crulCommandMap.get("crulCommand1");
            String crulCommand2 = crulCommandMap.get("crulCommand2");
            String crulCommand3 = crulCommandMap.get("crulCommand3")+" ";


            //String[] cmdString = {"/bin/bash", "-c", "/usr/bin/curl "
            String[] cmdString = {crulCommand1, crulCommand2, crulCommand3
                    + " --connect-timeout " + CONNECT_TIME_OUT + " -m " + DOWNLOAD_TIME_OUT
                    + " -L -w state=%{http_code}size=%{size_download}end"
                    + " -A " + "\"" + user_agent + "\""
                    + " -o " + tempFilePath +" "
                    + url};
            resultStr = exec(Arrays.asList(cmdString));
        }


        if(resultStr.contains("timed out")){
            errSb.append("下载超时");
            logger.error("curl 下载超时");
            return -1;
        }


        // 匹配下载输出结果中的 Http状态和文件大小
        Matcher match = p.matcher(resultStr);
        if(match.find()){
            String stateStr = match.group(1);
            String sizeStr = match.group(2);

            // http状态为200 Ok
            if("200".equals(stateStr)){
                File downFile = new File(tempFilePath);
                if(downFile.exists()){
                    try{
                        long fileSize = Long.parseLong(sizeStr);

                        // 比较下载下来的文件和Get请求返回的真实文件大小
                        if(fileSize!=downFile.length()){
                            errSb.append("文件下载中断，下载未完成！");
                            logger.error("curl 文件下载中断，下载未完成！");
                        }
                        else{
                            // 下载的文件大小和真实文件大小相符合，下载成功
                            state = 1;
                            downFile.renameTo(new File(savePath));
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                        logger.error("curl 文件下载失败！");
                        errSb.append("下载失败" + ", Http_code:" + stateStr + ";size:" + sizeStr
                                +";Excpetion:" + e.getMessage());
                    }
                }else{
                    errSb.append("下载已完成，但文件丢失！");
                    logger.error("curl 下载已完成，但文件丢失！");
                }
            }
            else{
                errSb.append("下载失败" + ", Http_code:" + stateStr);
                logger.error("curl 下载失败" + "Http_code:" + stateStr);
            }
        }
        else{
            state=-2;
        }

        return state;
    }

    private static String encodeUrl(String url){
        url = url.replace("&", "\\&");
        url = url.replace("(", "%28");
        url = url.replace(")", "%29");

        return url;
    }


    private static String exec(List<String> commandList) throws IOException {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command(commandList);
        builder.redirectErrorStream(true);
        Process p;
        StringBuffer sb = new StringBuffer();
        BufferedReader inputBuf = null;
        try {
            p = builder.start();
//            BufferedReader inputBuf = null;
            String line = null;
            inputBuf = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = inputBuf.readLine()) != null) {
                sb.append(line);
                continue;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            if(null!=inputBuf){
                inputBuf.close();
            }

        }

        // 这里线程阻塞，将等待外部转换进程运行成功运行结束后，才往下执行
        try {
            p.waitFor();
        } catch (InterruptedException e) {
            logger.error("Curl下载过程被打断！");
            throw new RuntimeException("Curl下载过程被打断！");
        }
        return sb.toString();
    }


}
