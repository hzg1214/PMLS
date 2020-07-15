package cn.com.eju.pmls.commission.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.dto.common.CityDto;
import cn.com.eju.pmls.commission.dto.PerformSwitchDto;
import cn.com.eju.pmls.commission.service.EstateSwitchService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
@RequestMapping(value = "pmlsSwitch")
public class PmlsSwitchController extends BaseController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "estateSwitchService")
    private EstateSwitchService estateSwitchService;

    /**
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list() {
        //构建ModelAndView实例，并设置跳转页面
        ModelAndView mv = new ModelAndView("commission/switchList");
        String year = DateUtil.fmtDate(new Date(), "yyyy");
        String month = DateUtil.fmtDate(new Date(), "MM");
        if ("01".equals(month)) {
            mv.addObject("year", Integer.valueOf(year) - 1);
            mv.addObject("month", "12");
        } else {
            mv.addObject("year", year);
            int monthNow = Integer.valueOf(month) - 1;
            if (monthNow < 10) {
                mv.addObject("month", "0" + monthNow);
            } else {
                mv.addObject("month", monthNow);
            }
        }

        //待改2  登录用户的城市权限
        List<CityDto> cityDtoList = JSON.parseArray(JSON.toJSONString(UserInfoHolder.get().getCities()), CityDto.class);

        mv.addObject("cityDtoList", cityDtoList);
        return mv;
    }

    @RequestMapping(value = "q", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> listCtx(HttpServletRequest request, @RequestBody List<String> searchCityNoList) {

        //获取页面请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

    /*    List<Map<String,Object>> cities = UserInfoHolder.get().getCities();
        List<String> cityList = new ArrayList<>();
        for (CityDto cityDto : cities) {
            cityList.add(cityDto.getCityNo());
        }*/

        List<String> cityList = new ArrayList<>();

        if (searchCityNoList == null || searchCityNoList.size() == 0) {
            //待改2  登录用户的城市权限
            List<CityDto> cityDtoList = JSON.parseArray(JSON.toJSONString(UserInfoHolder.get().getCities()), CityDto.class);
            for (int i = 0; i < cityDtoList.size(); i++) {
                cityList.add(cityDtoList.get(i).getCityNo());
            }
        } else {
            cityList = searchCityNoList;
        }


        if (cityList.size() == 0) {
            //此处加null是为了防止list为空时，sql中没有cityNo的判断条件，导致所有数据全部展示出来
            cityList.add("null");
        }
        //当前用户的城市NO list
        reqMap.put("cityList", cityList);

        reqMap.put("relateSystem", 27601);

        //获取页面显示数据
        ResultData<?> reback = new ResultData<>();
        try {
            reback = estateSwitchService.listCtx(reqMap);
        } catch (Exception e) {
            logger.error("estateswitch",
                    "PmlsSwitchController",
                    "listCtx",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询列表内容异常",
                    e);
        }
        return reback;
    }

    @RequestMapping(value = "openCloseSwitch", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> create(HttpServletRequest request, String estateList) {
        //获取页面请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        ResultData<?> resultData = new ResultData<>();
        UserInfo userInfo = UserInfoHolder.get();
        List<PerformSwitchDto> dtolist = JSONArray.parseArray(estateList, PerformSwitchDto.class);
        for (PerformSwitchDto performSwitchDto : dtolist) {
            if (27502 == performSwitchDto.getSwitchState().intValue()) {
                performSwitchDto.setCloseDate(new Date());
                performSwitchDto.setCloseUserCode(userInfo.getUserCode());
                performSwitchDto.setCloseUserName(userInfo.getUserName());
            } else {
                performSwitchDto.setOpenDate(new Date());
                performSwitchDto.setOpenUserCode(userInfo.getUserCode());
                performSwitchDto.setOpenUserName(userInfo.getUserName());
            }
            performSwitchDto.setCreateUserCode(userInfo.getUserCode());
            performSwitchDto.setCreateUserName(userInfo.getUserName());
        }
        try {
            resultData = estateSwitchService.create(dtolist);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        } catch (Exception e) {
            logger.error("pmlsEstateswitch",
                    "PmlsSwitchController",
                    "create",
                    "input param: List<PerformSwitchDto>=" + dtolist.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "新增异常",
                    e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "操作失败");
        }
        return getSearchJSONView(rspMap);

    }

    /**
     * 查看该月份下城市是否尚有大定待审核的记录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/checkRoughToValid", method = RequestMethod.GET)
    @ResponseBody
    public Object checkRoughToValid(Model model, HttpServletRequest request) {
        ResultData<?> resultData = new ResultData<>();
        Map<String, Object> reqMap = bindParamToMap(request);
        Object result = null;
        try {
            resultData = estateSwitchService.checkRoughToValid(reqMap);
            if (resultData != null && resultData.getReturnCode().equals(ReturnCode.SUCCESS)) {
                result = resultData.getReturnData();
            }
        } catch (Exception e) {
            if (result == null) {
                resultData.setFail();
            }
            logger.error("pmlsEstateswitch", "PmlsSwitchController", "checkRoughToValid", "", UserInfoHolder.getUserId(), "", "", e);
        }
        return result;
    }

    /**
     * * 检验收款单的入账日期处于关账月份且未拆分完毕，不允许关账！
     */
    @RequestMapping(value = "/checkSkAllocate", method = RequestMethod.GET)
    @ResponseBody
    public ResultData<?> checkSkAllocate(HttpServletRequest request) {
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        try {
            resultData = estateSwitchService.checkSkAllocate(reqMap);
        } catch (Exception e) {
            resultData.setFail("操作发生异常，请联系管理员！");
            logger.error("PmlsSwitchController.checkSkAllocate 校验拆分操作发生异常！", e);
        }
        return resultData;
    }
}
