package com.sushant.productservice.inheritancedemo.joinedtable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Instructor extends User {
    private String favouriteStudent;
}
