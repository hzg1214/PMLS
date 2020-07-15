package cn.com.eju.pmls.channelBusiness.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.channelBusiness.dto.BusinessManagerDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("businessManagerService")
public class BusinessManagerService extends BaseService {
    //获取商户管理列表
    public ResultData<?> getBusinessManagerList(Map<String,Object> reqMap, PageInfo pageInfo) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "businessManagerController" + "/getBusinessManagerList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }

    //获取商户信息
    public ResultData<BusinessManagerDto> getBusinessInfo(BusinessManagerDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "businessManagerController" + "/getBusinessInfo";
        ResultData<BusinessManagerDto> reback = post(url, dto);
        return reback;
    }
    //获取商户信息
    public ResultData<BusinessManagerDto> getBusinessInfo2(BusinessManagerDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "businessManagerController" + "/getBusinessInfo2";
        ResultData<BusinessManagerDto> reback = post(url, dto);
        return reback;
    }
    //新增商户
    public ResultData<?> addBusiness(BusinessManagerDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "businessManagerController" + "/addBusiness";
        ResultData<?> reback = post(url, dto);
        return reback;
    }
    //修改商户
    public ResultData<?> updateBusiness(BusinessManagerDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "businessManagerController" + "/updateBusiness";
        ResultData<?> reback = post(url, dto);
        return reback;
    }
    //删除商户
    public ResultData<?> deleteBusiness(BusinessManagerDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "businessManagerController" + "/deleteBusiness";
        ResultData<?> reback = post(url, dto);
        return reback;
    }

    //获取联系人列表
    public ResultData<?> getContactsList(Map<String,Object> reqMap, PageInfo pageInfo) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "businessManagerController" + "/getContactsList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }

    //获取联系人信息
    public ResultData<BusinessManagerDto> getContactsInfo(BusinessManagerDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "businessManagerController" + "/getContactsInfo";
        ResultData<BusinessManagerDto> reback = post(url, dto);
        return reback;
    }
    //新增联系人
    public ResultData<?> addContacts(BusinessManagerDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "businessManagerController" + "/addContacts";
        ResultData<?> reback = post(url, dto);
        return reback;
    }

    //修改联系人
    public ResultData<?> updateContacts(BusinessManagerDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "businessManagerController" + "/updateContacts";
        ResultData<?> reback = post(url, dto);
        return reback;
    }

    //获取公司日志列表
    public ResultData<?> getOperationLogList(Map<String,Object> reqMap, PageInfo pageInfo) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "businessManagerController" + "/getOperationLogList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }
    //新增商户
    public ResultData<?> addCompanyReleaseCity(BusinessManagerDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "businessManagerController" + "/addCompanyReleaseCity";
        ResultData<?> reback = post(url, dto);
        return reback;
    }

    //获取维护人列表
    public ResultData<?> getMaintainerList(Map<String,Object> reqMap, PageInfo pageInfo) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "businessManagerController" + "/getMaintainerList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }
    //获取公司维护人记录列表
    public ResultData<?> getCompanyMaintainerList(Map<String,Object> reqMap, PageInfo pageInfo) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "businessManagerController" + "/getCompanyMaintainerList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }

    //修改联系人
    public ResultData<?> updateMaintainer(BusinessManagerDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "businessManagerController" + "/updateMaintainer";
        ResultData<?> reback = post(url, dto);
        return reback;
    }

    public ResultData<?> adjustToProcuration(BusinessManagerDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "businessManagerController" + "/adjustToProcuration";
        ResultData<?> reback = post(url, dto);
        return reback;
    }
    // 获取公司门店信息
    public ResultData<?> getCompanyStoreList(Map<String,Object> reqMap, PageInfo pageInfo) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "store" + "/qCompanyId/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }


}
