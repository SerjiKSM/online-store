package net.ukr.ksm.dao;

import net.ukr.ksm.model.Product;

import javax.sql.DataSource;
import java.util.List;

public interface ProductDAO {
    public void setDataSource(DataSource dataSource);

    public void createProduct(String name, String description, Double price);

    public Product getProductById(long id);

    public void updateProduct(long id, String name, String description, Double price);

    public void removeProduct(long id);

    public List listProducts();

    public List<Product> listProductsWithPictures();

    List<Product> listProductsWithPicturesSortByPrice(double priceFrom, double priceTo);

    public long insertProduct(Product product);

    List<Object> getProductDataById(long idProduct);

}
