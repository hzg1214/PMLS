package cn.com.eju.deal.dto.picture;

import java.io.Serializable;

/**
 * Created by tanlang on 2017-06-12.
 */
public class PictureDto implements Serializable {

    private String smallPictureUrl;

    private String middlePictureUrl;

    private String bigPictureUrl;


    public String getBigPictureUrl() {
        return bigPictureUrl;
    }

    public void setBigPictureUrl(String bigPictureUrl) {
        this.bigPictureUrl = bigPictureUrl;
    }

    public String getMiddlePictureUrl() {
        return middlePictureUrl;
    }

    public void setMiddlePictureUrl(String middlePictureUrl) {
        this.middlePictureUrl = middlePictureUrl;
    }

    public String getSmallPictureUrl() {
        return smallPictureUrl;
    }

    public void setSmallPictureUrl(String smallPictureUrl) {
        this.smallPictureUrl = smallPictureUrl;
    }
}
