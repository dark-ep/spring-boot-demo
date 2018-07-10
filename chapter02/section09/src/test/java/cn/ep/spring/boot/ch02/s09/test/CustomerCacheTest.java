package cn.ep.spring.boot.ch02.s09.test;

import cn.ep.spring.boot.ch02.s09.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerCacheTest {

    private static final String TEST = "test::";
    @Autowired
    private CustomerService customerService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testSave() {
        final String id = "1";
        Customer customer = customerService.save(new Customer().withId(id).withFirstName("Jack").withLastName("Bauer"));
        Customer result = (Customer) redisTemplate.opsForValue().get(TEST + id);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(customer.getId());
        assertThat(result.getFirstName()).isEqualTo(customer.getFirstName());
        assertThat(result.getLastName()).isEqualTo(customer.getLastName());
        redisTemplate.delete(TEST + id);
    }

    @Test
    public void testDelete() {
        final String id = "1";
        customerService.save(new Customer().withId(id).withFirstName("Jack").withLastName("Bauer"));
        customerService.remove(id);
        Customer result = (Customer) redisTemplate.opsForValue().get(TEST + id);
        assertThat(result).isNull();
    }

    @Test
    public void testFindOne() {
        final String id = "1";
        customerService.save(new Customer().withId(id).withFirstName("Jack").withLastName("Bauer"));
        Customer customer = customerService.findOne(id);
        Customer result = (Customer) redisTemplate.opsForValue().get(TEST + id);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(customer.getId());
        assertThat(result.getFirstName()).isEqualTo(customer.getFirstName());
        assertThat(result.getLastName()).isEqualTo(customer.getLastName());
        redisTemplate.delete(TEST + id);
    }

}
