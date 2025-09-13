package com.macariomiguel.ecommerceproject.service;

import com.macariomiguel.ecommerceproject.dto.CategoryResponseDTO;
import com.macariomiguel.ecommerceproject.entity.Category;
import com.macariomiguel.ecommerceproject.exceptions.ResourceNotFoundException;
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

    public void createCategory(CategoryResponseDTO data){
        Category category = new Category(data);
        categoryRepository.save(category);
    }

    public void deleteCategory(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        categoryRepository.deleteById(id);
    }

}
