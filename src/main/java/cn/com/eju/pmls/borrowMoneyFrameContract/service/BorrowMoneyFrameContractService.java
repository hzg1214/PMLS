package cn.com.eju.pmls.borrowMoneyFrameContract.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.borrowMoneyFrameContract.dto.BorrowMoneyFrameContractDto;

/**
*  借佣框架协议service
*/
@Service("borrowMoneyFrameContractService")
public class BorrowMoneyFrameContractService extends BaseService{
    /**
     * 查询列表
     */
    public ResultData getBorrowMoneyFrameContractList(Map<String, Object> reqMap, PageInfo pageInfo)throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "borrowMoneyFrameContract" + "/getList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;

    }
    
    /**
     * 获取框架合同基本信息
     * @param id
     * @return
     * @throws Exception
     */
    public ResultData<?> getbriefById(int id) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "borrowMoneyFrameContract" + "/brief/{id}";
        ResultData<?> backResult = get(url, id);
        return backResult;
    }
    
    /**
     * 根据公司编号获取最新审核通过的框架合同的银行相关信息
     */
    public ResultData<?> getOldBMFCBankInfo(Map<String, Object> reqMap) throws Exception{
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "/borrowMoneyFrameContract" + "/getOldBMFCBankInfo/{param}";
        ResultData<?> backResult = get(url, reqMap);
        return backResult;
    }
    
    /**
     * 查询公司
     */
    public ResultData<?> getBorrowMoneyFrameContractCompanyList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
     	//调用 接口
     	String url = SystemParam.getWebConfigValue("RestServer") + "/borrowMoneyFrameContract" + "/getBorrowMoneyFrameContractCompanyList/{param}";
     	ResultData<?> reback = get(url, reqMap, pageInfo);
     	return reback;
     }
    
    
    /**
     * 查询开户行信息列表
     */
    public ResultData getBankInfoList(Map<String, Object> reqMap, PageInfo pageInfo)throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "/borrowMoneyFrameContract" + "/getBankInfoList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }

    /**
     * desc:新建借佣框架协议
     * 2020年4月28日
     */
    public ResultData<?> create(BorrowMoneyFrameContractDto borrowMoneyFrameContractDto)
	        throws Exception{
	    String url = SystemParam.getWebConfigValue("RestServer") + "borrowMoneyFrameContract/create";
	    ResultData<?> backResult = post(url, borrowMoneyFrameContractDto);
	    return backResult;
	}
    
    /**
     * 根据员工编号获取其核算主体信息
     * @return
     * @throws Exception
     */
    public ResultData getUserMappingAccountProject(String userCode) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "frameContract" + "/getUserMappingAccountProject/{userCode}";
        ResultData<?> backResult = get(url, userCode);
        return backResult;
    }
    
    /**
     * desc:更新
     * 2020年4月29日
     */
    public void update(BorrowMoneyFrameContractDto borrowMoneyFrameContractDto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "/borrowMoneyFrameContract" + "/update";
        put(url, borrowMoneyFrameContractDto);
    }
    
    /**
     * 
     * desc:提交oa
     * 2020年5月7日
     */
    public ResultData<String> submitToOA(Map<String,String> param) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "borrowMoneyFrameContract" + "/submitToOA/{param}";
        ResultData<String> backResult = get(url, param);
        return backResult;
    }
    
    /**
     * 
     * desc:移除
     * 2020年5月8日
     */
    public ResultData<?> remove(Map<String, Object> reqMap) throws Exception{
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "/borrowMoneyFrameContract" + "/remove/{param}";
        ResultData<?> backResult = get(url, reqMap);
        return backResult;
    }
    
}
