package cn.ep.spring.boot.ch06.s02.test;

import cn.ep.spring.boot.ch06.s02.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.*;

import java.nio.charset.Charset;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerServiceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testPut() {
        Customer customer = new Customer(3L, "AA", "BB");
        ResponseEntity<String> response = exchange("/customer", HttpMethod.PUT, String.class, customer);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
    }

    @Test
    public void testPost() {
        Customer customer = new Customer(3L, "AA", "BB");
        ResponseEntity<String> response = exchange("/customer", HttpMethod.POST, String.class, customer);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
    }

    @Test
    public void testDelete() {
        ResponseEntity<String> response = exchange("/customer/1", HttpMethod.DELETE, String.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
    }

    @Test
    public void testGet() {
        ResponseEntity<List> response = exchange("/getall", HttpMethod.GET, List.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        response.getBody().forEach(System.out::println);
    }

    private <T> ResponseEntity<T> exchange(String url, HttpMethod method, Class<T> bodyType) {
        return exchange(url, method, bodyType, null);
    }

    private <T> ResponseEntity<T> exchange(String url, HttpMethod method, Class<T> bodyType, Customer params) {
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        MimeType mimeType = MimeTypeUtils.APPLICATION_JSON;
        MediaType mediaType = new MediaType(mimeType.getType(), mimeType.getSubtype(), Charset.forName("UTF-8"));
        // 请求体
        headers.setContentType(mediaType);
        // 发送请求
        HttpEntity<Object> entity = new HttpEntity<>(params, headers);
        return restTemplate.exchange(url, method, entity, bodyType);
    }

}
