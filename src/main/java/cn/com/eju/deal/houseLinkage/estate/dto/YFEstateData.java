package cn.com.eju.deal.houseLinkage.estate.dto;

import java.io.Serializable;
import java.util.List;

public class YFEstateData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2011286987245588978L;
	
	private String currentPage;		
	private String dataCount;		
	private List<Data> data;
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	public String getDataCount() {
		return dataCount;
	}
	public void setDataCount(String dataCount) {
		this.dataCount = dataCount;
	}
	public List<Data> getData() {
		return data;
	}
	public void setData(List<Data> data) {
		this.data = data;
	}
}
