package cn.com.eju.deal.contract.enums;

/**   
* OA 流程列别 0标准;1 特殊
* @author (li_xiaodong)
* @date 2016年4月30日 上午9:51:59
*/
public enum OaFlowSortEnum
{
    /**
    * STANDARD("0", "标准"), SPECIAL("1", "特殊")
    */ 
//    STANDARD("0", "标准"), SPECIAL("1", "特殊");
    
    STANDARD("6633123988421234241", "标准"), SPECIAL("7396753548494522566", "特殊");
    
    private OaFlowSortEnum(String code, String name)
    {
        this.code = code;
        this.name = name;
    }
    
    private String code;
    
    private String name;
    
    public String getCode()
    {
        return code;
    }
    
    public void setCode(String code)
    {
        this.code = code;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public static String getNameByCode(String code)
    {
        for (OaFlowSortEnum enumObj : OaFlowSortEnum.values())
        {
            if (code == enumObj.getCode())
            {
                return enumObj.getName();
            }
        }
        return code;
    }
}
