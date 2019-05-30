package net.ukr.ksm.dao.jdbc;

import net.ukr.ksm.dao.ProductStockDAO;
import net.ukr.ksm.model.ProductStock;
import net.ukr.ksm.util.ProductStockMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcTemplateProductStockDAOImpl implements ProductStockDAO {
    protected final Log logger = LogFactory.getLog(getClass());
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ProductStock getProductStockById(long id) {
        String sql = "SELECT id, id_product, quantity FROM products_stocks WHERE id = ?";
        ProductStock product = null;
        try {
            product = (ProductStock) jdbcTemplate.queryForObject(sql, new Object[]{id}, new ProductStockMapper());
            return product;
        } catch (EmptyResultDataAccessException e) {
            logger.info("Empty result!");
        }
        return product;
    }

    @Override
    public ProductStock getProductStockQuantityById(long id) {
        String sql = "SELECT products.name, products_stocks.id, products_stocks.id_product," +
                " products_stocks.quantity FROM products JOIN products_stocks\n" +
                "ON products.product_id = products_stocks.id_product\n" +
                "WHERE products_stocks.id_product = ?";

        ProductStock product = null;
        try {
            product = (ProductStock) jdbcTemplate.queryForObject(sql, new Object[]{id}, new ProductStockMapper());
            return product;
        } catch (EmptyResultDataAccessException e) {
            logger.info("Empty result!");
        }
        return product;
    }

    @Override
    public void updateProductStock(ProductStock productStock) {
        String sql = "UPDATE products_stocks SET products_stocks.quantity = ? WHERE id = ?";

        Object[] objects = new Object[] {
                productStock.getQuantity(),
                productStock.getId()
        };
        jdbcTemplate.update(sql, objects);
    }

    @Override
    public long insertProductStock(final ProductStock productStock) {
        final String sql = "INSERT INTO products_stocks (id_product, quantity) VALUES (?,?)";

        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id"});
                ps.setString(1, String.valueOf(productStock.getId_product()));
                ps.setString(2, String.valueOf(productStock.getQuantity()));
                return ps;
            }
        };
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, holder);
        return holder.getKey().longValue();
    }

    @Override
    public ProductStock getProductStockByIdProduct(long idProduct) {
        String sql = "SELECT id, id_product, quantity FROM products_stocks WHERE id_product = ?";
        ProductStock product = null;
        try {
            product = (ProductStock) jdbcTemplate.queryForObject(sql, new Object[]{idProduct}, new ProductStockMapper());
            return product;
        } catch (EmptyResultDataAccessException e) {
            logger.info("Empty result!");
        }
        return product;
    }
}
