package cn.com.eju.deal.houseLinkage.report.service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.util.BuildingNoUtil;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.houseLinkage.report.ReportDto;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**   
* Service层
* @author (qianwei)
* @date 2016年4月29日 下午9:30:27
*/
@Service("reportService")
public class ReportService extends BaseService
{
    
    //private final static String REST_REPORT = SystemCfg.getString("reportRestServer");
    
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
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/{id}";
        
        ResultData<?> backResult = get(url, id);
        
        return backResult;
    }
    
    public ResultData<?> getReport(String estateId, String companyId, String customerId)
            throws Exception
    {
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/{estateId}/{companyId}/{customerId}";
        
        ResultData<?> backResult = get(url, estateId, companyId, customerId);
        
        return backResult;
    }
    
    /** 
     * 退回带看已确认的状态
     * @param reportDto
     * @return
     * @throws Exception
     */
     public ResultData<?> updateReback(ReportDto reportDto) throws Exception
     {
         
         String url = SystemParam.getWebConfigValue("RestServer") + "report/reback";
         
         ResultData<?> reback = post(url, reportDto);
		return reback;
         
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
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/q/{param}";
        
        ResultData<?> reback = get(url, reqMap, pageInfo);
        
        return reback;
        
    }
    
    /** 
    * 创建
    * @param contractInfoDto
    * @return
    * @throws Exception
    */
    public ResultData<?> create(ReportDto contractInfoDto)
        throws Exception
    {
        
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "";
        
        ResultData<?> backResult = post(url, contractInfoDto);
        
        return backResult;
    }
    
    /** 
    * 更新
    * @param reportDto
    * @return
    * @throws Exception
    */
    public ResultData<?> update(ReportDto reportDto)
        throws Exception
    {
        
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "";

        ResultData<?> backResult = post(url, reportDto);

        return backResult;
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
        
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/{id}/{updateId}";
        
        delete(url, id, updateId);
        
    }
    
    /** 
     * 审核
     * @param contractInfoDto
     * @return
     * @throws Exception
     */
     public void audit(ReportDto contractInfoDto)
         throws Exception
     {
         
         String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/audit";
         
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
          String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/check/{param}";
          ResultData<?> backResult =get(url, reqMap);
          return backResult;
      }
      /** 
       *  * 查询进度列表
       * @return
       * @throws Exception
       */
       public ResultData<?> getProcessList()
           throws Exception
       {
           
           //调用 接口
           String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/process";
           
           ResultData<?> backResult = get(url);
           
           return backResult;
       }

    public ResultData<?> getOperDetail(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/getOperDetail/{param}";
        ResultData<?> backResult =get(url, reqMap);
        return backResult;
    }

    public ResultData<?> operDetailUpdate(Map<String, Object> reqMap) throws Exception {
        reqMap = BuildingNoUtil.parse(reqMap);
        //String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/operDetailUpdate/{param}";
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/operDetailUpdate";
        ResultData<?> backResult =post(url, reqMap);
        return backResult;
    }

    public void updateDetailRoughDate(ReportDto reportDto) throws Exception {

        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/updateDetailRoughDate";
        put(url, reportDto);
    }
    
    

    public ResultData<?> getYHApproveCheck(Integer id)
        throws Exception{
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/getYHApproveCheck/{id}";
        ResultData<?> backResult = get(url, id);
        
        return backResult;
    }
    
    public ResultData<?> selectBuildingNoRepeatCount(String buildingNo,String reportId)
            throws Exception{
            //调用 接口
            String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/selectBuildingNoRepeatCount";
            ReportDto reportDto = new ReportDto();
            reportDto.setBuildingNo(buildingNo);
            reportDto.setReportId(reportId);
            ResultData<?> backResult = post(url, reportDto);
            
            return backResult;
        }
    /**
     * 获取业绩人的中心
     * @throws Exception
     */
    public ResultData<?> getLinkUserCenter(String userCode)
            throws Exception{
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/getLinkUserCenter/{userCode}";
        ResultData<?> backResult = get(url, userCode);
        return backResult;
   }

    /**
     * 添加返佣对象表记录
     * @param reportDto
     * @throws Exception
     */
    public void insertYjReport(ReportDto reportDto) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/insertYjReport";
        put(url, reportDto);
    }

    public ResultData<?> getAccountProject(String cityNo) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "report" + "/getAccountProject/{cityNo}";
        ResultData<?> backResult = get(url, cityNo);
        return backResult;
    }
}
