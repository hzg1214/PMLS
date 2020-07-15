package cn.com.eju.pmls.remittanceTrack.service;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.util.CollectionUtils;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STUnsignedShortHex;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.pmls.remittanceTrack.dto.RemittanceTrackDto;
import cn.com.eju.pmls.remittanceTrack.dto.RemittanceTrackImportDto;

@Service("remittanceTrackService")	
public class RemittanceTrackService extends BaseService {
	
	private final LogHelper logger = LogHelper.getLogger(this.getClass());
	
	/**
	 * desc:回款跟踪数据初始化-年月-周
	 * 2020年4月7日
	 */
	public ResultData<?> getWeeks(Map<String, Object> map) throws Exception {
		
		String url = SystemParam.getWebConfigValue("RestServer") + "remittanceTrack" + "/getWeeks/{param}";
		
		ResultData<?> backResult = get(url, map);
		
		return backResult;
	}
	
	/**
	 * desc:获取周数
	 * 2020年4月22日
	 */
	public ResultData<?> getNums(Map<String, Object> map) throws Exception {
		
		String url = SystemParam.getWebConfigValue("RestServer") + "remittanceTrack" + "/getNums/{param}";
		
		ResultData<?> backResult = get(url, map);
		
		return backResult;
	}
	
	/**
	 * desc:列表
	 * 2020年4月7日
	 */
	public ResultData<?> queryRemitanceTrackList(Map<String, Object> reqMap, PageInfo pageInfo) throws Exception {
		// 调用 接口
		String url = SystemParam.getWebConfigValue("RestServer") + "remittanceTrack" + "/queryRemitanceTrackList/{param}";
		ResultData<?> reback = get(url, reqMap, pageInfo);
		return reback;
	}
	
	/**
	 * desc:获取表头
	 * 2020年4月8日
	 */
    public ResultData<?> getTitle(Map<String, Object> reqMap) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "remittanceTrack" + "/getTitle";
        ResultData<?> reback = post(url, reqMap);
        return reback;
    }
    
    /**
     * desc:导入
     * 2020年4月15日
     */
    public Map<String, Object> importRemittanceTrack(HttpServletRequest request) {
    	
    	Map<String, Object> rspMap = new HashMap<String, Object>();
    	String fileName = "";
        CommonsMultipartFile multFile = null;

        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                MultipartFile importFile = multiRequest.getFile(iter.next());
                //文件名
                fileName = importFile.getOriginalFilename();
                logger.info("回款跟踪数据，导入，文件名为:"+fileName);
                //MultipartFile转File
                multFile = (CommonsMultipartFile) importFile;
            }
        }

        Workbook wb;
        try {
            wb = new XSSFWorkbook(multFile.getInputStream());
            int  number= wb.getNumberOfSheets();
            if (number>1){
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "Excel文档格式错误,不允许多个sheet！");
                return rspMap;
            }
            Sheet sheet1 = wb.getSheetAt(0);
            
            LinkedHashMap<String, Object> switchWeek = getSwitchWeek(sheet1);
            //导入check
            Map<String, Object> rtnMap = checkImport(sheet1,switchWeek);
            if (null != rtnMap) {
                return rtnMap;
            }
            //周数
            String weekNo = sheet1.getRow(0).getCell(7).getStringCellValue();
            String trackType = sheet1.getRow(0).getCell(1).getStringCellValue();
            String cityNo = sheet1.getRow(0).getCell(8).getStringCellValue();
            //获取导入excel 周表头
            Map<String, Object> weekNoTitleMap = new HashMap<>();
            List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
            RemittanceTrackImportDto remittanceTrackImportDto = new RemittanceTrackImportDto();
            
            for (Row r : sheet1) {
                Integer reault = getSheetCellValue(sheet1,wb, r, mapList,weekNo,weekNoTitleMap);
                if (reault == 1) {
                    continue;
                }else if(reault == 2){
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    rspMap.put(Constant.RETURN_MSG_KEY, "调整列数据填写错误，请调整导表后重新导入！");
                    return rspMap;
                }
            }
            wb.close();
            //数据导入
            ResultData insertResult = insertRemittanceTrackImport(mapList,remittanceTrackImportDto,weekNo,
            		switchWeek,trackType,cityNo,weekNoTitleMap);
            if (!"200".equals(insertResult.getReturnCode())) {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, insertResult.getReturnMsg());
                return rspMap;
            }
            String switchDate = getSwitchDate(switchWeek);
            if(weekNoTitleMap.containsKey("importFlag") && (Integer)weekNoTitleMap.get("importFlag")==1) {
            	rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
            	rspMap.put(Constant.RETURN_MSG_KEY, "回款跟踪数据已关账至"+switchDate+"，部分数据未导入成功！");
            	logger.info("pmls,RemittanceTrackService,imput,回款跟踪数据已关账至"+switchDate+",部门数据未导入成功！");
            }else if(weekNoTitleMap.containsKey("importFlag") && (Integer)weekNoTitleMap.get("importFlag")==2) {
            	rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
	    		rspMap.put(Constant.RETURN_MSG_KEY, "回款跟踪数据已关账至"+switchDate+"，数据未导入成功！");
	    		logger.info("pmls,RemittanceTrackService,imput,回款跟踪数据已关账至"+switchDate+",数据未导入成功！");
            }else {
            	rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
            	rspMap.put(Constant.RETURN_MSG_KEY, "导入成功！");
            }
        } catch (Exception e1) {
        	e1.printStackTrace();
            logger.error("pmls", "RemittanceTrackService", "imput", "", null, "导入数据失败！", "", e1);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导入数据失败！");
            return rspMap;
        }
        return rspMap;
    }
    
    /**
     * desc:关账信息
     * 2020年4月17日
     */
    private LinkedHashMap<String, Object> getSwitchWeek(Sheet sheet1) {
    	/* 
		        导入的预计回款数据只能为关账一周后的数据，例如现在关账是2020年3月第1周，只能录入3月第3周及以后周的数据
		       导入的实际回款数据只能为关账后一周内的数据，例如现在关账是2020年3月第1周，只能录入3月第2周的数据
		       预计回款和实际回款金额允许录入负数。导入表现金或抵房列数据为空时，系统保存为空。
		      根据关账版本，记录导入数据的版本，同一关账版本的数据，导入多次后覆盖。
      */
		String cityNo = sheet1.getRow(0).getCell(8).getStringCellValue();
		
		Map<String,Object> map = new HashMap<>();
		map.put("cityNo", cityNo);
		map.put("relateSystem", 29301);//先固定写死
		ResultData reback=null;
		try {
			logger.info("回款跟踪数据，导入，获取关账信息开始");
			reback = querySwitchWeek(map);
			logger.info("回款跟踪数据，导入，获取关账信息:"+reback);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("remittanceTrack", "RemittanceTrackController", "imput", "", null, "", "", e);
		}
		LinkedHashMap<String, Object> switchWeek = (LinkedHashMap<String, Object>) reback.getReturnData();
		return switchWeek;
	}

	/**
     * 导入Check
     *
     * @return
     */
    public Map<String, Object> checkImport(Sheet sheet1,LinkedHashMap<String, Object> switchWeek) {
        Map<String, Object> rspMap = new HashMap<String, Object>();
        if (sheet1.getLastRowNum() == 0) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导入文件数据格式有误！");
            logger.info("回款跟踪数据，导入，导入文件数据格式有误！");
            return rspMap;
        }

        //密码验证
        if (isSheetModify((XSSFSheet) sheet1)) {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "导入数据模板不对，请重新下载模板数据！");
            logger.info("回款跟踪数据，导入，导入数据模板不对，请重新下载模板数据！");
            return rspMap;
        }

        if(switchWeek==null) {
    		rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
    		rspMap.put(Constant.RETURN_MSG_KEY, "获取回款跟踪关账信息失败，请先做关账操作！");
    		logger.info("回款跟踪数据，导入，导入数据模板不对，获取关账信息失败");
    		return rspMap;
    	}
		Integer switchState = (Integer) switchWeek.get("switchState");
		if(switchState==27501){//开帐
    		rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
    		rspMap.put(Constant.RETURN_MSG_KEY, "获取回款跟踪关账信息失败，请先做关账操作！");
    		logger.info("回款跟踪数据，导入，导入数据模板不对，获取关账信息失败");
    		return rspMap;
		}else if(switchState==27502) {//关账
			//数据范围-年
	        String year = sheet1.getRow(0).getCell(5).getStringCellValue();
	        //数据范围-月
	        String dataMonth = sheet1.getRow(0).getCell(6).getStringCellValue();
	        if(Integer.parseInt(dataMonth)<10) {
	        	dataMonth="0"+dataMonth;
	        }
	        String dataVersion = year+dataMonth;
	        
	        String switchMonth = (String) switchWeek.get("month");//月
	        String switchYear = (String) switchWeek.get("year");//年
	        String switchDate = getSwitchDate(switchWeek);
	        if(Integer.parseInt(switchMonth)<10) {
	        	switchMonth="0"+switchMonth;
	        }
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
	        String performSwitch = switchYear+switchMonth;
	        try {
				if(sdf.parse(dataVersion).compareTo(sdf.parse(performSwitch))<0) {
					rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
		    		rspMap.put(Constant.RETURN_MSG_KEY, "回款跟踪数据已关账至"+switchDate+",数据未导入成功！");
		    		logger.info("回款跟踪数据，导入，导入数据模板不对，回款跟踪数据已关账至"+switchDate+",数据未导入成功！");
		    		return rspMap;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
        return null;
    }

    /**
     * desc:获取关账日期 str
     * 2020年4月21日
     */
    private String getSwitchDate(LinkedHashMap<String, Object> switchWeek) {
    	//关账信息
        Integer switchWeekNo = (Integer) switchWeek.get("weekNo");//周
        String switchYear = (String) switchWeek.get("year");//年
        String switchMonth = (String) switchWeek.get("month");//月
        String switchDate = switchYear+"年"+switchMonth+"月第"+switchWeekNo+"周";
		return switchDate;
	}

	/**
     * desc:验证excel密码
     * 2020年4月15日
     */
    private boolean isSheetModify(XSSFSheet sheet) {
        boolean haveModify = true;
        try {
            String pwd = sheet.getRow(0).getCell(1).getStringCellValue();
            CTSheetProtection csheet = sheet.getCTWorksheet().getSheetProtection();
            if (csheet != null) {
                STUnsignedShortHex passwordST = csheet.xgetPassword();
                if (passwordST != null || !passwordST.getStringValue().equals(pwd)) {
                    haveModify = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return haveModify;
    }
    
    
    /**
     * 取得excel值
     *1,跳过列头2,调整列数据填写错误，请调整导表后重新导入
     * @param r excel行
     * @param estateType
     * @return
     */
    private Integer getSheetCellValue(Sheet sheet1,Workbook wb, Row r, 
    		List<Map<String, Object>> mapList,String weekNo,Map<String,Object> weekNoTitleMap) {
    	
        //跳过列头
    	String weekNoTitle1="";
    	String weekNoTitle2="";
    	String weekNoTitle3="";
    	String weekNoTitle4="";
    	String weekNoTitle5="";
    	String weekNoTitle6="";
        if (r.getRowNum() ==0 ||  r.getRowNum() ==2) {
            return 1;
        }else if(r.getRowNum() ==1) {//获取表头
        	weekNoTitle1 = r.getCell(6).getStringCellValue();
        	weekNoTitleMap.put("weekNoTitle1", weekNoTitle1);
        	weekNoTitle2 = r.getCell(9).getStringCellValue();
        	weekNoTitleMap.put("weekNoTitle2", weekNoTitle2);
        	weekNoTitle3 = r.getCell(12).getStringCellValue();
        	weekNoTitleMap.put("weekNoTitle3", weekNoTitle3);
        	weekNoTitle4 = r.getCell(15).getStringCellValue();
        	weekNoTitleMap.put("weekNoTitle4", weekNoTitle4);
        	if("5".equals(weekNo) || "6".equals(weekNo)) {
        		weekNoTitle5 = r.getCell(18).getStringCellValue();
        	}
        	weekNoTitleMap.put("weekNoTitle5", weekNoTitle5);
        	if( "6".equals(weekNo)) {
        		weekNoTitle6 = r.getCell(21).getStringCellValue();
        	}
        	weekNoTitleMap.put("weekNoTitle6", weekNoTitle6);
        	logger.info("回款跟踪数据，导入，获取表头信息:"+weekNoTitleMap);
        	return 1;
        }
        
        //模板类型
        String trackType = sheet1.getRow(0).getCell(1).getStringCellValue();
        //年月版本
    	String yearMonthVersion = sheet1.getRow(0).getCell(2).getStringCellValue();
    	//周版本
        String weekVersion = sheet1.getRow(0).getCell(3).getStringCellValue();
        //排序
        String sortNo = sheet1.getRow(0).getCell(4).getStringCellValue();
        //数据范围-年
        String year = sheet1.getRow(0).getCell(5).getStringCellValue();
        //数据范围-月
        String dataMonth = sheet1.getRow(0).getCell(6).getStringCellValue();
        
        
        //项目编号
        String projectNo = r.getCell(1).getStringCellValue();    
        //项目名称
        String projectName = r.getCell(2).getStringCellValue();    
        //合作方
        String partnerName = r.getCell(3).getStringCellValue();   
        //项目状态
        String projectStatusStr = r.getCell(4).getStringCellValue();    

        Map<String, Object> map = new HashMap<>();
        map.put("dataMonth",dataMonth);
        //回款合计
        String hkTotal = getCellValue(wb, r.getCell(5));  
        
        //第1周合计
        String weekTotal1 = getCellValue(wb, r.getCell(6));
        //第1周现金
        String xjAccount1 = null;
        //第1周抵房
        String dfAccount1 = null;
        if(weekTotal1!=null) {
        	xjAccount1 = getCellValue(wb, r.getCell(7));
        	dfAccount1 = getCellValue(wb, r.getCell(8));
        	map.put("weekTotal1",weekTotal1);
        	if(StringUtil.isNotEmpty(xjAccount1)) {
        		map.put("xjAccount1",xjAccount1);
        	}
        	if(StringUtil.isNotEmpty(dfAccount1)) {
        		map.put("dfAccount1",dfAccount1);
        	}
        }
        
        //第2周合计
        String weekTotal2 = getCellValue(wb, r.getCell(9));
        //第2周现金
        String xjAccount2 = null;
        //第2周抵房
        String dfAccount2 = null;
        if(weekTotal1!=null) {
        	xjAccount2 = getCellValue(wb, r.getCell(10));
        	dfAccount2 = getCellValue(wb, r.getCell(11));
        	map.put("weekTotal2",weekTotal2);
        	if(StringUtil.isNotEmpty(xjAccount2)) {
        		map.put("xjAccount2",xjAccount2);
        	}
        	if(StringUtil.isNotEmpty(dfAccount2)) {
        		map.put("dfAccount2",dfAccount2);
        	}
        }
        
        //第3周合计
        String weekTotal3 = getCellValue(wb, r.getCell(12));
        //第3周现金
        String xjAccount3 = null;
        //第3周抵房
        String dfAccount3 = null;
        if(weekTotal1!=null) {
        	xjAccount3 = getCellValue(wb, r.getCell(13));
        	dfAccount3 = getCellValue(wb, r.getCell(14));
        	map.put("weekTotal3",weekTotal3);
        	if(StringUtil.isNotEmpty(xjAccount3)) {
        		map.put("xjAccount3",xjAccount3);
        	}
        	if(StringUtil.isNotEmpty(dfAccount3)) {
        		map.put("dfAccount3",dfAccount3);
        	}
        }
        
        //第4周合计
        String weekTotal4 = getCellValue(wb, r.getCell(15));
        //第4周现金
        String xjAccount4 = null;
        //第4周抵房
        String dfAccount4 = null;
        if(weekTotal1!=null) {
        	xjAccount4 = getCellValue(wb, r.getCell(16));
        	dfAccount4 = getCellValue(wb, r.getCell(17));
        	map.put("weekTotal4",weekTotal4);
        	if(StringUtil.isNotEmpty(xjAccount4)) {
        		map.put("xjAccount4",xjAccount4);
        	}
        	if(StringUtil.isNotEmpty(dfAccount4)) {
        		map.put("dfAccount4",dfAccount4);
        	}
        }
        
        if("5".equals(weekNo) || "6".equals(weekNo)) {
        	//第5周合计
        	String weekTotal5 = getCellValue(wb, r.getCell(18));
        	//第5周现金
        	String xjAccount5 = null;
        	//第5周抵房
        	String dfAccount5 = null;
        	if(weekTotal1!=null) {
        		xjAccount5 = getCellValue(wb, r.getCell(19));
        		dfAccount5 = getCellValue(wb, r.getCell(20));
        		map.put("weekTotal5",weekTotal5);
        		if(StringUtil.isNotEmpty(xjAccount5)) {
        			map.put("xjAccount5",xjAccount5);
        		}
        		if(StringUtil.isNotEmpty(dfAccount5)) {
        			map.put("dfAccount5",dfAccount5);
        		}
        	}
        }
        
        if("6".equals(weekNo)) {
        	//第6周合计
        	String weekTotal6 = getCellValue(wb, r.getCell(21));
        	//第6周现金
        	String xjAccount6 = null;
        	//第6周抵房
        	String dfAccount6 = null;
        	if(weekTotal1!=null) {
        		xjAccount6 = getCellValue(wb, r.getCell(22));
        		dfAccount6 = getCellValue(wb, r.getCell(23));
        		map.put("weekTotal6",weekTotal6);
        		if(StringUtil.isNotEmpty(xjAccount6)) {
        			map.put("xjAccount6",xjAccount6);
        		}
        		if(StringUtil.isNotEmpty(dfAccount6)) {
        			map. put("dfAccount6",dfAccount6);
        		}
        	}
        }
        
        
        //数据添加 
        //模板类型
        map.put("trackType",trackType);
        //年月版本
        map.put("yearMonthVersion",yearMonthVersion);
        //周版本
        map.put("weekVersion",weekVersion);
        //排序
        map.put("sortNo",sortNo);
        //数据范围-年
        map.put("year",year);
        //数据范围-月
        map.put("dataMonth",dataMonth);
        //项目编号
        map.put("projectNo",projectNo); 
        //项目名称
        map.put("projectName",projectName);   
        //合作方
        map.put("partnerName",partnerName); 
        //项目状态
        map.put("projectStatusStr",projectStatusStr);

        map.put("userIdCreate", UserInfoHolder.getUserId());
        map.put("delFlag", 0);
        mapList.add(map);
        return 0;
    }
    
  //excel类型判断
    private String getCellValue(Workbook wb, Cell cell) {
        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
        String rtnVal = "";

        DecimalFormat df = new DecimalFormat("0.00");
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            //判断是否为日期类型
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                //用于转化为日期格式
                Date d = cell.getDateCellValue();
                DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                rtnVal = formater.format(d);
            } else {
                // 用于格式化数字，只保留数字的整数部分
                rtnVal = df.format(cell.getNumericCellValue());
            }
        }
        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            rtnVal = cell.getStringCellValue().replace(",", "");
        }
        if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            evaluator.evaluateFormulaCell(cell);
            rtnVal = df.format(cell.getNumericCellValue());
        }
        return rtnVal;
    }
    
    /**
     * desc:组装entity
     * 2020年4月15日
     */
    private ResultData insertRemittanceTrackImport(List<Map<String, Object>> mapList,
    		RemittanceTrackImportDto remittanceTrackImportDto,String weekNo,
    		LinkedHashMap<String, Object> switchWeek,String trackType,String cityNo
    		,Map<String, Object> weekNoTitleMap) {
        ResultData resultData = new ResultData();
        try {
            List<RemittanceTrackDto> remittanceTrackList = new ArrayList<>();
            
            Map<String,Object> performSwitchDate = getPerformSwitchDate(trackType,switchWeek);
            //关账日期后缀  2020-01-19
            String performDate =(String) performSwitchDate.get("finishEndDate");
            //关账日期前缀  2020-01-13
            String performSwitchFinishDate =(String) performSwitchDate.get("finishStartDate");
            boolean weekFlag1 = false;
            boolean weekFlag2 = false;
            boolean weekFlag3 = false;
            boolean weekFlag4 = false;
            boolean weekFlag5 = false;
            boolean weekFlag6 = false;
            
            String dateYear =(String) mapList.get(0).get("year");
            
            String weekNoTitle1=(String) weekNoTitleMap.get("weekNoTitle1");
            String TitleRef1=weekNoTitle1.split("-")[0];
            String Title1 = getPerformDate(TitleRef1,dateYear);
            
        	String weekNoTitle2=(String) weekNoTitleMap.get("weekNoTitle2");
        	String TitleRef2=weekNoTitle2.split("-")[0];
        	String Title2 = getPerformDate(TitleRef2,dateYear);
        	
        	String weekNoTitle3=(String) weekNoTitleMap.get("weekNoTitle3");
        	String TitleRef3=weekNoTitle3.split("-")[0];
        	String Title3 = getPerformDate(TitleRef3,dateYear);		
        	
        	String weekNoTitle4=(String) weekNoTitleMap.get("weekNoTitle4");
        	String TitleRef4=weekNoTitle4.split("-")[0];
        	String Title4 = getPerformDate(TitleRef4,dateYear);	
        	
        	String weekNoTitle5=(String) weekNoTitleMap.get("weekNoTitle5");
        	String Title5 ="";
        	if(StringUtil.isNotEmpty(weekNoTitle5)) {
        		String TitleRef5=weekNoTitle5.split("-")[0];
            	Title5 = getPerformDate(TitleRef5,dateYear);		
        	}
        	String weekNoTitle6=(String) weekNoTitleMap.get("weekNoTitle6");
        	String Title6 ="";
        	if(StringUtil.isNotEmpty(weekNoTitle6)) {
        		String TitleRef6=weekNoTitle6.split("-")[0];
            	Title6 = getPerformDate(TitleRef6,dateYear);
        	}
            //批量处理数据
            for (Map<String, Object> map : mapList) {
            	RemittanceTrackDto remittanceTrackDto = new RemittanceTrackDto();
            	String dataMonth = map.get("dataMonth").toString();
            	remittanceTrackDto.setDataMonth(dataMonth);
                String year = map.get("year").toString();
                remittanceTrackDto.setYear(Integer.parseInt(year));
                String projectNo = map.get("projectNo").toString();
                remittanceTrackDto.setProjectNo(projectNo);
                String yearMonthVersion = map.get("yearMonthVersion").toString();
                remittanceTrackDto.setYearMonthVersion(Integer.parseInt(yearMonthVersion));
                String weekVersion = map.get("weekVersion").toString();
                remittanceTrackDto.setWeekVersion(Integer.parseInt(weekVersion));
                String partnerName = map.get("partnerName").toString();
                remittanceTrackDto.setPartnerName(partnerName);
                String sortNo = map.get("sortNo").toString();
                remittanceTrackDto.setSortNo(Integer.parseInt(sortNo));
//                String trackType = map.get("trackType").toString();
                remittanceTrackDto.setTrackType(trackType);
                String userIdCreate = map.get("userIdCreate").toString();
                remittanceTrackDto.setUserIdCreate(Integer.parseInt(userIdCreate));
                remittanceTrackDto.setUserIdUpdate(Integer.parseInt(userIdCreate));
                    String weekTotal1 = map.get("weekTotal1").toString();
                    String xjAccount1=null;
                    String dfAccount1=null;
                    if(weekTotal1!=null) {
                    	if(weekFlag1 || (StringUtil.isNotEmpty(weekNoTitle1) && (Title1.equals(performSwitchFinishDate) || (Title1.compareTo(performDate)>=0)))) {
                    		weekFlag1 = true;
                    		weekFlag2 = true;
                    		if(map.containsKey("xjAccount1") && !StringUtils.isEmpty(map.get("xjAccount1"))) {
                				xjAccount1 = map.get("xjAccount1").toString();
                				remittanceTrackDto.setXjAccount1(xjAccount1);
                			}
                			if(map.containsKey("dfAccount1") && !StringUtils.isEmpty(map.get("dfAccount1"))) {
                				dfAccount1 = map.get("dfAccount1").toString();
                				remittanceTrackDto.setDfAccount1(dfAccount1);
                			}
                    	}else {//表示不完全导入
                    		weekNoTitleMap.put("importFlag", 1);
                    	}
                    }

                	String weekTotal2 = map.get("weekTotal2").toString();
                	String xjAccount2=null;
                	String dfAccount2=null;
                	if(weekTotal2!=null) {
                		if(weekFlag2 || (StringUtil.isNotEmpty(weekNoTitle2) && (Title2.equals(performSwitchFinishDate) || (Title2.compareTo(performDate)>=0)))) {
                			weekFlag2 = true;
                			weekFlag3 = true;
                			if(map.containsKey("xjAccount2") && !StringUtils.isEmpty(map.get("xjAccount2"))) {
                				xjAccount2 = map.get("xjAccount2").toString();
                				remittanceTrackDto.setXjAccount2(xjAccount2);
                			}
                			if(map.containsKey("dfAccount2") && !StringUtils.isEmpty(map.get("dfAccount2"))) {
                				dfAccount2 = map.get("dfAccount2").toString();
                				remittanceTrackDto.setDfAccount2(dfAccount2);
                			}
                		}else {//表示不完全导入
                			weekNoTitleMap.put("importFlag", 1);
                    	}
	                }

	                String weekTotal3 = map.get("weekTotal3").toString();
	                String xjAccount3=null;
	                String dfAccount3=null;
	                if(weekTotal3!=null) {
	                	if(weekFlag3 || (StringUtil.isNotEmpty(weekNoTitle3) && (Title3.equals(performSwitchFinishDate) || (Title3.compareTo(performDate)>=0) ))) {
	                		weekFlag3 = true;
	                		weekFlag4 = true;
	                		if(map.containsKey("xjAccount3") && !StringUtils.isEmpty(map.get("xjAccount3"))) {
                				xjAccount3 = map.get("xjAccount3").toString();
                				remittanceTrackDto.setXjAccount3(xjAccount3);
                			}
                			if(map.containsKey("dfAccount3") && !StringUtils.isEmpty(map.get("dfAccount3"))) {
                				dfAccount3 = map.get("dfAccount3").toString();
                				remittanceTrackDto.setDfAccount3(dfAccount3);
                			}
	                	}else {//表示不完全导入
	                		weekNoTitleMap.put("importFlag", 1);
                    	}
	                }

	                String weekTotal4 = map.get("weekTotal4").toString();
	                String weekTotal5 =null;
	                String weekTotal6 =null;
	                if("5".equals(weekNo) || "6".equals(weekNo)) {
	                	weekTotal5 = map.get("weekTotal5").toString();
	                }
	                if("6".equals(weekNo)) {
	                	weekTotal6 = map.get("weekTotal6").toString();
	                }
	                String xjAccount4=null;
	                String dfAccount4=null;
	                if(weekTotal4!=null) {
	                	if(weekFlag4 || (StringUtil.isNotEmpty(weekNoTitle4) && (Title4.equals(performSwitchFinishDate) || (Title4.compareTo(performDate)>=0)))) {
	                		weekFlag4 = true;
	                		weekFlag5 = true;
	                		if(map.containsKey("xjAccount4") && !StringUtils.isEmpty(map.get("xjAccount4"))) {
                				xjAccount4 = map.get("xjAccount4").toString();
                				remittanceTrackDto.setXjAccount4(xjAccount4);
                			}
                			if(map.containsKey("dfAccount4") && !StringUtils.isEmpty(map.get("dfAccount4"))) {
                				dfAccount4 = map.get("dfAccount4").toString();
                				remittanceTrackDto.setDfAccount4(dfAccount4);
                			}
	                	}else if(weekTotal5 ==null&&weekTotal6 ==null&&!weekFlag1
	                			&&!weekFlag2&&!weekFlag3&&!weekFlag4){
	                		weekNoTitleMap.put("importFlag", 2);//完全不导入
	                	}else {//表示不完全导入
	                		weekNoTitleMap.put("importFlag", 1);
                    	}
	                }
                
	                if("5".equals(weekNo) || "6".equals(weekNo)) {
	                	String xjAccount5=null;
	                	String dfAccount5=null;
	                	if(weekTotal5!=null) {
	                		if(weekFlag5 || (StringUtil.isNotEmpty(weekNoTitle5) && (Title5.equals(performSwitchFinishDate)|| (Title5.compareTo(performDate)>=0)))) {
	                			weekFlag5 = true;
	                			weekFlag6 = true;
		                		
	                			if(map.containsKey("xjAccount5") && !StringUtils.isEmpty(map.get("xjAccount5"))) {
	                				xjAccount5 = map.get("xjAccount5").toString();
	                				remittanceTrackDto.setXjAccount5(xjAccount5);
	                			}
	                			if(map.containsKey("dfAccount5") && !StringUtils.isEmpty(map.get("dfAccount5"))) {
	                				dfAccount5 = map.get("dfAccount5").toString();
	                				remittanceTrackDto.setDfAccount5(dfAccount5);
	                			}
		                	}else if(weekTotal6 ==null&&!weekFlag1
		                			&&!weekFlag2&&!weekFlag3&&!weekFlag4&&!weekFlag5){
		                		weekNoTitleMap.put("importFlag", 2);
		                	}else {//表示不完全导入
		                		weekNoTitleMap.put("importFlag", 1);
	                    	}
	                }
                }

                
	                if("6".equals(weekNo)) {
	                	String xjAccount6=null;
	                	String dfAccount6=null;
	                	if(weekTotal6!=null) {
	                		if(weekFlag6 || (StringUtil.isNotEmpty(weekNoTitle6) && (Title6.equals(performSwitchFinishDate) || (Title6.compareTo(performDate)>=0)))) {
	                			if(map.containsKey("xjAccount6") && !StringUtils.isEmpty(map.get("xjAccount6"))) {
	                				xjAccount6 = map.get("xjAccount6").toString();
	                				remittanceTrackDto.setXjAccount6(xjAccount6);
	                			}
	                			if(map.containsKey("dfAccount6") && !StringUtils.isEmpty(map.get("dfAccount6"))) {
	                				dfAccount6 = map.get("dfAccount6").toString();
	                				remittanceTrackDto.setDfAccount6(dfAccount6);
	                			}
		                	}else if(!weekFlag1&&!weekFlag2&&!weekFlag3&&!weekFlag4&&!weekFlag5&&!weekFlag6){//表示不完全导入
		                		weekNoTitleMap.put("importFlag", 2);
	                    	}
		                }
                }
	                if(StringUtil.isNotEmpty(remittanceTrackDto.getXjAccount1()) ||
	                   StringUtil.isNotEmpty(remittanceTrackDto.getXjAccount2()) ||
	                   StringUtil.isNotEmpty(remittanceTrackDto.getXjAccount3()) ||
	                   StringUtil.isNotEmpty(remittanceTrackDto.getXjAccount4()) ||
	                   StringUtil.isNotEmpty(remittanceTrackDto.getXjAccount5()) ||
	                   StringUtil.isNotEmpty(remittanceTrackDto.getXjAccount6()) ||
	                   StringUtil.isNotEmpty(remittanceTrackDto.getDfAccount1()) ||
	                   StringUtil.isNotEmpty(remittanceTrackDto.getDfAccount2()) ||
	                   StringUtil.isNotEmpty(remittanceTrackDto.getDfAccount3()) ||
	                   StringUtil.isNotEmpty(remittanceTrackDto.getDfAccount4()) ||
	                   StringUtil.isNotEmpty(remittanceTrackDto.getDfAccount5()) ||
	                   StringUtil.isNotEmpty(remittanceTrackDto.getDfAccount6()) ) {
	                	remittanceTrackList.add(remittanceTrackDto);
	                }

            }
            int size = 0;
            if(!CollectionUtils.isEmpty(remittanceTrackList)) {
            	size=remittanceTrackList.size();
            }
            logger.info("回款跟踪数据，导入，待导入数量为:"+size+"条");
            remittanceTrackImportDto.setRemittanceTrackDtoList(remittanceTrackList);
            resultData = insertRemittanceTrack(remittanceTrackImportDto);
            logger.info("回款跟踪数据，导入成功");
            return resultData;
        } catch (Exception e) {
        	e.printStackTrace();
            resultData.setReturnCode("-1");
            return resultData;
        }
    }
    
    /**
     * 
     * desc:关账日期格式化 2020-01-04
     * 2020年4月24日
     */
    private String getPerformDate(String weekEndDateStr,String performYear) {
    	String weekBef = weekEndDateStr.substring(0, weekEndDateStr.indexOf("."));
    	String month = "";
    	if(Integer.parseInt(weekBef)<10) {
    		month = "0"+weekBef;
    	}else {
    		month = weekBef;
    	}
    	String weekAf = weekEndDateStr.substring(weekEndDateStr.indexOf(".")+1,weekEndDateStr.length());
    	String day = "";
    	if(Integer.parseInt(weekAf)<10) {
    		day = "0"+weekAf;
    	}else {
    		day = weekAf;
    		
    	}
		return performYear+"-"+month+"-"+day;
	}

	/**
     * 
     * desc:获取关账日期
     * 2020年4月20日
     */
    private Map<String,Object>  getPerformSwitchDate(String trackType,LinkedHashMap<String, Object> switchWeek) {
    	Integer performSwitchSortNo = (Integer) switchWeek.get("sortNo");
    	Map<String,Object> map = new HashMap<>();
		map.put("sortNo", performSwitchSortNo);
		
		ResultData reback=null;
		String performStartDate = "";
		String performEmdDate = "";
		String finishStartDate = "";
		String finishEndDate = "";
		try {
			reback = querySwitchFinishWeek(map);
			logger.info("回款跟踪数据，导入,获取关账日期为:"+reback);
			LinkedHashMap<String, Object> switchFinishWeek =null;
			if(reback !=null && reback.getReturnData() !=null) {
				switchFinishWeek = (LinkedHashMap<String, Object>) reback.getReturnData();
				String year = (String) switchFinishWeek.get("year");
				String weekStartDateStr = (String) switchFinishWeek.get("weekStartDateStr");
				String weekEndDateStr = (String) switchFinishWeek.get("weekEndDateStr");
				performStartDate = getPerformDate(weekStartDateStr,year);
				performEmdDate = getPerformDate(weekEndDateStr,year);
				if("0".equals(trackType)) {//预计回款
					finishStartDate = formatDate(performStartDate,8);
					finishEndDate = formatDate(performEmdDate,8);
				}else if("1".equals(trackType)) {//实际回款
					finishStartDate = formatDate(performStartDate,1);
					finishEndDate = formatDate(performEmdDate,1);
				}
				map.put("finishStartDate", finishStartDate);
				map.put("finishEndDate", finishEndDate);
			}
			logger.info("回款跟踪数据，导入,格式化关账日期为:"+finishStartDate+"-"+finishEndDate);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("回款跟踪数据，导入,格式化关账日期失败");
		}
    	
		return map;
	}

    /**
     * 
     * desc:获取关账之后能导入的日期  回款预计+7天，实际回款+1天
     * 2020年4月24日
     * @throws ParseException 
     */
	private String formatDate(String performDate, int i) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(performDate));
		cal.set(Calendar.DAY_OF_MONTH,cal.get(Calendar.DAY_OF_MONTH)+i);
		Date date=cal.getTime();
		return sdf.format(date);
	}

	/**
     * desc:插入
     * 2020年4月15日
     */
    public ResultData insertRemittanceTrack(RemittanceTrackImportDto remittanceTrackImportDto) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "remittanceTrack" + "/insertImput";
        ResultData reback = post(url, remittanceTrackImportDto);
        return  reback;
    }
    
    /**
     * desc:导入时查询关账数据
     * 2020年4月17日
     * @throws Exception 
     */
    public ResultData querySwitchWeek(Map<String, Object> map) throws Exception {
    	String url = SystemParam.getWebConfigValue("RestServer") + "remittanceTrack" + "/querySwitchWeek/{param}";
    	ResultData reback = get(url, map);
    	return  reback;
    }
    
    public ResultData querySwitchFinishWeek(Map<String, Object> map) throws Exception {
    	String url = SystemParam.getWebConfigValue("RestServer") + "remittanceTrack" + "/querySwitchFinishWeek/{param}";
    	ResultData reback = get(url, map);
    	return  reback;
    }
}
