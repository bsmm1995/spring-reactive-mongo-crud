package com.bsmm.reactive.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String id;
    @NotEmpty
    private String name;
    @NotNull
    private int qty;
    @PositiveOrZero
    private double price;
}