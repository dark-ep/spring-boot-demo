package cn.ep.spring.boot.ch07.s03;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.*;

@Configuration
public class ConditionalPropertyConfig {

    @Bean(name = "conditionalOnPropertyMissPrefixStr")
    @ConditionalOnProperty(prefix = "aa", name = "key", matchIfMissing = true)//当设置matchIfMissing为true，不论参数是否存在都会执行该方法，默认不存在则不加载
    public String conditionalOnPropertyMissPrefixStr() {
        return "conditionalOnPropertyMissPrefixStr";
    }

    @Bean(name = "conditionalOnPropertyPrefixStr")
    @ConditionalOnProperty(prefix = "bb", name = "key")
    public String conditionalOnPropertyPrefixStr() {
        return "conditionalOnPropertyPrefixStr";
    }

    @Bean(name = "conditionalOnPropertyMissNameStr")
    @ConditionalOnProperty(matchIfMissing = true, name = {"aa.key"})
    public String conditionalOnPropertyMissNameStr() {
        return "conditionalOnPropertyMissNameStr";
    }

    @Bean(name = "conditionalOnPropertyNameStr")
    @ConditionalOnProperty(name = {"bb.key"})
    public String conditionalOnPropertyNameStr() {
        return "conditionalOnPropertyNameStr";
    }

    @Bean(name = "conditionalOnPropertyMissHavingValueStr")
    @ConditionalOnProperty(matchIfMissing = true, havingValue = "aa.value", name = "aa.key")
    public String conditionalOnPropertyMissHavingValueStr() {
        return "conditionalOnPropertyMissHavingValueStr";
    }

    @Bean(name = "conditionalOnPropertyHavingValueStr")
    @ConditionalOnProperty(havingValue = "bb.value", name = "bb.key")
    public String conditionalOnPropertyHavingValueStr() {
        return "conditionalOnPropertyHavingValueStr";
    }

}
