package cn.ep.spring.boot.ch05.s07.test;

import cn.ep.spring.boot.ch05.s07.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(CustomerRestClient.class)
public class CustomerRestTest {

    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private CustomerRestClient service;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testNoParam() throws Exception {
        String name = "World";
        String detailsString = objectMapper.writeValueAsString(new Customer(counter.incrementAndGet(),
                String.format(TEMPLATE, name)));
        this.server.expect(requestTo("/customer"))
                .andRespond(withSuccess(detailsString, MediaType.APPLICATION_JSON));
        Customer customer = this.service.customer(null);
        assertThat(customer).isNotNull();
        assertThat(customer.getName()).isEqualTo(String.format(TEMPLATE, name));
    }

    @Test
    public void testUseParam() throws Exception {
        String name = "aa";
        String detailsString = objectMapper.writeValueAsString(new Customer(counter.incrementAndGet(),
                String.format(TEMPLATE, name)));
        this.server.expect(requestToUriTemplate("/customer?name={name}", name))
                .andRespond(withSuccess(detailsString, MediaType.APPLICATION_JSON));
        Customer customer = this.service.customer(name);
        assertThat(customer).isNotNull();
        assertThat(customer.getName()).isEqualTo(String.format(TEMPLATE, name));
    }

}
