package cn.ep.spring.boot.ch07.s06;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.*;

@Configuration
public class ConditionalExpressionConfig {

    @Bean(name = "conditionalOnExpressionStr")
    @ConditionalOnExpression("${flag1:false}" +
            " && ${flag2:false}" +
            " && ${flag3:true}")
    public String conditionalOnExpressionStr() {
        return "conditionalOnExpressionStr";
    }

}
