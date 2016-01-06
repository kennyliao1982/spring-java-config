package com.springdemo.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring database related configuration
 * 
 * @author kenny
 * 
 */
@Configuration
@PropertySource("classpath:config.properties")
@EnableTransactionManagement
@EnableJpaRepositories(DatabaseConfig.REPOSITORY_PACKAGES_TO_SCAN)
public class DatabaseConfig {

	private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
	private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";

	private static final String ENTITYMANAGER_PACKAGES_TO_SCAN = "com.springdemo.entity";
	static final String REPOSITORY_PACKAGES_TO_SCAN = "com.springdemo.repository";
	private static boolean generateDDL = true;

	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
		dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
		dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
		dataSource.setPoolPreparedStatements(true);
		dataSource.setInitialSize(5);
		dataSource.setMaxTotal(25);
		dataSource.setMaxIdle(5);
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);

		HibernateJpaVendorAdapter jpsVenderAdapter = new HibernateJpaVendorAdapter();
		jpsVenderAdapter.setGenerateDdl(generateDDL);
		entityManagerFactoryBean.setJpaVendorAdapter(jpsVenderAdapter);

		Properties hibernateProperties = new Properties();
		hibernateProperties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		hibernateProperties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		entityManagerFactoryBean.setJpaProperties(hibernateProperties);

		return entityManagerFactoryBean;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

}