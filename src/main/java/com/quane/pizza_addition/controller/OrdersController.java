package com.quane.pizza_addition.controller;

import com.quane.pizza_addition.model.OrderRequest;
import com.quane.pizza_addition.model.OrderResponse;
import com.quane.pizza_addition.service.OrdersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestHeader("Authorization") String token, @RequestBody OrderRequest orderRequest) {
        ordersService.createOrder(token, orderRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Order Created");
    }

}
