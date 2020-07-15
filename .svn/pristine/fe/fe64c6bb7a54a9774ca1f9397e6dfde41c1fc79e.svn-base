package cn.com.eju.deal.houseLinkage.report.service;

import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import cn.com.eju.deal.dto.houseLinkage.report.ReportInsertDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** 
* @ClassName: ReportService 
* @Description: 报备Service 
* @author 陆海丹
* @date 2016年5月19日 上午11:37:23 
*  
*/
@Service("reportInsertService")
public class ReportInsertService extends BaseService<ReportInsertDto>
{
    
    /** 
    * @Title: createReport 
    * @Description: 新增报备
    * @param reqMap
    * @return
    * @throws Exception     
    */
    public ResultData<?> createReport(Map<String, Object> reqMap, UserInfo userInfo)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "APIHouseLink" + "/insertReportBack2";
        ReportInsertDto reportInsertDto = this.convertDto(reqMap, userInfo);
        ResultData<?> backResult = post(url, reportInsertDto);//TODO 接口返回的resultData类型不一样
        return backResult;
    }
    
    private ReportInsertDto convertDto(Map<String, Object> reqMap, UserInfo userInfo)
    {
        ReportInsertDto reportInsertDto = new ReportInsertDto();
        String estateNm = (String)reqMap.get("estateNm");
        String estateId = (String)reqMap.get("estateId");
        String customerName = (String)reqMap.get("customerName");
        String customerPhone = (String)reqMap.get("customerPhone");
        String customerNum = (String)reqMap.get("customerNum");
        String validRelationDate = (String)reqMap.get("validRelationDate");
        String reportDate = (String)reqMap.get("reportDate");
        if(StringUtil.isNotEmpty(validRelationDate)){
        	validRelationDate = validRelationDate+" 00:00:00";
        }
        if(StringUtil.isNotEmpty(reportDate)){
        	reportDate = reportDate+" 00:00:00";
        }
//        if (StringUtil.isNotEmpty(validRelationDate))
//        {
//            validRelationDate = validRelationDate.replace("T", " ");
//            validRelationDate = validRelationDate + ":01";//需要到秒才能存
//        }
        String customerRequire = (String)reqMap.get("customerRequire");
        String memo = (String)reqMap.get("memo");

        if (null != userInfo)
        {
            reportInsertDto.setCityId(UserInfoHolder.get().getCityNo());
            reportInsertDto.setCompanyId((String)reqMap.get("reportCompanyId"));
            reportInsertDto.setCompanyName((String)reqMap.get("reportCompanyNm"));
            reportInsertDto.setCreateId(userInfo.getUserId());
            reportInsertDto.setEmployeeId(userInfo.getUserCode());
            reportInsertDto.setEmployeeName(userInfo.getUserName());
        }
        if (reqMap.get("storeNm") != null) {
            String storeParam = (String) reqMap.get("storeNm");
            String[] storeary = storeParam.split(",");
            if (storeary != null && storeary.length > 0) {
                for (int i = 0; i < storeary.length; i++) {
                    if (i == 0 &&  !"null".equals(storeary[i])) {
                        reportInsertDto.setStoreId(storeary[i]);
                    }
                    if (i == 1 && !"null".equals(storeary[i])) {
                        reportInsertDto.setStoreName(storeary[i]);
                    }
                    if (i == 2 && !"null".equals(storeary[i])) {
                        reportInsertDto.setContactId(storeary[i]);
                    }
                    if (i == 3 && !"null".equals(storeary[i])) {
                        reportInsertDto.setContactNm(storeary[i]);
                    }
                    // 门店的城市编号
                    if (i == 4 && !"null".equals(storeary[i])) {
                        reportInsertDto.setStoreCityNo(storeary[i]);
                    }
                }
            }
        }

        //附件信息
        if (reqMap.get("fileRecordMainIds") != null && !"".equals(reqMap.get("fileRecordMainIds").toString())) {
            String[] fileIdArr = reqMap.get("fileRecordMainIds").toString().split(",");
            FileRecordMainDto fileRecordMainDto;
            List<FileRecordMainDto> list = new ArrayList<>();

            for(int i = 0,l = fileIdArr.length;i<l;i++){
                fileRecordMainDto = new FileRecordMainDto();
                fileRecordMainDto.setFileRecordMainId(Integer.valueOf(fileIdArr[i]));
                list.add(fileRecordMainDto);
            }

            reportInsertDto.setFileList(list);
        }

        reportInsertDto.setCustomerId(RandomStringUtils.randomNumeric(10));
        reportInsertDto.setCustomerName(customerName);
        reportInsertDto.setCustomerPhone(customerPhone);
        reportInsertDto.setCustomerNum(customerNum);
        reportInsertDto.setCustomerRequire(customerRequire);
        reportInsertDto.setEstateId(estateId);
        reportInsertDto.setEstateName(estateNm);
        reportInsertDto.setMemo(memo);
        reportInsertDto.setSource("web");
        reportInsertDto.setValidRelationDate(validRelationDate);
        reportInsertDto.setReportDate(reportDate);
        
        //重新设置业绩归属
        reportInsertDto.setContactId(reqMap.get("tmpContactId").toString());
        reportInsertDto.setContactNm(reqMap.get("tmpContactNm").toString());
        //设置业绩归属中心
        if(reqMap.containsKey("centerGroupId") ){
        	String centerGroupId = (String)reqMap.get("centerGroupId");
        	String centerGroupName = (String)reqMap.get("centerGroupName");
        	reportInsertDto.setCenterGroupId(Integer.valueOf(centerGroupId));
        	reportInsertDto.setCenterGroupName(centerGroupName);
        }
        String reportAgent = (String)reqMap.get("reportAgent");
        String reportAgentTel = (String)reqMap.get("reportAgentTel");
        String customerNmTwo = (String)reqMap.get("customerNmTwo");
        String customerTelTwo = (String)reqMap.get("customerTelTwo");
        reportInsertDto.setReportAgent(reportAgent);
        reportInsertDto.setReportAgentTel(reportAgentTel);
        reportInsertDto.setCustomerIdTwo(RandomStringUtils.randomNumeric(10));
        reportInsertDto.setCustomerNmTwo(customerNmTwo);
        reportInsertDto.setCustomerTelTwo(customerTelTwo);
        return reportInsertDto;
    }
    
}
