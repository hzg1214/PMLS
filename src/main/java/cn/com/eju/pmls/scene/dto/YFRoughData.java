package cn.com.eju.pmls.scene.dto;


import java.io.Serializable;
import java.util.List;

public class YFRoughData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String currentPage;		
	private String dataCount;		
	private List<RoughData> data;

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

    public List<RoughData> getData() {
        return data;
    }

    public void setData(List<RoughData> data) {
        this.data = data;
    }
}
