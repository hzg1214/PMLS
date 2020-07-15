package cn.com.eju.deal.followDetail.controller;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.dto.followDetail.FollowDetailDto;
import cn.com.eju.deal.followDetail.service.FollowDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.Store;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "followDetail")
public class FollowDetailController extends BaseReportController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private FollowDetailService followDetailService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String followDetail() {
        return "followDetail/followDetail";
    }

    @RequestMapping(value = "query", method = RequestMethod.GET)
    public String query(HttpServletRequest request, ModelMap mop) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        PageInfo pageInfo = getPageInfo(request);

        changeParamToList(reqMap);
        changeParam(reqMap);

        try {
            //获取页面显示数据
            ResultData<?> reback = this.followDetailService.query(reqMap, pageInfo);
            //页面数据
            List<?> contentlist = (List<?>) reback.getReturnData();
            //存放到mop中
            mop.addAttribute("contentlist", contentlist);

        } catch (Exception e) {
            logger.error("follow", "FollowDetailController", "query", "", null, "", " 跟进明细失败！", e);
        }

        return "followDetail/followDetailListCtx";
    }

    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        changeParamToList(reqMap);
        changeParam(reqMap);

        try {
            //获取页面显示数据
            ResultData<?> reback = this.followDetailService.query(reqMap, null);
            //页面数据
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            downLoadExcel(request,response,contentlist,reqMap, ReportConstant.FOLLOWDETAIL_CODE,ReportConstant.FOLLOWDETAIL_NAME);

        } catch (Exception e) {
            logger.error("follow", "FollowDetailController", "export", "", null, "", " 导出跟进明细失败！", e);
        }

    }

    @RequestMapping(value = "showMap/{followerId}/{followDate}", method = RequestMethod.GET)
    public String showMap(@PathVariable String followerId, @PathVariable String followDate, ModelMap modelMap) {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("followerId", followerId);
        reqMap.put("followDate", followDate);

        try {
            ResultData<?> reback = followDetailService.getMapInfo(reqMap);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();
            StringBuffer sb = new StringBuffer();
            sb.append("[");
            int index = 0;
            int size = contentlist.size();
            for (LinkedHashMap<String, Object> map : contentlist) {
                if (index == size - 1){
                    sb.append("{\'longitude\':\'").append(map.get("signLongitude")).append("\',\'latitude\':\'").append(map.get("signLatitude")).append("\',\'followDate\':\'").append(map.get("followDate")).append("\'}");
                }else{
                    sb.append("{\'longitude\':\'").append(map.get("signLongitude")).append("\',\'latitude\':\'").append(map.get("signLatitude")).append("\',\'followDate\':\'").append(map.get("followDate")).append("\'},");
                }
                index++;
            }

            sb.append("]");
            modelMap.addAttribute("contentlist", sb.toString());

        } catch (Exception e) {
            logger.error("follow", "FollowDetailController", "showMap", "", null, "", " 查看地图失败！", e);
        }

        return "followDetail/followMap";
    }

    @RequestMapping(value = "getSignDetail", method = RequestMethod.GET)
    public String getSignDetail(ModelMap modelMap, HttpServletRequest request) {
        try {
            Map<String, Object> reqMap = bindParamToMap(request);
            FollowDetailDto dto = MapToEntityConvertUtil.convert(reqMap,FollowDetailDto.class,"");
            ResultData<?> resultData = followDetailService.getSignDetail(dto);
            modelMap.addAttribute("contentlist", resultData.getReturnData());
        } catch (Exception e) {
            logger.error("picture", "PictureController", "showPicture", "", null, "", " 查看签到详情失败！", e);
        }

        return "followDetail/pictureDetail";
    }
}
