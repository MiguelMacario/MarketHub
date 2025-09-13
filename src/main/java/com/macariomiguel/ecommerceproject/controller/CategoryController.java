package com.macariomiguel.ecommerceproject.controller;


import com.macariomiguel.ecommerceproject.dto.CategoryResponseDTO;
import com.macariomiguel.ecommerceproject.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categorys")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Void> createCategory(@RequestBody CategoryResponseDTO data){
        categoryService.createCategory(data);
        return ResponseEntity.ok().build();
    }


}
