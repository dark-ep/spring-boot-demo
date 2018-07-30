package cn.ep.spring.boot.ch04.s19;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@Api(value = "/test", tags = "测试接口模块")
@RequestMapping("/test")
@RestController
public class CustomerController {

    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @ApiOperation(value = "用户名展示", notes = "测试用户名展示")
    @ApiImplicitParam(name = "name", value = "String", required = true, dataType = "String")
    @GetMapping("/customer")
    public Customer customer(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Customer(counter.incrementAndGet(),
                String.format(TEMPLATE, name));
    }

}
