package cn.com.eju.deal.cashbill.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.cashbill.model.CashBillProject;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by yinkun on 2018/8/13.
 */
@Service("cashBillService")
public class CashBillService extends BaseService {
    public ResultData<?> selectCashBill(Map<String, Object> queryParam) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/selectCashBill";
        ResultData<?> backResult = post(url, queryParam);
        return backResult;
    }

    public ResultData<?> addToBatchCash(Map<String, Object> map) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/addToBatchCash";
        ResultData<?> backResult = post(url, map);
        return backResult;
    }

    public ResultData<?> selectCashBillForPop(Map<String, Object> map) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/selectCashBillForPop";
        ResultData<?> backResult = post(url, map);
        return backResult;
    }

    public ResultData<?> removeFromCashBill(Map<String, Object> map) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/removeFromCashBill";
        ResultData<?> backResult = post(url, map);
        return backResult;
    }

    public ResultData<String> saveCashBill(CashBillProject project) throws Exception{

        String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/saveCashBill";
        ResultData<String> backResult = post(url, project);
        return backResult;
    }
    /**
     * 查询列表
     */
    public ResultData getCashBillList(Map<String, Object> reqMap, PageInfo pageInfo)throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/getCashBillList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }
    /**
     * 获取请款单详情信息
     * @return
     * @throws Exception
     */
	public ResultData getCashBillDeatilById(Map<String,Object> map) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/getCashBillDeatilById/{param}";
        ResultData<?> backResult = get(url, map);
        return backResult;
    }
	/**
	 * 提交OA
	 * @param reqMap
	 * @throws Exception
	 */
	public ResultData listToSubmitOa(Map<String, Object> reqMap) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/listToSubmitOa";
        reqMap.put("userCode",UserInfoHolder.get().getUserCode());
        ResultData resultData = post(url, reqMap);
        return resultData;
    }
    /**
     * 获取联动核算主体
     * @throws Exception
     */
    public ResultData<?> getLnkAccountProjectList(String cityNo) throws Exception{ //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/getLnkAccountProjectList/{cityNo}";
        ResultData<?> backResult = get(url, cityNo);
        return backResult;
    }

	/**
     * 获取联动核算主体
     * @throws Exception
     */
    public ResultData<?> getOALnkAccountProjectList(String projectNo) throws Exception{ //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/getOALnkAccountProjectList/{projectNo}";
        ResultData<?> backResult = get(url, projectNo);
        return backResult;
   }

    /**
     * desc:获取框架协议信息
     * 2019年8月13日
     * author:zhenggang.Huang
     */
    public ResultData<?> getOAFrmAgreementList(Map<String, Object> reqMap, PageInfo pageInfo)
            throws Exception{
            //调用 接口
            String url =SystemParam.getWebConfigValue("RestServer")+ "cashBill" + "/getOAFrmAgreementList/{param}";
            
            ResultData<?> reback = get(url, reqMap, pageInfo);
            
            return reback;
            
    }
    
    /**
     * desc:收款银行信息
     * 2019年8月13日
     * author:zhenggang.Huang
     */
    public ResultData<?> getOAReceiveBankList(Map<String, Object> reqMap, PageInfo pageInfo)
    		throws Exception{
    	//调用 接口
    	String url =SystemParam.getWebConfigValue("RestServer")+ "cashBill" + "/getOAReceiveBankList/{param}";
    	
    	ResultData<?> reback = get(url, reqMap, pageInfo);
    	
    	return reback;
    	
    }

    /**
     * desc: 考核主体信息
     * 2019年8月13日
     * author: huangmc
     */
    public ResultData<?> getOACheckBodyList(Map<String, Object> reqMap)
            throws Exception{
        //调用 接口
        String url =SystemParam.getWebConfigValue("RestServer")+ "cashBill" + "/getOACheckBodyList/{param}";

        ResultData<?> reback = get(url, reqMap);

        return reback;

    }

    public ResultData<String> saveOACashBill(CashBillProject project) throws Exception{

        String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/saveOACashBill";
        ResultData<String> backResult = post(url, project);
        return backResult;
    }
    
    public void remove(Map<String, Object> reqMap) throws Exception {
		String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/remove";
		put(url, reqMap);
	}
}
