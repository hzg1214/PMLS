package cn.com.eju.deal.scene.commission.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateInfoDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateSupportDto;
import cn.com.eju.deal.dto.houseLinkage.estate.PhotoDto;

/**   
* Service层
* @author (qianwei)
* @date 2016年4月29日 下午9:30:27
*/
@Service("sceneCommissionService")
public class SceneCommissionService extends BaseService<EstateInfoDto>
{
    
    //private final static String REST_SCENE_COMMISION = SystemCfg.getString("sceneCommissionRestServer");
    
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
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
        String url = SystemParam.getWebConfigValue("RestServer") + "sceneCommission" + "/{id}";
        
        ResultData<?> backResult = get(url, id);
        
        return backResult;
    }

    /** 
     * 查询楼盘名
     * @param id
     * @return
     * @throws Exception
     */
     public ResultData<?> getByEstateId(String estateId)
         throws Exception
     {
         
         //调用 接口
         String url = SystemParam.getWebConfigValue("RestServer") + "sceneCommission" + "/estateId/{estateId}";
         
         ResultData<?> backResult = get(url, estateId);
         
         return backResult;
     }
     
    /** 
     * 
    * 查询-佣金楼盘list
    * @param reqMap
    * @return
    * @throws Exception
    */
    public ResultData<?> sceneCommission(Map<String, Object> reqMap, PageInfo pageInfo)
        throws Exception
    {
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "sceneCommission" + "/qSceneCommission/{param}";
        
        ResultData<?> reback = get(url, reqMap, pageInfo);
        
        return reback;
        
    }
    
    /** 
     * 
    * 查询-佣金明细list
    * @param reqMap
    * @return
    * @throws Exception
    */
    public ResultData<?> sceneCommissionDetail(Map<String, Object> reqMap, PageInfo pageInfo)
        throws Exception
    {
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "sceneCommission" + "/qSceneCommissionDetail/{param}";
        
        ResultData<?> reback = get(url, reqMap, pageInfo);
        
        return reback;
        
    }
    
    /** 
     * 
    * 修改佣金list
    * @param reqMap
    * @return
    * @throws Exception
    */
    public ResultData<?> sceneCommissionDetailModify(Map<String, Object> reqMap)
        throws Exception
    {
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "sceneCommission" + "/qSceneCommissionDetail/modify/{param}";
        
        ResultData<?> reback = get(url, reqMap);
        
        return reback;
        
    }
    
    /** 
     * 
    * 确认结算list
    * @param reqMap
    * @return
    * @throws Exception
    */
    public ResultData<?> sceneCommissionDetailConfirm(Map<String, Object> reqMap)
        throws Exception
    {
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "sceneCommission" + "/qSceneCommissionDetail/confirm/{param}";
        
        ResultData<?> reback = get(url, reqMap);
        
        return reback;
        
    }
   
    /** 
    * 创建
    * @param EstateInfoDto
    * @return
    * @throws Exception
    */
    public ResultData<?> create(Map<String, Object> reqMap)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "sceneCommission" + "";
        EstateInfoDto estateInfoDto=this.SetNewEstateInfoDto(reqMap);
        ResultData<?> backResult = post(url, estateInfoDto);
        return backResult;
    }
    
    /** 
    * 更新
    * @param EstateInfoDto
    * @return
    * @throws Exception
    */
    public void update(Map<String, Object> reqMap)
        throws Exception
    {
        
        String url = SystemParam.getWebConfigValue("RestServer") + "sceneCommission" + "";
        EstateInfoDto estateInfoDto=new EstateInfoDto();
        put(url, estateInfoDto);
        
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
        
        String url = SystemParam.getWebConfigValue("RestServer") + "sceneCommission" + "/{id}/{updateId}";
        
        delete(url, id, updateId);
        
    }
    
    /** 
     * 审核
     * @param EstateInfoDto
     * @return
     * @throws Exception
     */
     public void audit(EstateInfoDto EstateInfoDto)
         throws Exception
     {
         
         String url = SystemParam.getWebConfigValue("RestServer") + "sceneCommission" + "/audit";
         
         put(url, EstateInfoDto);
         
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
          String url = SystemParam.getWebConfigValue("RestServer") + "sceneCommission" + "/check/{param}";
          ResultData<?> backResult =get(url, reqMap);
          return backResult;
      }
      
      /** 
       * @Title: uploadFile 
       * @Description: 文件上传
       * @param request
       * @param reqMap
       * @return     
       */
//       public Map<String, Object> uploadFile(HttpServletRequest request,Map<String, Object> reqMap)throws Exception
//       {
//           Map<String, Object> resultMap=new HashMap<>();
//           ResultData<?> resultData=new ResultData<>();
//           Map<String, Object> upMap=FileHelper.uploadFile(request);
//           List<Map<String, Object>> rspList = (ArrayList<Map<String,Object>>)upMap.get("data");
//           String fileId="";
//           for (Map<String, Object> map : rspList)
//           {
//               String returnCode=(String)map.get("returnCode");
//               if(returnCode==ReturnCode.SUCCESS)
//               {
//                   fileId=(String)map.get("fileCode");
//               }
//               else {
//                   resultData.setFail();
//                   break;
//               }
//           }if(rspList.size()==0){
//               resultData.setFail();
//           }
//           resultMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
//           resultMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
//           if(resultData.getReturnCode().equals(ReturnCode.SUCCESS)){
//               resultMap.put(Constant.RETURN_DATA_KEY, fileId);
//           }
//           return resultMap;
//       }
       
       /** 
    * @Title: SetNewEstateInfoDto 
    * @Description: 新增时的转换
    * @param reqMap
    * @return     
    */
    private EstateInfoDto SetNewEstateInfoDto(Map<String, Object> reqMap){
           UserInfo userInfo=UserInfoHolder.get();
           EstateInfoDto estateInfoDto=new EstateInfoDto();
           //楼盘信息
           EstateDto estateDto=new EstateDto();
           
           return estateInfoDto;
       }
       
       /** 
    * @Title: getMark 
    * @Description: 楼盘标签
    * @param reqMap
    * @return     
    */
    private String getMark(Map<String, Object> reqMap){
           String markStr="";
           List<Integer> indexList=new ArrayList<>();
           for(String key :reqMap.keySet()){
               if(!key.contains("mark")){
                   continue;
               }
               Pattern pattern=Pattern.compile("\\[.*?\\]");// 查找规则公式中大括号以内的字符
               Matcher matcher=pattern.matcher(key);
               while (matcher.find())
               {
                   String iStr=matcher.group().replaceAll("\\[", "");// 去掉左括号
                   iStr=iStr.replaceAll("\\]", "");// 去掉右括号
                   Integer index=Integer.parseInt(iStr);
                   indexList.add(index);
               }
           }
           for (Integer index : indexList)
           {
               String mark=(String)reqMap.get("mark["+index +"]");
               if (!StringUtil.isEmpty(mark))
               {
                   markStr=markStr+mark+",";
               }
           }
           if(StringUtil.isNotEmpty(markStr)){
               markStr=markStr.substring(0, markStr.length()-1);
           }
           return markStr;
       }

       /** 
    * @Title: getLabel 
    * @Description: 户型标签
    * @param reqMap
    * @return     
    */
    private String getLabel(Map<String, Object> reqMap){
           String labelStr="";
           List<Integer> indexList=new ArrayList<>();
           for(String key :reqMap.keySet()){
               if(!key.contains("label")){
                   continue;
               }
               Pattern pattern=Pattern.compile("\\[.*?\\]");// 查找规则公式中大括号以内的字符
               Matcher matcher=pattern.matcher(key);
               while (matcher.find())
               {
                   String iStr=matcher.group().replaceAll("\\[", "");// 去掉左括号
                   iStr=iStr.replaceAll("\\]", "");// 去掉右括号
                   Integer index=Integer.parseInt(iStr);
                   indexList.add(index);
               }
           }
           for (Integer index : indexList)
           {
               String label=(String)reqMap.get("label["+index +"]");
               if (!StringUtil.isEmpty(label))
               {
                   labelStr=labelStr+label+",";
               }
           }
           if(StringUtil.isNotEmpty(labelStr)){
               labelStr=labelStr.substring(0, labelStr.length()-1);
           }
           return labelStr;
       }

       /** 
    * @Title: getTypeKbn 
    * @Description: 建筑类型
    * @param reqMap
    * @return     
    */
    private String getTypeKbn(Map<String, Object> reqMap){
           String typeKbn="";//(String)reqMap.get("typeKbn");//建筑类型
           for(String key :reqMap.keySet()){
               if(key=="typeKbn"){
                   typeKbn=typeKbn+reqMap.get(key)+",";
               }
           }
           if(StringUtil.isNotEmpty(typeKbn)){
               typeKbn=typeKbn.substring(0, typeKbn.length()-1);
           }
           return typeKbn;
       }
    
    /** 
    * @Title: getHouseStair 
    * @Description: 梯户数
    * @param reqMap
    * @return     
    */
    private String getHouseStair(Map<String, Object> reqMap){
        String stairHouse="";
        List<Integer> indexList=new ArrayList<>();
        for(String key :reqMap.keySet()){
            if(!key.contains("staircase")){
                continue;
            }
            Pattern pattern=Pattern.compile("\\[.*?\\]");// 查找规则公式中大括号以内的字符
            Matcher matcher=pattern.matcher(key);
            while (matcher.find())
            {
                String iStr=matcher.group().replaceAll("\\[", "");// 去掉左括号
                iStr=iStr.replaceAll("\\]", "");// 去掉右括号
                Integer index=Integer.parseInt(iStr);
                indexList.add(index);
            }
        }
        for (Integer index : indexList)
        {
            String staircase=(String)reqMap.get("staircase["+index +"]");//梯
            String household = (String)reqMap.get("household["+index +"]");//户
            if (StringUtil.isNotEmpty(staircase) && StringUtil.isNotEmpty(household))
            {
                stairHouse=stairHouse+staircase+"|"+household+",";
            }
        }
        if(StringUtil.isNotEmpty(stairHouse)){
            stairHouse=stairHouse.substring(0, stairHouse.length()-1);
        }
        return stairHouse;
    }
    
    /** 
    * @Title: getPhotos 
    * @Description: 图片
    * @param reqMap
    * @return     
    */
    private List<PhotoDto> getPhotos(Map<String, Object> reqMap){
        List<PhotoDto> filePhoto=new ArrayList<>();
        PhotoDto photoDto=new PhotoDto();
        String houseTypeImgs = (String)reqMap.get("uploadPhotoId6");//在售户型户型图
        String houseTemplateImgs = (String)reqMap.get("uploadPhotoId7");//在售户型样板间
        String estateDesignImgs = (String)reqMap.get("uploadPhotoId1");//楼盘效果图
        String estateTemplateImgs = (String)reqMap.get("uploadPhotoId2");//楼盘样板间
        String estateMapImgs = (String)reqMap.get("uploadPhotoId3");//楼盘地理位置
        String estateDistrictImgs = (String)reqMap.get("uploadPhotoId4");//楼盘区域规划
        String estateRealImgs = (String)reqMap.get("uploadPhotoId5");//楼盘实景图
        String estateCoverImg=(String)reqMap.get("estateCoverImg");//楼盘封面图片ID
        String houseCoverImg=(String)reqMap.get("houseCoverImg");//户型封面图片ID
        //在售户型户型图
        if(StringUtil.isNotEmpty(houseTypeImgs)){
            String[] houseTypeArr=houseTypeImgs.split(",");
            for (int i = 0; i < houseTypeArr.length; i++)
            {
                photoDto.setCrtDt(new Date());
                if(houseTypeArr[i].equals(houseCoverImg)){
                    photoDto.setCoverFlg("Y");
                }else {
                    photoDto.setCoverFlg("N");
                }
                photoDto.setCrtEmpId(UserInfoHolder.getUserId());
                photoDto.setDelFlg(false);
                photoDto.setOrderNo(i+"");
                photoDto.setPhotoId(houseTypeArr[i]);
                photoDto.setPhotoKbn(DictionaryConstants.HOUSE_TYPE_IMG);
                //TODO estateId typeId photoNm photoPath
                filePhoto.add(photoDto);
            }
        }
        //在售户型样板间
        if(StringUtil.isNotEmpty(houseTemplateImgs)){
            String[] houseTemplateArr=houseTemplateImgs.split(",");
            for (int i = 0; i < houseTemplateArr.length; i++)
            {
                photoDto.setCrtDt(new Date());
                if(houseTemplateArr[i].equals(houseCoverImg)){
                    photoDto.setCoverFlg("Y");
                }else {
                    photoDto.setCoverFlg("N");
                }
                photoDto.setCrtEmpId(UserInfoHolder.getUserId());
                photoDto.setDelFlg(false);
                photoDto.setOrderNo(i+"");
                photoDto.setPhotoId(houseTemplateArr[i]);
                photoDto.setPhotoKbn(DictionaryConstants.HOUSE_TEMPLATE_IMG);
                //TODO estateId typeId photoNm photoPath
                filePhoto.add(photoDto);
            }
        }
        //楼盘效果图
        if(StringUtil.isNotEmpty(estateDesignImgs)){
            String[] estateDesignArr=estateDesignImgs.split(",");
            for (int i = 0; i < estateDesignArr.length; i++)
            {
                photoDto.setCrtDt(new Date());
                if(estateDesignArr[i].equals(estateCoverImg)){
                    photoDto.setCoverFlg("Y");
                }else {
                    photoDto.setCoverFlg("N");
                }
                photoDto.setCrtEmpId(UserInfoHolder.getUserId());
                photoDto.setDelFlg(false);
                photoDto.setOrderNo(i+"");
                photoDto.setPhotoId(estateDesignArr[i]);
                photoDto.setPhotoKbn(DictionaryConstants.ESTATE_DESIGN_IMG);
                //TODO estateId typeId photoNm photoPath
                filePhoto.add(photoDto);
            }
        }
      //楼盘样板间
        if(StringUtil.isNotEmpty(estateTemplateImgs)){
            String[] estateTemplateArr=estateTemplateImgs.split(",");
            for (int i = 0; i < estateTemplateArr.length; i++)
            {
                photoDto.setCrtDt(new Date());
                if(estateTemplateArr[i].equals(estateCoverImg)){
                    photoDto.setCoverFlg("Y");
                }else {
                    photoDto.setCoverFlg("N");
                }
                photoDto.setCrtEmpId(UserInfoHolder.getUserId());
                photoDto.setDelFlg(false);
                photoDto.setOrderNo(i+"");
                photoDto.setPhotoId(estateTemplateArr[i]);
                photoDto.setPhotoKbn(DictionaryConstants.ESTATE_TEMPLATE_IMG);
                //TODO estateId typeId photoNm photoPath
                filePhoto.add(photoDto);
            }
        }
      //楼盘地理位置
        if(StringUtil.isNotEmpty(estateMapImgs)){
            String[] estateMapArr=estateMapImgs.split(",");
            for (int i = 0; i < estateMapArr.length; i++)
            {
                photoDto.setCrtDt(new Date());
                if(estateMapArr[i].equals(estateCoverImg)){
                    photoDto.setCoverFlg("Y");
                }else {
                    photoDto.setCoverFlg("N");
                }
                photoDto.setCrtEmpId(UserInfoHolder.getUserId());
                photoDto.setDelFlg(false);
                photoDto.setOrderNo(i+"");
                photoDto.setPhotoId(estateMapArr[i]);
                photoDto.setPhotoKbn(DictionaryConstants.ESTATE_MAP_IMG);
                //TODO estateId typeId photoNm photoPath
                filePhoto.add(photoDto);
            }
        }
      //楼盘区域规划
        if(StringUtil.isNotEmpty(estateDistrictImgs)){
            String[] estateDistrictArr=estateDistrictImgs.split(",");
            for (int i = 0; i < estateDistrictArr.length; i++)
            {
                photoDto.setCrtDt(new Date());
                if(estateDistrictArr[i].equals(estateCoverImg)){
                    photoDto.setCoverFlg("Y");
                }else {
                    photoDto.setCoverFlg("N");
                }
                photoDto.setCrtEmpId(UserInfoHolder.getUserId());
                photoDto.setDelFlg(false);
                photoDto.setOrderNo(i+"");
                photoDto.setPhotoId(estateDistrictArr[i]);
                photoDto.setPhotoKbn(DictionaryConstants.ESTATE_DISTRICT_IMG);
                //TODO estateId typeId photoNm photoPath
                filePhoto.add(photoDto);
            }
        }
      //楼盘实景图
        if(StringUtil.isNotEmpty(estateRealImgs)){
            String[] estateRealArr=estateRealImgs.split(",");
            for (int i = 0; i < estateRealArr.length; i++)
            {
                photoDto.setCrtDt(new Date());
                if(estateRealArr[i].equals(estateCoverImg)){
                    photoDto.setCoverFlg("Y");
                }else {
                    photoDto.setCoverFlg("N");
                }
                photoDto.setCrtEmpId(UserInfoHolder.getUserId());
                photoDto.setDelFlg(false);
                photoDto.setOrderNo(i+"");
                photoDto.setPhotoId(estateRealArr[i]);
                photoDto.setPhotoKbn(DictionaryConstants.ESTATE_REAL_IMG);
                //TODO estateId typeId photoNm photoPath
                filePhoto.add(photoDto);
            }
        }
        return filePhoto;
    }
    
    /** 
    * @Title: getSupportDtos 
    * @Description: 周边配套
    * @param reqMap
    * @return     
    */
    private List<EstateSupportDto> getSupportDtos(Map<String, Object> reqMap){
        List<EstateSupportDto> estateSupportDetails=new ArrayList<>();
        EstateSupportDto estateSupportDto=new EstateSupportDto();
        List<Integer> indexList=new ArrayList<>();
        for(String key :reqMap.keySet()){
            if(!key.contains("description")){
                continue;
            }
            Pattern pattern=Pattern.compile("\\[.*?\\]");// 查找规则公式中大括号以内的字符
            Matcher matcher=pattern.matcher(key);
            while (matcher.find())
            {
                String iStr=matcher.group().replaceAll("\\[", "");// 去掉左括号
                iStr=iStr.replaceAll("\\]", "");// 去掉右括号
                Integer index=Integer.parseInt(iStr);
                indexList.add(index);
            }
        }
        for (Integer index : indexList)
        {
            estateSupportDto=new EstateSupportDto();
            String description=(String)reqMap.get("description["+index +"]");//梯
            String title = (String)reqMap.get("title["+index +"]");//户
            if (StringUtil.isNotEmpty(description)){
                estateSupportDto.setDescription(description);
            }
            if(StringUtil.isNotEmpty(title))
            {
                estateSupportDto.setTitle(title);
            }
            estateSupportDto.setCrtDt(new Date());
            estateSupportDto.setCrtEmpId(UserInfoHolder.getUserId());
            estateSupportDto.setDelFlg(false);
            //TODO estateId
            estateSupportDetails.add(estateSupportDto);
        }
        
        return estateSupportDetails;
    }
    
    private Date stringToDate(String dateStr){
        Date date=null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        try
        {
            date = sdf.parse(dateStr);
        }
        catch (ParseException e)
        {
            logger.warn("楼盘新增页面日期转换有问题");
        }
        return date;
    }

}
