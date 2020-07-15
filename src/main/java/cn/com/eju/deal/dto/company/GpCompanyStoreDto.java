package cn.com.eju.deal.dto.company;

/**   
* 公司门店关系
* @author 张文辉
* @date 2016年7月5日 上午10:35:37
*/
public class GpCompanyStoreDto {

    /**
     * 主键
     */
    private int id;

    /**
     * 公司编号
     */
    private int companyId;

    /**
     * 门店编号
     */
    private int storeId;

    /**
     * 是否删除
     */
    private boolean isDelete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}

