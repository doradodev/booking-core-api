package com.doradodev.digital.factory.core.repository.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.slf4j.Slf4j;

/**
 * @author cristiandorado
 * 
 */
@Slf4j
@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
@EnableJpaRepositories("com.doradodev.digital.factory.core.repository.model")
@ComponentScan("com.doradodev.digital.factory.core.repository")
@PropertySource("classpath:hibernate.properties")
public class RepositoryConfig {

	private static final String INIT_SCRIPT = "dbscript/schema.sql";
	private static final String[] ENTITY_PACKAGES = { "com.doradodev.digital.factory.core.entity" };
	private static final String HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
	private static final String HIBERNATE_HBM2DDL_SCRIPTS = "hibernate.hbm2ddl.import_files";
	private static final String HIBERNATE_HBM2DDL_AUTO_UPDATE = "update";
	private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";

	@Value("${hibernate.hibernateDialect}")
	private String hibernateDialect;
	@Value("${hibernate.showSQL}")
	private String showSql;
	@Value("${hibernate.generateStatistics}")
	private String generateStatistics;

	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		builder.setName("H2-Room-DB");
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2).addScript("dbscript/schema.sql").build();
		log.info("Initiating the database from dbscript.");
		return db;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {

		Properties properties = new Properties();
		properties.put(HIBERNATE_DIALECT, hibernateDialect);
		properties.put(HIBERNATE_SHOW_SQL, showSql);

		log.info("Init database from base scripts...");

		properties.put(HIBERNATE_HBM2DDL_AUTO, HIBERNATE_HBM2DDL_AUTO_UPDATE);
		properties.put(HIBERNATE_HBM2DDL_SCRIPTS, INIT_SCRIPT);

		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setPackagesToScan(ENTITY_PACKAGES);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setJpaProperties(properties);
		
        

		return entityManagerFactoryBean;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}
