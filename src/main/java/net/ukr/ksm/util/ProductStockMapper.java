package net.ukr.ksm.util;

import net.ukr.ksm.model.ProductStock;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductStockMapper implements RowMapper {
    @Override
    public ProductStock mapRow(ResultSet resultSet, int i) throws SQLException {
        ProductStock productStock = new ProductStock();

        try {
            productStock.setName(resultSet.getString("name"));
        } catch (java.sql.SQLException e) {
            productStock.setName(null);
        }

        productStock.setId(resultSet.getInt("id"));
        productStock.setId_product(resultSet.getInt("id_product"));
        productStock.setQuantity(resultSet.getInt("quantity"));
        return productStock;
    }
}
