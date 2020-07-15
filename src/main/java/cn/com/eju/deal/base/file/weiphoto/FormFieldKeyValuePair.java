package cn.com.eju.deal.base.file.weiphoto;

/**   
* weiphoto文件系统 -- receive user's input
* @author (li_xiaodong)
* @date 2016年6月29日 下午9:40:27
*/
public class FormFieldKeyValuePair
{
    
    private String key;
    
    private String value;
    
    public FormFieldKeyValuePair(String key, String value)
    {
        
        this.key = key;
        
        this.value = value;
        
    }
    
    public String getKey()
    
    {
        
        return key;
        
    }
    
    public void setKey(String key)
    {
        
        this.key = key;
        
    }
    
    public String getValue()
    
    {
        
        return value;
        
    }
    
    public void setValue(String value)
    
    {
        
        this.value = value;
        
    }
    
}
