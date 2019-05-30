package net.ukr.ksm.dao;

import net.ukr.ksm.model.Picture;

import javax.sql.DataSource;

public interface PictureDAO {
    public void setDataSource(DataSource dataSource);

    public long insertPicture(Picture picture);

    Picture findPictureById(Long id);

    void deletePictureById(Long id);
}
