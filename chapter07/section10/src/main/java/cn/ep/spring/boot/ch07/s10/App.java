package cn.ep.spring.boot.ch07.s10;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    @Bean
    public CommandLineRunner demo(CustomService customService) {
        return (args) -> {
            System.out.println("key:" + customService.getKey());
            System.out.println("value:" + customService.getValue());
        };
    }

}
