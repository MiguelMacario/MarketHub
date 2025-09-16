package com.macariomiguel.ecommerceproject.dto;

import lombok.NonNull;

import java.math.BigDecimal;

public record ProductRequestDTO(String name, String description, BigDecimal price, Long category_id, @NonNull String sku) {
}
