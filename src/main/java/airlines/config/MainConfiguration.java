package airlines.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by winio_000 on 2015-12-13.
 */
@Configuration
@Import(value = {BeansConfiguration.class, DatabaseConfiguration.class})
@ComponentScan(basePackages = {"airlines"})
public class MainConfiguration {

}
