package cn.ep.spring.boot.ch07.s01;

import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.*;

@Configuration
public class ConditionalClassConfig {

    @Bean(name = "conditionalOnClassStr")
    @ConditionalOnClass({String.class})
    public String ConditionalOnClassStr() {
        return "conditionalOnClassStr";
    }

    @Bean(name = "conditionalOnMissingClassStr")
    @ConditionalOnMissingClass({"test"})
    public String conditionalOnMissingClassStr() {
        return "conditionalOnMissingClassStr";
    }

}
