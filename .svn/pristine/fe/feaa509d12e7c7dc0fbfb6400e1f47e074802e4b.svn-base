/**
 * Copyright (c) 2017, www.ehousechina.com.
 * Project Name:PMLSWeb
 * File Name:StoreBizStopService.java
 * Package Name:cn.com.eju.deal.store.service
 * Date:2017年9月26日下午5:43:30
 */
package cn.com.eju.deal.store.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.store.StoreBizStop;

/**
 * ClassName: StoreBizStopService <br/>
 * Description: 门店停业上报 <br/>
 * 
 * @author yinkun
 * @date: 2017年9月26日 下午5:43:30 <br/>
 * @version V1.0
 */
@Service("storeBizStopService")
public class StoreBizStopService extends BaseService<StoreBizStop> {

    /**
     * 【描述】: 查询列表
     *
     * @author yinkun
     * @date: 2017年9月26日 下午6:13:09
     * @param reqMap
     * @param pageInfo
     * @return
     * @throws Exception
     */
    public ResultData<?> queryList(Map <String, Object> reqMap, PageInfo pageInfo) throws Exception{
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "storeBizStop" + "/list/{param}";
        
        return get(url, reqMap, pageInfo);

    }

    /**
     * 【描述】: 查看明细
     *
     * @author yinkun
     * @date: 2017年9月27日 上午11:54:37
     * @param stopId
     * @return
     * @throws Exception
     */
    public ResultData<?> getStopById(Integer stopId) throws Exception {
        
        String url = SystemParam.getWebConfigValue("RestServer") + "storeBizStop" + "/{stopId}";
        
        return get(url,stopId);
        
    }

    /**
     * 【描述】: 停业上报审核驳回
     *
     * @author yinkun
     * @date: 2017年9月27日 下午5:48:28
     * @param storeBizStop
     * @return
     * @throws Exception 
     */
    public ResultData<?> rejectStop(StoreBizStop storeBizStop) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "storeBizStop/reject";
        return post(url,storeBizStop);
    }

    /**
     * 【描述】: 停业上报审核通过
     *
     * @author yinkun
     * @date: 2017年9月28日 上午10:52:37
     * @param storeBizStop
     * @return
     * @throws Exception
     */
    public ResultData<?> auditPass(StoreBizStop storeBizStop) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "storeBizStop/pass";
        return post(url,storeBizStop);
    }
}

