package com.sushant.productservice.inheritancedemo.mappedsuperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "mappedsuperclass_instructor")
public class Instructor extends User {
    private String favouriteStudent;
}
