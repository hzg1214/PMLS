package cn.com.eju.pmls.store.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.store.service.PmlsFollowService;

/**
 * desc:门店跟进
 * @author :zhenggang.Huang
 * @date   :2020年2月26日
 */
@RestController
@RequestMapping(value = "pmlsFollow")
public class PmlsFollowController extends BaseController
{
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "pmlsFollowService")
    private PmlsFollowService pmlsFollowService;
    
    /**
     * 查询门店维护人历史
     */
	@RequestMapping(value = "queryFollowList/{storeId}", method = RequestMethod.POST)
	public ResultData<?> querylist(@PathVariable("storeId") Integer storeId,HttpServletRequest request, ModelMap mop) {
		//获取请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = new ResultData<>();
        PageInfo pageInfo = getPageInfo(request);
        try
        {
        	reqMap.put("storeId", storeId);
        	reqMap.put(Constant.DATA_AUTH_KEY, true);
            resultData = pmlsFollowService.queryList(reqMap, pageInfo);
          //获取当前用户及其下属用户Id集合, 用于页面权限过滤
            List<Integer> idsList = pmlsFollowService.getUserIdList();
            mop.addAttribute("userIdList", idsList);
        }
        
        catch (Exception e)
        {
            logger.error("follow", "PlmsFollowController", "queryFollowList", "", null, "", "", e);
        }
		return resultData;
	}
	
	/**
	 * desc:查看
	 * 2020年2月27日
	 */
	@RequestMapping(value = "followView/{id}/{storeId}", method = RequestMethod.GET)
    public ModelAndView followView(@PathVariable("id") Integer id,@PathVariable("storeId") Integer storeId, ModelMap mop) {

        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("storeId", storeId);
        reqMap.put("id", id);
        ResultData<?> resultData = new ResultData<>();
        try {
//        	resultData = pmlsFollowService.getFollowViewById(reqMap);
        	resultData = pmlsFollowService.getFollowViewById(id);
            mop.addAttribute("followDetail", JSONObject.toJSON(resultData.getReturnData()));
        } catch (Exception e) {
            logger.error("Store", "PmlsFollowControler", "followView",
                    "", null, "", "查看门店跟进详细页面", e);
        }

        //返回视图
        ModelAndView mv = new ModelAndView("store/followView");
        return mv;
    }
}
