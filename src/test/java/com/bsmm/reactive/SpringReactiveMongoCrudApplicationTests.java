package com.bsmm.reactive;

import com.bsmm.reactive.controller.ProductController;
import com.bsmm.reactive.domain.dto.ProductDto;
import com.bsmm.reactive.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(ProductController.class)
class SpringReactiveMongoCrudApplicationTests {
    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private ProductServiceImpl service;

    @Test
    void addProductTest() {
        Mono<ProductDto> productDtoMono = Mono.just(new ProductDto("102", "mobile", 1, 10000));
        when(service.create(productDtoMono)).thenReturn(productDtoMono);

        webTestClient.post().uri("/products")
                .body(Mono.just(productDtoMono), ProductDto.class)
                .exchange()
                .expectStatus().isOk();
    }


    @Test
    void getProductsTest() {
        Flux<ProductDto> productDtoFlux = Flux.just(new ProductDto("102", "mobile", 1, 10000),
                new ProductDto("103", "TV", 1, 50000));
        when(service.getAll()).thenReturn(productDtoFlux);

        Flux<ProductDto> responseBody = webTestClient.get().uri("/products")
                .exchange()
                .expectStatus().isOk()
                .returnResult(ProductDto.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(new ProductDto("102", "mobile", 1, 10000))
                .expectNext(new ProductDto("103", "TV", 1, 50000))
                .verifyComplete();
    }


    @Test
    void getProductTest() {
        Mono<ProductDto> productDtoMono = Mono.just(new ProductDto("102", "mobile", 1, 10000));
        when(service.getById(any())).thenReturn(productDtoMono);

        Flux<ProductDto> responseBody = webTestClient.get().uri("/products/102")
                .exchange()
                .expectStatus().isOk()
                .returnResult(ProductDto.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNextMatches(p -> p.getName().equals("mobile"))
                .verifyComplete();
    }


    @Test
    void updateProductTest() {
        Mono<ProductDto> productDtoMono = Mono.just(new ProductDto("102", "mobile", 1, 10000));
        when(service.update("102", productDtoMono)).thenReturn(productDtoMono);

        webTestClient.put().uri("/products/update/102")
                .body(Mono.just(productDtoMono), ProductDto.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void deleteProductTest() {
        given(service.deleteById(any())).willReturn(Mono.empty());
        webTestClient.delete().uri("/products/delete/102")
                .exchange()
                .expectStatus().isOk();
    }

}
