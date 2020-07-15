package cn.com.eju.deal.fangyou.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.fangyou.FangyouAccountDto;

/** 
* @ClassName: FangyouAccountService 
* @Description: 房友账号绑定
* @author cning
* @date 2017年07月06日
*/

@Service("fangyouAccountService")
public class FangyouAccountService extends BaseService<FangyouAccountDto>{
	 private final LogHelper logger = LogHelper.getLogger(this.getClass());
	    
	 /** 
	    * @Title: queryList 
	    * @Description: 房友账号绑定列表
	    * @param reqMap
	    * @param pageInfo
	    * @return
	    * @throws Exception     
	    */
	    public ResultData<?> queryList(Map<String, Object> reqMap, PageInfo pageInfo)
	        throws Exception
	    {
	        //调用 接口
	        String url = SystemParam.getWebConfigValue("RestServer") + "fangyouAccount" + "/q/{param}";
	        ResultData<?> reback = get(url, reqMap, pageInfo);
	        return reback;
	    }
	    
	    /** 
	     * @Title: addBundling 
	     * @Description: 房友账号绑定新增
	     * @param reqMap
	     * @return
	     * @throws Exception     
	     */
	     public ResultData<?> addBundling(Map<String, Object> reqMap)
	         throws Exception
	     {
	         ResultData<?> backResult =new ResultData<>();
	         String url = SystemParam.getWebConfigValue("RestServer") + "fangyouAccount/add";
	         FangyouAccountDto fangyouAccountDto = this.reqMapToFangyouAccountDto(reqMap);
	         UserInfo userInfo = UserInfoHolder.get();
	         fangyouAccountDto.setUserIdCreate(userInfo.getUserId());
	         fangyouAccountDto.setUserName(userInfo.getUserName());
	         if(fangyouAccountDto.getOperType()==0)
	         {
	        	 fangyouAccountDto.setOperType(1);
	         }else{
	        	 fangyouAccountDto.setOperType(0);
	         }
	         
	         backResult = post(url, fangyouAccountDto);
	         return backResult;
	     }
	     
	     private FangyouAccountDto reqMapToFangyouAccountDto(Map<String, Object> reqMap)
	     {
	    	 FangyouAccountDto fangyouAccountDto=new FangyouAccountDto();
	         String fangyouNoStr=reqMap.get("fangyouNo").toString().trim();
	         String storeIdStr=(String)reqMap.get("storeId");
	         Integer operTypeStr=Integer.valueOf(reqMap.get("operType").toString());
	         String dateCreateStr=(String)reqMap.get("dateCreate");
	         String userCreateStr=(String)reqMap.get("userIdCreate");
	         String name = (String)reqMap.get("name");
	         String openStatus = (String)reqMap.get("openStatus");
	         String storeNo = (String)reqMap.get("storeNo");
	         
	         if(null!=fangyouNoStr && !fangyouNoStr.isEmpty()){
	        	 fangyouAccountDto.setFangyouNo(fangyouNoStr);
	         }
	         if(null!=storeIdStr && !storeIdStr.isEmpty()){
	             Integer storeId=Integer.valueOf(storeIdStr);
	             fangyouAccountDto.setStoreId(storeId);
	         }
	         if(null!=operTypeStr){
	        	 fangyouAccountDto.setOperType(operTypeStr);
	         }
	         if(null!=dateCreateStr && StringUtil.isNotEmpty(dateCreateStr)){
	             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	             try
	             {
	                 Date dateCreate = sdf.parse(dateCreateStr);
	                 fangyouAccountDto.setDateCreate(dateCreate);
	             }
	             catch (ParseException e)
	             {
	                 logger.warn("房友账号绑定日期转换有问题");
	             }
	         }
	         if(null!=userCreateStr && StringUtil.isNotEmpty(userCreateStr)){
	             Integer userCreate=Integer.valueOf(userCreateStr);
	             fangyouAccountDto.setUserIdCreate(userCreate);
	         }
	         if(null!=name && StringUtil.isNotEmpty(name)){
	        	 fangyouAccountDto.setName(name);
	         }
	         if(null!=storeNo && StringUtil.isNotEmpty(storeNo)){
	        	 fangyouAccountDto.setStoreNo(storeNo);
	         }
	         if(null!=openStatus && StringUtil.isNotEmpty(openStatus)){
	        	 Integer openStatus2=Integer.valueOf(openStatus);
	        	 fangyouAccountDto.setOpenStatus(openStatus2);
	         }
	         return fangyouAccountDto;
	     }
	     
		/** 
	    * @Title: getByStoreId 
	    * @Description: 门店房友账号关联表
	    * @param storeId
	    * @return
	    * @throws Exception     
	    */
	    public ResultData<?> getByStoreId(Integer storeId)
	        throws Exception
	    {
	        //调用 接口
	        String url = SystemParam.getWebConfigValue("RestServer") + "fangyouAccount" + "/storeAccount/{storeId}";
	        ResultData<?> reback = get(url, storeId);
	        return reback;
	    }

}
