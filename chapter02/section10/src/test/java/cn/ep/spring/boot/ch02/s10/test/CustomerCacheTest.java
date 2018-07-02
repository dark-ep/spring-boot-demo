package cn.ep.spring.boot.ch02.s10.test;

import cn.ep.spring.boot.ch02.s10.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerCacheTest {

    private static Logger log = LoggerFactory.getLogger(CustomerCacheTest.class);

    @Autowired
    private CustomerService customerService;

    @Test
    public void test() {
        String id = "1";
        Customer customer = customerService.save(id);
        log.info("save :" + customer);
        Customer customer1 = customerService.findOne(id);
        log.info("findOne :" + customer1);
        Customer customer2 = customerService.findOne(id);
        log.info("findOne :" + customer2);
        customerService.remove("2");
        log.info("remove : 2");
        Customer customer3 = customerService.findOne(id);
        log.info("findOne :" + customer3);
        customerService.remove(id);
        log.info("remove :" + id);
        Customer customer4 = customerService.findOne(id);
        log.info("findOne :" + customer4);
    }

}
