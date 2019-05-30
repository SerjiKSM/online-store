package net.ukr.ksm.util;

import net.ukr.ksm.model.Picture;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PictureMapper implements RowMapper {
    @Override
    public Picture mapRow(ResultSet resultSet, int i) throws SQLException {
        Picture picture = new Picture();
        picture.setPicture_id(resultSet.getInt("picture_id"));
        picture.setLink(resultSet.getString("link"));

        return picture;
    }
}
