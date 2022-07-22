package com.bsmm.reactive.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "products")
public class ProductEntity {
    @Id
    @Indexed(unique = true)
    private String id;
    @TextIndexed
    private String name;
    private int qty;
    private double price;
}
