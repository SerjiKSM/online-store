package net.ukr.ksm.dao.jdbc;

import net.ukr.ksm.dao.PictureDAO;
import net.ukr.ksm.model.Picture;
import net.ukr.ksm.util.PictureMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcTemplatePictureDAOImpl implements PictureDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public long insertPicture(final Picture picture) {
        final String sql = "INSERT INTO pictures (link) VALUES (?)";

        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id"});
                ps.setString(1, picture.getLink());
                return ps;
            }
        };
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, holder);
        return holder.getKey().longValue();
    }

    @Override
    public Picture findPictureById(Long id) {
        String sql = "SELECT * FROM pictures WHERE picture_id = ?";
        Picture picture = (Picture) jdbcTemplate.queryForObject(sql, new Object[]{id}, new PictureMapper());
        return picture;
    }

    @Override
    public void deletePictureById(Long id) {
        String sql = "DELETE FROM pictures WHERE picture_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
