package cn.ep.spring.boot.ch02.s05;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @TestAnn
    @Override
    public void run(String... args) {
        System.out.println("MyCommandLineRunner.run()");
    }
}
