package cn.com.eju.pmls.cooperation.service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.channelBusiness.dto.BusinessManagerDto;
import cn.com.eju.pmls.cooperation.dto.CooperationDto;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service("cooperationService")
public class CooperationService extends BaseService {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    /**
     * 获取 合作确认函列表
     * @param reqMap
     * @param pageInfo
     * @return
     * @throws Exception
     */
    public ResultData<?> getCooperationList(Map<String,Object> reqMap, PageInfo pageInfo) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "cooperationController" + "/getCooperationList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }

    /**
     * 新增合作确认函
     * @param dto
     * @return
     * @throws Exception
     */
    public ResultData<?> addCooperation(CooperationDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "cooperationController" + "/addCooperation";
        ResultData<?> reback = post(url, dto);
        return reback;
    }

    /**
     * 修改合作确认函
     * @param dto
     * @return
     * @throws Exception
     */
    public ResultData<?> updateCooperation(CooperationDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "cooperationController" + "/updateCooperation";
        ResultData<?> reback = post(url, dto);
        return reback;
    }

    /**
     * 获取合作确认函详情
     * @param dto
     * @return
     * @throws Exception
     */
    public ResultData<?> getCooperationInfo(CooperationDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "cooperationController" + "/getCooperationInfo";
        ResultData<?> reback = post(url, dto);
        return reback;
    }

    /**
     * 作废合作确认函
     * @param dto
     * @return
     * @throws Exception
     */
    public ResultData<?> invalidCooperation(CooperationDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "cooperationController" + "/invalidCooperation";
        ResultData<?> reback = post(url, dto);
        return reback;
    }

    /**
     * 获取经纪公司列表
     * @param reqMap
     * @param pageInfo
     * @return
     * @throws Exception
     */
    public ResultData<?> getCompanyList(Map<String,Object> reqMap, PageInfo pageInfo) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "cooperationController" + "/getCompanyList/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }

    /**
     * 检查经纪公司是否可选
     * @param dto
     * @return
     * @throws Exception
     */
    public ResultData<?> checkCompany(BusinessManagerDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "cooperationController" + "/checkCompany";
        ResultData<?> reback = post(url, dto);
        return reback;
    }

    /**
     * 获取项目合同列表
     * @param reqMap
     * @param pageInfo
     * @return
     * @throws Exception
     */
    public ResultData<?> getEstateHtRecord(Map<String,Object> reqMap, PageInfo pageInfo) throws Exception{
        logger.info("分销合同#获取项目合同列表#start queryParam="+ JSON.toJSONString(reqMap));
        String url = SystemParam.getWebConfigValue("RestServer") + "cooperationController" + "/getEstateHtRecord/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        logger.info("分销合同#获取项目合同列表#end queryParam="+JSON.toJSONString(reqMap));
        return reback;
    }

    /**
     * 获取经纪公司详情
     * @param dto
     * @return
     * @throws Exception
     */
    public ResultData<?> getCompanyInfo(BusinessManagerDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "cooperationController" + "/getCompanyInfo";
        ResultData<?> reback = post(url, dto);
        return reback;
    }

    /**
     * 发起OA申请 合作协议
     * @param dto
     * @return
     * @throws Exception
     */
    public ResultData<?> sendOACooperation(CooperationDto dto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "cooperationController" + "/sendOACooperation";
        ResultData<?> reback = post(url, dto);
        return reback;
    }


}
