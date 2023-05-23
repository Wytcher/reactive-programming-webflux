package com.productservice.controller;

import com.productservice.dto.ProductRequestDTO;
import com.productservice.dto.ProductResponseDTO;
import com.productservice.dto.validate.ProductsValidateRequestDTO;
import com.productservice.dto.validate.ValidateResponseDTO;
import com.productservice.entity.Product;
import com.productservice.service.ProductServiceImpl;
import com.productservice.utils.MapperUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final MapperUtils mapper = new MapperUtils();
    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping
    public Mono<ResponseEntity<Page<ProductResponseDTO>>> getProduct(Pageable pageable){
        Mono<Page<Product>> productsMono = productService.getProducts(pageable);
        return productsMono
                .map(pageableProducts -> mapper.mapEntityPageIntoDtoPage(pageableProducts, ProductResponseDTO.class))
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ProductResponseDTO>> getProductById(@PathVariable UUID id) {
        Mono<Product> productMono = productService.getProductById(id);
        return productMono
                .map(product -> mapper.map(product, ProductResponseDTO.class))
                .map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<ProductResponseDTO>> create(@RequestBody ProductRequestDTO productRequestDTO) {
        Product productEntity = mapper.map(productRequestDTO, Product.class);
        Mono<Product> productMono = productService.create(productEntity, productRequestDTO.getCategoryId());
        return productMono
                .map(product -> mapper.map(product, ProductResponseDTO.class))
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
    }

    @PostMapping("/validate")
    public Mono<ResponseEntity<ValidateResponseDTO>> validateProducts(@RequestBody ProductsValidateRequestDTO productsValidateRequestDTO) {
        Flux<Boolean> validated = productService.validateProductStock(productsValidateRequestDTO);
        ValidateResponseDTO response = new ValidateResponseDTO();
        response.setMessage("Ok");
        return validated.collectList()
                .flatMap(result -> Mono.just(response).map(ResponseEntity::ok));
    }

}
