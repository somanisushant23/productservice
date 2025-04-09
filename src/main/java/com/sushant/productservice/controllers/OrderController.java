package com.sushant.productservice.controllers;

import com.sushant.productservice.dtos.OrderCreationDto;
import com.sushant.productservice.exceptions.NotFoundException;
import com.sushant.productservice.models.Order;
import com.sushant.productservice.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderCreationDto orderCreationDto) {
        return orderService.createOrder(orderCreationDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable long id) throws NotFoundException {
        return orderService.getOrderDetails(id);
    }
}
