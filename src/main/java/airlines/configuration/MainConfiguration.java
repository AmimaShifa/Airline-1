package airlines.configuration;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Created by winio_000 on 2015-12-13.
 */
@Configuration
@Import(value = {BeansConfiguration.class, DatabaseConfiguration.class, TransactionConfiguration.class})
@ComponentScan(basePackages = {"airlines"})
public class MainConfiguration {
    private static Logger logger = Logger.getLogger(MainConfiguration.class);

    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
        resource.setBasenames("classpath:messages", "classpath:validation");
        resource.setDefaultEncoding("UTF-8");
        return resource;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
