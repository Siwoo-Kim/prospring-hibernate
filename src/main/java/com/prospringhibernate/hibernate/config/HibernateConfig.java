package com.prospringhibernate.hibernate.config;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Properties;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-08-02 오전 11:15
 **/

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.prospringhibernate.hibernate.repository"})
public class HibernateConfig {

    @Bean
    DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:/sql/schema.sql")
                .addScript("classpath:/sql/data.sql")
                .build();
    }

    @Bean
    PlatformTransactionManager transactionManager() {
        return new HibernateTransactionManager(sessionFactory());
    }

    @Bean
    SessionFactory sessionFactory() {
        LocalSessionFactoryBean factoryBean = null;
        try {
            factoryBean = new LocalSessionFactoryBean();
            factoryBean.setPackagesToScan("com.prospringhibernate.hibernate.domain");
            factoryBean.setDataSource(dataSource());
            factoryBean.setHibernateProperties(hibernateProperties());
            factoryBean.afterPropertiesSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return factoryBean.getObject();
    }

    @Bean
    Properties hibernateProperties() {
        Properties properties = new Properties();
//        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.format_sql", true);
        return properties;
    }
}

