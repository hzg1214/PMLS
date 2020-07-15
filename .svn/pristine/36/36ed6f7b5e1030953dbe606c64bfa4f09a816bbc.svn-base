package cn.com.eju.pmls.otherReport.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.otherReport.dto.LnkYjFyDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by eju on 2019/10/29.
 */
@Service("lnkYjQtFyService")
public class LnkYjQtFyService extends BaseService {

    public ResultData<?> getLnkYjQtFyList(Map<String,Object> reqMap, PageInfo pageInfo) throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "lnkYjQtFy" + "/getLnkYjQtFyList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;
    }

    public ResultData insertLnkQtFy(List<LnkYjFyDto> yjWyDtoList) throws Exception {
        LnkYjFyDto dto = new LnkYjFyDto();
        dto.setLnkYjFyDtoList(yjWyDtoList);
        String url = SystemParam.getWebConfigValue("RestServer") + "lnkYjQtFy" + "/imputAdd";
        ResultData reback = post(url, dto);
        return  reback;
    }
}
