package cn.ep.spring.boot.ch05.s08;

import org.springframework.stereotype.Component;

@Component
public class CustomerBuilder {

    public Customer create() {
        return new Customer("aa", "bb");
    }

}
