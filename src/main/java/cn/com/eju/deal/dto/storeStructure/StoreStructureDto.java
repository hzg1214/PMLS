package cn.com.eju.deal.dto.storeStructure;

/**
 * Created by Administrator on 2017/6/22.
 */
public class StoreStructureDto {

    private String bizType;
    private String bizStage;
    private String subjCode;
    private String subjName;
    private String rowType;
    private String align;
    private int groupSize;

    private String col1;
    private String col2;

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getBizStage() {
        return bizStage;
    }

    public void setBizStage(String bizStage) {
        this.bizStage = bizStage;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public String getRowType() {
        return rowType;
    }

    public void setRowType(String rowType) {
        this.rowType = rowType;
    }

    public String getSubjCode() {
        return subjCode;
    }

    public void setSubjCode(String subjCode) {
        this.subjCode = subjCode;
    }

    public String getSubjName() {
        return subjName;
    }

    public void setSubjName(String subjName) {
        this.subjName = subjName;
    }
}
