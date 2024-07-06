package com.sushant.productservice.inheritancedemo.singleclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity//this wil NOT create the table
@DiscriminatorValue(value = "2")
public class Instructor extends User {
    private String favouriteStudent;
}
