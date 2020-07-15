package cn.com.eju.deal.dto.accountproject;

/**
 * desc:核算主体拼接列表
 * @author :zhenggang.Huang
 * @date   :2019年7月26日
 */
public class AccountProjectList {

    private Integer id;

    private String accountProjectNo;//核算主体编号
    
    private String accountProject;//核算主体名称
    
    private String accountProjectStr;//核算主体编号+“_”+核算主体名称
    
    public String getAccountProjectNo() {
		return accountProjectNo;
	}

	public void setAccountProjectNo(String accountProjectNo) {
		this.accountProjectNo = accountProjectNo;
	}

	public String getAccountProject() {
		return accountProject;
	}

	public void setAccountProject(String accountProject) {
		this.accountProject = accountProject;
	}

    public String getAccountProjectStr() {
		return accountProjectStr;
	}

	public void setAccountProjectStr(String accountProjectStr) {
		this.accountProjectStr = accountProjectStr;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
