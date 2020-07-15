package cn.com.eju.deal.dto.kefuWj;

import java.math.BigDecimal;

/**
 * Created by eju on 2019/7/16.
 */
public class WjAnalyseTmScoreDto {

    private String wjtmflType;

    private BigDecimal wjtmScore;

    public String getWjtmflType() {
        return wjtmflType;
    }

    public void setWjtmflType(String wjtmflType) {
        this.wjtmflType = wjtmflType;
    }

    public BigDecimal getWjtmScore() {
        return wjtmScore;
    }

    public void setWjtmScore(BigDecimal wjtmScore) {
        this.wjtmScore = wjtmScore;
    }
}
