package cn.ep.spring.boot.ch04.s19;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class App extends SpringBootServletInitializer {

    //http://localhost:8080/swagger-ui.html
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

}
