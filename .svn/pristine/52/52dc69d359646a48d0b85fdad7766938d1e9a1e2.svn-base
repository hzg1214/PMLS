package cn.com.eju.deal.base.controller;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.common.util.ComConstants;
import cn.com.eju.deal.core.support.AppMsg;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.core.util.WebUtil;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**   
* Controller 基类
* @author (li_xiaodong)
* @date 2016年2月17日 下午9:33:44
*/
public abstract class BaseController
{
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    public PageInfo pageInfo;
    
    public PageInfo getPageInfo(HttpServletRequest request)
    {
        pageInfo = new PageInfo(request);
        return pageInfo;
    }
    
    public void setPageInfo(PageInfo pageInfo)
    {
        this.pageInfo = pageInfo;
    }
    
    public ReturnView<?, ?> getMapView(Map<?, ?> map)
    {
        ReturnView<?, ?> jsonView = new ReturnView<String, Object>();
        if (map != null)
        {
            
            jsonView.putAll(map);
        }
        return jsonView;
    }
    
    /** 
    * (绑定参数到request Attrbute)
    * @param request
    */
    public void bindParamToAttrbute(HttpServletRequest request)
    {
        Enumeration<?> enumer = request.getParameterNames();
        while (enumer.hasMoreElements())
        {
            String key = (String)enumer.nextElement();
            String[] values = request.getParameterValues(key);
            if (values.length > 1)
            {
                request.setAttribute(key, values);
            }
            else
            {
                request.setAttribute(key, request.getParameter(key));
            }
        }
    }
    
    /** 
    * (绑定参数到Map)
    * @param request
    * @return
    */
    public Map<String, Object> bindParamToMap(HttpServletRequest request)
    {
        Enumeration<?> enumer = request.getParameterNames();
        Map<String, Object> map = new HashMap<String, Object>();
        while (enumer.hasMoreElements())
        {
            String key = (String)enumer.nextElement();
            String val = request.getParameter(key);
            if (!"randomId".equals(key))
            {
                if ("orderBy".equals(key))
                {
                    if (!StringUtil.isEmpty(val))
                    {
                        Object orderByList = JsonUtil.parseToObject(val, List.class);
                        map.put(key, orderByList);
                    }
                    continue;
                }
                map.put(key, val);
            }
        }
        return map;
    }
    
    /** 
    * (返回查询list的jsonView)
    * @param list
    * @return
    */
    public ReturnView<?, ?> getSearchJSONView(List<?> list)
    {
        ReturnView<?, ?> jsonView = new ReturnView<String, Object>();
        jsonView.setSearchReturnType();
        if (list != null)
        {
            jsonView.setReturnValue(list);
        }
        // pageinfo
        //      jsonView.setPageInfo(pageInfo);
        return jsonView;
    }
    
    /** 
    * (返回查询list的jsonView)
    * @param map
    * @return
    */
    public ReturnView<?, ?> getSearchJSONView(Map<?, ?> map)
    {
        ReturnView<?, ?> jsonView = new ReturnView<String, Object>();
        jsonView.setSearchReturnType();
        if (map != null)
        {
            jsonView.putAll(map);
        }
        return jsonView;
    }
    
    /** 
    * (返回操作的jsonView)
    * @param result
    * @return
    */
    public ReturnView<?, ?> getOperateJSONView(Map<?, ?> result)
    {
        ReturnView<?, ?> jsonView = new ReturnView<String, Object>();
        jsonView.setOperateReturnType();
        if (result != null)
        {
            jsonView.putAll(result);
        }
        return jsonView;
    }
    
    /** 
    * TODO (这里用一句话描述这个方法的作用)
    * @return
    */
    public ReturnView<?, ?> succSeachView()
    {
        ReturnView<?, ?> view = new ReturnView<Object, Object>();
        view.setSuccess();
        view.setSearchReturnType();
        return view;
    }
    
    /** 
    * TODO (这里用一句话描述这个方法的作用)
    * @return
    */
    public ReturnView<?, ?> succOperateView()
    {
        ReturnView<?, ?> view = new ReturnView<Object, Object>();
        view.setSuccess();
        view.setOperateReturnType();
        return view;
    }
    
    /** 
    * TODO (获取关账信息)
    * @return
    */
    public Map<String, Object> getSwitchInfo(HttpServletRequest request,String cityNo){
    	@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) WebUtil.getValueFromSession(request, ComConstants.CRM_PERFORM_SWITCH_LNK);//关账月
        if("52F1D510-DDA2-4A8D-9FD3-E165E933CEEC".equals(cityNo)){//昆山当做上海处理
        	cityNo = "AAAD4421-21B1-46FD-9DE4-D5A3183CE260";
        }
        String yearMonth = (String) map.get(cityNo);
        
        if(StringUtil.isNotEmpty(yearMonth)){
        	map.put("yearMonth", yearMonth);
        }else{
        	map.put("yearMonth", "1970-01-01");
        }
        int diffNum = DateUtil.getDaysDiff(new Date(),DateUtil.getDate(yearMonth, "yyyy-MM-dd"));
        if(diffNum>=0){
        	map.put("isShowCurrDate",true);
        }
        return map;
    }

    protected void download(HttpServletRequest request, HttpServletResponse response, String pathName, String downloadName) throws Exception {
        response.setContentType("textml;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        BufferedInputStream bis;
        BufferedOutputStream bos;

        long fileLength = new File(pathName).length();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment; filename="
                + new String(downloadName.getBytes("GBK"), "ISO8859-1"));
        response.setHeader("Content-Length", String.valueOf(fileLength));

        bis = new BufferedInputStream(new FileInputStream(pathName));
        bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
        }

        bis.close();
        bos.close();
    }
    public  void setExportSuccess(String cookieName,HttpServletResponse response){
        Cookie c2 = new Cookie(cookieName,"Success");
        c2.setMaxAge(600);
        response.addCookie(c2);
    }

    public void rememberSearch(HttpServletRequest request, HttpServletResponse response, String pageType, Map<String, Object> map) {
        try {
            if (map == null) {
                map = new HashMap<>();
            }
            String jsonMapStr = JSON.toJSONString(map);
            Cookie cookie = new Cookie(pageType, URLEncoder.encode(jsonMapStr, "UTF-8"));
            cookie.setMaxAge(3600);
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            logger.error("base",
                    "BaseController",
                    "rememberSearch",
                    null, null, null,
                    AppMsg.getString("缓存失败"),
                    e);
        }
    }

    //获取记忆
    @SuppressWarnings("unchecked")
    public Map<String, Object> getRememberSearch( HttpServletRequest request, String pageType) {
        Map<String, Object> searchMap = new HashMap<>();
        try {
            Cookie cookies[] = request.getCookies();
            for (int i = 0; cookies != null && i < cookies.length; i++) {
                if (pageType.equals(cookies[i].getName())) {
                    String jsonMapStr = cookies[i].getValue();
                    if (jsonMapStr != null) {
                        jsonMapStr = URLDecoder.decode(jsonMapStr, "UTF-8");
                    }
                    if (jsonMapStr != null) {
                        searchMap = JSON.parseObject(jsonMapStr, Map.class);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("base",
                    "BaseController",
                    "getRememberSearch",
                    null,null,null,
                    AppMsg.getString("获取失败"),
                    e);
        }
        return searchMap;
    }

    public void clearSearch(HttpServletRequest request, HttpServletResponse response, String pageType) {
        try {
            String jsonMapStr = JSON.toJSONString(new HashMap<>());
            Cookie cookie = new Cookie(pageType, jsonMapStr);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            logger.error("base",
                    "BaseController",
                    "getRememberSearch",
                    null, null, null,
                    AppMsg.getString("清空失败"),
                    e);
        }
    }
}
