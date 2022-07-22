package com.bsmm.reactive.service;

import com.bsmm.reactive.domain.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Flux<ProductDto> getAll();

    Mono<ProductDto> getById(String id);

    Flux<ProductDto> getInRange(double min, double max);

    Mono<ProductDto> create(Mono<ProductDto> data);

    Mono<ProductDto> update(String id, Mono<ProductDto> data);

    Mono<Void> deleteById(String id);
}
