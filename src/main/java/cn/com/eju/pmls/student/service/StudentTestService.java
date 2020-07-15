package cn.com.eju.pmls.student.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by eju on 2019/12/25.
 */
@Service("studentTestService")
public class StudentTestService extends BaseService{

    public ResultData<?> oaFrameContract(Map<String, Object> reqMap) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "test/oaFrameContract";
        ResultData<?> backResult = post(url, reqMap);
        return backResult;
    }

    public ResultData<?> oaProject(Map<String, Object> reqMap) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "test/oaProject";
        ResultData<?> backResult = post(url, reqMap);
        return backResult;
    }

    public ResultData<?> oaSignOrApproach(Map<String, Object> reqMap) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "test/oaSignOrApproach";
        ResultData<?> backResult = post(url, reqMap);
        return backResult;
    }

    public ResultData<?> oaDistribution(Map<String, Object> reqMap) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "test/oaDistribution";
        ResultData<?> backResult = post(url, reqMap);
        return backResult;
    }

    public ResultData<?> oaStatement(Map<String, Object> reqMap) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "test/oaStatement";
        ResultData<?> backResult = post(url, reqMap);
        return backResult;
    }

    public ResultData<?> oaAgreement(Map<String, Object> reqMap) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "test/oaAgreement";
        ResultData<?> backResult = post(url, reqMap);
        return backResult;
    }

    public ResultData<?> oaReceivables(Map<String, Object> reqMap) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "test/oaReceivables";
        ResultData<?> backResult = post(url, reqMap);
        return backResult;
    }

    public ResultData<?> oaExpenditure(Map<String, Object> reqMap) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "test/oaExpenditure";
        ResultData<?> backResult = post(url, reqMap);
        return backResult;
    }

    public ResultData<?> oaQk(Map<String, Object> reqMap) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "test/oaQk";
        ResultData<?> backResult = post(url, reqMap);
        return backResult;
    }
}
