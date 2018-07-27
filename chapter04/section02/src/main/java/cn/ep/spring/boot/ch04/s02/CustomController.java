package cn.ep.spring.boot.ch04.s02;

import org.springframework.web.bind.annotation.*;

@RestController
public class CustomController {

    @GetMapping("/")
    public int zeroException() {
        return 100 / 0;
    }

}
