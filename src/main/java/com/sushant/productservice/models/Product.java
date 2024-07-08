package com.sushant.productservice.models;

import jakarta.persistence.*;
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
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})//THis cascade type will create a new Product and category if category doesn't exist
    @JoinColumn(name = "category")
    private Category category;

}
