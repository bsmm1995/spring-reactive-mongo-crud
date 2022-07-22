package com.bsmm.reactive.controller;

import com.bsmm.reactive.domain.dto.ProductDto;
import com.bsmm.reactive.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Flux<ProductDto> getAll() {
        log.info("---GET ALL---");
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<ProductDto> getByID(@PathVariable String id) {
        log.info("id=" + id);
        return productService.getById(id);
    }

    @GetMapping("/range")
    public Flux<ProductDto> getInRange(@RequestParam("min") double min, @RequestParam("max") double max) {
        log.info("min=" + min + ", max=" + max);
        return productService.getInRange(min, max);
    }

    @PostMapping
    public Mono<ProductDto> create(@RequestBody @Valid Mono<ProductDto> data) {
        log.info("data=" + data);
        return productService.create(data);
    }

    @PutMapping("/{id}")
    public Mono<ProductDto> update(@RequestBody @Valid Mono<ProductDto> data, @PathVariable String id) {
        log.info("Id=" + id + ", data=" + data);
        return productService.update(id, data);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        log.info("id=" + id);
        return productService.deleteById(id);
    }
}
