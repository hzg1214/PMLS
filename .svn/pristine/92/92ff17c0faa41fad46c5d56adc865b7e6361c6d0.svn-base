package cn.com.eju.deal.Signed.controller;

import cn.com.eju.deal.Report.constant.ReportConstant;
import cn.com.eju.deal.Report.controller.BaseReportController;
import cn.com.eju.deal.Report.service.ExpendReportService;
import cn.com.eju.deal.Signed.service.SignReportService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.dto.follow.FollowDto;
import cn.com.eju.deal.follow.service.FollowService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Controller层
 *
 * @author QJP
 * @date 2017年6月22日
 */
@Controller
@RequestMapping(value = "signedStore")
public class SignReportController extends BaseReportController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private SignReportService signReportService;

    @Resource(name = "followService")
    private FollowService followService;

    @Resource
    private ExpendReportService expendReportService;

    /**
     * 初始化
     *
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap mop) {
        return "Signed/signDetailList";
    }

    /**
     * 查询已签门店明细--list
     *
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "q", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap mop) {
        // 获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("createUserId", UserInfoHolder.getUserId());
        PageInfo pageInfo = getPageInfo(request);

        changeParamToList(reqMap);
        changeParam(reqMap);

        //获得当前登陆人所有的城市
        try {

            getAllCity(reqMap);
            changeParamToList(reqMap);

        } catch (Exception e) {

            logger.error("signReport", "SignReportController", "index", "", UserInfoHolder.getUserId(), "", "所有城市查询失败", e);
        }

        //获取页面显示数据
        List<?> reportlist = new ArrayList<>();
        try {
            ResultData<?> reback = signReportService.querySignDetailList(reqMap, pageInfo);

            //页面数据
            reportlist = (List<?>) reback.getReturnData();

        } catch (Exception e) {
            logger.error("signReport", "SignReportController", "index", "", UserInfoHolder.getUserId(), "", "已签门店明细查询失败", e);
        }

        //存放到mop中
        mop.addAttribute("reportlist", reportlist);


        return "Signed/signDetailListCxt";
    }


    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.put("createUserId", UserInfoHolder.getUserId());

        changeParamToList(reqMap);
        changeParam(reqMap);

        //获得当前登陆人所有的城市
        try {

            getAllCity(reqMap);
            changeParamToList(reqMap);

        } catch (Exception e) {

            logger.error("signReport", "SignReportController", "export", "", UserInfoHolder.getUserId(), "", "所有城市查询失败", e);
        }

        try {
            ResultData<?> reback = signReportService.querySignDetailList(reqMap, null);
            List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();

            downLoadExcel(request, response, contentlist, reqMap, ReportConstant.SIGNDETAIL_CODE, ReportConstant.SIGNDETAIL_NAME);

        } catch (Exception e) {
            logger.error("signReport", "SignReportController", "export", "input param: reqMap=" + reqMap.toString(), -UserInfoHolder.getUserId(), WebUtil.getIPAddress(request), "导出异常", e);
        }
    }

    /**
     * @param request
     * @param mop
     * @return
     * @throws Exception
     * @Title: getfollowList
     * @Description: 取得跟进列表
     */
    @RequestMapping(value = "getfollowList/{id}", method = RequestMethod.GET)
    public String getFollowList(@PathVariable("id") Integer id, HttpServletRequest request, ModelMap mop) {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);

        //需要数据权限
        reqMap.put(Constant.DATA_AUTH_KEY, true);
        reqMap.put("storeId", id);
        reqMap.put("orderType", "DESC");
        reqMap.put("orderName", "dateCreate");

        try {
            //获取页面显示数据
            ResultData<?> reback = this.followService.queryList(reqMap, pageInfo);
            //页面数据
            List<?> contentlist = (List<?>) reback.getReturnData();
            //存放到mop中
            mop.addAttribute("contentlist", contentlist);

            //获取当前用户及其下属用户Id集合, 用于页面权限过滤
            List<Integer> idsList = followService.getUserIdList();

            //存放到mop中
            mop.addAttribute("userIdList", idsList);
        } catch (Exception e) {
            logger.error("storeFollow", "storeFollowListCtxControler", "querylist", "", null, "", " 跟进列表失败！", e);
        }

        return "Signed/storeFollowListCtx";
    }

    /**
     * @param id
     * @param mop
     * @return
     * @throws Exception
     * @Title: showdetail
     * @Description: 查看跟进详情
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showdetail(@PathVariable("id") Integer id, ModelMap mop) {
        //返回map
        ResultData<?> resultData = new ResultData<FollowDto>();
        try {
            resultData = followService.getById(id);
        } catch (Exception e) {
            logger.error("follow", "FollowControler", "showdetail", "", null, "", " 查看跟进详情失败！", e);
        }
        //存放到mop中
        mop.addAttribute("followDetail", resultData.getReturnData());
        return "Signed/storeFollowDetail";
    }


    //选择城市查询中心
    @RequestMapping(value = "getCenterGroupName", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView getCenterGroupName(HttpServletRequest request, ModelMap mop) {

        // 获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        //设置用户id
        reqMap.put("userId", UserInfoHolder.getUserId());
        Map<?, ?> reback = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        // 获取页面显示数据
        try {
            reback = signReportService.getCenterGroupName(reqMap);
            if (null != reback.get("list")) {
                List<LinkedHashMap<String, Object>> list = (List<LinkedHashMap<String, Object>>) reback.get("list");
                map.put("list", list);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return getOperateJSONView(map);
    }

}
