package net.ukr.ksm.dao;

import net.ukr.ksm.model.ProductStock;

import javax.sql.DataSource;

public interface ProductStockDAO {
    public void setDataSource(DataSource dataSource);

    public ProductStock getProductStockById(long id);

    public ProductStock getProductStockQuantityById(long id);

    void updateProductStock(ProductStock productStock);

    public long insertProductStock(ProductStock productStock);

    ProductStock getProductStockByIdProduct(long idProduct);
}
