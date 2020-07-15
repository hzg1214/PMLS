package cn.com.eju.deal.picture.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.picture.PictureDto;
import org.springframework.stereotype.Service;

/**
 * Created by tanlang on 2017-06-12.
 */
@Service(value = "pictureService")
public class PictureService extends BaseService<PictureDto> {

    public ResultData<?> getPictureUrl(String pictureRefId) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "picture/getPictureUrl/" + pictureRefId;

        ResultData<?> reback = get(url);

        return reback;
    }

}
