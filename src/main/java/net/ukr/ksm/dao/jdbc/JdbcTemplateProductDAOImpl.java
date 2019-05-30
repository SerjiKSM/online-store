package net.ukr.ksm.dao.jdbc;

import net.ukr.ksm.model.Product;
import net.ukr.ksm.util.ProductMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

import net.ukr.ksm.dao.ProductDAO;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class JdbcTemplateProductDAOImpl implements ProductDAO {
    protected final Log logger = LogFactory.getLog(getClass());
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void createProduct(String name, String description, Double price) {
        String sql = "INSERT INTO products (name, description, price) VALUES (?,?,?)";
        jdbcTemplate.update(sql, name, description, price);
    }

    @Override
    public Product getProductById(long id) {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        Product product = null;
        try {
            product = (Product) jdbcTemplate.queryForObject(sql, new Object[]{id}, new ProductMapper());
            return product;
        } catch (EmptyResultDataAccessException e) {
            logger.info("Empty result!");
        }
        return product;
    }

    @Override
    public void updateProduct(long id, String name, String description, Double price) {
        String sql = "UPDATE products SET name = ?, description = ?, price = ? WHERE product_id = ?";
        jdbcTemplate.update(sql, name, description, price, id);
    }

    @Override
    public void removeProduct(long id) {
        String sql = "DELETE FROM products WHERE product_id = ?";
        jdbcTemplate.update(sql, new Object[]{id});
    }

    @Override
    public List listProducts() {
        String sql = "SELECT * FROM products";
        List products = jdbcTemplate.query(sql, new ProductMapper());
        return products;
    }

    @Override
    public List<Product> listProductsWithPictures() {
        String sql = "SELECT products.product_id, products.name, products.description, products.price,\n" +
                "pictures.picture_id, pictures.link, products_stocks.quantity FROM products \n" +
                "LEFT OUTER JOIN products_pictures \n" +
                "ON products.product_id = products_pictures.product_id \n" +
                "LEFT OUTER JOIN pictures\n" +
                "ON products_pictures.picture_id = pictures.picture_id\n" +
                "LEFT OUTER JOIN products_stocks\n" +
                "ON products.product_id = products_stocks.id_product\n" +
                "ORDER BY products.product_id";

        List<Product> products = jdbcTemplate.query(sql, new ProductMapper());

        return products;
    }

    @Override
    public List<Product> listProductsWithPicturesSortByPrice(double priceFrom, double priceTo) {
        String sql = "SELECT products.product_id, products.name, products.description, products.price,\n" +
                "pictures.picture_id, pictures.link, products_stocks.quantity FROM products \n" +
                "LEFT OUTER JOIN products_pictures \n" +
                "ON products.product_id = products_pictures.product_id \n" +
                "LEFT OUTER JOIN pictures \n" +
                "ON products_pictures.picture_id = pictures.picture_id \n" +
                "LEFT OUTER JOIN products_stocks \n" +
                "ON products.product_id = products_stocks.id_product \n" +
                "WHERE products.price >= ? AND products.price <= ? " +
                "ORDER BY products.product_id";

        Object product = null;
        try {
            product = jdbcTemplate.query(sql, new Object[]{priceFrom, priceTo}, new ProductMapper());
            return (List<Product>) product;
        } catch (EmptyResultDataAccessException e) {
            logger.info("Empty result!");
        }
        return (List<Product>) product;
    }

    @Override
    public long insertProduct(final Product product) {
        final String sql = "INSERT INTO products (`name`, description, price) VALUES (?,?,?)";

        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id"});
                ps.setString(1, product.getName());
                ps.setString(2, product.getDescription());
                ps.setString(3, String.valueOf(product.getPrice()));
                return ps;
            }
        };
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, holder);
        return holder.getKey().longValue();
    }

    @Override
    public List<Object> getProductDataById(long id) {
        String sql = "SELECT products.product_id, products.name, products.description, products.price,\n" +
                "pictures.picture_id, pictures.link, products_stocks.quantity FROM products \n" +
                "LEFT OUTER JOIN products_pictures \n" +
                "ON products.product_id = products_pictures.product_id \n" +
                "LEFT OUTER JOIN pictures\n" +
                "ON products_pictures.picture_id = pictures.picture_id\n" +
                "LEFT OUTER JOIN products_stocks\n" +
                "ON products.product_id = products_stocks.id_product\n" +
                "WHERE products.product_id = ?\n" +
                "ORDER BY products.product_id";

        Object product = null;
        try {
            product = jdbcTemplate.query(sql, new Object[]{id}, new ProductMapper());
            return (List<Object>) product;
        } catch (EmptyResultDataAccessException e) {
            logger.info("Empty result!");
        }
        return (List<Object>) product;

    }

}
