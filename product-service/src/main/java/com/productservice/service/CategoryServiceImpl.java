package com.productservice.service;

import com.productservice.dto.validate.ItemToValidateDTO;
import com.productservice.dto.validate.ProductsValidateRequestDTO;
import com.productservice.entity.Category;
import com.productservice.entity.Product;
import com.productservice.exceptions.business.ObjectNotFoundException;
import com.productservice.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Mono<Page<Category>> getCategories(Pageable pageable) {
        return null;
    }

    @Override
    public Mono<Category> getCategoryById(Long id) {
        return Mono.just(categoryRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("A category for the given id %s not found", id))));
    }

    @Override
    public Category create(Category category) {
        return null;
    }

    @Override
    public Category update(Long id, Category category) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }


}
