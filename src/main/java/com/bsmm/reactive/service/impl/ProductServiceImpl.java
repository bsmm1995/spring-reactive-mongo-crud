package com.bsmm.reactive.service.impl;

import com.bsmm.reactive.domain.dto.ProductDto;
import com.bsmm.reactive.repository.ProductRepository;
import com.bsmm.reactive.service.ProductService;
import com.bsmm.reactive.utils.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Flux<ProductDto> getAll() {
        log.info("---GET ALL---");
        return productRepository.findAll().map(ProductMapper::entityToDto);
    }

    @Override
    public Mono<ProductDto> getById(String id) {
        log.info("id=" + id);
        return productRepository.findById(id).map(ProductMapper::entityToDto);
    }

    @Override
    public Flux<ProductDto> getInRange(double min, double max) {
        log.info("min=" + min + ", max=" + max);
        return productRepository.findByPriceBetween(Mono.just(min), Mono.just(max));
    }

    @Override
    public Mono<ProductDto> create(Mono<ProductDto> data) {
        log.info("data=" + data);
        return data.map(ProductMapper::dtoToEntity)
                .flatMap(productRepository::insert)
                .map(ProductMapper::entityToDto);
    }

    @Override
    public Mono<ProductDto> update(String id, Mono<ProductDto> data) {
        log.info("Id=" + id + ", data=" + data);
        return productRepository.findById(id)
                .flatMap(p -> data.map(ProductMapper::dtoToEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(productRepository::save)
                .map(ProductMapper::entityToDto);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        log.info("id=" + id);
        return productRepository.deleteById(id);
    }
}
