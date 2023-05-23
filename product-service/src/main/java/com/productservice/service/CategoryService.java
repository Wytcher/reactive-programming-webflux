package com.productservice.service;

import com.productservice.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface CategoryService {

    Mono<Page<Category>> getCategories(Pageable pageable);

    Mono<Category> getCategoryById(Long id);

    Category create(Category category);

    Category update(Long id, Category category);

    void delete(Long id);
}
