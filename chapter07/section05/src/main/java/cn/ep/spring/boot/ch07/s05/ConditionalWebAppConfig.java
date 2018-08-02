package cn.ep.spring.boot.ch07.s05;

import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.*;

@Configuration
public class ConditionalWebAppConfig {

    @Bean(name = "conditionalOnNotWebApplicationStr")
    @ConditionalOnNotWebApplication
    public String conditionalOnNotWebApplicationStr() {
        return "conditionalOnNotWebApplicationStr";
    }

    @Bean(name = "conditionalOnWebApplicationStr")
    @ConditionalOnWebApplication
    public String conditionalOnWebApplicationStr() {
        return "conditionalOnWebApplicationStr";
    }

}
