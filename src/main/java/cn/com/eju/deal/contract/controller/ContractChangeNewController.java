package cn.com.eju.deal.contract.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.helper.SeqNoHelper;
import cn.com.eju.deal.base.linkage.service.LinkageCityService;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.service.CityService;
import cn.com.eju.deal.common.util.DateEditor;
import cn.com.eju.deal.contract.service.ContractChangeNewService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.dto.contract.ContractChangeDto;
import cn.com.eju.deal.dto.oa.OaOperatorDto;
import cn.com.eju.deal.oa.service.OaOperatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 合同变更
 */
@Controller
@RequestMapping(value = "contractChangeNew")
public class ContractChangeNewController extends BaseController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private ContractChangeNewService contractChangeNewService;

    @Resource(name = "cityService")
    private CityService cityService;

    @Resource(name = "oaOperatorService")
    private OaOperatorService oaOperatorService;

    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        //对于需要转换为Date类型的属性，使用DateEditor进行处理
        binder.registerCustomEditor(Date.class, new DateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }


    /**
     * 变更列表
     * @param contractId
     * @param contractStatus
     * @return
     */
    @RequestMapping(value = "list/{contractId}/{contractStatus}",method = RequestMethod.GET)
    public ModelAndView list(@PathVariable("contractId") Integer contractId,@PathVariable("contractStatus") Integer contractStatus){
        ModelAndView modelAndView = new ModelAndView("contractchangenew/contractChangeNewList");

        try{
            ResultData<?> list = contractChangeNewService.queryContractChangeNewList(contractId);
            if(list != null&&list.getReturnCode().equals("200")){
                modelAndView.addObject("contentList",list.getReturnData());
            }
        }catch (Exception e){
            logger.error("contractChangeNew", "ContractChangeNewController", "list", "contractId="+contractId,
                    UserInfoHolder.getUserId(), "", "合同变更查询失败！", e);
        }

        //查询是否是经办人
        ResultData<?> backResult;
        OaOperatorDto oaOperatorDto = new OaOperatorDto();
        try{
            backResult = oaOperatorService.getByUserCode(UserInfoHolder.get().getUserCode());
            Map<?, ?> mapTemp = (Map<?, ?>)backResult.getReturnData();
            if (null != mapTemp){
                oaOperatorDto = MapToEntityConvertUtil.convert(mapTemp, OaOperatorDto.class, "");
            }
        }catch (Exception e){
            logger.error("contractChangeNew",
                    "ContractChangeNewController",
                    "list",
                    "contractId="+contractId,
                    UserInfoHolder.getUserId(),
                    "",
                    "合同变更列表,查询是否是经办人失败",
                    e);
        }

        modelAndView.addObject("oaOperator",oaOperatorDto);
        modelAndView.addObject("contractId",contractId);
        modelAndView.addObject("contractStatus",contractStatus);
        return modelAndView;
    }

    /**
     * 跳转新增三方变更
     * @param storeId
     * @param abTypeStore
     * @param contractId
     * @param contractStatus
     * @return
     */
    @RequestMapping(value = "toAddChange/{storeId}/{abTypeStore}/{contractId}/{contractStatus}",method = RequestMethod.GET)
    public ModelAndView toAddChange(@PathVariable("storeId") Integer storeId,@PathVariable("abTypeStore") Integer abTypeStore,
        @PathVariable("contractId") Integer contractId,@PathVariable("contractStatus") Integer contractStatus){

        ModelAndView modelAndView = new ModelAndView("contractchangenew/contractChangeNewAdd");

        try {
            //查询isService=1的城市
            ResultData<?> reback = cityService.queryCityListByIsService();
            List<?> citylist = (List<?>) reback.getReturnData();
            modelAndView.addObject("citylist", citylist);
        } catch (Exception e) {
            logger.error("contractChangeNew", "ContractChangeNewController", "toAddChange", "contractId="+contractId+",storeId="+storeId,
                    UserInfoHolder.getUserId(), "", "查询城市列表失败！", e);
        }

        try{
            ResultData<?> info = contractChangeNewService.getContractAndStoreInfo(storeId,contractId);
            if(info != null&&info.getReturnCode().equals("200")){
                modelAndView.addObject("contractInfo",info.getReturnData());
            }
        }catch (Exception e){
            logger.error("contractChangeNew", "ContractChangeNewController", "toAddChange", "contractId="+contractId+",storeId="+storeId,
                    UserInfoHolder.getUserId(), "", "合同及门店信息查询失败！", e);
        }

        return modelAndView;
    }

    /**
     * 保存三方变更
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveContractChange",method = RequestMethod.POST)
    public ResultData<?> saveContractChange(HttpServletRequest request, ContractChangeDto dto){
        ResultData<?> resultData = new ResultData<>();
        dto.setUserIdCreate(UserInfoHolder.getUserId());

        if(dto.getId() == null){
            String contractStopNo = SeqNoHelper.getInstance()
                    .getSeqNoByTypeCode("TYPE_CONTRACTCHANGE");
            dto.setContractStopNo(contractStopNo);
        }

        try{
           resultData = contractChangeNewService.saveContractChange(dto);
        }catch (Exception e){
            resultData.setFail("保存三方变更失败");
            logger.error("contractChangeNew", "ContractChangeNewController", "saveContractChange", JsonUtil.parseToJson(dto),
                    UserInfoHolder.getUserId(), "", "保存三方变更失败！", e);
        }

        return resultData;
    }

    /**
     * 跳转三方变更查看页面
     * @param id
     * @param contractId
     * @param contractStatus
     * @return
     */
    @RequestMapping(value = "toView/{id}/{contractId}/{contractStatus}", method = RequestMethod.GET)
    public ModelAndView toView(@PathVariable("id") Integer id,
                         @PathVariable("contractId") int contractId,
                         @PathVariable("contractStatus") Integer contractStatus) {
        ModelAndView modelAndView = new ModelAndView("contractchangenew/contractChangeNewView");

        ResultData<?> resultData = new ResultData<>();
        try {
            resultData = contractChangeNewService.findContractChangeNewById(id);
            if (resultData != null && resultData.getReturnCode().equals("200")) {
                modelAndView.addObject("contractChange", resultData.getReturnData());
            }
        }catch (Exception e){
            resultData.setFail("查询三方变更失败");
            logger.error("contractChangeNew", "ContractChangeNewController", "toView", "contractId="+contractId+",id="+id,
                    UserInfoHolder.getUserId(), "", "查询三方变更失败", e);
        }

        return modelAndView;
    }

    /**
     * 跳转三方变更修改页面
     * @param id
     * @param contractId
     * @param contractStatus
     * @return
     */
    @RequestMapping(value = "toEdit/{id}/{contractId}/{contractStatus}", method = RequestMethod.GET)
    public ModelAndView toEdit(@PathVariable("id") int id,
                         @PathVariable("contractId") Integer contractId,
                         @PathVariable("contractStatus") Integer contractStatus) {
        ModelAndView modelAndView = new ModelAndView("contractchangenew/contractChangeNewEdit");

        try{
            ResultData<?> cityList = cityService.queryCityListByIsService();
            if(cityList != null && "200".equals(cityList.getReturnCode())){
                modelAndView.addObject("citylist",cityList.getReturnData());
            }
        }catch (Exception e){
            logger.error("contractChangeNew", "ContractChangeNewController", "toEdit", "contractId="+contractId+",id="+id,
                    UserInfoHolder.getUserId(), "", "查询城市列表失败", e);
        }

        ResultData<?> resultData = new ResultData<>();
        try {
            resultData = contractChangeNewService.findContractChangeNewById(id);
            if (resultData != null && resultData.getReturnCode().equals("200")) {
                modelAndView.addObject("contractChange", resultData.getReturnData());
            }
        }catch (Exception e){
            resultData.setFail("查询三方变更失败");
            logger.error("contractChangeNew", "ContractChangeNewController", "toEdit", "contractId="+contractId+",id="+id,
                    UserInfoHolder.getUserId(), "", "查询三方变更失败", e);
        }

        return modelAndView;
    }
}
