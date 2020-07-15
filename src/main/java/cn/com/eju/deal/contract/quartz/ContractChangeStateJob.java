package cn.com.eju.deal.contract.quartz;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.contract.service.ContractChangeService;
import cn.com.eju.deal.contract.service.ExtOmsService;
import cn.com.eju.deal.contract.service.OaService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.dto.contract.ContractChangeDto;

/**   
* 每天凌晨拉取合同变更状态
* @author wenhui.zhang
* @date 2016年8月10日 上午午10:02:59
*/
public class ContractChangeStateJob
{
    @Resource(name = "stopContractService")
    private ContractChangeService stopContractService;
    
    @Resource(name = "oaService")
    private OaService oaService;
    
    @Resource(name = "extOmsService")
    private ExtOmsService extOmsService;
    
    //标示线程执行标示
    private static Boolean isExcute = false;
    
    private static long oldTime;
    
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    /**
     * 拉取合同变更主方法
     */
    public void handleChangeJob()
    {
        //180秒后解锁
        long dif = (System.currentTimeMillis() - oldTime) / 1000;
        if (dif > 180)
        {
            isExcute = false;
        }
        
        if (isExcute)
        {
            return;
        }
        else
        {
            //拉取合同
            handle();
        }
        
    }
    
    private void handle()
    {
        isExcute = true;
        
        oldTime = System.currentTimeMillis(); //这是获取毫秒数
        
        ResultData<?> resultData = null;
        
        logger.info("CRM Web 定时任务begin，this time is" + new Date());
        // 1、获取"合同变更审核中" 状态的list
        List<String> flowIdList = getChgFlowIdStateList();
        // 2、OA变更审核结果集
        Map<String, Object> reqMap = getChgOaAuditMap(flowIdList);
        // 3、更新合同状态
        try
        {
            resultData = stopContractService.updateChgStatusByParam(reqMap);
        }
        catch (Exception e)
        {
            logger.error("ContractChangeStateJob", "ContractChangeStateJob", "handleChangeJob", "", -1, "", "更新合同变更状态失败！", e);
        }
        
        if (ReturnCode.SUCCESS.equals(resultData.getReturnCode()))
        {
            //4、变更终止通过-更新OMS保证金中合同状态
            try
            {
                extOmsService.updateChgStatusToOmsSplit(reqMap);
            }
            catch (Exception e)
            {
                logger.error("ContractChangeStateJob", "ContractChangeStateJob", "handleChangeJob", "", -1, "", "变更终止通过-更新OMS保证金中合同状态失败！", e);
            }
        }
    }
    
    /**
     * 获取合同变更审核中的flowId集合
     */
    private List<String> getChgFlowIdStateList()
    {
        // 返回组装List
        List<String> flowIdList = new ArrayList<String>();
        Map<String, Object> reqMap = new HashMap<String, Object>();
        // 获取页面显示数据
        ResultData<?> reback = new ResultData<>();
        try
        {
            // 1:审核中
            reqMap.put("approveState", 1);
            reback = stopContractService.queryByApproveState(reqMap);
        }
        catch (Exception e)
        {
            logger.error("ContractChangeStateJob",
                "ContractChangeStateJob",
                "getChgFlowIdStateList",
                "",
                UserInfoHolder.getUserId(),
                "",
                "查询合同变更审核中状态失败!",
                e);
        }
        // 查询到合同数据
        List<?> contentlist = (List<?>)reback.getReturnData();
        if (null != contentlist && !contentlist.isEmpty())
        {
            String flowId = null;
            ContractChangeDto contractChangeDto;
            for (Object rebac : contentlist)
            {
                contractChangeDto = MapToEntityConvertUtil.convert((Map<?, ?>)rebac, ContractChangeDto.class, "");
                flowId = contractChangeDto.getFlowId();
                flowIdList.add(flowId);
            }
        }
        return flowIdList;
    }
    
    /**
     * 组装 变更审核状态
     * 
     * @param flowIdList
     */
    private Map<String, Object> getChgOaAuditMap(List<String> flowIdList)
    {
        Map<String, Object> flowIdStateMap = new HashMap<String, Object>();
        // 审核通过List
        List<String> passList = new ArrayList<String>();
        // 作废List
        List<String> noPassList = new ArrayList<String>();
        if (null != flowIdList && !flowIdList.isEmpty())
        {
            try
            {
                for (String flowId : flowIdList)
                {
                    Integer reback = -1;
                    reback = oaService.getOaState(flowId);
                    // 审核通过 （ 0正常结束）
                    if (0 == reback)
                    {
                        passList.add(flowId);
                    }
                    // 作废 （ 非正常结束：5 撤销、15终止）
                    else if (5 == reback || 15 == reback)
                    {
                        noPassList.add(flowId);
                    }
                }
            }
            catch (Exception e)
            {
                logger.error("ContractChangeStateJob",
                    "ContractChangeStateJob",
                    "getChgOaAuditMap",
                    "",
                    UserInfoHolder.getUserId(),
                    "",
                    "查询合同变更OA审核状态失败!",
                    e);
            }
            flowIdStateMap.put("pass", passList);
            flowIdStateMap.put("noPass", noPassList);
        }
        return flowIdStateMap;
    }
}
