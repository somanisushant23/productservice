package com.sushant.productservice.dtos;

import com.sushant.productservice.models.Payment;
import com.sushant.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderCreationDto {

    private List<Product> products;

    private String userEmail;

    private int amount;

    private Payment payment;

    private Date createdAt;
}
