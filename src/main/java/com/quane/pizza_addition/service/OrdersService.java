package com.quane.pizza_addition.service;

import com.quane.pizza_addition.model.OrderResponse;
import com.quane.pizza_addition.model.RegistrationResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class OrdersService {

    private final String ordersUrl = "https://order-pizza-api.herokuapp.com/api/orders";

    public List<OrderResponse> getAllOrders() {
        HttpHeaders headers = new HttpHeaders();
        //        headers.add("Authorization", "Bearer" + token);
        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<List<OrderResponse>> response = new RestTemplate().exchange(ordersUrl, HttpMethod.GET, request,
                new ParameterizedTypeReference<List<OrderResponse>>(){});

        return response.getBody();
    }

}
