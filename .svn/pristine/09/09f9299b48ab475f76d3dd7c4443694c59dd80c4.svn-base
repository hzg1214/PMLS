package cn.com.eju.pmls.borrowMoneyFrameContract.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.helper.SeqNoHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.pmls.borrowMoneyFrameContract.dto.BorrowMoneyFrameContractDto;
import cn.com.eju.pmls.borrowMoneyFrameContract.service.BorrowMoneyFrameContractService;
import cn.com.eju.pmls.channelBusiness.service.BusinessManagerService;

@RestController
@RequestMapping("borrowMoneyFrameContract")
public class BorrowMoneyFrameContractController extends BaseController {
	
    @Resource(name = "borrowMoneyFrameContractService")
    private BorrowMoneyFrameContractService borrowMoneyFrameContractService;
    
    @Resource(name = "businessManagerService")
    private BusinessManagerService businessManagerService;

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    /**
     * desc:借佣框架协议-初始化
     * 2020年4月7日
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView remittanceTrackList(ModelMap mop) {
        return new ModelAndView("borrowMoneyFrameContract/borrowMoneyFrameContractList");
    }
    
    
    /**
     * desc:借佣框架协议-列表
     * 2020年4月7日
     */
    @RequestMapping(value = "/getBorrowMoneyFrameContractList", method = RequestMethod.POST)
    @ResponseBody
    public ResultData<?> getBorrowMoneyFrameContractList(HttpServletRequest request) {

    	Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = null;
        String cityNo = UserInfoHolder.get().getCityNo();
//        reqMap.put("cityNo", cityNo);
        PageInfo pageInfo = getPageInfo(request);
        logger.info("借佣框架协议，查询列表，入参:"+reqMap);
        try {
            //获取页面显示数据
            resultData = borrowMoneyFrameContractService.getBorrowMoneyFrameContractList(reqMap, pageInfo);
            logger.info("回款跟踪数据，查询列表成功，返回数据:"+resultData);
        } catch (Exception e) {
            logger.error("borrowMoneyFrameContract",
                    "BorrowMoneyFrameContractController",
                    "getBorrowMoneyFrameContractList",
                    "input param: reqMap=" + reqMap.toString(),
                    UserInfoHolder.getUserId(),
                    WebUtil.getIPAddress(request),
                    "查询借佣框架协议列表异常",
                    e);
        }
        return resultData;
    }
    
    /**
     * 新增框架合同页面
     * @return
     */
    @RequestMapping(value = "/addBorrowMoneyFrameContractPage", method = RequestMethod.GET)
    public ModelAndView addBorrowMoneyFrameContractPage(Model model, HttpServletRequest request) {
        String id=request.getParameter("id");
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = new ResultData<>();
        try {
            if(id!=null && !"".equals(id)){//查询商户信息，进入修改页面
                resultData = borrowMoneyFrameContractService.getbriefById(Integer.parseInt(id));
                model.addAttribute("id",id);
                model.addAttribute("frameContractDto", JSONObject.toJSON(resultData.getReturnData()));
            }
        }catch (Exception e){
            logger.error("borrowMoneyFrameContract", "BorrowMoneyFrameContractController", "addBorrowMoneyFrameContractPage", reqMap.toString(), null, "", "查询借佣框架协议信息失败", e);
        }
        ModelAndView mv = new ModelAndView("borrowMoneyFrameContract/addBorrowMoneyFrameContractPage");
        return mv;
    }
    
    /**
     * 
     * desc:选择公司名称
     * 2020年4月27日
     */
    @RequestMapping(value = "/selectCompanyPage", method = RequestMethod.GET)
    public ModelAndView selectBusinessPage(HttpServletRequest request, ModelMap mop){
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.addAttribute("reqMap",reqMap);
        return new ModelAndView("borrowMoneyFrameContract/selectCompanyPage");
    }
    
    
    /**
     * 获取公司列表
     * @param request
     * @param mop
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getBorrowMoneyFrameContractCompanyList", method = RequestMethod.POST)
    public ResultData getBorrowMoneyFrameContractCompanyList(HttpServletRequest request, ModelMap mop)
    {
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        //返回list
        ResultData<?> resultData = null;
        List<?> contentlist = new ArrayList<>();
        //1公司名称选择 2 关联公司添加  并且不带城市筛选
        String companyType = (String) reqMap.get("companyType");
        try
        {
//        	if("1".equals(companyType)) {
//        		reqMap.put("cityNo", UserInfoHolder.get().getCityNo());
//        	}
            resultData = borrowMoneyFrameContractService.getBorrowMoneyFrameContractCompanyList(reqMap,pageInfo);

        }
        catch (Exception e)
        {
            logger.error("borrowMoneyFrameContract", "BorrowMoneyFrameContractController", "getBorrowMoneyFrameContractCompanyList", reqMap.toString(), null, "", "新增借佣框架协议获取公司列表失败", e);
        }
        return resultData;
    }
    
    /**
     * desc:获取借佣协议开户信息
     * 2020年4月28日
     */
    @RequestMapping(value = "/getOldBMFCBankInfo", method = RequestMethod.GET)
    @ResponseBody
    public Object getOldBMFCBankInfo(Model model,HttpServletRequest request) {
    	ResultData<?> resultData = new ResultData<>();
        Map<String, Object> reqMap = bindParamToMap(request);
        Object result = null;
        try {
        	resultData = borrowMoneyFrameContractService.getOldBMFCBankInfo(reqMap);
			if(resultData != null &&  resultData.getReturnCode().equals(ReturnCode.SUCCESS)){
				result = resultData.getReturnData();
			}
		} catch (Exception e) {
			if(result == null){
    			resultData.setFail();
    		}
			logger.error("borrowMoneyFrameContract", "BorrowMoneyFrameContractController", "getOldBMFCBankInfo", "", UserInfoHolder.getUserId(), "", "", e);
		}
        return result;
    }
    
    /**
     * desc:选择开户行
     * 2020年4月28日
     */
    @RequestMapping(value = "/selectBankInfoPage", method = RequestMethod.GET)
    public ModelAndView selectBankInfoPage(HttpServletRequest request, ModelMap mop){
        Map<String, Object> reqMap = bindParamToMap(request);
        mop.addAllAttributes(reqMap);
        return new ModelAndView("borrowMoneyFrameContract/selectBankInfoPage");
    }
    
    /**
     * 
     * desc:开户行列表
     * 2020年4月28日
     */
    @ResponseBody
    @RequestMapping(value = "getBankInfoList", method = RequestMethod.POST)
    public ResultData getBankInfoList(HttpServletRequest request, ModelMap mop,HttpServletResponse response)
    {
        Map<String, Object> reqMap = bindParamToMap(request);
        PageInfo pageInfo = getPageInfo(request);
        //返回list
        ResultData<?> resultData = new ResultData<>();
        try {
            resultData = borrowMoneyFrameContractService.getBankInfoList(reqMap,pageInfo);
        }catch (Exception e){
            logger.error("borrowMoneyFrameContract", "BorrowMoneyFrameContractController", "getBankInfoList", reqMap.toString(), null, "", "查询借佣框架协议开户行信息列表失败", e);
        }
        //存放到mop中
        return resultData;
    }
    
    
    /**
     * desc:新增借佣框架协议
     * 2020年4月28日
     */
    @RequestMapping(value = "/addBorrowMoneyFrameContract", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> addBorrowMoneyFrameContract(HttpServletRequest request, ModelMap modelMap,
    		 @RequestBody  BorrowMoneyFrameContractDto borrowMoneyFrameContractDto){
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        logger.info("借佣框架协议，新增入参:"+reqMap+",start...");
        Integer userIdCreate = UserInfoHolder.getUserId();
        String cityNo = UserInfoHolder.get().getCityNo();
        borrowMoneyFrameContractDto.setUserIdCreate(userIdCreate);
        borrowMoneyFrameContractDto.setCityNo(cityNo);
        //生成借佣框架协议编号
        borrowMoneyFrameContractDto.setContractNo(SeqNoHelper.getInstance().getSeqNoByTypeCode("TYPE_JYKJ"));
        try
        {
            ResultData<?> resultData = borrowMoneyFrameContractService.create(borrowMoneyFrameContractDto);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        }
        catch (Exception e)
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, " 新增借佣框架协议失败！");
            logger.error("borrowMoneyFrameContract", "BorrowMoneyFrameContractController", "addBorrowMoneyFrameContract", "", null, "", "新增借佣框架协议失败！", e);
        }

        return getOperateJSONView(rspMap);
    }
    
    /**
     * desc:框架协议详情页面
     * 2020年4月29日
     */
    @RequestMapping(value = "borrowMoneyFrameContractDetailPage", method = RequestMethod.GET)
    public ModelAndView borrowMoneyFrameContractDetailPage(Model model, HttpServletRequest request) {
        String id=request.getParameter("id");
        Map<String, Object> reqMap = bindParamToMap(request);
        ResultData<?> resultData = new ResultData<>();
        String oaOperator = "0";
        //查询登陆人的核算主体数量
//        ResultData usercodeResult = null;
        try {
//            usercodeResult = borrowMoneyFrameContractService.getUserMappingAccountProject(UserInfoHolder.get().getUserCode());
            String frameContractAccountProject=SystemParam.getWebConfigValue("JY_FrameContract_AccountProject");//借用框架协议默认核算主体
            
            if(!StringUtils.isEmpty(frameContractAccountProject)){
                oaOperator = "1";
                String accountProjectCode = frameContractAccountProject.split("\\|")[0];//核算主体编码
                String accountProject = frameContractAccountProject.split("\\|")[1];//核算主体
                model.addAttribute("oaOperator",oaOperator);
                model.addAttribute("accountProject", accountProject);
                model.addAttribute("accountProjectCode", accountProjectCode);
            }
            if(id!=null && !"".equals(id)){//查询商户信息，进入修改页面
                resultData = borrowMoneyFrameContractService.getbriefById(Integer.parseInt(id));
                model.addAttribute("id",id);
                model.addAttribute("frameContractDto", JSONObject.toJSON(resultData.getReturnData()));
            }
        }catch (Exception e){
            logger.error("borrowMoneyFrameContract", "BorrowMoneyFrameContractController", "borrowMoneyFrameContractDetailPage", reqMap.toString(), null, "", "查询框架协议信息失败", e);
        }
        ModelAndView mv = new ModelAndView("borrowMoneyFrameContract/borrowMoneyFrameContractDetailPage");
        return mv;
    }
    
    /**
     * desc:更新
     * 2020年4月29日
     */
    @ResponseBody
    @RequestMapping(value = "/updateBorrowMoneyFrameContract", method = RequestMethod.POST)
    public ReturnView<?, ?> updateFrameContract(HttpServletRequest request, ModelMap modelMap,
   		 @RequestBody  BorrowMoneyFrameContractDto borrowMoneyFrameContractDto) {
    	Map<String, Object> rspMap = new HashMap<String, Object>();
        try{
        	borrowMoneyFrameContractDto.setUserIdUpt(UserInfoHolder.getUserId());
            //更新
        	borrowMoneyFrameContractService.update(borrowMoneyFrameContractDto);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
            rspMap.put(Constant.RETURN_MSG_KEY, "修改成功！");
        }
        catch (Exception e){
            logger.error("borrowMoneyFrameContract", "BorrowMoneyFrameContractController", "update", "", UserInfoHolder.getUserId(), "", "更新借佣框架协议失败", e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "更新借佣框架协议失败");
        }
        return getOperateJSONView(rspMap);
    }
    
    /**
     * desc:作废
     * 2020年5月6日
     */
    @ResponseBody
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public ReturnView<?, ?> cancel(HttpServletRequest request) {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        BorrowMoneyFrameContractDto borrowMoneyFrameContractDto = new BorrowMoneyFrameContractDto();
        borrowMoneyFrameContractDto.setApproveState(10405);//作废
        borrowMoneyFrameContractDto.setUserIdUpt(UserInfoHolder.getUserId());
        borrowMoneyFrameContractDto.setId(Integer.parseInt((String)reqMap.get("id")));
        borrowMoneyFrameContractDto.setCompanyNo((String)reqMap.get("companyNo"));
        borrowMoneyFrameContractDto.setIsCancel(1);//废除标志
        try
        {
            //更新
        	borrowMoneyFrameContractService.update(borrowMoneyFrameContractDto);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
            rspMap.put(Constant.RETURN_MSG_KEY, "作废成功！");
        }
        catch (Exception e)
        {
            logger.error("borrowMoneyFrameContract", "BorrowMoneyFrameContractController", "cancel", "", UserInfoHolder.getUserId(), "", "借佣框架协议作废失败", e);

            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);

            rspMap.put(Constant.RETURN_MSG_KEY, "借佣框架协议作废失败");
        }
        return getOperateJSONView(rspMap);
    }
    
    /**
     * 
     * desc:提交oa审核
     * 2020年5月7日
     */
    @ResponseBody
    @RequestMapping(value = "/submitToOA/{contractId}")
    public ResultData<String> submitToOA(@PathVariable Integer contractId){
        ResultData<String> resultData = new ResultData<>();
        Map<String,String> param = new HashMap<>();
        param.put("contractId",contractId+"");
        param.put("userId",UserInfoHolder.getUserId()+"");
        param.put("userCode",UserInfoHolder.get().getUserCode());
        param.put("cityNo",UserInfoHolder.get().getCityNo());
        param.put("userName",UserInfoHolder.get().getUserName());
        String oaNo = SeqNoHelper.getInstance().getSeqNoByTypeCode("TYPE_JYKJ_OA");//借用框架协议OA编码
        param.put("oANo",oaNo);
        try{
            resultData = borrowMoneyFrameContractService.submitToOA(param);
        }catch (Exception e){
            resultData.setFail("系统异常，请联系管理员");
            logger.error("borrowMoneyFrameContract", "BorrowMoneyFrameContractController", "submitToOA", param.toString(), UserInfoHolder.getUserId(), "", "借用框架协议提交OA失败", e);
        }
        resultData.setReturnData(contractId+"");

        return resultData;
    }
    
    /**
     * 暂没用到
     * desc:移除
     * 2020年5月8日
     */
    @ResponseBody
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public  ResultData remove(HttpServletRequest request) {
    	Map<String, Object> reqMap = bindParamToMap(request);
    	ResultData<?> resultData = new ResultData<>();
        try{
            //更新
        	borrowMoneyFrameContractService.remove(reqMap);
        }
        catch (Exception e){
        	e.printStackTrace();
            logger.error("borrowMoneyFrameContract", "BorrowMoneyFrameContractController", "remove", "", UserInfoHolder.getUserId(), "", "更新借佣框架协议失败", e);
        }
        return resultData;
    }
}
