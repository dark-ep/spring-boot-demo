package cn.ep.spring.boot.ch04.s17;

import cn.ep.ws.GetCountryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CountryController {

    @Autowired
    private WsClient wsClient;

    @GetMapping("callws")
    public Object callWs() {
        GetCountryResponse response = wsClient.getCountry("hello");
        return response.getCountry();
    }

}
