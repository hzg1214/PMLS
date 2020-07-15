package cn.com.eju.deal.poi.common;

/**
 * Created by Administrator on 14-6-11.
 */
public class ExcelColumnSet {
    private String min;
    private String max;
    private double width = 0.;
    private boolean hidden = false;

    public ExcelColumnSet(String min, String max) {
        this.min = min;
        this.max = max;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getColString(){
        String result = "<col min=\""+min+"\" max=\""+max+"\" ";
        if(hidden) result += "hidden=\"1\" ";
        if(width > 0.){
            result += "width=\""+width+"\" customWidth=\"1\"";
        }
        result += "/>";
        return result;
    }
}
