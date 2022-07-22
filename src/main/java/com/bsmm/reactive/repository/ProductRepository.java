package com.bsmm.reactive.repository;

import com.bsmm.reactive.domain.dto.ProductDto;
import com.bsmm.reactive.domain.entity.ProductEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<ProductEntity, String> {
    Flux<ProductDto> findByPriceBetween(Mono<Double> min, Mono<Double> max);
}
