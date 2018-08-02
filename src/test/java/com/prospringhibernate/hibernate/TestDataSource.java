package com.prospringhibernate.hibernate;

import com.prospringhibernate.hibernate.config.HibernateConfig;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

import static org.junit.Assert.assertNotNull;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-08-01 오전 10:31
 **/


public class TestDataSource {
    ApplicationContext c;

    @Test
    public void dataSource() {
        c = new ClassPathXmlApplicationContext("spring/hibernate-context.xml");
        assertNotNull(c.getBean(DataSource.class));
        assertNotNull(c.getBean(SessionFactory.class));

        c = new AnnotationConfigApplicationContext(HibernateConfig.class);
        assertNotNull(c.getBean(DataSource.class));
        assertNotNull(c.getBean(SessionFactory.class));
    }
}
