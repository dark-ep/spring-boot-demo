package cn.ep.spring.boot.run1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyCommandLineRunner1 implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("MyCommandLineRunner1.run()");
    }
}
