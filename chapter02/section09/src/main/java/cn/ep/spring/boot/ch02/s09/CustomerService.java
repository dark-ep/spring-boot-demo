package cn.ep.spring.boot.ch02.s09;

import org.slf4j.*;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private static Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @CachePut(value = "test", key = "#id")
    public Customer save(String id) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName("a");
        customer.setLastName("b");
        logger.info("为id、key为:" + customer.getId() + "数据做了缓存");
        return customer;
    }

    @CacheEvict(value = "test", key = "#id")
    public void remove(String id) {
        logger.info("删除了id、key为" + id + "的数据缓存");
        //这里不做实际删除操作
    }

    /**
     * Cacheable
     * value：缓存key的前缀。
     * key：缓存key的后缀。
     * sync：设置如果缓存过期是不是只放一个请求去请求数据库，其他请求阻塞，默认是false。
     */
    @Cacheable(value = "test", key = "#id", sync = true)
    public Customer findOne(String id) {
        Customer c = new Customer();
        c.setId("1");
        c.setFirstName("a");
        c.setLastName("b");
        logger.info("为id、key为:" + c.getId() + "数据做了缓存");
        return c;
    }

}
