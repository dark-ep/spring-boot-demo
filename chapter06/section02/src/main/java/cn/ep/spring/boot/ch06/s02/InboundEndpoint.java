package cn.ep.spring.boot.ch06.s02;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InboundEndpoint {

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());
    @Autowired
    private CustomerService customerService;

    public Message<?> get(Message<?> msg) {
        log.info("GET method");
        List<Customer> customers = customerService.getAll();
        return MessageBuilder.withPayload(customers).copyHeadersIfAbsent(msg.getHeaders())
                .setHeader("http_statusCode", HttpStatus.OK).build();
    }

    public void post(Message<Customer> msg) {
        log.info("POST method");
        customerService.insert(msg.getPayload());
    }

    public void put(Message<Customer> msg) {
        log.info("PUT method");
        customerService.change(msg.getPayload());
    }

    public void delete(Message<String> msg) {
        log.info("DELETE method");
        Long id = Long.valueOf(msg.getPayload());
        customerService.delete(id);
    }
}
