package com.epam.ta.config;

import com.epam.ta.helper.WebDriverFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.epam.ta")
public class TestConfig {

    @Bean(destroyMethod = "tearDown")
    WebDriverFactory webDriverFactory() {
        return new WebDriverFactory();
    }

}
