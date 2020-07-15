package cn.com.eju.pmls.frameContract.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
*  框架合同service
*/
@Service("pmlsFrameContractService")
public class PmlsFrameContractService extends BaseService{
    /**
     * 查询列表
     */
    public ResultData getFrameContractList(Map<String, Object> reqMap, PageInfo pageInfo)throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "frameContract" + "/getList/{param}";

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
        String url = SystemParam.getWebConfigValue("RestServer") + "frameContract" + "/brief/{id}";
        ResultData<?> backResult = get(url, id);
        return backResult;
    }
    /**
     * @Title: update
     * @Description: 根据框架合同id更新作废
     * @throws Exception
     */
     public void update(Map<String, Object> reqMap) throws Exception{
         String url = SystemParam.getWebConfigValue("RestServer") + "frameContract" + "";
         put(url, reqMap);
     }
     /**
      * 查询公司
      */
     public ResultData<?> getFrameContractCompanyList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
      	//调用 接口
      	String url = SystemParam.getWebConfigValue("RestServer") + "frameContract" + "/getFrameContractCompanyList/{param}";
      	ResultData<?> reback = get(url, reqMap, pageInfo);
      	return reback;
      }

     /**
      * 根据公司id获取详情信息
      * @param id
      * @return
      * @throws Exception
      */
     public ResultData<?> getCompanyInfoById(int id) throws Exception{
     	String url = SystemParam.getWebConfigValue("RestServer") + "frameContract" + "/getCompanyInfoById/{id}";
     	ResultData<?> backResult = get(url, id);
     	return backResult;
     }
     /**
      * 创建
      * @param reqMap
      * @return
      * @throws Exception
      */
     public ResultData<?> create(Map<String, Object> reqMap)
    	        throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "frameContract/create";
        ResultData<?> backResult = post(url, reqMap);
        return backResult;
    }
     /**
      * 根据companyNo获取详情信息
      * @param companyNo
      * @return
      * @throws Exception
      */
     public ResultData<?> getCompanyInfoByCompanyNo(String companyNo) throws Exception{
     	String url = SystemParam.getWebConfigValue("RestServer") + "frameContract" + "/getCompanyInfoByCompanyNo/{companyNo}";
     	ResultData<?> backResult = get(url, companyNo);
     	return backResult;
     }

    /**
     * 提交OA
     * @param param
     * @return
     * @throws Exception
     */
     public ResultData<String> submitToOA(Map<String,String> param) throws Exception{
         String url = SystemParam.getWebConfigValue("RestServer") + "frameContract" + "/submitToOA/{param}";
         ResultData<String> backResult = get(url, param);
         return backResult;
     }
     /**
      * 根据公司编号查询合同状态为未签，审核未通过的框架合同
      * @param companyNo 公司编号
      * @return
      * @throws Exception
      */
     public ResultData<?> getFrameContractByCompanyNo(String companyNO) throws Exception{
         String url = SystemParam.getWebConfigValue("RestServer") + "frameContract" + "/getFrameContractByCompanyNo/{companyNO}";
         ResultData<?> backResult = get(url, companyNO);
         return backResult;
     }
     /**
      * 跟新框架协议中公司信息
      * @param reqMap
      * @return
      * @throws Exception
      */
     public ResultData<?> updateCompanyInfo(Map<String, Object> reqMap)throws Exception{

        String url = SystemParam.getWebConfigValue("RestServer") + "frameContract" + "/updateCompanyInfo";

        ResultData<?> backResult = post(url, reqMap);

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
      * 根据员工编号获取其核算主体信息
      * @return
      * @throws Exception
      */
     public ResultData<?> getLoginCityAccountProject(String cityNo) throws Exception{
    	 String url = SystemParam.getWebConfigValue("RestServer") + "frameContract" + "/getLoginCityAccountProject/{cityNo}";
    	 ResultData<?> backResult = get(url, cityNo);
    	 return backResult;
     }

    public ResultData<?> selectNewestContractByCompanyId(int companyId) throws Exception{
        // 调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "frameContract" + "/selectNewestContractByCompanyId/{companyId}";

        ResultData<?> backResult = get(url, companyId);

        return backResult;
    }
    public void operateChangeCt(Map<String, Object> reqMap) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "frameContract" + "/operateChangeCt";
        //变更人
        reqMap.put("wjConfirmUser",UserInfoHolder.getUserId());
        put(url, reqMap);
    }
    /**
     * 根据公司编号获取最新审核通过的框架合同的银行相关信息
     */
    public ResultData<?> getOldFtBankInfo(Map<String, Object> reqMap) throws Exception{
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "/frameContract" + "/getOldFtBankInfo/{param}";
        ResultData<?> backResult = get(url, reqMap);
        return backResult;
    }

    /**
     * 查询渠道开户行信息列表
     */
    public ResultData getBankInfoList(Map<String, Object> reqMap, PageInfo pageInfo)throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "frameContract" + "/getBankInfoList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;

    }
    /**
     * 删除对公账户
     */
    public ResultData deleteBankInfo(Map<String, Object> reqMap, PageInfo pageInfo)throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "frameContract" + "/deleteBankInfo/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;

    }


}
