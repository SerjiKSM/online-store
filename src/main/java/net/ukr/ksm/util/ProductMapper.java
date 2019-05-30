package net.ukr.ksm.util;

import net.ukr.ksm.model.Picture;
import net.ukr.ksm.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductMapper implements RowMapper {
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setProduct_id(resultSet.getInt("product_id"));
        product.setName(resultSet.getString("name"));
        product.setDescription(resultSet.getString("description"));
        product.setPrice(resultSet.getDouble("price"));

        try {
            product.setLink(resultSet.getString("link"));
            if (resultSet.getString("link") != null) {
                Picture picture = new Picture();
                picture.setPicture_id(resultSet.getInt("picture_id"));
                picture.setLink(resultSet.getString("link"));

                Set<Picture> pictures = new HashSet<Picture>();
                pictures.add(picture);

                product.setPictures(pictures);
            }
        } catch (java.sql.SQLException e) {
            product.setLink(null);
        }

        try {
            product.setQuantity(resultSet.getInt("quantity"));
        } catch (java.sql.SQLException e) {
            product.setQuantity(0);
        }

        return product;
    }
}
