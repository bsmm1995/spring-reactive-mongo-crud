package com.bsmm.reactive.utils;

import com.bsmm.reactive.domain.dto.ProductDto;
import com.bsmm.reactive.domain.entity.ProductEntity;
import org.springframework.beans.BeanUtils;

public class ProductMapper {

    private ProductMapper() {
    }

    public static ProductDto entityToDto(ProductEntity product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    public static ProductEntity dtoToEntity(ProductDto productDto) {
        ProductEntity product = new ProductEntity();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}
