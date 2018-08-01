package cn.ep.spring.boot.ch07.s02;

import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.*;

@Configuration
public class ConditionalBeanConfig {

    @Bean(name = "conditionalOnBeanStr")
    @ConditionalOnBean({BeanObj.class})
    public String conditionalOnBeanStr() {
        return "conditionalOnBeanStr";
    }

    @Bean(name = "conditionalOnMissingBeanStr")
    @ConditionalOnMissingBean(name = {"test"})
    public String conditionalOnMissingBeanStr() {
        return "conditionalOnMissingBeanStr";
    }

}
