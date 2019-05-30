package net.ukr.ksm.model;

public class ProductPicture {
    private long id;
    private long pictureId;
    private long productId;

    public ProductPicture() {
    }

    public ProductPicture(long id, long pictureId, long productId) {
        this.id = id;
        this.pictureId = pictureId;
        this.productId = productId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPictureId() {
        return pictureId;
    }

    public void setPictureId(long pictureId) {
        this.pictureId = pictureId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
