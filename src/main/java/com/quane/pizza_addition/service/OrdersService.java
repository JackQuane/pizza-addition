package com.quane.pizza_addition.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.quane.pizza_addition.exceptions.PizzaApiException;
import com.quane.pizza_addition.model.OrderRequest;
import com.quane.pizza_addition.model.OrderResponse;
import com.quane.pizza_addition.model.RegistrationResponse;
import lombok.AllArgsConstructor;
import net.sf.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.SocketUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
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

    public void createOrder(String token, OrderRequest orderRequest) {
        
        isOrderValid(orderRequest);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", token);

        String jsonStr = null;
        ObjectMapper Obj = new ObjectMapper();
        try {
            jsonStr = Obj.writeValueAsString(orderRequest);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        HttpEntity request = new HttpEntity<>(jsonStr, headers);

        ResponseEntity<OrderResponse> response = new RestTemplate().exchange(ordersUrl, HttpMethod.POST, request,
            new ParameterizedTypeReference<OrderResponse>(){});
    }

    private void isOrderValid(OrderRequest orderRequest) {
        if (orderRequest.getCrust().equals("Thin") &&
           (orderRequest.getFlavor().equals("Hawaii") || orderRequest.getFlavor().equals("Regina") || orderRequest.getFlavor().equals("Quattro-Formaggi")) &&
           (orderRequest.getSize().equals("L") || orderRequest.getSize().equals("M")) )
        {
            System.out.println("Order is Valid");
        } else {
            throw new PizzaApiException("Invalid Order: Pizza type not allowed");
        }
    }
}
