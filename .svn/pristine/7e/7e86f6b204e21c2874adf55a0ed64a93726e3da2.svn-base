package cn.com.eju.deal.picture.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.picture.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by tanlang on 2017-06-12.
 */
@Controller
@RequestMapping(value = "picture")
public class PictureController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private PictureService pictureService;

    @RequestMapping(value = "showPicture/{pictureRefId}", method = RequestMethod.GET)
    public String showPicture(@PathVariable String pictureRefId, ModelMap modelMap) {
        try {
            ResultData<?> resultData = pictureService.getPictureUrl(pictureRefId);

            modelMap.addAttribute("contentlist", resultData.getReturnData());
        } catch (Exception e) {
            logger.error("picture", "PictureController", "showPicture", "", null, "", " 查看图片失败！", e);
        }

        return "followDetail/pictureDetail";
    }

}
