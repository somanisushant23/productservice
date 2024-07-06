package com.sushant.productservice.inheritancedemo.singleclass;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_user")//only 1 table for parent class
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "userType", discriminatorType = DiscriminatorType.INTEGER)//this will determine if record in row is for mentor or instructor
@DiscriminatorValue(value = "0")
public class User {
    @Id
    private Long id;
    private String name, email;
}
