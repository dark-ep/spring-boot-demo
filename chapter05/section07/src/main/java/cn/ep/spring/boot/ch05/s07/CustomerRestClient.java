package cn.ep.spring.boot.ch05.s07;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerRestClient {

    private final RestTemplate restTemplate;

    public CustomerRestClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public Customer customer(String name) {
        if (StringUtils.isEmpty(name)) {
            return restTemplate.getForObject("/customer", Customer.class);
        }
        return restTemplate.getForObject("/customer?name={name}", Customer.class, name);
    }

}
