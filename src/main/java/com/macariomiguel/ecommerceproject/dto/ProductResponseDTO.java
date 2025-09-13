package com.macariomiguel.ecommerceproject.dto;

import com.macariomiguel.ecommerceproject.entity.Product;

public record ProductResponseDTO(String name, String description, Double price, Long category_id) {
    public ProductResponseDTO(Product product) {
        this (
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory().getId()
        );

    }
}
