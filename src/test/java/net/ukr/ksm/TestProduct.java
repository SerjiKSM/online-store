package net.ukr.ksm;

import net.ukr.ksm.dao.ProductDAO;
import net.ukr.ksm.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;
import static org.junit.Assert.assertEquals;


public class TestProduct {

    ConfigurableApplicationContext applicationContext;
    ProductDAO productDAO;

    @Before
    public void getApplicationContext() {
        this.applicationContext = new ClassPathXmlApplicationContext("Spring-Module.xml");
        this.productDAO = (ProductDAO) applicationContext.getBean("productDAO");
    }

    @Test
    public void createProductTest() {
        productDAO.createProduct("dvd 10", "Description dvd", (double) 15);
        productDAO.createProduct("dvd 11", "Description dvd 2", (double) 16);
        productDAO.createProduct("dvd 12", "Description dvd 3", (double) 10);
        System.out.println("Product successfully created.");
    }

    @Test
    public void getProductByIdTest() {
        Product product = productDAO.getProductById(1);
        assertEquals("Product exists!",1, product.getProduct_id());
        System.out.println("Product exists!");
    }

    @Test
    public void updateProductTest() {
        productDAO.updateProduct(1, "dvdUpdateTest", "Description dvdUpdateTest", 1.0);
        Product product = productDAO.getProductById(1);
        assertEquals("dvdUpdateTest", product.getName());
        System.out.println("Product updated!");
    }

    @Test
    public void listProductsTest() {
        List<Product> products = productDAO.listProducts();
        for (Product product : products) {
            System.out.println(product);
        }
    }

}
