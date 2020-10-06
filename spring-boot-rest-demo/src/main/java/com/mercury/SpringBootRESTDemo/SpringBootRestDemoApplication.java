package com.mercury.SpringBootRESTDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
// @SpringBootApplication 中包含了：
//1. @Configuration : Spring IoC configuration class ( @Bean )
//2. @ComponentScan : default basePackage is current class's package
//3. Spring boot 精华！
// @EnableAutoConfiguration : automatically config certain beans according
// to class path
// pom.xml and application.properties(configuration)  e.g. DispatcherServlet

@EnableCaching //concurrentHashMap
@SpringBootApplication
//@SpringBootConfiguration
//@EnableAutoConfiguration

public class SpringBootRestDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestDemoApplication.class, args);
    }

    //password
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }
}
