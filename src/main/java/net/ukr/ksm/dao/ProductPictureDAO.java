package net.ukr.ksm.dao;

import net.ukr.ksm.model.ProductPicture;

import javax.sql.DataSource;

public interface ProductPictureDAO {
    public void setDataSource(DataSource dataSource);

    public long insertProductPicture(ProductPicture productPicture);
}
