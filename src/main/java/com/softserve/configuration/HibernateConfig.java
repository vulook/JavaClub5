package com.softserve.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

@Configuration
@EnableTransactionManagement
@ComponentScan({"com.softserve"})
@PropertySource({"classpath:db.properties"})

public class HibernateConfig {

    @Resource
    private Environment env;

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Bean
    public DataSource securityDataSource() {

        ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

        try {
            securityDataSource.setDriverClass(env.getRequiredProperty("jdbc.driverClassName"));
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }

        logger.info("=>>>> jdbc.url=" + env.getProperty("jdbc.url"));
        logger.info("=>>>> jdbc.user=" + env.getProperty("jdbc.username"));

        securityDataSource.setJdbcUrl(env.getRequiredProperty("jdbc.url"));
        securityDataSource.setUser(env.getRequiredProperty("jdbc.username"));
        securityDataSource.setPassword(env.getRequiredProperty("jdbc.password"));

        // set connection pool props
        securityDataSource.setInitialPoolSize(
                getIntProperty("connection.pool.initialPoolSize"));

        securityDataSource.setMinPoolSize(
                getIntProperty("connection.pool.minPoolSize"));

        securityDataSource.setMaxPoolSize(
                getIntProperty("connection.pool.maxPoolSize"));

        securityDataSource.setMaxIdleTime(
                getIntProperty("connection.pool.maxIdleTime"));

        return securityDataSource;
    }

    private int getIntProperty(String propName) {
        String propVal = env.getProperty(propName);
        assert propVal != null;
        return Integer.parseInt(propVal);
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(securityDataSource());
        sessionFactory.setPackagesToScan("com.softserve.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.connection.characterEncoding", env.getRequiredProperty("hibernate.connection.characterEncoding"));
        properties.put("hibernate.cache.use_second_level_cache", env.getRequiredProperty("hibernate.cache.use_second_level_cache"));
        properties.put("hibernate.cache.use_query_cache", env.getRequiredProperty("hibernate.cache.use_query_cache"));
        properties.put("hibernate.globally_quoted_identifiers", env.getRequiredProperty("hibernate.globally_quoted_identifiers"));
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}