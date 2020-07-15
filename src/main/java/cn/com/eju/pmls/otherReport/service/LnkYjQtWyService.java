package cn.com.eju.pmls.otherReport.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;

import cn.com.eju.pmls.otherReport.dto.LnkYjWyDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by eju on 2019/10/25.
 */
@Service("lnkYjQtWyService")
public class LnkYjQtWyService extends BaseService {

    public ResultData<?> getLnkYjQtWyList(Map<String,Object> reqMap, PageInfo pageInfo) throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "lnkYjQtWy" + "/getLnkYjQtWyList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;
    }

    public ResultData insertLnkQtWy(List<LnkYjWyDto> yjWyDtoList) throws Exception {
        LnkYjWyDto dto = new LnkYjWyDto();
        dto.setLnkYjWyDtoList(yjWyDtoList);
        String url = SystemParam.getWebConfigValue("RestServer") + "lnkYjQtWy" + "/imputAdd";
        ResultData reback = post(url, dto);
        return  reback;
    }
}
