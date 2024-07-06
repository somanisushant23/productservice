package com.sushant.productservice.inheritancedemo.tableperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tbc_user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)//In addition to mapped super class, this creates table of base class too!!
public class User {
    @Id
    private Long id;
    private String name, email;
}
