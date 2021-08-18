package com.quane.pizza_addition.service;

import com.quane.pizza_addition.model.OrderResponse;
import com.quane.pizza_addition.model.RegistrationResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import net.sf.json.JSONObject;

import java.util.List;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final OrdersService ordersService;

    public RegistrationResponse register() {

        int maxTableNum = -1;
        int newTableNum = 0;

        for (OrderResponse orderResponse: ordersService.getAllOrders()) {
            int num = orderResponse.getTableNum();
            if(num>maxTableNum) {
                maxTableNum = num;
            }
        }

        //"we have agreed with the pizzeria, that if we use table number which is greater than 10000,
        //then they will know, that the pizza must be prepared for the takeway."
        if(maxTableNum>10000) {
            newTableNum = maxTableNum+1;
        } else {
            newTableNum = 10001;
        }

        RegistrationResponse registrationResponse = new RegistrationResponse();
        registrationResponse.setToken(getAccessToken());
        registrationResponse.setTableNum(newTableNum);

        return registrationResponse;
    }

    public String getAccessToken() {
        final String authUrl = "https://order-pizza-api.herokuapp.com/api/auth";
        JSONObject credentials = new JSONObject();
        credentials.put("password", "test");
        credentials.put("username", "test");

        RestTemplate restTemplate = new RestTemplate();
        String bearerToken = restTemplate.postForObject(authUrl, credentials, String.class);

        //removes words 'access_token' and quotes etc. To just get the token value
        return bearerToken.substring(17, bearerToken.lastIndexOf('"'));
    }

}
