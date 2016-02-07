package airlines.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by winio_000 on 2016-02-07.
 */
@Configuration
@PropertySources({@PropertySource(value = "classpath:persistence.releaseProperties")})
@EnableJpaRepositories(basePackages = "airlines")
public class DatabaseConfigurationRelease extends DatabaseConfiguration {

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url + "/" + dbName);
        ds.setUsername(userName);
        ds.setPassword(password);

        return ds;
    }

    public Properties additionalJpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        properties.setProperty("current_session_context_class", "thread");

        return properties;
    }
}
