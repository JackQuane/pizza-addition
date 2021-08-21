package com.quane.pizza_addition.controller;

import com.quane.pizza_addition.model.OrderRequest;
import com.quane.pizza_addition.model.OrderResponse;
import com.quane.pizza_addition.service.OrdersService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    @PostMapping
    @ApiOperation("user can place order for one of the pre-set pizza types")
    public ResponseEntity<String> createOrder(@RequestHeader("Authorization") String token, @RequestBody OrderRequest orderRequest) {
        ordersService.createOrder(token, orderRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Order Created");
    }

    //Each user has a unique table number. Works as a user ID.
    //Below is equivalent to getting all orders for a specific user.
    @GetMapping("/{tableNum}")
    @ApiOperation("display all orders for a specific user")
    public ResponseEntity<List<OrderResponse>> getOrdersByTableNum(@PathVariable int tableNum) {
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.getOrdersByTableId(tableNum));
    }

    //Below is equivalent to cancelling an order
    @DeleteMapping("/{orderId}")
    @ApiOperation("user can cancel one of their orders")
    public ResponseEntity<String> deleteOrderById(@PathVariable int orderId) {
        ordersService.deleteOrderById(orderId);
        return ResponseEntity.status(HttpStatus.OK).body("Order with Id '" + orderId + "' deleted successfully");
    }

}
