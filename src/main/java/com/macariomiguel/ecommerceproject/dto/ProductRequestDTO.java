package com.macariomiguel.ecommerceproject.dto;

public record ProductRequestDTO(String name, String description, Double price, Long category_id, String sku) {
}
