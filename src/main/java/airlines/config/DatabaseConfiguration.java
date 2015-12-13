package airlines.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by winio_000 on 2015-12-13.
 */

@Configuration
@PropertySources({@PropertySource(value = "classpath:persistence.properties")})
@EnableJpaRepositories(basePackages = "airlines")
public class DatabaseConfiguration {
    private static Logger logger = Logger.getLogger(DatabaseConfiguration.class);

    @Value("${db.url}")
    private String url;

    @Value("${db.dbName}")
    private String dbName;

    @Value("${db.driver}")
    private String driver;

    @Value("${db.userName}")
    private String userName;

    @Value("${db.password}")
    private String password;

    @Value("${db.tcpServer}")
    private String tcpServer;


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("airlines");
        em.setJpaDialect(new HibernateJpaDialect());
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(additionalJpaProperties());

        return em;
    }


    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url + "/" + dbName);
        ds.setUsername(userName);
        ds.setPassword(password);
        ds.setInitialSize(5);
        ds.setMaxActive(10);
        ds.setMaxIdle(5);
        ds.setMinIdle(2);

        return ds;
    }

    @Bean
    public JdbcOperations tpl() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties additionalJpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.setProperty("current_session_context_class", "thread");

        return properties;
    }


}
