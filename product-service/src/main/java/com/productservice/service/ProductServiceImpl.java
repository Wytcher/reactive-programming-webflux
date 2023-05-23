package com.productservice.service;

import com.productservice.dto.validate.ItemToValidateDTO;
import com.productservice.dto.validate.ProductsValidateRequestDTO;
import com.productservice.entity.Category;
import com.productservice.entity.Product;
import com.productservice.exceptions.business.BusinessRuleException;
import com.productservice.exceptions.business.ObjectNotFoundException;
import com.productservice.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryServiceImpl categoryService;

    public ProductServiceImpl(ProductRepository productRepository, CategoryServiceImpl categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Mono<Page<Product>> getProducts(Pageable pageable) {
        return Mono.just(productRepository.findAll(pageable));
    }

    @Override
    public Mono<Product> getProductById(UUID id) {
        return Mono.fromCallable(() -> productRepository
                        .findProductById(id)
                        .orElseThrow(() -> new ObjectNotFoundException(String.format("A product for the given id %s not found", id))))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Product> create(Product product, Long categoryId) {
        return categoryService.getCategoryById(categoryId).flatMap(category -> {
            product.setCategory(category);
            return Mono
                    .fromCallable(() -> productRepository.save(product))
                    .subscribeOn(Schedulers.boundedElastic());
        });

    }

    @Override
    public Mono<Product> update(Long id, Product product) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Flux<Boolean> validateProductStock(ProductsValidateRequestDTO productsValidateRequestDTO) {
        return Flux.fromIterable(productsValidateRequestDTO.getProducts())
                .publishOn(Schedulers.boundedElastic())
                .flatMap(productToValidate -> {
                    Product product = productRepository
                            .findProductById(productToValidate.getIdProduct())
                            .orElseThrow(() -> new ObjectNotFoundException(String.format("A product for the given id %s was not found", productToValidate.getIdProduct())));

                    if (product.getStock() < productToValidate.getQuantity()) {
                        return Flux.error(new BusinessRuleException("There is not enough stock for this purchase"));
                    }

                    return Mono.just(true);
                })
                .publishOn(Schedulers.boundedElastic());
    }
}
