package cn.ep.spring.boot.ch04.s14;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CustomerController {

    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/customer")
    public Customer customer(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Customer(counter.incrementAndGet(),
                String.format(TEMPLATE, name));
    }
}
