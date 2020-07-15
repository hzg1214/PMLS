package cn.com.eju.deal.follow.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.company.service.CompanyService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.follow.FollowDto;

/** 
* @ClassName: followService 
* @Description: 跟进Service
* @author 陆海丹 
* @date 2016年3月29日 下午9:00:04 
*/
@Service("followService")
public class FollowService extends BaseService<FollowDto>
{
    //private final static String REST_SERVICE = SystemCfg.getString("followRestServer");
    
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name="companyService")
    private CompanyService companyService;
    /** 
    * @Title: queryList 
    * @Description: 跟进列表
    * @param reqMap
    * @param pageInfo
    * @return
    * @throws Exception     
    */
    public ResultData<?> queryList(Map<String, Object> reqMap, PageInfo pageInfo)
        throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "follow" + "/q/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }
    
    /** 
    * @Title: getById 
    * @Description: 跟进编号查询跟进详情
    * @param id
    * @return
    * @throws Exception     
    */
    public ResultData<?> getById(int id)
        throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "follow" + "/{id}";
        ResultData<?> backResult = get(url, id);
        return backResult;
    }
    
    /** 
    * @Title: create 
    * @Description: 跟进新增
    * @param followDto
    * @return
    * @throws Exception     
    */
    public ResultData<?> create(Map<String, Object> reqMap)
        throws Exception
    {
        ResultData<?> backResult =new ResultData<>();
        String url = SystemParam.getWebConfigValue("RestServer") + "follow" + "";
        FollowDto followDto=this.reqMapToFollowDto(reqMap);
        followDto.setUserCreate(UserInfoHolder.getUserId());
        backResult = post(url, followDto);
        return backResult;
    }
    
    /** 
    * @Title: update 
    * @Description: 更新跟进信息
    * @param followDto
    * @throws Exception     
    */
    public void update(Map<String, Object> reqMap)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "follow" + "";
        //Attention!保持门店新增页面和门店更新页面的字段命名一致！
        FollowDto followDto=this.reqMapToFollowDto(reqMap);
        put(url, followDto);
    }
    
    /** 
    * @Title: checkFollow 
    * @Description: 检查是否可以添加跟进
    * @param reqMap
    * @return
    * @throws Exception     
    */
    public ResultData<?> checkFollow(Map<String, Object> reqMap)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "follow" + "/check/{param}";
        ResultData<?> backResult =get(url, reqMap);
        return backResult;
    }
    
    /*-------------------------------------------------------private function-------------------------------------------------------*/
    private FollowDto reqMapToFollowDto(Map<String, Object> reqMap)
    {
        FollowDto followDto=new FollowDto();
        String companyIdStr=(String)reqMap.get("companyId");
        String storeIdStr=(String)reqMap.get("storeId");
        String title=(String)reqMap.get("title");
        String followTypeStr=(String)reqMap.get("followType");
        String content=(String)reqMap.get("content");
        String longitudeStr=(String)reqMap.get("longitude");
        String latitudeStr=(String)reqMap.get("latitude");
        String dateCreateStr=(String)reqMap.get("dateCreate");
        String followIdStr=(String)reqMap.get("followId");
        String userCreateStr=(String)reqMap.get("userCreate");
        String isDeleteStr=(String)reqMap.get("isDelete");
        if(null!=companyIdStr && !companyIdStr.isEmpty()){
            Integer companyId=Integer.valueOf(companyIdStr);
            followDto.setCompanyId(companyId);
        }
        if(null!=storeIdStr && !storeIdStr.isEmpty()){
            Integer storeId=Integer.valueOf(storeIdStr);
            followDto.setStoreId(storeId);
        }
        if(null!=title && !title.isEmpty()){
            followDto.setTitle(title);
        }
        if(null!=followTypeStr && !followTypeStr.isEmpty()){
            Integer followType=Integer.valueOf(followTypeStr);
            followDto.setFollowType(followType);
        }
        if(null!=content && !content.isEmpty()){
            followDto.setContent(content);
        }
        if(null!=longitudeStr && StringUtil.isNotEmpty(longitudeStr))
        {
            BigDecimal longitude=new BigDecimal(longitudeStr);
            followDto.setLongitude(longitude);
        }
        else {
            followDto.setLongitude(BigDecimal.ZERO);
        }
        if(null!=latitudeStr && StringUtil.isNotEmpty(latitudeStr))
        {
            BigDecimal latitude=new BigDecimal(latitudeStr);
            followDto.setLatitude(latitude);
        }else {
            followDto.setLatitude(BigDecimal.ZERO);
        }
        if(null!=dateCreateStr && StringUtil.isNotEmpty(dateCreateStr)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
            try
            {
                Date dateCreate = sdf.parse(dateCreateStr);
                followDto.setDateCreate(dateCreate);
            }
            catch (ParseException e)
            {
                logger.warn("跟进编辑页面日期转换有问题");
            }
        }
        if(null!=followIdStr && StringUtil.isNotEmpty(followIdStr)){
            Integer followId=Integer.valueOf(followIdStr);
            followDto.setFollowId(followId);
        }
        if(null!=userCreateStr && StringUtil.isNotEmpty(userCreateStr)){
            Integer userCreate=Integer.valueOf(userCreateStr);
            followDto.setUserCreate(userCreate);
        }
        if(null!=isDeleteStr && StringUtil.isNotEmpty(isDeleteStr)){
            Boolean isDelete=Boolean.valueOf(isDeleteStr);
            followDto.setIsDelete(isDelete);
        }
        return followDto;
    }
    
    /*-------------------------------------------------------private function-------------------------------------------------------*/
}
