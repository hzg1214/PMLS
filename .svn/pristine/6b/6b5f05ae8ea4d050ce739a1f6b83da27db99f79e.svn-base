package cn.com.eju.pmls.utils;

import cn.com.eju.deal.common.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UpdateExcelUtils {
    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    private static Workbook workbook;
    private static Sheet sheet;

    /**
     * 读取Excel文件并修改
     */
    private static void createExcel(){
        InputStream is=null;
        OutputStream os =null;
        try {
//          String excelPath = request.getSession().getServletContext().getRealPath("/template/" + filename);
            String excelPath="I:/work_PMLS/PMLSWeb/src/main/webapp/template/项目中介服务费结算明细表.xlsx";
            is = new FileInputStream(excelPath);
            workbook = new XSSFWorkbook(is);
            sheet = workbook.getSheetAt(0);
            //单元格样式
            CellStyle style=workbook.createCellStyle();
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
            Font font = workbook.createFont();
            font.setFontName("微软雅黑");
            font.setFontHeightInPoints((short) 10);//设置字体大小
            style.setFont(font);


            int index=4;//开始行下标
            int startRow=5;//开始行数
            Map<String,String> mapData=new HashMap<String,String>();
            int mapLength=10;

            for(int i=1;i<mapLength;i++){
                mapData.put("rowNum",i+"");
                createRow(index++,mapData,style);
                if(i<10-1){//最后一行不下移
                    sheet.shiftRows(startRow++, sheet.getLastRowNum(), 1); //从第5行开始，向下移动一行
                }else{
                    int sumRowIndex=index;
                    //结束后添加公式
                    Row sumRow=sheet.getRow(sumRowIndex);
                    sumRow.getCell(3).setCellFormula("SUM(D5:D"+sumRowIndex+")");
                    sumRow.getCell(4).setCellFormula("SUM(E5:E"+sumRowIndex+")");
                    sumRow.getCell(7).setCellFormula("SUM(H5:H"+sumRowIndex+")");
                    sumRow.getCell(8).setCellFormula("SUM(I5:I"+sumRowIndex+")");
                    sumRow.getCell(9).setCellFormula("SUM(J5:J"+sumRowIndex+")");
                    sumRow.getCell(10).setCellFormula("SUM(K5:K"+sumRowIndex+")");
                }
            }
            os = new FileOutputStream("G:/tmp"+"/项目中介服务费结算明细表.xlsx");

            workbook.write(os);
            workbook.close();
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private static void createRow(int index, Map<String,String> mapData,CellStyle style) throws Exception{
        try {

            Row row=sheet.createRow(index);
            //创建单元格并设置单元格内容
            int cellIndex=0;
            createCell(cellIndex++,row,mapData.get("rowNum"),"Integer",style);
            createCell(cellIndex++,row,"5#101","String",style);
            createCell(cellIndex++,row,"张三","String",style);
            createCell(cellIndex++,row,"100","Double",style);
            createCell(cellIndex++,row,"1200000","Double",style);
            createCell(cellIndex++,row,"上海房友","String",style);
            createCell(cellIndex++,row,"2.5%","String",style);
            createCell(cellIndex++,row,"1500000","Double",style);
            createCell(cellIndex++,row,"1200000","Double",style);
            createCell(cellIndex++,row,"10000","Double",style);
            createCell(cellIndex++,row,"1200000","Double",style);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //创建单元格
    private static void createCell(int cellIndex,Row row,String value,String valueType,CellStyle style){
        Cell cell= row.createCell(cellIndex);
        //cell.setCellValue(value);
        switch (valueType) {
            case "String": {
                cell.setCellValue(value);
                break;
            }
            case "Double":
                cell.setCellValue(Double.parseDouble(value));
                break;
            case "Integer":
                cell.setCellValue(Integer.parseInt(value));
                break;
            default: {
                cell.setCellValue(value);
                break;
            }
        }
        cell.setCellStyle(style);//
    }

    public static void main(String[] args) {
        System.out.println("生成excel开始");
        createExcel();
        System.out.println("生成excel结束");
    }
}
