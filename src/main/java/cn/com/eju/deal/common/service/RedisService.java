package cn.com.eju.deal.common.service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by hzy on 2017/8/23.
 */
@Service("redisService")
public class RedisService {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     *
     * @param key key
     * @param val val
     * @param time 失效时间
     * @return json
     */
    public ResultData setVal(String key,String val,Integer time){
        ResultData resultData = new ResultData();
        resultData.setFail("调用redis接口失败");

        try {

            redisTemplate.opsForValue().set(key,val);
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
            resultData.setSuccess();
            resultData.setReturnData(key);

        } catch (Exception e) {

            logger.error("IRedisService","RedisService","addWithTimeQr","userId="+ 0, 0,null,
                    "调用redis接口:添加key带时效性#####请求参数#redisKey="+key,e);
            resultData.setFail("调用redis接口异常");
            return resultData;

        }

        return resultData;
    }

    /**
     * 获取redis中的值
     * @param key key
     * @return json
     */
    public ResultData getVal(String key) {
        ResultData resultData = new ResultData<Boolean>();
        resultData.setFail("调用redis接口失败");

        String Value = null;
        try {
            Value = redisTemplate.opsForValue().get(key);
            if(StringUtil.isEmpty(Value)){
                resultData.setFail("验证码已经失效");
            }else{
                resultData.setSuccess();
                resultData.setReturnData(Value);
            }
        } catch (Exception e) {
            logger.error("IRedisService","RedisServiceImpl","getVal","userId="+ 0, 0,null,
                    "调用redis接口:获取key异常 #####请求参数#key="+key+ "#####返回信息keyValue="+Value,e);
            resultData.setFail("调用redis接口异常");
            return resultData;
        }

        return resultData;
    }
}
