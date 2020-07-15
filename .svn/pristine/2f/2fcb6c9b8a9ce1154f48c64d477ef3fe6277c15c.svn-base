package cn.com.eju.deal.contract.service;

import java.util.Map;

import cn.com.eju.deal.dto.contract.ContractDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.contract.ContractInfoDto;

/**   
* Service层
* @author (qianwei)
* @date 2016年3月23日 下午9:30:27
*/
@Service("contractService")
public class ContractService extends BaseService<ContractInfoDto>
{
    
    //private final static String REST_CONTRACT = SystemCfg.getString("contractRestServer");
    
    /** 
    * 查询
    * @param id
    * @return
    * @throws Exception
    */
    public ResultData<?> getById(int id)
        throws Exception
    {
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/{id}";
        
        ResultData<?> backResult = get(url, id);
        
        return backResult;
    }

    
    //Add By NingChao 2017/04/07 Start
      /** 
       * 查询续签画面
       * @param id 合同Id
       * @param storeId 门店Id
       * @return
       * @throws Exception
       */
       public ResultData<?> getById(int id,int storeId)
           throws Exception
       {
           
           //调用 接口
           String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/{id}/{storeId}";
           
           ResultData<?> backResult = get(url, id, storeId);
           
           return backResult;
       }
    //Add By NingChao 2017/04/07 Start
       
    /** 
     * 获取产品信息
     * @param id
     * @return
     * @throws Exception
     */
    public ResultData<?> getProductInfo()
        throws Exception
    {
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/p";
        
        ResultData<?> backResult = get(url);
        
        return backResult;
    }
    
    /** 
     * 查询-list
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData<?> index(Map<String, Object> reqMap)
        throws Exception
    {
        
        return index(reqMap, null);
        
    }
    
    /** 
    * 查询-list
    * @param reqMap
    * @return
    * @throws Exception
    */
    public ResultData<?> index(Map<String, Object> reqMap, PageInfo pageInfo)
        throws Exception
    {
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/q/{param}";
        
        ResultData<?> reback = get(url, reqMap, pageInfo);
        
        return reback;
        
    }
    
    /** 
    * 创建
    * @param contractInfoDto
    * @return
    * @throws Exception
    */
    public ResultData<?> create(ContractInfoDto contractInfoDto)
        throws Exception
    {
        
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "";
        
        ResultData<?> backResult = post(url, contractInfoDto);
        
        return backResult;
    }
    
    /** 
    * 更新
    * @param contractInfoDto
    * @return
    * @throws Exception
    */
    public ResultData<?> update(ContractInfoDto contractInfoDto)
        throws Exception
    {
        
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/update";
        
        ResultData<?> backResult = post(url, contractInfoDto);
        
        return backResult;
        
    }
    
    /** 
    * 更新
    * @param contractInfoDto
    * @return
    * @throws Exception
    */
    public ResultData<?> updateByContractId(ContractInfoDto contractInfoDto)
        throws Exception
    {
        
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/updateById";
        
        ResultData<?> backResult = post(url, contractInfoDto);
        
        return backResult;
        
    }
    
    /** 
    * 合同作废后，释放门店
    * @param id
    * @param updateId
    * @return
    * @throws Exception
    */
    public void invalid(int id, int updateId)
        throws Exception
    {
        
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/invalid/{id}/{updateId}";
        
        delete(url, id, updateId);
        
    }
    
    /** 
     * 审核
     * @param contractInfoDto
     * @return
     * @throws Exception
     */
    public void audit(ContractInfoDto contractInfoDto)
        throws Exception
    {
        
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/audit";
        
        put(url, contractInfoDto);
        
    }
    
    /** 
     * 验证处理
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData<?> checkStoreLock(Map<String, Object> reqMap)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/check/{param}";
        ResultData<?> backResult = get(url, reqMap);
        return backResult;
    }
    
    //Add By cning 2017/07/13 Start
   /* ** 
     * 验证处理--该公司的所有合同是否有审核中或者审核通过的,如果有，不允许修改公司信息
     * @param reqMap
     * @return
     * @throws Exception
     **/
    public ResultData<?> checkCompanyContract(Integer companyId)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/checkCompany/{companyId}";
        ResultData<?> backResult = get(url, companyId);
        return backResult;
    }
    //Add By cning 2017/07/13 End
    
    /** 
     * 创建房友账号
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData<?> createFangyou(Map<String, Object> reqMap)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/createFangyou/{param}";
        ResultData<?> backResult = get(url, reqMap);
        return backResult;
    }
    
    /** 
     * 更新FlowId OA专用
     * @param contractInfoDto
     * @return
     * @throws Exception
     */
    public void updateFlowIdById(ContractInfoDto contractInfoDto)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/flowId";
        put(url, contractInfoDto);
    }
    
    /** 
     * 更新ContractStatus OA专用
     * @param contractInfoDto
     * @return
     * @throws Exception
     */
    public void updateContractStatusByFlowId(ContractInfoDto contractInfoDto)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/contractStatus";
        put(url, contractInfoDto);
    }
    
    /** 
     * 定时批量更新状态
     * @param 
     * @return
     * @throws Exception
     */
    public ResultData<?> updateStatusByParam(Map<String, Object> reqMap)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/job/state/{param}";
        
        ResultData<?> backResult = get(url, reqMap);
        return backResult;
    }
    
    /**
     * 查询公司下是否有合同
     * @param companyId
     * @return
     * @throws Exception
     */
    public ResultData<?> getContractsByCompanyId(Integer companyId)
        throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/company/{companyId}";
        
        ResultData<?> reback = get(url, companyId);
        
        return reback;
    }
    
    /**
     * 根据门店Id查询签约的合同
     * @param storeId
     * @return
     * @throws Exception
     */
    public ResultData<?> getSignHisByStoreId(Map<String, Object> reqMap, PageInfo pageInfo)
        throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/store/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }
    
    /** 
     * 根据公司Id查询审核通过的合同
     * @param  companyId 公司Id
     * @return
     */
    public ResultData<?> getAuditpassByCompanyId(@PathVariable Integer companyId) throws Exception{
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/getAuditpassContract/{companyId}";
        ResultData<?> reback = get(url, companyId);
        return reback;
    }
    
    
    /** 
     * 定时批量更新状态
     * @param 
     * @return
     * @throws Exception
     */
    public void updateChgStatusByParam(Map<String, Object> reqMap)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/job/chgState/{param}";
        get(url, reqMap);
    }
    
    /** 
	* 根据合同变更ID查询合同信息
	* @param contractId 合同ID
	* @return
	* @throws Exception
	*/
	public ResultData<?> getContractById(Integer contractId) throws Exception {
		// 调用 接口
		 String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/queryContract/{contractId}";
		ResultData<?> reback = get(url, contractId);
		return reback;
	}
	


	/**
	 * 根据合同Id获取保证金分账页面信息
	 * @param contractId
	 * @return 保证金分账信息
	 * @throws Exception
	 */
	public ResultData<?> getSplitInfo(Integer contractId) throws Exception {
		// 调用 接口
		String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/getSplitInfo/{contractId}";
		ResultData<?> backResult = get(url, contractId);
		return backResult;
	}
	
	/**
	 * 更新合同门店关系表 的是否到账和到账日期字段
	 * @param contractId
	 * @return 保证金分账信息
	 * @throws Exception
	 */
	public ResultData<?> updateCtctStore(Integer contractId,String storeIds) throws Exception {
		// 调用 接口
		String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/updateCtctStore/{contractId}/{storeIds}";
		ResultData<?> backResult = get(url, contractId,storeIds);
		return backResult;
	}
	
	/**
	 * @param flowId
	 * @return 根据flowId获取总保证金不为0的合同的信息
	 * @throws Exception
	 */
	public ResultData<?> getDepoistNozeroCtrctByFlowId(String flowId) throws Exception {
		// 调用 接口
		String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/get/{flowId}";
		ResultData<?> backResult = get(url, flowId);
		return backResult;
	}

	/**
	 * 
	    * @Title: insertNewDecorationRecord
	    * @Description: 手动、批量获取审核状态新增门店装修共用
	    * @return
	    * @throws Exception
	 */
    public ResultData<?> insertNewDecorationRecord(Map<String, Object> mop) throws Exception
    {
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/insertDecoration/{param}";
        ResultData<?> backResult = get(url, mop);
        return backResult;
    }

    /**
     * 
    * 获取合同门店关联表的业绩撤销状态
    * @param id
    * @return
     * @throws Exception 
     */
    public ResultData<?> getContractStoreIsCancel(Integer id) throws Exception
    {
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/contractStoreIsCancel/{contractId}";
        ResultData<?> backResult = get(url, id);
        return backResult;
    }

    /**
     * 
    * 获取合同剩余未分账保证金-用于校验
    * @param contractId
    * @return
     * @throws Exception 
     */
    public ResultData<?> checkRestDeposit(Integer contractId) throws Exception
    {
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/checkRestDeposit/{contractId}";
        ResultData<?> backResult = get(url, contractId);
        return backResult;
    }

    //Add by WangLei 2017/04/07 Start
    /**
     * 获取原合同信息
     * @param contractId
     * @return
     * @throws Exception
     */
    public ResultData<?> getOrgContractInfo(Integer storeId,String originalContractNo) throws Exception
    {
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/depositFee/{storeId}/{originalContractNo}";
        ResultData<?> backResult = get(url, storeId,originalContractNo);
        return backResult;
    }
    
    /**
     * 根据flowId取得合同信息
     * @param contractId
     * @return
     * @throws Exception
     */
    public ResultData<?> getContractInfoByFlowId(String flowId) throws Exception
    {
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/flowId/{flowId}";
        ResultData<?> backResult = get(url, flowId);
        return backResult;
    }
    //Add by WangLei 2017/04/07 End
    
//Add By GUOPENGFEI 2017/04/25 Start    
	/**
	 * 获取审核批注原因
	 * 
	 * @param contractInfoDto
	 * @return
	 * @throws Exception
	 */
	public ResultData<?> getOpinionsCrm(String flowId) throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/Opinions/{flowId}";
        
        ResultData<?> backResult = get(url, flowId);
        
        return backResult;
	}
//Add By GUOPENGFEI 2017/04/25 End
	
    //Add By GuoPengFei 2017/05/25 合规性 start	
	/**
	 * 根据门店ID查找最新合同
	 * 
	 * @param contractInfoDto
	 * @return
	 * @throws Exception
	 */
	public ResultData<?> getContractByStoreId(Integer storeId) throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/storeNewestContract/{storeId}";
        
        ResultData<?> backResult = get(url, storeId);
        
        return backResult;
	}
	
	/**
	 * 根据合同No查找该合同门店的乙转甲变更单号
	 * @param contractNo 合同编号
	 * @return
	 * @throws Exception
	 */
	public ResultData<?> getcontractB2AChangeNo(String contractNo) throws Exception {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/B2AChangeNo/{contractNo}";
        
        ResultData<?> backResult = get(url, contractNo);
        
        return backResult;
	}
	
    /** 
    * 根据flowId 更新 合同里的 公司地址
    * @param contractInfoDto
    * @return
    * @throws Exception
    */
    public ResultData<?> updateContractCompanyAdressByFlowId(ContractInfoDto contractInfoDto)
        throws Exception
    {
        
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/contractcompanyadress";
        
        ResultData<?> backResult = post(url, contractInfoDto);
        
        return backResult;
    }
	
    
    /** 
     * 根据公司ID查询合同状态为未签，审核未通过的合同
     * @param companyId 合同Id
     * @return
     * @throws Exception
     */
    public ResultData<?> getContractByCompanyId(Integer companyId)
            throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/getByCompanyId/{companyId}";
        ResultData<?> backResult = get(url, companyId);
        return backResult;
    }	
	
    /** 
     * 根据登录用户获取所属中心
     * @param userId
     * @return
     * @throws Exception
     */
    public ResultData<?> getCenterListByUserId(Integer userId)
        throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "users" + "/center/{userId}";
        ResultData<?> backResult = get(url, userId);
        return backResult;
    }
    
    /**
     * 【描述】: 根据合同编号查询信息
     *
     * @author yinkun
     * @date: 2017年10月16日 下午5:43:30
     * @param contractNo
     * @return
     * @throws Exception
     */
    public ResultData<?> getByNo(String contractNo) throws Exception {

        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/getByNo/{contractNo}";

        ResultData<?> backResult = get(url, contractNo);

        return backResult;
    }


    public ResultData<?> uploadAdditional(String contractId, String fileRecordMainIds) throws Exception{
     
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/uploadAdditional/{contractId}/{fileRecordMainIds}";

        if("".equals(fileRecordMainIds)) {
            fileRecordMainIds  = "-1";
        }
        ResultData<?> backResult = get(url, contractId,fileRecordMainIds);

        return backResult;
    }

    public ResultData<?> selectNewestContract(Integer storeId) throws Exception{
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/selectNewestContract/{storeId}";

        ResultData<?> backResult = get(url, storeId);

        return backResult;
    }

    public ResultData<?> selectNewestContractByCompanyId(int companyId) throws Exception{
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/selectNewestContractByCompanyId/{companyId}";

        ResultData<?> backResult = get(url, companyId);

        return backResult;
    }
    /** 
     * @Title: operateChangeCt 
     * @Description: 运营维护合同状态
     * @throws Exception     
     */
     public void operateChangeCt(ContractInfoDto contractInfoDto) throws Exception{
         String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/operateChangeCt";
         put(url, contractInfoDto);
     }

    /**
     * @param storeId 门店Id
     * @return
     * @throws Exception
     */
    public ResultData<?> selectDateLifeEnd(int storeId)
            throws Exception
    {

        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "contract" + "/selectDateLifeEnd/{storeId}";

        ResultData<?> backResult = get(url, storeId);

        return backResult;
    }
}
