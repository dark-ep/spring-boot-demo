package cn.ep.spring.boot.ch04.s14.test;

import cn.ep.spring.boot.ch04.s14.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerRestTest {

    private static final String TEMPLATE = "Hello, %s!";
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testNoParam() {
        Customer customer = this.restTemplate.getForObject("/customer", Customer.class);
        assertThat(customer).isNotNull();
        assertThat(customer.getName()).isEqualTo(String.format(TEMPLATE, "World"));
    }

    @Test
    public void testUseParam() {
        String name = "aa";
        Customer customer = this.restTemplate.getForObject("/customer?name=" + name, Customer.class);
        assertThat(customer).isNotNull();
        assertThat(customer.getName()).isEqualTo(String.format(TEMPLATE, name));
    }

}
