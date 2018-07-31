package cn.ep.spring.boot.ch05.s02.test;

import cn.ep.spring.boot.ch05.s02.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureJsonTesters
public class CustomJsonTest {

    @Autowired
    private GsonTester<Customer> jsonTester;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testJson() throws Exception {
        String content = this.restTemplate.getForObject("/getCustom", String.class);
        Customer customer = jsonTester.parseObject(content);
        assertThat(customer.getFirstName()).isEqualTo("aa");
        assertThat(customer.getLastName()).isEqualTo("bb");
    }

}
