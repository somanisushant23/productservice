package com.sushant.productservice.services;

import com.sushant.productservice.dtos.OrderCreationDto;
import com.sushant.productservice.exceptions.NotFoundException;
import com.sushant.productservice.models.Order;
import com.sushant.productservice.repositories.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository repository) {
        this.orderRepository = repository;
    }

    public ResponseEntity<String> createOrder(OrderCreationDto orderCreationDto) {
        return new ResponseEntity<>("Order created", HttpStatus.CREATED);
    }

    public ResponseEntity<Order> getOrderDetails(Long id) throws NotFoundException {
        return new ResponseEntity<>(new Order(), HttpStatus.OK);
    }
}
