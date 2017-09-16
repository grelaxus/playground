package com.vk.dal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.util.ClassUtils;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Properties;


@Configuration
//@ConditionalOnClass(DataSource.class)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@PropertySource("classpath:mysql.properties")
public class MySQLAutoconfiguration {

  @Autowired
  private Environment env;

//
//  @ConditionalOnProperty(name = "usemysql", havingValue = "local")
//  @ConditionalOnMissingBean
/*  @Bean
    public DataSource dataSource() {
    final DriverManagerDataSource dataSource = new DriverManagerDataSource();

    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    dataSource.setUrl("jdbc:mysql://localhost:3306/myDb?createDatabaseIfNotExist=true");
    dataSource.setUsername("mysqluser");
    dataSource.setPassword("mysqlpass");

    return dataSource;
  }
*/
  @Bean/*(name = "dataSource")*/
//  @ConditionalOnProperty(name = "usemysql", havingValue = "custom")
  @ConditionalOnMissingBean
  public DataSource dataSource() {
    final DriverManagerDataSource dataSource = new DriverManagerDataSource();

    dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
    dataSource.setUrl(env.getProperty("spring.datasource.url"));
    dataSource.setUsername(env.getProperty("spring.datasource.username") != null ? env.getProperty("spring.datasource.username") : "");
    dataSource.setPassword(env.getProperty("spring.datasource.password") != null ? env.getProperty("spring.datasource.password") : "");

    return dataSource;
  }

  @Bean
  @ConditionalOnBean(name = "dataSource")
  @ConditionalOnMissingBean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(dataSource());
    em.setPackagesToScan("com.vk.dal");
    em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    if (additionalProperties() != null) {
      em.setJpaProperties(additionalProperties());
    }
    return em;
  }

  @Bean
  @ConditionalOnMissingBean(type = "JpaTransactionManager")
  JpaTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
    final JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory);
    return transactionManager;
  }

  @ConditionalOnResource(resources = "classpath:mysql.properties")
  @Conditional(HibernateCondition.class)
  final Properties additionalProperties() {
    final Properties hibernateProperties = new Properties();

    hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("mysql-hibernate.hbm2ddl.auto"));
    hibernateProperties.setProperty("hibernate.dialect", env.getProperty("mysql-hibernate.dialect"));
    hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("mysql-hibernate.show_sql") != null ? env.getProperty("mysql-hibernate.show_sql") : "false");

/*
    hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
    hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
    hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("spring.jpa.show-sql") != null ? env.getProperty("spring.jpa.show-sql") : "false");*/

    return hibernateProperties;
  }

  static class HibernateCondition extends SpringBootCondition {

    private static final String[] CLASS_NAMES = { "org.hibernate.ejb.HibernateEntityManager", "org.hibernate.jpa.HibernateEntityManager" };

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
      ConditionMessage.Builder message = ConditionMessage.forCondition("Hibernate");

      return Arrays.stream(CLASS_NAMES)
          .filter(className -> ClassUtils.isPresent(className, context.getClassLoader()))
          .map(className -> ConditionOutcome.match(message.found("class")
              .items(ConditionMessage.Style.NORMAL, className)))
          .findAny()
          .orElseGet(() -> ConditionOutcome.noMatch(message.didNotFind("class", "classes")
              .items(ConditionMessage.Style.NORMAL, Arrays.asList(CLASS_NAMES))));
    }

  }
}
