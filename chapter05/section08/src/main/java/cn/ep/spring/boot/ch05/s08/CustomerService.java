package cn.ep.spring.boot.ch05.s08;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerBuilder builder;

    public Customer getCustom() {
        return builder.create();
    }

}
