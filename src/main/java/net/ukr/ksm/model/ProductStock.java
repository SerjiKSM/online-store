package net.ukr.ksm.model;

public class ProductStock {
    private long id;
    private long id_product;
    private int quantity;

    private String name;

    public ProductStock() {
    }

    public ProductStock(long id, long id_product, int quantity) {
        this.id = id;
        this.id_product = id_product;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_product() {
        return id_product;
    }

    public void setId_product(long id_product) {
        this.id_product = id_product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
