package cn.com.eju.deal.follow.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.dto.follow.FollowDto;
import cn.com.eju.deal.follow.service.FollowService;

/** 
* @ClassName: followController 
* @Description: 跟进Controller
* @author 陆海丹 
* @date 2016年3月29日 下午8:59:31 
*/
@Controller
@RequestMapping(value = "follow")
public class FollowController extends BaseController
{
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "followService")
    private FollowService followService;
    
    /** 
    * @Title: querylist 
    * @Description: 跟进列表
    * @param request
    * @param mop
    * @return
    * @throws Exception     
    */
    @RequestMapping(value = "q", method = RequestMethod.GET)
    public String querylist(HttpServletRequest request, ModelMap mop)
    {
        //获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        
        //需要数据权限
        reqMap.put(Constant.DATA_AUTH_KEY, true);
        
        PageInfo pageInfo = getPageInfo(request);
        
        try
        {
            //获取页面显示数据
            ResultData<?> reback = this.followService.queryList(reqMap, pageInfo);
            //页面数据
            List<?> contentlist = (List<?>)reback.getReturnData();
            //存放到mop中
            mop.addAttribute("contentlist", contentlist);
            
            //获取当前用户及其下属用户Id集合, 用于页面权限过滤
            List<Integer> idsList = followService.getUserIdList();
            
            //存放到mop中
            mop.addAttribute("userIdList", idsList);
        }
        catch (Exception e)
        {
            logger.error("follow", "FollowControler", "querylist", "", null, "", " 跟进列表失败！", e);
        }
        
        return "follow/followListCtx";
    }
    
    /** 
    * @Title: showdetail 
    * @Description: 查看跟进详情
    * @param id
    * @param mop
    * @return
    * @throws Exception     
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showdetail(@PathVariable("id") Integer id, ModelMap mop)
    {
        //返回map
        ResultData<?> resultData = new ResultData<FollowDto>();
        try
        {
            resultData = followService.getById(id);
        }
        catch (Exception e)
        {
            logger.error("follow", "FollowControler", "showdetail", "", null, "", " 查看跟进详情失败！", e);
        }
        //存放到mop中
        mop.addAttribute("followDetail", resultData.getReturnData());
        return "follow/followDetail";
    }
    
    /** 
    * @Title: toAdd 
    * @Description: 跟进新增页面
    * @return
    * @throws Exception     
    */
    @RequestMapping(value = "c", method = RequestMethod.GET)
    public String tocreate(Integer storeId,  ModelMap mop)
    {
        mop.addAttribute("storeId", storeId);
        return "follow/followAdd";
    }
    
    /** 
    * @Title: create 
    * @Description: 新增跟进
    * @param request
    * @param modelMap
    * @return
    * @throws Exception     
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> create(HttpServletRequest request, ModelMap modelMap)
    {
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try
        {
            ResultData<?> resultData = this.followService.create(reqMap);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        }
        catch (Exception e)
        {
            logger.error("follow", "FollowControler", "create", "", null, "", " 新增跟进失败！", e);
        }
        return getOperateJSONView(rspMap);
    }
    
    /** 
    * @Title: toupdate 
    * @Description: 跟进更新页面
    * @param u ？？？
    * @param id 跟进编号
    * @param mop
    * @return
    * @throws Exception     
    */
    @RequestMapping(value = "u", method = RequestMethod.GET)
    public String toupdate(Integer followId, ModelMap mop)
    {
        //返回map
        ResultData<?> resultData = new ResultData<FollowDto>();
        try
        {
            resultData = this.followService.getById(followId);
        }
        catch (Exception e)
        {
            logger.error("follow", "FollowControler", "toupdate", "", null, "", "跟进更新页面", e);
        }
        //存放到mop中
        mop.addAttribute("followDetail", resultData.getReturnData());
        return "follow/followEdit";
    }
    
    /** 
    * @Title: update 
    * @Description: 跟进信息更新
    * @param request
    * @param isform
    * @param id 跟进编号
    * @return
    * @throws Exception     
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnView<?, ?> update(HttpServletRequest request, @RequestParam(value = "isform", required = false) String isform, @PathVariable("id") String id)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        reqMap.remove("_method");
        try
        {
            //更新
            this.followService.update(reqMap);
        }
        catch (Exception e)
        {
            logger.error("follow", "FollowControler", "update", "", null, "", "跟进信息更新", e);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);
    }
    
/*    @RequestMapping(value = "check", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> tocheckFollow(Integer storeId, ModelMap mop)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
      //请求map
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("storeId", storeId);
        reqMap.put("userCreate", UserInfoHolder.getUserId());
        ResultData<?> resultData = new ResultData<>();
        try
        {
            resultData = this.followService.checkFollow(reqMap);
        }
        catch (Exception e)
        {
            logger.error("follow", "FollowControler", "tocheckFollow", "", null, "", "", e);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, null);
          rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        if (null != resultData.getReturnData())
        {
            rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        }
        return getMapView(rspMap);
    }*/
}
