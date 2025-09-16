package com.macariomiguel.ecommerceproject.dto;

import lombok.NonNull;

public record ProductRequestDTO(String name, String description, Double price, Long category_id, @NonNull String sku) {
}
