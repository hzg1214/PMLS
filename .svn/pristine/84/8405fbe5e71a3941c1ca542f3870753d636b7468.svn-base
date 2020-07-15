package cn.com.eju.deal.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.kefuWj.KeFuWjDto;
import cn.com.eju.deal.dto.kefuWj.KefuWjDDaDto;
import cn.com.eju.deal.dto.kefuWj.KefuWjDTmDto;
import cn.com.eju.deal.poi.common.ExcelColumnSet;
import cn.com.eju.deal.poi.common.ExcelStyleEnum;
import cn.com.eju.deal.poi.common.ExcelUseXmlAbstract;
import cn.com.eju.deal.poi.common.SpeedSheetWriter;

public class ExcelForKefuWj extends ExcelUseXmlAbstract {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

	private short bodyBlackStyle;
	private short bodyLeftStyle;
    private short bodyRedStyle;
    private short dateBlackStyle;
    private short dateRedStyle;
    //问卷-业务类型
    private String Wj_wjtmflType_pp = "品牌服务";//	品牌服务	
    
    private String Wj_wjtmflType_px = "培训服务";//	培训服务	
    
    private String Wj_wjtmflType_zp = "招聘服务";//	招聘服务	
    
    private String Wj_wjtmflType_xt = "系统服务";//	系统服务	
    
    private String Wj_wjtmflType_jy = "交易服务";//	交易服务	
    
    private String Wj_wjtmflType_gp = "公盘业务";//	公盘业务	
    
    private String Wj_wjtmflType_ld = "联动业务";//	联动业务	
    
    private String Wj_wjtmflType_qt = "其他";//	其他	
    
    //问卷-题型
    private String Wj_wjtmType_dx = "单选题";//	单选题	
    
    private String Wj_wjtmType_dxs = "多选题";//	多选题	
    
    private String Wj_wjtmType_wd = "问答题";//	问答题

    public void downloadSheet(List<LinkedHashMap<String, Object>> list, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("问卷调查");
        /*sheet.autoSizeColumn(1,true);*/
        Map<String, XSSFCellStyle> style = super.createStyles(wb);
        super.headerStyle = style.get(ExcelStyleEnum.HEADER.getValue()).getIndex();
        
        this.bodyBlackStyle = style.get(ExcelStyleEnum.BODY_BLACK.getValue()).getIndex();
        this.bodyLeftStyle = style.get(ExcelStyleEnum.BODY_BLACK_Left.getValue()).getIndex();
        this.bodyRedStyle = style.get(ExcelStyleEnum.BODY_RED.getValue()).getIndex();
        this.dateBlackStyle = style.get(ExcelStyleEnum.DATE_BLACK.getValue()).getIndex();
        this.dateRedStyle = style.get(ExcelStyleEnum.DATE_RED.getValue()).getIndex();
        
        
        File tempExcel = File.createTempFile("tempExcel", ".xlsx");
        File temp = File.createTempFile("sheet", ".xml");
        try {
            FileOutputStream fos = new FileOutputStream(tempExcel);
            wb.write(fos);
            fos.close();
            String sheetRef = sheet.getPackagePart().getPartName().getName();
            Writer fw = new PrintWriter(temp, "UTF-8");
            this.createSheet(fw, list);
            fw.close();
            super.substitute(tempExcel, temp, sheetRef.substring(1), os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            os.close();
        }
    }

    private void createSheet(Writer out, List<LinkedHashMap<String, Object>> results) throws Exception {
        SpeedSheetWriter sw = new SpeedSheetWriter(out);
        sw.beginSheet(setColumnWidth(), 0, 1, "A3");
        //sw.createCell(columnIndex, value);
        int index = 0;
        List<String> listMerge = new ArrayList<>();
        index = this.createSheetHead(sw, index, listMerge,results);
        if (results != null && results.size() > 0) {
        	KeFuWjDto keFuWjDto = JSON.parseObject(JSON.toJSONString(results.get(0)), KeFuWjDto.class);
        	if(!StringUtils.isEmpty(keFuWjDto)) {
        		List<KefuWjDTmDto> wjDTmList = keFuWjDto.getWjDTmList();
        		int countIndex = 0;
        		if(wjDTmList  != null && wjDTmList.size() > 0) {
        			for (int i = 0 ; i< wjDTmList.size();i++) {
        				KefuWjDTmDto kefuWjDTmDto = wjDTmList.get(i);
        				List<KefuWjDDaDto> wjDDaList = kefuWjDTmDto.getWjDDaList();//答案集合
        				countIndex++;
        				if(wjDDaList != null && wjDDaList.size() > 0) {
        					String tmIndex = kefuWjDTmDto.getId().toString();
        					Map<String,Object> map = new HashMap<>();
        					map.put("flag", false);
							for (KefuWjDDaDto kefuWjDDaDto : wjDDaList) {
								if (!StringUtils.isEmpty(kefuWjDDaDto)) {
									String ddIndex = kefuWjDDaDto.getWjdid().toString();
									if (tmIndex.equals(ddIndex)) {
										index = this.createSheetBody(countIndex,sw, index, kefuWjDTmDto, kefuWjDDaDto,map);
									}
								}
							}
        				}else {
        					index = this.createSheetBody(countIndex,sw, index, kefuWjDTmDto,null,null);
        				}
        			}
        		}
        	}
        }

        sw.endSheetWithMerge(listMerge);
    }

    /**
     * 创建sheet头部内容
     */
    private int createSheetHead(SpeedSheetWriter sw, int index, List<String> listMerge
    		,List<LinkedHashMap<String, Object>> results) throws IOException {
        nowRowIndex = index;
        nowColIndex = 0;
        sw.insertRow(nowRowIndex);
        //第一行

        nowColIndex = createBlock(sw, results.get(0).get("wjName").toString(), 1, 1, listMerge);
        sw.endRow();
        
        nowRowIndex++;

        nowColIndex = 0;
        //第一行
        sw.insertRow(nowRowIndex);
        nowColIndex = createBlock(sw, "序号", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "业务类型", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "题目", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "题型", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "选项", 1, 1, listMerge);
        nowColIndex = createBlock(sw, "分数", 1, 1, listMerge);

        sw.endRow();

        nowRowIndex = nowRowIndex + 1;

        return nowRowIndex;

    }


    /**
     * 创建sheet具体内容
     */
    private int createSheetBody(int countIndex,SpeedSheetWriter sw, int index, 
			KefuWjDTmDto kefuWjDTmDto, KefuWjDDaDto kefuWjDDaDto,
			Map<String,Object> map) throws Exception {
    	String wjtmflType = getWjtmflType(kefuWjDTmDto.getWjtmflType());
    	String wjtmType = getWjtmType(kefuWjDTmDto.getWjtmType());
		sw.insertRow(index);
		int i = 0;
		if (kefuWjDDaDto == null) {// 问答题
			sw.createCell(i++, countIndex, bodyBlackStyle);// 序号
			sw.createCell(i++, wjtmflType, bodyBlackStyle);// 业务类型
			sw.createCell(i++, kefuWjDTmDto.getWjtmValue().toString(), bodyLeftStyle);// 题目
			sw.createCell(i++, wjtmType, bodyBlackStyle);// 题型
			sw.createCell(i++, "", bodyBlackStyle);// 选项
			sw.createCell(i++, "", bodyBlackStyle);// 分数
		} else if(!(boolean)map.get("flag")){
			sw.createCell(i++, countIndex, bodyBlackStyle);// 序号
			sw.createCell(i++, wjtmflType, bodyBlackStyle);// 业务类型
			sw.createCell(i++, kefuWjDTmDto.getWjtmValue().toString(), bodyLeftStyle);// 题目
			sw.createCell(i++, wjtmType, bodyBlackStyle);// 题型
			if (!StringUtils.isEmpty(kefuWjDDaDto)) {
				sw.createCell(i++, kefuWjDDaDto.getWjxx().toString(), bodyBlackStyle);// 选项
				sw.createCell(i++, kefuWjDDaDto.getDirectScore(), bodyBlackStyle);// 分数
			}
			map.put("flag", true);
		}else if((boolean)map.get("flag")) {
			sw.createCell(i++, "", bodyBlackStyle);// 序号
			sw.createCell(i++, "", bodyBlackStyle);// 业务类型
			sw.createCell(i++, "", bodyLeftStyle);// 题目
			sw.createCell(i++, "", bodyBlackStyle);// 题型
			if (!StringUtils.isEmpty(kefuWjDDaDto)) {
				sw.createCell(i++, kefuWjDDaDto.getWjxx().toString(), bodyBlackStyle);// 选项
				sw.createCell(i++, kefuWjDDaDto.getDirectScore(), bodyBlackStyle);// 分数
			} else {
				sw.createCell(i++, "", bodyBlackStyle);// 选项
				sw.createCell(i++, "", bodyBlackStyle);// 分数
			}
			
		}

		sw.endRow();
		index++;

		return  index;
	}

    /**
     * desc:题型
     * 2019年7月3日
     * author:zhenggang.Huang
     */
    private String getWjtmType(String wjtmType) {
    	if(StringUtil.isEmpty(wjtmType)) {
			return wjtmType;
		}
		switch(wjtmType){
		    case "25201" :
		    	wjtmType = Wj_wjtmType_dx;
		       break; 
		    case "25202" :
		    	wjtmType = Wj_wjtmType_dxs;
		    	break; 
		    case "25203" :
		    	wjtmType = Wj_wjtmType_wd;
		       break; 
		}
		return wjtmType;
	}

    /**
     * desc:业务类型
     * 2019年7月3日
     * author:zhenggang.Huang
     */
	private String getWjtmflType(String wjtmflType) {
		if(StringUtil.isEmpty(wjtmflType)) {
			return wjtmflType;
		}
		switch(wjtmflType){
		    case "25101" :
		    	wjtmflType = Wj_wjtmflType_pp;
		       break; 
		    case "25102" :
		    	wjtmflType = Wj_wjtmflType_px;
		    	break; 
		    case "25103" :
		    	wjtmflType = Wj_wjtmflType_zp;
		       break; 
		    case "25104" :
		    	wjtmflType = Wj_wjtmflType_xt;
		    	break; 
		    case "25105" :
		    	wjtmflType = Wj_wjtmflType_jy;
		    	break; 
		    case "25106" :
		    	wjtmflType = Wj_wjtmflType_gp;
		    	break; 
		    case "25107" :
		    	wjtmflType = Wj_wjtmflType_ld;
		    	break; 
		    case "25108" :
		    	wjtmflType = Wj_wjtmflType_qt;
		    	break; 
		}
		return wjtmflType;
	}

	/**
     * 设置列宽
     */
    public List<ExcelColumnSet> setColumnWidth() {
        List<ExcelColumnSet> columnSet = new ArrayList<>();
        
        ExcelColumnSet c;
        int nColIndex = 1;
        //序号
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(14);
        columnSet.add(c);
        nColIndex++;
        
        //业务类型
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(13);
        columnSet.add(c);
        nColIndex++;
        
        //题目
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(35);
        columnSet.add(c);
        nColIndex++;
        
        //题型
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(13);
        columnSet.add(c);
        nColIndex++;
        
       //选项
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(13);
        columnSet.add(c);
        nColIndex++;
        
        //分数
        c = new ExcelColumnSet(String.valueOf(nColIndex), String.valueOf(nColIndex));
        c.setWidth(13);
        columnSet.add(c);
        nColIndex++;
        
        return columnSet;
    }
}
