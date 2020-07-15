package cn.com.eju.pmls.commission.dto;

import java.io.Serializable;

/**
 * 开关记录model
 */
public class PmlsPerformSwitchWeekDto extends PmlsPerformSwitchWeek  implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 城市名称
     */
    private String cityName;

    private Integer sortNo;
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }
}
