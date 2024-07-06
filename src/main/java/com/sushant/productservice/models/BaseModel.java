package com.sushant.productservice.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass//this will tell ORM, that we don't need table for this table.
public class BaseModel {
    @Id
    private Long id;

    private Date createdAt, lastUpdatedAt;

    private boolean isDeleted;
}
