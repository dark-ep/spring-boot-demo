package cn.ep.spring.boot.ch04.s09;

import org.springframework.web.bind.annotation.*;

@RestController
public class CustomController {

    @GetMapping("/getCustom")
    public Customer getCustom() {
        Customer customer = new Customer();
        customer.setFirstName("aa");
        customer.setLastName("bb");
        return customer;
    }

}
