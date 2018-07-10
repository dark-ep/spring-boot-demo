package cn.ep.spring.boot.ch03.s06;

import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;

@SpringBootApplication
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner demo(RedisTemplate<String, Object> redisTemplate) {
        return args -> {
            log.info("Customers save");
            redisTemplate.opsForValue().set("1", new Customer().withId("1").withFirstName("Jack").withLastName("Bauer"));
            redisTemplate.opsForValue().set("2", new Customer().withId("2").withFirstName("Chloe").withLastName("O'Brian"));
            redisTemplate.opsForValue().set("3", new Customer().withId("3").withFirstName("Kim").withLastName("Bauer"));
            redisTemplate.opsForValue().set("4", new Customer().withId("4").withFirstName("David").withLastName("Palmer"));
            redisTemplate.opsForValue().set("5", new Customer().withId("5").withFirstName("Michelle").withLastName("Dessler"));

            Customer customer1 = (Customer) redisTemplate.opsForValue().get("1");
            log.info(String.valueOf(customer1));
            Customer customer2 = (Customer) redisTemplate.opsForValue().get("2");
            log.info(String.valueOf(customer2));
            Customer customer3 = (Customer) redisTemplate.opsForValue().get("3");
            log.info(String.valueOf(customer3));
            Customer customer4 = (Customer) redisTemplate.opsForValue().get("4");
            log.info(String.valueOf(customer4));
            Customer customer5 = (Customer) redisTemplate.opsForValue().get("5");
            log.info(String.valueOf(customer5));

            log.info("Customers delete");
            redisTemplate.delete(Arrays.asList("1", "2", "3", "4", "5"));
            customer1 = (Customer) redisTemplate.opsForValue().get("1");
            log.info(String.valueOf(customer1));
            customer2 = (Customer) redisTemplate.opsForValue().get("2");
            log.info(String.valueOf(customer2));
            customer3 = (Customer) redisTemplate.opsForValue().get("3");
            log.info(String.valueOf(customer3));
            customer4 = (Customer) redisTemplate.opsForValue().get("4");
            log.info(String.valueOf(customer4));
            customer5 = (Customer) redisTemplate.opsForValue().get("5");
            log.info(String.valueOf(customer5));
        };
    }
}
