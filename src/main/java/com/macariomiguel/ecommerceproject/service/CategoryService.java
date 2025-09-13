package com.macariomiguel.ecommerceproject.service;

import com.macariomiguel.ecommerceproject.entity.Category;
import com.macariomiguel.ecommerceproject.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> findByName(String name){
        return categoryRepository.findByNameIgnoreCase(name);
    }

}
