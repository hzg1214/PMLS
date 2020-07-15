package cn.com.eju.deal.store.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.seqNo.service.SeqNoService;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.service.FileRecordMainService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.SystemCfg;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import cn.com.eju.deal.dto.store.StoreDto;

/** 
* @ClassName: StoreService 
* @Description: 门店service
* @author 陆海丹 
* @date 2016年3月24日 下午2:18:04 
*/
@Service("storeService")
public class StoreService extends BaseService<StoreDto>
{
//    private final static String REST_SERVICE = SystemCfg.getString("storeRestServer");
//    private final String REST_SERVICE = SystemParam.getWebConfigValue("RestServer") + "store";
    
    private final static String SYS_NAME = SystemCfg.getString("systemName");
    
    private final static Integer INSERT = 1;
    
    private final static Integer UPDATE = 2;
    
    @Resource(name = "fileService")
    private FileRecordMainService fileRecordMainService;
    
    @Resource(name = "seqNoService")
    private SeqNoService seqNoService;
    
    @Resource(name = "storeMaintainerService")
    private StoreMaintainerService storeMaintainerService;
    
    /** 
    * @Title: queryList 
    * @Description: 门店列表
    * @param reqMap
    * @param pageInfo
    * @return
    * @throws Exception     
    */
    public List<?> queryList(Map<String, Object> reqMap, PageInfo pageInfo)
        throws Exception
    {
        List<?> contentlist = new ArrayList<>();
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "store" + "/q/{param}";
        //url = "http://localhost:8001/PMLSService/" + "store" + "/q/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        contentlist = (List<?>)reback.getReturnData();
        return contentlist;
    }
    
    /** 
     * @Title: queryList 
     * @Description: 门店列表
     * @param reqMap
     * @param pageInfo
     * @return
     * @throws Exception     
     */
    public ResultData<?> queryRelatelist(Map<String, Object> reqMap)
        throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "store" + "/qr/{param}";
        ResultData<?> backResult = get(url, reqMap);
        return backResult;
    }

    //Add By NingChao 2017/04/07 Start
      /**
       * @Title: queryList
       * @Description: 门店列表
       * @param reqMap
       * @param pageInfo
       * @return
       * @throws Exception
       */
      public ResultData<?> queryRelatelistByRenew(Map<String, Object> reqMap)
          throws Exception
      {
          //调用 接口
          String url = SystemParam.getWebConfigValue("RestServer") + "store" + "/qrRenew/{param}";
          ResultData<?> backResult = get(url, reqMap);
          return backResult;
      }
    //Add By NingChao 2017/04/07 End

    /** 
     * @Title: querylistByCompanyId 
     * @Description: 通过公司ID查找关联门店列表
     * @param reqMap
     * @param pageInfo
     * @return
     * @throws Exception     
     */
    public List<?> querylistByCompanyId(Map<String, Object> reqMap, PageInfo pageInfo)
        throws Exception
    {
        List<?> contentlist = new ArrayList<>();
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "store" + "/qCompanyId/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        contentlist = (List<?>)reback.getReturnData();
        return contentlist;
    }

    /**
     * 获取门店基本信息
     * @param id
     * @return
     * @throws Exception
     */
    public ResultData<?> getbriefById(int id) throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "store" + "/brief/{id}/{userId}";//"http://localhost:8001/PMLSService/store" + "/brief/{id}/{userId}";//
        ResultData<?> backResult = get(url, id, UserInfoHolder.getUserId());
        return backResult;
    }

    /**
     * @Title: getById
     * @Description: 跟进编号查询门店详情
     * @param id
     * @return
     * @throws Exception
     */
    public ResultData<?> getById(int id)
            throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "store" + "/{id}/{userId}";
        //url = "http://localhost:8001/PMLSService/" + "store" + "/{id}/{userId}";
        ResultData<?> backResult = get(url, id, UserInfoHolder.getUserId());
        return backResult;
    }

    /**
     *
     * @param id 根据门店ID查询收款
     * @return
     * @throws Exception
     */
    public ResultData<?> getReceivedMoneyByStoreId(int storeId) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "store/receive/" + storeId;
        ResultData<?> backResult = get(url);
        return backResult;
    }

    /** 
    * @Title: create 
    * @Description: 门店新增
    * @param storeDto
    * @return
    * @throws Exception     
    */
    @SuppressWarnings("unchecked")
    public ResultData<?> create(Map<String, Object> reqMap)
        throws Exception
    {
        ResultData<?> backResult = new ResultData<>();
        String url =  SystemParam.getWebConfigValue("RestServer") + "store" + "";
        //Attention!保持门店新增页面和门店更新页面的字段命名一致！
        StoreDto storeDto = this.reqMapToStoreDto(reqMap, INSERT);
        storeDto.setUserCreate(UserInfoHolder.getUserId());
        //门店编号
        ResultData<?> resultData = this.seqNoService.getSeqNo("TYPE_STORE");
        storeDto.setStoreNo((String)resultData.getReturnData());
/*        //门店拼音
        if (StringUtil.isNotEmpty(storeDto.getName()))
        {
            PinyinUtil pinyinUtil = new PinyinUtil();
            try
            {
                String namePinyin = pinyinUtil.toPinYin(storeDto.getName());
                storeDto.setNamePinyin(namePinyin);
            }
            catch (BadHanyuPinyinOutputFormatCombination e)
            {
                logger.warn("company create setDto pinyinUtil.toPinYin failure");
            }
        }*/
        storeDto.setInputSource(SYS_NAME);
        // 新增门店时 装修状态 默认为 ：未装修
        storeDto.setDecorationState(16301);
        // 新增门店时 默认维护人为当前登录人 维护时间为门店创建时间
        UserInfo userInfo = UserInfoHolder.get();
        storeDto.setMaintainer(userInfo.getUserCode());
        storeDto.setMaintainerName(userInfo.getUserName());
        backResult = post(url, storeDto);
        if (backResult.getReturnCode().equals(ReturnCode.SUCCESS))
        {
            Map<String, Object> storeDtoMap = (Map<String, Object>)backResult.getReturnData();
            if (null != (String)reqMap.get("fileRecordMainId") && !"".equals((String)reqMap.get("fileRecordMainId")))
            {
                FileRecordMainDto fileRecordMainDto = new FileRecordMainDto();
                fileRecordMainDto.setRefId((Integer)storeDtoMap.get("storeId"));
                fileRecordMainDto.setFileRecordMainId(Integer.valueOf((String)reqMap.get("fileRecordMainId")));
                fileRecordMainService.update(fileRecordMainDto);
            }
        }
        return backResult;
    }
    
    /** 
    * @Title: update 
    * @Description: 更新门店信息
    * @param storeDto
    * @throws Exception     
    */
    public void update(Map<String, Object> reqMap)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "store" + "";
        //Attention!保持门店新增页面和门店更新页面的字段命名一致！
        StoreDto storeDto = this.reqMapToStoreDto(reqMap, UPDATE);
        storeDto.setUserUpdate(UserInfoHolder.getUserId());
        storeDto.setDateUpdate(new Date());


        String storeManager = (String)reqMap.get("storeManager");
        storeDto.setStoreManager(storeManager);
        String storeManagerPhone = (String)reqMap.get("storeManagerPhone");
        storeDto.setStoreManagerPhone(storeManagerPhone);
        //linkageSituation
        String linkageSituation = (String)reqMap.get("linkageSituation");
        storeDto.setLinkageSituation(linkageSituation);
        //chainBrand
        String chainBrand = (String)reqMap.get("chainBrand");
        storeDto.setChainBrand(chainBrand);
        //storePersonNum
        String storePersonNum = (String)reqMap.get("storePersonNum");
        storeDto.setStorePersonNum(storePersonNum);
        //nowUserSystem
        String nowUserSystem = (String)reqMap.get("nowUserSystem");
        storeDto.setNowUserSystem(nowUserSystem);
        //storeDueTime
        String storeDueTime = (String)reqMap.get("storeDueTime");
        storeDto.setStoreDueTime(storeDueTime);
        //storeLeaseDueTime
        String storeLeaseDueTime = (String)reqMap.get("storeLeaseDueTime");
        storeDto.setStoreLeaseDueTime(storeLeaseDueTime);
        //mainBusiness
        String mainBusiness = (String)reqMap.get("mainBusiness");
        if(mainBusiness!=null && !"".equals(mainBusiness)){
            mainBusiness=mainBusiness.replaceAll(";",",");
        }
        storeDto.setMainBusiness(mainBusiness);
        //transactionMode
        String transactionMode = (String)reqMap.get("transactionMode");
        storeDto.setTransactionMode(transactionMode);

        String storePicListJson = (String)reqMap.get("storePicListJson");
        storeDto.setStorePicListJson(storePicListJson);

        String pictureRefId = (String)reqMap.get("pictureRefId");
        storeDto.setPictureRefId(pictureRefId);
        
        if(reqMap.get("bToAAlert") != null) {
            if("2".equals(reqMap.get("bToAAlert").toString())) {
                storeDto.setBToAAlert(2);
                storeDto.setBToAAlertDesc(reqMap.get("bToAAlertDesc").toString().trim());
            }else {
                storeDto.setBToAAlert(1);
                storeDto.setBToAAlertDesc("");
            }
        }
        if(reqMap.containsKey("businessPlaceType")) {
        	String businessPlaceType = (String)reqMap.get("businessPlaceType");
        	storeDto.setBusinessPlaceType(Integer.valueOf(businessPlaceType));
        }
        //原有字段得门店规模指的是公司规模，门店规模新增字段
        if(reqMap.containsKey("storeSizeScale")) {
        	String storeSizeScale = (String)reqMap.get("storeSizeScale");
        	storeDto.setStoreSizeScale(Integer.valueOf(storeSizeScale));
        }
        put(url, storeDto);
        //更新图片
        if (null != (String)reqMap.get("fileRecordMainId") && !"".equals((String)reqMap.get("fileRecordMainId")))
        {
            FileRecordMainDto fileRecordMainDto = new FileRecordMainDto();
            fileRecordMainDto.setRefId(storeDto.getStoreId());
            fileRecordMainDto.setFileRecordMainId(Integer.valueOf((String)reqMap.get("fileRecordMainId")));
            fileRecordMainService.update(fileRecordMainDto);
        }
    }
    
    /** 
    * @Title: delete 
    * @Description: 删除门店
    * @param id
    * @throws Exception     
    */
    public void delete(int id)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "store" + "/{id}";
        delete(url, id);
    }
    
    /** 
     * @Title: create
     * @Description: 插入门店关联的信息
     * @param storeDto
     * @param type 1:维护人 2:装修状况 3:保证金 4.装修翻牌费
     * @throws Exception     
     */
    public void createStoreRelateInfo(Map<String, Object> reqMap, String storeId, String type)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "store" + "";
        StoreDto storeDto = new StoreDto();
        StoreDto dbStore = null;
        Integer storeIdInt = Integer.valueOf(storeId);
        ResultData<?> resultData = this.getById(storeIdInt);
        Map<?, ?> mapTemp = (Map<?, ?>)resultData.getReturnData();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (null != mapTemp)
        {
            BigDecimal decoractionFeeOneDb = null;
            BigDecimal decoractionFeeTwoDb = null;
            BigDecimal depositDb = null;
            Object decoractionFeeOneObj = mapTemp.get("decoractionFeeOne");
            Object decoractionFeeTwoObj = mapTemp.get("decoractionFeeTwo");
            Object depositObj = mapTemp.get("deposit");
            if(null != decoractionFeeOneObj) {
                decoractionFeeOneDb = new BigDecimal(decoractionFeeOneObj.toString());
            }
            if(null != decoractionFeeTwoObj) {
                decoractionFeeTwoDb = new BigDecimal(decoractionFeeTwoObj.toString());
            }
            if(null != depositObj) {
                depositDb = new BigDecimal(depositObj.toString());
            }
            dbStore = MapToEntityConvertUtil.convert(mapTemp, StoreDto.class, "");
            dbStore.setDecoractionFeeOne(decoractionFeeOneDb);
            dbStore.setDecoractionFeeTwo(decoractionFeeTwoDb);
            dbStore.setDeposit(depositDb);
        }
        BeanUtils.copyProperties(dbStore, storeDto);
        if (type.equals("1"))
        { //维护人
            // 新编辑的维护人
            String maintainer = (String)reqMap.get("maintainer");
            String maintainerName = (String)reqMap.get("maintainerName");
            if (StringUtil.isNotEmpty(maintainer))
            {
                storeDto.setMaintainer(maintainer);
            }
            if (StringUtil.isNotEmpty(maintainerName))
            {
                storeDto.setMaintainerName(maintainerName);
            }
        }
        if (type.equals("2"))
        { //装修状况
            setDecorationStateInfo(reqMap,storeDto,format);
        }
        if (type.equals("3"))
        { //保证金
            // 新编辑的保证金
            String deposit = (String)reqMap.get("deposit");
            String dateAccountDeposit = (String)reqMap.get("dateAccountDeposit");
            if (StringUtil.isNotEmpty(deposit))
            {
                storeDto.setDeposit(new BigDecimal(deposit));
            }
            if (StringUtil.isNotEmpty(dateAccountDeposit))
            {
                storeDto.setDateAccountDeposit(format.parse(dateAccountDeposit));
            }
        }
        if (type.equals("4"))
        { //装修翻牌费
            setDecorationFeeInfo(reqMap,storeDto,format);
        }
        storeDto.setDateUpdate(new Date());
        post(url, storeDto);
        
    }
    /** 
     * @Title: update 
     * @Description: 更新门店关联的信息
     * @param storeDto
     * @param type 1:维护人 2:装修状况 3:保证金 4.装修翻牌费
     * @throws Exception     
     */
    public void updateStoreRelateInfo(Map<String, Object> reqMap, String storeId, String type)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "store" + "";
        StoreDto storeDto = new StoreDto();
        StoreDto dbStore = null;
        Integer storeIdInt = Integer.valueOf(storeId);
        ResultData<?> resultData = this.getById(storeIdInt);
        Map<?, ?> mapTemp = (Map<?, ?>)resultData.getReturnData();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (null != mapTemp)
        {
            BigDecimal decoractionFeeOneDb = null;
            BigDecimal decoractionFeeTwoDb = null;
            BigDecimal depositDb = null;
            Object decoractionFeeOneObj = mapTemp.get("decoractionFeeOne");
            Object decoractionFeeTwoObj = mapTemp.get("decoractionFeeTwo");
            Object depositObj = mapTemp.get("deposit");
            if(null != decoractionFeeOneObj) {
                decoractionFeeOneDb = new BigDecimal(decoractionFeeOneObj.toString());
            }
            if(null != decoractionFeeTwoObj) {
                decoractionFeeTwoDb = new BigDecimal(decoractionFeeTwoObj.toString());
            }
            if(null != depositObj) {
                decoractionFeeTwoDb = new BigDecimal(depositObj.toString());
            }
            dbStore = MapToEntityConvertUtil.convert(mapTemp, StoreDto.class, "");
            dbStore.setDecoractionFeeOne(decoractionFeeOneDb);
            dbStore.setDecoractionFeeTwo(decoractionFeeTwoDb);
            dbStore.setDeposit(depositDb);
        }
        BeanUtils.copyProperties(dbStore, storeDto);
        if (type.equals("1"))
        { //维护人
            // 新编辑的维护人
            String maintainer = (String)reqMap.get("maintainer");
            String maintainerName = (String)reqMap.get("maintainerName");
            if (StringUtil.isNotEmpty(maintainer))
            {
                storeDto.setMaintainer(maintainer);
            }
            if (StringUtil.isNotEmpty(maintainerName))
            {
                storeDto.setMaintainerName(maintainerName);
            }
        }
        if (type.equals("2"))
        { //装修状况
            setDecorationStateInfo(reqMap,storeDto,format);
        }
        if (type.equals("3"))
        { //保证金
            // 新编辑的保证金
            String deposit = (String)reqMap.get("deposit");
            String dateAccountDeposit = (String)reqMap.get("dateAccountDeposit");
            if (StringUtil.isNotEmpty(deposit))
            {
                storeDto.setDeposit(new BigDecimal(deposit));
            }
            if (StringUtil.isNotEmpty(dateAccountDeposit))
            {
                storeDto.setDateAccountDeposit(format.parse(dateAccountDeposit));
            }
        }
        if (type.equals("4"))
        { //装修翻牌费
            setDecorationFeeInfo(reqMap,storeDto,format);
        }
        storeDto.setDateUpdate(new Date());
        put(url, storeDto);
    }
    
    /**
     * 更新维护人到门店表
     * @param reqMap
     * @return
     * @throws Exception
     */
    public void updateMtcToStore(StoreDto storeDto) throws Exception{
    	//调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "store" + "/uMtcToStore";
    	put(url, storeDto);
    }
    /*-------------------------------------------------------private function-------------------------------------------------------*/
    private StoreDto reqMapToStoreDto(Map<String, Object> reqMap, int type)
    {
        StoreDto storeDto = new StoreDto();
        String storeIdStr = (String)reqMap.get("storeId");
        String storeNo = (String)reqMap.get("storeNo");
        String name = (String)reqMap.get("name");
        String longitudeStr = (String)reqMap.get("longitude");
        String latitudeStr = (String)reqMap.get("latitude");
        String cityNo = (String)reqMap.get("cityNo");
        String address = (String)reqMap.get("address");
        String districtNo = (String)reqMap.get("districtNo");
        String areaNo = (String)reqMap.get("areaNo");
		String abType =  (String) reqMap.get("abTypeStore");
        
        
        String storeStatusStr = (String)reqMap.get("storeStatus");
        //Add By NingChao 2017/04/07 Start
        //是否续签
        String neededRenew = (String)reqMap.get("neededRenew");
        if(null != neededRenew && StringUtil.isNotEmpty(neededRenew))
        {
        	storeDto.setNeededRenew(Integer.valueOf(neededRenew));
        }
        //Add By NingChao 2017/04/07 End
        //分店名称
        String subName = (String)reqMap.get("subName");
        if(StringUtil.isNotEmpty(name)){
        	name = name.trim();
        }
        if(StringUtil.isNotEmpty(subName)){
        	subName = subName.trim();
        }
        
        //门店联系人
        String contacts = (String)reqMap.get("contacts");
        //门店规模
        String storeScale = (String)reqMap.get("storeScale");
        
        if (null != subName && StringUtil.isNotEmpty(subName))
        {
            storeDto.setSubName(subName);
        }
        if (null != contacts && StringUtil.isNotEmpty(contacts))
        {
            storeDto.setContacts(contacts);
        }
        if (null != storeScale && StringUtil.isNotEmpty(storeScale))
        {
            storeDto.setStoreScale(Integer.valueOf(storeScale));
        }
        
        if (null != storeIdStr && StringUtil.isNotEmpty(storeIdStr))
        {
            storeDto.setStoreId(Integer.valueOf(storeIdStr));
        }
        if (null != storeNo && StringUtil.isNotEmpty(storeNo))
        {
            storeDto.setStoreNo(storeNo);
        }
        if (null != name && StringUtil.isNotEmpty(name))
        {
            storeDto.setName(name);
        }
        if (null != longitudeStr && StringUtil.isNotEmpty(longitudeStr))
        {
            BigDecimal longitude = new BigDecimal(longitudeStr);
            storeDto.setLongitude(longitude);
        }
        else
        {
            storeDto.setLongitude(BigDecimal.ZERO);
        }
        if (null != latitudeStr && StringUtil.isNotEmpty(latitudeStr))
        {
            BigDecimal latitude = new BigDecimal(latitudeStr);
            storeDto.setLatitude(latitude);
        }
        else
        {
            storeDto.setLatitude(BigDecimal.ZERO);
        }
        if (null != cityNo && StringUtil.isNotEmpty(cityNo))
        {
            storeDto.setCityNo(cityNo);
        }
        if (null != address && StringUtil.isNotEmpty(address))
        {
            storeDto.setAddress(address);
        }
        if (null != districtNo && StringUtil.isNotEmpty(districtNo))
        {
            storeDto.setDistrictNo(districtNo);
        }
        if (null != areaNo && StringUtil.isNotEmpty(areaNo))
        {
            storeDto.setAreaNo(areaNo);
        }
        
        if (null != storeStatusStr && StringUtil.isNotEmpty(storeStatusStr))
        {
            storeDto.setStoreStatus(Integer.valueOf(storeStatusStr));
        }
        
        //门店资质等级
        if (null != abType && StringUtil.isNotEmpty(abType))
        {
            storeDto.setABTypeStore(Integer.valueOf(abType));
        }
        
        return storeDto;
    }
    
    /**
     * 
     * 翻牌费 组装更新的值
     * @param reqMap
     * @param storeDto
     * @param format
     * @throws ParseException
     */
    private void setDecorationFeeInfo(Map<String, Object> reqMap,StoreDto storeDto, DateFormat format) throws ParseException {
        // 新编辑的装修翻牌费1
        String decoractionFeeOne = (String)reqMap.get("decoractionFeeOne");
        // 新编辑的装修翻牌费2
        String decoractionFeeTwo = (String)reqMap.get("decoractionFeeTwo");
        // 支付日期1
        String datePayDcrtFeeOne = (String)reqMap.get("datePayDcrtFeeOne");
        // 支付日期2
        String datePayDcrtFeeTwo = (String)reqMap.get("datePayDcrtFeeTwo");
        // 请款单号1
        String pleasePayNoOne = (String)reqMap.get("pleasePayNoOne");
        // 请款单号2
        String pleasePayNoTwo = (String)reqMap.get("pleasePayNoTwo");
        
        storeDto.setDecoractionFeeOne(new BigDecimal(decoractionFeeOne));
        storeDto.setPleasePayNoOne(pleasePayNoOne);
        
        storeDto.setDatePayDcrtFeeOne(format.parse(datePayDcrtFeeOne));
        
        if (StringUtil.isNotEmpty(decoractionFeeTwo)){
            storeDto.setDecoractionFeeTwo(new BigDecimal(decoractionFeeTwo));
        }else{
            storeDto.setDecoractionFeeTwo(null);
        }
        if (StringUtil.isNotEmpty(datePayDcrtFeeTwo)){
            storeDto.setDatePayDcrtFeeTwo(format.parse(datePayDcrtFeeTwo));
        }else{
            storeDto.setDatePayDcrtFeeTwo(null);
        }
        if (StringUtil.isNotEmpty(pleasePayNoTwo)){
            storeDto.setPleasePayNoTwo(pleasePayNoTwo);
        }else{
            storeDto.setPleasePayNoTwo(null);
        }
    }
    
    /**
     * 
     * 装修状态 组装更新的值
     * @param reqMap
     * @param storeDto
     * @param format
     * @throws ParseException
     */
    private void setDecorationStateInfo(Map<String, Object> reqMap,StoreDto storeDto, DateFormat format) throws ParseException {
        // 新编辑的门店装修状况
        String decorationState = (String)reqMap.get("decorationState");
        // 装修公司名称
        String decorationCompNm = (String)reqMap.get("decorationCompNm");
        // 装修合同会签单号
        String decorationContractNo = (String)reqMap.get("decorationContractNo");
        // 装修发票开具日期
        String dateDecorationBill = (String)reqMap.get("dateDecorationBill");
        // OA翻牌验收单号
        String oaFlopNo = (String)reqMap.get("oaFlopNo");
        // 翻牌验收通过日期
        String dateFlopCkAccept = (String)reqMap.get("dateFlopCkAccept");
        if (StringUtil.isNotEmpty(decorationState)){
            storeDto.setDecorationState(Integer.valueOf(decorationState));
        }
        
        if (StringUtil.isNotEmpty(decorationCompNm)) {
            storeDto.setDecorationCompNm(decorationCompNm);
        } else {
            storeDto.setDecorationCompNm(null);
        }
        
        if (StringUtil.isNotEmpty(decorationContractNo)) {
            storeDto.setDecorationContractNo(decorationContractNo);
        } else {
            storeDto.setDecorationContractNo(null);
        }
        
        if (StringUtil.isNotEmpty(dateDecorationBill)) {
            storeDto.setDateDecorationBill(format.parse(dateDecorationBill));
        } else {
            storeDto.setDateDecorationBill(null);
        }
        
        if (StringUtil.isNotEmpty(oaFlopNo)) {
            storeDto.setOaFlopNo(oaFlopNo);
        } else {
            storeDto.setOaFlopNo(null);
        }
        
        if (StringUtil.isNotEmpty(dateFlopCkAccept)) {
            storeDto.setDateFlopCkAccept(format.parse(dateFlopCkAccept));
        } else {
            storeDto.setDateFlopCkAccept(null);
        }
    }
    /*-------------------------------------------------------private function-------------------------------------------------------*/

    /** 
    * @Title: getEditBanFlag 
    * @Description: 获取是否编辑限制状态
    * @param storeId
    * @throws Exception     
    */
    public boolean getEditBanFlag(int storeId)
        throws Exception
    {
        String url = SystemParam.getWebConfigValue("RestServer") + "store/getEditBanFlag/" +storeId;
        ResultData<?> reback = get(url);
        Boolean banFlag = (Boolean) reback.getReturnData();
        return banFlag;
    }

	//Add By WangLei 2017/04/07 Start
    /**
     * @Title: updateNeededRenewFlag
     * @Description: 更新是否无需续签状态
     * @param reqMap
     * @param storeId
     * @param neededRenew
     * @throws Exception
     */
    public ResultData<?> updateNeededRenewFlag(Integer storeId,Integer neededRenew)  throws Exception
	{
    	 String url = SystemParam.getWebConfigValue("RestServer") + "store/renew/{storeId}/{neededRenew}";
    	  ResultData<?> reback = get(url,storeId,neededRenew);
          return reback;
	}

	// Add By WangLei 2017/04/07 End

	/**
	 * 分配维护人时 check选择的维护人是否是当前维护人
	 * 
	 * @param storeId,maitainer
	 * @return
	 * @throws Exception
	 */
	public boolean checkMtner(Map<String, Object> reqMap) throws Exception {
		String url = SystemParam.getWebConfigValue("RestServer") + "store" + "/checkmtner/{param}";
		
		ResultData<?> reback = get(url,reqMap);
		Boolean banFlag = (Boolean) reback.getReturnData();
		return banFlag;
	}
	
    /** 
    * @Title: getStoreById 
    * @Description: 根据编号查询门店详情
    * @param id
    * @return
    * @throws Exception     
    */
    public ResultData<?> getStoreById(int id)
        throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "store" + "/{id}";
        ResultData<?> backResult = get(url, id, UserInfoHolder.getUserId());
        return backResult;
    }
	
    
    /**
     * 修改OMS装修记录的门店地址
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData<?> UpdateOmsStoreAdress(Map<String, Object> reqMap)  throws Exception
	{
    	 String url = SystemParam.getWebConfigValue("RestServer") + "store/updateOmsAdress/{param}";
    	  ResultData<?> reback = get(url,reqMap);
          return reback;
	}
    
  //Add By cning 2017/07/10 Start
    /**
     * 获取门店审核状态
     * @param URL
     * @param fileName
     * @param file
     * @return
     */
    public ResultData<?> getAuditStatus(Integer storeId)  throws Exception {
    	 String url = SystemParam.getWebConfigValue("RestServer") + "store" + "/status/{storeId}";
 		
 		ResultData<?> reback = get(url,storeId);
        return reback;
    }
    //Add By cning 2017/07/10 End

    /**
     * 查看门店修改日志
     * @param storeId
     * @return
     * @throws Exception
     */
    public ResultData<?> getStoreLogList(Map<String, Object> reqMap,PageInfo pageInfo) throws Exception
    {
    	String url = SystemParam.getWebConfigValue("RestServer") + "store/findStoreLogList/{param}";
    	 ResultData<?> reback = get(url,reqMap,pageInfo);
         return reback;
    }
    
    
 //Add By QJP 2017/07/12 Start    
    /** 
     * @Title: updateD 
     * @Description: 更新门店店招,地址,营业执照图片同时插入一条更新记录信息
     * @param storeDto
     * @throws Exception     
     */
     public void updateD(StoreDto storeDto)
         throws Exception
     {
    	 //String url = SystemParam.getWebConfigValue("RestServer") + "store/1" + "";
        String url = SystemParam.getWebConfigValue("RestServer") + "store/updateS/" + ""; 
         put(url, storeDto);
         //更新图片
//         if (null != (String)reqMap.get("fileRecordMainId") && !"".equals((String)reqMap.get("fileRecordMainId")))
//         {
//             FileRecordMainDto fileRecordMainDto = new FileRecordMainDto();
//             fileRecordMainDto.setRefId(storeDto.getStoreId());
//             fileRecordMainDto.setFileRecordMainId(Integer.valueOf((String)reqMap.get("fileRecordMainId")));
//             fileRecordMainService.update(fileRecordMainDto);
//         }
    }
 //Add By QJP 2017/07/12 End

     /**
      * check门店地址是否重复
      * @param storeDto
     * @throws Exception 
      */
     public ResultData<?> checkAddress(Map<String,Object> param) throws Exception
     {	 
    	String url = SystemParam.getWebConfigValue("RestServer") + "store/check/{param}";
		ResultData<?> resultData = get(url,param);
    	return resultData;
     }

     /** 
      * @Title: queryCompanyStore 
      * @Description: 通过公司ID查找未关联门店列表
      * @param reqMap
      * @param pageInfo
      * @return
      * @throws Exception     
      */
     public List<?> queryCompanyStore(Map<String, Object> reqMap, PageInfo pageInfo)
         throws Exception
     {
         List<?> contentlist = new ArrayList<>();
         //调用 接口
         String url = SystemParam.getWebConfigValue("RestServer") + "store" + "/companyStore/{param}";
         ResultData<?> reback = get(url, reqMap, pageInfo);
         contentlist = (List<?>)reback.getReturnData();
         return contentlist;
     }

    public ResultData<?> checkBToAAlert(int id) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "store/bToAAlert/{id}";
        return get(url, id);
    }

    public ResultData queryServiceFrameContract(Integer storeId) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "store/queryServiceFrameContract/{storeId}";
        return get(url, storeId);
    }

    /**
     * 判断门店资质等级
     * @param param
     * @return
     * @throws Exception
     */
    public ResultData<?> checkGrade(Map<String, Object> param) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "store/checkGrade/{param}";
        ResultData<?> resultData = get(url,param);
        return resultData;
    }
    
    /** 
     * @Title: querylistByCompanyId 
     * @Description: 通过公司ID查找关联门店列表
     * @param reqMap
     * @param pageInfo
     * @return
     * @throws Exception     
     */
    public List<?> querygplistByCompanyId(Map<String, Object> reqMap, PageInfo pageInfo)
        throws Exception
    {
        List<?> contentlist = new ArrayList<>();
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "store" + "/qCompanyIdgp/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        contentlist = (List<?>)reback.getReturnData();
        return contentlist;
    }
    
    /** 
     * @Title: queryCompanyStore 
     * @Description: 通过公司ID查找未关联门店列表
     * @param reqMap
     * @param pageInfo
     * @return
     * @throws Exception     
     */
    public List<?> querygpCompanyStore(Map<String, Object> reqMap, PageInfo pageInfo)
        throws Exception
    {
        List<?> contentlist = new ArrayList<>();
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "store" + "/gpcompanyStore/{param}";
        ResultData<?> reback = get(url, reqMap, pageInfo);
        contentlist = (List<?>)reback.getReturnData();
        return contentlist;
    }

    public ResultData<?> queryGpRelatelist(Map<String, Object> reqMap)
            throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "store" + "/gpqr/{param}";
        ResultData<?> backResult = get(url, reqMap);
        return backResult;
    }

    public ResultData<?> getPlainInfoById(Integer storeId) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "store" + "/getPlainInfoById/{id}";
        ResultData<?> backResult = get(url, storeId);
        return backResult;
    }
}
