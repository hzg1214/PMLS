package cn.com.eju.deal.contract.service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**   
* 调用REST服务类的基类
* @author (li_xiaodong)
* @date 2016年2月16日 下午3:10:06
* @param <T>
*/
public abstract class OaBaseService<T>
{
    
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    /**
    * Spring提供的用于访问Rest服务的客户端
    */
    private RestTemplate restTemplate = new RestTemplate();
    
    /** 
    * post 请求 -- 鉴权
    * @param url
    * @param dto
    * @return
    * @throws Exception
    */
    public String post(String url, String jsonStr)
        throws Exception
    {
        
        //日志记录begin
        long startTime = System.currentTimeMillis();
        logger.info("request url=" + url + "; request params:" + jsonStr);
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.add("Content-Type", "application/soap+xml; charset=utf-8");
        
        
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonStr, headers);
        
        String backResult = null;
        
        try
        {
            //HTTP POST
            backResult = restTemplate.postForObject(url, formEntity, String.class);
        }
        catch (Exception e)
        {
            throw e;
        }
        
        //日志记录end
        long endTime = System.currentTimeMillis();
        logger.info("response url=" + url + "; response data:" + JsonUtil.parseToJson(backResult) + "; cost time:("
            + (endTime - startTime) + ") ms");
        
        return backResult;
    }

    public ResultData<?> get(String url, Map<String, Object> reqMap, PageInfo pageInfo)
            throws Exception
    {
        if (null != pageInfo)
        {
            reqMap.put(QueryConst.PAGE_IDX, pageInfo.getCurPage().toString());
            reqMap.put(QueryConst.PAGE_SIZE, pageInfo.getPageLimit().toString());
        }

        //数据权限标示
        Boolean needAuth = (Boolean)reqMap.get(Constant.DATA_AUTH_KEY);
        if (null != needAuth && needAuth)
        {
            //获取当前用户及其下属用户Id集合, 用于数据权限过滤
            List<Integer> idsList = getUserIdList();

            reqMap.put("userIdList", idsList);
        }

        //日志记录begin
        long startTime = System.currentTimeMillis();

        //记录日志
        logger.info("BaseService request url=" + url + "; request params:" + JsonUtil.parseToJson(reqMap));

        //HTTP GET
        ResultData<?> reback = restTemplate.getForObject(url, ResultData.class, JsonUtil.parseToJson(reqMap));

        //日志记录end
        long endTime = System.currentTimeMillis();

        //记录日志
        logger.info("BaseService response url=" + url + "; response data:" + JsonUtil.parseToJson(reback) + "; cost time:(" + (endTime - startTime)
                + ") ms");

        if (null != pageInfo && null != reback)
        {

            //记录总数
            String total = (String)reback.getTotalCount();

            if (StringUtil.isNotEmpty(total))
            {

                pageInfo.setDataCount(Integer.valueOf(total));
            }
        }

        return reback;

    }

    /**
     * 获取当前用户及其下属用户Id集合
     * @param idsList
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Integer> getUserIdList()
            throws Exception
    {

        List<Integer> idsList = new ArrayList<Integer>();

        try
        {
            UserInfo userInfoTemp = UserInfoHolder.get();
            if (null != userInfoTemp && null != userInfoTemp.getUserId())
            {

                String AuthUrl = SystemParam.getWebConfigValue("RestServer") + "users" + "/{userId}/{postId}";
                ResultData<?> br = get(AuthUrl, UserInfoHolder.getUserId(), UserInfoHolder.get().getSelectpostId());
                idsList = (List<Integer>)br.getReturnData();
            }
        }
        catch (Exception e)
        {
        }
        return idsList;
    }

    /**
     * GET,  带参数，参数类型Object... urlVariables
     * @param url
     * @param id
     * @return
     * @throws Exception
     */
    public ResultData<?> get(String url, Object... urlVariables)
            throws Exception
    {
        //日志记录begin
        long startTime = System.currentTimeMillis();
        logger.info("BaseService request url=" + url + "; request params: urlVariables=" + urlVariables);

        //HTTP GET
        ResultData<?> backResult = new ResultData<T>();

        try
        {
            //HTTP GET
            backResult = restTemplate.getForObject(url, ResultData.class, urlVariables);

        }
        catch (Exception e)
        {
            //logger.error(moduleName, className, methodName, dto.toString(), operateId, ipAddress, description, e);
            throw e;
        }

        //日志记录end
        long endTime = System.currentTimeMillis();
        logger.info("BaseService response url=" + url + "; response data:" + JsonUtil.parseToJson(backResult) + "; cost time:("
                + (endTime - startTime) + ") ms");

        return backResult;
    }
    
}
