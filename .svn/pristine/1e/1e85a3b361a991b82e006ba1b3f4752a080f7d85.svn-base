package cn.com.eju.pmls.scene.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.cashbill.model.CashBillCompany;
import cn.com.eju.deal.cashbill.model.CashBillProject;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("sceneExpentService")
public class SceneExpentService extends BaseService {

    public ResultData<?> addBatchExpent(Map<String, Object> map) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/addToBatchCash";
        ResultData<?> backResult = post(url, map);
        return backResult;
    }

    public ResultData<?> selectCashBill(Map<String, Object> map) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/selectCashBill";
        ResultData<?> backResult = post(url, map);
        return backResult;
    }

    public ResultData<?> selectCashBillForPop(Map<String, Object> map) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/selectCashBillForPop";
        ResultData<?> backResult = post(url, map);
        return backResult;
    }

    /**
     * 获取联动核算主体
     *
     * @throws Exception
     */
    public ResultData<?> getOALnkAccountProjectList(String projectNo) throws Exception { //调用 接口
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
            throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/getOAFrmAgreementList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;

    }

    /**
     * desc:收款银行信息
     * 2019年8月13日
     * author:zhenggang.Huang
     */
    public ResultData<?> getOAReceiveBankList(Map<String, Object> reqMap, PageInfo pageInfo)
            throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/getOAReceiveBankList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;

    }

    /**
     * desc: 考核主体信息
     * 2019年8月13日
     * author: huangmc
     */
    public ResultData<?> getOACheckBodyList(Map<String, Object> reqMap)
            throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/getOACheckBodyList/{param}";

        ResultData<?> reback = get(url, reqMap);

        return reback;

    }


    public ResultData<?> saveOACashBill(CashBillProject project) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/saveOACashBill";
        ResultData<?> backResult = post(url, project);
        return backResult;
    }

    public ResultData<?> removeFromCashBill(Map<String, Object> map) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/removeFromCashBill";
        ResultData<?> backResult = post(url, map);
        return backResult;
    }


    public ResultData<?> getOffsetInfoList(Map<String, Object> reqMap, PageInfo pageInfo)
            throws Exception{
        //调用 接口
        String url =SystemParam.getWebConfigValue("RestServer")+ "cashBill" + "/getOffsetInfoList/{param}";

        ResultData<?> reback = get(url, reqMap, pageInfo);

        return reback;
    }

    /**
     * 获取请款单详情信息
     * @return
     * @throws Exception
     */
    public ResultData getCashBillDeatilByIdNew(Map<String,Object> map) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/getCashBillDeatilByIdNew/{param}";
        ResultData<?> backResult = get(url, map);
        return backResult;
    }

    public ResultData<?> selectJsStatementDtlListCanQk(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/selectJsStatementDtlListCanQk/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }

    public ResultData<?> saveOACashBillNew(CashBillCompany cashBillCompany) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/saveOACashBillNew";
        ResultData<?> backResult = post(url, cashBillCompany);
        return backResult;
    }


    public ResultData invalidBill(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "cashBill" + "/invalidBill";
        ResultData<?> reback =  post(url, reqMap);
        return reback;
    }
}
