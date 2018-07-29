package cn.ep.spring.boot.ch04.s17;

import cn.ep.ws.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class WsClient extends WebServiceGatewaySupport {

    public GetCountryResponse getCountry(String name) {
        GetCountryRequest request = new GetCountryRequest();
        request.setName(name);
        GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate().marshalSendAndReceive(
                "http://localhost:8080/ws/countries.wsdl", request);
        return response;
    }
}
