package cn.ep.spring.boot.ch01.s03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//其中@SpringBootApplication申明让spring boot自动给程序进行必要的配置，等价于以默认属性使用@Configuration，@EnableAutoConfiguration和@ComponentScan
public class App {

    @Autowired
    private Prop prop;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public Prop demo() {
        System.out.println("prop.key='" + prop.getKey() + "',prop.value='" + prop.getValue() + "'");
        return prop;
    }

}
