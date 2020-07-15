package cn.com.eju.deal.contract.enums;

/**   
* 宜居公司枚举
* @author (li_xiaodong)
* @date 2016年4月30日 上午9:51:59
*/
public enum FirmEnum
{
    /**
    * YJJY("4061", "上海易居交易服务"), YXXY("2002", "营销祥悦"), LTXX("6001", "励拓行销")
    */ 
    YJJY("4061", "上海易居交易服务"), YXXY("2002", "营销祥悦"), LTXX("6001", "励拓行销");
    
    private FirmEnum(String code, String name)
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
        for (FirmEnum enumObj : FirmEnum.values())
        {
            if (code == enumObj.getCode())
            {
                return enumObj.getName();
            }
        }
        return code;
    }
}
