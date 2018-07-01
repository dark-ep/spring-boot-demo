package cn.ep.spring.boot.run2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyCommandLineRunner2 implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("MyCommandLineRunner2.run()");
    }
}