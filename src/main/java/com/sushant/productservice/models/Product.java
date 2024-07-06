package com.sushant.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {

    private String title, description, imageUrl;
    private double price;
    /*
    This is a non-primitive attribute
    Cardinality between a Product and a category
     1  -> 1
     m  <- 1
    -----------
     m  : 1 */
    @ManyToOne
    private Category category;

}
