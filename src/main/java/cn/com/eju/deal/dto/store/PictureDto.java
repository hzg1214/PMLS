package cn.com.eju.deal.dto.store;

/**
 * Created by Administrator on 2017/6/15.
 */
public class PictureDto {

    private String id;//ID

    private String pictureRefId;//

    private String smallPictureUrl;//

    private String middlePictureUrl;//

    private String bigPictureUrl;//

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPictureRefId() {
        return pictureRefId;
    }

    public void setPictureRefId(String pictureRefId) {
        this.pictureRefId = pictureRefId;
    }

    public String getSmallPictureUrl() {
        return smallPictureUrl;
    }

    public void setSmallPictureUrl(String smallPictureUrl) {
        this.smallPictureUrl = smallPictureUrl;
    }

    public String getMiddlePictureUrl() {
        return middlePictureUrl;
    }

    public void setMiddlePictureUrl(String middlePictureUrl) {
        this.middlePictureUrl = middlePictureUrl;
    }

    public String getBigPictureUrl() {
        return bigPictureUrl;
    }

    public void setBigPictureUrl(String bigPictureUrl) {
        this.bigPictureUrl = bigPictureUrl;
    }
}
