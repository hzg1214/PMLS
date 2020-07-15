package cn.com.eju.pmls.poi.common;

/**
 * Excel每行的显示样式
 */
public enum ExcelRowTypeEnum {
    t_normal("body"),
    t_rate("percent"),
    t_header("header"),
    t_normalInt("body_int")
    ;
    private String chineseName;
    private ExcelRowTypeEnum(String chineseName) {
        this.chineseName = chineseName;
    }
    public String getChineseName() {
        return this.chineseName;
    }
}
