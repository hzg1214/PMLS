package cn.com.eju.deal.dto.houseLinkage.custom;

import java.io.Serializable;
import java.util.List;

import cn.com.eju.deal.dto.houseLinkage.report.ReportDetailDto;
import cn.com.eju.deal.dto.houseLinkage.report.ReportDto;

/**   
* CustomInfoDto  Dto
* @author (qianwei)
* @date 2016年3月22日 下午4:42:54
*/
public class CustomInfoDto implements Serializable
{

    /**
    * TODO(用一句话描述这个变量表示什么)
    */ 
    private static final long serialVersionUID = 8797366850847783778L;
    // 客户报备
    private ReportDto report;
    // 客户报备详细列表
    private List<ReportDetailDto> reportDetails;
	public ReportDto getReport() {
		return report;
	}
	public void setReport(ReportDto report) {
		this.report = report;
	}
	public List<ReportDetailDto> getReportDetails() {
		return reportDetails;
	}
	public void setReportDetails(List<ReportDetailDto> reportDetails) {
		this.reportDetails = reportDetails;
	}
    
	
	
}
