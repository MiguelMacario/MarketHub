package com.macariomiguel.ecommerceproject.dto;


import com.macariomiguel.ecommerceproject.entity.CartItem;


public record CartItemResponseDTO(Integer quantity) {
    public CartItemResponseDTO(CartItem item) {
        this(
                item.getQuantity()
        );
    }
}
