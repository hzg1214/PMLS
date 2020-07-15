package cn.com.eju.deal.storeStructure.controller;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.FileUtils;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.storeStructure.StoreStructureDto;
import cn.com.eju.deal.poi.ExcelForStoreStructure;
import cn.com.eju.deal.storeStructure.service.StoreStructureService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@RestController
@RequestMapping(value = "storeStructure")
public class StoreStructureController extends BaseReportController {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "storeStructureService")
    private StoreStructureService storeStructureService;

    /**
     * 门店结构视图
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView storeStructure() {
        ModelAndView mav = new ModelAndView("storeStructure/storeStructure");
        return mav;
    }

    /**
     * 查询
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "query", method = RequestMethod.GET)
    public ModelAndView query(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("storeStructure/storeStructureCtx");
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());

        reqMap = changeParam(reqMap);

        try {
            //获取页面显示数据
            ResultData<StoreStructureDto> reback = storeStructureService.queryStoreStructure(reqMap);
            //页面数据
            List<StoreStructureDto> list = (List<StoreStructureDto>) reback.getReturnData();

            mav.addObject("contentlist", list);

        } catch (Exception e) {
            logger.error("storeStructure", "StoreStructureController", "query", "", null, "", "门店结构失败！", e);
        }
        return mav;
    }


    /**
     * 导出
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("userId", UserInfoHolder.getUserId());

        reqMap = changeParam(reqMap);

        try {
            //获取页面显示数据
            ResultData<?> reback = storeStructureService.queryStoreStructure(reqMap);
            //页面数据
            List<LinkedHashMap<String, Object>> storeStructureList = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            downLoadExcel(request, response, storeStructureList, reqMap, ReportConstant.STORESTRUCTURE_CODE, ReportConstant.STORESTRUCTURE_NAME);
        } catch (Exception e) {
            logger.error("follow", "FollowDetailController", "export", "", null, "", " 导出门店结构失败！", e);
        }

    }
}
