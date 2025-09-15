package com.macariomiguel.ecommerceproject.dto;


import com.macariomiguel.ecommerceproject.entity.CartItem;

public record CartItemResponseDTO(String name, Integer quantity, Double price) {
    public CartItemResponseDTO(CartItem item) {
        this(
                item.getName(),
                item.getQuantity(),
                item.getPrice()
        );
    }
}
