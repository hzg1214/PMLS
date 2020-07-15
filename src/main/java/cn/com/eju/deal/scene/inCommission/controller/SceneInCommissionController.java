package cn.com.eju.deal.scene.inCommission.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STUnsignedShortHex;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.linkage.service.LinkageCityService;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;

import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.common.util.FileUtils;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.houseLinkage.estate.service.EstateService;
import cn.com.eju.deal.poi.ExcelForInCommision;
import cn.com.eju.deal.scene.inCommission.service.SceneInCommissionService;

/**   
* Controller层
* @author sucen
* @date 2017年8月7日 下午9:25:30
*/
@Controller
@RequestMapping(value = "sceneInCommission")
public class SceneInCommissionController extends BaseController
{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "sceneInCommissionService")
    private SceneInCommissionService sceneInCommissionService;
    
    @Resource(name = "linkageCityService")
    private LinkageCityService linkageCityService;
    
    @Resource(name = "estateService")
    private EstateService estateService;

    //模板类型
    private String templateType= "";
    /** 
     * 初始化
     * @param request
     * @param model
     * @return
     */
     @RequestMapping(value = "", method = RequestMethod.GET)
     public String list(HttpServletRequest request, ModelMap mop)
     {
         String cityNo = UserInfoHolder.get().getCityNo();
         //区域列表
         ResultData<?> resultDistrictList = new ResultData<>();
         try
         {
             resultDistrictList = this.linkageCityService.getDistrictList(cityNo);
         }
         catch (Exception e)
         {
             logger.error("sceneInCommission", "SceneInCommissionController", "", "", null, "", "", e);
         }
         
         //归属项目部列表
         try{
         	Map<String,Object> reqMap = new HashMap<>();
         	reqMap.put("cityNo", cityNo);
         	ResultData<?> resultData = estateService.getProjectDepartment(reqMap);
         	List<?> rebacklist = (List<?>) resultData.getReturnData();
         	mop.put("rebacklist", rebacklist);
         }catch(Exception e)
         {
         	 logger.error("sceneInCommission", "SceneInCommissionController", "", "", null, "", "创建--初始化-归属项目部", e);
         }
         
         mop.put("districtList", resultDistrictList.getReturnData());

		 try{
			 Map<String,Object> reqMap = new HashMap<>();
			 reqMap.put("cityNo", cityNo);
			 ResultData<?> resultData = estateService.getEstateNmList(reqMap);
			 List<?> estateList = (List<?>) resultData.getReturnData();
			 mop.put("estateList", estateList);
		 } catch (Exception e) {
			 logger.error("sceneInCommission", "SceneInCommissionController", "", "", null, "", "创建--初始化-楼盘名称", e);
		 }
         return "scene/inCommission/sceneInCommissionList";
     }
     
     /** 
     * 
     * @param request
     * @param mop
     * @return
     * @throws Exception
     */
     @RequestMapping(value = "qSceneInCommission", method = RequestMethod.GET)
     public String index(HttpServletRequest request, ModelMap mop)
     {
         
         //获取请求参数
         Map<String, Object> reqMap = bindParamToMap(request);
         String yaers = (String) reqMap.get("year");
         String estateTypes = (String) reqMap.get("estateType");
         Integer estateType = Integer.valueOf(estateTypes);
  
         PageInfo pageInfo = getPageInfo(request);
         //获取页面显示数据
         ResultData<?> reback = new ResultData<>();
         try
         {
             reback = sceneInCommissionService.getInCommissionList(reqMap, pageInfo);
         }
         catch (Exception e)
         {
             logger.error("sceneInCommission", "SceneInCommissionController", "qSceneInCommission", "", null, "", "", e);
             reback.setFail();
         }
         
         //页面数据
         List<?> contentlist = (List<?>)reback.getReturnData();
         
         //存放到mop中
         mop.addAttribute("contentlist", contentlist);
         mop.addAttribute("yaers", yaers);
         String returnAddress = "";
		 if (estateType == 20601) {//收入、返佣模板
			 returnAddress = "scene/inCommission/sceneIncomeListCtx";
		 } else if (estateType == 20602) {//内佣
			 returnAddress = "scene/inCommission/sceneInCommissionListCtx";
		 } else if (estateType == 20603) {//实收、实返模板
			 returnAddress = "scene/inCommission/scenePaiclUpListCtx";
		 } else if (estateType == 20606) {//应收模板
			 returnAddress = "scene/inCommission/sceneReceiveListCtx";
		 }
		 return returnAddress;

	 }
    
    
    
    /** 
     * 导入
     * @param id
     * @return
     * @throws Exception
     */
     @RequestMapping(value = "imput", method = RequestMethod.POST)
     @ResponseBody
     public ReturnView<?, ?> imput(HttpServletRequest request, HttpServletResponse response)
     {
    	 // 返回map
         Map<String, Object> rspMap = new HashMap<String, Object>();
         
         //获取请求参数
         Map<String, Object> reqMap = bindParamToMap(request);
         
         MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
         MultipartFile multFile = multiRequest.getFile("historyDataFile");
         String fileName = multFile.getOriginalFilename();
         String estateType = reqMap.get("estateTypeImput").toString();   //模板類型
         
         Workbook wb;
		 try {
			wb = new XSSFWorkbook(multFile.getInputStream());
			   int  number= wb.getNumberOfSheets();
	            if (number>1){
	            	 rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
	                 rspMap.put(Constant.RETURN_MSG_KEY, "Excel文档格式错误,不允许多个sheet！");
	                 return  getSearchJSONView(rspMap);
	            }
			Sheet sheet1 = wb.getSheetAt(0);
			
			//导入check
			Map<String, Object> rtnMap = checkImport(sheet1, estateType);
			if(null != rtnMap){
		        return getSearchJSONView(rtnMap);
			}

			//如果业务年在系统时间之前，更新数据库中今年的当年以前数据 add by wang kanlin 2017/09/13
			String yearSale= sheet1.getRow(0).getCell(8).getStringCellValue(); //业务年
			Calendar c = Calendar.getInstance();
			int yearNow = c.get(Calendar.YEAR);
			boolean needUpdateBefore = !yearSale.equals(yearNow + "");
			String year = sheet1.getRow(0).getCell(8).getStringCellValue();
			List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
			for (Row r : sheet1) {				
				Boolean reault = getSheetCellValue(wb, r, mapList, year);
				if(reault == false)
				{
					continue;
				}
			}
			//数据导入
			Boolean insertResult = insertLinkImport(mapList, fileName, needUpdateBefore);
			if(!insertResult)
			{
				rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
	            rspMap.put(Constant.RETURN_MSG_KEY, "导入数据失败！");	
	            return getSearchJSONView(rspMap);
			}
			
			wb.close();
			
			rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
			rspMap.put(Constant.RETURN_MSG_KEY, "导入数据成功！");		
		} catch (IOException e1) {
			logger.error("sceneInCommission", "SceneInCommissionController", "imput", "", null, "", "", e1);
			rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
			rspMap.put(Constant.RETURN_MSG_KEY, "导入数据失败！");	
		}
		
		return getSearchJSONView(rspMap);      
     }
     
     /**
      * 导入Check
      * @return
      */
     private Map<String, Object> checkImport(Sheet sheet1, String strTemplateType)
     {
    	Map<String, Object> rspMap = new HashMap<String,Object>();
		if(sheet1.getLastRowNum()==0)
		{
			rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导入文件数据格式有误！");	
            return rspMap;
		}

		//密码验证
		if(isSheetModify((XSSFSheet)sheet1)){
			rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导入数据模板不对，请重新下载模板数据！");	
            return rspMap;
		}	 
		
		templateType = sheet1.getRow(0).getCell(4).getStringCellValue();//模板类型
		String year= sheet1.getRow(0).getCell(2).getStringCellValue(); //关账年
		String month= sheet1.getRow(0).getCell(3).getStringCellValue();//关账月
		String userId= sheet1.getRow(0).getCell(0).getStringCellValue();

		//验证模板类型
		if(!strTemplateType.equals(templateType))
		{
			rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "选择的模板类型与导入文件的模板类型不匹配！");	
            return rspMap;
		}
		
		//判断操作人的工号是否和模板的用户工号一致
		if(!userId.equals(UserInfoHolder.getUserId().toString()))
		{
			String userName= sheet1.getRow(0).getCell(7).getStringCellValue();
			rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导入模板为"+userName+"下载的，请使用自己下载的模板进行导入！");	
            return rspMap;
		}
		
		 //已关账月份不能导入数据，如发现数据问题需要调整，则先进行开账操作后再导入
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("cityNo", UserInfoHolder.get().getCityNo());
		try {
			ResultData<?> reback2 = sceneInCommissionService.getInCommissionSwitchMonth(map, null);
			Map<String, String> switchMonth = (Map<String, String>) reback2.getReturnData();
			if(null != switchMonth){
				String strYear = switchMonth.get("switchYear").toString();
				String strMonth = switchMonth.get("switchMonth").toString();
				if(!year.equals(strYear) || !month.equals(strMonth))
				{
					rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
		            rspMap.put(Constant.RETURN_MSG_KEY, "已关账月份不能导入数据，如发现数据问题需要调整，则先进行开账操作后再导入！");	
		            return rspMap;
				}
			}
      	  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
     }

    /**
     *  取得excel值
     * @param r excel行
     * @return
     */
    private Boolean getSheetCellValue(Workbook wb, Row r, List<Map<String, Object>> mapList, String year)
    {		
		//跳过列头
		if(r.getRowNum()<4){
	    	return false;
	    }
		
		 String estateNm= r.getCell(1).getStringCellValue();	//楼盘名
		 String reportId= r.getCell(2).getStringCellValue();	//报备ID
		 String buildingNo= r.getCell(3).getStringCellValue();	//楼室号
		 String num= r.getCell(4).getStringCellValue();	//套数
		 String dealAmount= getCellValue(wb, r.getCell(5)); //成销总价
		 String dealDate= r.getCell(6).getStringCellValue();		//成销日期
		 
		 String amountType = ""; //金额类型
		 switch(templateType)
		 {
		 	case "20601" :
		 		amountType = "20701" ; //应计收入
		 		break;
			case "20602" :
				amountType = "20703" ; //应计内佣
				 break;
			case "20603" :
				amountType = "20704" ; //应计实收
				 break;
			 case "20606" :
				 amountType = "20706" ; //应收收入
				 break;
		 }
		 
		 Map<String, Object> map = new HashMap<String, Object>();

		 //税前
		 String subTotalPreTax= getCellValue(wb, r.getCell(7));
		 String beforeAmountPreTax= getCellValue(wb, r.getCell(9));
		 String janPreTax= getCellValue(wb, r.getCell(11));
		 String febPreTax= getCellValue(wb, r.getCell(13));
		 String marPreTax= getCellValue(wb, r.getCell(15));
		 String aprPreTax= getCellValue(wb, r.getCell(17));
		 String mayPreTax= getCellValue(wb, r.getCell(19));
		 String junPreTax= getCellValue(wb, r.getCell(21));		
		 String julPreTax= getCellValue(wb, r.getCell(23));
		 String augPreTax= getCellValue(wb, r.getCell(25));
		 String sepPreTax= getCellValue(wb, r.getCell(27));
		 String octPreTax= getCellValue(wb, r.getCell(29));
		 String novPreTax= getCellValue(wb, r.getCell(31));
		 String decPreTax= getCellValue(wb, r.getCell(33));
		 //税后
		 String subTotalAfterTax= getCellValue(wb, r.getCell(8));
		 String beforeAmountAfterTax= getCellValue(wb, r.getCell(10));
		 String janAfterTax= getCellValue(wb, r.getCell(12));
		 String febAfterTax= getCellValue(wb, r.getCell(14));
		 String marAfterTax= getCellValue(wb, r.getCell(16));
		 String aprAfterTax= getCellValue(wb, r.getCell(18));
		 String mayAfterTax= getCellValue(wb, r.getCell(20));
		 String junAfterTax= getCellValue(wb, r.getCell(22));
		 String julAfterTax= getCellValue(wb, r.getCell(24));
		 String augAfterTax= getCellValue(wb, r.getCell(26));
		 String sepAfterTax= getCellValue(wb, r.getCell(28));
		 String octAfterTax= getCellValue(wb, r.getCell(30));
		 String novAfterTax= getCellValue(wb, r.getCell(32));
		 String decAfterTax= getCellValue(wb, r.getCell(34));
		 String estateId= getCellValue(wb, r.getCell(r.getLastCellNum()-2));
		 String detailId= getCellValue(wb, r.getCell(r.getLastCellNum()-1));
		 //数据添加
		 map.put("templateType", templateType);		 
		 map.put("amountType", amountType);
		 map.put("year", Integer.valueOf(year));
		 map.put("estateId", estateId);//楼盘ID
		 map.put("estateNm", estateNm);
		 map.put("reportId", reportId);
		 map.put("detailId", detailId);
		 map.put("buildingNo", buildingNo);
		 map.put("num", num);
		 map.put("dealAmount", dealAmount);		 
		 map.put("dealDate", dealDate);		 
		 map.put("subTotalPreTax", subTotalPreTax);
		 map.put("beforeAmountPreTax", beforeAmountPreTax);
		 map.put("janPreTax", janPreTax);
		 map.put("febPreTax", febPreTax);
		 map.put("marPreTax", marPreTax);
		 map.put("aprPreTax", aprPreTax);
		 map.put("mayPreTax", mayPreTax);
		 map.put("junPreTax", junPreTax);
		 map.put("julPreTax", julPreTax);
		 map.put("augPreTax", augPreTax);
		 map.put("sepPreTax", sepPreTax);
		 map.put("octPreTax", octPreTax);
		 map.put("novPreTax", novPreTax);
		 map.put("decPreTax", decPreTax);
		 //税后
		 map.put("subTotalAfterTax", subTotalAfterTax);
		 map.put("beforeAmountAfterTax", beforeAmountAfterTax);
		 map.put("janAfterTax", janAfterTax);
		 map.put("febAfterTax", febAfterTax);
		 map.put("marAfterTax", marAfterTax);
		 map.put("aprAfterTax", aprAfterTax);
		 map.put("mayAfterTax", mayAfterTax);
		 map.put("junAfterTax", junAfterTax);
		 map.put("julAfterTax", julAfterTax);
		 map.put("augAfterTax", augAfterTax);
		 map.put("sepAfterTax", sepAfterTax);
		 map.put("octAfterTax", octAfterTax);
		 map.put("novAfterTax", novAfterTax);
		 map.put("decAfterTax", decAfterTax);
		 map.put("CrtEmpId", UserInfoHolder.getUserId());
		 map.put("UptEmpId", UserInfoHolder.getUserId());
		 map.put("delFlg", 0);
		 mapList.add(map);
		 
		 switch(templateType)
		 {
		 	case "20601" :
		 		amountType = "20702" ; //应计收入
		 		break;
			case "20602" :
			 case "20606" :
				return true;
			case "20603" :
				amountType = "20705" ; //应计实收
				 break;
		 }
		//税前
		 subTotalPreTax= getCellValue(wb, r.getCell(35));
		 beforeAmountPreTax= getCellValue(wb, r.getCell(37));
		 janPreTax= getCellValue(wb, r.getCell(39));
		 febPreTax= getCellValue(wb, r.getCell(41));
		 marPreTax= getCellValue(wb, r.getCell(43));
		 aprPreTax= getCellValue(wb, r.getCell(45));
		 mayPreTax= getCellValue(wb, r.getCell(47));
		 junPreTax= getCellValue(wb, r.getCell(49));		
		 julPreTax= getCellValue(wb, r.getCell(51));
		 augPreTax= getCellValue(wb, r.getCell(53));
		 sepPreTax= getCellValue(wb, r.getCell(55));
		 octPreTax= getCellValue(wb, r.getCell(57));
		 novPreTax= getCellValue(wb, r.getCell(59));
		 decPreTax= getCellValue(wb, r.getCell(61));
		 //税后
		 subTotalAfterTax= getCellValue(wb, r.getCell(36));
		 beforeAmountAfterTax= getCellValue(wb, r.getCell(38));
		 janAfterTax= getCellValue(wb, r.getCell(40));
		 febAfterTax= getCellValue(wb, r.getCell(42));
		 marAfterTax= getCellValue(wb, r.getCell(44));
		 aprAfterTax= getCellValue(wb, r.getCell(46));
		 mayAfterTax= getCellValue(wb, r.getCell(48));
		 junAfterTax= getCellValue(wb, r.getCell(50));
		 julAfterTax= getCellValue(wb, r.getCell(52));
		 augAfterTax= getCellValue(wb, r.getCell(54));
		 sepAfterTax= getCellValue(wb, r.getCell(56));
		 octAfterTax= getCellValue(wb, r.getCell(58));
		 novAfterTax= getCellValue(wb, r.getCell(60));
		 decAfterTax= getCellValue(wb, r.getCell(62));
		 estateId= getCellValue(wb, r.getCell(r.getLastCellNum()-2));
		 detailId= getCellValue(wb, r.getCell(r.getLastCellNum()-1));
		 //数据添加
		 map = new HashMap<String, Object>();
		 map.put("templateType", templateType);		 
		 map.put("amountType", amountType);
		 map.put("year", Integer.valueOf(year));
		 map.put("estateId", estateId);//楼盘ID
		 map.put("estateNm", estateNm);		 
		 map.put("reportId", reportId);
		 map.put("detailId", detailId);
		 map.put("buildingNo", buildingNo);
		 map.put("num", num);
		 map.put("dealAmount", dealAmount);		 
		 map.put("dealDate", dealDate);		 
		 map.put("subTotalPreTax", subTotalPreTax);
		 map.put("beforeAmountPreTax", beforeAmountPreTax);
		 map.put("janPreTax", janPreTax);
		 map.put("febPreTax", febPreTax);
		 map.put("marPreTax", marPreTax);
		 map.put("aprPreTax", aprPreTax);
		 map.put("mayPreTax", mayPreTax);
		 map.put("junPreTax", junPreTax);
		 map.put("julPreTax", julPreTax);
		 map.put("augPreTax", augPreTax);
		 map.put("sepPreTax", sepPreTax);
		 map.put("octPreTax", octPreTax);
		 map.put("novPreTax", novPreTax);
		 map.put("decPreTax", decPreTax);
		 //税后
		 map.put("subTotalAfterTax", subTotalAfterTax);
		 map.put("beforeAmountAfterTax", beforeAmountAfterTax);
		 map.put("janAfterTax", janAfterTax);
		 map.put("febAfterTax", febAfterTax);
		 map.put("marAfterTax", marAfterTax);
		 map.put("aprAfterTax", aprAfterTax);
		 map.put("mayAfterTax", mayAfterTax);
		 map.put("junAfterTax", junAfterTax);
		 map.put("julAfterTax", julAfterTax);
		 map.put("augAfterTax", augAfterTax);
		 map.put("sepAfterTax", sepAfterTax);
		 map.put("octAfterTax", octAfterTax);
		 map.put("novAfterTax", novAfterTax);
		 map.put("decAfterTax", decAfterTax);
		 map.put("CrtEmpId", UserInfoHolder.getUserId());
		 map.put("UptEmpId", UserInfoHolder.getUserId());
		 map.put("delFlg", 0);
		 mapList.add(map);
		 return true;
    }
    
    /**
     * 导入操作
     * @param mapList
     * @return
     */
    private Boolean insertLinkImport(List<Map<String, Object>> mapList, String fileName, boolean needUpdateBefore)
    {
    	try {
            //更新数据和新增的数据分开，批量处理
            for (Map<String, Object> map : mapList) {
            	String num = map.get("num").toString();
            	String reportId = map.get("reportId").toString();
            	String detailId = map.get("detailId").toString();
            	String templateType = map.get("templateType").toString();
            	String amountType = map.get("amountType").toString();
            	String year = map.get("year").toString();
            	Map<String, Object> resMap = new HashMap<String, Object>();
            	resMap.put("num", num);
            	resMap.put("reportId", reportId);
            	resMap.put("detailId", detailId);
            	resMap.put("templateType", templateType);
            	resMap.put("amountType", amountType);
            	resMap.put("year", Integer.valueOf(year));
				//查询数据库是否已存在，存在修改否则新增
            	ResultData<?> resultData = sceneInCommissionService.getLnkImportByReportId(resMap);
            	Map<?,?> mapInfo = (Map<?,?> )resultData.getReturnData();
            	map.put("needUpdateBefore", Boolean.valueOf(needUpdateBefore));
            	if(null != mapInfo)
            	{
            		sceneInCommissionService.updateLnkImport(map);
            	}else{
            		sceneInCommissionService.insertLnkImport(map);
            	}  
            	
            	//写入日志
            	createLogLnkImport(fileName, map);
			}
			mapList.clear();
		} catch (Exception e) {
			return false;
		}
    	return true;
    }

    /**
     * 导入时log写入日志
     * @param fileName
     */
    private void createLogLnkImport(String fileName, Map<String,Object> map)
    {
    	try {
    		map.put("operType", "1");
    		map.put("operUserId", UserInfoHolder.getUserId());
    		map.put("operUserName", UserInfoHolder.get().getUserName());
    		map.put("fileName",fileName);
    		map.put("remarks","");
    		
			ResultData<?> resultData = sceneInCommissionService.createLogLnkImport(map);
			Integer rtn = (Integer)resultData.getReturnData();
			if(rtn <= 0)
			{
				logger.error("sceneInCommission", "SceneInCommissionController", "createLogLnkImport", "", null, "", "导入log写入失败！",null);
			}
		} catch (Exception e) {
			logger.error("sceneInCommission", "SceneInCommissionController", "createLogLnkImport", "", null, "", "导入log写入失败！", e);
		}
    }
    
    //excel类型判断
    private String getCellValue(Workbook wb, Cell cell)
    {
    	FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator(); 
    	String rtnVal="";
    	
    	DecimalFormat df = new DecimalFormat("0.00"); 
    	if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC)
    	{
    		rtnVal = df.format(cell.getNumericCellValue());
    	}
    	if(cell.getCellType()==Cell.CELL_TYPE_STRING)
    	{
    		rtnVal = cell.getStringCellValue();
    	}
    	if(cell.getCellType()==Cell.CELL_TYPE_FORMULA)
    	{    		
    		evaluator.evaluateFormulaCell(cell);  
    		rtnVal = df.format(cell.getNumericCellValue());
    	}    	
    	return rtnVal;
    }
    
    
     //验证excel密码
   	private boolean isSheetModify(XSSFSheet sheet) {
       boolean haveModify = true;
       try {
    	   String pwd = sheet.getRow(0).getCell(1).getStringCellValue();
           CTSheetProtection csheet = sheet.getCTWorksheet().getSheetProtection();
           if(csheet!=null) {
               STUnsignedShortHex passwordST = csheet.xgetPassword();
               if(passwordST!=null || !passwordST.getStringValue().equals(pwd)) {
                   haveModify = false;
               }
           }
       }catch(Exception e){
           e.printStackTrace();
       }
       return haveModify;
    }
	
    /*
     * 导出check
     * created by wang kanlin 2017/8/17
     */
	@RequestMapping(value = "exportCheck", method = RequestMethod.POST)
	@ResponseBody
    public ReturnView<?, ?> exportCheck(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> rspMap = new HashMap<String,Object>();
		Map<String, Object> reqMap = bindParamToMap(request);
		ResultData<?> reback;
		try {
			reback = sceneInCommissionService.getInCommissionList(reqMap, null);
			List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();
			if (contentlist == null || contentlist.isEmpty()) {
				rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
	            rspMap.put(Constant.RETURN_MSG_KEY, "没有数据，不能导出Excel！");	
	            return getOperateJSONView(rspMap);
		    }
		} catch (Exception e) {
			rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导出失败！");	
            return getOperateJSONView(rspMap);
		}
		rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);	
		rspMap.put(Constant.RETURN_MSG_KEY,reback.getReturnMsg());
        return getOperateJSONView(rspMap);
	}
	
    /*
     * 导出Excel
     * created by wang kanlin 2017/8/15
     */
	@RequestMapping(value = "export", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response) {

         Map<String, Object> rspMap = new HashMap<String, Object>();
		 Map<String, Object> reqMap = bindParamToMap(request);
		 
		 try {
			ResultData<?> reback =  sceneInCommissionService.queryInCommissionList(reqMap, null);
			List<LinkedHashMap<String, Object>> contentlist = (List<LinkedHashMap<String, Object>>) reback.getReturnData();
			
			String ctxPath = request.getSession().getServletContext().getRealPath("/");
			String url = ctxPath + "data" + File.separator + "sceneInCommission";
			
			File dataFile = new File(ctxPath + "data");
          if (!dataFile.isDirectory()) {
              dataFile.mkdir();
          }

          File direct = new File(url);
          if (!direct.isDirectory()) {
              direct.mkdir();
          }
          
          long time = System.currentTimeMillis();
          //删掉历史文件
          FileUtils.deleteFile(direct, time);

          File directory = new File(url + File.separator + time);
          if (!directory.isDirectory()) {
              directory.mkdir();
          }

          try {
              String templateType = reqMap.get("estateType").toString();
              String templateTypeStr = SystemParam.getDicValueByDicCode(templateType);
              String pathName = url + File.separator + time + File.separator + templateTypeStr + ".xlsx";
              ExcelForInCommision instance = new ExcelForInCommision(Integer.valueOf(templateType));
        	  Map<String, String> map = new HashMap<>();
              UserInfo userInfo = UserInfoHolder.get();
        	  map.put("UserId", String.valueOf(userInfo.getUserId()));
        	  map.put("UserName", userInfo.getUserName() + "(" + userInfo.getUserCode() + ")");
        	  map.put("templateType", templateTypeStr);
        	  ResultData<?> reback2 =  sceneInCommissionService.getInCommissionSwitchMonth(reqMap, null);
        	  Map<String, String> switchMonth = (Map<String, String>) reback2.getReturnData();
        	  map.put("EndYear", switchMonth.get("switchYear"));
        	  map.put("EndMonth", switchMonth.get("switchMonth"));
        	  String year = reqMap.get("countDateStart").toString();
        	  if (year != null && year.length()>=4) year = year.substring(0, 4);
        	  map.put("Year", year);
        	  map.put("CountDateStart", reqMap.get("countDateStart").toString());
        	  map.put("CountDateEnd", reqMap.get("countDateEnd").toString());
        	  map.put("YearSale", reqMap.get("year").toString());
              instance.downloadSheet(map, contentlist, new File(pathName));
              
              String fileName = templateTypeStr + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx";
              super.download(request, response, pathName, fileName);
          }catch(Exception e){
	        response.setCharacterEncoding("GBK");
	        response.getWriter().write("下载Excel失败" + e.getMessage());
	        response.getWriter().close();
	        logger.error("下载Excel失败", e);
	        e.printStackTrace();
         }
          
		 }catch(Exception e){
			 logger.error("sceneInCommission",
	                    "SceneInCommissionController",
	                    "export",
	                    "input param: reqMap=" + reqMap.toString(), -
	                            UserInfoHolder.getUserId(),
	                    WebUtil.getIPAddress(request),
	                    "导出异常",
	                    e);
		 }
	} 	
}
