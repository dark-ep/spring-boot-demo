package cn.ep.spring.boot.ch04.s15;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Path("/")
public class CustomerRestApi {

    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/customer")
    public Customer customer(@QueryParam("name") String name) {
        name = StringUtils.isEmpty(name) ? "World" : name;
        return new Customer(counter.incrementAndGet(),
                String.format(TEMPLATE, name));
    }

}
