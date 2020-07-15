package cn.com.eju.pmls.otherReport.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;

import cn.com.eju.pmls.otherReport.dto.LnkYjNyDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by eju on 2019/10/28.
 */
@Service("lnkYjQtNyService")
public class LnkYjQtNyService extends BaseService {

    public ResultData<?> getLnkYjQtNyList(Map<String,Object> reqMap, PageInfo pageInfo) throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "lnkYjQtNy" + "/getLnkYjQtNyList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;
    }

    public ResultData insertLnkQtNy(List<LnkYjNyDto> yjNyDtoList) throws Exception {
        LnkYjNyDto dto = new LnkYjNyDto();
        dto.setLnkYjNyDtoList(yjNyDtoList);
        String url = SystemParam.getWebConfigValue("RestServer") + "lnkYjQtNy" + "/imputAdd";
        ResultData reback = post(url, dto);
        return  reback;
    }
}
