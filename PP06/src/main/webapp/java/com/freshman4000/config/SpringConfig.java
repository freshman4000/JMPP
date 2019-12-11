package com.freshman4000.config;

import com.freshman4000.model.Cat;
import com.freshman4000.model.HelloWorld;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SpringConfig {
    @Bean("HelloWorld")
    public HelloWorld getHelloWorld() {
        return new HelloWorld();
    }
    @Bean("cat")
    @Scope("prototype")
    public Cat getCat() {
        return new Cat();
    }
}
