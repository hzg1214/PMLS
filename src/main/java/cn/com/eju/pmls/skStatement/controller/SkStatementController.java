package cn.com.eju.pmls.skStatement.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.skStatement.service.SkStatementService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("skStatement")
public class SkStatementController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "skStatementService")
    private SkStatementService skStatementService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView jsStatementList(HttpServletRequest request, ModelMap mop) {
        return new ModelAndView("skStatement/skStatementList");
    }

    @RequestMapping(value = "queryList", method = RequestMethod.POST)
    public ResultData<?> queryList(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        PageInfo pageInfo = getPageInfo(request);
        try {
            reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
            //获取页面显示数据
            resultData = skStatementService.queryList(reqMap, pageInfo);
        } catch (Exception e) {
            resultData.setFail("查询收入明细发生异常");
            logger.error("查询收入明细发生异常", e);
        }
        return resultData;
    }

    @RequestMapping(value = "{id}/view", method = RequestMethod.GET)
    public ModelAndView showDetail(@PathVariable("id") String id, ModelMap mop, HttpServletRequest request) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("userId", UserInfoHolder.getUserId());
            map.put("cityNo", UserInfoHolder.get().getCityNo());
            ResultData<?> resultData = skStatementService.getSkStatementById(map);

            mop.addAttribute("info", resultData.getReturnData());
        } catch (Exception ex) {
            logger.error("【showDetail】获取收入拆分基本信息异常,id:" + id, ex);
        }
        return new ModelAndView("skStatement/skStatementDetail");
    }

    @RequestMapping(value = "{id}/allocate", method = RequestMethod.GET)
    public ModelAndView allocate(@PathVariable("id") String id, ModelMap mop, HttpServletRequest request) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("userId", UserInfoHolder.getUserId());
            map.put("cityNo", UserInfoHolder.get().getCityNo());
            ResultData<?> resultData = skStatementService.getSkStatementById(map);

            mop.addAttribute("info", resultData.getReturnData());
        } catch (Exception ex) {
            logger.error("【allocate】获取收入拆分基本信息异常,id:" + id, ex);
        }
        return new ModelAndView("skStatement/skStatementAllocate");
    }

    @RequestMapping(value = "{id}/allocateCheck", method = RequestMethod.GET)
    public ResultData<?> toInvalid(@PathVariable("id") String id, HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            reqMap.put("id", id);
            reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
            resultData = skStatementService.allocateCheck(reqMap);
        } catch (Exception e) {
            resultData.setFail("操作发生异常，请联系管理员！");
            logger.error("SkStatementController.allocateCheck 校验拆分操作发生异常！", e);
        }
        return resultData;
    }
}
