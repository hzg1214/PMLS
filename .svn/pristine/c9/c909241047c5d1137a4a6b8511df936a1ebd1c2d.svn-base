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
import cn.com.eju.pmls.commission.dto.PmlsPerformSwitchWeekDto;
import cn.com.eju.pmls.commission.service.PmlsPerformSwitchWeekService;
import cn.com.eju.pmls.remittanceTrack.service.RemittanceTrackService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
@RequestMapping(value = "pmlsPerformSwitchWeek")
public class PmlsPerformSwitchWeekController extends BaseController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "pmlsPerformSwitchWeekService")
    private PmlsPerformSwitchWeekService pmlsPerformSwitchWeekService;

    @Resource(name = "remittanceTrackService")
    private RemittanceTrackService remittanceTrackService;


    /**
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView list(){
        //构建ModelAndView实例，并设置跳转页面
        ModelAndView mv = new ModelAndView("commission/switchWeekList");
        /*String year= DateUtil.fmtDate(new Date(),"yyyy");
        String month=DateUtil.fmtDate(new Date(),"MM");
        if("01".equals(month)){
            mv.addObject("year",Integer.valueOf(year)-1);
            mv.addObject("month", "12");
        }else{
            mv.addObject("year", year);
            int monthNow = Integer.valueOf(month)-1;
            if(monthNow < 10){
                mv.addObject("month", "0"+monthNow);
            }else{
                mv.addObject("month", monthNow);
            }
        }*/

        String year= DateUtil.fmtDate(new Date(),"yyyy");
        String month=DateUtil.fmtDate(new Date(),"MM");
        mv.addObject("year",year);
        mv.addObject("month",month);

        //待改2  登录用户的城市权限
        List<CityDto> cityDtoList =  JSON.parseArray(JSON.toJSONString(UserInfoHolder.get().getCities()),CityDto.class);

        mv.addObject("cityDtoList", cityDtoList);
        return mv;
    }

    @RequestMapping(value = "getList", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> getList(HttpServletRequest request, @RequestBody List<String> searchCityNoList) {

        //获取页面请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

    /*    List<Map<String,Object>> cities = UserInfoHolder.get().getCities();
        List<String> cityList = new ArrayList<>();
        for (CityDto cityDto : cities) {
            cityList.add(cityDto.getCityNo());
        }*/

        List<String> cityList = new ArrayList<>();

        if(searchCityNoList==null || searchCityNoList.size()==0){
            //待改2  登录用户的城市权限
            List<CityDto> cityDtoList =  JSON.parseArray(JSON.toJSONString(UserInfoHolder.get().getCities()),CityDto.class);
            for (int i=0;i<cityDtoList.size();i++) {
                cityList.add(cityDtoList.get(i).getCityNo());
            }
        }else {
            cityList = searchCityNoList;
        }


        if(cityList.size() == 0){
            //此处加null是为了防止list为空时，sql中没有cityNo的判断条件，导致所有数据全部展示出来
            cityList.add("null");
        }
        //当前用户的城市NO list
        reqMap.put("cityList", cityList);

        reqMap.put("relateSystem", 29301);

        //获取页面显示数据
        ResultData<?> reback =new ResultData<>();
        try {
            reback = pmlsPerformSwitchWeekService.getList(reqMap);
        } catch (Exception e) {
            logger.error("pmlsPerformSwitchWeek",
                    "PmlsPerformSwitchWeekController",
                    "getList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询列表内容异常",
                    e);
        }
        return reback;
    }



    /**
     * desc:关账数据初始化-年月-周
     * 2020年04月07日
     */
    @RequestMapping(value = "/getWeeks", method = RequestMethod.GET)
    @ResponseBody
    public ResultData<?> getWeeks(HttpServletRequest request) {
        //返回map
        Map<String, Object> queryParam = bindParamToMap(request);
        //返回map
        ResultData resultData = new ResultData<>();

        try {
            resultData = remittanceTrackService.getWeeks(queryParam);
        } catch (Exception e) {
            logger.error("pmlsPerformSwitchWeek", "PmlsPerformSwitchWeekController", "getWeeks", "", null, "", "回款跟踪数据初始化-年月-周", e);
        }

        return resultData;
    }




    @RequestMapping(value = "openCloseSwitch", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> openCloseSwitch(HttpServletRequest request, @RequestBody List<PmlsPerformSwitchWeekDto> dtolist) {
        //获取页面请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        ResultData<?> resultData = new ResultData<>();
        UserInfo userInfo = UserInfoHolder.get();

        for (PmlsPerformSwitchWeekDto pmlsPerformSwitchWeekDto : dtolist) {
            pmlsPerformSwitchWeekDto.setCreateDate(new Date());
            pmlsPerformSwitchWeekDto.setCreateUserCode(userInfo.getUserCode());
           /* if(27502==performSwitchDto.getSwitchState().intValue()){
                performSwitchDto.setCloseDate(new Date());
                performSwitchDto.setCloseUserCode(userInfo.getUserCode());
                performSwitchDto.setCloseUserName(userInfo.getUserName());
            }else{
                performSwitchDto.setOpenDate(new Date());
                performSwitchDto.setOpenUserCode(userInfo.getUserCode());
                performSwitchDto.setOpenUserName(userInfo.getUserName());
            }
            performSwitchDto.setCreateUserCode(userInfo.getUserCode());
            performSwitchDto.setCreateUserName(userInfo.getUserName());*/
        }
        try {
            resultData=pmlsPerformSwitchWeekService.create(dtolist);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        } catch (Exception e) {
            logger.error("pmlsPerformSwitchWeek",
                    "PmlsPerformSwitchWeekController",
                    "openCloseSwitch",
                    "input param: List<PmlsPerformSwitchWeekDto>=" + dtolist.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "新增异常",
                    e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "操作失败");
        }
        return getSearchJSONView(rspMap);

    }
}
