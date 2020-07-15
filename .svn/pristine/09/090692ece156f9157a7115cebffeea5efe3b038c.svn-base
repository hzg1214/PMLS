package cn.com.eju.pmls.jsStatement.service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.jsStatement.dto.PmlsJsStatementDto;
import cn.com.eju.pmls.jsStatement.dto.PmlsJsStatementVto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("jsStatementService")
public class JsStatementService extends BaseService {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    // 查询
    public ResultData<?> queryList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "jsStatementController" + "/queryList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }

    // 作废
    public ResultData<?> toInvalid(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "jsStatementController" + "/toInvalid";
        ResultData<?> reback = post(url, reqMap);
        return reback;
    }

    // 终止
    public ResultData<?> toTermination(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "jsStatementController" + "/toTermination";
        ResultData<?> reback = post(url, reqMap);
        return reback;
    }


    // 查询结算书（公司）
    public ResultData<?> queryJsCompanyList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "jsStatementController" + "/queryJsCompanyList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }

    // 查询结算书（项目）
    public ResultData<?> queryJsProjectList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "jsStatementController" + "/queryJsProjectList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }

    // 查询结算书（核算主体）
    public ResultData<?> queryJsHSCodeList(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "jsStatementController" + "/queryJsHSCodeList/{param}";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }

    // 查询结算书（考核主体）
    public ResultData<?> queryJsKHCodeList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "jsStatementController" + "/queryJsKHCodeList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }

    // 查询结算书（合同）
    public ResultData<?> queryJsFrameOaList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "jsStatementController" + "/queryJsFrameOaList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }

    // 查询结算书（正常结算）
    public ResultData<?> queryJsNormalReportList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "jsStatementController" + "/queryJsNormalReportList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }

    // 查询结算书（冲抵结算）
    public ResultData<?> queryJsOffsetReportList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "jsStatementController" + "/queryJsOffsetReportList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }

    // 获取结算书详情
    public ResultData<PmlsJsStatementDto> getJsStatementDetail(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "jsStatementController" + "/getJsStatementDetail";
        ResultData<PmlsJsStatementDto> reback = post(url, reqMap);
        return reback;
    }

    //获取请款单详情信息
    public ResultData getCashBillDeatilByCashBillNo(Map<String, Object> map) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "jsStatementController" + "/getCashBillDeatilByCashBillNo/{param}";
        ResultData<?> backResult = get(url, map);
        return backResult;
    }

    // 取得总控相关数据
    public ResultData<?> getZkInfo(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "jsStatementController" + "/getZkInfo/{param}";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }

    // 保存结算书
    public ResultData<?> save(PmlsJsStatementVto info) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "jsStatementController" + "/save";
        ResultData<?> reback = post(url, info);
        return reback;
    }

    // 更新结算书
    public ResultData<?> update(PmlsJsStatementVto info) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "jsStatementController" + "/update";
        ResultData<?> reback = post(url, info);
        return reback;
    }

    // 取得结算书信息
    public ResultData<?> getJsStatementById(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "jsStatementController" + "/getJsStatementById/{param}";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }

    // 取得结算书明细信息
    public ResultData<?> getJsStatementDtlById(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "jsStatementController" + "/getJsStatementDtlById/{param}";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }

   // 取得结算书附件
   public ResultData<?>  getJsStatementFile(String id) throws Exception{
       String url = SystemParam.getWebConfigValue("RestServer") + "jsStatementController" + "/getJsStatementFile/" + id;
       ResultData<?> reback = get(url);
       return reback;
   }

}
