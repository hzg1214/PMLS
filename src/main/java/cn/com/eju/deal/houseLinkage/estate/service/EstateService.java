package cn.com.eju.deal.houseLinkage.estate.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.seqNo.service.SeqNoService;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.service.CommonService;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateChangeDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateInfoDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstatePhotosDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateReleaseCityDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateRuleDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateSupportDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateTypeDto;
import cn.com.eju.deal.dto.houseLinkage.estate.PhotoDto;

/**   
* Service层
* @author (qianwei)
* @date 2016年4月29日 下午9:30:27
*/
@Service("estateService")
public class EstateService extends BaseService<EstateInfoDto>
{
    
    //private final static String REST_ESTATE = SystemCfg.getString("estateRestServer");
    
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "seqNoService")
    private SeqNoService seqNoService;
    
    @Resource(name = "commonService")
    private CommonService commonService;
    
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
        String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/{id}";
        
        ResultData<?> backResult = get(url, id);
        
        return backResult;
    }
    
    /** 
    * @Title: getEstateDetailById 
    * @Description:  根据自增编号获取楼盘基本信息和楼盘详情
    * @param id
    * @return
    * @throws Exception     
    */
    public ResultData<?> getEstateDetailById(int id)
        throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/qd/{id}/";
        ResultData<?> backResult = get(url, id);
        return backResult;
    }
    
    /** 
    * @Title: getEstateRuleByEstateId 
    * @Description: 根据楼盘编号获取联动规则
    * @param reqMap
    * @return
    * @throws Exception     
    */
    public ResultData<?> getEstateRuleByEstateId(Map<String, Object> reqMap)
        throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/qru/{param}/";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }
    
    /** 
    * @Title: getEstateTypeByEstateId 
    * @Description: 根据楼盘编号获取在售户型
    * @param reqMap
    * @return
    * @throws Exception     
    */
    public ResultData<?> getEstateTypeByEstateId(Map<String, Object> reqMap)
        throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/qt/{param}/";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }
    
    /** 
    * @Title: getEstateSupportByEstateId 
    * @Description: 根据楼盘编号获取周边配套 
    * @param reqMap
    * @return
    * @throws Exception     
    */
    public ResultData<?> getEstateSupportByEstateId(Map<String, Object> reqMap)
        throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/qs/{param}/";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }
    
    /** 
    * @Title: getPhotoByEstateId 
    * @Description: 根据楼盘编号获取楼盘图片
    * @param reqMap
    * @return
    * @throws Exception     
    */
    public ResultData<?> getPhotoByEstateId(Map<String, Object> reqMap)
        throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/qp/{param}/";
        ResultData<?> reback = get(url, reqMap);
        return reback;
    }
    
    /** 
     *  * 查询部门列表
     * @param groupId
     * @return
     * @throws Exception
     */
    public ResultData<?> getGroupList(String groupId)
        throws Exception
    {
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/groupId/{groupId}";
        
        ResultData<?> backResult = get(url, groupId);
        
        return backResult;
    }
    
    /** 
    * @Title: getSceneGroupList 
    * @Description: 获取案场负责人部门
    * @return
    * @throws Exception     
    */
    public ResultData<?> getSceneUserList(String cityNo)
        throws Exception
    {
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/scene/{cityNo}";
        
        ResultData<?> backResult = get(url, cityNo);
        
        return backResult;
    }
    public ResultData<?> getSceneUserList2(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
    	//调用 接口
    	String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/scene1/{param}";
    	ResultData<?> reback = get(url, reqMap, pageInfo);
    	return reback;
    }
    /** 
     * 查询员工列表
     * @param groupId
     * @return
     * @throws Exception
     */
    public ResultData<?> getUserList(String groupId)
        throws Exception
    {
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/user/{groupId}";
        
        ResultData<?> backResult = get(url, groupId);
        
        return backResult;
    }
    
    /** 
     * 获取日期类型列表
     * @param dateTypeKbn
     * @return
     * @throws Exception
     */
    public ResultData<?> getDateKbnList(String dateTypeKbn)
        throws Exception
    {
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/dateTypeKbn/{dateTypeKbn}";
        
        ResultData<?> backResult = get(url, dateTypeKbn);
        
        return backResult;
    }
    
    /** 
     * 
    * 查询-list
    * @param reqMap
    * @return
    * @throws Exception
    */
    public ResultData<?> index(Map<String, Object> reqMap, PageInfo pageInfo)
        throws Exception
    {
    	
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/q/{param}";
        
        ResultData<?> reback = get(url, reqMap, pageInfo);
        
        return reback;
        
    }
    
    /** 
    * 创建
    * @param reqMap
    * @param estatePhotosDto
    * @return
    * @throws Exception
    */
    public ResultData<?> create(Map<String, Object> reqMap,EstatePhotosDto estatePhotosDto)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "estate/create";
        EstateInfoDto estateInfoDto = this.SetNewEstateInfoDto(reqMap,estatePhotosDto);
        ResultData<?> backResult = post(url, estateInfoDto);
        return backResult;
    }
    
    /** 
    * 更新
    * @param reqMap
    * @return
    * @throws Exception
    */
    public ResultData<?> update(Map<String, Object> reqMap,EstatePhotosDto estatePhotosDto)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "estate/update";
        EstateInfoDto estateInfoDto = this.SetEditEstateInfoDto(reqMap,estatePhotosDto);
        
        estateInfoDto.getEstate().setProjectStatus(DictionaryConstants.Project_Status_Draft);
        UserInfo userInfo = UserInfoHolder.get();
        EstateChangeDto estateChangeDto = new EstateChangeDto();
        estateChangeDto.setChangeType(String.valueOf(DictionaryConstants.Project_Change_Status));
        estateChangeDto.setChangeName("项目信息更新");
        estateChangeDto.setChangeDetail("项目信息更新");
        estateChangeDto.setChangeDate(new Date());
        estateChangeDto.setEstateId(estateInfoDto.getEstate().getId());
        estateChangeDto.setOperator(userInfo.getUserId());
        estateChangeDto.setOperatorCode(userInfo.getUserCode());
        estateChangeDto.setOperatorName(userInfo.getUserName());
        ArrayList<EstateChangeDto> ecList = new ArrayList<>();
        ecList.add(estateChangeDto);
        estateInfoDto.setEstateChangeDetails(ecList);
        
        ResultData<?> result = post(url, estateInfoDto);
        return result;
    }
    
    /** 
    * 删除
    * @param id
    * @param updateId
    * @return
    * @throws Exception
    */
    public void delete(int id, int updateId)
        throws Exception
    {
        
        String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/{id}/{updateId}";
        
        delete(url, id, updateId);
        
    }
    
    /** 
     * @Title: uploadFile 
     * @Description: 文件上传
     * @param request
     * @param reqMap
     * @return     
     */
//    public Map<String, Object> uploadFile(HttpServletRequest request, Map<String, Object> reqMap)
//        throws Exception
//    {
//        Map<String, Object> resultMap = new HashMap<>();
//        ResultData<?> resultData = new ResultData<>();
//        Map<String, Object> upMap = FileHelper.uploadFile(request);
//        
//        @SuppressWarnings("unchecked")
//        List<Map<String, Object>> rspList = (ArrayList<Map<String, Object>>)upMap.get("data");
//        
//        String fileId = "";
//        for (Map<String, Object> map : rspList)
//        {
//            String returnCode = (String)map.get("returnCode");
//            if (returnCode == ReturnCode.SUCCESS)
//            {
//                fileId = (String)map.get("fileCode");
//            }
//            else
//            {
//                resultData.setFail();
//                break;
//            }
//        }
//        if (rspList.size() == 0)
//        {
//            resultData.setFail();
//        }
//        resultMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
//        resultMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
//        if (resultData.getReturnCode().equals(ReturnCode.SUCCESS))
//        {
//            resultMap.put(Constant.RETURN_DATA_KEY, fileId);
//        }
//        return resultMap;
//    }
    
    /** 
    * @Title: createEstateType 
    * @Description: 新增在售户型
    * @param reqMap
    * @return
    * @throws Exception     
    */
    public ResultData<?> createEstateType(Map<String, Object> reqMap,EstatePhotosDto estatePhotosDto)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/ct";
        EstateInfoDto estateInfoDto = this.setNewEstateTypeDto(reqMap,estatePhotosDto);
        ResultData<?> backResult = post(url, estateInfoDto);
        return backResult;
    }
    
    /** 
    * @Title: getEstateTypeById 
    * @Description: 根据编号获取在售户型
    * @param typeId
    * @return
    * @throws Exception     
    */
    public ResultData<?> getEstateTypeById(int typeId)
        throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/qtid/{typeId}/";
        ResultData<?> reback = get(url, typeId);
        return reback;
    }
    
    /** 
    * @Title: editEstateType 
    * @Description: 编辑在售户型
    * @param reqMap
    * @throws Exception
    */
    public void editEstateType(Map<String, Object> reqMap,EstatePhotosDto estatePhotosDto)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/ut";
        EstateInfoDto estateInfoDto = this.setEditEstateTypeDto(reqMap,estatePhotosDto);
        put(url, estateInfoDto);
    }
    
    /** 
    * @Title: delEstateType 
    * @Description: 删除在售户型
    * @param id
    * @param updateId     
     * @throws Exception 
    */
    public void delEstateType(int id, int updateId)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/dt/{id}/{updateId}";
        delete(url, id, updateId);
    }
    
    /** 
    * @Title: audit 
    * @Description: 审核
    * @param reqMap
    * @throws Exception     
    */
    public void audit(Map<String, Object> reqMap)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "estate/update";
        EstateInfoDto estateInfoDto = new EstateInfoDto();
        EstateDto estateDto = new EstateDto();
        UserInfo userInfo = UserInfoHolder.get();
        String estId = (String)reqMap.get("id");//楼盘自增编号
        String auditStatusStr = (String)reqMap.get("auditStatus");//审核状态
        String auditMemo = (String)reqMap.get("auditMemo");//审核不通过说明
        estateDto.setAuditMemo(auditMemo);
        estateDto.setId(Integer.valueOf(estId));
        
        // 审核追加日志 add by wangkanlin 2017/09/04
        EstateChangeDto estateChangeDto = new EstateChangeDto();
        estateChangeDto.setChangeDate(new Date());
        estateChangeDto.setEstateId(Integer.valueOf(estId));
        estateChangeDto.setOperator(userInfo.getUserId());
        estateChangeDto.setOperatorCode(userInfo.getUserCode());
        estateChangeDto.setOperatorName(userInfo.getUserName());
        estateChangeDto.setChangeType(String.valueOf(DictionaryConstants.Project_Change_Audit));
        estateChangeDto.setChangeName("项目审核");
        ArrayList<EstateChangeDto> ecList = new ArrayList<>();
        ecList.add(estateChangeDto);
        estateInfoDto.setEstateChangeDetails(ecList);
        
        //如果是审核通过设置项目状态为跟单和跟单日期
        Integer AuditStatus = Integer.valueOf(auditStatusStr);
        if(AuditStatus == DictionaryConstants.ESTATE_AUDIT_PASS)
        {
        	estateDto.setProjectStatus(DictionaryConstants.Project_Status_Start);
        	estateDto.setStartDate(new Date());	
            // 审核日志 通过时 add by wangkanlin 2017/09/04
            estateChangeDto.setChangeDetail("项目状态更新为跟单");
        }else {
            // 审核日志 驳回时 add by wangkanlin 2017/09/04
            estateChangeDto.setChangeDetail(auditMemo);
        }
        if (StringUtil.isNotEmpty(auditStatusStr))
        {
            Integer auditStatus = Integer.valueOf(auditStatusStr);
            estateDto.setAuditStatus(auditStatus);
        }
        estateDto.setUptDt(new Date());
        estateDto.setUptEmpId(userInfo.getUserId());
        estateInfoDto.setEstate(estateDto);
        post(url, estateInfoDto);
    }
    
    /** 
    * @Title: release 
    * @Description: 发布
    * @param reqMap
    * @throws Exception     
    */
    public void release(Map<String, Object> reqMap)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "estate/update";
        EstateInfoDto estateInfoDto = new EstateInfoDto();
        EstateDto estateDto = new EstateDto();
        UserInfo userInfo = UserInfoHolder.get();
        String estId = (String)reqMap.get("id");//楼盘自增编号
        String releaseFlg = (String)reqMap.get("releaseFlg");//发布状态
        String releaseDt = (String)reqMap.get("releaseDt");//发布时间
        estateDto.setId(Integer.valueOf(estId));
        if (releaseFlg.equals("1"))
        {
            Integer releaseStatus = Integer.valueOf(DictionaryConstants.ESTATE_PUBLISH_YES);
            estateDto.setReleaseStatus(releaseStatus);
            estateDto.setReleaseDt(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        }
        else if (releaseFlg.equals("0"))
        {
            Integer releaseStatus = Integer.valueOf(DictionaryConstants.ESTATE_PUBLISH_NO);
            estateDto.setReleaseStatus(releaseStatus);
            estateDto.setReleaseDt(releaseDt);
        }
        estateDto.setUptDt(new Date());
        estateDto.setUptEmpId(userInfo.getUserId());
        estateInfoDto.setEstate(estateDto);
        
        List<EstateChangeDto> List = new ArrayList<>();
        EstateChangeDto estateChangeDto = new EstateChangeDto();
        estateChangeDto.setChangeType(String.valueOf(DictionaryConstants.Project_Change_Open));
        estateChangeDto.setChangeName("发布");
        estateChangeDto.setChangeDetail("发布上架");
        if (releaseFlg.equals("1"))
        {
        	 estateChangeDto.setChangeDate(new Date());
        } else if (releaseFlg.equals("0"))
        {
        	SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
        	Date date = sdf.parse(releaseDt);
        	estateChangeDto.setChangeDate(date);
        }
        estateChangeDto.setEstateId(Integer.valueOf(estId));
        estateChangeDto.setOperator(userInfo.getUserId());
        estateChangeDto.setOperatorCode(userInfo.getUserCode());
        estateChangeDto.setOperatorName(userInfo.getUserName());
        List.add(estateChangeDto);
        estateInfoDto.setEstateChangeDetails(List);
        post(url, estateInfoDto);
    }
    
    /** 
    * @Title: down 
    * @Description: 下架
    * @param reqMap
    * @throws Exception     
    */
    public void down(Map<String, Object> reqMap)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "estate/update";
        EstateInfoDto estateInfoDto = new EstateInfoDto();
        EstateDto estateDto = new EstateDto();
        UserInfo userInfo = UserInfoHolder.get();
        String estId = (String)reqMap.get("id");//楼盘自增编号
        String releaseStatusStr = (String)reqMap.get("releaseStatus");//审核状态
        String releaseOffMemo = (String)reqMap.get("releaseOffMemo");//审核不通过说明
        estateDto.setReleaseOffMemo(releaseOffMemo);
        estateDto.setId(Integer.valueOf(estId));
        if (StringUtil.isNotEmpty(releaseStatusStr))
        {
            Integer releaseStatus = Integer.valueOf(releaseStatusStr);
            estateDto.setReleaseStatus(releaseStatus);
        }
        estateDto.setUptDt(new Date());
        estateDto.setUptEmpId(userInfo.getUserId());
        estateInfoDto.setEstate(estateDto);
        
        List<EstateChangeDto> List = new ArrayList<>();
        EstateChangeDto estateChangeDto = new EstateChangeDto();
        estateChangeDto.setChangeType(String.valueOf(DictionaryConstants.Project_Change_Close));
        estateChangeDto.setChangeName("下架");
        estateChangeDto.setChangeDetail(releaseOffMemo);
        estateChangeDto.setChangeDate(new Date());
        estateChangeDto.setEstateId(Integer.valueOf(estId));
        estateChangeDto.setOperator(userInfo.getUserId());
        estateChangeDto.setOperatorCode(userInfo.getUserCode());
        estateChangeDto.setOperatorName(userInfo.getUserName());
        List.add(estateChangeDto);
        estateInfoDto.setEstateChangeDetails(List);
        post(url, estateInfoDto);
    }
    
    /** 
    * @Title: backoff 
    * @Description: 撤回
    * @param id
    * @throws Exception     
    */
    public void backoff(int id)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "estate/update";
        EstateInfoDto estateInfoDto = new EstateInfoDto();
        EstateDto estateDto = new EstateDto();
        UserInfo userInfo = UserInfoHolder.get();
        Integer auditStatus = DictionaryConstants.ESTATE_AUDIT_NO_PENDING;
        estateDto.setAuditStatus(auditStatus);
        estateDto.setId(id);
        estateDto.setUptDt(new Date());
        estateDto.setUptEmpId(userInfo.getUserId());
        estateInfoDto.setEstate(estateDto);
        EstateChangeDto estateChangeDto = new EstateChangeDto();
        estateChangeDto.setChangeType(String.valueOf(DictionaryConstants.Project_Change_Backoff));
        estateChangeDto.setChangeName("撤回");
        estateChangeDto.setChangeDetail("提交审核撤回");
        estateChangeDto.setChangeDate(new Date());
        estateChangeDto.setEstateId(id);
        estateChangeDto.setOperator(userInfo.getUserId());
        estateChangeDto.setOperatorCode(userInfo.getUserCode());
        estateChangeDto.setOperatorName(userInfo.getUserName());
        ArrayList<EstateChangeDto> ecList = new ArrayList<>();
        ecList.add(estateChangeDto);
        estateInfoDto.setEstateChangeDetails(ecList);
        post(url, estateInfoDto);
    }
    
    /** 
    * @Title: start 
    * @Description: 跟单
    * @param id
    * @throws Exception     
    */
    public void startProject(int id)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "estate/update";
        EstateInfoDto estateInfoDto = new EstateInfoDto();
        EstateDto estateDto = new EstateDto();
        Integer projectStatus = DictionaryConstants.Project_Status_Start;
        estateDto.setProjectStatus(projectStatus);
        estateDto.setId(id);
        estateDto.setStartDate(new Date());
        UserInfo userInfo = UserInfoHolder.get();
        estateDto.setUptEmpId(userInfo.getUserId());
        estateInfoDto.setEstate(estateDto);
        EstateChangeDto estateChangeDto = new EstateChangeDto();
        estateChangeDto.setChangeType(String.valueOf(DictionaryConstants.Project_Change_Status));
        estateChangeDto.setChangeName("项目状态变更");
        estateChangeDto.setChangeDetail("项目状态更新为跟单");
        estateChangeDto.setChangeDate(new Date());
        estateChangeDto.setEstateId(id);
        estateChangeDto.setOperator(userInfo.getUserId());
        estateChangeDto.setOperatorCode(userInfo.getUserCode());
        estateChangeDto.setOperatorName(userInfo.getUserName());
        ArrayList<EstateChangeDto> ecList = new ArrayList<>();
        ecList.add(estateChangeDto);
        estateInfoDto.setEstateChangeDetails(ecList);
        post(url, estateInfoDto);
    }
    
    /** 
    * @Title: startCancel
    * @Description: 取消跟单
    * @param id
    * @throws Exception     
    */
    public void startCancel(int id)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "estate/update";
        EstateInfoDto estateInfoDto = new EstateInfoDto();
        EstateDto estateDto = new EstateDto();
        Integer projectStatus = DictionaryConstants.Project_Status_Start_Cancel;
        estateDto.setProjectStatus(projectStatus);
        estateDto.setId(id);
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        estateDto.setStartDate(format.parse("1900-01-01 00:00:00"));
        UserInfo userInfo = UserInfoHolder.get();
        estateDto.setUptEmpId(userInfo.getUserId());
        estateInfoDto.setEstate(estateDto);
        EstateChangeDto estateChangeDto = new EstateChangeDto();
        estateChangeDto.setChangeType(String.valueOf(DictionaryConstants.Project_Change_Status));
        estateChangeDto.setChangeName("项目状态变更");
        estateChangeDto.setChangeDetail("项目状态更新为取消跟单");
        estateChangeDto.setChangeDate(new Date());
        estateChangeDto.setEstateId(id);
        estateChangeDto.setOperator(userInfo.getUserId());
        estateChangeDto.setOperatorCode(userInfo.getUserCode());
        estateChangeDto.setOperatorName(userInfo.getUserName());
        ArrayList<EstateChangeDto> ecList = new ArrayList<>();
        ecList.add(estateChangeDto);
        estateInfoDto.setEstateChangeDetails(ecList);
        post(url, estateInfoDto);
    }
    
    /** 
    * @Title: end
    * @Description: 结案
    * @param id
    * @throws Exception     
    */
    public void endProject(int id)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "estate/update";
        EstateInfoDto estateInfoDto = new EstateInfoDto();
        EstateDto estateDto = new EstateDto();
        Integer projectStatus = DictionaryConstants.Project_Status_End;
        estateDto.setProjectStatus(projectStatus);
        estateDto.setId(id);
        estateDto.setEndDate(new Date());
        UserInfo userInfo = UserInfoHolder.get();
        estateDto.setUptEmpId(userInfo.getUserId());
        
        estateDto.setSalesStatus(1443);
        
        estateInfoDto.setEstate(estateDto);
        EstateChangeDto estateChangeDto = new EstateChangeDto();
        estateChangeDto.setChangeType(String.valueOf(DictionaryConstants.Project_Change_Status));
        estateChangeDto.setChangeName("项目状态变更");
        estateChangeDto.setChangeDetail("项目状态更新为结案");
        estateChangeDto.setChangeDate(new Date());
        estateChangeDto.setEstateId(id);
        estateChangeDto.setOperator(userInfo.getUserId());
        estateChangeDto.setOperatorCode(userInfo.getUserCode());
        estateChangeDto.setOperatorName(userInfo.getUserName());
        ArrayList<EstateChangeDto> ecList = new ArrayList<>();
        ecList.add(estateChangeDto);
        estateInfoDto.setEstateChangeDetails(ecList);
        post(url, estateInfoDto);
    }
    
    /** 
    * @Title: endCancel
    * @Description: 取消结案
    * @param id
    * @throws Exception     
    */
    public void endCancel(int id)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "estate/update";
        EstateInfoDto estateInfoDto = new EstateInfoDto();
        EstateDto estateDto = new EstateDto();
        Integer projectStatus = DictionaryConstants.Project_Status_Sign;
        estateDto.setProjectStatus(projectStatus);
        estateDto.setId(id);
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        estateDto.setEndDate(format.parse("1900-01-01 00:00:00"));
        UserInfo userInfo = UserInfoHolder.get();
        estateDto.setUptEmpId(userInfo.getUserId());
        
        estateDto.setSalesStatus(1441);
        
        estateInfoDto.setEstate(estateDto);
        EstateChangeDto estateChangeDto = new EstateChangeDto();
        estateChangeDto.setChangeType(String.valueOf(DictionaryConstants.Project_Change_Status));
        estateChangeDto.setChangeName("项目状态变更");
        estateChangeDto.setChangeDetail("取消结案，项目状态更新为签约");
        estateChangeDto.setChangeDate(new Date());
        estateChangeDto.setEstateId(id);
        estateChangeDto.setOperator(userInfo.getUserId());
        estateChangeDto.setOperatorCode(userInfo.getUserCode());
        estateChangeDto.setOperatorName(userInfo.getUserName());
        ArrayList<EstateChangeDto> ecList = new ArrayList<>();
        ecList.add(estateChangeDto);
        estateInfoDto.setEstateChangeDetails(ecList);
        post(url, estateInfoDto);
    }
    
    /** 
    * @Title: endCancel
    * @Description: 取消结案
    * @param id
     * @return 
    * @throws Exception     
    */
    public void changeCoMode(Map<String, Object> reqMap)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "estate/update";
        EstateInfoDto estateInfoDto = new EstateInfoDto();
        EstateDto estateDto = new EstateDto();
        Integer id = Integer.valueOf(reqMap.get("id").toString());//楼盘自增编号
        String coMode = reqMap.get("cooperationMode").toString();
        String coChangeReason = "";
        if (coMode.equals("20401")){
        	coChangeReason = "整合变分销，";
        }else if(coMode.equals("20402")){
        	coChangeReason = "分销变整合，";
        }
        coChangeReason += reqMap.get("cooperationChangeReason").toString();
        estateDto.setId(id);
        UserInfo userInfo = UserInfoHolder.get();
        estateDto.setUptEmpId(userInfo.getUserId());
        estateDto.setCooperationMode(Integer.valueOf(coMode));
        estateInfoDto.setEstate(estateDto);
        EstateChangeDto estateChangeDto = new EstateChangeDto();
        estateChangeDto.setChangeType(String.valueOf(DictionaryConstants.Project_Change_Status));
        estateChangeDto.setChangeName("合作模式变更");
        estateChangeDto.setChangeDetail(coChangeReason);
        estateChangeDto.setChangeDate(new Date());
        estateChangeDto.setEstateId(id);
        estateChangeDto.setOperator(userInfo.getUserId());
        estateChangeDto.setOperatorCode(userInfo.getUserCode());
        estateChangeDto.setOperatorName(userInfo.getUserName());
        ArrayList<EstateChangeDto> ecList = new ArrayList<>();
        ecList.add(estateChangeDto);
        estateInfoDto.setEstateChangeDetails(ecList);
        post(url, estateInfoDto);
    }
    /** 
     * @Title: changeStatusMode
     * @Description: 销售状态变更
     */
    public void changeStatusMode(Map<String, Object> reqMap)
    		throws Exception
    {
    	String url = SystemParam.getWebConfigValue("RestServer") + "estate/update";
    	EstateInfoDto estateInfoDto = new EstateInfoDto();
    	EstateDto estateDto = new EstateDto();
    	Integer id = Integer.valueOf(reqMap.get("id").toString());
    	//销售状态
    	Integer salesStatus = Integer.valueOf(reqMap.get("salesStatus").toString());
    	//实际开盘时间
    	String realOpenTime = (String) reqMap.get("realOpenTime");
    	
    	estateDto.setId(id);
    	UserInfo userInfo = UserInfoHolder.get();
    	estateDto.setUptEmpId(userInfo.getUserId());
    	estateDto.setSalesStatus(salesStatus);
    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	
		if (StringUtil.isNotEmpty(realOpenTime)) {
			estateDto.setRealOpenTime(format.parse(realOpenTime));
		}
    	estateInfoDto.setEstate(estateDto);
    	EstateChangeDto estateChangeDto = new EstateChangeDto();
    	estateChangeDto.setChangeType(String.valueOf(DictionaryConstants.Project_Change_Status));
    	estateChangeDto.setChangeName("销售状态变更");
    	estateChangeDto.setChangeDetail("销售状态由&quot;待售&quot;变为&quot;在售&quot;，"+"实际开盘日期为: "+realOpenTime);
    	estateChangeDto.setChangeDate(new Date());
    	estateChangeDto.setEstateId(id);
    	estateChangeDto.setOperator(userInfo.getUserId());
    	estateChangeDto.setOperatorCode(userInfo.getUserCode());
    	estateChangeDto.setOperatorName(userInfo.getUserName());
    	ArrayList<EstateChangeDto> ecList = new ArrayList<>();
    	ecList.add(estateChangeDto);
    	estateInfoDto.setEstateChangeDetails(ecList);
    	post(url, estateInfoDto);
    }
    /** 
     * @Title: changeStatusMode
     * @Description: 项目发布城市变更
     */
    public void releaseCity(Map<String, Object> reqMap)
    		throws Exception
    {
    	String url = SystemParam.getWebConfigValue("RestServer") + "estate/updateReleaseCity";
    	EstateInfoDto estateInfoDto = new EstateInfoDto();
    	
    	Integer id = Integer.valueOf(reqMap.get("id").toString());
    	String estateId = reqMap.get("estateId").toString();
    	String relseaseCityNo = reqMap.get("relseaseCityNo").toString();
    	
    	EstateReleaseCityDto releaseCityDto = new EstateReleaseCityDto();
    	releaseCityDto.setCityNo(relseaseCityNo);
    	releaseCityDto.setDelflag("0");
    	releaseCityDto.setEstateId(estateId);
    	releaseCityDto.setUserCreate(UserInfoHolder.get().getUserId());
    	releaseCityDto.setDateCreate(new Date());
    	
    	ArrayList<EstateReleaseCityDto> ecList = new ArrayList<>();
    	ecList.add(releaseCityDto);
    	estateInfoDto.setEstateReleaseCityDto(ecList);
    	post(url, estateInfoDto);
    }
    
    
    /**********************************private function*******************************/
    /** 
    * @Title: SetNewEstateInfoDto 
    * @Description: 新增时的转换
    * @param reqMap
    * @return     
    * @throws Exception 
    */
    private EstateInfoDto SetNewEstateInfoDto(Map<String, Object> reqMap,EstatePhotosDto estatePhotosDto)
        throws Exception
    {
        UserInfo userInfo = UserInfoHolder.get();
        EstateInfoDto estateInfoDto = new EstateInfoDto();
        //楼盘信息
        EstateDto estateDto = new EstateDto();
        String accountProjectNo  = "";
        String accountProject = "";
        if(reqMap.containsKey("lnkAccountProjectCode")){
        	accountProjectNo = reqMap.get("lnkAccountProjectCode").toString();
        	accountProject = reqMap.get("lnkAccountProject").toString();
        }
        String auditStatusStr = (String)reqMap.get("auditStatus");//审核状态
        String projectStatus = (String)reqMap.get("projectStatus");//项目状态
        Integer releaseStatus = DictionaryConstants.ESTATE_PUBLISH_NO;
        String estateNm = (String)reqMap.get("estateNm");//楼盘名
        String cityNo = (String)reqMap.get("cityNo");//城市
        String realityCityNo = (String)reqMap.get("realityCityNo");//城市
        String districtId = (String)reqMap.get("districtId");//区域
        String areaId = (String)reqMap.get("areaId");//板块
        String address = (String)reqMap.get("address");//地址
        String salesStatusStr = (String)reqMap.get("salesStatus");//销售状态
        String salePriceUnitStr = (String)reqMap.get("salePriceUnit");//均价
        String salePriceUnitMinStr = (String)reqMap.get("salePriceUnitMin");//总价段开始
        String salePriceUnitMaxStr = (String)reqMap.get("salePriceUnitMax");//总价段结束
        String mark = this.getMark(reqMap);//标签
        String openTime = (String)reqMap.get("openTime");//开盘时间
        String houseTransferTime = (String)reqMap.get("houseTransferTime");//交房时间
        String partner = (String)reqMap.get("partner");//合作方
        String partnerNm = (String)reqMap.get("partnerNm");//合作人
        String sceneDeptId = (String)reqMap.get("sceneDeptId");//案场归属部门
        String sceneEmpId = (String)reqMap.get("sceneEmpId");//案场归属人
        String cooperationDtStart = (String)reqMap.get("cooperationDtStart");//合作期自
        String cooperationDtEnd = (String)reqMap.get("cooperationDtEnd");//合作期至
        String projectDescription = (String)reqMap.get("projectDescription");//项目简介
        String particularFlag = (String)reqMap.get("particularFlag");//独家

        // 业务模式
        String businessModel = (String ) reqMap.get("businessModel");
        estateDto.setBusinessModel(Integer.valueOf(businessModel));

        // 预计当年大定金额
        String subscribedThisYearStr = (String)reqMap.get("subscribedThisYear");
        if (StringUtil.isNotEmpty(subscribedThisYearStr))
        {
            BigDecimal subscribedThisYear = new BigDecimal(subscribedThisYearStr);
            estateDto.setSubscribedThisYear(subscribedThisYear);
        }
        // 预计明年大定金额
        String subscribedNextYearStr = (String)reqMap.get("subscribedNextYear");
        if (StringUtil.isNotEmpty(subscribedNextYearStr))
        {
            BigDecimal subscribedNextYear = new BigDecimal(subscribedNextYearStr);
            estateDto.setSubscribedNextYear(subscribedNextYear);
        }


        //楼盘详情
        String bigCustomerFlag = (String)reqMap.get("bigCustomerFlag");//大客户标识
        String fieldAddress = (String)reqMap.get("fieldAddress");//案场地址
        String preSalePermitKbnStr = (String)reqMap.get("preSalePermitKbn");//预售许可
        String mgtKbn = (String)reqMap.get("mgtKbn");//物业类型
        String ownYearKbn = (String)reqMap.get("ownYearKbn");//产权年限
        String decorationKbn = (String)reqMap.get("decorationKbn");//装修情况
        String typeKbn = (String)reqMap.get("typeKbn");//建筑类型
        String houseCntStr = (String)reqMap.get("houseCnt");//规划户数
        String parkCntStr = (String)reqMap.get("parkCnt");//车位情况
        String parkFeeStr = (String)reqMap.get("parkFee");//停车费
        String staircaseHousehold = this.getHouseStair(reqMap);//梯户
        String mgtCompany = (String)reqMap.get("mgtCompany");//物业公司
        String rateFAR = (String)reqMap.get("rateFAR");//容积率
        String rateGreen = (String)reqMap.get("rateGreen");//绿化率
        String mgtPriceStr = (String)reqMap.get("mgtPrice");//物业费用
        String heatKbnStr = (String)reqMap.get("heatKbn");//供暖方式
        String hydropowerGasKbnStr = (String)reqMap.get("hydropowerGasKbn");//水电燃气
        String cooperationMode = (String) reqMap.get("cooperationMode");  //合作模式
        String projectDepartmentNo = (String) reqMap.get("projectDepartmentId");  //归属项目部
        String devCompanyBroker = (String) reqMap.get("devCompanyBroker");  //开发商对接人
        String devCompanyBrokerTel = (String) reqMap.get("devCompanyBrokerTel"); //开发商对接人电话
        String empTel = (String) reqMap.get("empTel");   //项目负责人电话
        String cityName = (String) reqMap.get("cityName");
        String typeCode = "";
        Boolean addEstateManual = null;
        ResultData<?> citySetting = commonService.getCitySettingByCityNo(cityNo);
        if (citySetting != null && citySetting.getReturnData() != null){
        	Map<?, ?> csMap = (Map<?, ?>) citySetting.getReturnData();
        	typeCode = csMap.get("linkTypeCode").toString();
            addEstateManual = Boolean.valueOf(csMap.get("addEstateManual").toString());
        }
        //设置核算主体
        if(!"".equals(accountProjectNo)) {
        	estateDto.setAccountProject(accountProject);
        	estateDto.setAccountProjectNo(accountProjectNo);
        }
        //是否纯垫佣
        estateDto.setPureDy(addEstateManual);
        //项目编号
        String projectNo = "";
        ResultData<?> raback = this.seqNoService.getSeqNo(typeCode);
        projectNo = (String)raback.getReturnData();
        estateDto.setProjectNo(projectNo);
        //楼盘编号
        String estateId = "";
        
        ResultData<?> resultData = this.seqNoService.getSeqNo("TYPE_ESTATE");
        estateId = (String)resultData.getReturnData();
        estateDto.setEstateId(estateId);
        
        estateDto.setPartnerContactNm((String) reqMap.get("partnerContactNm"));
        estateDto.setPartnerContactTel((String) reqMap.get("partnerContactTel"));
        
        if(StringUtil.isNotEmpty(reqMap.get("opEstateId").toString())) {
            estateDto.setOpEstateId(reqMap.get("opEstateId").toString());
        }
        if(StringUtil.isNotEmpty(reqMap.get("opEstateNm").toString())) {
            estateDto.setOpEstateNm(reqMap.get("opEstateNm").toString());
        }
        
        estateDto.setEmpTel(empTel);
        estateDto.setDevCompanyBroker(devCompanyBroker);
        estateDto.setDevCompanyBrokerTel(devCompanyBrokerTel);
        if (null != projectDepartmentNo && StringUtil.isNotEmpty(projectDepartmentNo))
        {
            estateDto.setProjectDepartmentId(Integer.valueOf(projectDepartmentNo));
        }
        if (null != cooperationMode && StringUtil.isNotEmpty(cooperationMode))
        {
            estateDto.setCooperationMode(Integer.valueOf(cooperationMode));
        }
        if (StringUtil.isNotEmpty(auditStatusStr))
        {
            Integer auditStatus = Integer.valueOf(auditStatusStr);
            estateDto.setAuditStatus(auditStatus);
        }
        estateDto.setReleaseStatus(releaseStatus);
        estateDto.setEstateNm(estateNm);
        estateDto.setCityNo(cityNo);
        estateDto.setRealityCityNo(realityCityNo);
        estateDto.setDistrictId(districtId);
        estateDto.setAreaId(areaId);
        estateDto.setAddress(address.trim());
        if (StringUtil.isNotEmpty(salesStatusStr))
        {
            Integer salesStatus = Integer.valueOf(salesStatusStr);
            estateDto.setSalesStatus(salesStatus);
        }
        //独家
        if (StringUtil.isNotEmpty(particularFlag)) {
        	Integer particular = Integer.valueOf(particularFlag);
        	estateDto.setParticularFlag(particular);
        }
        //是否为大客户
        if (StringUtil.isNotEmpty(bigCustomerFlag)) {
        	String devCompany ="";
        	if("22601".equals(bigCustomerFlag)){
                String bigCustomerIdStr = reqMap.get("bigCustomerId").toString();
                if(StringUtil.isNotEmpty(bigCustomerIdStr)){
                    Integer bigCustomerId = Integer.valueOf(bigCustomerIdStr);//大客户id
                    estateDto.setBigCustomerId(bigCustomerId);
                }
        		if (reqMap.containsKey("directSignFlag")){
        			 if (StringUtil.isNotEmpty(reqMap.get("directSignFlag").toString())) {
        				 Integer directSignFlag = Integer.valueOf(reqMap.get("directSignFlag").toString());//直签
        				 estateDto.setDirectSignFlag(directSignFlag);
        			 }
        		}
        		devCompany = (String)reqMap.get("devCompanyText");//开发商
        		estateDto.setDevCompany(devCompany);
        		estateDto.setDeveloperName((String)reqMap.get("developerNameBigYes"));//开发商全称
        	}
        	if("22602".equals(bigCustomerFlag)){
//        		devCompany = (String)reqMap.get("devCompany");//开发商
//        		if(StringUtil.isEmpty(devCompany)) {
//        			devCompany = (String)reqMap.get("developerNameBigNo");
//        		}
//        		estateDto.setDevCompany(devCompany);
                estateDto.setDevCompany("");
        		estateDto.setDeveloperName((String)reqMap.get("developerNameBigNo"));//开发商全称
        	}
        	Integer bigCustomer = Integer.valueOf(bigCustomerFlag);
        	estateDto.setBigCustomerFlag(bigCustomer);
        }
        if (StringUtil.isNotEmpty(salePriceUnitStr))
        {
            BigDecimal salePriceUnit = new BigDecimal(salePriceUnitStr);
            estateDto.setSalePriceUnit(salePriceUnit);
        }
        if (StringUtil.isNotEmpty(salePriceUnitMinStr))
        {
            BigDecimal salePriceUnitMin = new BigDecimal(salePriceUnitMinStr);
            estateDto.setSalePriceUnitMin(salePriceUnitMin);
        }
        if (StringUtil.isNotEmpty(salePriceUnitMaxStr))
        {
            BigDecimal salePriceUnitMax = new BigDecimal(salePriceUnitMaxStr);
            estateDto.setSalePriceUnitMax(salePriceUnitMax);
        }
        estateDto.setMark(mark);
        estateDto.setOpenTime(this.stringToDate(openTime));
        if (StringUtil.isNotEmpty(houseTransferTime))
        {
            Date houseTransferDate = this.stringToDate(houseTransferTime);
            houseTransferTime = new SimpleDateFormat("yyyy-MM").format(houseTransferDate);
            estateDto.setHouseTransferTime(houseTransferTime);
        }
        if (null != partner && StringUtil.isNotEmpty(partner))
        {
            Integer partnerId = Integer.valueOf(partner);
            estateDto.setPartner(partnerId);
        }
        estateDto.setPartnerNm(partnerNm.trim());
        if (null != sceneDeptId && StringUtil.isNotEmpty(sceneDeptId))
        {
            estateDto.setSceneDeptId(Integer.valueOf(sceneDeptId));
        }
        if (null != sceneEmpId && StringUtil.isNotEmpty(sceneEmpId))
        {
            estateDto.setSceneEmpId(Integer.valueOf(sceneEmpId));
        }
        estateDto.setCooperationDtStart(this.stringToDate(cooperationDtStart));
        estateDto.setCooperationDtEnd(this.stringToDate(cooperationDtEnd));
        estateDto.setProjectDescription(projectDescription);
        estateDto.setFieldAddress(fieldAddress);
        if (StringUtil.isNotEmpty(preSalePermitKbnStr))
        {
            Integer preSalePermitKbn = Integer.valueOf(preSalePermitKbnStr);
            estateDto.setPreSalePermitKbn(preSalePermitKbn);
        }
        estateDto.setMgtKbn(mgtKbn);
        estateDto.setOwnYearKbn(ownYearKbn);
        estateDto.setDecorationKbn(decorationKbn);
        estateDto.setTypeKbn(typeKbn);
        Integer houseCnt = null;
        if (StringUtil.isNotEmpty(houseCntStr))
        {
            houseCnt = Integer.valueOf(houseCntStr);
        }
        estateDto.setHouseCnt(houseCnt);
        Integer parkCnt = null;
        if (StringUtil.isNotEmpty(parkCntStr))
        {
            parkCnt = Integer.valueOf(parkCntStr);
        }
        estateDto.setParkCnt(parkCnt);
        if (StringUtil.isNotEmpty(parkFeeStr))
        {
            BigDecimal parkFee = new BigDecimal(parkFeeStr);
            estateDto.setParkFee(parkFee);
        }
        estateDto.setStaircaseHousehold(staircaseHousehold);
        estateDto.setMgtCompany(mgtCompany);
        estateDto.setRateFAR(rateFAR);
        estateDto.setRateGreen(rateGreen);
        if (StringUtil.isNotEmpty(mgtPriceStr))
        {
            BigDecimal mgtPrice = new BigDecimal(mgtPriceStr);
            estateDto.setMgtPrice(mgtPrice);
        }
        Integer heatKbn = null;
        if (StringUtil.isNotEmpty(heatKbnStr))
        {
            heatKbn = Integer.valueOf(heatKbnStr);
        }
        estateDto.setHeatKbn(heatKbn);
        Integer hydropowerGasKbn = null;
        if (StringUtil.isNotEmpty(hydropowerGasKbnStr))
        {
            hydropowerGasKbn = Integer.valueOf(hydropowerGasKbnStr);
        }
        estateDto.setHydropowerGasKbn(hydropowerGasKbn);
        estateDto.setCrtDt(new Date());
        estateDto.setCrtEmpId(userInfo.getUserId());
        estateDto.setDelFlg(false);
        estateDto.setDeptId(Integer.valueOf(userInfo.getGroupId()));
        
        // 备案名
        String recordName = (String)reqMap.get("recordName");
        //推广名
        String promotionName = (String)reqMap.get("promotionName");
        //签约名
        String signName = (String)reqMap.get("signName");
        estateDto.setRecordName(recordName);
        estateDto.setPromotionName(promotionName);
        estateDto.setSignName(signName);
        estateDto.setProjectStatus(Integer.valueOf(projectStatus));
        estateDto.setEstatePosition(Integer.valueOf(reqMap.get("estatePosition").toString()));
        estateDto.setCountryNo(reqMap.get("countryNo").toString());
        //是否垫佣甲方
        Integer custodyFlgStr = Integer.valueOf(reqMap.get("custodyFlg").toString());
        estateDto.setCustodyFlg(custodyFlgStr);
        if(custodyFlgStr == 1) {//是   垫佣甲方简称
        	String bigCustomerIdStr = reqMap.get("mattressNailId").toString();
            if(StringUtil.isNotEmpty(bigCustomerIdStr)){
                Integer bigCustomerId = Integer.valueOf(bigCustomerIdStr);
                estateDto.setMattressNailId(bigCustomerId);//垫佣甲方简称
            }
    	}
        estateInfoDto.setEstate(estateDto);
        
        //联动规则
        List<EstateRuleDto> estateRuleDtos = new ArrayList<>();
        EstateRuleDto estateRuleDto = new EstateRuleDto();
        String authenticationKbnStr = (String)reqMap.get("authenticationKbn");//认证类型
        String advanceReportHH = (String)reqMap.get("advanceReportHH");//提前报备期(时)
        String advanceReportMM = (String)reqMap.get("advanceReportMM");//提前报备期(分)
        String relationProtectPeriodStr = (String)reqMap.get("relationProtectPeriod");//带看保护期
        String relationRewardStr = (String)reqMap.get("relationReward");//带看奖励
        String relationDtStart = (String)reqMap.get("relationDtStart");//带看起始日期
        String relationDtEnd = (String)reqMap.get("relationDtEnd");//带看截止日期
        String pledgedRewardStr = (String)reqMap.get("pledgedReward");//认筹奖励
        String pledgedDtStart = (String)reqMap.get("pledgedDtStart");//认筹起始日期
        String pledgedDtEnd = (String)reqMap.get("pledgedDtEnd");//认筹截止日期
        String subscribedRewardStr = (String)reqMap.get("subscribedReward");//认购奖励
        String subscribedDtStart = (String)reqMap.get("subscribedDtStart");//认购起始日期
        String subscribedDtEnd = (String)reqMap.get("subscribedDtEnd");//认购截止日期
        String bargainRewardStr = (String)reqMap.get("bargainReward");//成交奖励
        String commissionPeriodStr = (String)reqMap.get("commissionPeriod");//结佣期限
        String commissionKbnStr = (String)reqMap.get("commissionKbn");//佣金方式
        String commissionStr = (String)reqMap.get("commission");//佣金
        String commissionMemo = (String)reqMap.get("commissionMemo");//收入结算描述
        String commissionCondition = (String)reqMap.get("commissionCondition");//收入结算条件
        String payKbnStr = (String)reqMap.get("payKbn");//结佣方式
        String saleKbnStr = (String)reqMap.get("saleKbn");//销售方式
        String reportDtStart = (String)reqMap.get("reportDtStart");//报备开始日
        String reportDtEnd = (String)reqMap.get("reportDtEnd");//报备截止日
        String reportKbnStr = (String)reqMap.get("reportKbn");//报备模式
        String hideNumberFlg = (String)reqMap.get("hideNumberFlg");//隐号报备
        String reportRule = (String)reqMap.get("reportRule");//报备规则
        String commissionRule = (String)reqMap.get("commissionRule");//结佣规则
        String rtnCommission = (String) reqMap.get("rtnCommission");  //返佣标准
        String rtnCommissionMemo = (String) reqMap.get("rtnCommissionMemo"); //返佣结算条件
        String incomeSubject = (String) reqMap.get("incomeSubject");  //收入标的
        String ruleId = "";
        
        ResultData<?> resultDataRuleId = this.seqNoService.getSeqNo("TYPE_ESTATERULE");
        ruleId = (String)resultDataRuleId.getReturnData();
        estateRuleDto.setRuleId(ruleId);
        
        estateRuleDto.setIncomeSubject(incomeSubject);
        estateRuleDto.setRtnCommission(rtnCommission);
        estateRuleDto.setRtnCommissionMemo(rtnCommissionMemo);
        Integer authenticationKbn = null;
        if (StringUtil.isNotEmpty(authenticationKbnStr))
        {
            authenticationKbn = Integer.valueOf(authenticationKbnStr);
            estateRuleDto.setAuthenticationKbn(authenticationKbn);
        }
        estateRuleDto.setAdvanceReportHH(advanceReportHH);
        estateRuleDto.setAdvanceReportMM(advanceReportMM);
        if (StringUtil.isNotEmpty(relationProtectPeriodStr))
        {
            BigDecimal relationProtectPeriod = new BigDecimal(relationProtectPeriodStr);
            estateRuleDto.setRelationProtectPeriod(relationProtectPeriod);
        }
        if (StringUtil.isNotEmpty(relationRewardStr))
        {
            BigDecimal relationReward = new BigDecimal(relationRewardStr);
            estateRuleDto.setRelationReward(relationReward);
        }
        estateRuleDto.setRelationDtStart(this.stringToDate(relationDtStart));
        estateRuleDto.setRelationDtEnd(this.stringToDate(relationDtEnd));
        if (StringUtil.isNotEmpty(pledgedRewardStr))
        {
            BigDecimal pledgedReward = new BigDecimal(pledgedRewardStr);
            estateRuleDto.setPledgedReward(pledgedReward);
        }
        estateRuleDto.setPledgedDtStart(this.stringToDate(pledgedDtStart));
        estateRuleDto.setPledgedDtEnd(this.stringToDate(pledgedDtEnd));
        if (StringUtil.isNotEmpty(subscribedRewardStr))
        {
            BigDecimal subscribedReward = new BigDecimal(subscribedRewardStr);
            estateRuleDto.setSubscribedReward(subscribedReward);
        }
        estateRuleDto.setSubscribedDtStart(this.stringToDate(subscribedDtStart));
        estateRuleDto.setSubscribedDtEnd(this.stringToDate(subscribedDtEnd));
        if (StringUtil.isNotEmpty(bargainRewardStr))
        {
            BigDecimal bargainReward = new BigDecimal(bargainRewardStr);
            estateRuleDto.setBargainReward(bargainReward);
        }
        if (StringUtil.isNotEmpty(commissionPeriodStr))
        {
            BigDecimal commissionPeriod = new BigDecimal(commissionPeriodStr);
            estateRuleDto.setCommissionPeriod(commissionPeriod);
        }
        Integer commissionKbn = null;
        if (StringUtil.isNotEmpty(commissionKbnStr))
        {
            commissionKbn = Integer.valueOf(commissionKbnStr);
            estateRuleDto.setCommissionKbn(commissionKbn);
        }
        if (StringUtil.isNotEmpty(commissionStr))
        {
            BigDecimal commission = new BigDecimal(commissionStr);
            estateRuleDto.setCommission(commission);
        }
        estateRuleDto.setCommissionMemo(commissionMemo);
        //结算收入条件
        if(StringUtils.isNotBlank(commissionCondition)){
        	estateRuleDto.setCommissionCondition(Integer.valueOf(commissionCondition));
        }
        Integer payKbn = null;
        if (StringUtil.isNotEmpty(payKbnStr))
        {
            payKbn = Integer.valueOf(payKbnStr);
            estateRuleDto.setPayKbn(payKbn);
        }
        Integer saleKbn = null;
        if (StringUtil.isNotEmpty(saleKbnStr))
        {
            saleKbn = Integer.valueOf(saleKbnStr);
            estateRuleDto.setSaleKbn(saleKbn);
        }
        estateRuleDto.setReportDtStart(this.stringToDate(reportDtStart));
        estateRuleDto.setReportDtEnd(this.stringToDate(reportDtEnd));
        Integer reportKbn = null;
        if (StringUtil.isNotEmpty(reportKbnStr))
        {
            reportKbn = Integer.valueOf(reportKbnStr);
            estateRuleDto.setReportKbn(reportKbn);
        }
        estateRuleDto.setHideNumberFlg(hideNumberFlg);
        estateRuleDto.setReportRule(reportRule);
        estateRuleDto.setCommissionRule(commissionRule);
        estateRuleDto.setEstateId(estateId);
        estateRuleDto.setCrtDt(new Date());
        estateRuleDto.setCrtEmpId(userInfo.getUserId());
        estateRuleDto.setDelFlg(false);
        estateRuleDtos.add(estateRuleDto);
        estateInfoDto.setEstateRuleDetails(estateRuleDtos);
        
        //在售户型
        List<EstateTypeDto> estateTypeDtos = new ArrayList<>();
        EstateTypeDto estateTypeDto = new EstateTypeDto();
        String countFlgStr = (String)reqMap.get("countFlg");//是否是主力户型
        String countF = (String)reqMap.get("countF");//房型（房）
        String countT = (String)reqMap.get("countT");//房型（厅）
        String countW = (String)reqMap.get("countW");//房型（卫）
        String buildSquareStr = (String)reqMap.get("buildSquare");//面积（建筑）
        String directionKbnStr = (String)reqMap.get("directionKbn");//朝向
        String label = this.getLabel(reqMap);//户型标签
        //户型编号
        String typeId = "";
        ResultData<?> resultDataTypeId = this.seqNoService.getSeqNo("TYPE_ESTATETYPE");
        typeId = (String)resultDataTypeId.getReturnData();
        estateTypeDto.setTypeId(typeId);
        Integer countFlg = null;
        if (StringUtil.isNotEmpty(countFlgStr))
        {
            countFlg = Integer.valueOf(countFlgStr);
            estateTypeDto.setCountFlg(countFlg);
        }
        estateTypeDto.setCountF(countF);
        estateTypeDto.setCountT(countT);
        estateTypeDto.setCountW(countW);
        if (StringUtil.isNotEmpty(buildSquareStr))
        {
            BigDecimal buildSquare = new BigDecimal(buildSquareStr);
            estateTypeDto.setBuildSquare(buildSquare);
        }
        Integer directionKbn = null;
        if (StringUtil.isNotEmpty(directionKbnStr))
        {
            directionKbn = Integer.valueOf(directionKbnStr);
            estateTypeDto.setDirectionKbn(directionKbn);
        }
        estateTypeDto.setLabel(label);
        estateTypeDto.setEstateId(estateId);
        estateTypeDto.setCrtDt(new Date());
        estateTypeDto.setCrtEmpId(userInfo.getUserId());
        estateTypeDto.setDelFlg(false);
        estateTypeDtos.add(estateTypeDto);
        
        //图片List
        List<PhotoDto> filePhoto = new ArrayList<>();
        String houseCoverImg = (String)reqMap.get("houseCoverImg");//户型封面图片No
        String estateCoverImg = (String)reqMap.get("estateCoverImg");//楼盘封面图片No
        //在售户型
        getHousePhotoDtos(filePhoto,estatePhotosDto.getHouseTypePhotos(),estatePhotosDto.getTempletPhotos(),houseCoverImg, estateId, typeId);
        //楼盘相册
        getEstatePhotoDtos(filePhoto,estatePhotosDto.getPhoto1(),estatePhotosDto.getPhoto2(),estatePhotosDto.getPhoto3(),estatePhotosDto.getPhoto4(),estatePhotosDto.getPhoto5(),estateCoverImg,estateId,null);
        //循环添加多户型，及图片
        for (int i = 8; i < 12; i++)
        {
            if (StringUtil.isEmpty((String)reqMap.get("countF" + i)))
            {
                continue;
            }
            else
            {
                //在售户型
                EstateTypeDto estateTypeDtoi = new EstateTypeDto();
                String countFlgStri = (String)reqMap.get("countFlg" + i);//是否是主力户型
                String countFi = (String)reqMap.get("countF" + i);//房型（房）
                String countTi = (String)reqMap.get("countT" + i);//房型（厅）
                String countWi = (String)reqMap.get("countW" + i);//房型（卫）
                String buildSquareStri = (String)reqMap.get("buildSquare" + i);//面积（建筑）
                String directionKbnStri = (String)reqMap.get("directionKbn" + i);//朝向
                //户型编号
                String typeIdi = "";
                ResultData<?> resultDataTypeIdi = this.seqNoService.getSeqNo("TYPE_ESTATETYPE");
                typeIdi = (String)resultDataTypeIdi.getReturnData();
                estateTypeDtoi.setTypeId(typeIdi);
                Integer countFlgi = null;
                if (StringUtil.isNotEmpty(countFlgStri))
                {
                    countFlgi = Integer.valueOf(countFlgStri);
                    estateTypeDtoi.setCountFlg(countFlgi);
                }
                estateTypeDtoi.setCountF(countFi);
                estateTypeDtoi.setCountT(countTi);
                estateTypeDtoi.setCountW(countWi);
                if (StringUtil.isNotEmpty(buildSquareStri))
                {
                    BigDecimal buildSquarei = new BigDecimal(buildSquareStri);
                    estateTypeDtoi.setBuildSquare(buildSquarei);
                }
                Integer directionKbni = null;
                if (StringUtil.isNotEmpty(directionKbnStri))
                {
                    directionKbni = Integer.valueOf(directionKbnStri);
                    estateTypeDtoi.setDirectionKbn(directionKbni);
                }
                estateTypeDtoi.setEstateId(estateId);
                estateTypeDtoi.setCrtDt(new Date());
                estateTypeDtoi.setCrtEmpId(userInfo.getUserId());
                estateTypeDtoi.setDelFlg(false);
                estateTypeDtos.add(estateTypeDtoi);
                
                //添加多户型图片到List
                switch (i){
                    case 8 :
                        getHousePhotoDtos(filePhoto,estatePhotosDto.getHouseTypePhotos8(),estatePhotosDto.getTempletPhotos81(),houseCoverImg, estateId, typeId);
                        break;
                    case 9 :
                        getHousePhotoDtos(filePhoto,estatePhotosDto.getHouseTypePhotos9(),estatePhotosDto.getTempletPhotos91(),houseCoverImg, estateId, typeId);
                        break;
                    case 10 :
                        getHousePhotoDtos(filePhoto,estatePhotosDto.getHouseTypePhotos10(),estatePhotosDto.getTempletPhotos101(),houseCoverImg, estateId, typeId);
                        break;
                    case 11 :
                        getHousePhotoDtos(filePhoto,estatePhotosDto.getHouseTypePhotos11(),estatePhotosDto.getTempletPhotos111(),houseCoverImg, estateId, typeId);
                        break;
                    default:
                        break;
                }

            }
        }
        
        //添加户型数据    
        estateInfoDto.setEstateTypeDetails(estateTypeDtos);
        
        //添加图片数据
        if (null != filePhoto && !filePhoto.isEmpty())
        {
            estateInfoDto.setFilePhoto(filePhoto);
        }
        //周边配套
        List<EstateSupportDto> estateSupportDetails = this.getSupportDtos(reqMap, estateId);
        if (null != estateSupportDetails && !estateSupportDetails.isEmpty())
        {
            estateInfoDto.setEstateSupportDetails(estateSupportDetails);
        }
        return estateInfoDto;
        
    }
    
    /** 
    * @Title: SetEditEstateInfoDto 
    * @Description: 楼盘编辑对象
    * @param reqMap
    * @return     
     * @throws Exception 
    */
    private EstateInfoDto SetEditEstateInfoDto(Map<String, Object> reqMap,EstatePhotosDto estatePhotosDto)
        throws Exception
    {
        UserInfo userInfo = UserInfoHolder.get();
        EstateInfoDto estateInfoDto = new EstateInfoDto();
        //楼盘信息
        EstateDto estateDto = new EstateDto();
        String accountProjectNo  = "";
        String accountProject = "";
        if(reqMap.containsKey("lnkAccountProjectCode")){
        	accountProjectNo = reqMap.get("lnkAccountProjectCode").toString();
        	accountProject = reqMap.get("lnkAccountProject").toString();
        }
        String estId = (String)reqMap.get("estId");//楼盘自增编号
        String estateId = (String)reqMap.get("estateId");//楼盘编号
        String auditStatusStr = (String)reqMap.get("auditStatus");//楼盘审核状态
        String estateNm = (String)reqMap.get("estateNm");//楼盘名
        String cityNo = (String)reqMap.get("cityNo");//城市
        String realityCityNo = (String)reqMap.get("realityCityNo");//项目所在地
        String districtId = (String)reqMap.get("districtId");//区域
        String areaId = (String)reqMap.get("areaId");//板块
        String address = (String)reqMap.get("address");//地址
        String salesStatusStr = (String)reqMap.get("salesStatus");//销售状态
        String salePriceUnitStr = (String)reqMap.get("salePriceUnit");//均价
        String salePriceUnitMinStr = (String)reqMap.get("salePriceUnitMin");//总价段开始
        String salePriceUnitMaxStr = (String)reqMap.get("salePriceUnitMax");//总价段结束
        String mark = this.getMark(reqMap);//标签
        String openTime = (String)reqMap.get("openTime");//开盘时间
        String houseTransferTime = (String)reqMap.get("houseTransferTime");//交房时间
        String partner = (String)reqMap.get("partner");//合作方
        String partnerNm = (String)reqMap.get("partnerNm");//合作人
        String sceneDeptId = (String)reqMap.get("sceneDeptId");//案场归属部门
        String sceneEmpId = (String)reqMap.get("sceneEmpId");//案场归属人
        String cooperationDtStart = (String)reqMap.get("cooperationDtStart");//合作期自
        String cooperationDtEnd = (String)reqMap.get("cooperationDtEnd");//合作期至
        String projectDescription = (String)reqMap.get("projectDescription");//项目简介
        String particularFlag = (String)reqMap.get("particularFlag");//独家
        String custodyFlg = (String)reqMap.get("custodyFlg");//是否可垫佣甲方
        //楼盘详情
        String bigCustomerFlag = (String)reqMap.get("bigCustomerFlag");//大客户标识
        String fieldAddress = (String)reqMap.get("fieldAddress");//案场地址
        String preSalePermitKbnStr = (String)reqMap.get("preSalePermitKbn");//预售许可
        String mgtKbn = (String)reqMap.get("mgtKbn");//物业类型
        String ownYearKbn = (String)reqMap.get("ownYearKbn");//产权年限
        String decorationKbn = (String)reqMap.get("decorationKbn");//装修情况
        String typeKbn = (String)reqMap.get("typeKbn");//建筑类型
        String houseCntStr = (String)reqMap.get("houseCnt");//规划户数
        String parkCntStr = (String)reqMap.get("parkCnt");//车位情况
        String parkFeeStr = (String)reqMap.get("parkFee");//停车费
        String staircaseHousehold = this.getHouseStair(reqMap);//梯户
        String mgtCompany = (String)reqMap.get("mgtCompany");//物业公司
        String rateFAR = (String)reqMap.get("rateFAR");//容积率
        String rateGreen = (String)reqMap.get("rateGreen");//绿化率
        String mgtPriceStr = (String)reqMap.get("mgtPrice");//物业费用
        String heatKbnStr = (String)reqMap.get("heatKbn");//供暖方式
        String hydropowerGasKbnStr = (String)reqMap.get("hydropowerGasKbn");//水电燃气
        String projectDepartmentNo = (String) reqMap.get("projectDepartmentId");//归属项目部
        String cooperationMode = (String) reqMap.get("cooperationMode");  //合作模式
        String devCompanyBroker = (String) reqMap.get("devCompanyBroker");  //开发商对接人
        String devCompanyBrokerTel = (String) reqMap.get("devCompanyBrokerTel"); //开发商对接人电话
        String empTel = (String) reqMap.get("empTel");   //项目负责人电话

        // 业务模式
        String businessModel = (String ) reqMap.get("businessModel");
        estateDto.setBusinessModel(Integer.valueOf(businessModel));

        // 预计当年大定金额
        String subscribedThisYearStr = (String)reqMap.get("subscribedThisYear");
        if (StringUtil.isNotEmpty(subscribedThisYearStr))
        {
            BigDecimal subscribedThisYear = new BigDecimal(subscribedThisYearStr);
            estateDto.setSubscribedThisYear(subscribedThisYear);
        }
        // 预计明年大定金额
        String subscribedNextYearStr = (String)reqMap.get("subscribedNextYear");
        if (StringUtil.isNotEmpty(subscribedNextYearStr))
        {
            BigDecimal subscribedNextYear = new BigDecimal(subscribedNextYearStr);
            estateDto.setSubscribedNextYear(subscribedNextYear);
        }

        estateDto.setId(Integer.valueOf(estId));
        estateDto.setEstateId(estateId);
        
        //设置核算主体
        if(!"".equals(accountProjectNo)) {
        	estateDto.setAccountProject(accountProject);
        	estateDto.setAccountProjectNo(accountProjectNo);
        }
        estateDto.setEmpTel(empTel);
        estateDto.setDevCompanyBroker(devCompanyBroker);
        estateDto.setDevCompanyBrokerTel(devCompanyBrokerTel);
        if(StringUtil.isNotEmpty(projectDepartmentNo))
        {
        	 Integer projectDepartmentId = Integer.valueOf(projectDepartmentNo);
        	 estateDto.setProjectDepartmentId(projectDepartmentId);
        }
        if (StringUtil.isNotEmpty(auditStatusStr))
        {
            Integer auditStatus = Integer.valueOf(auditStatusStr);
            estateDto.setAuditStatus(auditStatus);
        }
        if (null != cooperationMode && StringUtil.isNotEmpty(cooperationMode))
        {
            estateDto.setCooperationMode(Integer.valueOf(cooperationMode));
        }
        
        estateDto.setPartnerContactNm((String) reqMap.get("partnerContactNm"));
        estateDto.setPartnerContactTel((String) reqMap.get("partnerContactTel"));
        
        estateDto.setEstateNm(estateNm);
        estateDto.setCityNo(cityNo);
        estateDto.setRealityCityNo(realityCityNo);
        estateDto.setDistrictId(districtId);
        estateDto.setAreaId(areaId);
        estateDto.setAddress(address.trim());
        if (StringUtil.isNotEmpty(salesStatusStr))
        {
            Integer salesStatus = Integer.valueOf(salesStatusStr);
            estateDto.setSalesStatus(salesStatus);
        }
      //独家
        if (StringUtil.isNotEmpty(particularFlag)) {
        	Integer particular = Integer.valueOf(particularFlag);
        	estateDto.setParticularFlag(particular);
        }
        //是否可垫佣甲方
        if (StringUtil.isNotEmpty(custodyFlg)) {
        	Integer custodyFlgStr = Integer.valueOf(custodyFlg);
        	estateDto.setCustodyFlg(custodyFlgStr);
        	if(custodyFlgStr == 1) {//是 垫佣甲方简称
        		String bigCustomerIdStr = reqMap.get("mattressNailId").toString();
                if(StringUtil.isNotEmpty(bigCustomerIdStr)){
                    Integer bigCustomerId = Integer.valueOf(bigCustomerIdStr);
                    estateDto.setMattressNailId(bigCustomerId);//垫佣甲方简称
                }
        	}
        }
        //是否为大客户
        if (StringUtil.isNotEmpty(bigCustomerFlag)) {
        	String devCompany ="";
        	if("22601".equals(bigCustomerFlag)){
                String bigCustomerIdStr = reqMap.get("bigCustomerId").toString();
                if(StringUtil.isNotEmpty(bigCustomerIdStr)){
                    Integer bigCustomerId = Integer.valueOf(bigCustomerIdStr);//大客户id
                    estateDto.setBigCustomerId(bigCustomerId);
                }
        		if(reqMap.containsKey("directSignFlag")){
        			if (StringUtil.isNotEmpty(reqMap.get("directSignFlag").toString())) {
        				Integer directSignFlag = Integer.valueOf(reqMap.get("directSignFlag").toString());//直签
        				estateDto.setDirectSignFlag(directSignFlag);
        			}
        		}
        		devCompany = (String)reqMap.get("devCompanyText");//开发商
        		estateDto.setDevCompany(devCompany);
        		estateDto.setDeveloperName((String)reqMap.get("developerNameBigYes"));//开发商全称
        		
        	}
        	if("22602".equals(bigCustomerFlag)){
//        		devCompany = (String)reqMap.get("devCompany");//开发商
//        		if(StringUtil.isEmpty(devCompany)) {
//        			devCompany = (String)reqMap.get("developerNameBigNo");
//        		}
//        		estateDto.setDevCompany(devCompany);
                estateDto.setDevCompany("");
        		estateDto.setDeveloperName((String)reqMap.get("developerNameBigNo"));//开发商全称
        	}
        	Integer bigCustomer = Integer.valueOf(bigCustomerFlag);
        	estateDto.setBigCustomerFlag(bigCustomer);
        }
        if (StringUtil.isNotEmpty(salePriceUnitStr))
        {
            BigDecimal salePriceUnit = new BigDecimal(salePriceUnitStr);
            estateDto.setSalePriceUnit(salePriceUnit);
        }
        if (StringUtil.isNotEmpty(salePriceUnitMinStr))
        {
            BigDecimal salePriceUnitMin = new BigDecimal(salePriceUnitMinStr);
            estateDto.setSalePriceUnitMin(salePriceUnitMin);
        }
        if (StringUtil.isNotEmpty(salePriceUnitMaxStr))
        {
            BigDecimal salePriceUnitMax = new BigDecimal(salePriceUnitMaxStr);
            estateDto.setSalePriceUnitMax(salePriceUnitMax);
        }
        estateDto.setMark(mark);
        estateDto.setOpenTime(this.stringToDate(openTime));
        if (StringUtil.isNotEmpty(houseTransferTime))
        {
            Date houseTransferDate = this.stringToDate(houseTransferTime);
            houseTransferTime = new SimpleDateFormat("yyyy-MM").format(houseTransferDate);
            estateDto.setHouseTransferTime(houseTransferTime);
        }
        if (null != partner && StringUtil.isNotEmpty(partner))
        {
            Integer partnerId = Integer.valueOf(partner);
            estateDto.setPartner(partnerId);
        }
        estateDto.setPartnerNm(partnerNm.trim());
        if (null != sceneDeptId && StringUtil.isNotEmpty(sceneDeptId))
        {
            estateDto.setSceneDeptId(Integer.valueOf(sceneDeptId));
        }
        if (null != sceneEmpId && StringUtil.isNotEmpty(sceneEmpId))
        {
            estateDto.setSceneEmpId(Integer.valueOf(sceneEmpId));
        }
        estateDto.setCooperationDtStart(this.stringToDate(cooperationDtStart));
        estateDto.setCooperationDtEnd(this.stringToDate(cooperationDtEnd));
        estateDto.setProjectDescription(projectDescription);
        //estateDto.setDevCompany(devCompany);
        estateDto.setFieldAddress(fieldAddress);
        if (StringUtil.isNotEmpty(preSalePermitKbnStr))
        {
            Integer preSalePermitKbn = Integer.valueOf(preSalePermitKbnStr);
            estateDto.setPreSalePermitKbn(preSalePermitKbn);
        }
        estateDto.setMgtKbn(mgtKbn);
        estateDto.setOwnYearKbn(ownYearKbn);
        estateDto.setDecorationKbn(decorationKbn);
        estateDto.setTypeKbn(typeKbn);
        Integer houseCnt = null;
        if (StringUtil.isNotEmpty(houseCntStr))
        {
            houseCnt = Integer.valueOf(houseCntStr);
        }
        estateDto.setHouseCnt(houseCnt);
        Integer parkCnt = null;
        if (StringUtil.isNotEmpty(parkCntStr))
        {
            parkCnt = Integer.valueOf(parkCntStr);
        }
        estateDto.setParkCnt(parkCnt);
        if (StringUtil.isNotEmpty(parkFeeStr))
        {
            BigDecimal parkFee = new BigDecimal(parkFeeStr);
            estateDto.setParkFee(parkFee);
        }
        estateDto.setStaircaseHousehold(staircaseHousehold);
        estateDto.setMgtCompany(mgtCompany);
        estateDto.setRateFAR(rateFAR);
        estateDto.setRateGreen(rateGreen);
        if (StringUtil.isNotEmpty(mgtPriceStr))
        {
            BigDecimal mgtPrice = new BigDecimal(mgtPriceStr);
            estateDto.setMgtPrice(mgtPrice);
        }
        Integer heatKbn = null;
        if (StringUtil.isNotEmpty(heatKbnStr))
        {
            heatKbn = Integer.valueOf(heatKbnStr);
        }
        estateDto.setHeatKbn(heatKbn);
        Integer hydropowerGasKbn = null;
        if (StringUtil.isNotEmpty(hydropowerGasKbnStr))
        {
            hydropowerGasKbn = Integer.valueOf(hydropowerGasKbnStr);
        }
        estateDto.setHydropowerGasKbn(hydropowerGasKbn);
        estateDto.setUptDt(new Date());
        estateDto.setUptEmpId(userInfo.getUserId());
        estateDto.setDelFlg(false);
        estateDto.setDeptId(Integer.valueOf(userInfo.getGroupId()));
        // 备案名
        String recordName = (String)reqMap.get("recordName");
        //推广名
        String promotionName = (String)reqMap.get("promotionName");
        //签约名
        String signName = (String)reqMap.get("signName");
        estateDto.setRecordName(recordName);
        estateDto.setPromotionName(promotionName);
        estateDto.setSignName(signName);
        estateDto.setCountryNo(reqMap.get("countryNo").toString());
        estateDto.setOpEstateId(reqMap.get("opEstateId").toString());
        estateDto.setOpEstateNm(reqMap.get("opEstateNm").toString());
        estateInfoDto.setEstate(estateDto);
        
        //联动规则
        List<EstateRuleDto> estateRuleDtos = new ArrayList<>();
        EstateRuleDto estateRuleDto = new EstateRuleDto();
        String ruIdStr = (String)reqMap.get("ruId");//联动规则自增编号
        String authenticationKbnStr = (String)reqMap.get("authenticationKbn");//认证类型
        String advanceReportHH = (String)reqMap.get("advanceReportHH");//提前报备期(时)
        String advanceReportMM = (String)reqMap.get("advanceReportMM");//提前报备期(分)
        String relationProtectPeriodStr = (String)reqMap.get("relationProtectPeriod");//带看保护期
        String relationRewardStr = (String)reqMap.get("relationReward");//带看奖励
        String relationDtStart = (String)reqMap.get("relationDtStart");//带看起始日期
        String relationDtEnd = (String)reqMap.get("relationDtEnd");//带看截止日期
        String pledgedRewardStr = (String)reqMap.get("pledgedReward");//认筹奖励
        String pledgedDtStart = (String)reqMap.get("pledgedDtStart");//认筹起始日期
        String pledgedDtEnd = (String)reqMap.get("pledgedDtEnd");//认筹截止日期
        String subscribedRewardStr = (String)reqMap.get("subscribedReward");//认购奖励
        String subscribedDtStart = (String)reqMap.get("subscribedDtStart");//认购起始日期
        String subscribedDtEnd = (String)reqMap.get("subscribedDtEnd");//认购截止日期
        String bargainRewardStr = (String)reqMap.get("bargainReward");//成交奖励
        String commissionPeriodStr = (String)reqMap.get("commissionPeriod");//结佣期限
        String commissionKbnStr = (String)reqMap.get("commissionKbn");//佣金方式
        String commissionStr = (String)reqMap.get("commission");//佣金
        String commissionMemo = (String)reqMap.get("commissionMemo");//收入结算描述
        String commissionCondition = (String)reqMap.get("commissionCondition");//收入结算条件
        String payKbnStr = (String)reqMap.get("payKbn");//结佣方式
        String saleKbnStr = (String)reqMap.get("saleKbn");//销售方式
        String reportDtStart = (String)reqMap.get("reportDtStart");//报备开始日
        String reportDtEnd = (String)reqMap.get("reportDtEnd");//报备截止日
        String reportKbnStr = (String)reqMap.get("reportKbn");//报备模式
        String hideNumberFlg = (String)reqMap.get("hideNumberFlg");//隐号报备
        String reportRule = (String)reqMap.get("reportRule");//报备规则
        String commissionRule = (String)reqMap.get("commissionRule");//结佣规则
        String rtnCommission = (String) reqMap.get("rtnCommission");  //返佣标准
        String rtnCommissionMemo = (String) reqMap.get("rtnCommissionMemo"); //返佣结算条件 
        String incomeSubject = (String) reqMap.get("incomeSubject");  //收入标的
        
        if (StringUtil.isNotEmpty(ruIdStr))
        {
            Integer ruId = Integer.valueOf(ruIdStr);
            
            if(ruId.equals(Integer.valueOf(-9999))) {
                String ruleId = "";
                
                ResultData<?> resultDataRuleId = this.seqNoService.getSeqNo("TYPE_ESTATERULE");
                ruleId = (String)resultDataRuleId.getReturnData();
                estateRuleDto.setRuleId(ruleId);
                
                estateRuleDto.setCrtDt(new Date());
                estateRuleDto.setCrtEmpId(userInfo.getUserId());
            }
            
            estateRuleDto.setId(Integer.valueOf(ruId));
            if (StringUtil.isNotEmpty(authenticationKbnStr))
            {
                Integer authenticationKbn = null;
                authenticationKbn = Integer.valueOf(authenticationKbnStr);
                estateRuleDto.setAuthenticationKbn(authenticationKbn);
            }
            estateRuleDto.setAdvanceReportHH(advanceReportHH);
            estateRuleDto.setAdvanceReportMM(advanceReportMM);
            if (StringUtil.isNotEmpty(relationProtectPeriodStr))
            {
                BigDecimal relationProtectPeriod = new BigDecimal(relationProtectPeriodStr);
                estateRuleDto.setRelationProtectPeriod(relationProtectPeriod);
            }
            if (StringUtil.isNotEmpty(relationRewardStr))
            {
                BigDecimal relationReward = new BigDecimal(relationRewardStr);
                estateRuleDto.setRelationReward(relationReward);
            }
            estateRuleDto.setRelationDtStart(this.stringToDate(relationDtStart));
            estateRuleDto.setRelationDtEnd(this.stringToDate(relationDtEnd));
            if (StringUtil.isNotEmpty(pledgedRewardStr))
            {
                BigDecimal pledgedReward = new BigDecimal(pledgedRewardStr);
                estateRuleDto.setPledgedReward(pledgedReward);
            }
            estateRuleDto.setPledgedDtStart(this.stringToDate(pledgedDtStart));
            estateRuleDto.setPledgedDtEnd(this.stringToDate(pledgedDtEnd));
            if (StringUtil.isNotEmpty(subscribedRewardStr))
            {
                BigDecimal subscribedReward = new BigDecimal(subscribedRewardStr);
                estateRuleDto.setSubscribedReward(subscribedReward);
            }
            estateRuleDto.setSubscribedDtStart(this.stringToDate(subscribedDtStart));
            estateRuleDto.setSubscribedDtEnd(this.stringToDate(subscribedDtEnd));
            if (StringUtil.isNotEmpty(bargainRewardStr))
            {
                BigDecimal bargainReward = new BigDecimal(bargainRewardStr);
                estateRuleDto.setBargainReward(bargainReward);
            }
            if (StringUtil.isNotEmpty(commissionPeriodStr))
            {
                BigDecimal commissionPeriod = new BigDecimal(commissionPeriodStr);
                estateRuleDto.setCommissionPeriod(commissionPeriod);
            }
            Integer commissionKbn = null;
            if (StringUtil.isNotEmpty(commissionKbnStr))
            {
                commissionKbn = Integer.valueOf(commissionKbnStr);
                estateRuleDto.setCommissionKbn(commissionKbn);
            }
            if (StringUtil.isNotEmpty(commissionStr))
            {
                BigDecimal commission = new BigDecimal(commissionStr);
                estateRuleDto.setCommission(commission);
            }
            //结算收入条件
            if(StringUtils.isNotBlank(commissionCondition)){
            	estateRuleDto.setCommissionCondition(Integer.valueOf(commissionCondition));
            }
            estateRuleDto.setCommissionMemo(commissionMemo);
            Integer payKbn = null;
            if (StringUtil.isNotEmpty(payKbnStr))
            {
                payKbn = Integer.valueOf(payKbnStr);
                estateRuleDto.setPayKbn(payKbn);
            }
            Integer saleKbn = null;
            if (StringUtil.isNotEmpty(saleKbnStr))
            {
                saleKbn = Integer.valueOf(saleKbnStr);
                estateRuleDto.setSaleKbn(saleKbn);
            }
            estateRuleDto.setReportDtStart(this.stringToDate(reportDtStart));
            estateRuleDto.setReportDtEnd(this.stringToDate(reportDtEnd));
            Integer reportKbn = null;
            if (StringUtil.isNotEmpty(reportKbnStr))
            {
                reportKbn = Integer.valueOf(reportKbnStr);
                estateRuleDto.setReportKbn(reportKbn);
            } 
            estateRuleDto.setIncomeSubject(incomeSubject);
            estateRuleDto.setRtnCommission(rtnCommission);
            estateRuleDto.setRtnCommissionMemo(rtnCommissionMemo);
            estateRuleDto.setHideNumberFlg(hideNumberFlg);
            estateRuleDto.setReportRule(reportRule);
            estateRuleDto.setCommissionRule(commissionRule);
            estateRuleDto.setEstateId(estateId);
            estateRuleDto.setUptDt(new Date());
            estateRuleDto.setUptEmpId(userInfo.getUserId());
            estateRuleDto.setDelFlg(false);
            estateRuleDtos.add(estateRuleDto);
            estateInfoDto.setEstateRuleDetails(estateRuleDtos); 
        }
        
        //在售户型 默认已经修改过
        
        //图片
//        List<PhotoDto> filePhoto = this.getPhotos(reqMap, estateId, null);
        List<PhotoDto> filePhoto = new ArrayList<>();
        String estateCoverImg = (String)reqMap.get("estateCoverImg");//楼盘封面图片No
        //楼盘相册
        getEstatePhotoDtos(filePhoto,estatePhotosDto.getPhoto1(),estatePhotosDto.getPhoto2(),estatePhotosDto.getPhoto3(),estatePhotosDto.getPhoto4(),estatePhotosDto.getPhoto5(),estateCoverImg,estateId,null);

        if (null != filePhoto && !filePhoto.isEmpty())
        {
            estateInfoDto.setFilePhoto(filePhoto);
        }
        //周边配套
        List<EstateSupportDto> estateSupportDetails = this.getSupportDtos(reqMap, estateId);
        if (null != estateSupportDetails && !estateSupportDetails.isEmpty())
        {
            estateInfoDto.setEstateSupportDetails(estateSupportDetails);
        }
        return estateInfoDto;
    }
    
    /** 
    * @Title: setNewEstateTypeDto 
    * @Description: 新增在售户型时用
    * @param reqMap
    * @return     
     * @throws Exception 
    */
    private EstateInfoDto setNewEstateTypeDto(Map<String, Object> reqMap,EstatePhotosDto estatePhotosDto)
        throws Exception
    {
        EstateInfoDto estateInfoDto = new EstateInfoDto();
        List<EstateTypeDto> estateTypeDtos = new ArrayList<>();
        EstateTypeDto estateTypeDto = new EstateTypeDto();
        UserInfo userInfo = UserInfoHolder.get();
        String estateId = (String)reqMap.get("estateId");
        String countFlgStr = (String)reqMap.get("countFlg");//是否是主力户型
        String countF = (String)reqMap.get("countF");//房型（房）
        String countT = (String)reqMap.get("countT");//房型（厅）
        String countW = (String)reqMap.get("countW");//房型（卫）
        String buildSquareStr = (String)reqMap.get("buildSquare");//面积（建筑）
        String directionKbnStr = (String)reqMap.get("directionKbn");//朝向
        String label = this.getLabel(reqMap);//户型标签
        //户型编号
        String typeId = "";
        ResultData<?> resultData = this.seqNoService.getSeqNo("TYPE_ESTATETYPE");
        typeId = (String)resultData.getReturnData();
        estateTypeDto.setTypeId(typeId);
        Integer countFlg = null;
        if (StringUtil.isNotEmpty(countFlgStr))
        {
            countFlg = Integer.valueOf(countFlgStr);
            estateTypeDto.setCountFlg(countFlg);
        }
        estateTypeDto.setCountF(countF);
        estateTypeDto.setCountT(countT);
        estateTypeDto.setCountW(countW);
        if (StringUtil.isNotEmpty(buildSquareStr))
        {
            BigDecimal buildSquare = new BigDecimal(buildSquareStr);
            estateTypeDto.setBuildSquare(buildSquare);
        }
        Integer directionKbn = null;
        if (StringUtil.isNotEmpty(directionKbnStr))
        {
            directionKbn = Integer.valueOf(directionKbnStr);
            estateTypeDto.setDirectionKbn(directionKbn);
        }
        estateTypeDto.setLabel(label);
        estateTypeDto.setEstateId(estateId);
        estateTypeDto.setCrtDt(new Date());
        estateTypeDto.setCrtEmpId(userInfo.getUserId());
        estateTypeDto.setDelFlg(false);
        estateTypeDtos.add(estateTypeDto);
        estateInfoDto.setEstateTypeDetails(estateTypeDtos);
        
        //图片
//        List<PhotoDto> filePhoto = this.getPhotos(reqMap, estateId, typeId);
        List<PhotoDto> filePhoto = new ArrayList<>();
        String houseCoverImg = (String)reqMap.get("houseCoverImg");//户型封面图片No
        getHousePhotoDtos(filePhoto,estatePhotosDto.getHouseTypePhotos(),estatePhotosDto.getTempletPhotos(),houseCoverImg, estateId, typeId);

        if (null != filePhoto && !filePhoto.isEmpty())
        {
            estateInfoDto.setFilePhoto(filePhoto);
        }
        return estateInfoDto;
    }
    
    private EstateInfoDto setEditEstateTypeDto(Map<String, Object> reqMap,EstatePhotosDto estatePhotosDto)
    {
        EstateInfoDto estateInfoDto = new EstateInfoDto();
        List<EstateTypeDto> estateTypeDtos = new ArrayList<>();
        EstateTypeDto estateTypeDto = new EstateTypeDto();
        UserInfo userInfo = UserInfoHolder.get();
        String id = (String)reqMap.get("id");
        String estateId = (String)reqMap.get("estateId");
        String typeId = (String)reqMap.get("typeId");
        String countFlgStr = (String)reqMap.get("countFlg");//是否是主力户型
        String countF = (String)reqMap.get("countF");//房型（房）
        String countT = (String)reqMap.get("countT");//房型（厅）
        String countW = (String)reqMap.get("countW");//房型（卫）
        String buildSquareStr = (String)reqMap.get("buildSquare");//面积（建筑）
        String directionKbnStr = (String)reqMap.get("directionKbn");//朝向
        String label = this.getLabel(reqMap);//户型标签
        Integer countFlg = null;
        if (StringUtil.isNotEmpty(countFlgStr))
        {
            countFlg = Integer.valueOf(countFlgStr);
            estateTypeDto.setCountFlg(countFlg);
        }
        estateTypeDto.setCountF(countF);
        estateTypeDto.setCountT(countT);
        estateTypeDto.setCountW(countW);
        if (StringUtil.isNotEmpty(buildSquareStr))
        {
            BigDecimal buildSquare = new BigDecimal(buildSquareStr);
            estateTypeDto.setBuildSquare(buildSquare);
        }
        Integer directionKbn = null;
        if (StringUtil.isNotEmpty(directionKbnStr))
        {
            directionKbn = Integer.valueOf(directionKbnStr);
            estateTypeDto.setDirectionKbn(directionKbn);
        }
        estateTypeDto.setLabel(label);
        estateTypeDto.setId(Integer.valueOf(id));
        estateTypeDto.setEstateId(estateId.trim());
        estateTypeDto.setTypeId(typeId.trim());
        estateTypeDto.setUptDt(new Date());
        estateTypeDto.setUptEmpId(userInfo.getUserId());
        estateTypeDtos.add(estateTypeDto);
        estateInfoDto.setEstateTypeDetails(estateTypeDtos);
        
        //图片
        List<PhotoDto> filePhoto = new ArrayList<>();
        String houseCoverImg = (String)reqMap.get("houseCoverImg");//户型封面图片No
        //在售户型
        getHousePhotoDtos(filePhoto,estatePhotosDto.getHouseTypePhotos(),estatePhotosDto.getTempletPhotos(),houseCoverImg, estateId, typeId);

        if (null != filePhoto && !filePhoto.isEmpty())
        {
            estateInfoDto.setFilePhoto(filePhoto);
        }
        return estateInfoDto;
    }
    
    /** 
    * @Title: getMark 
    * @Description: 楼盘标签
    * @param reqMap
    * @return     
    */
    private String getMark(Map<String, Object> reqMap)
    {
        String markStr = "";
        List<Integer> indexList = new ArrayList<>();
        for (String key : reqMap.keySet())
        {
            if (!key.contains("mark"))
            {
                continue;
            }
            Pattern pattern = Pattern.compile("\\[.*?\\]");// 查找规则公式中大括号以内的字符
            Matcher matcher = pattern.matcher(key);
            while (matcher.find())
            {
                String iStr = matcher.group().replaceAll("\\[", "");// 去掉左括号
                iStr = iStr.replaceAll("\\]", "");// 去掉右括号
                Integer index = Integer.parseInt(iStr);
                indexList.add(index);
            }
        }
        Collections.sort(indexList);
        for (Integer index : indexList)
        {
            String mark = (String)reqMap.get("mark[" + index + "]");
            if (!StringUtil.isEmpty(mark))
            {
                markStr = markStr + mark + ",";
            }
        }
        if (StringUtil.isNotEmpty(markStr))
        {
            markStr = markStr.substring(0, markStr.length() - 1);
        }
        return markStr;
    }
    
    /** 
    * @Title: getLabel 
    * @Description: 户型标签
    * @param reqMap
    * @return     
    */
    private String getLabel(Map<String, Object> reqMap)
    {
        String labelStr = "";
        List<Integer> indexList = new ArrayList<>();
        for (String key : reqMap.keySet())
        {
            if (!key.contains("label"))
            {
                continue;
            }
            Pattern pattern = Pattern.compile("\\[.*?\\]");// 查找规则公式中大括号以内的字符
            Matcher matcher = pattern.matcher(key);
            while (matcher.find())
            {
                String iStr = matcher.group().replaceAll("\\[", "");// 去掉左括号
                iStr = iStr.replaceAll("\\]", "");// 去掉右括号
                Integer index = Integer.parseInt(iStr);
                indexList.add(index);
            }
        }
        Collections.sort(indexList);
        for (Integer index : indexList)
        {
            String label = (String)reqMap.get("label[" + index + "]");
            if (!StringUtil.isEmpty(label))
            {
                labelStr = labelStr + label + ",";
            }
        }
        if (StringUtil.isNotEmpty(labelStr))
        {
            labelStr = labelStr.substring(0, labelStr.length() - 1);
        }
        return labelStr;
    }
    
    /** 
    * @Title: getHouseStair 
    * @Description: 梯户数
    * @param reqMap
    * @return     
    */
    private String getHouseStair(Map<String, Object> reqMap)
    {
        String stairHouse = "";
        List<Integer> indexList = new ArrayList<>();
        for (String key : reqMap.keySet())
        {
            if (!key.contains("staircase"))
            {
                continue;
            }
            Pattern pattern = Pattern.compile("\\[.*?\\]");// 查找规则公式中大括号以内的字符
            Matcher matcher = pattern.matcher(key);
            while (matcher.find())
            {
                String iStr = matcher.group().replaceAll("\\[", "");// 去掉左括号
                iStr = iStr.replaceAll("\\]", "");// 去掉右括号
                Integer index = Integer.parseInt(iStr);
                indexList.add(index);
            }
        }
        Collections.sort(indexList);
        for (Integer index : indexList)
        {
            String staircase = (String)reqMap.get("staircase[" + index + "]");//梯
            String household = (String)reqMap.get("household[" + index + "]");//户
            if (StringUtil.isNotEmpty(staircase) && StringUtil.isNotEmpty(household))
            {
                stairHouse = stairHouse + staircase + "|" + household + ",";
            }
        }
        if (StringUtil.isNotEmpty(stairHouse))
        {
            stairHouse = stairHouse.substring(0, stairHouse.length() - 1);
        }
        return stairHouse;
    }
    
    /** 
    * @Title: getPhotos 
    * @Description: 图片
    * @param reqMap
    * @return     
    */
    private List<PhotoDto> getPhotos(Map<String, Object> reqMap, String estateId, String typeId)
    {
        List<PhotoDto> filePhoto = new ArrayList<>();
        String houseTypeImgs = (String)reqMap.get("uploadPhotoId6");//在售户型户型图
        String houseTemplateImgs = (String)reqMap.get("uploadPhotoId7");//在售户型样板间
        String estateDesignImgs = (String)reqMap.get("uploadPhotoId1");//楼盘效果图
        String estateTemplateImgs = (String)reqMap.get("uploadPhotoId2");//楼盘样板间
        String estateMapImgs = (String)reqMap.get("uploadPhotoId3");//楼盘地理位置
        String estateDistrictImgs = (String)reqMap.get("uploadPhotoId4");//楼盘区域规划
        String estateRealImgs = (String)reqMap.get("uploadPhotoId5");//楼盘实景图
        String estateCoverImg = (String)reqMap.get("estateCoverImg");//楼盘封面图片ID
        String houseCoverImg = (String)reqMap.get("houseCoverImg");//户型封面图片ID
        //在售户型户型图
        this.getPhotoDto(filePhoto, houseTypeImgs, houseCoverImg, DictionaryConstants.HOUSE_TYPE_IMG, estateId, typeId);
        
        //在售户型样板间
        this.getPhotoDto(filePhoto,
            houseTemplateImgs,
            houseCoverImg,
            DictionaryConstants.HOUSE_TEMPLATE_IMG,
            estateId,
            typeId);
        
        //楼盘效果图
        this.getPhotoDto(filePhoto,
            estateDesignImgs,
            estateCoverImg,
            DictionaryConstants.ESTATE_DESIGN_IMG,
            estateId,
            null);
        
        //楼盘样板间
        this.getPhotoDto(filePhoto,
            estateTemplateImgs,
            estateCoverImg,
            DictionaryConstants.ESTATE_TEMPLATE_IMG,
            estateId,
            null);
        
        //楼盘地理位置
        this.getPhotoDto(filePhoto, estateMapImgs, estateCoverImg, DictionaryConstants.ESTATE_MAP_IMG, estateId, null);
        
        //楼盘区域规划
        this.getPhotoDto(filePhoto,
            estateDistrictImgs,
            estateCoverImg,
            DictionaryConstants.ESTATE_DISTRICT_IMG,
            estateId,
            null);
        
        //楼盘实景图
        this.getPhotoDto(filePhoto, estateRealImgs, estateCoverImg, DictionaryConstants.ESTATE_REAL_IMG, estateId, null);
        
        return filePhoto;
    }


    /**
     * 获取在售户型-户型图和样板间的图片信息
     * @param filePhoto 总图片list
     * @param houseTypePhotos
     * @param templetPhotos
     * @param coverSellFileNo
     * @param estateId
     * @param typeId
     */
    private void getHousePhotoDtos(List<PhotoDto> filePhoto,List<PhotoDto> houseTypePhotos,List<PhotoDto> templetPhotos, String coverSellFileNo, String estateId,String typeId)
    {
        //清除空数据
        removeEmpty(houseTypePhotos);
        removeEmpty(templetPhotos);

        //在售户型-户型图
        this.setPhotosDto(houseTypePhotos,coverSellFileNo,DictionaryConstants.HOUSE_TYPE_IMG, estateId, typeId);
        //在售户型-样板间
        this.setPhotosDto(templetPhotos,coverSellFileNo,DictionaryConstants.HOUSE_TEMPLATE_IMG, estateId, typeId);

        if(houseTypePhotos != null && houseTypePhotos.size() > 0){
            filePhoto.addAll(houseTypePhotos);
        }
        if(templetPhotos != null && templetPhotos.size() > 0){
            filePhoto.addAll(templetPhotos);
        }
    }

    /**
     * 获取在楼盘相册户型图和样板间等图片信息
     * @param filePhoto 总图片list
     * @param photo1 楼盘相册-效果图
     * @param photo2 楼盘相册-样板间
     * @param photo3 楼盘相册-地理位置
     * @param photo4 楼盘相册-区域规划
     * @param photo5 楼盘相册-实景图
     * @param coverSellFileNo 封面图No
     * @param estateId 楼盘id
     * @param typeId
     */
    private void getEstatePhotoDtos(List<PhotoDto> filePhoto,List<PhotoDto> photo1,List<PhotoDto> photo2,List<PhotoDto> photo3,List<PhotoDto> photo4,List<PhotoDto> photo5, String coverSellFileNo, String estateId,String typeId)
    {
        //清除空数据
        removeEmpty(photo1);
        removeEmpty(photo2);
        removeEmpty(photo3);
        removeEmpty(photo4);
        removeEmpty(photo5);

        //楼盘相册-效果图
        this.setPhotosDto(photo1,coverSellFileNo,DictionaryConstants.ESTATE_DESIGN_IMG, estateId, typeId);
        //楼盘相册-样板间
        this.setPhotosDto(photo2,coverSellFileNo,DictionaryConstants.ESTATE_TEMPLATE_IMG, estateId, typeId);
        //楼盘相册-地理位置
        this.setPhotosDto(photo3,coverSellFileNo,DictionaryConstants.ESTATE_MAP_IMG, estateId, typeId);
        //楼盘相册-区域规划
        this.setPhotosDto(photo4,coverSellFileNo,DictionaryConstants.ESTATE_DISTRICT_IMG, estateId, typeId);
        //楼盘相册-实景图
        this.setPhotosDto(photo5,coverSellFileNo,DictionaryConstants.ESTATE_REAL_IMG, estateId, typeId);

        if(photo1 != null && photo1.size() > 0){
            filePhoto.addAll(photo1);
        }
        if(photo2 != null && photo2.size() > 0){
            filePhoto.addAll(photo2);
        }
        if(photo3 != null && photo3.size() > 0){
            filePhoto.addAll(photo3);
        }
        if(photo4 != null && photo4.size() > 0){
            filePhoto.addAll(photo4);
        }
        if(photo5 != null && photo5.size() > 0){
            filePhoto.addAll(photo5);
        }

    }

    /**
     * 去除集合中的空对象
     * @param photoDtos
     * @return
     */
    private void removeEmpty(List<PhotoDto> photoDtos){
        if(photoDtos != null){
            int length = photoDtos.size();
            for(int i = length - 1;i>=0;i--){
                String sellfileNo = photoDtos.get(i).getSellFileNo();
                String fileAbbrUrl = photoDtos.get(i).getFileAbbrUrl();
                if(StringUtil.isEmpty(sellfileNo) || StringUtil.isEmpty(fileAbbrUrl)){
                    photoDtos.remove(i);
                }
            }
        }
    }
    
    /** 
    * @Title: getSupportDtos 
    * @Description: 周边配套
    * @param reqMap
    * @return     
     * @throws Exception 
    */
    private List<EstateSupportDto> getSupportDtos(Map<String, Object> reqMap, String estateId)
        throws Exception
    {
        List<EstateSupportDto> estateSupportDetails = new ArrayList<>();
        EstateSupportDto estateSupportDto = new EstateSupportDto();
        //户型编号
        String supportId = "";
        ResultData<?> resultData = this.seqNoService.getSeqNo("TYPE_ESTATESUPPORT");
        supportId = (String)resultData.getReturnData();
        List<Integer> indexList = new ArrayList<>();
        for (String key : reqMap.keySet())
        {
            if (!key.contains("description"))
            {
                continue;
            }
            Pattern pattern = Pattern.compile("\\[.*?\\]");// 查找规则公式中大括号以内的字符
            Matcher matcher = pattern.matcher(key);
            while (matcher.find())
            {
                String iStr = matcher.group().replaceAll("\\[", "");// 去掉左括号
                iStr = iStr.replaceAll("\\]", "");// 去掉右括号
                Integer index = Integer.parseInt(iStr);
                indexList.add(index);
            }
        }
        Collections.sort(indexList);
        for (Integer index : indexList)
        {
            estateSupportDto = new EstateSupportDto();
            String description = (String)reqMap.get("description[" + index + "]");//梯
            String title = (String)reqMap.get("title[" + index + "]");//户
            if (StringUtil.isNotEmpty(description))
            {
                estateSupportDto.setDescription(description);
            }
            if (StringUtil.isNotEmpty(title))
            {
                estateSupportDto.setTitle(title);
                estateSupportDto.setCrtDt(new Date());
                estateSupportDto.setCrtEmpId(UserInfoHolder.getUserId());
                estateSupportDto.setDelFlg(false);
                estateSupportDto.setEstateId(estateId);
                estateSupportDto.setSupportId(supportId);
                estateSupportDetails.add(estateSupportDto);
            }
        }
        
        return estateSupportDetails;
    }
    
    /** 
    * @Title: getPhotoDto 
    * @Description: 户型楼盘图片
    * @param photoDtos 图片对象列表
    * @param imgs 图片ID序列
    * @param coverImg 封面图ID
    * @param photoKbn 图片类型
    * @param estateId 楼盘编号
    * @param typeId 楼盘户型编号
    */
    private void getPhotoDto(List<PhotoDto> photoDtos, String imgs, String coverImg, int photoKbn, String estateId,
        String typeId)
    {
        PhotoDto photoDto = new PhotoDto();
        if (StringUtil.isNotEmpty(imgs))
        {
            String[] imgArr = imgs.split(",");
            for (int i = 0; i < imgArr.length; i++)
            {
                photoDto = new PhotoDto();
                photoDto.setCrtDt(new Date());
                if (imgArr[i].equals(coverImg))
                {
                    photoDto.setCoverFlg("Y");
                }
                else
                {
                    photoDto.setCoverFlg("N");
                }
                photoDto.setCrtEmpId(UserInfoHolder.getUserId());
                photoDto.setDelFlg(false);
                photoDto.setOrderNo(i + "");
                photoDto.setPhotoId(imgArr[i]);
                photoDto.setPhotoKbn(photoKbn);
                //TODO photoNm
                //photoDto.setPhotoPath(FileHelper.getFilePath(imgArr[i]));
                photoDto.setEstateId(estateId);
                photoDto.setTypeId(typeId);
                photoDtos.add(photoDto);
            }
        }
    }


    /**
     * @Title: setPhotosDto
     * @Description: 户型楼盘图片
     * @param photoDtos 图片对象列表
     * @param coverSellFileNo 封面图No
     * @param fileType 图片类型
     * @param estateId 楼盘编号
     * @param typeId 楼盘户型编号
     */
    private void setPhotosDto(List<PhotoDto> photoDtos, String coverSellFileNo, int fileType, String estateId,String typeId)
    {
        if(photoDtos != null && photoDtos.size() > 0){
            for(int i = 0,length = photoDtos.size();i<length;i++){
                PhotoDto photoDto = photoDtos.get(i);
                photoDto.setCrtDt(new Date());
                if(coverSellFileNo.equals(photoDto.getSellFileNo())){
                    photoDto.setCoverFlg("Y");
                }else{
                    photoDto.setCoverFlg("N");
                }
                photoDto.setCrtEmpId(UserInfoHolder.getUserId());
                photoDto.setDelFlg(false);
                photoDto.setOrderNo(i + "");
                photoDto.setPhotoKbn(fileType);
                photoDto.setEstateId(estateId);
                photoDto.setTypeId(typeId);
            }
        }
    }
    
    private Date stringToDate(String dateStr)
    {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            if (StringUtil.isNotEmpty(dateStr))
            {
                if (dateStr.length() <= 12)
                {
                    dateStr = dateStr + "-01";
                }
                date = sdf.parse(dateStr);
            }
        }
        catch (ParseException e)
        {
            logger.warn("楼盘新增页面日期转换有问题");
        }
        return date;
    }
    
    /**
     * 查询归属项目部
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData<?> getProjectDepartment(Map<String,Object> reqMap)throws Exception
    {
            //调用 接口
            String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/getProjectDepartment/{param}";
            
            ResultData<?> backResult = get(url, reqMap);
            
            return backResult;
     }

    public ResultData<?> selectFromOpCheck(Map<String, Object> reqMap) throws Exception{
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/selectFromOpCheck/{opEstateId}/{estId}";
        
        ResultData<?> backResult = get(url, reqMap.get("opEstateId"),reqMap.get("estId"));
        
        return backResult;
    }

    public ResultData<?> queryCountryList() throws Exception{
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/queryCountryList/";

        ResultData<?> backResult = get(url);

        return backResult;
    }
    public ResultData<?> getBigCustomerList() throws Exception{
    	//调用 接口
    	String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/getBigCustomerList";
    	
    	ResultData<?> backResult = get(url);
    	
    	return backResult;
    }
    
    public ResultData<?> getMattressNail() throws Exception{
    	//调用 接口
    	String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/getMattressNail";
    	
    	ResultData<?> backResult = get(url);
    	
    	return backResult;
    }

    public ResultData<?> updatePopupDetail(Map<String, Object> reqMap) throws Exception {
        ResultData<?> resultData = new ResultData<>();
        String url = SystemParam.getWebConfigValue("RestServer") + "estate/update";
        EstateInfoDto estateInfoDto = new EstateInfoDto();
        EstateDto estateDto = new EstateDto();
        ArrayList<EstateChangeDto> ecList = new ArrayList<>();
        UserInfo userInfo = UserInfoHolder.get();
        estateDto.setUptEmpId(userInfo.getUserId());
        estateDto.setUptDt(new Date());

        Integer id = Integer.valueOf(reqMap.get("id").toString());
        String modifyReason = "<br>修改原因:" + reqMap.get("modifyReason");
        String changeDetail = "";
        estateDto.setId(id);
        Integer count = 0;
        //楼盘名称
        String estateNm = (String) reqMap.get("estateNm");
        String oldName = (String) reqMap.get("oldName");
        String newName = (String) reqMap.get("newName");
        String estDetail = "";
        if (null != estateNm) {
            count ++;
            estateDto.setEstateNm(newName);
            estDetail = "原楼盘名称:" + oldName + ";<br>修改后楼盘名称:" + newName;
            changeDetail = estDetail;
        }
        //项目地址
        String estateAddress = (String) reqMap.get("estateAddress");
        String oldAddress = (String) reqMap.get("oldAddress");
        String realityCityNo = (String) reqMap.get("realityCityNo");
        String districtNo = (String) reqMap.get("districtNo");
        String newCityNm = (String) reqMap.get("newCityNm");
        String newDistrictNm = (String) reqMap.get("newDistrictNm");
        String newAddress = (String) reqMap.get("newAddress");
        String addDetail = "";
        if (null != estateAddress) {
            count ++;
            estateDto.setRealityCityNo(realityCityNo);
            estateDto.setRealityCityNm(newCityNm);
            estateDto.setDistrictId(districtNo);
            estateDto.setDistrictNm(newDistrictNm);
            estateDto.setAddress(newAddress);
            addDetail = "原项目地址:" + oldAddress + ";<br>修改后项目地址:" + newCityNm + newDistrictNm + newAddress;
            if (StringUtil.isNotEmpty(changeDetail)) {
                changeDetail = changeDetail + "<br>-------------------------------------------<br>" + addDetail;
            } else {
                changeDetail = addDetail;
            }
        }
        //销售状态
        String salesStatus = (String) reqMap.get("salesStatus");
        String oldSalesStatusStr = (String) reqMap.get("oldSalesStatusStr");
        String newSalesStatus = (String) reqMap.get("newSalesStatus");
        String openTime = (String) reqMap.get("openTime");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String newSalesStatusStr = null;
        String saleDetail = "";
        if (null != salesStatus) {
            count ++;
            estateDto.setSalesStatus(Integer.valueOf(newSalesStatus));
            if (Objects.equals(newSalesStatus, "1441")) {
                newSalesStatusStr = "在售;实际开盘日期为:" + openTime;
                estateDto.setRealOpenTime(format.parse(openTime));
            } else if (Objects.equals(newSalesStatus, "1442")) {
                newSalesStatusStr = "待售;预计开盘日期为:" + openTime;
                estateDto.setOpenTime(format.parse(openTime));
            } else if (Objects.equals(newSalesStatus, "1443")) {
                newSalesStatusStr = "售完";
            }
            saleDetail = "原销售状态:" + oldSalesStatusStr + ";<br>修改后销售状态:" + newSalesStatusStr;
            if (StringUtil.isNotEmpty(changeDetail)) {
                changeDetail = changeDetail + "<br>-------------------------------------------<br>" + saleDetail;
            } else {
                changeDetail = saleDetail;
            }
        }

        //业务模式
        String businessMode = (String) reqMap.get("businessMode");
        String oldBusinessModeStr = (String) reqMap.get("oldBusinessModeStr");
        String newBusinessMode = (String) reqMap.get("newBusinessMode");
        String newBusinessModeStr = (String) reqMap.get("newBusinessModeStr");
        String coopDetail = "";
        if (null != businessMode) {
            count ++;
            estateDto.setCooperationMode(-1);
            estateDto.setBusinessModel(Integer.valueOf(newBusinessMode));

            coopDetail = "原业务模式:" + oldBusinessModeStr.trim() + ";<br>修改后业务模式:" + newBusinessModeStr.trim();
            if (StringUtil.isNotEmpty(changeDetail)) {
                changeDetail = changeDetail + "<br>-------------------------------------------<br>" + coopDetail;
            } else {
                changeDetail = coopDetail;
            }
        }

        //合作方
        String partnerNm = (String) reqMap.get("partnerNm");
        String oldPartnerNm = (String) reqMap.get("oldPartnerNm");
        String newPartnerNm = (String) reqMap.get("newPartnerNm");
        String ptDetail = "";
        if (null != partnerNm) {
            count ++;
            estateDto.setPartnerNm(newPartnerNm);
            ptDetail = "原合作方:" + oldPartnerNm + ";<br>修改后合作方:" + newPartnerNm;
            if (StringUtil.isNotEmpty(changeDetail)) {
                changeDetail = changeDetail + "<br>-------------------------------------------<br>" + ptDetail;
            } else {
                changeDetail = ptDetail;
            }
        }
        //开发商
        String devCompany = (String) reqMap.get("devCompany");
        String oldDevCompany = (String) reqMap.get("oldDevCompany");
        String newDevCompany = (String) reqMap.get("newDevCompany");
        String devDetail = "";
        if (null != devCompany) {
            count ++;
            estateDto.setDevCompany(newDevCompany);
            devDetail = "原开发商:" + oldDevCompany + ";<br>修改后开发商:" + newDevCompany;
            if (StringUtil.isNotEmpty(changeDetail)) {
                changeDetail = changeDetail + "<br>-------------------------------------------<br>" + devDetail;
            } else {
                changeDetail = devDetail;
            }
        }

        if(count>1){
            changeDetail = changeDetail + "<br>-------------------------------------------" + modifyReason;
        }else{
            changeDetail = changeDetail + modifyReason;
        }

        EstateChangeDto estateChangeDto = new EstateChangeDto();
        estateChangeDto.setChangeType(String.valueOf(DictionaryConstants.Project_Change_Status));
        estateChangeDto.setChangeName("项目信息更新");
        estateChangeDto.setChangeDetail(changeDetail);
        estateChangeDto.setChangeDate(new Date());
        estateChangeDto.setEstateId(id);
        estateChangeDto.setOperator(userInfo.getUserId());
        estateChangeDto.setOperatorCode(userInfo.getUserCode());
        estateChangeDto.setOperatorName(userInfo.getUserName());
        ecList.add(estateChangeDto);
        estateInfoDto.setEstate(estateDto);
        estateInfoDto.setEstateChangeDetails(ecList);

        resultData = post(url, estateInfoDto);
        return resultData;
    }

    public ResultData<?> getCityByGovCityCode(String govCityCode) throws Exception{
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "linkages" + "/getCityByGovCityCode/{govCityCode}";

        ResultData<?> backResult = get(url, govCityCode);

        return backResult;
    }

    public ResultData<?> getEstateNmList(Map<String,Object> reqMap) throws Exception{
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "estate" + "/getEstateNmList/{param}";

        ResultData<?> backResult = get(url,reqMap);

        return backResult;
    }
    /**********************************private function*******************************/
}
