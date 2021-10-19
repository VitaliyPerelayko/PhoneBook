package by.itstep.phonebook.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "by.itstep.phonebook")
@Import({DatabaseConfiguration.class, WebConfiguration.class})
public class AppConfiguration {
}
