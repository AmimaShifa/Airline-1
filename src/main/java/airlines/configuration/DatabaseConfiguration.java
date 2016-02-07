package airlines.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
 * Created by winio_000 on 2016-02-07.
 */
@Configuration
@EnableJpaRepositories(basePackages = "airlines")
public abstract class DatabaseConfiguration {

    @Value("${db.url}")
    protected String url;

    @Value("${db.dbName}")
    protected String dbName;

    @Value("${db.driver}")
    protected String driver;

    @Value("${db.userName}")
    protected String userName;

    @Value("${db.password}")
    protected String password;

    @Value("${db.tcpServer}")
    protected String tcpServer;

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

    @Bean
    protected JdbcOperations tpl() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    protected PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    public abstract Properties additionalJpaProperties();

    public abstract DataSource dataSource();
}
