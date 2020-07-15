package cn.com.eju.deal.dto.performanceSum;

import java.io.Serializable;

/**
 * Created by tanlang on 2017-06-22.
 */
public class PerformanceSumColumnDto implements Serializable {

    private String colTitle;

    private int mergeColNum;

    private String colType;

    private String colWidth;

    public String getColTitle() {
        return colTitle;
    }

    public void setColTitle(String colTitle) {
        this.colTitle = colTitle;
    }

    public int getMergeColNum() {
        return mergeColNum;
    }

    public void setMergeColNum(int mergeColNum) {
        this.mergeColNum = mergeColNum;
    }

    public String getColType() {
        return colType;
    }

    public void setColType(String colType) {
        this.colType = colType;
    }

    public String getColWidth() {
        return colWidth;
    }

    public void setColWidth(String colWidth) {
        this.colWidth = colWidth;
    }
}
