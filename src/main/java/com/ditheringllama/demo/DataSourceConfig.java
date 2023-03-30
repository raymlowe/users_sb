package com.ditheringllama.demo;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

	@Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/app_auth");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("password");
        return dataSourceBuilder.build();
    }
	
	@Bean(name="orbeonWSSessionFactory")
	@Primary
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	    sessionFactory.setDataSource(this.getDataSource());
	    sessionFactory.setPackagesToScan(
	            new String[] {
	              "com.ditheringllama.demo"
	            });
	    sessionFactory.setHibernateProperties(hibernateProperties());
	    return sessionFactory;
	}
	
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL81Dialect");
	    properties.put("hibernate.show_sql", "true");
	    properties.put("hibernate.format_sql", "true");
	    return properties;
	}

}
