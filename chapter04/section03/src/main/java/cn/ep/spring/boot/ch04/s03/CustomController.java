package cn.ep.spring.boot.ch04.s03;

import org.springframework.web.bind.annotation.*;

@RestController
public class CustomController {

    @GetMapping("/")
    public String hello() {
        return "Hello World!";
    }

}
