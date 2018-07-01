package cn.ep.spring.boot.ch02.s04;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner demo(DynamicTask task) {
        return (args) -> {
            task.startCron();
            // 让线程睡眠 15秒.
            Thread.sleep(15000);
            task.startCron10();
        };
    }

}
