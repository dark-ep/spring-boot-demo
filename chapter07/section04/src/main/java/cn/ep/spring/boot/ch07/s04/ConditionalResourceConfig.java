package cn.ep.spring.boot.ch07.s04;

import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.*;

@Configuration
public class ConditionalResourceConfig {

    @Bean(name = "conditionalOnResourceStr")
    @ConditionalOnResource(resources = {"classpath:config.properties"})
    public String conditionalOnResourceStr() {
        return "conditionalOnResourceStr";
    }

}
