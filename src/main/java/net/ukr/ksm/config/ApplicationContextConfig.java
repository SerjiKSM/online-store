package net.ukr.ksm.config;

import net.ukr.ksm.dao.ProductDAO;
import net.ukr.ksm.dao.jdbc.JdbcTemplateProductDAOImpl;
import net.ukr.ksm.model.Product;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@PropertySource("classpath:site.properties")
@ComponentScan("net.ukr.ksm.*")
public class ApplicationContextConfig {
    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean(name = "multipartResolver")
    public MultipartResolver getMultipartResolver() {
        CommonsMultipartResolver resover = new CommonsMultipartResolver();
        // 1MB
        resover.setMaxUploadSize(1 * 1024 * 1024);

        return resover;
    }

//    @Bean
//    public ConfigurableApplicationContext applicationContext() {
//        return new ClassPathXmlApplicationContext("Spring-Module.xml");
//    }
//
//    @Bean
//    public ProductDAO productTestDAO() {
//        return new JdbcTemplateProductDAOImpl();
//    }
}
