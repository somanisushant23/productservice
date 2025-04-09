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

    @ManyToOne(cascade = {CascadeType.ALL})//This cascade type will create a new Product and category if category doesn't exist
    private Category category;

}
