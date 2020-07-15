/**
 * Copyright (c) 2017, www.ehousechina.com.
 * Project Name:PMLSWeb
 * File Name:FeebackService.java
 * Package Name:cn.com.eju.deal.feeback.service
 * Date:2017年9月19日下午3:28:16
 */
package cn.com.eju.deal.feeback.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.feeback.Feeback;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.seqNo.service.SeqNoService;

/**
 * ClassName: FeebackService <br/>
 * Description: 意见反馈 <br/>
 * 
 * @author yinkun
 * @date: 2017年9月19日 下午3:28:16 <br/>
 * @version V1.0
 */
@Service("feebackService")
public class FeebackService extends BaseService<Feeback> {
    
    @Resource(name = "seqNoService")
    private SeqNoService seqNoService;

    /**
     * 【描述】: 保存意见反馈
     *
     * @author yinkun
     * @date: 2017年9月19日 下午3:32:53
     * @param feeback
     * @throws Exception
     */
    public void save(Feeback feeback) throws Exception{
        
        //反馈编号
        ResultData<?> resultData = seqNoService.getSeqNo("TYPE_FB");
        feeback.setFeebackNo(resultData.getReturnData().toString());
        //反馈人信息
        UserInfo info = UserInfoHolder.get();
        feeback.setUserId(info.getUserId());
        feeback.setUserCode(info.getUserCode());
        feeback.setUserName(info.getUserName());
        feeback.setCellphone(info.getCellphone());
        //未处理
        feeback.setReplyStatus(18101);
        //系统标识
        feeback.setSystemCode(18202);
        
        String url = SystemParam.getWebConfigValue("RestServer") + "feeback";
        post(url, feeback);
    }
}

