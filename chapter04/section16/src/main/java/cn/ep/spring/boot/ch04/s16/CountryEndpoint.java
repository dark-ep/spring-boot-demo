package cn.ep.spring.boot.ch04.s16;

import cn.ep.ws.*;
import org.springframework.ws.server.endpoint.annotation.*;

@Endpoint
public class CountryEndpoint {

    private static final String NAMESPACE_URI = "http://www.ep.cn/ws";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        Country poland = new Country();
        poland.setName("Poland-" + request.getName());
        poland.setCapital("Warsaw");
        poland.setCurrency(Currency.PLN);
        poland.setPopulation(38186860);
        response.setCountry(poland);
        return response;
    }

}
