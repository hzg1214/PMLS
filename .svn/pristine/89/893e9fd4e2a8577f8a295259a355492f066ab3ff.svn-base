package cn.com.eju.deal.base.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

public class DownloadFileUtil {
	
	
	public static Integer ISDELFILE_YES=1;
	
	public static Integer ISDELFILE_NO=0;
	
	/**
	 * 
	 * @param response
	 * @param fileName  下载文件名
	 * @param isDelFile 是否删除下载文件  0 不删除 1删除
	 * @param File  文件
	 */
	
	
	
	public static void DownloadFile(HttpServletResponse response,String fileName,Integer isDelFile,File file){
		
		try {
			response.reset();// 清空输出流
			// 下面是对中文文件名的处理
			response.setCharacterEncoding("UTF-8");// 设置相应内容的编码格式
			fileName = new String(fileName.getBytes("Gbk"), "iso-8859-1");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ fileName);
			response.setContentType("application/msexcel");// 定义输出类型
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;

			InputStream input = new FileInputStream(file);
			try {
				bis = new BufferedInputStream(input);
				bos = new BufferedOutputStream(response.getOutputStream());
				byte[] buff = new byte[2048];
				int bytesRead;
				// Simple read/write loop.
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
			} catch (final IOException e) {
				throw e;
			} finally {
				bos.flush();
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
				if(isDelFile !=null && isDelFile == DownloadFileUtil.ISDELFILE_YES){
					file.delete();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
