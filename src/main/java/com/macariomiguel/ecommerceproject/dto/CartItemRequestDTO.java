package com.macariomiguel.ecommerceproject.dto;

import lombok.NonNull;

public record CartItemRequestDTO(@NonNull String sku, Integer quantity) {
}
