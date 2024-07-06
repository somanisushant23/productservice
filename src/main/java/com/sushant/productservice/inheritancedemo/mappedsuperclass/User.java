package com.sushant.productservice.inheritancedemo.mappedsuperclass;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass//no table for parent class
public class User {
    @Id
    private Long id;
    private String name, email;
}
