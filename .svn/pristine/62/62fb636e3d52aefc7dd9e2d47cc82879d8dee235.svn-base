package cn.com.eju.deal.keFuWj.service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.seqNo.service.SeqNoService;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.kefuWj.KeFuWjDto;
import cn.com.eju.deal.dto.kefuWj.KefuWjDDaDto;
import cn.com.eju.deal.dto.kefuWj.KefuWjDTmDto;


@Service("keFuWjService")
public class KeFuWjService extends BaseService {

	@Resource(name = "seqNoService")
    private SeqNoService seqNoService;
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
	/**
     * 查询列表
     */
    public ResultData getKeFuWjList(Map<String, Object> reqMap, PageInfo pageInfo)throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "keFuWj" + "/getKeFuWjList/{param}";
        putWjCitys(reqMap);
        ResultData<?> reback = get(url, reqMap, pageInfo);
        return reback;
    }
    
    /**
     * desc:获取已调查列表
     * 2019年7月29日
     * author:zhenggang.Huang
     */
    public ResultData<?> getInvestedList(Integer storeId) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "keFuWj" + "/getInvestedList/{storeId}";
        ResultData<?> backResult = get(url, storeId);
        return backResult;
    }

    /**
     * desc:重新组装wjCitys
     * 2019年6月18日
     * author:zhenggang.Huang
     */
    private void putWjCitys(Map<String, Object> reqMap) {
    	if(reqMap.containsKey("wjCitys") && StringUtil.isNotEmpty((String)reqMap.get("wjCitys"))) {
        	String centerIds = (String)reqMap.get("wjCitys");
        	String[] centerIdArr = centerIds.split(";");
        	reqMap.put("wjCitys", Arrays.asList(centerIdArr));
        }
	}
	/**
     * 根据id查询客服反馈工单详情
     * @throws Exception
     */
    public ResultData<?> getKeFuOrderById(Integer id) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "keFuOrder" + "/getKeFuOrderById/{id}";
        ResultData<?> backResult = get(url, id);
        return backResult;
    }


    /**
     * 根据storeId查看客服反馈工单列表
     * @throws Exception
     */
    public ResultData<?> getKeFuOrderListByStoreId(Integer storeId) throws Exception{
        String url = SystemParam.getWebConfigValue("RestServer") + "keFuOrder" + "/getKeFuOrderListByStoreId/{storeId}";
        ResultData<?> backResult = get(url, storeId);
        return backResult;
    }

    /**
     *
     * desc:获取适用城市列表
     * 2019年6月17日
     * author:zhenggang.Huang
     */
    public ResultData<?> getWjCityList() throws Exception{
    	String url = SystemParam.getWebConfigValue("RestServer") + "keFuWj" + "/getWjCityList";
    	ResultData<?> backResult = get(url);
    	return backResult;
    }


    public void finalize(Map<String, Object> reqMap) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "keFuWj" + "/finalize";
        //变更人
        reqMap.put("wjConfirmUser", UserInfoHolder.getUserId());
        put(url, reqMap);
    }

    public ResultData<?> queryKeFuWjList(Integer id) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "keFuWj" + "/{id}";

        ResultData<?> backResult = get(url, id);

        return backResult;
    }

    public ResultData<?> queryKeFuWjHById(int id) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "keFuWj" + "/queryKeFuWjHById/{id}";
        ResultData<?> backResult = get(url, id);
        return backResult;
    }

    public String queryCityIsAvailable(Map<String, Object> reqMap) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "keFuWj" + "/queryCityIsAvailable";

        ResultData<?> resultData = post(url, reqMap);

        return resultData.getReturnMsg();
    }


    public ResultData<?> update(Map<String, Object> reqMap) throws Exception {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "keFuWj" + "/update";
        reqMap.put("userCreate", UserInfoHolder.getUserId());
        ResultData<?> resultData = post(url, reqMap);

        return resultData;
    }

    /**
	 * desc:导入
	 * 2019年6月19日
	 * author:zhenggang.Huang
	 *
	 * @throws Exception
	 */
	public Map<String, Object> wjImport(Map<String, Object> reqMap, Map<String, Object> rspMap,
			HttpServletRequest request) throws Exception {

		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

		MultipartFile multFile = multiRequest.getFile("historyDataFile");

		String fileName = multFile.getOriginalFilename();

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

			// 导入check
			Map<String, Object> rtnMap = checkImport(sheet1);
			if (null != rtnMap) {
				return rtnMap;
			}

			int count = 0;
			KeFuWjDto keFuWjDto = new KeFuWjDto();
			List<KefuWjDTmDto> wjDTmList = new ArrayList<>();//题目集合
			List<KefuWjDDaDto> wjDDaList = new ArrayList<>();//答案集合
			Boolean insertResult = true;
			int countInt = 2;//答案排序
			Map<String,Object> indexMap = new HashMap<>();
			indexMap.put("insertResult", insertResult);
			indexMap.put("countInt", countInt);
			for (Row r : sheet1) {
				count++;
				indexMap = getSheetCellValue(count,indexMap, wb, r,keFuWjDto,wjDTmList,wjDDaList);
				if (indexMap.containsKey("insertResult") && indexMap.get("insertResult") != null &&
						!(Boolean) indexMap.get("insertResult")) {
					break;
				}
				
			}
			// 数据导入
			if(indexMap.containsKey("insertResult") && (boolean) indexMap.get("insertResult")) {
				rspMap = setWjtmMaxScore(keFuWjDto,rspMap);//获取每道题最大分值
				if(rspMap.containsKey(Constant.RETURN_CODE_KEY) 
						&& ReturnCode.FAILURE.equals(rspMap.get(Constant.RETURN_CODE_KEY))) {
					return rspMap;
				}
				insertResult = insertWjImport(keFuWjDto, fileName);
			}else{
				wjDTmList.clear();
				wjDDaList.clear();
				if(indexMap.containsKey(Constant.RETURN_MSG_KEY) 
						&& indexMap.get(Constant.RETURN_MSG_KEY) != null) {
					rspMap.put(Constant.RETURN_MSG_KEY, indexMap.get(Constant.RETURN_MSG_KEY));
				}else {
					rspMap.put(Constant.RETURN_MSG_KEY, "导入数据失败！");
				}
				rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
				return rspMap;
			}
			wb.close();
			rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
			rspMap.put(Constant.RETURN_MSG_KEY, "导入数据成功！");
		} catch (IOException e1) {
			logger.error("sceneInCommission", "SceneInCommissionController", "imput", "", null, "", "", e1);
			rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
			rspMap.put(Constant.RETURN_MSG_KEY, "导入数据失败！");
			throw new Exception();
		}

		return rspMap;
	}

	/**
	 * desc:获取每道题的最大分值 
	 * 计算方式:单选题-最大分值  多选题-选项分值和   问答题-0
	 * 2019年6月28日
	 * author:zhenggang.Huang
	 */
	private Map<String,Object> setWjtmMaxScore(KeFuWjDto keFuWjDto,Map<String,Object> rspMap) {
		if(StringUtils.isEmpty(keFuWjDto)) {
			rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
			rspMap.put(Constant.RETURN_MSG_KEY, "导入数据失败！");
			return rspMap;
		}
		//题目集合
		try {
			List<KefuWjDTmDto> wjDTmList = keFuWjDto.getWjDTmList();
			if(wjDTmList != null && wjDTmList.size() >0) {
				for (KefuWjDTmDto kefuWjDTmDto : wjDTmList) {
					Integer wjtmMaxScore = 0;
					String index = kefuWjDTmDto.getIndex();//绑定题目(分值、选项)
					String wjtmType = getWjtmType(kefuWjDTmDto.getWjtmType().trim());//题型 (单选题、多选题、问答题)
					String wjtmflType = getWjtmflType(kefuWjDTmDto.getWjtmflType().trim());//业务类型
					if(StringUtil.isEmpty(wjtmType) || StringUtil.isEmpty(wjtmflType)) {
						logger.error("导入问卷的题型或业务类型不存在");
						rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
						rspMap.put(Constant.RETURN_MSG_KEY, "导入问卷的题型或业务类型不存在！");
						return rspMap;
					}
					kefuWjDTmDto.setWjtmflType(wjtmflType);
					kefuWjDTmDto.setWjtmType(wjtmType);
					//分值集合 
					List<Integer> wjtmMaxScoreList = new ArrayList<>();
					List<KefuWjDDaDto> wjDDaList = kefuWjDTmDto.getWjDDaList();//答案
					if(wjDDaList != null && wjDDaList.size() > 0) {
						for (KefuWjDDaDto kefuWjDDa : wjDDaList) {
							if(index.equals(kefuWjDDa.getIndex())) {//判断是否为同一个题目
								wjtmMaxScoreList.add(kefuWjDDa.getDirectScore());
							}
						}
					}
					if(DictionaryConstants.Wj_wjtmType_wd.equals(wjtmType)) {//问答题 0
						kefuWjDTmDto.setWjtmMaxScore(0);
					}else if(DictionaryConstants.Wj_wjtmType_dxs.equals(wjtmType)) {//多选题  选项分值和
						if(wjtmMaxScoreList != null && wjtmMaxScoreList.size() > 0) {
							for (Integer maxScores : wjtmMaxScoreList) {
								wjtmMaxScore += maxScores;
							}
						}
					}else if(DictionaryConstants.Wj_wjtmType_dx.equals(wjtmType)) {//单选题  获取最大值
						if(wjtmMaxScoreList != null && wjtmMaxScoreList.size() > 0) {
							wjtmMaxScore = Collections.max(wjtmMaxScoreList);
						}
					}
					kefuWjDTmDto.setWjtmMaxScore(wjtmMaxScore);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("导入问卷获取每道题的最大分值失败");
			rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
			rspMap.put(Constant.RETURN_MSG_KEY, "导入数据失败！");
			return rspMap;
		}
		
		return rspMap;
	}

    /**
     * desc:获取题型(单选、多选、问答题)
     * 2019年6月28日
     * author:zhenggang.Huang
     */
    private String getWjtmType(String wjtmType) {
    	if(StringUtil.isEmpty(wjtmType)) {
			return wjtmType;
		}
		switch(wjtmType){
		    case "单选题" :
		    	wjtmType = DictionaryConstants.Wj_wjtmType_dx;
		       break; 
		    case "多选题" :
		    	wjtmType = DictionaryConstants.Wj_wjtmType_dxs;
		    	break; 
		    case "问答题" :
		    	wjtmType = DictionaryConstants.Wj_wjtmType_wd;
		       break; 
		    default :
		    	wjtmType = null;
		    	
		}
		return wjtmType;
	}

	/**
     * desc:获取业务类型
     * 2019年6月28日
     * author:zhenggang.Huang
     */
	private String getWjtmflType(String wjtmflType) {
		if(StringUtil.isEmpty(wjtmflType)) {
			return wjtmflType;
		}
		switch(wjtmflType){
		    case "品牌服务" :
		    	wjtmflType = DictionaryConstants.Wj_wjtmflType_pp;
		       break; 
		    case "培训服务" :
		    	wjtmflType = DictionaryConstants.Wj_wjtmflType_px;
		    	break; 
		    case "招聘服务" :
		    	wjtmflType = DictionaryConstants.Wj_wjtmflType_zp;
		       break; 
		    case "系统服务" :
		    	wjtmflType = DictionaryConstants.Wj_wjtmflType_xt;
		    	break; 
		    case "交易服务" :
		    	wjtmflType = DictionaryConstants.Wj_wjtmflType_jy;
		    	break; 
		    case "公盘业务" :
		    	wjtmflType = DictionaryConstants.Wj_wjtmflType_gp;
		    	break; 
		    case "联动业务" :
		    	wjtmflType = DictionaryConstants.Wj_wjtmflType_ld;
		    	break; 
		    case "其他" :
		    	wjtmflType = DictionaryConstants.Wj_wjtmflType_qt;
		    	break; 
		    default :
		    	wjtmflType = null;
		}
		return wjtmflType;
	}
	
	/**
	 * desc:导入check
	 * 2019年6月19日
	 * author:zhenggang.Huang
	 */
	private Map<String, Object> checkImport(Sheet sheet1) {
		Map<String, Object> rspMap = new HashMap<String, Object>();
		// 无数据
		if (sheet1.getLastRowNum() == 0) {
			rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
			rspMap.put(Constant.RETURN_MSG_KEY, "导入文件数据格式有误！");
			return rspMap;
		}

		return null;
	}

	/**
	 * 取得excel值
	 *
	 * @param r excel行
	 * @return
	 * @throws Exception 
	 */
	private Map<String,Object> getSheetCellValue(int count,Map<String,Object> indexMap, Workbook wb, Row r, 
			KeFuWjDto keFuWjDto,List<KefuWjDTmDto> wjDTmList,List<KefuWjDDaDto> wjDDaList) throws Exception {

		String wjName = "";
		String wjTitle = "客户满意度调查";
		
			if (count == 1 && r.getCell(0) != null) {// 第一行
				wjName = r.getCell(0).getStringCellValue().trim(); 
				if(!StringUtil.isEmpty(wjName)) {
	//				wjName = wjName.split("：")[1];
					ResultData<?> resultData = this.seqNoService.getSeqNo("TYPE_WJMB");//问卷编号
					keFuWjDto.setWjCode((String)resultData.getReturnData());
					keFuWjDto.setWjName(wjName);// 问卷模板名称
					keFuWjDto.setWjTitle(wjTitle);
	//				keFuWjDto.setWjStatus("24701");//草签
					keFuWjDto.setUserCreate(UserInfoHolder.getUserId());//创建人
	//				keFuWjDto.setDateCreate(new Date());
					return indexMap;
				}else {
					indexMap.put("insertResult", false);
					return indexMap;
				}
			} else if (count == 2) {// 跳过第二行
				indexMap.put("insertResult", true);
				return indexMap;
			}
	
			String index = "";
			try {
				if (r.getCell(0) != null) {
					r.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
					if(!StringUtil.isEmpty(r.getCell(0).getStringCellValue())) {
	//					index =  Double.valueOf(r.getCell(0).getStringCellValue()).intValue()+""; // 序号
						index =  Integer.parseInt(r.getCell(0).getStringCellValue())+""; // 序号
					}
				}
			} catch (Exception e) {
				indexMap.put("insertResult", false);
				indexMap.put(Constant.RETURN_MSG_KEY, "序号列和分数列为数字格式，请重新输入！");
				return indexMap;
			}
	
			String wjtmflType = "";
			if (r.getCell(1) != null) {
				if(!StringUtil.isEmpty(r.getCell(1).getStringCellValue())) {
					wjtmflType = r.getCell(1).getStringCellValue().trim(); // 业务类型
				}
			}
	
			String wjtmValue = "";
			if (r.getCell(2) != null) {
				if(!StringUtil.isEmpty(r.getCell(2).getStringCellValue())) {
					wjtmValue = r.getCell(2).getStringCellValue().trim(); // 题目
				}
			}
	
			String wjtmType = "";
			if (r.getCell(3) != null) {
				if(!StringUtil.isEmpty(r.getCell(3).getStringCellValue())) {
					wjtmType = r.getCell(3).getStringCellValue().trim(); // 题型
				}
			}
	
			String wjxx = "";
			if (r.getCell(4) != null) {
				if(!StringUtil.isEmpty(r.getCell(4).getStringCellValue())) {
					wjxx = r.getCell(4).getStringCellValue().trim(); // 选项
				}
			}
	
			String directScore ="-1";
			try {
				if (r.getCell(5) != null) {
	//				r.getCell(5).setCellType(Cell.CELL_TYPE_NUMERIC);
					r.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
	//				directScore = ((int)r.getCell(5).getNumericCellValue()+"").trim(); // 指导分值
					if(!StringUtil.isEmpty(r.getCell(5).getStringCellValue())) {
						directScore =  Integer.parseInt(r.getCell(5).getStringCellValue())+""; // 指导分值
					}
				}
			} catch (Exception e) {
				indexMap.put("insertResult", false);
				indexMap.put(Constant.RETURN_MSG_KEY, "序号列和分数列为数字格式，请重新输入！");
				return indexMap;
			}
	
			// 数据添加
			//题目 带序号一行
			if( !StringUtil.isEmpty(index) && !StringUtil.isEmpty(wjtmflType)
					&&  !StringUtil.isEmpty(wjtmValue) && !StringUtil.isEmpty(wjtmType)) {
				KefuWjDTmDto kefuWjDTmDto = new KefuWjDTmDto();
				kefuWjDTmDto.setIndex(index);//序号
				kefuWjDTmDto.setWjtmflType(wjtmflType);//业务类型
				kefuWjDTmDto.setWjtmValue(wjtmValue);//题目内容
				kefuWjDTmDto.setWjtmType(wjtmType);//题型
				kefuWjDTmDto.setUserCreate(UserInfoHolder.getUserId());//创建人
				kefuWjDTmDto.setWjtmSortindex(Integer.parseInt(index));//排序字段
				if(!StringUtil.isEmpty(wjxx) && !StringUtil.isEmpty(directScore) && !"-1".equals(directScore)) {//答案集合
					KefuWjDDaDto kefuWjDDaDto =new KefuWjDDaDto();
					kefuWjDDaDto.setIndex(index);//序号
					kefuWjDDaDto.setWjxx(wjxx);//选项
					kefuWjDDaDto.setDirectScore(Integer.parseInt(directScore));//指导分值
					kefuWjDDaDto.setUserCreate(UserInfoHolder.getUserId());//创建人
					kefuWjDDaDto.setSortIndex(1);//答案排序
					wjDDaList.add(kefuWjDDaDto);
					kefuWjDTmDto.setWjDDaList(wjDDaList);
				}
				wjDTmList.add(kefuWjDTmDto);//题目集合
				if(wjDTmList != null && wjDTmList.size()>0) {
					keFuWjDto.setWjDTmList(wjDTmList);
				}
				indexMap.put("countInt", 2);
			}else {//不带序号行
				//答案  选项、分数
				if( !StringUtil.isEmpty(wjxx) && !StringUtil.isEmpty(directScore) && !"-1".equals(directScore)) {
					KefuWjDDaDto kefuWjDDaDto =new KefuWjDDaDto();
					if(wjDTmList != null && wjDTmList.size()>0) {
						KefuWjDTmDto kefuWjDTmDto = wjDTmList.get(wjDTmList.size()-1);
						if(StringUtil.isEmpty(index)) {//说明上一题得选项
							index = kefuWjDTmDto.getIndex();
							kefuWjDDaDto.setIndex(index);
						}else {//数据有问题
							indexMap.put("insertResult", false);
							return indexMap;
						}
						if(indexMap.containsKey("countInt")  && indexMap.get("countInt") != null) {
							int countIntStr = (int) indexMap.get("countInt");
							kefuWjDDaDto.setSortIndex(countIntStr);
							countIntStr++;
							indexMap.put("countInt", countIntStr);
						}
						kefuWjDDaDto.setWjxx(wjxx);//选项
						kefuWjDDaDto.setDirectScore(Integer.parseInt(directScore));//指导分值
						kefuWjDDaDto.setUserCreate(UserInfoHolder.getUserId());//创建人
						wjDDaList.add(kefuWjDDaDto);
						kefuWjDTmDto.setWjDDaList(wjDDaList);
					}
				}
			}
		
		return indexMap;
	}

	/**
	 * 导入操作
	 *
	 * @param mapList
	 * @return
	 */
	private Boolean insertWjImport(KeFuWjDto keFuWjDto,String fileName) {
		try {
			// 更新数据和新增的数据分开，批量处理
			ResultData resultData = insertWjImport(keFuWjDto);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// excel类型判断
	private String getCellValue(Workbook wb, Cell cell) {
		FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
		String rtnVal = "";

		DecimalFormat df = new DecimalFormat("0.00");
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			rtnVal = df.format(cell.getNumericCellValue());
		}
		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			rtnVal = cell.getStringCellValue();
		}
		if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
			evaluator.evaluateFormulaCell(cell);
			rtnVal = df.format(cell.getNumericCellValue());
		}
		return rtnVal;
	}

	/**
	 * desc:插入库中
	 * 2019年6月20日
	 * author:zhenggang.Huang
	 */
	public ResultData insertWjImport(KeFuWjDto keFuWjDto) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "keFuWj" + "/imputAdd";
        ResultData reback = post(url, keFuWjDto);
        return  reback;
    }

	public void remove(Map<String, Object> reqMap) throws Exception {
		String url = SystemParam.getWebConfigValue("RestServer") + "keFuWj" + "/remove";
		put(url, reqMap);
	}

	public ResultData<?> getWjCheckCityList(Map<String, Object> reqMap) throws Exception {
		//调用 接口
		String url = SystemParam.getWebConfigValue("RestServer") + "keFuWj" + "/getWjCheckCityList";

		ResultData<?> resultData = post(url, reqMap);

		return resultData;
	}
	
	/**
	 * desc:获取门店信息
	 * 2019年7月30日
	 * author:zhenggang.Huang
	 */
	public ResultData<?> getStoreData(Integer storeId) throws Exception {
	        String url = SystemParam.getWebConfigValue("RestServer") + "satisfactionSurvey" + "/getStoreData/{id}";
	        ResultData reback = get(url, storeId);
	        return reback;
	    }

	/**
	 * desc:获取问卷调查信息
	 * 2019年7月30日
	 * author:zhenggang.Huang
	 */
    public ResultData<?> getSurveyData(Integer surveyId) throws Exception {
        String url = SystemParam.getWebConfigValue("RestServer") + "satisfactionSurvey" + "/getSurveyData/{id}";
        ResultData reback = get(url, surveyId);
        return reback;
    }

    public ResultData<?> getEvaluationList(Integer storeId) throws Exception {
		String url = SystemParam.getWebConfigValue("RestServer") + "keFuWj" + "/getEvaluationList/{storeId}";
		ResultData<?> backResult = get(url, storeId);
		return backResult;
    }

	public ResultData<?> getEvaluationData(Integer id) throws Exception {
		String url = SystemParam.getWebConfigValue("RestServer") + "keFuWj" + "/getEvaluationData/{id}";
		ResultData reback = get(url, id);
		return reback;
	}
}
