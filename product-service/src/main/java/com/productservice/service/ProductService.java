package com.productservice.service;

import com.productservice.dto.validate.ProductsValidateRequestDTO;
import com.productservice.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ProductService {

    Mono<Page<Product>> getProducts(Pageable pageable);

    Mono<Product> getProductById(UUID id);

    Mono<Product> create(Product product, Long categoryId);

    Mono<Product> update(Long id, Product product);

    void delete(Long id);

    Flux<Boolean> validateProductStock(ProductsValidateRequestDTO productsValidateRequestDTO);
}
