package net.ukr.ksm;

import net.ukr.ksm.dao.PictureDAO;
import net.ukr.ksm.model.Picture;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;

public class TestPicture {
    ConfigurableApplicationContext applicationContext;
    PictureDAO pictureDAO;

    @Before
    public void getApplicationContext() {
        this.applicationContext = new ClassPathXmlApplicationContext("Spring-Module.xml");
        this.pictureDAO = (PictureDAO) applicationContext.getBean("pictureDAO");
    }

    @Test
    public void createPictuteTest() {
        Picture picture = new Picture();
        picture.setLink("link 4");

        Long id = pictureDAO.insertPicture(picture);

        Picture pictureFromDb = pictureDAO.findPictureById(id);

        assertEquals(picture, pictureFromDb);

        pictureDAO.deletePictureById(pictureFromDb.getPicture_id());
    }
}
